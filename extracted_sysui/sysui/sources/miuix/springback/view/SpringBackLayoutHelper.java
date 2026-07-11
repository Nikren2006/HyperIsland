package miuix.springback.view;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes5.dex */
public class SpringBackLayoutHelper {
    private static final int INVALID_POINTER = -1;
    int mActivePointerId = -1;
    float mInitialDownX;
    float mInitialDownY;
    int mScrollOrientation;
    private ViewGroup mTarget;
    int mTargetScrollOrientation;
    private int mTouchSlop;

    public SpringBackLayoutHelper(ViewGroup viewGroup, int i2) {
        this.mTarget = viewGroup;
        this.mTargetScrollOrientation = i2;
        this.mTouchSlop = ViewConfiguration.get(viewGroup.getContext()).getScaledTouchSlop();
    }

    public void checkOrientation(MotionEvent motionEvent) {
        int iFindPointerIndex;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            int pointerId = motionEvent.getPointerId(0);
            this.mActivePointerId = pointerId;
            int iFindPointerIndex2 = motionEvent.findPointerIndex(pointerId);
            if (iFindPointerIndex2 < 0) {
                return;
            }
            this.mInitialDownY = motionEvent.getY(iFindPointerIndex2);
            this.mInitialDownX = motionEvent.getX(iFindPointerIndex2);
            this.mScrollOrientation = 0;
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int i2 = this.mActivePointerId;
                if (i2 != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float f2 = y2 - this.mInitialDownY;
                    float f3 = x2 - this.mInitialDownX;
                    if (Math.abs(f3) > this.mTouchSlop || Math.abs(f2) > this.mTouchSlop) {
                        this.mScrollOrientation = Math.abs(f3) <= Math.abs(f2) ? 2 : 1;
                        return;
                    }
                    return;
                }
                return;
            }
            if (actionMasked != 3) {
                return;
            }
        }
        this.mScrollOrientation = 0;
        this.mTarget.requestDisallowInterceptTouchEvent(false);
    }

    public boolean isTouchInTarget(MotionEvent motionEvent) {
        int iFindPointerIndex = motionEvent.findPointerIndex(motionEvent.getPointerId(0));
        if (iFindPointerIndex < 0) {
            return false;
        }
        float y2 = motionEvent.getY(iFindPointerIndex);
        float x2 = motionEvent.getX(iFindPointerIndex);
        int[] iArr = {0, 0};
        this.mTarget.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        return new Rect(i2, i3, this.mTarget.getWidth() + i2, this.mTarget.getHeight() + i3).contains((int) x2, (int) y2);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        checkOrientation(motionEvent);
        int i2 = this.mScrollOrientation;
        if (i2 == 0 || i2 == this.mTargetScrollOrientation) {
            this.mTarget.requestDisallowInterceptTouchEvent(false);
            return true;
        }
        this.mTarget.requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
