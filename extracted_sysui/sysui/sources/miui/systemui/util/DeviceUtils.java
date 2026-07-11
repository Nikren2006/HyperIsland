package miui.systemui.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import miui.os.Build;
import miui.util.DeviceLevel;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();
    private static final boolean _isLowEndDevice;
    private static final int computilityLevel;
    private static final int computilityVersion;
    private static Boolean isForceMiddleDevice;
    private static final boolean isHighLevel;
    private static final boolean isLowLevel;
    private static final boolean isMidLowLevel;
    private static final boolean isNormalLevel;
    private static final boolean isSubMidLevel;
    private static final boolean isUltraLevel;

    static {
        boolean z2;
        if (DeviceLevel.IS_MIUI_LITE_VERSION || Build.IS_MIUI_LITE_VERSION) {
            z2 = true;
        } else {
            try {
                z2 = DeviceLevel.IS_MIUI_MIDDLE_VERSION;
            } catch (Throwable unused) {
                z2 = false;
            }
        }
        _isLowEndDevice = z2;
        int computilityLevel2 = miuix.device.DeviceUtils.getComputilityLevel();
        computilityLevel = computilityLevel2;
        computilityVersion = miuix.device.DeviceUtils.getComputilityVersion();
        isLowLevel = computilityLevel2 == 1;
        isMidLowLevel = computilityLevel2 == 2;
        isSubMidLevel = computilityLevel2 == 3;
        isNormalLevel = computilityLevel2 == 4;
        isHighLevel = computilityLevel2 == 5;
        isUltraLevel = computilityLevel2 == 6;
    }

    private DeviceUtils() {
    }

    public static final boolean ccDeviceCenterDefaultOff() {
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        return (commonUtils.getMIUI_LITE_V2() || isLowLevel) && !commonUtils.getIS_TABLET() && Build.IS_INTERNATIONAL_BUILD;
    }

    public static final int getComputilityLevel() {
        return computilityLevel;
    }

    public static /* synthetic */ void getComputilityLevel$annotations() {
    }

    public static final int getComputilityVersion() {
        return computilityVersion;
    }

    public static /* synthetic */ void getComputilityVersion$annotations() {
    }

    public static final boolean isHighLevel() {
        return isHighLevel;
    }

    public static /* synthetic */ void isHighLevel$annotations() {
    }

    public static final boolean isLowEndDevice() {
        return _isLowEndDevice || kotlin.jvm.internal.n.c(isForceMiddleDevice, Boolean.TRUE);
    }

    public static /* synthetic */ void isLowEndDevice$annotations() {
    }

    public static final boolean isLowLevel() {
        return isLowLevel;
    }

    public static /* synthetic */ void isLowLevel$annotations() {
    }

    public static final boolean isMidLowLevel() {
        return isMidLowLevel;
    }

    public static /* synthetic */ void isMidLowLevel$annotations() {
    }

    public static final boolean isNormalLevel() {
        return isNormalLevel;
    }

    public static /* synthetic */ void isNormalLevel$annotations() {
    }

    public static final boolean isSubMidLevel() {
        return isSubMidLevel;
    }

    public static /* synthetic */ void isSubMidLevel$annotations() {
    }

    public static final boolean isSupportCameraHandle() {
        return SystemProperties.getBoolean("persist.vendor.usb.camerahandle", false);
    }

    public static final boolean isSupportCarSickness(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        kotlin.jvm.internal.n.g(context, "context");
        if (Settings.Secure.getInt(context.getContentResolver(), "car_sickness_is_support", 0) != 1) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.miui.securitycenter", 128);
            if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null || (bundle = applicationInfo.metaData) == null) {
                return false;
            }
            return bundle.getBoolean("miui.supportCarSicknessSetting");
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static final boolean isSupportControlDevices() {
        return true;
    }

    public static /* synthetic */ void isSupportControlDevices$annotations() {
    }

    public static final boolean isUltraLevel() {
        return isUltraLevel;
    }

    public static /* synthetic */ void isUltraLevel$annotations() {
    }

    public final void updateForceMiddle(SystemUIResourcesHelper resHelper) {
        Boolean boolValueOf;
        kotlin.jvm.internal.n.g(resHelper, "resHelper");
        Integer integer = resHelper.getInteger("force_middle_device");
        if (integer != null) {
            boolValueOf = Boolean.valueOf(integer.intValue() == 1);
        } else {
            boolValueOf = null;
        }
        isForceMiddleDevice = boolValueOf;
    }
}
