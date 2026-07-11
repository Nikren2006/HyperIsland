package miuix.core.util;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.core.util.MaterialConfig;
import miuix.reflect.ReflectionHelper;

/* JADX INFO: loaded from: classes3.dex */
public class MiShadowUtils {
    public static final boolean SUPPORT_MI_SHADOW;
    private static final String TAG = "MiShadowHelper";

    static {
        boolean z2 = Boolean.parseBoolean(SystemProperties.get("persist.sys.mi_shadow_supported", "false"));
        SUPPORT_MI_SHADOW = z2;
        if (z2) {
            return;
        }
        Log.d(TAG, "This device does not support mi shadow!");
    }

    private MiShadowUtils() {
    }

    public static void clearMiShadow(View view) {
        setMiShadow(view, 0, 0.0f, 0.0f, 0.0f);
    }

    public static void setMiShadow(View view, @ColorInt int i2, float f2) {
        setMiShadow(view, i2, 0.0f, 0.0f, f2);
    }

    public static void setShadowConfig(@NonNull View view, @Nullable MaterialConfig.ShadowConfig shadowConfig) {
        if (view != null && SUPPORT_MI_SHADOW) {
            if (shadowConfig != null) {
                setMiShadow(view, shadowConfig.shadowColor, shadowConfig.shadowOffsetX, shadowConfig.shadowOffsetY, shadowConfig.shadowRadius, shadowConfig.shadowDispersion);
            } else {
                clearMiShadow(view);
            }
        }
    }

    public static void setMiShadow(View view, @ColorInt int i2, float f2, float f3, float f4) {
        setMiShadow(view, i2, f2, f3, f4, 1.0f);
    }

    public static void setMiShadow(View view, @ColorInt int i2, float f2, float f3, float f4, float f5) {
        if (SUPPORT_MI_SHADOW) {
            try {
                Class cls = Integer.TYPE;
                Class cls2 = Float.TYPE;
                ReflectionHelper.invoke(View.class, view, "setMiShadow", new Class[]{cls, cls2, cls2, cls2, cls2}, Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5));
            } catch (Exception e2) {
                Log.e(TAG, "Failed to call setMiShadow", e2);
            }
        }
    }

    public static void setMiShadow(View view, @ColorInt int i2, float f2, float f3, float f4, float f5, boolean z2) {
        if (SUPPORT_MI_SHADOW) {
            if (Build.VERSION.SDK_INT <= 34) {
                setMiShadow(view, i2, f2, f3, f4, f5);
                return;
            }
            try {
                Class cls = Integer.TYPE;
                Class cls2 = Float.TYPE;
                ReflectionHelper.invoke(View.class, view, "setMiShadow", new Class[]{cls, cls2, cls2, cls2, cls2, Boolean.TYPE}, Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(f4), Float.valueOf(f5), Boolean.valueOf(z2));
            } catch (Exception e2) {
                Log.e(TAG, "Failed to call setMiShadow", e2);
            }
        }
    }
}
