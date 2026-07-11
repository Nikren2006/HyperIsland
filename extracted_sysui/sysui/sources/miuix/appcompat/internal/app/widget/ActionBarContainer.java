package miuix.appcompat.internal.app.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.app.ActionBarTransitionListener;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView;
import miuix.core.util.HyperMaterialUtils;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MaterialDayNightConfig;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.core.util.WindowUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.DeviceHelper;
import miuix.theme.token.hypermaterial.Blur;
import miuix.theme.token.hypermaterial.Mask;
import miuix.view.BlurableWidget;
import miuix.view.MiuiBlurUiHelper;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarContainer extends FrameLayout implements BlurableWidget, ActionBar.FragmentViewPagerChangeListener {
    private static final int BG_EMBEDED_TABS_IDX = 1;
    private static final int BG_LENGTH = 3;
    private static final int BG_NORMAL_IDX = 0;
    private static final int BG_STACKED_IDX = 2;
    private ActionBarContextView mActionBarContextView;
    private int mActionBarHeightGap;
    private int mActionBarHeightTotalGap;
    List<ActionBarTransitionListener> mActionBarTransitionListeners;
    private ActionBarView mActionBarView;
    private ActionMenuView mActionModeMenuView;
    private Drawable mBackground;
    private Drawable[] mBackgroundArray;
    private Drawable mBackgroundBackup;

    @Nullable
    private final MiuiBlurUiHelper mBlurHelper;
    protected ActionBarCoordinateListener mCoordinateListener;
    private int mCoordinatedOffsetYInSearchModeAnimation;
    private int mCurBarExpandState;
    private boolean mCurBarResizable;
    private int mCurContextBarExpandState;
    private boolean mCurContextBarResizable;
    private Animator mCurrentShowAnim;
    private boolean mCustomBackground;
    private boolean mCustomViewAutoFitSystemWindow;
    private boolean mDrawBackground;
    private int mHeightMaxMeasureSpec;
    private AnimatorListenerAdapter mHideListener;
    private boolean mInternalApplyBgBlur;
    private boolean mInternalApplySpiltBgBlur;
    private boolean mIsInActionMode;
    private boolean mIsInWideMode;
    private boolean mIsMiuixFloating;
    private boolean mIsSplit;
    private boolean mIsStacked;
    private boolean mIsTransitioning;
    private float mLastActionBarResizingProcess;
    private int mLastToState;

    @Nullable
    private MaterialDayNightConfig mMaterial;
    private boolean mNowShowing;
    private boolean mOverlayMode;
    private Rect mPendingInsets;
    private boolean mRequestAnimation;
    private ActionMenuView mResidentActionMenuView;
    private AnimatorListenerAdapter mShowListener;
    private Drawable mSplitBackground;
    private Drawable mSplitBackgroundBackup;
    private Drawable mStackedBackground;
    private View mTabContainer;
    private int mTabContainerPaddingTop;
    private Boolean mUserApplyBgBlur;
    private Boolean mUserApplySplitActionBarBgBlur;
    private Boolean mUserBgViewApplyBlur;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.ClassLoaderCreator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: miuix.appcompat.internal.app.widget.ActionBarContainer.SavedState.1
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
        boolean actionBarApplyBlur;
        boolean actionBarEnableBlur;
        boolean actionBarSupportBlur;
        int userActionBarApplyBlur;
        int userSplitActionBarApplyBlur;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.userActionBarApplyBlur);
            parcel.writeInt(this.userSplitActionBarApplyBlur);
            parcel.writeInt(this.actionBarSupportBlur ? 1 : 0);
            parcel.writeInt(this.actionBarEnableBlur ? 1 : 0);
            parcel.writeInt(this.actionBarApplyBlur ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.userActionBarApplyBlur = parcel.readInt();
            this.userSplitActionBarApplyBlur = parcel.readInt();
            this.actionBarSupportBlur = parcel.readInt() != 0;
            this.actionBarEnableBlur = parcel.readInt() != 0;
            this.actionBarApplyBlur = parcel.readInt() != 0;
        }

        @RequiresApi(api = 24)
        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.userActionBarApplyBlur = parcel.readInt();
            this.userSplitActionBarApplyBlur = parcel.readInt();
            this.actionBarSupportBlur = parcel.readInt() != 0;
            this.actionBarEnableBlur = parcel.readInt() != 0;
            this.actionBarApplyBlur = parcel.readInt() != 0;
        }
    }

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    private void applyInsetsTopByMargin(View view) {
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        Rect rect = this.mPendingInsets;
        marginLayoutParams.topMargin = rect != null ? rect.top : 0;
        view.setLayoutParams(marginLayoutParams);
    }

    private int getActionMenuViewCollapseHeight(ActionMenuView actionMenuView) {
        if (actionMenuView == null || actionMenuView.getVisibility() != 0 || actionMenuView.getAlpha() == 0.0f || actionMenuView.getCollapsedHeight() <= 0) {
            return 0;
        }
        return Math.max(0, actionMenuView.getCollapsedHeight());
    }

    private int getActionMenuViewInsetHeight(ActionMenuView actionMenuView) {
        if (actionMenuView == null || actionMenuView.getVisibility() != 0 || actionMenuView.getAlpha() == 0.0f || actionMenuView.getCollapsedHeight() <= 0) {
            return 0;
        }
        return Math.max(0, (int) (actionMenuView.getCollapsedHeight() - actionMenuView.getTranslationY()));
    }

    @SuppressLint({"WrongCall", "WrongConstant"})
    private void onMeasureSplit(int i2, int i3) {
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), BasicMeasure.EXACTLY);
        }
        int i4 = this.mHeightMaxMeasureSpec;
        if (i4 != -1) {
            i3 = i4;
        }
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        int iMax = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            iMax = Math.max(iMax, getChildAt(i5).getMeasuredHeight());
        }
        if (iMax == 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        ActionMenuView actionMenuView = this.mResidentActionMenuView;
        if (actionMenuView == null || !actionMenuView.hasOnlyCustomView()) {
            return;
        }
        ActionMenuView actionMenuView2 = this.mResidentActionMenuView;
        if (!(actionMenuView2 instanceof ResponsiveActionMenuView) || ((ResponsiveActionMenuView) actionMenuView2).isSuspend()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), iMax);
    }

    private void resizeSplitMaxHeight() {
        TypedValue typedValueResolveTypedValue;
        if (this.mIsSplit && (typedValueResolveTypedValue = AttributeResolver.resolveTypedValue(getContext(), R.attr.actionBarSplitMaxPercentageHeight)) != null && typedValueResolveTypedValue.type == 6) {
            float windowHeight = WindowUtils.getWindowHeight(getContext());
            this.mHeightMaxMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) typedValueResolveTypedValue.getFraction(windowHeight, windowHeight), Integer.MIN_VALUE);
        }
    }

    private void selectDrawable() {
        ActionBarView actionBarView;
        Drawable[] drawableArr;
        char c2;
        if (this.mCustomBackground || this.mIsSplit || (actionBarView = this.mActionBarView) == null || this.mBackground == null || (drawableArr = this.mBackgroundArray) == null || drawableArr.length < 3) {
            return;
        }
        if (actionBarView.isTightTitleWithEmbeddedTabs()) {
            int displayOptions = this.mActionBarView.getDisplayOptions();
            c2 = ((displayOptions & 2) == 0 && (displayOptions & 4) == 0 && (displayOptions & 16) == 0) ? (char) 1 : (char) 2;
        } else {
            c2 = 0;
        }
        Drawable drawable = this.mBackgroundArray[c2];
        if (drawable != null) {
            this.mBackground = drawable;
        }
    }

    public void addActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener) {
        if (actionBarTransitionListener != null) {
            this.mActionBarTransitionListeners.add(actionBarTransitionListener);
        }
    }

    @Override // miuix.view.BlurableWidget
    public void applyBlur(boolean z2) {
        if (this.mBlurHelper == null || this.mIsSplit) {
            return;
        }
        if (z2 && getVisibility() == 0) {
            this.mBlurHelper.applyBlur(true);
        } else {
            this.mBlurHelper.applyBlur(false);
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mRequestAnimation) {
            post(new Runnable() { // from class: miuix.appcompat.internal.app.widget.ActionBarContainer.4
                @Override // java.lang.Runnable
                public void run() {
                    ActionBarContainer.this.show(true);
                }
            });
            this.mRequestAnimation = false;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mBackground;
        if (drawable != null && drawable.isStateful()) {
            this.mBackground.setState(getDrawableState());
        }
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null && drawable2.isStateful()) {
            this.mStackedBackground.setState(getDrawableState());
        }
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.mSplitBackground.setState(getDrawableState());
    }

    public void finishActionMode() {
        this.mIsInActionMode = false;
    }

    public ActionBarCoordinateListener getActionBarCoordinateListener() {
        return this.mCoordinateListener;
    }

    public int getCollapsedHeight() {
        int collapsedHeight;
        int i2;
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView == null || actionBarContextView.getVisibility() == 8 || this.mActionBarContextView.getMeasuredHeight() <= 0) {
            ActionBarView actionBarView = this.mActionBarView;
            if (actionBarView == null) {
                return 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) actionBarView.getLayoutParams();
            collapsedHeight = this.mActionBarView.getCollapsedHeight();
            i2 = marginLayoutParams.topMargin;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mActionBarContextView.getLayoutParams();
            collapsedHeight = this.mActionBarContextView.getCollapsedHeight();
            i2 = marginLayoutParams2.topMargin;
        }
        return collapsedHeight + i2;
    }

    public int getExpandedHeight() {
        int expandedHeight;
        int i2;
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView == null || actionBarContextView.getVisibility() == 8 || this.mActionBarContextView.getMeasuredHeight() <= 0) {
            ActionBarView actionBarView = this.mActionBarView;
            if (actionBarView == null) {
                return 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) actionBarView.getLayoutParams();
            expandedHeight = this.mActionBarView.getExpandedHeight();
            i2 = marginLayoutParams.topMargin;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mActionBarContextView.getLayoutParams();
            expandedHeight = this.mActionBarContextView.getExpandedHeight();
            i2 = marginLayoutParams2.topMargin;
        }
        return expandedHeight + i2;
    }

    public int getInsetHeight() {
        if (this.mIsSplit) {
            return Math.max(Math.max(0, getActionMenuViewInsetHeight(this.mActionModeMenuView)), getActionMenuViewInsetHeight(this.mResidentActionMenuView));
        }
        return 0;
    }

    public Rect getPendingInsets() {
        return this.mPendingInsets;
    }

    public Drawable getPrimaryBackground() {
        return this.mBackground;
    }

    public int getSplitCollapsedHeight() {
        if (this.mIsSplit) {
            return Math.max(Math.max(0, getActionMenuViewCollapseHeight(this.mActionModeMenuView)), getActionMenuViewCollapseHeight(this.mResidentActionMenuView));
        }
        return 0;
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    public void hide(boolean z2) {
        if (this.mNowShowing) {
            this.mNowShowing = false;
            Animator animator = this.mCurrentShowAnim;
            if (animator != null) {
                animator.cancel();
            }
            if (!z2 || !this.mIsSplit) {
                setVisibility(8);
                return;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "TranslationY", 0.0f, getHeight());
            this.mCurrentShowAnim = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(DeviceHelper.isFeatureWholeAnim() ? getContext().getResources().getInteger(android.R.integer.config_shortAnimTime) : 0L);
            this.mCurrentShowAnim.addListener(this.mHideListener);
            this.mCurrentShowAnim.start();
        }
    }

    @Override // miuix.view.BlurableWidget
    public boolean isApplyBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isApplyBlur();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isEnableBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isEnableBlur();
    }

    public boolean isMiuixFloating() {
        return this.mIsMiuixFloating;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isSupportBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isSupportBlur();
    }

    public void onActionModeMenuViewAdded(ActionMenuView actionMenuView) {
        this.mActionModeMenuView = actionMenuView;
        if (actionMenuView == null || !isSupportBlur()) {
            return;
        }
        Boolean bool = this.mUserApplySplitActionBarBgBlur;
        actionMenuView.applyBlur((bool != null ? bool.booleanValue() : isEnableBlur()) && actionMenuView.getMeasuredWidth() > 0 && actionMenuView.getMeasuredHeight() > 0);
    }

    public void onActionModeMenuViewRemoved(ActionMenuView actionMenuView) {
        if (this.mActionModeMenuView == actionMenuView) {
            this.mActionModeMenuView = null;
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resizeSplitMaxHeight();
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.onConfigChanged();
            if (this.mBlurHelper.isEnableBlur() || this.mUserBgViewApplyBlur != null) {
                return;
            }
            updateBackgroundInternal(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setActionBarCoordinateListener(null);
        this.mActionBarTransitionListeners.clear();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Drawable drawable;
        if (getWidth() == 0 || getHeight() == 0 || this.mIsSplit || (drawable = this.mBackground) == null || !this.mDrawBackground) {
            return;
        }
        drawable.draw(canvas);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = (ActionBarView) findViewById(R.id.action_bar);
        this.mActionBarContextView = (ActionBarContextView) findViewById(R.id.action_context_bar);
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.bindActionBarTransitionListeners(this.mActionBarTransitionListeners);
            this.mCurBarExpandState = this.mActionBarView.getExpandState();
            this.mCurBarResizable = this.mActionBarView.isResizable();
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null) {
            this.mCurContextBarExpandState = actionBarContextView.getExpandState();
            this.mCurContextBarResizable = this.mActionBarContextView.isResizable();
            this.mActionBarContextView.setActionBarView(this.mActionBarView);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        return !this.mIsSplit;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.app.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int measuredHeight;
        int measuredHeight2;
        Rect rect;
        if (this.mIsSplit) {
            onMeasureSplit(i2, i3);
            return;
        }
        View view = this.mTabContainer;
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), this.mTabContainerPaddingTop, this.mTabContainer.getPaddingRight(), this.mTabContainer.getPaddingBottom());
        }
        applyInsetsTopByMargin(this.mActionBarView);
        applyInsetsTopByMargin(this.mActionBarContextView);
        super.onMeasure(i2, i3);
        ActionBarView actionBarView = this.mActionBarView;
        boolean z2 = (actionBarView == null || actionBarView.getVisibility() == 8 || this.mActionBarView.getMeasuredHeight() <= 0) ? false : true;
        if (z2) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mActionBarView.getLayoutParams();
            measuredHeight = this.mActionBarView.isCollapsed() ? layoutParams.topMargin : layoutParams.bottomMargin + this.mActionBarView.getMeasuredHeight() + layoutParams.topMargin;
        } else {
            measuredHeight = 0;
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if ((actionBarContextView == null || actionBarContextView.getVisibility() == 8 || this.mActionBarContextView.getMeasuredHeight() <= 0) ? false : true) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mActionBarContextView.getLayoutParams();
            measuredHeight2 = this.mActionBarContextView.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
        } else {
            measuredHeight2 = 0;
        }
        if (measuredHeight > 0 || measuredHeight2 > 0) {
            setMeasuredDimension(getMeasuredWidth(), Math.max(measuredHeight, measuredHeight2));
        }
        View view2 = this.mTabContainer;
        if (view2 != null && view2.getVisibility() != 8 && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE) {
            setMeasuredDimension(getMeasuredWidth(), Math.min(measuredHeight + this.mTabContainer.getMeasuredHeight(), View.MeasureSpec.getSize(i3)) + ((z2 || (rect = this.mPendingInsets) == null) ? 0 : rect.top));
        }
        int i4 = 0;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 0 && childAt.getMeasuredHeight() > 0 && childAt.getMeasuredWidth() > 0) {
                i4++;
            }
        }
        if (i4 == 0) {
            setMeasuredDimension(0, 0);
        }
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4, int[] iArr2) {
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null && actionBarContextView.getVisibility() == 0) {
            this.mActionBarContextView.onNestedPreScroll(view, i2, i3, iArr, i4, iArr2);
        } else if (!this.mIsSplit && getVisibility() != 8) {
            this.mActionBarView.onNestedPreScroll(view, i2, i3, iArr, i4, iArr2);
        }
        if (!this.mOverlayMode || i3 <= 0 || i3 - iArr[1] <= 0) {
            return;
        }
        if (!this.mIsSplit && getVisibility() == 8) {
            setActionBarBlurByNestedScrolled(true);
            this.mActionBarView.setExpandState(0);
            ActionBarCoordinateListener actionBarCoordinateListener = this.mCoordinateListener;
            if (actionBarCoordinateListener != null) {
                actionBarCoordinateListener.onActionBarResizing(0, 1.0f, 0, this.mActionBarHeightTotalGap);
            }
        }
        if (isLayoutRequested()) {
            return;
        }
        setActionBarBlurByNestedScrolled(true);
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr, int[] iArr2) {
        int i7 = iArr[1];
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null && actionBarContextView.getVisibility() == 0) {
            this.mActionBarContextView.onNestedScroll(view, i2, i3, i4, i5, i6, iArr, iArr2);
        } else if (!this.mIsSplit && getVisibility() != 8) {
            this.mActionBarView.onNestedScroll(view, i2, i3, i4, i5, i6, iArr, iArr2);
        }
        int i8 = iArr[1] - i7;
        if (!this.mOverlayMode || i5 >= 0 || i8 > 0) {
            return;
        }
        setActionBarBlurByNestedScrolled(false);
        if (this.mIsSplit || getVisibility() != 8) {
            return;
        }
        this.mActionBarView.setExpandState(1);
        ActionBarCoordinateListener actionBarCoordinateListener = this.mCoordinateListener;
        if (actionBarCoordinateListener != null) {
            int i9 = this.mActionBarHeightTotalGap;
            actionBarCoordinateListener.onActionBarResizing(1, 0.0f, i9, i9);
        }
    }

    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null && actionBarContextView.getVisibility() == 0) {
            this.mActionBarContextView.onNestedScrollAccepted(view, view2, i2, i3);
        } else {
            if (this.mIsSplit || getVisibility() == 8) {
                return;
            }
            this.mActionBarView.onNestedScrollAccepted(view, view2, i2, i3);
        }
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        ActionMenuView actionMenuView;
        if (!this.mIsSplit || (actionMenuView = this.mResidentActionMenuView) == null) {
            return;
        }
        actionMenuView.onPageScrolled(i2, f2, z2, z3);
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
    }

    public void onResidentActionMenuViewAdded(ActionMenuView actionMenuView) {
        this.mResidentActionMenuView = actionMenuView;
        if (actionMenuView == null || !isSupportBlur()) {
            return;
        }
        actionMenuView.setSupportBlur(isSupportBlur());
        actionMenuView.setEnableBlur(isEnableBlur());
        Boolean bool = this.mUserApplySplitActionBarBgBlur;
        actionMenuView.applyBlur((bool != null ? bool.booleanValue() : isEnableBlur()) && getMeasuredWidth() > 0 && getMeasuredHeight() > 0);
    }

    public void onResidentActionMenuViewRemoved(ActionMenuView actionMenuView) {
        if (this.mResidentActionMenuView == actionMenuView) {
            this.mResidentActionMenuView = null;
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i2 = savedState.userActionBarApplyBlur;
        if (i2 == -1) {
            this.mUserApplyBgBlur = null;
        } else if (i2 == 0) {
            this.mUserApplyBgBlur = Boolean.FALSE;
        } else if (i2 == 1) {
            this.mUserApplyBgBlur = Boolean.TRUE;
        }
        int i3 = savedState.userSplitActionBarApplyBlur;
        if (i3 == -1) {
            this.mUserApplySplitActionBarBgBlur = null;
        } else if (i3 == 0) {
            this.mUserApplySplitActionBarBgBlur = Boolean.FALSE;
        } else if (i3 == 1) {
            this.mUserApplySplitActionBarBgBlur = Boolean.TRUE;
        }
        if (savedState.actionBarSupportBlur) {
            setSupportBlur(true);
        }
        if (savedState.actionBarEnableBlur) {
            setEnableBlur(true);
        }
        if (savedState.actionBarApplyBlur && isEnableBlur()) {
            applyBlur(true);
        }
    }

    @Override // android.view.View
    @Nullable
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Boolean bool = this.mUserApplyBgBlur;
        int i2 = 0;
        savedState.userActionBarApplyBlur = bool == null ? -1 : bool.booleanValue() ? 1 : 0;
        Boolean bool2 = this.mUserApplySplitActionBarBgBlur;
        if (bool2 == null) {
            i2 = -1;
        } else if (bool2.booleanValue()) {
            i2 = 1;
        }
        savedState.userSplitActionBarApplyBlur = i2;
        savedState.actionBarSupportBlur = isSupportBlur();
        savedState.actionBarEnableBlur = isEnableBlur();
        savedState.actionBarApplyBlur = isApplyBlur();
        return savedState;
    }

    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        return (actionBarContextView == null || actionBarContextView.getVisibility() != 0) ? this.mActionBarView.onStartNestedScroll(view, view2, i2, i3) : this.mActionBarContextView.onStartNestedScroll(view, view2, i2, i3);
    }

    public void onStopNestedScroll(View view, int i2) {
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null && actionBarContextView.getVisibility() == 0) {
            this.mActionBarContextView.onStopNestedScroll(view, i2);
        } else {
            if (this.mIsSplit || getVisibility() == 8) {
                return;
            }
            this.mActionBarView.onStopNestedScroll(view, i2);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.mIsSplit && super.onTouchEvent(motionEvent);
    }

    public void onWindowHide() {
        if (this.mActionBarView.getMenuView() != null) {
            this.mActionBarView.getMenuView().startLayoutAnimation();
        }
    }

    public void onWindowShow() {
        if (this.mActionBarView.getMenuView() != null) {
            this.mActionBarView.getMenuView().startLayoutAnimation();
        }
    }

    public void removeActionBarTransitionListener(ActionBarTransitionListener actionBarTransitionListener) {
        if (actionBarTransitionListener != null) {
            this.mActionBarTransitionListeners.remove(actionBarTransitionListener);
        }
    }

    public void resetActionBarBlurConfig() {
        this.mUserApplyBgBlur = null;
        applyBlur(this.mInternalApplyBgBlur);
    }

    public void resetActionBarBlurConfigOnReshow() {
        Boolean bool = this.mUserApplyBgBlur;
        if (bool != null) {
            applyBlur(bool.booleanValue());
        } else {
            applyBlur(this.mInternalApplyBgBlur);
        }
    }

    public void setActionBarBlur(@Nullable Boolean bool) {
        if (isEnableBlur()) {
            if (bool == null) {
                this.mUserApplyBgBlur = null;
                applyBlur(this.mInternalApplyBgBlur);
                return;
            }
            Boolean bool2 = this.mUserApplyBgBlur;
            if (bool2 == null || bool2.booleanValue() != bool.booleanValue()) {
                this.mUserApplyBgBlur = bool;
                applyBlur(bool.booleanValue());
            }
        }
    }

    public void setActionBarBlurByNestedScrolled(boolean z2) {
        this.mInternalApplyBgBlur = z2;
        if (this.mUserApplyBgBlur != null) {
            return;
        }
        applyBlur(z2);
    }

    public void setActionBarContextView(ActionBarContextView actionBarContextView) {
        this.mActionBarContextView = actionBarContextView;
        if (actionBarContextView != null) {
            actionBarContextView.setActionBarView(this.mActionBarView);
            this.mCurContextBarExpandState = this.mActionBarContextView.getExpandState();
            this.mCurContextBarResizable = this.mActionBarContextView.isResizable();
        }
    }

    public void setActionBarCoordinateListener(ActionBarCoordinateListener actionBarCoordinateListener) {
        this.mCoordinateListener = actionBarCoordinateListener;
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
    }

    public void setCoordinatedOffsetYInSearchModeAnimation(int i2) {
        this.mCoordinatedOffsetYInSearchModeAnimation = i2;
        ActionBarCoordinateListener actionBarCoordinateListener = this.mCoordinateListener;
        if (actionBarCoordinateListener != null) {
            actionBarCoordinateListener.onActionBarResizing(this.mLastToState, this.mLastActionBarResizingProcess, this.mActionBarHeightGap + i2, this.mActionBarHeightTotalGap);
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setEnableBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper == null) {
            return;
        }
        miuiBlurUiHelper.setEnableBlur(z2);
    }

    public void setIsMiuixFloating(boolean z2) {
        this.mIsMiuixFloating = z2;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            if (z2) {
                this.mCurBarExpandState = actionBarView.getExpandState();
                this.mCurBarResizable = this.mActionBarView.isResizable();
                this.mActionBarView.setExpandState(0);
                this.mActionBarView.setResizable(false);
            } else {
                actionBarView.setResizable(this.mCurBarResizable);
                this.mActionBarView.setExpandState(this.mCurBarExpandState);
            }
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null) {
            if (!z2) {
                actionBarContextView.setResizable(this.mCurContextBarResizable);
                this.mActionBarContextView.setExpandState(this.mCurContextBarExpandState);
            } else {
                this.mCurContextBarExpandState = actionBarContextView.getExpandState();
                this.mCurContextBarResizable = this.mActionBarContextView.isResizable();
                this.mActionBarContextView.setExpandState(0);
                this.mActionBarContextView.setResizable(false);
            }
        }
    }

    public void setMiuixFloatingOnInit(boolean z2) {
        this.mIsMiuixFloating = z2;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null && z2) {
            this.mCurBarResizable = actionBarView.isResizable();
            this.mActionBarView.setExpandState(0);
            this.mActionBarView.setResizable(false);
            this.mCurBarExpandState = this.mActionBarView.getExpandState();
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView == null || !z2) {
            return;
        }
        this.mCurContextBarResizable = actionBarContextView.isResizable();
        this.mActionBarContextView.setExpandState(0);
        this.mActionBarContextView.setResizable(false);
        this.mCurContextBarExpandState = this.mActionBarContextView.getExpandState();
    }

    public void setOverlayMode(boolean z2) {
        this.mOverlayMode = z2;
    }

    public void setPendingInsets(Rect rect) {
        if (this.mIsSplit) {
            return;
        }
        if (this.mPendingInsets == null) {
            this.mPendingInsets = new Rect();
        }
        if (Objects.equals(this.mPendingInsets, rect)) {
            return;
        }
        this.mPendingInsets.set(rect);
        applyInsetsTopByMargin(this.mActionBarView);
        applyInsetsTopByMargin(this.mActionBarContextView);
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.mBackground;
        Rect rect = null;
        if (drawable2 != null) {
            Rect bounds = drawable2.getBounds();
            this.mBackground.setCallback(null);
            unscheduleDrawable(this.mBackground);
            rect = bounds;
        }
        this.mBackground = drawable;
        boolean z2 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (rect == null) {
                requestLayout();
            } else {
                this.mBackground.setBounds(rect);
            }
            this.mCustomBackground = true;
        } else {
            this.mCustomBackground = false;
        }
        if (!this.mIsSplit ? !(this.mBackground != null || this.mStackedBackground != null) : this.mSplitBackground == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setSplitActionBarBlur(@Nullable Boolean bool) {
        if (this.mIsSplit) {
            this.mUserApplySplitActionBarBgBlur = bool;
            ActionMenuView actionMenuView = this.mActionModeMenuView;
            if (actionMenuView != null) {
                actionMenuView.applyBlur(bool != null ? bool.booleanValue() : this.mInternalApplySpiltBgBlur);
            }
            ActionMenuView actionMenuView2 = this.mResidentActionMenuView;
            if (actionMenuView2 != null) {
                actionMenuView2.applyBlur(bool != null ? bool.booleanValue() : this.mInternalApplySpiltBgBlur);
            }
        }
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2 = this.mSplitBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        boolean z2 = false;
        if (!this.mIsSplit ? !(this.mBackground != null || this.mStackedBackground != null) : this.mSplitBackground == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        boolean z2 = false;
        if (!this.mIsSplit ? !(this.mBackground != null || this.mStackedBackground != null) : this.mSplitBackground == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        View view = this.mTabContainer;
        if (view != null) {
            view.setBackground(this.mStackedBackground);
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setSupportBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.setSupportBlur(z2);
        }
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.mTabContainer;
        if (view != null) {
            removeView(view);
        }
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
            this.mTabContainerPaddingTop = scrollingTabContainerView.getPaddingTop();
        } else {
            View view2 = this.mTabContainer;
            if (view2 != null) {
                view2.setBackground(null);
            }
        }
        this.mTabContainer = scrollingTabContainerView;
    }

    public void setTransitioning(boolean z2) {
        this.mIsTransitioning = z2;
        setDescendantFocusability(z2 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z2 = i2 == 0;
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
        Drawable drawable2 = this.mStackedBackground;
        if (drawable2 != null) {
            drawable2.setVisible(z2, false);
        }
        Drawable drawable3 = this.mSplitBackground;
        if (drawable3 != null) {
            drawable3.setVisible(z2, false);
        }
    }

    public void show(boolean z2) {
        if (this.mNowShowing) {
            return;
        }
        this.mNowShowing = true;
        Animator animator = this.mCurrentShowAnim;
        if (animator != null) {
            animator.cancel();
        }
        setVisibility(0);
        if (!z2) {
            setTranslationY(0.0f);
            return;
        }
        if (this.mIsSplit) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "TranslationY", getHeight(), 0.0f);
            this.mCurrentShowAnim = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration(DeviceHelper.isFeatureWholeAnim() ? getContext().getResources().getInteger(android.R.integer.config_shortAnimTime) : 0L);
            this.mCurrentShowAnim.addListener(this.mShowListener);
            this.mCurrentShowAnim.start();
            ActionMenuView actionMenuView = this.mResidentActionMenuView;
            if (actionMenuView != null) {
                actionMenuView.startLayoutAnimation();
            }
        }
    }

    public void startActionMode() {
        this.mIsInActionMode = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public void updateBackground(boolean z2) {
        this.mUserBgViewApplyBlur = Boolean.valueOf(z2);
        updateBackgroundInternal(z2);
    }

    public void updateBackgroundInternal(boolean z2) {
        if (z2) {
            this.mDrawBackground = false;
        } else {
            this.mDrawBackground = true;
        }
        ActionBarContextView actionBarContextView = this.mActionBarContextView;
        if (actionBarContextView != null) {
            actionBarContextView.updateBackground(z2);
        }
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            actionBarView.setApplyBgBlur(z2);
        }
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.mBackground && !this.mIsSplit) || (drawable == this.mStackedBackground && this.mIsStacked) || ((drawable == this.mSplitBackground && this.mIsSplit) || super.verifyDrawable(drawable));
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNowShowing = true;
        boolean z2 = false;
        this.mInternalApplyBgBlur = false;
        this.mInternalApplySpiltBgBlur = false;
        this.mUserApplyBgBlur = null;
        this.mUserApplySplitActionBarBgBlur = null;
        this.mUserBgViewApplyBlur = null;
        this.mResidentActionMenuView = null;
        this.mActionModeMenuView = null;
        this.mCustomBackground = false;
        this.mHeightMaxMeasureSpec = -1;
        this.mLastActionBarResizingProcess = 0.0f;
        this.mLastToState = 0;
        this.mActionBarHeightTotalGap = 0;
        this.mActionBarHeightGap = 0;
        this.mCoordinateListener = null;
        this.mActionBarTransitionListeners = new CopyOnWriteArrayList();
        this.mHideListener = new AnimatorListenerAdapter() { // from class: miuix.appcompat.internal.app.widget.ActionBarContainer.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ActionBarContainer.this.setVisibility(8);
                ActionBarContainer.this.mCurrentShowAnim = null;
            }
        };
        this.mShowListener = new AnimatorListenerAdapter() { // from class: miuix.appcompat.internal.app.widget.ActionBarContainer.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ActionBarContainer.this.mCurrentShowAnim = null;
            }
        };
        setBackground(null);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionBar);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionBar_android_background);
        this.mBackground = drawable;
        this.mBackgroundArray = new Drawable[]{drawable, typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionBar_actionBarEmbededTabsBackground), typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionBar_actionBarStackedBackground)};
        this.mCustomViewAutoFitSystemWindow = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActionBar_customViewAutoFitSystemWindow, false);
        if (getId() == R.id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActionBar_android_backgroundSplit);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.mIsSplit) {
            setPadding(0, 0, 0, 0);
        }
        resizeSplitMaxHeight();
        if (!this.mIsSplit ? !(this.mBackground != null || this.mStackedBackground != null) : this.mSplitBackground == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        this.mDrawBackground = true;
        if (!HyperMaterialUtils.isEnable()) {
            this.mBlurHelper = null;
        } else {
            this.mMaterial = MaterialDayNightConfig.create(RomUtils.getHyperOsVersion() > 2 ? Mask.Pured_Regular : Blur.ExtraHeavy);
            this.mBlurHelper = new MiuiBlurUiHelper(context, this, false, false, new MiuiBlurUiHelper.BlurStateCallback() { // from class: miuix.appcompat.internal.app.widget.ActionBarContainer.3
                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public Drawable getBackground() {
                    return ActionBarContainer.this.mBackground;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public MaterialConfig.BlurConfig getBlurConfig(boolean z3) {
                    MaterialDayNightConfig materialDayNightConfig = ActionBarContainer.this.mMaterial;
                    if (materialDayNightConfig != null) {
                        return materialDayNightConfig.getBlurConfig(z3);
                    }
                    return null;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public boolean isLightTheme() {
                    Integer colorFromDrawable;
                    return (ActionBarContainer.this.mBackground == null || (colorFromDrawable = MiuixUIUtils.getColorFromDrawable(ActionBarContainer.this.mBackground)) == null) ? AttributeResolver.resolveBoolean(ActionBarContainer.this.getContext(), R.attr.isLightTheme, true) : MiuixUIUtils.isLightColor(colorFromDrawable.intValue());
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurApplyStateChanged(boolean z3) {
                    if (z3) {
                        ActionBarContainer.this.mDrawBackground = false;
                    } else {
                        ActionBarContainer.this.mDrawBackground = true;
                    }
                    if (ActionBarContainer.this.mActionBarView != null) {
                        ActionBarContainer.this.mActionBarView.setApplyBgBlur(z3);
                    }
                    if (ActionBarContainer.this.mActionBarContextView != null) {
                        ActionBarContainer.this.mActionBarContextView.updateBackground(z3);
                    }
                    ActionBarContainer.this.invalidate();
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurEnableStateChanged(boolean z3) {
                    if (ActionBarContainer.this.mIsSplit) {
                        ActionBarContainer.this.mInternalApplySpiltBgBlur = z3;
                        if (ActionBarContainer.this.mResidentActionMenuView != null) {
                            boolean zBooleanValue = ActionBarContainer.this.mUserApplySplitActionBarBgBlur != null ? ActionBarContainer.this.mUserApplySplitActionBarBgBlur.booleanValue() : ActionBarContainer.this.mInternalApplySpiltBgBlur;
                            if (z3) {
                                ActionBarContainer.this.mResidentActionMenuView.setSupportBlur(true);
                                ActionBarContainer.this.mResidentActionMenuView.setEnableBlur(true);
                            }
                            ActionBarContainer.this.mResidentActionMenuView.applyBlur(zBooleanValue);
                        }
                    }
                }
            });
        }
    }
}
