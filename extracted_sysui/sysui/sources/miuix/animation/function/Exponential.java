package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Exponential implements Differentiable {
    public static final Exponential EXP = new Exponential();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double f5947a;
    private Function derivative;
    private final boolean isExp;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final double f5948k;

    private Exponential() {
        this(1.0d, 2.718281828459045d);
        this.derivative = this;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.f5948k * (this.isExp ? Math.pow(this.f5947a, d2) : Math.exp(d2));
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = this.isExp ? new Exponential(this.f5948k, 2.718281828459045d) : new Exponential(this.f5948k * Math.log(this.f5947a), this.f5947a);
        }
        return this.derivative;
    }

    public Exponential(double d2) {
        this(1.0d, d2);
    }

    public Exponential(double d2, double d3) {
        this.f5948k = d2;
        this.f5947a = d3;
        this.isExp = d3 == 2.718281828459045d;
    }
}
