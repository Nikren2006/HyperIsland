package miuix.animation.physics;

/* JADX INFO: loaded from: classes4.dex */
public interface PhysicsOperator {
    void getParameters(double[] dArr, double[] dArr2);

    @Deprecated
    default void getParameters(float[] fArr, double[] dArr) {
        double[] dArr2 = new double[fArr.length];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            dArr2[i2] = fArr[i2];
        }
        getParameters(dArr2, dArr);
    }

    double updateVelocity(double d2, double d3, double d4, double d5, double... dArr);
}
