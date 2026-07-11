package miuix.animation.function;

import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class CriticalDamping implements Damping {

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private final double f5945c1;
    private final double c2;
    private Function derivative;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private final double f5946r;
    private final double xStar;

    public CriticalDamping(double d2, double d3, double d4, double d5) {
        this.f5945c1 = d2;
        this.c2 = d3;
        this.f5946r = d4;
        this.xStar = d5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$derivative$0(double d2) {
        double d3 = this.f5945c1;
        double d4 = this.f5946r;
        return ((d3 * d4) + (this.c2 * ((d4 * d2) + 1.0d))) * Math.exp(d4 * d2);
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return ((this.f5945c1 + (this.c2 * d2)) * Math.exp(this.f5946r * d2)) + this.xStar;
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new Function() { // from class: C1.a
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f50a.lambda$derivative$0(d2);
                }
            };
        }
        return this.derivative;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "CriticalDamping{c1=%.3f c2=%.3f r=%.3f x*=%.3f}", Double.valueOf(this.f5945c1), Double.valueOf(this.c2), Double.valueOf(this.f5946r), Double.valueOf(this.xStar));
    }
}
