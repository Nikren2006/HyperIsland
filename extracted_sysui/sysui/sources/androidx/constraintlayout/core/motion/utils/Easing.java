package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Easing {
    private static final String ACCELERATE = "cubic(0.4, 0.05, 0.8, 0.7)";
    private static final String ACCELERATE_NAME = "accelerate";
    private static final String ANTICIPATE = "cubic(0.36, 0, 0.66, -0.56)";
    private static final String ANTICIPATE_NAME = "anticipate";
    private static final String DECELERATE = "cubic(0.0, 0.0, 0.2, 0.95)";
    private static final String DECELERATE_NAME = "decelerate";
    private static final String LINEAR = "cubic(1, 1, 0, 0)";
    private static final String LINEAR_NAME = "linear";
    private static final String OVERSHOOT = "cubic(0.34, 1.56, 0.64, 1)";
    private static final String OVERSHOOT_NAME = "overshoot";
    private static final String STANDARD = "cubic(0.4, 0.0, 0.2, 1)";
    String mStr = "identity";
    static Easing sDefault = new Easing();
    private static final String STANDARD_NAME = "standard";
    public static String[] NAMED_EASING = {STANDARD_NAME, "accelerate", "decelerate", "linear"};

    public static Easing getInterpolator(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new CubicEasing(str);
        }
        if (str.startsWith("spline")) {
            return new StepCurve(str);
        }
        if (str.startsWith("Schlick")) {
            return new Schlick(str);
        }
        switch (str) {
            case "accelerate":
                return new CubicEasing(ACCELERATE);
            case "decelerate":
                return new CubicEasing(DECELERATE);
            case "anticipate":
                return new CubicEasing(ANTICIPATE);
            case "linear":
                return new CubicEasing(LINEAR);
            case "overshoot":
                return new CubicEasing(OVERSHOOT);
            case "standard":
                return new CubicEasing(STANDARD);
            default:
                System.err.println("transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(NAMED_EASING));
                return sDefault;
        }
    }

    public double get(double d2) {
        return d2;
    }

    public double getDiff(double d2) {
        return 1.0d;
    }

    public String toString() {
        return this.mStr;
    }

    public static class CubicEasing extends Easing {
        private static double sDError = 1.0E-4d;
        private static double sError = 0.01d;
        double mX1;
        double mX2;
        double mY1;
        double mY2;

        public CubicEasing(String str) {
            this.mStr = str;
            int iIndexOf = str.indexOf(40);
            int iIndexOf2 = str.indexOf(44, iIndexOf);
            this.mX1 = Double.parseDouble(str.substring(iIndexOf + 1, iIndexOf2).trim());
            int i2 = iIndexOf2 + 1;
            int iIndexOf3 = str.indexOf(44, i2);
            this.mY1 = Double.parseDouble(str.substring(i2, iIndexOf3).trim());
            int i3 = iIndexOf3 + 1;
            int iIndexOf4 = str.indexOf(44, i3);
            this.mX2 = Double.parseDouble(str.substring(i3, iIndexOf4).trim());
            int i4 = iIndexOf4 + 1;
            this.mY2 = Double.parseDouble(str.substring(i4, str.indexOf(41, i4)).trim());
        }

        private double getDiffX(double d2) {
            double d3 = 1.0d - d2;
            double d4 = this.mX1;
            double d5 = this.mX2;
            return (d3 * 3.0d * d3 * d4) + (d3 * 6.0d * d2 * (d5 - d4)) + (3.0d * d2 * d2 * (1.0d - d5));
        }

        private double getDiffY(double d2) {
            double d3 = 1.0d - d2;
            double d4 = this.mY1;
            double d5 = this.mY2;
            return (d3 * 3.0d * d3 * d4) + (d3 * 6.0d * d2 * (d5 - d4)) + (3.0d * d2 * d2 * (1.0d - d5));
        }

        private double getX(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.mX1 * d3 * d4 * d2) + (this.mX2 * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        private double getY(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.mY1 * d3 * d4 * d2) + (this.mY2 * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double get(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > sError) {
                d3 *= 0.5d;
                d4 = getX(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double x2 = getX(d5);
            double d6 = d4 + d3;
            double x3 = getX(d6);
            double y2 = getY(d5);
            return (((getY(d6) - y2) * (d2 - x2)) / (x3 - x2)) + y2;
        }

        @Override // androidx.constraintlayout.core.motion.utils.Easing
        public double getDiff(double d2) {
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > sDError) {
                d3 *= 0.5d;
                d4 = getX(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d4 + d3;
            return (getY(d6) - getY(d5)) / (getX(d6) - getX(d5));
        }

        public void setup(double d2, double d3, double d4, double d5) {
            this.mX1 = d2;
            this.mY1 = d3;
            this.mX2 = d4;
            this.mY2 = d5;
        }

        public CubicEasing(double d2, double d3, double d4, double d5) {
            setup(d2, d3, d4, d5);
        }
    }
}
