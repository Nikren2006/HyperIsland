package miuix.springback.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.widget.ListViewCompat;
import androidx.core.widget.NestedScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miuix.core.util.EnvStateManager;
import miuix.core.view.NestedCurrentFling;
import miuix.core.view.ScrollStateDispatcher;
import miuix.core.view.ViewCompatOnScrollChangeListener;
import miuix.os.Build;
import miuix.overscroller.widget.AnimationHelper;
import miuix.springback.R;

/* JADX INFO: loaded from: classes5.dex */
public class SpringBackLayout extends ViewGroup implements NestedScrollingParent3, NestedScrollingChild3, NestedCurrentFling, ScrollStateDispatcher {
    public static final int ANGLE = 4;
    public static final int HORIZONTAL = 1;
    private static final int INVALID_ID = -1;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_FLING_CONSUME_COUNTER = 4;
    public static final int SPRING_BACK_BOTTOM = 2;
    public static final int SPRING_BACK_TOP = 1;
    private static final String TAG = "SpringBackLayout";
    public static final int UNCHECK_ORIENTATION = 0;
    private static final int VELOCITY_THRESHOLD = 2000;
    public static final int VERTICAL = 2;
    private int consumeNestFlingCounter;
    private int mActivePointerId;
    private int mFakeScrollX;
    private int mFakeScrollY;
    private SpringBackLayoutHelper mHelper;
    private boolean mInGlobalRomMode;
    private int mInitPaddingTop;
    private float mInitialDownX;
    private float mInitialDownY;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private boolean mNestedFlingInProgress;
    private int mNestedScrollAxes;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final int[] mNestedScrollingV2ConsumedCompat;
    private List<ViewCompatOnScrollChangeListener> mOnScrollChangeListeners;
    private OnSpringListener mOnSpringListener;
    private int mOriginScrollOrientation;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    protected int mScreenHeight;
    protected int mScreenWidth;
    private boolean mScrollByFling;
    private int mScrollOrientation;
    private int mScrollState;
    private boolean mSpringBackEnable;
    private int mSpringBackMode;
    private SpringScroller mSpringScroller;
    private View mTarget;
    private int mTargetId;
    private float mTotalFlingUnconsumed;
    private float mTotalScrollBottomUnconsumed;
    private float mTotalScrollTopUnconsumed;
    private int mTouchSlop;
    private float mVelocityX;
    private float mVelocityY;

    public interface OnSpringListener {
        boolean onSpringBack();
    }

    public SpringBackLayout(Context context) {
        this(context, null);
    }

    private void checkHorizontalScrollStart(int i2) {
        if (getScrollX() == 0) {
            this.mIsBeingDragged = false;
            return;
        }
        this.mIsBeingDragged = true;
        float fObtainTouchDistance = obtainTouchDistance(Math.abs(getScrollX()), Math.abs(obtainMaxSpringBackDistance(i2)), 2);
        if (getScrollX() < 0) {
            this.mInitialDownX -= fObtainTouchDistance;
        } else {
            this.mInitialDownX += fObtainTouchDistance;
        }
        this.mInitialMotionX = this.mInitialDownX;
    }

