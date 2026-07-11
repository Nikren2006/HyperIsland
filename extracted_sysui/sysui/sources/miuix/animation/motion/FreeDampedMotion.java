package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.FreeDamping;

/* JADX INFO: loaded from: classes4.dex */
public class FreeDampedMotion extends BaseMotion implements Motion {
    private Differentiable function = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final double f5985g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final double f5986p;

    public FreeDampedMotion(double d2, double d3) {
        this.f5985g = d3;
        this.f5986p = d2;
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        if (this.f5985g == 0.0d && getInitialV() == 0.0d) {
            return 0.0d;
        }
        return super.finishTime();
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialVChanged() {
        super.onInitialVChanged();
        this.function = null;
    }

    @Override // miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        this.function = null;
    }

    @Override // miuix.animation.motion.Motion
    public Differentiable solve() {
        if (this.function == null) {
            double initialV = getInitialV() - (this.f5985g / this.f5986p);
            double initialX = getInitialX();
            double d2 = this.f5986p;
            this.function = new FreeDamping(initialV, initialX + (initialV / d2), d2, this.f5985g);
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        if (this.f5985g == 0.0d) {
            return getInitialX() + (getInitialV() / this.f5986p);
        }
        return Double.POSITIVE_INFINITY;
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return this.f5985g / this.f5986p;
    }
}
