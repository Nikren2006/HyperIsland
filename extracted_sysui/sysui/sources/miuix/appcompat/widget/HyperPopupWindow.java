package miuix.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.Collection;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.ViewProperty;
import miuix.appcompat.R;
import miuix.appcompat.view.menu.HyperBaseAdapter;
import miuix.appcompat.view.menu.HyperMenuAdapter;
import miuix.appcompat.view.menu.HyperMenuContract;
import miuix.appcompat.view.menu.HyperSecondaryAdapter;
import miuix.appcompat.widget.HyperPopupWindow;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.popupwidget.internal.strategy.IPopupWindowStrategy;
import miuix.popupwidget.internal.strategy.PopupWindowSpec;
import miuix.popupwidget.internal.strategy.PopupWindowStrategy;
import miuix.popupwidget.internal.util.SinglePopControl;
import miuix.popupwidget.widget.PopupAnimHelper;
import miuix.popupwidget.widget.PopupWindow;
import miuix.smooth.SmoothContainerDrawable2;
import miuix.smooth.SmoothFrameLayout2;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
public class HyperPopupWindow extends PopupWindow {
    private static final String TAG = "HyperPopupWindow";
    private int mAnimationExtensionMargin;
    private ClipLayout mClipView;
    private ViewGroup mContainer;
    private final float mCornerRadius;
    private boolean mEnableFolmeAnimation;
    private boolean mEnableSecondaryMenu;
    private View mFocusedMainPopupItemView;
    private ClipLayout mInnerClip;
    private PopupContentHolder mMainPopContentHolder;
    protected IPopupWindowStrategy mMainPopupStrategy;
    private OnMenuItemClickListener mMenuItemClickListener;
    private Rect mRootBounds;
    private PopupContentHolder mSecondaryContentHolder;
    private IPopupWindowStrategy mSecondaryPopupStrategy;

