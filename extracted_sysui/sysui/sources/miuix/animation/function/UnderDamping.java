package miuix.animation.function;

import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class UnderDamping implements Damping {
    private final double alpha;
    private final double beta;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private final double f5960c1;
    private final double c2;
    private Function derivative;
    private final double xStar;

    public UnderDamping(double d2, double d3, double d4, double d5, double d6) {
        this.f5960c1 = d2;
        this.c2 = d3;
        this.alpha = d4;
        this.beta = d5;
        this.xStar = d6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ double lambda$derivative$0(double d2) {
        double dExp = Math.exp(this.alpha * d2);
        double d3 = this.f5960c1 * this.alpha;
        double d4 = this.c2;
        double d5 = this.beta;
        double dCos = (d3 + (d4 * d5)) * Math.cos(d5 * d2);
        double d6 = this.c2 * this.alpha;
        double d7 = this.f5960c1;
        double d8 = this.beta;
        return dExp * (dCos + ((d6 - (d7 * d8)) * Math.sin(d8 * d2)));
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        return (Math.exp(this.alpha * d2) * ((this.f5960c1 * Math.cos(this.beta * d2)) + (this.c2 * Math.sin(this.beta * d2)))) + this.xStar;
    }

    @Override // miuix.animation.function.Damping, miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = new Function() { // from class: C1.e
                @Override // miuix.animation.function.Function
                public final double apply(double d2) {
                    return this.f54a.lambda$derivative$0(d2);
                }
            };
        }
        return this.derivative;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "UnderDamping{c1=%.3f c2=%.3f a=%.3f b=%.3f x*=%.3f}", Double.valueOf(this.f5960c1), Double.valueOf(this.c2), Double.valueOf(this.alpha), Double.valueOf(this.beta), Double.valueOf(this.xStar));
    }
}
