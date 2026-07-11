package miuix.overscroller.widget;

import android.content.Context;
import miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation;
import miuix.overscroller.internal.dynamicanimation.animation.FlingAnimation;
import miuix.overscroller.internal.dynamicanimation.animation.FloatValueHolder;
import miuix.overscroller.internal.dynamicanimation.animation.SpringAnimation;
import miuix.overscroller.internal.dynamicanimation.animation.SpringForce;
import miuix.overscroller.widget.OverScroller;
import miuix.view.animation.AnimationUtils;

/* JADX INFO: loaded from: classes.dex */
class DynamicScroller extends OverScroller.SplineOverScroller implements FlingAnimation.FinalValueListener {
    private static final float MAX_SPRING_INITIAL_VELOCITY = 8000.0f;
    private static final float MINIMAL_VISIBLE_CHANGE = 0.5f;
    private FlingAnimation mFlingAnimation;
    private OverScrollHandler mHandler;
    private SpringAnimation mSpringAnimation;
    private FloatValueHolder mValue;

    public static class OverScrollHandler {
        private float mAnimMaxValue;
        private float mAnimMinValue;
        DynamicAnimation<?> mAnimation;
        private long mLastUpdateTime;
        private final int mMaxLegalValue;
        private final int mMinLegalValue;
        private Monitor mMonitor = new Monitor();
        private OnFinishedListener mOnFinishedListener;
        int mStartValue;
        int mValue;
        float mVelocity;

        public class Monitor implements DynamicAnimation.OnAnimationUpdateListener {
            private Monitor() {
            }

            @Override // miuix.overscroller.internal.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                OverScrollHandler overScrollHandler = OverScrollHandler.this;
                overScrollHandler.mVelocity = f3;
                overScrollHandler.mValue = overScrollHandler.mStartValue + ((int) f2);
                OverScrollLogger.verbose("%s updating value(%f), velocity(%f), min(%f), max(%f)", dynamicAnimation.getClass().getSimpleName(), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(OverScrollHandler.this.mAnimMinValue), Float.valueOf(OverScrollHandler.this.mAnimMaxValue));
            }
        }

        public interface OnFinishedListener {
            boolean whenFinished(float f2, float f3);
        }

        public OverScrollHandler(DynamicAnimation<?> dynamicAnimation, int i2, float f2) {
            this.mAnimation = dynamicAnimation;
            dynamicAnimation.setMinValue(-3.4028235E38f);
            this.mAnimation.setMaxValue(Float.MAX_VALUE);
            this.mStartValue = i2;
            this.mVelocity = f2;
            int i3 = Integer.MAX_VALUE;
            int i4 = Integer.MIN_VALUE;
            if (i2 > 0) {
                i4 = Integer.MIN_VALUE + i2;
            } else if (i2 < 0) {
                i3 = Integer.MAX_VALUE + i2;
            }
            this.mMinLegalValue = i4;
            this.mMaxLegalValue = i3;
            this.mAnimation.setStartValue(0.0f);
            this.mAnimation.setStartVelocity(f2);
        }

        public void cancel() {
            this.mLastUpdateTime = 0L;
            this.mAnimation.cancel();
            this.mAnimation.removeUpdateListener(this.mMonitor);
        }

        public boolean continueWhenFinished() {
            OnFinishedListener onFinishedListener = this.mOnFinishedListener;
            if (onFinishedListener != null) {
                return onFinishedListener.whenFinished(this.mValue, this.mVelocity);
            }
            return false;
        }

        public DynamicAnimation<?> getAnimation() {
            return this.mAnimation;
        }

        public int getOffset(int i2) {
            return i2 - this.mStartValue;
        }

        public void setMaxValue(int i2) {
            int i3 = this.mMaxLegalValue;
            if (i2 > i3) {
                i2 = i3;
            }
            float fMax = Math.max(i2 - this.mStartValue, 0);
            this.mAnimation.setMaxValue(fMax);
            this.mAnimMaxValue = fMax;
        }

