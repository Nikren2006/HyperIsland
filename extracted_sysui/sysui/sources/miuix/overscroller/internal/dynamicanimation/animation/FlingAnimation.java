package miuix.overscroller.internal.dynamicanimation.animation;

import miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation;

/* JADX INFO: loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    private FinalValueListener mFinalValueListener;
    private final DragForce mFlingForce;

    public static final class DragForce implements Force {
        private static final float DEFAULT_FRICTION = -4.2f;
        private static final float VELOCITY_THRESHOLD_MULTIPLIER = 62.5f;
        private double mDragRate;
        private float mVelocityThreshold;
        private float mFriction = DEFAULT_FRICTION;
        private final DynamicAnimation.MassState mMassState = new DynamicAnimation.MassState();
        private final double NANOSECONDS_PER_SECOND = 1.0E9d;

        @Override // miuix.overscroller.internal.dynamicanimation.animation.Force
        public float getAcceleration(float f2, float f3) {
            return f3 * this.mFriction;
        }

        public float getFrictionScalar() {
            return this.mFriction / DEFAULT_FRICTION;
        }

        @Override // miuix.overscroller.internal.dynamicanimation.animation.Force
        public boolean isAtEquilibrium(float f2, float f3) {
            return Math.abs(f3) < this.mVelocityThreshold;
        }

        public void setFrictionScalar(float f2) {
            float f3 = f2 * DEFAULT_FRICTION;
            this.mFriction = f3;
            this.mDragRate = 1.0d - Math.pow(2.718281828459045d, f3);
        }

        public void setValueThreshold(float f2) {
            this.mVelocityThreshold = f2 * VELOCITY_THRESHOLD_MULTIPLIER;
        }

        public DynamicAnimation.MassState updateValueAndVelocity(float f2, float f3, long j2) {
            double d2 = j2 / 1.0E9d;
            double dPow = Math.pow(1.0d - this.mDragRate, d2);
            DynamicAnimation.MassState massState = this.mMassState;
            float f4 = (float) (((double) f3) * dPow);
            massState.mVelocity = f4;
            float f5 = (float) (((double) f2) + (((double) f4) * d2));
            massState.mValue = f5;
            if (isAtEquilibrium(f5, f4)) {
                this.mMassState.mVelocity = 0.0f;
            }
            return this.mMassState;
        }
    }

    public interface FinalValueListener {
        void onFinalValueArrived(int i2);
    }

    public FlingAnimation(FloatValueHolder floatValueHolder, FinalValueListener finalValueListener) {
        super(floatValueHolder);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.setValueThreshold(getValueThreshold());
        this.mFinalValueListener = finalValueListener;
    }

    private float predictTimeWithVelocity(float f2) {
        return (float) ((Math.log(f2 / this.mVelocity) * 1000.0d) / ((double) this.mFlingForce.mFriction));
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public float getAcceleration(float f2, float f3) {
        return this.mFlingForce.getAcceleration(f2, f3);
    }

    public float getFriction() {
        return this.mFlingForce.getFrictionScalar();
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public boolean isAtEquilibrium(float f2, float f3) {
        return f2 >= this.mMaxValue || f2 <= this.mMinValue || this.mFlingForce.isAtEquilibrium(f2, f3);
    }

    public float predictDuration() {
        return predictTimeWithVelocity(Math.signum(this.mVelocity) * this.mFlingForce.mVelocityThreshold);
    }

    public float predictNaturalDest() {
        return (this.mValue - (this.mVelocity / this.mFlingForce.mFriction)) + ((Math.signum(this.mVelocity) * this.mFlingForce.mVelocityThreshold) / this.mFlingForce.mFriction);
    }

    public float predictTimeTo(float f2) {
        return predictTimeWithVelocity(((f2 - this.mValue) + (this.mVelocity / this.mFlingForce.mFriction)) * this.mFlingForce.mFriction);
    }

    public FlingAnimation setFriction(float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Friction must be positive");
        }
        this.mFlingForce.setFrictionScalar(f2);
        return this;
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public void setValueThreshold(float f2) {
        this.mFlingForce.setValueThreshold(f2);
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public boolean updateValueAndVelocity(long j2) {
        DynamicAnimation.MassState massStateUpdateValueAndVelocity = this.mFlingForce.updateValueAndVelocity(this.mValue, this.mVelocity, j2);
        float f2 = massStateUpdateValueAndVelocity.mValue;
        this.mValue = f2;
        float f3 = massStateUpdateValueAndVelocity.mVelocity;
        this.mVelocity = f3;
        float f4 = this.mMinValue;
        if (f2 < f4) {
            this.mValue = f4;
            return true;
        }
        float f5 = this.mMaxValue;
        if (f2 > f5) {
            this.mValue = f5;
            return true;
        }
        if (!isAtEquilibrium(f2, f3)) {
            return false;
        }
        this.mFinalValueListener.onFinalValueArrived((int) this.mValue);
        return true;
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMaxValue(float f2) {
        super.setMaxValue(f2);
        return this;
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMinValue(float f2) {
        super.setMinValue(f2);
        return this;
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setStartVelocity(float f2) {
        super.setStartVelocity(f2);
        return this;
    }

    public <K> FlingAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k2, floatPropertyCompat);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.setValueThreshold(getValueThreshold());
    }
}
