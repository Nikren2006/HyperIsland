package miui.systemui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class CapsuleProgressBar extends android.view.View {
    private ValueAnimator animator;
    private Path capsulePath;
    private float cornerRadius;
    private float currentProgress;
    private float maxProgress;
    private float pathLength;
    private PathMeasure pathMeasure;
    private int progressColor;
    private Paint progressPaint;
    private Path progressPath;
    private RectF rectBounds;
    private int strokeWidth;
    private int trackColor;
    private Paint trackPaint;

    public CapsuleProgressBar(Context context) {
        this(context, null);
    }

    private void init() {
        Paint paint = new Paint(1);
        this.trackPaint = paint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.trackPaint.setStrokeWidth(this.strokeWidth);
        this.trackPaint.setColor(this.trackColor);
        Paint paint2 = this.trackPaint;
        Paint.Cap cap = Paint.Cap.ROUND;
        paint2.setStrokeCap(cap);
        Paint paint3 = new Paint(1);
        this.progressPaint = paint3;
        paint3.setStyle(style);
        this.progressPaint.setStrokeWidth(this.strokeWidth);
        this.progressPaint.setColor(this.progressColor);
        this.progressPaint.setStrokeCap(cap);
        this.capsulePath = new Path();
        this.progressPath = new Path();
        this.pathMeasure = new PathMeasure();
        this.rectBounds = new RectF();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setProgress$0(ValueAnimator valueAnimator) {
        this.currentProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    public void cancelAnimation() {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.animator.removeAllListeners();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.drawPath(this.capsulePath, this.trackPaint);
        this.progressPath.reset();
        this.pathMeasure.getSegment(0.0f, this.pathLength * (this.currentProgress / this.maxProgress), this.progressPath, true);
        canvas.drawPath(this.progressPath, this.progressPaint);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        float f2 = this.strokeWidth / 2.0f;
        float f3 = this.cornerRadius;
        if (f3 <= 0.0f) {
            f3 = i3 / 2.0f;
        }
        this.rectBounds.set(f2, f2, i2 - f2, i3 - f2);
        this.capsulePath.reset();
        this.capsulePath.addRoundRect(this.rectBounds, f3, f3, Path.Direction.CW);
        this.pathMeasure.setPath(this.capsulePath, false);
        this.pathLength = this.pathMeasure.getLength();
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        float f3 = this.maxProgress;
        if (f2 > f3) {
            f2 = f3;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.currentProgress, f2);
        this.animator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.widget.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f5926a.lambda$setProgress$0(valueAnimator);
            }
        });
        this.animator.start();
    }

    public void setUp(int i2, int i3, int i4, float f2, float f3) {
        this.strokeWidth = i2;
        this.trackColor = i3;
        this.progressColor = i4;
        this.maxProgress = f2;
        this.cornerRadius = f3;
        init();
        invalidate();
    }

    public void updateColor(int i2, int i3, int i4) {
        if (i3 != -1) {
            this.trackColor = i3;
        }
        if (i4 != -1) {
            this.progressColor = i4;
        }
        this.trackPaint.setColor(this.trackColor);
        this.progressPaint.setColor(this.progressColor);
        postInvalidate();
    }

    public CapsuleProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.strokeWidth = 12;
        this.cornerRadius = -1.0f;
        this.progressColor = -16711936;
        this.trackColor = -3355444;
        this.maxProgress = 100.0f;
        this.currentProgress = 0.0f;
        init();
    }
}
