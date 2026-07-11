package miuix.smooth;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import miuix.smooth.internal.SmoothDrawHelper;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class SmoothGradientDrawable extends GradientDrawable {
    private static final PorterDuffXfermode XFERMODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    private SmoothDrawHelper mHelper;
    private Rect mLayer;
    private GradientDrawable mParentDrawable;
    private RectF mSavedLayer;
    protected SmoothConstantState mSmoothConstantState;

    @NonNull
    private TypedArray obtainAttributes(@NonNull Resources resources, @Nullable Resources.Theme theme, @NonNull AttributeSet attributeSet, @NonNull int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        this.mSmoothConstantState.setConstantState(super.getConstantState());
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        SmoothConstantState smoothConstantState = this.mSmoothConstantState;
        return (smoothConstantState != null && smoothConstantState.canApplyTheme()) || super.canApplyTheme();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iSaveLayer = getLayerType() != 2 ? canvas.saveLayer(this.mSavedLayer, null, 31) : -1;
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.draw(canvas);
        } else {
            super.draw(canvas);
        }
        this.mHelper.drawMask(canvas, XFERMODE_DST_OUT);
        if (getLayerType() != 2) {
            canvas.restoreToCount(iSaveLayer);
        }
        this.mHelper.drawStroke(canvas);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public int getAlpha() {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        return gradientDrawable != null ? gradientDrawable.getAlpha() : super.getAlpha();
    }

    @Override // android.graphics.drawable.GradientDrawable
    @Nullable
    public ColorStateList getColor() {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        return gradientDrawable != null ? gradientDrawable.getColor() : super.getColor();
    }

    @Override // android.graphics.drawable.GradientDrawable
    @Nullable
    public int[] getColors() {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        return gradientDrawable != null ? gradientDrawable.getColors() : super.getColors();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.mSmoothConstantState;
    }

    public int getLayerType() {
        return this.mSmoothConstantState.mLayerType;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setPath(this.mHelper.getSmoothPath(this.mLayer));
    }

    public int getStrokeColor() {
        return this.mSmoothConstantState.mStrokeColor;
    }

    public int getStrokeWidth() {
        return this.mSmoothConstantState.mStrokeWidth;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void inflate(@NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = obtainAttributes(resources, theme, attributeSet, R.styleable.MiuixSmoothGradientDrawable);
        setStrokeWidth(typedArrayObtainAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothGradientDrawable_miuix_strokeWidth, 0));
        setStrokeColor(typedArrayObtainAttributes.getColor(R.styleable.MiuixSmoothGradientDrawable_miuix_strokeColor, 0));
        setLayerType(typedArrayObtainAttributes.getInt(R.styleable.MiuixSmoothGradientDrawable_android_layerType, 0));
        typedArrayObtainAttributes.recycle();
        super.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.setBounds(rect);
        }
        this.mHelper.onBoundsChange(rect);
        this.mLayer = rect;
        this.mSavedLayer.set(0.0f, 0.0f, rect.width(), rect.height());
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.setAlpha(i2);
        } else {
            super.setAlpha(i2);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.GradientDrawable
    public void setColor(int i2) {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(i2);
        } else {
            super.setColor(i2);
        }
    }

    @Override // android.graphics.drawable.GradientDrawable
    public void setColors(@Nullable int[] iArr, @Nullable float[] fArr) {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.setColors(iArr, fArr);
        } else {
            super.setColors(iArr, fArr);
        }
    }

    @Override // android.graphics.drawable.GradientDrawable
    public void setCornerRadii(@Nullable float[] fArr) {
        super.setCornerRadii(fArr);
        this.mSmoothConstantState.mRadii = fArr;
        this.mHelper.setRadii(fArr);
        if (fArr == null) {
            this.mSmoothConstantState.mRadius = 0.0f;
            this.mHelper.setRadius(0.0f);
        }
    }

    @Override // android.graphics.drawable.GradientDrawable
    public void setCornerRadius(float f2) {
        if (Float.isNaN(f2)) {
            return;
        }
        super.setCornerRadius(f2);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        SmoothConstantState smoothConstantState = this.mSmoothConstantState;
        smoothConstantState.mRadius = f2;
        smoothConstantState.mRadii = null;
        this.mHelper.setRadius(f2);
        this.mHelper.setRadii(null);
    }

    public void setLayerType(int i2) {
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException("Layer type can only be one of: LAYER_TYPE_NONE, LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE");
        }
        SmoothConstantState smoothConstantState = this.mSmoothConstantState;
        if (smoothConstantState.mLayerType != i2) {
            smoothConstantState.mLayerType = i2;
            invalidateSelf();
        }
    }

    public void setStrokeColor(int i2) {
        SmoothConstantState smoothConstantState = this.mSmoothConstantState;
        if (smoothConstantState.mStrokeColor != i2) {
            smoothConstantState.mStrokeColor = i2;
            this.mHelper.setStrokeColor(i2);
            invalidateSelf();
        }
    }

    public void setStrokeWidth(int i2) {
        SmoothConstantState smoothConstantState = this.mSmoothConstantState;
        if (smoothConstantState.mStrokeWidth != i2) {
            smoothConstantState.mStrokeWidth = i2;
            this.mHelper.setStrokeWidth(i2);
            invalidateSelf();
        }
    }

    public static class SmoothConstantState extends Drawable.ConstantState {
        int mLayerType;
        Drawable.ConstantState mParent;
        float[] mRadii;
        float mRadius;
        int mStrokeColor;
        int mStrokeWidth;

        public SmoothConstantState() {
            this.mStrokeWidth = 0;
            this.mStrokeColor = 0;
            this.mLayerType = 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mParent.getChangingConfigurations();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            Resources resources = null;
            Object[] objArr = 0;
            if (this.mParent == null) {
                return null;
            }
            return new SmoothGradientDrawable(this, resources);
        }

        public void setConstantState(Drawable.ConstantState constantState) {
            this.mParent = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            if (this.mParent == null) {
                return null;
            }
            return new SmoothGradientDrawable(new SmoothConstantState(this), resources);
        }

        public SmoothConstantState(@NonNull SmoothConstantState smoothConstantState) {
            this.mStrokeWidth = 0;
            this.mStrokeColor = 0;
            this.mLayerType = 0;
            this.mRadius = smoothConstantState.mRadius;
            this.mRadii = smoothConstantState.mRadii;
            this.mStrokeWidth = smoothConstantState.mStrokeWidth;
            this.mStrokeColor = smoothConstantState.mStrokeColor;
            this.mParent = smoothConstantState.mParent;
            this.mLayerType = smoothConstantState.mLayerType;
        }
    }

    public SmoothGradientDrawable() {
        this.mHelper = new SmoothDrawHelper();
        this.mSavedLayer = new RectF();
        this.mLayer = new Rect();
        SmoothConstantState smoothConstantState = new SmoothConstantState();
        this.mSmoothConstantState = smoothConstantState;
        smoothConstantState.setConstantState(super.getConstantState());
    }

    @Override // android.graphics.drawable.GradientDrawable
    @RequiresApi(api = 21)
    public void setColor(@Nullable ColorStateList colorStateList) {
        GradientDrawable gradientDrawable = this.mParentDrawable;
        if (gradientDrawable != null) {
            gradientDrawable.setColor(colorStateList);
        } else {
            super.setColor(colorStateList);
        }
    }

    public SmoothGradientDrawable(GradientDrawable.Orientation orientation, @ColorInt int[] iArr) {
        super(orientation, iArr);
        this.mHelper = new SmoothDrawHelper();
        this.mSavedLayer = new RectF();
        this.mLayer = new Rect();
        SmoothConstantState smoothConstantState = new SmoothConstantState();
        this.mSmoothConstantState = smoothConstantState;
        smoothConstantState.setConstantState(super.getConstantState());
    }

    private SmoothGradientDrawable(SmoothConstantState smoothConstantState, Resources resources) {
        Drawable drawableNewDrawable;
        this.mHelper = new SmoothDrawHelper();
        this.mSavedLayer = new RectF();
        this.mLayer = new Rect();
        this.mSmoothConstantState = smoothConstantState;
        if (resources == null) {
            drawableNewDrawable = smoothConstantState.mParent.newDrawable();
        } else {
            drawableNewDrawable = smoothConstantState.mParent.newDrawable(resources);
        }
        this.mSmoothConstantState.setConstantState(drawableNewDrawable.getConstantState());
        this.mParentDrawable = (GradientDrawable) drawableNewDrawable;
        this.mHelper.setRadii(smoothConstantState.mRadii);
        this.mHelper.setRadius(smoothConstantState.mRadius);
        this.mHelper.setStrokeWidth(smoothConstantState.mStrokeWidth);
        this.mHelper.setStrokeColor(smoothConstantState.mStrokeColor);
    }
}
