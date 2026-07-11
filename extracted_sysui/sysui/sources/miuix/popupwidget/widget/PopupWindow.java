package miuix.popupwidget.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.WindowBaseInfo;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.popupwidget.R;
import miuix.popupwidget.internal.strategy.IPopupWindowStrategy;
import miuix.popupwidget.internal.strategy.MarginPopupWindowStrategy;
import miuix.popupwidget.internal.strategy.PopupWindowSpec;
import miuix.popupwidget.internal.strategy.PopupWindowStrategy;
import miuix.popupwidget.internal.util.SinglePopControl;
import miuix.popupwidget.widget.PopupWindow;
import miuix.smooth.SmoothFrameLayout2;
import miuix.springback.view.SpringBackLayout;
import miuix.theme.token.DimToken;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes5.dex */
public class PopupWindow extends android.widget.PopupWindow {
    protected static final int ANIMATION_EXTENSION_MARGIN = 35;
    public static final int ANIMATION_STYLE_DEFAULT = -1;
    public static final int ANIMATION_STYLE_NONE = 0;
    private static final int SHADOW_OFFSET_X = 0;
    private static final int SHADOW_OFFSET_Y = 26;
    private static final int SHADOW_RADIUS = 32;
    private static final String TAG = "PopupWindow";
    protected ListAdapter mAdapter;
    ViewTreeObserver.OnGlobalLayoutListener mAnchorGlobalLayoutListener;
    private WeakReference<View> mAnchorView;
    protected PopupAnimHelper mAnimHelper;
    private int mAnimationStyle;
    private boolean mAutoDismiss;
    private ContentSize mContentSize;
    protected View mContentView;
    protected final Context mContext;
    private int mDensityDpi;
    private boolean mDetachAnchorLayoutFlag;
    private boolean mDifferDensityCompat;
    private float mDimAmount;
    protected int mElevation;
    protected int mElevationExtra;
    private WeakReference<View> mFenceDecor;
    private boolean mHasShadow;
    private boolean mHideSoftInputEnabled;
    protected boolean mIgnoreAnchorVisibility;
    private ListView mListView;
    private int mMaxAllowedHeight;
    private int mMaxAllowedWidth;
    private int mMinAllowedWidth;
    private int mMinSafeInsetDimen;
    private final DataSetObserver mObserver;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public PopupWindowSpec mPopupWindowSpec;
    protected IPopupWindowStrategy mPopupWindowStrategy;
    protected FrameLayout mRootView;
    private int mShadowColor;
    private SpringBackLayout mSpringBackLayout;
    private int mUserAnimationGravity;
    protected boolean mWindowAnimationEnabled;
    private int mWindowManagerFlags;

