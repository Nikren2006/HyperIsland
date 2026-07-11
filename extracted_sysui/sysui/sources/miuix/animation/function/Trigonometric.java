package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Trigonometric implements Differentiable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double f5959a;
    private Function derivative;
    private final double omega;
    private final double phi;
    private final double xStar;

    public Trigonometric(double d2, double d3, double d4, double d5) {
        this.f5959a = d2;
        this.omega = d3;
        this.phi = d4;
        this.xStar = d5;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return (this.f5959a * Math.cos((this.omega * d2) + this.phi)) + this.xStar;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            double d2 = this.omega;
            this.derivative = new Trigonometric(d2 * this.f5959a, d2, this.phi + 1.5707963267948966d, 0.0d);
        }
        return this.derivative;
    }
}
