package miuix.pickerwidget.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import miuix.internal.util.ViewUtils;
import miuix.pickerwidget.R;

/* JADX INFO: loaded from: classes5.dex */
public class ProperPaddingViewGroup extends ViewGroup {
    private static final int ENABLE_SMALL_HORIZONTAL_PADDING_WIDTH_DP = 340;
    private static final int MIN_NORMALLY_SHOW_WIDTH_DP = 290;
    private static final int UNDEFINED_PADDING = Integer.MIN_VALUE;
    private final float DENSITY;
    private View mChildView;
    private boolean mFixedHorizontalPadding;
    private int mFixedHorizontalPaddingEnd;
    private int mFixedHorizontalPaddingStart;
    private final int mHorizontalPaddingEnd;
    private final int mHorizontalPaddingStart;
    private int mPaddingEnd;
    private int mPaddingStart;
    private final int mSmallHorizontalPaddingEnd;
    private final int mSmallHorizontalPaddingStart;

    public ProperPaddingViewGroup(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("Only one child view can be added into the ProperPaddingViewGroup!");
        }
        this.mChildView = getChildAt(0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = ViewUtils.isLayoutRtl(this) ? this.mPaddingEnd : this.mPaddingStart;
        this.mChildView.layout(i6, 0, this.mChildView.getMeasuredWidth() + i6, this.mChildView.getMeasuredHeight());
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        float f2 = size;
        float f3 = this.DENSITY;
        float f4 = f2 / f3;
        if (this.mFixedHorizontalPadding) {
            this.mPaddingStart = this.mFixedHorizontalPaddingStart;
            this.mPaddingEnd = this.mFixedHorizontalPaddingEnd;
        } else if (f4 <= 340.0f) {
            int i4 = ((int) (f2 - (f3 * 290.0f))) / 2;
            if (i4 < 0) {
                i4 = 0;
            }
            int i5 = i4 / 2;
            this.mPaddingStart = this.mSmallHorizontalPaddingStart + i5;
            this.mPaddingEnd = this.mSmallHorizontalPaddingEnd + i5;
        } else {
            this.mPaddingStart = this.mHorizontalPaddingStart;
            this.mPaddingEnd = this.mHorizontalPaddingEnd;
        }
        this.mChildView.measure(ViewGroup.getChildMeasureSpec(i2, this.mPaddingStart + this.mPaddingEnd, this.mChildView.getLayoutParams().width), ViewGroup.getChildMeasureSpec(i3, 0, this.mChildView.getLayoutParams().height));
        setMeasuredDimension(size, this.mChildView.getMeasuredHeight());
    }

    public void setFixedContentHorizontalPadding(int i2, int i3) {
        this.mFixedHorizontalPadding = true;
        int i4 = this.mFixedHorizontalPaddingStart;
        int i5 = this.mFixedHorizontalPaddingEnd;
        this.mFixedHorizontalPaddingStart = i2;
        this.mFixedHorizontalPaddingEnd = i3;
        if (i3 == i5 ? i2 != i4 : true) {
            requestLayout();
        }
    }

    public ProperPaddingViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProperPaddingViewGroup(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mFixedHorizontalPadding = false;
        this.mFixedHorizontalPaddingStart = Integer.MIN_VALUE;
        this.mFixedHorizontalPaddingEnd = Integer.MIN_VALUE;
        TypedArray typedArrayObtainStyledAttributes = null;
        this.mChildView = null;
        this.DENSITY = context.getResources().getDisplayMetrics().density;
        try {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProperPaddingViewGroup);
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_horizontalPadding, 0);
            this.mHorizontalPaddingStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_horizontalPaddingStart, dimensionPixelSize);
            this.mHorizontalPaddingEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_horizontalPaddingEnd, dimensionPixelSize);
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_smallHorizontalPadding, 0);
            this.mSmallHorizontalPaddingStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_smallHorizontalPaddingStart, dimensionPixelSize2);
            this.mSmallHorizontalPaddingEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ProperPaddingViewGroup_smallHorizontalPaddingEnd, dimensionPixelSize2);
            typedArrayObtainStyledAttributes.recycle();
        } catch (Throwable th) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            throw th;
        }
    }
}