    /* JADX INFO: renamed from: miuix.popupwidget.widget.PopupWindow$1, reason: invalid class name */
    public class AnonymousClass1 extends DataSetObserver {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChanged$0(View view) {
            FrameLayout frameLayout = PopupWindow.this.mRootView;
            if (frameLayout == null || !frameLayout.isAttachedToWindow()) {
                return;
            }
            PopupWindow.this.updateLocation(view);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            final View anchor;
            PopupWindow.this.mContentSize.mHasContentWidth = false;
            if (!PopupWindow.this.isShowing() || (anchor = PopupWindow.this.getAnchor()) == null) {
                return;
            }
            anchor.post(new Runnable() { // from class: miuix.popupwidget.widget.g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6146a.lambda$onChanged$0(anchor);
                }
            });
        }
    }

    /* JADX INFO: renamed from: miuix.popupwidget.widget.PopupWindow$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnTouchListener {
        int lastIndex = -1;

        public AnonymousClass5() {
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
                    Log.e(PopupWindow.TAG, "list onTouch error " + e2);
                }
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            int firstVisiblePosition;
            int i2;
            View childAt;
            int iPointToPosition = PopupWindow.this.mListView.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1 || action == 3 || action == 6) {
                    this.lastIndex = -1;
                    PopupWindow.this.mListView.postDelayed(new Runnable() { // from class: miuix.popupwidget.widget.h
                        @Override // java.lang.Runnable
                        public final void run() {
                            PopupWindow.AnonymousClass5.lambda$onTouch$0(view);
                        }
                    }, ViewConfiguration.getPressedStateDuration());
                }
            } else if (iPointToPosition != -1 && (firstVisiblePosition = iPointToPosition - PopupWindow.this.mListView.getFirstVisiblePosition()) != (i2 = this.lastIndex)) {
                if (i2 != -1 && (childAt = PopupWindow.this.mListView.getChildAt(this.lastIndex)) != null) {
                    childAt.setPressed(false);
                }
                PopupWindow.this.mListView.getChildAt(firstVisiblePosition).setPressed(true);
                this.lastIndex = firstVisiblePosition;
            }
            return false;
        }
    }

    public static class ContentSize {
        boolean mHasContentWidth;
        int mHeight;
        int mWidth;

        private ContentSize() {
        }

        @NonNull
        public String toString() {
            return "ContentSize{ w= " + this.mWidth + " h= " + this.mHeight + " }";
        }

        public void updateWidth(int i2) {
            this.mWidth = i2;
            this.mHasContentWidth = true;
        }

        public /* synthetic */ ContentSize(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public PopupWindow(Context context) {
        this(context, null);
    }

    public static int computeGravity(@NonNull Rect rect, @NonNull Rect rect2, int i2, int i3) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, i3) & 112;
        int i4 = 48;
        if (Math.abs(rect2.centerY() - rect.centerY()) <= 10 ? absoluteGravity == 80 : rect2.centerY() <= rect.centerY()) {
            i4 = 80;
        }
        return Math.abs(rect2.centerX() - rect.centerX()) > 10 ? rect2.centerX() > rect.centerX() ? i4 | 3 : i4 | 5 : i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configurationChanged(Configuration configuration) {
        int i2;
        View anchor = getAnchor();
        if (isShowing() && this.mDifferDensityCompat && (i2 = configuration.densityDpi) != this.mDensityDpi) {
            this.mDensityDpi = i2;
            updateDisplayConfig(null);
            if (isActivityRunning(getBaseActivity(this.mContext))) {
                dismissWithNoNotify();
                this.mRootView.removeAllViews();
                this.mContentView = null;
                if (prepareShow(anchor)) {
                    showAsDropDown(anchor);
                }
            }
        }
        if (anchor != null && !this.mDetachAnchorLayoutFlag) {
            this.mDetachAnchorLayoutFlag = true;
            anchor.getViewTreeObserver().addOnGlobalLayoutListener(this.mAnchorGlobalLayoutListener);
        }
        this.mContentSize.mHasContentWidth = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void detachAnchorView() {
        WeakReference<View> weakReference;
        if (!this.mDetachAnchorLayoutFlag || (weakReference = this.mAnchorView) == null) {
            return;
        }
        this.mDetachAnchorLayoutFlag = false;
        weakReference.get().getViewTreeObserver().removeOnGlobalLayoutListener(this.mAnchorGlobalLayoutListener);
    }

    private void dismissWithAnim() {
        PopupAnimHelper popupAnimHelper = this.mAnimHelper;
        if (popupAnimHelper == null) {
            super.dismiss();
        } else {
            popupAnimHelper.dismissWithAnim(new Runnable() { // from class: miuix.popupwidget.widget.PopupWindow.7
                @Override // java.lang.Runnable
                public void run() {
                    PopupWindow.super.dismiss();
                }
            });
        }
    }

    private void dismissWithNoNotify() {
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        this.mOnDismissListener = null;
        dismiss();
        this.mOnDismissListener = onDismissListener;
    }

    private static Activity getBaseActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private static boolean isActivityRunning(Activity activity) {
        return (activity == null || activity.isDestroyed() || activity.isFinishing()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareShow$2(AdapterView adapterView, View view, int i2, long j2) {
        int headerViewsCount = i2 - this.mListView.getHeaderViewsCount();
        if (this.mOnItemClickListener == null || headerViewsCount < 0 || headerViewsCount >= this.mAdapter.getCount()) {
            return;
        }
        this.mOnItemClickListener.onItemClick(adapterView, view, headerViewsCount, j2);
    }

    private void updateDisplayConfig(View view) {
        if (view == null) {
            view = getDecorView();
        }
        Resources resources = this.mContext.getResources();
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(this.mContext);
        int width = view != null ? view.getWidth() : windowInfo.windowSize.x;
        int height = view != null ? view.getHeight() : windowInfo.windowSize.y;
        this.mMaxAllowedWidth = Math.min(width, resources.getDimensionPixelSize(R.dimen.miuix_popup_window_max_width));
        this.mMinAllowedWidth = Math.min(width, resources.getDimensionPixelSize(R.dimen.miuix_popup_window_min_width));
        this.mMaxAllowedHeight = Math.min(height, resources.getDimensionPixelSize(R.dimen.miuix_popup_window_max_height));
        this.mMinSafeInsetDimen = resources.getDimensionPixelSize(R.dimen.miuix_popup_window_safe_margin);
    }

    private static Rect updateSafeInsetsByDecor(Context context, View view, int i2) {
        Rect rect = new Rect();
        rect.set(i2, 0, i2, i2);
        Rect rect2 = new Rect();
        ViewUtils.getBoundsInWindow(view, rect2);
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets != null) {
            Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            rect.set(insets.left, insets.top, insets.right, insets.bottom);
        }
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(context);
        rect.left = Math.max(i2, rect.left - rect2.left);
        rect.right = Math.max(i2, rect.right - Math.max(0, windowInfo.windowSize.x - rect2.right));
        rect.top = Math.max(i2, rect.top - rect2.top);
        rect.bottom = Math.max(i2, rect.bottom - Math.max(0, windowInfo.windowSize.y - rect2.bottom));
        return rect;
    }

    public void changeWindowBackground(View view) {
        WindowManager.LayoutParams layoutParams;
        if (view == null || (layoutParams = (WindowManager.LayoutParams) view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.flags |= this.mWindowManagerFlags;
        float f2 = this.mDimAmount;
        if (f2 == Float.MAX_VALUE) {
            f2 = ViewUtils.isNightMode(view.getContext()) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT;
        }
        layoutParams.dimAmount = f2;
        ((WindowManager) view.getContext().getSystemService("window")).updateViewLayout(view, layoutParams);
    }

    public int checkMaxHeight(Rect rect, Rect rect2) {
        return Math.min(this.mMaxAllowedHeight, (rect.height() - rect2.top) - rect2.bottom);
    }

    public int checkMaxWidth(Rect rect, Rect rect2) {
        return Math.min(this.mMaxAllowedWidth, (rect.width() - rect2.left) - rect2.right);
    }

    public int checkMinWidth(Rect rect, Rect rect2) {
        return Math.min(this.mMinAllowedWidth, (rect.width() - rect2.left) - rect2.right);
    }

    public void computePopupContentSize() {
        Log.d(TAG, "computePopupContentSize");
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            this.mPopupWindowSpec.mItemViewBounds = getItemViewBounds(listAdapter, null, this.mContext);
        } else {
            getContentViewBounds(this.mPopupWindowSpec);
        }
        this.mPopupWindowStrategy.measureContentSize(this.mPopupWindowSpec);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        detachAnchorView();
        dismissWithAnim();
        SinglePopControl.hidePop(this.mContext, this);
    }

    public void enableHideSoftInput(boolean z2) {
        this.mHideSoftInputEnabled = z2;
    }

    public View getAnchor() {
        WeakReference<View> weakReference = this.mAnchorView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public boolean getAutoDismiss() {
        return this.mAutoDismiss;
    }

    public void getContentViewBounds(PopupWindowSpec popupWindowSpec) {
        if (this.mContentView != null) {
            popupWindowSpec.mContentViewBounds.set(0, 0, 0, 0);
            this.mContentView.measure(0, 0);
            popupWindowSpec.mContentViewBounds.set(0, 0, this.mContentView.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
        }
    }

    public View getDecorView() {
        WeakReference<View> weakReference = this.mFenceDecor;
        if (weakReference != null && weakReference.get() != null) {
            return this.mFenceDecor.get();
        }
        WeakReference<View> weakReference2 = this.mAnchorView;
        if (weakReference2 != null) {
            return weakReference2.get().getRootView();
        }
        return null;
    }

    public float getDimAmount() {
        return this.mDimAmount;
    }

    public int getHorizontalOffset() {
        return this.mPopupWindowSpec.mUserOffsetX;
    }

    public int[][] getItemViewBounds(ListAdapter listAdapter, ViewGroup viewGroup, Context context) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mPopupWindowSpec.mMaxWidth, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, count, 2);
        int i2 = 0;
        View view = null;
        for (int i3 = 0; i3 < count; i3++) {
            int itemViewType = listAdapter.getItemViewType(i3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i3, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iArr[i3][0] = view.getMeasuredWidth();
            iArr[i3][1] = view.getMeasuredHeight();
        }
        return iArr;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public int getVerticalOffset() {
        return this.mPopupWindowSpec.mUserOffsetY;
    }

    public int getWindowManagerFlags() {
        return this.mWindowManagerFlags;
    }

    public void prepareContentView() {
        super.setContentView(this.mRootView);
    }

    public boolean prepareShow(View view) {
        if (view == null) {
            Log.e(TAG, "show: anchor is null");
            return false;
        }
        boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
        if (!this.mIgnoreAnchorVisibility && !localVisibleRect) {
            return false;
        }
        this.mAnchorView = new WeakReference<>(view);
        updatePopupWindowSpec(this.mPopupWindowSpec);
        PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
        if (popupWindowSpec.mMinWidth <= 0 || popupWindowSpec.mMaxWidth <= 0 || popupWindowSpec.mMaxHeight <= 0) {
            return false;
        }
        if (shouldSetElevation()) {
            setElevation(this.mElevation + this.mElevationExtra);
        }
        if (this.mContentView == null) {
            this.mContentView = LayoutInflater.from(this.mContext).inflate(R.layout.miuix_appcompat_drop_down_popup_list, (ViewGroup) null);
            Drawable drawableResolveDrawable = AttributeResolver.resolveDrawable(this.mContext, R.attr.immersionWindowBackground);
            if (drawableResolveDrawable != null) {
                this.mContentView.setBackground(drawableResolveDrawable);
            }
            this.mSpringBackLayout = (SpringBackLayout) this.mContentView.findViewById(R.id.spring_back);
            this.mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.popupwidget.widget.PopupWindow.4
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    boolean zIsNeedScroll;
                    if (PopupWindow.this.mListView.getAdapter() != null) {
                        PopupWindow popupWindow = PopupWindow.this;
                        zIsNeedScroll = popupWindow.mPopupWindowStrategy.isNeedScroll(i5 - i3, popupWindow.mPopupWindowSpec);
                    } else {
                        zIsNeedScroll = true;
                    }
                    PopupWindow.this.mSpringBackLayout.setEnabled(zIsNeedScroll);
                    PopupWindow.this.mListView.setVerticalScrollBarEnabled(zIsNeedScroll);
                }
            });
            setWindowAnimationEnabled(false);
        }
        if (this.mRootView.getChildCount() != 1 || this.mRootView.getChildAt(0) != this.mContentView) {
            this.mRootView.removeAllViews();
            this.mRootView.addView(this.mContentView);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mContentView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.gravity = 16;
            layoutParams.setMargins(35, 35, 35, 35);
        }
        ListView listView = (ListView) this.mContentView.findViewById(android.R.id.list);
        this.mListView = listView;
        if (listView != null) {
            listView.setOnTouchListener(new AnonymousClass5());
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.popupwidget.widget.d
                @Override // android.widget.AdapterView.OnItemClickListener
                public final void onItemClick(AdapterView adapterView, View view2, int i2, long j2) {
                    this.f6143a.lambda$prepareShow$2(adapterView, view2, i2, j2);
                }
            });
            this.mListView.setAdapter(this.mAdapter);
        }
        computePopupContentSize();
        setWidth(this.mPopupWindowSpec.mFinalPopupWidth);
        if (this.mHideSoftInputEnabled) {
            ((InputMethodManager) this.mContext.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (!this.mWindowAnimationEnabled && this.mAnimHelper == null) {
            PopupAnimHelper popupAnimHelper = new PopupAnimHelper(this.mContentView);
            this.mAnimHelper = popupAnimHelper;
            popupAnimHelper.setEdgeExtension(true);
            float f2 = this.mDimAmount;
            if (f2 == Float.MAX_VALUE) {
                f2 = ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT;
            }
            this.mAnimHelper.setDimValue(f2);
        }
        return true;
    }

    public void prepareWindowElevation(View view, int i2) {
        if (shouldSetElevation()) {
            if (MiShadowUtils.SUPPORT_MI_SHADOW) {
                float f2 = view.getContext().getResources().getDisplayMetrics().density;
                MiShadowUtils.setMiShadow(view, this.mShadowColor, 0.0f * f2, f2 * 26.0f, this.mElevation);
            } else {
                view.setElevation(i2);
                setPopupShadowAlpha(view);
            }
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2 = this.mAdapter;
        if (listAdapter2 != null) {
            listAdapter2.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.mObserver);
        }
    }

    public void setAnchorView(@NonNull View view) {
        if (getAnchor() != view) {
            detachAnchorView();
        }
        ViewUtils.getBoundsInWindow(view, this.mPopupWindowSpec.mAnchorViewBounds);
        this.mAnchorView = new WeakReference<>(view);
    }

    @Deprecated
    public void setAnimationGravity(int i2) {
        this.mUserAnimationGravity = i2;
    }

    @Override // android.widget.PopupWindow
    public void setAnimationStyle(int i2) {
        this.mAnimationStyle = i2;
        super.setAnimationStyle(i2);
    }

    public void setAnimationStyleByGravity(int i2) {
        int i3 = R.style.Animation_PopupWindow_ImmersionMenu;
        if (i2 == 51) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_LeftTop;
        } else if (i2 == 83) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_LeftBottom;
        } else if (i2 == 53) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_RightTop;
        } else if (i2 == 85) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_RightBottom;
        } else if (i2 == 48) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_Top;
        } else if (i2 == 80) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_Bottom;
        } else if (i2 == 17) {
            i3 = R.style.Animation_PopupWindow_ImmersionMenu_Center;
        }
        super.setAnimationStyle(i3);
    }

    public void setAutoDismiss(boolean z2) {
        this.mAutoDismiss = z2;
    }

    public void setContentHeight(int i2) {
        this.mContentSize.mHeight = i2;
    }

    @Override // android.widget.PopupWindow
    public void setContentView(View view) {
        if (view instanceof SmoothFrameLayout2) {
            this.mContentView = view;
        } else {
            SmoothFrameLayout2 smoothFrameLayout2 = new SmoothFrameLayout2(this.mContext);
            smoothFrameLayout2.setCornerRadius(this.mContext.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_radius));
            smoothFrameLayout2.addView(view);
            this.mContentView = smoothFrameLayout2;
        }
        this.mRootView.removeAllViews();
        this.mRootView.addView(this.mContentView);
        setPopupWindowStrategy(new PopupWindowStrategy());
        setClippingEnabled(true);
        super.setContentView(this.mRootView);
    }

    public void setContentWidth(int i2) {
        this.mContentSize.updateWidth(i2);
    }

    public void setDecorView(final View view) {
        if (view == null) {
            return;
        }
        this.mFenceDecor = new WeakReference<>(view);
        if (view.isAttachedToWindow()) {
            updatePopupWindowSpec(this.mPopupWindowSpec);
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miuix.popupwidget.widget.PopupWindow.3
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(@NonNull View view2) {
                    PopupWindow popupWindow = PopupWindow.this;
                    popupWindow.updatePopupWindowSpec(popupWindow.mPopupWindowSpec);
                    view.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(@NonNull View view2) {
                }
            });
        }
    }

    public void setDimAmount(float f2) {
        this.mDimAmount = f2;
    }

    public void setDropDownGravity(int i2) {
        if (i2 != -1) {
            this.mPopupWindowSpec.mGravity = i2;
        }
    }

    public void setHasShadow(boolean z2) {
        this.mHasShadow = z2;
    }

    public void setHorizontalOffset(int i2) {
        PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
        popupWindowSpec.mOffsetXSet = true;
        popupWindowSpec.mUserOffsetX = i2;
    }

    public void setMaxAllowedHeight(int i2) {
        this.mMaxAllowedHeight = i2;
    }

    @Override // android.widget.PopupWindow
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setPopupShadowAlpha(View view) {
        if (EnvStateManager.isFreeFormMode(this.mContext)) {
            view.setOutlineProvider(null);
        } else {
            view.setOutlineProvider(new ViewOutlineProvider() { // from class: miuix.popupwidget.widget.PopupWindow.6
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    if (view2.getWidth() == 0 || view2.getHeight() == 0) {
                        return;
                    }
                    outline.setAlpha(AttributeResolver.resolveFloat(view2.getContext(), R.attr.popupWindowShadowAlpha, 0.3f));
                    if (view2.getBackground() != null) {
                        view2.getBackground().getOutline(outline);
                    }
                }
            });
            view.setOutlineSpotShadowColor(this.mContext.getColor(R.color.miuix_appcompat_drop_down_menu_spot_shadow_color));
        }
    }

    public void setPopupWindowStrategy(IPopupWindowStrategy iPopupWindowStrategy) {
        this.mPopupWindowStrategy = iPopupWindowStrategy;
    }

    public void setVerticalOffset(int i2) {
        PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
        popupWindowSpec.mOffsetYSet = true;
        popupWindowSpec.mUserOffsetY = i2;
    }

    public void setWindowAnimationEnabled(boolean z2) {
        this.mWindowAnimationEnabled = z2;
    }

    public void setWindowManagerFlags(int i2) {
        this.mWindowManagerFlags = i2;
    }

    public boolean shouldSetElevation() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            accessibilityManager.isTouchExplorationEnabled();
        }
        return this.mHasShadow;
    }

    public void show(View view, ViewGroup viewGroup) {
        if (getDecorView() != viewGroup) {
            setDecorView(viewGroup);
        }
        show(view);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(@NonNull View view) {
        int iComputeGravity;
        this.mDifferDensityCompat = true;
        Log.d(TAG, "showAsDropDown popupwindowspec:" + this.mPopupWindowSpec);
        PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
        Rect rect = popupWindowSpec.mAnchorViewBounds;
        int xInWindow = this.mPopupWindowStrategy.getXInWindow(popupWindowSpec);
        int yInWindow = this.mPopupWindowStrategy.getYInWindow(this.mPopupWindowSpec);
        PopupWindowSpec popupWindowSpec2 = this.mPopupWindowSpec;
        int i2 = popupWindowSpec2.mFinalPopupWidth;
        int i3 = popupWindowSpec2.mFinalPopupHeight;
        Rect rect2 = new Rect();
        rect2.set(0, 0, i2, i3);
        setWidth(i2);
        setHeight(i3);
        Log.d(TAG, "showWithAnchor getWidth " + i2 + " getHeight " + i3);
        rect2.offsetTo(xInWindow, yInWindow);
        if (this.mAnimationStyle == -1) {
            iComputeGravity = this.mUserAnimationGravity;
            if (iComputeGravity == -1) {
                PopupWindowSpec popupWindowSpec3 = this.mPopupWindowSpec;
                iComputeGravity = computeGravity(popupWindowSpec3.mAnchorViewBounds, rect2, popupWindowSpec3.mGravity, view.getLayoutDirection());
            }
        } else {
            iComputeGravity = 0;
        }
        if (!isShowing()) {
            HapticCompat.performHapticFeedback(view, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        showWithAnim(iComputeGravity);
        super.showAtLocation(getDecorView(), 0, xInWindow, yInWindow);
        prepareWindowElevation(this.mContentView, this.mElevation + this.mElevationExtra);
        this.mRootView.setElevation(0.0f);
        if (this.mWindowAnimationEnabled || this.mAnimHelper == null) {
            changeWindowBackground(this.mRootView.getRootView());
        }
        SinglePopControl.showPop(this.mContext, this);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i2, int i3, int i4) {
        this.mDifferDensityCompat = false;
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int width = getWidth() > 0 ? getWidth() : this.mContentSize.mWidth;
        int height = getHeight() > 0 ? getHeight() : this.mContentSize.mHeight;
        Rect rect2 = new Rect();
        rect2.set(i3, i4, width + i3, height + i4);
        int iComputeGravity = this.mAnimationStyle == -1 ? computeGravity(rect, rect2, 0, view.getLayoutDirection()) : 0;
        if (!isShowing()) {
            HapticCompat.performHapticFeedback(this.mRootView, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        super.showAtLocation(view, i2, i3, i4);
        prepareWindowElevation(this.mContentView, this.mElevation + this.mElevationExtra);
        this.mRootView.setElevation(0.0f);
        if (this.mWindowAnimationEnabled || this.mAnimHelper == null) {
            changeWindowBackground(this.mRootView.getRootView());
        }
        showWithAnim(iComputeGravity);
        SinglePopControl.showPop(this.mContext, this);
    }

    public void showWithAnim(int i2) {
        if (this.mAnimHelper == null || this.mWindowAnimationEnabled) {
            setAnimationStyleByGravity(i2);
            return;
        }
        float f2 = this.mDimAmount;
        if (f2 == Float.MAX_VALUE) {
            f2 = ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT;
        }
        this.mAnimHelper.setDimValue(f2);
        this.mAnimHelper.showWithAnim(i2);
    }

    public void superSetContentViewWithoutClip(View view) {
        this.mRootView.removeAllViews();
        this.mRootView.addView(view);
        this.mContentView = view;
        super.setContentView(this.mRootView);
    }

    public void superShowAtLocation(View view, int i2, int i3, int i4) {
        super.showAtLocation(view, i2, i3, i4);
    }

    public void updateLocation(@NonNull View view) {
        if (isShowing()) {
            computePopupContentSize();
            ViewUtils.getBoundsInWindow(view, this.mPopupWindowSpec.mAnchorViewBounds);
            int xInWindow = this.mPopupWindowStrategy.getXInWindow(this.mPopupWindowSpec);
            int yInWindow = this.mPopupWindowStrategy.getYInWindow(this.mPopupWindowSpec);
            setWidth(this.mPopupWindowSpec.mFinalPopupWidth);
            setHeight(this.mPopupWindowSpec.mFinalPopupHeight);
            PopupWindowSpec popupWindowSpec = this.mPopupWindowSpec;
            update(xInWindow, yInWindow, popupWindowSpec.mFinalPopupWidth, popupWindowSpec.mFinalPopupHeight);
            if (this.mAnimHelper != null) {
                PopupWindowSpec popupWindowSpec2 = this.mPopupWindowSpec;
                this.mAnimHelper.update(computeGravity(this.mPopupWindowSpec.mAnchorViewBounds, new Rect(xInWindow, yInWindow, popupWindowSpec2.mFinalPopupWidth + xInWindow, popupWindowSpec2.mFinalPopupHeight + yInWindow), 0, view.getLayoutDirection()));
            }
        }
    }

    public void updatePopupWindowSpec(PopupWindowSpec popupWindowSpec) {
        View anchor = getAnchor();
        View decorView = getDecorView();
        if (anchor == null || decorView == null) {
            return;
        }
        Rect rectUpdateSafeInsets = updateSafeInsets(decorView);
        ViewUtils.getBoundsInWindow(decorView, popupWindowSpec.mDecorViewBounds);
        ViewUtils.getBoundsInWindow(anchor, popupWindowSpec.mAnchorViewBounds);
        Rect rect = popupWindowSpec.mDecorViewBounds;
        Point windowSize = EnvStateManager.getWindowSize(this.mContext);
        rect.set(Math.max(0, rect.left), Math.max(0, rect.top), Math.min(windowSize.x, rect.right), Math.min(windowSize.y, rect.bottom));
        int iCheckMaxWidth = checkMaxWidth(rect, rectUpdateSafeInsets);
        int iCheckMinWidth = checkMinWidth(rect, rectUpdateSafeInsets);
        int iCheckMaxHeight = checkMaxHeight(rect, rectUpdateSafeInsets);
        popupWindowSpec.mSafeInsets = rectUpdateSafeInsets;
        popupWindowSpec.mMaxWidth = iCheckMaxWidth;
        popupWindowSpec.mMinWidth = iCheckMinWidth;
        popupWindowSpec.mMaxHeight = iCheckMaxHeight;
        popupWindowSpec.layoutDirection = decorView.getLayoutDirection();
    }

    public Rect updateSafeInsets(@NonNull View view) {
        return updateSafeInsetsByDecor(this.mContext, view, this.mMinSafeInsetDimen);
    }

    public class ContainerView extends FrameLayout {
        private Runnable mPopupConfigChangeAction;

        public ContainerView(@NonNull Context context) {
            super(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onConfigurationChanged$0(Configuration configuration) {
            PopupWindow.this.configurationChanged(configuration);
            if (PopupWindow.this.mAutoDismiss) {
                PopupWindow.this.dismiss();
            }
        }

        @Override // android.view.View
        public void onConfigurationChanged(final Configuration configuration) {
            super.onConfigurationChanged(configuration);
            if (this.mPopupConfigChangeAction == null) {
                this.mPopupConfigChangeAction = new Runnable() { // from class: miuix.popupwidget.widget.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6149a.lambda$onConfigurationChanged$0(configuration);
                    }
                };
            }
            post(this.mPopupConfigChangeAction);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            PopupWindow.this.detachAnchorView();
            Runnable runnable = this.mPopupConfigChangeAction;
            if (runnable != null) {
                removeCallbacks(runnable);
            }
        }

        public ContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    public PopupWindow(Context context, View view) {
        this(context, view, null);
    }

    public PopupWindow(Context context, View view, IPopupWindowStrategy iPopupWindowStrategy) {
        super(context);
        this.mUserAnimationGravity = -1;
        this.mAnimationStyle = -1;
        this.mHasShadow = true;
        this.mShadowColor = 0;
        this.mHideSoftInputEnabled = true;
        this.mDifferDensityCompat = false;
        this.mDimAmount = Float.MAX_VALUE;
        this.mWindowManagerFlags = 2;
        this.mAutoDismiss = false;
        this.mIgnoreAnchorVisibility = false;
        this.mWindowAnimationEnabled = true;
        this.mObserver = new AnonymousClass1();
        this.mDetachAnchorLayoutFlag = false;
        this.mAnchorGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: miuix.popupwidget.widget.PopupWindow.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                PopupWindow popupWindow = PopupWindow.this;
                popupWindow.updatePopupWindowSpec(popupWindow.mPopupWindowSpec);
                PopupWindow popupWindow2 = PopupWindow.this;
                popupWindow2.updateLocation(popupWindow2.getAnchor());
            }
        };
        this.mContext = context;
        this.mDensityDpi = context.getResources().getConfiguration().densityDpi;
        AnonymousClass1 anonymousClass1 = null;
        setBackgroundDrawable(null);
        updateDisplayConfig(view);
        this.mPopupWindowSpec = new PopupWindowSpec();
        this.mPopupWindowStrategy = iPopupWindowStrategy;
        if (iPopupWindowStrategy == null) {
            this.mPopupWindowStrategy = new MarginPopupWindowStrategy();
        }
        if (view != null) {
            setDecorView(view);
        }
        this.mContentSize = new ContentSize(anonymousClass1);
        setFocusable(true);
        setOutsideTouchable(true);
        ContainerView containerView = new ContainerView(context);
        this.mRootView = containerView;
        containerView.setClipChildren(false);
        this.mRootView.setClipToPadding(false);
        this.mRootView.setOnClickListener(new View.OnClickListener() { // from class: miuix.popupwidget.widget.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f6144a.lambda$new$0(view2);
            }
        });
        prepareContentView();
        setClippingEnabled(false);
        super.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.popupwidget.widget.f
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                this.f6145a.lambda$new$1();
            }
        });
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mShadowColor = context.getResources().getColor(R.color.miuix_appcompat_drop_down_menu_spot_shadow_color);
        if (MiShadowUtils.SUPPORT_MI_SHADOW) {
            this.mElevation = (int) (f2 * 32.0f);
        } else {
            this.mElevation = AttributeResolver.resolveDimensionPixelSize(context, R.attr.popupWindowElevation);
            this.mElevationExtra = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_menu_popup_extra_elevation);
        }
        this.mDimAmount = AttributeResolver.resolveFloat(context, R.attr.popupWindowDimAmount, Float.MAX_VALUE);
    }

    public void show(View view) {
        if (view == null) {
            return;
        }
        if (getAnchor() != view) {
            setAnchorView(view);
        }
        if (prepareShow(view)) {
            showAsDropDown(view);
        }
    }

    public void show() {
        show(getAnchor());
    }

    public void showAsDropDown(View view, int i2) {
        setDropDownGravity(i2);
        showAsDropDown(view);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        setHorizontalOffset(i2);
        setVerticalOffset(i3);
        showAsDropDown(view);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        setHorizontalOffset(i2);
        setVerticalOffset(i3);
        setDropDownGravity(i4);
        showAsDropDown(view);
    }
}
