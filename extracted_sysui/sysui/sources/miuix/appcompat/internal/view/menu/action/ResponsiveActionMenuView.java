package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.appcompat.R;
import miuix.appcompat.app.floatingactivity.helper.BaseFloatingActivityHelper;
import miuix.appcompat.internal.view.OutDropShadowView;
import miuix.appcompat.internal.view.menu.action.ActionMenuView;
import miuix.core.util.ContextUtils;
import miuix.core.util.HyperMaterialUtils;
import miuix.core.util.MaterialConfig;
import miuix.core.util.MaterialDayNightConfig;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.smooth.SmoothCornerHelper;
import miuix.theme.token.hypermaterial.Blur;
import miuix.theme.token.hypermaterial.Mask;
import miuix.view.MiuiBlurUiHelper;

/* JADX INFO: loaded from: classes3.dex */
public class ResponsiveActionMenuView extends ActionMenuView {
    private static final int ITEM_NORMAL_PADDING_TOP_DP = 8;
    private static final int ITEM_SMALL_PADDING_TOP_DP = 5;
    private static final int MENU_ITEM_GAP_DP = 11;
    private static final int MENU_ITEM_GAP_DP_IN_LARGER_FONT = 16;
    private static final int SUSPEND_ITEM_CENTER_EXTRA_UP_DP = 2;
    private static final int SUSPEND_MENU_MIN_MARGIN_DP = 16;
    private static final int SUSPEND_MENU_MIN_WIDTH_DP = 196;
    private static final String TARGET = "target";
    private AnimConfig mAnimConfig;
    private boolean mApplyBlur;
    private AttributeSet mAttrs;

    @Nullable
    private Drawable mBackgroundInBlur;

    @Nullable
    private final MiuiBlurUiHelper mBlurUiHelper;
    private Drawable mBottomMenuBackground;
    private int mBottomMenuItemHeight;
    private final Context mContext;
    private View mCustomView;
    private Rect mCustomViewClipBounds;
    private int mDensityDpi;
    private boolean mHasOnlyCustomView;
    private boolean mIsCustomViewHidden;
    private boolean mIsEmpty;
    boolean mIsFloatingWindow;
    private boolean mIsHidden;
    private int mItemNormalPaddingTop;
    private int mItemSmallPaddingTop;
    private boolean mLargeFontAdaptionEnabled;

    @Nullable
    private MaterialDayNightConfig mMaterial;
    private int mMenuItemGap;
    private int mMenuItemHeight;
    private int mMenuItemWidth;
    private OutDropShadowView mMenuOutShadowView;
    private int mOffSet;
    private boolean[] mOriginViewParentClipState;
    private View.OnLayoutChangeListener mParentLayoutChangeListener;
    private boolean mSuspendEnabled;
    private int mSuspendItemCenterExtraUp;
    private Drawable mSuspendMenuBackground;
    private int mSuspendMenuBackgroundRadius;

    @ColorInt
    private int mSuspendMenuMiShadowColor;
    private float mSuspendMenuMiShadowRadius;
    private float mSuspendMenuMiShadowRadiusOffsetX;
    private float mSuspendMenuMiShadowRadiusOffsetY;
    private int mSuspendMenuMinMargin;
    private int mSuspendMenuMinWidth;
    private ViewOutlineProvider mViewOutlineInSuspend;

    public ResponsiveActionMenuView(Context context) {
        this(context, null);
    }

    private int getActionMenuItemCount() {
        int childCount = getChildCount();
        return indexOfChild(this.mCustomView) != -1 ? childCount - 1 : childCount;
    }

