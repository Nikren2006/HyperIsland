package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class CirclularEaseOutInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float f3 = f2 - 1.0f;
        return (float) Math.sqrt(1.0f - (f3 * f3));
    }
}
