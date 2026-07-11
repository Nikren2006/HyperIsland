package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class InverseProportional implements Differentiable {
    private Function derivative;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final double f5953k;

    public InverseProportional(double d2) {
        this.f5953k = d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$derivative$0(double d2) {
        return ((-this.f5953k) / d2) / d2;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.f5953k / d2;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new Function() { // from class: C1.c
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f52a.lambda$derivative$0(d2);
                }
            };
        }
        return this.derivative;
    }
}
