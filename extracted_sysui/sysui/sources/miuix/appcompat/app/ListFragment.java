package miuix.appcompat.app;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentFactory;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.responsive.interfaces.IResponsive;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;

/* JADX INFO: loaded from: classes2.dex */
public class ListFragment extends androidx.fragment.app.ListFragment implements IFragment, IResponsive<androidx.fragment.app.Fragment> {
    private FragmentDelegate mDelegate;
    private boolean mHasMenu = true;
    private boolean mMenuVisible = true;

    @Override // miuix.appcompat.app.IFragment
    public boolean acceptExtraPaddingFromParent() {
        return this.mDelegate.acceptExtraPaddingFromParent();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        this.mDelegate.addExtraPaddingObserver(extraPaddingObserver);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void bindViewWithContentInset(View view) {
        this.mDelegate.bindViewWithContentInset(view);
    }

    @Override // miuix.appcompat.app.IFragment
    public void checkThemeLegality() {
    }

    @Override // miuix.appcompat.app.IImmersionMenu
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

    @Override // miuix.appcompat.app.IContentInsetState
    public Rect getContentInset() {
        return this.mDelegate.getContentInset();
    }

    @Override // miuix.container.ExtraPaddingObserver
    public int getExtraHorizontalPadding() {
        return this.mDelegate.getExtraHorizontalPadding();
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // miuix.responsive.interfaces.IResponsive
    public androidx.fragment.app.Fragment getResponsiveSubject() {
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

    @Override // miuix.appcompat.app.IFragment
    public boolean hasActionBar() {
        FragmentDelegate fragmentDelegate = this.mDelegate;
        if (fragmentDelegate == null) {
            return false;
        }
        return fragmentDelegate.hasActionBar();
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
    public final void onActionModeFinished(ActionMode actionMode) {
        this.mDelegate.onActionModeFinished(actionMode);
    }

    @Override // miuix.appcompat.app.IFragment
    public final void onActionModeStarted(ActionMode actionMode) {
        this.mDelegate.onActionModeStarted(actionMode);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FragmentFactory fragmentFactory = getParentFragmentManager().getFragmentFactory();
        if (fragmentFactory instanceof DelegateFragmentFactory) {
            this.mDelegate = ((DelegateFragmentFactory) fragmentFactory).createFragmentDelegate(this);
        } else {
            this.mDelegate = new FragmentDelegate(this);
        }
        this.mDelegate.setResponsiveEnabled(isResponsiveEnabled());
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    @Override // miuix.appcompat.app.IContentInsetState
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

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z2;
        View viewOnCreateView = this.mDelegate.onCreateView(layoutInflater, viewGroup, bundle);
        if (viewOnCreateView instanceof ActionBarOverlayLayout) {
            boolean zEquals = "splitActionBarWhenNarrow".equals(this.mDelegate.getUiOptionsFromMetadata());
            if (zEquals) {
                z2 = getActivity().getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow);
            } else {
                TypedArray typedArrayObtainStyledAttributes = getActivity().obtainStyledAttributes(R.styleable.Window);
                boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowSplitActionBar, false);
                typedArrayObtainStyledAttributes.recycle();
                z2 = z3;
            }
            this.mDelegate.addSplitActionBar(z2, zEquals, (ActionBarOverlayLayout) viewOnCreateView);
        }
        return viewOnCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mDelegate.dismissImmersionMenu(false);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onDispatchNestedScrollOffset(int[] iArr) {
        this.mDelegate.onDispatchNestedScrollOffset(iArr);
    }

    @Override // miuix.container.ExtraPaddingObserver
    public void onExtraPaddingChanged(int i2) {
        this.mDelegate.onExtraPaddingChanged(i2);
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
        return super.onCreateView(layoutInflater, viewGroup, bundle);
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

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    @Deprecated
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        this.mDelegate.onViewCreated(view, bundle);
    }

    @Override // miuix.appcompat.app.IFragment
    public void onViewInflated(View view, Bundle bundle) {
    }

    public void onVisibilityChanged(boolean z2) {
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

    @Override // miuix.appcompat.app.IContentInsetState
    public void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        this.mDelegate.setCorrectNestedScrollMotionEventEnabled(z2);
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

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        this.mDelegate.setExtraPaddingPolicy(extraPaddingPolicy);
    }

    @Override // androidx.fragment.app.Fragment
    public void setHasOptionsMenu(boolean z2) {
        FragmentDelegate fragmentDelegate;
        super.setHasOptionsMenu(z2);
        if (this.mHasMenu != z2) {
            this.mHasMenu = z2;
            if (isHidden() || !isAdded() || (fragmentDelegate = this.mDelegate) == null) {
                return;
            }
            fragmentDelegate.invalidateOptionsMenu();
        }
    }

    @Override // miuix.appcompat.app.IImmersionMenu
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

    @Override // miuix.appcompat.app.IFragment
    public void setThemeRes(int i2) {
        this.mDelegate.setExtraThemeRes(i2);
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showEndOverflowMenu() {
        this.mDelegate.showEndOverflowMenu();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
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

    public void updateOptionsMenuContent() {
        if (this.mDelegate != null && !isHidden() && this.mHasMenu && this.mMenuVisible && isAdded()) {
            this.mDelegate.invalidateOptionsMenu();
        }
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.mDelegate.showImmersionMenu(view, viewGroup);
    }
}
