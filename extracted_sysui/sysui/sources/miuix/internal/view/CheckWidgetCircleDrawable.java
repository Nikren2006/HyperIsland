package miuix.internal.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public class CheckWidgetCircleDrawable extends Drawable {
    private static final int PADDING = 1;
    private static final float STOKE_WIDTH = 2.0f;
    private boolean mHasStroke;
    private Paint mPaint;
    private float mScale;
    private int mStrokeDisableAlpha;
    private int mStrokeNormalAlpha;
    private Paint mStrokePaint;
    private int mUncheckedDisableAlpha;
    private int mUncheckedNormalAlpha;

    public CheckWidgetCircleDrawable(int i2, int i3, int i4) {
        this(i2, i3, i4, 0, 0, 0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int i2 = bounds.right;
        int i3 = bounds.left;
        int i4 = bounds.top;
        int i5 = bounds.bottom;
        int i6 = (i4 + i5) / 2;
        int iMin = Math.min(i2 - i3, i5 - i4) / 2;
        float f2 = (i2 + i3) / 2;
        float f3 = i6;
        float f4 = iMin;
        canvas.drawCircle(f2, f3, (this.mScale * f4) - 1.0f, this.mPaint);
        if (this.mHasStroke) {
            canvas.drawCircle(f2, f3, ((f4 * this.mScale) - 1.0f) - STOKE_WIDTH, this.mStrokePaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getScale() {
        return this.mScale;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
        if (this.mHasStroke) {
            if (i2 == this.mUncheckedNormalAlpha) {
                this.mStrokePaint.setAlpha(this.mStrokeNormalAlpha);
            } else if (i2 == this.mUncheckedDisableAlpha) {
                this.mStrokePaint.setAlpha(this.mStrokeDisableAlpha);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setScale(float f2) {
        this.mScale = f2;
    }

    public CheckWidgetCircleDrawable(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.mPaint = new Paint();
        this.mStrokePaint = new Paint();
        this.mScale = 1.0f;
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(i2);
        this.mUncheckedNormalAlpha = i3;
        this.mUncheckedDisableAlpha = i4;
        boolean z2 = i5 != 0;
        this.mHasStroke = z2;
        if (z2) {
            this.mStrokePaint.setAntiAlias(true);
            this.mStrokePaint.setColor(i5);
            this.mStrokePaint.setStyle(Paint.Style.STROKE);
            this.mStrokePaint.setStrokeWidth(STOKE_WIDTH);
        }
        this.mStrokeNormalAlpha = i6;
        this.mStrokeDisableAlpha = i7;
    }
}
