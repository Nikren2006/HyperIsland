package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;
import miuix.core.util.HyperBloomStrokeUtils;
import miuix.core.util.HyperMaterialUtils;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MaterialDayNightConfig;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.RomUtils;
import miuix.internal.util.AttributeResolver;
import miuix.miuixbasewidget.R;
import miuix.miuixbasewidget.widget.DropShadowConfig;
import miuix.theme.token.MaterialDayNightToken;
import miuix.theme.token.MaterialToken;
import miuix.view.BlurableWidget;
import miuix.view.DynamicThemeWidget;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;
import miuix.view.MiuiBlurUiHelper;

/* JADX INFO: loaded from: classes.dex */
public class OvalImageButton extends AppCompatImageView implements BlurableWidget, DynamicThemeWidget {

    @Nullable
    private OvalDrawable mBackground;

    @Nullable
    private final MiuiBlurUiHelper mBlurUiHelper;

    @Nullable
    private MaterialConfig mCurrentMaterial;
    private Drawable mDefaultBackground;
    private final EmptyHolder mEmptyHolder;
    private final ITouchStyle mFolmeTouch;
    private boolean mHasOibColor;
    private int mImageAlpha;
    private boolean mIsLightStyle;
    private final boolean mIsShadowEnabled;

    @Nullable
    private MaterialDayNightConfig mMaterial;

    @Nullable
    private ColorStateList mOibColor;

    @Nullable
    private BaseWidgetDropShadowHelper mShadowHelper;
    private int mStrokeColor;
    private float[] mStrokeGradientColorPositions;
    private int[] mStrokeGradientColors;
    private int mStrokeWidth;
    private boolean mUseCompatShadow;
    protected int mUserThemeType;

    public class EmptyHolder extends Drawable {
        private Drawable mDrawable;
        private Paint mPaint = new Paint();

        public EmptyHolder(Drawable drawable) {
            this.mDrawable = drawable;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            int width = OvalImageButton.this.getWidth();
            int paddingLeft = OvalImageButton.this.getPaddingLeft();
            int paddingTop = OvalImageButton.this.getPaddingTop();
            int paddingRight = (((width - paddingLeft) - OvalImageButton.this.getPaddingRight()) / 2) * 2;
            this.mDrawable.setBounds(paddingLeft, paddingTop, paddingLeft + paddingRight, paddingRight + paddingTop);
            this.mDrawable.draw(canvas);
        }

        @Override // android.graphics.drawable.Drawable
        @Nullable
        public Drawable.ConstantState getConstantState() {
            return this.mDrawable.getConstantState();
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.mDrawable.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i2) {
            this.mDrawable.setAlpha(i2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            this.mDrawable.setColorFilter(colorFilter);
        }
    }

    public OvalImageButton(Context context) {
        this(context, null);
    }

    private Drawable createOibBackground() {
        OvalDrawable ovalDrawable = new OvalDrawable(this.mOibColor);
        if (this.mIsShadowEnabled) {
            boolean zResolveBoolean = AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true);
            BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
            if (baseWidgetDropShadowHelper == null) {
                updateShadow(zResolveBoolean);
            } else {
                baseWidgetDropShadowHelper.onConfigChanged(this, getResources().getConfiguration(), zResolveBoolean);
            }
        } else {
            BaseWidgetDropShadowHelper baseWidgetDropShadowHelper2 = this.mShadowHelper;
            if (baseWidgetDropShadowHelper2 != null) {
                baseWidgetDropShadowHelper2.enableViewShadow(this, false, 2);
            }
            this.mShadowHelper = null;
        }
        this.mBackground = ovalDrawable;
        return ovalDrawable;
    }

    private Drawable getDefaultBackground() {
        if (this.mDefaultBackground == null) {
            this.mOibColor = ContextCompat.getColorStateList(getContext(), R.color.miuix_color_black_level6);
            this.mHasOibColor = true;
            this.mDefaultBackground = createOibBackground();
        }
        return this.mDefaultBackground;
    }

    @Nullable
    private OvalDrawable getOvalBackground() {
        return this.mBackground;
    }

