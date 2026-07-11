package miui.systemui.devicecontrols.management;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Slog;
import com.android.systemui.SystemVolumeController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: loaded from: classes3.dex */
public class ServiceListing {
    private final boolean mAddDeviceLockedFlags;
    private Executor mBgExecutor;
    private final List<Callback> mCallbacks;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final HashSet<ComponentName> mEnabledServices;
    private final String mIntentAction;
    private boolean mListening;
    private final String mNoun;
    private final BroadcastReceiver mPackageReceiver;
    private final String mPermission;
    private final List<ServiceInfo> mServices;
    private final String mSetting;
    private final ContentObserver mSettingsObserver;
    private final String mTag;
    private final Predicate mValidator;

    public static class Builder {
        private boolean mAddDeviceLockedFlags = false;
        private Executor mBgExecutor;
        private final Context mContext;
        private String mIntentAction;
        private String mNoun;
        private String mPermission;
        private String mSetting;
        private String mTag;
        private Predicate mValidator;

        public Builder(Context context) {
            this.mContext = context;
        }

        public ServiceListing build() {
            return new ServiceListing(this.mContext, this.mTag, this.mSetting, this.mIntentAction, this.mPermission, this.mNoun, this.mAddDeviceLockedFlags, this.mBgExecutor, this.mValidator);
        }

        public Builder setAddDeviceLockedFlags(boolean z2) {
            this.mAddDeviceLockedFlags = z2;
            return this;
        }

        public Builder setBgExecutor(Executor executor) {
            this.mBgExecutor = executor;
            return this;
        }

        public Builder setIntentAction(String str) {
            this.mIntentAction = str;
            return this;
        }

        public Builder setNoun(String str) {
            this.mNoun = str;
            return this;
        }

        public Builder setPermission(String str) {
            this.mPermission = str;
            return this;
        }

        public Builder setSetting(String str) {
            this.mSetting = str;
            return this;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public Builder setValidator(Predicate<ServiceInfo> predicate) {
            this.mValidator = predicate;
            return this;
        }
    }

    public interface Callback {
        void onServicesReloaded(List<ServiceInfo> list, boolean z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reload$0(boolean z2) {
        loadEnabledServices();
        this.mServices.clear();
        Iterator it = this.mContext.getPackageManager().queryIntentServicesAsUser(new Intent(this.mIntentAction), this.mAddDeviceLockedFlags ? 786564 : 132, ActivityManager.getCurrentUser()).iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = ((ResolveInfo) it.next()).serviceInfo;
            if (this.mPermission.equals(serviceInfo.permission)) {
                Predicate predicate = this.mValidator;
                if (predicate == null || predicate.test(serviceInfo)) {
                    this.mServices.add(serviceInfo);
                }
            } else {
                Slog.w(this.mTag, "Skipping " + this.mNoun + " service " + serviceInfo.packageName + "/" + serviceInfo.name + ": it does not require the permission " + this.mPermission);
            }
        }
        Iterator<Callback> it2 = this.mCallbacks.iterator();
        while (it2.hasNext()) {
            it2.next().onServicesReloaded(this.mServices, z2);
        }
    }

    private void loadEnabledServices() {
        this.mEnabledServices.clear();
        String string = Settings.Secure.getString(this.mContentResolver, this.mSetting);
        if (string == null || "".equals(string)) {
            return;
        }
        for (String str : string.split(MethodCodeHelper.IDENTITY_INFO_SEPARATOR)) {
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
            if (componentNameUnflattenFromString != null) {
                this.mEnabledServices.add(componentNameUnflattenFromString);
            }
        }
    }

    private void saveEnabledServices() {
        StringBuilder sb = null;
        for (ComponentName componentName : this.mEnabledServices) {
            if (sb == null) {
                sb = new StringBuilder();
            } else {
                sb.append(':');
            }
            sb.append(componentName.flattenToString());
        }
        Settings.Secure.putString(this.mContentResolver, this.mSetting, sb != null ? sb.toString() : "");
    }

    public void addCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public boolean isEnabled(ComponentName componentName) {
        return this.mEnabledServices.contains(componentName);
    }

    public void reload() {
        reload(false);
    }

    public void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void setEnabled(ComponentName componentName, boolean z2) {
        if (z2) {
            this.mEnabledServices.add(componentName);
        } else {
            this.mEnabledServices.remove(componentName);
        }
        saveEnabledServices();
    }

    public void setListening(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        if (!z2) {
            this.mContext.unregisterReceiver(this.mPackageReceiver);
            this.mContentResolver.unregisterContentObserver(this.mSettingsObserver);
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction(SystemVolumeController.ACTION_PACKAGE_REPLACED);
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this.mPackageReceiver, intentFilter, 2);
        this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(this.mSetting), false, this.mSettingsObserver);
    }

    private ServiceListing(Context context, String str, String str2, String str3, String str4, String str5, boolean z2, Executor executor, Predicate predicate) {
        this.mEnabledServices = new HashSet<>();
        this.mServices = new ArrayList();
        this.mCallbacks = new ArrayList();
        this.mSettingsObserver = new ContentObserver(new Handler()) { // from class: miui.systemui.devicecontrols.management.ServiceListing.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z3, Uri uri) {
                ServiceListing.this.reload();
            }
        };
        this.mPackageReceiver = new BroadcastReceiver() { // from class: miui.systemui.devicecontrols.management.ServiceListing.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                ServiceListing.this.reload();
            }
        };
        this.mContentResolver = context.getContentResolver();
        this.mContext = context;
        this.mTag = str;
        this.mSetting = str2;
        this.mIntentAction = str3;
        this.mPermission = str4;
        this.mNoun = str5;
        this.mAddDeviceLockedFlags = z2;
        this.mBgExecutor = executor;
        this.mValidator = predicate;
    }

    public void reload(final boolean z2) {
        this.mBgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f5632a.lambda$reload$0(z2);
            }
        });
    }
}
