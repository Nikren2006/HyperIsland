package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;
import miuix.internal.util.AttributeResolver;
import miuix.miuixbasewidget.R;
import miuix.miuixbasewidget.widget.DropShadowConfig;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends ImageView {
    private static final int SHADOW_ALPHA = 102;
    private static final float SHADOW_RADIUS = 20.0f;
    private static final float X_OFFSET = 0.0f;
    private static final float Y_OFFSET = 16.0f;
    private Drawable mDefaultBackground;
    private EmptyHolder mEmptyHolder;
    private int mFabColor;
    private int mFabShadowColor;
    private boolean mHasFabColor;
    private boolean mHasFabShadowColor;
    private int mImageAlpha;
    private final boolean mIsShadowEnabled;
    private final DropShadowConfig mShadowConfig;
    private BaseWidgetDropShadowHelper mShadowHelper;

    public class EmptyHolder extends Drawable {
        private Drawable mDrawable;
        private Paint mPaint = new Paint();

        public EmptyHolder(Drawable drawable) {
            this.mDrawable = drawable;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(@NonNull Canvas canvas) {
            int width = FloatingActionButton.this.getWidth();
            int paddingLeft = FloatingActionButton.this.getPaddingLeft();
            int paddingTop = FloatingActionButton.this.getPaddingTop();
            int paddingRight = (((width - paddingLeft) - FloatingActionButton.this.getPaddingRight()) / 2) * 2;
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

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private int computeShadowColor(int i2) {
        return Color.argb(102, Color.red(i2), Math.max(0, Color.green(i2) - 30), Color.blue(i2));
    }

    private Drawable createFabBackground() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShapeWithPadding(this));
        if (this.mIsShadowEnabled) {
            this.mShadowConfig.shadowColor = this.mHasFabShadowColor ? this.mFabShadowColor : computeShadowColor(this.mFabColor);
            if (this.mShadowHelper == null) {
                initDropShadowHelper();
            } else {
                this.mShadowHelper.onConfigChanged(this, getResources().getConfiguration(), AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true));
            }
        } else {
            BaseWidgetDropShadowHelper baseWidgetDropShadowHelper = this.mShadowHelper;
            if (baseWidgetDropShadowHelper != null) {
                baseWidgetDropShadowHelper.enableViewShadow(this, false, 2);
            }
            this.mShadowHelper = null;
        }
        shapeDrawable.getPaint().setColor(this.mFabColor);
        return shapeDrawable;
    }

    private Drawable getDefaultBackground() {
        if (this.mDefaultBackground == null) {
            this.mFabColor = getContext().getResources().getColor(R.color.miuix_appcompat_fab_color_light);
            this.mHasFabColor = true;
            this.mDefaultBackground = createFabBackground();
        }
        return this.mDefaultBackground;
    }

    private void initBackground() {
        if (getBackground() != null) {
            this.mHasFabColor = false;
            return;
        }
        if (this.mHasFabColor) {
            super.setBackground(createFabBackground());
        } else {
            super.setBackground(getDefaultBackground());
        }
        Drawable background = getBackground();
        if (background != null) {
            background.setAlpha(this.mImageAlpha);
        }
    }

    private void initDropShadowHelper() {
        this.mShadowHelper = new BaseWidgetDropShadowHelper(getContext(), this.mShadowConfig, AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true));
    }

    private void initEmptyHolder() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = (((getWidth() - paddingLeft) - getPaddingRight()) / 2) * 2;
        this.mEmptyHolder.setBounds(paddingLeft, paddingTop, paddingLeft + width, width + paddingTop);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        BaseWidgetDropShadowHelper baseWidgetDropShadowHelper;
        if (this.mIsShadowEnabled && (baseWidgetDropShadowHelper = this.mShadowHelper) != null) {
            baseWidgetDropShadowHelper.setAlpha(this.mImageAlpha / 255.0f);
            this.mShadowHelper.drawShadow(canvas, getHeight());
        }
        super.draw(canvas);
    }

    @Override // android.view.View
    public float getAlpha() {
        return (float) (((double) this.mImageAlpha) / 255.0d);
    }

    @Override // android.widget.ImageView
    public int getImageAlpha() {
        return this.mImageAlpha;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mShadowHelper != null) {
            this.mShadowHelper.onConfigChanged(this, configuration, AttributeResolver.resolveBoolean(getContext(), R.attr.isLightTheme, true));
        }
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
            baseWidgetDropShadowHelper.updateShadowRect(i2, i3, i4, i5);
            if (this.mIsShadowEnabled) {
                this.mShadowHelper.enableViewShadow(this, true, 2);
            } else {
                this.mShadowHelper.enableViewShadow(this, false, 2);
            }
        }
    }

    @Override // android.view.View
    public boolean performClick() {
        HapticCompat.performHapticFeedback(this, HapticFeedbackConstants.MIUI_BUTTON_MIDDLE, HapticFeedbackConstants.MIUI_TAP_LIGHT);
        return super.performClick();
    }

    @Override // android.widget.ImageView
    public void setAlpha(int i2) {
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

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.mHasFabColor = false;
        if (drawable == null) {
            drawable = getDefaultBackground();
        }
        super.setBackground(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        if (!this.mHasFabColor || this.mFabColor != i2) {
            this.mFabColor = i2;
            super.setBackground(createFabBackground());
        }
        this.mHasFabColor = true;
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        this.mHasFabColor = false;
        if (i2 == 0) {
            super.setBackground(getDefaultBackground());
        } else {
            super.setBackgroundResource(i2);
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

    public static class OvalShapeWithPadding extends OvalShape {
        private WeakReference<View> mViewRef;

        public OvalShapeWithPadding() {
            this.mViewRef = new WeakReference<>(null);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            View view = this.mViewRef.get();
            if (view != null) {
                int width = view.getWidth();
                int paddingLeft = view.getPaddingLeft();
                int paddingTop = view.getPaddingTop();
                float paddingRight = ((width - paddingLeft) - view.getPaddingRight()) / 2.0f;
                canvas.drawCircle(paddingLeft + paddingRight, paddingTop + paddingRight, paddingRight, paint);
            }
        }

        public OvalShapeWithPadding(View view) {
            this.mViewRef = new WeakReference<>(view);
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mImageAlpha = 255;
        DropShadowConfig dropShadowConfigCreate = new DropShadowConfig.Builder(SHADOW_RADIUS).create();
        this.mShadowConfig = dropShadowConfigCreate;
        dropShadowConfigCreate.offsetXDp = 0.0f;
        dropShadowConfigCreate.offsetYDp = Y_OFFSET;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i2, R.style.Widget_FloatingActionButton);
        this.mIsShadowEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FloatingActionButton_fabShadowEnabled, true);
        int i3 = R.styleable.FloatingActionButton_fabColor;
        this.mHasFabColor = typedArrayObtainStyledAttributes.hasValue(i3);
        this.mFabColor = typedArrayObtainStyledAttributes.getColor(i3, context.getResources().getColor(R.color.miuix_appcompat_fab_color));
        int i4 = R.styleable.FloatingActionButton_fabShadowColor;
        this.mHasFabShadowColor = typedArrayObtainStyledAttributes.hasValue(i4);
        this.mFabShadowColor = typedArrayObtainStyledAttributes.getColor(i4, this.mFabColor);
        typedArrayObtainStyledAttributes.recycle();
        this.mEmptyHolder = new EmptyHolder(getContext().getResources().getDrawable(R.drawable.miuix_appcompat_fab_empty_holder));
        initBackground();
        if (getDrawable() != null) {
            Folme.useAt(this).touch().setTouchRadius((float) Math.ceil(r4.getIntrinsicWidth() / 2.0f)).handleTouchOf(this, new AnimConfig[0]);
        } else {
            Folme.useAt(this).touch().setTouchRadius(1000.0f).handleTouchOf(this, new AnimConfig[0]);
        }
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this, new AnimConfig[0]);
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
