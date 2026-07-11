package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Decelerate implements Differentiable {
    private double mFactor;

    public Decelerate() {
        this.mFactor = 1.0d;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        double dPow;
        double d3 = this.mFactor;
        if (d3 == 1.0d) {
            double d4 = 1.0d - d2;
            dPow = d4 * d4;
        } else {
            dPow = Math.pow(1.0d - d2, d3 * 2.0d);
        }
        return (float) (1.0d - dPow);
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return Constant.ZERO;
    }

    public Decelerate(double d2) {
        this.mFactor = d2;
    }
}
