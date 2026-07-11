package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class FreeDamping implements Differentiable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f5949c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final double f5950d;
    private Function derivative;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final double f5951g;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final double f5952p;

    public FreeDamping(double d2, double d3, double d4, double d5) {
        this.f5949c = d2;
        this.f5950d = d3;
        this.f5952p = d4;
        this.f5951g = d5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$derivative$0(double d2) {
        return (this.f5949c * Math.exp((-this.f5952p) * d2)) + (this.f5951g / this.f5952p);
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        double d3 = this.f5949c;
        double d4 = this.f5952p;
        return ((-(d3 / d4)) * Math.exp((-d4) * d2)) + ((this.f5951g / this.f5952p) * d2) + this.f5950d;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new Function() { // from class: C1.b
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f51a.lambda$derivative$0(d2);
                }
            };
        }
        return this.derivative;
    }
}
