package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import miuix.springback.view.SpringBackLayout;

/* JADX INFO: loaded from: classes3.dex */
public class SecondaryCollapseTabContainer extends SpringBackLayout implements SecondaryTabBar {

    @NonNull
    private final SecondaryTabContainerView mTabContainer;

    public SecondaryCollapseTabContainer(Context context) {
        super(context);
        setScrollOrientation(1);
        SecondaryTabContainerView secondaryTabContainerView = new SecondaryTabContainerView(context);
        this.mTabContainer = secondaryTabContainerView;
        addView(secondaryTabContainerView);
        secondaryTabContainerView.setContentHeight(secondaryTabContainerView.getTabContainerHeight());
        setTarget(secondaryTabContainerView);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, boolean z2) {
        this.mTabContainer.addTab(tab, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void animateToTab(int i2) {
        this.mTabContainer.animateToTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    @NonNull
    public ViewGroup asViewGroup() {
        return this;
    }

    @Override // miuix.springback.view.SpringBackLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        setSpringBackEnable(this.mTabContainer.canScrollHorizontally());
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
        this.mTabContainer.onPageScrollStateChanged(i2);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        this.mTabContainer.onPageScrolled(i2, f2, z2, z3);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
        this.mTabContainer.onPageSelected(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeAllTabs() {
        this.mTabContainer.removeAllTabs();
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void removeTabAt(int i2) {
        this.mTabContainer.removeTabAt(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setBadgeVisibility(int i2, boolean z2) {
        this.mTabContainer.setBadgeVisibility(i2, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setParentBlurEnabled(boolean z2) {
        this.mTabContainer.setParentBlurEnabled(z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabBadgeDisappearOnClick(int i2, boolean z2) {
        this.mTabContainer.setTabBadgeDisappearOnClick(i2, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.mTabContainer.setTabIconWithPosition(i2, i3, drawable, drawable2, drawable3, drawable4);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTabSelected(int i2) {
        this.mTabContainer.setTabSelected(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTextAppearance(int i2, int i3) {
        this.mTabContainer.setTextAppearance(i2, i3);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void updateTab(int i2) {
        this.mTabContainer.updateTab(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void addTab(@NonNull ActionBar.Tab tab, int i2, boolean z2) {
        this.mTabContainer.addTab(tab, i2, z2);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabBar
    public void setTextAppearance(int i2, int i3, int i4) {
        this.mTabContainer.setTextAppearance(i2, i3, i4);
    }
}
