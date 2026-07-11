package com.android.systemui.miui.globalactions;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import miui.os.Build;
import miui.security.SecurityManager;
import miui.systemui.util.FlipUtils;
import miui.systemui.util.FoldUtils;
import miui.systemui.util.ReflectUtil;
import miui.util.FeatureParser;
import miuix.pickerwidget.date.DateUtils;

/* JADX INFO: loaded from: classes2.dex */
class Util {
    private static final String BOOT_ALARM_INTENT_SERVICE = "com.miui.powercenter.provider.BootAlarmIntentService";
    private static final int DEFAULT_REBOOT_TIME = -1;
    public static boolean IS_MUILT_DISPLAY = false;
    public static final boolean IS_PAD;
    private static final String IS_SUPPORT_LONG_PRESS_POWER_KEY_TO_TALK = "support_long_press_power_key_to_talk";
    private static final String LONG_PRESS_POWER_KEY_REBOOT_TIME = "ro.vendor.s2_reboot_time";
    private static final String SHUTDOWN_ALARM_CLOCK_OFFSET = "shutdown_alarm_clock_offset";
    private static final String SHUTDOWN_ALARM_SERVICE_NAME = "com.android.deskclock.util.ShutdownAlarm";
    private static String TAG = "MiuiGlobalActions";
    private static final String UPDATER_ENABLE_KEY = "miui_updater_enable";
    private static final int WAKE_ALARM_TIME_OFFSET = 120;

    static {
        IS_MUILT_DISPLAY = SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
        IS_PAD = "tablet".equals(SystemProperties.get("ro.build.characteristics", "default"));
    }

    public static int checkFlipDimen(int i2) {
        return FlipUtils.isFlipTiny() ? i2 == R.dimen.slider_margin_end ? R.dimen.flip_tiny_slider_margin_end : i2 == R.dimen.slider_height ? R.dimen.flip_tiny_slider_height : i2 == R.dimen.point_margin_top ? R.dimen.flip_tiny_point_margin_top : i2 == R.dimen.emergency_call_text_margin_bottom ? R.dimen.flip_tiny_emergency_call_text_margin_bottom : i2 == R.dimen.emergency_call_text_margin_slider ? R.dimen.flip_tiny_emergency_call_text_margin_slider : i2 == R.dimen.emergency_call_height ? R.dimen.flip_tiny_emergency_call_height : i2 == R.dimen.reboot_shutdown_text_margin ? R.dimen.flip_tiny_reboot_shutdown_text_margin : i2 == R.dimen.emergency_call_center_margin_left ? R.dimen.flip_tiny_emergency_call_center_margin_left : i2 == R.dimen.slider_margin_bottom_with_emergency ? R.dimen.flip_tiny_slider_margin_bottom_with_emergency : i2 : i2;
    }

    public static String getAlarmTime(Context context) {
        boolean z2;
        long j2 = 120;
        try {
            String string = Settings.System.getString(context.getContentResolver(), SHUTDOWN_ALARM_CLOCK_OFFSET);
            if (string != null) {
                j2 = Long.parseLong(string);
            }
        } catch (Exception e2) {
            Log.e(TAG, "get deskclock ShutdownAlarm error " + e2);
        }
        SecurityManager securityManager = (SecurityManager) context.getSystemService("security");
        if (securityManager == null) {
            return null;
        }
        long wakeUpTime = securityManager.getWakeUpTime(SHUTDOWN_ALARM_SERVICE_NAME);
        long wakeUpTime2 = securityManager.getWakeUpTime(BOOT_ALARM_INTENT_SERVICE);
        if (wakeUpTime == 0 || (wakeUpTime2 > 0 && wakeUpTime2 < wakeUpTime + j2)) {
            z2 = false;
        } else {
            wakeUpTime2 = wakeUpTime + j2;
            z2 = true;
        }
        if (wakeUpTime2 > 0) {
            return String.format(context.getString(z2 ? R.string.reboot_info_shutdown_alarm : R.string.reboot_info_auto_boot), DateUtils.formatRelativeTime(context, wakeUpTime2 * 1000, true));
        }
        return null;
    }

    public static String getRestartHint(Context context) {
        boolean zBooleanValue;
        try {
            zBooleanValue = ((Boolean) ReflectUtil.callStaticObjectMethod(Class.forName("miui.hardware.input.InputFeature"), Boolean.class, "supportXiaoaiLongPressPowerKeyTalk", new Class[0], new Object[0])).booleanValue();
        } catch (Exception e2) {
            Log.d(TAG, "getRestartHint e: " + e2);
            zBooleanValue = false;
        }
        boolean z2 = Build.IS_INTERNATIONAL_BUILD || FeatureParser.getBoolean(IS_SUPPORT_LONG_PRESS_POWER_KEY_TO_TALK, false) || zBooleanValue;
        int i2 = SystemProperties.getInt(LONG_PRESS_POWER_KEY_REBOOT_TIME, -1);
        Log.d(TAG, "getRestartHint: support=" + z2 + ", time= " + i2 + " supportXiaoaiLongPressPowerKeyTalk: " + zBooleanValue);
        if (!z2 || i2 == -1) {
            return null;
        }
        return context.getString(R.string.restart_hint, Integer.valueOf(i2));
    }

    public static boolean inLargeScreen(Context context) {
        int i2 = context.getResources().getConfiguration().screenLayout & 15;
        return i2 == 3 || i2 == 4;
    }

    public static boolean isLayoutR2L(Context context) {
        return context.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    public static boolean isUpdaterEnable(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), UPDATER_ENABLE_KEY, 0) == 1;
    }

    public static boolean isUseGota() {
        return Build.IS_INTERNATIONAL_BUILD && SystemProperties.getInt("ro.miui.gota", 0) == 1 && !SystemProperties.get("ro.product.name", "unknown").endsWith("eea");
    }

    public static boolean showEmergencyCall() {
        return "IN".equals(SystemProperties.get("ro.miui.region", "")) && !IS_PAD;
    }
}
