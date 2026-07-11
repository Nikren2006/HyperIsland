package miui.systemui.clouddata;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MiuiSettings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class CloudDataManager {
    public static final Companion Companion = new Companion(null);
    public static final String TAG = "CloudDataManager";
    private static final Uri URI;
    private static boolean cloudDataUpdated;
    private final Context context;
    private final List<CloudDataListener> listeners;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getCloudDataInt(Context ctx, String module, String key) {
            n.g(ctx, "ctx");
            n.g(module, "module");
            n.g(key, "key");
            ContentResolver contentResolver = ctx.getContentResolver();
            n.f(contentResolver, "getContentResolver(...)");
            return getCloudDataInt(contentResolver, module, key);
        }

        public final List<MiuiSettings.SettingsCloudData.CloudData> getCloudDataList(Context ctx, String module) {
            n.g(ctx, "ctx");
            n.g(module, "module");
            ContentResolver contentResolver = ctx.getContentResolver();
            n.f(contentResolver, "getContentResolver(...)");
            return getCloudDataList(contentResolver, module);
        }

        public final MiuiSettings.SettingsCloudData.CloudData getCloudDataSingle(Context ctx, String module, String key) {
            n.g(ctx, "ctx");
            n.g(module, "module");
            n.g(key, "key");
            ContentResolver contentResolver = ctx.getContentResolver();
            n.f(contentResolver, "getContentResolver(...)");
            return getCloudDataSingle(contentResolver, module, key);
        }

        public final String getCloudDataString(Context ctx, String module, String key) {
            n.g(ctx, "ctx");
            n.g(module, "module");
            n.g(key, "key");
            ContentResolver contentResolver = ctx.getContentResolver();
            n.f(contentResolver, "getContentResolver(...)");
            return getCloudDataString(contentResolver, module, key);
        }

        public final boolean getCloudDataUpdated() {
            return CloudDataManager.cloudDataUpdated;
        }

        public final Uri getURI() {
            return CloudDataManager.URI;
        }

        public final void setCloudDataUpdated(boolean z2) {
            CloudDataManager.cloudDataUpdated = z2;
        }

        private Companion() {
        }

        public final int getCloudDataInt(ContentResolver resolver, String module, String key) {
            n.g(resolver, "resolver");
            n.g(module, "module");
            n.g(key, "key");
            return MiuiSettings.SettingsCloudData.getCloudDataInt(resolver, module, key, Integer.MIN_VALUE);
        }

        public final List<MiuiSettings.SettingsCloudData.CloudData> getCloudDataList(ContentResolver resolver, String module) {
            n.g(resolver, "resolver");
            n.g(module, "module");
            List<MiuiSettings.SettingsCloudData.CloudData> cloudDataList = MiuiSettings.SettingsCloudData.getCloudDataList(resolver, module);
            n.f(cloudDataList, "getCloudDataList(...)");
            return cloudDataList;
        }

        public final MiuiSettings.SettingsCloudData.CloudData getCloudDataSingle(ContentResolver resolver, String module, String key) {
            n.g(resolver, "resolver");
            n.g(module, "module");
            n.g(key, "key");
            return getCloudDataSingle(resolver, module, key, "", false);
        }

        public final String getCloudDataString(ContentResolver resolver, String module, String key) {
            n.g(resolver, "resolver");
            n.g(module, "module");
            n.g(key, "key");
            String cloudDataString = MiuiSettings.SettingsCloudData.getCloudDataString(resolver, module, key, "");
            n.f(cloudDataString, "getCloudDataString(...)");
            return cloudDataString;
        }

        public final MiuiSettings.SettingsCloudData.CloudData getCloudDataSingle(Context ctx, String module, String key, String propertyName, boolean z2) {
            n.g(ctx, "ctx");
            n.g(module, "module");
            n.g(key, "key");
            n.g(propertyName, "propertyName");
            ContentResolver contentResolver = ctx.getContentResolver();
            n.f(contentResolver, "getContentResolver(...)");
            return getCloudDataSingle(contentResolver, module, key, propertyName, z2);
        }

        public final MiuiSettings.SettingsCloudData.CloudData getCloudDataSingle(ContentResolver resolver, String module, String key, String propertyName, boolean z2) {
            n.g(resolver, "resolver");
            n.g(module, "module");
            n.g(key, "key");
            n.g(propertyName, "propertyName");
            MiuiSettings.SettingsCloudData.CloudData cloudDataSingle = MiuiSettings.SettingsCloudData.getCloudDataSingle(resolver, module, key, propertyName, z2);
            n.f(cloudDataSingle, "getCloudDataSingle(...)");
            return cloudDataSingle;
        }
    }

    static {
        Uri uri = Uri.parse("content://com.android.settings.cloud.CloudSettings/cloud_all_data/notify");
        n.f(uri, "parse(...)");
        URI = uri;
    }

    public CloudDataManager(Context ctx) {
        n.g(ctx, "ctx");
        this.context = ctx;
        this.listeners = new ArrayList();
        ctx.getContentResolver().registerContentObserver(URI, false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: miui.systemui.clouddata.CloudDataManager.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                Iterator it = CloudDataManager.this.listeners.iterator();
                while (it.hasNext()) {
                    ((CloudDataListener) it.next()).onCloudDataUpdate(z2);
                    CloudDataManager.Companion.setCloudDataUpdated(true);
                }
            }
        });
    }

    public final void registerListener(CloudDataListener listener) {
        n.g(listener, "listener");
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
        listener.onCloudDataUpdate(false);
    }
}
