package miui.systemui.util;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public final class AnimationUtils {
    public static final AnimationUtils INSTANCE = new AnimationUtils();

    private AnimationUtils() {
    }

    public final float afterFriction(float f2, float f3) {
        return afterFriction(false, f2, f3);
    }

    public final float afterFrictionByLine(boolean z2, int i2, int i3, float f2, float f3) {
        System.currentTimeMillis();
        float f4 = i2 / (i3 - 1);
        float f5 = 1;
        float f6 = f5 - f4;
        float fB = c1.f.b(0.0f, c1.f.b(0.0f, (afterFriction(z2, Math.abs(f2) - 0.0f, f3) * (((f5 - (f6 * f6)) * 0.15f) + 0.5f)) + 0.0f) - 0.0f);
        return f2 > 0.0f ? fB : -fB;
    }

    public final float afterFrictionRatio(float f2, float f3) {
        float fE = c1.f.e(Math.abs(f2) / Math.abs(f3), f3);
        float f4 = fE * fE;
        float f5 = ((((f4 * fE) / 3) - f4) + fE) * f3;
        return f2 > 0.0f ? f5 : -f5;
    }

    public final float inverseFriction(float f2, float f3) {
        float f4 = 1 - (3 * (f2 / f3));
        float fPow = (float) Math.pow(Math.abs(f4), 0.33333334f);
        if (f4 <= 0.0f) {
            fPow = -fPow;
        }
        return (1.0f - fPow) * f3;
    }

    public final void setFactorScale(View view, float f2, float f3) {
        kotlin.jvm.internal.n.g(view, "<this>");
        float fH = c1.f.h(f2, 0.0f, 1.0f);
        if (fH == 1.0f) {
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            return;
        }
        float fC = c1.f.c(view.getWidth(), view.getHeight());
        float f4 = (fC - f3) / fC;
        float fH2 = c1.f.h(f4 + ((1.0f - f4) * fH), 0.0f, 1.0f);
        if (Float.isNaN(fH2)) {
            return;
        }
        view.setScaleX(fH2);
        view.setScaleY(fH2);
    }

    public final float afterFriction(boolean z2, float f2, float f3) {
        float fAbs = Math.abs(f2) / Math.abs(f3);
        if (!z2) {
            fAbs = c1.f.e(fAbs, 1.0f);
        }
        float f4 = fAbs * fAbs;
        float f5 = ((((f4 * fAbs) / 3) - f4) + fAbs) * f3;
        return f2 > 0.0f ? f5 : -f5;
    }

    public final float afterFrictionByLine(int i2, int i3, float f2, float f3) {
        return afterFrictionByLine(false, i2, i3, f2, f3);
    }
}
