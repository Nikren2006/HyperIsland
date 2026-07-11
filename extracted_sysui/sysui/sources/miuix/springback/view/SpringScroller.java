package miuix.springback.view;

import miuix.view.animation.AnimationUtils;

/* JADX INFO: loaded from: classes5.dex */
public class SpringScroller {
    private static final float MAX_DELTA_TIME = 0.016f;
    private static final float VALUE_THRESHOLD = 1.0f;
    private double mCurrX;
    private double mCurrY;
    private long mCurrentTimeNanos;
    private double mEndX;
    private double mEndY;
    private boolean mFinished = true;
    private int mFirstStep;
    private boolean mLastStep;
    private int mOrientation;
    private double mOriginStartX;
    private double mOriginStartY;
    private double mOriginVelocity;
    private SpringOperator mSpringOperator;
    private long mStartTimeNanos;
    private double mStartX;
    private double mStartY;
    private double mVelocity;

    public boolean computeScrollOffset() {
        if (this.mSpringOperator == null || this.mFinished) {
            return false;
        }
        int i2 = this.mFirstStep;
        if (i2 != 0) {
            if (this.mOrientation == 1) {
                this.mCurrX = i2;
                this.mStartX = i2;
            } else {
                this.mCurrY = i2;
                this.mStartY = i2;
            }
            this.mFirstStep = 0;
            return true;
        }
        if (this.mLastStep) {
            this.mFinished = true;
            return true;
        }
        this.mCurrentTimeNanos = AnimationUtils.currentAnimationTimeNanos();
        double dMin = Math.min((r0 - this.mStartTimeNanos) / 1.0E9d, 0.01600000075995922d);
        double d2 = dMin != 0.0d ? dMin : 0.01600000075995922d;
        this.mStartTimeNanos = this.mCurrentTimeNanos;
        if (this.mOrientation == 2) {
            double dUpdateVelocity = this.mSpringOperator.updateVelocity(this.mVelocity, d2, this.mEndY, this.mStartY);
            double d3 = this.mStartY + (d2 * dUpdateVelocity);
            this.mCurrY = d3;
            this.mVelocity = dUpdateVelocity;
            if (isAtEquilibrium(d3, this.mOriginStartY, this.mEndY)) {
                this.mLastStep = true;
                this.mCurrY = this.mEndY;
            } else {
                this.mStartY = this.mCurrY;
            }
        } else {
            double dUpdateVelocity2 = this.mSpringOperator.updateVelocity(this.mVelocity, d2, this.mEndX, this.mStartX);
            double d4 = this.mStartX + (d2 * dUpdateVelocity2);
            this.mCurrX = d4;
            this.mVelocity = dUpdateVelocity2;
            if (isAtEquilibrium(d4, this.mOriginStartX, this.mEndX)) {
                this.mLastStep = true;
                this.mCurrX = this.mEndX;
            } else {
                this.mStartX = this.mCurrX;
            }
        }
        return true;
    }

    public final void forceStop() {
        this.mFinished = true;
        this.mFirstStep = 0;
    }

    public final int getCurrX() {
        return (int) this.mCurrX;
    }

    public final int getCurrY() {
        return (int) this.mCurrY;
    }

    public boolean isAtEquilibrium(double d2, double d3, double d4) {
        if (d3 < d4 && d2 > d4) {
            return true;
        }
        if (d3 <= d4 || d2 >= d4) {
            return (d3 == d4 && Math.signum(this.mOriginVelocity) != Math.signum(d2)) || Math.abs(d2 - d4) < 1.0d;
        }
        return true;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public void scrollByFling(float f2, float f3, float f4, float f5, float f6, int i2, boolean z2) {
        this.mFinished = false;
        this.mLastStep = false;
        double d2 = f2;
        this.mStartX = d2;
        this.mOriginStartX = d2;
        this.mEndX = f3;
        double d3 = f4;
        this.mStartY = d3;
        this.mOriginStartY = d3;
        this.mCurrY = (int) d3;
        this.mEndY = f5;
        double d4 = f6;
        this.mOriginVelocity = d4;
        this.mVelocity = d4;
        if (Math.abs(d4) <= 5000.0d || z2) {
            this.mSpringOperator = new SpringOperator(1.0f, 0.4f);
        } else {
            this.mSpringOperator = new SpringOperator(1.0f, 0.55f);
        }
        this.mOrientation = i2;
        this.mStartTimeNanos = AnimationUtils.currentAnimationTimeNanos();
    }

    public void setFirstStep(int i2) {
        this.mFirstStep = i2;
    }
}
