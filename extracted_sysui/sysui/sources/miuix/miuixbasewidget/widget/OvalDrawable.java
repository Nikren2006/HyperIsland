package miuix.miuixbasewidget.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.OvalShape;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.FolmeObject;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.ColorProperty;
import miuix.device.DeviceUtils;

/* JADX INFO: loaded from: classes.dex */
class OvalDrawable extends Drawable implements FolmeObject {
    private static final ColorProperty<OvalDrawable> COLOR = new ColorProperty<OvalDrawable>("ovalBgColor") { // from class: miuix.miuixbasewidget.widget.OvalDrawable.1
        @Override // miuix.animation.property.ColorProperty, miuix.animation.property.IIntValueProperty
        public int getIntValue(OvalDrawable ovalDrawable) {
            return ovalDrawable.getBackgroundColor();
        }

        @Override // miuix.animation.property.ColorProperty, miuix.animation.property.IIntValueProperty
        public void setIntValue(OvalDrawable ovalDrawable, int i2) {
            ovalDrawable.setBackgroundColor(i2);
        }
    };
    private static final double COS_45;
    private static final float SHADOW_MULTIPLIER = 1.5f;
    private static final boolean USE_FOLME;
    private int mAlpha;
    private ColorStateList mBackground;
    private int mBackgroundColor;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mDrawStroke;

    @Nullable
    private Folme.ObjectFolmeImpl mFolmeAnimator;
    private boolean mInsetForPadding;
    private boolean mInsetForRadius;
    private boolean mIsStrokeShaderDirty;
    private boolean mIsStrokeShapeDirty;
    private float mPadding;

    @NonNull
    private final Paint mPaint;
    private int mStrokeColor;
    private float[] mStrokeGradientColorPositions;
    private int[] mStrokeGradientColors;
    private Paint mStrokePaint;
    private Shader mStrokeShader;
    private OvalShape mStrokeShape;
    private int mStrokeWidth;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode;

    static {
        USE_FOLME = (DeviceUtils.isMiuiLiteV2() || DeviceUtils.isLiteV1StockPlus() || DeviceUtils.isMiuiMiddle()) ? false : true;
        COS_45 = Math.cos(Math.toRadians(45.0d));
    }

    public OvalDrawable(ColorStateList colorStateList) {
        this(colorStateList, 0, 0);
    }

    public static float calculateHorizontalPadding(float f2, float f3, boolean z2) {
        return z2 ? (float) (((double) f2) + ((1.0d - COS_45) * ((double) f3))) : f2;
    }

    public static float calculateVerticalPadding(float f2, float f3, boolean z2) {
        return z2 ? (float) (((double) (f2 * SHADOW_MULTIPLIER)) + ((1.0d - COS_45) * ((double) f3))) : f2 * SHADOW_MULTIPLIER;
    }

