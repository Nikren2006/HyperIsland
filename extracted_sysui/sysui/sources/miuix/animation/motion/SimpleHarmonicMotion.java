package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.Trigonometric;

/* JADX INFO: loaded from: classes4.dex */
public class SimpleHarmonicMotion extends BaseMotion implements Motion {
    private Differentiable function;
    private double omega;
    private double xStar;

    public SimpleHarmonicMotion(double d2, double d3, double d4) {
        this.omega = Math.sqrt(d2 / d4);
        this.xStar = (d4 * d3) / d2;
        this.function = null;
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
            double initialX = getInitialX() - this.xStar;
            double initialV = getInitialV() * getInitialV();
            double d2 = this.omega;
            this.function = new Trigonometric(Math.sqrt((initialX * initialX) + ((initialV / d2) / d2)), this.omega, Math.atan2(-getInitialV(), this.omega * initialX), this.xStar);
        }
        return this.function;
    }

    public SimpleHarmonicMotion(double d2) {
        this.omega = d2;
        this.xStar = 0.0d;
        this.function = null;
    }
}
