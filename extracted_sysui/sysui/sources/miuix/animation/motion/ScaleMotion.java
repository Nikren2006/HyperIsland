package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.DifferentiableScale;

/* JADX INFO: loaded from: classes4.dex */
public class ScaleMotion extends BaseMotion {
    private final Motion base;
    private Differentiable function;
    private final double pivotX;
    private final double scaleTime;
    private final double scaleX;

    public ScaleMotion(Motion motion, double d2) {
        this(motion, d2, 1.0d);
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        return this.base.finishTime() * this.scaleTime;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        Differentiable differentiableSolve;
        if (this.function == null && (differentiableSolve = this.base.solve()) != null) {
            this.function = new DifferentiableScale(differentiableSolve, this.scaleTime, this.scaleX, 0.0d, this.pivotX);
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        double dStopPosition = this.base.stopPosition();
        double d2 = this.pivotX;
        return ((dStopPosition - d2) * this.scaleX) + d2;
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return (this.base.stopSpeed() * this.scaleX) / this.scaleTime;
    }

    public ScaleMotion(Motion motion, double d2, double d3) {
        this(motion, d2, d3, 0.0d);
    }

    public ScaleMotion(Motion motion, double d2, double d3, double d4) {
        this.base = motion;
        this.scaleX = d2;
        this.scaleTime = d3;
        this.pivotX = d4;
    }
}
