package miuix.overscroller.widget;

import android.content.Context;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import miuix.animation.controller.AnimState;
import miuix.animation.internal.AnimData;
import miuix.animation.internal.FolmeCore;
import miuix.animation.physics.SpringOperator;
import miuix.view.animation.AnimationUtils;

/* JADX INFO: loaded from: classes.dex */
public class OverScroller {
    private static final int DEFAULT_DURATION = 250;
    public static final int FLING_MODE = 1;
    private static boolean SCROLL_BOOST_SS_ENABLE = false;
    public static final int SCROLL_BY_FLING_MODE = 2;
    public static final int SCROLL_MODE = 0;
    private final boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMode;
    private final SplineOverScroller mScrollerX;
    private final SplineOverScroller mScrollerY;

    public static class SplineOverScroller {
        private static final int BALLISTIC = 2;
        private static final int CUBIC = 1;
        private static final float END_TENSION = 1.0f;
        private static final float GRAVITY = 2000.0f;
        private static final float INFLEXION = 0.35f;
        private static final float MAX_DELTA_TIME = 0.016f;
        private static final int NB_SAMPLES = 100;
        private static final float P1 = 0.175f;
        private static final float P2 = 0.35000002f;
        private static final int SPLINE = 0;
        private static final float START_TENSION = 0.5f;
        static final int STATE_BALLISTIC = 2;
        static final int STATE_CUBIC = 1;
        static final int STATE_SPLINE = 0;
        private static final float VALUE_THRESHOLD = 1.0f;
        private Context mContext;
        private double mCurrVelocity;
        private double mCurrentPosition;
        private float mDeceleration;
        private int mDuration;
        private double mFinal;
        private boolean mLastStep;
        private double mOriginStart;
        private int mOver;
        private float mPhysicalCoeff;
        private int mSplineDistance;
        private int mSplineDuration;
        private AnimData mSpringAnimData;
        private double[] mSpringFactor;
        private SpringOperator mSpringOperator;
        private double[] mSpringParams;
        private double mStart;
        private long mStartTime;
        private double mVelocity;
        private static float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
        private static final float[] SPLINE_POSITION = new float[101];
        private static final float[] SPLINE_TIME = new float[101];
        private float mFlingFriction = ViewConfiguration.getScrollFriction();
        private int mState = 0;
        private boolean mFinished = true;

        static {
            float f2;
            float f3;
            float f4;
            float f5;
            float f6;
            float f7;
            float f8;
            float f9;
            float f10;
            float f11;
            float f12 = 0.0f;
            float f13 = 0.0f;
            for (int i2 = 0; i2 < 100; i2++) {
                float f14 = i2 / 100.0f;
                float f15 = 1.0f;
                while (true) {
                    f2 = 2.0f;
                    f3 = ((f15 - f12) / 2.0f) + f12;
                    f4 = 3.0f;
                    f5 = 1.0f - f3;
                    f6 = f3 * 3.0f * f5;
                    f7 = f3 * f3 * f3;
                    float f16 = (((f5 * P1) + (f3 * P2)) * f6) + f7;
                    if (Math.abs(f16 - f14) < 1.0E-5d) {
                        break;
                    } else if (f16 > f14) {
                        f15 = f3;
                    } else {
                        f12 = f3;
                    }
                }
                SPLINE_POSITION[i2] = (f6 * ((f5 * 0.5f) + f3)) + f7;
                float f17 = 1.0f;
                while (true) {
                    f8 = ((f17 - f13) / f2) + f13;
                    f9 = 1.0f - f8;
                    f10 = f8 * f4 * f9;
                    f11 = f8 * f8 * f8;
                    float f18 = (((f9 * 0.5f) + f8) * f10) + f11;
                    if (Math.abs(f18 - f14) < 1.0E-5d) {
                        break;
                    }
                    if (f18 > f14) {
                        f17 = f8;
                    } else {
                        f13 = f8;
                    }
                    f2 = 2.0f;
                    f4 = 3.0f;
                }
                SPLINE_TIME[i2] = (f10 * ((f9 * P1) + (f8 * P2))) + f11;
            }
            float[] fArr = SPLINE_POSITION;
            SPLINE_TIME[100] = 1.0f;
            fArr[100] = 1.0f;
        }

