package miuix.animation.motion;

import miuix.animation.function.Bezier;
import miuix.animation.function.Differentiable;

/* JADX INFO: loaded from: classes4.dex */
public class CubicBezierMotion extends BaseMotion {

    /* JADX INFO: renamed from: x1, reason: collision with root package name */
    private final double f5980x1;
    private final double x2;

    /* JADX INFO: renamed from: y1, reason: collision with root package name */
    private final double f5981y1;
    private final double y2;

    public CubicBezierMotion(double d2, double d3, double d4, double d5) {
        this.f5980x1 = d2;
        this.f5981y1 = d3;
        this.x2 = d4;
        this.y2 = d5;
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        return 1.0d;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        return new Bezier(0.0d, 0.0d, this.f5980x1, this.f5981y1, this.x2, this.y2, 1.0d, 1.0d);
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        return 1.0d;
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return 0.0d;
    }
}
