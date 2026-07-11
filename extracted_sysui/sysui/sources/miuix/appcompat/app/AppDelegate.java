package miuix.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.lifecycle.LifecycleOwner;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.util.List;
import miuix.appcompat.R;
import miuix.appcompat.app.floatingactivity.FloatingActivitySwitcher;
import miuix.appcompat.app.floatingactivity.OnFloatingActivityCallback;
import miuix.appcompat.app.floatingactivity.OnFloatingCallback;
import miuix.appcompat.app.floatingactivity.OnFloatingModeCallback;
import miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper;
import miuix.appcompat.app.floatingactivity.helper.FloatingHelperFactory;
import miuix.appcompat.app.floatingactivity.multiapp.MultiAppFloatingActivitySwitcher;
import miuix.appcompat.internal.app.widget.ActionBarContainer;
import miuix.appcompat.internal.app.widget.ActionBarImpl;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.app.widget.ActionBarView;
import miuix.appcompat.internal.util.AppcompatClassPreLoader;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.core.util.EnvStateManager;
import miuix.core.util.variable.WindowWrapper;
import miuix.internal.util.AttributeResolver;
import miuix.os.Build;
import miuix.os.DeviceHelper;
import miuix.responsive.interfaces.IResponsive;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;
import miuix.responsive.page.manager.BaseResponseStateManager;
import miuix.view.SearchActionMode;

/* JADX INFO: loaded from: classes2.dex */
class AppDelegate extends ActionBarDelegateImpl implements IResponsive<Activity> {
    private static final String ACTION_BAR_TAG = "miuix:ActionBar";
    private ActionBarContainer mActionBarContainer;
    private ActivityCallback mActivityCallback;
    private final String mActivityIdentity;
    private AppCompatWindowCallback mAppCompatWindowCallback;
    private ViewGroup mContentParent;
    private boolean mDelegateFinished;
    private OnFloatingModeCallback mFloatingModeCallback;
    private ViewGroup mFloatingRoot;
    private BaseFloatingActivityHelper mFloatingWindowHelper;
    private final Runnable mInvalidateMenuRunnable;
    private boolean mIsFloatingTheme;
    private boolean mIsFloatingWindow;
    private boolean mIsUserResponsiveEnabled;
    private LayoutInflater mLayoutInflater;

    @Nullable
    private BaseResponseStateManager mResponsiveStateManager;
    private boolean mSplitActionBarEnable;
    private ActionBarOverlayLayout mSubDecor;
    private CharSequence mTitle;
    private int mUIMode;
    private Runnable mUpdateWindowInfoRunnable;
    private Boolean mUserIsFloatingWindow;
    Window mWindow;

