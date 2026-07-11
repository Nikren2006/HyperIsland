package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.lang.ref.WeakReference;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.widget.BadgeDrawable;
import miuix.internal.util.AttributeResolver;
import miuix.miuixbasewidget.widget.FilterSortView2;

/* JADX INFO: loaded from: classes3.dex */
public class SecondaryTabContainerView extends FilterSortView2 implements SecondaryTabBar, ActionBar.FragmentViewPagerChangeListener {
    private int mActivatedTextAppearanceId;
    private boolean mAllowCollapse;
    private int mContentHeight;
    private TabClickListener mTabClickListener;
    private int mTextAppearanceId;

    public static class SecondaryTabView extends FilterSortView2.TabView {
        private final BadgeDrawable mBadge;
        private boolean mBadgeDisappearOnClick;
        private boolean mBadgeNeeded;
        private View mCustomView;
        private SecondaryTabContainerView mParent;
        private ActionBar.Tab mTab;

        public SecondaryTabView(Context context) {
            this(context, null);
        }

        private void attachBadge() {
            BadgeDrawable badgeDrawable = this.mBadge;
            if (badgeDrawable != null) {
                badgeDrawable.attachBadgeDrawable(this);
            }
        }

        private void detachBadge() {
            BadgeDrawable badgeDrawable = this.mBadge;
            if (badgeDrawable != null) {
                badgeDrawable.detachBadgeDrawable();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void initView(CharSequence charSequence) {
            super.initView(charSequence, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBadgeDisappearOnClick(boolean z2) {
            this.mBadgeDisappearOnClick = z2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBadgeNeeded(boolean z2) {
            this.mBadgeNeeded = z2;
            updateBadge();
        }

        private void updateBadge() {
            if (this.mBadgeNeeded) {
                attachBadge();
            } else {
                detachBadge();
            }
        }

        private void updateIconView() {
            ImageView iconView = getIconView();
            if (iconView != null) {
                iconView.setImageDrawable(this.mTab.getIcon());
            }
        }

        public void attach(SecondaryTabContainerView secondaryTabContainerView, ActionBar.Tab tab) {
            this.mParent = secondaryTabContainerView;
            this.mTab = tab;
            update();
        }

        public void bindTab(ActionBar.Tab tab) {
            this.mTab = tab;
            update();
        }

        public ActionBar.Tab getTab() {
            return this.mTab;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            updateIconView();
        }

        @Override // android.view.View
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            updateBadge();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (this.mBadgeNeeded) {
                StringBuilder sb = new StringBuilder();
                TextView textView = getTextView();
                if (textView != null && textView.getText() != null) {
                    sb.append(textView.getText());
                }
                sb.append(getResources().getString(R.string.miuix_appcompat_accessibility_new_message));
                accessibilityNodeInfo.setContentDescription(sb);
            }
        }

        public void update() {
            ActionBar.Tab tab = this.mTab;
            View customView = tab.getCustomView();
            ImageView iconView = getIconView();
            TextView textView = getTextView();
            if (customView != null) {
                this.mCustomView = this.mParent.updateCustomTabView(this, customView, textView, iconView);
                return;
            }
            View view = this.mCustomView;
            if (view != null) {
                removeView(view);
                this.mCustomView = null;
            }
            Context context = getContext();
            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
                if (iconView == null) {
                    ImageView imageView = new ImageView(context);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    setIconView(imageView);
                } else {
                    iconView.setImageDrawable(icon);
                    iconView.setVisibility(0);
                }
            } else if (iconView != null) {
                iconView.setVisibility(8);
                iconView.setImageDrawable(null);
            }
            if (text != null) {
                if (textView == null) {
                    TextView textView2 = new TextView(context);
                    textView2.setEllipsize(TextUtils.TruncateAt.END);
                    FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    textView2.setLayoutParams(layoutParams2);
                    addView(textView2);
                    setTextView(textView2);
                } else {
                    textView.setText(text);
                    textView.setVisibility(0);
                }
            } else if (textView != null) {
                textView.setVisibility(8);
                textView.setText((CharSequence) null);
            }
            if (iconView != null) {
                iconView.setContentDescription(tab.getContentDescription());
            }
        }

        public SecondaryTabView(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 0);
        }

        public SecondaryTabView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.mBadgeNeeded = false;
            this.mBadgeDisappearOnClick = true;
            this.mBadge = new BadgeDrawable(context, 2);
        }
    }

    public static class TabClickListener implements View.OnClickListener {
        private WeakReference<SecondaryTabContainerView> mRefs;

        public TabClickListener(SecondaryTabContainerView secondaryTabContainerView) {
            this.mRefs = new WeakReference<>(secondaryTabContainerView);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WeakReference<SecondaryTabContainerView> weakReference = this.mRefs;
            SecondaryTabContainerView secondaryTabContainerView = weakReference != null ? weakReference.get() : null;
            if (secondaryTabContainerView == null) {
                return;
            }
            SecondaryTabView secondaryTabView = (SecondaryTabView) view;
            secondaryTabView.getTab().select();
            int tabCount = secondaryTabContainerView.getTabCount();
            for (int i2 = 0; i2 < tabCount; i2++) {
                FilterSortView2.TabView tabViewAt = secondaryTabContainerView.getTabViewAt(i2);
                tabViewAt.setActivated(tabViewAt == view);
            }
            if (secondaryTabView.mBadgeDisappearOnClick) {
                secondaryTabView.setBadgeNeeded(false);
            }
        }
    }

    public SecondaryTabContainerView(Context context) {
        super(context);
        init(context);
    }