        public void setMinValue(int i2) {
            int i3 = this.mMinLegalValue;
            if (i2 < i3) {
                i2 = i3;
            }
            float fMin = Math.min(i2 - this.mStartValue, 0);
            this.mAnimation.setMinValue(fMin);
            this.mAnimMinValue = fMin;
        }

        public void setOnFinishedListener(OnFinishedListener onFinishedListener) {
            this.mOnFinishedListener = onFinishedListener;
        }

        public void start() {
            this.mAnimation.addUpdateListener(this.mMonitor);
            this.mAnimation.start(true);
            this.mLastUpdateTime = 0L;
        }

        public boolean update() {
            long j2 = this.mLastUpdateTime;
            long jCurrentAnimationTimeNanos = AnimationUtils.currentAnimationTimeNanos();
            if (jCurrentAnimationTimeNanos == j2) {
                OverScrollLogger.verbose("update done in this frame, dropping current update request");
                return !this.mAnimation.isRunning();
            }
            boolean zDoAnimationFrame = this.mAnimation.doAnimationFrame(jCurrentAnimationTimeNanos);
            if (zDoAnimationFrame) {
                OverScrollLogger.verbose("%s finishing value(%d) velocity(%f)", this.mAnimation.getClass().getSimpleName(), Integer.valueOf(this.mValue), Float.valueOf(this.mVelocity));
                this.mAnimation.removeUpdateListener(this.mMonitor);
            }
            this.mLastUpdateTime = jCurrentAnimationTimeNanos;
            return zDoAnimationFrame;
        }
    }

    public DynamicScroller(Context context) {
        super(context);
        this.mValue = new FloatValueHolder();
        SpringAnimation springAnimation = new SpringAnimation(this.mValue);
        this.mSpringAnimation = springAnimation;
        springAnimation.setSpring(new SpringForce());
        this.mSpringAnimation.setMinimumVisibleChange(0.5f);
        this.mSpringAnimation.getSpring().setDampingRatio(0.97f);
        this.mSpringAnimation.getSpring().setStiffness(130.5f);
        this.mSpringAnimation.getSpring().setTimeRatio(1000.0d);
        FlingAnimation flingAnimation = new FlingAnimation(this.mValue, this);
        this.mFlingAnimation = flingAnimation;
        flingAnimation.setMinimumVisibleChange(0.5f);
        this.mFlingAnimation.setFriction(0.4761905f);
    }

    private void doFling(int i2, int i3, final int i4, final int i5, final int i6) {
        int i7;
        int iPredictDuration;
        this.mFlingAnimation.setStartValue(0.0f);
        float f2 = i3;
        this.mFlingAnimation.setStartVelocity(f2);
        long jPredictNaturalDest = ((long) i2) + ((long) this.mFlingAnimation.predictNaturalDest());
        if (jPredictNaturalDest > i5) {
            iPredictDuration = (int) this.mFlingAnimation.predictTimeTo(i5 - i2);
            i7 = i5;
        } else if (jPredictNaturalDest < i4) {
            iPredictDuration = (int) this.mFlingAnimation.predictTimeTo(i4 - i2);
            i7 = i4;
        } else {
            i7 = (int) jPredictNaturalDest;
            iPredictDuration = (int) this.mFlingAnimation.predictDuration();
        }
        setFinished(false);
        setCurrVelocity(f2);
        setStartTime(AnimationUtils.currentAnimationTimeNanos());
        setCurrentPosition(i2);
        setStart(i2);
        setDuration(iPredictDuration);
        setFinal(i7);
        setState(0);
        int iMin = Math.min(i4, i2);
        int iMax = Math.max(i5, i2);
        OverScrollHandler overScrollHandler = new OverScrollHandler(this.mFlingAnimation, i2, f2);
        this.mHandler = overScrollHandler;
        overScrollHandler.setOnFinishedListener(new OverScrollHandler.OnFinishedListener() { // from class: miuix.overscroller.widget.DynamicScroller.1
            @Override // miuix.overscroller.widget.DynamicScroller.OverScrollHandler.OnFinishedListener
            public boolean whenFinished(float f3, float f4) {
                OverScrollLogger.debug("fling finished: value(%f), velocity(%f), scroller boundary(%d, %d)", Float.valueOf(f3), Float.valueOf(f4), Integer.valueOf(i4), Integer.valueOf(i5));
                DynamicScroller.this.mFlingAnimation.setStartValue(DynamicScroller.this.mHandler.mValue);
                DynamicScroller.this.mFlingAnimation.setStartVelocity(DynamicScroller.this.mHandler.mVelocity);
                float fPredictNaturalDest = DynamicScroller.this.mFlingAnimation.predictNaturalDest();
                if (((int) f3) == 0 || (fPredictNaturalDest <= i5 && fPredictNaturalDest >= i4)) {
                    OverScrollLogger.debug("fling finished, no more work.");
                    return false;
                }
                OverScrollLogger.debug("fling destination beyound boundary, start spring");
                DynamicScroller.this.resetHandler();
                DynamicScroller dynamicScroller = DynamicScroller.this;
                dynamicScroller.doSpring(2, dynamicScroller.getCurrentPosition(), DynamicScroller.this.getCurrVelocity(), DynamicScroller.this.getFinal(), i6);
                return true;
            }
        });
        this.mHandler.setMinValue(iMin);
        this.mHandler.setMaxValue(iMax);
        this.mHandler.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSpring(int i2, int i3, float f2, int i4, int i5) {
        if (f2 > MAX_SPRING_INITIAL_VELOCITY) {
            OverScrollLogger.debug("%f is too fast for spring, slow down", Float.valueOf(f2));
            f2 = 8000.0f;
        }
        setFinished(false);
        setCurrVelocity(f2);
        setStartTime(AnimationUtils.currentAnimationTimeNanos());
        setCurrentPosition(i3);
        setStart(i3);
        setDuration(Integer.MAX_VALUE);
        setFinal(i4);
        setState(i2);
        this.mHandler = new OverScrollHandler(this.mSpringAnimation, i3, f2);
        this.mSpringAnimation.getSpring().setFinalPosition(this.mHandler.getOffset(i4));
        if (i5 != 0) {
            if (f2 < 0.0f) {
                this.mHandler.setMinValue(i4 - i5);
                this.mHandler.setMaxValue(Math.max(i4, i3));
            } else {
                this.mHandler.setMinValue(Math.min(i4, i3));
                this.mHandler.setMaxValue(i4 + i5);
            }
        }
        this.mHandler.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetHandler() {
        if (this.mHandler != null) {
            OverScrollLogger.debug("resetting current handler: state(%d), anim(%s), value(%d), velocity(%f)", Integer.valueOf(getState()), this.mHandler.getAnimation().getClass().getSimpleName(), Integer.valueOf(this.mHandler.mValue), Float.valueOf(this.mHandler.mVelocity));
            this.mHandler.cancel();
            this.mHandler = null;
        }
    }

    private void startAfterEdge(int i2, int i3, int i4, int i5, int i6) {
        OverScrollLogger.debug("startAfterEdge: start(%d) velocity(%d) boundary(%d, %d) over(%d)", Integer.valueOf(i2), Integer.valueOf(i5), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i6));
        if (i2 > i3 && i2 < i4) {
            setFinished(true);
            return;
        }
        boolean z2 = i2 > i4;
        int i7 = z2 ? i4 : i3;
        int i8 = i2 - i7;
        if (i5 != 0 && Integer.signum(i8) * i5 >= 0) {
            OverScrollLogger.debug("spring forward");
            doSpring(2, i2, i5, i7, i6);
            return;
        }
        this.mFlingAnimation.setStartValue(i2);
        float f2 = i5;
        this.mFlingAnimation.setStartVelocity(f2);
        float fPredictNaturalDest = this.mFlingAnimation.predictNaturalDest();
        if ((!z2 || fPredictNaturalDest >= i4) && (z2 || fPredictNaturalDest <= i3)) {
            OverScrollLogger.debug("spring backward");
            doSpring(1, i2, f2, i7, i6);
        } else {
            OverScrollLogger.debug("fling to content");
            doFling(i2, i5, i3, i4, i6);
        }
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public boolean continueWhenFinished() {
        OverScrollHandler overScrollHandler = this.mHandler;
        if (overScrollHandler == null || !overScrollHandler.continueWhenFinished()) {
            return false;
        }
        OverScrollLogger.debug("checking have more work when finish");
        update();
        return true;
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void extendDuration(int i2) {
        super.extendDuration(i2);
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void finish() {
        OverScrollLogger.debug("finish scroller");
        setCurrentPosition(getFinal());
        setFinished(true);
        resetHandler();
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void fling(int i2, int i3, int i4, int i5, int i6) {
        OverScrollLogger.debug("FLING: start(%d) velocity(%d) boundary(%d, %d) over(%d)", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
        resetHandler();
        if (i3 == 0) {
            setCurrentPosition(i2);
            setStart(i2);
            setFinal(i2);
            setDuration(0);
            setFinished(true);
            return;
        }
        updateStiffness(i3);
        if (i2 > i5 || i2 < i4) {
            startAfterEdge(i2, i4, i5, i3, i6);
        } else {
            doFling(i2, i3, i4, i5, i6);
        }
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void notifyEdgeReached(int i2, int i3, int i4) {
        if (getState() == 0) {
            if (this.mHandler != null) {
                resetHandler();
            }
            startAfterEdge(i2, i3, i3, (int) getCurrVelocity(), i4);
        }
    }

    @Override // miuix.overscroller.internal.dynamicanimation.animation.FlingAnimation.FinalValueListener
    public void onFinalValueArrived(int i2) {
        setFinalPosition(getStart() + i2);
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void setFinalPosition(int i2) {
        super.setFinalPosition(i2);
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public void setFriction(float f2) {
        this.mFlingAnimation.setFriction(f2);
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public boolean springback(int i2, int i3, int i4) {
        OverScrollLogger.debug("SPRING_BACK start(%d) boundary(%d, %d)", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        if (this.mHandler != null) {
            resetHandler();
        }
        if (i2 < i3) {
            doSpring(1, i2, 0.0f, i3, 0);
        } else if (i2 > i4) {
            doSpring(1, i2, 0.0f, i4, 0);
        } else {
            setCurrentPosition(i2);
            setStart(i2);
            setFinal(i2);
            setDuration(0);
            setFinished(true);
        }
        return !isFinished();
    }

    @Override // miuix.overscroller.widget.OverScroller.SplineOverScroller
    public boolean update() {
        OverScrollHandler overScrollHandler = this.mHandler;
        if (overScrollHandler == null) {
            OverScrollLogger.debug("no handler found, aborting");
            return false;
        }
        boolean zUpdate = overScrollHandler.update();
        setCurrentPosition(this.mHandler.mValue);
        setCurrVelocity(this.mHandler.mVelocity);
        if (getState() == 2 && Math.signum(this.mHandler.mValue) * Math.signum(this.mHandler.mVelocity) < 0.0f) {
            OverScrollLogger.debug("State Changed: BALLISTIC -> CUBIC");
            setState(1);
        }
        return !zUpdate;
    }

    public void updateStiffness(double d2) {
        if (Math.abs(d2) <= 5000.0d) {
            this.mSpringAnimation.getSpring().setStiffness(246.7f);
        } else {
            this.mSpringAnimation.getSpring().setStiffness(130.5f);
        }
    }
}
