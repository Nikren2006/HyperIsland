package miuix.animation.physics;

import miuix.animation.physics.DynamicAnimation;

/* JADX INFO: loaded from: classes4.dex */
public final class SpringStepForce extends SpringForce {
    @Override // miuix.animation.physics.SpringForce
    public void init() {
        if (this.mInitialized) {
            return;
        }
        if (this.mFinalPosition == Double.MAX_VALUE) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the miuix.animation starts");
        }
        this.mDampedFreq = Math.min(this.mNaturalFreq * 2.0d * this.mDampingRatio, 60.0d);
        this.mInitialized = true;
    }

    @Override // miuix.animation.physics.SpringForce
    public DynamicAnimation.MassState updateValues(double d2, double d3, long j2) {
        init();
        double d4 = j2 / 1.0E9d;
        double stiffness = ((1.0d - (this.mDampedFreq * d4)) * d3) + (((double) getStiffness()) * (this.mFinalPosition - d2) * d4);
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = (float) (d2 + ((d3 + stiffness) * 0.5d * d4));
        massState.mVelocity = (float) stiffness;
        return massState;
    }
}
