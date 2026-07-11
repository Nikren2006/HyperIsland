package miui.systemui.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class CircularProgressBar extends android.view.View {
    private final Paint mBgPainter;
    private final long mDuration;
    private int mEndAngle;
    private boolean mIsAnimating;
    private boolean mIsCounterClockwise;
    private float mLastAngle;
    private float mMaxProgress;
    private float mPendingProgress;
    private float mProgressAngle;
    private ValueAnimator mProgressAnimator;
    private int mProgressColor;
    private final Paint mProgressPainter;
    private int mProgressbarBgColor;
    private int mProgressbarCircleBgColor;
    private final Paint mRingPainter;
    private int mStartAngle;
    private int mStrokeWidth;
    private float mTargetProgress;
    private final RectF rectF;

    public CircularProgressBar(Context context) {
        this(context, null);
    }

    private void drawProgress(Canvas canvas, RectF rectF) {
        canvas.save();
        canvas.drawArc(rectF, this.mStartAngle, this.mProgressAngle, false, this.mProgressPainter);
        canvas.restore();
    }

    private void drawProgressbarBg(Canvas canvas, RectF rectF, int i2) {
        canvas.drawArc(rectF, 0.0f, 360.0f, false, this.mRingPainter);
    }

    private void initBgPaint() {
        this.mBgPainter.setStyle(Paint.Style.FILL);
        this.mBgPainter.setColor(this.mProgressbarCircleBgColor);
    }

    private void initProgressPainter() {
        Paint paint = this.mRingPainter;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        this.mRingPainter.setStrokeWidth(this.mStrokeWidth);
        this.mRingPainter.setAntiAlias(true);
        Paint paint2 = this.mRingPainter;
        Paint.Cap cap = Paint.Cap.ROUND;
        paint2.setStrokeCap(cap);
        this.mProgressPainter.setStyle(style);
        this.mProgressPainter.setStrokeWidth(this.mStrokeWidth);
        this.mProgressPainter.setAntiAlias(true);
        this.mProgressPainter.setStrokeCap(cap);
    }

    private void initRingPainter() {
        this.mRingPainter.setStyle(Paint.Style.STROKE);
        this.mRingPainter.setStrokeWidth(this.mStrokeWidth);
        this.mRingPainter.setAntiAlias(true);
        this.mRingPainter.setColor(this.mProgressbarBgColor);
        this.mRingPainter.setStrokeCap(Paint.Cap.ROUND);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$progressAnimator$0(float f2, ValueAnimator valueAnimator) {
        this.mProgressAngle = (f2 * 1.0f * ((Float) valueAnimator.getAnimatedValue()).floatValue()) + this.mLastAngle;
        if (this.mProgressAnimator.isRunning()) {
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void progressAnimator(final float f2) {
        if (this.mIsAnimating) {
            this.mPendingProgress = f2;
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mProgressAnimator = valueAnimatorOfFloat;
        final float f3 = (360.0f * f2) / this.mMaxProgress;
        final float f4 = f3 - this.mLastAngle;
        valueAnimatorOfFloat.cancel();
        this.mProgressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: miui.systemui.widget.d
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f5927a.lambda$progressAnimator$0(f4, valueAnimator);
            }
        });
        this.mProgressAnimator.addListener(new AnimatorListenerAdapter() { // from class: miui.systemui.widget.CircularProgressBar.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                CircularProgressBar.this.mLastAngle = f3;
                CircularProgressBar.this.mIsAnimating = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                CircularProgressBar.this.mLastAngle = f3;
                CircularProgressBar.this.mIsAnimating = false;
                if (CircularProgressBar.this.mPendingProgress < 0.0f || CircularProgressBar.this.mPendingProgress == f2) {
                    return;
                }
                float f5 = CircularProgressBar.this.mPendingProgress;
                CircularProgressBar.this.mPendingProgress = -1.0f;
                CircularProgressBar.this.progressAnimator(f5);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                CircularProgressBar.this.mIsAnimating = true;
            }
        });
        if (this.mProgressAnimator.isStarted() || this.mProgressAnimator.isRunning()) {
            return;
        }
        this.mProgressAnimator.start();
    }

    public void cancelAnimation() {
        ValueAnimator valueAnimator = this.mProgressAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mProgressAnimator.removeAllListeners();
        }
    }

    public float getMaxProgress() {
        return this.mMaxProgress;
    }

    public float getTargetProgress() {
        return this.mTargetProgress;
    }

    public void isCCW(boolean z2) {
        if (z2) {
            this.mStartAngle = 270;
        } else {
            this.mStartAngle = 0;
        }
        this.mIsCounterClockwise = z2;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        RectF rectF = this.rectF;
        int i2 = this.mStrokeWidth;
        rectF.left = i2 / 2.0f;
        rectF.top = i2 / 2.0f;
        float f2 = width * 2;
        rectF.right = f2 - (i2 / 2.0f);
        rectF.bottom = f2 - (i2 / 2.0f);
        drawProgressbarBg(canvas, rectF, width);
        drawProgress(canvas, this.rectF);
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Progress value can not be less than 0");
        }
        float f3 = this.mMaxProgress;
        if (f2 > f3) {
            f2 = f3;
        }
        if (f2 == this.mTargetProgress) {
            return;
        }
        this.mTargetProgress = f2;
        progressAnimator(f2);
    }

    public void setUp(int i2, int i3, int i4, float f2) {
        this.mStrokeWidth = i2;
        this.mProgressbarBgColor = i3;
        this.mProgressColor = i4;
        this.mMaxProgress = f2;
        this.mProgressPainter.setStrokeWidth(i2);
        this.mRingPainter.setStrokeWidth(this.mStrokeWidth);
        this.mProgressPainter.setColor(this.mProgressColor);
        this.mRingPainter.setColor(this.mProgressbarBgColor);
    }

    public void updateColor(int i2, int i3, int i4) {
        if (i2 == -1 && i3 == -1 && i4 == -1) {
            return;
        }
        if (i2 != -1) {
            this.mProgressbarCircleBgColor = i2;
        }
        if (i3 != -1) {
            this.mProgressbarBgColor = i3;
        }
        if (i4 != -1) {
            this.mProgressColor = i4;
        }
        this.mBgPainter.setColor(this.mProgressbarCircleBgColor);
        this.mRingPainter.setColor(this.mProgressbarBgColor);
        this.mProgressPainter.setColor(this.mProgressColor);
        postInvalidate();
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircularProgressBar(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mStartAngle = 0;
        this.mEndAngle = 360;
        this.mDuration = 1000L;
        this.mBgPainter = new Paint(1);
        this.mRingPainter = new Paint(1);
        this.mProgressPainter = new Paint(1);
        this.rectF = new RectF();
        this.mIsAnimating = false;
        this.mPendingProgress = -1.0f;
        initProgressPainter();
    }
}
