package miuix.androidbasewidget.internal.view;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import miuix.androidbasewidget.R;
import miuix.androidbasewidget.internal.view.SeekBarGradientDrawable;
import miuix.animation.physics.DynamicAnimation;
import miuix.animation.physics.SpringAnimation;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes4.dex */
public class SeekBaThumbShapeDrawable extends SeekBarGradientDrawable {
    private static final int FULL_ALPHA = 255;
    private static final String TAG = "SeekBaThumbShape";
    private static Drawable mShadowDrawable;
    private DynamicAnimation.OnAnimationUpdateListener mInvalidateUpdateListener;
    private SpringAnimation mPressedScaleAnim;
    private SpringAnimation mPressedShadowShowAnim;
    private float mScale;
    private FloatProperty<SeekBaThumbShapeDrawable> mScaleFloatProperty;
    private float mShadowAlpha;
    private FloatProperty<SeekBaThumbShapeDrawable> mShadowAlphaProperty;
    private SpringAnimation mUnPressedScaleAnim;
    private SpringAnimation mUnPressedShadowHideAnim;

    public static class SeekBaThumbShapeDrawableState extends SeekBarGradientDrawable.SeekBarGradientState {
        @Override // miuix.androidbasewidget.internal.view.SeekBarGradientDrawable.SeekBarGradientState
        public Drawable newSeekBarGradientDrawable(Resources resources, Resources.Theme theme, SeekBarGradientDrawable.SeekBarGradientState seekBarGradientState) {
            return new SeekBaThumbShapeDrawable(resources, theme, seekBarGradientState);
        }
    }

