package miuix.animation.easing;

import miuix.animation.FolmeEase;
import miuix.animation.function.Differentiable;
import miuix.animation.motion.Motion;
import miuix.animation.motion.PerlinMotion;

/* JADX INFO: loaded from: classes4.dex */
public class PerlinEasing implements FolmeEase {
    public static final Differentiable INTERPOLATOR = PerlinMotion.INTERPOLATOR;
    public static final Differentiable INTERPOLATOR2 = PerlinMotion.INTERPOLATOR2;
    private final double duration;
    private final Differentiable interpolator;
    private final double range;

    public PerlinEasing(double d2, double d3) {
        this(d2, d3, INTERPOLATOR);
    }

    @Override // miuix.animation.FolmeEase
    public Motion newMotion() {
        return new PerlinMotion(this.duration, this.range, this.interpolator);
    }

    public PerlinEasing(double d2, double d3, Differentiable differentiable) {
        this.duration = d2;
        this.range = d3;
        this.interpolator = differentiable;
    }
}
