package miuix.smooth;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import miuix.core.util.SystemProperties;
import miuix.reflect.ReflectionHelper;

/* JADX INFO: loaded from: classes5.dex */
public class SmoothCornerHelper {
    public static Boolean FORCE_USE_SMOOTH;
    public static boolean IS_SUPPORT_SMOOTH_CORNER;
    private static Boolean sEnableAppSmoothCorner;

    static {
        boolean z2 = Boolean.parseBoolean(SystemProperties.get("persist.sys.support_view_smoothcorner", "false"));
        IS_SUPPORT_SMOOTH_CORNER = z2;
        if (z2) {
            return;
        }
        Log.d("SmoothCornerHelper", "this device is not support system smooth corner");
    }

    public static void init(Context context) {
        if (IS_SUPPORT_SMOOTH_CORNER) {
            isEnableAppSmoothCorner(context);
        }
    }

    public static boolean isEnableAppSmoothCorner(Context context) {
        if (sEnableAppSmoothCorner == null) {
            try {
                Boolean bool = (Boolean) ReflectionHelper.invokeObject(ApplicationInfo.class, context.getApplicationInfo(), "getGlobalSmoothCornerEnabled", new Class[0], new Object[0]);
                sEnableAppSmoothCorner = bool;
                if (bool == null) {
                    sEnableAppSmoothCorner = Boolean.FALSE;
                }
            } catch (Exception e2) {
                sEnableAppSmoothCorner = Boolean.FALSE;
                Log.d("SmoothCornerHelper", "isEnableAppSmoothCorner fail " + e2);
            }
        }
        return sEnableAppSmoothCorner.booleanValue();
    }

    public static void setDrawableSmoothCornerEnable(Drawable drawable, boolean z2) {
        if (IS_SUPPORT_SMOOTH_CORNER) {
            Boolean bool = sEnableAppSmoothCorner;
            if (bool == null || !bool.booleanValue()) {
                setDrawableSmoothCornerEnableByReflect(drawable, z2);
            }
        }
    }

    private static void setDrawableSmoothCornerEnableByReflect(Drawable drawable, boolean z2) {
        try {
            ReflectionHelper.invoke(Drawable.class, drawable, "setSmoothCornerEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z2));
        } catch (Exception e2) {
            Log.d("SmoothCornerHelper", "setDrawableSmoothCornerEnable fail " + e2);
        }
    }

    public static void setDrawableSmoothCornerForceEnable(Drawable drawable, boolean z2) {
        if (IS_SUPPORT_SMOOTH_CORNER) {
            setDrawableSmoothCornerEnableByReflect(drawable, z2);
        }
    }

    public static void setViewSmoothCornerEnable(View view, boolean z2) {
        if (IS_SUPPORT_SMOOTH_CORNER && !isEnableAppSmoothCorner(view.getContext())) {
            setViewSmoothCornerEnableByReflect(view, z2);
        }
    }

    private static void setViewSmoothCornerEnableByReflect(View view, boolean z2) {
        try {
            ReflectionHelper.invoke(View.class, view, "setSmoothCornerEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z2));
        } catch (Exception e2) {
            Log.d("SmoothCornerHelper", "setViewSmoothCornerEnable fail " + e2);
        }
    }

    public static void setViewSmoothCornerForceEnable(View view, boolean z2) {
        if (IS_SUPPORT_SMOOTH_CORNER) {
            setViewSmoothCornerEnableByReflect(view, z2);
        }
    }
}