    private int getChildrenHeight(LinearLayout linearLayout) {
        int childCount = linearLayout.getChildCount();
        int measuredHeight = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            measuredHeight += linearLayout.getChildAt(i2).getMeasuredHeight();
        }
        return measuredHeight;
    }

    private Rect getCustomViewClipBounds() {
        if (this.mCustomViewClipBounds == null) {
            this.mCustomViewClipBounds = new Rect();
        }
        this.mCustomViewClipBounds.set(0, 0, this.mCustomView.getMeasuredWidth(), this.mCustomView.getMeasuredHeight() - this.mOffSet);
        return this.mCustomViewClipBounds;
    }

    private int getMaxChildrenTotalHeight() {
        int childrenHeight;
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!isNotActionMenuItemChild(childAt) && (childAt instanceof LinearLayout) && i2 < (childrenHeight = getChildrenHeight((LinearLayout) childAt))) {
                i2 = childrenHeight;
            }
        }
        return i2;
    }

    private boolean isNotActionMenuItemChild(View view) {
        return view == this.mCustomView;
    }

    private void keepHidden() {
        if (this.mIsHidden) {
            setTranslationY(ViewUtils.getMeasuredHeightWithMargin(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onAttachedToWindow$0(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        OutDropShadowView outDropShadowView = this.mMenuOutShadowView;
        if (outDropShadowView != null) {
            outDropShadowView.layout(getLeft(), getTop(), getRight(), getBottom());
        }
    }

    private void measureActionMenuItem(int i2, int i3, boolean z2) {
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (!isNotActionMenuItemChild(childAt)) {
                if (childAt instanceof LinearLayout) {
                    ((LinearLayout) childAt).setGravity(1);
                }
                if (z2) {
                    childAt.setPadding(0, 0, 0, 0);
                } else {
                    childAt.setPadding(0, (!MiuixUIUtils.isLayoutHideNavigation(this) || (MiuixUIUtils.isFullScreenGestureMode(this.mContext) && !MiuixUIUtils.isShowNavigationHandle(this.mContext))) ? this.mItemSmallPaddingTop : this.mItemNormalPaddingTop, 0, 0);
                }
                childAt.measure(i2, i3);
            }
        }
    }

    private void refreshBackground() {
        refreshMenuBackgroundDrawables(this.mAttrs);
        updateBackground();
    }

    private void refreshMenuBackgroundDrawables(AttributeSet attributeSet) {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            this.mIsFloatingWindow = BaseFloatingActivityHelper.isFloatingWindow(ContextUtils.getActivity(context));
            typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.ResponsiveActionMenuView, R.attr.responsiveActionMenuViewStyle, 0);
            this.mBottomMenuBackground = typedArrayObtainStyledAttributes.getDrawable(this.mIsFloatingWindow ? R.styleable.ResponsiveActionMenuView_floatingWindowBottomMenuBackground : R.styleable.ResponsiveActionMenuView_bottomMenuBackground);
            this.mSuspendMenuBackground = typedArrayObtainStyledAttributes.getDrawable(this.mIsFloatingWindow ? R.styleable.ResponsiveActionMenuView_floatingWindowSuspendMenuBackground : R.styleable.ResponsiveActionMenuView_suspendMenuBackground);
            typedArrayObtainStyledAttributes.recycle();
            if (HyperMaterialUtils.isEnable()) {
                this.mBackgroundInBlur = new ColorDrawable(0);
            }
        } catch (Throwable th) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            throw th;
        }
    }

    private void resetActionMenuItemPaddingTop(int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (!isNotActionMenuItemChild(childAt) && (childAt instanceof LinearLayout)) {
                ((LinearLayout) childAt).setPadding(0, i2, 0, 0);
            }
        }
    }

    private void restoreParentClipState(View view) {
        boolean[] zArr;
        if (!MiShadowUtils.SUPPORT_MI_SHADOW || (zArr = this.mOriginViewParentClipState) == null) {
            return;
        }
        int length = zArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Object parent = view.getParent();
            if (!(parent instanceof ViewGroup)) {
                break;
            }
            ((ViewGroup) parent).setClipChildren(this.mOriginViewParentClipState[i2]);
            view = (View) parent;
        }
        this.mOriginViewParentClipState = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBackground() {
        if (this.mSuspendEnabled) {
            setOutlineProvider(this.mViewOutlineInSuspend);
            setBackground(this.mApplyBlur ? this.mBackgroundInBlur : this.mSuspendMenuBackground);
            return;
        }
        setOutlineProvider(null);
        if (this.mBackgroundViewApplyBlur) {
            setBackground(null);
        } else {
            setBackground(this.mApplyBlur ? this.mBackgroundInBlur : this.mBottomMenuBackground);
        }
    }

    public void addCustomView(@NonNull View view) {
        if (view == null) {
            return;
        }
        this.mCustomView = view;
        addView(view);
    }

    @Override // miuix.view.BlurableWidget
    public void applyBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return;
        }
        miuiBlurUiHelper.applyBlur(z2);
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public void clearBackground() {
        setBackground(null);
    }

    public void clipParent(View view) {
        if (MiShadowUtils.SUPPORT_MI_SHADOW && this.mOriginViewParentClipState == null) {
            this.mOriginViewParentClipState = new boolean[2];
            for (int i2 = 0; i2 < 2; i2++) {
                Object parent = view.getParent();
                if (!(parent instanceof ViewGroup)) {
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) parent;
                this.mOriginViewParentClipState[i2] = viewGroup.getClipChildren();
                viewGroup.setClipChildren(false);
                view = (View) parent;
            }
        }
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, miuix.appcompat.internal.view.menu.MenuView
    public boolean filterLeftoverView(int i2) {
        View childAt = getChildAt(i2);
        if (isNotActionMenuItemChild(childAt)) {
            return false;
        }
        ActionMenuView.LayoutParams layoutParams = (ActionMenuView.LayoutParams) childAt.getLayoutParams();
        return (layoutParams == null || !layoutParams.isOverflowButton) && super.filterLeftoverView(i2);
    }

    public int getBottomMenuCustomViewOffset() {
        return this.mOffSet;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public int getCollapsedHeight() {
        if (this.mIsEmpty) {
            return 0;
        }
        int measuredHeightWithMargin = ViewUtils.getMeasuredHeightWithMargin(this);
        View view = (View) getParent();
        int measuredHeight = view != null ? view.getMeasuredHeight() : 0;
        if (measuredHeight <= 0) {
            return 0;
        }
        return Math.max(measuredHeight, measuredHeightWithMargin);
    }

    @Override // miuix.view.HyperMaterialWidget
    @Nullable
    public MaterialDayNightConfig getMaterial() {
        return this.mMaterial;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public boolean hasOnlyCustomView() {
        return this.mHasOnlyCustomView;
    }

    public void hideBottomMenuCustomView() {
        if (this.mCustomView == null || this.mIsCustomViewHidden) {
            return;
        }
        Folme.useValue(new Object[0]).setTo("target", Float.valueOf(0.0f)).to("target", Float.valueOf(this.mCustomView.getMeasuredHeight()), this.mAnimConfig);
        this.mIsCustomViewHidden = true;
    }

    @Override // miuix.view.BlurableWidget
    public boolean isApplyBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isApplyBlur();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isEnableBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isEnableBlur();
    }

    @Override // miuix.view.BlurableWidget
    public boolean isSupportBlur() {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return false;
        }
        return miuiBlurUiHelper.isSupportBlur();
    }

    public boolean isSuspend() {
        return this.mSuspendEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (MiShadowUtils.SUPPORT_MI_SHADOW) {
            if (isSuspend()) {
                clipParent(this);
                MiShadowUtils.setMiShadow(this, this.mSuspendMenuMiShadowColor, this.mSuspendMenuMiShadowRadiusOffsetX, this.mSuspendMenuMiShadowRadiusOffsetY, this.mSuspendMenuBackgroundRadius);
                return;
            } else {
                restoreParentClipState(this);
                MiShadowUtils.clearMiShadow(this);
                return;
            }
        }
        if (!isSuspend()) {
            OutDropShadowView outDropShadowView = this.mMenuOutShadowView;
            if (outDropShadowView != null) {
                outDropShadowView.onWillRemoved();
                ViewGroup viewGroup = (ViewGroup) getParent();
                viewGroup.removeOnLayoutChangeListener(this.mParentLayoutChangeListener);
                viewGroup.removeView(this.mMenuOutShadowView);
                this.mMenuOutShadowView = null;
                return;
            }
            return;
        }
        if (this.mMenuOutShadowView == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0);
            OutDropShadowView outDropShadowView2 = new OutDropShadowView(getContext());
            this.mMenuOutShadowView = outDropShadowView2;
            outDropShadowView2.setShadowHostViewRadius(this.mSuspendMenuBackgroundRadius);
            ViewGroup viewGroup2 = (ViewGroup) getParent();
            viewGroup2.addView(this.mMenuOutShadowView, layoutParams);
            View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: miuix.appcompat.internal.view.menu.action.c
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    this.f6086a.lambda$onAttachedToWindow$0(view, i2, i3, i4, i5, i6, i7, i8, i9);
                }
            };
            this.mParentLayoutChangeListener = onLayoutChangeListener;
            viewGroup2.addOnLayoutChangeListener(onLayoutChangeListener);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper != null) {
            miuiBlurUiHelper.onConfigChanged();
        }
        int i2 = configuration.densityDpi;
        if (i2 == this.mDensityDpi) {
            if (this.mIsFloatingWindow != BaseFloatingActivityHelper.isFloatingWindow(ContextUtils.getActivity(this.mContext))) {
                this.mIsFloatingWindow = BaseFloatingActivityHelper.isFloatingWindow(this.mContext);
                refreshBackground();
                return;
            }
            return;
        }
        this.mDensityDpi = i2;
        this.mMenuItemGap = this.mLargeFontAdaptionEnabled ? MiuixUIUtils.dp2px(this.mContext, 16.0f) : MiuixUIUtils.dp2px(this.mContext, 11.0f);
        this.mSuspendMenuMinMargin = MiuixUIUtils.dp2px(this.mContext, 16.0f);
        this.mSuspendMenuMinWidth = MiuixUIUtils.dp2px(this.mContext, 196.0f);
        this.mItemNormalPaddingTop = MiuixUIUtils.dp2px(this.mContext, 8.0f);
        this.mItemSmallPaddingTop = MiuixUIUtils.dp2px(this.mContext, 5.0f);
        this.mSuspendItemCenterExtraUp = MiuixUIUtils.dp2px(this.mContext, 2.0f);
        Resources resources = getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_bottom_menu_height);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_bottom_menu_height_in_large_font);
        if (this.mLargeFontAdaptionEnabled) {
            dimensionPixelSize = dimensionPixelSize2;
        }
        this.mBottomMenuItemHeight = dimensionPixelSize;
        this.mSuspendMenuBackgroundRadius = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_bg_radius);
        this.mSuspendMenuMiShadowRadius = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius);
        this.mSuspendMenuMiShadowRadiusOffsetX = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius_offset_x);
        this.mSuspendMenuMiShadowRadiusOffsetY = resources.getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius_offset_y);
        if (MiShadowUtils.SUPPORT_MI_SHADOW) {
            if (isSuspend()) {
                MiShadowUtils.setMiShadow(this, this.mSuspendMenuMiShadowColor, this.mSuspendMenuMiShadowRadiusOffsetX, this.mSuspendMenuMiShadowRadiusOffsetY, this.mSuspendMenuMiShadowRadius);
            } else {
                MiShadowUtils.clearMiShadow(this);
            }
        }
        refreshBackground();
        OutDropShadowView outDropShadowView = this.mMenuOutShadowView;
        if (outDropShadowView != null) {
            outDropShadowView.setShadowHostViewRadius(this.mSuspendMenuBackgroundRadius);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        applyBlur(false);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0054 A[PHI: r3
      0x0054: PHI (r3v1 int) = (r3v0 int), (r3v0 int), (r3v4 int) binds: [B:11:0x002e, B:13:0x0034, B:15:0x0050] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        /*
            r10 = this;
            int r11 = r10.getMeasuredWidth()
            int r12 = r10.getMeasuredHeight()
            boolean r13 = r10.mHasOnlyCustomView
            r14 = 8
            r2 = 0
            r3 = 0
            if (r13 == 0) goto L2b
            android.view.View r11 = r10.mCustomView
            if (r11 == 0) goto L2a
            int r11 = r11.getVisibility()
            if (r11 == r14) goto L2a
            android.view.View r1 = r10.mCustomView
            int r4 = r1.getMeasuredWidth()
            android.view.View r11 = r10.mCustomView
            int r5 = r11.getMeasuredHeight()
            r0 = r10
            miuix.internal.util.ViewUtils.layoutChildView(r0, r1, r2, r3, r4, r5)
        L2a:
            return
        L2b:
            android.view.View r13 = r10.mCustomView
            r15 = 0
            if (r13 == 0) goto L54
            int r13 = r13.getVisibility()
            if (r13 == r14) goto L54
            android.view.View r1 = r10.mCustomView
            int r4 = r1.getMeasuredWidth()
            android.view.View r13 = r10.mCustomView
            int r5 = r13.getMeasuredHeight()
            r0 = r10
            miuix.internal.util.ViewUtils.layoutChildView(r0, r1, r2, r3, r4, r5)
            android.view.View r13 = r10.mCustomView
            int r13 = r13.getMeasuredHeight()
            int r14 = r10.mOffSet
            int r3 = r13 - r14
            if (r3 >= 0) goto L54
            r13 = r15
            goto L55
        L54:
            r13 = r3
        L55:
            int r14 = r10.getChildCount()
            int r0 = r10.getActionMenuItemCount()
            int r1 = r10.getPaddingStart()
            int r1 = r11 - r1
            int r2 = r10.getPaddingEnd()
            int r1 = r1 - r2
            int r2 = r10.mMenuItemWidth
            int r2 = r2 * r0
            r6 = 1
            int r0 = r0 - r6
            int r3 = r10.mMenuItemGap
            int r0 = r0 * r3
            int r2 = r2 + r0
            int r0 = r10.getPaddingStart()
            int r1 = r1 - r2
            int r1 = r1 / 2
            int r0 = r0 + r1
            r8 = r15
            r7 = r0
        L7b:
            if (r8 >= r14) goto La1
            android.view.View r9 = r10.getChildAt(r8)
            boolean r0 = r10.isNotActionMenuItemChild(r9)
            if (r0 == 0) goto L88
            goto L9e
        L88:
            int r0 = r9.getMeasuredWidth()
            int r4 = r7 + r0
            r0 = r10
            r1 = r9
            r2 = r7
            r3 = r13
            r5 = r12
            miuix.internal.util.ViewUtils.layoutChildView(r0, r1, r2, r3, r4, r5)
            int r0 = r9.getMeasuredWidth()
            int r1 = r10.mMenuItemGap
            int r0 = r0 + r1
            int r7 = r7 + r0
        L9e:
            int r8 = r8 + 1
            goto L7b
        La1:
            boolean r13 = r10.isEnableBlur()
            if (r13 == 0) goto Lac
            if (r11 <= 0) goto Lac
            if (r12 <= 0) goto Lac
            r15 = r6
        Lac:
            r10.applyBlur(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView.onLayout(boolean, int, int, int, int):void");
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView, android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        this.mHasOnlyCustomView = false;
        this.mIsEmpty = false;
        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop() + paddingBottom;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int actionMenuItemCount = getActionMenuItemCount();
        int size = View.MeasureSpec.getSize(i2);
        if (childCount == 0 || actionMenuItemCount == 0) {
            this.mMenuItemHeight = 0;
            View view = this.mCustomView;
            if (view == null || view.getVisibility() == 8) {
                this.mIsEmpty = true;
                setMeasuredDimension(0, 0);
            } else {
                this.mHasOnlyCustomView = true;
                ActionMenuView.LayoutParams layoutParams = (ActionMenuView.LayoutParams) this.mCustomView.getLayoutParams();
                if (this.mSuspendEnabled) {
                    this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(size - (this.mSuspendMenuMinMargin * 2), BasicMeasure.EXACTLY), LinearLayout.getChildMeasureSpec(i3, paddingTop, ((LinearLayout.LayoutParams) layoutParams).height));
                } else {
                    this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(size, BasicMeasure.EXACTLY), LinearLayout.getChildMeasureSpec(i3, paddingTop, ((LinearLayout.LayoutParams) layoutParams).height));
                }
                this.mCustomView.setClipBounds(getCustomViewClipBounds());
                int measuredWidth = this.mCustomView.getMeasuredWidth();
                int measuredHeight = (this.mCustomView.getMeasuredHeight() + paddingTop) - this.mOffSet;
                setMeasuredDimension(measuredWidth, measuredHeight >= 0 ? measuredHeight : 0);
            }
            keepHidden();
            return;
        }
        if (this.mSuspendEnabled) {
            this.mMenuItemWidth = MiuixUIUtils.dp2px(this.mContext, 115.0f);
            int iDp2px = MiuixUIUtils.dp2px(this.mContext, 80.0f);
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iDp2px, Integer.MIN_VALUE);
            int i4 = (actionMenuItemCount - 1) * this.mMenuItemGap;
            int i5 = (this.mMenuItemWidth * actionMenuItemCount) + i4;
            int i6 = this.mSuspendMenuMinMargin;
            if (i5 >= size - (i6 * 2)) {
                this.mMenuItemWidth = (((size - paddingLeft) - (i6 * 2)) - i4) / actionMenuItemCount;
            }
            measureActionMenuItem(View.MeasureSpec.makeMeasureSpec(this.mMenuItemWidth, BasicMeasure.EXACTLY), iMakeMeasureSpec, true);
            resetActionMenuItemPaddingTop((iDp2px - (getMaxChildrenTotalHeight() + (this.mSuspendItemCenterExtraUp * 2))) / 2);
            this.mMenuItemHeight = iDp2px;
            size = Math.max((this.mMenuItemWidth * actionMenuItemCount) + paddingLeft + i4, this.mSuspendMenuMinWidth);
        } else {
            int i7 = ((size - paddingLeft) - ((actionMenuItemCount - 1) * this.mMenuItemGap)) / actionMenuItemCount;
            this.mMenuItemWidth = i7;
            int i8 = this.mBottomMenuItemHeight + paddingBottom;
            measureActionMenuItem(View.MeasureSpec.makeMeasureSpec(i7, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i8, BasicMeasure.EXACTLY), this.mSuspendEnabled);
            this.mMenuItemHeight = i8;
        }
        int measuredHeight2 = this.mMenuItemHeight + paddingTop;
        if (!this.mSuspendEnabled) {
            measuredHeight2 -= paddingBottom;
        }
        View view2 = this.mCustomView;
        if (view2 != null && view2.getVisibility() != 8) {
            this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(size, BasicMeasure.EXACTLY), LinearLayout.getChildMeasureSpec(i3, paddingTop, ((LinearLayout.LayoutParams) ((ActionMenuView.LayoutParams) this.mCustomView.getLayoutParams())).height));
            this.mCustomView.setClipBounds(getCustomViewClipBounds());
            measuredHeight2 = (measuredHeight2 + this.mCustomView.getMeasuredHeight()) - this.mOffSet;
        }
        setMeasuredDimension(size, measuredHeight2);
        keepHidden();
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        applyBlur(false);
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public void onWillRemoved() {
        super.onWillRemoved();
        applyBlur(false);
        restoreParentClipState(this);
        OutDropShadowView outDropShadowView = this.mMenuOutShadowView;
        if (outDropShadowView != null) {
            outDropShadowView.onWillRemoved();
            ViewGroup viewGroup = (ViewGroup) getParent();
            viewGroup.removeView(this.mMenuOutShadowView);
            viewGroup.removeOnLayoutChangeListener(this.mParentLayoutChangeListener);
            this.mMenuOutShadowView = null;
        }
    }

    public void removeCustomView() {
        View view = this.mCustomView;
        if (view == null || view.getParent() == null) {
            return;
        }
        removeView(this.mCustomView);
        this.mOffSet = 0;
        this.mCustomView = null;
        this.mIsCustomViewHidden = false;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuView
    public void resetBackground() {
        updateBackground();
    }

    public void setBottomMenuCustomViewTranslationYWithPx(int i2) {
        if (this.mCustomView == null || i2 < 0) {
            return;
        }
        this.mOffSet = i2;
        requestLayout();
    }

    @Override // miuix.view.BlurableWidget
    public void setEnableBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return;
        }
        miuiBlurUiHelper.setEnableBlur(z2);
        applyBlur(z2 && getMeasuredWidth() > 0 && getMeasuredHeight() > 0);
    }

    public void setHidden(boolean z2) {
        this.mIsHidden = z2;
    }

    @Override // miuix.view.HyperMaterialWidget
    public void setMaterial(@Nullable MaterialDayNightConfig materialDayNightConfig) {
        boolean z2 = this.mMaterial == null && materialDayNightConfig != null;
        if (materialDayNightConfig == null) {
            this.mMaterial = null;
            applyBlur(false);
            return;
        }
        this.mMaterial = materialDayNightConfig;
        if (this.mBlurUiHelper != null) {
            if (!isApplyBlur() && z2) {
                applyBlur(true);
            }
            this.mBlurUiHelper.onConfigChanged();
        }
    }

    @Override // miuix.view.BlurableWidget
    public void setSupportBlur(boolean z2) {
        MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
        if (miuiBlurUiHelper == null) {
            return;
        }
        miuiBlurUiHelper.setSupportBlur(z2);
    }

    public void setSuspendEnabled(boolean z2) {
        if (this.mSuspendEnabled != z2) {
            this.mSuspendEnabled = z2;
            MiuiBlurUiHelper miuiBlurUiHelper = this.mBlurUiHelper;
            if (miuiBlurUiHelper != null) {
                miuiBlurUiHelper.resetBlurParams();
                this.mBlurUiHelper.refreshBlur();
            }
        }
        updateBackground();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        super.setTranslationY(f2);
        OutDropShadowView outDropShadowView = this.mMenuOutShadowView;
        if (outDropShadowView != null) {
            outDropShadowView.setTranslationY(f2);
        }
    }

    public void showBottomMenuCustomView() {
        if (this.mCustomView == null || !this.mIsCustomViewHidden) {
            return;
        }
        Folme.useValue(new Object[0]).setTo("target", Float.valueOf(this.mCustomView.getMeasuredHeight())).to("target", Float.valueOf(0.0f), this.mAnimConfig);
        this.mIsCustomViewHidden = false;
    }

    public ResponsiveActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSuspendEnabled = false;
        this.mHasOnlyCustomView = false;
        this.mIsEmpty = false;
        this.mMenuOutShadowView = null;
        this.mParentLayoutChangeListener = null;
        this.mOffSet = 0;
        this.mIsHidden = false;
        this.mIsCustomViewHidden = false;
        this.mApplyBlur = false;
        this.mLargeFontAdaptionEnabled = false;
        this.mViewOutlineInSuspend = new ViewOutlineProvider() { // from class: miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), ResponsiveActionMenuView.this.mSuspendMenuBackgroundRadius);
            }
        };
        this.mAnimConfig = new AnimConfig().setEase(-2, 0.9f, 0.25f).addListeners(new TransitionListener() { // from class: miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView.2
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                ResponsiveActionMenuView.this.mOffSet = UpdateInfo.findByName(collection, "target").getIntValue();
                ResponsiveActionMenuView.this.requestLayout();
            }
        });
        boolean z2 = AttributeResolver.resolveBoolean(context, R.attr.largeFontAdaptationEnabled, true) && MiuixUIUtils.getFontLevel(context) == 2;
        this.mLargeFontAdaptionEnabled = z2;
        this.mMenuItemGap = z2 ? MiuixUIUtils.dp2px(context, 16.0f) : MiuixUIUtils.dp2px(context, 11.0f);
        Resources resources = context.getResources();
        this.mBottomMenuItemHeight = this.mLargeFontAdaptionEnabled ? resources.getDimensionPixelSize(R.dimen.miuix_appcompat_bottom_menu_height_in_large_font) : resources.getDimensionPixelSize(R.dimen.miuix_appcompat_bottom_menu_height);
        this.mSuspendMenuMinMargin = MiuixUIUtils.dp2px(context, 16.0f);
        this.mSuspendMenuMinWidth = MiuixUIUtils.dp2px(context, 196.0f);
        this.mItemNormalPaddingTop = MiuixUIUtils.dp2px(context, 8.0f);
        this.mItemSmallPaddingTop = MiuixUIUtils.dp2px(context, 5.0f);
        this.mSuspendItemCenterExtraUp = MiuixUIUtils.dp2px(context, 2.0f);
        this.mSuspendMenuBackgroundRadius = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_bg_radius);
        this.mSuspendMenuMiShadowColor = context.getResources().getColor(R.color.miuix_appcompat_suspend_menu_mi_shadow);
        this.mSuspendMenuMiShadowRadius = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius);
        this.mSuspendMenuMiShadowRadiusOffsetX = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius_offset_x);
        this.mSuspendMenuMiShadowRadiusOffsetY = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_suspend_menu_mi_shadow_radius_offset_y);
        this.mDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
        this.mContext = context;
        this.mAttrs = attributeSet;
        setClickable(true);
        refreshMenuBackgroundDrawables(attributeSet);
        setClipToPadding(false);
        setWillNotDraw(false);
        SmoothCornerHelper.setViewSmoothCornerEnable(this, true);
        if (HyperMaterialUtils.isEnable()) {
            this.mMaterial = MaterialDayNightConfig.create(RomUtils.getHyperOsVersion() > 2 ? Mask.Pured_Regular : Blur.ExtraHeavy);
            this.mBlurUiHelper = new MiuiBlurUiHelper(context, this, false, false, new MiuiBlurUiHelper.BlurStateCallback() { // from class: miuix.appcompat.internal.view.menu.action.ResponsiveActionMenuView.3
                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public Drawable getBackground() {
                    boolean z3 = ResponsiveActionMenuView.this.mSuspendEnabled;
                    ResponsiveActionMenuView responsiveActionMenuView = ResponsiveActionMenuView.this;
                    return z3 ? responsiveActionMenuView.mSuspendMenuBackground : responsiveActionMenuView.mBottomMenuBackground;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                @Nullable
                public MaterialConfig.BlurConfig getBlurConfig(boolean z3) {
                    MaterialDayNightConfig materialDayNightConfig = ResponsiveActionMenuView.this.mMaterial;
                    if (materialDayNightConfig != null) {
                        return materialDayNightConfig.getBlurConfig(z3);
                    }
                    return null;
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public boolean isLightTheme() {
                    Integer colorFromDrawable;
                    Drawable drawable = ResponsiveActionMenuView.this.mSuspendEnabled ? ResponsiveActionMenuView.this.mSuspendMenuBackground : ResponsiveActionMenuView.this.mBottomMenuBackground;
                    return (drawable == null || (colorFromDrawable = MiuixUIUtils.getColorFromDrawable(drawable)) == null) ? AttributeResolver.resolveBoolean(ResponsiveActionMenuView.this.getContext(), R.attr.isLightTheme, true) : MiuixUIUtils.isLightColor(colorFromDrawable.intValue());
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurApplyStateChanged(boolean z3) {
                    ResponsiveActionMenuView.this.mApplyBlur = z3;
                    ResponsiveActionMenuView.this.updateBackground();
                }

                @Override // miuix.view.MiuiBlurUiHelper.BlurStateCallback
                public void onBlurEnableStateChanged(boolean z3) {
                }
            });
        } else {
            this.mBlurUiHelper = null;
        }
        updateBackground();
    }
}
