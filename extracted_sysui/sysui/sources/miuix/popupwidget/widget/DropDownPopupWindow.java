package miuix.popupwidget.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Locale;
import miuix.core.util.MiShadowUtils;
import miuix.core.util.WindowUtils;
import miuix.internal.util.DeviceHelper;
import miuix.popupwidget.R;
import miuix.popupwidget.widget.DropDownPopupWindow;

/* JADX INFO: loaded from: classes5.dex */
public class DropDownPopupWindow {
    private static final int SHADOW_OFFSET_X = 0;
    private static final int SHADOW_OFFSET_Y = 26;
    private static final int SHADOW_RADIUS = 32;
    private static final String TAG = "DropDownPopupWindow";
    private View mAnchorView;
    private ValueAnimator mAnimator;
    private int mBottomEdge;
    private ContainerView mContainer;
    private ContainerController mContainerController;
    private ContentController mContentController;
    private int mContentHeight;
    private View mContentView;
    private Context mContext;
    private boolean mDismissPending;
    private Controller mDropDownController;
    private int mEdgeDistance;
    private int mElevation;
    private int mMaxHeight;
    private int mMaxWidth;
    private int mMinWidth;
    private android.widget.PopupWindow mPopupWindow;
    private View mRealContainerView;
    private int mTopEdge;
    private int mWindowHeight;
    private int mWindowWidth;
    private int mShowDuration = 300;
    private int mDismissDuration = 300;
    private int mShadowColor = 0;
    private ValueAnimator.AnimatorUpdateListener mValueUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: miuix.popupwidget.widget.DropDownPopupWindow.1
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) DropDownPopupWindow.this.mAnimator.getAnimatedValue()).floatValue();
            if (DropDownPopupWindow.this.mContainerController != null) {
                DropDownPopupWindow.this.mContainerController.onAnimationUpdate(DropDownPopupWindow.this.mContainer, fFloatValue);
            }
            if (DropDownPopupWindow.this.mContentController != null) {
                DropDownPopupWindow.this.mContentController.onAnimationUpdate(DropDownPopupWindow.this.mContentView, fFloatValue);
            }
        }
    };
    private Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() { // from class: miuix.popupwidget.widget.DropDownPopupWindow.2
        private void tryDismiss() {
            if (DropDownPopupWindow.this.mDismissPending) {
                DropDownPopupWindow.this.realDismiss();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            tryDismiss();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            tryDismiss();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (!DropDownPopupWindow.this.mDismissPending || DropDownPopupWindow.this.mDropDownController == null) {
                return;
            }
            DropDownPopupWindow.this.mDropDownController.onDismiss();
        }
    };

    public interface ContainerController extends Controller {
        boolean onAddContent(View view, View view2);
    }

    public class ContainerView extends FrameLayout {
        public ContainerView(Context context, AttributeSet attributeSet, int i2) {
            super(context, attributeSet, i2);
            setClipChildren(false);
            setClipToPadding(false);
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            DropDownPopupWindow.this.configurationChanged(configuration);
        }

        @Override // android.view.View
        public boolean onKeyPreIme(int i2, KeyEvent keyEvent) {
            if (i2 != 4 || keyEvent.getAction() != 1) {
                return false;
            }
            DropDownPopupWindow.this.dismiss();
            return true;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!super.onTouchEvent(motionEvent) && motionEvent.getAction() == 1) {
                DropDownPopupWindow.this.dismiss();
            }
            return true;
        }
    }

    public interface ContentController extends Controller {
        View getContentView();
    }

    public interface Controller {
        void onAnimationUpdate(View view, float f2);

        void onDismiss();

        void onShow();
    }

    public static class DefaultContainerController implements ContainerController {
        @Override // miuix.popupwidget.widget.DropDownPopupWindow.ContainerController
        public boolean onAddContent(View view, View view2) {
            return false;
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onAnimationUpdate(View view, float f2) {
            Drawable background = view == null ? null : view.getBackground();
            if (background != null) {
                background.setAlpha((int) (f2 * 255.0f));
            }
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onDismiss() {
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onShow() {
        }
    }

    public static class ListController extends ViewContentController {
        private ListView mListView;

        /* JADX INFO: renamed from: miuix.popupwidget.widget.DropDownPopupWindow$ListController$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnTouchListener {
            int lastIndex = -1;

            public AnonymousClass1() {
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
                        Log.e(DropDownPopupWindow.TAG, "list onTouch error " + e2);
                    }
                }
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(final View view, MotionEvent motionEvent) {
                int firstVisiblePosition;
                int i2;
                int iPointToPosition = ListController.this.mListView.pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1 || action == 3 || action == 6) {
                        this.lastIndex = -1;
                        ListController.this.mListView.postDelayed(new Runnable() { // from class: miuix.popupwidget.widget.b
                            @Override // java.lang.Runnable
                            public final void run() {
                                DropDownPopupWindow.ListController.AnonymousClass1.lambda$onTouch$0(view);
                            }
                        }, ViewConfiguration.getPressedStateDuration());
                    }
                } else if (iPointToPosition != -1 && (firstVisiblePosition = iPointToPosition - ListController.this.mListView.getFirstVisiblePosition()) != (i2 = this.lastIndex)) {
                    if (i2 != -1) {
                        ListController.this.mListView.getChildAt(this.lastIndex).setPressed(false);
                    }
                    ListController.this.mListView.getChildAt(firstVisiblePosition).setPressed(true);
                    this.lastIndex = firstVisiblePosition;
                }
                return false;
            }
        }

        public ListController(DropDownPopupWindow dropDownPopupWindow) {
            super(dropDownPopupWindow, R.layout.miuix_appcompat_drop_down_popup_list);
        }

        public ListView getListView() {
            initContent();
            return this.mListView;
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.ViewContentController
        public void onContentInit(View view) {
            ListView listView = (ListView) view.findViewById(android.R.id.list);
            this.mListView = listView;
            listView.setOnTouchListener(new AnonymousClass1());
        }

        public ListController(DropDownPopupWindow dropDownPopupWindow, int i2) {
            super(dropDownPopupWindow, i2);
        }

        public ListController(Context context) {
            this(context, R.layout.miuix_appcompat_drop_down_popup_list);
        }

        public ListController(Context context, int i2) {
            super(context, i2);
        }
    }

    public DropDownPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this.mContext = context;
        this.mPopupWindow = new android.widget.PopupWindow(context, attributeSet, 0, i2);
        ContainerView containerView = new ContainerView(context, attributeSet, i2);
        this.mContainer = containerView;
        containerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miuix.popupwidget.widget.DropDownPopupWindow.4
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(@NonNull View view) {
                DropDownPopupWindow.this.adjustLocation(DropDownPopupWindow.this.getCutout(view), null);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(@NonNull View view) {
            }
        });
        this.mPopupWindow.setAnimationStyle(DeviceHelper.isFeatureWholeAnim() ? R.style.Animation_PopupWindow_DropDown : 0);
        initPopupWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void adjustLocation(Rect rect, WindowInsets windowInsets) {
        if (this.mAnchorView == null || this.mRealContainerView == null) {
            return;
        }
        updateMaxWidth(rect);
        updateMaxHeight(windowInsets);
        int width = this.mPopupWindow.getWidth();
        ContentController contentController = this.mContentController;
        if (contentController != null) {
            View contentView = contentController.getContentView();
            this.mContentView = contentView;
            if (contentView != null) {
                width = setupContentView(this.mContainer, contentView, this.mElevation, this.mMinWidth, this.mContainerController);
            }
        }
        int i2 = this.mMaxWidth;
        if (width > i2) {
            width = i2;
        }
        int i3 = this.mContentHeight;
        int i4 = this.mMaxHeight;
        if (i3 > i4) {
            this.mPopupWindow.setHeight(i4);
        } else {
            this.mPopupWindow.setHeight(-2);
        }
        this.mPopupWindow.setWidth(width);
        int height = this.mPopupWindow.getHeight();
        View view = this.mAnchorView;
        if (view != null && view.isAttachedToWindow()) {
            int[] iArrComputeLocation = computeLocation(width, rect);
            this.mPopupWindow.update(iArrComputeLocation[0], iArrComputeLocation[1], width, height);
        } else if (this.mContainer.isAttachedToWindow()) {
            this.mPopupWindow.update(0, 0, width, height);
        }
    }

    private int[] computeLocation(int i2, Rect rect) {
        int i3;
        boolean z2;
        int width;
        int i4;
        int i5;
        int[] iArr = new int[2];
        this.mAnchorView.getLocationInWindow(iArr);
        int i6 = this.mMaxWidth;
        if (i2 > i6) {
            i2 = i6;
        }
        int i7 = iArr[1];
        if (i2 == i6) {
            i4 = rect.left;
            if (i4 <= 0) {
                i4 = this.mEdgeDistance;
            }
        } else if (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) != 1) {
            width = this.mWindowWidth;
            int i8 = iArr[0];
            int i9 = width - (i8 + i2);
            i3 = this.mEdgeDistance;
            boolean z3 = i9 < i3;
            z2 = i8 < i3;
            if (z2 || !z3) {
                i4 = (z3 || !z2) ? i8 : i3;
            }
            i2 += i3;
            i4 = width - i2;
        } else {
            boolean z4 = (iArr[0] + this.mAnchorView.getWidth()) - i2 < this.mEdgeDistance;
            int width2 = this.mWindowWidth - (iArr[0] + this.mAnchorView.getWidth());
            i3 = this.mEdgeDistance;
            z2 = width2 < i3;
            if (z4 || !z2) {
                if (z2 || !z4) {
                    width = iArr[0] + this.mAnchorView.getWidth();
                    i4 = width - i2;
                }
            } else {
                width = this.mWindowWidth;
                i2 += i3;
                i4 = width - i2;
            }
        }
        int i10 = this.mWindowHeight;
        int i11 = this.mContentHeight;
        int i12 = (i10 - i7) - i11;
        int i13 = this.mBottomEdge;
        if (i12 < i13 && (i7 = i7 - (i13 - ((i10 - i7) - i11))) < (i5 = this.mTopEdge)) {
            i7 = i5;
        }
        return new int[]{i4, i7};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configurationChanged(Configuration configuration) {
        Activity activityContextFromView = getActivityContextFromView(this.mContainer);
        View decorView = activityContextFromView != null ? activityContextFromView.getWindow().getDecorView() : null;
        if (decorView != null) {
            decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: miuix.popupwidget.widget.DropDownPopupWindow.3
                @Override // android.view.View.OnApplyWindowInsetsListener
                @NonNull
                public WindowInsets onApplyWindowInsets(@NonNull View view, @NonNull WindowInsets windowInsets) {
                    DropDownPopupWindow dropDownPopupWindow = DropDownPopupWindow.this;
                    DropDownPopupWindow.this.adjustLocation(dropDownPopupWindow.getCutout(dropDownPopupWindow.mContainer), windowInsets);
                    return windowInsets;
                }
            });
        }
        this.mContainer.post(new Runnable() { // from class: miuix.popupwidget.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6141a.lambda$configurationChanged$0();
            }
        });
    }

    private Activity getActivityContextFromView(View view) {
        Context context = ((ViewGroup) view.getRootView()).getChildAt(0).getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect getCutout(View view) {
        DisplayCutout cutout;
        Rect rect = new Rect();
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
        if (rootWindowInsets != null) {
            DisplayCutoutCompat displayCutout = rootWindowInsets.getDisplayCutout();
            if (displayCutout == null) {
                Activity activityContextFromView = getActivityContextFromView(view);
                if (activityContextFromView != null && (cutout = activityContextFromView.getWindowManager().getDefaultDisplay().getCutout()) != null) {
                    rect.left = cutout.getSafeInsetLeft();
                    rect.right = cutout.getSafeInsetRight();
                }
                return rect;
            }
            rect.left = displayCutout.getSafeInsetLeft();
            rect.right = displayCutout.getSafeInsetRight();
        }
        return rect;
    }

    private void initData() {
        this.mElevation = (int) (this.mContext.getResources().getDisplayMetrics().density * 32.0f);
        this.mShadowColor = this.mContext.getResources().getColor(R.color.miuix_appcompat_drop_down_menu_spot_shadow_color);
        this.mEdgeDistance = this.mContext.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_horizontal_edge_margin);
        this.mMinWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_drop_down_menu_min_width);
        this.mWindowWidth = WindowUtils.getWindowSize(this.mContext).x;
        this.mWindowHeight = WindowUtils.getWindowSize(this.mContext).y;
    }

    private void initPopupWindow() {
        initData();
        this.mPopupWindow.setWidth(-2);
        this.mPopupWindow.setHeight(-2);
        this.mPopupWindow.setSoftInputMode(3);
        this.mPopupWindow.setOutsideTouchable(false);
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setOutsideTouchable(true);
        this.mContainer.setFocusableInTouchMode(true);
        this.mPopupWindow.setContentView(this.mContainer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configurationChanged$0() {
        initData();
        adjustLocation(getCutout(this.mContainer), null);
    }

    private int measureListViewWidth(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = adapter.getCount();
        int measuredHeight = 0;
        int i2 = 0;
        int i3 = 0;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = adapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            view = adapter.getView(i4, view, listView);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            measuredHeight += view.getMeasuredHeight();
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        this.mContentHeight = measuredHeight;
        return i2;
    }

    private void prepareWindowElevation(final View view, int i2) {
        if (MiShadowUtils.SUPPORT_MI_SHADOW) {
            float f2 = view.getContext().getResources().getDisplayMetrics().density;
            MiShadowUtils.setMiShadow(view, this.mShadowColor, 0.0f * f2, f2 * 26.0f, this.mElevation);
        } else {
            view.setElevation(i2);
            view.setOutlineProvider(new ViewOutlineProvider() { // from class: miuix.popupwidget.widget.DropDownPopupWindow.6
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view2, Outline outline) {
                    if (view2.getWidth() == 0 || view2.getHeight() == 0) {
                        return;
                    }
                    outline.setAlpha(0.3f);
                    if (view.getBackground() != null) {
                        view.getBackground().getOutline(outline);
                    }
                }
            });
            view.setOutlineSpotShadowColor(this.mContext.getColor(R.color.miuix_appcompat_drop_down_menu_spot_shadow_color));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void realDismiss() {
        android.widget.PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
        ContainerController containerController = this.mContainerController;
        if (containerController != null) {
            containerController.onDismiss();
        }
        ContentController contentController = this.mContentController;
        if (contentController != null) {
            contentController.onDismiss();
        }
        Controller controller = this.mDropDownController;
        if (controller != null) {
            controller.onDismiss();
        }
        this.mDismissPending = false;
    }

    private void startAnimation(float f2, float f3, int i2) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.mContainerController == null && this.mContentController == null) {
            return;
        }
        ValueAnimator valueAnimator2 = this.mAnimator;
        if (valueAnimator2 == null) {
            this.mAnimator = ValueAnimator.ofFloat(f2, f3);
        } else {
            valueAnimator2.setFloatValues(f2, f3);
        }
        this.mAnimator.setDuration(DeviceHelper.isFeatureWholeAnim() ? i2 : 0L);
        this.mAnimator.addUpdateListener(this.mValueUpdateListener);
        this.mAnimator.addListener(this.mAnimatorListener);
        this.mAnimator.start();
    }

    private void updateMaxHeight(WindowInsets windowInsets) {
        View view = this.mAnchorView;
        if (view == null || this.mWindowHeight == 0) {
            return;
        }
        int height = view.getRootView().getHeight();
        if (windowInsets == null) {
            windowInsets = this.mAnchorView.getRootWindowInsets();
        }
        if (windowInsets != null) {
            Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
            this.mTopEdge = insets.top;
            this.mBottomEdge = insets.bottom;
        }
        this.mMaxHeight = (height - this.mTopEdge) - this.mBottomEdge;
    }

    private void updateMaxWidth(Rect rect) {
        int i2 = rect.left;
        if (i2 > 0) {
            this.mMaxWidth = (this.mWindowWidth - i2) - this.mEdgeDistance;
            return;
        }
        int i3 = rect.right;
        if (i3 > 0) {
            this.mMaxWidth = (this.mWindowWidth - i3) - this.mEdgeDistance;
        } else {
            this.mMaxWidth = this.mWindowWidth - (this.mEdgeDistance * 2);
        }
    }

    public void changeWindowBackground(View view, float f2) {
        if (view == null) {
            Log.w(TAG, "can't change window dim with null view");
            return;
        }
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        layoutParams.flags |= 2;
        layoutParams.dimAmount = f2;
        ((WindowManager) view.getContext().getSystemService("window")).updateViewLayout(view, layoutParams);
    }

    public void dismiss() {
        this.mDismissPending = true;
        realDismiss();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void setAnchor(View view) {
        this.mAnchorView = view;
    }

    public void setContainerController(ContainerController containerController) {
        this.mContainerController = containerController;
    }

    public void setContentController(ContentController contentController) {
        this.mContentController = contentController;
    }

    public void setDropDownController(Controller controller) {
        this.mDropDownController = controller;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mPopupWindow.setOnDismissListener(onDismissListener);
    }

    public int setupContentView(FrameLayout frameLayout, View view, int i2, int i3, ContainerController containerController) {
        int iMeasureListViewWidth;
        if (view == null) {
            return -2;
        }
        if (view instanceof ListView) {
            iMeasureListViewWidth = measureListViewWidth((ListView) view);
        } else {
            view.measure(0, 0);
            int measuredWidth = view.getMeasuredWidth();
            this.mContentHeight = view.getMeasuredHeight();
            iMeasureListViewWidth = measuredWidth;
        }
        return iMeasureListViewWidth < i3 ? i3 : iMeasureListViewWidth;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void show() {
        /*
            Method dump skipped, instruction units count: 245
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.popupwidget.widget.DropDownPopupWindow.show():void");
    }

    public static class ViewContentController implements ContentController {
        private View mContent;
        private Context mContext;
        private int mLayoutId;

        public ViewContentController(DropDownPopupWindow dropDownPopupWindow, int i2) {
            this(dropDownPopupWindow.getContext(), i2);
            dropDownPopupWindow.setContentController(this);
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.ContentController
        public View getContentView() {
            initContent();
            return this.mContent;
        }

        public void initContent() {
            if (this.mContent == null) {
                View viewInflate = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, (ViewGroup) null);
                this.mContent = viewInflate;
                onContentInit(viewInflate);
            }
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onAnimationUpdate(View view, float f2) {
            if (view != null) {
                view.setTranslationY((-view.getHeight()) * (1.0f - f2));
            }
        }

        public void onContentInit(View view) {
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onDismiss() {
        }

        @Override // miuix.popupwidget.widget.DropDownPopupWindow.Controller
        public void onShow() {
        }

        public ViewContentController(Context context, int i2) {
            this.mContext = context;
            this.mLayoutId = i2;
        }
    }
}
