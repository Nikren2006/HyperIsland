package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Bezier implements Differentiable {
    private static final Bezier ZERO = new Bezier(null, null, null);
    private Differentiable derivative;
    private final double[] originX;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private double[] f5941p;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final double[] f5942x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final double[] f5943y;

    public Bezier(double... dArr) {
        double[] dArr2 = new double[dArr.length / 2];
        this.f5942x = dArr2;
        this.originX = dArr2;
        this.f5943y = new double[dArr.length / 2];
        int i2 = 0;
        while (true) {
            double[] dArr3 = this.f5942x;
            if (i2 >= dArr3.length) {
                return;
            }
            int i3 = i2 * 2;
            dArr3[i2] = dArr[i3];
            this.f5943y[i2] = dArr[i3 + 1];
            i2++;
        }
    }

    private double getTForXValue(double d2) {
        double d3;
        double d4 = 0.0d;
        double d5 = d2;
        double d6 = 1.0d;
        int i2 = 0;
        double dSolveAxis = 0.0d;
        while (true) {
            if (i2 >= 8) {
                d3 = d6;
                break;
            }
            dSolveAxis = solveAxis(this.originX, d5);
            d3 = d6;
            double dSolveAxis2 = (solveAxis(this.originX, d5 + 1.0E-6d) - dSolveAxis) / 1.0E-6d;
            double d7 = dSolveAxis - d2;
            if (Math.abs(d7) < 1.0E-6d) {
                return d5;
            }
            if (Math.abs(dSolveAxis2) < 1.0E-6d) {
                break;
            }
            if (dSolveAxis < d2) {
                d4 = d5;
            } else {
                d3 = d5;
            }
            d5 -= d7 / dSolveAxis2;
            i2++;
            d6 = d3;
        }
        double d8 = d3;
        for (int i3 = 0; Math.abs(dSolveAxis - d2) > 1.0E-6d && i3 < 8; i3++) {
            if (dSolveAxis < d2) {
                d4 = d5;
                d5 = (d5 + d8) / 2.0d;
            } else {
                d8 = d5;
                d5 = (d5 + d4) / 2.0d;
            }
            dSolveAxis = solveAxis(this.originX, d5);
        }
        return d5;
    }

    private double linearInterpolate(double d2, double d3, double d4) {
        return d2 + ((d3 - d2) * d4);
    }

    private double solveAxis(double[] dArr, double d2) {
        if (d2 == 0.0d) {
            return dArr[0];
        }
        if (d2 == 1.0d) {
            return dArr[dArr.length - 1];
        }
        double[] dArr2 = this.f5941p;
        if (dArr2 == null || dArr2.length < dArr.length) {
            this.f5941p = new double[dArr.length];
        }
        System.arraycopy(dArr, 0, this.f5941p, 0, dArr.length);
        for (int length = dArr.length - 1; length > 0; length--) {
            int i2 = 0;
            while (i2 < length) {
                double[] dArr3 = this.f5941p;
                int i3 = i2 + 1;
                dArr3[i2] = linearInterpolate(dArr3[i2], dArr3[i3], d2);
                i2 = i3;
            }
        }
        return this.f5941p[0];
    }

    private Bezier solveDerivative() {
        int length;
        Bezier bezier = ZERO;
        if (this == bezier || (length = this.f5943y.length - 1) <= 1) {
            return bezier;
        }
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        int i2 = 0;
        while (i2 < length) {
            double d2 = length;
            double[] dArr3 = this.f5942x;
            int i3 = i2 + 1;
            dArr[i2] = (dArr3[i3] - dArr3[i2]) * d2;
            double[] dArr4 = this.f5943y;
            dArr2[i2] = d2 * (dArr4[i3] - dArr4[i2]);
            i2 = i3;
        }
        return new Bezier(this.f5942x, dArr, dArr2);
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        if (this == ZERO) {
            return 0.0d;
        }
        double tForXValue = getTForXValue(d2);
        if (this.originX == this.f5942x) {
            return solveAxis(this.f5943y, tForXValue);
        }
        double dSolveAxis = solveAxis(this.f5943y, tForXValue) / solveAxis(this.f5942x, tForXValue);
        if (Double.isNaN(dSolveAxis)) {
            return 0.0d;
        }
        return Math.min(1000000.0d, Math.max(dSolveAxis, -1000000.0d));
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            this.derivative = solveDerivative();
        }
        return this.derivative;
    }

    private Bezier(double[] dArr, double[] dArr2, double[] dArr3) {
        this.originX = dArr;
        this.f5942x = dArr2;
        this.f5943y = dArr3;
    }
}
