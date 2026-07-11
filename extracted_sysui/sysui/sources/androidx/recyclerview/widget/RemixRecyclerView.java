package androidx.recyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;
import miuix.animation.utils.VelocityMonitor;
import miuix.overscroller.widget.AnimationHelper;
import miuix.overscroller.widget.OverScroller;
import miuix.recyclerview.R;
import miuix.util.HapticFeedbackCompat;

/* JADX INFO: loaded from: classes.dex */
abstract class RemixRecyclerView extends RecyclerView {
    private static final int INVALID_POINTER = -1;
    private static final int MAX_POINTER_COUNT = 5;
    private final int mMaxFlingVelocity;
    private boolean mMouseEvent;
    private long mMouseEventTime;
    private int mScrollPointerId;
    private boolean mSpringEnabled;
    private final VelocityMonitor[] mVelocityMonitor;

    public class ViewFlinger extends RecyclerView.ViewFlinger {
        int mCurrentFlingVelocityX;
        int mCurrentFlingVelocityY;
        int mDragFlingVelocityX;
        int mDragFlingVelocityY;
        private boolean mEatRunOnAnimationRequest;
        private HapticFeedbackCompat mHapticFeedbackCompat;
        private boolean mHasReachEdgeBeforeFling;
        boolean mInterimTarget;
        Interpolator mInterpolator;
        private int mLastFlingX;
        private int mLastFlingY;
        OverScroller mOverScroller;
        private boolean mReSchedulePostAnimationCallback;

        public ViewFlinger() {
            super();
            Interpolator interpolator = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = interpolator;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mCurrentFlingVelocityX = 0;
            this.mCurrentFlingVelocityY = 0;
            this.mDragFlingVelocityX = 0;
            this.mDragFlingVelocityY = 0;
            this.mInterimTarget = false;
            this.mOverScroller = new OverScroller(RemixRecyclerView.this.getContext(), interpolator);
        }

