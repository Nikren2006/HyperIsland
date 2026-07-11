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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
import miuix.appcompat.R;
import miuix.appcompat.app.AlertDialog;
import miuix.autodensity.DensityUtil;
import miuix.core.util.EnvStateManager;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.ActionSheet;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
public class ArrowActionSheet extends AlertDialog implements ActionSheet.IActionSheet {
    final ActionSheetController mActionController;
    private final View mAnchorView;
    private int mArrowIconLongSide;
    private int mArrowIconShortSide;
    private int mArrowLinkOffset;
    private ActionSheet.ArrowMode mArrowMode;
    private ActionSheet.ContentController mContentController;
    private Context mContext;
    private int mDefaultMargin;
    private int mFixedWidth;
    private boolean mIsDismissForShift;
    private boolean mIsFromAlertShape;
    private Point mOffset;
    private int mOffsetToPoint;

    /* JADX INFO: renamed from: miuix.internal.widget.ArrowActionSheet$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode;

        static {
            int[] iArr = new int[ActionSheet.ArrowMode.values().length];
            $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode = iArr;
            try {
                iArr[ActionSheet.ArrowMode.ARROW_TOP_LEFT_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_TOP_MODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_TOP_RIGHT_MODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_LEFT_MODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_LEFT_TOP_MODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_RIGHT_MODE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_RIGHT_TOP_MODE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_LEFT_BOTTOM_MODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_RIGHT_BOTTOM_MODE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_BOTTOM_LEFT_MODE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_BOTTOM_MODE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ActionSheet.ArrowMode.ARROW_BOTTOM_RIGHT_MODE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public ArrowActionSheet(Context context, View view) {
        this(context, 0, view);
    }

    private void init(Context context) {
        this.mArrowLinkOffset = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_arrow_icon_link_offset);
        this.mArrowIconLongSide = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_arrow_icon_long_side);
        this.mArrowIconShortSide = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_arrow_icon_short_side);
        this.mFixedWidth = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_content_arrow_fixed_width);
        this.mDefaultMargin = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_dialog_ime_margin);
        this.mOffsetToPoint = context.getResources().getDimensionPixelSize(R.dimen.miuix_appcompat_action_sheet_arrow_offset_to_point);
        setContentController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissWithAnimationExistDecorView$0() {
        this.mActionController.dismiss(this.mOnDismiss);
    }

    private void setContentController() {
        ActionSheet.ContentController contentController = new ActionSheet.ContentController() { // from class: miuix.internal.widget.ArrowActionSheet.1
            /* JADX WARN: Removed duplicated region for block: B:30:0x007b A[PHI: r5
              0x007b: PHI (r5v8 boolean) = (r5v5 boolean), (r5v13 boolean), (r5v21 boolean) binds: [B:44:0x00a4, B:37:0x0090, B:29:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:31:0x007d A[PHI: r5
              0x007d: PHI (r5v7 boolean) = (r5v5 boolean), (r5v13 boolean), (r5v21 boolean) binds: [B:44:0x00a4, B:37:0x0090, B:29:0x0079] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:75:0x011b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private boolean[] detectOverflow(android.graphics.Rect r8, android.graphics.Point r9, android.graphics.Point r10, android.view.WindowInsets r11, android.graphics.Point r12) {
                /*
                    Method dump skipped, instruction units count: 304
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: miuix.internal.widget.ArrowActionSheet.AnonymousClass1.detectOverflow(android.graphics.Rect, android.graphics.Point, android.graphics.Point, android.view.WindowInsets, android.graphics.Point):boolean[]");
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            private Point getReferencePoint(Rect rect, ActionSheet.ArrowMode arrowMode) {
                Point point = new Point();
                boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(ArrowActionSheet.this.mAnchorView);
                switch (AnonymousClass2.$SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[arrowMode.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        point.x = rect.left + (ArrowActionSheet.this.mAnchorView.getWidth() / 2);
                        point.y = rect.top + ArrowActionSheet.this.mAnchorView.getHeight();
                        return point;
                    case 4:
                    case 5:
                    case 8:
                        if (zIsLayoutRtl) {
                            point.x = rect.left;
                            point.y = rect.top + (ArrowActionSheet.this.mAnchorView.getHeight() / 2);
                        } else {
                            point.x = rect.left + ArrowActionSheet.this.mAnchorView.getWidth();
                            point.y = rect.top + (ArrowActionSheet.this.mAnchorView.getHeight() / 2);
                        }
                        return point;
                    case 6:
                    case 7:
                    case 9:
                        if (zIsLayoutRtl) {
                            point.x = rect.left + ArrowActionSheet.this.mAnchorView.getWidth();
                            point.y = rect.top + (ArrowActionSheet.this.mAnchorView.getHeight() / 2);
                        } else {
                            point.x = rect.left;
                            point.y = rect.top + (ArrowActionSheet.this.mAnchorView.getHeight() / 2);
                        }
                        return point;
                    case 10:
                    case 11:
                    case 12:
                        point.x = rect.left + (ArrowActionSheet.this.mAnchorView.getWidth() / 2);
                        point.y = rect.top;
                        return point;
                    default:
                        return point;
                }
            }

            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            private Point getShowOffset(ActionSheet.ArrowMode arrowMode, Point point) {
                Point point2 = new Point();
                boolean z2 = ArrowActionSheet.this.mAnchorView != null && ViewUtils.isLayoutRtl(ArrowActionSheet.this.mAnchorView);
                switch (AnonymousClass2.$SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[arrowMode.ordinal()]) {
                    case 1:
                        if (z2) {
                            point2.x = (point.x * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                            point2.y = 0;
                        } else {
                            point2.x = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                            point2.y = 0;
                        }
                        return point2;
                    case 2:
                        point2.x = (point.x / 2) * (-1);
                        point2.y = 0;
                        return point2;
                    case 3:
                        if (z2) {
                            point2.x = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                            point2.y = 0;
                        } else {
                            point2.x = (point.x * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                            point2.y = 0;
                        }
                        return point2;
                    case 4:
                        if (z2) {
                            point2.x = point.x * (-1);
                            point2.y = (point.y / 2) * (-1);
                        } else {
                            point2.x = 0;
                            point2.y = (point.y / 2) * (-1);
                        }
                        return point2;
                    case 5:
                        if (z2) {
                            point2.x = point.x * (-1);
                            point2.y = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                        } else {
                            point2.x = 0;
                            point2.y = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                        }
                        return point2;
                    case 6:
                        if (z2) {
                            point2.x = 0;
                            point2.y = (point.y / 2) * (-1);
                        } else {
                            point2.x = point.x * (-1);
                            point2.y = (point.y / 2) * (-1);
                        }
                        return point2;
                    case 7:
                        if (z2) {
                            point2.x = 0;
                            point2.y = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                        } else {
                            point2.x = point.x * (-1);
                            point2.y = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                        }
                        return point2;
                    case 8:
                        if (z2) {
                            point2.x = point.x * (-1);
                            point2.y = (point.y * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                        } else {
                            point2.x = 0;
                            point2.y = (point.y * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                        }
                        return point2;
                    case 9:
                        if (z2) {
                            point2.x = 0;
                            point2.y = (point.y * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                        } else {
                            point2.x = point.x * (-1);
                            point2.y = (point.y * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                        }
                        return point2;
                    case 10:
                        if (z2) {
                            point2.x = (point.x * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                            point2.y = point.y * (-1);
                        } else {
                            point2.x = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                            point2.y = point.y * (-1);
                        }
                        return point2;
                    case 11:
                        point2.x = (point.x / 2) * (-1);
                        point2.y = point.y * (-1);
                        return point2;
                    case 12:
                        if (z2) {
                            point2.x = (ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2)) * (-1);
                            point2.y = point.y * (-1);
                        } else {
                            point2.x = (point.x * (-1)) + ArrowActionSheet.this.mArrowLinkOffset + (ArrowActionSheet.this.mArrowIconLongSide / 2);
                            point2.y = point.y * (-1);
                        }
                        return point2;
                    default:
                        return point2;
                }
            }

            private Point getShowPosition(Rect rect, Point point, ActionSheet.ArrowMode arrowMode) {
                Point point2 = new Point();
                Point referencePoint = getReferencePoint(rect, arrowMode);
                Point showOffset = getShowOffset(arrowMode, point);
                point2.x = referencePoint.x + showOffset.x + ArrowActionSheet.this.mOffset.x;
                point2.y = referencePoint.y + showOffset.y + ArrowActionSheet.this.mOffset.y;
                handleReservedSpace(point2, arrowMode);
                return point2;
            }

            private void handleReservedSpace(Point point, ActionSheet.ArrowMode arrowMode) {
                boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(ArrowActionSheet.this.mAnchorView);
                switch (AnonymousClass2.$SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[arrowMode.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        point.y += ArrowActionSheet.this.mOffsetToPoint;
                        break;
                    case 4:
                    case 5:
                    case 8:
                        if (!zIsLayoutRtl) {
                            point.x += ArrowActionSheet.this.mOffsetToPoint;
                        } else {
                            point.x -= ArrowActionSheet.this.mOffsetToPoint;
                        }
                        break;
                    case 6:
                    case 7:
                    case 9:
                        if (!zIsLayoutRtl) {
                            point.x -= ArrowActionSheet.this.mOffsetToPoint;
                        } else {
                            point.x += ArrowActionSheet.this.mOffsetToPoint;
                        }
                        break;
                    case 10:
                    case 11:
                    case 12:
                        point.y -= ArrowActionSheet.this.mOffsetToPoint;
                        break;
                }
            }

            private ActionSheet.ArrowMode overflowCompactStrategy(boolean[] zArr) {
                ActionSheet.ArrowMode arrowMode = ArrowActionSheet.this.mArrowMode;
                boolean z2 = zArr[0];
                boolean z3 = zArr[1];
                boolean z4 = zArr[2];
                boolean z5 = zArr[3];
                switch (AnonymousClass2.$SwitchMap$miuix$internal$widget$ActionSheet$ArrowMode[ArrowActionSheet.this.mArrowMode.ordinal()]) {
                    case 1:
                        return (!z2 || z4) ? z5 ? ActionSheet.ArrowMode.ARROW_BOTTOM_LEFT_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_LEFT_TOP_MODE;
                    case 2:
                        return z5 ? ActionSheet.ArrowMode.ARROW_BOTTOM_MODE : arrowMode;
                    case 3:
                        return (!z4 || z2) ? z5 ? ActionSheet.ArrowMode.ARROW_BOTTOM_RIGHT_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_RIGHT_TOP_MODE;
                    case 4:
                    case 5:
                        return (!z4 || z5) ? z4 ? ActionSheet.ArrowMode.ARROW_BOTTOM_LEFT_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_TOP_LEFT_MODE;
                    case 6:
                    case 7:
                        return (!z2 || z5) ? z2 ? ActionSheet.ArrowMode.ARROW_BOTTOM_RIGHT_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_TOP_RIGHT_MODE;
                    case 8:
                        return (!z4 || z3) ? (z4 || !z3) ? arrowMode : ActionSheet.ArrowMode.ARROW_LEFT_TOP_MODE : ActionSheet.ArrowMode.ARROW_BOTTOM_LEFT_MODE;
                    case 9:
                        return (!z2 || z3) ? (z2 || !z3) ? arrowMode : ActionSheet.ArrowMode.ARROW_RIGHT_TOP_MODE : ActionSheet.ArrowMode.ARROW_BOTTOM_RIGHT_MODE;
                    case 10:
                        return (!z2 || z3) ? z3 ? ActionSheet.ArrowMode.ARROW_LEFT_TOP_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_LEFT_BOTTOM_MODE;
                    case 11:
                        return z3 ? ActionSheet.ArrowMode.ARROW_TOP_MODE : arrowMode;
                    case 12:
                        return (!z4 || z3) ? z3 ? ActionSheet.ArrowMode.ARROW_RIGHT_TOP_MODE : arrowMode : ActionSheet.ArrowMode.ARROW_RIGHT_BOTTOM_MODE;
                    default:
                        return arrowMode;
                }
            }

            private Point reComputePosition(Rect rect, Point point, ViewGroup viewGroup, boolean[] zArr) {
                ActionSheet.ArrowMode arrowModeOverflowCompactStrategy = overflowCompactStrategy(zArr);
                if (arrowModeOverflowCompactStrategy != ArrowActionSheet.this.mArrowMode) {
                    return getShowPosition(rect, point, arrowModeOverflowCompactStrategy);
                }
                return null;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int calcContentPanelHeight(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, int i2, WindowInsets windowInsets) {
                return -2;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int calcContentPanelWidth(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, int i2, WindowInsets windowInsets) {
                return ArrowActionSheet.this.mFixedWidth;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int[] calcHorizontalMargin(@NonNull Context context, WindowInsets windowInsets) {
                return new int[]{ArrowActionSheet.this.mDefaultMargin, ArrowActionSheet.this.mDefaultMargin};
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public int[] calcVerticalMargin(Context context, WindowInsets windowInsets) {
                if (windowInsets == null) {
                    return new int[]{ArrowActionSheet.this.mDefaultMargin, ArrowActionSheet.this.mDefaultMargin};
                }
                Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
                return new int[]{insetsIgnoringVisibility.top + ArrowActionSheet.this.mDefaultMargin, insetsIgnoringVisibility.bottom + ArrowActionSheet.this.mDefaultMargin};
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public Point computeContentPosition(Rect rect, Point point, Point point2, ViewGroup viewGroup, ViewGroup viewGroup2) {
                Point showPosition = getShowPosition(rect, point2, ArrowActionSheet.this.mArrowMode);
                Point pointReComputePosition = reComputePosition(rect, point2, viewGroup2, detectOverflow(rect, point, point2, viewGroup != null ? viewGroup.getRootWindowInsets() : null, showPosition));
                if (pointReComputePosition != null) {
                    showPosition.x = pointReComputePosition.x;
                    showPosition.y = pointReComputePosition.y;
                }
                return showPosition;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            @NonNull
            public View getArrowAnchor() {
                return ArrowActionSheet.this.mAnchorView;
            }

            @Override // miuix.internal.widget.ActionSheet.ContentController
            public ActionSheet.ArrowMode getArrowMode() {
                return ArrowActionSheet.this.mArrowMode;
            }
        };
        this.mContentController = contentController;
        ActionSheetController actionSheetController = this.mActionController;
        if (actionSheetController != null) {
            actionSheetController.setContentController(contentController);
        }
    }

    public AlertActionSheet createAlertActionSheet(View view) {
        AlertActionSheet alertActionSheet = new AlertActionSheet(this.mContext);
        alertActionSheet.setArrowActionAnchor(view);
        alertActionSheet.setArrowMode(this.mArrowMode);
        alertActionSheet.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        alertActionSheet.setHapticFeedbackEnabled(isHapticFeedbackEnabled());
        if (this.mActionController.getMessage() != null) {
            alertActionSheet.setMessage(this.mActionController.getMessage());
        }
        if (this.mActionController.getActionItems() != null && this.mActionController.getItemClickListener() != null) {
            alertActionSheet.setActionItems(this.mActionController.getActionItems(), this.mActionController.getItemClickListener());
        }
        if (this.mActionController.getActionItems() != null && this.mActionController.getItemClickListener() != null && this.mActionController.getItemTypes() != null) {
            alertActionSheet.setActionItems(this.mActionController.getActionItems(), this.mActionController.getItemTypes(), this.mActionController.getItemClickListener());
        }
        if (this.mActionController.getShowAnimListener() != null) {
            alertActionSheet.setOnShowAnimListener(this.mActionController.getShowAnimListener());
        }
        if (this.mActionController.getOnShowListener() != null) {
            alertActionSheet.setActionSheetOnShowListener(this.mActionController.getOnShowListener());
        }
        if (this.mActionController.getOnDismissListener() != null) {
            alertActionSheet.setActionSheetOnDismissListener(this.mActionController.getOnDismissListener());
        }
        if (this.mActionController.getOnKeyListener() != null) {
            alertActionSheet.setActionSheetOnKeyListener(this.mActionController.getOnKeyListener());
        }
        if (this.mActionController.getListViewAdapter() != null) {
            alertActionSheet.setListViewAdapter(this.mActionController.getListViewAdapter());
        }
        if (this.mActionController.getOnCancelListener() != null) {
            alertActionSheet.setActionSheetOnCancelListener(this.mActionController.getOnCancelListener());
        }
        return alertActionSheet;
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
            view.post(new Runnable() { // from class: miuix.internal.widget.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6104a.lambda$dismissWithAnimationExistDecorView$0();
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

    public View getArrowAnchor() {
        return this.mAnchorView;
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
    @Nullable
    public TextView getSeparateView() {
        return this.mActionController.getSeparateView();
    }

    public boolean isCanceledOnTouchOutside() {
        return this.mActionController.isCanceledOnTouchOutside();
    }

    public boolean isDismissForShift() {
        return this.mIsDismissForShift;
    }

    public boolean isFromAlertShape() {
        return this.mIsFromAlertShape;
    }

    public boolean isHapticFeedbackEnabled() {
        return this.mActionController.mHapticFeedbackEnabled;
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
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

    public void setActionItems(int i2, DialogInterface.OnClickListener onClickListener) {
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

    public void setIsFromAlertShape(boolean z2) {
        this.mIsFromAlertShape = z2;
    }

    public void setListViewAdapter(ListAdapter listAdapter) {
        this.mActionController.setListViewAdapter(listAdapter);
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        this.mActionController.setMessage(charSequence);
    }

    public void setOffset(int i2, int i3) {
        Point point = this.mOffset;
        point.x = i2;
        point.y = i3;
    }

    @Override // miuix.appcompat.app.AlertDialog
    public void setOnShowAnimListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        this.mActionController.setShowAnimListener(onDialogShowAnimListener);
    }

    @Override // miuix.appcompat.app.AlertDialog, android.app.Dialog
    public void show() {
        superShow();
    }

    public ArrowActionSheet(Context context, int i2, View view) {
        super(context, i2);
        this.mOffset = new Point();
        this.mActionController = new ActionSheetController(context, this, getWindow(), ActionSheet.ActionSheetMode.ARROW_MODE);
        this.mAnchorView = view;
        this.mContext = context;
        init(context);
    }

    public void setActionItems(int i2, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(i2, actionSheetItemTypeArr, onClickListener);
    }

    public void setActionItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(charSequenceArr, onClickListener);
    }

    public void setActionItems(CharSequence[] charSequenceArr, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionController.setActionItems(charSequenceArr, actionSheetItemTypeArr, onClickListener);
    }

    public ArrowActionSheet(Context context, View view, boolean z2, DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0, view);
        setCancelable(z2);
        setOnCancelListener(onCancelListener);
    }
}
