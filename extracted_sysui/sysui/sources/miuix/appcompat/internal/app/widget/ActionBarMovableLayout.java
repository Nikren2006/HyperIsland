package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.MotionEventCompat;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.internal.util.DeviceHelper;
import miuix.overscroller.widget.AnimationHelper;
import miuix.overscroller.widget.OverScroller;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarMovableLayout extends ActionBarOverlayLayout {
    private static final boolean DBG = false;
    public static final int DEFAULT_SPRING_BACK_DURATION = 800;
    public static final int STATE_DOWN = 1;
    public static final int STATE_UNKNOWN = -1;
    public static final int STATE_UP = 0;
    private static final String TAG = "ActionBarMovableLayout";
    private int mActivePointerId;
    private boolean mFlinging;
    private int mInitialMotionY;
    private boolean mInitialMotionYSet;
    private boolean mIsBeingDragged;
    private boolean mIsSpringBackEnabled;
    private float mLastMotionX;
    private float mLastMotionY;
    private final int mMaximumVelocity;
    private final int mMinimumVelocity;
    private int mMotionY;
    private ActionBar.OnScrollListener mOnScrollListener;
    private int mOverScrollDistance;
    private int mScrollRange;
    private int mScrollStart;
    private OverScroller mScroller;
    private int mState;
    private View mTabScrollView;
    private int mTabViewVisibility;
    private final int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    public ActionBarMovableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mState = -1;
        this.mScrollRange = -1;
        this.mInitialMotionY = -1;
        this.mTabViewVisibility = 8;
        this.mIsSpringBackEnabled = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBarMovableLayout, R.attr.actionBarMovableLayoutStyle, 0);
        if (DeviceHelper.isFeatureWholeAnim()) {
            this.mOverScrollDistance = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBarMovableLayout_overScrollRange, 0);
        }
        this.mScrollRange = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBarMovableLayout_scrollRange, -1);
        this.mInitialMotionY = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ActionBarMovableLayout_scrollStart, -1);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScroller = new OverScroller(context);
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        setOverScrollMode(0);
        typedArrayObtainStyledAttributes.recycle();
    }

    private boolean inChild(View view, int i2, int i3) {
        if (view == null) {
            return false;
        }
        int y2 = (int) view.getY();
        int x2 = (int) view.getX();
        int y3 = (int) (view.getY() + view.getHeight());
        int x3 = (int) (view.getX() + view.getWidth());
        if (view == this.mTabScrollView) {
            int top = this.mActionBarTop.getTop();
            y2 += top;
            y3 += top;
        }
        return i3 >= y2 && i3 < y3 && i2 >= x2 && i2 < x3;
    }

    private void initOrResetVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private boolean isTabViewVisibilityChanged() {
        int visibility;
        ensureTabScrollView();
        View view = this.mTabScrollView;
        if (view == null || (visibility = view.getVisibility()) == this.mTabViewVisibility) {
            return false;
        }
        this.mTabViewVisibility = visibility;
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            int i2 = action == 0 ? 1 : 0;
            this.mLastMotionY = (int) motionEvent.getY(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void recycleVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void applyTranslationY(float f2) {
        float fMotionToTranslation = motionToTranslation(f2);
        this.mContentView.setTranslationY(fMotionToTranslation);
        ensureTabScrollView();
        View view = this.mTabScrollView;
        if (view != null) {
            view.setTranslationY(fMotionToTranslation);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (!this.mScroller.computeScrollOffset()) {
            if (this.mFlinging) {
                springBack();
                this.mFlinging = false;
                return;
            }
            return;
        }
        int i2 = this.mMotionY;
        int currY = this.mScroller.getCurrY();
        if (i2 != currY) {
            overScrollBy(0, currY - i2, 0, this.mMotionY, 0, getScrollRange(), 0, getOverScrollDistance(), true);
        }
        AnimationHelper.postInvalidateOnAnimation(this);
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        return getScrollRange();
    }

    public int computeVerticalVelocity() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
        return (int) velocityTracker.getYVelocity(this.mActivePointerId);
    }

    public void ensureTabScrollView() {
        this.mTabScrollView = this.mActionBarTop.getTabContainer();
    }

    public void fling(int i2) {
        int overScrollDistance = getOverScrollDistance();
        this.mScroller.fling(0, this.mMotionY, 0, i2, 0, 0, 0, getScrollRange(), 0, overScrollDistance);
        this.mFlinging = true;
        postInvalidate();
    }

    public int getOverScrollDistance() {
        if (DeviceHelper.isFeatureWholeAnim()) {
            return this.mOverScrollDistance;
        }
        return 0;
    }

    public int getScrollRange() {
        return this.mScrollRange;
    }

    public int getScrollStart() {
        return this.mScrollStart;
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        if (view != this.mContentView) {
            super.measureChildWithMargins(view, i2, i3, i4, i5);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), FrameLayout.getChildMeasureSpec(i4, ((((((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.bottomMargin) + this.mActionBarView.getMeasuredHeight()) + ((ViewGroup.MarginLayoutParams) this.mActionBarView.getLayoutParams()).topMargin) - getScrollRange()) - getOverScrollDistance()) - this.mScrollStart, marginLayoutParams.height));
    }

    public float motionToTranslation(float f2) {
        float f3 = (((-this.mOverScrollDistance) + f2) - this.mScrollRange) - this.mScrollStart;
        ensureTabScrollView();
        View view = this.mTabScrollView;
        return (view == null || view.getVisibility() != 0) ? f3 : f3 - this.mTabScrollView.getHeight();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0042  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.getContentMask()
            r1 = 0
            if (r0 == 0) goto Le
            int r0 = r0.getVisibility()
            if (r0 != 0) goto Le
            return r1
        Le:
            int r0 = r6.getAction()
            r2 = 2
            r3 = 1
            if (r0 != r2) goto L1b
            boolean r4 = r5.mIsBeingDragged
            if (r4 == 0) goto L1b
            return r3
        L1b:
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 == 0) goto L4e
            if (r0 == r3) goto L42
            if (r0 == r2) goto L2e
            r2 = 3
            if (r0 == r2) goto L42
            r1 = 6
            if (r0 == r1) goto L2a
            goto L6d
        L2a:
            r5.onSecondaryPointerUp(r6)
            goto L6d
        L2e:
            boolean r0 = r5.shouldStartScroll(r6)
            if (r0 == 0) goto L6d
            r5.mIsBeingDragged = r3
            r5.initVelocityTrackerIfNotExists()
            android.view.VelocityTracker r0 = r5.mVelocityTracker
            r0.addMovement(r6)
            r5.onScrollBegin()
            goto L6d
        L42:
            r5.mIsBeingDragged = r1
            r6 = -1
            r5.mActivePointerId = r6
            r5.recycleVelocityTracker()
            r5.onScrollEnd()
            goto L6d
        L4e:
            float r0 = r6.getY()
            r5.mLastMotionY = r0
            float r0 = r6.getX()
            r5.mLastMotionX = r0
            int r0 = r6.getPointerId(r1)
            r5.mActivePointerId = r0
            r5.initOrResetVelocityTracker()
            android.view.VelocityTracker r0 = r5.mVelocityTracker
            r0.addMovement(r6)
            miuix.overscroller.widget.OverScroller r6 = r5.mScroller
            r6.forceFinished(r3)
        L6d:
            boolean r5 = r5.mIsBeingDragged
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarMovableLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // miuix.appcompat.internal.app.widget.ActionBarOverlayLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        boolean z3 = !this.mInitialMotionYSet || isTabViewVisibilityChanged();
        if (!this.mInitialMotionYSet) {
            if (this.mInitialMotionY < 0) {
                this.mInitialMotionY = this.mScrollRange;
            }
            this.mMotionY = this.mInitialMotionY;
            this.mInitialMotionYSet = true;
        }
        if (z3) {
            applyTranslationY(this.mMotionY);
        }
    }

    @Override // android.view.View
    public void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        ActionBar.OnScrollListener onScrollListener;
        onScroll(i3);
        this.mMotionY = i3;
        if (i3 == 0 && z3) {
            if (Math.abs(computeVerticalVelocity()) <= this.mMinimumVelocity * 2 || (onScrollListener = this.mOnScrollListener) == null) {
                return;
            }
            onScrollListener.onFling((-r1) * 0.2f, 500);
        }
    }

    public void onScroll(float f2) {
        applyTranslationY(f2);
        ActionBar.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(this.mState, f2 / this.mScrollRange);
        }
    }

    public void onScrollBegin() {
        ActionBar.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onStartScroll();
        }
    }

    public void onScrollEnd() {
        this.mState = -1;
        ActionBar.OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onStopScroll();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a5  */
    @Override // miuix.appcompat.internal.app.widget.ActionBarOverlayLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarMovableLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2) {
        int overScrollMode = getOverScrollMode();
        boolean z3 = true;
        int i10 = i5 + i3;
        if (!(overScrollMode == 0 || (overScrollMode == 1 && (computeVerticalScrollRange() > computeVerticalScrollExtent())))) {
            i9 = 0;
        }
        int i11 = i9 + i7;
        if (i10 > i11) {
            i10 = i11;
        } else if (i10 < 0) {
            i10 = 0;
        } else {
            z3 = false;
        }
        onOverScrolled(0, i10, false, z3);
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
    }

    public void setInitialMotionY(int i2) {
        this.mInitialMotionY = i2;
    }

    public void setMotionY(int i2) {
        this.mMotionY = i2;
        onScroll(i2);
    }

    public void setOnScrollListener(ActionBar.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    public void setOverScrollDistance(int i2) {
        if (DeviceHelper.isFeatureWholeAnim()) {
            this.mOverScrollDistance = i2;
        }
    }

    public void setScrollRange(int i2) {
        this.mScrollRange = i2;
    }

    public void setScrollStart(int i2) {
        this.mScrollStart = i2;
    }

    public void setSpringBackEnabled(boolean z2) {
        this.mIsSpringBackEnabled = z2;
    }

    public boolean shouldStartScroll(MotionEvent motionEvent) {
        int i2;
        ActionBar.OnScrollListener onScrollListener;
        ActionBar.OnScrollListener onScrollListener2;
        int i3 = this.mActivePointerId;
        if (i3 == -1) {
            return false;
        }
        int iFindPointerIndex = motionEvent.findPointerIndex(i3);
        if (iFindPointerIndex == -1) {
            Log.w(TAG, "invalid pointer index");
            return false;
        }
        float x2 = motionEvent.getX(iFindPointerIndex);
        float y2 = motionEvent.getY(iFindPointerIndex);
        int i4 = (int) (y2 - this.mLastMotionY);
        int iAbs = Math.abs(i4);
        int i5 = (int) x2;
        int i6 = (int) y2;
        boolean z2 = (inChild(this.mContentView, i5, i6) || inChild(this.mTabScrollView, i5, i6)) && iAbs > this.mTouchSlop && iAbs > ((int) Math.abs(x2 - this.mLastMotionX)) && ((i2 = this.mMotionY) != 0 ? i4 <= 0 || i2 < getOverScrollDistance() || (onScrollListener = this.mOnScrollListener) == null || !onScrollListener.onContentScrolled() : i4 >= 0 && ((onScrollListener2 = this.mOnScrollListener) == null || !onScrollListener2.onContentScrolled()));
        if (z2) {
            this.mLastMotionY = y2;
            this.mLastMotionX = x2;
            this.mState = i4 > 0 ? 1 : 0;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
        return z2;
    }

    public void springBack() {
        if (this.mIsSpringBackEnabled) {
            int scrollRange = getScrollRange();
            int i2 = this.mMotionY;
            this.mScroller.startScroll(0, i2, 0, i2 > scrollRange / 2 ? scrollRange - i2 : -i2, 800);
            AnimationHelper.postInvalidateOnAnimation(this);
        }
    }
}
