package miuix.androidbasewidget.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import miuix.androidbasewidget.R;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes4.dex */
public class CircleProgressBar extends ProgressBar {
    private static final int ALPHA_NEED_DRAW_MIN_VALUE = 10;
    private static final int DEFAULT_FADE_OUT_DURATION = 300;
    private static final int DEFAULT_ROTATE_VELOCITY = 300;
    private Path mArcPath;
    private RectF mArcRect;
    private Bitmap mBitmapForSoftLayer;
    private Canvas mCanvasForSoftLayer;
    private Animator mChangeProgressAnimator;
    private int mCurrentLevel;
    private Drawable[] mLevelsBackDrawable;
    private Drawable[] mLevelsForeDrawable;
    private Drawable[] mLevelsMiddleDrawable;
    private Paint mPaint;
    private int mPrevAlpha;
    private int mPrevLevel;
    private OnProgressChangedListener mProgressChangedListener;
    private int[] mProgressLevels;
    private int mRotateVelocity;
    private Drawable mThumb;

    public interface OnProgressChangedListener {
        void onProgressChanged();
    }

    public CircleProgressBar(Context context) {
        this(context, null);
    }

    private int calcDuration(int i2) {
        return (i2 * 1000) / this.mRotateVelocity;
    }

    private void drawLayer(Canvas canvas, Drawable drawable, Drawable drawable2, Drawable drawable3, float f2, int i2) {
        if (drawable != null) {
            drawable.setAlpha(i2);
            drawable.draw(canvas);
        }
        if (canvas.isHardwareAccelerated()) {
            canvas.saveLayer(drawable3.getBounds().left, drawable3.getBounds().top, drawable3.getBounds().right, drawable3.getBounds().bottom, null, 31);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(drawable3.getBounds().width());
            this.mArcPath.reset();
            this.mArcPath.addArc(this.mArcRect, -90.0f, f2 * 360.0f);
            canvas.drawPath(this.mArcPath, this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setStrokeWidth(0.0f);
            drawable3.setAlpha(i2);
            drawable3.draw(canvas);
            canvas.restore();
        } else {
            if (this.mBitmapForSoftLayer == null) {
                this.mBitmapForSoftLayer = Bitmap.createBitmap(drawable3.getBounds().width(), drawable3.getBounds().height(), Bitmap.Config.ARGB_8888);
                this.mCanvasForSoftLayer = new Canvas(this.mBitmapForSoftLayer);
            }
            this.mBitmapForSoftLayer.eraseColor(0);
            this.mCanvasForSoftLayer.save();
            this.mCanvasForSoftLayer.translate(-drawable3.getBounds().left, -drawable3.getBounds().top);
            this.mCanvasForSoftLayer.drawArc(this.mArcRect, -90.0f, f2 * 360.0f, true, this.mPaint);
            drawable3.setAlpha(i2);
            drawable3.draw(this.mCanvasForSoftLayer);
            this.mCanvasForSoftLayer.restore();
            canvas.drawBitmap(this.mBitmapForSoftLayer, drawable3.getBounds().left, drawable3.getBounds().top, (Paint) null);
        }
        Drawable drawable4 = this.mThumb;
        if (drawable4 != null) {
            canvas.save();
            int width = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2;
            int height = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 2;
            int intrinsicWidth = drawable4.getIntrinsicWidth();
            int intrinsicHeight = drawable4.getIntrinsicHeight();
            canvas.rotate((getProgress() * 360.0f) / getMax(), width, height);
            int i3 = intrinsicWidth / 2;
            int i4 = intrinsicHeight / 2;
            drawable4.setBounds(width - i3, height - i4, width + i3, height + i4);
            drawable4.draw(canvas);
            canvas.restore();
        }
        if (drawable2 != null) {
            drawable2.setAlpha(i2);
            drawable2.draw(canvas);
        }
    }

    private Drawable getBackDrawable(int i2) {
        Drawable[] drawableArr = this.mLevelsBackDrawable;
        if (drawableArr == null) {
            return null;
        }
        return drawableArr[i2];
    }

    private Drawable[] getDrawables(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        Resources resources = getContext().getResources();
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            Drawable drawable = resources.getDrawable(iArr[i2]);
            drawableArr[i2] = drawable;
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawableArr[i2].getIntrinsicHeight());
        }
        return drawableArr;
    }

    private Drawable getForeDrawable(int i2) {
        Drawable[] drawableArr = this.mLevelsForeDrawable;
        if (drawableArr == null) {
            return null;
        }
        return drawableArr[i2];
    }

    private int getIntrinsicHeight() {
        Drawable middleDrawable = getMiddleDrawable(0);
        if (middleDrawable == null) {
            return 0;
        }
        int intrinsicHeight = middleDrawable.getIntrinsicHeight();
        Drawable[] drawableArr = this.mLevelsForeDrawable;
        if (drawableArr != null) {
            intrinsicHeight = Math.max(intrinsicHeight, drawableArr[0].getIntrinsicHeight());
        }
        Drawable[] drawableArr2 = this.mLevelsBackDrawable;
        return drawableArr2 != null ? Math.max(intrinsicHeight, drawableArr2[0].getIntrinsicHeight()) : intrinsicHeight;
    }

    private int getIntrinsicWidth() {
        Drawable middleDrawable = getMiddleDrawable(0);
        if (middleDrawable == null) {
            return 0;
        }
        int intrinsicWidth = middleDrawable.getIntrinsicWidth();
        Drawable[] drawableArr = this.mLevelsForeDrawable;
        if (drawableArr != null) {
            intrinsicWidth = Math.max(intrinsicWidth, drawableArr[0].getIntrinsicWidth());
        }
        Drawable[] drawableArr2 = this.mLevelsBackDrawable;
        return drawableArr2 != null ? Math.max(intrinsicWidth, drawableArr2[0].getIntrinsicWidth()) : intrinsicWidth;
    }

    private Drawable getMiddleDrawable(int i2) {
        Drawable[] drawableArr = this.mLevelsMiddleDrawable;
        if (drawableArr == null) {
            return null;
        }
        return drawableArr[i2];
    }

    private float getRate() {
        return getProgress() / getMax();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int progressLevelCount = getProgressLevelCount();
        for (int i2 = 0; i2 < progressLevelCount; i2++) {
            Drawable[] drawableArr = this.mLevelsBackDrawable;
            if (drawableArr != null) {
                drawableArr[i2].setState(getDrawableState());
            }
            Drawable[] drawableArr2 = this.mLevelsMiddleDrawable;
            if (drawableArr2 != null) {
                drawableArr2[i2].setState(getDrawableState());
            }
            Drawable[] drawableArr3 = this.mLevelsForeDrawable;
            if (drawableArr3 != null) {
                drawableArr3[i2].setState(getDrawableState());
            }
        }
        invalidate();
    }

    public int getPrevAlpha() {
        return this.mPrevAlpha;
    }

    public int getProgressLevelCount() {
        int[] iArr = this.mProgressLevels;
        if (iArr == null) {
            return 1;
        }
        return 1 + iArr.length;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        drawLayer(canvas, getBackDrawable(this.mCurrentLevel), getForeDrawable(this.mCurrentLevel), getMiddleDrawable(this.mCurrentLevel), getRate(), 255 - this.mPrevAlpha);
        if (this.mPrevAlpha >= 10) {
            drawLayer(canvas, getBackDrawable(this.mPrevLevel), getForeDrawable(this.mPrevLevel), getMiddleDrawable(this.mPrevLevel), getRate(), this.mPrevAlpha);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i2, int i3) {
        setMeasuredDimension(getIntrinsicWidth(), getIntrinsicHeight());
    }

    public void setDrawablesForLevels(Drawable[] drawableArr, Drawable[] drawableArr2, Drawable[] drawableArr3) {
        this.mLevelsBackDrawable = drawableArr;
        this.mLevelsMiddleDrawable = drawableArr2;
        this.mLevelsForeDrawable = drawableArr3;
        if (drawableArr != null) {
            for (Drawable drawable : drawableArr) {
                drawable.mutate();
            }
        }
        if (drawableArr2 != null) {
            for (Drawable drawable2 : drawableArr2) {
                drawable2.mutate();
            }
        }
        if (drawableArr3 != null) {
            for (Drawable drawable3 : drawableArr3) {
                drawable3.mutate();
            }
        }
        if (drawableArr2 != null) {
            for (Drawable drawable4 : drawableArr2) {
                if (drawable4 instanceof BitmapDrawable) {
                    ((BitmapDrawable) drawable4).getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                } else {
                    if (!(drawable4 instanceof NinePatchDrawable)) {
                        throw new IllegalArgumentException("'middles' must a bitmap or nine patch drawable.");
                    }
                    ((NinePatchDrawable) drawable4).getPaint().setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                }
            }
            this.mArcRect = new RectF(drawableArr2[0].getBounds().left - 5, drawableArr2[0].getBounds().top - 5, drawableArr2[0].getBounds().right + 5, drawableArr2[0].getBounds().bottom + 5);
        }
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.mProgressChangedListener = onProgressChangedListener;
    }

    public void setPrevAlpha(int i2) {
        this.mPrevAlpha = i2;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i2) {
        int length;
        try {
            super.setProgress(i2);
            int[] iArr = this.mProgressLevels;
            if (iArr == null) {
                length = 0;
            } else {
                length = iArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        i3 = -1;
                        break;
                    } else if (i2 < this.mProgressLevels[i3]) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i3 != -1) {
                    length = i3;
                }
            }
            int i4 = this.mCurrentLevel;
            if (length != i4) {
                this.mPrevLevel = i4;
                this.mCurrentLevel = length;
                setPrevAlpha(255);
                ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "prevAlpha", 0);
                objectAnimatorOfInt.setDuration(300L);
                objectAnimatorOfInt.setInterpolator(new LinearInterpolator());
                objectAnimatorOfInt.start();
            }
            OnProgressChangedListener onProgressChangedListener = this.mProgressChangedListener;
            if (onProgressChangedListener != null) {
                onProgressChangedListener.onProgressChanged();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setProgressByAnimator(int i2) {
        setProgressByAnimator(i2, null);
    }

    public void setProgressLevels(int[] iArr) {
        this.mProgressLevels = iArr;
    }

    public void setRotateVelocity(int i2) {
        this.mRotateVelocity = i2;
    }

    public void setThumb(int i2) {
        setThumb(getResources().getDrawable(i2));
    }

    public void stopProgressAnimator() {
        Animator animator = this.mChangeProgressAnimator;
        if (animator == null || !animator.isRunning()) {
            return;
        }
        this.mChangeProgressAnimator.cancel();
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setProgressByAnimator(int i2, Animator.AnimatorListener animatorListener) {
        stopProgressAnimator();
        int iAbs = Math.abs((int) (((i2 - getProgress()) / getMax()) * 360.0f));
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "progress", i2);
        this.mChangeProgressAnimator = objectAnimatorOfInt;
        objectAnimatorOfInt.setDuration(calcDuration(iAbs));
        this.mChangeProgressAnimator.setInterpolator(getInterpolator());
        if (animatorListener != null) {
            this.mChangeProgressAnimator.addListener(animatorListener);
        }
        this.mChangeProgressAnimator.start();
    }

    public void setThumb(Drawable drawable) {
        this.mThumb = drawable;
    }

    public CircleProgressBar(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mArcPath = new Path();
        this.mRotateVelocity = 300;
        setIndeterminate(false);
        int iResolveColor = AttributeResolver.resolveColor(context, R.attr.circleProgressBarColor, context.getResources().getColor(ViewUtils.isNightMode(context) ? R.color.miuix_appcompat_progressbar_circle_color_dark : R.color.miuix_appcompat_progressbar_circle_color_light));
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(iResolveColor);
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.NORMAL).handleHoverOf(this, new AnimConfig[0]);
    }

    public void setDrawablesForLevels(int[] iArr, int[] iArr2, int[] iArr3) {
        setDrawablesForLevels(getDrawables(iArr), getDrawables(iArr2), getDrawables(iArr3));
    }
}
