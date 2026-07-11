package miuix.animation.function;

import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class OverDamping implements Damping {

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private final double f5956c1;
    private final double c2;
    private Function derivative;

    /* JADX INFO: renamed from: r1, reason: collision with root package name */
    private final double f5957r1;
    private final double r2;
    private final double xStar;

    public OverDamping(double d2, double d3, double d4, double d5, double d6) {
        this.f5956c1 = d2;
        this.c2 = d3;
        this.f5957r1 = d4;
        this.r2 = d5;
        this.xStar = d6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$derivative$0(double d2) {
        double d3 = this.f5956c1;
        double d4 = this.f5957r1;
        double dExp = d3 * d4 * Math.exp(d4 * d2);
        double d5 = this.c2;
        double d6 = this.r2;
        return dExp + (d5 * d6 * Math.exp(d6 * d2));
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return (this.f5956c1 * Math.exp(this.f5957r1 * d2)) + (this.c2 * Math.exp(this.r2 * d2)) + this.xStar;
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new Function() { // from class: C1.d
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f53a.lambda$derivative$0(d2);
                }
            };
        }
        return this.derivative;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "OverDamping{c1=%.3f c2=%.3f r1=%.3f r2=%.3f, x*=%.3f}", Double.valueOf(this.f5956c1), Double.valueOf(this.c2), Double.valueOf(this.f5957r1), Double.valueOf(this.r2), Double.valueOf(this.xStar));
    }
}
