package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Constant implements Differentiable {
    public static final Constant ZERO = new Constant(0.0d);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f5944c;

    public Constant(double d2) {
        this.f5944c = d2;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return this.f5944c;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return ZERO;
    }
}
