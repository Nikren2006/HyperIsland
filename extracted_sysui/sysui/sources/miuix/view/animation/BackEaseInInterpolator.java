package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class BackEaseInInterpolator implements Interpolator {
    private final float mOvershot;

    public BackEaseInInterpolator() {
        this(0.0f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float f3 = this.mOvershot;
        if (f3 == 0.0f) {
            f3 = 1.70158f;
        }
        return f2 * f2 * (((1.0f + f3) * f2) - f3);
    }

    public BackEaseInInterpolator(float f2) {
        this.mOvershot = f2;
    }
}
