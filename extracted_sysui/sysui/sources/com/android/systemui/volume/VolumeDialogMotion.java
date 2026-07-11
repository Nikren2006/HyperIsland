package com.android.systemui.volume;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.ItemTouchHelper;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miuix.appcompat.internal.app.widget.SearchActionModeView;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeDialogMotion {
    private static final float ANIMATION_SCALE = 1.0f;
    private static final int PRE_DISMISS_DELAY = 50;
    private static final String TAG = "VolumeDialogMotion";
    private boolean mAnimating;
    private final Callback mCallback;
    private final View mChevron;
    private ValueAnimator mChevronPositionAnimator;
    private final ViewGroup mContents;
    private ValueAnimator mContentsPositionAnimator;
    private final Dialog mDialog;
    private final View mDialogView;
    private boolean mDismissing;
    private final Handler mHandler = new Handler();
    private boolean mShowing;

    public interface Callback {
        void onAnimatingChanged(boolean z2);
    }

    public static final class LogAccelerateInterpolator implements TimeInterpolator {
        private final int mBase;
        private final int mDrift;
        private final float mLogScale;

        private static float computeLog(float f2, int i2, int i3) {
            return ((float) (-Math.pow(i2, -f2))) + 1.0f + (i3 * f2);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return 1.0f - (computeLog(1.0f - f2, this.mBase, this.mDrift) * this.mLogScale);
        }

        private LogAccelerateInterpolator() {
            this(100, 0);
        }

        private LogAccelerateInterpolator(int i2, int i3) {
            this.mBase = i2;
            this.mDrift = i3;
            this.mLogScale = 1.0f / computeLog(1.0f, i2, i3);
        }
    }

    public static final class LogDecelerateInterpolator implements TimeInterpolator {
        private final float mBase;
        private final float mDrift;
        private final float mOutputScale;
        private final float mTimeScale;

        private float computeLog(float f2) {
            return (1.0f - ((float) Math.pow(this.mBase, (-f2) * this.mTimeScale))) + (this.mDrift * f2);
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            return computeLog(f2) * this.mOutputScale;
        }

        private LogDecelerateInterpolator() {
            this(400.0f, 1.4f, 0.0f);
        }

        private LogDecelerateInterpolator(float f2, float f3, float f4) {
            this.mBase = f2;
            this.mDrift = f4;
            this.mTimeScale = 1.0f / f3;
            this.mOutputScale = 1.0f / computeLog(1.0f);
        }
    }

    public VolumeDialogMotion(Dialog dialog, View view, ViewGroup viewGroup, View view2, Callback callback) {
        this.mDialog = dialog;
        this.mDialogView = view;
        this.mContents = viewGroup;
        this.mChevron = view2;
        this.mCallback = callback;
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener(this) { // from class: com.android.systemui.volume.VolumeDialogMotion.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "mDialog.onDismiss");
                }
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.systemui.volume.VolumeDialogMotion.2
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "mDialog.onShow");
                }
                VolumeDialogMotion.this.mDialogView.setTranslationY(-VolumeDialogMotion.this.mDialogView.getHeight());
                VolumeDialogMotion.this.startShowAnimation();
            }
        });
    }

    private int chevronDistance() {
        return this.mChevron.getHeight() / 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int chevronPosY() {
        View view = this.mChevron;
        Object tag = view == null ? null : view.getTag();
        if (tag == null) {
            return 0;
        }
        return ((Integer) tag).intValue();
    }

    private static int scaledDuration(int i2) {
        return (int) (i2 * 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDismissing(boolean z2) {
        if (z2 == this.mDismissing) {
            return;
        }
        this.mDismissing = z2;
        if (Util.DEBUG) {
            Log.d(TAG, "mDismissing = " + this.mDismissing);
        }
        updateAnimating();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowing(boolean z2) {
        if (z2 == this.mShowing) {
            return;
        }
        this.mShowing = z2;
        if (Util.DEBUG) {
            Log.d(TAG, "mShowing = " + this.mShowing);
        }
        updateAnimating();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startShowAnimation() {
        if (Util.DEBUG) {
            Log.d(TAG, "startShowAnimation");
        }
        this.mDialogView.animate().translationY(0.0f).setDuration(scaledDuration(300)).setInterpolator(new LogDecelerateInterpolator()).setListener(null).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.volume.VolumeDialogMotion.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (VolumeDialogMotion.this.mChevronPositionAnimator == null) {
                    return;
                }
                VolumeDialogMotion.this.mChevron.setTranslationY(VolumeDialogMotion.this.chevronPosY() + ((Float) VolumeDialogMotion.this.mChevronPositionAnimator.getAnimatedValue()).floatValue() + (-VolumeDialogMotion.this.mDialogView.getTranslationY()));
            }
        }).withEndAction(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogMotion.3
            @Override // java.lang.Runnable
            public void run() {
                if (VolumeDialogMotion.this.mChevronPositionAnimator == null) {
                    return;
                }
                VolumeDialogMotion.this.mChevron.setTranslationY(VolumeDialogMotion.this.chevronPosY() + (-VolumeDialogMotion.this.mDialogView.getTranslationY()));
            }
        }).start();
        ValueAnimator duration = ValueAnimator.ofFloat(-chevronDistance(), 0.0f).setDuration(scaledDuration(SearchActionModeView.ANIMATION_DURATION));
        this.mContentsPositionAnimator = duration;
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.volume.VolumeDialogMotion.5
            private boolean mCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "show.onAnimationCancel");
                }
                this.mCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (this.mCancelled) {
                    return;
                }
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "show.onAnimationEnd");
                }
                VolumeDialogMotion.this.setShowing(false);
            }
        });
        this.mContentsPositionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.volume.VolumeDialogMotion.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                VolumeDialogMotion.this.mContents.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue() + (-VolumeDialogMotion.this.mDialogView.getTranslationY()));
            }
        });
        this.mContentsPositionAnimator.setInterpolator(new LogDecelerateInterpolator());
        this.mContentsPositionAnimator.start();
        this.mContents.setAlpha(0.0f);
        this.mContents.animate().alpha(1.0f).setDuration(scaledDuration(SecondaryParamsKt.FROM_BT)).setInterpolator(new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f)).start();
        ValueAnimator duration2 = ValueAnimator.ofFloat(-chevronDistance(), 0.0f).setDuration(scaledDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
        this.mChevronPositionAnimator = duration2;
        duration2.setInterpolator(new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f));
        this.mChevronPositionAnimator.start();
        this.mChevron.setAlpha(0.0f);
        this.mChevron.animate().alpha(1.0f).setStartDelay(scaledDuration(50)).setDuration(scaledDuration(SecondaryParamsKt.FROM_BT)).setInterpolator(new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f)).start();
    }

    private void updateAnimating() {
        boolean z2 = this.mShowing || this.mDismissing;
        if (z2 == this.mAnimating) {
            return;
        }
        this.mAnimating = z2;
        if (Util.DEBUG) {
            Log.d(TAG, "mAnimating = " + this.mAnimating);
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onAnimatingChanged(this.mAnimating);
        }
    }

    public boolean isAnimating() {
        return this.mAnimating;
    }

    public void startDismiss(final Runnable runnable) {
        if (Util.DEBUG) {
            Log.d(TAG, "startDismiss");
        }
        if (this.mDismissing) {
            return;
        }
        setDismissing(true);
        if (this.mShowing) {
            this.mDialogView.animate().cancel();
            ValueAnimator valueAnimator = this.mContentsPositionAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            this.mContents.animate().cancel();
            ValueAnimator valueAnimator2 = this.mChevronPositionAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            this.mChevron.animate().cancel();
            setShowing(false);
        }
        this.mDialogView.animate().translationY(-this.mDialogView.getHeight()).setDuration(scaledDuration(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)).setInterpolator(new LogAccelerateInterpolator()).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.volume.VolumeDialogMotion.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                VolumeDialogMotion.this.mContents.setTranslationY(-VolumeDialogMotion.this.mDialogView.getTranslationY());
                VolumeDialogMotion.this.mChevron.setTranslationY(VolumeDialogMotion.this.chevronPosY() + (-VolumeDialogMotion.this.mDialogView.getTranslationY()));
            }
        }).setListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.volume.VolumeDialogMotion.7
            private boolean mCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "dismiss.onAnimationCancel");
                }
                this.mCancelled = true;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (this.mCancelled) {
                    return;
                }
                if (Util.DEBUG) {
                    Log.d(VolumeDialogMotion.TAG, "dismiss.onAnimationEnd");
                }
                VolumeDialogMotion.this.mHandler.postDelayed(new Runnable() { // from class: com.android.systemui.volume.VolumeDialogMotion.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Util.DEBUG) {
                            Log.d(VolumeDialogMotion.TAG, "mDialog.dismiss()");
                        }
                        VolumeDialogMotion.this.mDialog.dismiss();
                        runnable.run();
                        VolumeDialogMotion.this.setDismissing(false);
                    }
                }, 50L);
            }
        }).start();
    }

    public void startShow() {
        if (Util.DEBUG) {
            Log.d(TAG, "startShow");
        }
        if (this.mShowing) {
            return;
        }
        setShowing(true);
        if (this.mDismissing) {
            this.mDialogView.animate().cancel();
            setDismissing(false);
            startShowAnimation();
        } else {
            if (Util.DEBUG) {
                Log.d(TAG, "mDialog.show()");
            }
            this.mDialog.show();
        }
    }
}
