package miuix.miuixbasewidget.widget.internal;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.List;
import miuix.core.util.MiuixUIUtils;
import miuix.internal.util.ViewUtils;
import miuix.miuixbasewidget.R;

/* JADX INFO: loaded from: classes.dex */
public class TabViewContainerView extends FrameLayout {
    private static final int MEASURE_MODE_COMPAT = 0;
    private static final int MEASURE_MODE_WIDE = 1;
    private static final int WIDE_LESS_THAN_TWO_ITEM_MIN_DP = 220;
    private static final int WIDE_MORE_THAN_FOUR_ITEM_MIN_DP = 150;
    private static final int WIDE_THREE_ITEM_MIN_DP = 180;
    private int mChildrenTotalWidth;
    private int mDensityDpi;
    private int mGapBetweenTabs;
    private boolean mLayoutCenter;
    private int mLayoutMode;
    private final List<View> mOverSizeViews;
    private final List<View> mSmallSizeViews;
    private int mSpaciousLessThanTwoItemMinWidth;
    private int mSpaciousMoreThanFourItemMinWidth;
    private int mSpaciousThreeItemMinWidth;
    private int mVerticalPaddingBottom;
    private int mVerticalPaddingTop;

    public TabViewContainerView(Context context) {
        this(context, null);
    }

    private boolean isViewGone(View view) {
        return view.getVisibility() == 8;
    }

