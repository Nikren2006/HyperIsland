package miuix.appcompat.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import miuix.appcompat.R;

/* JADX INFO: loaded from: classes3.dex */
public class NestedScrollViewExpander extends ViewGroup {
    private View mExpandView;
    private int mParentHeightMeasureSpec;

    public NestedScrollViewExpander(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i7 = (((((i4 - i2) - measuredWidth) / 2) + i2) + marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin;
            int i8 = marginLayoutParams.topMargin + i3;
            childAt.layout(i7, i8, measuredWidth + i7, i8 + measuredHeight);
            i3 = i3 + marginLayoutParams.topMargin + measuredHeight + marginLayoutParams.bottomMargin;
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int measuredHeight;
        View view;
        int mode = View.MeasureSpec.getMode(this.mParentHeightMeasureSpec);
        if (mode == 0) {
            mode = Integer.MIN_VALUE;
        }
        int i4 = mode;
        int size = View.MeasureSpec.getSize(i2);
        int childCount = getChildCount();
        int measuredHeight2 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int measuredHeight3 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                if (this.mExpandView != childAt) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                    view = childAt;
                    measureChildWithMargins(childAt, i2, 0, i3, 0);
                    measuredHeight2 += view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                    if (view.getId() == R.id.contentView) {
                        measuredHeight3 = view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                        z2 = true;
                    }
                } else {
                    view = childAt;
                }
                if (view.getId() == R.id.buttonPanel) {
                    z3 = true;
                }
            }
        }
        int size2 = View.MeasureSpec.getSize(this.mParentHeightMeasureSpec);
        int minimumHeight = size2 - measuredHeight2;
        View view2 = this.mExpandView;
        if (view2 == null || view2.getVisibility() == 8) {
            measuredHeight = 0;
        } else {
            if (minimumHeight < this.mExpandView.getMinimumHeight()) {
                minimumHeight = this.mExpandView.getMinimumHeight();
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mExpandView.getLayoutParams();
            if (!z2 || z3 || measuredHeight3 < size2) {
                measureChildWithMargins(this.mExpandView, i2, 0, View.MeasureSpec.makeMeasureSpec(minimumHeight, i4), 0);
            } else {
                measureChildWithMargins(this.mExpandView, i2, 0, i3, 0);
            }
            measuredHeight = this.mExpandView.getMeasuredHeight() + marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin;
        }
        setMeasuredDimension(size, measuredHeight + measuredHeight2);
    }

    public void setExpandView(View view) {
        this.mExpandView = view;
    }

    public void setParentHeightMeasureSpec(int i2) {
        this.mParentHeightMeasureSpec = i2;
    }

    public NestedScrollViewExpander(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NestedScrollViewExpander(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
