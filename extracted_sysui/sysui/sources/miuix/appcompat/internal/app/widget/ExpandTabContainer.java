package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ScrollingTabContainerView;
import miuix.core.util.RomUtils;
import miuix.theme.Typography;

/* JADX INFO: loaded from: classes3.dex */
public class ExpandTabContainer extends ScrollingTabContainerView {
    private int[] mTabSizeStages;

    public ExpandTabContainer(Context context) {
        super(context);
        setContentHeight(getTabContainerHeight());
        this.mTabSizeStages = new int[]{context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_tab_expand_text_size), 0, 0};
        this.mTabSizeStages[1] = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_tab_expand_text_size_1);
        this.mTabSizeStages[2] = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_tab_expand_text_size_2);
    }

    private void measureTabViewSizeStage2(int[] iArr, int i2, int i3) {
        int i4 = iArr[0];
        int tabViewMarginHorizontal = getTabViewMarginHorizontal();
        for (int i5 = 0; i5 < iArr.length; i5++) {
            i4 = iArr[i5];
            TextPaint textPaint = null;
            int iMeasureText = 0;
            for (int i6 = 0; i6 < this.mTabLayout.getChildCount(); i6++) {
                TextView textView = ((ScrollingTabContainerView.TabView) this.mTabLayout.getChildAt(i6)).getTextView();
                if (textView != null) {
                    if (textPaint == null) {
                        textPaint = new TextPaint(textView.getPaint());
                        iMeasureText += tabViewMarginHorizontal;
                    }
                    textPaint.setTextSize(i4);
                    iMeasureText = (int) (iMeasureText + textPaint.measureText(textView.getText().toString()));
                }
            }
            if (iMeasureText <= Math.max(getMeasuredWidth(), View.MeasureSpec.getSize(i2))) {
                break;
            }
        }
        for (int i7 = 0; i7 < this.mTabLayout.getChildCount(); i7++) {
            TextView textView2 = ((ScrollingTabContainerView.TabView) this.mTabLayout.getChildAt(i7)).getTextView();
            if (textView2 != null) {
                textView2.setTextSize(0, i4);
            }
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getDefaultTabTextStyle() {
        return R.attr.actionBarTabTextExpandStyle;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabBarLayoutRes() {
        return R.layout.miuix_appcompat_action_bar_tabbar_expand;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabContainerHeight() {
        return -2;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabViewLayoutRes() {
        return R.layout.miuix_appcompat_action_bar_tab_expand;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabViewMarginHorizontal() {
        return getContext().getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_tab_expand_margin);
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView, android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        measureTabViewSizeStage2(this.mTabSizeStages, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 0), i3);
        super.onMeasure(i2, i3);
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public void updateTabTextStyle(TextView textView) {
        if (RomUtils.getHyperOsVersion() <= 1) {
            Typography.applyMiSansLight(textView);
        }
    }
}
