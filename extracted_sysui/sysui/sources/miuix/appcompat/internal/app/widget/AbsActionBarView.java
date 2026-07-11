package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBarTransitionListener;
import miuix.appcompat.internal.app.widget.actionbar.CollapseTitle;
import miuix.appcompat.internal.app.widget.actionbar.ExpandTitle;
import miuix.appcompat.internal.view.menu.action.ActionMenuPresenter;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.appcompat.internal.view.menu.action.EndActionMenuPresenter;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbsActionBarView extends ViewGroup {
    protected static final int COLLAPSE_LAYOUT_MAX_TRANSY = 20;
    static final int INNER_STATE_COLLAPSE = 0;
    static final int INNER_STATE_EXPAND = 1;
    static final int INNER_STATE_RESIZING = 2;
    protected static final int MAX_ACTION_MENU_ITEM_COUNT_UNSET = Integer.MIN_VALUE;
    List<ActionBarTransitionListener> mActionBarTransitionListeners;
    protected ActionMenuPresenter mActionMenuPresenter;
    protected TransitionListener mAnimConfigListener;
    int mBottomMenuMode;
    protected AnimConfig mCollapseAnimHideConfig;
    protected AnimConfig mCollapseAnimShowConfig;
    protected boolean mCollapseTitleColorTransitEnable;
    protected boolean mEndActionMenuEnable;
    int mExpandState;
    int mExpandStateBeforeResizing;
    int mExpandStateOnLayout;
    protected boolean mExpandTitleColorTransitEnable;
    protected AnimConfig mHideProcessConfig;
    protected boolean mHyperActionMenuEnable;
    int mInnerExpandState;
    protected boolean mIsInWideMode;
    float mLastProcess;
    protected int mMaxActionMenuItemCount;

    @Nullable
    protected ActionMenuView mMenuView;
    protected AnimConfig mMovableAnimNormalConfig;
    protected AnimConfig mMovableAnimShowConfig;
    protected Rect mPendingInset;
    private boolean mResizable;
    protected AnimConfig mShowProcessConfig;
    protected boolean mSplitActionBarEnable;
    protected ActionBarContainer mSplitView;
    protected boolean mSplitWhenNarrow;
    protected int mSubtitlePaddingV;
    protected boolean mTitleClickable;
    protected int mTitleMaxHeight;
    protected int mTitleMinHeight;
    protected int mTitlePaddingV;
    protected int mUserExpandState;
    protected boolean mUserSetExpandState;
    protected View.OnClickListener mUserSubTitleClickListener;

    public static class CollapseView {
        private float mAlpha;
        private boolean mDetached;
        private List<View> mViews = new ArrayList();
        private boolean mIsAcceptAlphaChange = true;

        public void animTo(float f2, int i2, int i3, AnimConfig animConfig) {
            if (this.mDetached) {
                return;
            }
            if (!this.mIsAcceptAlphaChange) {
                f2 = this.mAlpha;
            }
            AnimState animStateAdd = new AnimState("to").add(ViewProperty.ALPHA, f2).add(ViewProperty.TRANSLATION_X, i2).add(ViewProperty.TRANSLATION_Y, i3);
            for (View view : this.mViews) {
                if (view.isAttachedToWindow() && (view.getAlpha() != f2 || view.getTranslationX() != i2 || view.getTranslationY() != i3)) {
                    Folme.useAt(view).state().to(animStateAdd, animConfig);
                }
            }
        }

        public void attachViews(View view) {
            if (this.mViews.contains(view)) {
                return;
            }
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miuix.appcompat.internal.app.widget.AbsActionBarView.CollapseView.1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view2) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view2) {
                    Folme.clean(view2);
                }
            });
            this.mViews.add(view);
        }

        public void detachView(View view) {
            if (view == null || !this.mViews.contains(view)) {
                return;
            }
            this.mViews.remove(view);
        }

        public void onAttachedToWindow() {
            this.mDetached = false;
        }

        public void onDetachedFromWindow() {
            this.mDetached = true;
            Iterator<View> it = this.mViews.iterator();
            while (it.hasNext()) {
                Folme.clean(it.next());
            }
        }

        public void onHide() {
            for (View view : this.mViews) {
                view.clearFocus();
                view.setEnabled(false);
                view.setVisibility(4);
            }
        }

        public void onShow() {
            Iterator<View> it = this.mViews.iterator();
            while (it.hasNext()) {
                it.next().setEnabled(true);
            }
        }

        public void setAcceptAlphaChange(boolean z2) {
            this.mIsAcceptAlphaChange = z2;
        }

        public void setAlpha(float f2) {
            if (this.mDetached) {
                return;
            }
            this.mAlpha = f2;
            Iterator<View> it = this.mViews.iterator();
            while (it.hasNext()) {
                Folme.useAt(it.next()).state().setTo(ViewProperty.ALPHA, Float.valueOf(f2));
            }
        }

        public void setAnimFrom(float f2, int i2, int i3) {
            setAnimFrom(f2, i2, i3, false);
        }

        public void setTransparent(int i2, int i3) {
            if (this.mDetached) {
                return;
            }
            for (View view : this.mViews) {
                if (view.isAttachedToWindow()) {
                    Folme.useAt(view).state().setTo(ViewProperty.TRANSLATION_X, Integer.valueOf(i2), ViewProperty.TRANSLATION_Y, Integer.valueOf(i3));
                }
            }
        }

        public void setVisibility(int i2) {
            for (View view : this.mViews) {
                view.setVisibility(i2);
                if (i2 != 0) {
                    view.clearFocus();
                }
            }
        }

        public void setAnimFrom(float f2, int i2, int i3, boolean z2) {
            if (this.mDetached) {
                return;
            }
            AnimState animStateAdd = new AnimState("from").add(ViewProperty.ALPHA, this.mIsAcceptAlphaChange ? f2 : this.mAlpha).add(ViewProperty.TRANSLATION_X, i2).add(ViewProperty.TRANSLATION_Y, i3);
            for (View view : this.mViews) {
                if (z2) {
                    view.setAlpha(f2);
                    view.setTranslationX(i2);
                    view.setTranslationY(i3);
                }
                if (view.isAttachedToWindow()) {
                    Folme.useAt(view).state().setTo(animStateAdd);
                }
            }
        }
    }

    public AbsActionBarView(Context context) {
        this(context, null);
    }

    private void setTitleMaxHeight(int i2) {
        this.mTitleMaxHeight = i2;
        requestLayout();
    }

    private void setTitleMinHeight(int i2) {
        this.mTitleMinHeight = i2;
        requestLayout();
    }

    public void animateToVisibility(int i2) {
        ActionMenuView actionMenuView;
        clearAnimation();
        if (i2 != getVisibility()) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), i2 == 0 ? R.anim.action_bar_fade_in : R.anim.action_bar_fade_out);
            startAnimation(animationLoadAnimation);
            setVisibility(i2);
            if (this.mSplitView == null || (actionMenuView = this.mMenuView) == null) {
                return;
            }
            actionMenuView.startAnimation(animationLoadAnimation);
            this.mMenuView.setVisibility(i2);
        }
    }

    public void bindActionBarTransitionListeners(List<ActionBarTransitionListener> list) {
        this.mActionBarTransitionListeners = list;
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus(false);
        }
    }

    public int getActionBarStyle() {
        return android.R.attr.actionBarStyle;
    }

    public ActionMenuView getActionMenuView() {
        return this.mMenuView;
    }

    public int getAnimatedVisibility() {
        return getVisibility();
    }

    public abstract CollapseTitle getCollapseTitle();

    public int getExpandState() {
        return this.mExpandState;
    }

    public abstract ExpandTitle getExpandTitle();

    public ActionMenuView getMenuView() {
        return this.mMenuView;
    }

    @Nullable
    public abstract View getSubTitleView(int i2);

    @Nullable
    public abstract View getTitleView(int i2);

    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu(false);
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowReserved();
    }

    public boolean isResizable() {
        return this.mResizable;
    }

    public boolean isUserSetExpandState() {
        return this.mUserSetExpandState;
    }

    public int measureChildView(View view, int i2, int i3, int i4) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - i4);
    }

    public void onAnimatedExpandStateChanged(int i2, int i3) {
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mSplitWhenNarrow) {
            setSplitActionBar(getContext().getResources().getBoolean(R.bool.abc_split_action_bar_is_narrow));
        }
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.onConfigurationChanged(configuration);
        }
    }

    public void onExpandStateChanged(int i2, int i3) {
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4, int[] iArr2) {
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr, int[] iArr2) {
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        return false;
    }

    public void onStopNestedScroll(View view, int i2) {
    }

    public int positionChild(View view, int i2, int i3, int i4) {
        return positionChild(view, i2, i3, i4, true);
    }

    public int positionChildInverse(View view, int i2, int i3, int i4) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = (this.mTitleMinHeight - measuredHeight) / 2;
        ViewUtils.layoutChildView(this, view, i2 - measuredWidth, i5, i2, i5 + measuredHeight);
        return measuredWidth;
    }

    public int positionChildWithOffset(View view, int i2, int i3, int i4, boolean z2, int i5) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i6 = i3 + ((i4 - measuredHeight) / 2);
        if (!z2) {
            i6 = (this.mTitleMinHeight - measuredHeight) / 2;
        }
        int i7 = i6;
        ViewUtils.layoutChildView(this, view, i2 + i5, i7, i2 + measuredWidth + i5, i7 + measuredHeight);
        return measuredWidth + i5;
    }

    public void postShowOverflowMenu() {
        post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6059a.showOverflowMenu();
            }
        });
    }

    public abstract void refreshBottomMenu();

    public void setActionMenuItemLimit(int i2) {
        this.mMaxActionMenuItemCount = i2;
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter == null || (actionMenuPresenter instanceof EndActionMenuPresenter)) {
            return;
        }
        actionMenuPresenter.setItemLimit(i2);
    }

    public void setBottomMenuMode(int i2) {
        this.mBottomMenuMode = i2;
    }

    public void setExpandState(int i2) {
        setExpandState(i2, false, false);
    }

    public void setExpandStateByUser(int i2) {
        if (i2 != -1) {
            this.mUserSetExpandState = true;
            this.mUserExpandState = i2;
        } else {
            this.mUserSetExpandState = false;
            this.mUserExpandState = -1;
        }
    }

    public void setPendingInset(Rect rect) {
        Rect rect2;
        if (rect == null) {
            return;
        }
        boolean z2 = this.mMenuView != null && ((rect2 = this.mPendingInset) == null || rect2.bottom != rect.bottom);
        if (this.mPendingInset == null) {
            this.mPendingInset = new Rect();
        }
        this.mPendingInset.set(rect);
        if (z2) {
            refreshBottomMenu();
        }
    }

    public void setResizable(boolean z2) {
        this.mResizable = z2;
    }

    public void setSplitActionBar(boolean z2) {
        this.mSplitActionBarEnable = z2;
    }

    public void setSplitView(ActionBarContainer actionBarContainer) {
        this.mSplitView = actionBarContainer;
    }

    public void setSplitWhenNarrow(boolean z2) {
        this.mSplitWhenNarrow = z2;
    }

    public void setSubTitleClickListener(View.OnClickListener onClickListener) {
        this.mUserSubTitleClickListener = onClickListener;
    }

    public void setTitleClickable(boolean z2) {
        this.mTitleClickable = z2;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            super.setVisibility(i2);
        }
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu();
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public int positionChild(View view, int i2, int i3, int i4, boolean z2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = i3 + ((i4 - measuredHeight) / 2);
        if (!z2) {
            i5 = (this.mTitleMinHeight - measuredHeight) / 2;
        }
        int i6 = i5;
        ViewUtils.layoutChildView(this, view, i2, i6, i2 + measuredWidth, i6 + measuredHeight);
        return measuredWidth;
    }

    public void setExpandState(int i2, boolean z2, boolean z3) {
        int i3;
        if ((this.mResizable || z3) && (i3 = this.mInnerExpandState) != i2) {
            if (z2) {
                onAnimatedExpandStateChanged(i3, i2);
                return;
            }
            if (i2 == 2) {
                this.mExpandStateBeforeResizing = this.mExpandState;
            }
            this.mInnerExpandState = i2;
            if (i2 == 0) {
                this.mExpandState = 0;
            } else if (i2 == 1) {
                this.mExpandState = 1;
            }
            onExpandStateChanged(i3, i2);
            this.mExpandStateOnLayout = this.mExpandState;
            requestLayout();
        }
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mInnerExpandState = 1;
        this.mExpandStateBeforeResizing = 1;
        this.mExpandState = 1;
        this.mExpandStateOnLayout = 1;
        this.mResizable = true;
        this.mTitleClickable = false;
        this.mLastProcess = 0.0f;
        this.mBottomMenuMode = 2;
        this.mMaxActionMenuItemCount = Integer.MIN_VALUE;
        this.mIsInWideMode = false;
        this.mAnimConfigListener = new TransitionListener() { // from class: miuix.appcompat.internal.app.widget.AbsActionBarView.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
                List<ActionBarTransitionListener> list = AbsActionBarView.this.mActionBarTransitionListeners;
                if (list != null) {
                    Iterator<ActionBarTransitionListener> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onTransitionBegin(obj);
                    }
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                List<ActionBarTransitionListener> list = AbsActionBarView.this.mActionBarTransitionListeners;
                if (list != null) {
                    Iterator<ActionBarTransitionListener> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onTransitionComplete(obj);
                    }
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                List<ActionBarTransitionListener> list = AbsActionBarView.this.mActionBarTransitionListeners;
                if (list != null) {
                    Iterator<ActionBarTransitionListener> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().onTransitionUpdate(obj, collection);
                    }
                }
            }
        };
        this.mUserSubTitleClickListener = null;
        this.mUserSetExpandState = false;
        this.mUserExpandState = -1;
        this.mTitlePaddingV = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_title_collapse_padding_vertical);
        this.mSubtitlePaddingV = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_bar_subtitle_collapse_padding_vertical);
        this.mCollapseAnimShowConfig = new AnimConfig().setEase(-2, 1.0f, 0.3f);
        this.mShowProcessConfig = new AnimConfig().setEase(-2, 1.0f, 0.3f).addListeners(this.mAnimConfigListener);
        this.mCollapseAnimHideConfig = new AnimConfig().setEase(-2, 1.0f, 0.15f);
        this.mHideProcessConfig = new AnimConfig().setEase(-2, 1.0f, 0.15f).addListeners(this.mAnimConfigListener);
        this.mMovableAnimShowConfig = new AnimConfig().setEase(-2, 1.0f, 0.6f);
        this.mMovableAnimNormalConfig = new AnimConfig().setEase(-2, 1.0f, 0.6f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar, android.R.attr.actionBarStyle, 0);
        int i3 = typedArrayObtainStyledAttributes.getInt(R.styleable.ActionBar_expandState, 1);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActionBar_resizable, true);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActionBar_titleClickable, false);
        typedArrayObtainStyledAttributes.recycle();
        if (isUserSetExpandState()) {
            int i4 = this.mUserExpandState;
            this.mInnerExpandState = i4;
            this.mExpandState = i4;
        } else if (i3 == 0) {
            this.mInnerExpandState = 0;
            this.mExpandState = 0;
        } else {
            this.mInnerExpandState = 1;
            this.mExpandState = 1;
        }
        this.mResizable = z2;
        this.mTitleClickable = z3;
    }
}