    private SecondaryTabView doAddTab(ActionBar.Tab tab, boolean z2) {
        SecondaryTabView secondaryTabViewCreateTabView = createTabView(tab);
        addTabViewAt(secondaryTabViewCreateTabView, -1);
        addTabViewChildId(secondaryTabViewCreateTabView.getId());
        secondaryTabViewCreateTabView.initView(tab.getText());
        secondaryTabViewCreateTabView.setSelected(this.mIsParentApplyBlur);
        if (z2) {
            setFilteredTab(secondaryTabViewCreateTabView);
            secondaryTabViewCreateTabView.setActivated(true);
        }
        requestLayout();
        return secondaryTabViewCreateTabView;
    }

    private void init(Context context) {
        this.mTextAppearanceId = AttributeResolver.resolve(context, getDefaultTabTextStyle());
        this.mActivatedTextAppearanceId = AttributeResolver.resolve(context, getTabActivatedTextStyle());
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, boolean z2) {
        doAddTab(tab, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void animateToTab(int i2) {
        setFilteredTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    @NonNull
    public ViewGroup asViewGroup() {
        return this;
    }

    public SecondaryTabView createTabView(ActionBar.Tab tab) {
        SecondaryTabView secondaryTabView = (SecondaryTabView) LayoutInflater.from(getContext()).inflate(R.layout.miuix_appcompat_action_bar_filter_tab_view, (ViewGroup) null);
        secondaryTabView.attach(this, tab);
        secondaryTabView.setFocusable(true);
        if (this.mTabClickListener == null) {
            this.mTabClickListener = new TabClickListener(this);
        }
        secondaryTabView.setOnClickListener(this.mTabClickListener);
        secondaryTabView.setEnabled(getEnabled());
        secondaryTabView.setTextAppearance(this.mTextAppearanceId);
        secondaryTabView.setActivatedTextAppearance(this.mActivatedTextAppearanceId);
        return secondaryTabView;
    }

    public int getDefaultTabTextStyle() {
        return R.attr.actionBarTabTextSecondaryStyle;
    }

    public int getTabActivatedTextStyle() {
        return R.attr.actionBarTabActivatedTextSecondaryStyle;
    }

    public int getTabContainerHeight() {
        return -2;
    }

    @Override // miuix.miuixbasewidget.widget.FilterSortView2, android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int i4 = this.mContentHeight;
        if (i4 != -2) {
            i3 = View.MeasureSpec.makeMeasureSpec(i4, BasicMeasure.EXACTLY);
        }
        super.onMeasure(i2, i3);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
        FilterSortView2.TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt instanceof SecondaryTabView) {
            setFilteredTab(tabViewAt);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeAllTabs() {
        removeAllTabViews();
        clearTabViewChildIds();
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeTabAt(int i2) {
        FilterSortView2.TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt instanceof SecondaryTabView) {
            removeTabViewAt(i2);
            removeTabViewChildId(tabViewAt.getId());
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void setAllowCollapse(boolean z2) {
        this.mAllowCollapse = z2;
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setBadgeVisibility(int i2, boolean z2) {
        if (i2 >= getTabCount()) {
            return;
        }
        FilterSortView2.TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt instanceof SecondaryTabView) {
            ((SecondaryTabView) tabViewAt).setBadgeNeeded(z2);
        }
    }

    public void setContentHeight(int i2) {
        if (this.mContentHeight != i2) {
            this.mContentHeight = i2;
            requestLayout();
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setParentBlurEnabled(boolean z2) {
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabBadgeDisappearOnClick(int i2, boolean z2) {
        if (i2 >= getTabCount()) {
            return;
        }
        FilterSortView2.TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt instanceof SecondaryTabView) {
            ((SecondaryTabView) tabViewAt).setBadgeDisappearOnClick(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        FilterSortView2.TabView tabViewAt;
        TextView textView;
        if (i2 > getChildCount() - 1 || (tabViewAt = getTabViewAt(i2)) == null || (textView = tabViewAt.getTextView()) == null) {
            return;
        }
        textView.setCompoundDrawablePadding(i3);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabSelected(int i2) {
        setFilteredTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    @Deprecated
    public void setTextAppearance(int i2, int i3) {
        setTextAppearance(i2, i3, i3);
    }

    public View updateCustomTabView(ViewGroup viewGroup, View view, TextView textView, ImageView imageView) {
        return null;
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void updateTab(int i2) {
        FilterSortView2.TabView tabViewAt = getTabViewAt(i2);
        if (tabViewAt instanceof SecondaryTabView) {
            ((SecondaryTabView) tabViewAt).update();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, int i2, boolean z2) {
        doAddTab(tab, i2, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTextAppearance(int i2, int i3, int i4) {
        FilterSortView2.TabView tabViewAt;
        if (i2 < 0 || i2 >= getTabCount() || (tabViewAt = getTabViewAt(i2)) == null) {
            return;
        }
        tabViewAt.setTextAppearance(i3);
        tabViewAt.setActivatedTextAppearance(i4);
    }

    public SecondaryTabContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SecondaryTabContainerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }

    public SecondaryTabView doAddTab(ActionBar.Tab tab, int i2, boolean z2) {
        SecondaryTabView secondaryTabViewCreateTabView = createTabView(tab);
        addTabViewAt(secondaryTabViewCreateTabView, i2);
        addTabViewChildId(secondaryTabViewCreateTabView.getId());
        secondaryTabViewCreateTabView.initView(tab.getText());
        secondaryTabViewCreateTabView.setSelected(this.mIsParentApplyBlur);
        if (z2) {
            setFilteredTab(secondaryTabViewCreateTabView);
            secondaryTabViewCreateTabView.setActivated(true);
        }
        requestLayout();
        return secondaryTabViewCreateTabView;
    }
}