    public class AppCompatWindowCallback extends WindowCallbackWrapper {
        public AppCompatWindowCallback(Window.Callback callback) {
            super(callback);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
            if (ShortcutsCallback.dispatchGenericMotionEvent(AppDelegate.this.mActivity.getSupportFragmentManager(), motionEvent)) {
                return true;
            }
            return super.dispatchGenericMotionEvent(motionEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            if (ShortcutsCallback.dispatchKeyEvent(AppDelegate.this.mActivity.getSupportFragmentManager(), keyEvent)) {
                return true;
            }
            return super.dispatchKeyEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            if (ShortcutsCallback.dispatchKeyShortcutEvent(AppDelegate.this.mActivity.getSupportFragmentManager(), keyEvent)) {
                return true;
            }
            return super.dispatchKeyShortcutEvent(keyEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (ShortcutsCallback.dispatchTouchEvent(AppDelegate.this.mActivity.getSupportFragmentManager(), motionEvent)) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
            if (ShortcutsCallback.dispatchTrackballEvent(AppDelegate.this.mActivity.getSupportFragmentManager(), motionEvent)) {
                return true;
            }
            return super.dispatchTrackballEvent(motionEvent);
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onContentChanged() {
        }

        @Override // androidx.appcompat.view.WindowCallbackWrapper, android.view.Window.Callback
        public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            ShortcutsCallback.dispatchProvideKeyboardShortcuts(AppDelegate.this.mActivity.getSupportFragmentManager(), list, menu, i2);
            super.onProvideKeyboardShortcuts(list, menu, i2);
        }
    }

    public AppDelegate(AppCompatActivity appCompatActivity, ActivityCallback activityCallback, OnFloatingModeCallback onFloatingModeCallback) {
        super(appCompatActivity);
        this.mIsUserResponsiveEnabled = false;
        this.mIsFloatingTheme = false;
        this.mIsFloatingWindow = false;
        this.mUserIsFloatingWindow = null;
        this.mFloatingRoot = null;
        this.mDelegateFinished = false;
        this.mInvalidateMenuRunnable = new Runnable() { // from class: miuix.appcompat.app.AppDelegate.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v1, types: [android.view.Menu, miuix.appcompat.internal.view.menu.MenuBuilder] */
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
            @Override // java.lang.Runnable
            public void run() {
                ?? CreateMenu = AppDelegate.this.createMenu();
                if ((AppDelegate.this.isEndActionMenuEnabled() || AppDelegate.this.mSplitActionBarEnable) && AppDelegate.this.mActivityCallback.onCreatePanelMenu(0, CreateMenu) && AppDelegate.this.mActivityCallback.onPreparePanel(0, null, CreateMenu)) {
                    AppDelegate.this.setMenu(CreateMenu);
                } else {
                    AppDelegate.this.setMenu(null);
                }
            }
        };
        this.mActivityIdentity = String.valueOf(SystemClock.elapsedRealtimeNanos());
        this.mActivityCallback = activityCallback;
        this.mFloatingModeCallback = onFloatingModeCallback;
    }

    private void attachToWindow(@NonNull Window window) {
        if (this.mWindow != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof AppCompatWindowCallback) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        AppCompatWindowCallback appCompatWindowCallback = new AppCompatWindowCallback(callback);
        this.mAppCompatWindowCallback = appCompatWindowCallback;
        window.setCallback(appCompatWindowCallback);
        this.mWindow = window;
    }

    private void ensureWindow() {
        AppCompatActivity appCompatActivity;
        Window window = this.mWindow;
        if (window != null) {
            return;
        }
        if (window == null && (appCompatActivity = this.mActivity) != null) {
            attachToWindow(appCompatActivity.getWindow());
        }
        if (this.mWindow == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private int getDecorViewLayoutRes(Window window) {
        Context context = window.getContext();
        int i2 = AttributeResolver.resolveBoolean(context, R.attr.windowActionBar, false) ? AttributeResolver.resolveBoolean(context, R.attr.windowActionBarMovable, false) ? R.layout.miuix_appcompat_screen_action_bar_movable : R.layout.miuix_appcompat_screen_action_bar : R.layout.miuix_appcompat_screen_simple;
        int iResolve = AttributeResolver.resolve(context, R.attr.startingWindowOverlay);
        if (iResolve > 0 && isSystemProcess() && isWindowActionBarEnabled(context)) {
            i2 = iResolve;
        }
        if (!window.isFloating() && (window.getCallback() instanceof Dialog)) {
            WindowWrapper.setTranslucentStatus(window, AttributeResolver.resolveInt(context, R.attr.windowTranslucentStatus, 0));
        }
        return i2;
    }

    private void installSubDecor() {
        ActionBarOverlayLayout actionBarOverlayLayout;
        if (this.mSubDecorInstalled) {
            return;
        }
        ensureWindow();
        this.mSubDecorInstalled = true;
        Window window = this.mActivity.getWindow();
        this.mLayoutInflater = window.getLayoutInflater();
        TypedArray typedArrayObtainStyledAttributes = this.mActivity.obtainStyledAttributes(R.styleable.Window);
        if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_responsiveEnabled, this.mIsUserResponsiveEnabled)) {
            this.mResponsiveStateManager = new BaseResponseStateManager(this) { // from class: miuix.appcompat.app.AppDelegate.1
                @Override // miuix.responsive.page.manager.BaseStateManager
                public Context getContext() {
                    return AppDelegate.this.mActivity;
                }
            };
        }
        if (typedArrayObtainStyledAttributes.getInt(R.styleable.Window_windowLayoutMode, 0) == 1) {
            this.mActivity.getWindow().setGravity(80);
        }
        int i2 = R.styleable.Window_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a miui theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            requestWindowFeature(8);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowActionBarOverlay, false)) {
            requestWindowFeature(9);
        }
        this.mIsFloatingTheme = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_isMiuixFloatingTheme, false);
        this.mIsFloatingWindow = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowFloating, false);
        setTranslucentStatus(typedArrayObtainStyledAttributes.getInt(R.styleable.Window_windowTranslucentStatus, 0));
        this.mUIMode = this.mActivity.getResources().getConfiguration().uiMode;
        installToDecor(window);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.mSubDecor;
        if (actionBarOverlayLayout2 != null) {
            actionBarOverlayLayout2.setCallback(this.mActivity);
            this.mSubDecor.setContentInsetStateCallback(this.mActivity);
            this.mSubDecor.addExtraPaddingObserver(this.mActivity);
            this.mSubDecor.setTranslucentStatus(getTranslucentStatus());
        }
        if (this.mHasActionBar && (actionBarOverlayLayout = this.mSubDecor) != null) {
            this.mActionBarContainer = (ActionBarContainer) actionBarOverlayLayout.findViewById(R.id.action_bar_container);
            this.mSubDecor.setOverlayMode(this.mOverlayActionBar);
            ActionBarView actionBarView = (ActionBarView) this.mSubDecor.findViewById(R.id.action_bar);
            this.mActionBarView = actionBarView;
            actionBarView.setLifecycleOwner(getLifecycleOwner());
            this.mActionBarView.setWindowCallback(this.mActivity);
            if (this.mFeatureIndeterminateProgress) {
                this.mActionBarView.initIndeterminateProgress();
            }
            if (isEndActionMenuEnabled()) {
                this.mActionBarView.setEndActionMenuEnable(true);
            }
            if (this.mActionBarView.getCustomNavigationView() != null) {
                ActionBarView actionBarView2 = this.mActionBarView;
                actionBarView2.setDisplayOptions(actionBarView2.getDisplayOptions() | 16);
            }
            boolean zEquals = "splitActionBarWhenNarrow".equals(getUiOptionsFromMetadata());
            if (zEquals) {
                this.mSplitActionBarEnable = this.mActivity.getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow);
            } else {
                this.mSplitActionBarEnable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowSplitActionBar, false);
            }
            if (this.mSplitActionBarEnable) {
                addSplitActionBar(true, zEquals, this.mSubDecor);
            }
            if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_endActionMenuEnabled, false)) {
                setEndActionMenuEnabled(true, typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_hyperActionMenuEnabled, false), false);
            } else if (this.mActivity.getWindow() != null) {
                this.mActivity.getWindow().getDecorView().post(this.mInvalidateMenuRunnable);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
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
    private void installToDecor(Window window) {
        this.mFloatingWindowHelper = this.mIsFloatingTheme ? FloatingHelperFactory.get(this.mActivity) : null;
        this.mFloatingRoot = null;
        View viewInflate = View.inflate(this.mActivity, getDecorViewLayoutRes(window), null);
        View view = viewInflate;
        if (this.mFloatingWindowHelper != null) {
            boolean zShouldShowFloatingActivityTabletMode = shouldShowFloatingActivityTabletMode();
            this.mIsFloatingWindow = zShouldShowFloatingActivityTabletMode;
            this.mFloatingWindowHelper.setFloatingWindowMode(zShouldShowFloatingActivityTabletMode);
            ViewGroup viewGroupReplaceSubDecor = this.mFloatingWindowHelper.replaceSubDecor(viewInflate, this.mIsFloatingWindow);
            this.mFloatingRoot = viewGroupReplaceSubDecor;
            updateSystemUiStateInFloatingTheme(this.mIsFloatingWindow);
            view = viewGroupReplaceSubDecor;
            if (this.mFloatingWindowHelper.shouldInterceptBack()) {
                this.mActivity.getOnBackPressedDispatcher().addCallback(this.mActivity, new OnBackPressedCallback(true) { // from class: miuix.appcompat.app.AppDelegate.2
                    @Override // androidx.activity.OnBackPressedCallback
                    public void handleOnBackPressed() {
                        AppDelegate.this.mFloatingWindowHelper.onBackPressed();
                    }
                });
                view = viewGroupReplaceSubDecor;
            }
        }
        if (!this.mUserExtraPaddingPolicy) {
            initExtraPaddingPolicy();
        }
        View viewFindViewById = view.findViewById(R.id.action_bar_overlay_layout);
        if (viewFindViewById instanceof ActionBarOverlayLayout) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) viewFindViewById;
            this.mSubDecor = actionBarOverlayLayout;
            actionBarOverlayLayout.setLifecycleOwner(getLifecycleOwner());
            this.mSubDecor.setExtraHorizontalPaddingEnable(this.mExtraPaddingEnable);
            this.mSubDecor.setExtraHorizontalPaddingInitEnable(this.mExtraPaddingInitEnable);
            this.mSubDecor.setExtraPaddingApplyToContentEnable(this.mExtraPaddingApplyToContentEnable);
            this.mSubDecor.setExtraPaddingPolicy(getExtraPaddingPolicy());
            ViewGroup viewGroup = (ViewGroup) this.mSubDecor.findViewById(android.R.id.content);
            ViewGroup viewGroup2 = (ViewGroup) window.findViewById(android.R.id.content);
            if (viewGroup2 != null) {
                while (viewGroup2.getChildCount() > 0) {
                    View childAt = viewGroup2.getChildAt(0);
                    viewGroup2.removeViewAt(0);
                    viewGroup.addView(childAt);
                }
                viewGroup2.setId(-1);
                viewGroup.setId(android.R.id.content);
                if (viewGroup2 instanceof FrameLayout) {
                    ((FrameLayout) viewGroup2).setForeground(null);
                }
            }
        }
        window.setContentView(view);
        ActionBarOverlayLayout actionBarOverlayLayout2 = this.mSubDecor;
        if (actionBarOverlayLayout2 != null) {
            this.mContentParent = (ViewGroup) actionBarOverlayLayout2.findViewById(android.R.id.content);
        }
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.init(this.mFloatingRoot, shouldShowFloatingActivityTabletMode());
        }
    }

    private boolean isSystemProcess() {
        return SystemMediaRouteProvider.PACKAGE_NAME.equals(getActivity().getApplicationContext().getApplicationInfo().packageName);
    }

    private static boolean isWindowActionBarEnabled(Context context) {
        return AttributeResolver.resolveBoolean(context, R.attr.windowActionBar, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$0(Configuration configuration) {
        AppCompatActivity appCompatActivity = this.mActivity;
        EnvStateManager.updateWindowInfo(appCompatActivity, appCompatActivity.getWindowInfo(), null, true);
        setFloatingWindowMode(isInFloatingWindowMode(), configuration.uiMode, true, Build.IS_FOLDABLE);
    }

    private void notifyFloatWindowModeChanged(boolean z2) {
        this.mFloatingModeCallback.onFloatingWindowModeChanged(z2);
    }

    private boolean shouldShowFloatingActivityTabletMode() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        return baseFloatingActivityHelper != null && baseFloatingActivityHelper.isFloatingModeSupport();
    }

    private void updateSystemUiStateInFloatingTheme(boolean z2) {
        Window window = this.mActivity.getWindow();
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        boolean z3 = ((systemUiVisibility & 1024) != 0) || (getTranslucentStatus() != 0);
        if (z2) {
            window.addFlags(201326592);
            window.setDecorFitsSystemWindows(false);
        } else {
            systemUiVisibility = z3 ? systemUiVisibility | 1024 : systemUiVisibility & (-1025);
            if (z3) {
                window.setDecorFitsSystemWindows(false);
            } else {
                window.setDecorFitsSystemWindows(true);
            }
        }
        window.getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.mSubDecorInstalled) {
            installSubDecor();
        }
        ViewGroup viewGroup = this.mContentParent;
        if (viewGroup != null) {
            viewGroup.addView(view, layoutParams);
        }
        this.mAppCompatWindowCallback.getWrapped().onContentChanged();
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        super.addExtraPaddingObserver(extraPaddingObserver);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.addExtraPaddingObserver(extraPaddingObserver);
        }
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig, boolean z2) {
        super.addGroupButtons(groupButtonsConfig);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.addGroupButtons(groupButtonsConfig, z2);
        }
    }

    public void afterConfigurationChanged(Configuration configuration) {
        BaseResponseStateManager baseResponseStateManager = this.mResponsiveStateManager;
        if (baseResponseStateManager != null) {
            baseResponseStateManager.afterConfigurationChanged(configuration);
        }
    }

    public void beforeConfigurationChanged(Configuration configuration) {
        BaseResponseStateManager baseResponseStateManager = this.mResponsiveStateManager;
        if (baseResponseStateManager != null) {
            baseResponseStateManager.beforeConfigurationChanged(configuration);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public ActionBar createActionBar() {
        if (!this.mSubDecorInstalled) {
            installSubDecor();
        }
        if (this.mSubDecor == null) {
            return null;
        }
        return new ActionBarImpl(this.mActivity, this.mSubDecor);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void dispatchResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        onResponsiveLayout(configuration, screenSpec, z2);
    }

    public void executeCloseEnterAnimation() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.executeCloseEnterAnimation();
        }
    }

    public void executeCloseExitAnimation() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.executeCloseExitAnimation();
        }
    }

    public void executeOpenEnterAnimation() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.executeOpenEnterAnimation();
        }
    }

    public void executeOpenExitAnimation() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.executeOpenExitAnimation();
        }
    }

    public void exitFloatingActivityAll() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.exitFloatingActivityAll();
        }
    }

    public View getActionBarOverlay() {
        return this.mSubDecor;
    }

    public String getActivityIdentity() {
        return this.mActivityIdentity;
    }

    public int getBottomMenuCustomViewTranslationY() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            return actionBarOverlayLayout.getBottomMenuCustomViewTranslationY();
        }
        return 0;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public int getBottomMenuMode() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        return actionBarOverlayLayout != null ? actionBarOverlayLayout.getBottomMenuMode() : super.getBottomMenuMode();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public Rect getContentInset() {
        return this.mContentInset;
    }

    public View getFloatingBrightPanel() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            return baseFloatingActivityHelper.getFloatingBrightPanel();
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public LifecycleOwner getLifecycleOwner() {
        return this.mActivity;
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public ResponsiveState getResponsiveState() {
        BaseResponseStateManager baseResponseStateManager = this.mResponsiveStateManager;
        if (baseResponseStateManager != null) {
            return baseResponseStateManager.getState();
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public Context getThemedContext() {
        return this.mActivity;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public View getView() {
        return this.mSubDecor;
    }

    public void hideBottomMenu(boolean z2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.hideBottomMenu(z2);
        }
    }

    public void hideBottomMenuCustomView() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.hideBottomMenuCustomView();
        }
    }

    public void hideFloatingBrightPanel() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.hideFloatingBrightPanel();
        }
    }

    public void hideFloatingDimBackground() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.hideFloatingDimBackground();
        }
    }

    public void installFloatingSwitcher(boolean z2, Bundle bundle) {
        if (z2) {
            Intent intent = this.mActivity.getIntent();
            if (intent == null || !MultiAppFloatingActivitySwitcher.isFromMultiApp(intent)) {
                FloatingActivitySwitcher.install(this.mActivity, bundle);
            } else {
                MultiAppFloatingActivitySwitcher.install(this.mActivity, intent, bundle);
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void invalidateOptionsMenu() {
        AppCompatActivity appCompatActivity = this.mActivity;
        if (appCompatActivity == null || appCompatActivity.isFinishing() || this.mActivity.isDestroyed()) {
            return;
        }
        this.mInvalidateMenuRunnable.run();
    }

    public boolean isDelegateFinishing() {
        return this.mDelegateFinished;
    }

    public boolean isFloatingTheme() {
        return this.mIsFloatingTheme;
    }

    public boolean isInFloatingWindowMode() {
        Boolean bool = this.mUserIsFloatingWindow;
        return bool == null ? shouldShowFloatingActivityTabletMode() : bool.booleanValue();
    }

    public boolean isRegisterResponsive() {
        return this.mResponsiveStateManager != null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void onConfigurationChanged(final Configuration configuration) {
        int iDetectType;
        AppCompatActivity appCompatActivity = this.mActivity;
        EnvStateManager.updateWindowInfo(appCompatActivity, appCompatActivity.getWindowInfo(), configuration, false);
        this.mUpdateWindowInfoRunnable = new Runnable() { // from class: miuix.appcompat.app.l
            @Override // java.lang.Runnable
            public final void run() {
                this.f6047a.lambda$onConfigurationChanged$0(configuration);
            }
        };
        if (this.mActivity.getWindow() != null) {
            this.mActivity.getWindow().getDecorView().post(this.mUpdateWindowInfoRunnable);
        }
        super.onConfigurationChanged(configuration);
        if (!this.mUserExtraPaddingPolicy && this.mDeviceType != (iDetectType = DeviceHelper.detectType(this.mActivity))) {
            this.mDeviceType = iDetectType;
            initExtraPaddingPolicy();
            ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setExtraPaddingPolicy(this.mExtraPaddingPolicy);
            }
        }
        this.mActivityCallback.onConfigurationChanged(configuration);
        if (isImmersionMenuShowing()) {
            showImmersionMenu();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.IContentInsetState
    public void onContentInsetChanged(Rect rect) {
        super.onContentInsetChanged(rect);
        List<androidx.fragment.app.Fragment> fragments = this.mActivity.getSupportFragmentManager().getFragments();
        int size = fragments.size();
        for (int i2 = 0; i2 < size; i2++) {
            ActivityResultCaller activityResultCaller = (androidx.fragment.app.Fragment) fragments.get(i2);
            if (activityResultCaller instanceof IFragment) {
                IFragment iFragment = (IFragment) activityResultCaller;
                if (!iFragment.hasActionBar()) {
                    iFragment.onContentInsetChanged(rect);
                }
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public void onCreate(Bundle bundle) {
        this.mActivity.checkThemeLegality();
        if (!AppcompatClassPreLoader.sIsActionBarClassInit) {
            AppcompatClassPreLoader.sIsActionBarClassInit = true;
            AppcompatClassPreLoader.preloadClass(getThemedContext().getApplicationContext());
        }
        boolean zResolveBoolean = AttributeResolver.resolveBoolean(this.mActivity, R.attr.windowExtraPaddingHorizontalEnable, AttributeResolver.resolveInt(this.mActivity, R.attr.windowExtraPaddingHorizontal, 0) != 0);
        if (this.mExtraPaddingEnable) {
            zResolveBoolean = true;
        }
        boolean zResolveBoolean2 = AttributeResolver.resolveBoolean(this.mActivity, R.attr.windowExtraPaddingHorizontalInitEnable, this.mExtraPaddingInitEnable);
        if (this.mExtraPaddingInitEnable) {
            zResolveBoolean2 = true;
        }
        boolean zResolveBoolean3 = this.mExtraPaddingApplyToContentEnable ? true : AttributeResolver.resolveBoolean(this.mActivity, R.attr.windowExtraPaddingApplyToContentEnable, this.mExtraPaddingApplyToContentEnable);
        setExtraHorizontalPaddingEnable(zResolveBoolean);
        setExtraHorizontalPaddingInitEnable(zResolveBoolean2);
        setExtraPaddingApplyToContentEnable(zResolveBoolean3);
        this.mActivityCallback.onCreate(bundle);
        installSubDecor();
        installFloatingSwitcher(this.mIsFloatingTheme, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public boolean onCreateImmersionMenu(MenuBuilder menuBuilder) {
        return this.mActivity.onCreateOptionsMenu(menuBuilder);
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return i2 != 0 && this.mActivityCallback.onCreatePanelMenu(i2, menu);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [miuix.appcompat.app.ActivityCallback] */
    /* JADX WARN: Type inference failed for: r1v2, types: [miuix.appcompat.app.ActivityCallback] */
    /* JADX WARN: Type inference failed for: r4v0, types: [miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.AppDelegate] */
    /* JADX WARN: Type inference failed for: r5v2, types: [miuix.appcompat.internal.view.menu.MenuBuilder] */
    /* JADX WARN: Type inference failed for: r5v3, types: [miuix.appcompat.internal.view.menu.MenuBuilder] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.Menu, miuix.appcompat.internal.view.menu.MenuBuilder] */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.view.Menu, miuix.appcompat.internal.view.menu.MenuBuilder] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
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
    @Override // miuix.appcompat.app.ActionBarDelegate
    public View onCreatePanelView(int i2) {
        if (i2 != 0) {
            return this.mActivityCallback.onCreatePanelView(i2);
        }
        if (isEndActionMenuEnabled() || this.mSplitActionBarEnable) {
            ?? r5 = this.mMenu;
            boolean zOnPreparePanel = true;
            r5 = r5;
            if (this.mActionMode == null) {
                if (r5 == 0) {
                    ?? CreateMenu = createMenu();
                    setMenu(CreateMenu);
                    CreateMenu.stopDispatchingItemsChanged();
                    zOnPreparePanel = this.mActivityCallback.onCreatePanelMenu(0, CreateMenu);
                    r5 = CreateMenu;
                }
                if (zOnPreparePanel) {
                    r5.stopDispatchingItemsChanged();
                    zOnPreparePanel = this.mActivityCallback.onPreparePanel(0, null, r5);
                }
            } else if (r5 == 0) {
                zOnPreparePanel = false;
            }
            if (zOnPreparePanel) {
                r5.startDispatchingItemsChanged();
            } else {
                setMenu(null);
            }
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void onDestroy() {
        super.onDestroy();
        AppCompatActivity appCompatActivity = this.mActivity;
        if (appCompatActivity == null || appCompatActivity.getWindow() == null) {
            return;
        }
        View decorView = this.mActivity.getWindow().getDecorView();
        decorView.removeCallbacks(this.mInvalidateMenuRunnable);
        Runnable runnable = this.mUpdateWindowInfoRunnable;
        if (runnable != null) {
            decorView.removeCallbacks(runnable);
        }
    }

    @Override // miuix.container.ExtraPaddingObserver
    public void onExtraPaddingChanged(int i2) {
        this.mExtraHorizontalPadding = i2;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public boolean onMenuItemSelected(int i2, @NonNull MenuItem menuItem) {
        if (this.mActivityCallback.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        if (i2 == 0 && menuItem.getItemId() == 16908332 && getActionBar() != null && (getActionBar().getDisplayOptions() & 4) != 0) {
            if (!(this.mActivity.getParent() == null ? this.mActivity.onNavigateUp() : this.mActivity.getParent().onNavigateUpFromChild(this.mActivity))) {
                this.mActivity.finish();
            }
        }
        return false;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onPanelClosed(int i2, Menu menu) {
        this.mActivityCallback.onPanelClosed(i2, menu);
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onPanelViewAdded(int i2, @Nullable View view, @Nullable Menu menu, @Nullable Menu menu2) {
        this.mActivityCallback.onPanelViewAdded(i2, view, menu, menu2);
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void onPostResume() {
        this.mActivityCallback.onPostResume();
        ActionBarImpl actionBarImpl = (ActionBarImpl) getActionBar();
        if (actionBarImpl != null) {
            actionBarImpl.setShowHideAnimationEnabled(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public boolean onPrepareImmersionMenu(MenuBuilder menuBuilder) {
        return this.mActivity.onPrepareOptionsMenu(menuBuilder);
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public boolean onPreparePanel(int i2, @Nullable View view, Menu menu) {
        return i2 != 0 && this.mActivityCallback.onPreparePanel(i2, view, menu);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void onResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        if (this.mActivity != null) {
            this.mActivity.onResponsiveLayout(configuration, screenSpec, z2);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        SparseArray sparseParcelableArray;
        this.mActivityCallback.onRestoreInstanceState(bundle);
        if (this.mActionBarContainer == null || (sparseParcelableArray = bundle.getSparseParcelableArray(ACTION_BAR_TAG)) == null) {
            return;
        }
        this.mActionBarContainer.restoreHierarchyState(sparseParcelableArray);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mActivityCallback.onSaveInstanceState(bundle);
        if (this.mFloatingWindowHelper != null) {
            FloatingActivitySwitcher.onSaveInstanceState(this.mActivity, bundle);
            MultiAppFloatingActivitySwitcher.onSaveInstanceState(this.mActivity.getTaskId(), this.mActivity.getActivityIdentity(), bundle);
        }
        if (this.mActionBarContainer != null) {
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            this.mActionBarContainer.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(ACTION_BAR_TAG, sparseArray);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void onStop() {
        this.mActivityCallback.onStop();
        dismissImmersionMenu(false);
        ActionBarImpl actionBarImpl = (ActionBarImpl) getActionBar();
        if (actionBarImpl != null) {
            actionBarImpl.setShowHideAnimationEnabled(false);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return getActionBar() != null ? ((ActionBarImpl) getActionBar()).startActionMode(callback) : super.onWindowStartingActionMode(callback);
    }

    public void removeBottomMenuCustomView() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.removeBottomMenuCustomView();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        super.removeExtraPaddingObserver(extraPaddingObserver);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.removeExtraPaddingObserver(extraPaddingObserver);
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public boolean requestDispatchContentInset() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout == null) {
            return false;
        }
        actionBarOverlayLayout.requestDispatchContentInset();
        return true;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void setBottomExtraInset(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setBottomExtraInset(i2);
        }
    }

    public void setBottomMenuCustomView(View view) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setBottomMenuCustomView(view);
        }
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setBottomMenuCustomViewTranslationYWithPx(i2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void setBottomMenuMode(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setBottomMenuMode(i2);
        }
    }

    public void setContentView(int i2) {
        if (!this.mSubDecorInstalled) {
            installSubDecor();
        }
        ViewGroup viewGroup = this.mContentParent;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.mLayoutInflater.inflate(i2, this.mContentParent);
        }
        this.mAppCompatWindowCallback.getWrapped().onContentChanged();
    }

    @Override // miuix.appcompat.app.ActionBarDelegate, miuix.appcompat.app.IContentInsetState
    public void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setCorrectNestedScrollMotionEventEnabled(z2);
        }
    }

    public void setEnableSwipToDismiss(boolean z2) {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.setEnableSwipToDismiss(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        super.setExtraHorizontalPaddingEnable(z2);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setExtraHorizontalPaddingEnable(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        super.setExtraHorizontalPaddingInitEnable(z2);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setExtraHorizontalPaddingInitEnable(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        super.setExtraPaddingApplyToContentEnable(z2);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setExtraPaddingApplyToContentEnable(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        super.setExtraPaddingPolicy(extraPaddingPolicy);
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setExtraPaddingPolicy(this.mExtraPaddingPolicy);
        }
    }

    public void setFloatingWindowBorderEnable(boolean z2) {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.setFloatingWindowBorderEnable(z2);
        }
    }

    public void setFloatingWindowMode(boolean z2) {
        this.mUserIsFloatingWindow = Boolean.valueOf(z2);
        setFloatingWindowMode(z2, this.mUIMode, true, true);
    }

    public void setGroupButtonsPanelBackground(Drawable drawable) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setGroupButtonsPanelBackground(drawable);
        }
    }

    public void setGroupButtonsPanelBackgroundColor(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setGroupButtonsPanelBackgroundColor(i2);
        }
    }

    public void setGroupButtonsPanelBackgroundResource(int i2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setGroupButtonsPanelBackgroundResource(i2);
        }
    }

    public void setOnFloatingCallback(OnFloatingCallback onFloatingCallback) {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.setOnFloatingCallback(onFloatingCallback);
        }
    }

    public void setOnFloatingWindowCallback(OnFloatingActivityCallback onFloatingActivityCallback) {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.setOnFloatingWindowCallback(onFloatingActivityCallback);
        }
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setOnStatusBarChangeListener(onStatusBarChangeListener);
        }
    }

    public void setResponsiveEnabled(boolean z2) {
        this.mIsUserResponsiveEnabled = z2;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setWindowTitle(charSequence);
        }
    }

    public boolean shouldDelegateActivityFinish() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper == null) {
            return false;
        }
        boolean zDelegateFinishFloatingActivityInternal = baseFloatingActivityHelper.delegateFinishFloatingActivityInternal();
        if (zDelegateFinishFloatingActivityInternal) {
            this.mDelegateFinished = true;
        }
        return zDelegateFinishFloatingActivityInternal;
    }

    public void showBottomMenu(boolean z2) {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.showBottomMenu(z2);
        }
    }

    public void showBottomMenuCustomView() {
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.showBottomMenuCustomView();
        }
    }

    public void showFloatingBrightPanel() {
        BaseFloatingActivityHelper baseFloatingActivityHelper = this.mFloatingWindowHelper;
        if (baseFloatingActivityHelper != null) {
            baseFloatingActivityHelper.showFloatingBrightPanel();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (callback instanceof SearchActionMode.Callback) {
            addContentMask(this.mSubDecor);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
        if (actionBarOverlayLayout != null) {
            return actionBarOverlayLayout.startActionMode(callback);
        }
        return null;
    }

    @VisibleForTesting
    public void testNotifyResponseChange(int i2) {
        BaseResponseStateManager baseResponseStateManager = this.mResponsiveStateManager;
        if (baseResponseStateManager != null) {
            baseResponseStateManager.testNotifyResponseChange(i2);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // miuix.responsive.interfaces.IResponsive
    public Activity getResponsiveSubject() {
        return this.mActivity;
    }

    private void setFloatingWindowMode(boolean z2, int i2, boolean z3, boolean z4) {
        if (this.mIsFloatingTheme) {
            if (z4 || Build.IS_TABLET) {
                if (this.mIsFloatingWindow != z2 && this.mFloatingModeCallback.onFloatingWindowModeChanging(z2)) {
                    this.mIsFloatingWindow = z2;
                    this.mFloatingWindowHelper.setFloatingWindowMode(z2);
                    updateSystemUiStateInFloatingTheme(this.mIsFloatingWindow);
                    ViewGroup.LayoutParams floatingLayoutParam = this.mFloatingWindowHelper.getFloatingLayoutParam();
                    if (floatingLayoutParam != null) {
                        if (z2) {
                            floatingLayoutParam.height = -2;
                            floatingLayoutParam.width = -2;
                        } else {
                            floatingLayoutParam.height = -1;
                            floatingLayoutParam.width = -1;
                        }
                    }
                    ActionBarOverlayLayout actionBarOverlayLayout = this.mSubDecor;
                    if (actionBarOverlayLayout != null) {
                        actionBarOverlayLayout.requestLayout();
                        this.mSubDecor.onFloatingModeChanged(z2);
                    }
                    if (z3) {
                        notifyFloatWindowModeChanged(z2);
                        return;
                    }
                    return;
                }
                if (i2 != this.mUIMode) {
                    this.mUIMode = i2;
                    this.mFloatingWindowHelper.setFloatingWindowMode(z2);
                }
            }
        }
    }

    public void setContentView(View view) {
        setContentView(view, new ViewGroup.LayoutParams(-1, -1));
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (!this.mSubDecorInstalled) {
            installSubDecor();
        }
        ViewGroup viewGroup = this.mContentParent;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.mContentParent.addView(view, layoutParams);
        }
        this.mAppCompatWindowCallback.getWrapped().onContentChanged();
    }

    @Override // miuix.appcompat.internal.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.mActivity.onMenuItemSelected(0, menuItem);
    }
}
