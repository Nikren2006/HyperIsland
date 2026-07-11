package miuix.miuixbasewidget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.property.ViewProperty;
import miuix.internal.util.ViewUtils;
import miuix.miuixbasewidget.R;

/* JADX INFO: loaded from: classes.dex */
public class PageIndicator extends View {
    private ViewProperty mBackgroundDrawableAlpha;
    private int mCurrentPosition;
    private float mCurrentPositionOffset;
    private final ArgbEvaluator mEvaluator;
    private int mHorizontalPadding;
    private int mIndicatorCount;
    private float mIndicatorGap;
    private float mIndicatorInterval;
    private Paint mIndicatorPaint;
    private float mIndicatorRadius;
    private boolean mIsRtl;
    private float mLargeSizeGap;
    private int mLargeSizeHorizontalPadding;
    private float mLargeSizeRadius;
    private float mLargeSizeVerticalPadding;
    private boolean mNeedBackground;
    private OnPageChangeListener mOnPageChangeListener;
    private boolean mPageScrolling;
    private int mSelectedColor;
    private int mSize;
    private float mSmallSizeGap;
    private int mSmallSizeHorizontalPadding;
    private float mSmallSizeRadius;
    private float mSmallSizeVerticalPadding;
    private int mUnselectedColor;
    private float mVerticalPadding;

    public interface OnPageChangeListener {
        void onPageSelected(int i2);
    }

    public static class Size {
        public static final int LARGE = 1;
        public static final int SMALL = 0;
    }

    public PageIndicator(Context context) {
        this(context, null);
    }

