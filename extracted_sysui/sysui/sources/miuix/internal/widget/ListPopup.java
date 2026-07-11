package miuix.internal.widget;

import android.content.Context;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import miuix.animation.ViewHoverListener;
import miuix.animation.utils.LogUtils;
import miuix.appcompat.R;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.WindowBaseInfo;
import miuix.core.util.WindowUtils;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.ListPopup;
import miuix.popupwidget.internal.util.SinglePopControl;
import miuix.theme.token.DimToken;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
public class ListPopup extends PopupWindow {
    private static final float OFFSET_X = 8.0f;
    private static final float OFFSET_Y = 8.0f;
    private static final int SHADOW_OFFSET_X = 0;
    private static final int SHADOW_OFFSET_Y = 26;
    private static final int SHADOW_RADIUS = 32;
    private static final String TAG = "ListPopupWindow";
    private ListAdapter mAdapter;
    private WeakReference<View> mAnchor;
    protected final Rect mBackgroundPadding;
    private ContentSize mContentSize;
    protected View mContentView;
    private Context mContext;
    private int mDropDownGravity;
    protected int mElevation;
    protected int mElevationExtra;
    private WeakReference<View> mFenceDecor;
    private boolean mHasShadow;
    private boolean mIsCustomContent;
    private ListView mListView;
    protected int mMaxAllowedHeight;
    private int mMaxAllowedWidth;
    private int mMinAllowedWidth;
    private int mMinSafeInset;
    private DataSetObserver mObserver;
    private int mOffsetFromStatusBar;
    private int mOffsetX;
    private boolean mOffsetXSet;
    private int mOffsetY;
    private boolean mOffsetYSet;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    private Rect mPositionSafeInsets;
    protected FrameLayout mRootView;
    private int mShadowColor;
    private int mUserAnimationGravity;
    private Rect mWindowDecorBounds;

