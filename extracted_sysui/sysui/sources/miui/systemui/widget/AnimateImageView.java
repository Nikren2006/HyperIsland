package miui.systemui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.LottieAnimationView;
import d.AbstractC0315p;
import d.C0307h;
import d.F;
import d.H;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public class AnimateImageView extends FrameLayout {
    private static final int ANIM_DURATION = 0;
    private LottieAnimationView mImageLottieView;
    private AppCompatImageView mImageView;
    private String mKey;
    private int mResId;
    private ValueAnimator mValueAnimator;

    public AnimateImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initImage(context);
        init();
    }

    private void animateUpdate(final String str, final int i2) {
        this.mValueAnimator.cancel();
        this.mValueAnimator.removeAllListeners();
        this.mResId = i2;
        this.mKey = str;
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.widget.b
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f5925a.lambda$animateUpdate$1(valueAnimator);
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.widget.AnimateImageView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimateImageView.this.reset(i2, str);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimateImageView.this.reset(i2, str);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimateImageView.this.reset(i2, str);
            }
        });
        this.mValueAnimator.start();
    }

    private void init() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mValueAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(0L);
        this.mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void initImage(Context context) {
        this.mImageView = new AppCompatImageView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mImageView.setLayoutParams(layoutParams);
        addView(this.mImageView);
        if (CommonUtils.NOT_SUPPORT_LOTTIE) {
            return;
        }
        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        this.mImageLottieView = lottieAnimationView;
        lottieAnimationView.setLayoutParams(layoutParams);
        addView(this.mImageLottieView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$animateUpdate$1(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        float f2 = ((double) animatedFraction) < 0.5d ? 1.0f - ((animatedFraction * 8.0f) / 5.0f) : ((animatedFraction * 8.0f) / 5.0f) - 0.6f;
        this.mImageView.setAlpha(f2);
        this.mImageView.setScaleX(f2);
        this.mImageView.setScaleY(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAndPauseAnimation$0(C0307h c0307h) {
        if (c0307h != null) {
            F f2 = new F();
            f2.N0(c0307h);
            f2.g1(0.0f);
            this.mImageLottieView.setImageDrawable(f2);
        }
    }

    public void cancelAnimation() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mValueAnimator.removeAllListeners();
        }
    }

    public void clearLottieFrame() {
        LottieAnimationView lottieAnimationView = this.mImageLottieView;
        if (lottieAnimationView != null) {
            lottieAnimationView.setVisibility(4);
        }
        this.mImageView.setVisibility(0);
    }

    public void handleLottieAnim(int i2) {
        this.mImageView.setVisibility(4);
        this.mImageLottieView.setVisibility(0);
        this.mImageLottieView.setAnimation(i2);
        this.mImageLottieView.playAnimation();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        reset(this.mResId, this.mKey);
    }

    public void reset(int i2, String str) {
        AppCompatImageView appCompatImageView = this.mImageView;
        if (appCompatImageView == null || i2 == 0) {
            return;
        }
        this.mKey = str;
        appCompatImageView.setImageResource(i2);
        this.mImageView.setAlpha(1.0f);
        this.mImageView.setScaleX(1.0f);
        this.mImageView.setScaleY(1.0f);
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mImageView.setScaleType(scaleType);
    }

    public void updateAndPauseAnimation(int i2, Context context) {
        this.mImageView.setVisibility(4);
        this.mImageLottieView.setVisibility(0);
        AbstractC0315p.s(context, i2).d(new H() { // from class: miui.systemui.widget.a
            @Override // d.H
            public final void onResult(Object obj) {
                this.f5924a.lambda$updateAndPauseAnimation$0((C0307h) obj);
            }
        });
    }

    public void updateImageResource(String str, int i2, Icon icon) {
        this.mImageView.setVisibility(0);
        if (i2 == 0) {
            this.mImageView.setImageIcon(icon);
        } else {
            updateImageResource(str, i2);
        }
    }

    private void updateImageResource(String str, int i2) {
        if (this.mKey == null) {
            this.mImageView.setImageResource(i2);
            this.mKey = str;
        }
        if (TextUtils.equals(this.mKey, str)) {
            return;
        }
        animateUpdate(str, i2);
    }
}
