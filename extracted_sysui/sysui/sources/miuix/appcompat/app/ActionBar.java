package miuix.appcompat.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentActivity;
import java.util.Map;
import miuix.animation.controller.AnimState;
import miuix.appcompat.app.strategy.IActionBarStrategy;
import miuix.appcompat.internal.app.widget.actionbar.CollapseTitle;
import miuix.appcompat.internal.app.widget.actionbar.ExpandTitle;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActionBar extends androidx.appcompat.app.ActionBar {
    public static final int DISPLAY_SHOW_ACTIONBAR_BLUR = 32768;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static final int DISPLAY_SHOW_NAVIGATOR_SWITCH = 8192;
    public static final int DISPLAY_SHOW_SPLIT_ACTIONBAR_BLUR = 16384;
    public static final int DISPLAY_UNBIND_TITLE_UP = 32;
    public static final int STATE_AUTO = -1;
    public static final int STATE_COLLAPSE = 0;
    public static final int STATE_EXPAND = 1;

    public interface FragmentViewPagerChangeListener {
        void onPageScrollStateChanged(int i2);

        void onPageScrolled(int i2, float f2, boolean z2, boolean z3);

        void onPageSelected(int i2);
    }

    public interface OnScrollListener {
        boolean onContentScrolled();

        void onFling(float f2, int i2);

        void onScroll(int i2, float f2);

        void onStartScroll();

        void onStopScroll();
    }

    public abstract void addActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener);

    public abstract void addBadgeOnItemView(int i2);

    public abstract void addBadgeOnItemView(int i2, int i3);

    public abstract void addBadgeOnItemView(MenuItem menuItem);

    public abstract void addBadgeOnItemView(MenuItem menuItem, int i2);

    public abstract void addBadgeOnMoreButton();

    public abstract void addBadgeOnMoreButton(int i2);

    public abstract int addFragmentTab(String str, ActionBar.Tab tab, int i2, Class<? extends androidx.fragment.app.Fragment> cls, Bundle bundle, boolean z2);

    public abstract int addFragmentTab(String str, ActionBar.Tab tab, Class<? extends androidx.fragment.app.Fragment> cls, Bundle bundle, boolean z2);

    public abstract void addNumberBadgeOnItemView(int i2, int i3);

    public abstract void addNumberBadgeOnItemView(int i2, int i3, int i4);

    public abstract void addNumberBadgeOnMoreButton(int i2);

    public abstract void addNumberBadgeOnMoreButton(int i2, int i3);

    public abstract void addOnFragmentViewPagerChangeListener(FragmentViewPagerChangeListener fragmentViewPagerChangeListener);

    public abstract void clearBadgeOnItemView(int i2);

    public abstract void clearBadgeOnItemView(MenuItem menuItem);

    public abstract void clearBadgeOnMoreButton();

    public abstract IActionBarStrategy getActionBarStrategy();

    public abstract View getActionBarView();

    public abstract CollapseTitle getCollapseTitle();

    public abstract View getEndView();

    public abstract int getExpandState();

    public abstract ExpandTitle getExpandTitle();

    public abstract int getExpandedHeight();

    public abstract androidx.fragment.app.Fragment getFragmentAt(int i2);

    public abstract int getFragmentTabCount();

    public abstract Map<Integer, Boolean> getHyperMenuPrimaryCheckedData();

    public abstract Map<Integer, Boolean[]> getHyperMenuSecondaryCheckedData();

    public abstract View getStartView();

    public abstract View getSubTitleView(int i2);

    public abstract View getTitleView(int i2);

    public abstract int getViewPagerOffscreenPageLimit();

    public abstract void hide(AnimState animState);

    public abstract void hide(boolean z2);

    public abstract void hide(boolean z2, AnimState animState);

    public abstract boolean isAdsorptionToNoOverlay();

    public abstract boolean isFragmentViewPagerMode();

    public abstract boolean isResizable();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public abstract void onDestroy();

    public abstract void registerCoordinateScrollView(View view);

    public abstract void registerCoordinatedScrollView(View view);

    public abstract void removeActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener);

    public abstract void removeAllFragmentTab();

    public abstract void removeFragmentTab(ActionBar.Tab tab);

    public abstract void removeFragmentTab(androidx.fragment.app.Fragment fragment);

    public abstract void removeFragmentTab(String str);

    public abstract void removeFragmentTabAt(int i2);

    public abstract void removeOnFragmentViewPagerChangeListener(FragmentViewPagerChangeListener fragmentViewPagerChangeListener);

    public abstract void replaceFragmentTab(String str, int i2, Class<? extends androidx.fragment.app.Fragment> cls, Bundle bundle, boolean z2);

    public abstract void resetCoordinateScrollView(View view);

    public abstract void restoreHyperMenuPrimaryCheckedData(Map<Integer, Boolean> map);

    public abstract void restoreHyperMenuSecondaryCheckedData(Map<Integer, Boolean[]> map);

    public abstract void selectTab(ActionBar.Tab tab, boolean z2);

    public abstract void setActionBarStrategy(IActionBarStrategy iActionBarStrategy);

    public abstract void setActionMenuItemLimit(int i2);

    public abstract void setActionModeAnim(boolean z2);

    public abstract void setAdsorptionToNoOverlay(boolean z2);

    public abstract void setEndActionMenuItemLimit(int i2);

    public abstract void setEndView(View view);

    public abstract void setExpandState(int i2);

    public abstract void setExpandState(int i2, boolean z2);

    public abstract void setExpandState(int i2, boolean z2, boolean z3);

    public abstract void setExpandTabTextAppearance(int i2, int i3);

    public abstract void setFragmentActionMenuAt(int i2, boolean z2);

    public abstract void setFragmentViewPagerMode(FragmentActivity fragmentActivity);

    public abstract void setFragmentViewPagerMode(FragmentActivity fragmentActivity, boolean z2);

    public abstract void setProgress(int i2);

    public abstract void setProgressBarIndeterminate(boolean z2);

    public abstract void setProgressBarIndeterminateVisibility(boolean z2);

    public abstract void setProgressBarVisibility(boolean z2);

    public abstract void setResizable(boolean z2);

    @Deprecated
    public abstract void setSecondaryTabTextAppearance(int i2, int i3);

    public abstract void setSecondaryTabTextAppearance(int i2, int i3, int i4);

    public abstract void setStartView(View view);

    public abstract void setSubTitleClickListener(View.OnClickListener onClickListener);

    public abstract void setSubTitleDrawable(TextViewDrawableConfig textViewDrawableConfig);

    public abstract void setTabBadgeDisappearOnClick(int i2, boolean z2);

    public abstract void setTabBadgeVisibility(int i2, boolean z2);

    public abstract void setTabIconWithPosition(int i2, int i3, int i4, int i5, int i6, int i7);

    public abstract void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4);

    public abstract void setTabTextAppearance(int i2, int i3);

    public abstract void setTabsMode(boolean z2);

    public abstract void setTitleClickable(boolean z2);

    public abstract void setViewPagerDecor(View view);

    public abstract void setViewPagerDraggable(boolean z2);

    public abstract void setViewPagerOffscreenPageLimit(int i2);

    public abstract void show(AnimState animState);

    public abstract void show(boolean z2);

    public abstract void show(boolean z2, AnimState animState);

    public abstract void showActionBarShadow(boolean z2);

    public abstract void showSplitActionBar(boolean z2, boolean z3);

    public abstract void unregisterCoordinateScrollView(View view);

    public abstract void unregisterCoordinatedScrollView(View view);
}
