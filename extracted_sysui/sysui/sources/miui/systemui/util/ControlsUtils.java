package miui.systemui.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class ControlsUtils {
    public static final String EXTRA_ANIMATE = "extra_animate";
    public static final String GOOGLE_HOME_PACKAGE = "com.google.android.apps.chromecast.app";
    public static final String GO_TO_DEVICE_APP = "go_to_device_app";
    public static final ControlsUtils INSTANCE = new ControlsUtils();
    public static final String MI_SMART_HUB_ACTION = "com.milink.service.deviceworld";
    public static final String SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL = "miui_control_center_device_controller";
    private static final String SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL_FEATURE_OPEN_STATUS = "hyperos_device_controls_feature_enabled";
    private static final String TAG = "ControlsUtils";
    private static final Uri URI_DEVICE_CONTROL_PACKAGE_NAME;

    static {
        Uri uriFor = Settings.Secure.getUriFor(SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL);
        kotlin.jvm.internal.n.f(uriFor, "getUriFor(...)");
        URI_DEVICE_CONTROL_PACKAGE_NAME = uriFor;
    }

    private ControlsUtils() {
    }

    private final List<ResolveInfo> getControlsProviderServices(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return I0.m.h();
        }
        try {
            ArrayList arrayList = (ArrayList) packageManager.getClass().getMethod("queryIntentServicesAsUser", Intent.class, PackageManager.ResolveInfoFlags.class, Integer.TYPE).invoke(packageManager, new Intent("android.service.controls.ControlsProviderService"), PackageManager.ResolveInfoFlags.of(786564), Integer.valueOf(context.getUserId()));
            if (arrayList == null) {
                return I0.m.h();
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (!((ResolveInfo) obj).serviceInfo.packageName.equals("com.xiaomi.smarthome")) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            Log.w(TAG, "getControlsProviderServices.", th);
            return I0.m.h();
        }
    }

    public final Uri getURI_DEVICE_CONTROL_PACKAGE_NAME() {
        return URI_DEVICE_CONTROL_PACKAGE_NAME;
    }

    public final boolean isAppDevicesServiceStillLived(Context context, String packageName) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(packageName, "packageName");
        if (packageName.equals(MI_SMART_HUB_ACTION)) {
            return false;
        }
        List<ResolveInfo> controlsProviderServices = getControlsProviderServices(context);
        if (packageName.length() > 0) {
            Iterator<T> it = controlsProviderServices.iterator();
            while (it.hasNext()) {
                if (((ResolveInfo) it.next()).serviceInfo.packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isFeatureSupport(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        boolean z2 = Settings.System.getInt(context.getContentResolver(), SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL_FEATURE_OPEN_STATUS, 0) == 1;
        Log.d(TAG, "isFeatureSupport: " + z2);
        return z2;
    }
}
