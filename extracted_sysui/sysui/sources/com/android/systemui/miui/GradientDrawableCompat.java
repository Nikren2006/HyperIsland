package com.android.systemui.miui;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class GradientDrawableCompat {
    private static final float[] DEFAULT_CORNER_RADII = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private static final String TAG = GradientDrawableCompat.class.getSimpleName();
    private static Field sGradientField;
    private static Field sRadiusArrayField;
    private static Field sRadiusField;

    private GradientDrawableCompat() {
    }

    public static float[] getCornerRadii(GradientDrawable gradientDrawable) {
        try {
            if (sGradientField == null) {
                Field declaredField = gradientDrawable.getClass().getDeclaredField("mGradientState");
                sGradientField = declaredField;
                declaredField.setAccessible(true);
            }
            if (sRadiusArrayField == null) {
                Field declaredField2 = sGradientField.get(gradientDrawable).getClass().getDeclaredField("mRadiusArray");
                sRadiusArrayField = declaredField2;
                declaredField2.setAccessible(true);
            }
            return (float[]) sRadiusArrayField.get(sGradientField.get(gradientDrawable));
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
            return DEFAULT_CORNER_RADII;
        }
    }

    public static float getCornerRadius(GradientDrawable gradientDrawable) {
        try {
            if (sGradientField == null) {
                Field declaredField = gradientDrawable.getClass().getDeclaredField("mGradientState");
                sGradientField = declaredField;
                declaredField.setAccessible(true);
            }
            if (sRadiusField == null) {
                Field declaredField2 = sGradientField.get(gradientDrawable).getClass().getDeclaredField("mRadius");
                sRadiusField = declaredField2;
                declaredField2.setAccessible(true);
            }
            return sRadiusField.getFloat(sGradientField.get(gradientDrawable));
        } catch (Exception e2) {
            Log.e(TAG, e2.getMessage(), e2);
            return 0.0f;
        }
    }
}