    public SeekBaThumbShapeDrawable() {
        this.mScale = 1.0f;
        this.mShadowAlpha = 0.0f;
        this.mShadowAlphaProperty = new FloatProperty<SeekBaThumbShapeDrawable>("ShadowAlpha") { // from class: miuix.androidbasewidget.internal.view.SeekBaThumbShapeDrawable.1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable) {
                return seekBaThumbShapeDrawable.getShadowAlpha();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable, float f2) {
                if (f2 > 1.0f) {
                    f2 = 1.0f;
                }
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                seekBaThumbShapeDrawable.setShadowAlpha(f2);
            }
        };
        this.mInvalidateUpdateListener = new DynamicAnimation.OnAnimationUpdateListener() { // from class: miuix.androidbasewidget.internal.view.a
            @Override // miuix.animation.physics.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                this.f5932a.lambda$new$0(dynamicAnimation, f2, f3);
            }
        };
        this.mScaleFloatProperty = new FloatProperty<SeekBaThumbShapeDrawable>("Scale") { // from class: miuix.androidbasewidget.internal.view.SeekBaThumbShapeDrawable.2
            @Override // miuix.animation.property.FloatProperty
            public float getValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable) {
                return seekBaThumbShapeDrawable.getScale();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable, float f2) {
                seekBaThumbShapeDrawable.setScale(f2);
            }
        };
        initAnim();
    }

    private void drawShadow(Canvas canvas) {
        Rect bounds = getBounds();
        Drawable drawable = mShadowDrawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = mShadowDrawable.getIntrinsicHeight();
            int intrinsicWidth2 = intrinsicWidth - getIntrinsicWidth();
            int i2 = intrinsicWidth2 / 2;
            int intrinsicHeight2 = (intrinsicHeight - getIntrinsicHeight()) / 2;
            mShadowDrawable.setBounds(bounds.left - i2, bounds.top - intrinsicHeight2, bounds.right + i2, bounds.bottom + intrinsicHeight2);
            mShadowDrawable.setAlpha((int) (this.mShadowAlpha * 255.0f));
            mShadowDrawable.draw(canvas);
        }
    }

    private void initAnim() {
        SpringAnimation springAnimation = new SpringAnimation(this, this.mScaleFloatProperty, 3.19f);
        this.mPressedScaleAnim = springAnimation;
        springAnimation.getSpring().setStiffness(986.96f);
        this.mPressedScaleAnim.getSpring().setDampingRatio(0.7f);
        this.mPressedScaleAnim.setMinimumVisibleChange(0.002f);
        this.mPressedScaleAnim.addUpdateListener(this.mInvalidateUpdateListener);
        SpringAnimation springAnimation2 = new SpringAnimation(this, this.mScaleFloatProperty, 1.0f);
        this.mUnPressedScaleAnim = springAnimation2;
        springAnimation2.getSpring().setStiffness(986.96f);
        this.mUnPressedScaleAnim.getSpring().setDampingRatio(0.8f);
        this.mUnPressedScaleAnim.setMinimumVisibleChange(0.002f);
        this.mUnPressedScaleAnim.addUpdateListener(this.mInvalidateUpdateListener);
        SpringAnimation springAnimation3 = new SpringAnimation(this, this.mShadowAlphaProperty, 1.0f);
        this.mPressedShadowShowAnim = springAnimation3;
        springAnimation3.getSpring().setStiffness(986.96f);
        this.mPressedShadowShowAnim.getSpring().setDampingRatio(0.99f);
        this.mPressedShadowShowAnim.setMinimumVisibleChange(0.00390625f);
        this.mPressedShadowShowAnim.addUpdateListener(this.mInvalidateUpdateListener);
        SpringAnimation springAnimation4 = new SpringAnimation(this, this.mShadowAlphaProperty, 0.0f);
        this.mUnPressedShadowHideAnim = springAnimation4;
        springAnimation4.getSpring().setStiffness(986.96f);
        this.mUnPressedShadowHideAnim.getSpring().setDampingRatio(0.99f);
        this.mUnPressedShadowHideAnim.setMinimumVisibleChange(0.00390625f);
        this.mUnPressedShadowHideAnim.addUpdateListener(this.mInvalidateUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(DynamicAnimation dynamicAnimation, float f2, float f3) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = (bounds.right + bounds.left) / 2;
        int i3 = (bounds.top + bounds.bottom) / 2;
        drawShadow(canvas);
        canvas.save();
        float f2 = this.mScale;
        canvas.scale(f2, f2, i2, i3);
        super.draw(canvas);
        canvas.restore();
    }

    public float getScale() {
        return this.mScale;
    }

    public float getShadowAlpha() {
        return this.mShadowAlpha;
    }

    @Override // miuix.androidbasewidget.internal.view.SeekBarGradientDrawable
    public SeekBarGradientDrawable.SeekBarGradientState newSeekBarGradientState() {
        return new SeekBaThumbShapeDrawableState();
    }

    public void setScale(float f2) {
        this.mScale = f2;
    }

    public void setShadowAlpha(float f2) {
        this.mShadowAlpha = f2;
    }

    @Override // miuix.androidbasewidget.internal.view.SeekBarGradientDrawable
    public void startPressedAnim() {
        if (this.mUnPressedScaleAnim.isRunning()) {
            this.mUnPressedScaleAnim.cancel();
        }
        if (!this.mPressedScaleAnim.isRunning()) {
            this.mPressedScaleAnim.start();
        }
        if (this.mUnPressedShadowHideAnim.isRunning()) {
            this.mUnPressedShadowHideAnim.cancel();
        }
        if (this.mPressedShadowShowAnim.isRunning()) {
            return;
        }
        this.mPressedShadowShowAnim.start();
    }

    @Override // miuix.androidbasewidget.internal.view.SeekBarGradientDrawable
    public void startUnPressedAnim() {
        if (this.mPressedScaleAnim.isRunning()) {
            this.mPressedScaleAnim.cancel();
        }
        if (!this.mUnPressedScaleAnim.isRunning()) {
            this.mUnPressedScaleAnim.start();
        }
        if (this.mPressedShadowShowAnim.isRunning()) {
            this.mPressedShadowShowAnim.cancel();
        }
        if (this.mUnPressedShadowHideAnim.isRunning()) {
            return;
        }
        this.mUnPressedShadowHideAnim.start();
    }

    public SeekBaThumbShapeDrawable(Resources resources, Resources.Theme theme, SeekBarGradientDrawable.SeekBarGradientState seekBarGradientState) {
        super(resources, theme, seekBarGradientState);
        this.mScale = 1.0f;
        this.mShadowAlpha = 0.0f;
        this.mShadowAlphaProperty = new FloatProperty<SeekBaThumbShapeDrawable>("ShadowAlpha") { // from class: miuix.androidbasewidget.internal.view.SeekBaThumbShapeDrawable.1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable) {
                return seekBaThumbShapeDrawable.getShadowAlpha();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable, float f2) {
                if (f2 > 1.0f) {
                    f2 = 1.0f;
                }
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                seekBaThumbShapeDrawable.setShadowAlpha(f2);
            }
        };
        this.mInvalidateUpdateListener = new DynamicAnimation.OnAnimationUpdateListener() { // from class: miuix.androidbasewidget.internal.view.a
            @Override // miuix.animation.physics.DynamicAnimation.OnAnimationUpdateListener
            public final void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3) {
                this.f5932a.lambda$new$0(dynamicAnimation, f2, f3);
            }
        };
        this.mScaleFloatProperty = new FloatProperty<SeekBaThumbShapeDrawable>("Scale") { // from class: miuix.androidbasewidget.internal.view.SeekBaThumbShapeDrawable.2
            @Override // miuix.animation.property.FloatProperty
            public float getValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable) {
                return seekBaThumbShapeDrawable.getScale();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(SeekBaThumbShapeDrawable seekBaThumbShapeDrawable, float f2) {
                seekBaThumbShapeDrawable.setScale(f2);
            }
        };
        initAnim();
        if (resources == null || mShadowDrawable != null) {
            return;
        }
        mShadowDrawable = resources.getDrawable(R.drawable.miuix_appcompat_sliding_btn_slider_shadow);
    }
}
