package miuix.appcompat.app;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.ActionMode;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.activity.result.ActivityResultCaller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import java.util.List;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarImpl;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.app.widget.ActionBarView;
import miuix.appcompat.internal.util.AnimationUtils;
import miuix.appcompat.internal.view.SimpleWindowCallback;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.core.util.EnvStateManager;
import miuix.core.util.WindowBaseInfo;
import miuix.internal.util.AttributeResolver;
import miuix.os.DeviceHelper;
import miuix.responsive.interfaces.IResponsive;
import miuix.responsive.map.ResponsiveState;
import miuix.responsive.map.ScreenSpec;
import miuix.responsive.page.manager.BaseResponseStateManager;
import miuix.view.SearchActionMode;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentDelegate extends ActionBarDelegateImpl implements IResponsive<androidx.fragment.app.Fragment> {
    private static final int INVALIDATE_MENU_POSTED = 16;
    public static final int MENU_INVALIDATE = 1;
    private int mExtraThemeRes;
    private androidx.fragment.app.Fragment mFragment;
    private final Handler mHandler;
    private View mInflatedView;
    private byte mInvalidateMenuFlags;
    private Runnable mInvalidateMenuRunnable;
    protected boolean mIsInEditActionMode;
    protected boolean mIsInSearchActionMode;
    private boolean mIsUserResponsiveEnabled;

    @Nullable
    private BaseResponseStateManager mResponsiveStateManager;
    private boolean mSplitActionBarEnable;
    private View mSubDecor;
    private Context mThemedContext;
    private final Window.Callback mWindowCallback;

    public class InvalidateMenuRunnable implements Runnable {
        private InvalidateMenuRunnable() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.view.Menu, miuix.appcompat.internal.view.menu.MenuBuilder] */
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
            if (FragmentDelegate.this.isEndActionMenuEnabled() || FragmentDelegate.this.ismSplitActionBarEnable()) {
                ?? CreateMenu = FragmentDelegate.this.createMenu();
                boolean zOnCreatePanelMenu = FragmentDelegate.this.onCreatePanelMenu(0, CreateMenu);
                if (zOnCreatePanelMenu) {
                    zOnCreatePanelMenu = FragmentDelegate.this.onPreparePanel(0, null, CreateMenu);
                }
                if (zOnCreatePanelMenu) {
                    FragmentDelegate.this.setMenu(CreateMenu);
                } else {
                    FragmentDelegate.this.setMenu(null);
                }
            } else {
                FragmentDelegate.this.setMenu(null);
            }
            FragmentDelegate.access$372(FragmentDelegate.this, -18);
        }
    }

    public FragmentDelegate(androidx.fragment.app.Fragment fragment) {
        super((AppCompatActivity) fragment.getActivity());
        this.mIsUserResponsiveEnabled = false;
        this.mIsInEditActionMode = false;
        this.mIsInSearchActionMode = false;
        this.mSplitActionBarEnable = false;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mWindowCallback = new SimpleWindowCallback() { // from class: miuix.appcompat.app.FragmentDelegate.1
            @Override // miuix.appcompat.internal.view.SimpleWindowCallback, android.view.Window.Callback
            public void onActionModeFinished(ActionMode actionMode) {
                ((IFragment) FragmentDelegate.this.mFragment).onActionModeFinished(actionMode);
            }

            @Override // miuix.appcompat.internal.view.SimpleWindowCallback, android.view.Window.Callback
            public void onActionModeStarted(ActionMode actionMode) {
                ((IFragment) FragmentDelegate.this.mFragment).onActionModeStarted(actionMode);
            }

            @Override // miuix.appcompat.internal.view.SimpleWindowCallback, android.view.Window.Callback
            public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
                return FragmentDelegate.this.onMenuItemSelected(i2, menuItem);
            }

            @Override // miuix.appcompat.internal.view.SimpleWindowCallback, android.view.Window.Callback
            public void onPanelClosed(int i2, Menu menu) {
                FragmentDelegate.this.onPanelClosed(i2, menu);
            }

            @Override // miuix.appcompat.internal.view.SimpleWindowCallback, android.view.Window.Callback
            public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
                return FragmentDelegate.this.onWindowStartingActionMode(callback);
            }
        };
        this.mFragment = fragment;
    }

    public static /* synthetic */ byte access$372(FragmentDelegate fragmentDelegate, int i2) {
        byte b2 = (byte) (i2 & fragmentDelegate.mInvalidateMenuFlags);
        fragmentDelegate.mInvalidateMenuFlags = b2;
        return b2;
    }

    private Runnable getInvalidateMenuRunnable() {
        if (this.mInvalidateMenuRunnable == null) {
            this.mInvalidateMenuRunnable = new InvalidateMenuRunnable();
        }
        return this.mInvalidateMenuRunnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateExtraPaddingHorizontal(@NonNull Context context, @NonNull ExtraPaddingPolicy extraPaddingPolicy, int i2, int i3) {
        Resources resources = context.getResources();
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(context, resources.getConfiguration());
        if (i2 == -1) {
            i2 = windowInfo.windowSize.x;
        }
        int i4 = i2;
        if (i3 == -1) {
            i3 = windowInfo.windowSize.y;
        }
        float f2 = resources.getDisplayMetrics().density;
        Point point = windowInfo.windowSizeDp;
        extraPaddingPolicy.onContainerSizeChanged(point.x, point.y, i4, i3, f2, false);
        return setExtraHorizontalPadding(extraPaddingPolicy.isEnable() ? (int) (extraPaddingPolicy.getExtraPaddingDp() * f2) : 0);
    }

    public boolean acceptExtraPaddingFromParent() {
        return hasActionBar() || !isExtraHorizontalPaddingEnable() || this.mExtraPaddingPolicy == null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        super.addExtraPaddingObserver(extraPaddingObserver);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).addExtraPaddingObserver(extraPaddingObserver);
        }
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig, boolean z2) {
        super.addGroupButtons(groupButtonsConfig);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).addGroupButtons(groupButtonsConfig, z2);
        }
    }

    public void checkThemeLegality() {
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public ActionBar createActionBar() {
        if (!this.mFragment.isAdded() || this.mActionBarView == null) {
            return null;
        }
        return new ActionBarImpl(this.mFragment);
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void dispatchResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        onResponsiveLayout(configuration, screenSpec, z2);
    }

    public int getBottomMenuCustomViewTranslationY() {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            return ((ActionBarOverlayLayout) view).getBottomMenuCustomViewTranslationY();
        }
        return 0;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public int getBottomMenuMode() {
        View view = this.mSubDecor;
        return view instanceof ActionBarOverlayLayout ? ((ActionBarOverlayLayout) view).getBottomMenuMode() : super.getBottomMenuMode();
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public Rect getContentInset() {
        boolean z2 = this.mHasActionBar;
        if (!z2 && this.mContentInset == null) {
            ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
            if (parentFragment instanceof IFragment) {
                this.mContentInset = ((IFragment) parentFragment).getContentInset();
            } else if (parentFragment == null) {
                this.mContentInset = getActivity().getContentInset();
            }
        } else if (z2) {
            View view = this.mSubDecor;
            if (view instanceof ActionBarOverlayLayout) {
                this.mContentInset = ((ActionBarOverlayLayout) view).getContentInset();
            }
        }
        return this.mContentInset;
    }

    public View getInflatedView() {
        return this.mInflatedView;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public LifecycleOwner getLifecycleOwner() {
        return this.mFragment;
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
        if (this.mThemedContext == null) {
            this.mThemedContext = this.mActivity;
            if (this.mExtraThemeRes != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mThemedContext, this.mExtraThemeRes);
            }
        }
        return this.mThemedContext;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public View getView() {
        return this.mSubDecor;
    }

    public void hideBottomMenu(boolean z2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).hideBottomMenu(z2);
        }
    }

    public void hideBottomMenuCustomView() {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).hideBottomMenuCustomView();
        }
    }

    public final void installSubDecor(Context context, ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (this.mSubDecorInstalled) {
            if (this.mSubDecor.getParent() == null || !(this.mSubDecor.getParent() instanceof ViewGroup)) {
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup) this.mSubDecor.getParent();
            if (viewGroup2.getChildCount() == 0) {
                viewGroup2.endViewTransition(this.mSubDecor);
                return;
            }
            return;
        }
        FragmentActivity activity = this.mFragment.getActivity();
        boolean z2 = activity instanceof AppCompatActivity;
        if (z2) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            appCompatActivity.setExtraHorizontalPaddingEnable(false);
            appCompatActivity.setExtraPaddingApplyToContentEnable(false);
        }
        this.mSubDecorInstalled = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) layoutInflater.inflate(R.layout.miuix_appcompat_screen_action_bar, viewGroup, false);
        actionBarOverlayLayout.setLifecycleOwner(getLifecycleOwner());
        actionBarOverlayLayout.setCallback(this.mWindowCallback);
        ActivityResultCaller activityResultCaller = this.mFragment;
        if (activityResultCaller instanceof IFragment) {
            actionBarOverlayLayout.setContentInsetStateCallback((IContentInsetState) activityResultCaller);
            actionBarOverlayLayout.addExtraPaddingObserver((ExtraPaddingObserver) this.mFragment);
        }
        actionBarOverlayLayout.setRootSubDecor(false);
        actionBarOverlayLayout.setOverlayMode(this.mOverlayActionBar);
        actionBarOverlayLayout.setTranslucentStatus(getTranslucentStatus());
        if (this.mExtraThemeRes != 0) {
            checkThemeLegality();
            ((IFragment) this.mFragment).checkThemeLegality();
            actionBarOverlayLayout.setBackground(AttributeResolver.resolveDrawable(context, android.R.attr.windowBackground));
        }
        if (z2) {
            actionBarOverlayLayout.onFloatingModeChanged(((AppCompatActivity) activity).isInFloatingWindowMode());
        }
        ActionBarView actionBarView = (ActionBarView) actionBarOverlayLayout.findViewById(R.id.action_bar);
        this.mActionBarView = actionBarView;
        actionBarView.setLifecycleOwner(getLifecycleOwner());
        this.mActionBarView.setWindowCallback(this.mWindowCallback);
        if (this.mFeatureIndeterminateProgress) {
            this.mActionBarView.initIndeterminateProgress();
        }
        if (isEndActionMenuEnabled()) {
            this.mActionBarView.setEndActionMenuEnable(true);
        }
        boolean zEquals = "splitActionBarWhenNarrow".equals(getUiOptionsFromMetadata());
        if (zEquals) {
            this.mSplitActionBarEnable = context.getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow);
        } else {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.styleable.Window);
            this.mSplitActionBarEnable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowSplitActionBar, false);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.mSplitActionBarEnable) {
            addSplitActionBar(true, zEquals, actionBarOverlayLayout);
        }
        updateOptionsMenu(1);
        this.mSubDecor = actionBarOverlayLayout;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void invalidateOptionsMenu() {
        byte b2 = this.mInvalidateMenuFlags;
        if ((b2 & 16) == 0) {
            this.mInvalidateMenuFlags = (byte) (b2 | 16);
            getInvalidateMenuRunnable().run();
        }
    }

    public boolean isInEditActionMode() {
        ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
        return (hasActionBar() || !(parentFragment instanceof IFragment)) ? this.mIsInEditActionMode : ((IFragment) parentFragment).isInEditActionMode();
    }

    public boolean isIsInSearchActionMode() {
        ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
        return (hasActionBar() || !(parentFragment instanceof IFragment)) ? this.mIsInSearchActionMode : ((IFragment) parentFragment).isIsInSearchActionMode();
    }

    public boolean isRegisterResponsive() {
        return this.mResponsiveStateManager != null;
    }

    public boolean ismSplitActionBarEnable() {
        return this.mSplitActionBarEnable;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void onConfigurationChanged(Configuration configuration) {
        int iDetectType;
        BaseResponseStateManager baseResponseStateManager = this.mResponsiveStateManager;
        if (baseResponseStateManager != null) {
            baseResponseStateManager.beforeConfigurationChanged(this.mFragment.getResources().getConfiguration());
        }
        super.onConfigurationChanged(configuration);
        if (!this.mUserExtraPaddingPolicy && this.mDeviceType != (iDetectType = DeviceHelper.detectType(this.mActivity))) {
            this.mDeviceType = iDetectType;
            initExtraPaddingPolicy();
            View view = this.mSubDecor;
            if (view instanceof ActionBarOverlayLayout) {
                ((ActionBarOverlayLayout) view).setExtraPaddingPolicy(this.mExtraPaddingPolicy);
            }
        }
        View view2 = this.mSubDecor;
        if (view2 != null && (view2 instanceof ActionBarOverlayLayout)) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view2;
            if (!this.mUserExtraPaddingPolicy) {
                actionBarOverlayLayout.setExtraPaddingPolicy(getExtraPaddingPolicy());
            }
            FragmentActivity activity = this.mFragment.getActivity();
            if (activity instanceof AppCompatActivity) {
                ((ActionBarOverlayLayout) this.mSubDecor).onFloatingModeChanged(((AppCompatActivity) activity).isInFloatingWindowMode());
            }
        }
        BaseResponseStateManager baseResponseStateManager2 = this.mResponsiveStateManager;
        if (baseResponseStateManager2 != null) {
            baseResponseStateManager2.afterConfigurationChanged(configuration);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.IContentInsetState
    public void onContentInsetChanged(Rect rect) {
        super.onContentInsetChanged(rect);
        List<androidx.fragment.app.Fragment> fragments = this.mFragment.getChildFragmentManager().getFragments();
        int size = fragments.size();
        for (int i2 = 0; i2 < size; i2++) {
            androidx.fragment.app.Fragment fragment = fragments.get(i2);
            if ((fragment instanceof IFragment) && fragment.isAdded()) {
                IFragment iFragment = (IFragment) fragment;
                if (!iFragment.hasActionBar()) {
                    iFragment.onContentInsetChanged(rect);
                }
            }
        }
    }

    @Nullable
    public Animator onCreateAnimator(int i2, boolean z2, int i3) {
        return AnimationUtils.createAnimator(this.mFragment, i3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public boolean onCreateImmersionMenu(MenuBuilder menuBuilder) {
        return ((IFragment) this.mFragment).onCreateOptionsMenu(menuBuilder);
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 == 0) {
            return ((IFragment) this.mFragment).onCreatePanelMenu(i2, menu);
        }
        return false;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public View onCreatePanelView(int i2) {
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedArray typedArrayObtainStyledAttributes = getThemedContext().obtainStyledAttributes(R.styleable.Window);
        if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_responsiveEnabled, this.mIsUserResponsiveEnabled)) {
            this.mResponsiveStateManager = new BaseResponseStateManager(this) { // from class: miuix.appcompat.app.FragmentDelegate.2
                @Override // miuix.responsive.page.manager.BaseStateManager
                public Context getContext() {
                    return FragmentDelegate.this.getThemedContext();
                }
            };
        }
        int i2 = R.styleable.Window_windowActionBar;
        if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a miuix theme (or descendant) with this fragment.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(i2, false)) {
            requestWindowFeature(8);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowActionBarOverlay, false)) {
            requestWindowFeature(9);
        }
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowExtraPaddingHorizontalEnable, this.mExtraPaddingEnable);
        if (this.mExtraPaddingEnable) {
            z2 = true;
        }
        setExtraHorizontalPaddingEnable(z2);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowExtraPaddingHorizontalInitEnable, this.mExtraPaddingInitEnable);
        if (this.mExtraPaddingInitEnable) {
            z3 = true;
        }
        setExtraHorizontalPaddingInitEnable(z3);
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_windowExtraPaddingApplyToContentEnable, this.mExtraPaddingApplyToContentEnable);
        if (this.mExtraPaddingApplyToContentEnable) {
            z4 = true;
        }
        setExtraPaddingApplyToContentEnable(z4);
        setTranslucentStatus(typedArrayObtainStyledAttributes.getInt(R.styleable.Window_windowTranslucentStatus, 0));
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(getThemedContext());
        if (this.mHasActionBar) {
            installSubDecor(getThemedContext(), viewGroup, layoutInflaterCloneInContext);
            if (this.mSubDecor instanceof ActionBarOverlayLayout) {
                if (!this.mUserExtraPaddingPolicy) {
                    initExtraPaddingPolicy();
                }
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.mSubDecor;
                actionBarOverlayLayout.setExtraHorizontalPaddingEnable(isExtraHorizontalPaddingEnable());
                actionBarOverlayLayout.setExtraHorizontalPaddingInitEnable(this.mExtraPaddingInitEnable);
                actionBarOverlayLayout.setExtraPaddingApplyToContentEnable(isExtraPaddingApplyToContentEnable());
                actionBarOverlayLayout.setExtraPaddingPolicy(this.mExtraPaddingPolicy);
            }
            ViewGroup viewGroup2 = (ViewGroup) this.mSubDecor.findViewById(android.R.id.content);
            View viewOnInflateView = ((IFragment) this.mFragment).onInflateView(layoutInflaterCloneInContext, viewGroup2, bundle);
            this.mInflatedView = viewOnInflateView;
            if (viewOnInflateView != null && viewOnInflateView.getParent() != viewGroup2) {
                if (this.mInflatedView.getParent() != null) {
                    ((ViewGroup) this.mInflatedView.getParent()).removeView(this.mInflatedView);
                }
                viewGroup2.removeAllViews();
                viewGroup2.addView(this.mInflatedView);
            }
            if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_endActionMenuEnabled, false)) {
                setEndActionMenuEnabled(true, typedArrayObtainStyledAttributes.getBoolean(R.styleable.Window_hyperActionMenuEnabled, false), false);
            } else {
                byte b2 = this.mInvalidateMenuFlags;
                if ((b2 & 16) == 0) {
                    this.mInvalidateMenuFlags = (byte) (b2 | 16);
                    this.mHandler.post(getInvalidateMenuRunnable());
                }
            }
        } else {
            View viewOnInflateView2 = ((IFragment) this.mFragment).onInflateView(layoutInflaterCloneInContext, viewGroup, bundle);
            this.mInflatedView = viewOnInflateView2;
            this.mSubDecor = viewOnInflateView2;
            if (viewOnInflateView2 != null) {
                if (!this.mUserExtraPaddingPolicy) {
                    initExtraPaddingPolicy();
                }
                if (!((IFragment) this.mFragment).acceptExtraPaddingFromParent()) {
                    if (this.mExtraPaddingInitEnable) {
                        Context context = this.mFragment.getContext();
                        ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
                        if (extraPaddingPolicy != null && context != null) {
                            updateExtraPaddingHorizontal(context, extraPaddingPolicy, -1, -1);
                        }
                    }
                    this.mSubDecor.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.appcompat.app.FragmentDelegate.3
                        @Override // android.view.View.OnLayoutChangeListener
                        public void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                            Context context2 = FragmentDelegate.this.mFragment.getContext();
                            FragmentDelegate fragmentDelegate = FragmentDelegate.this;
                            ExtraPaddingPolicy extraPaddingPolicy2 = fragmentDelegate.mExtraPaddingPolicy;
                            if (extraPaddingPolicy2 == null || context2 == null || !fragmentDelegate.updateExtraPaddingHorizontal(context2, extraPaddingPolicy2, i5 - i3, i6 - i4)) {
                                return;
                            }
                            if (FragmentDelegate.this.mExtraPaddingObserver != null) {
                                for (int i11 = 0; i11 < FragmentDelegate.this.mExtraPaddingObserver.size(); i11++) {
                                    FragmentDelegate.this.mExtraPaddingObserver.get(i11).onExtraPaddingChanged(FragmentDelegate.this.mExtraHorizontalPadding);
                                }
                            }
                            ((IFragment) FragmentDelegate.this.mFragment).onExtraPaddingChanged(FragmentDelegate.this.mExtraHorizontalPadding);
                        }
                    });
                }
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return this.mSubDecor;
    }

    public void onDestroyView() {
        onDestroy();
        List<ExtraPaddingObserver> list = this.mExtraPaddingObserver;
        if (list != null) {
            list.clear();
        }
        this.mInflatedView = null;
        this.mSubDecor = null;
        this.mSubDecorInstalled = false;
        this.mHasAddSplitActionBar = false;
        this.mActionBar = null;
        this.mActionBarView = null;
        Runnable runnable = this.mInvalidateMenuRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
            this.mInvalidateMenuRunnable = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.container.ExtraPaddingObserver
    public void onExtraPaddingChanged(int i2) {
        this.mExtraHorizontalPadding = i2;
        List<androidx.fragment.app.Fragment> fragments = this.mFragment.getChildFragmentManager().getFragments();
        int size = fragments.size();
        for (int i3 = 0; i3 < size; i3++) {
            androidx.fragment.app.Fragment fragment = fragments.get(i3);
            if ((fragment instanceof IFragment) && fragment.isAdded()) {
                IFragment iFragment = (IFragment) fragment;
                if (iFragment.acceptExtraPaddingFromParent() && iFragment.isExtraHorizontalPaddingEnable()) {
                    iFragment.onExtraPaddingChanged(i2);
                }
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (i2 == 0) {
            return this.mFragment.onOptionsItemSelected(menuItem);
        }
        if (i2 == 6) {
            return this.mFragment.onContextItemSelected(menuItem);
        }
        return false;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onPanelClosed(int i2, Menu menu) {
        ((IFragment) this.mFragment).onPanelClosed(i2, menu);
        if (i2 == 0) {
            this.mFragment.onOptionsMenuClosed(menu);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void onPanelViewAdded(int i2, @Nullable View view, @Nullable Menu menu, @Nullable Menu menu2) {
        if (i2 == 0) {
            ((IFragment) this.mFragment).onOptionsMenuViewAdded(menu, menu2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public boolean onPrepareImmersionMenu(MenuBuilder menuBuilder) {
        this.mFragment.onPrepareOptionsMenu(menuBuilder);
        return true;
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public boolean onPreparePanel(int i2, @Nullable View view, Menu menu) {
        if (i2 != 0) {
            return false;
        }
        ((IFragment) this.mFragment).onPreparePanel(i2, null, menu);
        return true;
    }

    @Override // miuix.responsive.interfaces.IResponsive
    public void onResponsiveLayout(Configuration configuration, ScreenSpec screenSpec, boolean z2) {
        ActivityResultCaller activityResultCaller = this.mFragment;
        if (activityResultCaller instanceof IResponsive) {
            ((IResponsive) activityResultCaller).onResponsiveLayout(configuration, screenSpec, z2);
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        ((IFragment) this.mFragment).onViewInflated(this.mInflatedView, bundle);
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        if (getActionBar() != null) {
            return ((ActionBarImpl) getActionBar()).startActionMode(callback);
        }
        return null;
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void registerCoordinateScrollView(View view) {
        super.registerCoordinateScrollView(view);
        if (hasActionBar()) {
            return;
        }
        ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
        ActionBar actionBar = parentFragment instanceof IFragment ? ((IFragment) parentFragment).getActionBar() : null;
        if (actionBar != null) {
            actionBar.registerCoordinateScrollView(view);
        }
    }

    public void removeBottomMenuCustomView() {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).removeBottomMenuCustomView();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        List<ExtraPaddingObserver> list = this.mExtraPaddingObserver;
        if (list != null) {
            list.remove(extraPaddingObserver);
        }
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).removeExtraPaddingObserver(extraPaddingObserver);
        }
    }

    @Override // miuix.appcompat.app.IContentInsetState
    public boolean requestDispatchContentInset() {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).requestDispatchContentInset();
            return true;
        }
        ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
        if (parentFragment instanceof IFragment ? ((IFragment) parentFragment).requestDispatchContentInset() : false) {
            return false;
        }
        return getActivity().requestDispatchContentInset();
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void setBottomExtraInset(int i2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setBottomExtraInset(i2);
        }
    }

    public void setBottomMenuCustomView(View view) {
        View view2 = this.mSubDecor;
        if (view2 instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view2).setBottomMenuCustomView(view);
        }
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setBottomMenuCustomViewTranslationYWithPx(i2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate
    public void setBottomMenuMode(int i2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setBottomMenuMode(i2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegate, miuix.appcompat.app.IContentInsetState
    public void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setCorrectNestedScrollMotionEventEnabled(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        super.setExtraHorizontalPaddingEnable(z2);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setExtraHorizontalPaddingEnable(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        super.setExtraHorizontalPaddingInitEnable(z2);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setExtraHorizontalPaddingInitEnable(this.mExtraPaddingInitEnable);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl
    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        super.setExtraPaddingApplyToContentEnable(z2);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setExtraPaddingApplyToContentEnable(z2);
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        super.setExtraPaddingPolicy(extraPaddingPolicy);
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setExtraPaddingPolicy(this.mExtraPaddingPolicy);
        }
    }

    public void setExtraThemeRes(int i2) {
        this.mExtraThemeRes = i2;
    }

    public void setGroupButtonsPanelBackground(Drawable drawable) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setGroupButtonsPanelBackground(drawable);
        }
    }

    public void setGroupButtonsPanelBackgroundColor(int i2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setGroupButtonsPanelBackgroundColor(i2);
        }
    }

    public void setGroupButtonsPanelBackgroundResource(int i2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setGroupButtonsPanelBackgroundResource(i2);
        }
    }

    public void setNestedScrollingParentEnabled(boolean z2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).setNestedScrollingParentEnabled(z2);
        }
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        View view = this.mSubDecor;
        if (view == null || !(view instanceof ActionBarOverlayLayout)) {
            return;
        }
        ((ActionBarOverlayLayout) view).setOnStatusBarChangeListener(onStatusBarChangeListener);
    }

    public void setResponsiveEnabled(boolean z2) {
        this.mIsUserResponsiveEnabled = z2;
    }

    public void showBottomMenu(boolean z2) {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).showBottomMenu(z2);
        }
    }

    public void showBottomMenuCustomView() {
        View view = this.mSubDecor;
        if (view instanceof ActionBarOverlayLayout) {
            ((ActionBarOverlayLayout) view).showBottomMenuCustomView();
        }
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (callback instanceof SearchActionMode.Callback) {
            addContentMask((ActionBarOverlayLayout) this.mSubDecor);
        }
        return this.mSubDecor.startActionMode(callback);
    }

    @Override // miuix.appcompat.app.ActionBarDelegateImpl, miuix.appcompat.app.ActionBarDelegate
    public void unregisterCoordinateScrollView(View view) {
        super.unregisterCoordinateScrollView(view);
        if (hasActionBar()) {
            return;
        }
        ActivityResultCaller parentFragment = this.mFragment.getParentFragment();
        ActionBar actionBar = parentFragment instanceof IFragment ? ((IFragment) parentFragment).getActionBar() : null;
        if (actionBar != null) {
            actionBar.unregisterCoordinateScrollView(view);
        }
    }

    public void updateOptionsMenu(int i2) {
        this.mInvalidateMenuFlags = (byte) ((i2 & 1) | this.mInvalidateMenuFlags);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // miuix.responsive.interfaces.IResponsive
    public androidx.fragment.app.Fragment getResponsiveSubject() {
        return this.mFragment;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return onMenuItemSelected(0, menuItem);
    }
}
