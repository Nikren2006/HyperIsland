package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class BackEaseOutInterpolator implements Interpolator {
    private final float mOvershot;

    public BackEaseOutInterpolator() {
        this(0.0f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float f3 = this.mOvershot;
        if (f3 == 0.0f) {
            f3 = 1.70158f;
        }
        float f4 = f2 - 1.0f;
        return (f4 * f4 * (((f3 + 1.0f) * f4) + f3)) + 1.0f;
    }

    public BackEaseOutInterpolator(float f2) {
        this.mOvershot = f2;
    }
}