        public SplineOverScroller(Context context) {
            this.mContext = context;
            this.mPhysicalCoeff = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        }

        private void adjustDuration(int i2, int i3, int i4) {
            float fAbs = Math.abs((i4 - i2) / (i3 - i2));
            int i5 = (int) (fAbs * 100.0f);
            if (i5 < 100) {
                float f2 = i5 / 100.0f;
                int i6 = i5 + 1;
                float[] fArr = SPLINE_TIME;
                float f3 = fArr[i5];
                this.mDuration = (int) (this.mDuration * (f3 + (((fAbs - f2) / ((i6 / 100.0f) - f2)) * (fArr[i6] - f3))));
            }
        }

        private void fitOnBounceCurve(int i2, int i3, int i4) {
            float f2 = this.mDeceleration;
            float f3 = (-i4) / f2;
            float f4 = i4;
            this.mStartTime -= (long) ((int) ((((float) Math.sqrt((((double) ((((f4 * f4) / 2.0f) / Math.abs(f2)) + Math.abs(i3 - i2))) * 2.0d) / ((double) Math.abs(this.mDeceleration)))) - f3) * 1.0E9f));
            double d2 = i3;
            this.mStart = d2;
            this.mCurrentPosition = d2;
            this.mVelocity = (int) ((-this.mDeceleration) * r6);
        }

        private static float getDeceleration(int i2) {
            if (i2 > 0) {
                return -2000.0f;
            }
            return GRAVITY;
        }

        private double getSplineDeceleration(int i2) {
            return Math.log((Math.abs(i2) * 0.35f) / (this.mFlingFriction * this.mPhysicalCoeff));
        }

        private double getSplineFlingDistance(int i2) {
            double splineDeceleration = getSplineDeceleration(i2);
            float f2 = DECELERATION_RATE;
            return ((double) (this.mFlingFriction * this.mPhysicalCoeff)) * Math.exp((((double) f2) / (((double) f2) - 1.0d)) * splineDeceleration);
        }

        private int getSplineFlingDuration(int i2) {
            return (int) (Math.exp(getSplineDeceleration(i2) / (((double) DECELERATION_RATE) - 1.0d)) * 1000.0d);
        }

        private void onEdgeReached() {
            double d2 = this.mVelocity;
            float f2 = ((float) d2) * ((float) d2);
            float fAbs = f2 / (Math.abs(this.mDeceleration) * 2.0f);
            float fSignum = Math.signum((float) this.mVelocity);
            int i2 = this.mOver;
            if (fAbs > i2) {
                this.mDeceleration = ((-fSignum) * f2) / (i2 * 2.0f);
                fAbs = i2;
            }
            this.mOver = (int) fAbs;
            this.mState = 2;
            double d3 = this.mStart;
            double d4 = this.mVelocity;
            if (d4 <= 0.0d) {
                fAbs = -fAbs;
            }
            this.mFinal = d3 + ((double) ((int) fAbs));
            this.mDuration = -((int) ((d4 * 1000.0d) / ((double) this.mDeceleration)));
        }

        private void startAfterEdge(int i2, int i3, int i4, int i5) {
            if (i2 > i3 && i2 < i4) {
                Log.e("OverScroller", "startAfterEdge called from a valid position");
                this.mFinished = true;
                return;
            }
            boolean z2 = i2 > i4;
            int i6 = z2 ? i4 : i3;
            if ((i2 - i6) * i5 >= 0) {
                startBounceAfterEdge(i2, i6, i5);
            } else if (getSplineFlingDistance(i5) > Math.abs(r2)) {
                fling(i2, i5, z2 ? i3 : i2, z2 ? i2 : i4, this.mOver);
            } else {
                startSpringback(i2, i6, i5);
            }
        }

