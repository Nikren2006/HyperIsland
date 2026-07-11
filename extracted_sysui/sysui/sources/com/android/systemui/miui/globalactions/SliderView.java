package com.android.systemui.miui.globalactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miui.systemui.util.BlurUtils;
import miuix.view.animation.CubicEaseInOutInterpolator;
import miuix.view.animation.CubicEaseOutInterpolator;
import miuix.view.animation.ExponentialEaseOutInterpolator;
import miuix.view.animation.QuadraticEaseOutInterpolator;
import miuix.view.animation.SineEaseInOutInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public class SliderView extends FrameLayout {
    public static final int CLOSE_ANIMATION_TIME = 200;
    private static final int DRAG_BUTTON_MODE_NORMAL = 0;
    private static final int DRAG_BUTTON_MODE_REBOOT = -1;
    private static final int DRAG_BUTTON_MODE_SHUTDOWN = 1;
    public static final int OPEN_ANIMATION_TIME = 300;
    private static final String TAG = "SliderView";
    private static final int UP_ACTION_ANIMATION_TIME = 300;
    private BlurUtils mBlurUtils;
    private Callback mCallback;
    private int mCenterY;
    private Command mCloseCommand;
    private Context mContext;
    private FrameLayout mDark;
    private boolean mDontAnimate;
    private DragButtonElement mDragButtonElement;
    private int mDragButtonMode;
    private View.OnTouchListener mDragButtonTouchListener;
    private float mDragTar;
    private List<BaseElement> mElements;
    private H mH;
    private Paint mHighLightPaint;
    private float mInitialTouchY;
    private boolean mIsAnimating;
    private boolean mIsUp;
    private int[] mLocation;
    private int mMoveIconHeight;
    private int mMoveIconWidth;
    private Command mOpenCommand;
    private PointGroupElement mPointGroupElementDown;
    private PointGroupElement mPointGroupElementUp;
    private ImageButtonElement mRebootImageButtonElement;
    private TextElement mRebootTextElement;
    private float mRootAlpha;
    private boolean mShutDownPasswordEnabled;
    private Command mShutdownActionLaterCommand;
    private ImageButtonElement mShutdownImageButtonElement;
    private TextElement mShutdownTextElement;
    private float mSliderAlpha;
    private float mSliderGradientBackgroudBottom;
    private float mSliderGradientBackgroudColor;
    private float mSliderGradientBackgroudTop;
    private int mSliderHeight;
    private int mSliderTopOnScreen;
    private int mSliderWidth;
    private Command mUpNormalCommand;
    private Command mUpShutCommand;
    private Window mWindow;
    private static final Interpolator CUBIC_EASE_IN_OUT = new CubicEaseInOutInterpolator();
    private static final Interpolator CUBIC_EASE_OUT = new CubicEaseOutInterpolator();
    public static final Interpolator QUART_EASE_OUT = new QuadraticEaseOutInterpolator();
    private static final Interpolator SINE_EASE_IN_OUT = new SineEaseInOutInterpolator();
    private static final Interpolator EXPO_EASE_OUT = new ExponentialEaseOutInterpolator();

    public abstract class BaseElement {
        protected float mAlpha = 1.0f;
        public int mHeight;
        public int mLeft;
        public int mTop;
        protected View mView;
        public int mWidth;

        public BaseElement(View view, int i2, int i3, int i4, int i5) {
            this.mView = view;
            this.mWidth = i2;
            this.mHeight = i3;
            this.mLeft = i4;
            this.mTop = i5;
        }

        public abstract void closeAction();

        public abstract View getView();

        public void layout() {
            View view = this.mView;
            int i2 = this.mLeft;
            int i3 = this.mTop;
            view.layout(i2, i3, this.mWidth + i2, this.mHeight + i3);
        }

        public void measure() {
            if (!(this instanceof TextElement)) {
                this.mView.measure(View.MeasureSpec.makeMeasureSpec(this.mWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mHeight, BasicMeasure.EXACTLY));
                return;
            }
            this.mView.measure(View.MeasureSpec.makeMeasureSpec(-2, 0), View.MeasureSpec.makeMeasureSpec(-2, 0));
            TextElement textElement = (TextElement) this;
            if (textElement.isMeasuredHeight) {
                return;
            }
            int measuredHeight = this.mView.getMeasuredHeight();
            this.mHeight = measuredHeight;
            if (textElement.adjustTop) {
                this.mTop -= measuredHeight;
            }
            textElement.isMeasuredHeight = true;
        }

        public abstract void openAction();

        public void startAlphaAnimation(View view, float f2, float f3, int i2, Interpolator interpolator) {
            ObjectAnimator objectAnimatorCreateAlphaAnimator = SliderView.this.createAlphaAnimator(f2, f3, view);
            objectAnimatorCreateAlphaAnimator.setDuration(i2);
            objectAnimatorCreateAlphaAnimator.setInterpolator(interpolator);
            objectAnimatorCreateAlphaAnimator.start();
        }

        public void startScaleAmination(final View view, float f2, float f3, int i2, Interpolator interpolator) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f3);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.systemui.miui.globalactions.SliderView.BaseElement.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    view.setScaleX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    view.setScaleY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            valueAnimatorOfFloat.setInterpolator(interpolator);
            valueAnimatorOfFloat.setDuration(i2);
            valueAnimatorOfFloat.start();
        }

        public void upNormalAction() {
        }

        public void upShutdownAction() {
        }

        public void upShutdownActionLater() {
        }
    }

    public interface Callback {
        public static final int ACTION_DOWN = 0;
        public static final int ACTION_UP = 1;

        void action(int i2, boolean z2);

        void onConfigChanged(Configuration configuration);

        void sliderViewDismiss();

        void textViewAlphaAnimator(boolean z2);
    }

    public class CloseCommand implements Command {
        private void sliderCloseAnimation() {
            SliderView sliderView = SliderView.this;
            ValueAnimator valueAnimatorCreateSliderAlphaAnimator = sliderView.createSliderAlphaAnimator((int) (sliderView.mSliderAlpha * 255.0f), 0, 200, SliderView.EXPO_EASE_OUT);
            SliderView sliderView2 = SliderView.this;
            ValueAnimator valueAnimatorCreateSliderHeightAnimator = sliderView2.createSliderHeightAnimator(sliderView2.mSliderHeight, SliderView.this.mSliderWidth, 200, SliderView.SINE_EASE_IN_OUT);
            SliderView sliderView3 = SliderView.this;
            ObjectAnimator objectAnimatorCreateAlphaAnimator = sliderView3.createAlphaAnimator(sliderView3.mRootAlpha, 0.0f, SliderView.this.mDark);
            objectAnimatorCreateAlphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.CloseCommand.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SliderView.this.mPointGroupElementUp.getView().clear();
                    SliderView.this.mPointGroupElementDown.getView().clear();
                    SliderView.this.mCallback.sliderViewDismiss();
                }
            });
            objectAnimatorCreateAlphaAnimator.setDuration(200L);
            objectAnimatorCreateAlphaAnimator.setInterpolator(SliderView.EXPO_EASE_OUT);
            AnimatorSet animatorSet = new AnimatorSet();
            if (SliderView.this.mShutDownPasswordEnabled) {
                animatorSet.playTogether(objectAnimatorCreateAlphaAnimator, valueAnimatorCreateSliderHeightAnimator);
            } else {
                animatorSet.playTogether(valueAnimatorCreateSliderAlphaAnimator, objectAnimatorCreateAlphaAnimator, valueAnimatorCreateSliderHeightAnimator);
            }
            animatorSet.start();
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.Command
        public void execute() {
            if (!SliderView.this.mShutDownPasswordEnabled) {
                Iterator it = SliderView.this.mElements.iterator();
                while (it.hasNext()) {
                    ((BaseElement) it.next()).closeAction();
                }
            }
            sliderCloseAnimation();
            SliderView.this.startBlurAnim(1.0f, 0.0f, 200);
        }

        private CloseCommand() {
        }
    }

    public interface Command {
        void execute();
    }

    public class DragButtonElement extends BaseElement {
        private float mDragButtonScale;
        private ValueAnimator mReboundAnimator;
        private ValueAnimator mTopAndButtonAnimator;

        public DragButtonElement(DragButton dragButton, int i2, int i3, int i4, int i5) {
            super(dragButton, i2, i3, i4, i5);
            this.mDragButtonScale = 1.0f;
        }

        private ValueAnimator createScaleAnimator(float f2, float f3, int i2, Interpolator interpolator) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f3);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.DragButtonElement.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DragButtonElement.this.mDragButtonScale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    DragButtonElement dragButtonElement = DragButtonElement.this;
                    dragButtonElement.mView.setScaleX(dragButtonElement.mDragButtonScale);
                    DragButtonElement dragButtonElement2 = DragButtonElement.this;
                    dragButtonElement2.mView.setScaleY(dragButtonElement2.mDragButtonScale);
                }
            });
            valueAnimatorOfFloat.setDuration(i2);
            valueAnimatorOfFloat.setInterpolator(interpolator);
            return valueAnimatorOfFloat;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startDragButtonAnimate() {
            createScaleAnimator(1.0f, SliderView.this.mSliderWidth / this.mWidth, 100, SliderView.CUBIC_EASE_IN_OUT).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startReboundAnimation() {
            ValueAnimator valueAnimatorCreateScaleAnimator = createScaleAnimator(1.276f, 1.16f, 300, SliderView.QUART_EASE_OUT);
            this.mReboundAnimator = valueAnimatorCreateScaleAnimator;
            if (valueAnimatorCreateScaleAnimator.isRunning()) {
                return;
            }
            this.mReboundAnimator.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void startTopAndBottomAnimation() {
            ValueAnimator valueAnimatorCreateScaleAnimator = createScaleAnimator(1.16f, 1.276f, 100, SliderView.CUBIC_EASE_OUT);
            this.mTopAndButtonAnimator = valueAnimatorCreateScaleAnimator;
            valueAnimatorCreateScaleAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.DragButtonElement.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    DragButtonElement.this.startReboundAnimation();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    SliderView.this.mDontAnimate = true;
                    if (SliderView.this.mDragButtonMode == -1) {
                        DragButtonElement.this.getView().changeImage(R.drawable.ic_reboot, SliderView.this.mRebootImageButtonElement.mWidth, SliderView.this.mRebootImageButtonElement.mHeight);
                        DragButtonElement.this.mView.setBackgroundResource(R.drawable.ic_mask_reboot);
                    } else if (SliderView.this.mDragButtonMode == 1) {
                        DragButtonElement.this.getView().changeImage(R.drawable.ic_shutdown, SliderView.this.mRebootImageButtonElement.mWidth, SliderView.this.mRebootImageButtonElement.mHeight);
                        DragButtonElement.this.mView.setBackgroundResource(R.drawable.ic_mask_shutdown);
                    }
                    DragButtonElement dragButtonElement = DragButtonElement.this;
                    View view = dragButtonElement.mView;
                    int i2 = dragButtonElement.mLeft;
                    int i3 = dragButtonElement.mTop;
                    int right = view.getRight();
                    DragButtonElement dragButtonElement2 = DragButtonElement.this;
                    view.layout(i2, i3, right, dragButtonElement2.mTop + dragButtonElement2.mHeight + 1);
                }
            });
            if (this.mTopAndButtonAnimator.isRunning()) {
                return;
            }
            ValueAnimator valueAnimator = this.mReboundAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                this.mTopAndButtonAnimator.start();
            }
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void closeAction() {
            startScaleAmination(this.mView, 1.0f, 0.23f, 132, SliderView.SINE_EASE_IN_OUT);
            startAlphaAnimation(this.mView, 1.0f, 0.0f, 132, SliderView.SINE_EASE_IN_OUT);
            this.mDragButtonScale = 0.23f;
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void openAction() {
            startScaleAmination(this.mView, 0.23f, 1.0f, 200, SliderView.EXPO_EASE_OUT);
            startAlphaAnimation(this.mView, 0.0f, 1.0f, 200, SliderView.EXPO_EASE_OUT);
            this.mDragButtonScale = 1.0f;
            this.mView.setVisibility(0);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upNormalAction() {
            this.mView.setBackgroundResource(R.drawable.ic_mask);
            getView().changeImage(R.drawable.ic_moveicon, SliderView.this.mMoveIconWidth, SliderView.this.mMoveIconHeight);
            float f2 = this.mDragButtonScale;
            Interpolator interpolator = SliderView.QUART_EASE_OUT;
            ValueAnimator valueAnimatorCreateScaleAnimator = createScaleAnimator(f2, 1.0f, 200, interpolator);
            ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mTop, (SliderView.this.mSliderHeight - this.mHeight) / 2);
            valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.DragButtonElement.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DragButtonElement.this.mTop = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    DragButtonElement dragButtonElement = DragButtonElement.this;
                    View view = dragButtonElement.mView;
                    int i2 = dragButtonElement.mLeft;
                    int i3 = dragButtonElement.mTop;
                    int right = view.getRight();
                    DragButtonElement dragButtonElement2 = DragButtonElement.this;
                    view.layout(i2, i3, right, dragButtonElement2.mTop + dragButtonElement2.mHeight);
                }
            });
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(valueAnimatorCreateScaleAnimator, valueAnimatorOfInt);
            animatorSet.setDuration(200L);
            animatorSet.setInterpolator(interpolator);
            animatorSet.start();
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upShutdownActionLater() {
            ObjectAnimator objectAnimatorCreateAlphaAnimator = SliderView.this.createAlphaAnimator(1.0f, 0.0f, this.mView);
            objectAnimatorCreateAlphaAnimator.setDuration(600L);
            objectAnimatorCreateAlphaAnimator.setInterpolator(SliderView.QUART_EASE_OUT);
            objectAnimatorCreateAlphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.DragButtonElement.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (SliderView.this.mShutDownPasswordEnabled) {
                        SliderView.this.mIsAnimating = false;
                    }
                    Log.d(SliderView.TAG, " mShutDownPasswordEnabled = " + SliderView.this.mShutDownPasswordEnabled);
                    if (SliderView.this.mDragButtonMode == -1) {
                        SliderView.this.mCallback.action(1, SliderView.this.mShutDownPasswordEnabled);
                    } else {
                        SliderView.this.mCallback.action(0, SliderView.this.mShutDownPasswordEnabled);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    SliderView.this.mIsAnimating = true;
                }
            });
            objectAnimatorCreateAlphaAnimator.start();
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public DragButton getView() {
            return (DragButton) this.mView;
        }
    }

    public static class H extends Handler {
        private static final int CLOSE_COMMAND = 1;
        private static final int MOVE_TOP_OR_BOTTOM_MSG = 5;
        private static final int OPEN_COMMAND = 0;
        private static final int UP_NORMAL_COMMAND = 3;
        private static final int UP_SHUTDOWN_COMMAND = 2;
        private static final int UP_SHUTDOWN_LATER_COMMAND = 4;
        private final WeakReference<SliderView> mSliderView;

        public H(SliderView sliderView) {
            this.mSliderView = new WeakReference<>(sliderView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            if (i2 != 4) {
                                if (i2 == 5 && this.mSliderView.get() != null) {
                                    this.mSliderView.get().mDragButtonElement.startTopAndBottomAnimation();
                                }
                            } else if (this.mSliderView.get() != null) {
                                this.mSliderView.get().mShutdownActionLaterCommand.execute();
                            }
                        } else if (this.mSliderView.get() != null) {
                            this.mSliderView.get().mUpNormalCommand.execute();
                        }
                    } else if (this.mSliderView.get() != null) {
                        this.mSliderView.get().mUpShutCommand.execute();
                    }
                } else if (this.mSliderView.get() != null) {
                    this.mSliderView.get().mCloseCommand.execute();
                }
            } else if (this.mSliderView.get() != null) {
                this.mSliderView.get().mOpenCommand.execute();
            }
            super.handleMessage(message);
        }
    }

    public class ImageButtonElement extends BaseElement {
        public ImageButtonElement(SliderView sliderView, ImageView imageView, int i2, int i3, int i4, int i5) {
            super(imageView, i2, i3, i4, i5);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void closeAction() {
            startAlphaAnimation(this.mView, 1.0f, 0.0f, 100, SliderView.EXPO_EASE_OUT);
            startScaleAmination(this.mView, 1.0f, 0.7f, 100, SliderView.SINE_EASE_IN_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void openAction() {
            startAlphaAnimation(this.mView, 0.0f, 1.0f, 300, SliderView.SINE_EASE_IN_OUT);
            startScaleAmination(this.mView, 0.23f, 1.0f, 200, SliderView.SINE_EASE_IN_OUT);
            this.mView.setVisibility(0);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upNormalAction() {
            startAlphaAnimation(this.mView, this.mAlpha, 1.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upShutdownActionLater() {
            startAlphaAnimation(this.mView, this.mAlpha, 0.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public ImageView getView() {
            return (ImageView) this.mView;
        }
    }

    public class OpenCommand implements Command {
        private void sliderOpenAnimation() {
            ValueAnimator valueAnimatorCreateSliderAlphaAnimator = SliderView.this.createSliderAlphaAnimator(0, 255, 300, SliderView.EXPO_EASE_OUT);
            SliderView sliderView = SliderView.this;
            ValueAnimator valueAnimatorCreateSliderHeightAnimator = sliderView.createSliderHeightAnimator(sliderView.mSliderWidth, SliderView.this.mSliderHeight, 300, SliderView.EXPO_EASE_OUT);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(valueAnimatorCreateSliderAlphaAnimator, valueAnimatorCreateSliderHeightAnimator);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.OpenCommand.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SliderView.this.mPointGroupElementUp.getView().startLoop();
                    SliderView.this.mPointGroupElementDown.getView().startLoop();
                }
            });
            animatorSet.start();
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.Command
        public void execute() {
            Iterator it = SliderView.this.mElements.iterator();
            while (it.hasNext()) {
                ((BaseElement) it.next()).openAction();
            }
            sliderOpenAnimation();
            SliderView.this.startBlurAnim(0.0f, 1.0f, 300);
        }

        private OpenCommand() {
        }
    }

    public class PointGroupElement extends BaseElement {
        public PointGroupElement(SliderView sliderView, PointGroup pointGroup, int i2, int i3, int i4, int i5) {
            super(pointGroup, i2, i3, i4, i5);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void closeAction() {
            startAlphaAnimation(this.mView, 1.0f, 0.0f, 100, SliderView.EXPO_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void openAction() {
            startAlphaAnimation(this.mView, 0.0f, 1.0f, 300, SliderView.SINE_EASE_IN_OUT);
            this.mView.setVisibility(0);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upNormalAction() {
            startAlphaAnimation(this.mView, this.mAlpha, 1.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upShutdownAction() {
            startAlphaAnimation(this.mView, this.mAlpha, 0.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public PointGroup getView() {
            return (PointGroup) this.mView;
        }
    }

    public class ShutdownActionLaterCommand implements Command {
        @Override // com.android.systemui.miui.globalactions.SliderView.Command
        public void execute() {
            SliderView.this.mCallback.textViewAlphaAnimator(false);
            Iterator it = SliderView.this.mElements.iterator();
            while (it.hasNext()) {
                ((BaseElement) it.next()).upShutdownActionLater();
            }
        }

        private ShutdownActionLaterCommand() {
        }
    }

    public class TextElement extends BaseElement {
        public boolean adjustTop;
        public boolean isMeasuredHeight;

        public TextElement(SliderView sliderView, View view, int i2, int i3, int i4, int i5) {
            super(view, i2, i3, i4, i5);
            this.adjustTop = false;
            this.isMeasuredHeight = false;
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void closeAction() {
            startAlphaAnimation(this.mView, 1.0f, 0.0f, 100, SliderView.EXPO_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void openAction() {
            startAlphaAnimation(this.mView, 0.0f, 1.0f, 300, SliderView.SINE_EASE_IN_OUT);
            this.mView.setVisibility(0);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upNormalAction() {
            startAlphaAnimation(this.mView, this.mAlpha, 1.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public void upShutdownActionLater() {
            startAlphaAnimation(this.mView, this.mAlpha, 0.0f, 300, SliderView.QUART_EASE_OUT);
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.BaseElement
        public TextView getView() {
            return (TextView) this.mView;
        }
    }

    public class UpNormalCommand implements Command {
        @Override // com.android.systemui.miui.globalactions.SliderView.Command
        public void execute() {
            Iterator it = SliderView.this.mElements.iterator();
            while (it.hasNext()) {
                ((BaseElement) it.next()).upNormalAction();
            }
            SliderView sliderView = SliderView.this;
            ObjectAnimator objectAnimatorCreateAlphaAnimator = sliderView.createAlphaAnimator(sliderView.mRootAlpha, 0.0f, SliderView.this.mDark);
            objectAnimatorCreateAlphaAnimator.setDuration(300L);
            objectAnimatorCreateAlphaAnimator.setInterpolator(SliderView.QUART_EASE_OUT);
            objectAnimatorCreateAlphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.UpNormalCommand.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SliderView.this.mIsAnimating = false;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    SliderView.this.mIsAnimating = true;
                }
            });
            objectAnimatorCreateAlphaAnimator.start();
            SliderView.this.getBackground().setAlpha(255);
        }

        private UpNormalCommand() {
        }
    }

    public class UpShutCommand implements Command {
        private void startShutDownAnimation() {
            SliderView sliderView = SliderView.this;
            ValueAnimator valueAnimatorCreateSliderAlphaAnimator = sliderView.createSliderAlphaAnimator((int) (Math.max(sliderView.mSliderAlpha, 0.0f) * 255.0f), 0, 300, SliderView.SINE_EASE_IN_OUT);
            SliderView sliderView2 = SliderView.this;
            ObjectAnimator objectAnimatorCreateAlphaAnimator = sliderView2.createAlphaAnimator(sliderView2.mRootAlpha, 1.0f, SliderView.this.mDark);
            objectAnimatorCreateAlphaAnimator.setInterpolator(SliderView.SINE_EASE_IN_OUT);
            objectAnimatorCreateAlphaAnimator.setDuration(600L);
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(SliderView.this.mSliderGradientBackgroudColor, 0.0f);
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.UpShutCommand.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SliderView.this.mSliderGradientBackgroudColor = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                }
            });
            valueAnimatorOfFloat.setInterpolator(SliderView.QUART_EASE_OUT);
            valueAnimatorOfFloat.setDuration(300L);
            AnimatorSet animatorSet = new AnimatorSet();
            if (SliderView.this.mShutDownPasswordEnabled) {
                animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorCreateSliderAlphaAnimator);
            } else {
                animatorSet.playTogether(objectAnimatorCreateAlphaAnimator, valueAnimatorOfFloat, valueAnimatorCreateSliderAlphaAnimator);
            }
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.miui.globalactions.SliderView.UpShutCommand.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SliderView.this.mH.sendEmptyMessage(4);
                    SliderView.this.mIsAnimating = false;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    SliderView.this.mIsAnimating = true;
                }
            });
            animatorSet.start();
        }

        @Override // com.android.systemui.miui.globalactions.SliderView.Command
        public void execute() {
            SliderView.this.mPointGroupElementUp.upShutdownAction();
            SliderView.this.mPointGroupElementDown.upShutdownAction();
            startShutDownAnimation();
        }

        private UpShutCommand() {
        }
    }

    public SliderView(@NonNull Context context, Callback callback, FrameLayout frameLayout) {
        this(context, null);
        this.mCallback = callback;
        this.mDark = frameLayout;
        setForceDarkAllowed(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ObjectAnimator createAlphaAnimator(float f2, float f3, View view) {
        return ObjectAnimator.ofFloat(view, "alpha", f2, f3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator createSliderAlphaAnimator(int i2, int i3, int i4, Interpolator interpolator) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(i2, i3);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SliderView.this.getBackground().setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        valueAnimatorOfInt.setInterpolator(interpolator);
        valueAnimatorOfInt.setDuration(i4);
        return valueAnimatorOfInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ValueAnimator createSliderHeightAnimator(int i2, int i3, int i4, Interpolator interpolator) {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(i2, i3);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SliderView.this.mSliderHeight = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SliderView.this.mDragButtonElement.mTop = (SliderView.this.mSliderHeight - SliderView.this.mDragButtonElement.mHeight) / 2;
                SliderView sliderView = SliderView.this;
                sliderView.measure(View.MeasureSpec.makeMeasureSpec(sliderView.mSliderWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(SliderView.this.mSliderHeight, BasicMeasure.EXACTLY));
                if (!BlurUtils.isFlipTinyScreen(SliderView.this.mContext) || !Util.showEmergencyCall()) {
                    SliderView sliderView2 = SliderView.this;
                    sliderView2.layout(sliderView2.getLeft(), (SliderView.this.mDark.getHeight() / 2) - (SliderView.this.mSliderHeight / 2), SliderView.this.getRight(), (SliderView.this.mDark.getHeight() / 2) + (SliderView.this.mSliderHeight / 2));
                } else {
                    int dimension = ((int) SliderView.this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.emergency_call_text_margin_slider))) + ((int) SliderView.this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.emergency_call_height)));
                    SliderView sliderView3 = SliderView.this;
                    sliderView3.layout(sliderView3.getLeft(), ((SliderView.this.mDark.getHeight() - dimension) / 2) - (SliderView.this.mSliderHeight / 2), SliderView.this.getRight(), ((SliderView.this.mDark.getHeight() - dimension) / 2) + (SliderView.this.mSliderHeight / 2));
                }
            }
        });
        valueAnimatorOfInt.setDuration(i4);
        valueAnimatorOfInt.setInterpolator(interpolator);
        return valueAnimatorOfInt;
    }

    private TextElement createTextElement(String str, int i2, boolean z2) {
        TextView textView = new TextView(this.mContext);
        textView.setText(str);
        textView.setTextColor(this.mContext.getResources().getColor(R.color.shutdown_text_color));
        textView.setGravity(17);
        textView.setAlpha(0.0f);
        textView.setFallbackLineSpacing(true);
        textView.setTypeface(Typeface.create("mipro-medium", 0));
        Paint.FontMetrics fontMetrics = new TextPaint(textView.getPaint()).getFontMetrics();
        int iIntValue = new Double(Math.ceil(r0.measureText(str))).intValue();
        TextElement textElement = new TextElement(this, textView, iIntValue, new Double(Math.ceil(fontMetrics.bottom - fontMetrics.top)).intValue(), (this.mSliderWidth - iIntValue) / 2, i2);
        textElement.adjustTop = z2;
        addView(textView, new FrameLayout.LayoutParams(-2, -2));
        return textElement;
    }

    private void handleActionMoveForAlpha(float f2) {
        PointGroupElement pointGroupElement = this.mPointGroupElementUp;
        PointGroupElement pointGroupElement2 = this.mPointGroupElementDown;
        float fAbs = 1.0f - Math.abs(3.0f * f2);
        pointGroupElement2.mAlpha = fAbs;
        pointGroupElement.mAlpha = fAbs;
        TextElement textElement = this.mRebootTextElement;
        ImageButtonElement imageButtonElement = this.mRebootImageButtonElement;
        float f3 = 1.2f * f2;
        float fMin = Math.min(f3 + 1.0f, 1.0f);
        imageButtonElement.mAlpha = fMin;
        textElement.mAlpha = fMin;
        TextElement textElement2 = this.mShutdownTextElement;
        ImageButtonElement imageButtonElement2 = this.mShutdownImageButtonElement;
        float fMin2 = Math.min(1.0f - f3, 1.0f);
        imageButtonElement2.mAlpha = fMin2;
        textElement2.mAlpha = fMin2;
        for (BaseElement baseElement : this.mElements) {
            baseElement.getView().setAlpha(baseElement.mAlpha);
        }
        this.mSliderAlpha += ((1.0f - (Math.abs(f2) * 0.9f)) - this.mSliderAlpha) * 0.3f;
        getBackground().setAlpha((int) (Math.max(this.mSliderAlpha, 0.0f) * 255.0f));
        float fAbs2 = (float) (((double) this.mRootAlpha) + (((double) (Math.abs(f2) - this.mRootAlpha)) * 0.7d));
        this.mRootAlpha = fAbs2;
        this.mDark.setAlpha(fAbs2);
    }

    private void initDragElement() {
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.mask_size);
        int i2 = (this.mSliderWidth - dimension) / 2;
        int i3 = (this.mSliderHeight - dimension) / 2;
        DragButton dragButton = new DragButton(this.mContext);
        this.mDragButtonElement = new DragButtonElement(dragButton, dimension, dimension, i2, i3);
        dragButton.setBackgroundResource(R.drawable.ic_mask);
        dragButton.setOnTouchListener(this.mDragButtonTouchListener);
        dragButton.setVisibility(4);
        this.mElements.add(this.mDragButtonElement);
        addView(dragButton);
    }

    private void initElements() {
        initImageElement();
        initPointGroupElement();
        initDragElement();
        initTextElement();
    }

    private void initImageElement() {
        int dimension = (int) this.mContext.getResources().getDimension(R.dimen.reboot_shutdown_icon_size_small);
        int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.reboot_icon_margin_top);
        int i2 = (this.mSliderWidth - dimension) / 2;
        int i3 = (this.mSliderHeight - dimension) - dimension2;
        ImageView imageView = new ImageView(this.mContext);
        imageView.setImageResource(R.drawable.ic_shutdown_small);
        imageView.setVisibility(4);
        ImageView imageView2 = new ImageView(this.mContext);
        imageView2.setImageResource(R.drawable.ic_reboot_small);
        imageView2.setVisibility(4);
        this.mRebootImageButtonElement = new ImageButtonElement(this, imageView2, dimension, dimension, i2, i3);
        this.mShutdownImageButtonElement = new ImageButtonElement(this, imageView, dimension, dimension, i2, dimension2);
        this.mElements.add(this.mRebootImageButtonElement);
        this.mElements.add(this.mShutdownImageButtonElement);
        addView(imageView);
        addView(imageView2);
    }

    private void initPointGroupElement() {
        int dimension = (int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.point_margin_top));
        int dimension2 = (int) this.mContext.getResources().getDimension(R.dimen.point_size);
        int i2 = (this.mSliderWidth - dimension2) / 2;
        int dimension3 = (dimension2 * 3) + ((int) (this.mContext.getResources().getDimension(R.dimen.point_interval) * 2.0f));
        int i3 = (this.mSliderHeight - dimension3) - dimension;
        PointGroup pointGroup = new PointGroup(this.mContext, 0);
        PointGroup pointGroup2 = new PointGroup(this.mContext, 1);
        pointGroup.setVisibility(4);
        pointGroup2.setVisibility(4);
        this.mPointGroupElementUp = new PointGroupElement(this, pointGroup, dimension2, dimension3, i2, dimension);
        this.mPointGroupElementDown = new PointGroupElement(this, pointGroup2, dimension2, dimension3, i2, i3);
        this.mElements.add(this.mPointGroupElementUp);
        this.mElements.add(this.mPointGroupElementDown);
        addView(pointGroup);
        addView(pointGroup2);
    }

    private void initTextElement() {
        Resources resources = this.mContext.getResources();
        int i2 = R.dimen.reboot_shutdown_text_margin;
        TextElement textElementCreateTextElement = createTextElement(this.mContext.getResources().getString(R.string.power_off), -((int) resources.getDimension(Util.checkFlipDimen(i2))), true);
        this.mShutdownTextElement = textElementCreateTextElement;
        this.mElements.add(textElementCreateTextElement);
        TextElement textElementCreateTextElement2 = createTextElement(this.mContext.getResources().getString(R.string.restart), this.mSliderHeight + ((int) this.mContext.getResources().getDimension(Util.checkFlipDimen(i2))), false);
        this.mRebootTextElement = textElementCreateTextElement2;
        this.mElements.add(textElementCreateTextElement2);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mShutDownPasswordEnabled = ShutDownPasswordUtils.isShutDownPasswordEnabled(context);
        Log.d(TAG, " initView mShutDownPasswordEnabled = " + this.mShutDownPasswordEnabled);
        this.mSliderWidth = (int) this.mContext.getResources().getDimension(R.dimen.slider_width);
        this.mSliderHeight = (int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_height));
        setVisibility(4);
        this.mMoveIconWidth = (int) this.mContext.getResources().getDimension(R.dimen.moveicon_width);
        this.mMoveIconHeight = (int) this.mContext.getResources().getDimension(R.dimen.moveicon_height);
        initElements();
        this.mOpenCommand = new OpenCommand();
        this.mCloseCommand = new CloseCommand();
        this.mUpNormalCommand = new UpNormalCommand();
        this.mUpShutCommand = new UpShutCommand();
        this.mShutdownActionLaterCommand = new ShutdownActionLaterCommand();
        setContentDescription(this.mContext.getResources().getString(R.string.shutdown_menu));
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.android.systemui.miui.globalactions.SliderView.5
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
                accessibilityNodeInfoCompat.setHintText(SliderView.this.mContext.getResources().getString(R.string.slider_view_content_description));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBlurAnim(float f2, float f3, int i2) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f3);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.miui.globalactions.SliderView.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    Float f4 = (Float) valueAnimator.getAnimatedValue();
                    f4.floatValue();
                    SliderView.this.mBlurUtils.setBackgroundBlur(SliderView.this.mDark.getRootView(), f4, SliderView.this.mWindow);
                    ((FrameLayout.LayoutParams) SliderView.this.getLayoutParams()).height = SliderView.this.mSliderHeight;
                } catch (Exception e2) {
                    Log.e(SliderView.TAG, "updateBlurRatio error.", e2);
                }
            }
        });
        valueAnimatorOfFloat.setDuration(i2);
        valueAnimatorOfFloat.setInterpolator(QUART_EASE_OUT);
        valueAnimatorOfFloat.start();
    }

    public void dismiss() {
        this.mH.sendEmptyMessage(1);
    }

    public void dismissWithoutAnimation() {
        Iterator<BaseElement> it = this.mElements.iterator();
        while (it.hasNext()) {
            it.next().getView().setAlpha(0.0f);
        }
        this.mPointGroupElementUp.getView().clear();
        this.mPointGroupElementDown.getView().clear();
        startBlurAnim(1.0f, 0.0f, 0);
        this.mCallback.sliderViewDismiss();
    }

    public void handleActionDown(float f2, boolean z2) {
        if (this.mIsAnimating) {
            return;
        }
        if (z2) {
            f2 -= this.mDragButtonElement.mView.getTop();
        }
        this.mDragButtonElement.startDragButtonAnimate();
        this.mInitialTouchY = f2;
        this.mDragButtonMode = 0;
        this.mDontAnimate = false;
        this.mDragTar = 0.0f;
        this.mRootAlpha = 0.0f;
        this.mRebootImageButtonElement.mAlpha = 1.0f;
        this.mShutdownImageButtonElement.mAlpha = 1.0f;
        this.mRebootTextElement.mAlpha = 1.0f;
        this.mShutdownTextElement.mAlpha = 1.0f;
    }

    public void handleActionMove(float f2, float f3, boolean z2) {
        int i2;
        if (this.mIsAnimating) {
            return;
        }
        if (z2) {
            f2 -= this.mDragButtonElement.mView.getTop();
        }
        int i3 = this.mCenterY;
        float f4 = (f3 - i3) / (this.mSliderHeight / 2);
        float f5 = this.mDragTar;
        this.mDragTar = f5 + (((f3 - i3) - f5) * 0.1f);
        int top = this.mDragButtonElement.mView.getTop() + ((int) (f2 - this.mInitialTouchY));
        int i4 = this.mSliderWidth;
        DragButtonElement dragButtonElement = this.mDragButtonElement;
        int i5 = (i4 - dragButtonElement.mWidth) / 2;
        if (top < i5) {
            this.mDragButtonMode = 1;
            if (!this.mDontAnimate) {
                this.mH.sendEmptyMessage(5);
            }
            top = i5;
        } else {
            int i6 = this.mSliderHeight;
            int i7 = dragButtonElement.mHeight;
            if (top > (i6 - i7) - i5) {
                top = (i6 - i7) - i5;
                this.mDragButtonMode = -1;
                if (!this.mDontAnimate) {
                    this.mH.sendEmptyMessage(5);
                }
            } else {
                this.mDontAnimate = false;
                if (this.mDragButtonMode != 0) {
                    this.mDragButtonMode = 0;
                    dragButtonElement.mView.setBackgroundResource(R.drawable.ic_mask);
                    this.mDragButtonElement.getView().changeImage(R.drawable.ic_moveicon, this.mMoveIconWidth, this.mMoveIconHeight);
                }
            }
        }
        DragButtonElement dragButtonElement2 = this.mDragButtonElement;
        dragButtonElement2.mTop = top;
        View view = dragButtonElement2.mView;
        int i8 = dragButtonElement2.mLeft;
        int right = view.getRight();
        DragButtonElement dragButtonElement3 = this.mDragButtonElement;
        view.layout(i8, top, right, dragButtonElement3.mTop + dragButtonElement3.mHeight);
        if (!this.mShutDownPasswordEnabled) {
            handleActionMoveForAlpha(f4);
        }
        if (Math.abs(this.mDragTar) >= this.mSliderHeight / 2) {
            this.mDragTar = f3 - this.mCenterY;
        }
        this.mSliderGradientBackgroudColor = z2 ? 0.0f : Math.min(Math.abs((f3 - this.mCenterY) - this.mDragTar) / 200.0f, 1.0f);
        boolean z3 = (f3 - ((float) this.mCenterY)) - this.mDragTar < 0.0f;
        this.mIsUp = z3;
        int i9 = this.mSliderTopOnScreen;
        DragButtonElement dragButtonElement4 = this.mDragButtonElement;
        if (z3) {
            i9 += dragButtonElement4.mTop;
        }
        this.mSliderGradientBackgroudTop = i9 + (dragButtonElement4.mHeight / 2);
        int i10 = this.mSliderTopOnScreen;
        if (z3) {
            i2 = (i10 + this.mSliderHeight) - (this.mDragButtonElement.mHeight / 2);
        } else {
            DragButtonElement dragButtonElement5 = this.mDragButtonElement;
            i2 = i10 + dragButtonElement5.mTop + (dragButtonElement5.mHeight / 2);
        }
        this.mSliderGradientBackgroudBottom = i2;
        invalidate();
    }

    public void handleActionUp() {
        this.mSliderGradientBackgroudColor = 0.0f;
        if (this.mDragButtonMode == 0) {
            this.mH.sendEmptyMessage(3);
        } else {
            invalidate();
            this.mH.sendEmptyMessage(2);
        }
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        this.mCallback.onConfigChanged(configuration);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        LinearGradient linearGradient;
        super.onDraw(canvas);
        this.mHighLightPaint.setColor(-1);
        int i2 = (int) (this.mSliderGradientBackgroudColor * 255.0f);
        if (this.mIsUp) {
            float f2 = this.mSliderGradientBackgroudTop;
            int i3 = this.mSliderTopOnScreen;
            linearGradient = new LinearGradient(0.0f, f2 - i3, 0.0f, this.mSliderGradientBackgroudBottom - i3, new int[]{ViewCompat.MEASURED_SIZE_MASK, (i2 << 24) ^ ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK}, new float[]{0.0f, 0.07f, 1.0f}, Shader.TileMode.CLAMP);
        } else {
            float f3 = this.mSliderGradientBackgroudTop;
            int i4 = this.mSliderTopOnScreen;
            linearGradient = new LinearGradient(0.0f, f3 - i4, 0.0f, this.mSliderGradientBackgroudBottom - i4, new int[]{ViewCompat.MEASURED_SIZE_MASK, (i2 << 24) ^ ViewCompat.MEASURED_SIZE_MASK, ViewCompat.MEASURED_SIZE_MASK}, new float[]{0.0f, 0.93f, 1.0f}, Shader.TileMode.CLAMP);
        }
        this.mHighLightPaint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.mHighLightPaint);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        ImageButtonElement imageButtonElement = this.mRebootImageButtonElement;
        imageButtonElement.mTop = (this.mSliderHeight - imageButtonElement.mHeight) - this.mShutdownImageButtonElement.mTop;
        Iterator<BaseElement> it = this.mElements.iterator();
        while (it.hasNext()) {
            it.next().layout();
        }
        getLocationOnScreen(this.mLocation);
        int i6 = this.mLocation[1];
        this.mSliderTopOnScreen = i6;
        this.mCenterY = i6 + (this.mSliderHeight / 2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        Iterator<BaseElement> it = this.mElements.iterator();
        while (it.hasNext()) {
            it.next().measure();
        }
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(this.mSliderWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.mSliderHeight, BasicMeasure.EXACTLY));
    }

    public void show(Window window) {
        this.mWindow = window;
        this.mShutDownPasswordEnabled = ShutDownPasswordUtils.isShutDownPasswordEnabled(this.mContext);
        Log.d(TAG, " show mShutDownPasswordEnabled = " + this.mShutDownPasswordEnabled);
        this.mSliderHeight = (int) this.mContext.getResources().getDimension(Util.checkFlipDimen(R.dimen.slider_height));
        setVisibility(4);
        post(new Runnable() { // from class: com.android.systemui.miui.globalactions.SliderView.1
            @Override // java.lang.Runnable
            public void run() {
                SliderView.this.mH.sendEmptyMessage(0);
                SliderView.this.setVisibility(0);
                SliderView.this.mCallback.textViewAlphaAnimator(true);
            }
        });
    }

    public SliderView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mH = new H(this);
        this.mDragButtonMode = 0;
        this.mSliderAlpha = 1.0f;
        this.mRootAlpha = 0.0f;
        this.mElements = new ArrayList();
        this.mHighLightPaint = new Paint();
        this.mLocation = new int[2];
        this.mIsAnimating = false;
        this.mDontAnimate = false;
        this.mDragTar = 0.0f;
        this.mIsUp = false;
        this.mDragButtonTouchListener = new View.OnTouchListener() { // from class: com.android.systemui.miui.globalactions.SliderView.2
            /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
            @Override // android.view.View.OnTouchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean onTouch(android.view.View r5, android.view.MotionEvent r6) {
                /*
                    r4 = this;
                    com.android.systemui.miui.globalactions.SliderView r5 = com.android.systemui.miui.globalactions.SliderView.this
                    boolean r5 = com.android.systemui.miui.globalactions.SliderView.j(r5)
                    r0 = 1
                    if (r5 == 0) goto La
                    return r0
                La:
                    int r5 = r6.getAction()
                    float r1 = r6.getY()
                    r2 = 0
                    if (r5 == 0) goto L34
                    if (r5 == r0) goto L2e
                    r3 = 2
                    if (r5 == r3) goto L24
                    r6 = 3
                    if (r5 == r6) goto L2e
                    r6 = 5
                    if (r5 == r6) goto L34
                    r6 = 6
                    if (r5 == r6) goto L2e
                    goto L39
                L24:
                    com.android.systemui.miui.globalactions.SliderView r4 = com.android.systemui.miui.globalactions.SliderView.this
                    float r5 = r6.getRawY()
                    r4.handleActionMove(r1, r5, r2)
                    goto L39
                L2e:
                    com.android.systemui.miui.globalactions.SliderView r4 = com.android.systemui.miui.globalactions.SliderView.this
                    r4.handleActionUp()
                    goto L39
                L34:
                    com.android.systemui.miui.globalactions.SliderView r4 = com.android.systemui.miui.globalactions.SliderView.this
                    r4.handleActionDown(r1, r2)
                L39:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.miui.globalactions.SliderView.AnonymousClass2.onTouch(android.view.View, android.view.MotionEvent):boolean");
            }
        };
        this.mBlurUtils = new BlurUtils(context);
        initView(context);
    }
}
