package miuix.appcompat.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import miuix.appcompat.R;
import miuix.appcompat.app.floatingactivity.IActivityIdentity;
import miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation;
import miuix.appcompat.app.floatingactivity.OnFloatingActivityCallback;
import miuix.appcompat.app.floatingactivity.OnFloatingCallback;
import miuix.appcompat.app.floatingactivity.OnFloatingModeCallback;
import miuix.appcompat.internal.util.LayoutUIUtils;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.container.ExtraPaddingProcessor;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.WindowBaseInfo;
import miuix.responsive.interfaces.IResponsive;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"MissingSuperCall"})
public class AppCompatActivity extends FragmentActivity implements IActivity, IActivitySwitcherAnimation, IActivityIdentity, IResponsive<Activity>, ExtraPaddingProcessor {
    private AppDelegate mAppDelegate;
    private int mInputViewLimitTextSizeDp;
    private WindowBaseInfo mWindowInfo;

    public class Callback implements ActivityCallback {
        private Callback() {
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onConfigurationChanged(Configuration configuration) {
            AppCompatActivity.super.onConfigurationChanged(configuration);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onCreate(@Nullable Bundle bundle) {
            AppCompatActivity.super.onCreate(bundle);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public boolean onCreatePanelMenu(int i2, Menu menu) {
            return AppCompatActivity.super.onCreatePanelMenu(i2, menu);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public View onCreatePanelView(int i2) {
            return AppCompatActivity.super.onCreatePanelView(i2);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public boolean onMenuItemSelected(int i2, @NonNull MenuItem menuItem) {
            return AppCompatActivity.super.onMenuItemSelected(i2, menuItem);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onPanelClosed(int i2, Menu menu) {
            AppCompatActivity.super.onPanelClosed(i2, menu);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onPanelViewAdded(int i2, View view, Menu menu, Menu menu2) {
            AppCompatActivity.this.onOptionsMenuViewAdded(menu, menu2);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onPostResume() {
            AppCompatActivity.super.onPostResume();
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            return AppCompatActivity.super.onPreparePanel(i2, view, menu);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onRestoreInstanceState(Bundle bundle) {
            AppCompatActivity.super.onRestoreInstanceState(bundle);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onSaveInstanceState(Bundle bundle) {
            AppCompatActivity.super.onSaveInstanceState(bundle);
        }

        @Override // miuix.appcompat.app.ActivityCallback
        public void onStop() {
            AppCompatActivity.super.onStop();
        }
    }

    public class FloatingModeCallback implements OnFloatingModeCallback {
        private FloatingModeCallback() {
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingModeCallback
        public void onFloatingWindowModeChanged(boolean z2) {
            AppCompatActivity.this.onFloatingWindowModeChanged(z2);
        }

        @Override // miuix.appcompat.app.floatingactivity.OnFloatingModeCallback
        public boolean onFloatingWindowModeChanging(boolean z2) {
            return AppCompatActivity.this.onFloatingWindowModeChanging(z2);
        }
    }

    public AppCompatActivity() {
        this.mAppDelegate = new AppDelegate(this, new Callback(), new FloatingModeCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0() {
        LayoutUIUtils.resetSearchModeStubInputTextSize(getResources(), findViewById(R.id.search_mode_stub), this.mInputViewLimitTextSizeDp);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mAppDelegate.addContentView(view, layoutParams);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        this.mAppDelegate.addExtraPaddingObserver(extraPaddingObserver);
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig) {
        addGroupButtons(groupButtonsConfig, true);
    }

    public void afterConfigurationChanged(Configuration configuration) {
        this.mAppDelegate.afterConfigurationChanged(configuration);
    }

    public void beforeConfigurationChanged(Configuration configuration) {
        this.mAppDelegate.beforeConfigurationChanged(configuration);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void bindViewWithContentInset(View view) {
        this.mAppDelegate.bindViewWithContentInset(view);
    }

    @Override // miuix.appcompat.app.IActivity
    public void checkThemeLegality() {
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void dismissImmersionMenu(boolean z2) {
        this.mAppDelegate.dismissImmersionMenu(z2);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void dispatchResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        this.mAppDelegate.dispatchResponsiveLayout(configuration, screenSpec, z2);
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeCloseEnterAnimation() {
        this.mAppDelegate.executeCloseEnterAnimation();
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeCloseExitAnimation() {
        this.mAppDelegate.executeCloseExitAnimation();
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeOpenEnterAnimation() {
        this.mAppDelegate.executeOpenEnterAnimation();
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivitySwitcherAnimation
    public void executeOpenExitAnimation() {
        this.mAppDelegate.executeOpenExitAnimation();
    }

    public void exitFloatingActivityAll() {
        this.mAppDelegate.exitFloatingActivityAll();
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.mAppDelegate.shouldDelegateActivityFinish()) {
            return;
        }
        realFinish();
    }

    @Override // miuix.appcompat.app.floatingactivity.IActivityIdentity
    public String getActivityIdentity() {
        return this.mAppDelegate.getActivityIdentity();
    }

    @Nullable
    public ActionBar getAppCompatActionBar() {
        return this.mAppDelegate.getActionBar();
    }

    public int getBottomMenuCustomViewTranslationY() {
        return this.mAppDelegate.getBottomMenuCustomViewTranslationY();
    }

    public int getBottomMenuMode() {
        return this.mAppDelegate.getBottomMenuMode();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public Rect getContentInset() {
        return this.mAppDelegate.getContentInset();
    }

    @Override // miuix.container.ExtraPaddingObserver
    public int getExtraHorizontalPadding() {
        return this.mAppDelegate.getExtraHorizontalPadding();
    }

    @Deprecated
    public int getExtraHorizontalPaddingLevel() {
        return this.mAppDelegate.getExtraHorizontalPaddingLevel();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    @Nullable
    public ExtraPaddingPolicy getExtraPaddingPolicy() {
        return this.mAppDelegate.getExtraPaddingPolicy();
    }

    public View getFloatingBrightPanel() {
        return this.mAppDelegate.getFloatingBrightPanel();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return this.mAppDelegate.getMenuInflater();
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public ResponsiveState getResponsiveState() {
        return this.mAppDelegate.getResponsiveState();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // miuix.responsive.interfaces.IResponsive
    public Activity getResponsiveSubject() {
        return this;
    }

    @Override // miuix.appcompat.app.IActivity
    public int getTranslucentStatus() {
        return this.mAppDelegate.getTranslucentStatus();
    }

    public WindowBaseInfo getWindowInfo() {
        return this.mWindowInfo;
    }

    public int getWindowType() {
        WindowBaseInfo windowBaseInfo = this.mWindowInfo;
        if (windowBaseInfo != null) {
            return windowBaseInfo.windowType;
        }
        return 1;
    }

    public void hideBottomMenu() {
        hideBottomMenu(true);
    }

    public void hideBottomMenuCustomView() {
        this.mAppDelegate.hideBottomMenuCustomView();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void hideEndOverflowMenu() {
        this.mAppDelegate.hideEndOverflowMenu();
    }

    public void hideFloatingBrightPanel() {
        this.mAppDelegate.hideFloatingBrightPanel();
    }

    public void hideFloatingDimBackground() {
        this.mAppDelegate.hideFloatingDimBackground();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void hideOverflowMenu() {
        this.mAppDelegate.hideOverflowMenu();
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        this.mAppDelegate.invalidateOptionsMenu();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public boolean isExtraHorizontalPaddingEnable() {
        return this.mAppDelegate.isExtraHorizontalPaddingEnable();
    }

    public boolean isExtraPaddingApplyToContentEnable() {
        return this.mAppDelegate.isExtraPaddingApplyToContentEnable();
    }

    @Override // android.app.Activity
    public boolean isFinishing() {
        return this.mAppDelegate.isDelegateFinishing() || super.isFinishing();
    }

    @Override // miuix.appcompat.app.IActivity
    public boolean isFloatingWindowTheme() {
        return this.mAppDelegate.isFloatingTheme();
    }

    @Override // miuix.appcompat.app.IActivity
    public boolean isInFloatingWindowMode() {
        return this.mAppDelegate.isInFloatingWindowMode();
    }

    public boolean isRegisterResponsive() {
        return this.mAppDelegate.isRegisterResponsive();
    }

    public boolean isResponsiveEnabled() {
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
        this.mAppDelegate.onActionModeFinished(actionMode);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
        this.mAppDelegate.onActionModeStarted(actionMode);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        beforeConfigurationChanged(getResources().getConfiguration());
        if (!this.mWindowInfo.isDirty()) {
            EnvStateManager.markWindowInfoDirty(this.mWindowInfo);
        }
        this.mAppDelegate.onConfigurationChanged(configuration);
        afterConfigurationChanged(configuration);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onContentInsetChanged(Rect rect) {
        this.mAppDelegate.onContentInsetChanged(rect);
        onProcessBindViewWithContentInset(rect);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        EnvStateManager.markWindowInfoDirty(this);
        this.mAppDelegate.setResponsiveEnabled(isResponsiveEnabled());
        this.mAppDelegate.onCreate(bundle);
        this.mWindowInfo = EnvStateManager.getWindowInfo(this, null, true);
        this.mInputViewLimitTextSizeDp = MiuixUIUtils.isTallFontLang(this) ? 16 : 27;
        getWindow().getDecorView().post(new Runnable() { // from class: miuix.appcompat.app.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f6046a.lambda$onCreate$0();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return this.mAppDelegate.onCreatePanelMenu(i2, menu);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    @Nullable
    public View onCreatePanelView(int i2) {
        return this.mAppDelegate.onCreatePanelView(i2);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.mAppDelegate.onDestroy();
        EnvStateManager.removeInfoOfContext(this);
        this.mWindowInfo = null;
        super.onDestroy();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onDispatchNestedScrollOffset(int[] iArr) {
    }

    @Override // miuix.container.ExtraPaddingObserver
    public void onExtraPaddingChanged(int i2) {
        this.mAppDelegate.onExtraPaddingChanged(i2);
    }

    public void onFloatingWindowModeChanged(boolean z2) {
    }

    public boolean onFloatingWindowModeChanging(boolean z2) {
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (ShortcutsCallback.dispatchKeyDown(getSupportFragmentManager(), i2, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        if (ShortcutsCallback.dispatchKeyLongPress(getSupportFragmentManager(), i2, keyEvent)) {
            return true;
        }
        return super.onKeyLongPress(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i2, int i3, KeyEvent keyEvent) {
        if (ShortcutsCallback.dispatchKeyMultiple(getSupportFragmentManager(), i2, i3, keyEvent)) {
            return true;
        }
        return super.onKeyMultiple(i2, i3, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (ShortcutsCallback.dispatchKeyUp(getSupportFragmentManager(), i2, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, @NonNull MenuItem menuItem) {
        return this.mAppDelegate.onMenuItemSelected(i2, menuItem);
    }

    public void onOptionsMenuViewAdded(Menu menu, Menu menu2) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, @NonNull Menu menu) {
        this.mAppDelegate.onPanelClosed(i2, menu);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        this.mAppDelegate.onPostResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i2, View view, Menu menu) {
        return this.mAppDelegate.onPreparePanel(i2, view, menu);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public void onProcessBindViewWithContentInset(Rect rect) {
        this.mAppDelegate.onProcessBindViewWithContentInset(rect);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void onResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        this.mAppDelegate.onRestoreInstanceState(bundle);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.mAppDelegate.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.mAppDelegate.onStop();
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        this.mAppDelegate.setTitle(charSequence);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        return this.mAppDelegate.onWindowStartingActionMode(callback, i2);
    }

    public void realFinish() {
        super.finish();
    }

    public void registerCoordinateScrollView(View view) {
        this.mAppDelegate.registerCoordinateScrollView(view);
    }

    public void removeBottomMenuCustomView() {
        this.mAppDelegate.removeBottomMenuCustomView();
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        this.mAppDelegate.removeExtraPaddingObserver(extraPaddingObserver);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public boolean requestDispatchContentInset() {
        return this.mAppDelegate.requestDispatchContentInset();
    }

    public boolean requestExtraWindowFeature(int i2) {
        return this.mAppDelegate.requestWindowFeature(i2);
    }

    public void setBottomExtraInset(int i2) {
        this.mAppDelegate.setBottomExtraInset(i2);
    }

    public void setBottomMenuCustomView(View view) {
        this.mAppDelegate.setBottomMenuCustomView(view);
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        this.mAppDelegate.setBottomMenuCustomViewTranslationYWithPx(i2);
    }

    public void setBottomMenuMode(int i2) {
        this.mAppDelegate.setBottomMenuMode(i2);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i2) {
        this.mAppDelegate.setContentView(i2);
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public final void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        this.mAppDelegate.setCorrectNestedScrollMotionEventEnabled(z2);
    }

    public void setEnableSwipToDismiss(boolean z2) {
        this.mAppDelegate.setEnableSwipToDismiss(z2);
    }

    public void setEndActionMenuEnabled(boolean z2) {
        this.mAppDelegate.setEndActionMenuEnabled(z2);
    }

    @Override // miuix.container.ExtraPaddingObserver
    public boolean setExtraHorizontalPadding(int i2) {
        return this.mAppDelegate.setExtraHorizontalPadding(i2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        this.mAppDelegate.setExtraHorizontalPaddingEnable(z2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        this.mAppDelegate.setExtraHorizontalPaddingInitEnable(z2);
    }

    @Deprecated
    public void setExtraHorizontalPaddingLevel(int i2) {
        this.mAppDelegate.setExtraHorizontalPaddingLevel(i2);
    }

    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        this.mAppDelegate.setExtraPaddingApplyToContentEnable(z2);
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        this.mAppDelegate.setExtraPaddingPolicy(extraPaddingPolicy);
    }

    @Override // miuix.appcompat.app.IActivity
    public void setFloatingWindowBorderEnable(boolean z2) {
        this.mAppDelegate.setFloatingWindowBorderEnable(z2);
    }

    @Override // miuix.appcompat.app.IActivity
    public void setFloatingWindowMode(boolean z2) {
        this.mAppDelegate.setFloatingWindowMode(z2);
    }

    public void setGroupButtonsPanelBackground(Drawable drawable) {
        AppDelegate appDelegate = this.mAppDelegate;
        if (appDelegate != null) {
            appDelegate.setGroupButtonsPanelBackground(drawable);
        }
    }

    public void setGroupButtonsPanelBackgroundColor(int i2) {
        AppDelegate appDelegate = this.mAppDelegate;
        if (appDelegate != null) {
            appDelegate.setGroupButtonsPanelBackgroundColor(i2);
        }
    }

    public void setGroupButtonsPanelBackgroundResource(int i2) {
        AppDelegate appDelegate = this.mAppDelegate;
        if (appDelegate != null) {
            appDelegate.setGroupButtonsPanelBackgroundResource(i2);
        }
    }

    public void setHyperActionMenuEnabled(boolean z2) {
        this.mAppDelegate.setHyperActionMenuEnabled(z2);
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void setImmersionMenuEnabled(boolean z2) {
        this.mAppDelegate.setImmersionMenuEnabled(z2);
    }

    public void setOnFloatingCallback(OnFloatingCallback onFloatingCallback) {
        this.mAppDelegate.setOnFloatingCallback(onFloatingCallback);
    }

    public void setOnFloatingWindowCallback(OnFloatingActivityCallback onFloatingActivityCallback) {
        this.mAppDelegate.setOnFloatingWindowCallback(onFloatingActivityCallback);
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.mAppDelegate.setOnStatusBarChangeListener(onStatusBarChangeListener);
    }

    @Override // miuix.appcompat.app.IActivity
    public void setTranslucentStatus(int i2) {
        this.mAppDelegate.setTranslucentStatus(i2);
    }

    public void showBottomMenu() {
        showBottomMenu(true);
    }

    public void showBottomMenuCustomView() {
        this.mAppDelegate.showBottomMenuCustomView();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showEndOverflowMenu() {
        this.mAppDelegate.showEndOverflowMenu();
    }

    public void showFloatingBrightPanel() {
        this.mAppDelegate.showFloatingBrightPanel();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void showImmersionMenu() {
        this.mAppDelegate.showImmersionMenu();
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    public void showOverflowMenu() {
        this.mAppDelegate.showOverflowMenu();
    }

    @Override // android.app.Activity
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return this.mAppDelegate.startActionMode(callback);
    }

    @VisibleForTesting
    public void testNotifyResponseChange(int i2) {
        this.mAppDelegate.testNotifyResponseChange(i2);
    }

    public void unregisterCoordinateScrollView(View view) {
        this.mAppDelegate.unregisterCoordinateScrollView(view);
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig, boolean z2) {
        AppDelegate appDelegate = this.mAppDelegate;
        if (appDelegate != null) {
            appDelegate.addGroupButtons(groupButtonsConfig, z2);
        }
    }

    public void hideBottomMenu(boolean z2) {
        this.mAppDelegate.hideBottomMenu(z2);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.mAppDelegate.onWindowStartingActionMode(callback);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        this.mAppDelegate.setContentView(view);
    }

    public void showBottomMenu(boolean z2) {
        this.mAppDelegate.showBottomMenu(z2);
    }

    @Override // miuix.appcompat.app.IImmersionMenu
    @Deprecated
    public void showImmersionMenu(View view, ViewGroup viewGroup) {
        this.mAppDelegate.showImmersionMenu(view, viewGroup);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mAppDelegate.setContentView(view, layoutParams);
    }
}