    /* JADX INFO: renamed from: miuix.appcompat.widget.HyperPopupWindow$1, reason: invalid class name */
    public class AnonymousClass1 implements AdapterView.OnItemClickListener {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onItemClick$0(ListAdapter listAdapter, AdapterView adapterView, View view, int i2, long j2) {
            HyperMenuContract.HyperMenuTextItem textItem;
            if (view.getId() == R.id.tag_secondary_popup_menu_item_head) {
                HyperPopupWindow.this.collapseSecondaryMenu();
                return;
            }
            if ((listAdapter instanceof HyperSecondaryAdapter) && (textItem = HyperPopupWindow.this.getTextItem(listAdapter, i2)) != null && !textItem.isHeaderItem) {
                ((HyperSecondaryAdapter) listAdapter).resumeSecondaryItemClickStatus((int) j2);
            }
            if (HyperPopupWindow.this.mMenuItemClickListener != null) {
                HyperPopupWindow.this.mMenuItemClickListener.onMenuItemClick((MenuItem) listAdapter.getItem(i2));
            }
            HyperPopupWindow.this.dismiss();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onItemClick$1(View view) {
            HyperPopupWindow.this.collapseSecondaryMenu();
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
            final BaseAdapter secondaryAdapterByItemId;
            if (HyperPopupWindow.this.mMainPopContentHolder.mAdapter instanceof HyperMenuAdapter) {
                secondaryAdapterByItemId = ((HyperMenuAdapter) HyperPopupWindow.this.mMainPopContentHolder.mAdapter).getSecondaryAdapterByItemId(j2);
                HyperPopupWindow hyperPopupWindow = HyperPopupWindow.this;
                HyperMenuContract.HyperMenuTextItem textItem = hyperPopupWindow.getTextItem((HyperMenuAdapter) hyperPopupWindow.mMainPopContentHolder.mAdapter, i2);
                if (textItem != null && !textItem.isExpandable) {
                    ((HyperMenuAdapter) HyperPopupWindow.this.mMainPopContentHolder.mAdapter).resumePrimaryItemClickStatus((int) j2, i2);
                }
            } else {
                secondaryAdapterByItemId = null;
            }
            if (HyperPopupWindow.this.mSecondaryContentHolder == null) {
                if (secondaryAdapterByItemId != null) {
                    HyperPopupWindow.this.mSecondaryPopupStrategy = new SecondaryPopupWindowStrategy();
                    HyperPopupWindow.this.expandSecondaryMenu(view, secondaryAdapterByItemId);
                    HyperPopupWindow.this.mSecondaryContentHolder.setItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.appcompat.widget.b
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView adapterView2, View view2, int i3, long j3) {
                            this.f6090a.lambda$onItemClick$0(secondaryAdapterByItemId, adapterView2, view2, i3, j3);
                        }
                    });
                } else {
                    if (HyperPopupWindow.this.mMenuItemClickListener != null && HyperPopupWindow.this.mMainPopContentHolder != null && HyperPopupWindow.this.mMainPopContentHolder.mAdapter != null) {
                        HyperPopupWindow.this.mMenuItemClickListener.onMenuItemClick((MenuItem) HyperPopupWindow.this.mMainPopContentHolder.mAdapter.getItem(i2));
                    }
                    HyperPopupWindow.this.dismiss();
                }
                HyperPopupWindow.this.mContainer.findViewById(R.id.mask).setOnClickListener(new View.OnClickListener() { // from class: miuix.appcompat.widget.c
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f6092a.lambda$onItemClick$1(view2);
                    }
                });
            }
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(MenuItem menuItem);
    }

    public static class SecondaryPopupWindowStrategy extends PopupWindowStrategy {
        @Override // miuix.popupwidget.internal.strategy.PopupWindowStrategy, miuix.popupwidget.internal.strategy.IPopupWindowStrategy
        public int getXInWindow(PopupWindowSpec popupWindowSpec) {
            Rect rect = popupWindowSpec.mAnchorViewBounds;
            Rect rect2 = popupWindowSpec.mDecorViewBounds;
            int i2 = popupWindowSpec.mFinalPopupWidth;
            int i3 = rect.left;
            int i4 = i3 + i2;
            int i5 = rect2.right;
            if (i4 > i5) {
                i3 = i5 - i2;
                i4 = i5;
            }
            int i6 = rect2.left;
            if (i3 < i6) {
                i3 = i6;
            }
            popupWindowSpec.mFinalPopupWidth = i4 - i3;
            return i3;
        }

        @Override // miuix.popupwidget.internal.strategy.PopupWindowStrategy, miuix.popupwidget.internal.strategy.IPopupWindowStrategy
        public int getYInWindow(PopupWindowSpec popupWindowSpec) {
            Rect rect = popupWindowSpec.mAnchorViewBounds;
            Rect rect2 = popupWindowSpec.mDecorViewBounds;
            int i2 = popupWindowSpec.mFinalPopupHeight;
            int i3 = rect.top;
            int i4 = i3 + i2;
            int i5 = rect2.bottom;
            if (i4 < i5) {
                return i3;
            }
            int i6 = i5 - i2;
            int i7 = rect2.top;
            if (i6 >= i7) {
                return i6;
            }
            popupWindowSpec.mFinalPopupHeight = i5 - i7;
            return i7;
        }
    }

    public static class ViewBounds {
        private static final String PROPERTY_FRACTION = "fraction";
        private WeakReference<View> mHeaderArrowView;
        private WeakReference<View> mView;
        private static final AnimConfig sOpenConfig = new AnimConfig();
        private static final AnimConfig sCloseConfig = new AnimConfig().setEase(-2, 0.95f, 0.2f);
        private int mMeasureWidth = -1;
        private float mCornerRadius = 0.0f;
        private float mArrowRotation = 0.0f;
        private AnimConfig mArrowOpenConfig = new AnimConfig().setEase(-2, 0.95f, 0.2f);
        private AnimConfig mArrowCloseConfig = new AnimConfig().setEase(-2, 0.95f, 0.3f);
        private final FloatProperty<ViewBounds> mArrowRotationProperty = new ArrowRotationProperty();
        private final FloatProperty<ViewBounds> mCornerProperty = new CornerProperty();

        public static class ArrowRotationProperty extends FloatProperty<ViewBounds> {
            public ArrowRotationProperty() {
                super("arrowRotation");
            }

            @Override // miuix.animation.property.FloatProperty
            public float getValue(ViewBounds viewBounds) {
                return viewBounds.getArrowRotation();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ViewBounds viewBounds, float f2) {
                viewBounds.setArrowRotation(f2);
            }
        }

        public static class CornerProperty extends FloatProperty<ViewBounds> {
            public CornerProperty() {
                super("corner");
            }

            @Override // miuix.animation.property.FloatProperty
            public float getValue(ViewBounds viewBounds) {
                return viewBounds.getCornerRadius();
            }

            @Override // miuix.animation.property.FloatProperty
            public void setValue(ViewBounds viewBounds, float f2) {
                viewBounds.setCornerRadius(f2);
            }
        }

        public ViewBounds(View view) {
            this.mView = new WeakReference<>(view);
        }

        public float getArrowRotation() {
            return this.mArrowRotation;
        }

        public float getCornerRadius() {
            return this.mCornerRadius;
        }

        public void setArrowRotation(float f2) {
            this.mArrowRotation = f2;
            WeakReference<View> weakReference = this.mHeaderArrowView;
            if (weakReference == null || weakReference.get() == null) {
                if (this.mView.get() == null) {
                    return;
                }
                View viewFindViewById = this.mView.get().findViewById(R.id.tag_secondary_popup_menu_item_head).findViewById(R.id.arrow);
                this.mHeaderArrowView = new WeakReference<>(viewFindViewById);
                viewFindViewById.setPivotX(viewFindViewById.getWidth() / 2.0f);
                viewFindViewById.setPivotY(viewFindViewById.getHeight() / 2.0f);
            }
            this.mHeaderArrowView.get().setRotation(f2);
        }

        public void setCornerRadius(float f2) {
            this.mCornerRadius = f2;
            View view = this.mView.get();
            Drawable background = view.getBackground();
            if (view instanceof SmoothFrameLayout2) {
                ((SmoothFrameLayout2) view).setCornerRadius(this.mCornerRadius);
            }
            if (background instanceof SmoothContainerDrawable2) {
                ((SmoothContainerDrawable2) background).setCornerRadius(this.mCornerRadius);
            }
            ((ClipLayout) view.getParent()).setRadius(f2);
        }

        public void setMeasureWidth(int i2) {
            this.mMeasureWidth = i2;
        }

        public void updateLeftTopRightBottom(int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            View view = this.mView.get();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = i2;
            layoutParams.topMargin = i3;
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.list);
            for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
                View childAt = viewGroup.getChildAt(i9);
                AbsListView.LayoutParams layoutParams2 = (AbsListView.LayoutParams) viewGroup.getChildAt(i9).getLayoutParams();
                if (childAt.getId() != R.id.tag_secondary_popup_menu_item_head) {
                    layoutParams2.width = this.mMeasureWidth;
                } else {
                    layoutParams2.width = i4 - i2;
                    layoutParams2.height = i6;
                    childAt.setPadding(childAt.getPaddingLeft(), i7, childAt.getPaddingRight(), i8);
                    childAt.requestLayout();
                }
            }
        }
    }

    public HyperPopupWindow(Context context) {
        this(context, null);
    }

    public static /* synthetic */ void access$900(HyperPopupWindow hyperPopupWindow) {
        hyperPopupWindow.collapseSecondaryMenu();
    }

    private void announceForSecondaryMenu(String str) {
        if (this.mMainPopContentHolder.mContentView != null) {
            this.mMainPopContentHolder.mContentView.announceForAccessibility(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collapseSecondaryMenu() {
        PopupContentHolder popupContentHolder = this.mSecondaryContentHolder;
        if (popupContentHolder == null) {
            return;
        }
        popupContentHolder.mContentView.findViewById(R.id.mask).setVisibility(0);
        toFrontAnim();
        doCollapseAnimation();
        this.mSecondaryContentHolder = null;
        enableMainMenuAccessibility(this.mMainPopContentHolder.mContentView);
        announceForSecondaryMenu(this.mContext.getResources().getString(R.string.miuix_appcompat_accessibility_collapse_state));
    }

    private void disableMainMenuAccessibility(View view) {
        if (view != null) {
            view.setImportantForAccessibility(4);
        }
    }

    private void enableAccessibility(View view, boolean z2) {
        view.setImportantForAccessibility(z2 ? 1 : 2);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                enableAccessibility(viewGroup.getChildAt(i2), z2);
            }
        }
    }

    private void enableMainMenuAccessibility(View view) {
        if (view != null) {
            view.setImportantForAccessibility(1);
        }
        View view2 = this.mFocusedMainPopupItemView;
        if (view2 != null) {
            view2.post(new Runnable() { // from class: miuix.appcompat.widget.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6089a.lambda$enableMainMenuAccessibility$0();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expandSecondaryMenu(View view, ListAdapter listAdapter) {
        toBackAnim();
        doExpandAnimation(view, listAdapter);
        disableMainMenuAccessibility(this.mMainPopContentHolder.mContentView);
        announceForSecondaryMenu(this.mContext.getResources().getString(R.string.miuix_appcompat_accessibility_expand_state));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[][] getItemViewBounds(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i2, int i3) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, count, 2);
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (i3 != -1) {
                measuredWidth = Math.max(measuredWidth, i3);
            }
            int[] iArr2 = iArr[i4];
            iArr2[0] = measuredWidth;
            iArr2[1] = view.getMeasuredHeight();
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Rect getRootBounds() {
        Rect rect = new Rect();
        PopupWindowSpec popupWindowSpecClone = this.mPopupWindowSpec.clone();
        Rect rect2 = popupWindowSpecClone.mDecorViewBounds;
        int i2 = rect2.left;
        Rect rect3 = popupWindowSpecClone.mSafeInsets;
        rect.set(i2 + rect3.left, rect2.top + rect3.top, rect2.right - rect3.right, rect2.bottom - rect3.bottom);
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HyperMenuContract.HyperMenuTextItem getTextItem(Object obj, int i2) {
        HyperMenuContract.HyperMenuItem hyperMenuItem = obj instanceof HyperBaseAdapter ? ((HyperBaseAdapter) obj).getHyperMenuItem(i2) : null;
        if (hyperMenuItem instanceof HyperMenuContract.HyperMenuTextItem) {
            return (HyperMenuContract.HyperMenuTextItem) hyperMenuItem;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rect getUnionBounds(Rect rect, Rect rect2) {
        Rect rect3 = new Rect();
        rect3.left = Math.min(rect.left, rect2.left);
        rect3.top = Math.min(rect.top, rect2.top);
        rect3.right = Math.max(rect.right, rect2.right);
        rect3.bottom = Math.max(rect.bottom, rect2.bottom);
        return rect3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$enableMainMenuAccessibility$0() {
        this.mFocusedMainPopupItemView.sendAccessibilityEvent(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void offsetRootBounds(PopupWindowSpec popupWindowSpec, int i2, int i3, int i4, int i5) {
        Rect rect = this.mRootBounds;
        int i6 = popupWindowSpec.mMaxWidth;
        int i7 = popupWindowSpec.mMaxHeight + i3;
        int i8 = rect.bottom;
        if (i7 > i8) {
            rect.top = i3 + (i8 - i7);
        } else {
            rect.top = i3;
            rect.bottom = i7;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(popupWindowSpec.mGravity, popupWindowSpec.layoutDirection) & 7;
        if (absoluteGravity != 1) {
            if (absoluteGravity != 5) {
                rect.left = i2;
                rect.right = Math.min(i2 + i6, rect.right);
            } else {
                rect.right = i4 + i2;
                rect.left = Math.max(i2 - i6, rect.left);
            }
        }
    }

    private void prepareWindowElevation() {
        if (shouldSetElevation()) {
            setElevation(this.mElevation + this.mElevationExtra);
        }
        prepareWindowElevation(this.mMainPopContentHolder.mContentView, this.mElevation + this.mElevationExtra);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeAddView(@NonNull ViewGroup viewGroup, @NonNull View view) {
        if (viewGroup == null || view == null) {
            return;
        }
        safeAddView(viewGroup, view, -1);
    }

    private void showAtLocation(View view, int i2, int i3, int i4, Rect rect) {
        prepareWindowElevation();
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        int width = getWidth();
        int height = getHeight();
        if (rect == null) {
            rect = new Rect();
            rect.set(i3, i4, width + i3, height + i4);
        }
        showWithAnim(PopupWindow.computeGravity(rect2, rect, 0, view.getLayoutDirection()));
        if (!isShowing()) {
            HapticCompat.performHapticFeedback(this.mRootView, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        superShowAtLocation(view, i2, i3, i4);
        this.mRootView.setElevation(0.0f);
        if (this.mWindowAnimationEnabled || this.mAnimHelper == null) {
            changeWindowBackground(this.mRootView.getRootView());
        }
        SinglePopControl.showPop(this.mContext, this);
    }

    private void toBackAnim() {
        SmoothFrameLayout2 smoothFrameLayout2 = this.mMainPopContentHolder.mContentView;
        View viewFindViewById = smoothFrameLayout2.findViewById(R.id.mask);
        IStateStyle iStateStyleState = Folme.useAt(smoothFrameLayout2).state();
        ViewProperty viewProperty = ViewProperty.SCALE_X;
        Float fValueOf = Float.valueOf(0.95f);
        iStateStyleState.to(viewProperty, fValueOf, ViewProperty.SCALE_Y, fValueOf, ViewBounds.sOpenConfig);
        Folme.useAt(viewFindViewById).state().to(ViewProperty.AUTO_ALPHA, Float.valueOf(1.0f), ViewBounds.sOpenConfig);
    }

    private void toFrontAnim() {
        SmoothFrameLayout2 smoothFrameLayout2 = this.mMainPopContentHolder.mContentView;
        View viewFindViewById = smoothFrameLayout2.findViewById(R.id.mask);
        IStateStyle iStateStyleState = Folme.useAt(smoothFrameLayout2).state();
        ViewProperty viewProperty = ViewProperty.SCALE_X;
        Float fValueOf = Float.valueOf(1.0f);
        iStateStyleState.to(viewProperty, fValueOf, ViewProperty.SCALE_Y, fValueOf, ViewBounds.sCloseConfig);
        Folme.useAt(viewFindViewById).state().to(ViewProperty.AUTO_ALPHA, Float.valueOf(0.0f), ViewBounds.sCloseConfig);
    }

    public void doCollapseAnimation() {
        PopupWindowSpec popupWindowSpec = this.mSecondaryContentHolder.mPopupWindowSpec;
        final SmoothFrameLayout2 smoothFrameLayout2 = this.mSecondaryContentHolder.mContentView;
        final ViewBounds viewBounds = this.mSecondaryContentHolder.mViewBounds;
        viewBounds.setMeasureWidth(smoothFrameLayout2.getWidth());
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        Rect rect2 = this.mMainPopContentHolder.mBoundsRect;
        Rect unionBounds = getUnionBounds(rect2, this.mSecondaryContentHolder.mBoundsRect);
        final int iWidth = unionBounds.width();
        final int iHeight = unionBounds.height();
        final int i2 = rect2.left - unionBounds.left;
        final int i3 = rect2.top - unionBounds.top;
        final int iWidth2 = i2 + rect2.width();
        final int iHeight2 = rect2.height() + i3;
        final int left = smoothFrameLayout2.getLeft();
        final int top = smoothFrameLayout2.getTop();
        final int right = smoothFrameLayout2.getRight();
        final int bottom = smoothFrameLayout2.getBottom();
        int i4 = rect.left;
        int i5 = unionBounds.left;
        final int i6 = i4 - i5;
        int i7 = rect.top;
        int i8 = unionBounds.top;
        final int i9 = i7 - i8;
        final int i10 = rect.right - i5;
        final int i11 = rect.bottom - i8;
        final int i12 = this.mSecondaryContentHolder.mHeaderViewHeight;
        final int i13 = this.mSecondaryContentHolder.mAnchorHeight;
        final int i14 = this.mSecondaryContentHolder.mHeaderViewPaddingTop;
        final int i15 = this.mSecondaryContentHolder.mAnchorPaddingTop;
        final int i16 = this.mSecondaryContentHolder.mHeaderViewPaddingBottom;
        final int i17 = this.mSecondaryContentHolder.mAnchorPaddingBottom;
        this.mSecondaryContentHolder.mListView.setScrollBarStyle(0);
        this.mSecondaryContentHolder.mIsInAnimation = true;
        final int i18 = 0;
        AnimConfig animConfigAddListeners = new AnimConfig().addListeners(new TransitionListener() { // from class: miuix.appcompat.widget.HyperPopupWindow.2
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                HyperPopupWindow.this.mContainer.removeView(smoothFrameLayout2);
                HyperPopupWindow.this.mContainer.removeView(HyperPopupWindow.this.mInnerClip);
                HyperPopupWindow.this.mContainer.removeView(HyperPopupWindow.this.mClipView);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                HyperPopupWindow.this.mContainer.removeView(smoothFrameLayout2);
                HyperPopupWindow.this.mContainer.removeView(HyperPopupWindow.this.mInnerClip);
                HyperPopupWindow.this.mContainer.removeView(HyperPopupWindow.this.mClipView);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                super.onUpdate(obj, collection);
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "fraction");
                if (updateInfoFindByName == null) {
                    return;
                }
                float floatValue = updateInfoFindByName.getFloatValue();
                HyperPopupWindow.this.mClipView.setClipBounds((int) (i18 + ((i2 - r11) * floatValue)), (int) (i18 + ((i3 - r0) * floatValue)), (int) (iWidth + ((iWidth2 - r1) * floatValue)), (int) (iHeight + ((iHeight2 - r2) * floatValue)));
                HyperPopupWindow.this.mClipView.refreshClipPath();
                int i19 = (int) (left + ((i6 - r11) * floatValue));
                int i20 = (int) (top + ((i9 - r11) * floatValue));
                int i21 = (int) (right + ((i10 - r11) * floatValue));
                int i22 = (int) (bottom + ((i11 - r11) * floatValue));
                HyperPopupWindow.this.mInnerClip.setClipBounds(i19, i20, i21, i22);
                HyperPopupWindow.this.mInnerClip.refreshClipPath();
                viewBounds.updateLeftTopRightBottom(i19, i20, i21, i22, (int) (i12 + ((i13 - r11) * floatValue)), (int) (i14 + ((i15 - r11) * floatValue)), (int) (i16 + ((i17 - r11) * floatValue)));
            }
        });
        animConfigAddListeners.setEase(-2, 0.95f, 0.2f);
        IStateStyle iStateStyleUseValue = Folme.useValue(viewBounds);
        Float fValueOf = Float.valueOf(0.0f);
        iStateStyleUseValue.setTo("fraction", fValueOf).to("fraction", Float.valueOf(1.0f), animConfigAddListeners);
        smoothFrameLayout2.setCornerRadius(viewBounds.getCornerRadius());
        Folme.useValue(viewBounds).to(viewBounds.mCornerProperty, fValueOf, ViewBounds.sCloseConfig);
        Folme.useValue(viewBounds).to(viewBounds.mArrowRotationProperty, fValueOf, viewBounds.mArrowCloseConfig);
    }

    public void doExpandAnimation(View view, ListAdapter listAdapter) {
        PopupWindowSpec popupWindowSpecClone = this.mPopupWindowSpec.clone();
        ViewUtils.getBoundsInWindow(view, popupWindowSpecClone.mAnchorViewBounds);
        Rect rect = popupWindowSpecClone.mAnchorViewBounds;
        int i2 = rect.left;
        Rect rect2 = this.mRootBounds;
        rect.left = i2 + rect2.left;
        int i3 = rect.right;
        int i4 = rect2.left;
        rect.right = i3 + i4;
        rect.top += rect2.top;
        int i5 = rect.bottom;
        int i6 = rect2.top;
        rect.bottom = i5 + i6;
        popupWindowSpecClone.mDecorViewBounds.set(i4, i6, rect2.right, rect2.bottom);
        PopupContentHolder popupContentHolder = new PopupContentHolder(this.mContext, listAdapter, this.mSecondaryPopupStrategy, popupWindowSpecClone);
        this.mSecondaryContentHolder = popupContentHolder;
        popupContentHolder.inflate();
        this.mSecondaryContentHolder.setMinWidth(this.mMainPopContentHolder.mContentView.getWidth());
        this.mSecondaryContentHolder.show(view, this.mContainer, this.mRootBounds, true);
    }

    public boolean getSecondaryMenuEnabled() {
        return this.mEnableSecondaryMenu;
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void prepareContentView() {
        FrameLayout frameLayout = this.mRootView;
        int i2 = this.mAnimationExtensionMargin;
        frameLayout.setPadding(i2, i2, i2, i2);
        super.prepareContentView();
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void setAdapter(ListAdapter listAdapter) {
        this.mMainPopContentHolder.setAdapter(listAdapter);
    }

    @Override // android.widget.PopupWindow
    public void setClippingEnabled(boolean z2) {
        FrameLayout frameLayout = this.mRootView;
        if (frameLayout != null) {
            if (z2) {
                this.mAnimationExtensionMargin = 0;
            } else {
                this.mAnimationExtensionMargin = 35;
            }
            int i2 = this.mAnimationExtensionMargin;
            frameLayout.setPadding(i2, i2, i2, i2);
        }
        super.setClippingEnabled(z2);
    }

    public void setOnMenuItemClickListener(@Nullable OnMenuItemClickListener onMenuItemClickListener) {
        this.mMenuItemClickListener = onMenuItemClickListener;
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void setPopupWindowStrategy(IPopupWindowStrategy iPopupWindowStrategy) {
        this.mMainPopupStrategy = iPopupWindowStrategy;
        this.mMainPopContentHolder.mPopupWindowStrategy = iPopupWindowStrategy;
    }

    public void setSecondaryMenuEnabled(boolean z2) {
        this.mEnableSecondaryMenu = z2;
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void show(View view) {
        setAnchorView(view);
        updatePopupWindowSpec(this.mPopupWindowSpec);
        this.mRootBounds = getRootBounds();
        if (this.mContainer == null) {
            this.mContainer = new FrameLayout(this.mContext);
            this.mContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            superSetContentViewWithoutClip(this.mContainer);
            this.mContainer.setLayoutDirection(0);
            this.mContainer.setClipChildren(false);
            this.mContainer.setClipToPadding(false);
            ((ViewGroup) this.mContainer.getParent()).setClipChildren(false);
            ((ViewGroup) this.mContainer.getParent()).setClipToPadding(false);
        }
        this.mMainPopContentHolder.mPopupWindowSpec = this.mPopupWindowSpec;
        this.mMainPopContentHolder.inflate();
        this.mMainPopContentHolder.setMenuListAccessibilityDelegate();
        this.mMainPopContentHolder.show(view, this.mContainer, this.mRootBounds, false);
        this.mMainPopContentHolder.setItemClickListener(new AnonymousClass1());
        if (this.mEnableSecondaryMenu) {
            int iWidth = this.mRootBounds.width();
            int iHeight = this.mRootBounds.height();
            setWidth(iWidth + (this.mAnimationExtensionMargin * 2));
            setHeight(iHeight + (this.mAnimationExtensionMargin * 2));
            Rect rect = this.mRootBounds;
            int i2 = rect.left;
            int i3 = this.mAnimationExtensionMargin;
            showAtLocation(view, 0, i2 - i3, rect.top - i3, this.mMainPopContentHolder.mBoundsRect);
            return;
        }
        Rect rect2 = this.mMainPopContentHolder.mBoundsRect;
        int iWidth2 = rect2.width();
        int iHeight2 = rect2.height();
        setWidth(iWidth2 + (this.mAnimationExtensionMargin * 2));
        setHeight(iHeight2 + (this.mAnimationExtensionMargin * 2));
        int i4 = rect2.left;
        int i5 = this.mAnimationExtensionMargin;
        showAtLocation(view, 0, i4 - i5, rect2.top - i5, rect2);
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void showWithAnim(int i2) {
        if (!this.mWindowAnimationEnabled && this.mAnimHelper == null) {
            this.mAnimHelper = new PopupAnimHelper((View) this.mContentView.findViewById(R.id.spring_back).getParent());
        }
        super.showWithAnim(i2);
    }

    @Override // android.widget.PopupWindow
    public void update() {
        this.mMainPopContentHolder.update();
    }

    @Override // miuix.popupwidget.widget.PopupWindow
    public void updateLocation(@NonNull View view) {
        this.mMainPopContentHolder.update();
    }

    public HyperPopupWindow(Context context, View view) {
        super(context, view);
        this.mEnableSecondaryMenu = true;
        this.mAnimationExtensionMargin = 35;
        setAutoDismiss(true);
        PopupWindowStrategy popupWindowStrategy = new PopupWindowStrategy();
        this.mMainPopupStrategy = popupWindowStrategy;
        this.mMainPopContentHolder = new PopupContentHolder(this.mContext, popupWindowStrategy);
        this.mCornerRadius = this.mContext.getResources().getDimension(R.dimen.miuix_appcompat_drop_down_menu_radius);
    }

    private void safeAddView(ViewGroup viewGroup, View view, int i2) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (viewGroup != null) {
            viewGroup.addView(view, i2);
        }
    }

    public class ClipLayout extends FrameLayout {
        private OnBackInvokedCallback backCallBack;
        OnBackInvokedDispatcher dispatcher;
        private boolean interceptedTouchEvent;
        private Path mClipPath;
        private RectF mClipRoundRect;
        private boolean mIsClip;
        private float mRadius;

        public ClipLayout(@NonNull Context context) {
            super(context);
            this.mIsClip = false;
            this.mClipRoundRect = new RectF();
            this.mClipPath = new Path();
            this.interceptedTouchEvent = false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            if (this.mIsClip) {
                canvas.clipPath(this.mClipPath);
            }
            super.draw(canvas);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.dispatcher = findOnBackInvokedDispatcher();
            final HyperPopupWindow hyperPopupWindow = HyperPopupWindow.this;
            OnBackInvokedCallback onBackInvokedCallback = new OnBackInvokedCallback() { // from class: miuix.appcompat.widget.d
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    HyperPopupWindow.access$900(hyperPopupWindow);
                }
            };
            this.backCallBack = onBackInvokedCallback;
            OnBackInvokedDispatcher onBackInvokedDispatcher = this.dispatcher;
            if (onBackInvokedDispatcher != null) {
                onBackInvokedDispatcher.registerOnBackInvokedCallback(AnimState.VIEW_SIZE, onBackInvokedCallback);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            OnBackInvokedDispatcher onBackInvokedDispatcher = this.dispatcher;
            if (onBackInvokedDispatcher != null) {
                onBackInvokedDispatcher.unregisterOnBackInvokedCallback(this.backCallBack);
            }
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
            return this.interceptedTouchEvent;
        }

        public void refreshClipPath() {
            this.mClipPath.reset();
            Path path = this.mClipPath;
            RectF rectF = this.mClipRoundRect;
            float f2 = this.mRadius;
            path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
            this.mIsClip = true;
        }

        public void setClipBounds(int i2, int i3, int i4, int i5) {
            this.mClipRoundRect.set(i2, i3, i4, i5);
        }

        public void setRadius(float f2) {
            this.mRadius = f2;
        }

        public ClipLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mIsClip = false;
            this.mClipRoundRect = new RectF();
            this.mClipPath = new Path();
            this.interceptedTouchEvent = false;
        }

        public ClipLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            this.mIsClip = false;
            this.mClipRoundRect = new RectF();
            this.mClipPath = new Path();
            this.interceptedTouchEvent = false;
        }
    }

    public class PopupContentHolder {
        private ListAdapter mAdapter;
        private int mAnchorHeight;
        private int mAnchorPaddingBottom;
        private int mAnchorPaddingTop;
        private SmoothFrameLayout2 mContentView;
        private Context mContext;
        private View mHeaderView;
        private int mHeaderViewHeight;
        private int mHeaderViewPaddingBottom;
        private int mHeaderViewPaddingTop;
        private ListView mListView;
        private AdapterView.OnItemClickListener mOnItemClickListener;
        private PopupWindowSpec mPopupWindowSpec;
        private IPopupWindowStrategy mPopupWindowStrategy;
        private ViewBounds mViewBounds;
        private int mMinWidth = -1;
        private final Rect mBoundsRect = new Rect();
        private boolean mIsInAnimation = false;

        /* JADX INFO: renamed from: miuix.appcompat.widget.HyperPopupWindow$PopupContentHolder$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnTouchListener {
            int lastIndex = -1;

            public AnonymousClass2() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static /* synthetic */ void lambda$onTouch$0(View view) {
                if (view instanceof ViewGroup) {
                    try {
                        int childCount = ((ViewGroup) view).getChildCount();
                        for (int i2 = 0; i2 < childCount; i2++) {
                            ((ViewGroup) view).getChildAt(i2).setPressed(false);
                        }
                    } catch (Exception e2) {
                        Log.e(HyperPopupWindow.TAG, "list onTouch error " + e2);
                    }
                }
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(final View view, MotionEvent motionEvent) {
                int firstVisiblePosition;
                int i2;
                View childAt;
                int iPointToPosition = PopupContentHolder.this.mListView.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1 || action == 3 || action == 6) {
                        this.lastIndex = -1;
                        PopupContentHolder.this.mListView.postDelayed(new Runnable() { // from class: miuix.appcompat.widget.f
                            @Override // java.lang.Runnable
                            public final void run() {
                                HyperPopupWindow.PopupContentHolder.AnonymousClass2.lambda$onTouch$0(view);
                            }
                        }, ViewConfiguration.getPressedStateDuration());
                    }
                } else if (iPointToPosition != -1 && (firstVisiblePosition = iPointToPosition - PopupContentHolder.this.mListView.getFirstVisiblePosition()) != (i2 = this.lastIndex)) {
                    if (i2 != -1 && (childAt = PopupContentHolder.this.mListView.getChildAt(this.lastIndex)) != null) {
                        childAt.setPressed(false);
                    }
                    PopupContentHolder.this.mListView.getChildAt(firstVisiblePosition).setPressed(true);
                    this.lastIndex = firstVisiblePosition;
                }
                return false;
            }
        }

        public PopupContentHolder(Context context, IPopupWindowStrategy iPopupWindowStrategy) {
            this.mContext = context;
            this.mPopupWindowStrategy = iPopupWindowStrategy;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$inflate$0(AdapterView adapterView, View view, int i2, long j2) {
            int headerViewsCount = i2 - this.mListView.getHeaderViewsCount();
            if (this.mOnItemClickListener == null || headerViewsCount < 0 || headerViewsCount >= this.mAdapter.getCount()) {
                return;
            }
            this.mOnItemClickListener.onItemClick(adapterView, view, headerViewsCount, j2);
        }

        public void inflate() {
            if (this.mContentView == null) {
                this.mContentView = (SmoothFrameLayout2) LayoutInflater.from(this.mContext).inflate(R.layout.miuix_appcompat_hyper_popup_list, (ViewGroup) null);
                Drawable drawableResolveDrawable = AttributeResolver.resolveDrawable(this.mContext, R.attr.immersionWindowBackground);
                if (drawableResolveDrawable instanceof SmoothContainerDrawable2) {
                    ((SmoothContainerDrawable2) drawableResolveDrawable).setCornerRadius(HyperPopupWindow.this.mCornerRadius);
                }
                if (drawableResolveDrawable != null) {
                    this.mContentView.setBackground(drawableResolveDrawable);
                }
                final View viewFindViewById = this.mContentView.findViewById(R.id.spring_back);
                this.mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.appcompat.widget.HyperPopupWindow.PopupContentHolder.1
                    @Override // android.view.View.OnLayoutChangeListener
                    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                        boolean z2 = !PopupContentHolder.this.mIsInAnimation && (PopupContentHolder.this.mAdapter != null ? PopupContentHolder.this.mPopupWindowStrategy.isNeedScroll(i5 - i3, PopupContentHolder.this.mPopupWindowSpec) : true);
                        viewFindViewById.setEnabled(z2);
                        PopupContentHolder.this.mListView.setVerticalScrollBarEnabled(z2);
                    }
                });
            }
            ListView listView = (ListView) this.mContentView.findViewById(android.R.id.list);
            this.mListView = listView;
            if (listView != null) {
                listView.setOnTouchListener(new AnonymousClass2());
                this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.appcompat.widget.e
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                        this.f6094a.lambda$inflate$0(adapterView, view, i2, j2);
                    }
                });
                this.mListView.setAdapter(this.mAdapter);
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            this.mAdapter = listAdapter;
        }

        public void setItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setMenuListAccessibilityDelegate() {
            ListView listView;
            if (HyperPopupWindow.this.mMainPopContentHolder == null || (listView = this.mListView) == null) {
                return;
            }
            ViewCompat.setAccessibilityDelegate(listView, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.widget.HyperPopupWindow.PopupContentHolder.4
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean onRequestSendAccessibilityEvent(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
                    if (accessibilityEvent.getEventType() == 32768) {
                        HyperPopupWindow.this.mFocusedMainPopupItemView = view;
                    }
                    return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
                }
            });
        }

        public void setMinWidth(int i2) {
            this.mMinWidth = i2;
        }

        public boolean show(final View view, ViewGroup viewGroup, Rect rect, boolean z2) {
            PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
            final Rect rect2 = popupWindowSpec.mAnchorViewBounds;
            if (z2) {
                rect2.left -= HyperPopupWindow.this.mAnimationExtensionMargin;
                rect2.top -= HyperPopupWindow.this.mAnimationExtensionMargin;
                rect2.right -= HyperPopupWindow.this.mAnimationExtensionMargin;
                rect2.bottom -= HyperPopupWindow.this.mAnimationExtensionMargin;
            }
            popupWindowSpec.mItemViewBounds = HyperPopupWindow.getItemViewBounds(this.mAdapter, this.mListView, this.mContext, popupWindowSpec.mMaxWidth, this.mMinWidth);
            this.mPopupWindowStrategy.measureContentSize(popupWindowSpec);
            int xInWindow = this.mPopupWindowStrategy.getXInWindow(popupWindowSpec);
            int yInWindow = this.mPopupWindowStrategy.getYInWindow(popupWindowSpec);
            int i2 = popupWindowSpec.mFinalPopupWidth;
            int i3 = popupWindowSpec.mFinalPopupHeight;
            int i4 = xInWindow + i2;
            int i5 = yInWindow + i3;
            this.mBoundsRect.set(xInWindow, yInWindow, i4, i5);
            if (HyperPopupWindow.this.mEnableSecondaryMenu) {
                HyperPopupWindow.this.offsetRootBounds(popupWindowSpec, xInWindow, yInWindow, i2, i3);
            }
            if (!z2) {
                this.mContentView.setPivotX(i4 / 2 > rect2.centerX() ? 0.0f : i2);
                this.mContentView.setPivotY(yInWindow <= rect2.top ? i3 : 0.0f);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i3);
                if (HyperPopupWindow.this.mEnableSecondaryMenu) {
                    layoutParams.leftMargin = xInWindow - rect.left;
                    layoutParams.topMargin = yInWindow - rect.top;
                }
                this.mContentView.setLayoutParams(layoutParams);
                HyperPopupWindow hyperPopupWindow = HyperPopupWindow.this;
                hyperPopupWindow.safeAddView(hyperPopupWindow.mContainer, this.mContentView);
                return true;
            }
            Rect rect3 = HyperPopupWindow.this.mMainPopContentHolder.mBoundsRect;
            Rect unionBounds = HyperPopupWindow.getUnionBounds(rect3, this.mBoundsRect);
            HyperPopupWindow.this.mClipView = HyperPopupWindow.this.new ClipLayout(this.mContext);
            HyperPopupWindow.this.mClipView.setBackgroundColor(0);
            HyperPopupWindow.this.mClipView.setRadius(HyperPopupWindow.this.mCornerRadius);
            HyperPopupWindow.this.mClipView.setElevation(((PopupWindow) HyperPopupWindow.this).mElevation + (((PopupWindow) HyperPopupWindow.this).mElevationExtra * 2));
            final int i6 = rect3.left - unionBounds.left;
            final int i7 = rect3.top - unionBounds.top;
            final int iWidth = i6 + rect3.width();
            final int iHeight = rect3.height() + i7;
            final int iWidth2 = unionBounds.width();
            final int iHeight2 = unionBounds.height();
            HyperPopupWindow.this.mClipView.setClipBounds(i6, i7, iWidth, iHeight);
            HyperPopupWindow.this.mClipView.refreshClipPath();
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(unionBounds.width(), unionBounds.height());
            layoutParams2.leftMargin = unionBounds.left - rect.left;
            layoutParams2.topMargin = unionBounds.top - rect.top;
            HyperPopupWindow.this.mClipView.setLayoutParams(layoutParams2);
            viewGroup.addView(HyperPopupWindow.this.mClipView);
            int i8 = rect2.left;
            int i9 = unionBounds.left;
            final int i10 = i8 - i9;
            int i11 = rect2.top;
            int i12 = unionBounds.top;
            final int i13 = i11 - i12;
            final int i14 = rect2.right - i9;
            final int i15 = rect2.bottom - i12;
            final int i16 = xInWindow - i9;
            final int i17 = yInWindow - i12;
            final int i18 = i4 - i9;
            final int i19 = i5 - i12;
            this.mContentView.setLayoutParams(new FrameLayout.LayoutParams(i18 - i16, i19 - i17));
            ClipLayout clipLayout = HyperPopupWindow.this.new ClipLayout(this.mContext);
            clipLayout.setLayoutParams(new FrameLayout.LayoutParams(unionBounds.width(), unionBounds.height()));
            clipLayout.setBackgroundColor(0);
            clipLayout.setClipBounds(i10, i13, i14, i15);
            clipLayout.refreshClipPath();
            clipLayout.addView(this.mContentView);
            HyperPopupWindow.this.mClipView.addView(clipLayout);
            HyperPopupWindow.this.mInnerClip = clipLayout;
            ViewBounds viewBounds = new ViewBounds(this.mContentView);
            this.mViewBounds = viewBounds;
            viewBounds.setMeasureWidth(i2);
            HyperPopupWindow.this.mMainPopContentHolder.mIsInAnimation = true;
            HyperPopupWindow.this.mSecondaryContentHolder.mIsInAnimation = true;
            final int i20 = 0;
            final int i21 = 0;
            this.mContentView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: miuix.appcompat.widget.HyperPopupWindow.PopupContentHolder.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    View viewFindViewById = PopupContentHolder.this.mContentView.findViewById(R.id.tag_secondary_popup_menu_item_head);
                    if (viewFindViewById == null) {
                        return false;
                    }
                    PopupContentHolder.this.mContentView.getViewTreeObserver().removeOnPreDrawListener(this);
                    viewFindViewById.sendAccessibilityEvent(8);
                    PopupContentHolder.this.mAnchorHeight = rect2.height();
                    PopupContentHolder.this.mAnchorPaddingTop = view.getPaddingTop();
                    PopupContentHolder.this.mAnchorPaddingBottom = view.getPaddingBottom();
                    PopupContentHolder.this.mHeaderViewHeight = viewFindViewById.getHeight();
                    PopupContentHolder.this.mHeaderViewPaddingTop = viewFindViewById.getPaddingTop();
                    PopupContentHolder.this.mHeaderViewPaddingBottom = viewFindViewById.getPaddingBottom();
                    final int i22 = PopupContentHolder.this.mAnchorHeight;
                    final int i23 = PopupContentHolder.this.mHeaderViewHeight;
                    final int i24 = PopupContentHolder.this.mAnchorPaddingTop;
                    final int i25 = PopupContentHolder.this.mHeaderViewPaddingTop;
                    final int i26 = PopupContentHolder.this.mAnchorPaddingBottom;
                    final int i27 = PopupContentHolder.this.mHeaderViewPaddingBottom;
                    PopupContentHolder.this.mHeaderView = viewFindViewById;
                    Folme.useValue(PopupContentHolder.this.mViewBounds).setTo("fraction", Float.valueOf(0.0f)).to("fraction", Float.valueOf(1.0f), new AnimConfig().addListeners(new TransitionListener() { // from class: miuix.appcompat.widget.HyperPopupWindow.PopupContentHolder.3.1
                        @Override // miuix.animation.listener.TransitionListener
                        public void onCancel(Object obj) {
                            super.onCancel(obj);
                            HyperPopupWindow.this.mMainPopContentHolder.mIsInAnimation = false;
                            if (HyperPopupWindow.this.mSecondaryContentHolder != null) {
                                HyperPopupWindow.this.mSecondaryContentHolder.mIsInAnimation = false;
                            }
                        }

                        @Override // miuix.animation.listener.TransitionListener
                        public void onComplete(Object obj) {
                            super.onComplete(obj);
                            HyperPopupWindow.this.mMainPopContentHolder.mIsInAnimation = false;
                            if (HyperPopupWindow.this.mSecondaryContentHolder != null) {
                                HyperPopupWindow.this.mSecondaryContentHolder.mIsInAnimation = false;
                            }
                        }

                        @Override // miuix.animation.listener.TransitionListener
                        public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                            super.onUpdate(obj, collection);
                            UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "fraction");
                            if (updateInfoFindByName == null) {
                                return;
                            }
                            float floatValue = updateInfoFindByName.getFloatValue();
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            HyperPopupWindow.this.mClipView.setClipBounds((int) (i6 + ((i20 - r0) * floatValue)), (int) (i7 + ((i21 - r1) * floatValue)), (int) (iWidth + ((iWidth2 - r2) * floatValue)), (int) (iHeight + ((iHeight2 - r3) * floatValue)));
                            HyperPopupWindow.this.mClipView.refreshClipPath();
                            AnonymousClass3 anonymousClass32 = AnonymousClass3.this;
                            int i28 = (int) (i10 + ((i16 - r0) * floatValue));
                            int i29 = (int) (i13 + ((i17 - r0) * floatValue));
                            int i30 = (int) (i14 + ((i18 - r0) * floatValue));
                            int i31 = (int) (i15 + ((i19 - r0) * floatValue));
                            HyperPopupWindow.this.mInnerClip.setClipBounds(i28, i29, i30, i31);
                            HyperPopupWindow.this.mInnerClip.refreshClipPath();
                            PopupContentHolder.this.mViewBounds.updateLeftTopRightBottom(i28, i29, i30, i31, (int) (i22 + ((i23 - r12) * floatValue)), (int) (i24 + ((i25 - r12) * floatValue)), (int) (i26 + ((i27 - r12) * floatValue)));
                        }
                    }));
                    float cornerRadius = PopupContentHolder.this.mViewBounds.getCornerRadius();
                    float f2 = HyperPopupWindow.this.mCornerRadius;
                    PopupContentHolder.this.mContentView.setCornerRadius(cornerRadius);
                    Folme.useValue(PopupContentHolder.this.mViewBounds).to(PopupContentHolder.this.mViewBounds.mCornerProperty, Float.valueOf(f2), ViewBounds.sOpenConfig);
                    Folme.useValue(PopupContentHolder.this.mViewBounds).to(PopupContentHolder.this.mViewBounds.mArrowRotationProperty, Float.valueOf(-90.0f), PopupContentHolder.this.mViewBounds.mArrowOpenConfig);
                    return false;
                }
            });
            return true;
        }

        public boolean update() {
            PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
            HyperPopupWindow hyperPopupWindow = HyperPopupWindow.this;
            hyperPopupWindow.mRootBounds = hyperPopupWindow.getRootBounds();
            popupWindowSpec.mItemViewBounds = HyperPopupWindow.getItemViewBounds(this.mAdapter, this.mListView, this.mContext, popupWindowSpec.mMaxWidth, this.mMinWidth);
            this.mPopupWindowStrategy.measureContentSize(popupWindowSpec);
            int xInWindow = this.mPopupWindowStrategy.getXInWindow(popupWindowSpec);
            int yInWindow = this.mPopupWindowStrategy.getYInWindow(popupWindowSpec);
            int i2 = popupWindowSpec.mFinalPopupWidth;
            int i3 = popupWindowSpec.mFinalPopupHeight;
            this.mBoundsRect.set(xInWindow, yInWindow, xInWindow + i2, yInWindow + i3);
            if (HyperPopupWindow.this.mEnableSecondaryMenu) {
                HyperPopupWindow.this.offsetRootBounds(popupWindowSpec, xInWindow, yInWindow, i2, i3);
                int iWidth = HyperPopupWindow.this.mRootBounds.width();
                int iHeight = HyperPopupWindow.this.mRootBounds.height();
                HyperPopupWindow.this.setWidth(iWidth);
                HyperPopupWindow.this.setHeight(iHeight);
                HyperPopupWindow hyperPopupWindow2 = HyperPopupWindow.this;
                hyperPopupWindow2.update(hyperPopupWindow2.mRootBounds.left, HyperPopupWindow.this.mRootBounds.top, this.mBoundsRect.width(), this.mBoundsRect.height());
                return true;
            }
            int iWidth2 = this.mBoundsRect.width();
            int iHeight2 = this.mBoundsRect.height();
            HyperPopupWindow.this.setWidth(iWidth2);
            HyperPopupWindow.this.setHeight(iHeight2);
            HyperPopupWindow hyperPopupWindow3 = HyperPopupWindow.this;
            Rect rect = this.mBoundsRect;
            hyperPopupWindow3.update(rect.left, rect.top, rect.width(), this.mBoundsRect.height());
            return true;
        }

        public PopupContentHolder(Context context, ListAdapter listAdapter, IPopupWindowStrategy iPopupWindowStrategy, PopupWindowSpec popupWindowSpec) {
            this.mContext = context;
            this.mAdapter = listAdapter;
            this.mPopupWindowStrategy = iPopupWindowStrategy;
            this.mPopupWindowSpec = popupWindowSpec;
        }
    }
}