    private void initBackground() {
        if (getBackground() != null) {
            this.mHasOibColor = false;
            return;
        }
        if (this.mHasOibColor) {
            super.setBackground(createOibBackground());
        } else {
            super.setBackground(getDefaultBackground());
        }
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha(this.mImageAlpha);
        }
    }

    private void initEmptyHolder() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = (((getWidth() - paddingLeft) - getPaddingRight()) / 2) * 2;
        this.mEmptyHolder.setBounds(paddingLeft, paddingTop, paddingLeft + width, width + paddingTop);
    }

    private void updateShadow(boolean z2) {
        MaterialConfig materialConfig = this.mCurrentMaterial;
        if (materialConfig == null) {
            return;
        }
        if (materialConfig.getShadowConfig() == null) {
            BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
            if (baseWidgetDropShadowHelper != null) {
                baseWidgetDropShadowHelper.enableViewShadow(this, false, 2);
                return;
            }
            return;
        }
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper2 = this.mShadowHelper;
        if (baseWidgetDropShadowHelper2 == null) {
            this.mShadowHelper = new BaseWidgetDropShadowHelper(getContext(), new DropShadowConfig.Builder(this.mCurrentMaterial.getShadowConfig()).create(), z2);
        } else {
            baseWidgetDropShadowHelper2.updateDropShadowConfig(this.mCurrentMaterial.getShadowConfig());
            this.mShadowHelper.updateViewShadow(this, 2);
        }
    }

    @Override // miuix.view.BlurableWidget
    public void applyBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.applyBlur(z2);
        }
    }

    public void enableUseCompatShadow(boolean z2) {
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper;
        if (this.mUseCompatShadow == z2 || (baseWidgetDropShadowHelper = this.mShadowHelper) == null) {
            return;
        }
        if (z2) {
            baseWidgetDropShadowHelper.setEnableMiShadow(false);
        } else {
            baseWidgetDropShadowHelper.setEnableMiShadow(RomUtils.getHyperOsVersion() >= 2 && MiShadowUtils.SUPPORT_MI_SHADOW);
        }
        this.mShadowHelper.enableViewShadow(this, false, 2);
        this.mUseCompatShadow = z2;
        float f2 = this.mShadowHelper.mBlurRadiusPx;
        if (f2 > 0.0f) {
            setElevation(f2);
        }
        setOutlineSpotShadowColor(this.mShadowHelper.mShadowColor);
        requestLayout();
    }

    @Override // android.widget.ImageView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ImageButton.class.getName();
    }

    @Override // android.view.View
    public float getAlpha() {
        return (float) (((double) this.mImageAlpha) / 255.0d);
    }

    @Override // miuix.view.HyperMaterialWidget
    @Nullable
    public MaterialConfig getCurrentMaterial() {
        return this.mCurrentMaterial;
    }

    @Override // android.widget.ImageView
    public int getImageAlpha() {
        return this.mImageAlpha;
    }

    @Override // miuix.view.HyperMaterialWidget
    @Nullable
    public MaterialDayNightConfig getMaterial() {
        return this.mMaterial;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    @Override // miuix.view.DynamicThemeWidget
    public int getThemeType() {
        return this.mUserThemeType;
    }

    @Override // miuix.view.DynamicThemeWidget
    public boolean hasThemeType() {
        return this.mUserThemeType > 0;
    }

    public void hide() {
        Folme.use((View) this).visible().hide(new AnimConfig[0]);
    }

    public void hideStroke() {
        OvalDrawable ovalBackground = getOvalBackground();
        if (ovalBackground != null) {
            ovalBackground.enableDrawStroke(false);
        }
        invalidate();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isApplyBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isApplyBlur();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isEnableBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isEnableBlur();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isSupportBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isSupportBlur();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateMaterialEffect();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        initEmptyHolder();
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
        if (baseWidgetDropShadowHelper != null) {
            baseWidgetDropShadowHelper.enableViewShadow(this, this.mIsShadowEnabled, 2);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.mFolmeTouch.setTouchRadius(getMeasuredWidth() / 2.0f);
    }

    @Override // android.view.View
    public boolean performClick() {
        HapticCompat.performHapticFeedback(this, HapticFeedbackConstants.MIUI_BUTTON_MIDDLE, HapticFeedbackConstants.MIUI_TAP_LIGHT);
        return super.performClick();
    }

    @Override // android.widget.ImageView
    public void setAlpha(int i2) {
        int iMax = Math.max(0, Math.min(i2, 255));
        boolean z2 = this.mImageAlpha != iMax;
        this.mImageAlpha = iMax;
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha(iMax);
        }
        Drawable drawable = getDrawable();
        if (drawable != null && z2) {
            drawable.mutate().setAlpha(iMax);
        }
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
        if (baseWidgetDropShadowHelper != null && baseWidgetDropShadowHelper.mEnableMiShadow) {
            baseWidgetDropShadowHelper.invalidateShadow(this, this.mImageAlpha / 255.0f);
        }
        invalidate();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.mHasOibColor = false;
        if (drawable == null) {
            drawable = getDefaultBackground();
        }
        super.setBackground(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        this.mOibColor = ColorStateList.valueOf(i2);
        super.setBackground(createOibBackground());
        this.mHasOibColor = true;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.view.View
    public void setBackgroundResource(int i2) {
        this.mHasOibColor = false;
        if (i2 == 0) {
            super.setBackground(getDefaultBackground());
        } else {
            super.setBackgroundResource(i2);
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setEnableBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.setEnableBlur(z2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageAlpha(int i2) {
        boolean z2 = this.mImageAlpha != i2;
        this.mImageAlpha = i2;
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha(i2);
        }
        Drawable drawable = getDrawable();
        if (drawable != null && z2) {
            drawable.mutate().setAlpha(i2);
        }
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
        if (baseWidgetDropShadowHelper != null && baseWidgetDropShadowHelper.mEnableMiShadow) {
            baseWidgetDropShadowHelper.invalidateShadow(this, this.mImageAlpha / 255.0f);
        }
        invalidate();
    }

    public void setMaterial(@Nullable MaterialToken materialToken) {
        setMaterial(MaterialDayNightConfig.create(new MaterialDayNightToken(materialToken)));
    }

    public void setStrokeColor(int i2) {
        if (this.mStrokeColor != i2) {
            this.mStrokeColor = i2;
            OvalDrawable ovalBackground = getOvalBackground();
            if (ovalBackground != null) {
                ovalBackground.setStrokeColor(this.mStrokeColor);
            }
        }
    }

    public void setStrokeGradientColors(int i2, int i3) {
        this.mStrokeGradientColors = new int[]{i2, i3};
        this.mStrokeGradientColorPositions = new float[]{0.0f, 1.0f};
        OvalDrawable ovalBackground = getOvalBackground();
        if (ovalBackground != null) {
            ovalBackground.setStrokeGradientColors(this.mStrokeGradientColors);
            ovalBackground.setStrokeColorGradientPositions(this.mStrokeGradientColorPositions);
        }
    }

    public void setStrokeWidth(int i2) {
        if (this.mStrokeWidth != i2) {
            this.mStrokeWidth = i2;
            OvalDrawable ovalBackground = getOvalBackground();
            if (ovalBackground != null) {
                ovalBackground.setStrokeWidth(i2);
            }
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setSupportBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.setSupportBlur(z2);
        }
    }

    @Override // miuix.view.DynamicThemeWidget
    public void setThemeType(int i2) {
        if (this.mUserThemeType != i2) {
            this.mUserThemeType = i2;
            updateMaterialEffect();
        }
    }

    public void setTouchColor(int i2) {
        this.mFolmeTouch.setTint(i2);
    }

    public void setTouchScalable(boolean z2) {
        this.mFolmeTouch.setNoScale(!z2);
    }

    public void show() {
        Folme.use((View) this).visible().show(new AnimConfig[0]);
    }

    public void showStroke() {
        OvalDrawable ovalBackground = getOvalBackground();
        if (ovalBackground != null) {
            ovalBackground.enableDrawStroke(true);
        }
        invalidate();
    }

    @Override // miuix.view.HyperMaterialWidget
    public void updateMaterialEffect() {
        if (this.mMaterial == null) {
            return;
        }
        boolean z2 = this.mIsLightStyle;
        if (hasThemeType()) {
            z2 = this.mUserThemeType == 1;
        }
        MaterialConfig materialConfig = this.mMaterial.get(z2);
        this.mCurrentMaterial = materialConfig;
        if (materialConfig == null || !HyperMaterialUtils.isFeatureEnable(getContext())) {
            applyBlur(false);
            setEnableBlur(false);
            HyperBloomStrokeUtils.clearBloomStroke(this);
            return;
        }
        setEnableBlur(true);
        if (this.mBlurUiHelper != null && this.mCurrentMaterial.getBlurConfig() != null) {
            if (isApplyBlur()) {
                this.mBlurUiHelper.onConfigChanged();
                this.mBlurUiHelper.refreshBlur();
            } else {
                this.mBlurUiHelper.onConfigChanged();
                applyBlur(true);
            }
        }
        MaterialConfig.BloomStrokeConfig bloomStrokeConfig = this.mCurrentMaterial.getBloomStrokeConfig();
        if (bloomStrokeConfig != null) {
            HyperBloomStrokeUtils.setBloomStrokeConfig(this, bloomStrokeConfig);
        } else {
            HyperBloomStrokeUtils.clearBloomStroke(this);
        }
        updateShadow(z2);
    }

    public OvalImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setMaterial(@Nullable MaterialDayNightToken materialDayNightToken) {
        setMaterial(MaterialDayNightConfig.create(materialDayNightToken));
    }

    public OvalImageButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mImageAlpha = 255;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OvalImageButton, i2, R.style.Widget_OvalImageButton);
        this.mIsLightStyle = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OvalImageButton_isLightTheme, true);
        this.mIsShadowEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OvalImageButton_oibShadowEnabled, true);
        ColorStateList colorStateList = typedArrayObtainStyledAttributes.getColorStateList(R.styleable.OvalImageButton_oibColor);
        this.mOibColor = colorStateList;
        this.mHasOibColor = colorStateList != null;
        int i3 = R.styleable.OvalImageButton_oibTouchColor;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i3);
        int color = typedArrayObtainStyledAttributes.getColor(i3, 0);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.OvalImageButton_oibTouchScalable, true);
        typedArrayObtainStyledAttributes.recycle();
        this.mEmptyHolder = new EmptyHolder(getContext().getResources().getDrawable(R.drawable.miuix_appcompat_fab_empty_holder));
        initBackground();
        if (HyperMaterialUtils.isEnable()) {
            MiuiBlurUiHelper miuiBlurUiHelper = new MiuiBlurUiHelper(context, this, false, false, false, new MiuiBlurUiHelper.BlurStateCallback() { // from class: miuix.miuixbasewidget.widget.OvalImageButton.1
                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public Drawable getBackground() {
                    return null;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public MaterialConfig.BlurConfig getBlurConfig(boolean z3) {
                    if (OvalImageButton.this.mMaterial == null) {
                        return null;
                    }
                    MaterialConfig.BlurConfig blurConfig = OvalImageButton.this.mMaterial.getBlurConfig(z3);
                    MaterialConfig.ColorBlendConfig colorBlendConfig = OvalImageButton.this.mMaterial.getColorBlendConfig(z3);
                    return (blurConfig != null || colorBlendConfig == null) ? blurConfig : new MaterialConfig.BlurConfig(colorBlendConfig);
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public boolean isLightTheme() {
                    return OvalImageButton.this.hasThemeType() ? OvalImageButton.this.mUserThemeType == 1 : OvalImageButton.this.mIsLightStyle;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurApplyStateChanged(boolean z3) {
                    Drawable background = OvalImageButton.this.getBackground();
                    if (background != null) {
                        background.setAlpha(z3 ? 0 : 255);
                    }
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurEnableStateChanged(boolean z3) {
                }
            });
            this.mBlurUiHelper = miuiBlurUiHelper;
            miuiBlurUiHelper.setSupportBlur(true);
            setEnableBlur(true);
        } else {
            this.mBlurUiHelper = null;
        }
        ITouchStyle iTouchStyle = Folme.use((View) this).touch();
        this.mFolmeTouch = iTouchStyle;
        if (zHasValue) {
            iTouchStyle.setTint(color);
        }
        iTouchStyle.setNoScale(!z2);
        iTouchStyle.setTintMode(3).handleTouchOf(this, new AnimConfig[0]);
        Folme.use((View) this).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this, new AnimConfig[0]);
    }

    @Override // miuix.view.HyperMaterialWidget
    public void setMaterial(@Nullable MaterialConfig materialConfig) {
        setMaterial(new MaterialDayNightConfig(materialConfig));
    }

    @Override // miuix.view.HyperMaterialWidget
    public void setMaterial(@Nullable MaterialDayNightConfig materialDayNightConfig) {
        if (isSupportBlur()) {
            if (materialDayNightConfig == null) {
                this.mMaterial = null;
                applyBlur(false);
                HyperBloomStrokeUtils.clearBloomStroke(this);
            } else {
                this.mMaterial = materialDayNightConfig;
                updateMaterialEffect();
            }
        }
    }

    public void setStrokeGradientColors(int[] iArr, float[] fArr) {
        this.mStrokeGradientColors = iArr;
        this.mStrokeGradientColorPositions = fArr;
        OvalDrawable ovalBackground = getOvalBackground();
        if (ovalBackground != null) {
            ovalBackground.setStrokeGradientColors(this.mStrokeGradientColors);
            ovalBackground.setStrokeColorGradientPositions(this.mStrokeGradientColorPositions);
        }
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        float f3 = 255.0f * f2;
        boolean z2 = ((float) this.mImageAlpha) != f3;
        int i2 = (int) f3;
        this.mImageAlpha = i2;
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha(i2);
        }
        Drawable drawable = getDrawable();
        if (drawable != null && z2) {
            drawable.mutate().setAlpha(i2);
        }
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
        if (baseWidgetDropShadowHelper != null && baseWidgetDropShadowHelper.mEnableMiShadow) {
            baseWidgetDropShadowHelper.invalidateShadow(this, f2);
        }
        invalidate();
    }
}
