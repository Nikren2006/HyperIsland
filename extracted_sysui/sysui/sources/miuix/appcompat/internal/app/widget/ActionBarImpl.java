package miuix.appcompat.internal.app.widget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.SpinnerAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.animation.utils.EaseManager;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.app.ActionBarTransitionListener;
import miuix.appcompat.app.AppCompatActivity;
import miuix.appcompat.app.IFragment;
import miuix.appcompat.app.TextViewDrawableConfig;
import miuix.appcompat.app.strategy.ActionBarConfig;
import miuix.appcompat.app.strategy.ActionBarSpec;
import miuix.appcompat.app.strategy.CommonActionBarStrategy;
import miuix.appcompat.app.strategy.IActionBarStrategy;
import miuix.appcompat.internal.app.widget.actionbar.CollapseTitle;
import miuix.appcompat.internal.app.widget.actionbar.ExpandTitle;
import miuix.appcompat.internal.view.ActionBarPolicy;
import miuix.appcompat.internal.view.ActionModeImpl;
import miuix.appcompat.internal.view.EditActionModeImpl;
import miuix.appcompat.internal.view.SearchActionModeImpl;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.appcompat.internal.view.menu.action.PhoneActionMenuView;
import miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView;
import miuix.container.ExtraPaddingPolicy;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuiBlurUtils;
import miuix.core.util.MiuixUIUtils;
import miuix.core.view.NestedContentInsetObserver;
import miuix.core.view.NestedCoordinatorObserver;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.LiteUtils;
import miuix.view.SearchActionMode;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarImpl extends ActionBar {
    private static final int CONTEXT_DISPLAY_NORMAL = 0;
    private static final int CONTEXT_DISPLAY_SPLIT = 1;
    private static final int INVALID_POSITION = -1;
    public static final boolean IS_COMPLY_WITH_THEME = true;
    private static final int MAX_ACTION_MENU_ITEM_COUNT_UNSET = -1;
    private IActionBarStrategy mActionBarStrategy;
    ActionMode mActionMode;
    private ActionModeView mActionModeView;
    private ActionBarView mActionView;
    private boolean mAdsorptionToNoOverlay;
    private IStateStyle mContainerAnim;
    private ActionBarContainer mContainerView;
    private Rect mContentInset;
    private View mContentMask;
    private View.OnClickListener mContentMaskOnClickListener;
    private Context mContext;
    private int mContextDisplayMode;
    private ActionBarContextView mContextView;
    private int mCurrentAccessibilityImportant;
    private int mCurrentExpandState;
    private boolean mCurrentResizable;
    private boolean mDisplayHomeAsUpSet;
    private ScrollingTabContainerView mExpandTabScrollView;
    private ExtraPaddingPolicy mExtraPaddingPolicy;
    private FragmentManager mFragmentManager;
    private boolean mHiddenByApp;
    private boolean mHiddenBySystem;
    private boolean mIsWindowInfoChanged;
    private ActionBarOverlayLayout mOverlayLayout;
    private SearchActionModeView mSearchActionModeView;
    private SecondaryTabBar mSecondaryExpandTabScrollView;
    private SecondaryTabBar mSecondaryTabScrollView;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    private boolean mShowingForMode;
    private PhoneActionMenuView mSplitMenuView;
    private ActionBarContainer mSplitView;
    private IStateStyle mSpliterAnim;
    private ScrollingTabContainerView mTabScrollView;
    private Context mThemedContext;
    private ActionBarViewPagerController mViewPagerController;
    private int mWindowMode;
    private static ActionBar.TabListener sTabListenerWrapper = new ActionBar.TabListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.1
        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.mInternalCallback != null) {
                tabImpl.mInternalCallback.onTabReselected(tab, fragmentTransaction);
            }
            if (tabImpl.mCallback != null) {
                tabImpl.mCallback.onTabReselected(tab, fragmentTransaction);
            }
        }

        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.mInternalCallback != null) {
                tabImpl.mInternalCallback.onTabSelected(tab, fragmentTransaction);
            }
            if (tabImpl.mCallback != null) {
                tabImpl.mCallback.onTabSelected(tab, fragmentTransaction);
            }
        }

        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            TabImpl tabImpl = (TabImpl) tab;
            if (tabImpl.mInternalCallback != null) {
                tabImpl.mInternalCallback.onTabUnselected(tab, fragmentTransaction);
            }
            if (tabImpl.mCallback != null) {
                tabImpl.mCallback.onTabUnselected(tab, fragmentTransaction);
            }
        }
    };
    private static final Integer UNINITIALIZED_OFFSET = -1;
    private final HashMap<View, Integer> mCoordinateOffsetViewSet = new HashMap<>();
    private final HashSet<NestedContentInsetObserver> mNestedContentInsetObserverSet = new HashSet<>();
    private ArrayList<TabImpl> mTabs = new ArrayList<>();
    private boolean isSelectingTab = false;
    private int mSavedTabPosition = -1;
    private ArrayList<ActionBar.OnMenuVisibilityListener> mMenuVisibilityListeners = new ArrayList<>();
    private int mCurWindowVisibility = 0;
    private boolean mNowShowing = true;
    private ActionModeImpl.ActionModeCallback mActionModeCallback = new ActionModeImpl.ActionModeCallback() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.2
        @Override // miuix.appcompat.internal.view.ActionModeImpl.ActionModeCallback
        public void onActionModeFinish(ActionMode actionMode) {
            ActionBarImpl.this.animateToMode(false);
            ActionBarImpl.this.mActionMode = null;
        }
    };
    private boolean mIsContainerAnimationRunning = false;
    private int mMaxActionMenuItemCount = -1;
    private int mContentInsetTop = 0;
    private int mCurrentActionBarHeightGap = 0;
    private int mActionBarHeightTotalGap = 0;
    private int mCurrentActionBarHeightGapOnShow = 0;
    private int mActionBarHeightTotalGapOnShow = 0;
    private float mTargetTranslationY = 0.0f;
    private final TransitionListener mContainerViewAnimationListener = new TransitionListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.7
        @Override // miuix.animation.listener.TransitionListener
        public void onBegin(Object obj) {
            super.onBegin(obj);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onCancel(Object obj) {
            super.onCancel(obj);
            ActionBarImpl.this.mIsContainerAnimationRunning = false;
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            super.onComplete(obj);
            ActionBarImpl.this.mIsContainerAnimationRunning = false;
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
            super.onUpdate(obj, collection);
            float translationY = (ActionBarImpl.this.mTargetTranslationY - ActionBarImpl.this.mContainerView.getTranslationY()) / ActionBarImpl.this.mTargetTranslationY;
            ActionBarImpl.this.mActionBarHeightTotalGap = (int) Math.max(0.0f, r4.mActionBarHeightTotalGapOnShow * translationY);
            ActionBarImpl.this.mCurrentActionBarHeightGap = (int) Math.max(0.0f, r4.mCurrentActionBarHeightGapOnShow * translationY);
            ActionBarImpl.this.mOverlayLayout.updateCurrentContentInsetInOverlayMode();
            ActionBarImpl.this.updateCoordinateOffsetView();
        }
    };

    /* JADX INFO: renamed from: miuix.appcompat.internal.app.widget.ActionBarImpl$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnLayoutChangeListener {
        int lastWidth = 0;

        public AnonymousClass5() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onLayoutChange$0() {
            ActionBarImpl actionBarImpl = ActionBarImpl.this;
            actionBarImpl.applyActionBarStrategy(actionBarImpl.mActionView, ActionBarImpl.this.mContextView);
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            int i10 = i4 - i2;
            if (this.lastWidth != i10 || ActionBarImpl.this.mIsWindowInfoChanged) {
                ActionBarImpl.this.mIsWindowInfoChanged = false;
                this.lastWidth = i10;
                ActionBarImpl.this.mActionView.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6069a.lambda$onLayoutChange$0();
                    }
                });
            }
        }
    }

    public static class ViewHideTransitionListener extends TransitionListener {
        private WeakReference<ActionBarImpl> mActionBarRef;
        private WeakReference<View> mRef;

        public ViewHideTransitionListener(View view, ActionBarImpl actionBarImpl) {
            this.mRef = new WeakReference<>(view);
            this.mActionBarRef = new WeakReference<>(actionBarImpl);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            super.onComplete(obj);
            ActionBarImpl actionBarImpl = this.mActionBarRef.get();
            View view = this.mRef.get();
            if (view == null || actionBarImpl == null || actionBarImpl.mNowShowing) {
                return;
            }
            view.setVisibility(8);
            if (view.getId() == R.id.action_bar_container) {
                view.setTranslationY(-view.getHeight());
            }
        }
    }

    public static class ViewShowTransitionListener extends TransitionListener {
        private WeakReference<ActionBarImpl> mActionBarRef;
        private WeakReference<View> mRef;

        public ViewShowTransitionListener(View view, ActionBarImpl actionBarImpl) {
            this.mRef = new WeakReference<>(view);
            this.mActionBarRef = new WeakReference<>(actionBarImpl);
        }

        @Override // miuix.animation.listener.TransitionListener
        public void onComplete(Object obj) {
            super.onComplete(obj);
            ActionBarImpl actionBarImpl = this.mActionBarRef.get();
            View view = this.mRef.get();
            if (view == null || actionBarImpl == null || !actionBarImpl.mNowShowing || view.getId() != R.id.action_bar_container) {
                return;
            }
            view.setTranslationY(0.0f);
        }
    }

    public ActionBarImpl(AppCompatActivity appCompatActivity, ViewGroup viewGroup) {
        this.mContext = appCompatActivity;
        this.mFragmentManager = appCompatActivity.getSupportFragmentManager();
        init(viewGroup);
        this.mActionView.setWindowTitle(appCompatActivity.getTitle());
    }

    private void addContentMask() {
        ViewStub viewStub = (ViewStub) this.mOverlayLayout.findViewById(R.id.content_mask_vs);
        this.mOverlayLayout.setContentMask(viewStub != null ? viewStub.inflate() : this.mOverlayLayout.findViewById(R.id.content_mask));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyActionBarStrategy(ActionBarView actionBarView, ActionBarContextView actionBarContextView) {
        if (this.mActionBarStrategy == null) {
            return;
        }
        int expandState = getExpandState();
        ActionBarConfig actionBarConfigConfig = this.mActionBarStrategy.config(this, getActionBarSpec(this.mContainerView, this.mActionView));
        if (actionBarView != null && actionBarConfigConfig != null) {
            if (!actionBarView.isUserSetExpandState() || actionBarConfigConfig.overrideUserExpandStateConfig) {
                if (!actionBarView.isResizable() || !actionBarConfigConfig.resizable) {
                    actionBarView.setExpandState(actionBarConfigConfig.expandState, false, true);
                }
                actionBarView.setResizable(actionBarConfigConfig.resizable);
            }
            if (!actionBarView.isUserSetEndActionMenuItemLimit() || actionBarConfigConfig.overrideUserEndMenuConfig) {
                actionBarView.setEndActionMenuItemLimit(actionBarConfigConfig.endMenuMaxItemCount);
            }
        }
        if (actionBarContextView != null && actionBarConfigConfig != null && (!actionBarContextView.isUserSetExpandState() || actionBarConfigConfig.overrideUserExpandStateConfig)) {
            if (!actionBarContextView.isResizable() || !actionBarConfigConfig.resizable) {
                actionBarContextView.setExpandState(actionBarConfigConfig.expandState, false, true);
            }
            actionBarContextView.setResizable(actionBarConfigConfig.resizable);
        }
        this.mCurrentExpandState = getExpandState();
        this.mCurrentResizable = isResizable();
        int i2 = this.mCurrentExpandState;
        if (i2 != 1 || expandState == i2 || this.mContentInset == null) {
            return;
        }
        Iterator<View> it = this.mCoordinateOffsetViewSet.keySet().iterator();
        while (it.hasNext()) {
            this.mCoordinateOffsetViewSet.put(it.next(), Integer.valueOf(this.mContentInset.top));
        }
        Iterator<NestedContentInsetObserver> it2 = this.mNestedContentInsetObserverSet.iterator();
        while (it2.hasNext()) {
            it2.next().onContentInsetChanged(this.mContentInset);
        }
        ActionBarContainer actionBarContainer = this.mContainerView;
        if (actionBarContainer != null) {
            actionBarContainer.setActionBarBlurByNestedScrolled(false);
        }
    }

    private static boolean checkShowingFlags(boolean z2, boolean z3, boolean z4) {
        if (z4) {
            return true;
        }
        return (z2 || z3) ? false : true;
    }

    private void cleanupTabs() {
        if (this.mSelectedTab != null) {
            selectTab(null);
        }
        this.mTabs.clear();
        ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
        if (scrollingTabContainerView != null) {
            scrollingTabContainerView.removeAllTabs();
        }
        ScrollingTabContainerView scrollingTabContainerView2 = this.mExpandTabScrollView;
        if (scrollingTabContainerView2 != null) {
            scrollingTabContainerView2.removeAllTabs();
        }
        SecondaryTabBar secondaryTabBar = this.mSecondaryTabScrollView;
        if (secondaryTabBar != null) {
            secondaryTabBar.removeAllTabs();
        }
        SecondaryTabBar secondaryTabBar2 = this.mSecondaryExpandTabScrollView;
        if (secondaryTabBar2 != null) {
            secondaryTabBar2.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    private void configureTab(ActionBar.Tab tab, int i2) {
        TabImpl tabImpl = (TabImpl) tab;
        if (tabImpl.getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        tabImpl.setPosition(i2);
        this.mTabs.add(i2, tabImpl);
        int size = this.mTabs.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.mTabs.get(i2).setPosition(i2);
            }
        }
    }

    private ActionMode createActionMode(ActionMode.Callback callback) {
        return callback instanceof SearchActionMode.Callback ? new SearchActionModeImpl(this.mContext, callback) : new EditActionModeImpl(this.mContext, callback);
    }

    private void createContextView(boolean z2, boolean z3) {
        ViewStub viewStub = (ViewStub) this.mOverlayLayout.findViewById(R.id.split_action_bar_vs);
        ActionBarContainer actionBarContainer = viewStub != null ? (ActionBarContainer) viewStub.inflate() : (ActionBarContainer) this.mOverlayLayout.findViewById(R.id.split_action_bar);
        if (actionBarContainer != null) {
            this.mActionView.setSplitView(actionBarContainer);
            this.mActionView.setSplitActionBar(z2);
            this.mActionView.setSplitWhenNarrow(z3);
            ViewStub viewStub2 = (ViewStub) this.mOverlayLayout.findViewById(R.id.action_context_bar_vs);
            if (viewStub2 != null) {
                this.mContextView = (ActionBarContextView) viewStub2.inflate();
            } else {
                this.mContextView = (ActionBarContextView) this.mOverlayLayout.findViewById(R.id.action_context_bar);
            }
            ActionBarContextView actionBarContextView = this.mContextView;
            if (actionBarContextView != null) {
                this.mContainerView.setActionBarContextView(actionBarContextView);
                this.mOverlayLayout.setActionBarContextView(this.mContextView);
                this.mContextView.setSplitView(actionBarContainer);
                this.mContextView.setSplitActionBar(z2);
                this.mContextView.setSplitWhenNarrow(z3);
            }
        }
    }

    private void doHide(boolean z2) {
        doHide(z2, true, null);
    }

    private void doShow(boolean z2) {
        doShow(z2, true, null);
    }

    private void doUpdateTopOffsetForCoordinateView(View view, int i2) {
        int top = view.getTop();
        int i3 = this.mCurrentActionBarHeightGap;
        if (top != i3 + i2) {
            view.offsetTopAndBottom((Math.max(0, i3) + i2) - top);
        }
    }

    private void ensureTabsExist() {
        SecondaryTabBar secondarySegmentTabBar;
        SecondaryTabBar secondarySegmentTabBar2;
        if (this.mTabScrollView != null) {
            this.mActionView.checkTabsAdded();
            return;
        }
        CollapseTabContainer collapseTabContainer = new CollapseTabContainer(this.mContext);
        ExpandTabContainer expandTabContainer = new ExpandTabContainer(this.mContext);
        int iResolveInt = AttributeResolver.resolveInt(this.mContext, R.attr.actionBarSecondaryTabBarType, 0);
        if (iResolveInt == 0) {
            secondarySegmentTabBar = new SecondaryCollapseTabContainer(this.mContext);
            secondarySegmentTabBar2 = new SecondaryExpandTabContainer(this.mContext);
        } else {
            if (iResolveInt != 1) {
                throw new IllegalArgumentException("actionBarSecondaryTabBarType: " + iResolveInt + " is invalid.");
            }
            secondarySegmentTabBar = new SecondarySegmentTabBar(this.mContext);
            secondarySegmentTabBar2 = new SecondarySegmentTabBar(this.mContext);
        }
        collapseTabContainer.setVisibility(0);
        expandTabContainer.setVisibility(0);
        secondarySegmentTabBar.asViewGroup().setVisibility(0);
        secondarySegmentTabBar2.asViewGroup().setVisibility(0);
        this.mActionView.setEmbeddedTabView(collapseTabContainer, expandTabContainer, secondarySegmentTabBar, secondarySegmentTabBar2);
        collapseTabContainer.setEmbeded(true);
        this.mTabScrollView = collapseTabContainer;
        this.mExpandTabScrollView = expandTabContainer;
        this.mSecondaryTabScrollView = secondarySegmentTabBar;
        this.mSecondaryExpandTabScrollView = secondarySegmentTabBar2;
    }

    public static ActionBarImpl getActionBar(View view) {
        while (view != null) {
            if (view instanceof ActionBarOverlayLayout) {
                return (ActionBarImpl) ((ActionBarOverlayLayout) view).getActionBar();
            }
            view = view.getParent() instanceof View ? (View) view.getParent() : null;
        }
        return null;
    }

    private ActionBarSpec getActionBarSpec(ActionBarContainer actionBarContainer, ActionBarView actionBarView) {
        ActionBarSpec actionBarSpec = new ActionBarSpec();
        actionBarSpec.deviceType = this.mOverlayLayout.getDeviceType();
        actionBarSpec.windowMode = this.mWindowMode;
        if (actionBarContainer != null && actionBarView != null) {
            float f2 = actionBarView.getContext().getResources().getDisplayMetrics().density;
            Point windowSize = EnvStateManager.getWindowSize(actionBarView.getContext());
            int i2 = windowSize.x;
            actionBarSpec.windowWidth = i2;
            actionBarSpec.windowHeight = windowSize.y;
            actionBarSpec.windowWidthDp = MiuixUIUtils.px2dp(f2, i2);
            actionBarSpec.windowHeightDp = MiuixUIUtils.px2dp(f2, actionBarSpec.windowHeight);
            int measuredWidth = actionBarContainer.getMeasuredWidth();
            actionBarSpec.actionBarWidth = measuredWidth;
            if (measuredWidth == 0) {
                actionBarSpec.actionBarWidth = this.mOverlayLayout.getMeasuredWidth();
            }
            actionBarSpec.actionBarWidthDp = MiuixUIUtils.px2dp(f2, actionBarSpec.actionBarWidth);
            int measuredHeight = actionBarView.getMeasuredHeight();
            actionBarSpec.actionBarHeight = measuredHeight;
            actionBarSpec.actionBarHeightDp = MiuixUIUtils.px2dp(f2, measuredHeight);
            actionBarSpec.isUserSetExpandState = actionBarView.isUserSetExpandState();
            actionBarSpec.expandState = actionBarView.getExpandState();
            actionBarSpec.resizable = actionBarView.isResizable();
            actionBarSpec.isUserSetEndActionMenuItemLimit = actionBarView.isUserSetEndActionMenuItemLimit();
            actionBarSpec.endActionMenuItemLimit = actionBarView.getEndActionMenuItemLimit();
        }
        Context context = this.mContext;
        if (context instanceof AppCompatActivity) {
            actionBarSpec.isInFloatingWindowMode = ((AppCompatActivity) context).isInFloatingWindowMode();
        }
        return actionBarSpec;
    }

    private int getBlurOptions() {
        return ((getDisplayOptions() & 32768) != 0 ? 32768 : 0) | ((getDisplayOptions() & 16384) != 0 ? 16384 : 0);
    }

    private Integer getCoordinateOffsetViewTopOffsetOrDefault(View view) {
        Integer num = this.mCoordinateOffsetViewSet.get(view);
        return Integer.valueOf(Objects.equals(num, UNINITIALIZED_OFFSET) ? 0 : num.intValue());
    }

    private int getSplitHeight() {
        View childAt;
        int height = this.mSplitView.getHeight();
        if (this.mSplitView.getChildCount() != 1 || (childAt = this.mSplitView.getChildAt(0)) == null) {
            return height;
        }
        if (childAt instanceof ResponsiveActionMenuView) {
            return height;
        }
        if (!(childAt instanceof PhoneActionMenuView)) {
            return height;
        }
        PhoneActionMenuView phoneActionMenuView = (PhoneActionMenuView) childAt;
        return !phoneActionMenuView.isOverflowMenuShowing() ? phoneActionMenuView.getCollapsedHeight() : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createActionBarCoordinateListener$1(int i2, float f2, int i3, int i4) {
        this.mCurrentActionBarHeightGapOnShow = i3;
        this.mActionBarHeightTotalGapOnShow = i4;
        float height = (this.mContainerView.getHeight() + this.mContainerView.getTranslationY()) / this.mContainerView.getHeight();
        float f3 = this.mTargetTranslationY;
        if (f3 != 0.0f) {
            height = (f3 - this.mContainerView.getTranslationY()) / this.mTargetTranslationY;
        }
        if (this.mContainerView.getHeight() == 0) {
            height = 1.0f;
        }
        this.mCurrentActionBarHeightGap = (int) (this.mCurrentActionBarHeightGapOnShow * height);
        this.mActionBarHeightTotalGap = (int) (this.mActionBarHeightTotalGapOnShow * height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionBarStrategy$0() {
        applyActionBarStrategy(this.mActionView, this.mContextView);
    }

    private void measureSearchActionModeView() {
        this.mSearchActionModeView.measure(ViewGroup.getChildMeasureSpec(this.mOverlayLayout.getMeasuredWidth(), 0, this.mSearchActionModeView.getLayoutParams().width), ViewGroup.getChildMeasureSpec(this.mOverlayLayout.getMeasuredHeight(), 0, this.mSearchActionModeView.getLayoutParams().height));
    }

    private void setHasEmbeddedTabs(boolean z2) {
        this.mContainerView.setTabContainer(null);
        this.mActionView.setEmbeddedTabView(this.mTabScrollView, this.mExpandTabScrollView, this.mSecondaryTabScrollView, this.mSecondaryExpandTabScrollView);
        boolean z3 = getNavigationMode() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.mTabScrollView;
        if (scrollingTabContainerView != null) {
            if (z3) {
                scrollingTabContainerView.setVisibility(0);
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
            this.mTabScrollView.setEmbeded(true);
        }
        ScrollingTabContainerView scrollingTabContainerView2 = this.mExpandTabScrollView;
        if (scrollingTabContainerView2 != null) {
            if (z3) {
                scrollingTabContainerView2.setVisibility(0);
            } else {
                scrollingTabContainerView2.setVisibility(8);
            }
            this.mExpandTabScrollView.setEmbeded(true);
        }
        SecondaryTabBar secondaryTabBar = this.mSecondaryTabScrollView;
        if (secondaryTabBar != null) {
            if (z3) {
                secondaryTabBar.asViewGroup().setVisibility(0);
            } else {
                secondaryTabBar.asViewGroup().setVisibility(8);
            }
        }
        SecondaryTabBar secondaryTabBar2 = this.mSecondaryExpandTabScrollView;
        if (secondaryTabBar2 != null) {
            if (z3) {
                secondaryTabBar2.asViewGroup().setVisibility(0);
            } else {
                secondaryTabBar2.asViewGroup().setVisibility(8);
            }
        }
        this.mActionView.setCollapsable(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00a9 A[PHI: r2
      0x00a9: PHI (r2v9 miuix.animation.controller.AnimState) = 
      (r2v5 miuix.animation.controller.AnimState)
      (r2v5 miuix.animation.controller.AnimState)
      (r2v15 miuix.animation.controller.AnimState)
      (r2v15 miuix.animation.controller.AnimState)
     binds: [B:19:0x00dd, B:20:0x00df, B:11:0x0092, B:12:0x0094] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private miuix.animation.IStateStyle startContainerViewAnimation(boolean r15, java.lang.String r16, miuix.animation.controller.AnimState r17, miuix.animation.controller.AnimState r18) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarImpl.startContainerViewAnimation(boolean, java.lang.String, miuix.animation.controller.AnimState, miuix.animation.controller.AnimState):miuix.animation.IStateStyle");
    }

    private IStateStyle startSplitViewAnimation(boolean z2, String str, AnimState animState) {
        int splitHeight = getSplitHeight();
        if (z2) {
            AnimConfig animConfig = new AnimConfig();
            animConfig.setEase(EaseManager.getStyle(-2, 0.9f, 0.25f));
            AnimState animStateAdd = new AnimState(str).add(ViewProperty.TRANSLATION_Y, 0.0d).add(ViewProperty.ALPHA, 1.0d);
            IStateStyle iStateStyleState = Folme.useAt(this.mSplitView).state();
            if (animState != null) {
                animState.setTag(str);
                iStateStyleState = iStateStyleState.setTo(animState);
            }
            return iStateStyleState.to(animStateAdd, animConfig);
        }
        AnimConfig animConfig2 = new AnimConfig();
        animConfig2.setEase(EaseManager.getStyle(-2, 1.0f, 0.35f));
        animConfig2.addListeners(new ViewHideTransitionListener(this.mSplitView, this));
        AnimState animStateAdd2 = new AnimState(str).add(ViewProperty.TRANSLATION_Y, splitHeight + 100).add(ViewProperty.ALPHA, 0.0d);
        IStateStyle iStateStyleState2 = Folme.useAt(this.mSplitView).state();
        if (animState != null) {
            animState.setTag(str);
            iStateStyleState2 = iStateStyleState2.setTo(animState);
        }
        return iStateStyleState2.to(animStateAdd2, animConfig2);
    }

    private void updateContentMaskVisibility(boolean z2) {
        if (this.mSplitView.getChildCount() == 2 && (this.mSplitView.getChildAt(1) instanceof PhoneActionMenuView)) {
            PhoneActionMenuView phoneActionMenuView = (PhoneActionMenuView) this.mSplitView.getChildAt(1);
            this.mSplitMenuView = phoneActionMenuView;
            if (!phoneActionMenuView.isOverflowMenuShowing() || this.mContentMask == null) {
                return;
            }
            if (z2) {
                this.mOverlayLayout.getContentMaskAnimator(this.mContentMaskOnClickListener).show().start();
            } else {
                this.mOverlayLayout.getContentMaskAnimator(null).hide().start();
            }
        }
    }

    private void updateVisibility(boolean z2) {
        updateVisibility(z2, true, null);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener) {
        this.mContainerView.addActionBarTransitionListener(actionBarTransitionListener);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnItemView(int i2) {
        addBadgeOnItemView(i2, 2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnMoreButton() {
        addBadgeOnMoreButton(2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public int addFragmentTab(String str, ActionBar.Tab tab, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        return this.mViewPagerController.addFragmentTab(str, tab, cls, bundle, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addNumberBadgeOnItemView(int i2, int i3) {
        addNumberBadgeOnItemView(i2, i3, 2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addNumberBadgeOnMoreButton(int i2) {
        addNumberBadgeOnMoreButton(i2, 2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addOnFragmentViewPagerChangeListener(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        this.mViewPagerController.addOnFragmentViewPagerChangeListener(fragmentViewPagerChangeListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.add(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab) {
        addTab(tab, this.mTabs.isEmpty());
    }

    public void animateToMode(boolean z2) {
        if (z2) {
            showForActionMode();
        } else {
            hideForActionMode();
        }
        this.mActionModeView.animateToVisibility(z2);
        if (this.mTabScrollView == null || this.mActionView.isTightTitleWithEmbeddedTabs() || !this.mActionView.isCollapsed()) {
            return;
        }
        this.mTabScrollView.setEnabled(!z2);
        this.mExpandTabScrollView.setEnabled(!z2);
        this.mSecondaryTabScrollView.asViewGroup().setEnabled(!z2);
        this.mSecondaryExpandTabScrollView.asViewGroup().setEnabled(!z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void clearBadgeOnItemView(int i2) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.clearBadgeOnItemView(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void clearBadgeOnMoreButton() {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.clearBadgeOnMoreButton();
    }

    public ActionBarCoordinateListener createActionBarCoordinateListener() {
        return new ActionBarCoordinateListener() { // from class: miuix.appcompat.internal.app.widget.e
            @Override // miuix.appcompat.internal.app.widget.ActionBarCoordinateListener
            public final void onActionBarResizing(int i2, float f2, int i3, int i4) {
                this.f6067a.lambda$createActionBarCoordinateListener$1(i2, f2, i3, i4);
            }
        };
    }

    public ActionModeView createActionModeView(ActionMode.Callback callback) {
        ActionModeView actionModeView;
        int i2;
        if (callback instanceof SearchActionMode.Callback) {
            if (this.mSearchActionModeView == null) {
                SearchActionModeView searchActionModeViewCreateSearchActionModeView = createSearchActionModeView();
                this.mSearchActionModeView = searchActionModeViewCreateSearchActionModeView;
                searchActionModeViewCreateSearchActionModeView.setExtraPaddingPolicy(this.mExtraPaddingPolicy);
            }
            if (this.mOverlayLayout != this.mSearchActionModeView.getParent()) {
                this.mOverlayLayout.addView(this.mSearchActionModeView);
            }
            measureSearchActionModeView();
            this.mSearchActionModeView.addAnimationListener(this.mActionView);
            actionModeView = this.mSearchActionModeView;
        } else {
            actionModeView = this.mContextView;
            if (actionModeView == null) {
                throw new IllegalStateException("not set windowSplitActionBar true in activity style!");
            }
        }
        if ((actionModeView instanceof ActionBarContextView) && (i2 = this.mMaxActionMenuItemCount) != -1) {
            ((ActionBarContextView) actionModeView).setActionMenuItemLimit(i2);
        }
        return actionModeView;
    }

    public SearchActionModeView createSearchActionModeView() {
        SearchActionModeView searchActionModeView = (SearchActionModeView) LayoutInflater.from(getThemedContext()).inflate(R.layout.miuix_appcompat_search_action_mode_view, (ViewGroup) this.mOverlayLayout, false);
        searchActionModeView.setOverlayModeView(this.mOverlayLayout);
        searchActionModeView.setOnBackClickListener(new View.OnClickListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActionMode actionMode = ActionBarImpl.this.mActionMode;
                if (actionMode != null) {
                    actionMode.finish();
                }
            }
        });
        return searchActionModeView;
    }

    public ActionBarContainer getActionBarContainer() {
        return this.mContainerView;
    }

    public ActionBarOverlayLayout getActionBarOverlayLayout() {
        return this.mOverlayLayout;
    }

    @Override // miuix.appcompat.app.ActionBar
    public IActionBarStrategy getActionBarStrategy() {
        return this.mActionBarStrategy;
    }

    @Override // miuix.appcompat.app.ActionBar
    public View getActionBarView() {
        return this.mActionView;
    }

    @Override // miuix.appcompat.app.ActionBar
    public CollapseTitle getCollapseTitle() {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null) {
            return actionBarView.getCollapseTitle();
        }
        return null;
    }

    public View getContentView() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            return actionBarOverlayLayout.findViewById(android.R.id.content);
        }
        return null;
    }

    @Override // androidx.appcompat.app.ActionBar
    public View getCustomView() {
        return this.mActionView.getCustomNavigationView();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mActionView.getDisplayOptions();
    }

    @Override // miuix.appcompat.app.ActionBar
    public View getEndView() {
        return this.mActionView.getEndView();
    }

    @Override // miuix.appcompat.app.ActionBar
    public int getExpandState() {
        return this.mActionView.getExpandState();
    }

    @Override // miuix.appcompat.app.ActionBar
    public ExpandTitle getExpandTitle() {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null) {
            return actionBarView.getExpandTitle();
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBar
    public int getExpandedHeight() {
        return this.mContainerView.getExpandedHeight();
    }

    @Override // miuix.appcompat.app.ActionBar
    public Fragment getFragmentAt(int i2) {
        return this.mViewPagerController.getFragmentAt(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public int getFragmentTabCount() {
        return this.mViewPagerController.getFragmentTabCount();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    @Override // miuix.appcompat.app.ActionBar
    public Map<Integer, Boolean> getHyperMenuPrimaryCheckedData() {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null && actionBarView.isEndActionMenuEnable() && this.mActionView.isHyperActionMenuEnable()) {
            return this.mActionView.getHyperMenuPrimaryCheckedData();
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBar
    public Map<Integer, Boolean[]> getHyperMenuSecondaryCheckedData() {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null && actionBarView.isEndActionMenuEnable() && this.mActionView.isHyperActionMenuEnable()) {
            return this.mActionView.getHyperMenuSecondaryCheckedData();
        }
        return null;
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getNavigationItemCount() {
        int navigationMode = this.mActionView.getNavigationMode();
        if (navigationMode != 1) {
            if (navigationMode != 2) {
                return 0;
            }
            return this.mTabs.size();
        }
        SpinnerAdapter dropdownAdapter = this.mActionView.getDropdownAdapter();
        if (dropdownAdapter != null) {
            return dropdownAdapter.getCount();
        }
        return 0;
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getNavigationMode() {
        return this.mActionView.getNavigationMode();
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getSelectedNavigationIndex() {
        TabImpl tabImpl;
        int navigationMode = this.mActionView.getNavigationMode();
        if (navigationMode == 1) {
            return this.mActionView.getDropdownSelectedPosition();
        }
        if (navigationMode == 2 && (tabImpl = this.mSelectedTab) != null) {
            return tabImpl.getPosition();
        }
        return -1;
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    @Override // miuix.appcompat.app.ActionBar
    public View getStartView() {
        return this.mActionView.getStartView();
    }

    @Override // miuix.appcompat.app.ActionBar
    public View getSubTitleView(int i2) {
        return this.mActionView.getSubTitleView(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mActionView.getSubtitle();
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab getTabAt(int i2) {
        return this.mTabs.get(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public int getTabCount() {
        return this.mTabs.size();
    }

    @Override // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(android.R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i2);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    @Override // androidx.appcompat.app.ActionBar
    public CharSequence getTitle() {
        return this.mActionView.getTitle();
    }

    @Override // miuix.appcompat.app.ActionBar
    public View getTitleView(int i2) {
        return this.mActionView.getTitleView(i2);
    }

    public int getTopOffsetForCoordinateView(View view) {
        if (this.mCoordinateOffsetViewSet.containsKey(view)) {
            return getCoordinateOffsetViewTopOffsetOrDefault(view).intValue();
        }
        return -1;
    }

    public int getTopViewHeight() {
        ActionModeView actionModeView;
        if (this.mActionMode != null && (actionModeView = this.mActionModeView) != null) {
            return actionModeView.getViewHeight();
        }
        if (this.mActionView.isCollapsed()) {
            return 0;
        }
        return this.mActionView.getCollapsedHeight();
    }

    @Override // miuix.appcompat.app.ActionBar
    public int getViewPagerOffscreenPageLimit() {
        return this.mViewPagerController.getViewPagerOffscreenPageLimit();
    }

    public boolean hasNonEmbeddedTabs() {
        return false;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void hide() {
        hide((AnimState) null);
    }

    public void hideForActionMode() {
        if (this.mShowingForMode) {
            this.mShowingForMode = false;
            this.mActionView.onActionModeEnd((getDisplayOptions() & 32768) != 0);
            updateVisibility(false);
            if (this.mActionModeView instanceof SearchActionModeView) {
                setResizable(this.mCurrentResizable);
            } else {
                this.mContainerView.finishActionMode();
                this.mCurrentResizable = ((ActionBarContextView) this.mActionModeView).isResizable();
                this.mCurrentExpandState = ((ActionBarContextView) this.mActionModeView).getExpandState();
                setResizable(this.mCurrentResizable);
                this.mActionView.setExpandState(this.mCurrentExpandState);
            }
            this.mActionView.setImportantForAccessibility(this.mCurrentAccessibilityImportant);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void init(@Nullable ViewGroup viewGroup) {
        int iResolveInt;
        ExtraPaddingPolicy extraPaddingPolicy;
        if (viewGroup == null) {
            return;
        }
        TypedValue typedValueResolveTypedValue = AttributeResolver.resolveTypedValue(this.mContext, R.attr.actionBarStrategy);
        if (typedValueResolveTypedValue != null) {
            try {
                this.mActionBarStrategy = (IActionBarStrategy) Class.forName(typedValueResolveTypedValue.string.toString()).getDeclaredConstructor(null).newInstance(null);
            } catch (Exception unused) {
            }
        }
        this.mWindowMode = EnvStateManager.getWindowInfo(this.mContext).windowMode;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) viewGroup;
        this.mOverlayLayout = actionBarOverlayLayout;
        actionBarOverlayLayout.setActionBar(this);
        ActionBarView actionBarView = (ActionBarView) viewGroup.findViewById(R.id.action_bar);
        this.mActionView = actionBarView;
        if (actionBarView != null && (extraPaddingPolicy = this.mExtraPaddingPolicy) != null) {
            actionBarView.setExtraPaddingPolicy(extraPaddingPolicy);
        }
        this.mContextView = (ActionBarContextView) viewGroup.findViewById(R.id.action_context_bar);
        this.mContainerView = (ActionBarContainer) viewGroup.findViewById(R.id.action_bar_container);
        this.mSplitView = (ActionBarContainer) viewGroup.findViewById(R.id.split_action_bar);
        View viewFindViewById = viewGroup.findViewById(R.id.content_mask);
        this.mContentMask = viewFindViewById;
        if (viewFindViewById != null) {
            this.mContentMaskOnClickListener = new View.OnClickListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ActionBarImpl.this.mSplitMenuView == null || !ActionBarImpl.this.mSplitMenuView.isOverflowMenuShowing()) {
                        return;
                    }
                    ActionBarImpl.this.mSplitMenuView.getPresenter().hideOverflowMenu(true);
                }
            };
        }
        ActionBarView actionBarView2 = this.mActionView;
        if (actionBarView2 == null && this.mContextView == null && this.mContainerView == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.mContextDisplayMode = actionBarView2.isSplitActionBar() ? 1 : 0;
        Object[] objArr = (this.mActionView.getDisplayOptions() & 4) != 0;
        if (objArr != false) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.mContext);
        setHomeButtonEnabled(actionBarPolicy.enableHomeButtonByDefault() || objArr == true);
        setHasEmbeddedTabs(actionBarPolicy.hasEmbeddedTabs());
        boolean z2 = MiuiBlurUtils.isEnable() && !LiteUtils.isCommonLiteStrategy();
        ActionBarContainer actionBarContainer = this.mContainerView;
        if (actionBarContainer != null) {
            actionBarContainer.setSupportBlur(z2);
        }
        ActionBarContainer actionBarContainer2 = this.mSplitView;
        if (actionBarContainer2 != null) {
            actionBarContainer2.setSupportBlur(z2);
        }
        if (z2 && (iResolveInt = AttributeResolver.resolveInt(this.mContext, R.attr.bgBlurOptions, 0)) != 0) {
            int displayOptions = getDisplayOptions();
            if ((iResolveInt & 1) != 0) {
                displayOptions |= 32768;
            }
            if ((iResolveInt & 2) != 0) {
                displayOptions |= 16384;
            }
            setDisplayOptions(displayOptions);
        }
        if (this.mActionBarStrategy == null) {
            this.mActionBarStrategy = new CommonActionBarStrategy();
        }
        this.mOverlayLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarImpl.4
            int lastWidth = 0;

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                int measuredWidth = ActionBarImpl.this.mOverlayLayout.getMeasuredWidth();
                if (this.lastWidth == measuredWidth && !ActionBarImpl.this.mIsWindowInfoChanged) {
                    return true;
                }
                ActionBarImpl.this.mIsWindowInfoChanged = false;
                this.lastWidth = measuredWidth;
                ActionBarImpl actionBarImpl = ActionBarImpl.this;
                actionBarImpl.applyActionBarStrategy(actionBarImpl.mActionView, ActionBarImpl.this.mContextView);
                ActionBarImpl.this.mOverlayLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
        this.mOverlayLayout.addOnLayoutChangeListener(new AnonymousClass5());
    }

    public void internalAddTab(ActionBar.Tab tab) {
        internalAddTab(tab, getTabCount() == 0);
    }

    public void internalRemoveAllTabs() {
        cleanupTabs();
    }

    public void internalRemoveTab(ActionBar.Tab tab) {
        internalRemoveTabAt(tab.getPosition());
    }

    public void internalRemoveTabAt(int i2) {
        if (this.mTabScrollView == null) {
            return;
        }
        TabImpl tabImpl = this.mSelectedTab;
        int position = tabImpl != null ? tabImpl.getPosition() : this.mSavedTabPosition;
        this.mTabScrollView.removeTabAt(i2);
        this.mExpandTabScrollView.removeTabAt(i2);
        this.mSecondaryTabScrollView.removeTabAt(i2);
        this.mSecondaryExpandTabScrollView.removeTabAt(i2);
        TabImpl tabImplRemove = this.mTabs.remove(i2);
        if (tabImplRemove != null) {
            tabImplRemove.setPosition(-1);
        }
        int size = this.mTabs.size();
        for (int i3 = i2; i3 < size; i3++) {
            this.mTabs.get(i3).setPosition(i3);
        }
        if (position == i2) {
            selectTab(this.mTabs.isEmpty() ? null : this.mTabs.get(Math.max(0, i2 - 1)));
        }
        if (this.mTabs.isEmpty()) {
            this.mSavedTabPosition = -1;
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public boolean isAdsorptionToNoOverlay() {
        return this.mAdsorptionToNoOverlay;
    }

    @Override // miuix.appcompat.app.ActionBar
    public boolean isFragmentViewPagerMode() {
        return this.mViewPagerController != null;
    }

    @Override // miuix.appcompat.app.ActionBar
    public boolean isResizable() {
        return this.mActionView.isResizable();
    }

    public boolean isShowHideAnimationEnabled() {
        return this.mShowHideAnimationEnabled;
    }

    @Override // androidx.appcompat.app.ActionBar
    public boolean isShowing() {
        return this.mNowShowing;
    }

    @Override // androidx.appcompat.app.ActionBar
    public ActionBar.Tab newTab() {
        return new TabImpl();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration configuration) {
        this.mIsWindowInfoChanged = true;
        this.mWindowMode = EnvStateManager.getWindowInfo(this.mContext, configuration).windowMode;
        setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
        SearchActionModeView searchActionModeView = this.mSearchActionModeView;
        if (searchActionModeView == null || searchActionModeView.isAttachedToWindow()) {
            return;
        }
        this.mSearchActionModeView.onConfigurationChanged(configuration);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void onDestroy() {
    }

    public void onFloatingModeChanged(boolean z2) {
        this.mContainerView.setIsMiuixFloating(z2);
        SearchActionModeView searchActionModeView = this.mSearchActionModeView;
        if (searchActionModeView != null) {
            searchActionModeView.onFloatingModeChanged();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBar
    public void registerCoordinateScrollView(View view) {
        if (view == 0) {
            Log.w("miuix-appcompat", "warning!! the view is null on registerCoordinateScrollView!!");
            return;
        }
        if (view instanceof NestedContentInsetObserver) {
            NestedContentInsetObserver nestedContentInsetObserver = (NestedContentInsetObserver) view;
            this.mNestedContentInsetObserverSet.add(nestedContentInsetObserver);
            Rect rect = this.mContentInset;
            if (rect != null) {
                nestedContentInsetObserver.onContentInsetChanged(rect);
            }
        } else {
            HashMap<View, Integer> map = this.mCoordinateOffsetViewSet;
            Rect rect2 = this.mContentInset;
            map.put(view, Integer.valueOf(rect2 != null ? rect2.top : UNINITIALIZED_OFFSET.intValue()));
            Rect rect3 = this.mContentInset;
            if (rect3 != null) {
                this.mCoordinateOffsetViewSet.put(view, Integer.valueOf(rect3.top));
                doUpdateTopOffsetForCoordinateView(view, this.mContentInset.top);
            }
        }
        if (this.mContainerView.getActionBarCoordinateListener() == null) {
            this.mContainerView.setActionBarCoordinateListener(createActionBarCoordinateListener());
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void registerCoordinatedScrollView(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.registerCoordinatedScrollView(view);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener) {
        this.mContainerView.removeActionBarTransitionListener(actionBarTransitionListener);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeAllFragmentTab() {
        this.mViewPagerController.removeAllFragmentTab();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeAllTabs() {
        if (isFragmentViewPagerMode()) {
            throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
        }
        internalRemoveAllTabs();
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeFragmentTab(String str) {
        this.mViewPagerController.removeFragmentTab(str);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeFragmentTabAt(int i2) {
        this.mViewPagerController.removeFragmentAt(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeOnFragmentViewPagerChangeListener(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        this.mViewPagerController.removeOnFragmentViewPagerChangeListener(fragmentViewPagerChangeListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.mMenuVisibilityListeners.remove(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeTab(ActionBar.Tab tab) {
        if (isFragmentViewPagerMode()) {
            throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
        }
        internalRemoveTab(tab);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeTabAt(int i2) {
        if (isFragmentViewPagerMode()) {
            throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
        }
        internalRemoveTabAt(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void replaceFragmentTab(String str, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        this.mViewPagerController.replaceFragmentTab(str, i2, cls, bundle, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void resetCoordinateScrollView(View view) {
        if (view instanceof NestedContentInsetObserver) {
            if (this.mNestedContentInsetObserverSet.contains(view)) {
                doUpdateTopOffsetForCoordinateView(view, 0);
            }
        } else if (this.mCoordinateOffsetViewSet.containsKey(view)) {
            HashMap<View, Integer> map = this.mCoordinateOffsetViewSet;
            Rect rect = this.mContentInset;
            map.put(view, Integer.valueOf(rect != null ? rect.top : UNINITIALIZED_OFFSET.intValue()));
            Rect rect2 = this.mContentInset;
            doUpdateTopOffsetForCoordinateView(view, rect2 != null ? rect2.top : 0);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void restoreHyperMenuPrimaryCheckedData(Map<Integer, Boolean> map) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null && actionBarView.isEndActionMenuEnable() && this.mActionView.isHyperActionMenuEnable()) {
            this.mActionView.restorePrimaryMenuCheckedData(map);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void restoreHyperMenuSecondaryCheckedData(Map<Integer, Boolean[]> map) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView != null && actionBarView.isEndActionMenuEnable() && this.mActionView.isHyperActionMenuEnable()) {
            this.mActionView.restoreSecondaryMenuCheckedData(map);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void selectTab(ActionBar.Tab tab) {
        selectTab(tab, true);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setActionBarStrategy(IActionBarStrategy iActionBarStrategy) {
        this.mActionBarStrategy = iActionBarStrategy;
        this.mOverlayLayout.post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f6068a.lambda$setActionBarStrategy$0();
            }
        });
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setActionMenuItemLimit(int i2) {
        this.mMaxActionMenuItemCount = i2;
        this.mActionView.setActionMenuItemLimit(i2);
        ActionModeView actionModeView = this.mActionModeView;
        if (actionModeView instanceof ActionBarContextView) {
            ((ActionBarContextView) actionModeView).setActionMenuItemLimit(this.mMaxActionMenuItemCount);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setActionModeAnim(boolean z2) {
        ActionBarContextView actionBarContextView = this.mContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setActionModeAnim(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setAdsorptionToNoOverlay(boolean z2) {
        this.mAdsorptionToNoOverlay = z2;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable) {
        this.mContainerView.setPrimaryBackground(drawable);
    }

    public void setBlur(@Nullable Boolean bool) {
        ActionBarContainer actionBarContainer;
        if ((getDisplayOptions() & 32768) == 0 || (actionBarContainer = this.mContainerView) == null) {
            return;
        }
        actionBarContainer.setActionBarBlur(bool);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(View view) {
        this.mActionView.setCustomNavigationView(view);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z2) {
        int blurOptions = getBlurOptions();
        setDisplayOptions((z2 ? 4 : 0) | blurOptions, blurOptions | 4);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int i2, int i3) {
        int displayOptions = this.mActionView.getDisplayOptions();
        if ((i3 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mActionView.setDisplayOptions((i2 & i3) | ((~i3) & displayOptions));
        int displayOptions2 = this.mActionView.getDisplayOptions();
        ActionBarContainer actionBarContainer = this.mContainerView;
        if (actionBarContainer != null) {
            actionBarContainer.setEnableBlur((32768 & displayOptions2) != 0);
        }
        ActionBarContainer actionBarContainer2 = this.mSplitView;
        if (actionBarContainer2 != null) {
            actionBarContainer2.setEnableBlur((displayOptions2 & 16384) != 0);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z2) {
        int blurOptions = getBlurOptions();
        setDisplayOptions((z2 ? 16 : 0) | blurOptions, blurOptions | 16);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z2) {
        int blurOptions = getBlurOptions();
        setDisplayOptions((z2 ? 2 : 0) | blurOptions, blurOptions | 2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z2) {
        int blurOptions = getBlurOptions();
        setDisplayOptions((z2 ? 8 : 0) | blurOptions, blurOptions | 8);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z2) {
        int blurOptions = getBlurOptions();
        setDisplayOptions((z2 ? 1 : 0) | blurOptions, blurOptions | 1);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setEndActionMenuItemLimit(int i2) {
        this.mActionView.setUserSetEndActionMenuItemLimit(true);
        this.mActionView.setEndActionMenuItemLimit(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setEndView(View view) {
        this.mActionView.setEndView(view);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setExpandState(int i2) {
        this.mActionView.setExpandStateByUser(i2);
        this.mActionView.setExpandState(i2);
        ActionBarContextView actionBarContextView = this.mContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setExpandStateByUser(i2);
            this.mContextView.setExpandState(i2);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setExpandTabTextAppearance(int i2, int i3) {
        this.mExpandTabScrollView.setTextAppearance(i2, i3);
    }

    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        if (this.mExtraPaddingPolicy != extraPaddingPolicy) {
            this.mExtraPaddingPolicy = extraPaddingPolicy;
            ActionBarView actionBarView = this.mActionView;
            if (actionBarView != null) {
                actionBarView.setExtraPaddingPolicy(extraPaddingPolicy);
            }
            SearchActionModeView searchActionModeView = this.mSearchActionModeView;
            if (searchActionModeView != null) {
                searchActionModeView.setExtraPaddingPolicy(this.mExtraPaddingPolicy);
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setFragmentActionMenuAt(int i2, boolean z2) {
        this.mViewPagerController.setFragmentActionMenuAt(i2, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setFragmentViewPagerMode(FragmentActivity fragmentActivity) {
        setFragmentViewPagerMode(fragmentActivity, true);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setHomeButtonEnabled(boolean z2) {
        this.mActionView.setHomeButtonEnabled(z2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setIcon(int i2) {
        this.mActionView.setIcon(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, ActionBar.OnNavigationListener onNavigationListener) {
        this.mActionView.setDropdownAdapter(spinnerAdapter);
        this.mActionView.setCallback(onNavigationListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setLogo(int i2) {
        this.mActionView.setLogo(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setNavigationMode(int i2) {
        if (this.mActionView.getNavigationMode() == 2) {
            this.mSavedTabPosition = getSelectedNavigationIndex();
            selectTab(null);
            this.mTabScrollView.setVisibility(8);
            this.mExpandTabScrollView.setVisibility(8);
            this.mSecondaryTabScrollView.asViewGroup().setVisibility(8);
            this.mSecondaryExpandTabScrollView.asViewGroup().setVisibility(8);
        }
        this.mActionView.setNavigationMode(i2);
        if (i2 == 2) {
            ensureTabsExist();
            this.mTabScrollView.setVisibility(0);
            this.mExpandTabScrollView.setVisibility(0);
            this.mSecondaryTabScrollView.asViewGroup().setVisibility(0);
            this.mSecondaryExpandTabScrollView.asViewGroup().setVisibility(0);
            int i3 = this.mSavedTabPosition;
            if (i3 != -1) {
                setSelectedNavigationItem(i3);
                this.mSavedTabPosition = -1;
            }
        }
        this.mActionView.setCollapsable(false);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setProgress(int i2) {
        this.mActionView.setProgress(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setProgressBarIndeterminate(boolean z2) {
        this.mActionView.setProgressBarIndeterminate(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setProgressBarIndeterminateVisibility(boolean z2) {
        this.mActionView.setProgressBarIndeterminateVisibility(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setProgressBarVisibility(boolean z2) {
        this.mActionView.setProgressBarVisibility(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setResizable(boolean z2) {
        this.mActionView.setResizable(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setSecondaryTabTextAppearance(int i2, int i3) {
        this.mSecondaryTabScrollView.setTextAppearance(i2, i3);
        this.mSecondaryExpandTabScrollView.setTextAppearance(i2, i3);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSelectedNavigationItem(int i2) {
        int navigationMode = this.mActionView.getNavigationMode();
        if (navigationMode == 1) {
            this.mActionView.setDropdownSelectedPosition(i2);
        } else {
            if (navigationMode != 2) {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
            selectTab(this.mTabs.get(i2));
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    @SuppressLint({"RestrictedApi"})
    public void setShowHideAnimationEnabled(boolean z2) {
        this.mShowHideAnimationEnabled = z2;
        if (z2) {
            return;
        }
        if (isShowing()) {
            doShow(false);
        } else {
            doHide(false);
        }
    }

    public void setSplitActionBarBlur(Boolean bool) {
        ActionBarContainer actionBarContainer;
        if ((getDisplayOptions() & 16384) == 0 || (actionBarContainer = this.mSplitView) == null) {
            return;
        }
        actionBarContainer.setSplitActionBarBlur(bool);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable) {
        if (this.mSplitView != null) {
            for (int i2 = 0; i2 < this.mSplitView.getChildCount(); i2++) {
                if (this.mSplitView.getChildAt(i2) instanceof ActionMenuView) {
                    this.mSplitView.getChildAt(i2).setBackground(drawable);
                }
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setStartView(View view) {
        this.mActionView.setStartView(view);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setSubTitleClickListener(View.OnClickListener onClickListener) {
        this.mActionView.setSubTitleClickListener(onClickListener);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setSubTitleDrawable(TextViewDrawableConfig textViewDrawableConfig) {
        this.mActionView.setSubTitleDrawable(textViewDrawableConfig);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence charSequence) {
        this.mActionView.setSubtitle(charSequence);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabBadgeDisappearOnClick(int i2, boolean z2) {
        this.mSecondaryTabScrollView.setTabBadgeDisappearOnClick(i2, z2);
        this.mSecondaryExpandTabScrollView.setTabBadgeDisappearOnClick(i2, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabBadgeVisibility(int i2, boolean z2) {
        this.mTabScrollView.setBadgeVisibility(i2, z2);
        this.mExpandTabScrollView.setBadgeVisibility(i2, z2);
        this.mSecondaryTabScrollView.setBadgeVisibility(i2, z2);
        this.mSecondaryExpandTabScrollView.setBadgeVisibility(i2, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabIconWithPosition(int i2, int i3, int i4, int i5, int i6, int i7) {
        setTabIconWithPosition(i2, i3, i4 != 0 ? this.mContext.getDrawable(i4) : null, i5 != 0 ? this.mContext.getDrawable(i5) : null, i6 != 0 ? this.mContext.getDrawable(i6) : null, i7 != 0 ? this.mContext.getDrawable(i7) : null);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabTextAppearance(int i2, int i3) {
        this.mTabScrollView.setTextAppearance(i2, i3);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabsMode(boolean z2) {
        setHasEmbeddedTabs(z2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence charSequence) {
        this.mActionView.setTitle(charSequence);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTitleClickable(boolean z2) {
        this.mActionView.setTitleClickable(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setViewPagerDecor(View view) {
        this.mViewPagerController.setViewPagerDecor(view);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setViewPagerDraggable(boolean z2) {
        this.mViewPagerController.setViewPagerDraggable(z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setViewPagerOffscreenPageLimit(int i2) {
        this.mViewPagerController.setViewPagerOffscreenPageLimit(i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void show() {
        show((AnimState) null);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void showActionBarShadow(boolean z2) {
    }

    public void showForActionMode() {
        if (this.mShowingForMode) {
            return;
        }
        this.mShowingForMode = true;
        updateVisibility(false);
        this.mCurrentExpandState = getExpandState();
        this.mCurrentResizable = isResizable();
        if (this.mActionModeView instanceof SearchActionModeView) {
            setResizable(false);
        } else {
            this.mContainerView.startActionMode();
            ((ActionBarContextView) this.mActionModeView).setExpandState(this.mCurrentExpandState);
            ((ActionBarContextView) this.mActionModeView).setResizable(this.mCurrentResizable);
        }
        this.mCurrentAccessibilityImportant = this.mActionView.getImportantForAccessibility();
        this.mActionView.setImportantForAccessibility(4);
        this.mActionView.onActionModeStart(this.mActionModeView instanceof SearchActionModeView, (getDisplayOptions() & 32768) != 0);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void showSplitActionBar(boolean z2, boolean z3) {
        if (this.mActionView.isSplitActionBar()) {
            if (z2) {
                this.mSplitView.show(z3);
            } else {
                this.mSplitView.hide(z3);
            }
        }
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        Rect baseInnerInsets;
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        ActionMode actionModeCreateActionMode = createActionMode(callback);
        ActionModeView actionModeView = this.mActionModeView;
        if (((actionModeView instanceof SearchActionModeView) && (actionModeCreateActionMode instanceof SearchActionModeImpl)) || ((actionModeView instanceof ActionBarContextView) && (actionModeCreateActionMode instanceof EditActionModeImpl))) {
            actionModeView.closeMode();
            this.mActionModeView.killMode();
        }
        ActionModeView actionModeViewCreateActionModeView = createActionModeView(callback);
        this.mActionModeView = actionModeViewCreateActionModeView;
        if (actionModeViewCreateActionModeView == null) {
            throw new IllegalStateException("not set windowSplitActionBar true in activity style!");
        }
        if (!(actionModeCreateActionMode instanceof ActionModeImpl)) {
            return null;
        }
        ActionModeImpl actionModeImpl = (ActionModeImpl) actionModeCreateActionMode;
        actionModeImpl.setActionModeView(actionModeViewCreateActionModeView);
        if ((actionModeImpl instanceof SearchActionModeImpl) && (baseInnerInsets = this.mOverlayLayout.getBaseInnerInsets()) != null) {
            ((SearchActionModeImpl) actionModeImpl).setPendingInsets(baseInnerInsets);
        }
        actionModeImpl.setActionModeCallback(this.mActionModeCallback);
        if (!actionModeImpl.dispatchOnCreate()) {
            return null;
        }
        actionModeCreateActionMode.invalidate();
        this.mActionModeView.initForMode(actionModeCreateActionMode);
        animateToMode(true);
        ActionBarContainer actionBarContainer = this.mSplitView;
        if (actionBarContainer != null && this.mContextDisplayMode == 1 && actionBarContainer.getVisibility() != 0) {
            this.mSplitView.setVisibility(0);
        }
        ActionModeView actionModeView2 = this.mActionModeView;
        if (actionModeView2 instanceof ActionBarContextView) {
            ((ActionBarContextView) actionModeView2).sendAccessibilityEvent(32);
        }
        this.mActionMode = actionModeCreateActionMode;
        return actionModeCreateActionMode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBar
    public void unregisterCoordinateScrollView(View view) {
        if (view instanceof NestedContentInsetObserver) {
            this.mNestedContentInsetObserverSet.remove((NestedContentInsetObserver) view);
        } else {
            this.mCoordinateOffsetViewSet.remove(view);
        }
        if (this.mCoordinateOffsetViewSet.size() == 0 && this.mNestedContentInsetObserverSet.size() == 0) {
            this.mContainerView.setActionBarCoordinateListener(null);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void unregisterCoordinatedScrollView(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.unregisterCoordinatedScrollView(view);
        }
    }

    public void updateBackgroundViewBlurState(boolean z2) {
        if (!z2 || MiuiBlurUtils.isEffectEnable(getThemedContext())) {
            SearchActionModeView searchActionModeView = this.mSearchActionModeView;
            if (searchActionModeView != null) {
                searchActionModeView.updateBackground(z2);
            }
            this.mContainerView.updateBackground(z2);
        }
    }

    public void updateContentInsetForNestedObserver(Rect rect) {
        this.mContentInset = rect;
        int i2 = rect.top;
        int i3 = i2 - this.mContentInsetTop;
        this.mContentInsetTop = i2;
        Iterator<NestedContentInsetObserver> it = this.mNestedContentInsetObserverSet.iterator();
        while (it.hasNext()) {
            it.next().onContentInsetChanged(rect);
        }
        for (View view : this.mCoordinateOffsetViewSet.keySet()) {
            Integer num = this.mCoordinateOffsetViewSet.get(view);
            if (num != null && i3 != 0) {
                if (num.equals(UNINITIALIZED_OFFSET)) {
                    num = 0;
                } else if (num.intValue() == 0) {
                }
                int iMax = Math.max(0, num.intValue() + i3);
                this.mCoordinateOffsetViewSet.put(view, Integer.valueOf(iMax));
                doUpdateTopOffsetForCoordinateView(view, iMax);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateCoordinateOffsetView() {
        if (this.mCoordinateOffsetViewSet.size() == 0 && this.mNestedContentInsetObserverSet.size() == 0) {
            this.mContainerView.setActionBarCoordinateListener(null);
            return;
        }
        for (View view : this.mCoordinateOffsetViewSet.keySet()) {
            doUpdateTopOffsetForCoordinateView(view, getCoordinateOffsetViewTopOffsetOrDefault(view).intValue());
        }
        Iterator<NestedContentInsetObserver> it = this.mNestedContentInsetObserverSet.iterator();
        while (it.hasNext()) {
            View view2 = (View) ((NestedContentInsetObserver) it.next());
            if (view2 instanceof NestedCoordinatorObserver) {
                ((NestedCoordinatorObserver) view2).updateCoordinatorHeightGapInfo(this.mCurrentActionBarHeightGap, this.mActionBarHeightTotalGap);
            }
            doUpdateTopOffsetForCoordinateView(view2, 0);
        }
    }

    public void updateTab(int i2) {
        ensureTabsExist();
        this.mTabScrollView.updateTab(i2);
        this.mExpandTabScrollView.updateTab(i2);
        this.mSecondaryTabScrollView.updateTab(i2);
        this.mSecondaryExpandTabScrollView.updateTab(i2);
    }

    public int updateTopOffsetOnNestedPreScroll(View view, int i2) {
        if (this.mCoordinateOffsetViewSet.containsKey(view)) {
            Integer coordinateOffsetViewTopOffsetOrDefault = getCoordinateOffsetViewTopOffsetOrDefault(view);
            if (coordinateOffsetViewTopOffsetOrDefault.intValue() > i2) {
                this.mCoordinateOffsetViewSet.put(view, Integer.valueOf(i2));
                doUpdateTopOffsetForCoordinateView(view, i2);
                return coordinateOffsetViewTopOffsetOrDefault.intValue() - i2;
            }
        }
        return 0;
    }

    public int updateTopOffsetOnNestedScroll(View view, int i2) {
        int i3 = 0;
        for (View view2 : this.mCoordinateOffsetViewSet.keySet()) {
            int iIntValue = getCoordinateOffsetViewTopOffsetOrDefault(view2).intValue();
            int i4 = iIntValue - i2;
            Rect rect = this.mContentInset;
            int iMin = Math.min(i4, rect == null ? 0 : rect.top);
            if (iIntValue < iMin) {
                this.mCoordinateOffsetViewSet.put(view2, Integer.valueOf(iMin));
                doUpdateTopOffsetForCoordinateView(view2, iMin);
                if (view == view2) {
                    i3 = iIntValue - iMin;
                }
            }
        }
        return i3;
    }

    public void updateTopOffsetOnPostScroll(View view, int i2) {
        for (View view2 : this.mCoordinateOffsetViewSet.keySet()) {
            if (view == view2) {
                int iIntValue = getCoordinateOffsetViewTopOffsetOrDefault(view2).intValue();
                Rect rect = this.mContentInset;
                int iMin = Math.min(i2, rect == null ? 0 : rect.top);
                if (iIntValue != iMin) {
                    this.mCoordinateOffsetViewSet.put(view2, Integer.valueOf(iMin));
                    doUpdateTopOffsetForCoordinateView(view2, iMin);
                }
            }
        }
    }

    private void doHide(boolean z2, boolean z3, AnimState animState) {
        AnimState currentState;
        IStateStyle iStateStyle = this.mContainerAnim;
        if (iStateStyle == null || !this.mIsContainerAnimationRunning) {
            currentState = null;
        } else {
            currentState = iStateStyle.getCurrentState();
            this.mContainerAnim.cancel();
        }
        if ((isShowHideAnimationEnabled() || z2) && z3) {
            this.mContainerAnim = startContainerViewAnimation(false, "HideActionBar", currentState, animState);
            return;
        }
        this.mContainerView.setTranslationY(-r4.getHeight());
        this.mContainerView.setAlpha(0.0f);
        this.mActionBarHeightTotalGap = 0;
        this.mCurrentActionBarHeightGap = 0;
        this.mContainerView.setVisibility(8);
    }

    private void doShow(boolean z2, boolean z3, AnimState animState) {
        AnimState currentState;
        IStateStyle iStateStyle = this.mContainerAnim;
        if (iStateStyle == null || !this.mIsContainerAnimationRunning) {
            currentState = null;
        } else {
            currentState = iStateStyle.getCurrentState();
            this.mContainerAnim.cancel();
        }
        boolean z4 = (isShowHideAnimationEnabled() || z2) && z3;
        if (this.mActionMode instanceof SearchActionMode) {
            this.mContainerView.setVisibility(this.mOverlayLayout.isInOverlayMode() ? 4 : 8);
        } else {
            this.mContainerView.setVisibility(0);
        }
        this.mContainerView.resetActionBarBlurConfigOnReshow();
        if (z4) {
            this.mContainerAnim = startContainerViewAnimation(true, "ShowActionBar", currentState, animState);
        } else {
            this.mContainerView.setTranslationY(0.0f);
            this.mContainerView.setAlpha(1.0f);
        }
    }

    private void updateVisibility(boolean z2, boolean z3, AnimState animState) {
        if (checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if (this.mNowShowing) {
                return;
            }
            this.mNowShowing = true;
            doShow(z2, z3, animState);
            return;
        }
        if (this.mNowShowing) {
            this.mNowShowing = false;
            doHide(z2, z3, animState);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnItemView(int i2, int i3) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.addBadgeOnItemView(i2, i3);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnMoreButton(int i2) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.addBadgeOnMoreButton(i2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public int addFragmentTab(String str, ActionBar.Tab tab, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        return this.mViewPagerController.addFragmentTab(str, tab, i2, cls, bundle, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addNumberBadgeOnItemView(int i2, int i3, int i4) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.addNumberBadgeOnItemView(i2, i3, i4);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addNumberBadgeOnMoreButton(int i2, int i3) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.addNumberBadgeOnMoreButton(i2, i3);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, boolean z2) {
        if (isFragmentViewPagerMode()) {
            throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
        }
        internalAddTab(tab, z2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void hide(boolean z2) {
        hide(z2, null);
    }

    public void internalAddTab(ActionBar.Tab tab, boolean z2) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, z2);
        this.mExpandTabScrollView.addTab(tab, z2);
        this.mSecondaryTabScrollView.addTab(tab, z2);
        this.mSecondaryExpandTabScrollView.addTab(tab, z2);
        configureTab(tab, this.mTabs.size());
        if (z2) {
            selectTab(tab);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeFragmentTab(ActionBar.Tab tab) {
        this.mViewPagerController.removeFragmentTab(tab);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void selectTab(ActionBar.Tab tab, boolean z2) {
        if (this.isSelectingTab) {
            this.isSelectingTab = false;
            return;
        }
        this.isSelectingTab = true;
        Context context = this.mContext;
        if ((context instanceof Activity) && (((Activity) context).isDestroyed() || ((Activity) this.mContext).isFinishing())) {
            return;
        }
        if (getNavigationMode() != 2) {
            this.mSavedTabPosition = tab != null ? tab.getPosition() : -1;
            return;
        }
        FragmentTransaction fragmentTransactionDisallowAddToBackStack = this.mFragmentManager.beginTransaction().disallowAddToBackStack();
        TabImpl tabImpl = this.mSelectedTab;
        if (tabImpl != tab) {
            this.mTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1, z2);
            this.mExpandTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1, z2);
            this.mSecondaryTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1);
            this.mSecondaryExpandTabScrollView.setTabSelected(tab != null ? tab.getPosition() : -1);
            TabImpl tabImpl2 = this.mSelectedTab;
            if (tabImpl2 != null) {
                tabImpl2.getCallback().onTabUnselected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            }
            TabImpl tabImpl3 = (TabImpl) tab;
            this.mSelectedTab = tabImpl3;
            if (tabImpl3 != null) {
                tabImpl3.mWithAnim = z2;
                tabImpl3.getCallback().onTabSelected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            }
        } else if (tabImpl != null) {
            tabImpl.getCallback().onTabReselected(this.mSelectedTab, fragmentTransactionDisallowAddToBackStack);
            this.mTabScrollView.animateToTab(tab.getPosition());
            this.mExpandTabScrollView.animateToTab(tab.getPosition());
            this.mSecondaryTabScrollView.animateToTab(tab.getPosition());
            this.mSecondaryExpandTabScrollView.animateToTab(tab.getPosition());
        }
        if (!fragmentTransactionDisallowAddToBackStack.isEmpty()) {
            fragmentTransactionDisallowAddToBackStack.commit();
        }
        this.isSelectingTab = false;
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(View view, ActionBar.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        this.mActionView.setCustomNavigationView(view);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setFragmentViewPagerMode(FragmentActivity fragmentActivity, boolean z2) {
        if (isFragmentViewPagerMode()) {
            return;
        }
        removeAllTabs();
        setNavigationMode(2);
        this.mViewPagerController = new ActionBarViewPagerController(this, this.mFragmentManager, fragmentActivity.getLifecycle(), z2);
        addOnFragmentViewPagerChangeListener(this.mTabScrollView);
        addOnFragmentViewPagerChangeListener(this.mExpandTabScrollView);
        addOnFragmentViewPagerChangeListener(this.mSecondaryTabScrollView);
        addOnFragmentViewPagerChangeListener(this.mSecondaryExpandTabScrollView);
        ActionBarContainer actionBarContainer = this.mSplitView;
        if (actionBarContainer != null) {
            addOnFragmentViewPagerChangeListener(actionBarContainer);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setIcon(Drawable drawable) {
        this.mActionView.setIcon(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setLogo(Drawable drawable) {
        this.mActionView.setLogo(drawable);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setSubtitle(int i2) {
        setSubtitle(this.mContext.getString(i2));
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setTitle(int i2) {
        setTitle(this.mContext.getString(i2));
    }

    @Override // miuix.appcompat.app.ActionBar
    public void show(boolean z2) {
        show(z2, null);
    }

    public class TabImpl extends ActionBar.Tab {
        private ActionBar.TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private ActionBar.TabListener mInternalCallback;
        private Object mTag;
        private CharSequence mText;
        private int mPosition = -1;
        public boolean mWithAnim = true;

        public TabImpl() {
        }

        public ActionBar.TabListener getCallback() {
            return ActionBarImpl.sTabListenerWrapper;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public View getCustomView() {
            return this.mCustomView;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public Drawable getIcon() {
            return this.mIcon;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public int getPosition() {
            return this.mPosition;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public Object getTag() {
            return this.mTag;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public CharSequence getText() {
            return this.mText;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public void select() {
            ActionBarImpl.this.selectTab(this, false);
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(int i2) {
            return setContentDescription(ActionBarImpl.this.mContext.getResources().getText(i2));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(View view) {
            this.mCustomView = view;
            if (!ActionBarImpl.this.mActionView.isUserSetExpandState()) {
                ActionBarImpl.this.mActionView.setExpandState(0);
                ActionBarImpl.this.setResizable(false);
            }
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setIcon(Drawable drawable) {
            this.mIcon = drawable;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mExpandTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryExpandTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public ActionBar.Tab setInternalTabListener(ActionBar.TabListener tabListener) {
            this.mInternalCallback = tabListener;
            return this;
        }

        public void setPosition(int i2) {
            this.mPosition = i2;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setTabListener(ActionBar.TabListener tabListener) {
            this.mCallback = tabListener;
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setTag(Object obj) {
            this.mTag = obj;
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setText(CharSequence charSequence) {
            this.mText = charSequence;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mExpandTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setContentDescription(CharSequence charSequence) {
            this.mContentDesc = charSequence;
            if (this.mPosition >= 0) {
                ActionBarImpl.this.mTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mExpandTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryTabScrollView.updateTab(this.mPosition);
                ActionBarImpl.this.mSecondaryExpandTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setCustomView(int i2) {
            return setCustomView(LayoutInflater.from(ActionBarImpl.this.getThemedContext()).inflate(i2, (ViewGroup) null));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setIcon(int i2) {
            return setIcon(ActionBarImpl.this.mContext.getResources().getDrawable(i2));
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public ActionBar.Tab setText(int i2) {
            return setText(ActionBarImpl.this.mContext.getResources().getText(i2));
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void clearBadgeOnItemView(MenuItem menuItem) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.clearBadgeOnItemView(menuItem);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void hide(AnimState animState) {
        hide(true, animState);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void removeFragmentTab(Fragment fragment) {
        this.mViewPagerController.removeFragment(fragment);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setSecondaryTabTextAppearance(int i2, int i3, int i4) {
        this.mSecondaryTabScrollView.setTextAppearance(i2, i3, i4);
        this.mSecondaryExpandTabScrollView.setTextAppearance(i2, i3, i4);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void show(AnimState animState) {
        show(true, animState);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnItemView(MenuItem menuItem) {
        addBadgeOnItemView(menuItem, 2);
    }

    @Override // miuix.appcompat.app.ActionBar
    public void hide(boolean z2, AnimState animState) {
        if (this.mHiddenByApp) {
            return;
        }
        this.mHiddenByApp = true;
        updateVisibility(false, z2, animState);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setCustomView(int i2) {
        setCustomView(LayoutInflater.from(getThemedContext()).inflate(i2, (ViewGroup) this.mActionView, false));
    }

    @Override // miuix.appcompat.app.ActionBar
    public void show(boolean z2, AnimState animState) {
        if (this.mHiddenByApp) {
            this.mHiddenByApp = false;
            updateVisibility(false, z2, animState);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void addBadgeOnItemView(MenuItem menuItem, int i2) {
        ActionBarView actionBarView = this.mActionView;
        if (actionBarView == null || !actionBarView.isEndActionMenuEnable()) {
            return;
        }
        this.mActionView.addBadgeOnItemView(menuItem, i2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i2) {
        addTab(tab, i2, this.mTabs.isEmpty());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addTab(ActionBar.Tab tab, int i2, boolean z2) {
        if (!isFragmentViewPagerMode()) {
            internalAddTab(tab, i2, z2);
            return;
        }
        throw new IllegalStateException("Cannot add tab directly in fragment view pager mode!\n Please using addFragmentTab().");
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setExpandState(int i2, boolean z2) {
        this.mActionView.setExpandStateByUser(i2);
        this.mActionView.setExpandState(i2, z2, false);
        ActionBarContextView actionBarContextView = this.mContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setExpandStateByUser(i2);
            this.mContextView.setExpandState(i2, z2, false);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setTabIconWithPosition(int i2, int i3, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.mTabScrollView.setTabIconWithPosition(i2, i3, drawable, drawable2, drawable3, drawable4);
        this.mExpandTabScrollView.setTabIconWithPosition(i2, i3, drawable, drawable2, drawable3, drawable4);
        this.mSecondaryTabScrollView.setTabIconWithPosition(i2, i3, drawable, drawable2, drawable3, drawable4);
        this.mSecondaryExpandTabScrollView.setTabIconWithPosition(i2, i3, drawable, drawable2, drawable3, drawable4);
    }

    public void internalAddTab(ActionBar.Tab tab, int i2) {
        internalAddTab(tab, i2, i2 == getTabCount());
    }

    @Override // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int i2) {
        if ((i2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mActionView.setDisplayOptions(i2);
        int displayOptions = this.mActionView.getDisplayOptions();
        ActionBarContainer actionBarContainer = this.mContainerView;
        if (actionBarContainer != null) {
            actionBarContainer.setEnableBlur((displayOptions & 32768) != 0);
        }
        ActionBarContainer actionBarContainer2 = this.mSplitView;
        if (actionBarContainer2 != null) {
            actionBarContainer2.setEnableBlur((i2 & 16384) != 0);
        }
    }

    public void internalAddTab(ActionBar.Tab tab, int i2, boolean z2) {
        ensureTabsExist();
        this.mTabScrollView.addTab(tab, i2, z2);
        this.mExpandTabScrollView.addTab(tab, i2, z2);
        this.mSecondaryTabScrollView.addTab(tab, i2, z2);
        this.mSecondaryExpandTabScrollView.addTab(tab, i2, z2);
        configureTab(tab, i2);
        if (z2) {
            selectTab(tab);
        }
    }

    @Override // miuix.appcompat.app.ActionBar
    public void setExpandState(int i2, boolean z2, boolean z3) {
        this.mActionView.setExpandStateByUser(i2);
        this.mActionView.setExpandState(i2, z2, z3);
        ActionBarContextView actionBarContextView = this.mContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setExpandStateByUser(i2);
            this.mContextView.setExpandState(i2, z2, z3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActionBarImpl(Fragment fragment) {
        this.mContext = ((IFragment) fragment).getThemedContext();
        this.mFragmentManager = fragment.getChildFragmentManager();
        init((ViewGroup) fragment.getView());
        FragmentActivity activity = fragment.getActivity();
        this.mActionView.setWindowTitle(activity != null ? activity.getTitle() : null);
    }

    public ActionBarImpl(Dialog dialog, ViewGroup viewGroup) {
        this.mContext = dialog.getContext();
        init(viewGroup);
    }
}
