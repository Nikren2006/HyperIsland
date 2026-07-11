package miuix.androidbasewidget.widget;

import android.view.animation.AnimationUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SpringScroller {
    private static final int HORIZONTAL = 1;
    private static final float MAX_DELTA_TIME = 0.016f;
    private static final float VALUE_THRESHOLD = 1.0f;
    private static final int VERTICAL = 2;
    private double mCurrX;
    private double mCurrY;
    private long mCurrentTime;
    private double mEndX;
    private double mEndY;
    private boolean mFinished = true;
    private boolean mLastStep;
    private int mOrientation;
    private double mOriginStartY;
    private SpringOperator mSpringOperator;
    private long mStartTime;
    private double mStartX;
    private double mStartY;
    private double mVelocity;

    public final void abortAnimation() {
        this.mFinished = true;
    }

    public boolean computeScrollOffset() {
        if (this.mSpringOperator == null || this.mFinished) {
            return false;
        }
        if (this.mLastStep) {
            this.mFinished = true;
            this.mCurrY = this.mEndY;
            this.mCurrX = this.mEndX;
            return true;
        }
        this.mCurrentTime = AnimationUtils.currentAnimationTimeMillis();
        float f2 = (r2 - this.mStartTime) / 1000.0f;
        float f3 = MAX_DELTA_TIME;
        float fMin = Math.min(f2, MAX_DELTA_TIME);
        if (fMin != 0.0f) {
            f3 = fMin;
        }
        this.mStartTime = this.mCurrentTime;
        if (this.mOrientation == 2) {
            double dUpdateVelocity = this.mSpringOperator.updateVelocity(this.mVelocity, f3, this.mEndY, this.mStartY);
            double d2 = this.mStartY + (((double) f3) * dUpdateVelocity);
            this.mCurrY = d2;
            this.mVelocity = dUpdateVelocity;
            if (isAtEquilibrium(d2, this.mEndY)) {
                this.mLastStep = true;
            } else {
                this.mStartY = this.mCurrY;
            }
        } else {
            double dUpdateVelocity2 = this.mSpringOperator.updateVelocity(this.mVelocity, f3, this.mEndX, this.mStartX);
            double d3 = this.mStartX + (((double) f3) * dUpdateVelocity2);
            this.mCurrX = d3;
            this.mVelocity = dUpdateVelocity2;
            if (isAtEquilibrium(d3, this.mEndX)) {
                this.mLastStep = true;
            } else {
                this.mStartX = this.mCurrX;
            }
        }
        return true;
    }

    public final int getCurrX() {
        return (int) this.mCurrX;
    }

    public final int getCurrY() {
        return (int) this.mCurrY;
    }

    public final int getFinalX() {
        return (int) this.mEndX;
    }

    public final int getStartX() {
        return (int) this.mStartX;
    }

    public boolean isAtEquilibrium(double d2, double d3) {
        return Math.abs(d2 - d3) < 1.0d;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public void setFinalX(int i2) {
        this.mEndX = i2;
        this.mFinished = false;
    }

    public void startScroll(float f2, float f3, float f4, float f5, float f6) {
        this.mFinished = false;
        this.mLastStep = false;
        this.mStartX = f2;
        this.mEndX = f3;
        double d2 = f4;
        this.mStartY = d2;
        this.mOriginStartY = d2;
        this.mCurrY = (int) d2;
        this.mEndY = f5;
        double d3 = f6;
        this.mVelocity = d3;
        if (Math.abs(d3) <= 5000.0d) {
            this.mSpringOperator = new SpringOperator(0.9f, 0.35f);
        } else {
            this.mSpringOperator = new SpringOperator(0.9f, 0.35f);
        }
        this.mOrientation = Math.abs(f5 - f4) > Math.abs(f3 - f2) ? 2 : 1;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
    }
}