        private void startBounceAfterEdge(int i2, int i3, int i4) {
            this.mDeceleration = getDeceleration(i4 == 0 ? i2 - i3 : i4);
            fitOnBounceCurve(i2, i3, i4);
            onEdgeReached();
        }

        private void startSpringback(int i2, int i3, int i4) {
            this.mFinished = false;
            this.mState = 1;
            double d2 = i2;
            this.mStart = d2;
            this.mCurrentPosition = d2;
            this.mFinal = i3;
            int i5 = i2 - i3;
            this.mDeceleration = getDeceleration(i5);
            this.mVelocity = -i5;
            this.mOver = Math.abs(i5);
            this.mDuration = (int) (Math.sqrt((((double) i5) * (-2.0d)) / ((double) this.mDeceleration)) * 1000.0d);
        }

        public boolean computeScrollOffset() {
            if (this.mSpringOperator == null || this.mFinished) {
                return false;
            }
            if (this.mLastStep) {
                this.mFinished = true;
                this.mCurrentPosition = this.mFinal;
                return true;
            }
            long jCurrentAnimationTimeNanos = AnimationUtils.currentAnimationTimeNanos();
            double dMin = Math.min((jCurrentAnimationTimeNanos - this.mStartTime) / 1.0E9d, 0.01600000075995922d);
            double d2 = dMin == 0.0d ? 0.01600000075995922d : dMin;
            this.mStartTime = jCurrentAnimationTimeNanos;
            AnimData animData = this.mSpringAnimData;
            animData.startValue = this.mStart;
            animData.targetValue = this.mFinal;
            animData.value = this.mCurrentPosition;
            double d3 = this.mSpringFactor[0];
            double[] dArr = this.mSpringParams;
            SpringOperator.updateValues(animData, d3, dArr[1], dArr[2], d2, false);
            AnimData animData2 = this.mSpringAnimData;
            double d4 = animData2.value;
            this.mCurrentPosition = d4;
            this.mCurrVelocity = animData2.velocity;
            if (isAtEquilibrium(d4, this.mFinal)) {
                this.mLastStep = true;
            } else {
                this.mStart = this.mCurrentPosition;
            }
            return true;
        }

        public boolean continueWhenFinished() {
            int i2 = this.mState;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                if (i2 == 2) {
                    this.mStartTime += ((long) this.mDuration) * FolmeCore.NANOS_TO_MS;
                    startSpringback((int) this.mFinal, (int) this.mStart, 0);
                }
            } else {
                if (this.mDuration >= this.mSplineDuration) {
                    return false;
                }
                double d2 = this.mFinal;
                this.mStart = d2;
                this.mCurrentPosition = d2;
                double d3 = (int) this.mCurrVelocity;
                this.mVelocity = d3;
                this.mDeceleration = getDeceleration((int) d3);
                this.mStartTime += ((long) this.mDuration) * FolmeCore.NANOS_TO_MS;
                onEdgeReached();
            }
            update();
            return true;
        }

        public void extendDuration(int i2) {
            this.mDuration = (((int) (AnimationUtils.currentAnimationTimeNanos() - this.mStartTime)) + i2) / AnimState.VIEW_SIZE;
            this.mFinished = false;
        }

        public void finish() {
            this.mCurrentPosition = this.mFinal;
            this.mFinished = true;
        }

        public void fling(int i2, int i3, int i4, int i5, int i6) {
            double splineFlingDistance;
            this.mOver = i6;
            this.mFinished = false;
            double d2 = i3;
            this.mVelocity = d2;
            this.mCurrVelocity = d2;
            this.mSplineDuration = 0;
            this.mDuration = 0;
            this.mStartTime = AnimationUtils.currentAnimationTimeNanos();
            double d3 = i2;
            this.mStart = d3;
            this.mCurrentPosition = d3;
            if (i2 > i5 || i2 < i4) {
                startAfterEdge(i2, i4, i5, i3);
                return;
            }
            this.mState = 0;
            if (i3 != 0) {
                int splineFlingDuration = getSplineFlingDuration(i3);
                this.mSplineDuration = splineFlingDuration;
                this.mDuration = splineFlingDuration;
                splineFlingDistance = getSplineFlingDistance(i3);
            } else {
                splineFlingDistance = 0.0d;
            }
            int iSignum = (int) (splineFlingDistance * ((double) Math.signum(i3)));
            this.mSplineDistance = iSignum;
            double d4 = i2 + iSignum;
            this.mFinal = d4;
            double d5 = i4;
            if (d4 < d5) {
                adjustDuration((int) this.mStart, (int) d4, i4);
                this.mFinal = d5;
            }
            double d6 = this.mFinal;
            double d7 = i5;
            if (d6 > d7) {
                adjustDuration((int) this.mStart, (int) d6, i5);
                this.mFinal = d7;
            }
        }

