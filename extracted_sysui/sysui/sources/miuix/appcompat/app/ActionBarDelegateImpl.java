package miuix.appcompat.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LifecycleOwner;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarContainer;
import miuix.appcompat.internal.app.widget.ActionBarContextView;
import miuix.appcompat.internal.app.widget.ActionBarImpl;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.app.widget.ActionBarView;
import miuix.appcompat.internal.view.menu.ImmersionMenuPopupWindow;
import miuix.appcompat.internal.view.menu.ImmersionMenuPopupWindowImpl;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.container.ExtraPaddingProcessor;
import miuix.core.util.variable.WindowWrapper;
import miuix.internal.util.ViewUtils;
import miuix.os.DeviceHelper;
import miuix.theme.token.ContainerToken;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActionBarDelegateImpl implements ActionBarDelegate, ExtraPaddingProcessor, ExtraPaddingObserver, MenuPresenter.Callback, MenuBuilder.Callback {
    static final String METADATA_UI_OPTIONS = "android.support.UI_OPTIONS";
    private static final String TAG = "ActionBarDelegate";
    static final String UI_OPTION_SPLIT_ACTION_BAR_WHEN_NARROW = "splitActionBarWhenNarrow";
    protected ActionBar mActionBar;
    protected ActionBarView mActionBarView;
    protected ActionMode mActionMode;
    final AppCompatActivity mActivity;

    @Nullable
    protected Rect mContentInset;
    protected int mDeviceType;
    private boolean mEndActionMenuEnabled;
    protected boolean mExtraPaddingApplyToContentEnable;
    protected boolean mExtraPaddingEnable;
    protected boolean mExtraPaddingInitEnable;
    protected ExtraPaddingPolicy mExtraPaddingPolicy;
    protected boolean mFeatureIndeterminateProgress;
    protected boolean mFeatureProgress;
    protected GroupButtonsConfig mGroupButtonsConfig;
    protected boolean mHasActionBar;
    private boolean mHyperActionMenuEnabled;
    private MenuBuilder mImmersionMenu;
    private boolean mImmersionMenuEnabled;
    protected MenuBuilder mMenu;
    private MenuInflater mMenuInflater;
    private ImmersionMenuPopupWindow mMenuPopupWindow;
    private OnBackPressedCallback mOnBackPressedCallback;
    protected boolean mOverlayActionBar;
    protected boolean mSubDecorInstalled;
    protected boolean mUserExtraPaddingPolicy;

    @Nullable
    protected View mViewWithContentInset;

    @Nullable
    protected ViewUtils.RelativePadding mViewWithContentInsetInitPadding;
    private int mTranslucentStatus = 0;
    protected boolean mHasAddSplitActionBar = false;
    protected int mExtraHorizontalPadding = 0;
    protected List<ExtraPaddingObserver> mExtraPaddingObserver = null;
    protected boolean mIsDelegateAnimRunning = false;

    public ActionBarDelegateImpl(AppCompatActivity appCompatActivity) {
        this.mActivity = appCompatActivity;
        this.mDeviceType = DeviceHelper.detectType(appCompatActivity);
    }

    private void updateOnBackPressedCallbackState(boolean z2) {
        OnBackPressedCallback onBackPressedCallback = this.mOnBackPressedCallback;
        if (onBackPressedCallback != null) {
            onBackPressedCallback.setEnabled(z2);
        } else {
            this.mOnBackPressedCallback = new OnBackPressedCallback(z2) { // from class: miuix.appcompat.app.ActionBarDelegateImpl.1
                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    ActionMode actionMode;
                    ActionBarDelegateImpl actionBarDelegateImpl = ActionBarDelegateImpl.this;
                    if (actionBarDelegateImpl.mIsDelegateAnimRunning || (actionMode = actionBarDelegateImpl.mActionMode) == null) {
                        return;
                    }
                    actionMode.finish();
                }
            };
            this.mActivity.getOnBackPressedDispatcher().addCallback(getLifecycleOwner(), this.mOnBackPressedCallback);
        }
    }

    public void addContentMask(ActionBarOverlayLayout actionBarOverlayLayout) {
        if (actionBarOverlayLayout != null) {
            ViewStub viewStub = (ViewStub) actionBarOverlayLayout.findViewById(R.id.content_mask_vs);
            actionBarOverlayLayout.setContentMask(viewStub != null ? viewStub.inflate() : actionBarOverlayLayout.findViewById(R.id.content_mask));
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        if (this.mExtraPaddingObserver == null) {
            this.mExtraPaddingObserver = new CopyOnWriteArrayList();
        }
        if (this.mExtraPaddingObserver.contains(extraPaddingObserver)) {
            this.mExtraPaddingObserver.add(extraPaddingObserver);
            extraPaddingObserver.setExtraHorizontalPadding(this.mExtraHorizontalPadding);
        }
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig) {
        this.mGroupButtonsConfig = groupButtonsConfig;
    }

    public void addSplitActionBar(boolean z2, boolean z3, ActionBarOverlayLayout actionBarOverlayLayout) {
        if (this.mHasAddSplitActionBar) {
            return;
        }
        this.mHasAddSplitActionBar = true;
        ViewStub viewStub = (ViewStub) actionBarOverlayLayout.findViewById(R.id.split_action_bar_vs);
        ActionBarContainer actionBarContainer = viewStub != null ? (ActionBarContainer) viewStub.inflate() : (ActionBarContainer) actionBarOverlayLayout.findViewById(R.id.split_action_bar);
        if (actionBarContainer != null) {
            this.mActionBarView.setSplitView(actionBarContainer);
            this.mActionBarView.setSplitActionBar(z2);
            this.mActionBarView.setSplitWhenNarrow(z3);
            actionBarOverlayLayout.setSplitActionBarView(actionBarContainer);
            addContentMask(actionBarOverlayLayout);
        }
        ActionBarContainer actionBarContainer2 = (ActionBarContainer) actionBarOverlayLayout.findViewById(R.id.action_bar_container);
        ViewStub viewStub2 = (ViewStub) actionBarOverlayLayout.findViewById(R.id.action_context_bar_vs);
        ActionBarContextView actionBarContextView = viewStub2 != null ? (ActionBarContextView) viewStub2.inflate() : (ActionBarContextView) actionBarOverlayLayout.findViewById(R.id.action_context_bar);
        if (actionBarContextView != null) {
            actionBarContainer2.setActionBarContextView(actionBarContextView);
            actionBarOverlayLayout.setActionBarContextView(actionBarContextView);
            if (actionBarContainer != null) {
                actionBarContextView.setSplitView(actionBarContainer);
                actionBarContextView.setSplitActionBar(z2);
                actionBarContextView.setSplitWhenNarrow(z3);
            }
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void bindViewWithContentInset(View view) {
        this.mViewWithContentInset = view;
        ViewUtils.RelativePadding relativePadding = new ViewUtils.RelativePadding(ViewCompat.getPaddingStart(view), this.mViewWithContentInset.getPaddingTop(), ViewCompat.getPaddingEnd(this.mViewWithContentInset), this.mViewWithContentInset.getPaddingBottom());
        this.mViewWithContentInsetInitPadding = relativePadding;
        if (view instanceof ViewGroup) {
            relativePadding.clipToPadding = ((ViewGroup) view).getClipToPadding();
        }
    }

    public MenuBuilder createMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(getActionBarThemedContext());
        menuBuilder.setCallback(this);
        return menuBuilder;
    }

    @Deprecated
    public void dismissImmersionMenu(boolean z2) {
        ImmersionMenuPopupWindow immersionMenuPopupWindow = this.mMenuPopupWindow;
        if (immersionMenuPopupWindow != null) {
            immersionMenuPopupWindow.dismiss(z2);
        }
    }

    public ActionBar getActionBar() {
        if (!hasActionBar()) {
            this.mActionBar = null;
        } else if (this.mActionBar == null) {
            this.mActionBar = createActionBar();
        }
        return this.mActionBar;
    }

    public final Context getActionBarThemedContext() {
        AppCompatActivity appCompatActivity = this.mActivity;
        ActionBar actionBar = getActionBar();
        return actionBar != null ? actionBar.getThemedContext() : appCompatActivity;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ActionMode getActionMode() {
        return this.mActionMode;
    }

    public AppCompatActivity getActivity() {
        return this.mActivity;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public int getBottomMenuMode() {
        return 2;
    }

    @Override // miuix.container.ExtraPaddingObserver
    public int getExtraHorizontalPadding() {
        return this.mExtraHorizontalPadding;
    }

    @Deprecated
    public int getExtraHorizontalPaddingLevel() {
        return 0;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    @Nullable
    public ExtraPaddingPolicy getExtraPaddingPolicy() {
        return this.mExtraPaddingPolicy;
    }

    public abstract LifecycleOwner getLifecycleOwner();

    public MenuInflater getMenuInflater() {
        if (this.mMenuInflater == null) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                this.mMenuInflater = new MenuInflater(actionBar.getThemedContext());
            } else {
                this.mMenuInflater = new MenuInflater(this.mActivity);
            }
        }
        return this.mMenuInflater;
    }

    public abstract Context getThemedContext();

    public int getTranslucentStatus() {
        return this.mTranslucentStatus;
    }

    public final String getUiOptionsFromMetadata() {
        try {
            Bundle bundle = this.mActivity.getPackageManager().getActivityInfo(this.mActivity.getComponentName(), 128).metaData;
            if (bundle != null) {
                return bundle.getString(METADATA_UI_OPTIONS);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "getUiOptionsFromMetadata: Activity '" + this.mActivity.getClass().getSimpleName() + "' not in manifest");
            return null;
        }
    }

    public abstract View getView();

    public boolean hasActionBar() {
        return this.mHasActionBar || this.mOverlayActionBar;
    }

    public void hideEndOverflowMenu() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.hideEndOverflowMenu();
        }
    }

    public void hideOverflowMenu() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.hideOverflowMenu();
        }
    }

    public void initExtraPaddingPolicy() {
        ExtraPaddingPolicy extraPaddingPolicyCreateDefault = ExtraPaddingPolicy.Builder.createDefault(this.mDeviceType, ContainerToken.PADDING_BASE_DP, ContainerToken.PADDING_HORIZONTAL_COMMON);
        this.mExtraPaddingPolicy = extraPaddingPolicyCreateDefault;
        if (extraPaddingPolicyCreateDefault != null) {
            extraPaddingPolicyCreateDefault.setEnable(this.mExtraPaddingEnable);
        }
    }

    public boolean isEndActionMenuEnabled() {
        return this.mEndActionMenuEnabled;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public boolean isExtraHorizontalPaddingEnable() {
        return this.mExtraPaddingEnable;
    }

    public boolean isExtraPaddingApplyToContentEnable() {
        return this.mExtraPaddingApplyToContentEnable;
    }

    public boolean isHyperActionMenuEnabled() {
        return this.mHyperActionMenuEnabled;
    }

    @Deprecated
    public boolean isImmersionMenuEnabled() {
        return this.mImmersionMenuEnabled;
    }

    @Deprecated
    public boolean isImmersionMenuShowing() {
        ImmersionMenuPopupWindow immersionMenuPopupWindow = this.mMenuPopupWindow;
        if (immersionMenuPopupWindow instanceof ImmersionMenuPopupWindowImpl) {
            return immersionMenuPopupWindow.isShowing();
        }
        return false;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onActionModeFinished(ActionMode actionMode) {
        this.mActionMode = null;
        updateOnBackPressedCallbackState(false);
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onActionModeStarted(ActionMode actionMode) {
        this.mActionMode = actionMode;
        updateOnBackPressedCallbackState(true);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        this.mActivity.closeOptionsMenu();
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onConfigurationChanged(Configuration configuration) {
        ActionBarImpl actionBarImpl;
        if (this.mHasActionBar && this.mSubDecorInstalled && (actionBarImpl = (ActionBarImpl) getActionBar()) != null) {
            actionBarImpl.onConfigurationChanged(configuration);
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onContentInsetChanged(Rect rect) {
        this.mContentInset = rect;
    }

    public void onCreate(Bundle bundle) {
    }

    public abstract boolean onCreateImmersionMenu(MenuBuilder menuBuilder);

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onDestroy() {
        ActionBarImpl actionBarImpl;
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        if (this.mHasActionBar && this.mSubDecorInstalled && (actionBarImpl = (ActionBarImpl) getActionBar()) != null) {
            actionBarImpl.onDestroy();
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onDispatchNestedScrollOffset(int[] iArr) {
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public abstract /* synthetic */ boolean onMenuItemSelected(int i2, MenuItem menuItem);

    @Override // miuix.appcompat.internal.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(MenuBuilder menuBuilder) {
        reopenMenu(menuBuilder, true);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
    public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
        return false;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onPostResume() {
        ActionBarImpl actionBarImpl;
        if (this.mHasActionBar && this.mSubDecorInstalled && (actionBarImpl = (ActionBarImpl) getActionBar()) != null) {
            actionBarImpl.setShowHideAnimationEnabled(true);
        }
    }

    public abstract boolean onPrepareImmersionMenu(MenuBuilder menuBuilder);

    @Override // miuix.appcompat.app.IContentInsetState
    public void onProcessBindViewWithContentInset(Rect rect) {
        if (this.mViewWithContentInset == null) {
            return;
        }
        ViewUtils.RelativePadding relativePadding = new ViewUtils.RelativePadding(this.mViewWithContentInsetInitPadding);
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this.mViewWithContentInset);
        relativePadding.start += zIsLayoutRtl ? rect.right : rect.left;
        relativePadding.top += rect.top;
        relativePadding.end += zIsLayoutRtl ? rect.left : rect.right;
        View view = this.mViewWithContentInset;
        if ((view instanceof ViewGroup) && (view instanceof ScrollingView)) {
            relativePadding.applyToScrollingView((ViewGroup) view);
        } else {
            relativePadding.applyToView(view);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onStop() {
        ActionBarImpl actionBarImpl;
        dismissImmersionMenu(false);
        if (this.mHasActionBar && this.mSubDecorInstalled && (actionBarImpl = (ActionBarImpl) getActionBar()) != null) {
            actionBarImpl.setShowHideAnimationEnabled(false);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void registerCoordinateScrollView(View view) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.registerCoordinateScrollView(view);
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        List<ExtraPaddingObserver> list = this.mExtraPaddingObserver;
        if (list != null) {
            list.remove(extraPaddingObserver);
        }
    }

    public void reopenMenu(MenuBuilder menuBuilder, boolean z2) {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null || !actionBarView.isOverflowReserved()) {
            menuBuilder.close();
            return;
        }
        if (this.mActionBarView.isOverflowMenuShowing() && z2) {
            this.mActionBarView.hideOverflowMenu();
        } else if (this.mActionBarView.getVisibility() == 0) {
            this.mActionBarView.showOverflowMenu();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public boolean requestWindowFeature(int i2) {
        if (i2 == 2) {
            this.mFeatureProgress = true;
            return true;
        }
        if (i2 == 5) {
            this.mFeatureIndeterminateProgress = true;
            return true;
        }
        if (i2 == 8) {
            this.mHasActionBar = true;
            return true;
        }
        if (i2 != 9) {
            return this.mActivity.requestWindowFeature(i2);
        }
        this.mOverlayActionBar = true;
        return true;
    }

    public void setEndActionMenuEnabled(boolean z2) {
        setEndActionMenuEnabled(z2, true);
    }

    @Override // miuix.container.ExtraPaddingObserver
    public boolean setExtraHorizontalPadding(int i2) {
        if (this.mExtraHorizontalPadding == i2) {
            return false;
        }
        this.mExtraHorizontalPadding = i2;
        return true;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        this.mExtraPaddingEnable = z2;
        ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
        if (extraPaddingPolicy != null) {
            extraPaddingPolicy.setEnable(z2);
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        this.mExtraPaddingInitEnable = z2;
    }

    @Deprecated
    public void setExtraHorizontalPaddingLevel(int i2) {
    }

    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        this.mExtraPaddingApplyToContentEnable = z2;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        if (extraPaddingPolicy != null) {
            this.mUserExtraPaddingPolicy = true;
            this.mExtraPaddingPolicy = extraPaddingPolicy;
        } else if (this.mUserExtraPaddingPolicy && this.mExtraPaddingPolicy != null) {
            this.mUserExtraPaddingPolicy = false;
            initExtraPaddingPolicy();
        }
        ExtraPaddingPolicy extraPaddingPolicy2 = this.mExtraPaddingPolicy;
        if (extraPaddingPolicy2 != null) {
            extraPaddingPolicy2.setEnable(this.mExtraPaddingEnable);
        }
    }

    public void setHyperActionMenuEnabled(boolean z2) {
        setEndActionMenuEnabled(true, z2, true);
    }

    @Deprecated
    public void setImmersionMenuEnabled(boolean z2) {
        this.mImmersionMenuEnabled = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setMenu(MenuBuilder menuBuilder) {
        if (menuBuilder == this.mMenu) {
            return;
        }
        this.mMenu = menuBuilder;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setMenu(menuBuilder, this);
            if (this.mActionBarView.isEndActionMenuEnable()) {
                onPanelViewAdded(0, null, this.mMenu, this.mActionBarView.getEndMenu());
            }
        }
    }

    public void setTranslucentStatus(int i2) {
        int integer = this.mActivity.getResources().getInteger(R.integer.window_translucent_status);
        if (integer >= 0 && integer <= 2) {
            i2 = integer;
        }
        if (this.mTranslucentStatus == i2 || !WindowWrapper.setTranslucentStatus(this.mActivity.getWindow(), i2)) {
            return;
        }
        this.mTranslucentStatus = i2;
    }

    public void showEndOverflowMenu() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.showEndOverflowMenu();
        }
    }

    @Deprecated
    public void showImmersionMenu() {
        View viewFindViewById;
        ImmersionMenuPopupWindow immersionMenuPopupWindow = this.mMenuPopupWindow;
        if (immersionMenuPopupWindow instanceof ImmersionMenuPopupWindowImpl) {
            View lastAnchor = ((ImmersionMenuPopupWindowImpl) immersionMenuPopupWindow).getLastAnchor();
            ViewGroup lastParent = ((ImmersionMenuPopupWindowImpl) this.mMenuPopupWindow).getLastParent();
            if (lastAnchor != null) {
                showImmersionMenu(lastAnchor, lastParent);
                return;
            }
        }
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null || (viewFindViewById = actionBarView.findViewById(R.id.more)) == null) {
            throw new IllegalStateException("Can't find anchor view in actionbar or any other anchor view used before. Do you use default actionbar and immersion menu is enabled?");
        }
        showImmersionMenu(viewFindViewById, this.mActionBarView);
    }

    public void showOverflowMenu() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.showOverflowMenu();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void unregisterCoordinateScrollView(View view) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.unregisterCoordinateScrollView(view);
        }
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        if (i2 == 0) {
            return onWindowStartingActionMode(callback);
        }
        return null;
    }

    public void setEndActionMenuEnabled(boolean z2, boolean z3) {
        setEndActionMenuEnabled(z2, false, z3);
    }

    public void setEndActionMenuEnabled(boolean z2, boolean z3, boolean z4) {
        this.mEndActionMenuEnabled = z2;
        this.mHyperActionMenuEnabled = z3;
        if (this.mSubDecorInstalled && this.mHasActionBar) {
            this.mActionBarView.setEndActionMenuEnable(z2);
            this.mActionBarView.setHyperActionMenuEnable(z3);
            if (z4) {
                invalidateOptionsMenu();
            } else {
                this.mActivity.getWindow().getDecorView().post(new Runnable() { // from class: miuix.appcompat.app.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6020a.invalidateOptionsMenu();
                    }
                });
            }
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Deprecated
    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        if (!this.mImmersionMenuEnabled) {
            Log.w(TAG, "Try to show immersion menu when immersion menu disabled");
            return;
        }
        if (view != null) {
            if (this.mImmersionMenu == null) {
                MenuBuilder menuBuilderCreateMenu = createMenu();
                this.mImmersionMenu = menuBuilderCreateMenu;
                onCreateImmersionMenu(menuBuilderCreateMenu);
            }
            if (onPrepareImmersionMenu(this.mImmersionMenu) && this.mImmersionMenu.hasVisibleItems()) {
                ImmersionMenuPopupWindow immersionMenuPopupWindow = this.mMenuPopupWindow;
                if (immersionMenuPopupWindow == null) {
                    ImmersionMenuPopupWindowImpl immersionMenuPopupWindowImpl = new ImmersionMenuPopupWindowImpl(this, this.mImmersionMenu, getView());
                    immersionMenuPopupWindowImpl.setDropDownGravity(81);
                    immersionMenuPopupWindowImpl.setHorizontalOffset(0);
                    immersionMenuPopupWindowImpl.setVerticalOffset(0);
                    this.mMenuPopupWindow = immersionMenuPopupWindowImpl;
                } else {
                    immersionMenuPopupWindow.update(this.mImmersionMenu);
                }
                if (this.mMenuPopupWindow.isShowing()) {
                    return;
                }
                this.mMenuPopupWindow.show(view, null);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("You must specify a valid anchor view");
    }
}
