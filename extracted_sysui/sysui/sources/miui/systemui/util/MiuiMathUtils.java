package miui.systemui.util;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiMathUtils {
    public static final MiuiMathUtils INSTANCE = new MiuiMathUtils();

    private MiuiMathUtils() {
    }

    public final float calculatePercentage(int i2, int i3, int i4) {
        if (i4 <= i2) {
            return 0.0f;
        }
        if (i4 >= i3) {
            return 1.0f;
        }
        return (i4 - i2) / (i3 - i2);
    }

    public final float constrain(float f2, float f3, float f4) {
        return f2 < f3 ? f3 : f2 > f4 ? f4 : f2;
    }

    public final float constrainedMap(float f2, float f3, float f4, float f5, float f6) {
        return lerp(f2, f3, lerpInvSat(f4, f5, f6));
    }

    public final float lerp(float f2, float f3, float f4) {
        return f2 + ((f3 - f2) * f4);
    }

    public final float lerpInv(float f2, float f3, float f4) {
        if (f2 == f3) {
            return 0.0f;
        }
        return (f4 - f2) / (f3 - f2);
    }

    public final float lerpInvSat(float f2, float f3, float f4) {
        return saturate(lerpInv(f2, f3, f4));
    }

    public final float saturate(float f2) {
        return constrain(f2, 0.0f, 1.0f);
    }
}