        public final float getCurrVelocity() {
            return (float) this.mCurrVelocity;
        }

        public final int getCurrentPosition() {
            return (int) this.mCurrentPosition;
        }

        public final int getDuration() {
            return this.mDuration;
        }

        public final int getFinal() {
            return (int) this.mFinal;
        }

        public final int getStart() {
            return (int) this.mStart;
        }

        public final long getStartTime() {
            return this.mStartTime;
        }

        public final int getState() {
            return this.mState;
        }

        public boolean isAtEquilibrium(double d2, double d3) {
            return Math.abs(d2 - d3) < 1.0d;
        }

        public final boolean isFinished() {
            return this.mFinished;
        }

        public void notifyEdgeReached(int i2, int i3, int i4) {
            if (this.mState == 0) {
                this.mOver = i4;
                this.mStartTime = AnimationUtils.currentAnimationTimeNanos();
                startAfterEdge(i2, i3, i3, (int) this.mCurrVelocity);
            }
        }

        public final void setCurrVelocity(float f2) {
            this.mCurrVelocity = f2;
        }

        public final void setCurrentPosition(int i2) {
            this.mCurrentPosition = i2;
        }

        public final void setDuration(int i2) {
            this.mDuration = i2;
        }

        public final void setFinal(int i2) {
            this.mFinal = i2;
        }

        public void setFinalPosition(int i2) {
            this.mFinal = i2;
            this.mFinished = false;
        }

        public final void setFinished(boolean z2) {
            this.mFinished = z2;
        }

        public void setFriction(float f2) {
            this.mFlingFriction = f2;
        }

        public final void setStart(int i2) {
            this.mStart = i2;
        }

        public final void setStartTime(long j2) {
            this.mStartTime = j2;
        }

        public final void setState(int i2) {
            this.mState = i2;
        }

        public boolean springback(int i2, int i3, int i4) {
            this.mFinished = true;
            double d2 = i2;
            this.mFinal = d2;
            this.mStart = d2;
            this.mCurrentPosition = d2;
            this.mVelocity = 0.0d;
            this.mStartTime = AnimationUtils.currentAnimationTimeNanos();
            this.mDuration = 0;
            if (i2 < i3) {
                startSpringback(i2, i3, 0);
            } else if (i2 > i4) {
                startSpringback(i2, i4, 0);
            }
            return !this.mFinished;
        }

        public void startScroll(int i2, int i3, int i4) {
            this.mFinished = false;
            double d2 = i2;
            this.mStart = d2;
            this.mCurrentPosition = d2;
            this.mFinal = i2 + i3;
            this.mStartTime = AnimationUtils.currentAnimationTimeNanos();
            this.mDuration = i4;
            this.mDeceleration = 0.0f;
            this.mVelocity = 0.0d;
        }

        public void startScrollByFling(float f2, int i2, int i3) {
            this.mFinished = false;
            this.mLastStep = false;
            setState(0);
            double d2 = f2;
            this.mOriginStart = d2;
            this.mStart = d2;
            this.mCurrentPosition = d2;
            this.mFinal = f2 + i2;
            this.mStartTime = AnimationUtils.currentAnimationTimeNanos();
            double d3 = i3;
            this.mVelocity = d3;
            this.mCurrVelocity = d3;
            this.mSpringOperator = new SpringOperator();
            this.mSpringFactor = new double[]{0.99d, 0.4d};
            AnimData animData = new AnimData();
            this.mSpringAnimData = animData;
            animData.startValue = this.mStart;
            animData.targetValue = this.mFinal;
            animData.value = this.mCurrentPosition;
            animData.velocity = this.mCurrVelocity;
            double[] dArr = new double[3];
            this.mSpringParams = dArr;
            this.mSpringOperator.getParameters(this.mSpringFactor, dArr);
        }