    private void createStrokePaint() {
        Paint paint = new Paint(1);
        this.mStrokePaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setColor(this.mStrokeColor);
        this.mStrokePaint.setStrokeWidth(this.mStrokeWidth);
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    private void setBackground(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.mBackground = colorStateList;
        int colorForState = colorStateList.getColorForState(getState(), this.mBackground.getDefaultColor());
        this.mPaint.setColor(updateColorWithAlpha(colorForState, this.mAlpha));
        Folme.ObjectFolmeImpl objectFolmeImpl = this.mFolmeAnimator;
        if (objectFolmeImpl != null) {
            objectFolmeImpl.setTo(COLOR, Integer.valueOf(colorForState));
        } else {
            setBackgroundColor(colorForState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackgroundColor(int i2) {
        if (this.mBackgroundColor != i2) {
            this.mBackgroundColor = i2;
            this.mPaint.setColor(updateColorWithAlpha(i2, this.mAlpha));
            invalidateSelf();
        }
    }

    private void updateBounds(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.mBoundsF.set(rect.left, rect.top, rect.right, rect.bottom);
        this.mBoundsI.set(rect);
        if (this.mInsetForPadding) {
            this.mBoundsI.inset((int) Math.ceil(calculateHorizontalPadding(this.mPadding, rect.width() / 2.0f, this.mInsetForRadius)), (int) Math.ceil(calculateVerticalPadding(this.mPadding, rect.height() / 2.0f, this.mInsetForRadius)));
            this.mBoundsF.set(this.mBoundsI);
        }
    }

    private int updateColorWithAlpha(int i2, int i3) {
        return ((((i2 >>> 24) * i3) / 255) << 24) | (i2 & ViewCompat.MEASURED_SIZE_MASK);
    }

    private void updateStrokeShape() {
        this.mIsStrokeShapeDirty = false;
        this.mStrokeShape = new OvalShape();
        Rect bounds = getBounds();
        this.mStrokeShape.resize(bounds.width(), bounds.height());
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z2;
        Paint paint = this.mPaint;
        Paint paint2 = this.mStrokePaint;
        if (this.mTintFilter == null || paint.getColorFilter() != null) {
            z2 = false;
        } else {
            paint.setColorFilter(this.mTintFilter);
            z2 = true;
        }
        canvas.drawOval(this.mBoundsF, paint);
        if (paint2 != null && this.mDrawStroke) {
            if (this.mIsStrokeShaderDirty && this.mStrokeGradientColors != null) {
                this.mIsStrokeShaderDirty = false;
                this.mStrokeShader = new LinearGradient(0.0f, 0.0f, 0.0f, getBounds().height(), this.mStrokeGradientColors, this.mStrokeGradientColorPositions, Shader.TileMode.CLAMP);
            }
            if (this.mIsStrokeShapeDirty) {
                updateStrokeShape();
            }
            Shader shader = this.mStrokeShader;
            if (shader != null) {
                paint2.setShader(shader);
                paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            } else {
                paint2.setColor(this.mStrokeColor);
            }
            OvalShape ovalShape = this.mStrokeShape;
            if (ovalShape != null) {
                ovalShape.draw(canvas, this.mStrokePaint);
            }
        }
        if (z2) {
            paint.setColorFilter(null);
        }
    }

    public void enableDrawStroke(boolean z2) {
        this.mDrawStroke = z2;
    }

    @Override // miuix.animation.FolmeObject
    public Folme.ObjectFolmeImpl folme() {
        return this.mFolmeAnimator;
    }

    public ColorStateList getColor() {
        return this.mBackground;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setOval(this.mBoundsI);
    }

    public float getPadding() {
        return this.mPadding;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.mTint;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.mBackground) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateBounds(rect);
        OvalShape ovalShape = this.mStrokeShape;
        if (ovalShape != null) {
            ovalShape.resize(rect.width(), rect.height());
        }
        this.mIsStrokeShaderDirty = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.mBackground;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z2 = colorForState != this.mPaint.getColor();
        if (z2) {
            Folme.ObjectFolmeImpl objectFolmeImpl = this.mFolmeAnimator;
            if (objectFolmeImpl != null) {
                objectFolmeImpl.to(COLOR, Integer.valueOf(colorForState), new AnimConfig().setEase(FolmeEase.spring(1.0f, 0.2f)));
            } else {
                this.mPaint.setColor(colorForState);
            }
        }
        ColorStateList colorStateList2 = this.mTint;
        if (colorStateList2 == null || (mode = this.mTintMode) == null) {
            return z2;
        }
        this.mTintFilter = createTintFilter(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        int iMax = Math.max(0, Math.min(i2, 255));
        if (this.mAlpha != iMax) {
            this.mAlpha = iMax;
            this.mPaint.setColor(updateColorWithAlpha(this.mBackgroundColor, iMax));
        }
    }

    public void setColor(@Nullable ColorStateList colorStateList) {
        setBackground(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // miuix.animation.FolmeObject
    public void setFolmeImpl(Folme.ObjectFolmeImpl objectFolmeImpl) {
        this.mFolmeAnimator = objectFolmeImpl;
    }

    public void setPadding(float f2, boolean z2, boolean z3) {
        if (f2 == this.mPadding && this.mInsetForPadding == z2 && this.mInsetForRadius == z3) {
            return;
        }
        this.mPadding = f2;
        this.mInsetForPadding = z2;
        this.mInsetForRadius = z3;
        updateBounds(null);
        invalidateSelf();
    }

    public void setStrokeColor(int i2) {
        if (this.mStrokeColor != i2) {
            this.mStrokeColor = i2;
            this.mIsStrokeShapeDirty = true;
            Paint paint = this.mStrokePaint;
            if (paint != null) {
                paint.setColor(i2);
            }
            invalidateSelf();
        }
    }

    public void setStrokeColorGradientPositions(float[] fArr) {
        this.mStrokeGradientColorPositions = fArr;
        this.mIsStrokeShaderDirty = true;
    }

    public void setStrokeGradientColors(int[] iArr) {
        this.mStrokeGradientColors = iArr;
        this.mIsStrokeShaderDirty = true;
    }

    public void setStrokeWidth(int i2) {
        if (this.mStrokeWidth != i2) {
            this.mStrokeWidth = i2;
            this.mIsStrokeShapeDirty = true;
            Paint paint = this.mStrokePaint;
            if (paint != null) {
                paint.setStrokeWidth(i2);
            } else if (i2 > 0) {
                createStrokePaint();
            }
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.mTint = colorStateList;
        this.mTintFilter = createTintFilter(colorStateList, this.mTintMode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        this.mTintFilter = createTintFilter(this.mTint, mode);
        invalidateSelf();
    }

    public OvalDrawable(ColorStateList colorStateList, int i2, int i3) {
        this.mAlpha = 255;
        this.mInsetForPadding = false;
        this.mInsetForRadius = true;
        this.mDrawStroke = true;
        this.mStrokePaint = null;
        this.mTintMode = PorterDuff.Mode.SRC_IN;
        this.mPaint = new Paint(5);
        if (USE_FOLME) {
            this.mFolmeAnimator = Folme.use((FolmeObject) this);
        }
        this.mAlpha = 255;
        setBackground(colorStateList);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
        this.mStrokeWidth = i2;
        this.mStrokeColor = i3;
        if (i2 > 0) {
            createStrokePaint();
            this.mIsStrokeShapeDirty = true;
        }
    }
}
