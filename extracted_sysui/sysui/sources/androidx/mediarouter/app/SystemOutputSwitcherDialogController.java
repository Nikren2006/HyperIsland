package androidx.mediarouter.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import androidx.annotation.NonNull;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class SystemOutputSwitcherDialogController {
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R = "com.android.settings.panel.action.MEDIA_OUTPUT";
    private static final String OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S = "com.android.systemui.action.LAUNCH_MEDIA_OUTPUT_DIALOG";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R = "com.android.settings.panel.extra.PACKAGE_NAME";
    private static final String OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_S = "package_name";
    private static final String PACKAGE_NAME_SYSTEM_UI = "com.android.systemui";

    private SystemOutputSwitcherDialogController() {
    }

    private static boolean isRunningOnWear(@NonNull Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }

    private static boolean showBluetoothSettingsFragment(@NonNull Context context) {
        ApplicationInfo applicationInfo;
        Intent intentPutExtra = new Intent("android.settings.BLUETOOTH_SETTINGS").addFlags(268468224).putExtra("EXTRA_CONNECTION_ONLY", true).putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1);
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.startActivity(intentPutExtra);
                return true;
            }
        }
        return false;
    }

    public static boolean showDialog(@NonNull Context context) {
        if (showDialogForAndroidSAndAbove(context) || showDialogForAndroidR(context)) {
            return true;
        }
        return isRunningOnWear(context) && showBluetoothSettingsFragment(context);
    }

    private static boolean showDialogForAndroidR(@NonNull Context context) {
        ApplicationInfo applicationInfo;
        Intent intentPutExtra = new Intent().addFlags(268435456).setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_R).putExtra(OUTPUT_SWITCHER_INTENT_KEY_PACKAGE_NAME_ANDROID_R, context.getPackageName());
        Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.startActivity(intentPutExtra);
                return true;
            }
        }
        return false;
    }

    private static boolean showDialogForAndroidSAndAbove(@NonNull Context context) {
        ApplicationInfo applicationInfo;
        Intent intentPutExtra = new Intent().setAction(OUTPUT_SWITCHER_INTENT_ACTION_ANDROID_S).setPackage(PACKAGE_NAME_SYSTEM_UI).putExtra("package_name", context.getPackageName());
        Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(intentPutExtra, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null && (applicationInfo.flags & 129) != 0) {
                context.sendBroadcast(intentPutExtra);
                return true;
            }
        }
        return false;
    }
}
