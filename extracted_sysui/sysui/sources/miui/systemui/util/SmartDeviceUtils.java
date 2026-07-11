package miui.systemui.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import miui.os.Build;
import miui.systemui.quicksettings.common.R;

/* JADX INFO: loaded from: classes4.dex */
public final class SmartDeviceUtils {
    private static final int DEVICE_CENTER_CLOSE = 0;
    private static final int DEVICE_CENTER_OPEN = 1;
    public static final SmartDeviceUtils INSTANCE = new SmartDeviceUtils();
    public static final int INTENT_FROM_DEVICE_CENTER = 1;
    public static final int INTENT_FROM_DEVICE_CONTROL = 2;
    public static final String LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS = "lockscreen_allow_trivial_controls";
    public static final String LOCKSCREEN_SMART_DEVICE_CONTROL = "lockscreen_smart_device_control";
    public static final String MI_LINK_PACKAGE_NAME = "com.milink.service";
    public static final String SMART_DEVICE_CONTROL = "smart_device_control";
    private static final String TAG = "SmartDeviceUtils";
    private static final Uri URI_LOCKSCREEN_SMART_DEVICE_CONTROL;
    private static final Uri URI_SMART_DEVICE_CONTROL;

    static {
        Uri uriFor = Settings.Secure.getUriFor(SMART_DEVICE_CONTROL);
        kotlin.jvm.internal.n.f(uriFor, "getUriFor(...)");
        URI_SMART_DEVICE_CONTROL = uriFor;
        Uri uriFor2 = Settings.Secure.getUriFor(LOCKSCREEN_SMART_DEVICE_CONTROL);
        kotlin.jvm.internal.n.f(uriFor2, "getUriFor(...)");
        URI_LOCKSCREEN_SMART_DEVICE_CONTROL = uriFor2;
    }

    private SmartDeviceUtils() {
    }

    private final int getLockScreenSmartDeviceControl(Context context) {
        int intForUser = Settings.Secure.getIntForUser(context.getContentResolver(), LOCKSCREEN_SMART_DEVICE_CONTROL, 0, 0);
        return ControlsUtils.INSTANCE.isFeatureSupport(context) ? intForUser <= 0 ? 0 : 1 : intForUser;
    }

    private final int getSmartDeviceControl(Context context) {
        int intForUser = Settings.Secure.getIntForUser(context.getContentResolver(), SMART_DEVICE_CONTROL, -1, 0);
        if (intForUser == -1) {
            if (ControlsUtils.INSTANCE.isFeatureSupport(context)) {
                if (DeviceUtils.ccDeviceCenterDefaultOff()) {
                    return 0;
                }
            } else if (Build.IS_INTERNATIONAL_BUILD) {
                return 2;
            }
        } else if (!ControlsUtils.INSTANCE.isFeatureSupport(context) || intForUser <= 0) {
            return intForUser;
        }
        return 1;
    }

    public final boolean checkDeviceCenterAvailable(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        if (!Build.IS_INTERNATIONAL_BUILD) {
            return true;
        }
        try {
            return context.getPackageManager().getApplicationInfo(MI_LINK_PACKAGE_NAME, 128).metaData.getBoolean("is_support_device_center", false);
        } catch (Throwable th) {
            Log.e(TAG, "MiLink available false " + th);
            return false;
        }
    }

    public final Intent getDeviceCardIntent(int i2, Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.Settings$ConfigureNotificationSettingsActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(268435456);
        Bundle bundle = new Bundle();
        bundle.putBoolean("extra_key_use_custom_fragment", true);
        intent.putExtra(":settings:show_fragment_args", bundle);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        if (commonUtils.getIS_FOLD() || commonUtils.getIS_TABLET()) {
            intent = commonUtils.changeToSettingsSplitIntent(context, intent);
        }
        intent.putExtra(":settings:show_fragment", "com.android.settings.NotificationStatusBarSettings");
        intent.putExtra(":settings:show_fragment_title", context.getString(R.string.notification_status_bar));
        if (ControlsUtils.INSTANCE.isFeatureSupport(context)) {
            intent.putExtra(":settings:fragment_args_key", "device_control");
        } else if (!Build.IS_INTERNATIONAL_BUILD) {
            intent.putExtra(":settings:fragment_args_key", SMART_DEVICE_CONTROL);
        } else if (i2 == 1) {
            intent.putExtra(":settings:fragment_args_key", "xiaomi_smart_hub");
        } else {
            if (i2 != 2) {
                return intent;
            }
            intent.putExtra(":settings:fragment_args_key", "device_control");
        }
        return intent;
    }

    public final Uri getURI_LOCKSCREEN_SMART_DEVICE_CONTROL() {
        return URI_LOCKSCREEN_SMART_DEVICE_CONTROL;
    }

    public final Uri getURI_SMART_DEVICE_CONTROL() {
        return URI_SMART_DEVICE_CONTROL;
    }

    public final boolean isAllowTrivialControlsUnderLockscreen(Context context) {
        kotlin.jvm.internal.n.g(context, "context");
        return Settings.Secure.getInt(context.getContentResolver(), LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS, 1) == 1;
    }

    public final boolean isLockScreenSmartDeviceControlVisible(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        int lockScreenSmartDeviceControl = getLockScreenSmartDeviceControl(context);
        if (z2) {
            if (lockScreenSmartDeviceControl != 1 && (!Build.IS_INTERNATIONAL_BUILD || lockScreenSmartDeviceControl != 3)) {
                return false;
            }
        } else if ((lockScreenSmartDeviceControl != 1 && (!Build.IS_INTERNATIONAL_BUILD || lockScreenSmartDeviceControl != 2)) || !checkDeviceCenterAvailable(context)) {
            return false;
        }
        return true;
    }

    public final boolean isSmartDeviceControlVisible(Context context, boolean z2) {
        kotlin.jvm.internal.n.g(context, "context");
        int smartDeviceControl = getSmartDeviceControl(context);
        if (z2) {
            if (smartDeviceControl != 2) {
                if (!Build.IS_INTERNATIONAL_BUILD) {
                    return false;
                }
                if (ControlsUtils.INSTANCE.isFeatureSupport(context)) {
                    if (smartDeviceControl != 1) {
                        return false;
                    }
                } else if (smartDeviceControl != 4) {
                    return false;
                }
            }
        } else if ((smartDeviceControl != 1 && (!Build.IS_INTERNATIONAL_BUILD || smartDeviceControl != 4)) || !checkDeviceCenterAvailable(context)) {
            return false;
        }
        return true;
    }
}
