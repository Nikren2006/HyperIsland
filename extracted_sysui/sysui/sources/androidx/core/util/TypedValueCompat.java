package androidx.core.util;

import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class TypedValueCompat {
    private static final float INCHES_PER_MM = 0.03937008f;
    private static final float INCHES_PER_PT = 0.013888889f;

    @RequiresApi(34)
    public static class Api34Impl {
        private Api34Impl() {
        }

        public static float deriveDimension(int i2, float f2, DisplayMetrics displayMetrics) {
            return TypedValue.deriveDimension(i2, f2, displayMetrics);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public @interface ComplexDimensionUnit {
    }

    private TypedValueCompat() {
    }

    public static float deriveDimension(int i2, float f2, DisplayMetrics displayMetrics) {
        return Api34Impl.deriveDimension(i2, f2, displayMetrics);
    }

    public static float dpToPx(float f2, DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(1, f2, displayMetrics);
    }

    @SuppressLint({"WrongConstant"})
    public static int getUnitFromComplexDimension(int i2) {
        return i2 & 15;
    }

    public static float pxToDp(float f2, DisplayMetrics displayMetrics) {
        return deriveDimension(1, f2, displayMetrics);
    }

    public static float pxToSp(float f2, DisplayMetrics displayMetrics) {
        return deriveDimension(2, f2, displayMetrics);
    }

    public static float spToPx(float f2, DisplayMetrics displayMetrics) {
        return TypedValue.applyDimension(2, f2, displayMetrics);
    }
}
