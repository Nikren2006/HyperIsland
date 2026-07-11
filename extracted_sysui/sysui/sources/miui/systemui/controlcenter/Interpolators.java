package miui.systemui.controlcenter;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import miuix.view.animation.CubicEaseOutInterpolator;

/* JADX INFO: loaded from: classes.dex */
public final class Interpolators {
    public static final Interpolators INSTANCE = new Interpolators();
    private static final Interpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    private static final Interpolator CUBIC_EASE_OUT = new CubicEaseOutInterpolator();

    private Interpolators() {
    }

    public final Interpolator getCUBIC_EASE_OUT() {
        return CUBIC_EASE_OUT;
    }

    public final Interpolator getFAST_OUT_SLOW_IN() {
        return FAST_OUT_SLOW_IN;
    }
}
