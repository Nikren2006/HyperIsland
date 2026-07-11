package miuix.springback.view;

/* JADX INFO: loaded from: classes5.dex */
public class SpringOperator {
    private final double damping;
    private final double tension;

    public SpringOperator(float f2, float f3) {
        double d2 = f3;
        this.tension = Math.pow(6.283185307179586d / d2, 2.0d);
        this.damping = (((double) f2) * 12.566370614359172d) / d2;
    }

    public double updateVelocity(double d2, double d3, double d4, double d5) {
        return (d2 * (1.0d - (this.damping * d3))) + ((double) ((float) (this.tension * (d4 - d5) * d3)));
    }
}
