package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class DifferentiableImpl implements Differentiable {
    private final Function derivative;
    private final Function function;

    public DifferentiableImpl(Function function, Function function2) {
        this.function = function;
        this.derivative = function2;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.function.apply(d2);
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return this.derivative;
    }
}
