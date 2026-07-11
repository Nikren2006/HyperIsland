package miuix.smooth;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.smooth.internal.SmoothDrawHelper;

/* JADX INFO: loaded from: classes5.dex */
public class SmoothFrameLayout extends FrameLayout {
    private static final PorterDuffXfermode XFERMODE_DST_OUT = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    private SmoothDrawHelper mHelper;
    private Rect mLayer;
    private RectF mSavedLayer;

    public SmoothFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    private void updateBackground() {
        updateBounds();
        invalidateOutline();
        invalidate();
    }

    private void updateBounds() {
        this.mHelper.onBoundsChange(this.mLayer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int iSaveLayer = getLayerType() != 2 ? canvas.saveLayer(this.mSavedLayer, null, 31) : -1;
        super.dispatchDraw(canvas);
        this.mHelper.drawMask(canvas, XFERMODE_DST_OUT);
        if (getLayerType() != 2) {
            canvas.restoreToCount(iSaveLayer);
        }
        this.mHelper.drawStroke(canvas);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int iSaveLayer = getLayerType() != 2 ? canvas.saveLayer(this.mSavedLayer, null, 31) : -1;
        super.draw(canvas);
        this.mHelper.drawMask(canvas, XFERMODE_DST_OUT);
        if (getLayerType() != 2) {
            canvas.restoreToCount(iSaveLayer);
        }
        this.mHelper.drawStroke(canvas);
    }

    public float[] getCornerRadii() {
        if (this.mHelper.getRadii() == null) {
            return null;
        }
        return (float[]) this.mHelper.getRadii().clone();
    }

    public float getCornerRadius() {
        return this.mHelper.getRadius();
    }

    public int getStrokeColor() {
        return this.mHelper.getStrokeColor();
    }

    public int getStrokeWidth() {
        return this.mHelper.getStrokeWidth();
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mLayer.set(0, 0, i2, i3);
        this.mSavedLayer.set(0.0f, 0.0f, i2, i3);
        updateBounds();
    }

    public void setCornerRadii(float[] fArr) {
        this.mHelper.setRadii(fArr);
        if (fArr == null) {
            this.mHelper.setRadius(0.0f);
        }
        updateBackground();
    }

    public void setCornerRadius(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mHelper.setRadius(f2);
        this.mHelper.setRadii(null);
        updateBackground();
    }

    public void setStrokeColor(int i2) {
        if (this.mHelper.getStrokeColor() != i2) {
            this.mHelper.setStrokeColor(i2);
            updateBackground();
        }
    }

    public void setStrokeWidth(int i2) {
        if (this.mHelper.getStrokeWidth() != i2) {
            this.mHelper.setStrokeWidth(i2);
            updateBackground();
        }
    }

    public SmoothFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmoothFrameLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLayer = new Rect();
        this.mSavedLayer = new RectF();
        this.mHelper = new SmoothDrawHelper();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MiuixSmoothFrameLayout);
        setCornerRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout_android_radius, 0));
        int i3 = R.styleable.MiuixSmoothFrameLayout_android_topLeftRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i3) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout_android_topRightRadius) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout_android_bottomRightRadius) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout_android_bottomLeftRadius)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(i3, 0);
            float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout_android_topRightRadius, 0);
            float dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout_android_bottomRightRadius, 0);
            float dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout_android_bottomLeftRadius, 0);
            setCornerRadii(new float[]{dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize4});
        }
        setStrokeWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout_miuix_strokeWidth, 0));
        setStrokeColor(typedArrayObtainStyledAttributes.getColor(R.styleable.MiuixSmoothFrameLayout_miuix_strokeColor, 0));
        setLayerType(typedArrayObtainStyledAttributes.getColor(R.styleable.MiuixSmoothFrameLayout_android_layerType, 2), null);
        typedArrayObtainStyledAttributes.recycle();
    }
}