    private void measureByCompatMode(int i2, int i3, int i4) {
        this.mOverSizeViews.clear();
        this.mSmallSizeViews.clear();
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            getChildAt(i6).setMinimumWidth(0);
        }
        super.onMeasure(i2, i3);
        int paddingStart = getPaddingStart() + getPaddingEnd();
        int i7 = i4 > 1 ? (i4 - 1) * this.mGapBetweenTabs : 0;
        int size = View.MeasureSpec.getSize(i2);
        int i8 = (size - paddingStart) - i7;
        int i9 = i8 / i4;
        int i10 = i8 % i4;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i11 < childCount) {
            View childAt = getChildAt(i11);
            childAt.setMinimumWidth(i5);
            if (!isViewGone(childAt)) {
                int measuredWidth = childAt.getMeasuredWidth();
                i12 += measuredWidth;
                if (measuredWidth > i9) {
                    this.mOverSizeViews.add(childAt);
                    i14 += measuredWidth;
                } else {
                    this.mSmallSizeViews.add(childAt);
                    i13 += measuredWidth;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY));
            }
            i11++;
            i5 = 0;
        }
        int measuredHeight = getMeasuredHeight() + this.mVerticalPaddingTop + this.mVerticalPaddingBottom;
        if (i12 > i8) {
            setMeasuredDimension(i12 + i7 + paddingStart, measuredHeight);
            return;
        }
        if (this.mOverSizeViews.isEmpty()) {
            int i15 = 0;
            while (i15 < childCount) {
                View childAt2 = getChildAt(i15);
                if (!isViewGone(childAt2)) {
                    childAt2.measure(View.MeasureSpec.makeMeasureSpec((i15 < i10 ? 1 : 0) + i9, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), BasicMeasure.EXACTLY));
                }
                i15++;
            }
        } else if (i13 > 0) {
            int size2 = this.mSmallSizeViews.size();
            int i16 = i8 - i14;
            for (int i17 = 0; i17 < size2; i17++) {
                View view = this.mSmallSizeViews.get(i17);
                int measuredWidth2 = (int) (((view.getMeasuredWidth() * 1.0f) / i13) * i16);
                if (!isViewGone(view)) {
                    view.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), BasicMeasure.EXACTLY));
                }
            }
        }
        setMeasuredDimension(size, measuredHeight);
    }

    private boolean measureByWideMode(int i2, int i3, int i4) {
        int paddingStart = getPaddingStart() + getPaddingEnd();
        int i5 = i4 > 1 ? (i4 - 1) * this.mGapBetweenTabs : 0;
        int size = View.MeasureSpec.getSize(i2);
        int i6 = (size - paddingStart) - i5;
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (i4 <= 2) {
                childAt.setMinimumWidth(this.mSpaciousLessThanTwoItemMinWidth);
                i7 = this.mSpaciousLessThanTwoItemMinWidth;
            } else if (i4 == 3) {
                childAt.setMinimumWidth(this.mSpaciousThreeItemMinWidth);
                i7 = this.mSpaciousThreeItemMinWidth;
            } else {
                childAt.setMinimumWidth(this.mSpaciousMoreThanFourItemMinWidth);
                i7 = this.mSpaciousMoreThanFourItemMinWidth;
            }
        }
        super.onMeasure(i2, i3);
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            if (!isViewGone(childAt2)) {
                int measuredWidth = childAt2.getMeasuredWidth();
                i9 += measuredWidth;
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY));
            }
        }
        this.mChildrenTotalWidth = i5 + i9;
        setMeasuredDimension(size, getMeasuredHeight() + this.mVerticalPaddingTop + this.mVerticalPaddingBottom);
        return i9 >= i6 - i7;
    }

    private void measureByWrapMode(int i2, int i3, int i4) {
        int i5 = i4 > 1 ? (i4 - 1) * this.mGapBetweenTabs : 0;
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (!isViewGone(childAt)) {
                int measuredWidth = childAt.getMeasuredWidth();
                i6 += measuredWidth;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY));
            }
        }
        setMeasuredDimension(getPaddingStart() + getPaddingEnd() + i6 + i5, getMeasuredHeight() + this.mVerticalPaddingTop + this.mVerticalPaddingBottom);
    }

    private void updateLayoutParams() {
        Context context = getContext();
        Resources resources = getResources();
        this.mGapBetweenTabs = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_filter_sort_view2_tab_gap);
        this.mVerticalPaddingTop = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_filter_sort_view2_vertical_padding_top);
        this.mVerticalPaddingBottom = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_filter_sort_view2_vertical_padding_bottom);
        this.mSpaciousLessThanTwoItemMinWidth = MiuixUIUtils.dp2px(context, 220.0f);
        this.mSpaciousThreeItemMinWidth = MiuixUIUtils.dp2px(context, 180.0f);
        this.mSpaciousMoreThanFourItemMinWidth = MiuixUIUtils.dp2px(context, 150.0f);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = configuration.densityDpi;
        if (i2 != this.mDensityDpi) {
            this.mDensityDpi = i2;
            updateLayoutParams();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = i4 - i2;
        int childCount = getChildCount();
        int i7 = this.mVerticalPaddingTop;
        int paddingStart = this.mLayoutCenter ? getPaddingStart() + ((i6 - this.mChildrenTotalWidth) / 2) : getPaddingStart();
        int i8 = paddingStart;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (!isViewGone(childAt)) {
                int measuredWidth = childAt.getMeasuredWidth() + i8;
                ViewUtils.layoutChildView(this, childAt, i8, i7, measuredWidth, i7 + childAt.getMeasuredHeight());
                i8 = measuredWidth + this.mGapBetweenTabs;
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        this.mLayoutCenter = false;
        this.mChildrenTotalWidth = 0;
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            if (!isViewGone(getChildAt(i5))) {
                i4++;
            }
        }
        if (i4 <= 0) {
            super.onMeasure(i2, i3);
            return;
        }
        int i6 = this.mLayoutMode;
        if (i6 == 2) {
            measureByWrapMode(i2, i3, i4);
            return;
        }
        if (i6 != 0) {
            if (i6 != 1) {
                throw new IllegalStateException("Unexpected layout mode: " + this.mLayoutMode);
            }
            if (!measureByWideMode(i2, i3, i4)) {
                this.mLayoutCenter = true;
                return;
            }
        }
        measureByCompatMode(i2, i3, i4);
    }

    public void setTabViewLayoutMode(int i2) {
        if (this.mLayoutMode != i2) {
            this.mLayoutMode = i2;
            requestLayout();
        }
    }

    public TabViewContainerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabViewContainerView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public TabViewContainerView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mLayoutCenter = false;
        this.mLayoutMode = 0;
        this.mOverSizeViews = new ArrayList();
        this.mSmallSizeViews = new ArrayList();
        updateLayoutParams();
    }
}
