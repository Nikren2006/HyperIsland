package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Accelerate implements Differentiable {
    private final double mDoubleFactor;
    private final double mFactor;

    public Accelerate() {
        this.mFactor = 1.0d;
        this.mDoubleFactor = 2.0d;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.mFactor == 1.0d ? d2 * d2 : Math.pow(d2, this.mDoubleFactor);
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return Constant.ZERO;
    }

    public Accelerate(double d2) {
        this.mFactor = d2;
        this.mDoubleFactor = d2 * 2.0d;
    }
}
