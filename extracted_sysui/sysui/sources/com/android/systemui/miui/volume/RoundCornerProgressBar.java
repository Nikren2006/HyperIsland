package com.android.systemui.miui.volume;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RoundCornerProgressBar extends ProgressBar {
    private Paint mBackgroundPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private int mPathWidth;
    private int mProgressColor;
    private Paint mProgressPaint;
    private float mRadius;
    private final RectF mRectF;
    private float mStartOffsetPercent;

    public RoundCornerProgressBar(Context context) {
        this(context, null);
    }

    private void drawBackground(Canvas canvas) {
        RectF rectF = this.mRectF;
        float f2 = this.mRadius;
        canvas.drawRoundRect(rectF, f2, f2, this.mBackgroundPaint);
    }

    private void drawProgress(Canvas canvas) {
        Path path = this.mPath;
        RectF rectF = this.mRectF;
        float f2 = this.mRadius;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        Path path2 = new Path();
        this.mPathMeasure.setPath(this.mPath, false);
        float length = this.mPathMeasure.getLength() * this.mStartOffsetPercent;
        float length2 = (this.mPathMeasure.getLength() * (getProgress() / getMax())) + length;
        if (length2 < this.mPathMeasure.getLength()) {
            if (this.mPathMeasure.getSegment(length, length2, path2, true)) {
                canvas.drawPath(path2, this.mProgressPaint);
                return;
            }
            return;
        }
        PathMeasure pathMeasure = this.mPathMeasure;
        if (pathMeasure.getSegment(length, pathMeasure.getLength(), path2, true)) {
            canvas.drawPath(path2, this.mProgressPaint);
        }
        PathMeasure pathMeasure2 = this.mPathMeasure;
        if (pathMeasure2.getSegment(0.0f, length2 - pathMeasure2.getLength(), path2, true)) {
            canvas.drawPath(path2, this.mProgressPaint);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDraw(Canvas canvas) {
        float f2 = this.mPathWidth / 2;
        this.mRectF.set(f2, f2, getWidth() - r0, getHeight() - r0);
        drawBackground(canvas);
        drawProgress(canvas);
    }

    public void setProgressColor(int i2) {
        this.mProgressColor = i2;
        Paint paint = this.mProgressPaint;
        if (paint != null) {
            paint.setColor(i2);
        }
    }

    public RoundCornerProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundCornerProgressBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mProgressColor = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerProgressBar);
        this.mBackgroundPaint = new Paint();
        this.mProgressPaint = new Paint();
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.RoundCornerProgressBar_backgroundBoundColor, -7829368);
        int i3 = this.mProgressColor;
        this.mProgressColor = i3 == 0 ? typedArrayObtainStyledAttributes.getColor(R.styleable.RoundCornerProgressBar_progressColor, -65536) : i3;
        this.mPathWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundCornerProgressBar_pathWidth, 14);
        this.mRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.RoundCornerProgressBar_radius, 41.0f);
        this.mStartOffsetPercent = typedArrayObtainStyledAttributes.getFloat(R.styleable.RoundCornerProgressBar_startOffset, 0.0f);
        this.mBackgroundPaint.setColor(color);
        Paint paint = this.mBackgroundPaint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStrokeWidth(this.mPathWidth);
        this.mProgressPaint.setColor(this.mProgressColor);
        this.mProgressPaint.setStyle(style);
        this.mProgressPaint.setAntiAlias(true);
        this.mProgressPaint.setStrokeWidth(this.mPathWidth);
        float f2 = this.mPathWidth / 2;
        this.mRectF = new RectF(f2, f2, getWidth() - r3, getHeight() - r3);
        this.mPath = new Path();
        this.mPathMeasure = new PathMeasure();
    }
}
