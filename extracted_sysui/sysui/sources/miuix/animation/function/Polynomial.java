package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Polynomial implements Differentiable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double[] f5958a;
    private Function derivative;

    public Polynomial(int i2, double... dArr) {
        int i3 = 1;
        int i4 = i2 + 1;
        if (dArr.length != i4) {
            throw new IllegalArgumentException("params must have a length of " + i4);
        }
        if (dArr[0] != 0.0d) {
            this.f5958a = dArr;
            return;
        }
        while (i3 < dArr.length && dArr[i3] == 0.0d) {
            i3++;
        }
        i3 = i3 == dArr.length ? i3 - 1 : i3;
        double[] dArr2 = new double[dArr.length - i3];
        this.f5958a = dArr2;
        System.arraycopy(dArr, i3, dArr2, 0, dArr2.length);
    }

    @Override // miuix.animation.function.Differentiable, miuix.animation.function.Function
    public double apply(double d2) {
        double d3 = this.f5958a[0];
        int i2 = 1;
        while (true) {
            double[] dArr = this.f5958a;
            if (i2 >= dArr.length) {
                return d3;
            }
            d3 = (d3 * d2) + dArr[i2];
            i2++;
        }
    }

    @Override // miuix.animation.function.Differentiable
    public Function derivative() {
        if (this.derivative == null) {
            double[] dArr = this.f5958a;
            if (dArr.length == 1) {
                this.derivative = Constant.ZERO;
            } else {
                int length = dArr.length;
                int i2 = length - 1;
                double[] dArr2 = new double[i2];
                for (int i3 = 0; i3 < i2; i3++) {
                    dArr2[i3] = ((double) (i2 - i3)) * this.f5958a[i3];
                }
                this.derivative = new Polynomial(length - 2, dArr2);
            }
        }
        return this.derivative;
    }
}
