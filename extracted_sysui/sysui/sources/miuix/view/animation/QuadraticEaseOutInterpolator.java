package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class QuadraticEaseOutInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return (-f2) * (f2 - 2.0f);
    }
}
