package miuix.animation.motion;

import miuix.animation.function.Differentiable;
import miuix.animation.function.Function;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidDampedHarmonicMotion extends DampedHarmonicMotion implements AndroidMotion {
    private double finishTime;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final double f5975g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final double f5976p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final double f5977q;
    private double threshold;
    private final double xStar;

    public AndroidDampedHarmonicMotion(double d2, double d3, double d4, double d5) {
        super(d2, d3, d4, d5);
        this.f5976p = d2 * 2.0d * d3;
        double d6 = d3 * d3;
        this.f5977q = d6;
        this.xStar = ((-d5) / d6) + d4;
        this.f5975g = d5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$solveFinishTime$0(Differentiable differentiable, double d2) {
        double dApply = differentiable.apply(d2);
        double dApply2 = differentiable.derivative().apply(d2);
        return (((this.f5977q * dApply) * dApply) + (dApply2 * dApply2)) - ((this.f5975g * 2.0d) * (dApply - this.xStar));
    }

    private double solveFinishTime() {
        double d2;
        double dFinishTime = super.finishTime();
        if (dFinishTime == 0.0d || this.threshold == 0.0d) {
            return dFinishTime;
        }
        final Differentiable differentiableSolve = solve();
        if (this.f5975g == 0.0d) {
            return (-Math.log(this.threshold)) / this.f5976p;
        }
        Function function = new Function() { // from class: miuix.animation.motion.a
            @Override // miuix.animation.function.Function
            public final double apply(double d3) {
                return this.f5991a.lambda$solveFinishTime$0(differentiableSolve, d3);
            }
        };
        double dApply = function.apply(0.0d);
        double d3 = this.f5977q;
        double d4 = this.xStar;
        double d5 = d3 * d4 * d4;
        double d6 = (dApply - d5) * this.threshold;
        double dApply2 = function.apply(1.0d);
        double d7 = 0.0d;
        double d8 = 1.0d;
        while (true) {
            d2 = d5 + d6;
            if (dApply2 <= d2) {
                break;
            }
            double d9 = d8 + 1.0d;
            dApply2 = function.apply(d9);
            double d10 = d8;
            d8 = d9;
            d7 = d10;
        }
        do {
            double d11 = (d7 + d8) / 2.0d;
            if (function.apply(d11) > d2) {
                d7 = d11;
            } else {
                d8 = d11;
            }
        } while (d8 - d7 >= this.threshold);
        return d8;
    }

    @Override // miuix.animation.motion.DampedHarmonicMotion, miuix.animation.motion.Motion
    public double finishTime() {
        if (Double.isNaN(this.finishTime)) {
            this.finishTime = solveFinishTime();
        }
        return this.finishTime;
    }

    @Override // miuix.animation.motion.DampedHarmonicMotion, miuix.animation.motion.BaseMotion
    public void onInitialVChanged() {
        super.onInitialVChanged();
        this.finishTime = Double.NaN;
    }

    @Override // miuix.animation.motion.DampedHarmonicMotion, miuix.animation.motion.BaseMotion
    public void onInitialXChanged() {
        super.onInitialXChanged();
        this.finishTime = Double.NaN;
    }

    @Override // miuix.animation.motion.AndroidMotion
    public void setThreshold(double d2) {
        this.threshold = d2;
        this.finishTime = Double.NaN;
    }
}
