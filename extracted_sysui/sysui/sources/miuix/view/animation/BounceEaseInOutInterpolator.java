package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class BounceEaseInOutInterpolator implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return f2 < 0.5f ? new BounceEaseInInterpolator().getInterpolation(f2 * 2.0f) * 0.5f : (new BounceEaseOutInterpolator().getInterpolation((f2 * 2.0f) - 1.0f) * 0.5f) + 0.5f;
    }
}
