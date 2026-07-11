package miuix.overscroller.internal.dynamicanimation.animation;

import miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: loaded from: classes.dex */
public final class SpringForce implements Force {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    private static final float MOVE_STOP_PHYSICAL_DISTANCE = 0.6f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    private static final double UNSET = Double.MAX_VALUE;
    private static final double VELOCITY_THRESHOLD_MULTIPLIER = 62.5d;
    private double mDampedFreq;
    double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized;
    private final DynamicAnimation.MassState mMassState;
    double mNaturalFreq;
    double mTimeRatio;
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mTimeRatio = 1000.0d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
    }

    private void init() {
        if (this.mInitialized) {
            return;
        }
        if (this.mFinalPosition == UNSET) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the miuix.animation starts");
        }
        double d2 = this.mDampingRatio;
        if (d2 > 1.0d) {
            double d3 = this.mNaturalFreq;
            this.mGammaPlus = ((-d2) * d3) + (d3 * Math.sqrt((d2 * d2) - 1.0d));
            double d4 = this.mDampingRatio;
            double d5 = this.mNaturalFreq;
            this.mGammaMinus = ((-d4) * d5) - (d5 * Math.sqrt((d4 * d4) - 1.0d));
        } else if (d2 >= 0.0d && d2 < 1.0d) {
            this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0d - (d2 * d2));
        }
        this.mInitialized = true;
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.Force
    public float getAcceleration(float f2, float f3) {
        float finalPosition = f2 - getFinalPosition();
        double d2 = this.mNaturalFreq;
        return (float) (((-(d2 * d2)) * ((double) finalPosition)) - (((d2 * 2.0d) * this.mDampingRatio) * ((double) f3)));
    }

    public float getDampingRatio() {
        return (float) this.mDampingRatio;
    }

    public float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    public float getStiffness() {
        double d2 = this.mNaturalFreq;
        return (float) (d2 * d2);
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.Force
    public boolean isAtEquilibrium(float f2, float f3) {
        return ((double) Math.abs(f3)) < this.mVelocityThreshold && ((double) Math.abs(f2 - getFinalPosition())) < this.mValueThreshold;
    }

    public SpringForce setDampingRatio(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.mDampingRatio = f2;
        this.mInitialized = false;
        return this;
    }

    public SpringForce setFinalPosition(float f2) {
        this.mFinalPosition = f2;
        return this;
    }

    public SpringForce setStiffness(float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.mNaturalFreq = Math.sqrt(f2);
        this.mInitialized = false;
        return this;
    }

    public SpringForce setTimeRatio(double d2) {
        this.mTimeRatio = d2;
        return this;
    }

    public void setValueThreshold(double d2) {
        double dAbs = Math.abs(d2);
        this.mValueThreshold = dAbs;
        this.mVelocityThreshold = dAbs * VELOCITY_THRESHOLD_MULTIPLIER;
    }

    public DynamicAnimation.MassState updateValues(double d2, double d3, long j2) {
        double dCos;
        double dPow;
        init();
        double d4 = j2 / 1.0E9d;
        double d5 = d2 - this.mFinalPosition;
        double d6 = this.mDampingRatio;
        if (d6 > 1.0d) {
            double d7 = this.mGammaMinus;
            double d8 = this.mGammaPlus;
            double d9 = d5 - (((d7 * d5) - d3) / (d7 - d8));
            double d10 = ((d5 * d7) - d3) / (d7 - d8);
            dPow = (Math.pow(2.718281828459045d, d7 * d4) * d9) + (Math.pow(2.718281828459045d, this.mGammaPlus * d4) * d10);
            double d11 = this.mGammaMinus;
            double dPow2 = d9 * d11 * Math.pow(2.718281828459045d, d11 * d4);
            double d12 = this.mGammaPlus;
            dCos = dPow2 + (d10 * d12 * Math.pow(2.718281828459045d, d12 * d4));
        } else if (d6 == 1.0d) {
            double d13 = this.mNaturalFreq;
            double d14 = d3 + (d13 * d5);
            double d15 = d5 + (d14 * d4);
            dPow = Math.pow(2.718281828459045d, (-d13) * d4) * d15;
            double dPow3 = d15 * Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d4);
            double d16 = this.mNaturalFreq;
            dCos = (d14 * Math.pow(2.718281828459045d, (-d16) * d4)) + (dPow3 * (-d16));
        } else {
            double d17 = 1.0d / this.mDampedFreq;
            double d18 = this.mNaturalFreq;
            double d19 = d17 * ((d6 * d18 * d5) + d3);
            double dPow4 = Math.pow(2.718281828459045d, (-d6) * d18 * d4) * ((Math.cos(this.mDampedFreq * d4) * d5) + (Math.sin(this.mDampedFreq * d4) * d19));
            double d20 = this.mNaturalFreq;
            double d21 = this.mDampingRatio;
            double d22 = (-d20) * dPow4 * d21;
            double dPow5 = Math.pow(2.718281828459045d, (-d21) * d20 * d4);
            double d23 = this.mDampedFreq;
            double dSin = (-d23) * d5 * Math.sin(d23 * d4);
            double d24 = this.mDampedFreq;
            dCos = d22 + (dPow5 * (dSin + (d19 * d24 * Math.cos(d24 * d4))));
            dPow = dPow4;
        }
        if (Math.abs(dPow) < 0.6000000238418579d) {
            dPow = 0.0d;
            dCos = 0.0d;
        }
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = (float) (dPow + this.mFinalPosition);
        massState.mVelocity = (float) dCos;
        return massState;
    }

    public SpringForce(float f2) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mTimeRatio = 1000.0d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f2;
    }
}
