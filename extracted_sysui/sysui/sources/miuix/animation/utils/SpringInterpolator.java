package miuix.animation.utils;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes4.dex */
public class SpringInterpolator implements Interpolator {
    private float acceleration;
    private float dampingRatio;
    private long duration;
    private long fakeDuration;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private double f6010g;
    private float inputScale;
    private float mass;
    private double omega;
    private final double overDampThreshold;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private double f6011p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private double f6012q;
    private float response;
    private SpringSolution solution;
    private final double underDampThreshold;
    private float velocity;
    private final double velocityThreshold;
    private double xStar;
    private double zeta;

    public static class CriticalDampingSolution extends SpringSolution {

        /* JADX INFO: renamed from: c1, reason: collision with root package name */
        private final double f6013c1;
        private final double c2;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private final double f6014r;
        private final double xStar;

        public CriticalDampingSolution(double d2, double d3, double d4, double d5, double d6) {
            double d7 = (-d4) / 2.0d;
            this.f6014r = d7;
            this.f6013c1 = d3;
            this.c2 = d5 - (d3 * d7);
            this.xStar = d6;
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double dX(float f2) {
            double d2 = this.f6013c1;
            double d3 = this.f6014r;
            double d4 = this.c2;
            double d5 = f2;
            return ((d2 * d3) + (d4 * ((d3 * d5) + 1.0d))) * Math.exp(d3 * d5);
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double x(float f2) {
            double d2 = f2;
            return ((this.f6013c1 + (this.c2 * d2)) * Math.exp(this.f6014r * d2)) + this.xStar;
        }
    }

    public static class OverDampingSolution extends SpringSolution {

        /* JADX INFO: renamed from: c1, reason: collision with root package name */
        private final double f6015c1;
        private final double c2;

        /* JADX INFO: renamed from: r1, reason: collision with root package name */
        private final double f6016r1;
        private final double r2;
        private final double xStar;

        public OverDampingSolution(double d2, double d3, double d4, double d5, double d6) {
            double dSqrt = Math.sqrt(d2);
            double d7 = (dSqrt - d4) / 2.0d;
            this.f6016r1 = d7;
            double d8 = ((-dSqrt) - d4) / 2.0d;
            this.r2 = d8;
            this.f6015c1 = (d5 - (d3 * d8)) / dSqrt;
            this.c2 = (-(d5 - (d7 * d3))) / dSqrt;
            this.xStar = d6;
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double dX(float f2) {
            double d2 = this.f6015c1;
            double d3 = this.f6016r1;
            double d4 = f2;
            double dExp = d2 * d3 * Math.exp(d3 * d4);
            double d5 = this.c2;
            double d6 = this.r2;
            return dExp + (d5 * d6 * Math.exp(d6 * d4));
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double x(float f2) {
            double d2 = f2;
            return (this.f6015c1 * Math.exp(this.f6016r1 * d2)) + (this.c2 * Math.exp(this.r2 * d2)) + this.xStar;
        }
    }

    public static abstract class SpringSolution {
        public abstract double dX(float f2);

        public double solve(double d2, double d3, double d4, double d5) {
            float f2 = (float) d2;
            double dX = x(f2);
            double dDX = dX(f2);
            return (((d3 * dX) * dX) + (dDX * dDX)) - ((d4 * 2.0d) * (dX - d5));
        }

        public abstract double x(float f2);
    }

    public static class UnderDampingSolution extends SpringSolution {
        private final double alpha;
        private final double beta;

        /* JADX INFO: renamed from: c1, reason: collision with root package name */
        private final double f6017c1;
        private final double c2;
        private final double xStar;

        public UnderDampingSolution(double d2, double d3, double d4, double d5, double d6) {
            double d7 = (-d4) / 2.0d;
            this.alpha = d7;
            double dSqrt = Math.sqrt(-d2) / 2.0d;
            this.beta = dSqrt;
            this.f6017c1 = d3;
            this.c2 = (d5 - (d3 * d7)) / dSqrt;
            this.xStar = d6;
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double dX(float f2) {
            double d2 = f2;
            double dExp = Math.exp(this.alpha * d2);
            double d3 = this.f6017c1 * this.alpha;
            double d4 = this.c2;
            double d5 = this.beta;
            double dCos = (d3 + (d4 * d5)) * Math.cos(d5 * d2);
            double d6 = this.c2 * this.alpha;
            double d7 = this.f6017c1;
            double d8 = this.beta;
            return dExp * (dCos + ((d6 - (d7 * d8)) * Math.sin(d8 * d2)));
        }

        @Override // miuix.animation.utils.SpringInterpolator.SpringSolution
        public double x(float f2) {
            double d2 = f2;
            return (Math.exp(this.alpha * d2) * ((this.f6017c1 * Math.cos(this.beta * d2)) + (this.c2 * Math.sin(this.beta * d2)))) + this.xStar;
        }
    }

    public SpringInterpolator() {
        this(0.85f, 0.3f);
    }

    private double solveDuration(double d2) {
        double d3;
        double dX = 0.0d;
        double d4 = d2 >= 0.0d ? 0.001d : 1.0E-4d;
        double d5 = this.f6010g;
        double d6 = 1.0d;
        if (d5 == 0.0d) {
            float f2 = 0.0f;
            while (Math.abs(dX - 1.0d) > d4) {
                f2 += 0.001f;
                dX = this.solution.x(f2);
                double dDX = this.solution.dX(f2);
                if (Math.abs(dX - 1.0d) <= d4 && dDX <= 5.0E-4d) {
                    break;
                }
            }
            return f2;
        }
        double dSolve = this.solution.solve(0.0d, this.f6012q, d5, this.xStar);
        double d7 = this.f6012q;
        double d8 = this.xStar;
        double d9 = d7 * d8 * d8;
        double d10 = (dSolve - d9) * d4;
        double d11 = 1.0d;
        double dSolve2 = this.solution.solve(1.0d, d7, this.f6010g, d8);
        double d12 = 0.0d;
        while (true) {
            d3 = d9 + d10;
            if (dSolve2 <= d3) {
                break;
            }
            double d13 = d11 + d6;
            d12 = d11;
            d6 = 1.0d;
            d11 = d13;
            dSolve2 = this.solution.solve(d13, this.f6012q, this.f6010g, this.xStar);
            d10 = d10;
        }
        do {
            double d14 = (d12 + d11) / 2.0d;
            if (this.solution.solve(d14, this.f6012q, this.f6010g, this.xStar) > d3) {
                d12 = d14;
            } else {
                d11 = d14;
            }
        } while (d11 - d12 >= d4);
        return d11;
    }

    private void updateParameters() {
        double d2 = this.dampingRatio;
        this.zeta = d2;
        double d3 = 6.283185307179586d / ((double) this.response);
        this.omega = d3;
        float f2 = this.mass;
        double d4 = (((d2 * 2.0d) * d3) * ((double) f2)) / ((double) f2);
        this.f6011p = d4;
        double d5 = ((d3 * d3) * ((double) f2)) / ((double) f2);
        this.f6012q = d5;
        double d6 = this.acceleration;
        this.f6010g = d6;
        double d7 = ((-d6) / d5) + 1.0d;
        this.xStar = d7;
        double d8 = (d4 * d4) - (d5 * 4.0d);
        double d9 = 0.0d - d7;
        if (d8 > 0.0d) {
            this.solution = new OverDampingSolution(d8, d9, d4, this.velocity, d7);
        } else if (d8 == 0.0d) {
            this.solution = new CriticalDampingSolution(d8, d9, d4, this.velocity, d7);
        } else {
            this.solution = new UnderDampingSolution(d8, d9, d4, this.velocity, d7);
        }
        long jSolveDuration = (long) (solveDuration(d8) * 1000.0d);
        this.duration = jSolveDuration;
        this.inputScale = jSolveDuration / 1000.0f;
    }

    public float getDamping() {
        return this.dampingRatio;
    }

    public long getDuration() {
        return this.duration;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        if (f2 == 1.0f) {
            return 1.0f;
        }
        float f3 = f2 * this.inputScale;
        float fX = (float) this.solution.x(f3);
        this.velocity = (float) this.solution.dX(f3);
        return fX;
    }

    public float getResponse() {
        return this.response;
    }

    public SpringInterpolator setAcceleration(float f2) {
        this.acceleration = f2;
        updateParameters();
        return this;
    }

    public SpringInterpolator setDamping(float f2) {
        this.dampingRatio = f2;
        updateParameters();
        return this;
    }

    public SpringInterpolator setDampingAndResponse(float f2, float f3) {
        this.dampingRatio = f2;
        this.response = f3;
        updateParameters();
        return this;
    }

    public SpringInterpolator setFakeDuration(long j2) {
        this.fakeDuration = j2;
        updateParameters();
        this.inputScale = this.fakeDuration / 1000.0f;
        return this;
    }

    public SpringInterpolator setMass(float f2) {
        this.mass = f2;
        updateParameters();
        return this;
    }

    public SpringInterpolator setResponse(float f2) {
        this.response = f2;
        updateParameters();
        return this;
    }

    public SpringInterpolator(float f2, float f3) {
        this(f2, f3, 1.0f);
    }

    public SpringInterpolator(float f2, float f3, float f4) {
        this(f2, f3, f4, 0.0f);
    }

    public SpringInterpolator(float f2, float f3, float f4, float f5) {
        this.underDampThreshold = 1.0E-4d;
        this.overDampThreshold = 0.001d;
        this.velocityThreshold = 5.0E-4d;
        this.fakeDuration = 1000L;
        this.duration = 1000L;
        this.inputScale = 1.0f;
        this.velocity = 0.0f;
        this.dampingRatio = f2;
        this.response = f3;
        this.mass = f4;
        this.acceleration = f5;
        updateParameters();
    }
}