        public boolean update() {
            float f2;
            float f3;
            double d2;
            double d3;
            long jCurrentAnimationTimeNanos = AnimationUtils.currentAnimationTimeNanos() - this.mStartTime;
            if (jCurrentAnimationTimeNanos == 0) {
                return this.mDuration > 0;
            }
            int i2 = this.mDuration;
            if (jCurrentAnimationTimeNanos > ((long) i2) * FolmeCore.NANOS_TO_MS) {
                return false;
            }
            int i3 = this.mState;
            if (i3 == 0) {
                int i4 = this.mSplineDuration;
                float f4 = jCurrentAnimationTimeNanos / (((long) i4) * FolmeCore.NANOS_TO_MS);
                int i5 = (int) (f4 * 100.0f);
                if (i5 < 100) {
                    float f5 = i5 / 100.0f;
                    int i6 = i5 + 1;
                    float[] fArr = SPLINE_POSITION;
                    float f6 = fArr[i5];
                    f3 = (fArr[i6] - f6) / ((i6 / 100.0f) - f5);
                    f2 = f6 + ((f4 - f5) * f3);
                } else {
                    f2 = 1.0f;
                    f3 = 0.0f;
                }
                d2 = f2 * this.mSplineDistance;
                this.mCurrVelocity = ((f3 * r0) / i4) * 1000.0f;
            } else {
                if (i3 != 1) {
                    if (i3 != 2) {
                        d3 = 0.0d;
                    } else {
                        float f7 = (float) (jCurrentAnimationTimeNanos / 1.0E9d);
                        double d4 = this.mVelocity;
                        float f8 = this.mDeceleration;
                        this.mCurrVelocity = ((double) (f8 * f7)) + d4;
                        d3 = (d4 * ((double) f7)) + ((double) (((f8 * f7) * f7) / 2.0f));
                    }
                    this.mCurrentPosition = this.mStart + ((double) ((int) Math.round(d3)));
                    return true;
                }
                float f9 = jCurrentAnimationTimeNanos / i2;
                float f10 = f9 * f9;
                float fSignum = Math.signum((float) this.mVelocity);
                d2 = this.mOver * fSignum * ((3.0f * f10) - ((2.0f * f9) * f10));
                this.mCurrVelocity = fSignum * r3 * 6.0f * ((-f9) + f10);
            }
            d3 = d2;
            this.mCurrentPosition = this.mStart + ((double) ((int) Math.round(d3)));
            return true;
        }

