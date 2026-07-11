package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import miuix.animation.Folme;
import miuix.animation.utils.EaseManager;
import miuix.device.DeviceUtils;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes.dex */
public class SpringItemTouchHelper extends ItemTouchHelper {
    private static final TimeInterpolator INTERPOLATOR = new EaseManager.SpringInterpolator().setDamping(0.95f).setResponse(0.3f);
    boolean mSpringEnabled;
    private final float[] mTmpPosition;
    private final boolean mUseFolmeRecoverAnimation;

    public SpringItemTouchHelper(@NonNull ItemTouchHelper.Callback callback) {
        super(callback);
        this.mTmpPosition = new float[2];
        this.mUseFolmeRecoverAnimation = (DeviceUtils.isMiuiLiteV2() || DeviceUtils.isLiteV1StockPlus()) ? false : true;
    }

    private static boolean hitTest(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    @SuppressLint({"VisibleForTests"})
    private void super_select_overwrite() {
        boolean z2;
        Reflects.set(this, Reflects.getDeclaredField((Class<?>) ItemTouchHelper.class, "mDragScrollStartTimeInMs"), Long.MIN_VALUE);
        endRecoverAnimation(null, true);
        Reflects.set(this, Reflects.getDeclaredField((Class<?>) ItemTouchHelper.class, "mActionState"), 0);
        final RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder.itemView.getParent() != null) {
            releaseVelocityTracker();
            Reflects.invoke(this, Reflects.getDeclaredMethod((Class<?>) ItemTouchHelper.class, "getSelectedDxDy", (Class<?>[]) new Class[]{float[].class}), this.mTmpPosition);
            float[] fArr = this.mTmpPosition;
            ItemTouchHelper.RecoverAnimation recoverAnimation = new ItemTouchHelper.RecoverAnimation(viewHolder, 8, 2, fArr[0], fArr[1], 0.0f, 0.0f) { // from class: androidx.recyclerview.widget.SpringItemTouchHelper.1
                @Override // androidx.recyclerview.widget.ItemTouchHelper.RecoverAnimation, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (this.mOverridden) {
                        return;
                    }
                    SpringItemTouchHelper springItemTouchHelper = SpringItemTouchHelper.this;
                    springItemTouchHelper.mCallback.clearView(springItemTouchHelper.mRecyclerView, viewHolder);
                    SpringItemTouchHelper springItemTouchHelper2 = SpringItemTouchHelper.this;
                    View view = springItemTouchHelper2.mOverdrawChild;
                    View view2 = viewHolder.itemView;
                    if (view == view2) {
                        springItemTouchHelper2.removeChildDrawingOrderCallbackIfNecessary(view2);
                    }
                }
            };
            recoverAnimation.mValueAnimator.setInterpolator(INTERPOLATOR);
            this.mRecoverAnimations.add(recoverAnimation);
            recoverAnimation.start();
            z2 = true;
        } else {
            removeChildDrawingOrderCallbackIfNecessary(viewHolder.itemView);
            this.mCallback.clearView(this.mRecyclerView, viewHolder);
            z2 = false;
        }
        this.mSelected = null;
        ViewParent parent = this.mRecyclerView.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(this.mSelected != null);
        }
        if (!z2) {
            this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
        }
        this.mCallback.onSelectedChanged(this.mSelected, 0);
        this.mRecyclerView.invalidate();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper
    @SuppressLint({"VisibleForTests"})
    public View findChildView(MotionEvent motionEvent) {
        if (this.mSelected != null) {
            return super.findChildView(motionEvent);
        }
        float x2 = motionEvent.getX();
        float y2 = motionEvent.getY();
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            ItemTouchHelper.RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            View view = recoverAnimation.mViewHolder.itemView;
            if (hitTest(view, x2, y2, view.getX() + recoverAnimation.mX, view.getY() + recoverAnimation.mY)) {
                return view;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x2, y2);
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView instanceof miuix.recyclerview.widget.RecyclerView) {
            this.mSpringEnabled = ((miuix.recyclerview.widget.RecyclerView) recyclerView).getSpringEnabled();
            ((miuix.recyclerview.widget.RecyclerView) this.mRecyclerView).setSpringEnabled(false);
        }
    }

    public void onStopDrag(RecyclerView.ViewHolder viewHolder) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView instanceof miuix.recyclerview.widget.RecyclerView) {
            ((miuix.recyclerview.widget.RecyclerView) recyclerView).setSpringEnabled(this.mSpringEnabled);
        }
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper
    public void select(@Nullable RecyclerView.ViewHolder viewHolder, int i2) {
        boolean z2 = false;
        if (i2 == 2) {
            if (viewHolder != null && !Folme.isInDraggingState(this.mRecyclerView)) {
                Folme.setDraggingState(this.mRecyclerView, true);
                onStartDrag(viewHolder);
            }
        } else if (i2 == 0 && Folme.isInDraggingState(this.mRecyclerView)) {
            Folme.setDraggingState(this.mRecyclerView, false);
            RecyclerView.ViewHolder viewHolder2 = this.mSelected;
            if (viewHolder2 != null) {
                onStopDrag(viewHolder2);
                z2 = this.mUseFolmeRecoverAnimation;
            }
        }
        if (z2) {
            super_select_overwrite();
        } else {
            super.select(viewHolder, i2);
        }
    }
}
