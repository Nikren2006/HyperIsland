package miuix.animation.function;

/* JADX INFO: loaded from: classes4.dex */
public class Scale implements Function {
    private final Function base;
    private final double pivotX;
    private final double pivotY;
    private final double scaleX;
    private final double scaleY;

    public Scale(Function function, double d2, double d3, double d4, double d5) {
        this.base = function;
        this.scaleX = d2;
        this.scaleY = d3;
        this.pivotX = d4;
        this.pivotY = d5;
    }

    private double convertX(double d2) {
        double d3 = this.scaleX;
        if (d3 == 1.0d) {
            return d2;
        }
        double d4 = this.pivotX;
        return d4 == 0.0d ? d2 * d3 : ((d2 - d4) * d3) + d4;
    }

    private double convertY(double d2) {
        double d3 = this.scaleY;
        if (d3 == 1.0d) {
            return d2;
        }
        double d4 = this.pivotY;
        return d4 == 0.0d ? d2 * d3 : ((d2 - d4) * d3) + d4;
    }

    private double revertX(double d2) {
        double d3 = this.scaleX;
        if (d3 == 1.0d) {
            return d2;
        }
        double d4 = this.pivotX;
        return d4 == 0.0d ? d2 / d3 : ((d2 - d4) / d3) + d4;
    }

    private double revertY(double d2) {
        double d3 = this.scaleY;
        if (d3 == 1.0d) {
            return d2;
        }
        double d4 = this.pivotY;
        return d4 == 0.0d ? d2 / d3 : ((d2 - d4) / d3) + d4;
    }

    @Override // miuix.animation.function.Function
    public double apply(double d2) {
        return convertY(this.base.apply(revertX(d2)));
    }

    public Function getBase() {
        return this.base;
    }

    public double getPivotX() {
        return this.pivotX;
    }

    public double getPivotY() {
        return this.pivotY;
    }

    public double getScaleX() {
        return this.scaleX;
    }

    public double getScaleY() {
        return this.scaleY;
    }
}
