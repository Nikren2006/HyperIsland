package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.internal.util.DeviceHelper;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes3.dex */
public class EndActionMenuView extends ActionMenuView {
    private int mActionCount;
    private Context mContext;
    private int mMaxActionButtonWidth;
    private int mMenuItemGap;
    private int mMenuItemHeight;
    private int mMenuItemWidth;
    private int mStartPadding;

    public EndActionMenuView(Context context) {
        this(context, null);
    }

    private int getActionMenuItemCount() {
        return getChildCount();
    }

    private boolean isNotActionMenuItemChild(View view) {
        return false;
    }

    @Override // miuix.view.BlurableWidget
    public void applyBlur(boolean z2) {
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, miuix.appcompat.internal.view.menu.MenuView
    public boolean filterLeftoverView(int i2) {
        ActionMenuView.LayoutParams layoutParams;
        View childAt = getChildAt(i2);
        return !isNotActionMenuItemChild(childAt) && ((layoutParams = (ActionMenuView.LayoutParams) childAt.getLayoutParams()) == null || !layoutParams.isOverflowButton) && super.filterLeftoverView(i2);
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public ActionMenuView.LayoutParams generateOverflowButtonLayoutParams(@NonNull View view) {
        ActionMenuView.LayoutParams layoutParamsGenerateLayoutParams = generateLayoutParams(view.getLayoutParams());
        layoutParamsGenerateLayoutParams.isOverflowButton = true;
        return layoutParamsGenerateLayoutParams;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public int getCollapsedHeight() {
        return this.mMenuItemHeight;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, miuix.appcompat.internal.view.menu.MenuView
    public boolean hasBackgroundView() {
        return false;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isApplyBlur() {
        return false;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isEnableBlur() {
        return false;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isSupportBlur() {
        return false;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6 = i5 - i3;
        int childCount = getChildCount();
        int measuredWidth = this.mStartPadding;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (!isNotActionMenuItemChild(childAt)) {
                ViewUtils.layoutChildView(this, childAt, measuredWidth, 0, measuredWidth + childAt.getMeasuredWidth(), i6);
                measuredWidth += childAt.getMeasuredWidth() + this.mMenuItemGap;
            }
        }
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        int actionMenuItemCount = getActionMenuItemCount();
        this.mActionCount = actionMenuItemCount;
        if (childCount == 0 || actionMenuItemCount == 0) {
            this.mMenuItemHeight = 0;
            setMeasuredDimension(0, 0);
            return;
        }
        int size = View.MeasureSpec.getSize(i2);
        int iMin = Math.min(size / this.mActionCount, this.mMaxActionButtonWidth);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        int iMin2 = 0;
        int iMax = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!isNotActionMenuItemChild(childAt)) {
                measureChildWithMargins(childAt, iMakeMeasureSpec, 0, i3, 0);
                iMin2 += Math.min(childAt.getMeasuredWidth(), iMin);
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
            }
        }
        int i5 = this.mMenuItemGap * (this.mActionCount - 1);
        int i6 = this.mStartPadding;
        if (i6 + iMin2 + i5 > size) {
            this.mMenuItemGap = 0;
        }
        int i7 = iMin2 + i5 + i6;
        this.mMenuItemWidth = i7;
        this.mMenuItemHeight = iMax;
        setMeasuredDimension(i7, iMax);
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        if (DeviceHelper.isFeatureWholeAnim()) {
            setAlpha(computeAlpha(f2, z2, z3));
        }
        float fComputeTranslationY = computeTranslationY(f2, z2, z3);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (!isNotActionMenuItemChild(childAt)) {
                childAt.setTranslationY(fComputeTranslationY);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // miuix.view.BlurableWidget
    public void setEnableBlur(boolean z2) {
    }

    @Override // miuix.view.BlurableWidget
    public void setSupportBlur(boolean z2) {
    }

    public EndActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxActionButtonWidth = 0;
        this.mMenuItemGap = 0;
        this.mStartPadding = 0;
        this.mActionCount = 0;
        super.setBackground(null);
        this.mContext = context;
        this.mMenuItemGap = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_end_menu_button_gap);
        this.mStartPadding = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_title_view_padding_horizontal);
        this.mMaxActionButtonWidth = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_button_max_width);
    }
}
