package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class BounceEaseInInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return 1.0f - new BounceEaseOutInterpolator().getInterpolation(1.0f - f2);
    }
}