        private int computeScrollDuration(int i2, int i3, int i4, int i5) {
            int iRound;
            int iAbs = Math.abs(i2);
            int iAbs2 = Math.abs(i3);
            boolean z2 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt((i4 * i4) + (i5 * i5));
            int iSqrt2 = (int) Math.sqrt((i2 * i2) + (i3 * i3));
            RemixRecyclerView remixRecyclerView = RemixRecyclerView.this;
            int width = z2 ? remixRecyclerView.getWidth() : remixRecyclerView.getHeight();
            int i6 = width / 2;
            float f2 = width;
            float f3 = i6;
            float fDistanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (iSqrt2 * 1.0f) / f2)) * f3);
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z2) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f2) + 1.0f) * 300.0f);
            }
            return Math.min(iRound, 2000);
        }

        private float distanceInfluenceForSnapDuration(float f2) {
            return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
        }

        private HapticFeedbackCompat getHapticFeedbackCompat() {
            if (this.mHapticFeedbackCompat == null) {
                this.mHapticFeedbackCompat = new HapticFeedbackCompat(RemixRecyclerView.this.getContext());
            }
            return this.mHapticFeedbackCompat;
        }

        private void internalPostOnAnimation() {
            RemixRecyclerView.this.removeCallbacks(this);
            AnimationHelper.postOnAnimation(RemixRecyclerView.this, this);
        }

        public void checkDoneScrolling() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v19 */
        /* JADX WARN: Type inference failed for: r13v20 */
        /* JADX WARN: Type inference failed for: r13v9 */
        @Override // androidx.recyclerview.widget.RecyclerView.ViewFlinger
        public void fling(int i2, int i3) {
            RemixRecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator interpolator = this.mInterpolator;
            Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
            if (interpolator != interpolator2) {
                this.mInterpolator = interpolator2;
                this.mOverScroller = new OverScroller(RemixRecyclerView.this.getContext(), interpolator2);
            }
            int i4 = i2 != 0 ? -((int) RemixRecyclerView.this.getVelocityFromMonitor(0)) : i2;
            int i5 = i3 != 0 ? -((int) RemixRecyclerView.this.getVelocityFromMonitor(1)) : i3;
            if (i4 != 0) {
                i2 = i4;
            }
            if (i5 != 0) {
                i3 = i5;
            }
            int iMax = Math.max(-RemixRecyclerView.this.mMaxFlingVelocity, Math.min(i2, RemixRecyclerView.this.mMaxFlingVelocity));
            int iMax2 = Math.max(-RemixRecyclerView.this.mMaxFlingVelocity, Math.min(i3, RemixRecyclerView.this.mMaxFlingVelocity));
            boolean zCanScrollHorizontally = RemixRecyclerView.this.mLayout.canScrollHorizontally();
            ?? r13 = zCanScrollHorizontally;
            if (RemixRecyclerView.this.mLayout.canScrollVertically()) {
                r13 = (zCanScrollHorizontally ? 1 : 0) | 2;
            }
            if (r13 == 2) {
                this.mHasReachEdgeBeforeFling = !RemixRecyclerView.this.canScrollVertically(iMax2 > 0 ? 1 : -1);
            } else if (r13 == 1) {
                this.mHasReachEdgeBeforeFling = !RemixRecyclerView.this.canScrollHorizontally(iMax > 0 ? 1 : -1);
            }
            this.mOverScroller.fling(0, 0, iMax, iMax2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ViewFlinger
        public void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
            } else {
                internalPostOnAnimation();
            }
        }

        public void resetFlingPosition() {
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            this.mOverScroller.resetPosition();
        }

        /* JADX WARN: Removed duplicated region for block: B:120:0x0222  */
        /* JADX WARN: Removed duplicated region for block: B:123:0x0234  */
        /* JADX WARN: Removed duplicated region for block: B:126:0x023f  */
        @Override // androidx.recyclerview.widget.RecyclerView.ViewFlinger, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 644
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RemixRecyclerView.ViewFlinger.run():void");
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
        @Override // androidx.recyclerview.widget.RecyclerView.ViewFlinger
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void smoothScrollBy(int r9, int r10, int r11, @androidx.annotation.Nullable android.view.animation.Interpolator r12) {
            /*
                r8 = this;
                androidx.recyclerview.widget.RemixRecyclerView r0 = androidx.recyclerview.widget.RemixRecyclerView.this
                boolean r0 = r0.isOverScrolling()
                if (r0 == 0) goto L9
                return
            L9:
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = 0
                if (r11 != r0) goto L11
                r8.computeScrollDuration(r9, r10, r1, r1)
            L11:
                if (r12 != 0) goto L15
                android.view.animation.Interpolator r12 = androidx.recyclerview.widget.RecyclerView.sQuinticInterpolator
            L15:
                miuix.overscroller.widget.OverScroller r11 = r8.mOverScroller
                int r11 = r11.getMode()
                r0 = 2
                if (r11 != r0) goto L34
                boolean r11 = r8.mInterimTarget
                if (r11 != 0) goto L34
                miuix.overscroller.widget.OverScroller r11 = r8.mOverScroller
                float r11 = r11.getCurrVelocityY()
                int r11 = (int) r11
                r8.mCurrentFlingVelocityY = r11
                miuix.overscroller.widget.OverScroller r11 = r8.mOverScroller
                float r11 = r11.getCurrVelocityX()
                int r11 = (int) r11
                r8.mCurrentFlingVelocityX = r11
            L34:
                androidx.recyclerview.widget.RemixRecyclerView r11 = androidx.recyclerview.widget.RemixRecyclerView.this
                androidx.recyclerview.widget.RecyclerView$LayoutManager r11 = r11.mLayout
                androidx.recyclerview.widget.RecyclerView$SmoothScroller r11 = r11.mSmoothScroller
                boolean r2 = r11 instanceof androidx.recyclerview.widget.LinearSmoothScroller
                if (r2 == 0) goto L5a
                r2 = r11
                androidx.recyclerview.widget.LinearSmoothScroller r2 = (androidx.recyclerview.widget.LinearSmoothScroller) r2
                int r2 = r2.mInterimTargetDx
                float r2 = (float) r2
                r3 = 1067030938(0x3f99999a, float:1.2)
                float r2 = r2 * r3
                androidx.recyclerview.widget.LinearSmoothScroller r11 = (androidx.recyclerview.widget.LinearSmoothScroller) r11
                int r11 = r11.mInterimTargetDy
                float r11 = (float) r11
                float r11 = r11 * r3
                float r3 = (float) r9
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 != 0) goto L5a
                float r2 = (float) r10
                int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
                if (r11 != 0) goto L5a
                r11 = 1
                goto L5b
            L5a:
                r11 = r1
            L5b:
                r8.mInterimTarget = r11
                android.view.animation.Interpolator r11 = r8.mInterpolator
                if (r11 == r12) goto L70
                r8.mInterpolator = r12
                miuix.overscroller.widget.OverScroller r11 = new miuix.overscroller.widget.OverScroller
                androidx.recyclerview.widget.RemixRecyclerView r2 = androidx.recyclerview.widget.RemixRecyclerView.this
                android.content.Context r2 = r2.getContext()
                r11.<init>(r2, r12)
                r8.mOverScroller = r11
            L70:
                r8.mLastFlingY = r1
                r8.mLastFlingX = r1
                androidx.recyclerview.widget.RemixRecyclerView r11 = androidx.recyclerview.widget.RemixRecyclerView.this
                r11.setScrollState(r0)
                miuix.overscroller.widget.OverScroller r1 = r8.mOverScroller
                int r6 = r8.mCurrentFlingVelocityX
                int r7 = r8.mCurrentFlingVelocityY
                r2 = 0
                r3 = 0
                r4 = r9
                r5 = r10
                r1.startScrollByFling(r2, r3, r4, r5, r6, r7)
                r8.postOnAnimation()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RemixRecyclerView.ViewFlinger.smoothScrollBy(int, int, int, android.view.animation.Interpolator):void");
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ViewFlinger
        public void stop() {
            RemixRecyclerView.this.removeCallbacks(this);
            this.mOverScroller.abortAnimation();
        }
    }

    public RemixRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    private void checkVelocityMonitor(int i2) {
        VelocityMonitor[] velocityMonitorArr = this.mVelocityMonitor;
        if (velocityMonitorArr[i2] == null) {
            velocityMonitorArr[i2] = new VelocityMonitor();
        }
    }

    private void resetVelocity(MotionEvent motionEvent, int i2) {
        int pointerId = motionEvent.getPointerId(i2) % 5;
        checkVelocityMonitor(pointerId);
        this.mVelocityMonitor[pointerId].clear();
    }

    private void trackVelocity(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            this.mScrollPointerId = motionEvent.getPointerId(0);
            resetVelocity(motionEvent, actionIndex);
            updateVelocity(motionEvent, actionIndex);
        } else if (actionMasked == 2) {
            for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
                updateVelocity(motionEvent, i2);
            }
        } else {
            if (actionMasked != 5) {
                return;
            }
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            resetVelocity(motionEvent, actionIndex);
            updateVelocity(motionEvent, actionIndex);
        }
    }

    private void updateVelocity(MotionEvent motionEvent, int i2) {
        int pointerId = motionEvent.getPointerId(i2) % 5;
        checkVelocityMonitor(pointerId);
        this.mVelocityMonitor[pointerId].update(motionEvent.getRawX(i2), motionEvent.getRawY(i2));
    }

    public boolean getSpringEnabled() {
        boolean z2 = System.currentTimeMillis() - this.mMouseEventTime > 10;
        if (this.mSpringEnabled) {
            return !this.mMouseEvent || z2;
        }
        return false;
    }

    public float getVelocityFromMonitor(int i2) {
        int i3 = this.mScrollPointerId;
        if (i3 == -1) {
            return 0.0f;
        }
        int i4 = i3 % 5;
        checkVelocityMonitor(i4);
        return this.mVelocityMonitor[i4].getVelocity(i2);
    }

    public boolean isOverScrolling() {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        boolean zIsFromSource = MotionEventCompat.isFromSource(motionEvent, 8194);
        this.mMouseEvent = zIsFromSource;
        if (zIsFromSource) {
            this.mMouseEventTime = System.currentTimeMillis();
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        trackVelocity(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zIsFromSource = MotionEventCompat.isFromSource(motionEvent, 8194);
        this.mMouseEvent = zIsFromSource;
        if (zIsFromSource) {
            this.mMouseEventTime = System.currentTimeMillis();
        }
        trackVelocity(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setOverScrollMode(int i2) {
        super.setOverScrollMode(i2);
        if (i2 == 2) {
            this.mSpringEnabled = false;
        }
    }

    public void setSpringEnabled(boolean z2) {
        this.mSpringEnabled = z2;
    }

    public RemixRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.recyclerViewStyle);
    }

    public RemixRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mVelocityMonitor = new VelocityMonitor[5];
        this.mScrollPointerId = -1;
        this.mSpringEnabled = true;
        this.mMouseEvent = false;
        this.mMouseEventTime = 0L;
        this.mMaxFlingVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
