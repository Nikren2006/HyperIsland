package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Handler;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.RegisteredMediaRouteProvider;
import com.android.systemui.SystemVolumeController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class RegisteredMediaRouteProviderWatcher {
    final Callback mCallback;
    private final Context mContext;
    private final PackageManager mPackageManager;
    private boolean mRunning;
    private final ArrayList<RegisteredMediaRouteProvider> mProviders = new ArrayList<>();
    private final BroadcastReceiver mScanPackagesReceiver = new BroadcastReceiver() { // from class: androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            RegisteredMediaRouteProviderWatcher.this.scanPackages();
        }
    };
    private final Runnable mScanPackagesRunnable = new Runnable() { // from class: androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher.2
        @Override // java.lang.Runnable
        public void run() {
            RegisteredMediaRouteProviderWatcher.this.scanPackages();
        }
    };
    private final Handler mHandler = new Handler();

    @RequiresApi(33)
    public static class Api33 {
        private Api33() {
        }

        @DoNotInline
        public static void registerReceiver(@NonNull Context context, @NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter, Handler handler, int i2) {
            context.registerReceiver(broadcastReceiver, intentFilter, null, handler, i2);
        }
    }

    public interface Callback {
        void addProvider(@NonNull MediaRouteProvider mediaRouteProvider);

        void releaseProviderController(@NonNull RegisteredMediaRouteProvider registeredMediaRouteProvider, @NonNull MediaRouteProvider.RouteController routeController);

        void removeProvider(@NonNull MediaRouteProvider mediaRouteProvider);
    }

    public RegisteredMediaRouteProviderWatcher(Context context, Callback callback) {
        this.mContext = context;
        this.mCallback = callback;
        this.mPackageManager = context.getPackageManager();
    }

    private int findProvider(String str, String str2) {
        int size = this.mProviders.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mProviders.get(i2).hasComponentName(str, str2)) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scanPackages$0(RegisteredMediaRouteProvider registeredMediaRouteProvider, MediaRouteProvider.RouteController routeController) {
        this.mCallback.releaseProviderController(registeredMediaRouteProvider, routeController);
    }

    public static boolean listContainsServiceInfo(List<ServiceInfo> list, ServiceInfo serviceInfo) {
        if (serviceInfo != null && list != null && !list.isEmpty()) {
            for (ServiceInfo serviceInfo2 : list) {
                if (serviceInfo.packageName.equals(serviceInfo2.packageName) && serviceInfo.name.equals(serviceInfo2.name)) {
                    return true;
                }
            }
        }
        return false;
    }

    @NonNull
    @RequiresApi(30)
    public List<ServiceInfo> getMediaRoute2ProviderServices() {
        Intent intent = new Intent(MediaRoute2ProviderServiceAdapter.SERVICE_INTERFACE);
        ArrayList arrayList = new ArrayList();
        Iterator<ResolveInfo> it = this.mPackageManager.queryIntentServices(intent, 0).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().serviceInfo);
        }
        return arrayList;
    }

    public void rescan() {
        this.mHandler.post(this.mScanPackagesRunnable);
    }

    public void scanPackages() {
        int i2;
        if (this.mRunning) {
            new ArrayList();
            List<ServiceInfo> mediaRoute2ProviderServices = getMediaRoute2ProviderServices();
            int i3 = 0;
            Iterator<ResolveInfo> it = this.mPackageManager.queryIntentServices(new Intent("android.media.MediaRouteProviderService"), 0).iterator();
            while (it.hasNext()) {
                ServiceInfo serviceInfo = it.next().serviceInfo;
                if (serviceInfo != null && (!MediaRouter.isMediaTransferEnabled() || !listContainsServiceInfo(mediaRoute2ProviderServices, serviceInfo))) {
                    int iFindProvider = findProvider(serviceInfo.packageName, serviceInfo.name);
                    if (iFindProvider < 0) {
                        final RegisteredMediaRouteProvider registeredMediaRouteProvider = new RegisteredMediaRouteProvider(this.mContext, new ComponentName(serviceInfo.packageName, serviceInfo.name));
                        registeredMediaRouteProvider.setControllerCallback(new RegisteredMediaRouteProvider.ControllerCallback() { // from class: androidx.mediarouter.media.f
                            @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerCallback
                            public final void onControllerReleasedByProvider(MediaRouteProvider.RouteController routeController) {
                                this.f1269a.lambda$scanPackages$0(registeredMediaRouteProvider, routeController);
                            }
                        });
                        registeredMediaRouteProvider.start();
                        i2 = i3 + 1;
                        this.mProviders.add(i3, registeredMediaRouteProvider);
                        this.mCallback.addProvider(registeredMediaRouteProvider);
                    } else if (iFindProvider >= i3) {
                        RegisteredMediaRouteProvider registeredMediaRouteProvider2 = this.mProviders.get(iFindProvider);
                        registeredMediaRouteProvider2.start();
                        registeredMediaRouteProvider2.rebindIfDisconnected();
                        i2 = i3 + 1;
                        Collections.swap(this.mProviders, iFindProvider, i3);
                    }
                    i3 = i2;
                }
            }
            if (i3 < this.mProviders.size()) {
                for (int size = this.mProviders.size() - 1; size >= i3; size--) {
                    RegisteredMediaRouteProvider registeredMediaRouteProvider3 = this.mProviders.get(size);
                    this.mCallback.removeProvider(registeredMediaRouteProvider3);
                    this.mProviders.remove(registeredMediaRouteProvider3);
                    registeredMediaRouteProvider3.setControllerCallback(null);
                    registeredMediaRouteProvider3.stop();
                }
            }
        }
    }

    public void start() {
        if (this.mRunning) {
            return;
        }
        this.mRunning = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction(SystemVolumeController.ACTION_PACKAGE_REPLACED);
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addDataScheme("package");
        Api33.registerReceiver(this.mContext, this.mScanPackagesReceiver, intentFilter, this.mHandler, 4);
        this.mHandler.post(this.mScanPackagesRunnable);
    }

    public void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            this.mContext.unregisterReceiver(this.mScanPackagesReceiver);
            this.mHandler.removeCallbacks(this.mScanPackagesRunnable);
            for (int size = this.mProviders.size() - 1; size >= 0; size--) {
                this.mProviders.get(size).stop();
            }
        }
    }
}
