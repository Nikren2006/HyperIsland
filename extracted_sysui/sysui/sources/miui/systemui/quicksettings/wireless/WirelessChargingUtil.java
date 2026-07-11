package miui.systemui.quicksettings.wireless;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemProperties;
import android.util.Log;
import miui.systemui.util.ReflectBuilderUtil;
import miuix.os.Build;

/* JADX INFO: loaded from: classes4.dex */
public class WirelessChargingUtil {
    public static final int REVERSE_CHARGE_WHEN_WIRED_SHOW_DIALOG = 3;
    public static final int REVERSE_CHARGE_WHEN_WIRED_SHOW_DIALOG_INTERNATIONAL = 1;
    private static final String TAG = "MiuiWirelessPowerTile";
    public static final int WIRELESS_DISABLED = 1;
    public static final int WIRELESS_ENABLED = 0;

    public static int getIntForUser(ContentResolver contentResolver, String str, int i2, int i3) {
        try {
            ReflectBuilderUtil.ReflAgent reflAgent = ReflectBuilderUtil.ReflAgent.getClass("android.provider.Settings$System");
            Class cls = Integer.TYPE;
            return reflAgent.callStatic("getIntForUser", new Class[]{ContentResolver.class, String.class, cls, cls}, contentResolver, str, Integer.valueOf(i2), Integer.valueOf(i3)).intResult();
        } catch (Exception e2) {
            Log.e(TAG, "getIntForUser error" + e2);
            return 0;
        }
    }

    public static int getUserId() {
        try {
            return ReflectBuilderUtil.ReflAgent.getClass("android.os.UserHandle").getStaticFiled("USER_SYSTEM").intResult();
        } catch (Exception e2) {
            Log.e(TAG, "get USER_SYSTEM error" + e2);
            return 0;
        }
    }

    public static boolean isSupportControlHaptic() {
        return SystemProperties.get("ro.product.device", "").startsWith("mayfly") || SystemProperties.getBoolean("persist.vendor.revchg.shutmotor", false);
    }

    public static boolean isSupportNoReverseBox() {
        int i2 = SystemProperties.getInt("persist.vendor.noReverseBox.inGL", -1);
        Log.d(TAG, "get noReverseBox result : " + i2);
        if (i2 == 3) {
            return true;
        }
        return i2 == 1 && Build.IS_INTERNATIONAL_BUILD;
    }

    public static boolean isWiredCharging(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentRegisterReceiver == null) {
            return false;
        }
        int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("plugged", -1);
        boolean z2 = intExtra == 2 || intExtra == 5;
        boolean z3 = intExtra2 == 1 || intExtra2 == 2;
        Log.e(TAG, "status : " + intExtra + "  plugged : " + intExtra2);
        return z2 && z3;
    }

    public static boolean isWirelessChargingEnabled() {
        try {
            Object objInvoke = Class.forName("miui.util.IMiCharge").getDeclaredMethod("getInstance", null).invoke(null, null);
            return ((Integer) objInvoke.getClass().getMethod("getWirelessChargingStatus", null).invoke(objInvoke, null)).intValue() == 0;
        } catch (Exception e2) {
            Log.e(TAG, "isWirelessChargingEnabled error" + e2);
            return false;
        }
    }

    public static boolean isWirelessChargingSupported() {
        try {
            Object objInvoke = Class.forName("miui.util.IMiCharge").getDeclaredMethod("getInstance", null).invoke(null, null);
            return ((Boolean) objInvoke.getClass().getMethod("isWirelessChargingSupported", null).invoke(objInvoke, null)).booleanValue();
        } catch (Exception e2) {
            Log.e(TAG, "isWirelessChargingSupported error" + e2);
            return false;
        }
    }

    public static void putIntForUser(ContentResolver contentResolver, String str, int i2, int i3) {
        try {
            ReflectBuilderUtil.ReflAgent reflAgent = ReflectBuilderUtil.ReflAgent.getClass("android.provider.Settings$System");
            Class cls = Integer.TYPE;
            reflAgent.callStatic("putIntForUser", new Class[]{ContentResolver.class, String.class, cls, cls}, contentResolver, str, Integer.valueOf(i2), Integer.valueOf(i3));
        } catch (Exception e2) {
            Log.e(TAG, "getIntForUser error" + e2);
        }
    }

    public static void setWirelessSwitchEnabled(boolean z2, Context context) {
        if (isSupportControlHaptic() && z2 && context != null && getIntForUser(context.getContentResolver(), "haptic_feedback_disable", 0, getUserId()) <= 0) {
            Log.i(TAG, "close haptic when wireless reverse charge");
            putIntForUser(context.getContentResolver(), "haptic_feedback_disable", 1, getUserId());
        }
        try {
            Object objInvoke = Class.forName("miui.util.IMiCharge").getDeclaredMethod("getInstance", null).invoke(null, null);
            objInvoke.getClass().getMethod("setWirelessChargingEnabled", Boolean.TYPE).invoke(objInvoke, Boolean.valueOf(z2));
        } catch (Exception e2) {
            Log.e(TAG, "changeWirelessReverseChargeStatus error " + e2.toString());
        }
    }
}
