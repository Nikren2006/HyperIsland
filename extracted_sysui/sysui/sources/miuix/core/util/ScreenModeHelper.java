package miuix.core.util;

import android.content.Context;
import android.graphics.Point;
import miuix.core.util.screenutils.FreeFormModeHelper;
import miuix.core.util.screenutils.SplitScreenModeHelper;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenModeHelper {

    @Deprecated
    public static final int BIG_SCREEN_THRESHOLD = 600;
    private static final int FILTER_SUB_WINDOW_MODE = 15;
    private static final int FILTER_WINDOW_MODE = 61440;

    public static void detectWindowMode(Context context, WindowBaseInfo windowBaseInfo, Point point) {
        FreeFormModeHelper.detectFreeFormInfo(windowBaseInfo, context, point);
        if (isInFreeFormMode(windowBaseInfo.windowMode)) {
            return;
        }
        SplitScreenModeHelper.detectSplitScreenInfo(windowBaseInfo, point);
    }

    @Deprecated
    public static int getSubWindowMode(int i2) {
        return i2 & 15;
    }

    @Deprecated
    public static int getWindowMode(int i2) {
        return i2 & FILTER_WINDOW_MODE;
    }

    public static boolean isInFreeFormMode(int i2) {
        return (i2 & 8192) != 0;
    }

    public static boolean isInSplitScreenMode(int i2) {
        return (i2 & 4096) != 0;
    }
}
