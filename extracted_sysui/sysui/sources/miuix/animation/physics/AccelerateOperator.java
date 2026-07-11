package miuix.animation.physics;

/* JADX INFO: loaded from: classes4.dex */
public class AccelerateOperator implements PhysicsOperator {
    @Override // miuix.animation.physics.PhysicsOperator
    public void getParameters(double[] dArr, double[] dArr2) {
        dArr2[0] = dArr[0] * 1000.0d;
    }

    @Override // miuix.animation.physics.PhysicsOperator
    public double updateVelocity(double d2, double d3, double d4, double d5, double... dArr) {
        return d2 + (d3 * d5);
    }
}
