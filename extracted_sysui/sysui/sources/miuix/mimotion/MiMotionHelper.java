package miuix.mimotion;

import android.util.Log;
import com.xiaomi.mimotion.MimotionUtils;
import miuix.core.util.SystemProperties;
import miuix.internal.util.ReflectUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MiMotionHelper {
    private static boolean DEBUG = false;
    private static final boolean SUPPORT_MI_MOTION;
    public static final String SYSTEM_PROPERTY_MIMOTION_DEBUG = "persist.mimotion.debug";
    private static final String TAG = "MiMotionHelper";
    private static MiMotionHelper sInstance;
    private static boolean sIsMimotionUtilsAvailable;

    static {
        boolean z2 = Boolean.parseBoolean(SystemProperties.get("ro.display.mimotion", "false"));
        SUPPORT_MI_MOTION = z2;
        if (z2) {
            DEBUG = Boolean.parseBoolean(SystemProperties.get(SYSTEM_PROPERTY_MIMOTION_DEBUG, "false"));
            checkMimotionUtilsAvailable();
        }
    }

    private MiMotionHelper() {
    }

    private static void checkMimotionUtilsAvailable() {
        if (ReflectUtil.getClass("com.xiaomi.mimotion.MimotionUtils") == null) {
            return;
        }
        sIsMimotionUtilsAvailable = true;
    }

    public static MiMotionHelper getInstance() {
        if (sInstance == null) {
            sInstance = new MiMotionHelper();
        }
        return sInstance;
    }

    public static boolean isSupportMiMotion() {
        return SUPPORT_MI_MOTION;
    }

    public static void setEnable(boolean z2) {
        if (sIsMimotionUtilsAvailable) {
            MimotionUtils.setEnable(z2);
        }
    }

    public boolean isEnabled() {
        if (!sIsMimotionUtilsAvailable) {
            return false;
        }
        boolean zIsEnabled = MimotionUtils.isEnabled();
        if (DEBUG) {
            Log.i(TAG, "MimotionHelper isEnabled: " + zIsEnabled);
        }
        return zIsEnabled;
    }

    public boolean setPreferredRefreshRate(Object obj, int i2) {
        if (!sIsMimotionUtilsAvailable) {
            return false;
        }
        if (DEBUG) {
            Log.i(TAG, "setPreferredRefreshRate: " + i2);
        }
        return MimotionUtils.setPreferredRefreshRate(obj, i2);
    }
}
