package miuix.appcompat.internal.app.widget;

import android.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import miuix.appcompat.internal.view.ActionBarPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class CollapseTabContainer extends ScrollingTabContainerView {
    public CollapseTabContainer(Context context) {
        super(context);
        setContentHeight(getTabContainerHeight());
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getDefaultTabTextStyle() {
        return R.attr.actionBarTabTextStyle;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabBarLayoutRes() {
        return miuix.appcompat.R.layout.miuix_appcompat_action_bar_tabbar;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabContainerHeight() {
        return ActionBarPolicy.get(getContext()).getTabContainerHeight();
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabViewLayoutRes() {
        return miuix.appcompat.R.layout.miuix_appcompat_action_bar_tab;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public int getTabViewMarginHorizontal() {
        return 0;
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView, android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        int measuredHeight = getMeasuredHeight();
        int measuredHeight2 = this.mTabLayout.getMeasuredHeight();
        int i6 = (measuredHeight - measuredHeight2) / 2;
        this.mTabLayout.layout(i2, i6, this.mTabLayout.getMeasuredWidth(), measuredHeight2 + i6);
    }

    @Override // miuix.appcompat.internal.app.widget.ScrollingTabContainerView
    public View updateCustomTabView(ViewGroup viewGroup, View view, TextView textView, ImageView imageView) {
        ViewParent parent = view.getParent();
        if (parent != this) {
            if (parent != null) {
                ((ViewGroup) parent).removeView(view);
            }
            viewGroup.addView(view);
        }
        if (textView != null) {
            textView.setVisibility(8);
        }
        if (imageView != null) {
            imageView.setVisibility(8);
            imageView.setImageDrawable(null);
        }
        return view;
    }
}
