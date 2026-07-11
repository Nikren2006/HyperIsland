package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class CirclularEaseInOutInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float fSqrt;
        float f3;
        float f4 = f2 * 2.0f;
        if (f4 < 1.0f) {
            fSqrt = (float) (Math.sqrt(1.0f - (f4 * f4)) - 1.0d);
            f3 = -0.5f;
        } else {
            float f5 = f4 - 2.0f;
            fSqrt = (float) (Math.sqrt(1.0f - (f5 * f5)) + 1.0d);
            f3 = 0.5f;
        }
        return fSqrt * f3;
    }
}
