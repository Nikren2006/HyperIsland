package miuix.os;

import com.miui.circulate.device.api.Constant;
import miui.systemui.notification.NotificationUtil;
import miui.systemui.util.FoldUtils;
import miuix.core.util.SystemProperties;

/* JADX INFO: loaded from: classes.dex */
public class Build {
    public static final boolean IS_DEBUGGABLE;
    public static final boolean IS_FLIP;
    public static final boolean IS_FOLDABLE;
    public static final boolean IS_FOLD_INSIDE;
    public static final boolean IS_FOLD_OUTSIDE;
    public static final boolean IS_INDEPENDENT_REAR;
    public static final boolean IS_REAR;
    public static final boolean IS_INTERNATIONAL_BUILD = SystemProperties.get(NotificationUtil.PRODUCT_MODE_DEVICE, "").contains("_global");
    public static final boolean IS_TABLET = isTablet();
    public static final boolean IS_AUTOMOTIVE = isAutoMotive();
    public static final boolean IS_WEAR = isWear();

    static {
        IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
        int i2 = SystemProperties.getInt("persist.sys.multi_display_type", 1);
        if (i2 > 1) {
            int i3 = i2 & 15;
            IS_REAR = i3 == 2;
            IS_FOLD_INSIDE = i3 == 3;
            IS_FLIP = i3 == 4;
            IS_FOLD_OUTSIDE = i3 == 5;
            IS_INDEPENDENT_REAR = i3 == 6;
        } else {
            int i4 = SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0);
            IS_REAR = i4 == 1;
            IS_FOLD_INSIDE = i4 == 2;
            IS_FLIP = false;
            IS_FOLD_OUTSIDE = false;
            IS_INDEPENDENT_REAR = false;
        }
        IS_FOLDABLE = IS_FOLD_INSIDE || IS_FOLD_OUTSIDE || IS_FLIP;
    }

    private static boolean isAutoMotive() {
        return SystemProperties.get("ro.build.characteristics").contains("automotive");
    }

    private static boolean isTablet() {
        return SystemProperties.get("ro.build.characteristics").contains("tablet");
    }

    private static boolean isWear() {
        return SystemProperties.get("ro.build.characteristics").contains(Constant.DeviceType.WATCH);
    }
}
