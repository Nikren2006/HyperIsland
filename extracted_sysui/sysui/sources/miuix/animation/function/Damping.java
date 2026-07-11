package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public interface Damping extends Differentiable {
    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    double apply(double d2);

    @Override // miuix.animation.function.Differentiable
    Function derivative();
}
