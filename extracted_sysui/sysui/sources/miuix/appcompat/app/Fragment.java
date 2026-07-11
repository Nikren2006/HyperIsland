package miuix.appcompat.app;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentFactory;
import java.util.List;
import miuix.appcompat.R;
import miuix.appcompat.internal.util.LayoutUIUtils;
import miuix.autodensity.AutoDensityConfig;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.WindowBaseInfo;
import miuix.responsive.interfaces.IResponsive;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;

/* JADX INFO: loaded from: classes2.dex */
public class Fragment extends androidx.fragment.app.Fragment implements IFragment, IContentInsetState, IResponsive<Fragment>, ShortcutsCallback {
    protected FragmentDelegate mDelegate;
    private int mInputViewLimitTextSizeDp;
    private boolean mHasMenu = true;
    private boolean mMenuVisible = true;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        if (isAdded()) {
            LayoutUIUtils.resetSearchModeStubInputTextSize(getResources(), view, this.mInputViewLimitTextSizeDp);
        }
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean acceptExtraPaddingFromParent() {
        return this.mDelegate.acceptExtraPaddingFromParent();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        this.mDelegate.addExtraPaddingObserver(extraPaddingObserver);
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig) {
        addGroupButtons(groupButtonsConfig, true);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void bindViewWithContentInset(View view) {
        this.mDelegate.bindViewWithContentInset(view);
    }

    @Override // miuix.appcompat.app.IFragment
    public void checkThemeLegality() {
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void dismissImmersionMenu(boolean z2) {
        this.mDelegate.dismissImmersionMenu(z2);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void dispatchResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        this.mDelegate.dispatchResponsiveLayout(configuration, screenSpec, z2);
    }

    @Override // miuix.appcompat.app.IFragment
    @Nullable
    public ActionBar getActionBar() {
        return this.mDelegate.getActionBar();
    }

    public AppCompatActivity getAppCompatActivity() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate == null) {
            return null;
        }
        return fragmentDelegate.getActivity();
    }

    public int getBottomMenuCustomViewTranslationY() {
        return this.mDelegate.getBottomMenuCustomViewTranslationY();
    }

    public int getBottomMenuMode() {
        return this.mDelegate.getBottomMenuMode();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public Rect getContentInset() {
        return this.mDelegate.getContentInset();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public FragmentDelegate getDelegate() {
        return this.mDelegate;
    }

    @Override // miuix.container.ExtraPaddingObserver
    public int getExtraHorizontalPadding() {
        return this.mDelegate.getExtraHorizontalPadding();
    }

    @Deprecated
    public int getExtraHorizontalPaddingLevel() {
        return this.mDelegate.getExtraHorizontalPaddingLevel();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    @Nullable
    public ExtraPaddingPolicy getExtraPaddingPolicy() {
        return this.mDelegate.getExtraPaddingPolicy();
    }

    @Nullable
    public View getInflatedView() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate == null) {
            return null;
        }
        return fragmentDelegate.getInflatedView();
    }

    public MenuInflater getMenuInflater() {
        return this.mDelegate.getMenuInflater();
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public ResponsiveState getResponsiveState() {
        return this.mDelegate.getResponsiveState();
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public Fragment getResponsiveSubject() {
        return this;
    }

    @Override // miuix.appcompat.app.IFragment
    public Context getThemedContext() {
        return this.mDelegate.getThemedContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View getView() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate == null) {
            return null;
        }
        return fragmentDelegate.getView();
    }

    @Nullable
    public WindowBaseInfo getWindowInfo() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return EnvStateManager.getWindowInfo(activity);
        }
        return null;
    }