    private void checkOrientation(MotionEvent motionEvent) {
        int i2;
        this.mHelper.checkOrientation(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            SpringBackLayoutHelper springBackLayoutHelper = this.mHelper;
            this.mInitialDownY = springBackLayoutHelper.mInitialDownY;
            this.mInitialDownX = springBackLayoutHelper.mInitialDownX;
            this.mActivePointerId = springBackLayoutHelper.mActivePointerId;
            if (getScrollY() != 0) {
                this.mScrollOrientation = 2;
                requestDisallowParentInterceptTouchEvent(true);
            } else if (getScrollX() != 0) {
                this.mScrollOrientation = 1;
                requestDisallowParentInterceptTouchEvent(true);
            } else {
                this.mScrollOrientation = 0;
            }
            if ((this.mOriginScrollOrientation & 2) != 0) {
                checkScrollStart(2);
                return;
            } else {
                checkScrollStart(1);
                return;
            }
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.mScrollOrientation != 0 || (i2 = this.mHelper.mScrollOrientation) == 0) {
                    return;
                }
                this.mScrollOrientation = i2;
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked != 6) {
                    return;
                }
                onSecondaryPointerUp(motionEvent);
                return;
            }
        }
        disallowParentInterceptTouchEvent(false);
        if ((this.mOriginScrollOrientation & 2) != 0) {
            springBack(2);
        } else {
            springBack(1);
        }
    }

    private void checkScrollStart(int i2) {
        if (i2 == 2) {
            checkVerticalScrollStart(i2);
        } else {
            checkHorizontalScrollStart(i2);
        }
    }

    private void checkVerticalScrollStart(int i2) {
        if (getScrollY() == 0) {
            this.mIsBeingDragged = false;
            return;
        }
        this.mIsBeingDragged = true;
        float fObtainTouchDistance = obtainTouchDistance(Math.abs(getScrollY()), Math.abs(obtainMaxSpringBackDistance(i2)), 2);
        if (getScrollY() < 0) {
            this.mInitialDownY -= fObtainTouchDistance;
        } else {
            this.mInitialDownY += fObtainTouchDistance;
        }
        this.mInitialMotionY = this.mInitialDownY;
    }

    private void consumeDelta(int i2, @NonNull int[] iArr, int i3) {
        if (i3 == 2) {
            iArr[1] = i2;
        } else {
            iArr[0] = i2;
        }
    }

    private void disallowParentInterceptTouchEvent(boolean z2) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z2);
        }
    }

    private void dispatchScrollState(int i2) {
        int i3 = this.mScrollState;
        if (i3 != i2) {
            this.mScrollState = i2;
            Iterator<ViewCompatOnScrollChangeListener> it = this.mOnScrollChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onStateChanged(i3, i2, this.mSpringScroller.isFinished());
            }
        }
    }

    private void ensureTarget() {
        if (this.mTarget == null) {
            int i2 = this.mTargetId;
            if (i2 == -1) {
                throw new IllegalArgumentException("invalid target Id");
            }
            this.mTarget = findViewById(i2);
        }
        if (this.mTarget == null) {
            throw new IllegalArgumentException("fail to get target");
        }
        if (isEnabled()) {
            View view = this.mTarget;
            if ((view instanceof NestedScrollingChild3) && !view.isNestedScrollingEnabled()) {
                this.mTarget.setNestedScrollingEnabled(true);
            }
        }
        if (this.mTarget.getOverScrollMode() == 2 || !this.mSpringBackEnable) {
            return;
        }
        this.mTarget.setOverScrollMode(2);
    }

    private int getFakeScrollX() {
        return this.mFakeScrollX;
    }

    private int getFakeScrollY() {
        return this.mFakeScrollY;
    }

    private boolean isHorizontalTargetScrollToTop() {
        return !this.mTarget.canScrollHorizontally(-1);
    }

    private boolean isTargetScrollOrientation(int i2) {
        return this.mScrollOrientation == i2;
    }

    private boolean isTargetScrollToBottom(int i2) {
        if (i2 != 2) {
            return !this.mTarget.canScrollHorizontally(1);
        }
        return this.mTarget instanceof ListView ? !ListViewCompat.canScrollList((ListView) r2, 1) : !r2.canScrollVertically(1);
    }

    private boolean isTargetScrollToTop(int i2) {
        if (i2 != 2) {
            return !this.mTarget.canScrollHorizontally(-1);
        }
        return this.mTarget instanceof ListView ? !ListViewCompat.canScrollList((ListView) r2, -1) : !r2.canScrollVertically(-1);
    }

    private boolean isVerticalTargetScrollToTop() {
        return this.mTarget instanceof ListView ? !ListViewCompat.canScrollList((ListView) r2, -1) : !r2.canScrollVertically(-1);
    }

    private void moveTarget(float f2, int i2) {
        if (i2 == 2) {
            scrollTo(0, (int) (-f2));
        } else {
            scrollTo((int) (-f2), 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean onHorizontalInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.springback.view.SpringBackLayout.onHorizontalInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean onHorizontalTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        return ((this.mTarget instanceof GridView) && isTargetScrollToBottom(1) && isTargetScrollToTop(1)) ? onOverScrollEvent(motionEvent, actionMasked, 1) : (isTargetScrollToTop(1) || isTargetScrollToBottom(1)) ? isTargetScrollToBottom(1) ? onScrollUpEvent(motionEvent, actionMasked, 1) : onScrollDownEvent(motionEvent, actionMasked, 1) : onScrollEvent(motionEvent, actionMasked, 1);
    }

    private boolean onOverScrollEvent(MotionEvent motionEvent, int i2, int i3) {
        float fSignum;
        float fObtainSpringBackDistance;
        int actionIndex;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (iFindPointerIndex < 0) {
                        Log.e(TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    if (this.mIsBeingDragged) {
                        if (i3 == 2) {
                            float y2 = motionEvent.getY(iFindPointerIndex);
                            fSignum = Math.signum(y2 - this.mInitialMotionY);
                            fObtainSpringBackDistance = obtainSpringBackDistance(y2 - this.mInitialMotionY, i3);
                        } else {
                            float x2 = motionEvent.getX(iFindPointerIndex);
                            fSignum = Math.signum(x2 - this.mInitialMotionX);
                            fObtainSpringBackDistance = obtainSpringBackDistance(x2 - this.mInitialMotionX, i3);
                        }
                        float f2 = fSignum * fObtainSpringBackDistance;
                        if (Math.abs(f2) <= 0.0f) {
                            moveTarget(0.0f, i3);
                            return false;
                        }
                        requestDisallowParentInterceptTouchEvent(true);
                        moveTarget(f2, i3);
                    }
                } else if (i2 != 3) {
                    if (i2 == 5) {
                        int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (iFindPointerIndex2 < 0) {
                            Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid active pointer id.");
                            return false;
                        }
                        if (i3 == 2) {
                            float y3 = motionEvent.getY(iFindPointerIndex2) - this.mInitialDownY;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float y4 = motionEvent.getY(actionIndex) - y3;
                            this.mInitialDownY = y4;
                            this.mInitialMotionY = y4;
                        } else {
                            float x3 = motionEvent.getX(iFindPointerIndex2) - this.mInitialDownX;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float x4 = motionEvent.getX(actionIndex) - x3;
                            this.mInitialDownX = x4;
                            this.mInitialMotionX = x4;
                        }
                        this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                    } else if (i2 == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            if (motionEvent.findPointerIndex(this.mActivePointerId) < 0) {
                Log.e(TAG, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.mIsBeingDragged) {
                this.mIsBeingDragged = false;
                springBack(i3);
            }
            this.mActivePointerId = -1;
            return false;
        }
        this.mActivePointerId = motionEvent.getPointerId(0);
        checkScrollStart(i3);
        return true;
    }

    private boolean onScrollDownEvent(MotionEvent motionEvent, int i2, int i3) {
        float fSignum;
        float fObtainSpringBackDistance;
        int actionIndex;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (iFindPointerIndex < 0) {
                        Log.e(TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    if (this.mIsBeingDragged) {
                        if (i3 == 2) {
                            float y2 = motionEvent.getY(iFindPointerIndex);
                            fSignum = Math.signum(y2 - this.mInitialMotionY);
                            fObtainSpringBackDistance = obtainSpringBackDistance(y2 - this.mInitialMotionY, i3);
                        } else {
                            float x2 = motionEvent.getX(iFindPointerIndex);
                            fSignum = Math.signum(x2 - this.mInitialMotionX);
                            fObtainSpringBackDistance = obtainSpringBackDistance(x2 - this.mInitialMotionX, i3);
                        }
                        float f2 = fSignum * fObtainSpringBackDistance;
                        if (f2 <= 0.0f) {
                            moveTarget(0.0f, i3);
                            return false;
                        }
                        requestDisallowParentInterceptTouchEvent(true);
                        moveTarget(f2, i3);
                    }
                } else if (i2 != 3) {
                    if (i2 == 5) {
                        int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (iFindPointerIndex2 < 0) {
                            Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid active pointer id.");
                            return false;
                        }
                        if (i3 == 2) {
                            float y3 = motionEvent.getY(iFindPointerIndex2) - this.mInitialDownY;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float y4 = motionEvent.getY(actionIndex) - y3;
                            this.mInitialDownY = y4;
                            this.mInitialMotionY = y4;
                        } else {
                            float x3 = motionEvent.getX(iFindPointerIndex2) - this.mInitialDownX;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float x4 = motionEvent.getX(actionIndex) - x3;
                            this.mInitialDownX = x4;
                            this.mInitialMotionX = x4;
                        }
                        this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                    } else if (i2 == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            if (motionEvent.findPointerIndex(this.mActivePointerId) < 0) {
                Log.e(TAG, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.mIsBeingDragged) {
                this.mIsBeingDragged = false;
                springBack(i3);
            }
            this.mActivePointerId = -1;
            return false;
        }
        this.mActivePointerId = motionEvent.getPointerId(0);
        checkScrollStart(i3);
        return true;
    }

    private boolean onScrollEvent(MotionEvent motionEvent, int i2, int i3) {
        float fSignum;
        float fObtainSpringBackDistance;
        int actionIndex;
        if (i2 == 0) {
            this.mActivePointerId = motionEvent.getPointerId(0);
            checkScrollStart(i3);
        } else {
            if (i2 == 1) {
                if (motionEvent.findPointerIndex(this.mActivePointerId) < 0) {
                    Log.e(TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }
                if (this.mIsBeingDragged) {
                    this.mIsBeingDragged = false;
                    springBack(i3);
                }
                this.mActivePointerId = -1;
                return false;
            }
            if (i2 == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (iFindPointerIndex < 0) {
                    Log.e(TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }
                if (this.mIsBeingDragged) {
                    if (i3 == 2) {
                        float y2 = motionEvent.getY(iFindPointerIndex);
                        fSignum = Math.signum(y2 - this.mInitialMotionY);
                        fObtainSpringBackDistance = obtainSpringBackDistance(y2 - this.mInitialMotionY, i3);
                    } else {
                        float x2 = motionEvent.getX(iFindPointerIndex);
                        fSignum = Math.signum(x2 - this.mInitialMotionX);
                        fObtainSpringBackDistance = obtainSpringBackDistance(x2 - this.mInitialMotionX, i3);
                    }
                    float f2 = fSignum * fObtainSpringBackDistance;
                    requestDisallowParentInterceptTouchEvent(true);
                    moveTarget(f2, i3);
                }
            } else {
                if (i2 == 3) {
                    return false;
                }
                if (i2 == 5) {
                    int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (iFindPointerIndex2 < 0) {
                        Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid active pointer id.");
                        return false;
                    }
                    if (i3 == 2) {
                        float y3 = motionEvent.getY(iFindPointerIndex2) - this.mInitialDownY;
                        actionIndex = motionEvent.getActionIndex();
                        if (actionIndex < 0) {
                            Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                            return false;
                        }
                        float y4 = motionEvent.getY(actionIndex) - y3;
                        this.mInitialDownY = y4;
                        this.mInitialMotionY = y4;
                    } else {
                        float x3 = motionEvent.getX(iFindPointerIndex2) - this.mInitialDownX;
                        actionIndex = motionEvent.getActionIndex();
                        if (actionIndex < 0) {
                            Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                            return false;
                        }
                        float x4 = motionEvent.getX(actionIndex) - x3;
                        this.mInitialDownX = x4;
                        this.mInitialMotionX = x4;
                    }
                    this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                } else if (i2 == 6) {
                    onSecondaryPointerUp(motionEvent);
                }
            }
        }
        return true;
    }

    private boolean onScrollUpEvent(MotionEvent motionEvent, int i2, int i3) {
        float fSignum;
        float fObtainSpringBackDistance;
        int actionIndex;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (iFindPointerIndex < 0) {
                        Log.e(TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                        return false;
                    }
                    if (this.mIsBeingDragged) {
                        if (i3 == 2) {
                            float y2 = motionEvent.getY(iFindPointerIndex);
                            fSignum = Math.signum(this.mInitialMotionY - y2);
                            fObtainSpringBackDistance = obtainSpringBackDistance(this.mInitialMotionY - y2, i3);
                        } else {
                            float x2 = motionEvent.getX(iFindPointerIndex);
                            fSignum = Math.signum(this.mInitialMotionX - x2);
                            fObtainSpringBackDistance = obtainSpringBackDistance(this.mInitialMotionX - x2, i3);
                        }
                        float f2 = fSignum * fObtainSpringBackDistance;
                        if (f2 <= 0.0f) {
                            moveTarget(0.0f, i3);
                            return false;
                        }
                        requestDisallowParentInterceptTouchEvent(true);
                        moveTarget(-f2, i3);
                    }
                } else if (i2 != 3) {
                    if (i2 == 5) {
                        int iFindPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (iFindPointerIndex2 < 0) {
                            Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid active pointer id.");
                            return false;
                        }
                        if (i3 == 2) {
                            float y3 = motionEvent.getY(iFindPointerIndex2) - this.mInitialDownY;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float y4 = motionEvent.getY(actionIndex) - y3;
                            this.mInitialDownY = y4;
                            this.mInitialMotionY = y4;
                        } else {
                            float x3 = motionEvent.getX(iFindPointerIndex2) - this.mInitialDownX;
                            actionIndex = motionEvent.getActionIndex();
                            if (actionIndex < 0) {
                                Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                                return false;
                            }
                            float x4 = motionEvent.getX(actionIndex) - x3;
                            this.mInitialDownX = x4;
                            this.mInitialMotionX = x4;
                        }
                        this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                    } else if (i2 == 6) {
                        onSecondaryPointerUp(motionEvent);
                    }
                }
            }
            if (motionEvent.findPointerIndex(this.mActivePointerId) < 0) {
                Log.e(TAG, "Got ACTION_UP event but don't have an active pointer id.");
                return false;
            }
            if (this.mIsBeingDragged) {
                this.mIsBeingDragged = false;
                springBack(i3);
            }
            this.mActivePointerId = -1;
            return false;
        }
        this.mActivePointerId = motionEvent.getPointerId(0);
        checkScrollStart(i3);
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean onVerticalInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.springback.view.SpringBackLayout.onVerticalInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean onVerticalTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        return ((this.mTarget instanceof GridView) && isTargetScrollToBottom(2) && isTargetScrollToTop(2)) ? onOverScrollEvent(motionEvent, actionMasked, 2) : (isTargetScrollToTop(2) || isTargetScrollToBottom(2)) ? isTargetScrollToBottom(2) ? onScrollUpEvent(motionEvent, actionMasked, 2) : onScrollDownEvent(motionEvent, actionMasked, 2) : onScrollEvent(motionEvent, actionMasked, 2);
    }

    private void springBack(int i2) {
        springBack(0.0f, i2, true);
    }

    private void stopNestedFlingScroll(int i2) {
        this.mNestedFlingInProgress = false;
        if (!this.mScrollByFling) {
            springBack(i2);
            return;
        }
        if (this.mSpringScroller.isFinished()) {
            springBack(i2 == 2 ? this.mVelocityY : this.mVelocityX, i2, false);
        }
        AnimationHelper.postInvalidateOnAnimation(this);
    }

    private boolean supportBottomSpringBackMode() {
        return (this.mSpringBackMode & 2) != 0;
    }

    private boolean supportTopSpringBackMode() {
        return (this.mSpringBackMode & 1) != 0;
    }

    @Override // miuix.core.view.ScrollStateDispatcher
    public void addOnScrollChangeListener(ViewCompatOnScrollChangeListener viewCompatOnScrollChangeListener) {
        this.mOnScrollChangeListeners.add(viewCompatOnScrollChangeListener);
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.mSpringScroller.computeScrollOffset()) {
            scrollTo(this.mSpringScroller.getCurrX(), this.mSpringScroller.getCurrY());
            if (!this.mSpringScroller.isFinished()) {
                AnimationHelper.postInvalidateOnAnimation(this);
                return;
            }
            if (getSpringScrollX() != 0 || getSpringScrollY() != 0) {
                if (this.mScrollState != 2) {
                    Log.d(TAG, "Scroll stop but state is not correct.");
                    springBack(this.mNestedScrollAxes != 2 ? 1 : 2);
                    return;
                }
            }
            dispatchScrollState(0);
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f2, f3, z2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f2, float f3) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f2, f3);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2, i4);
    }

    @Override // androidx.core.view.NestedScrollingChild3
    public void dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @NonNull int[] iArr2) {
        this.mNestedScrollingChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && this.mScrollState == 2 && this.mHelper.isTouchInTarget(motionEvent)) {
            dispatchScrollState(1);
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (motionEvent.getActionMasked() == 1 && this.mScrollState != 2) {
            dispatchScrollState(0);
        }
        return zDispatchTouchEvent;
    }

    public int getSpringBackMode() {
        return this.mSpringBackMode;
    }

    public int getSpringBackRange(int i2) {
        return i2 == 2 ? this.mScreenHeight : this.mScreenWidth;
    }

    public int getSpringScrollX() {
        return this.mSpringBackEnable ? getScrollX() : getFakeScrollX();
    }

    public int getSpringScrollY() {
        return this.mSpringBackEnable ? getScrollY() : getFakeScrollY();
    }

    public View getTarget() {
        return this.mTarget;
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean hasNestedScrollingParent(int i2) {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent(i2);
    }

    public boolean hasSpringListener() {
        return this.mOnSpringListener != null;
    }

    public void internalRequestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public float obtainDampingDistance(float f2, int i2) {
        double dMin = Math.min(f2, 1.0f);
        return ((float) (((Math.pow(dMin, 3.0d) / 3.0d) - Math.pow(dMin, 2.0d)) + dMin)) * i2;
    }

    public float obtainMaxSpringBackDistance(int i2) {
        return obtainDampingDistance(1.0f, getSpringBackRange(i2));
    }

    public float obtainSpringBackDistance(float f2, int i2) {
        int springBackRange = getSpringBackRange(i2);
        return obtainDampingDistance(Math.min(Math.abs(f2) / springBackRange, 1.0f), springBackRange);
    }

    public float obtainTouchDistance(float f2, float f3, int i2) {
        int springBackRange = getSpringBackRange(i2);
        if (Math.abs(f2) >= Math.abs(f3)) {
            f2 = f3;
        }
        double d2 = springBackRange;
        return (float) (d2 - (Math.pow(d2, 0.6666666666666666d) * Math.pow(springBackRange - (f2 * 3.0f), 0.3333333333333333d)));
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Point screenSize = EnvStateManager.getScreenSize(getContext());
        this.mScreenWidth = screenSize.x;
        this.mScreenHeight = screenSize.y;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mInitPaddingTop = getPaddingTop();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mSpringBackEnable || !isEnabled() || this.mNestedFlingInProgress || this.mNestedScrollInProgress || this.mTarget.isNestedScrollingEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (!this.mSpringScroller.isFinished() && actionMasked == 0) {
            this.mSpringScroller.forceStop();
        }
        if (!supportTopSpringBackMode() && !supportBottomSpringBackMode()) {
            return false;
        }
        int i2 = this.mOriginScrollOrientation;
        if ((i2 & 4) != 0) {
            checkOrientation(motionEvent);
            if (isTargetScrollOrientation(2) && (this.mOriginScrollOrientation & 1) != 0 && getScrollX() == 0.0f) {
                return false;
            }
            if (isTargetScrollOrientation(1) && (this.mOriginScrollOrientation & 2) != 0 && getScrollY() == 0.0f) {
                return false;
            }
            if (isTargetScrollOrientation(2) || isTargetScrollOrientation(1)) {
                disallowParentInterceptTouchEvent(true);
            }
        } else {
            this.mScrollOrientation = i2;
        }
        if (isTargetScrollOrientation(2)) {
            return onVerticalInterceptTouchEvent(motionEvent);
        }
        if (isTargetScrollOrientation(1)) {
            return onHorizontalInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.mTarget.getVisibility() != 8) {
            int measuredWidth = this.mTarget.getMeasuredWidth();
            int measuredHeight = this.mTarget.getMeasuredHeight();
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            this.mTarget.layout(paddingLeft, paddingTop, measuredWidth + paddingLeft, measuredHeight + paddingTop);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        ensureTarget();
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        measureChild(this.mTarget, i2, i3);
        setMeasuredDimension(mode == 0 ? this.mTarget.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() : mode == 1073741824 ? View.MeasureSpec.getSize(i2) : Math.min(View.MeasureSpec.getSize(i2), this.mTarget.getMeasuredWidth() + getPaddingLeft() + getPaddingRight()), mode2 == 0 ? this.mTarget.getMeasuredHeight() + getPaddingTop() + getPaddingBottom() : mode2 == 1073741824 ? View.MeasureSpec.getSize(i3) : Math.min(View.MeasureSpec.getSize(i3), this.mTarget.getMeasuredHeight() + getPaddingTop() + getPaddingBottom()));
    }

    @Override // miuix.core.view.NestedCurrentFling
    public boolean onNestedCurrentFling(float f2, float f3) {
        this.mVelocityX = f2;
        this.mVelocityY = f3;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        return dispatchNestedFling(f2, f3, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        if (this.mSpringBackEnable) {
            if (this.mNestedScrollAxes == 2) {
                onNestedPreScroll(i3, iArr, i4);
            } else {
                onNestedPreScroll(i2, iArr, i4);
            }
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i2 - iArr[0], i3 - iArr[1], iArr2, null, i4)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6, @NonNull int[] iArr) {
        boolean z2 = this.mNestedScrollAxes == 2;
        int i7 = z2 ? i3 : i2;
        int i8 = z2 ? iArr[1] : iArr[0];
        dispatchNestedScroll(i2, i3, i4, i5, this.mParentOffsetInWindow, i6, iArr);
        if (this.mSpringBackEnable) {
            int i9 = (z2 ? iArr[1] : iArr[0]) - i8;
            int i10 = z2 ? i5 - i9 : i4 - i9;
            int i11 = i10 != 0 ? i10 : 0;
            int i12 = z2 ? 2 : 1;
            if (i11 < 0 && isTargetScrollToTop(i12) && supportTopSpringBackMode()) {
                if (i6 == 0) {
                    if (this.mSpringScroller.isFinished()) {
                        this.mTotalScrollTopUnconsumed += Math.abs(i11);
                        dispatchScrollState(1);
                        moveTarget(obtainSpringBackDistance(this.mTotalScrollTopUnconsumed, i12), i12);
                        iArr[1] = iArr[1] + i10;
                        return;
                    }
                    return;
                }
                float fObtainMaxSpringBackDistance = obtainMaxSpringBackDistance(i12);
                if (this.mVelocityY != 0.0f || this.mVelocityX != 0.0f) {
                    this.mScrollByFling = true;
                    if (i7 != 0 && (-i11) <= fObtainMaxSpringBackDistance) {
                        this.mSpringScroller.setFirstStep(i11);
                    }
                    dispatchScrollState(2);
                    return;
                }
                if (this.mTotalScrollTopUnconsumed != 0.0f) {
                    return;
                }
                float f2 = fObtainMaxSpringBackDistance - this.mTotalFlingUnconsumed;
                if (this.consumeNestFlingCounter < 4) {
                    if (f2 <= Math.abs(i11)) {
                        this.mTotalFlingUnconsumed += f2;
                        iArr[1] = (int) (iArr[1] + f2);
                    } else {
                        this.mTotalFlingUnconsumed += Math.abs(i11);
                        iArr[1] = iArr[1] + i10;
                    }
                    dispatchScrollState(2);
                    moveTarget(obtainSpringBackDistance(this.mTotalFlingUnconsumed, i12), i12);
                    this.consumeNestFlingCounter++;
                    return;
                }
                return;
            }
            if (i11 > 0 && isTargetScrollToBottom(i12) && supportBottomSpringBackMode()) {
                if (i6 == 0) {
                    if (this.mSpringScroller.isFinished()) {
                        this.mTotalScrollBottomUnconsumed += Math.abs(i11);
                        dispatchScrollState(1);
                        moveTarget(-obtainSpringBackDistance(this.mTotalScrollBottomUnconsumed, i12), i12);
                        iArr[1] = iArr[1] + i10;
                        return;
                    }
                    return;
                }
                float fObtainMaxSpringBackDistance2 = obtainMaxSpringBackDistance(i12);
                if (this.mVelocityY != 0.0f || this.mVelocityX != 0.0f) {
                    this.mScrollByFling = true;
                    if (i7 != 0 && i11 <= fObtainMaxSpringBackDistance2) {
                        this.mSpringScroller.setFirstStep(i11);
                    }
                    dispatchScrollState(2);
                    return;
                }
                if (this.mTotalScrollBottomUnconsumed != 0.0f) {
                    return;
                }
                float f3 = fObtainMaxSpringBackDistance2 - this.mTotalFlingUnconsumed;
                if (this.consumeNestFlingCounter < 4) {
                    if (f3 <= Math.abs(i11)) {
                        this.mTotalFlingUnconsumed += f3;
                        iArr[1] = (int) (iArr[1] + f3);
                    } else {
                        this.mTotalFlingUnconsumed += Math.abs(i11);
                        iArr[1] = iArr[1] + i10;
                    }
                    dispatchScrollState(2);
                    moveTarget(-obtainSpringBackDistance(this.mTotalFlingUnconsumed, i12), i12);
                    this.consumeNestFlingCounter++;
                }
            }
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
        if (this.mSpringBackEnable) {
            boolean z2 = this.mNestedScrollAxes == 2;
            int i4 = z2 ? 2 : 1;
            float scrollY = z2 ? getScrollY() : getScrollX();
            if (i3 != 0) {
                if (scrollY == 0.0f) {
                    this.mTotalFlingUnconsumed = 0.0f;
                } else {
                    this.mTotalFlingUnconsumed = obtainTouchDistance(Math.abs(scrollY), Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                }
                this.mNestedFlingInProgress = true;
                this.consumeNestFlingCounter = 0;
            } else {
                if (scrollY == 0.0f) {
                    this.mTotalScrollTopUnconsumed = 0.0f;
                    this.mTotalScrollBottomUnconsumed = 0.0f;
                } else if (scrollY < 0.0f) {
                    this.mTotalScrollTopUnconsumed = obtainTouchDistance(Math.abs(scrollY), Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                    this.mTotalScrollBottomUnconsumed = 0.0f;
                } else {
                    this.mTotalScrollTopUnconsumed = 0.0f;
                    this.mTotalScrollBottomUnconsumed = obtainTouchDistance(Math.abs(scrollY), Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                }
                this.mNestedScrollInProgress = true;
            }
            this.mVelocityY = 0.0f;
            this.mVelocityX = 0.0f;
            this.mScrollByFling = false;
            this.mSpringScroller.forceStop();
        }
        onNestedScrollAccepted(view, view2, i2);
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        Iterator<ViewCompatOnScrollChangeListener> it = this.mOnScrollChangeListeners.iterator();
        while (it.hasNext()) {
            it.next().onScrollChange(this, i2, i3, i4, i5);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        this.mNestedScrollAxes = i2;
        boolean z2 = i2 == 2;
        if (((z2 ? 2 : 1) & this.mOriginScrollOrientation) == 0) {
            return false;
        }
        if (this.mSpringBackEnable) {
            if (!onStartNestedScroll(view, view, i2)) {
                return false;
            }
            float scrollY = z2 ? getScrollY() : getScrollX();
            if (i3 != 0 && scrollY != 0.0f && (this.mTarget instanceof NestedScrollView)) {
                return false;
            }
        }
        this.mNestedScrollingChildHelper.startNestedScroll(i2, i3);
        return true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view, i2);
        stopNestedScroll(i2);
        if (this.mSpringBackEnable) {
            boolean z2 = this.mNestedScrollAxes == 2;
            int i3 = z2 ? 2 : 1;
            if (!this.mNestedScrollInProgress) {
                if (this.mNestedFlingInProgress) {
                    stopNestedFlingScroll(i3);
                    return;
                }
                return;
            }
            this.mNestedScrollInProgress = false;
            float scrollY = z2 ? getScrollY() : getScrollX();
            if (!this.mNestedFlingInProgress && scrollY != 0.0f) {
                springBack(i3);
            } else if (scrollY != 0.0f) {
                stopNestedFlingScroll(i3);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (!isEnabled() || this.mNestedFlingInProgress || this.mNestedScrollInProgress || this.mTarget.isNestedScrollingEnabled()) {
            return false;
        }
        if (!this.mSpringScroller.isFinished() && actionMasked == 0) {
            this.mSpringScroller.forceStop();
        }
        if (isTargetScrollOrientation(2)) {
            return onVerticalTouchEvent(motionEvent);
        }
        if (isTargetScrollOrientation(1)) {
            return onHorizontalTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // miuix.core.view.ScrollStateDispatcher
    public void removeOnScrollChangeListener(ViewCompatOnScrollChangeListener viewCompatOnScrollChangeListener) {
        this.mOnScrollChangeListeners.remove(viewCompatOnScrollChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z2) {
        if (isEnabled() && this.mSpringBackEnable) {
            return;
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    public void requestDisallowParentInterceptTouchEvent(boolean z2) {
        ViewParent parent = getParent();
        parent.requestDisallowInterceptTouchEvent(z2);
        while (parent != null) {
            if (parent instanceof SpringBackLayout) {
                ((SpringBackLayout) parent).internalRequestDisallowInterceptTouchEvent(z2);
            }
            parent = parent.getParent();
        }
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        if (this.mSpringBackEnable) {
            super.scrollTo(i2, i3);
            return;
        }
        int i4 = this.mFakeScrollX;
        if (i4 == i2 && this.mFakeScrollY == i3) {
            return;
        }
        int i5 = this.mFakeScrollY;
        this.mFakeScrollX = i2;
        this.mFakeScrollY = i3;
        onScrollChanged(i2, i3, i4, i5);
        if (!awakenScrollBars()) {
            postInvalidateOnAnimation();
        }
        requestLayout();
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        View view = this.mTarget;
        if (view == null || !(view instanceof NestedScrollingChild3) || z2 == view.isNestedScrollingEnabled()) {
            return;
        }
        this.mTarget.setNestedScrollingEnabled(z2);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z2) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z2);
    }

    public void setOnSpringListener(OnSpringListener onSpringListener) {
        this.mOnSpringListener = onSpringListener;
    }

    public void setScrollOrientation(int i2) {
        this.mOriginScrollOrientation = i2;
        this.mHelper.mTargetScrollOrientation = i2;
    }

    public void setSpringBackEnable(boolean z2) {
        if (this.mInGlobalRomMode) {
            return;
        }
        this.mSpringBackEnable = z2;
    }

    public void setSpringBackEnableOnTriggerAttached(boolean z2) {
        this.mSpringBackEnable = z2;
    }

    public void setSpringBackMode(int i2) {
        this.mSpringBackMode = i2;
    }

    public void setTarget(@NonNull View view) {
        this.mTarget = view;
        if ((view instanceof NestedScrollingChild3) && !view.isNestedScrollingEnabled()) {
            this.mTarget.setNestedScrollingEnabled(true);
        }
        if (this.mTarget.getOverScrollMode() == 2 || !this.mSpringBackEnable) {
            return;
        }
        this.mTarget.setOverScrollMode(2);
    }

    public void smoothScrollTo(int i2, int i3) {
        if (i2 - getScrollX() == 0 && i3 - getScrollY() == 0) {
            return;
        }
        this.mSpringScroller.forceStop();
        this.mSpringScroller.scrollByFling(getScrollX(), i2, getScrollY(), i3, 0.0f, 2, true);
        dispatchScrollState(2);
        AnimationHelper.postInvalidateOnAnimation(this);
    }

    public boolean springBackEnable() {
        return this.mSpringBackEnable;
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean startNestedScroll(int i2, int i3) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i2, i3);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }

    public SpringBackLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActivePointerId = -1;
        this.consumeNestFlingCounter = 0;
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mOnScrollChangeListeners = new ArrayList();
        this.mScrollState = 0;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = miuix.core.view.NestedScrollingChildHelper.obtain(this);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpringBackLayout);
        this.mTargetId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.SpringBackLayout_scrollableView, -1);
        this.mOriginScrollOrientation = typedArrayObtainStyledAttributes.getInt(R.styleable.SpringBackLayout_scrollOrientation, 2);
        this.mSpringBackMode = typedArrayObtainStyledAttributes.getInt(R.styleable.SpringBackLayout_springBackMode, 3);
        typedArrayObtainStyledAttributes.recycle();
        this.mSpringScroller = new SpringScroller();
        this.mHelper = new SpringBackLayoutHelper(this, this.mOriginScrollOrientation);
        setNestedScrollingEnabled(true);
        Point screenSize = EnvStateManager.getScreenSize(context);
        this.mScreenWidth = screenSize.x;
        this.mScreenHeight = screenSize.y;
        boolean z2 = Build.IS_INTERNATIONAL_BUILD;
        this.mInGlobalRomMode = z2;
        if (z2) {
            this.mSpringBackEnable = false;
        } else {
            this.mSpringBackEnable = true;
        }
    }

    private void springBack(float f2, int i2, boolean z2) {
        OnSpringListener onSpringListener = this.mOnSpringListener;
        if (onSpringListener == null || !onSpringListener.onSpringBack()) {
            this.mSpringScroller.forceStop();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            this.mSpringScroller.scrollByFling(scrollX, 0.0f, scrollY, 0.0f, f2, i2, false);
            if (scrollX == 0 && scrollY == 0 && f2 == 0.0f) {
                dispatchScrollState(0);
            } else {
                dispatchScrollState(2);
            }
            if (z2) {
                AnimationHelper.postInvalidateOnAnimation(this);
            }
        }
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr, i6);
    }

    @Override // android.view.View, androidx.core.view.NestedScrollingChild
    public boolean startNestedScroll(int i2) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i2);
    }

    @Override // androidx.core.view.NestedScrollingChild2
    public void stopNestedScroll(int i2) {
        this.mNestedScrollingChildHelper.stopNestedScroll(i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view, View view2, int i2) {
        return isEnabled();
    }

    private void onNestedPreScroll(int i2, @NonNull int[] iArr, int i3) {
        boolean z2 = this.mNestedScrollAxes == 2;
        int i4 = z2 ? 2 : 1;
        int iAbs = Math.abs(z2 ? getScrollY() : getScrollX());
        float f2 = 0.0f;
        if (i3 == 0) {
            if (i2 > 0) {
                float f3 = this.mTotalScrollTopUnconsumed;
                if (f3 > 0.0f) {
                    float f4 = i2;
                    if (f4 > f3) {
                        consumeDelta((int) f3, iArr, i4);
                        this.mTotalScrollTopUnconsumed = 0.0f;
                    } else {
                        this.mTotalScrollTopUnconsumed = f3 - f4;
                        consumeDelta(i2, iArr, i4);
                    }
                    dispatchScrollState(1);
                    moveTarget(obtainSpringBackDistance(this.mTotalScrollTopUnconsumed, i4), i4);
                    return;
                }
            }
            if (i2 < 0) {
                float f5 = this.mTotalScrollBottomUnconsumed;
                if ((-f5) < 0.0f) {
                    float f6 = i2;
                    if (f6 < (-f5)) {
                        consumeDelta((int) f5, iArr, i4);
                        this.mTotalScrollBottomUnconsumed = 0.0f;
                    } else {
                        this.mTotalScrollBottomUnconsumed = f5 + f6;
                        consumeDelta(i2, iArr, i4);
                    }
                    dispatchScrollState(1);
                    moveTarget(-obtainSpringBackDistance(this.mTotalScrollBottomUnconsumed, i4), i4);
                    return;
                }
                return;
            }
            return;
        }
        float f7 = i4 == 2 ? this.mVelocityY : this.mVelocityX;
        if (i2 > 0) {
            float f8 = this.mTotalScrollTopUnconsumed;
            if (f8 > 0.0f) {
                if (f7 > 2000.0f) {
                    float fObtainSpringBackDistance = obtainSpringBackDistance(f8, i4);
                    float f9 = i2;
                    if (f9 > fObtainSpringBackDistance) {
                        consumeDelta((int) fObtainSpringBackDistance, iArr, i4);
                        this.mTotalScrollTopUnconsumed = 0.0f;
                    } else {
                        consumeDelta(i2, iArr, i4);
                        f2 = fObtainSpringBackDistance - f9;
                        this.mTotalScrollTopUnconsumed = obtainTouchDistance(f2, Math.signum(f2) * Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                    }
                    moveTarget(f2, i4);
                    dispatchScrollState(1);
                    return;
                }
                if (!this.mScrollByFling) {
                    this.mScrollByFling = true;
                    springBack(f7, i4, false);
                }
                if (this.mSpringScroller.computeScrollOffset()) {
                    scrollTo(this.mSpringScroller.getCurrX(), this.mSpringScroller.getCurrY());
                    this.mTotalScrollTopUnconsumed = obtainTouchDistance(iAbs, Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                } else {
                    this.mTotalScrollTopUnconsumed = 0.0f;
                }
                consumeDelta(i2, iArr, i4);
                return;
            }
        }
        if (i2 < 0) {
            float f10 = this.mTotalScrollBottomUnconsumed;
            if ((-f10) < 0.0f) {
                if (f7 < -2000.0f) {
                    float fObtainSpringBackDistance2 = obtainSpringBackDistance(f10, i4);
                    float f11 = i2;
                    if (f11 < (-fObtainSpringBackDistance2)) {
                        consumeDelta((int) fObtainSpringBackDistance2, iArr, i4);
                        this.mTotalScrollBottomUnconsumed = 0.0f;
                    } else {
                        consumeDelta(i2, iArr, i4);
                        f2 = fObtainSpringBackDistance2 + f11;
                        this.mTotalScrollBottomUnconsumed = obtainTouchDistance(f2, Math.signum(f2) * Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                    }
                    dispatchScrollState(1);
                    moveTarget(-f2, i4);
                    return;
                }
                if (!this.mScrollByFling) {
                    this.mScrollByFling = true;
                    springBack(f7, i4, false);
                }
                if (this.mSpringScroller.computeScrollOffset()) {
                    scrollTo(this.mSpringScroller.getCurrX(), this.mSpringScroller.getCurrY());
                    this.mTotalScrollBottomUnconsumed = obtainTouchDistance(iAbs, Math.abs(obtainMaxSpringBackDistance(i4)), i4);
                } else {
                    this.mTotalScrollBottomUnconsumed = 0.0f;
                }
                consumeDelta(i2, iArr, i4);
                return;
            }
        }
        if (i2 != 0) {
            if ((this.mTotalScrollBottomUnconsumed == 0.0f || this.mTotalScrollTopUnconsumed == 0.0f) && this.mScrollByFling && getScrollY() == 0) {
                consumeDelta(i2, iArr, i4);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view, View view2, int i2) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i2);
        startNestedScroll(i2 & 2);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(@NonNull View view, int i2, int i3, int i4, int i5, int i6) {
        onNestedScroll(view, i2, i3, i4, i5, i6, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i2, i3, i4, i5, 0, this.mNestedScrollingV2ConsumedCompat);
    }
}
