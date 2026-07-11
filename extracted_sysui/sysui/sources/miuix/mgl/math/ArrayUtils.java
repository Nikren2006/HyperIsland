package miuix.mgl.math;

import android.content.res.Resources;
import android.util.TypedValue;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class ArrayUtils {
    public static final ArrayUtils INSTANCE = new ArrayUtils();

    private ArrayUtils() {
    }

    public final float[] arrayAddArray(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            return null;
        }
        float[] fArr3 = new float[fArr.length];
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            fArr3[i2] = fArr[i2] + fArr2[i2];
        }
        return fArr3;
    }

    public final float[] arrayAddFloat(float[] fArr, Float f2) {
        if (fArr == null || f2 == null) {
            return null;
        }
        float[] fArr2 = new float[fArr.length];
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = fArr[i2] + f2.floatValue();
        }
        return fArr2;
    }

    public final float[] arrayDivFloat(float[] fArr, Float f2) {
        if (fArr == null || f2 == null || n.b(f2, 0.0f)) {
            return null;
        }
        float[] fArr2 = new float[fArr.length];
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = fArr[i2] / f2.floatValue();
        }
        return fArr2;
    }

    public final float[] arrayMultiFloat(float[] fArr, Float f2) {
        if (fArr == null || f2 == null) {
            return null;
        }
        float[] fArr2 = new float[fArr.length];
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = fArr[i2] * f2.floatValue();
        }
        return fArr2;
    }

    public final float[] arraySubArray(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            return null;
        }
        float[] fArr3 = new float[fArr.length];
        int length = fArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            fArr3[i2] = fArr[i2] - fArr2[i2];
        }
        return fArr3;
    }

    public final float[] createFloatArray(float f2, int i2) {
        float[] fArr = new float[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            fArr[i3] = f2;
        }
        return fArr;
    }

    public final float dp2px(float f2) {
        return TypedValue.applyDimension(1, f2, Resources.getSystem().getDisplayMetrics());
    }

    public final float length(float[] fArr) {
        float f2 = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f3 : fArr) {
            f2 += f3 * f3;
        }
        return (float) java.lang.Math.sqrt(f2);
    }

    public final float[] normalize(float[] fArr) {
        if (fArr == null) {
            return new float[0];
        }
        float[] fArr2 = new float[fArr.length];
        float length = length(fArr);
        if (Float.compare(length, 0.0f) == 0) {
            return fArr2;
        }
        int length2 = fArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            fArr2[i2] = fArr[i2] / length;
        }
        return fArr2;
    }
}
