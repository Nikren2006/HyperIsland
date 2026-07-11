package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class CirclularEaseInInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return -((float) (Math.sqrt(1.0f - (f2 * f2)) - 1.0d));
    }
}
