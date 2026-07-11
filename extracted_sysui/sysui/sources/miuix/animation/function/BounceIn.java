package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class BounceIn implements Differentiable {
    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return 1.0d - new BounceOut().apply(1.0d - d2);
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return Constant.ZERO;
    }
}
