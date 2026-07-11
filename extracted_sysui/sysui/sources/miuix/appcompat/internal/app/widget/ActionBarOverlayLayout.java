package miuix.appcompat.internal.app.widget;

import android.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Scroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.app.GroupButtonsConfig;
import miuix.appcompat.app.GroupButtonsPanel;
import miuix.appcompat.app.IContentInsetState;
import miuix.appcompat.app.IMenuState;
import miuix.appcompat.app.OnStatusBarChangeListener;
import miuix.appcompat.app.floatingactivity.FloatingABOLayoutSpec;
import miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper;
import miuix.appcompat.internal.view.SearchActionModeImpl;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuDialogHelper;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.appcompat.internal.view.menu.context.ContextMenuBuilder;
import miuix.appcompat.internal.view.menu.context.ContextMenuPopupWindowHelper;
import miuix.appcompat.widget.Button;
import miuix.autodensity.AutoDensityConfig;
import miuix.autodensity.DebugUtil;
import miuix.autodensity.DensityConfigManager;
import miuix.container.ExtraPaddingObserver;
import miuix.container.ExtraPaddingPolicy;
import miuix.container.ExtraPaddingProcessor;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.WindowBaseInfo;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.DeviceHelper;
import miuix.smooth.SmoothCornerHelper;
import miuix.view.SearchActionMode;
import miuix.view.WindowInsetsState;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarOverlayLayout extends FrameLayout implements NestedScrollingParent3, IMenuState, ExtraPaddingProcessor, miuix.view.WindowInsetsController {

    @Nullable
    private ActionBar mActionBar;
    private ActionBarContainer mActionBarBottom;
    private ActionBarContextView mActionBarContextView;
    protected ActionBarContainer mActionBarTop;
    protected ActionBarView mActionBarView;
    private ActionMode mActionMode;
    private Rect mAnimateContentMarginBottomInsets;
    private boolean mAnimating;
    private Rect mBaseContentInsets;
    private Rect mBaseInnerInsets;
    private int mBottomExtraInset;
    private int mBottomMenuExtraInset;
    private int mBottomMenuMode;
    private int mBottomMenuModeConfig;
    private final int[] mBottomMenuVisibleHeight;
    private Window.Callback mCallback;
    private int mContainerViewHeight;
    private boolean mContentAutoFitSystemWindow;
    private Drawable mContentHeaderBackground;
    private IContentInsetState mContentInsetStateCallback;
    private Rect mContentInsets;
    private View mContentMask;
    private Rect mContentMaskInsets;
    protected View mContentView;
    private ContextMenuBuilder mContextMenu;
    private ContextMenuCallback mContextMenuCallback;
    private MenuDialogHelper mContextMenuHelper;
    private ContextMenuPopupWindowHelper mContextMenuPopupWindowHelper;
    protected final HashSet<View> mCoordinatedScrollViewSet;
    private boolean mCorrectNestedScrollMotionEventEnabled;
    private final Rect mCurrentContentInset;
    private int mDeviceType;
    private int mExtraHorizontalPadding;
    private boolean mExtraPaddingApplyToContentEnable;
    private boolean mExtraPaddingEnable;
    private boolean mExtraPaddingInitEnable;
    private List<ExtraPaddingObserver> mExtraPaddingObserver;

    @Nullable
    private ExtraPaddingPolicy mExtraPaddingPolicy;
    private FloatingABOLayoutSpec mFloatingWindowSize;
    private GroupButtonsPanel mGroupButtonPanelView;
    private int mImeInsetBottom;
    private View mInflateLayout;
    private Rect mInnerInsets;
    private int mInsetTopInMiuixFloating;
    private WindowInsetsController mInternalWindowInsetsController;
    private boolean mIsFloatingTheme;
    private boolean mIsFloatingWindow;
    private boolean mIsInSearchMode;
    private boolean mIsMiuixFloating;
    private Rect mLastBaseContentInsets;
    private final Rect mLastDispatchContentInset;
    private Rect mLastInnerInsets;
    private boolean mLayoutStable;
    private LifecycleOwner mLifecycleOwner;
    private boolean mNestedScrollingParentEnabled;
    private final int[] mOffsetInWindow;
    private View.OnLayoutChangeListener mOnContainerViewLayoutChangeListener;
    private OnStatusBarChangeListener mOnStatusBarChangeListener;
    private final Rect mOriginalInset;
    private boolean mOverlayMode;
    private Runnable mPostScroll;
    private View mPostScrollTarget;
    private final Scroller mPostScroller;
    private boolean mRequestFitSystemWindow;
    private boolean mRootSubDecor;
    private boolean mShouldExtraPaddingHorizontalNotifyChanged;
    protected ViewStub mSplitAnimContentMask;
    private boolean mSqueezeContentByIme;
    private final Rect mThemeCompatSystemInset;
    private int mTranslucentStatus;
    private WindowInsetsController mUserWindowInsetsController;
    private WindowInsetsController mWindowInsetsController;

    public class ActionModeCallbackWrapper implements ActionMode.Callback {
        private ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapper(ActionMode.Callback callback) {
            this.mWrapped = callback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.mWrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.mWrapped.onDestroyActionMode(actionMode);
            ActionBarView actionBarView = ActionBarOverlayLayout.this.mActionBarView;
            if (actionBarView != null && actionBarView.isSplitActionBar()) {
                ActionBarOverlayLayout.this.mActionBarView.makeMenuViewShowHideWithAnimation(true);
            }
            if (ActionBarOverlayLayout.this.getCallback() != null) {
                ActionBarOverlayLayout.this.getCallback().onActionModeFinished(actionMode);
            }
            ActionBarOverlayLayout.this.mActionMode = null;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.mWrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    public class ContentMaskAnimator implements Animator.AnimatorListener {
        private ObjectAnimator mHideAnimator;
        private View.OnClickListener mOnClickListener;
        private ObjectAnimator mShowAnimator;

        public Animator hide() {
            return this.mHideAnimator;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (ActionBarOverlayLayout.this.mContentMask == null || ActionBarOverlayLayout.this.mActionBarBottom == null || animator != this.mHideAnimator) {
                return;
            }
            ActionBarOverlayLayout.this.mActionBarBottom.bringToFront();
            ActionBarOverlayLayout.this.mContentMask.setOnClickListener(null);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (ActionBarOverlayLayout.this.mContentMask == null || ActionBarOverlayLayout.this.mActionBarBottom == null || ActionBarOverlayLayout.this.mContentMask.getAlpha() != 0.0f) {
                return;
            }
            ActionBarOverlayLayout.this.mActionBarBottom.bringToFront();
            ActionBarOverlayLayout.this.mContentMask.setOnClickListener(null);
            ActionBarOverlayLayout.this.mContentMask.setVisibility(8);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (ActionBarOverlayLayout.this.mContentMask == null || ActionBarOverlayLayout.this.mActionBarBottom == null || animator != this.mShowAnimator) {
                return;
            }
            ActionBarOverlayLayout.this.mContentMask.setVisibility(0);
            ActionBarOverlayLayout.this.mContentMask.bringToFront();
            ActionBarOverlayLayout.this.mActionBarBottom.bringToFront();
            ActionBarOverlayLayout.this.mContentMask.setOnClickListener(this.mOnClickListener);
        }

        public Animator show() {
            return this.mShowAnimator;
        }

        private ContentMaskAnimator(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(ActionBarOverlayLayout.this.mContentMask, "alpha", 0.0f, 1.0f);
            this.mShowAnimator = objectAnimatorOfFloat;
            objectAnimatorOfFloat.addListener(this);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(ActionBarOverlayLayout.this.mContentMask, "alpha", 1.0f, 0.0f);
            this.mHideAnimator = objectAnimatorOfFloat2;
            objectAnimatorOfFloat2.addListener(this);
            if (DeviceHelper.isFeatureWholeAnim()) {
                return;
            }
            this.mShowAnimator.setDuration(0L);
            this.mHideAnimator.setDuration(0L);
        }
    }

    public class ContextMenuCallback implements MenuBuilder.Callback, MenuPresenter.Callback {
        private MenuDialogHelper mSubMenuHelper;

        private ContextMenuCallback() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
            if (menuBuilder.getRootMenu() != menuBuilder) {
                onCloseSubMenu(menuBuilder);
            }
            if (z2) {
                if (ActionBarOverlayLayout.this.mCallback != null) {
                    ActionBarOverlayLayout.this.mCallback.onPanelClosed(6, menuBuilder);
                }
                ActionBarOverlayLayout.this.dismissContextMenu();
                MenuDialogHelper menuDialogHelper = this.mSubMenuHelper;
                if (menuDialogHelper != null) {
                    menuDialogHelper.dismiss();
                    this.mSubMenuHelper = null;
                }
            }
        }

        public void onCloseSubMenu(MenuBuilder menuBuilder) {
            if (ActionBarOverlayLayout.this.mCallback != null) {
                ActionBarOverlayLayout.this.mCallback.onPanelClosed(6, menuBuilder.getRootMenu());
            }
        }

        @Override // miuix.appcompat.internal.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (ActionBarOverlayLayout.this.mCallback != null) {
                return ActionBarOverlayLayout.this.mCallback.onMenuItemSelected(6, menuItem);
            }
            return false;
        }

        @Override // miuix.appcompat.internal.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            menuBuilder.setCallback(this);
            MenuDialogHelper menuDialogHelper = new MenuDialogHelper(menuBuilder);
            this.mSubMenuHelper = menuDialogHelper;
            menuDialogHelper.show(null);
            return true;
        }
    }

    public class SearchActionModeCallbackWrapper extends ActionModeCallbackWrapper implements SearchActionMode.Callback {
        public SearchActionModeCallbackWrapper(ActionMode.Callback callback) {
            super(callback);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.ActionModeCallbackWrapper, android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            ((SearchActionMode) actionMode).setAnimatedViewListener(new SearchActionMode.AnimatedViewListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.SearchActionModeCallbackWrapper.1
                @Override // miuix.view.SearchActionMode.AnimatedViewListener
                public void onInSearchMode(boolean z2) {
                    if (ActionBarOverlayLayout.this.mIsInSearchMode != z2) {
                        ActionBarOverlayLayout.this.mIsInSearchMode = z2;
                        if (ActionBarOverlayLayout.this.mActionBar != null) {
                            ((ActionBarImpl) ActionBarOverlayLayout.this.mActionBar).updateCoordinateOffsetView();
                        }
                    }
                }

                @Override // miuix.view.SearchActionMode.AnimatedViewListener
                public void onUpdateOffsetY(int i2) {
                    ActionBarContainer actionBarContainer = ActionBarOverlayLayout.this.mActionBarTop;
                    if (actionBarContainer != null) {
                        actionBarContainer.setCoordinatedOffsetYInSearchModeAnimation(i2);
                        ActionBarOverlayLayout.this.mActionBarTop.requestLayout();
                    }
                }
            });
            return super.onCreateActionMode(actionMode, menu);
        }
    }

    public static class WindowInsetsController {
        public boolean ignoreBottomInset;
        public boolean ignoreLeftInset;
        public boolean ignoreRightInset;
        public boolean ignoreTopInset;
        public boolean isFloatingMode;

        private WindowInsetsController() {
        }

        @NonNull
        public String toString() {
            return "isFloatingMode: " + this.isFloatingMode + ", ignoreLeftInset: " + this.ignoreLeftInset + ", ignoreTopInset: " + this.ignoreTopInset + ", ignoreRightInset: " + this.ignoreRightInset + " ,ignoreBottomInset: " + this.ignoreBottomInset;
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    private void adjustNestedScrollMotionEventCoordinate(View view) {
        if (!this.mOverlayMode || this.mCorrectNestedScrollMotionEventEnabled) {
            view.offsetTopAndBottom(-this.mOffsetInWindow[1]);
            return;
        }
        IContentInsetState iContentInsetState = this.mContentInsetStateCallback;
        if (iContentInsetState != null) {
            iContentInsetState.onDispatchNestedScrollOffset(this.mOffsetInWindow);
        }
    }

    private boolean applyInsetsByMargin(@Nullable View view, Rect rect, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6 = false;
        if (view == null) {
            return false;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (z2) {
            int i2 = layoutParams.leftMargin;
            int i3 = rect.left;
            if (i2 != i3) {
                layoutParams.leftMargin = i3;
                z6 = true;
            }
        }
        if (z3) {
            int i4 = layoutParams.topMargin;
            int i5 = rect.top;
            if (i4 != i5) {
                layoutParams.topMargin = i5;
                z6 = true;
            }
        }
        if (z4) {
            int i6 = layoutParams.rightMargin;
            int i7 = rect.right;
            if (i6 != i7) {
                layoutParams.rightMargin = i7;
                z6 = true;
            }
        }
        if (z5) {
            int i8 = layoutParams.bottomMargin;
            int i9 = rect.bottom;
            if (i8 != i9) {
                layoutParams.bottomMargin = i9;
                return true;
            }
        }
        return z6;
    }

    private void applyInternalWindowInsets(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (this.mUserWindowInsetsController != null) {
            return;
        }
        if (this.mInternalWindowInsetsController == null) {
            this.mInternalWindowInsetsController = new WindowInsetsController();
        }
        WindowInsetsController windowInsetsController = this.mInternalWindowInsetsController;
        windowInsetsController.isFloatingMode = z2;
        windowInsetsController.ignoreLeftInset = z3;
        windowInsetsController.ignoreTopInset = z4;
        windowInsetsController.ignoreRightInset = z5;
        windowInsetsController.ignoreBottomInset = z6;
        doApplyWindInsets(windowInsetsController);
    }

    private void computeFitSystemInsets(boolean z2, boolean z3, int i2, Rect rect, Rect rect2) {
        boolean zIsRootSubDecor = isRootSubDecor();
        rect2.set(rect);
        if ((!zIsRootSubDecor || z2) && !this.mContentAutoFitSystemWindow) {
            rect2.top = 0;
        }
        if (this.mIsFloatingWindow || z3) {
            rect2.bottom = 0;
        } else {
            int i3 = rect2.bottom;
            if (i3 != 0) {
                int i4 = i3 - i2;
                rect2.bottom = i4;
                if (i4 < 0) {
                    rect2.bottom = 0;
                }
            }
        }
        if (!this.mSqueezeContentByIme || i2 <= 0) {
            return;
        }
        rect2.bottom = this.mOriginalInset.bottom;
    }

    private ActionModeCallbackWrapper createActionModeCallbackWrapper(ActionMode.Callback callback) {
        return callback instanceof SearchActionMode.Callback ? new SearchActionModeCallbackWrapper(callback) : new ActionModeCallbackWrapper(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissContextMenu() {
        MenuDialogHelper menuDialogHelper = this.mContextMenuHelper;
        if (menuDialogHelper != null) {
            menuDialogHelper.dismiss();
            this.mContextMenuHelper = null;
            this.mContextMenu = null;
        }
    }

    private void dispatchContentInset(Rect rect) {
        dispatchContentInset(rect, false);
    }

    private void dispatchInsetsIgnoreVisibility(ViewGroup viewGroup, boolean z2) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            KeyEvent.Callback childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof WindowInsetsState) {
                ((WindowInsetsState) childAt).setInsetsIgnoringVisibility(z2);
            }
            if (childAt instanceof ViewGroup) {
                dispatchInsetsIgnoreVisibility((ViewGroup) childAt, z2);
            }
        }
    }

    private void doApplyWindInsets(WindowInsetsController windowInsetsController) {
        boolean z2;
        if (this.mWindowInsetsController == null) {
            this.mWindowInsetsController = new WindowInsetsController();
        }
        WindowInsetsController windowInsetsController2 = this.mWindowInsetsController;
        boolean z3 = windowInsetsController2.isFloatingMode;
        boolean z4 = windowInsetsController.isFloatingMode;
        boolean z5 = true;
        if (z3 != z4) {
            windowInsetsController2.isFloatingMode = z4;
            z2 = true;
        } else {
            z2 = false;
        }
        boolean z6 = windowInsetsController2.ignoreLeftInset;
        boolean z7 = windowInsetsController.ignoreLeftInset;
        if (z6 != z7) {
            windowInsetsController2.ignoreLeftInset = z7;
            z2 = true;
        }
        boolean z8 = windowInsetsController2.ignoreTopInset;
        boolean z9 = windowInsetsController.ignoreTopInset;
        if (z8 != z9) {
            windowInsetsController2.ignoreTopInset = z9;
            z2 = true;
        }
        boolean z10 = windowInsetsController2.ignoreRightInset;
        boolean z11 = windowInsetsController.ignoreRightInset;
        if (z10 != z11) {
            windowInsetsController2.ignoreRightInset = z11;
            z2 = true;
        }
        boolean z12 = windowInsetsController2.ignoreBottomInset;
        boolean z13 = windowInsetsController.ignoreBottomInset;
        if (z12 != z13) {
            windowInsetsController2.ignoreBottomInset = z13;
        } else {
            z5 = z2;
        }
        if (z5) {
            requestApplyInsets();
        }
    }

    private Activity getActivityContextFromView(View view) {
        Context context = ((ViewGroup) view.getRootView()).getChildAt(0).getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    private View getAdjustView(View view) {
        return (this.mCoordinatedScrollViewSet.isEmpty() || !this.mCoordinatedScrollViewSet.contains(view)) ? this.mContentView : view;
    }

    @RequiresApi(api = 28)
    private Insets getDisplayCoutInsets() {
        DisplayCutout cutout;
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this);
        if (rootWindowInsets == null) {
            return null;
        }
        DisplayCutoutCompat displayCutout = rootWindowInsets.getDisplayCutout();
        if (displayCutout != null) {
            return Insets.of(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom());
        }
        Activity activityContextFromView = getActivityContextFromView(this);
        if (activityContextFromView == null || (cutout = activityContextFromView.getWindowManager().getDefaultDisplay().getCutout()) == null) {
            return null;
        }
        return Insets.of(cutout.getSafeInsetLeft(), cutout.getSafeInsetTop(), cutout.getSafeInsetRight(), cutout.getSafeInsetBottom());
    }

    private boolean internalShowContextMenu(View view, float f2, float f3) {
        ContextMenuBuilder contextMenuBuilder = this.mContextMenu;
        if (contextMenuBuilder == null) {
            ContextMenuBuilder contextMenuBuilder2 = new ContextMenuBuilder(getContext());
            this.mContextMenu = contextMenuBuilder2;
            contextMenuBuilder2.setCallback(this.mContextMenuCallback);
        } else {
            contextMenuBuilder.clear();
        }
        ContextMenuPopupWindowHelper contextMenuPopupWindowHelperShow = this.mContextMenu.show(view, view.getWindowToken(), f2, f3);
        this.mContextMenuPopupWindowHelper = contextMenuPopupWindowHelperShow;
        if (contextMenuPopupWindowHelperShow == null) {
            return super.showContextMenuForChild(view);
        }
        contextMenuPopupWindowHelperShow.setPresenterCallback(this.mContextMenuCallback);
        return true;
    }

    private boolean isBackPressed(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1;
    }

    @RequiresApi(api = 28)
    private boolean isCutoutToLeftEdge(Insets insets) {
        return insets != null && insets.left > 0;
    }

    @RequiresApi(api = 28)
    private boolean isCutoutToRightEdge(Insets insets) {
        return insets != null && insets.right > 0;
    }

    private boolean isLayoutHideNavigation() {
        return MiuixUIUtils.isLayoutHideNavigation(this);
    }

    private boolean isNavigationBarToLeftEdge(WindowInsetsCompat windowInsetsCompat, boolean z2) {
        return (z2 ? windowInsetsCompat.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.captionBar() | WindowInsetsCompat.Type.navigationBars()) : windowInsetsCompat.getInsets(WindowInsetsCompat.Type.captionBar() | WindowInsetsCompat.Type.navigationBars())).left > 0;
    }

    private boolean isNavigationBarToRightEdge(WindowInsetsCompat windowInsetsCompat, boolean z2) {
        return (z2 ? windowInsetsCompat.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.captionBar() | WindowInsetsCompat.Type.navigationBars()) : windowInsetsCompat.getInsets(WindowInsetsCompat.Type.captionBar() | WindowInsetsCompat.Type.navigationBars())).right > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$0() {
        if (isAttachedToWindow()) {
            ActionBarContextView actionBarContextView = this.mActionBarContextView;
            if (actionBarContextView != null) {
                actionBarContextView.refreshBottomMenu();
            }
            ActionBarView actionBarView = this.mActionBarView;
            if (actionBarView != null) {
                actionBarView.refreshBottomMenu();
            }
            if (this.mContextMenu != null) {
                LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
                if (lifecycleOwner != null ? lifecycleOwner.getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED) : true) {
                    return;
                }
                this.mContextMenu.close();
            }
        }
    }

    private void notifyContentInset(boolean z2) {
        boolean z3 = this.mOverlayMode;
        if (z3 || z2) {
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null && z3) {
                ((ActionBarImpl) actionBar).updateContentInsetForNestedObserver(this.mLastDispatchContentInset);
            }
            IContentInsetState iContentInsetState = this.mContentInsetStateCallback;
            if (iContentInsetState != null) {
                iContentInsetState.onContentInsetChanged(this.mLastDispatchContentInset);
            }
        }
    }

    private void pullChildren() {
        if (this.mContentView == null) {
            this.mContentView = findViewById(R.id.content);
            ActionBarContainer actionBarContainer = (ActionBarContainer) findViewById(miuix.appcompat.R.id.action_bar_container);
            this.mActionBarTop = actionBarContainer;
            boolean z2 = false;
            if (this.mIsFloatingTheme && this.mIsFloatingWindow && actionBarContainer != null && !AttributeResolver.resolveBoolean(getContext(), miuix.appcompat.R.attr.windowActionBar, false)) {
                this.mActionBarTop.setVisibility(8);
                this.mActionBarTop = null;
            }
            ActionBarContainer actionBarContainer2 = this.mActionBarTop;
            if (actionBarContainer2 != null) {
                actionBarContainer2.setOverlayMode(this.mOverlayMode);
                ActionBarView actionBarView = (ActionBarView) this.mActionBarTop.findViewById(miuix.appcompat.R.id.action_bar);
                this.mActionBarView = actionBarView;
                actionBarView.setBottomMenuMode(this.mBottomMenuMode);
                if (this.mIsFloatingTheme && this.mIsFloatingWindow) {
                    z2 = true;
                }
                this.mIsMiuixFloating = z2;
                if (z2) {
                    this.mInsetTopInMiuixFloating = getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_floating_window_top_offset);
                }
                this.mActionBarTop.setMiuixFloatingOnInit(this.mIsMiuixFloating);
            }
        }
    }

    private void updateExtraPaddingHorizontal(@NonNull Context context, @NonNull ExtraPaddingPolicy extraPaddingPolicy, int i2, int i3) {
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
        extraPaddingPolicy.onContainerSizeChanged(point.x, point.y, i4, i3, f2, this.mIsMiuixFloating);
        int extraPaddingDp = extraPaddingPolicy.isEnable() ? (int) (extraPaddingPolicy.getExtraPaddingDp() * f2) : 0;
        if (extraPaddingDp != this.mExtraHorizontalPadding) {
            this.mExtraHorizontalPadding = extraPaddingDp;
            this.mShouldExtraPaddingHorizontalNotifyChanged = true;
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void addExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        if (this.mExtraPaddingObserver == null) {
            this.mExtraPaddingObserver = new CopyOnWriteArrayList();
        }
        if (this.mExtraPaddingObserver.contains(extraPaddingObserver)) {
            return;
        }
        this.mExtraPaddingObserver.add(extraPaddingObserver);
        extraPaddingObserver.setExtraHorizontalPadding(this.mExtraHorizontalPadding);
    }

    public void addGroupButtons(GroupButtonsConfig groupButtonsConfig, final boolean z2) {
        Context context = getContext();
        if (groupButtonsConfig == null || context == null) {
            return;
        }
        if (groupButtonsConfig.getOrientation() != 0) {
            FrameLayout.inflate(context, miuix.appcompat.R.layout.miuix_appcompat_group_buttons_layout, this);
        } else {
            FrameLayout.inflate(context, miuix.appcompat.R.layout.miuix_appcompat_group_buttons_horizontal_layout, this);
        }
        GroupButtonsPanel groupButtonsPanel = (GroupButtonsPanel) findViewById(miuix.appcompat.R.id.group_buttons_root_layout);
        this.mGroupButtonPanelView = groupButtonsPanel;
        if (groupButtonsPanel != null) {
            if (this.mOnContainerViewLayoutChangeListener == null) {
                View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.1
                    @Override // android.view.View.OnLayoutChangeListener
                    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                        int iMax;
                        int i10 = 0;
                        if (ActionBarOverlayLayout.this.mGroupButtonPanelView.isAllChildrenInvisible()) {
                            ActionBarOverlayLayout.this.mGroupButtonPanelView.setVisibility(4);
                        } else {
                            ActionBarOverlayLayout.this.mGroupButtonPanelView.setVisibility(0);
                            i10 = i5 - i3;
                        }
                        if (ActionBarOverlayLayout.this.mContainerViewHeight != i10) {
                            ActionBarOverlayLayout.this.mContainerViewHeight = i10;
                            if (!z2 || ActionBarOverlayLayout.this.mCurrentContentInset.bottom == (iMax = Math.max(ActionBarOverlayLayout.this.mCurrentContentInset.bottom, ActionBarOverlayLayout.this.mContainerViewHeight))) {
                                return;
                            }
                            ActionBarOverlayLayout.this.mCurrentContentInset.bottom = iMax;
                            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
                            actionBarOverlayLayout.dispatchContentInset(actionBarOverlayLayout.mCurrentContentInset, true);
                        }
                    }
                };
                this.mOnContainerViewLayoutChangeListener = onLayoutChangeListener;
                this.mGroupButtonPanelView.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            Button button = (Button) findViewById(miuix.appcompat.R.id.group_primary_button);
            Button button2 = (Button) findViewById(miuix.appcompat.R.id.group_secondary_button);
            Button button3 = (Button) findViewById(miuix.appcompat.R.id.group_tertiary_button);
            groupButtonsConfig.initButton(0, button);
            groupButtonsConfig.initButton(1, button2);
            groupButtonsConfig.initButton(2, button3);
        }
    }

    public void animateContentMarginBottomByBottomMenu(int i2) {
        if (this.mAnimateContentMarginBottomInsets == null) {
            this.mAnimateContentMarginBottomInsets = new Rect();
        }
        Rect rect = this.mAnimateContentMarginBottomInsets;
        Rect rect2 = this.mContentInsets;
        rect.top = rect2.top;
        rect.bottom = i2;
        rect.right = rect2.right;
        rect.left = rect2.left;
        applyInsetsByMargin(this.mContentView, rect, true, true, true, true);
        this.mContentView.requestLayout();
    }

    @Override // miuix.view.WindowInsetsController
    public void applyWindowInsets(boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (this.mUserWindowInsetsController == null) {
            this.mUserWindowInsetsController = new WindowInsetsController();
        }
        WindowInsetsController windowInsetsController = this.mUserWindowInsetsController;
        windowInsetsController.isFloatingMode = z2;
        windowInsetsController.ignoreLeftInset = z3;
        windowInsetsController.ignoreTopInset = z4;
        windowInsetsController.ignoreRightInset = z5;
        windowInsetsController.ignoreBottomInset = z6;
        doApplyWindInsets(windowInsetsController);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Drawable drawable;
        if (this.mContentAutoFitSystemWindow && (drawable = this.mContentHeaderBackground) != null) {
            drawable.setBounds(0, 0, getRight() - getLeft(), this.mBaseContentInsets.top);
            this.mContentHeaderBackground.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        if (isBackPressed(keyEvent)) {
            if (this.mActionMode != null) {
                ActionBarContextView actionBarContextView = this.mActionBarContextView;
                if (actionBarContextView != null && actionBarContextView.hideOverflowMenu()) {
                    return true;
                }
                this.mActionMode.finish();
                this.mActionMode = null;
                return true;
            }
            ActionBarView actionBarView = this.mActionBarView;
            if (actionBarView != null && actionBarView.hideOverflowMenu()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        int i2;
        boolean zApplyInsetsByMargin;
        boolean zApplyInsetsByMargin2;
        WindowInsetsController windowInsetsController;
        WindowInsetsController windowInsetsController2;
        Insets insets;
        Insets insets2;
        dispatchInsetsIgnoreVisibility(this, this.mLayoutStable);
        boolean zIsLayoutHideNavigation = isLayoutHideNavigation();
        boolean zIsTranslucentStatus = isTranslucentStatus();
        this.mOriginalInset.set(rect);
        this.mThemeCompatSystemInset.set(rect);
        this.mBaseInnerInsets.set(rect);
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(this);
        if (rootWindowInsets != null) {
            if (this.mLayoutStable) {
                insets = rootWindowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars());
                insets2 = rootWindowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            } else {
                insets = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
                insets2 = rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            }
            i2 = insets.bottom;
            int i3 = rootWindowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            this.mImeInsetBottom = i3;
            Rect rect2 = this.mThemeCompatSystemInset;
            rect2.left = insets2.left;
            rect2.right = insets2.right;
            rect2.bottom = i2;
            if (this.mSqueezeContentByIme && i3 > 0) {
                rect2.bottom = 0;
            }
        } else {
            i2 = 0;
        }
        if (!zIsLayoutHideNavigation) {
            this.mThemeCompatSystemInset.bottom = 0;
            if (isNavigationBarToLeftEdge(rootWindowInsets, this.mLayoutStable)) {
                this.mThemeCompatSystemInset.left = 0;
            }
            if (isNavigationBarToRightEdge(rootWindowInsets, this.mLayoutStable)) {
                this.mThemeCompatSystemInset.right = 0;
            }
        }
        OnStatusBarChangeListener onStatusBarChangeListener = this.mOnStatusBarChangeListener;
        if (onStatusBarChangeListener != null) {
            onStatusBarChangeListener.onStatusBarHeightChange(rect.top);
        }
        if (this.mIsMiuixFloating || ((windowInsetsController2 = this.mWindowInsetsController) != null && windowInsetsController2.isFloatingMode)) {
            Rect rect3 = this.mBaseInnerInsets;
            rect3.top = this.mInsetTopInMiuixFloating;
            rect3.left = 0;
            rect3.right = 0;
            Rect rect4 = this.mThemeCompatSystemInset;
            rect4.top = this.mBaseContentInsets.top;
            rect4.bottom = 0;
            rect4.left = 0;
            rect4.right = 0;
        }
        if (MiuixUIUtils.isHorizontalDirectionEdge2Edge(getContext())) {
            Rect rect5 = this.mBaseInnerInsets;
            rect5.left = 0;
            rect5.right = 0;
            Insets displayCoutInsets = getDisplayCoutInsets();
            if (isCutoutToLeftEdge(displayCoutInsets)) {
                this.mThemeCompatSystemInset.left = 0;
            }
            if (isCutoutToRightEdge(displayCoutInsets)) {
                this.mThemeCompatSystemInset.right = 0;
            }
        }
        if (this.mOverlayMode) {
            updateCurrentContentInsetInOverlayMode();
        }
        if (!isRootSubDecor() && (!zIsLayoutHideNavigation || this.mBaseInnerInsets.bottom != i2)) {
            this.mBaseInnerInsets.bottom = 0;
        }
        WindowInsetsController windowInsetsController3 = this.mWindowInsetsController;
        if (windowInsetsController3 != null && !windowInsetsController3.isFloatingMode) {
            if (windowInsetsController3.ignoreLeftInset) {
                this.mBaseInnerInsets.left = 0;
                this.mThemeCompatSystemInset.left = 0;
            }
            if (windowInsetsController3.ignoreTopInset) {
                this.mBaseInnerInsets.top = 0;
                this.mThemeCompatSystemInset.top = 0;
            }
            if (windowInsetsController3.ignoreRightInset) {
                this.mBaseInnerInsets.right = 0;
                this.mThemeCompatSystemInset.right = 0;
            }
            if (windowInsetsController3.ignoreBottomInset) {
                this.mBaseInnerInsets.bottom = 0;
                this.mThemeCompatSystemInset.bottom = 0;
            }
        }
        computeFitSystemInsets(zIsTranslucentStatus, zIsLayoutHideNavigation, this.mImeInsetBottom, this.mBaseInnerInsets, this.mBaseContentInsets);
        ActionBarContainer actionBarContainer = this.mActionBarTop;
        if (actionBarContainer != null) {
            if (zIsTranslucentStatus) {
                actionBarContainer.setPendingInsets(this.mBaseInnerInsets);
            }
            ActionMode actionMode = this.mActionMode;
            if (actionMode instanceof SearchActionModeImpl) {
                ((SearchActionModeImpl) actionMode).setPendingInsets(this.mBaseInnerInsets);
            }
            zApplyInsetsByMargin = applyInsetsByMargin(this.mActionBarTop, this.mThemeCompatSystemInset, true, isRootSubDecor() && !zIsTranslucentStatus, true, false);
        } else {
            zApplyInsetsByMargin = false;
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setPendingInset(this.mThemeCompatSystemInset);
        }
        ActionBarContainer actionBarContainer2 = this.mActionBarBottom;
        if (actionBarContainer2 != null) {
            actionBarContainer2.setPendingInsets(this.mThemeCompatSystemInset);
            this.mContentMaskInsets.set(this.mBaseInnerInsets);
            Rect rect6 = new Rect();
            rect6.set(this.mBaseContentInsets);
            if (this.mIsFloatingWindow || ((windowInsetsController = this.mWindowInsetsController) != null && windowInsetsController.isFloatingMode)) {
                rect6.bottom = 0;
            }
            if (this.mSqueezeContentByIme) {
                Rect rect7 = new Rect(this.mThemeCompatSystemInset);
                rect7.bottom = this.mBaseContentInsets.bottom;
                zApplyInsetsByMargin2 = applyInsetsByMargin(this.mActionBarBottom, rect7, true, false, true, true);
            } else {
                zApplyInsetsByMargin2 = applyInsetsByMargin(this.mActionBarBottom, this.mThemeCompatSystemInset, true, false, true, false);
            }
            zApplyInsetsByMargin |= zApplyInsetsByMargin2;
        }
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setPendingInset(this.mThemeCompatSystemInset);
        }
        if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            zApplyInsetsByMargin = true;
        }
        if (zApplyInsetsByMargin) {
            requestLayout();
        }
        return isRootSubDecor() && !(this.mActionBarTop == null && this.mActionBarBottom == null);
    }

    public ActionBar getActionBar() {
        return this.mActionBar;
    }

    public ActionBarView getActionBarView() {
        return this.mActionBarView;
    }

    public Rect getBaseInnerInsets() {
        return this.mBaseInnerInsets;
    }

    public int getBottomInset() {
        ActionBarContainer actionBarContainer = this.mActionBarBottom;
        if (actionBarContainer != null) {
            return actionBarContainer.getInsetHeight();
        }
        return 0;
    }

    public int getBottomMenuCustomViewTranslationY() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            return actionBarView.getBottomMenuCustomViewOffset();
        }
        return 0;
    }

    public int getBottomMenuMode() {
        return this.mBottomMenuMode;
    }

    public Window.Callback getCallback() {
        return this.mCallback;
    }

    public Rect getContentInset() {
        return this.mCurrentContentInset;
    }

    public View getContentMask() {
        return this.mContentMask;
    }

    public ContentMaskAnimator getContentMaskAnimator(View.OnClickListener onClickListener) {
        return new ContentMaskAnimator(onClickListener);
    }

    public View getContentView() {
        return this.mContentView;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    @Nullable
    public ExtraPaddingPolicy getExtraPaddingPolicy() {
        return this.mExtraPaddingPolicy;
    }

    public Rect getInnerInsets() {
        return this.mInnerInsets;
    }

    public void hideBottomMenu(boolean z2) {
        if (this.mActionBarView != null) {
            setBottomMenuExtraInset(0);
            if (z2) {
                this.mActionBarView.makeMenuViewShowHideWithAnimation(false);
            } else {
                this.mActionBarView.makeMenuViewShowHide(false);
            }
        }
    }

    public void hideBottomMenuCustomView() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.hideBottomMenuCustomView();
        }
    }

    public void hideContentMask() {
        if (this.mSplitAnimContentMask != null) {
            this.mInflateLayout.setVisibility(8);
            getContentView().setVisibility(0);
        }
    }

    public boolean isBottomAnimating() {
        return this.mAnimating;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public boolean isExtraHorizontalPaddingEnable() {
        ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
        if (extraPaddingPolicy != null) {
            return extraPaddingPolicy.isEnable();
        }
        return false;
    }

    public boolean isExtraPaddingApplyToContentEnable() {
        return this.mExtraPaddingApplyToContentEnable;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public boolean isRootSubDecor() {
        return this.mRootSubDecor;
    }

    public boolean isTranslucentStatus() {
        Context context = getContext();
        if (MiuixUIUtils.isTargetSdkVersionAboveV(context) || MiuixUIUtils.isDisplayCutoutModeAlways(context)) {
            return true;
        }
        int windowSystemUiVisibility = getWindowSystemUiVisibility();
        boolean z2 = (windowSystemUiVisibility & 256) != 0;
        boolean z3 = (windowSystemUiVisibility & 1024) != 0;
        boolean z4 = this.mTranslucentStatus != 0;
        return this.mIsFloatingTheme ? z3 || z4 : (z2 && z3) || z4;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets windowInsetsOnApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        return (windowInsetsOnApplyWindowInsets.isConsumed() || !isRootSubDecor()) ? windowInsetsOnApplyWindowInsets : windowInsets.consumeDisplayCutout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestFitSystemWindows();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Context context = getContext();
        DebugUtil.printDensityLog("->processActionBarOverlayLayout ConfigurationChanged newConfig.densityDpi " + configuration.densityDpi);
        DensityConfigManager.getInstance().tryUpdateConfig(context, configuration);
        AutoDensityConfig.updateDensity(context);
        this.mFloatingWindowSize.onConfigurationChanged();
        post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f6071a.lambda$onConfigurationChanged$0();
            }
        });
        ContextMenuBuilder contextMenuBuilder = this.mContextMenu;
        if (contextMenuBuilder == null || !contextMenuBuilder.isContextMenuPopupWindowShowing()) {
            return;
        }
        this.mContextMenu.refreshContextMenuPopupWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        View.OnLayoutChangeListener onLayoutChangeListener;
        super.onDetachedFromWindow();
        setContentInsetStateCallback(null);
        List<ExtraPaddingObserver> list = this.mExtraPaddingObserver;
        if (list != null) {
            list.clear();
        }
        GroupButtonsPanel groupButtonsPanel = this.mGroupButtonPanelView;
        if (groupButtonsPanel == null || (onLayoutChangeListener = this.mOnContainerViewLayoutChangeListener) == null) {
            return;
        }
        groupButtonsPanel.removeOnLayoutChangeListener(onLayoutChangeListener);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        pullChildren();
    }

    public void onFloatingModeChanged(boolean z2) {
        if (this.mIsMiuixFloating != (this.mIsFloatingTheme && z2)) {
            this.mIsFloatingWindow = z2;
            this.mIsMiuixFloating = z2;
            if (z2) {
                this.mInsetTopInMiuixFloating = getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_floating_window_top_offset);
            }
            this.mFloatingWindowSize.onFloatingModeChanged(this.mIsMiuixFloating);
            ActionBar actionBar = this.mActionBar;
            if (actionBar != null) {
                ((ActionBarImpl) actionBar).onFloatingModeChanged(this.mIsMiuixFloating);
            }
            requestFitSystemWindows();
            requestLayout();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        WindowInsetsCompat rootWindowInsets;
        super.onLayout(z2, i2, i3, i4, i5);
        if (this.mOverlayMode) {
            updateCurrentContentInsetInOverlayMode();
        }
        ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
        if (extraPaddingPolicy != null && extraPaddingPolicy.isEnable()) {
            if (this.mShouldExtraPaddingHorizontalNotifyChanged && this.mExtraPaddingObserver != null) {
                this.mShouldExtraPaddingHorizontalNotifyChanged = false;
                for (int i6 = 0; i6 < this.mExtraPaddingObserver.size(); i6++) {
                    this.mExtraPaddingObserver.get(i6).onExtraPaddingChanged(this.mExtraHorizontalPadding);
                }
            }
            if (this.mExtraPaddingApplyToContentEnable) {
                this.mExtraPaddingPolicy.applyExtraPadding(this.mContentView);
            }
        }
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null && !this.mIsInSearchMode) {
            ((ActionBarImpl) actionBar).updateCoordinateOffsetView();
        }
        Context context = getContext();
        if (MiuixUIUtils.isHorizontalDirectionEdge2Edge(getContext()) && this.mUserWindowInsetsController == null && (rootWindowInsets = ViewCompat.getRootWindowInsets(this)) != null) {
            Insets insetsIgnoringVisibility = this.mLayoutStable ? rootWindowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()) : rootWindowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            int i7 = EnvStateManager.getScreenSize(context).x;
            if (i7 != -1) {
                int[] iArr = new int[2];
                getLocationOnScreen(iArr);
                int i8 = iArr[0];
                applyInternalWindowInsets(false, i8 > insetsIgnoringVisibility.left, false, i7 - (getWidth() + i8) > insetsIgnoringVisibility.right, false);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x01bc  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r19, int r20) {
        /*
            Method dump skipped, instruction units count: 581
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.onMeasure(int, int):void");
    }

    @Override // miuix.appcompat.app.IMenuState
    public void onMenuStateChanged(int i2, int i3) {
        int i4;
        int[] iArr = this.mBottomMenuVisibleHeight;
        iArr[i3] = i2;
        int iMax = Math.max(iArr[0], iArr[1]);
        if (!this.mOverlayMode) {
            animateContentMarginBottomByBottomMenu(iMax);
            return;
        }
        if (isLayoutHideNavigation() && iMax <= (i4 = this.mThemeCompatSystemInset.bottom)) {
            iMax = i4;
        }
        this.mCurrentContentInset.bottom = Math.max(Math.max(iMax, this.mBottomMenuExtraInset), this.mBottomExtraInset);
        dispatchContentInset(this.mCurrentContentInset);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(@NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        ActionBar actionBar;
        if (!this.mPostScroller.isFinished()) {
            this.mPostScroller.forceFinished(true);
        }
        removeCallbacks(this.mPostScroll);
        View adjustView = getAdjustView(view);
        if (adjustView == null) {
            return;
        }
        int[] iArr2 = this.mOffsetInWindow;
        iArr2[1] = 0;
        ActionBarContainer actionBarContainer = this.mActionBarTop;
        if (actionBarContainer != null && !this.mIsInSearchMode) {
            actionBarContainer.onNestedPreScroll(view, i2, i3, iArr, i4, iArr2);
        }
        if (i3 > 0) {
            int i5 = iArr[1];
            if (i3 - i5 > 0 && (actionBar = this.mActionBar) != null && (actionBar instanceof ActionBarImpl)) {
                int i6 = i3 - i5;
                int topOffsetForCoordinateView = ((ActionBarImpl) actionBar).getTopOffsetForCoordinateView(view);
                if (topOffsetForCoordinateView != -1) {
                    iArr[1] = iArr[1] + ((ActionBarImpl) this.mActionBar).updateTopOffsetOnNestedPreScroll(view, Math.max(0, topOffsetForCoordinateView - i6));
                }
            }
        }
        adjustNestedScrollMotionEventCoordinate(adjustView);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002b  */
    @Override // androidx.core.view.NestedScrollingParent3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onNestedScroll(@androidx.annotation.NonNull android.view.View r12, int r13, int r14, int r15, int r16, int r17, @androidx.annotation.NonNull int[] r18) {
        /*
            r11 = this;
            r0 = r11
            android.view.View r10 = r11.getAdjustView(r12)
            if (r10 != 0) goto L8
            return
        L8:
            r1 = 1
            if (r16 >= 0) goto L2b
            r2 = r18[r1]
            int r3 = r16 - r2
            if (r3 > 0) goto L2b
            miuix.appcompat.app.ActionBar r3 = r0.mActionBar
            if (r3 == 0) goto L2b
            boolean r4 = r3 instanceof miuix.appcompat.internal.app.widget.ActionBarImpl
            if (r4 == 0) goto L2b
            int r2 = r16 - r2
            miuix.appcompat.internal.app.widget.ActionBarImpl r3 = (miuix.appcompat.internal.app.widget.ActionBarImpl) r3
            r4 = r12
            int r2 = r3.updateTopOffsetOnNestedScroll(r12, r2)
            int r3 = r16 - r2
            r5 = r18[r1]
            int r5 = r5 + r2
            r18[r1] = r5
            r6 = r3
            goto L2e
        L2b:
            r4 = r12
            r6 = r16
        L2e:
            int[] r9 = r0.mOffsetInWindow
            r2 = 0
            r9[r1] = r2
            miuix.appcompat.internal.app.widget.ActionBarContainer r1 = r0.mActionBarTop
            if (r1 == 0) goto L46
            boolean r2 = r0.mIsInSearchMode
            if (r2 != 0) goto L46
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r7 = r17
            r8 = r18
            r1.onNestedScroll(r2, r3, r4, r5, r6, r7, r8, r9)
        L46:
            r11.adjustNestedScrollMotionEventCoordinate(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.onNestedScroll(android.view.View, int, int, int, int, int, int[]):void");
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i2, int i3) {
        ActionBarContainer actionBarContainer;
        if (!this.mPostScroller.isFinished()) {
            this.mPostScroller.forceFinished(true);
        }
        if (getAdjustView(view2) == null || (actionBarContainer = this.mActionBarTop) == null) {
            return;
        }
        actionBarContainer.onNestedScrollAccepted(view, view2, i2, i3);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i2, int i3) {
        ActionBarContainer actionBarContainer;
        if (!this.mNestedScrollingParentEnabled) {
            return false;
        }
        if (!this.mPostScroller.isFinished()) {
            this.mPostScroller.forceFinished(true);
        }
        return (getAdjustView(view2) == null || (actionBarContainer = this.mActionBarTop) == null || !actionBarContainer.onStartNestedScroll(view, view2, i2, i3)) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(@NonNull View view, int i2) {
        ActionBarContainer actionBarContainer;
        ActionBar actionBar;
        if (getAdjustView(view) == null || (actionBarContainer = this.mActionBarTop) == null) {
            return;
        }
        actionBarContainer.onStopNestedScroll(view, i2);
        if (isInOverlayMode() && this.mActionBarView != null && (actionBar = this.mActionBar) != null && actionBar.isAdsorptionToNoOverlay() && this.mActionBarView.getExpandState() == 0) {
            this.mPostScrollTarget = view;
            if (!this.mPostScroller.isFinished()) {
                this.mPostScroller.forceFinished(true);
            }
            int topOffsetForCoordinateView = ((ActionBarImpl) this.mActionBar).getTopOffsetForCoordinateView(this.mPostScrollTarget);
            int collapsedHeight = this.mActionBarView.getCollapsedHeight() + this.mActionBarView.getTop();
            if (topOffsetForCoordinateView != collapsedHeight) {
                this.mPostScroller.startScroll(0, topOffsetForCoordinateView, 0, collapsedHeight - topOffsetForCoordinateView);
                postOnAnimation(this.mPostScroll);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        return this.mIsFloatingTheme;
    }

    public void registerCoordinatedScrollView(View view) {
        if (view != null) {
            this.mCoordinatedScrollViewSet.add(view);
        }
    }

    public void removeBottomMenuCustomView() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.removeBottomMenuCustomView();
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void removeExtraPaddingObserver(ExtraPaddingObserver extraPaddingObserver) {
        List<ExtraPaddingObserver> list = this.mExtraPaddingObserver;
        if (list != null) {
            list.remove(extraPaddingObserver);
        }
    }

    public void requestDispatchContentInset() {
        notifyContentInset(false);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestFitSystemWindows() {
        super.requestFitSystemWindows();
        this.mRequestFitSystemWindow = true;
    }

    public void setActionBar(ActionBar actionBar) {
        this.mActionBar = actionBar;
        if (actionBar != null) {
            ((ActionBarImpl) actionBar).setExtraPaddingPolicy(this.mExtraPaddingPolicy);
        }
    }

    public void setActionBarContextView(ActionBarContextView actionBarContextView) {
        this.mActionBarContextView = actionBarContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setActionBarView(this.mActionBarView);
            this.mActionBarContextView.setBottomMenuMode(this.mBottomMenuMode);
            this.mActionBarContextView.setPendingInset(this.mThemeCompatSystemInset);
        }
    }

    public void setAnimating(boolean z2) {
        this.mAnimating = z2;
    }

    public void setBottomExtraInset(int i2) {
        int i3;
        if (this.mBottomExtraInset != i2) {
            this.mBottomExtraInset = i2;
            int iMax = Math.max(getBottomInset(), this.mBottomMenuExtraInset);
            if (isLayoutHideNavigation() && iMax <= (i3 = this.mThemeCompatSystemInset.bottom)) {
                iMax = i3;
            }
            int iMax2 = Math.max(iMax, this.mBottomExtraInset);
            Rect rect = this.mCurrentContentInset;
            if (rect.bottom != iMax2) {
                rect.bottom = iMax2;
                dispatchContentInset(rect, true);
            }
        }
    }

    public void setBottomMenuCustomView(View view) {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setBottomMenuCustomView(view);
        }
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setBottomMenuCustomViewTranslationYWithPx(i2);
        }
    }

    public void setBottomMenuExtraInset(int i2) {
        this.mBottomMenuExtraInset = i2;
    }

    public void setBottomMenuMode(int i2) {
        if (this.mBottomMenuModeConfig != i2) {
            this.mBottomMenuModeConfig = i2;
            updateBottomMenuMode();
        }
    }

    public void setCallback(Window.Callback callback) {
        this.mCallback = callback;
    }

    public void setContentInsetStateCallback(IContentInsetState iContentInsetState) {
        this.mContentInsetStateCallback = iContentInsetState;
    }

    public void setContentMask(View view) {
        this.mContentMask = view;
        if (!DeviceHelper.isOled() || this.mContentMask == null) {
            return;
        }
        ResourcesCompat.getDrawable(getContext().getResources(), miuix.appcompat.R.drawable.miuix_appcompat_window_content_mask_oled, getContext().getTheme());
    }

    public void setContentView(View view) {
        this.mContentView = view;
    }

    public void setCorrectNestedScrollMotionEventEnabled(boolean z2) {
        this.mCorrectNestedScrollMotionEventEnabled = z2;
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingEnable(boolean z2) {
        if (this.mExtraPaddingEnable != z2) {
            this.mExtraPaddingEnable = z2;
            ExtraPaddingPolicy extraPaddingPolicy = this.mExtraPaddingPolicy;
            if (extraPaddingPolicy != null) {
                extraPaddingPolicy.setEnable(z2);
                requestLayout();
            }
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraHorizontalPaddingInitEnable(boolean z2) {
        if (this.mExtraPaddingInitEnable != z2) {
            this.mExtraPaddingInitEnable = z2;
        }
    }

    public void setExtraPaddingApplyToContentEnable(boolean z2) {
        if (this.mExtraPaddingApplyToContentEnable != z2) {
            this.mExtraPaddingApplyToContentEnable = z2;
            requestLayout();
        }
    }

    @Override // miuix.container.ExtraPaddingProcessor
    public void setExtraPaddingPolicy(ExtraPaddingPolicy extraPaddingPolicy) {
        if (this.mExtraPaddingPolicy != null || extraPaddingPolicy == null) {
            this.mExtraPaddingPolicy = extraPaddingPolicy;
            if (extraPaddingPolicy != null) {
                extraPaddingPolicy.setEnable(this.mExtraPaddingEnable);
            }
        } else {
            this.mExtraPaddingPolicy = extraPaddingPolicy;
            extraPaddingPolicy.setEnable(this.mExtraPaddingEnable);
            if (this.mExtraPaddingInitEnable) {
                updateExtraPaddingHorizontal(getContext(), this.mExtraPaddingPolicy, -1, -1);
                this.mShouldExtraPaddingHorizontalNotifyChanged = false;
                if (this.mExtraPaddingObserver != null) {
                    for (int i2 = 0; i2 < this.mExtraPaddingObserver.size(); i2++) {
                        this.mExtraPaddingObserver.get(i2).setExtraHorizontalPadding(this.mExtraHorizontalPadding);
                    }
                }
            }
        }
        ActionBar actionBar = this.mActionBar;
        if (actionBar != null) {
            ((ActionBarImpl) actionBar).setExtraPaddingPolicy(this.mExtraPaddingPolicy);
        }
        requestLayout();
    }

    public void setGroupButtonsPanelBackground(Drawable drawable) {
        GroupButtonsPanel groupButtonsPanel = this.mGroupButtonPanelView;
        if (groupButtonsPanel != null) {
            groupButtonsPanel.setBackground(drawable);
        }
    }

    public void setGroupButtonsPanelBackgroundColor(int i2) {
        GroupButtonsPanel groupButtonsPanel = this.mGroupButtonPanelView;
        if (groupButtonsPanel != null) {
            groupButtonsPanel.setBackgroundColor(i2);
        }
    }

    public void setGroupButtonsPanelBackgroundResource(int i2) {
        GroupButtonsPanel groupButtonsPanel = this.mGroupButtonPanelView;
        if (groupButtonsPanel != null) {
            groupButtonsPanel.setBackgroundResource(i2);
        }
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

    public void setNestedScrollingParentEnabled(boolean z2) {
        this.mNestedScrollingParentEnabled = z2;
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.mOnStatusBarChangeListener = onStatusBarChangeListener;
    }

    public void setOverlayMode(boolean z2) {
        this.mOverlayMode = z2;
        ActionBarContainer actionBarContainer = this.mActionBarTop;
        if (actionBarContainer != null) {
            actionBarContainer.setOverlayMode(z2);
        }
    }

    public void setRootSubDecor(boolean z2) {
        this.mRootSubDecor = z2;
    }

    public void setSplitActionBarView(ActionBarContainer actionBarContainer) {
        this.mActionBarBottom = actionBarContainer;
        actionBarContainer.setPendingInsets(this.mThemeCompatSystemInset);
    }

    public void setTranslucentStatus(int i2) {
        if (this.mTranslucentStatus != i2) {
            this.mTranslucentStatus = i2;
            requestFitSystemWindows();
        }
    }

    public void showBottomMenu(boolean z2) {
        if (this.mActionBarView != null) {
            setBottomMenuExtraInset(0);
            if (z2) {
                this.mActionBarView.makeMenuViewShowHideWithAnimation(true);
            } else {
                this.mActionBarView.makeMenuViewShowHide(true);
            }
        }
    }

    public void showBottomMenuCustomView() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.showBottomMenuCustomView();
        }
    }

    public void showContentMask(int i2) {
        if (this.mSplitAnimContentMask == null) {
            ViewStub viewStub = (ViewStub) findViewById(miuix.appcompat.R.id.split_anim_content_mask);
            this.mSplitAnimContentMask = viewStub;
            this.mInflateLayout = viewStub.inflate();
        }
        ImageView imageView = (ImageView) this.mInflateLayout.findViewById(miuix.appcompat.R.id.image_bg);
        getContext();
        int measuredWidth = getContentView().getMeasuredWidth();
        int measuredHeight = getContentView().getMeasuredHeight();
        if (measuredWidth > 0 && measuredHeight > 0) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            getContentView().draw(new Canvas(bitmapCreateBitmap));
            float f2 = i2;
            RenderEffect renderEffectCreateBlurEffect = RenderEffect.createBlurEffect(f2, f2, Shader.TileMode.CLAMP);
            imageView.setImageBitmap(bitmapCreateBitmap);
            imageView.setRenderEffect(renderEffectCreateBlurEffect);
        }
        this.mInflateLayout.setAlpha(1.0f);
        getContentView().setVisibility(4);
        this.mInflateLayout.setVisibility(0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view, float f2, float f3) {
        if (internalShowContextMenu(view, f2, f3)) {
            return true;
        }
        return getParent() != null && getParent().showContextMenuForChild(view, f2, f3);
    }

    @Override // android.view.View
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        ActionMode actionModeOnWindowStartingActionMode = null;
        if (actionBarContextView != null && actionBarContextView.isAnimating()) {
            return null;
        }
        ActionMode actionMode = this.mActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        this.mActionMode = null;
        if (getCallback() != null) {
            actionModeOnWindowStartingActionMode = getCallback().onWindowStartingActionMode(createActionModeCallbackWrapper(callback));
        }
        if (actionModeOnWindowStartingActionMode != null) {
            this.mActionMode = actionModeOnWindowStartingActionMode;
        }
        if (this.mActionMode != null && getCallback() != null) {
            getCallback().onActionModeStarted(this.mActionMode);
        }
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null && actionBarView.isSplitActionBar()) {
            ActionMenuView actionMenuView = this.mActionBarView.getActionMenuView();
            if (actionMenuView != null) {
                setBottomMenuExtraInset(actionMenuView.getCollapsedHeight());
            }
            this.mActionBarView.makeMenuViewShowHideWithAnimation(false);
        }
        if ((this.mActionMode instanceof SearchActionMode) && this.mOverlayMode) {
            updateCurrentContentInsetInOverlayMode();
        }
        return this.mActionMode;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return startActionMode(view, callback);
    }

    public void unregisterCoordinatedScrollView(View view) {
        if (view != null) {
            this.mCoordinatedScrollViewSet.remove(view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateBottomMenuMode() {
        /*
            r7 = this;
            int r0 = r7.mBottomMenuModeConfig
            android.content.Context r1 = r7.getContext()
            android.content.res.Resources r1 = r1.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.density
            r2 = 3
            r3 = 640(0x280, float:8.97E-43)
            r4 = 1065353216(0x3f800000, float:1.0)
            r5 = 2
            if (r0 != 0) goto L3c
            int r0 = r7.getMeasuredWidth()
            float r0 = (float) r0
            float r0 = r0 * r4
            float r0 = r0 / r1
            int r0 = (int) r0
            android.content.Context r6 = r7.getContext()
            android.graphics.Point r6 = miuix.core.util.EnvStateManager.getWindowSize(r6)
            int r6 = r6.x
            float r6 = (float) r6
            float r6 = r6 * r4
            float r6 = r6 / r1
            int r1 = (int) r6
            int r4 = r7.mDeviceType
            if (r4 != r5) goto L3a
            r4 = 410(0x19a, float:5.75E-43)
            if (r0 <= r4) goto L3a
            if (r1 <= r3) goto L3a
        L38:
            r0 = r2
            goto L54
        L3a:
            r0 = r5
            goto L54
        L3c:
            r6 = 1
            if (r0 != r6) goto L54
            android.content.Context r0 = r7.getContext()
            android.graphics.Point r0 = miuix.core.util.EnvStateManager.getWindowSize(r0)
            int r0 = r0.x
            float r0 = (float) r0
            float r0 = r0 * r4
            float r0 = r0 / r1
            int r0 = (int) r0
            int r1 = r7.mDeviceType
            if (r1 != r5) goto L3a
            if (r0 <= r3) goto L3a
            goto L38
        L54:
            int r1 = r7.mBottomMenuMode
            if (r0 == r1) goto L74
            r7.mBottomMenuMode = r0
            miuix.appcompat.internal.app.widget.ActionBarContextView r1 = r7.mActionBarContextView
            if (r1 == 0) goto L66
            r1.setBottomMenuMode(r0)
            miuix.appcompat.internal.app.widget.ActionBarContextView r0 = r7.mActionBarContextView
            r0.refreshBottomMenu()
        L66:
            miuix.appcompat.internal.app.widget.ActionBarView r0 = r7.mActionBarView
            if (r0 == 0) goto L74
            int r1 = r7.mBottomMenuMode
            r0.setBottomMenuMode(r1)
            miuix.appcompat.internal.app.widget.ActionBarView r7 = r7.mActionBarView
            r7.refreshBottomMenu()
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.updateBottomMenuMode():void");
    }

    public void updateCurrentContentInsetInOverlayMode() {
        int i2;
        int i3;
        ActionBarContainer actionBarContainer;
        this.mCurrentContentInset.set(this.mThemeCompatSystemInset);
        int iMax = 0;
        if (this.mActionBar != null && (actionBarContainer = this.mActionBarTop) != null && actionBarContainer.getVisibility() != 8 && this.mActionBarTop.getMeasuredHeight() > 0) {
            iMax = Math.max(0, (int) (((ActionBarImpl) this.mActionBar).getTopViewHeight() + this.mThemeCompatSystemInset.top + (this.mIsMiuixFloating ? this.mInsetTopInMiuixFloating : 0) + this.mActionBarTop.getTranslationY()));
        }
        int iMax2 = Math.max(Math.max(getBottomInset(), this.mBottomExtraInset), this.mBottomMenuExtraInset);
        if (!isTranslucentStatus() || iMax >= (i3 = this.mThemeCompatSystemInset.top)) {
            this.mCurrentContentInset.top = iMax;
        } else {
            this.mCurrentContentInset.top = i3;
        }
        if (!isLayoutHideNavigation() || iMax2 >= (i2 = this.mThemeCompatSystemInset.bottom)) {
            this.mCurrentContentInset.bottom = iMax2;
        } else {
            this.mCurrentContentInset.bottom = i2;
        }
        Rect rect = this.mCurrentContentInset;
        int i4 = rect.left;
        Rect rect2 = this.mThemeCompatSystemInset;
        int i5 = rect2.left;
        if (i4 < i5) {
            rect.left = i5;
        }
        int i6 = rect.right;
        int i7 = rect2.right;
        if (i6 < i7) {
            rect.right = i7;
        }
        dispatchContentInset(rect);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchContentInset(Rect rect, boolean z2) {
        if (this.mLastDispatchContentInset.equals(rect)) {
            return;
        }
        this.mLastDispatchContentInset.set(rect);
        notifyContentInset(z2);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mCoordinatedScrollViewSet = new HashSet<>();
        this.mLifecycleOwner = null;
        this.mIsInSearchMode = false;
        this.mRootSubDecor = true;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mContentMaskInsets = new Rect();
        this.mCurrentContentInset = new Rect();
        this.mLastDispatchContentInset = new Rect();
        this.mThemeCompatSystemInset = new Rect();
        this.mOriginalInset = new Rect();
        this.mBottomMenuVisibleHeight = new int[2];
        this.mAnimateContentMarginBottomInsets = null;
        this.mContextMenuCallback = new ContextMenuCallback();
        this.mIsFloatingTheme = false;
        this.mIsFloatingWindow = false;
        this.mImeInsetBottom = 0;
        this.mCorrectNestedScrollMotionEventEnabled = true;
        this.mSqueezeContentByIme = false;
        this.mLayoutStable = false;
        this.mNestedScrollingParentEnabled = true;
        this.mOffsetInWindow = new int[2];
        this.mPostScroll = new Runnable() { // from class: miuix.appcompat.internal.app.widget.ActionBarOverlayLayout.2
            @Override // java.lang.Runnable
            public void run() {
                if (ActionBarOverlayLayout.this.mPostScroller.computeScrollOffset()) {
                    if (ActionBarOverlayLayout.this.mActionBar != null && ActionBarOverlayLayout.this.mPostScrollTarget != null) {
                        ((ActionBarImpl) ActionBarOverlayLayout.this.mActionBar).updateTopOffsetOnPostScroll(ActionBarOverlayLayout.this.mPostScrollTarget, ActionBarOverlayLayout.this.mPostScroller.getCurrY());
                    }
                    if (ActionBarOverlayLayout.this.mPostScroller.isFinished()) {
                        return;
                    }
                    ActionBarOverlayLayout.this.postOnAnimation(this);
                }
            }
        };
        SmoothCornerHelper.init(context);
        this.mPostScroller = new Scroller(context);
        this.mFloatingWindowSize = new FloatingABOLayoutSpec(context, attributeSet);
        this.mDeviceType = miuix.os.DeviceHelper.detectType(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, miuix.appcompat.R.styleable.Window, i2, 0);
        this.mIsFloatingTheme = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.Window_isMiuixFloatingTheme, false);
        this.mIsFloatingWindow = BaseFloatingActivityHelper.isFloatingWindow(context);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.Window_contentAutoFitSystemWindow, false);
        this.mContentAutoFitSystemWindow = z2;
        if (z2) {
            this.mContentHeaderBackground = typedArrayObtainStyledAttributes.getDrawable(miuix.appcompat.R.styleable.Window_contentHeaderBackground);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.mBottomMenuModeConfig = AttributeResolver.resolveInt(context, miuix.appcompat.R.attr.bottomMenuMode, 0);
        this.mSqueezeContentByIme = AttributeResolver.resolveBoolean(context, miuix.appcompat.R.attr.squeezeContentByIme, false);
        this.mLayoutStable = AttributeResolver.resolveBoolean(context, miuix.appcompat.R.attr.layoutStable, false);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean showContextMenuForChild(View view) {
        ContextMenuBuilder contextMenuBuilder = this.mContextMenu;
        if (contextMenuBuilder == null) {
            ContextMenuBuilder contextMenuBuilder2 = new ContextMenuBuilder(getContext());
            this.mContextMenu = contextMenuBuilder2;
            contextMenuBuilder2.setCallback(this.mContextMenuCallback);
        } else {
            contextMenuBuilder.clear();
        }
        MenuDialogHelper menuDialogHelperShow = this.mContextMenu.show(view, view.getWindowToken());
        this.mContextMenuHelper = menuDialogHelperShow;
        if (menuDialogHelperShow != null) {
            menuDialogHelperShow.setPresenterCallback(this.mContextMenuCallback);
            return true;
        }
        return super.showContextMenuForChild(view);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
        if (!this.mPostScroller.isFinished()) {
            this.mPostScroller.forceFinished(true);
        }
        removeCallbacks(this.mPostScroll);
    }

    public ActionMode startActionMode(View view, ActionMode.Callback callback) {
        if (view instanceof ActionBarOverlayLayout) {
            ActionMode actionMode = this.mActionMode;
            if (actionMode != null) {
                actionMode.finish();
            }
            ActionMode actionModeStartActionMode = view.startActionMode(createActionModeCallbackWrapper(callback));
            this.mActionMode = actionModeStartActionMode;
            return actionModeStartActionMode;
        }
        return startActionMode(callback);
    }
}
