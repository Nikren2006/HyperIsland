package miuix.core.util.screenutils;

import android.content.Context;
import android.graphics.Point;
import androidx.annotation.NonNull;
import miuix.core.util.WindowBaseInfo;
import miuix.core.util.WindowUtils;

/* JADX INFO: loaded from: classes3.dex */
public class FreeFormModeHelper {
    public static final int EXCLUDE_FREE_FORM = 8192;
    public static final int FREE_FORM_ASPECT_RATIO_16_9 = 8193;
    public static final int FREE_FORM_ASPECT_RATIO_3_4 = 8194;
    public static final int FREE_FORM_ASPECT_RATIO_4_3 = 8195;
    public static final int FREE_FORM_ASPECT_RATIO_OTHER = 8196;
    public static final int SUB_MODE_16_9 = 1;
    public static final int SUB_MODE_3_4 = 2;
    public static final int SUB_MODE_4_3 = 3;
    public static final int SUB_MODE_NONE = 0;
    public static final int SUB_MODE_OTHER = 4;

    @NonNull
    private static WindowBaseInfo acquireFreeFormWindowRatioInternal(WindowBaseInfo windowBaseInfo, Context context, Point point) {
        if (!WindowUtils.isFreeformMode(context.getResources().getConfiguration(), point, windowBaseInfo.windowSize)) {
            windowBaseInfo.windowMode &= -8193;
            return windowBaseInfo;
        }
        int i2 = windowBaseInfo.windowSize.x;
        return freeFormModeRatioToCodeInternal(windowBaseInfo, i2 != 0 ? (r2.y * 1.0f) / i2 : 0.0f);
    }

    @NonNull
    public static WindowBaseInfo detectFreeFormInfo(WindowBaseInfo windowBaseInfo, Context context, Point point) {
        return acquireFreeFormWindowRatioInternal(windowBaseInfo, context, point);
    }

    @NonNull
    private static WindowBaseInfo freeFormModeRatioToCodeInternal(WindowBaseInfo windowBaseInfo, float f2) {
        if (f2 <= 0.0f) {
            windowBaseInfo.windowMode = 0;
        } else if (f2 >= 0.74f && f2 < 0.76f) {
            windowBaseInfo.windowMode = 8195;
        } else if (f2 >= 1.32f && f2 < 1.34f) {
            windowBaseInfo.windowMode = 8194;
        } else if (f2 < 1.76f || f2 >= 1.79f) {
            windowBaseInfo.windowMode = 8196;
        } else {
            windowBaseInfo.windowMode = 8193;
        }
        return windowBaseInfo;
    }
}
