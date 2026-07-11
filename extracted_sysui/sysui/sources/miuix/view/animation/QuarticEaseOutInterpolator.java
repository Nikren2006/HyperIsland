package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class QuarticEaseOutInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float f3 = f2 - 1.0f;
        return -((((f3 * f3) * f3) * f3) - 1.0f);
    }
}
