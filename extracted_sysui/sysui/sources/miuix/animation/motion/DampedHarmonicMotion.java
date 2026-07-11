package miuix.animation.motion;

import miuix.animation.function.CriticalDamping;
import miuix.animation.function.Differentiable;
import miuix.animation.function.OverDamping;
import miuix.animation.function.UnderDamping;

/* JADX INFO: loaded from: classes4.dex */
public class DampedHarmonicMotion extends BaseMotion implements Motion {
    private Differentiable function;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final double f5982g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final double f5983p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final double f5984q;
    private final double xStar;

    public DampedHarmonicMotion(double d2, double d3, double d4, double d5) {
        if (d2 == 0.0d) {
            throw new IllegalArgumentException("zeta must not be 0");
        }
        if (d3 == 0.0d) {
            throw new IllegalArgumentException("omega must not be 0");
        }
        this.f5983p = d2 * 2.0d * d3;
        double d6 = d3 * d3;
        this.f5984q = d6;
        this.f5982g = d5;
        this.xStar = ((-d5) / d6) + d4;
        this.function = null;
    }

    private void solveInternal() {
        double d2 = this.f5983p;
        double d3 = (d2 * d2) - (this.f5984q * 4.0d);
        double initialX = getInitialX() - this.xStar;
        if (d3 > 0.0d) {
            double dSqrt = Math.sqrt(d3);
            double d4 = this.f5983p;
            double d5 = (dSqrt - d4) / 2.0d;
            double d6 = ((-dSqrt) - d4) / 2.0d;
            this.function = new OverDamping((getInitialV() - (initialX * d6)) / dSqrt, (-(getInitialV() - (initialX * d5))) / dSqrt, d5, d6, this.xStar);
            return;
        }
        if (d3 == 0.0d) {
            double d7 = (-this.f5983p) / 2.0d;
            this.function = new CriticalDamping(initialX, getInitialV() - (initialX * d7), d7, this.xStar);
        } else {
            double d8 = (-this.f5983p) / 2.0d;
            double dSqrt2 = Math.sqrt(-d3) / 2.0d;
            this.function = new UnderDamping(initialX, (getInitialV() - (initialX * d8)) / dSqrt2, d8, dSqrt2, this.xStar);
        }
    }

    @Override // miuix.animation.motion.Motion
    public double finishTime() {
        if (getInitialX() == stopPosition() && getInitialV() == stopSpeed()) {
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
            solveInternal();
        }
        return this.function;
    }

    @Override // miuix.animation.motion.Motion
    public double stopPosition() {
        return this.xStar + (this.f5982g / this.f5983p);
    }

    @Override // miuix.animation.motion.Motion
    public double stopSpeed() {
        return 0.0d;
    }
}
