package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Bounce implements Differentiable {
    private static double bounce(double d2) {
        return d2 * d2 * 8.0d;
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        double dBounce;
        double d3;
        double d4 = d2 * 1.1226d;
        if (d4 < 0.3535d) {
            return bounce(d4);
        }
        if (d4 < 0.7408d) {
            dBounce = bounce(d4 - 0.54719d);
            d3 = 0.7d;
        } else if (d4 < 0.9644d) {
            dBounce = bounce(d4 - 0.8526d);
            d3 = 0.9d;
        } else {
            dBounce = bounce(d4 - 1.0435d);
            d3 = 0.95d;
        }
        return dBounce + d3;
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        return Constant.ZERO;
    }
}