    private Paint createIndicatorPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }

    private void drawIndicator(Canvas canvas, float f2, float f3, float f4, int i2) {
        this.mIndicatorPaint.setColor(i2);
        canvas.drawCircle(f2, f3, f4, this.mIndicatorPaint);
    }

    private void notifyUpdate() {
        if (this.mSize == 0) {
            this.mIndicatorRadius = this.mSmallSizeRadius;
            this.mVerticalPadding = this.mSmallSizeVerticalPadding;
            this.mHorizontalPadding = this.mSmallSizeHorizontalPadding;
            this.mIndicatorGap = this.mSmallSizeGap;
        } else {
            this.mIndicatorRadius = this.mLargeSizeRadius;
            this.mVerticalPadding = this.mLargeSizeVerticalPadding;
            this.mHorizontalPadding = this.mLargeSizeHorizontalPadding;
            this.mIndicatorGap = this.mLargeSizeGap;
        }
        this.mIndicatorInterval = (this.mIndicatorRadius * 2.0f) + this.mIndicatorGap;
        requestLayout();
    }

    public int getIndicatorCount() {
        return this.mIndicatorCount;
    }

    public boolean isBackgroundVisible() {
        return this.mNeedBackground;
    }

    public void notifyPageScrolling(boolean z2) {
        this.mPageScrolling = z2;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        this.mIsRtl = zIsLayoutRtl;
        float f2 = this.mHorizontalPadding;
        float f3 = this.mIndicatorRadius;
        float f4 = f2 + f3;
        float f5 = this.mVerticalPadding + f3;
        int i2 = 0;
        if (!zIsLayoutRtl) {
            while (i2 < this.mIndicatorCount) {
                int i3 = this.mCurrentPosition;
                drawIndicator(canvas, f4, f5, this.mIndicatorRadius, i2 == i3 ? ((Integer) this.mEvaluator.evaluate(this.mCurrentPositionOffset, Integer.valueOf(this.mSelectedColor), Integer.valueOf(this.mUnselectedColor))).intValue() : i2 == i3 + 1 ? ((Integer) this.mEvaluator.evaluate(this.mCurrentPositionOffset, Integer.valueOf(this.mUnselectedColor), Integer.valueOf(this.mSelectedColor))).intValue() : this.mUnselectedColor);
                f4 += this.mIndicatorInterval;
                i2++;
            }
            return;
        }
        while (true) {
            int i4 = this.mIndicatorCount;
            if (i2 >= i4) {
                return;
            }
            int i5 = this.mCurrentPosition;
            drawIndicator(canvas, f4, f5, this.mIndicatorRadius, i2 == (i4 - i5) + (-1) ? ((Integer) this.mEvaluator.evaluate(this.mCurrentPositionOffset, Integer.valueOf(this.mSelectedColor), Integer.valueOf(this.mUnselectedColor))).intValue() : i2 == (i4 - i5) + (-2) ? ((Integer) this.mEvaluator.evaluate(this.mCurrentPositionOffset, Integer.valueOf(this.mUnselectedColor), Integer.valueOf(this.mSelectedColor))).intValue() : this.mUnselectedColor);
            f4 += this.mIndicatorInterval;
            i2++;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        float f2 = (this.mIndicatorCount - 1) * this.mIndicatorInterval;
        float f3 = this.mIndicatorRadius;
        setMeasuredDimension((int) (f2 + (f3 * 2.0f) + (this.mHorizontalPadding * 2)), (int) ((f3 * 2.0f) + (this.mVerticalPadding * 2.0f)));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            r10 = this;
            int r0 = r11.getAction()
            if (r0 != 0) goto L66
            boolean r0 = r10.mPageScrolling
            if (r0 != 0) goto L66
            float r0 = r11.getX()
            int r1 = r10.mCurrentPosition
            boolean r2 = r10.mIsRtl
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 1
            if (r2 == 0) goto L3e
            int r2 = r10.mHorizontalPadding
            float r2 = (float) r2
            int r5 = r10.mIndicatorCount
            int r6 = r5 - r1
            int r6 = r6 - r4
            float r6 = (float) r6
            float r7 = r10.mIndicatorRadius
            float r8 = r7 * r3
            float r9 = r10.mIndicatorGap
            float r8 = r8 + r9
            float r6 = r6 * r8
            float r2 = r2 + r6
            float r7 = r7 * r3
            float r7 = r7 + r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L35
            int r5 = r5 - r4
            if (r1 >= r5) goto L35
        L32:
            int r1 = r1 + 1
            goto L5e
        L35:
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 <= 0) goto L5e
            if (r1 <= 0) goto L5e
        L3b:
            int r1 = r1 + (-1)
            goto L5e
        L3e:
            int r2 = r10.mHorizontalPadding
            float r2 = (float) r2
            float r5 = (float) r1
            float r6 = r10.mIndicatorRadius
            float r7 = r6 * r3
            float r8 = r10.mIndicatorGap
            float r7 = r7 + r8
            float r5 = r5 * r7
            float r2 = r2 + r5
            float r6 = r6 * r3
            float r6 = r6 + r2
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 >= 0) goto L54
            if (r1 <= 0) goto L54
            goto L3b
        L54:
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 <= 0) goto L5e
            int r0 = r10.mIndicatorCount
            int r0 = r0 - r4
            if (r1 >= r0) goto L5e
            goto L32
        L5e:
            miuix.miuixbasewidget.widget.PageIndicator$OnPageChangeListener r0 = r10.mOnPageChangeListener
            if (r0 == 0) goto L66
            r0.onPageSelected(r1)
            return r4
        L66:
            boolean r10 = super.onTouchEvent(r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.miuixbasewidget.widget.PageIndicator.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBackgroundVisible(boolean z2) {
        if (getBackground() == null || this.mNeedBackground == z2) {
            return;
        }
        this.mNeedBackground = z2;
        setBackgroundVisibleInternal(z2);
    }

    public void setBackgroundVisibleInternal(boolean z2) {
        if (z2) {
            Folme.use((View) this).to(this.mBackgroundDrawableAlpha, Float.valueOf(255.0f), new AnimConfig().setEase(FolmeEase.sinOut(300L)));
        } else {
            Folme.use((View) this).to(this.mBackgroundDrawableAlpha, Float.valueOf(1.0f), new AnimConfig().setEase(FolmeEase.sinOut(100L)));
        }
    }

    public void setCurrentPosition(int i2) {
        if (this.mCurrentPosition != i2) {
            this.mCurrentPosition = i2;
            invalidate();
        }
    }

    public void setCurrentPositionOffset(float f2) {
        if (this.mCurrentPositionOffset != f2) {
            this.mCurrentPositionOffset = f2;
            invalidate();
        }
    }

    public void setIndicatorCount(int i2) {
        if (i2 >= 0) {
            this.mIndicatorCount = i2;
            requestLayout();
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setSize(int i2) {
        if (i2 == 0 || i2 == 1) {
            this.mSize = i2;
            notifyUpdate();
        }
    }

    public PageIndicator(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PageIndicator(Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.Widget_PageIndicator_DayNight);
    }

    public PageIndicator(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mEvaluator = ArgbEvaluator.getInstance();
        this.mPageScrolling = false;
        this.mBackgroundDrawableAlpha = new ViewProperty("backgroundDrawableAlpha", 1.0f) { // from class: miuix.miuixbasewidget.widget.PageIndicator.1
            @Override // miuix.animation.property.FloatProperty
            public float getValue(View view) {
                if (view.getBackground() != null) {
                    return r0.getAlpha();
                }
                return 0.0f;
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(View view, float f2) {
                Drawable background;
                if (f2 < 0.0f || f2 > 255.0f || (background = view.getBackground()) == null) {
                    return;
                }
                background.setAlpha((int) f2);
            }
        };
        this.mSmallSizeHorizontalPadding = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_page_indicator_small_size_horizontal_padding);
        this.mLargeSizeHorizontalPadding = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_page_indicator_large_size_horizontal_padding);
        this.mSmallSizeVerticalPadding = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_page_indicator_small_size__vertical_padding);
        this.mLargeSizeVerticalPadding = context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_page_indicator_large_size__vertical_padding);
        this.mIndicatorPaint = createIndicatorPaint();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PageIndicator, i2, i3);
        this.mIndicatorCount = typedArrayObtainStyledAttributes.getInt(R.styleable.PageIndicator_totalCount, 0);
        this.mNeedBackground = typedArrayObtainStyledAttributes.getBoolean(R.styleable.PageIndicator_needBackground, false);
        this.mSelectedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.PageIndicator_selectedColor, 0);
        this.mUnselectedColor = typedArrayObtainStyledAttributes.getColor(R.styleable.PageIndicator_unselectedColor, 0);
        this.mSize = typedArrayObtainStyledAttributes.getInt(R.styleable.PageIndicator_sizeLevel, 0);
        this.mSmallSizeRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.PageIndicator_smallSizeRadius, 0.0f);
        this.mLargeSizeRadius = typedArrayObtainStyledAttributes.getDimension(R.styleable.PageIndicator_largeSizeRadius, 0.0f);
        this.mSmallSizeGap = typedArrayObtainStyledAttributes.getDimension(R.styleable.PageIndicator_smallSizeGap, 0.0f);
        this.mLargeSizeGap = typedArrayObtainStyledAttributes.getDimension(R.styleable.PageIndicator_largeSizeGap, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        setSize(this.mSize);
        if (getBackground() == null || this.mNeedBackground) {
            return;
        }
        getBackground().setAlpha(0);
    }
}
