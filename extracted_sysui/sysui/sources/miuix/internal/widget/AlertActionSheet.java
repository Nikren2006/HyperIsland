package miuix.internal.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Objects;
import miuix.appcompat.R;
import miuix.appcompat.app.AlertDialog;
import miuix.autodensity.DensityUtil;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.internal.widget.ActionSheet;
import miuix.os.Build;
import miuix.os.DeviceHelper;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
public class AlertActionSheet extends AlertDialog implements ActionSheet.IActionSheet {
    public static final int LARGE_WINDOW_WIDTH_THRESHOLD = 394;
    public static final int SMALL_WINDOW_WIDTH_THRESHOLD = 360;
    final ActionSheetController mActionController;
    private View mArrowActionAnchor;
    private ActionSheet.ArrowMode mArrowMode;
    private ActionSheet.ContentController mContentController;
    protected Context mContext;
    private int mFreeFormPhoneCompatHeight;
    private int mFreeFormTabletCompatHeight;
    private boolean mIsDismissForShift;
    private boolean mIsFlipTinyScreen;
    private boolean mIsFromArrowShape;
    private int mMaxFixedWidth;
    private int mNormalMargin;
    private int mSmallMargin;

    public AlertActionSheet(Context context) {
        this(context, 0);
    }

    private void init(Context context) {
        setContentController();
        this.mSmallMargin = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_width_small_margin);
        this.mNormalMargin = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_width_margin);
        this.mMaxFixedWidth = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_content_alert_max_fixed_width);
        this.mFreeFormPhoneCompatHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_freeform_bottom_height_phone_t);
        this.mFreeFormTabletCompatHeight = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_freeform_bottom_height_tablet_t);
        this.mIsFlipTinyScreen = Build.IS_FLIP && DeviceHelper.isTinyScreen(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissWithAnimationExistDecorView$0() {
        this.mActionController.dismiss(this.mOnDismiss);
    }

    private void setContentController() {
        ActionSheet.ContentController contentController = new ActionSheet.ContentController() { // from class: miuix.internal.widget.AlertActionSheet.1
            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int calcContentPanelHeight(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, int i2, WindowInsets windowInsets) {
                return -2;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int calcContentPanelWidth(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, int i2, WindowInsets windowInsets) {
                int i3;
                int i4;
                int iMax;
                boolean z2;
                int iMax2;
                int i5;
                int iMax3;
                if (windowInsets != null) {
                    Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
                    Insets insets2 = windowInsets.getInsets(WindowInsets.Type.displayCutout());
                    int i6 = insets.left;
                    int i7 = insets.right + i6;
                    int i8 = insets2.left;
                    int i9 = i7 + insets2.right + i8;
                    iMax = Math.max(i6, i8);
                    z2 = iMax == insets2.left;
                    int iMax4 = Math.max(insets.right, insets2.right);
                    i = iMax4 == insets2.right ? 1 : 0;
                    i4 = iMax4;
                    i3 = i;
                    i = i9;
                } else {
                    i3 = 0;
                    i4 = 0;
                    iMax = 0;
                    z2 = false;
                }
                int iPx2dp = MiuixUIUtils.px2dp(context, i2 - i);
                if (iPx2dp > 0 && iPx2dp <= 360) {
                    iMax2 = (!z2 || AlertActionSheet.this.mIsFlipTinyScreen) ? AlertActionSheet.this.mSmallMargin + iMax : Math.max(iMax, AlertActionSheet.this.mSmallMargin);
                    if (i3 == 0 || AlertActionSheet.this.mIsFlipTinyScreen) {
                        i5 = AlertActionSheet.this.mSmallMargin;
                        iMax3 = i5 + i4;
                    } else {
                        iMax3 = Math.max(i4, AlertActionSheet.this.mSmallMargin);
                    }
                } else {
                    if (360 >= iPx2dp || iPx2dp > 394) {
                        return AlertActionSheet.this.mMaxFixedWidth;
                    }
                    iMax2 = (!z2 || AlertActionSheet.this.mIsFlipTinyScreen) ? AlertActionSheet.this.mNormalMargin + iMax : Math.max(iMax, AlertActionSheet.this.mNormalMargin);
                    if (i3 == 0 || AlertActionSheet.this.mIsFlipTinyScreen) {
                        i5 = AlertActionSheet.this.mNormalMargin;
                        iMax3 = i5 + i4;
                    } else {
                        iMax3 = Math.max(i4, AlertActionSheet.this.mNormalMargin);
                    }
                }
                return (i2 - iMax2) - iMax3;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int[] calcHorizontalMargin(Context context, WindowInsets windowInsets) {
                int iMax;
                int i2 = (Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) ? AlertActionSheet.this.mSmallMargin : AlertActionSheet.this.mNormalMargin;
                if (windowInsets != null) {
                    Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
                    int iMax2 = Math.max(i2, insets.left);
                    int iMax3 = Math.max(i2, insets.right);
                    Insets insets2 = windowInsets.getInsets(WindowInsets.Type.displayCutout());
                    int iMax4 = Math.max(iMax2, insets2.left);
                    iMax = Math.max(iMax3, insets2.right);
                    boolean z2 = iMax4 == insets2.left;
                    boolean z3 = iMax == insets2.right;
                    if (z2 && AlertActionSheet.this.mIsFlipTinyScreen) {
                        iMax4 += i2;
                    }
                    if (z3 && AlertActionSheet.this.mIsFlipTinyScreen) {
                        iMax += i2;
                    }
                    if (!z2) {
                        int i3 = insets.left;
                        iMax4 = iMax4 == i3 ? i3 + i2 : i2;
                    }
                    if (!z3) {
                        int i4 = insets.right;
                        if (iMax == i4) {
                            i2 += i4;
                        }
                        iMax = i2;
                    }
                    i2 = iMax4;
                } else {
                    iMax = i2;
                }
                return new int[]{i2, iMax};
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            @RequiresApi(api = 30)
            public int[] calcVerticalMargin(Context context, WindowInsets windowInsets) {
                int i2 = (Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) ? AlertActionSheet.this.mSmallMargin : AlertActionSheet.this.mNormalMargin;
                if (windowInsets == null) {
                    return new int[]{i2, i2};
                }
                ActionSheetController actionSheetController = AlertActionSheet.this.mActionController;
                Insets insetsIgnoringVisibility = (actionSheetController == null || !actionSheetController.mIsAssociatedActivityNavigationBarHidden) ? windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()) : windowInsets.getInsets(WindowInsets.Type.systemBars());
                Insets insets = windowInsets.getInsets(WindowInsets.Type.displayCutout());
                boolean zIsFreeFormMode = EnvStateManager.isFreeFormMode(context);
                int iMax = Math.max(Math.max(insetsIgnoringVisibility.top, insets.top), i2);
                boolean z2 = Build.IS_TABLET;
                AlertActionSheet alertActionSheet = AlertActionSheet.this;
                int i3 = z2 ? alertActionSheet.mFreeFormTabletCompatHeight : alertActionSheet.mFreeFormPhoneCompatHeight;
                int i4 = insetsIgnoringVisibility.bottom;
                if (i4 != 0 || !zIsFreeFormMode) {
                    i3 = i4;
                }
                return new int[]{iMax, i2 + Math.max(i3, insets.bottom)};
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public Point computeContentPosition(Rect rect, Point point, Point point2, ViewGroup viewGroup, ViewGroup viewGroup2) {
                int i2 = (point.x - point2.x) / 2;
                Point point3 = new Point();
                int iMax = Math.max(rect.left, i2);
                int i3 = point.x;
                int i4 = point2.x;
                int i5 = (i3 - iMax) - i4;
                int i6 = rect.right;
                if (i5 < i6) {
                    iMax = (i3 - i6) - i4;
                }
                point3.x = iMax;
                point3.y = point.y - (point2.y + rect.bottom);
                return point3;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            @Nullable
            public View getArrowAnchor() {
                return null;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public ActionSheet.ArrowMode getArrowMode() {
                return ActionSheet.ArrowMode.ARROW_MODE_NONE;
            }
        };
        this.mContentController = contentController;
        ActionSheetController actionSheetController = this.mActionController;
        if (actionSheetController != null) {
            actionSheetController.setContentController(contentController);
        }
    }

    public ArrowActionSheet createArrowActionSheet(View view) {
        this.mArrowActionAnchor = view;
        ArrowActionSheet arrowActionSheet = new ArrowActionSheet(this.mContext, view);
        arrowActionSheet.setArrowMode(this.mArrowMode);
        arrowActionSheet.setHapticFeedbackEnabled(isHapticFeedbackEnabled());
        arrowActionSheet.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        if (this.mActionController.getMessage() != null) {
            arrowActionSheet.setMessage(this.mActionController.getMessage());
        }
        if (this.mActionController.getActionItems() != null && this.mActionController.getItemClickListener() != null) {
            arrowActionSheet.setActionItems(this.mActionController.getActionItems(), this.mActionController.getItemClickListener());
        }
        if (this.mActionController.getActionItems() != null && this.mActionController.getItemClickListener() != null && this.mActionController.getItemTypes() != null) {
            arrowActionSheet.setActionItems(this.mActionController.getActionItems(), this.mActionController.getItemTypes(), this.mActionController.getItemClickListener());
        }
        if (this.mActionController.getShowAnimListener() != null) {
            arrowActionSheet.setOnShowAnimListener(this.mActionController.getShowAnimListener());
        }
        if (this.mActionController.getOnShowListener() != null) {
            arrowActionSheet.setActionSheetOnShowListener(this.mActionController.getOnShowListener());
        }
        if (this.mActionController.getOnDismissListener() != null) {
            arrowActionSheet.setActionSheetOnDismissListener(this.mActionController.getOnDismissListener());
        }
        if (this.mActionController.getOnKeyListener() != null) {
            arrowActionSheet.setActionSheetOnKeyListener(this.mActionController.getOnKeyListener());
        }
        if (this.mActionController.getListViewAdapter() != null) {
            arrowActionSheet.setListViewAdapter(this.mActionController.getListViewAdapter());
        }
        if (this.mActionController.getOnCancelListener() != null) {
            arrowActionSheet.setActionSheetOnCancelListener(this.mActionController.getOnCancelListener());
        }
        return arrowActionSheet;
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        Window window = getWindow();
        Objects.requireNonNull(window);
        View decorView = window.getDecorView();
        if (this.mActionController.isShowingAnimation()) {
            this.mActionController.setPendingDismiss(true);
            return;
        }
        this.mActionController.setPendingDismiss(false);
        if (DensityUtil.findAutoDensityContextWrapper(decorView.getContext()) != null) {
            EnvStateManager.removeInfoOfContext(decorView.getContext());
        }
        Activity associatedActivity = getAssociatedActivity();
        if (associatedActivity == null || !associatedActivity.isFinishing()) {
            dismissWithAnimationOrNot(decorView);
        } else {
            dismissIfAttachedToWindow(decorView);
        }
    }

    public void dismissForShiftWithoutAnimation() {
        setIsDismissForShift(true);
        dismissWithoutAnimation();
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void dismissWithAnimationExistDecorView(View view) {
        if (Thread.currentThread() == view.getHandler().getLooper().getThread()) {
            this.mActionController.dismiss(this.mOnDismiss);
        } else {
            view.post(new Runnable() { // from class: miuix.internal.widget.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6103a.lambda$dismissWithAnimationExistDecorView$0();
                }
            });
        }
    }

    @Override // miuix.appcompat.app.AlertDialog, miuix.internal.widget.ActionSheet.IActionSheet
    public void dismissWithoutAnimation() {
        Window window = getWindow();
        Objects.requireNonNull(window);
        View decorView = window.getDecorView();
        if (getWindow().getDecorView().isAttachedToWindow()) {
            if (this.mActionController.isShowingAnimation()) {
                this.mActionController.setPendingDismiss(true);
                return;
            }
            this.mActionController.setPendingDismiss(false);
            if (DensityUtil.findAutoDensityContextWrapper(decorView.getContext()) != null) {
                EnvStateManager.removeInfoOfContext(decorView.getContext());
            }
            realDismiss();
        }
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mActionController.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return miuixSuperDispatchKeyEvent(keyEvent);
    }

    public View getArrowActionAnchor() {
        return this.mArrowActionAnchor;
    }

    public ActionSheet.ArrowMode getArrowMode() {
        return this.mArrowMode;
    }

    @Override // miuix.appcompat.app.AlertDialog, miuix.internal.widget.ActionSheet.IActionSheet
    public ListView getListView() {
        return this.mActionController.getListView();
    }

    @Override // miuix.appcompat.app.AlertDialog
    public TextView getMessageView() {
        return this.mActionController.getMessageView();
    }

    @Override // miuix.internal.widget.ActionSheet.IActionSheet
    @NonNull
    public TextView getSeparateView() {
        return this.mActionController.getSeparateView();
    }

    public boolean isCanceledOnTouchOutside() {
        return this.mActionController.isCanceledOnTouchOutside();
    }

    public boolean isDismissForShift() {
        return this.mIsDismissForShift;
    }

    public boolean isFromArrowShape() {
        return this.mIsFromArrowShape;
    }

    public boolean isHapticFeedbackEnabled() {
        return this.mActionController.mHapticFeedbackEnabled;
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        superOnAttachedToWindow();
        Window window = getWindow();
        Objects.requireNonNull(window);
        View decorView = window.getDecorView();
        if (this.mActionController.mHapticFeedbackEnabled) {
            HapticCompat.performHapticFeedbackAsync(decorView, HapticFeedbackConstants.MIUI_ALERT, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        this.mActionController.onAttachedToWindow();
        setAccessibilityDelegate(decorView);
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        AlertDialog.LifecycleOwnerCompat lifecycleOwnerCompat;
        if (isSystemSpecialUiThread() && (lifecycleOwnerCompat = this.mLifecycleOwnerCompat) != null) {
            lifecycleOwnerCompat.onCreate();
        }
        Window window = getWindow();
        Objects.requireNonNull(window);
        window.setWindowAnimations(0);
        superOnCreate(bundle);
        this.mActionController.installContent(bundle);
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        superOnDetachedFromWindow();
        this.mActionController.onDetachedFromWindow();
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onStart() {
        superOnStart();
        this.mActionController.onStart();
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onStop() {
        AlertDialog.LifecycleOwnerCompat lifecycleOwnerCompat;
        AlertDialog.LifecycleOwnerCompat lifecycleOwnerCompat2;
        if (isSystemSpecialUiThread() && (lifecycleOwnerCompat2 = this.mLifecycleOwnerCompat) != null) {
            lifecycleOwnerCompat2.onStopBefore();
        }
        superOnStop();
        if (!isSystemSpecialUiThread() || (lifecycleOwnerCompat = this.mLifecycleOwnerCompat) == null) {
            return;
        }
        lifecycleOwnerCompat.onStopAfter();
    }

    public void setActionItems(@ArrayRes int i2, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(i2, onClickListener);
    }

    public void setActionSheetOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mActionController.setOnCancelListener(onCancelListener);
    }

    public void setActionSheetOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        this.mActionController.setOnDismissListener(onDismissListener);
    }

    public void setActionSheetOnKeyListener(@Nullable DialogInterface.OnKeyListener onKeyListener) {
        this.mActionController.setOnKeyListener(onKeyListener);
    }

    public void setActionSheetOnShowListener(@Nullable DialogInterface.OnShowListener onShowListener) {
        this.mActionController.setOnShowListener(onShowListener);
    }

    public void setArrowActionAnchor(View view) {
        this.mArrowActionAnchor = view;
    }

    public void setArrowMode(ActionSheet.ArrowMode arrowMode) {
        this.mArrowMode = arrowMode;
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z2) {
        this.mActionController.setCanceledOnTouchOutside(z2);
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setEnableEnterAnim(boolean z2) {
        this.mActionController.setEnableEnterAnim(z2);
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setHapticFeedbackEnabled(boolean z2) {
        this.mActionController.mHapticFeedbackEnabled = z2;
    }

    public void setIsDismissForShift(boolean z2) {
        this.mIsDismissForShift = z2;
    }

    public void setIsFromArrowShape(boolean z2) {
        this.mIsFromArrowShape = z2;
    }

    public void setListViewAdapter(ListAdapter listAdapter) {
        this.mActionController.setListViewAdapter(listAdapter);
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        this.mActionController.setMessage(charSequence);
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setOnShowAnimListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        this.mActionController.setShowAnimListener(onDialogShowAnimListener);
    }

    public void setSeparateButtonText(CharSequence charSequence) {
        this.mActionController.setSeparateItemText(charSequence);
    }

    public void setSeparateClickListener(DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setSeparateClickListener(onClickListener);
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog
    public void show() {
        superShow();
    }

    public AlertActionSheet(Context context, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z2);
        setOnCancelListener(onCancelListener);
    }

    public void setActionItems(@ArrayRes int i2, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(i2, actionSheetItemTypeArr, onClickListener);
    }

    public void setActionItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(charSequenceArr, onClickListener);
    }

    public void setActionItems(CharSequence[] charSequenceArr, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(charSequenceArr, actionSheetItemTypeArr, onClickListener);
    }

    public AlertActionSheet(Context context, int i2) {
        super(context, i2);
        this.mContext = context;
        this.mActionController = new ActionSheetController(context, this, getWindow(), ActionSheet.ActionSheetMode.ALERT_MODE);
        init(context);
    }
}