    /* JADX INFO: renamed from: miuix.internal.widget.ListPopup$1, reason: invalid class name */
    public class AnonymousClass1 extends DataSetObserver {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onChanged$0(View view) {
            FrameLayout frameLayout = ListPopup.this.mRootView;
            if (frameLayout == null || !frameLayout.isAttachedToWindow()) {
                return;
            }
            ListPopup.this.updatePosition(view);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            final View anchor;
            ListPopup.this.mContentSize.mHasContentWidth = false;
            if (!ListPopup.this.isShowing() || (anchor = ListPopup.this.getAnchor()) == null) {
                return;
            }
            anchor.post(new Runnable() { // from class: miuix.internal.widget.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6108a.lambda$onChanged$0(anchor);
                }
            });
        }
    }

    /* JADX INFO: renamed from: miuix.internal.widget.ListPopup$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnTouchListener {
        int lastIndex = -1;

        public AnonymousClass4() {
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
                    Log.e(ListPopup.TAG, "list onTouch error " + e2);
                }
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            int firstVisiblePosition;
            int i2;
            int iPointToPosition = ListPopup.this.mListView.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1 || action == 3 || action == 6) {
                    this.lastIndex = -1;
                    ListPopup.this.mListView.postDelayed(new Runnable() { // from class: miuix.internal.widget.j
                        @Override // java.lang.Runnable
                        public final void run() {
                            ListPopup.AnonymousClass4.lambda$onTouch$0(view);
                        }
                    }, ViewConfiguration.getPressedStateDuration());
                }
            } else if (iPointToPosition != -1 && (firstVisiblePosition = iPointToPosition - ListPopup.this.mListView.getFirstVisiblePosition()) != (i2 = this.lastIndex)) {
                if (i2 != -1) {
                    ListPopup.this.mListView.getChildAt(this.lastIndex).setPressed(false);
                }
                ListPopup.this.mListView.getChildAt(firstVisiblePosition).setPressed(true);
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

    public ListPopup(Context context) {
        this(context, null);
    }

    private int calculateXoffset(int i2, Rect rect, Rect rect2) {
        int absoluteGravity = Gravity.getAbsoluteGravity(this.mDropDownGravity, i2) & 7;
        return absoluteGravity != 1 ? absoluteGravity != 5 ? calculateXoffsetAlignLeft(rect, rect2) : calculateXoffsetAlignRight(rect, rect2) : calculateXoffsetAlignCenterHorizontal(rect, rect2);
    }

    private int calculateXoffsetAlignCenterHorizontal(Rect rect, Rect rect2) {
        int i2;
        boolean z2;
        int iCenterX = rect.centerX();
        int i3 = rect.left;
        int i4 = this.mContentSize.mWidth;
        int i5 = i3 + i4;
        int i6 = (i4 / 2) + i3;
        int i7 = rect2.right;
        Rect rect3 = this.mPositionSafeInsets;
        int i8 = rect3.right;
        if (i5 > i7 - i8) {
            i2 = (i7 - i8) - i5;
            z2 = true;
        } else {
            i2 = 0;
            z2 = false;
        }
        if (z2) {
            return i2;
        }
        int i9 = iCenterX - i6;
        return i3 + i9 >= rect2.left + rect3.left ? i9 : i2;
    }

    private int calculateXoffsetAlignLeft(Rect rect, Rect rect2) {
        boolean z2;
        int i2;
        int i3 = rect.left;
        boolean z3 = this.mOffsetXSet;
        int i4 = (z3 ? this.mOffsetX : 0) + i3 + this.mContentSize.mWidth;
        int i5 = rect2.right;
        Rect rect3 = this.mPositionSafeInsets;
        int i6 = rect3.right;
        if (i4 > i5 - i6) {
            i2 = (i5 - i6) - i4;
            z2 = true;
        } else {
            z2 = false;
            i2 = 0;
        }
        if (z2) {
            return i2;
        }
        int i7 = z3 ? this.mOffsetX : 0;
        int i8 = i3 + i7;
        int i9 = rect2.left;
        int i10 = rect3.left;
        int i11 = i8 < i9 + i10 ? (i9 + i10) - i8 : i7;
        return i11 != 0 ? i11 - this.mBackgroundPadding.left : i11;
    }

    private int calculateXoffsetAlignRight(Rect rect, Rect rect2) {
        boolean z2;
        int i2;
        int i3 = rect.right;
        boolean z3 = this.mOffsetXSet;
        int i4 = ((z3 ? this.mOffsetX : 0) + i3) - this.mContentSize.mWidth;
        int i5 = rect2.left;
        Rect rect3 = this.mPositionSafeInsets;
        int i6 = rect3.left;
        if (i4 < i5 + i6) {
            i2 = (i5 + i6) - i4;
            z2 = true;
        } else {
            z2 = false;
            i2 = 0;
        }
        if (z2) {
            return i2;
        }
        int i7 = z3 ? this.mOffsetX : 0;
        int i8 = i3 + i7;
        int i9 = rect2.right;
        int i10 = rect3.right;
        int i11 = i8 > i9 - i10 ? (i9 - i10) - i8 : i7;
        return i11 != 0 ? i11 + this.mBackgroundPadding.right : i11;
    }

    private int calculateYoffset(Rect rect, Rect rect2) {
        int i2 = this.mOffsetYSet ? this.mOffsetY : (-rect.height()) - this.mBackgroundPadding.top;
        int iCheckMaxHeight = checkMaxHeight(rect2);
        int iMin = iCheckMaxHeight > 0 ? Math.min(this.mContentSize.mHeight, iCheckMaxHeight) : this.mContentSize.mHeight;
        int i3 = rect2.bottom;
        int i4 = this.mPositionSafeInsets.bottom;
        int i5 = (i3 - i4) - rect.bottom;
        int i6 = (rect.top - i4) - rect2.top;
        if (iMin + i2 > i5) {
            if (i5 < i6) {
                iHeight = (this.mOffsetYSet ? rect.height() : 0) + iMin;
            } else if (this.mOffsetYSet) {
                iHeight = rect.height();
            }
            i2 -= iHeight;
        }
        int i7 = rect.bottom + i2;
        int i8 = rect2.top;
        int i9 = this.mPositionSafeInsets.top;
        if (i7 < i8 + i9) {
            int i10 = (i8 + i9) - i7;
            setHeight(iMin - i10);
            i2 += i10;
        }
        int i11 = i7 + iMin;
        int i12 = rect2.bottom;
        int i13 = this.mPositionSafeInsets.bottom;
        if (i11 > i12 - i13) {
            setHeight(iMin - (i11 - (i12 - i13)));
        }
        return i2;
    }

    public static void changeWindowBackground(View view) {
        WindowManager.LayoutParams layoutParams;
        if (view == null || (layoutParams = (WindowManager.LayoutParams) view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.flags |= 2;
        layoutParams.dimAmount = ViewUtils.isNightMode(view.getContext()) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT;
        ((WindowManager) view.getContext().getSystemService("window")).updateViewLayout(view, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configurationChanged(Configuration configuration) {
        this.mRootView.post(new Runnable() { // from class: miuix.internal.widget.ListPopup.2
            @Override // java.lang.Runnable
            public void run() {
                FrameLayout frameLayout = ListPopup.this.mRootView;
                if (frameLayout == null || !frameLayout.isAttachedToWindow()) {
                    return;
                }
                ListPopup.this.updatePosition(ListPopup.this.mAnchor != null ? (View) ListPopup.this.mAnchor.get() : null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getAnchor() {
        WeakReference<View> weakReference = this.mAnchor;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getDecorView(@NonNull View view) {
        View view2 = this.mFenceDecor.get();
        return view2 != null ? view2 : view.getRootView();
    }

    private Rect getWindowDecorVisibleBounds(@NonNull View view) {
        Rect rect = new Rect();
        View rootView = view.getRootView();
        if (rootView != null) {
            view = rootView;
        }
        view.getWindowVisibleDisplayFrame(rect);
        return rect;
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

    private void measureContentSize(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i3 = 0;
        int i4 = 0;
        int measuredHeight = 0;
        View view = null;
        for (int i5 = 0; i5 < count; i5++) {
            int itemViewType = listAdapter.getItemViewType(i5);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (viewGroup == null) {
                viewGroup = new FrameLayout(context);
            }
            view = listAdapter.getView(i5, view, viewGroup);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            measuredHeight += view.getMeasuredHeight();
            if (!this.mContentSize.mHasContentWidth) {
                int measuredWidth = view.getMeasuredWidth();
                if (measuredWidth >= i2) {
                    this.mContentSize.updateWidth(i2);
                } else if (measuredWidth > i4) {
                    i4 = measuredWidth;
                }
            }
        }
        ContentSize contentSize = this.mContentSize;
        if (!contentSize.mHasContentWidth) {
            contentSize.updateWidth(i4);
        }
        this.mContentSize.mHeight = measuredHeight;
    }

    private void setAnimationStyleByGravity(int i2) {
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
        setAnimationStyle(i3);
    }

    private boolean shouldSetElevation() {
        return this.mHasShadow;
    }

    private void showWithAnchor(View view, Rect rect) {
        view.getLocationInWindow(new int[2]);
        Rect rect2 = new Rect();
        ViewUtils.getBoundsInWindow(view, rect2);
        int iCalculateYoffset = calculateYoffset(rect2, rect);
        int iCalculateXoffset = calculateXoffset(view.getLayoutDirection(), rect2, rect);
        int width = getWidth() > 0 ? getWidth() : this.mContentSize.mWidth;
        int height = getHeight() > 0 ? getHeight() : this.mContentSize.mHeight;
        Rect rect3 = new Rect();
        rect3.set(0, 0, width, height);
        Log.d("ListPopup", "showWithAnchor getWidth " + getWidth() + " getHeight " + getHeight());
        int absoluteGravity = Gravity.getAbsoluteGravity(this.mDropDownGravity, view.getLayoutDirection());
        int i2 = absoluteGravity & 112;
        if ((absoluteGravity & 7) == 5) {
            rect3.offsetTo((rect2.right + iCalculateXoffset) - rect3.width(), rect2.bottom + iCalculateYoffset);
        } else {
            rect3.offsetTo(rect2.left + iCalculateXoffset, rect2.bottom + iCalculateYoffset);
        }
        int i3 = 48;
        if (Math.abs(rect3.centerY() - rect2.centerY()) <= 10 ? i2 == 80 : rect3.centerY() <= rect2.centerY()) {
            i3 = 80;
        }
        if (Math.abs(rect3.centerX() - rect2.centerX()) > 10) {
            i3 = rect3.centerX() > rect2.centerX() ? i3 | 3 : i3 | 5;
        }
        int i4 = this.mUserAnimationGravity;
        if (i4 != -1) {
            setAnimationStyleByGravity(i4);
        } else {
            setAnimationStyleByGravity(i3);
        }
        if (!isShowing()) {
            HapticCompat.performHapticFeedback(view, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        showAsDropDown(view, iCalculateXoffset, iCalculateYoffset, this.mDropDownGravity);
        changeWindowBackground(this.mRootView.getRootView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePosition(View view) {
        if (view == null) {
            return;
        }
        View decorView = getDecorView(view);
        Rect rect = new Rect();
        ViewUtils.getBoundsInWindow(decorView, rect);
        updateSafeInsetsByDecor(decorView, rect, getWindowDecorActualBounds(), getWindowDecorVisibleBounds(view));
        int iCheckMaxHeight = checkMaxHeight(rect);
        int iComputePopupContentWidth = computePopupContentWidth(rect);
        int i2 = this.mContentSize.mHeight;
        int i3 = (iCheckMaxHeight <= 0 || i2 <= iCheckMaxHeight) ? i2 : iCheckMaxHeight;
        Rect rect2 = new Rect();
        ViewUtils.getBoundsInWindow(view, rect2);
        update(view, calculateXoffset(view.getLayoutDirection(), rect2, rect), calculateYoffset(rect2, rect), iComputePopupContentWidth, i3);
    }

    private void updateSafeInsetsByDecor(View view, Rect rect, Rect rect2, Rect rect3) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets != null) {
            Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            this.mPositionSafeInsets.set(insets.left, insets.top, insets.right, insets.bottom);
        }
        int i2 = rect3.left - rect2.left;
        int i3 = rect2.right - rect3.right;
        int i4 = rect3.top - rect2.top;
        int i5 = rect2.bottom - rect3.bottom;
        Rect rect4 = this.mPositionSafeInsets;
        rect4.left = Math.max(this.mMinSafeInset, (rect4.left - rect.left) - i2);
        Rect rect5 = this.mPositionSafeInsets;
        rect5.right = Math.max(this.mMinSafeInset, (rect5.right - Math.max(0, rect3.width() - rect.right)) - i3);
        Rect rect6 = this.mPositionSafeInsets;
        rect6.top = Math.max(this.mMinSafeInset, (rect6.top - rect.top) - i4);
        Rect rect7 = this.mPositionSafeInsets;
        rect7.bottom = Math.max(this.mMinSafeInset, (rect7.bottom - Math.max(0, rect3.height() - rect.bottom)) - i5);
    }

    public int checkMaxHeight(Rect rect) {
        int i2 = this.mMaxAllowedHeight;
        int iHeight = rect.height();
        Rect rect2 = this.mPositionSafeInsets;
        return Math.min(i2, (iHeight - rect2.top) - rect2.bottom);
    }

    public int checkMaxWidth(Rect rect) {
        int i2 = this.mMaxAllowedWidth;
        int iWidth = rect.width();
        Rect rect2 = this.mPositionSafeInsets;
        return Math.min(i2, (iWidth - rect2.left) - rect2.right);
    }

    public int checkMinWidth(Rect rect) {
        int i2 = this.mMinAllowedWidth;
        int iWidth = rect.width();
        Rect rect2 = this.mPositionSafeInsets;
        return Math.min(i2, (iWidth - rect2.left) - rect2.right);
    }

    public int computePopupContentWidth(Rect rect) {
        if (!this.mContentSize.mHasContentWidth) {
            measureContentSize(this.mAdapter, null, this.mContext, checkMaxWidth(rect));
        }
        int iMax = Math.max(this.mContentSize.mWidth, checkMinWidth(rect));
        Rect rect2 = this.mBackgroundPadding;
        int i2 = iMax + rect2.left + rect2.right;
        this.mContentSize.updateWidth(i2);
        return i2;
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
        SinglePopControl.hidePop(this.mContext, this);
    }

    public void fastShow(@NonNull View view, ViewGroup viewGroup) {
        View decorView = getDecorView(view);
        Rect rect = new Rect();
        ViewUtils.getBoundsInWindow(decorView, rect);
        setWidth(computePopupContentWidth(rect));
        int i2 = this.mContentSize.mHeight;
        int iCheckMaxHeight = checkMaxHeight(rect);
        if (i2 > iCheckMaxHeight) {
            i2 = iCheckMaxHeight;
        }
        setHeight(i2);
        showWithAnchor(view, rect);
    }

    public int getHorizontalOffset() {
        return this.mOffsetX;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public int getMinMarginScreen() {
        return this.mMinSafeInset;
    }

    public int getOffsetFromStatusBar() {
        return this.mOffsetFromStatusBar;
    }

    public Rect getPositionSafeInsets() {
        return this.mPositionSafeInsets;
    }

    public int getVerticalOffset() {
        return this.mOffsetY;
    }

    public Rect getWindowDecorActualBounds() {
        Rect rect = new Rect();
        WindowUtils.getWindowBounds(WindowUtils.getWindowManager(this.mContext), this.mContext, rect);
        return rect;
    }

    public boolean isNeedScroll(int i2, Rect rect) {
        int iCheckMaxHeight = checkMaxHeight(rect);
        int i3 = this.mContentSize.mHeight;
        return i3 > i2 || i3 > iCheckMaxHeight;
    }

    public void prepareContentView(Context context) {
        super.setContentView(this.mRootView);
    }

    public boolean prepareShow(View view, ViewGroup viewGroup, Rect rect) {
        if (view == null) {
            Log.e(TAG, "show: anchor is null");
            return false;
        }
        Log.d("ListPopup", "prepareShow");
        if (shouldSetElevation()) {
            setElevation(this.mElevation + this.mElevationExtra);
        }
        if (this.mContentView == null) {
            this.mContentView = LayoutInflater.from(this.mContext).inflate(R.layout.miuix_appcompat_list_popup_list, (ViewGroup) null);
            Drawable drawableResolveDrawable = AttributeResolver.resolveDrawable(this.mContext, R.attr.immersionWindowBackground);
            if (drawableResolveDrawable != null) {
                drawableResolveDrawable.getPadding(this.mBackgroundPadding);
                this.mContentView.setBackground(drawableResolveDrawable);
            }
            this.mContentView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: miuix.internal.widget.ListPopup.3
                private int lastContentHeight = -1;

                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    boolean zIsNeedScroll;
                    int measuredHeight = ListPopup.this.mContentView.getMeasuredHeight();
                    int i10 = this.lastContentHeight;
                    if (i10 == -1 || i10 != measuredHeight) {
                        if (ListPopup.this.mListView.getAdapter() != null) {
                            View anchor = ListPopup.this.getAnchor();
                            Rect rect2 = new Rect();
                            if (anchor != null) {
                                ViewUtils.getBoundsInWindow(ListPopup.this.getDecorView(anchor), rect2);
                            } else {
                                Point point = EnvStateManager.getWindowInfo(ListPopup.this.mContext).windowSize;
                                rect2.set(0, 0, point.x, point.y);
                            }
                            zIsNeedScroll = ListPopup.this.isNeedScroll(i5 - i3, rect2);
                        } else {
                            zIsNeedScroll = true;
                        }
                        ListPopup.this.mContentView.setEnabled(zIsNeedScroll);
                        ListPopup.this.mListView.setVerticalScrollBarEnabled(zIsNeedScroll);
                        this.lastContentHeight = measuredHeight;
                    }
                }
            });
            this.mIsCustomContent = false;
        }
        if (this.mRootView.getChildCount() != 1 || this.mRootView.getChildAt(0) != this.mContentView) {
            this.mRootView.removeAllViews();
            this.mRootView.addView(this.mContentView);
            if (this.mIsCustomContent) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mContentView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -2;
                layoutParams.gravity = 16;
            }
        }
        ListView listView = (ListView) this.mContentView.findViewById(android.R.id.list);
        this.mListView = listView;
        if (listView == null) {
            Log.e(TAG, "list not found");
            return false;
        }
        listView.setOnTouchListener(new AnonymousClass4());
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.internal.widget.h
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view2, int i2, long j2) {
                this.f6107a.lambda$prepareShow$2(adapterView, view2, i2, j2);
            }
        });
        this.mListView.setAdapter(this.mAdapter);
        setWidth(computePopupContentWidth(rect));
        int iCheckMaxHeight = checkMaxHeight(rect);
        setHeight(iCheckMaxHeight > 0 ? Math.min(this.mContentSize.mHeight, iCheckMaxHeight) : -2);
        ((InputMethodManager) this.mContext.getApplicationContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    public void setAnimationGravity(int i2) {
        this.mUserAnimationGravity = i2;
    }

    public void setContentHeight(int i2) {
        this.mContentSize.mHeight = i2;
    }

    public void setContentWidth(int i2) {
        this.mContentSize.updateWidth(i2);
    }

    public void setDropDownGravity(int i2) {
        this.mDropDownGravity = i2;
    }

    public void setFenceDecor(View view) {
        this.mFenceDecor = new WeakReference<>(view);
    }

    public void setHasShadow(boolean z2) {
        this.mHasShadow = z2;
    }

    public void setHorizontalOffset(int i2) {
        this.mOffsetX = i2;
        this.mOffsetXSet = true;
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
            view.setOutlineProvider(new ViewOutlineProvider() { // from class: miuix.internal.widget.ListPopup.5
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

    public void setPopupWindowContentView(View view) {
        this.mIsCustomContent = true;
        super.setContentView(view);
    }

    public void setVerticalOffset(int i2) {
        this.mOffsetY = i2;
        this.mOffsetYSet = true;
    }

    public void show(View view, ViewGroup viewGroup) {
        if (view == null) {
            return;
        }
        View decorView = getDecorView(view);
        Rect rect = new Rect();
        ViewUtils.getBoundsInWindow(decorView, rect);
        updateSafeInsetsByDecor(decorView, rect, getWindowDecorActualBounds(), getWindowDecorVisibleBounds(view));
        if (prepareShow(view, viewGroup, rect)) {
            showWithAnchor(view, rect);
        }
        prepareWindowElevation(this.mContentView, this.mElevation + this.mElevationExtra);
        this.mRootView.setElevation(0.0f);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        super.showAsDropDown(view, i2, i3, i4);
        this.mAnchor = new WeakReference<>(view);
        SinglePopControl.showPop(this.mContext, this);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i2, int i3, int i4) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int width = getWidth() > 0 ? getWidth() : this.mContentSize.mWidth;
        int height = getHeight() > 0 ? getHeight() : this.mContentSize.mHeight;
        Rect rect2 = new Rect();
        rect2.set(i3, i4, width + i3, height + i4);
        Log.d("ListPopup", "showAtLocation getWidth " + getWidth() + " getHeight " + getHeight());
        int i5 = rect2.top > rect.centerY() ? 48 : rect2.bottom <= rect.centerY() ? 80 : 0;
        int i6 = rect2.left;
        int i7 = rect.left;
        if (i6 >= i7 && rect2.right > rect.right) {
            i5 |= 3;
        } else if (rect2.right <= rect.right && i6 < i7) {
            i5 |= 5;
        }
        if (i5 == 0 && rect.contains(rect2)) {
            i5 = 17;
        }
        int i8 = this.mUserAnimationGravity;
        if (i8 != -1) {
            setAnimationStyleByGravity(i8);
        } else {
            setAnimationStyleByGravity(i5);
        }
        super.showAtLocation(view, i2, i3, i4);
        prepareWindowElevation(this.mContentView, this.mElevation + this.mElevationExtra);
        this.mRootView.setElevation(0.0f);
        SinglePopControl.showPop(this.mContext, this);
    }

    @Override // android.widget.PopupWindow
    public void update(int i2, int i3, int i4, int i5, boolean z2) {
        KeyEvent.Callback anchor = getAnchor();
        if ((anchor instanceof ViewHoverListener) && ((ViewHoverListener) anchor).isHover()) {
            LogUtils.debug("popupWindow update return", anchor);
        } else {
            LogUtils.debug("popupWindow update execute", anchor);
            super.update(i2, i3, i4, i5, z2);
        }
    }

    public class ContainerView extends FrameLayout {
        public ContainerView(@NonNull Context context) {
            super(context);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            ListPopup.this.configurationChanged(configuration);
        }

        public ContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public ContainerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
        }
    }

    public ListPopup(Context context, View view) {
        super(context);
        this.mDropDownGravity = 8388661;
        this.mUserAnimationGravity = -1;
        this.mOffsetFromStatusBar = 0;
        this.mHasShadow = true;
        this.mShadowColor = 0;
        this.mIsCustomContent = false;
        this.mObserver = new AnonymousClass1();
        this.mContext = context;
        AnonymousClass1 anonymousClass1 = null;
        setBackgroundDrawable(null);
        setHeight(-2);
        this.mFenceDecor = new WeakReference<>(view);
        Resources resources = context.getResources();
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(this.mContext);
        Log.d("ListPopup", "new windowInfo w " + windowInfo.windowSize.x + " h " + windowInfo.windowSize.y);
        this.mMinSafeInset = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_context_menu_window_margin_screen);
        Rect rect = new Rect();
        this.mPositionSafeInsets = rect;
        int i2 = this.mMinSafeInset;
        rect.set(i2, i2, i2, i2);
        if (view != null) {
            Rect rect2 = new Rect();
            ViewUtils.getBoundsInWindow(view, rect2);
            Point point = windowInfo.windowSize;
            Rect rect3 = new Rect(0, 0, point.x, point.y);
            Point point2 = windowInfo.windowSize;
            updateSafeInsetsByDecor(view, rect2, rect3, new Rect(0, 0, point2.x, point2.y));
        }
        int width = view != null ? view.getWidth() : windowInfo.windowSize.x;
        int height = view != null ? view.getHeight() : windowInfo.windowSize.y;
        this.mMaxAllowedWidth = Math.min(width, resources.getDimensionPixelSize(R.dimen.miuix_appcompat_popup_menu_max_width));
        this.mMinAllowedWidth = Math.min(width, resources.getDimensionPixelSize(R.dimen.miuix_appcompat_popup_menu_min_width));
        this.mMaxAllowedHeight = Math.min(height, resources.getDimensionPixelSize(R.dimen.miuix_appcompat_popup_menu_max_height));
        float f2 = this.mContext.getResources().getDisplayMetrics().density;
        int i3 = (int) (8.0f * f2);
        this.mOffsetX = i3;
        this.mOffsetY = i3;
        this.mBackgroundPadding = new Rect();
        this.mContentSize = new ContentSize(anonymousClass1);
        setFocusable(true);
        setOutsideTouchable(true);
        ContainerView containerView = new ContainerView(context);
        this.mRootView = containerView;
        containerView.setClipChildren(false);
        this.mRootView.setClipToPadding(false);
        this.mRootView.setOnClickListener(new View.OnClickListener() { // from class: miuix.internal.widget.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f6105a.lambda$new$0(view2);
            }
        });
        prepareContentView(context);
        setAnimationStyle(R.style.Animation_PopupWindow_ImmersionMenu);
        super.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.internal.widget.g
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                this.f6106a.lambda$new$1();
            }
        });
        this.mOffsetFromStatusBar = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_context_menu_window_margin_statusbar);
        this.mShadowColor = this.mContext.getResources().getColor(R.color.miuix_appcompat_drop_down_menu_spot_shadow_color);
        if (MiShadowUtils.SUPPORT_MI_SHADOW) {
            this.mElevation = (int) (f2 * 32.0f);
        } else {
            this.mElevation = AttributeResolver.resolveDimensionPixelSize(this.mContext, R.attr.popupWindowElevation);
            this.mElevationExtra = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_menu_popup_extra_elevation);
        }
    }
}