    public int getWindowType() {
        WindowBaseInfo windowInfo = getWindowInfo();
        if (windowInfo != null) {
            return windowInfo.windowType;
        }
        return 1;
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean hasActionBar() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate == null) {
            return false;
        }
        return fragmentDelegate.hasActionBar();
    }

    public void hideBottomMenu() {
        hideBottomMenu(true);
    }

    public void hideBottomMenuCustomView() {
        this.mDelegate.hideBottomMenuCustomView();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void hideEndOverflowMenu() {
        this.mDelegate.hideEndOverflowMenu();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void hideOverflowMenu() {
        this.mDelegate.hideOverflowMenu();
    }

    public void invalidateOptionsMenu() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.updateOptionsMenu(1);
            if (!isHidden() && this.mHasMenu && this.mMenuVisible && isAdded()) {
                this.mDelegate.invalidateOptionsMenu();
            }
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public boolean isExtraHorizontalPaddingEnable() {
        return this.mDelegate.isExtraHorizontalPaddingEnable();
    }

    public boolean isExtraPaddingApplyToContentEnable() {
        return this.mDelegate.isExtraPaddingApplyToContentEnable();
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean isInEditActionMode() {
        return this.mDelegate.isInEditActionMode();
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean isIsInSearchActionMode() {
        return this.mDelegate.isIsInSearchActionMode();
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean isRegisterResponsive() {
        return this.mDelegate.isRegisterResponsive();
    }

    public boolean isResponsiveEnabled() {
        return false;
    }

    @Override // miuix.appcompat.app.IFragment
    @CallSuper
    public void onActionModeFinished(ActionMode actionMode) {
        this.mDelegate.onActionModeFinished(actionMode);
    }

    @Override // miuix.appcompat.app.IFragment
    @CallSuper
    public void onActionModeStarted(ActionMode actionMode) {
        this.mDelegate.onActionModeStarted(actionMode);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        AutoDensityConfig.updateDensity(context);
        FragmentFactory fragmentFactory = getParentFragmentManager().getFragmentFactory();
        if (fragmentFactory instanceof DelegateFragmentFactory) {
            this.mDelegate = ((DelegateFragmentFactory) fragmentFactory).createFragmentDelegate(this);
        } else {
            this.mDelegate = new FragmentDelegate(this);
        }
        this.mDelegate.setResponsiveEnabled(isResponsiveEnabled());
        this.mInputViewLimitTextSizeDp = MiuixUIUtils.isTallFontLang(getContext()) ? 16 : 27;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        AutoDensityConfig.updateDensityByConfig(getContext(), configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    @CallSuper
    public void onContentInsetChanged(Rect rect) {
        this.mDelegate.onContentInsetChanged(rect);
        onProcessBindViewWithContentInset(rect);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDelegate.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public Animator onCreateAnimator(int i2, boolean z2, int i3) {
        return this.mDelegate.onCreateAnimator(i2, z2, i3);
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // miuix.appcompat.app.IFragment
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 == 0 && this.mHasMenu && this.mMenuVisible && !isHidden() && isAdded()) {
            return onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.mDelegate.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mDelegate.dismissImmersionMenu(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mDelegate.onDestroyView();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onDispatchNestedScrollOffset(int[] iArr) {
        this.mDelegate.onDispatchNestedScrollOffset(iArr);
    }

    @Override // miuix.container.ExtraPaddingObserver
    public void onExtraPaddingChanged(int i2) {
        this.mDelegate.onExtraPaddingChanged(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onGenericMotionEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onHiddenChanged(boolean z2) {
        FragmentDelegate fragmentDelegate;
        super.onHiddenChanged(z2);
        if (!z2 && (fragmentDelegate = this.mDelegate) != null) {
            fragmentDelegate.invalidateOptionsMenu();
        }
        onVisibilityChanged(!z2);
    }

    @Override // miuix.appcompat.app.IFragment
    public View onInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyDown(i2, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyEvent(KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyEvent(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyLongPress(i2, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyMultiple(int i2, int i3, KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyMultiple(i2, i3, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyShortcutEvent(KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyShortcutEvent(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onKeyUp(i2, keyEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332 || getActionBar() == null || (getActionBar().getDisplayOptions() & 4) == 0) {
            return false;
        }
        FragmentActivity activity = getActivity();
        if (activity.getParent() == null ? activity.onNavigateUp() : activity.getParent().onNavigateUpFromChild(activity)) {
            return true;
        }
        getActivity().getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

    @Override // miuix.appcompat.app.IFragment
    public void onOptionsMenuViewAdded(@Nullable Menu menu, @Nullable Menu menu2) {
    }

    @Override // miuix.appcompat.app.IFragment
    public void onPanelClosed(int i2, Menu menu) {
    }

    @Override // miuix.appcompat.app.IFragment
    public void onPreparePanel(int i2, View view, Menu menu) {
        if (i2 == 0 && this.mHasMenu && this.mMenuVisible && !isHidden() && isAdded()) {
            onPrepareOptionsMenu(menu);
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onProcessBindViewWithContentInset(Rect rect) {
        this.mDelegate.onProcessBindViewWithContentInset(rect);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback)) {
                ((ShortcutsCallback) fragment).onProvideKeyboardShortcuts(list, menu, i2);
            }
        }
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void onResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mDelegate.onPostResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mDelegate.onStop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onTouchEvent(MotionEvent motionEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onTouchEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ShortcutsCallback
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        for (androidx.fragment.app.Fragment fragment : getChildFragmentManager().getFragments()) {
            if (fragment.isAdded() && !fragment.isHidden() && fragment.isResumed() && (fragment instanceof ShortcutsCallback) && ((ShortcutsCallback) fragment).onTrackballEvent(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    public void onUpdateArguments(Bundle bundle) {
        if (isStateSaved()) {
            return;
        }
        setArguments(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Deprecated
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        final View viewFindViewById;
        this.mDelegate.onViewCreated(view, bundle);
        Rect contentInset = this.mDelegate.getContentInset();
        if (contentInset != null && (contentInset.top != 0 || contentInset.bottom != 0 || contentInset.left != 0 || contentInset.right != 0)) {
            onContentInsetChanged(contentInset);
        }
        if (view == null || !isAdded() || (viewFindViewById = view.findViewById(R.id.search_mode_stub)) == null) {
            return;
        }
        viewFindViewById.post(new Runnable() { // from class: miuix.appcompat.app.q
            @Override // java.lang.Runnable
            public final void run() {
                this.f6053a.lambda$onViewCreated$0(viewFindViewById);
            }
        });
    }

    @Override // miuix.appcompat.app.IFragment
    public void onViewInflated(@NonNull View view, @Nullable Bundle bundle) {
    }

    public void onVisibilityChanged(boolean z2) {
    }

    public void registerCoordinateScrollView(View view) {
        this.mDelegate.registerCoordinateScrollView(view);
    }

    public void removeBottomMenuCustomView() {
        this.mDelegate.removeBottomMenuCustomView();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        this.mDelegate.removeExtraPaddingObserver(extraPaddingObserver);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public boolean requestDispatchContentInset() {
        return this.mDelegate.requestDispatchContentInset();
    }

    public boolean requestWindowFeature(int i2) {
        return this.mDelegate.requestWindowFeature(i2);
    }

    public void setBottomExtraInset(int i2) {
        this.mDelegate.setBottomExtraInset(i2);
        int size = getChildFragmentManager().getFragments().size();
        for (int i3 = 0; i3 < size; i3++) {
            androidx.fragment.app.Fragment fragment = getChildFragmentManager().getFragments().get(i3);
            if ((fragment instanceof Fragment) && fragment.isAdded()) {
                ((Fragment) fragment).setBottomExtraInset(i2);
            }
        }
    }

    public void setBottomMenuCustomView(View view) {
        this.mDelegate.setBottomMenuCustomView(view);
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        this.mDelegate.setBottomMenuCustomViewTranslationYWithPx(i2);
    }

    public void setBottomMenuMode(int i2) {
        this.mDelegate.setBottomMenuMode(i2);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public final void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        this.mDelegate.setCorrectNestedScrollMotionEventEnabled(z2);
    }

    public void setEndActionMenuEnabled(boolean z2) {
        this.mDelegate.setEndActionMenuEnabled(z2);
    }

    @Override // miuix.container.ExtraPaddingObserver
    public boolean setExtraHorizontalPadding(int i2) {
        return this.mDelegate.setExtraHorizontalPadding(i2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        this.mDelegate.setExtraHorizontalPaddingEnable(z2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        this.mDelegate.setExtraHorizontalPaddingInitEnable(z2);
    }

    @Deprecated
    public void setExtraHorizontalPaddingLevel(int i2) {
        this.mDelegate.setExtraHorizontalPaddingLevel(i2);
    }

    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        this.mDelegate.setExtraPaddingApplyToContentEnable(z2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        this.mDelegate.setExtraPaddingPolicy(extraPaddingPolicy);
    }

    public void setGroupButtonsPanelBackground(Drawable drawable) {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.setGroupButtonsPanelBackground(drawable);
        }
    }

    public void setGroupButtonsPanelBackgroundColor(int i2) {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.setGroupButtonsPanelBackgroundColor(i2);
        }
    }

    public void setGroupButtonsPanelBackgroundResource(int i2) {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.setGroupButtonsPanelBackgroundResource(i2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void setHasOptionsMenu(boolean z2) {
        super.setHasOptionsMenu(z2);
        if (this.mHasMenu != z2) {
            this.mHasMenu = z2;
            if (!z2 || this.mDelegate == null || isHidden() || !isAdded()) {
                return;
            }
            this.mDelegate.invalidateOptionsMenu();
        }
    }

    public void setHyperActionMenuEnabled(boolean z2) {
        this.mDelegate.setHyperActionMenuEnabled(z2);
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void setImmersionMenuEnabled(boolean z2) {
        this.mDelegate.setImmersionMenuEnabled(z2);
    }

    @Override // androidx.fragment.app.Fragment
    public void setMenuVisibility(boolean z2) {
        FragmentDelegate fragmentDelegate;
        super.setMenuVisibility(z2);
        if (this.mMenuVisible != z2) {
            this.mMenuVisible = z2;
            if (isHidden() || !isAdded() || (fragmentDelegate = this.mDelegate) == null) {
                return;
            }
            fragmentDelegate.invalidateOptionsMenu();
        }
    }

    @Override // miuix.appcompat.app.IFragment
    public void setNestedScrollingParentEnabled(boolean z2) {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.setNestedScrollingParentEnabled(z2);
        }
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.mDelegate.setOnStatusBarChangeListener(onStatusBarChangeListener);
    }

    @Override // miuix.appcompat.app.IFragment
    public void setThemeRes(int i2) {
        this.mDelegate.setExtraThemeRes(i2);
    }

    public void showBottomMenu() {
        showBottomMenu(true);
    }

    public void showBottomMenuCustomView() {
        this.mDelegate.showBottomMenuCustomView();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showEndOverflowMenu() {
        this.mDelegate.showEndOverflowMenu();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void showImmersionMenu() {
        this.mDelegate.showImmersionMenu();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showOverflowMenu() {
        this.mDelegate.showOverflowMenu();
    }

    @Override // miuix.appcompat.app.IFragment
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return this.mDelegate.startActionMode(callback);
    }

    public void unregisterCoordinateScrollView(View view) {
        this.mDelegate.unregisterCoordinateScrollView(view);
    }

    public void updateOptionsMenuContent() {
        if (this.mDelegate != null && !isHidden() && this.mHasMenu && this.mMenuVisible && isAdded()) {
            this.mDelegate.invalidateOptionsMenu();
        }
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig, boolean z2) {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate != null) {
            fragmentDelegate.addGroupButtons(groupButtonsConfig, z2);
        }
    }

    public void hideBottomMenu(boolean z2) {
        this.mDelegate.hideBottomMenu(z2);
    }

    public void showBottomMenu(boolean z2) {
        this.mDelegate.showBottomMenu(z2);
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.mDelegate.showImmersionMenu(view, viewGroup);
    }
}
