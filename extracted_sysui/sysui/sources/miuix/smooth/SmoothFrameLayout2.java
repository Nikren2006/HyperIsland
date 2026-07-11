package miuix.smooth;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SmoothFrameLayout2 extends FrameLayout {
    private static final String TAG = "SmoothFrameLayout2";
    private boolean mClip;
    private Path mClipPath;
    private RectF mLayer;
    private Paint mPaintSolid;
    private Paint mPaintStroke;
    private float[] mRadii;
    private float mRadius;
    private int mStrokeColor;
    private int mStrokeWidth;
    private float[] mTempRadii;
    private boolean mUseSmooth;

    public SmoothFrameLayout2(@NonNull Context context) {
        this(context, null);
    }

    private void clipInnerRoundRect(Canvas canvas) {
        this.mClipPath.reset();
        float f2 = this.mStrokeWidth * 0.5f;
        float[] fArr = this.mRadii;
        if (fArr == null) {
            Path path = this.mClipPath;
            RectF rectF = this.mLayer;
            float f3 = rectF.left + f2;
            float f4 = rectF.top + f2;
            float f5 = rectF.right - f2;
            float f6 = rectF.bottom - f2;
            float f7 = this.mRadius;
            path.addRoundRect(f3, f4, f5, f6, f7 - f2, f7 - f2, Path.Direction.CW);
        } else {
            float[] fArr2 = (float[]) fArr.clone();
            this.mTempRadii = fArr2;
            float[] fArr3 = this.mRadii;
            fArr2[0] = fArr3[0] - f2;
            fArr2[1] = fArr3[1] - f2;
            fArr2[2] = fArr3[2] - f2;
            fArr2[3] = fArr3[3] - f2;
            Path path2 = this.mClipPath;
            RectF rectF2 = this.mLayer;
            path2.addRoundRect(rectF2.left + f2, rectF2.top + f2, rectF2.right - f2, rectF2.bottom - f2, fArr2, Path.Direction.CW);
        }
        canvas.clipPath(this.mClipPath);
    }

    private void clipRoundRect(Canvas canvas) {
        this.mClipPath.reset();
        float[] fArr = this.mRadii;
        if (fArr == null) {
            Path path = this.mClipPath;
            RectF rectF = this.mLayer;
            float f2 = this.mRadius;
            path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        } else {
            this.mClipPath.addRoundRect(this.mLayer, fArr, Path.Direction.CW);
        }
        canvas.clipPath(this.mClipPath);
    }

    private void drawRoundRectStroke(Canvas canvas) {
        this.mClipPath.reset();
        float f2 = this.mStrokeWidth * 0.5f;
        float[] fArr = this.mRadii;
        if (fArr == null) {
            Path path = this.mClipPath;
            RectF rectF = this.mLayer;
            float f3 = rectF.left + f2;
            float f4 = rectF.top + f2;
            float f5 = rectF.right - f2;
            float f6 = rectF.bottom - f2;
            float f7 = this.mRadius;
            path.addRoundRect(f3, f4, f5, f6, f7 + f2, f7 + f2, Path.Direction.CW);
        } else {
            float[] fArr2 = (float[]) fArr.clone();
            this.mTempRadii = fArr2;
            float[] fArr3 = this.mRadii;
            fArr2[0] = fArr3[0] + f2;
            fArr2[1] = fArr3[1] + f2;
            fArr2[2] = fArr3[2] + f2;
            fArr2[3] = fArr3[3] + f2;
            Path path2 = this.mClipPath;
            RectF rectF2 = this.mLayer;
            path2.addRoundRect(rectF2.left + f2, rectF2.top + f2, rectF2.right - f2, rectF2.bottom - f2, fArr2, Path.Direction.CW);
        }
        canvas.drawPath(this.mClipPath, this.mPaintStroke);
    }

    private void setSmoothCornerEnable(boolean z2) {
        SmoothCornerHelper.setViewSmoothCornerEnable(this, z2);
    }

    private void updateBackground() {
        invalidateOutline();
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int iSave = canvas.save();
        if (!this.mClip) {
            clipRoundRect(canvas);
        }
        if (this.mStrokeWidth > 0) {
            int iSave2 = canvas.save();
            clipInnerRoundRect(canvas);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave2);
        } else {
            super.dispatchDraw(canvas);
        }
        if (!this.mClip && this.mStrokeWidth > 0) {
            drawRoundRectStroke(canvas);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        clipRoundRect(canvas);
        this.mClip = true;
        if (this.mStrokeWidth > 0) {
            int iSave2 = canvas.save();
            clipInnerRoundRect(canvas);
            super.draw(canvas);
            canvas.restoreToCount(iSave2);
        } else {
            super.draw(canvas);
        }
        if (this.mStrokeWidth > 0) {
            drawRoundRectStroke(canvas);
        }
        this.mClip = false;
        canvas.restoreToCount(iSave);
    }

    public float[] getCornerRadii() {
        return (float[]) this.mRadii.clone();
    }

    public float getCornerRadius() {
        return this.mRadius;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public boolean getUseSmooth() {
        return this.mUseSmooth;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mLayer.set(0.0f, 0.0f, i2, i3);
    }

    public void setCornerRadii(float[] fArr) {
        this.mRadii = fArr;
        updateBackground();
    }

    public void setCornerRadius(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mRadius = f2;
        this.mRadii = null;
        updateBackground();
    }

    public void setStrokeColor(int i2) {
        this.mStrokeColor = i2;
        updateBackground();
    }

    public void setStrokeWidth(int i2) {
        this.mStrokeWidth = i2;
        updateBackground();
    }

    public void setUseSmooth(boolean z2) {
        this.mUseSmooth = z2;
        setSmoothCornerEnable(z2);
    }

    public SmoothFrameLayout2(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmoothFrameLayout2(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLayer = new RectF();
        this.mClipPath = new Path();
        this.mClip = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MiuixSmoothFrameLayout2);
        this.mRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout2_android_radius, 0);
        int i3 = R.styleable.MiuixSmoothFrameLayout2_android_topLeftRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i3) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout2_android_topRightRadius) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout2_android_bottomRightRadius) || typedArrayObtainStyledAttributes.hasValue(R.styleable.MiuixSmoothFrameLayout2_android_bottomLeftRadius)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(i3, 0);
            float dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout2_android_topRightRadius, 0);
            float dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout2_android_bottomRightRadius, 0);
            float dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout2_android_bottomLeftRadius, 0);
            setCornerRadii(new float[]{dimensionPixelSize, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize4});
        }
        this.mStrokeWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MiuixSmoothFrameLayout2_miuix_strokeWidth, 0);
        this.mStrokeColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MiuixSmoothFrameLayout2_miuix_strokeColor, 0);
        this.mUseSmooth = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MiuixSmoothContainerDrawable2_miuix_useSmooth, true);
        Boolean bool = SmoothCornerHelper.FORCE_USE_SMOOTH;
        if (bool != null) {
            this.mUseSmooth = bool.booleanValue();
        }
        if (this.mUseSmooth) {
            setSmoothCornerEnable(true);
        }
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mPaintSolid = paint;
        paint.setFlags(1);
        Paint paint2 = new Paint();
        this.mPaintStroke = paint2;
        paint2.setFlags(1);
        this.mPaintStroke.setStyle(Paint.Style.STROKE);
        this.mPaintStroke.setStrokeWidth(this.mStrokeWidth);
        this.mPaintStroke.setColor(this.mStrokeColor);
    }
}