        public void updateScroll(float f2) {
            this.mCurrentPosition = this.mStart + Math.round(((double) f2) * (this.mFinal - r0));
        }
    }

    public static class ViscousFluidInterpolator implements Interpolator {
        private static final float VISCOUS_FLUID_NORMALIZE;
        private static final float VISCOUS_FLUID_OFFSET;
        private static final float VISCOUS_FLUID_SCALE = 8.0f;

        static {
            float fViscousFluid = 1.0f / viscousFluid(1.0f);
            VISCOUS_FLUID_NORMALIZE = fViscousFluid;
            VISCOUS_FLUID_OFFSET = 1.0f - (fViscousFluid * viscousFluid(1.0f));
        }

        private static float viscousFluid(float f2) {
            float f3 = f2 * VISCOUS_FLUID_SCALE;
            return f3 < 1.0f ? f3 - (1.0f - ((float) Math.exp(-f3))) : 0.36787945f + ((1.0f - ((float) Math.exp(1.0f - f3))) * 0.63212055f);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float fViscousFluid = VISCOUS_FLUID_NORMALIZE * viscousFluid(f2);
            return fViscousFluid > 0.0f ? fViscousFluid + VISCOUS_FLUID_OFFSET : fViscousFluid;
        }
    }

    public OverScroller(Context context) {
        this(context, null);
    }

    private void resetScrollerPosition(SplineOverScroller splineOverScroller) {
        splineOverScroller.setStart(0);
        splineOverScroller.setFinal(0);
        splineOverScroller.setCurrentPosition(0);
    }

    public void abortAnimation() {
        this.mScrollerX.finish();
        this.mScrollerY.finish();
    }

    public boolean computeScrollOffset() {
        if (isFinished()) {
            return false;
        }
        int i2 = this.mMode;
        if (i2 == 0) {
            long jCurrentAnimationTimeNanos = AnimationUtils.currentAnimationTimeNanos() - this.mScrollerX.mStartTime;
            int i3 = this.mScrollerX.mDuration;
            if (jCurrentAnimationTimeNanos < i3) {
                float interpolation = this.mInterpolator.getInterpolation(jCurrentAnimationTimeNanos / i3);
                this.mScrollerX.updateScroll(interpolation);
                this.mScrollerY.updateScroll(interpolation);
            } else {
                abortAnimation();
            }
        } else if (i2 == 1) {
            if (!this.mScrollerX.mFinished && !this.mScrollerX.update() && !this.mScrollerX.continueWhenFinished()) {
                this.mScrollerX.finish();
            }
            if (!this.mScrollerY.mFinished && !this.mScrollerY.update() && !this.mScrollerY.continueWhenFinished()) {
                this.mScrollerY.finish();
            }
        } else if (i2 == 2) {
            return this.mScrollerY.computeScrollOffset() || this.mScrollerX.computeScrollOffset();
        }
        return true;
    }

    @Deprecated
    public void extendDuration(int i2) {
        this.mScrollerX.extendDuration(i2);
        this.mScrollerY.extendDuration(i2);
    }

    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        fling(i2, i3, i4, i5, i6, i7, i8, i9, 0, 0);
    }

    public final void forceFinished(boolean z2) {
        this.mScrollerX.mFinished = this.mScrollerY.mFinished = z2;
    }

    public float getCurrVelocity() {
        return (float) Math.hypot(this.mScrollerX.mCurrVelocity, this.mScrollerY.mCurrVelocity);
    }

    public float getCurrVelocityX() {
        return (float) this.mScrollerX.mCurrVelocity;
    }

    public float getCurrVelocityY() {
        return (float) this.mScrollerY.mCurrVelocity;
    }

    public final int getCurrX() {
        return (int) this.mScrollerX.mCurrentPosition;
    }

    public final int getCurrY() {
        return (int) this.mScrollerY.mCurrentPosition;
    }

    @Deprecated
    public final int getDuration() {
        return Math.max(this.mScrollerX.mDuration, this.mScrollerY.mDuration);
    }

    public final int getFinalX() {
        return (int) this.mScrollerX.mFinal;
    }

    public final int getFinalY() {
        return (int) this.mScrollerY.mFinal;
    }

    public int getMode() {
        return this.mMode;
    }

    public final int getStartX() {
        return (int) this.mScrollerX.mStart;
    }

    public final int getStartY() {
        return (int) this.mScrollerY.mStart;
    }

    public final boolean isFinished() {
        return this.mScrollerX.mFinished && this.mScrollerY.mFinished;
    }

    public boolean isOverScrolled() {
        return ((this.mScrollerX.mFinished || this.mScrollerX.mState == 0) && (this.mScrollerY.mFinished || this.mScrollerY.mState == 0)) ? false : true;
    }

    public boolean isScrollingInDirection(float f2, float f3) {
        return !isFinished() && Math.signum(f2) == Math.signum((float) (((int) this.mScrollerX.mFinal) - ((int) this.mScrollerX.mStart))) && Math.signum(f3) == Math.signum((float) (((int) this.mScrollerY.mFinal) - ((int) this.mScrollerY.mStart)));
    }

    public void notifyHorizontalEdgeReached(int i2, int i3, int i4) {
        this.mScrollerX.notifyEdgeReached(i2, i3, i4);
    }

    public void notifyVerticalEdgeReached(int i2, int i3, int i4) {
        this.mScrollerY.notifyEdgeReached(i2, i3, i4);
    }

    public final void resetPosition() {
        resetScrollerPosition(this.mScrollerX);
        resetScrollerPosition(this.mScrollerY);
    }

    @Deprecated
    public void setFinalX(int i2) {
        this.mScrollerX.setFinalPosition(i2);
    }

    @Deprecated
    public void setFinalY(int i2) {
        this.mScrollerY.setFinalPosition(i2);
    }

    public final void setFriction(float f2) {
        this.mScrollerX.setFriction(f2);
        this.mScrollerY.setFriction(f2);
    }

    public void setInterpolator(Interpolator interpolator) {
        if (interpolator == null) {
            this.mInterpolator = new ViscousFluidInterpolator();
        } else {
            this.mInterpolator = interpolator;
        }
    }

    public boolean springBack(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mMode = 1;
        return this.mScrollerX.springback(i2, i4, i5) || this.mScrollerY.springback(i3, i6, i7);
    }

    public void startScroll(int i2, int i3, int i4, int i5) {
        startScroll(i2, i3, i4, i5, 250);
    }

    public void startScrollByFling(int i2, int i3, int i4, int i5) {
        startScrollByFling(i2, i3, i4, i5, 0, 0);
    }

    public int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeNanos() - Math.min(this.mScrollerX.mStartTime, this.mScrollerY.mStartTime));
    }

    public OverScroller(Context context, Interpolator interpolator) {
        this(context, interpolator, true);
    }

    public void fling(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        int i12;
        int i13;
        int i14;
        int i15;
        if (this.mFlywheel && !isFinished()) {
            float f2 = (float) this.mScrollerX.mCurrVelocity;
            float f3 = (float) this.mScrollerY.mCurrVelocity;
            i12 = i4;
            float f4 = i12;
            if (Math.signum(f4) == Math.signum(f2)) {
                i13 = i5;
                float f5 = i13;
                if (Math.signum(f5) == Math.signum(f3)) {
                    i14 = (int) (f5 + f3);
                    i15 = (int) (f4 + f2);
                }
                this.mMode = 1;
                this.mScrollerX.fling(i2, i15, i6, i7, i10);
                this.mScrollerY.fling(i3, i14, i8, i9, i11);
            }
            i14 = i13;
            i15 = i12;
            this.mMode = 1;
            this.mScrollerX.fling(i2, i15, i6, i7, i10);
            this.mScrollerY.fling(i3, i14, i8, i9, i11);
        }
        i12 = i4;
        i13 = i5;
        i14 = i13;
        i15 = i12;
        this.mMode = 1;
        this.mScrollerX.fling(i2, i15, i6, i7, i10);
        this.mScrollerY.fling(i3, i14, i8, i9, i11);
    }

    public void startScroll(int i2, int i3, int i4, int i5, int i6) {
        this.mMode = 0;
        this.mScrollerX.startScroll(i2, i4, i6);
        this.mScrollerY.startScroll(i3, i5, i6);
    }

    public void startScrollByFling(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mMode = 2;
        this.mScrollerX.startScrollByFling(i2, i4, i6);
        this.mScrollerY.startScrollByFling(i3, i5, i7);
    }

    public OverScroller(Context context, Interpolator interpolator, boolean z2) {
        if (interpolator == null) {
            this.mInterpolator = new ViscousFluidInterpolator();
        } else {
            this.mInterpolator = interpolator;
        }
        this.mFlywheel = z2;
        this.mScrollerX = new DynamicScroller(context);
        this.mScrollerY = new DynamicScroller(context);
    }

    @Deprecated
    public OverScroller(Context context, Interpolator interpolator, float f2, float f3) {
        this(context, interpolator, true);
    }

    @Deprecated
    public OverScroller(Context context, Interpolator interpolator, float f2, float f3, boolean z2) {
        this(context, interpolator, z2);
    }
}
