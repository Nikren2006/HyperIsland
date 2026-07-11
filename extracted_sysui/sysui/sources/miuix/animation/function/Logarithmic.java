package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Logarithmic implements Differentiable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double f5954a;
    private Function derivative;
    private final boolean isLn;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final double f5955k;

    public Logarithmic(double d2) {
        this(1.0d, d2);
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.isLn ? this.f5955k * Math.log(d2) : (this.f5955k * Math.log(d2)) / Math.log(this.f5954a);
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new InverseProportional(this.isLn ? this.f5955k : this.f5955k / Math.log(this.f5954a));
        }
        return this.derivative;
    }

    public Logarithmic(double d2, double d3) {
        this.f5955k = d2;
        this.f5954a = d3;
        this.isLn = d3 == 2.718281828459045d;
    }
}
