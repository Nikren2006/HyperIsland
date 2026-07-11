package miui.systemui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.airbnb.lottie.LottieAnimationView;
import d.AbstractC0315p;
import d.C0307h;
import d.F;
import d.H;
import miui.systemui.quicksettings.common.R;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public class StatusProgressLayout extends android.widget.FrameLayout {
    private static final int ANIM_DURATION = 0;
    private float cornerRadius;
    private Boolean isCornerRadius;
    private LottieAnimationView mBtnLottieView;
    private android.widget.ImageView mBtnStatusOn;
    private CapsuleProgressBar mCapsuleProgressBar;
    private Icon mIcon;
    private String mKey;
    private float mMaxProgress;
    private CircularProgressBar mProgressBar;
    private int mProgressColor;
    private int mProgressbarBgColor;
    private int mResId;
    private ValueAnimator mStatusAnimator;
    private int mStrokeWidth;

    public StatusProgressLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mStatusAnimator = null;
        this.isCornerRadius = Boolean.FALSE;
        this.cornerRadius = -1.0f;
        initAttrs(context, attributeSet);
        initChildView(context, attributeSet);
    }

    private void addLottieView(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.focus_notify_action_anim_size);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.focus_notify_action_icon_margin);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
        layoutParams.setMargins(dimensionPixelOffset2, dimensionPixelOffset2, dimensionPixelOffset2, dimensionPixelOffset2);
        this.mBtnLottieView.setLayoutParams(layoutParams);
        addView(this.mBtnLottieView);
    }

    private String getViewTag(android.widget.ImageView imageView) {
        Object tag = imageView.getTag();
        if (tag == null) {
            return null;
        }
        return (String) tag;
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressBar);
        this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CircularProgressBar_strokeWidth, 8);
        this.mProgressbarBgColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CircularProgressBar_pbCircleColor, ContextCompat.getColor(context, R.color.color_progress_bg));
        this.mProgressColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CircularProgressBar_progressbarColor, ContextCompat.getColor(context, R.color.progress_color));
        this.mMaxProgress = typedArrayObtainStyledAttributes.getInt(R.styleable.CircularProgressBar_maxProgress, 100);
        this.cornerRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.CircularProgressBar_cornerRadiusCapsule, -1.0f);
        this.isCornerRadius = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(R.styleable.CircularProgressBar_isCornerRadius, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initChildView(Context context, AttributeSet attributeSet) {
        this.mBtnStatusOn = new android.widget.ImageView(context);
        this.mBtnLottieView = new LottieAnimationView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (this.isCornerRadius.booleanValue()) {
            CapsuleProgressBar capsuleProgressBar = new CapsuleProgressBar(context);
            this.mCapsuleProgressBar = capsuleProgressBar;
            capsuleProgressBar.setLayoutParams(layoutParams);
            addView(this.mCapsuleProgressBar);
            this.mCapsuleProgressBar.setUp(this.mStrokeWidth, this.mProgressbarBgColor, this.mProgressColor, this.mMaxProgress, this.cornerRadius);
        } else {
            CircularProgressBar circularProgressBar = new CircularProgressBar(context);
            this.mProgressBar = circularProgressBar;
            circularProgressBar.setLayoutParams(layoutParams);
            addView(this.mProgressBar);
            this.mProgressBar.setUp(this.mStrokeWidth, this.mProgressbarBgColor, this.mProgressColor, this.mMaxProgress);
        }
        this.mBtnStatusOn.setLayoutParams(layoutParams);
        addView(this.mBtnStatusOn);
        if (!CommonUtils.NOT_SUPPORT_LOTTIE) {
            addLottieView(context);
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mStatusAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mStatusAnimator.setDuration(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupAnimation$1(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        float f2 = ((double) animatedFraction) < 0.5d ? 1.0f - ((animatedFraction * 9.0f) / 5.0f) : ((animatedFraction * 9.0f) / 5.0f) - 0.8f;
        this.mBtnStatusOn.setAlpha(f2);
        this.mBtnStatusOn.setScaleX(f2);
        this.mBtnStatusOn.setScaleY(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAndPauseAnimation$0(C0307h c0307h) {
        if (c0307h != null) {
            F f2 = new F();
            f2.N0(c0307h);
            f2.g1(0.0f);
            this.mBtnLottieView.setImageDrawable(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset(int i2, Icon icon, String str) {
        android.widget.ImageView imageView = this.mBtnStatusOn;
        if (imageView != null && i2 != 0) {
            imageView.setTag(str);
            this.mBtnStatusOn.setImageResource(i2);
            this.mBtnStatusOn.setAlpha(1.0f);
            this.mBtnStatusOn.setScaleX(1.0f);
            this.mBtnStatusOn.setScaleY(1.0f);
            return;
        }
        if (imageView == null || icon == null) {
            return;
        }
        imageView.setTag(str);
        this.mBtnStatusOn.setImageIcon(icon);
        this.mBtnStatusOn.setAlpha(1.0f);
        this.mBtnStatusOn.setScaleX(1.0f);
        this.mBtnStatusOn.setScaleY(1.0f);
    }

    private void setupAnimation() {
        this.mStatusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.widget.e
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f5929a.lambda$setupAnimation$1(valueAnimator);
            }
        });
    }

    private void setupListener(final String str, final int i2, final Icon icon) {
        this.mStatusAnimator.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.widget.StatusProgressLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                StatusProgressLayout.this.reset(i2, icon, str);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                StatusProgressLayout.this.reset(i2, icon, str);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                StatusProgressLayout.this.reset(i2, icon, str);
            }
        });
    }

    private void statusAnimator(String str, int i2, Icon icon) {
        this.mStatusAnimator.cancel();
        this.mStatusAnimator.removeAllListeners();
        this.mKey = str;
        this.mResId = i2;
        this.mIcon = icon;
        setupAnimation();
        setupListener(str, i2, icon);
        this.mStatusAnimator.start();
    }

    public void cancelAnimation() {
        ValueAnimator valueAnimator = this.mStatusAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mStatusAnimator.removeAllListeners();
        }
        CircularProgressBar circularProgressBar = this.mProgressBar;
        if (circularProgressBar != null) {
            circularProgressBar.cancelAnimation();
        }
        CapsuleProgressBar capsuleProgressBar = this.mCapsuleProgressBar;
        if (capsuleProgressBar != null) {
            capsuleProgressBar.cancelAnimation();
        }
    }

    public void clearLottieFrame() {
        LottieAnimationView lottieAnimationView = this.mBtnLottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(4);
        }
    }

    public void handleLottieAnim(int i2) {
        this.mBtnStatusOn.setVisibility(4);
        this.mBtnLottieView.setVisibility(0);
        this.mBtnLottieView.setAnimation(i2);
        this.mBtnLottieView.playAnimation();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        reset(this.mResId, this.mIcon, this.mKey);
    }

    public void setCCW(boolean z2) {
        this.mProgressBar.isCCW(z2);
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f) {
            return;
        }
        if (this.isCornerRadius.booleanValue()) {
            if (this.mCapsuleProgressBar.getVisibility() == 8) {
                this.mCapsuleProgressBar.setVisibility(0);
            }
            this.mCapsuleProgressBar.setProgress(f2);
        } else {
            if (this.mProgressBar.getVisibility() == 8) {
                this.mProgressBar.setVisibility(0);
            }
            this.mProgressBar.setProgress(f2);
        }
    }

    public void updateAndPauseAnimation(int i2, Context context) {
        this.mBtnStatusOn.setVisibility(4);
        this.mBtnLottieView.setVisibility(0);
        AbstractC0315p.s(context, i2).d(new H() { // from class: miui.systemui.widget.f
            @Override // d.H
            public final void onResult(Object obj) {
                this.f5930a.lambda$updateAndPauseAnimation$0((C0307h) obj);
            }
        });
    }

    public void updateColor(int i2, int i3, int i4) {
        if (this.isCornerRadius.booleanValue()) {
            CapsuleProgressBar capsuleProgressBar = this.mCapsuleProgressBar;
            if (capsuleProgressBar != null) {
                capsuleProgressBar.updateColor(i2, i3, i4);
                return;
            }
            return;
        }
        CircularProgressBar circularProgressBar = this.mProgressBar;
        if (circularProgressBar != null) {
            circularProgressBar.updateColor(i2, i3, i4);
        }
    }

    public void updateStatusIcon(String str, int i2, Icon icon) {
        this.mBtnStatusOn.setVisibility(0);
        String viewTag = getViewTag(this.mBtnStatusOn);
        if (viewTag != null) {
            if (TextUtils.equals(viewTag, str)) {
                return;
            }
            statusAnimator(str, i2, icon);
        } else {
            if (i2 == 0) {
                this.mBtnStatusOn.setImageIcon(icon);
            } else {
                this.mBtnStatusOn.setImageResource(i2);
            }
            this.mBtnStatusOn.setTag(str);
        }
    }
}
