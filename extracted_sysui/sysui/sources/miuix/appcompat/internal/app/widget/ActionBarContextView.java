package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.IStateStyle;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.physics.DynamicAnimation;
import miuix.animation.physics.SpringAnimation;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.AbsActionBarView;
import miuix.appcompat.internal.app.widget.actionbar.CollapseTitle;
import miuix.appcompat.internal.app.widget.actionbar.ExpandTitle;
import miuix.appcompat.internal.view.ActionBarPolicy;
import miuix.appcompat.internal.view.EditActionModeImpl;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.action.ActionMenuItem;
import miuix.appcompat.internal.view.menu.action.ActionMenuPresenter;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.theme.Typography;
import miuix.view.ActionModeAnimationListener;

/* JADX INFO: loaded from: classes3.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class ActionBarContextView extends AbsActionBarView implements ActionModeView {
    private static final int ANIMATE_IDLE = 0;
    private static final int ANIMATE_IN = 1;
    private static final int ANIMATE_OUT = 2;
    private static final float DAMPING = 0.9f;
    private static final int DELAY_DURATION_50 = 50;
    private static final float STIFFNESS_HIGH = 986.96f;
    private static final float STIFFNESS_LOW = 322.27f;
    private static final int TYPE_NON_TOUCH = 1;
    private static final int TYPE_TOUCH = 0;
    private ActionBarView mActionBarView;
    private WeakReference<ActionMode> mActionMode;
    private Drawable mActionModeBackground;
    private boolean mAnimateStart;
    private boolean mAnimateToVisible;
    private List<ActionModeAnimationListener> mAnimationListeners;
    private int mAnimationMode;
    private float mAnimationProgress;
    private boolean mBackgroundViewApplyBlur;
    private Button mButton1;
    private ActionMenuItem mButton1MenuItem;
    private Button mButton2;
    private ActionMenuItem mButton2MenuItem;
    private AbsActionBarView.CollapseView mCollapseController;
    private int mCollapseTotalHeight;
    private int mContentInset;
    private int mExpandTitleStyleRes;
    private TextView mExpandTitleView;
    private int mExpandTotalHeight;
    private final Handler mHandler;
    private boolean mIsAnimating;
    private View mMainContainer;
    private AnimConfig mMenuAnimConfig;
    private AbsActionBarView.CollapseView mMovableController;
    private FrameLayout mMovableMainContainer;
    private boolean mNonTouchScrolling;
    private View.OnClickListener mOnMenuItemClickListener;
    private int mPendingHeight;
    private final Runnable mPostAnimationRunnable;
    private Runnable mPostScroll;
    private Scroller mPostScroller;
    private boolean mRequestAnimation;
    private Drawable mSplitBackground;
    private boolean mStartWithAnim;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;
    private boolean mTouchScrolling;
    private TransitionListener mTransitionListener;
    private SpringAnimation mVisibilityAnim;

    public static class CountDown {
        private int mCount;
        private CountDownCompleteListener mCountDownCompleteListener;

        public interface CountDownCompleteListener {
            void onCountDownComplete();
        }

        public CountDown(int i2, CountDownCompleteListener countDownCompleteListener) {
            this.mCount = i2;
            this.mCountDownCompleteListener = countDownCompleteListener;
        }

        public void countDown() {
            int i2 = this.mCount - 1;
            this.mCount = i2;
            if (i2 == 0) {
                this.mCountDownCompleteListener.onCountDownComplete();
            }
        }
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    private void addSplitMenuView() {
        this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
        ActionMenuView actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        this.mMenuView = actionMenuView;
        ViewGroup viewGroup = (ViewGroup) actionMenuView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.mMenuView);
            this.mSplitView.onActionModeMenuViewRemoved(this.mMenuView);
        }
        ActionMenuView actionMenuView2 = this.mMenuView;
        if (actionMenuView2 != null) {
            actionMenuView2.setSupportBlur(this.mSplitView.isSupportBlur());
            this.mMenuView.setEnableBlur(this.mSplitView.isEnableBlur());
            this.mMenuView.applyBlur(this.mSplitView.isEnableBlur() && this.mMenuView.getMeasuredWidth() > 0 && this.mMenuView.getMeasuredHeight() > 0);
            this.mMenuView.updateBackground(this.mBackgroundViewApplyBlur);
        }
        boolean z2 = this.mBottomMenuMode == 3;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 1;
        if (z2) {
            layoutParams.bottomMargin = MiuixUIUtils.dp2px(getContext(), 16.0f);
        }
        Rect rect = this.mPendingInset;
        if (rect != null) {
            if (z2) {
                layoutParams.bottomMargin += rect.bottom;
                ViewUtils.resetPaddingBottom(this.mMenuView, 0);
            } else {
                ViewUtils.resetPaddingBottom(this.mMenuView, rect.bottom);
            }
        }
        ActionMenuView actionMenuView3 = this.mMenuView;
        if (actionMenuView3 instanceof ResponsiveActionMenuView) {
            ((ResponsiveActionMenuView) actionMenuView3).setSuspendEnabled(z2);
        }
        this.mSplitView.addView(this.mMenuView, layoutParams);
        this.mSplitView.onActionModeMenuViewAdded(this.mMenuView);
        requestLayout();
    }

    private void animateLayoutWithProcess(float f2) {
        float fMin = 1.0f - Math.min(1.0f, f2 * 3.0f);
        int i2 = this.mInnerExpandState;
        if (i2 == 2) {
            if (fMin > 0.0f) {
                this.mCollapseController.animTo(0.0f, 0, 20, this.mCollapseAnimHideConfig);
            } else {
                this.mCollapseController.animTo(1.0f, 0, 0, this.mCollapseAnimShowConfig);
            }
            this.mMovableController.animTo(fMin, 0, 0, this.mMovableAnimNormalConfig);
            return;
        }
        if (i2 == 1) {
            this.mCollapseController.animTo(0.0f, 0, 20, this.mCollapseAnimHideConfig);
            this.mMovableController.animTo(1.0f, 0, 0, this.mMovableAnimNormalConfig);
        } else if (i2 == 0) {
            this.mCollapseController.animTo(1.0f, 0, 0, this.mCollapseAnimShowConfig);
            this.mMovableController.animTo(0.0f, 0, 0, this.mMovableAnimNormalConfig);
        }
    }

    private void bindActionMenuItemInfo(ActionMenuItem actionMenuItem, CharSequence charSequence) {
        if (actionMenuItem == null) {
            return;
        }
        actionMenuItem.setTitle(charSequence);
    }

    private void bindButtonInfo(Button button, CharSequence charSequence, int i2, CharSequence charSequence2) {
        if (button == null) {
            return;
        }
        button.setVisibility((TextUtils.isEmpty(charSequence) && i2 == 0) ? 8 : 0);
        button.setText(charSequence);
        button.setBackgroundResource(i2);
        if (!TextUtils.isEmpty(charSequence2)) {
            button.setContentDescription(charSequence2);
        }
        if (!TextUtils.isEmpty(charSequence) || i2 == 0) {
            button.setMaxHeight(Integer.MAX_VALUE);
        } else {
            button.setMaxHeight(getContext().getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_mode_title_button_height));
        }
    }

    private boolean canTitleBeShown() {
        boolean z2 = (!isResizable() && getExpandState() == 0) || this.mTitleView.getPaint().measureText(this.mTitle.toString()) <= ((float) this.mTitleView.getMeasuredWidth());
        if (!ActionBarPolicy.get(getContext()).isTitleEnableEllipsis() || z2) {
            return z2;
        }
        return true;
    }

    private void clearBackground() {
        ActionBarContainer actionBarContainer;
        setBackground(null);
        if (!this.mSplitActionBarEnable || (actionBarContainer = this.mSplitView) == null) {
            return;
        }
        actionBarContainer.updateBackgroundInternal(true);
    }

    private Button getButton(int i2) {
        if (i2 == 16908313) {
            return this.mButton1;
        }
        if (i2 == 16908314) {
            return this.mButton2;
        }
        return null;
    }

    private ActionMenuItem getButtonMenuItem(int i2) {
        if (i2 == 16908313) {
            return this.mButton1MenuItem;
        }
        if (i2 == 16908314) {
            return this.mButton2MenuItem;
        }
        return null;
    }

    private SpringAnimation getViewSpringAnima(View view, float f2, float f3, float f4) {
        SpringAnimation springAnimation = new SpringAnimation(view, ViewProperty.ALPHA, f4);
        springAnimation.setStartValue(f3);
        springAnimation.getSpring().setStiffness(f2);
        springAnimation.getSpring().setDampingRatio(0.9f);
        springAnimation.setMinimumVisibleChange(0.00390625f);
        return springAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAllAnimationsEnd() {
        ActionMenuView actionMenuView;
        setSplitAnimating(false);
        this.mAnimateStart = false;
        notifyAnimationEnd(this.mAnimateToVisible);
        if (this.mAnimationMode == 2) {
            killMode();
        }
        this.mAnimationMode = 0;
        this.mVisibilityAnim = null;
        setVisibility(this.mAnimateToVisible ? 0 : 8);
        if (this.mSplitView != null && (actionMenuView = this.mMenuView) != null) {
            actionMenuView.setVisibility(this.mAnimateToVisible ? 0 : 8);
        }
        ActionMenuView actionMenuView2 = this.mMenuView;
        if (actionMenuView2 != null) {
            Folme.clean(actionMenuView2);
        }
    }

    private void onFinishStartActionMode(boolean z2) {
        ActionMenuView actionMenuView;
        notifyAnimationEnd(z2);
        setVisibility(z2 ? 0 : 8);
        if (this.mSplitView == null || (actionMenuView = this.mMenuView) == null) {
            return;
        }
        actionMenuView.setVisibility(z2 ? 0 : 8);
    }

    private void onLayoutCollapseViews(int i2, int i3, int i4, int i5) {
        int paddingStart = getPaddingStart();
        int measuredHeight = this.mMainContainer.getMeasuredHeight();
        int i6 = ((i5 - i3) - measuredHeight) / 2;
        if (this.mMainContainer.getVisibility() != 8) {
            View view = this.mMainContainer;
            ViewUtils.layoutChildView(this, view, paddingStart, i6, paddingStart + view.getMeasuredWidth(), i6 + this.mMainContainer.getMeasuredHeight());
        }
        int paddingEnd = (i4 - i2) - getPaddingEnd();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            positionChildInverse(this.mMenuView, paddingEnd, i6, measuredHeight);
        }
        if (this.mRequestAnimation) {
            this.mAnimationMode = 1;
            makeContextViewsShowHideWithAnimation(true);
            this.mRequestAnimation = false;
        } else if (this.mMenuView != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) getParent().getParent();
            if (actionBarOverlayLayout.isBottomAnimating()) {
                return;
            }
            actionBarOverlayLayout.onMenuStateChanged(this.mMenuView.getCollapsedHeight(), 1);
        }
    }

    private void resetBackground() {
        ActionBarContainer actionBarContainer;
        setBackground(this.mActionModeBackground);
        if (!this.mSplitActionBarEnable || (actionBarContainer = this.mSplitView) == null) {
            return;
        }
        actionBarContainer.updateBackgroundInternal(false);
    }

    private void setButtonContentDescription(Button button, int i2) {
        if (button == null) {
            return;
        }
        if (R.drawable.miuix_appcompat_action_mode_title_button_cancel == i2 || R.drawable.miuix_action_icon_cancel_light == i2 || R.drawable.miuix_action_icon_cancel_dark == i2) {
            button.setContentDescription(getResources().getString(R.string.miuix_appcompat_cancel_description));
            return;
        }
        if (R.drawable.miuix_appcompat_action_mode_title_button_confirm == i2 || R.drawable.miuix_action_icon_immersion_confirm_light == i2 || R.drawable.miuix_action_icon_immersion_confirm_dark == i2) {
            button.setContentDescription(getResources().getString(R.string.miuix_appcompat_confirm_description));
            return;
        }
        if (R.drawable.miuix_appcompat_action_mode_title_button_select_all == i2 || R.drawable.miuix_action_icon_select_all_light == i2 || R.drawable.miuix_action_icon_select_all_dark == i2) {
            button.setContentDescription(getResources().getString(R.string.miuix_appcompat_select_all_description));
            return;
        }
        if (R.drawable.miuix_appcompat_action_mode_title_button_deselect_all == i2 || R.drawable.miuix_action_icon_deselect_all_light == i2 || R.drawable.miuix_action_icon_deselect_all_dark == i2) {
            button.setContentDescription(getResources().getString(R.string.miuix_appcompat_deselect_all_description));
        } else if (R.drawable.miuix_appcompat_action_mode_title_button_delete == i2 || R.drawable.miuix_action_icon_immersion_delete_light == i2 || R.drawable.miuix_action_icon_immersion_delete_dark == i2) {
            button.setContentDescription(getResources().getString(R.string.miuix_appcompat_delete_description));
        }
    }

    private void setSplitAnimating(boolean z2) {
        ActionBarContainer actionBarContainer = this.mSplitView;
        if (actionBarContainer != null) {
            ((ActionBarOverlayLayout) actionBarContainer.getParent()).setAnimating(z2);
        }
    }

    private void stopSplitMenuAnimation() {
        if (this.mMenuView != null) {
            Folme.useAt(this.mMenuView).state().setTo(new AnimState().add(ViewProperty.TRANSLATION_Y, this.mAnimateToVisible ? 0 : r1.getCollapsedHeight()));
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        if (actionModeAnimationListener == null) {
            return;
        }
        if (this.mAnimationListeners == null) {
            this.mAnimationListeners = new ArrayList();
        }
        this.mAnimationListeners.add(actionModeAnimationListener);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void animateToVisibility(int i2) {
        super.animateToVisibility(i2);
    }

    public void cancelAnimation() {
        SpringAnimation springAnimation = this.mVisibilityAnim;
        if (springAnimation != null) {
            springAnimation.cancel();
            this.mVisibilityAnim = null;
        }
        stopSplitMenuAnimation();
        setSplitAnimating(false);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void closeMode() {
        endAnimation();
        this.mAnimationMode = 2;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public void endAnimation() {
        SpringAnimation springAnimation = this.mVisibilityAnim;
        if (springAnimation != null) {
            springAnimation.skipToEnd();
            this.mVisibilityAnim = null;
        }
        stopSplitMenuAnimation();
        setSplitAnimating(false);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public int getActionBarStyle() {
        return android.R.attr.actionModeStyle;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ ActionMenuView getActionMenuView() {
        return super.getActionMenuView();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public float getAnimationProgress() {
        return this.mAnimationProgress;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public CollapseTitle getCollapseTitle() {
        return null;
    }

    public int getCollapsedHeight() {
        return this.mCollapseTotalHeight;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ int getExpandState() {
        return super.getExpandState();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public ExpandTitle getExpandTitle() {
        return null;
    }

    public int getExpandedHeight() {
        return this.mExpandTotalHeight;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ ActionMenuView getMenuView() {
        return super.getMenuView();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    @Nullable
    public View getSubTitleView(int i2) {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    @Nullable
    public View getTitleView(int i2) {
        if (i2 == 0) {
            return this.mTitleView;
        }
        if (i2 != 1) {
            return null;
        }
        return this.mExpandTitleView;
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public int getViewHeight() {
        return getCollapsedHeight();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu(false);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void initForMode(ActionMode actionMode) {
        if (this.mActionMode != null) {
            cancelAnimation();
            killMode();
        }
        initTitle();
        if (this.mTitleView.getEllipsize() == TextUtils.TruncateAt.MARQUEE) {
            this.mTitleView.requestFocus();
        }
        this.mActionMode = new WeakReference<>(actionMode);
        MenuBuilder menuBuilder = (MenuBuilder) actionMode.getMenu();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus(false);
        }
        Object parent = getParent();
        while (true) {
            View view = (View) parent;
            if (view instanceof ActionBarOverlayLayout) {
                ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(getContext(), (ActionBarOverlayLayout) view, R.layout.miuix_appcompat_responsive_action_menu_layout, R.layout.miuix_appcompat_action_mode_menu_item_layout);
                this.mActionMenuPresenter = actionMenuPresenter2;
                actionMenuPresenter2.setReserveOverflow(true);
                this.mActionMenuPresenter.setActionEditMode(true);
                int i2 = this.mMaxActionMenuItemCount;
                if (i2 != Integer.MIN_VALUE) {
                    this.mActionMenuPresenter.setItemLimit(i2);
                }
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
                menuBuilder.addMenuPresenter(this.mActionMenuPresenter);
                if (this.mSplitActionBarEnable) {
                    addSplitMenuView();
                    return;
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                this.mMenuView = actionMenuView;
                actionMenuView.setBackground(null);
                addView(this.mMenuView, layoutParams);
                return;
            }
            if (!(view.getParent() instanceof View)) {
                throw new IllegalStateException("ActionBarOverlayLayout not found");
            }
            parent = view.getParent();
        }
    }

    public void initTitle() {
        if (this.mTitleLayout == null) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.miuix_appcompat_action_mode_title_item, (ViewGroup) this, false);
            this.mTitleLayout = linearLayout;
            this.mButton1 = (Button) linearLayout.findViewById(16908313);
            this.mButton2 = (Button) this.mTitleLayout.findViewById(16908314);
            Button button = this.mButton1;
            if (button != null) {
                button.setOnClickListener(this.mOnMenuItemClickListener);
                Folme.useAt(this.mButton1).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).setAlpha(0.6f, new ITouchStyle.TouchType[0]).handleTouchOf(this.mButton1, new AnimConfig[0]);
                Folme.useAt(this.mButton1).hover().setFeedbackRadius(60.0f);
                Folme.useAt(this.mButton1).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this.mButton1, new AnimConfig[0]);
            }
            Button button2 = this.mButton2;
            if (button2 != null) {
                button2.setOnClickListener(this.mOnMenuItemClickListener);
                Folme.useAt(this.mButton2).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).setAlpha(0.6f, new ITouchStyle.TouchType[0]).handleTouchOf(this.mButton2, new AnimConfig[0]);
                Folme.useAt(this.mButton2).hover().setFeedbackRadius(60.0f);
                Folme.useAt(this.mButton2).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this.mButton2, new AnimConfig[0]);
            }
            TextView textView = (TextView) this.mTitleLayout.findViewById(android.R.id.title);
            this.mTitleView = textView;
            if (this.mTitleStyleRes != 0) {
                textView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            TextView textView2 = new TextView(getContext());
            this.mExpandTitleView = textView2;
            if (this.mExpandTitleStyleRes != 0) {
                textView2.setTextAppearance(getContext(), this.mExpandTitleStyleRes);
                if (RomUtils.getHyperOsVersion() <= 1) {
                    Typography.applyMiSansLight(this.mExpandTitleView);
                }
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mExpandTitleView.setText(this.mTitle);
        this.mMainContainer = this.mTitleLayout;
        this.mCollapseController.attachViews(this.mTitleView);
        boolean zIsEmpty = TextUtils.isEmpty(this.mTitle);
        this.mTitleLayout.setVisibility(!zIsEmpty ? 0 : 8);
        this.mExpandTitleView.setVisibility(zIsEmpty ? 8 : 0);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
        if (this.mExpandTitleView.getParent() == null) {
            this.mExpandTitleView.setId(R.id.action_context_bar_expand_title);
            this.mMovableMainContainer.addView(this.mExpandTitleView);
        }
        if (this.mMovableMainContainer.getParent() == null) {
            addView(this.mMovableMainContainer);
        }
        int i2 = this.mInnerExpandState;
        if (i2 == 0) {
            this.mCollapseController.setAnimFrom(1.0f, 0, 0);
            this.mMovableController.setAnimFrom(0.0f, 0, 0);
        } else if (i2 == 1) {
            this.mCollapseController.setAnimFrom(0.0f, 0, 20);
            this.mMovableController.setAnimFrom(1.0f, 0, 0);
        }
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ boolean isResizable() {
        return super.isResizable();
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ boolean isUserSetExpandState() {
        return super.isUserSetExpandState();
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void killMode() {
        removeAllViews();
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list != null) {
            list.clear();
            this.mAnimationListeners = null;
        }
        if (this.mSplitView != null) {
            ActionMenuView actionMenuView = this.mMenuView;
            if (actionMenuView != null) {
                actionMenuView.onWillRemoved();
            }
            this.mSplitView.removeView(this.mMenuView);
            this.mSplitView.onActionModeMenuViewRemoved(this.mMenuView);
        }
        this.mMenuView = null;
        this.mActionMode = null;
    }

    public void makeContextViewsShowHide(boolean z2) {
        setAlpha(z2 ? 1.0f : 0.0f);
        if (!this.mSplitActionBarEnable) {
            onFinishStartActionMode(z2);
            return;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.mSplitView.getParent();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            int collapsedHeight = actionMenuView.getCollapsedHeight();
            this.mMenuView.setTranslationY(z2 ? 0.0f : collapsedHeight);
            if (!z2) {
                collapsedHeight = 0;
            }
            actionBarOverlayLayout.animateContentMarginBottomByBottomMenu(collapsedHeight);
            this.mMenuView.setAlpha(z2 ? 1.0f : 0.0f);
        }
        onFinishStartActionMode(z2);
    }

    public void makeContextViewsShowHideWithAnimation(final boolean z2) {
        int i2;
        int i3;
        if (z2 != this.mAnimateToVisible || this.mVisibilityAnim == null) {
            this.mAnimateToVisible = z2;
            this.mAnimateStart = false;
            float f2 = 1.0f;
            float f3 = 0.0f;
            if (z2) {
                f3 = 1.0f;
                f2 = 0.0f;
            }
            SpringAnimation viewSpringAnima = getViewSpringAnima(this, z2 ? STIFFNESS_LOW : STIFFNESS_HIGH, f2, f3);
            viewSpringAnima.setStartDelay(z2 ? 50L : 0L);
            setAlpha(f2);
            this.mVisibilityAnim = viewSpringAnima;
            if (!this.mSplitActionBarEnable) {
                final CountDown countDown = new CountDown(1, new CountDown.CountDownCompleteListener() { // from class: miuix.appcompat.internal.app.widget.b
                    @Override // miuix.appcompat.internal.app.widget.ActionBarContextView.CountDown.CountDownCompleteListener
                    public final void onCountDownComplete() {
                        this.f6064a.onAllAnimationsEnd();
                    }
                });
                viewSpringAnima.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: miuix.appcompat.internal.app.widget.c
                    @Override // miuix.animation.physics.DynamicAnimation.OnAnimationEndListener
                    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z3, float f4, float f5) {
                        countDown.countDown();
                    }
                });
                viewSpringAnima.start();
                return;
            }
            final CountDown countDown2 = new CountDown(2, new CountDown.CountDownCompleteListener() { // from class: miuix.appcompat.internal.app.widget.b
                @Override // miuix.appcompat.internal.app.widget.ActionBarContextView.CountDown.CountDownCompleteListener
                public final void onCountDownComplete() {
                    this.f6064a.onAllAnimationsEnd();
                }
            });
            viewSpringAnima.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: miuix.appcompat.internal.app.widget.d
                @Override // miuix.animation.physics.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z3, float f4, float f5) {
                    countDown2.countDown();
                }
            });
            viewSpringAnima.start();
            ActionMenuView actionMenuView = this.mMenuView;
            final ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) getParent().getParent();
            final int collapsedHeight = actionMenuView == null ? 0 : actionMenuView.getCollapsedHeight();
            if (z2) {
                i3 = collapsedHeight;
                i2 = 0;
            } else {
                i2 = collapsedHeight;
                i3 = 0;
            }
            if (actionMenuView != null) {
                if (this.mMenuAnimConfig == null) {
                    this.mMenuAnimConfig = new AnimConfig().setEase(-2, 0.95f, 0.25f);
                }
                TransitionListener transitionListener = this.mTransitionListener;
                if (transitionListener != null) {
                    this.mMenuAnimConfig.removeListeners(transitionListener);
                }
                AnimConfig animConfig = this.mMenuAnimConfig;
                final int i4 = i2;
                final int i5 = i3;
                TransitionListener transitionListener2 = new TransitionListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarContextView.3
                    @Override // miuix.animation.listener.TransitionListener
                    public void onBegin(Object obj) {
                        if (ActionBarContextView.this.mAnimateStart) {
                            return;
                        }
                        ActionBarContextView.this.notifyAnimationStart(z2);
                        ActionBarContextView.this.mAnimateStart = true;
                        ActionBarContextView.this.mIsAnimating = true;
                    }

                    @Override // miuix.animation.listener.TransitionListener
                    public void onComplete(Object obj) {
                        ActionBarContextView.this.mIsAnimating = false;
                        countDown2.countDown();
                    }

                    @Override // miuix.animation.listener.TransitionListener
                    public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                        UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, View.TRANSLATION_Y.getName());
                        if (updateInfoFindByName == null) {
                            return;
                        }
                        float floatValue = updateInfoFindByName.getFloatValue();
                        actionBarOverlayLayout.onMenuStateChanged((int) (collapsedHeight - floatValue), 1);
                        int i6 = i4;
                        int i7 = i5;
                        ActionBarContextView.this.notifyAnimationUpdate(z2, i6 == i7 ? 1.0f : (floatValue - i7) / (i6 - i7));
                    }
                };
                this.mTransitionListener = transitionListener2;
                animConfig.addListeners(transitionListener2);
                IStateStyle iStateStyleState = Folme.useAt(actionMenuView).state();
                ViewProperty viewProperty = ViewProperty.TRANSLATION_Y;
                iStateStyleState.setTo(viewProperty, Integer.valueOf(i3)).to(viewProperty, Integer.valueOf(i2), this.mMenuAnimConfig);
                actionBarOverlayLayout.onMenuStateChanged(0, 1);
            }
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationEnd(boolean z2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onStop(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationStart(boolean z2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onStart(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void notifyAnimationUpdate(boolean z2, float f2) {
        List<ActionModeAnimationListener> list = this.mAnimationListeners;
        if (list == null) {
            return;
        }
        Iterator<ActionModeAnimationListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onUpdate(z2, f2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        TextView textView;
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.ActionMode, getActionBarStyle(), 0);
        this.mTitleMinHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ActionMode_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_horizontal_padding);
        this.mMovableMainContainer.setPaddingRelative(dimensionPixelOffset, getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_top_padding), dimensionPixelOffset, getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_bottom_padding));
        setPaddingRelative(AttributeResolver.resolveDimensionPixelSize(getContext(), R.attr.actionBarPaddingStart), getPaddingTop(), AttributeResolver.resolveDimensionPixelSize(getContext(), R.attr.actionBarPaddingEnd), getPaddingBottom());
        if (this.mTitleStyleRes == 0 || (textView = this.mTitleView) == null) {
            return;
        }
        textView.setTextAppearance(getContext(), this.mTitleStyleRes);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.hideOverflowMenu(false);
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void onExpandStateChanged(int i2, int i3) {
        AbsActionBarView.CollapseView collapseView;
        if (i2 == 2) {
            this.mPendingHeight = 0;
            if (!this.mPostScroller.isFinished()) {
                this.mPostScroller.forceFinished(true);
            }
        }
        if (i3 == 2 && (collapseView = this.mMovableController) != null) {
            collapseView.setVisibility(0);
        }
        if (i3 == 1) {
            if (this.mMovableMainContainer.getAlpha() > 0.0f) {
                AbsActionBarView.CollapseView collapseView2 = this.mCollapseController;
                if (collapseView2 != null) {
                    collapseView2.setAnimFrom(0.0f, 0, 20, true);
                }
                AbsActionBarView.CollapseView collapseView3 = this.mMovableController;
                if (collapseView3 != null) {
                    collapseView3.setAnimFrom(1.0f, 0, 0, true);
                }
            }
            AbsActionBarView.CollapseView collapseView4 = this.mMovableController;
            if (collapseView4 != null) {
                collapseView4.setVisibility(0);
            }
        }
        if (i3 != 0) {
            this.mPendingHeight = getHeight() - this.mCollapseTotalHeight;
            return;
        }
        AbsActionBarView.CollapseView collapseView5 = this.mCollapseController;
        if (collapseView5 != null) {
            collapseView5.setAnimFrom(1.0f, 0, 0, true);
            this.mCollapseController.setVisibility(0);
            this.mCollapseController.onShow();
        }
        AbsActionBarView.CollapseView collapseView6 = this.mMovableController;
        if (collapseView6 != null) {
            collapseView6.setAnimFrom(0.0f, 0, 0, true);
            this.mMovableController.setVisibility(8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r12, int r13, int r14, int r15, int r16) {
        /*
            r11 = this;
            r6 = r11
            r2 = r13
            r0 = r14
            r4 = r15
            int r1 = r4 - r2
            float r1 = (float) r1
            android.content.Context r3 = r11.getContext()
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            float r3 = r3.density
            float r1 = r1 / r3
            int r7 = (int) r1
            int r1 = r6.mInnerExpandState
            r3 = 2
            r8 = 0
            r9 = 1
            if (r1 != r3) goto L22
            int r1 = r6.mPendingHeight
        L20:
            r10 = r1
            goto L2c
        L22:
            if (r1 != r9) goto L2b
            android.widget.FrameLayout r1 = r6.mMovableMainContainer
            int r1 = r1.getMeasuredHeight()
            goto L20
        L2b:
            r10 = r8
        L2c:
            int r5 = r16 - r0
            int r3 = r5 - r10
            int r1 = r16 - r10
            r11.onLayoutCollapseViews(r13, r14, r15, r1)
            r0 = r11
            r1 = r12
            r2 = r13
            r4 = r15
            r0.onLayoutExpandViews(r1, r2, r3, r4, r5)
            android.widget.FrameLayout r0 = r6.mMovableMainContainer
            int r0 = r0.getMeasuredHeight()
            int r0 = r0 - r10
            float r0 = (float) r0
            android.widget.FrameLayout r1 = r6.mMovableMainContainer
            int r1 = r1.getMeasuredHeight()
            float r1 = (float) r1
            float r0 = r0 / r1
            r1 = 1065353216(0x3f800000, float:1.0)
            float r0 = java.lang.Math.min(r1, r0)
            r11.animateLayoutWithProcess(r0)
            r6.mLastProcess = r0
            r0 = 640(0x280, float:8.97E-43)
            if (r7 <= r0) goto L5c
            r8 = r9
        L5c:
            r6.mIsInWideMode = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarContextView.onLayout(boolean, int, int, int, int):void");
    }

    public void onLayoutExpandViews(boolean z2, int i2, int i3, int i4, int i5) {
        FrameLayout frameLayout = this.mMovableMainContainer;
        if (frameLayout == null || frameLayout.getVisibility() != 0 || this.mInnerExpandState == 0) {
            return;
        }
        FrameLayout frameLayout2 = this.mMovableMainContainer;
        frameLayout2.layout(i2, i5 - frameLayout2.getMeasuredHeight(), i4, i5);
        if (ViewUtils.isLayoutRtl(this)) {
            i2 = i4 - this.mMovableMainContainer.getMeasuredWidth();
        }
        Rect rect = new Rect();
        rect.set(i2, this.mMovableMainContainer.getMeasuredHeight() - (i5 - i3), this.mMovableMainContainer.getMeasuredWidth() + i2, this.mMovableMainContainer.getMeasuredHeight());
        this.mMovableMainContainer.setClipBounds(rect);
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int iMax;
        int size = View.MeasureSpec.getSize(i2);
        int i4 = this.mTitleMaxHeight;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec((i4 > 0 ? i4 : View.MeasureSpec.getSize(i3)) - paddingTop, Integer.MIN_VALUE);
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || actionMenuView.getParent() != this) {
            iMax = 0;
        } else {
            paddingLeft = measureChildView(this.mMenuView, paddingLeft, iMakeMeasureSpec, 0);
            iMax = this.mMenuView.getMeasuredHeight();
        }
        if (this.mMainContainer.getVisibility() != 8) {
            this.mMainContainer.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, BasicMeasure.EXACTLY), iMakeMeasureSpec);
            iMax = Math.max(iMax, this.mMainContainer.getMeasuredHeight());
            TextView textView = this.mTitleView;
            if (textView != null) {
                textView.setVisibility(canTitleBeShown() ? 0 : 4);
            }
        }
        if (this.mMovableMainContainer.getVisibility() != 8) {
            this.mMovableMainContainer.measure(View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        if (i4 <= 0) {
            this.mCollapseTotalHeight = iMax > 0 ? Math.max(iMax, this.mTitleMinHeight) + this.mContentInset : 0;
        } else if (iMax >= i4) {
            this.mCollapseTotalHeight = i4 + this.mContentInset;
        }
        int measuredHeight = this.mCollapseTotalHeight + this.mMovableMainContainer.getMeasuredHeight();
        this.mExpandTotalHeight = measuredHeight;
        int i5 = this.mInnerExpandState;
        if (i5 == 2) {
            setMeasuredDimension(size, this.mCollapseTotalHeight + this.mPendingHeight);
        } else if (i5 == 1) {
            setMeasuredDimension(size, measuredHeight);
        } else {
            setMeasuredDimension(size, this.mCollapseTotalHeight);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4, int[] iArr2) {
        int i5;
        if (isResizable()) {
            int height = getHeight();
            if (i3 <= 0 || height <= (i5 = this.mCollapseTotalHeight)) {
                return;
            }
            int i6 = height - i3;
            int i7 = this.mPendingHeight;
            if (i6 >= i5) {
                this.mPendingHeight = i7 - i3;
            } else {
                this.mPendingHeight = 0;
            }
            iArr[1] = iArr[1] + i3;
            if (this.mPendingHeight != i7) {
                if (this.mInnerExpandState != 2) {
                    if (!this.mPostScroller.isFinished()) {
                        this.mPostScroller.forceFinished(true);
                    }
                    setExpandState(2);
                }
                iArr2[1] = i3;
                requestLayout();
            }
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr, int[] iArr2) {
        if (isResizable()) {
            int measuredHeight = this.mMovableMainContainer.getMeasuredHeight();
            int i7 = this.mCollapseTotalHeight + measuredHeight;
            int height = getHeight();
            if (i5 >= 0 || height >= i7) {
                return;
            }
            int i8 = this.mPendingHeight;
            if (height - i5 <= i7) {
                this.mPendingHeight = i8 - i5;
                iArr[1] = iArr[1] + i5;
            } else {
                this.mPendingHeight = measuredHeight;
                iArr[1] = iArr[1] + (-(i7 - height));
            }
            if (this.mPendingHeight != i8) {
                if (this.mInnerExpandState != 2) {
                    if (!this.mPostScroller.isFinished()) {
                        this.mPostScroller.forceFinished(true);
                    }
                    setExpandState(2);
                }
                iArr2[1] = i5;
                requestLayout();
            }
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
        if (isResizable()) {
            if (i3 == 0) {
                this.mTouchScrolling = true;
            } else {
                this.mNonTouchScrolling = true;
            }
            if (!this.mPostScroller.isFinished()) {
                this.mPostScroller.forceFinished(true);
                Runnable runnable = this.mPostScroll;
                if (runnable != null) {
                    removeCallbacks(runnable);
                }
            }
            setExpandState(2);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setTitle(savedState.title);
        setButton(16908314, savedState.defaultButtonText);
        if (savedState.isOverflowOpen) {
            postShowOverflowMenu();
        }
        setExpandState(savedState.expandState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isOverflowOpen = isOverflowMenuShowing();
        savedState.title = getTitle();
        Button button = this.mButton2;
        if (button != null) {
            savedState.defaultButtonText = button.getText();
        }
        int i2 = this.mInnerExpandState;
        if (i2 == 2) {
            savedState.expandState = 0;
        } else {
            savedState.expandState = i2;
        }
        return savedState;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        return true;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void onStopNestedScroll(View view, int i2) {
        if (isResizable()) {
            int measuredHeight = this.mMovableMainContainer.getMeasuredHeight();
            int height = getHeight();
            if (this.mTouchScrolling) {
                this.mTouchScrolling = false;
                if (this.mNonTouchScrolling) {
                    return;
                }
            } else if (!this.mNonTouchScrolling) {
                return;
            } else {
                this.mNonTouchScrolling = false;
            }
            int i3 = this.mPendingHeight;
            if (i3 == 0) {
                setExpandState(0);
                return;
            }
            if (i3 == measuredHeight) {
                setExpandState(1);
                return;
            }
            int i4 = this.mCollapseTotalHeight;
            if (height > (measuredHeight / 2) + i4) {
                this.mPostScroller.startScroll(0, height, 0, (i4 + measuredHeight) - height);
            } else {
                this.mPostScroller.startScroll(0, height, 0, i4 - height);
            }
            this.mHandler.postDelayed(this.mPostAnimationRunnable, 17L);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void refreshBottomMenu() {
        if (!this.mSplitActionBarEnable || this.mActionMenuPresenter == null || this.mActionMode == null) {
            return;
        }
        addSplitMenuView();
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener) {
        List<ActionModeAnimationListener> list;
        if (actionModeAnimationListener == null || (list = this.mAnimationListeners) == null) {
            return;
        }
        list.remove(actionModeAnimationListener);
    }

    public void setActionBarView(ActionBarView actionBarView) {
        this.mActionBarView = actionBarView;
    }

    public void setActionModeAnim(boolean z2) {
        this.mStartWithAnim = z2;
    }

    public void setAnimationProgress(float f2) {
        this.mAnimationProgress = f2;
        notifyAnimationUpdate(this.mAnimateToVisible, f2);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setBottomMenuMode(int i2) {
        super.setBottomMenuMode(i2);
    }

    public void setButton(int i2, CharSequence charSequence) {
        setButton(i2, null, charSequence, 0);
    }

    public void setContentInset(int i2) {
        this.mContentInset = i2;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setExpandState(int i2) {
        super.setExpandState(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setPendingInset(Rect rect) {
        super.setPendingInset(rect);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setResizable(boolean z2) {
        super.setResizable(z2);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public void setSplitActionBar(boolean z2) {
        if (this.mSplitActionBarEnable != z2) {
            if (this.mActionMenuPresenter != null) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
                if (z2) {
                    this.mActionMenuPresenter.setWidthLimit(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    layoutParams.width = -1;
                    layoutParams.height = -2;
                    layoutParams.gravity = this.mIsInWideMode ? 17 : 80;
                    ActionMenuView actionMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView = actionMenuView;
                    actionMenuView.setBackground(this.mSplitBackground);
                    ViewGroup viewGroup = (ViewGroup) this.mMenuView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.mMenuView);
                        this.mSplitView.onActionModeMenuViewRemoved(this.mMenuView);
                    }
                    this.mSplitView.addView(this.mMenuView, layoutParams);
                    this.mSplitView.onActionModeMenuViewAdded(this.mMenuView);
                } else {
                    ActionMenuView actionMenuView2 = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
                    this.mMenuView = actionMenuView2;
                    actionMenuView2.setBackground(null);
                    ViewGroup viewGroup2 = (ViewGroup) this.mMenuView.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(this.mMenuView);
                    }
                    addView(this.mMenuView, layoutParams);
                }
            }
            super.setSplitActionBar(z2);
        }
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer actionBarContainer) {
        super.setSplitView(actionBarContainer);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z2) {
        super.setSplitWhenNarrow(z2);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setSubTitleClickListener(View.OnClickListener onClickListener) {
        super.setSubTitleClickListener(onClickListener);
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        initTitle();
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setTitleClickable(boolean z2) {
        super.setTitleClickable(z2);
    }

    public void setTitleOptional(boolean z2) {
        if (z2 != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = z2;
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView, android.view.View
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu();
    }

    public void updateBackground(boolean z2) {
        this.mBackgroundViewApplyBlur = z2;
        if (z2) {
            clearBackground();
        } else {
            resetBackground();
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.ClassLoaderCreator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: miuix.appcompat.internal.app.widget.ActionBarContextView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        };
        public CharSequence defaultButtonText;
        public int expandState;
        public boolean isOverflowOpen;
        public CharSequence title;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.isOverflowOpen ? 1 : 0);
            TextUtils.writeToParcel(this.title, parcel, 0);
            TextUtils.writeToParcel(this.defaultButtonText, parcel, 0);
            parcel.writeInt(this.expandState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.isOverflowOpen = parcel.readInt() != 0;
            Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
            this.title = (CharSequence) creator.createFromParcel(parcel);
            this.defaultButtonText = (CharSequence) creator.createFromParcel(parcel);
            this.expandState = parcel.readInt();
        }

        @RequiresApi(api = 24)
        private SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isOverflowOpen = parcel.readInt() != 0;
            Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
            this.title = (CharSequence) creator.createFromParcel(parcel);
            this.defaultButtonText = (CharSequence) creator.createFromParcel(parcel);
            this.expandState = parcel.readInt();
        }
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.actionModeStyle);
    }

    @Override // miuix.appcompat.internal.app.widget.ActionModeView
    public void animateToVisibility(boolean z2) {
        cancelAnimation();
        setSplitAnimating(this.mStartWithAnim);
        if (!z2) {
            if (this.mStartWithAnim) {
                makeContextViewsShowHideWithAnimation(false);
                return;
            } else {
                makeContextViewsShowHide(false);
                return;
            }
        }
        if (!this.mStartWithAnim) {
            makeContextViewsShowHide(true);
        } else {
            setVisibility(0);
            this.mRequestAnimation = true;
        }
    }

    public void setButton(int i2, CharSequence charSequence, CharSequence charSequence2, int i3) {
        setButton(i2, charSequence, 0, charSequence2, i3);
    }

    @Override // miuix.appcompat.internal.app.widget.AbsActionBarView
    public /* bridge */ /* synthetic */ void setExpandState(int i2, boolean z2, boolean z3) {
        super.setExpandState(i2, z2, z3);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mStartWithAnim = true;
        this.mBackgroundViewApplyBlur = false;
        this.mHandler = new Handler(Looper.myLooper());
        this.mPostAnimationRunnable = new Runnable() { // from class: miuix.appcompat.internal.app.widget.ActionBarContextView.1
            @Override // java.lang.Runnable
            public void run() {
                if (ActionBarContextView.this.mPostScroll != null) {
                    ActionBarContextView actionBarContextView = ActionBarContextView.this;
                    actionBarContextView.postOnAnimation(actionBarContextView.mPostScroll);
                }
            }
        };
        this.mOnMenuItemClickListener = new View.OnClickListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarContextView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditActionModeImpl editActionModeImpl;
                ActionMenuItem actionMenuItem = view.getId() == 16908313 ? ActionBarContextView.this.mButton1MenuItem : ActionBarContextView.this.mButton2MenuItem;
                if (ActionBarContextView.this.mActionMode == null || (editActionModeImpl = (EditActionModeImpl) ActionBarContextView.this.mActionMode.get()) == null) {
                    return;
                }
                editActionModeImpl.onMenuItemSelected((MenuBuilder) editActionModeImpl.getMenu(), actionMenuItem);
            }
        };
        this.mCollapseController = new AbsActionBarView.CollapseView();
        this.mMovableController = new AbsActionBarView.CollapseView();
        this.mTouchScrolling = false;
        this.mNonTouchScrolling = false;
        this.mPostScroll = new Runnable() { // from class: miuix.appcompat.internal.app.widget.ActionBarContextView.4
            @Override // java.lang.Runnable
            public void run() {
                if (ActionBarContextView.this.mPostScroller.computeScrollOffset()) {
                    ActionBarContextView actionBarContextView = ActionBarContextView.this;
                    actionBarContextView.mPendingHeight = actionBarContextView.mPostScroller.getCurrY() - ActionBarContextView.this.mCollapseTotalHeight;
                    ActionBarContextView.this.requestLayout();
                    if (!ActionBarContextView.this.mPostScroller.isFinished()) {
                        ActionBarContextView.this.postOnAnimation(this);
                    } else if (ActionBarContextView.this.mPostScroller.getCurrY() == ActionBarContextView.this.mCollapseTotalHeight) {
                        ActionBarContextView.this.setExpandState(0);
                    } else if (ActionBarContextView.this.mPostScroller.getCurrY() == ActionBarContextView.this.mCollapseTotalHeight + ActionBarContextView.this.mMovableMainContainer.getMeasuredHeight()) {
                        ActionBarContextView.this.setExpandState(1);
                    }
                }
            }
        };
        this.mPostScroller = new Scroller(context);
        FrameLayout frameLayout = new FrameLayout(context);
        this.mMovableMainContainer = frameLayout;
        frameLayout.setId(R.id.action_bar_movable_container);
        FrameLayout frameLayout2 = this.mMovableMainContainer;
        Resources resources = context.getResources();
        int i3 = R.dimen.miuix_appcompat_action_bar_title_horizontal_padding;
        frameLayout2.setPaddingRelative(resources.getDimensionPixelOffset(i3), context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_top_padding), context.getResources().getDimensionPixelOffset(i3), context.getResources().getDimensionPixelOffset(R.dimen.miuix_appcompat_action_bar_title_bottom_padding));
        this.mMovableMainContainer.setVisibility(0);
        this.mMovableController.attachViews(this.mMovableMainContainer);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMode, i2, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionMode_android_background);
        this.mActionModeBackground = drawable;
        setBackground(drawable);
        this.mTitleStyleRes = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ActionMode_android_titleTextStyle, 0);
        this.mExpandTitleStyleRes = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ActionMode_expandTitleTextStyle, 0);
        this.mTitleMinHeight = typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ActionMode_android_minHeight, 0);
        this.mSplitBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionMode_android_backgroundSplit);
        this.mButton1MenuItem = new ActionMenuItem(context, 0, 16908313, 0, 0, context.getString(android.R.string.cancel));
        this.mButton2MenuItem = new ActionMenuItem(context, 0, 16908314, 0, 0, context.getString(R.string.miuix_appcompat_action_mode_select_all));
        this.mStartWithAnim = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActionMode_actionModeAnim, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setButton(int i2, CharSequence charSequence, int i3, CharSequence charSequence2, int i4) {
        initTitle();
        Button button = getButton(i2);
        bindButtonInfo(button, charSequence2, i4, charSequence);
        bindActionMenuItemInfo(getButtonMenuItem(i2), charSequence2);
        if (button != null) {
            button.setImportantForAccessibility(i3);
        }
    }

    public void setButton(int i2, CharSequence charSequence, int i3) {
        initTitle();
        Button button = getButton(i2);
        bindButtonInfo(button, charSequence, i3, null);
        bindActionMenuItemInfo(getButtonMenuItem(i2), charSequence);
        if (!TextUtils.isEmpty(charSequence) || i3 == 0) {
            return;
        }
        setButtonContentDescription(button, i3);
    }
}
