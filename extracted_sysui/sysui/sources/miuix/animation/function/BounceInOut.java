package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class BounceInOut implements Differentiable {
    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return d2 < 0.5d ? new BounceIn().apply(d2 * 2.0d) * 0.5d : (new BounceOut().apply((d2 * 2.0d) - 1.0d) * 0.5d) + 0.5d;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return Constant.ZERO;
    }
}
