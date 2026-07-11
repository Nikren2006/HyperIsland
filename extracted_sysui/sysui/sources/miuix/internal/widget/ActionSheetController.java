package miuix.internal.widget;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import miuix.animation.Folme;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.internal.widget.DialogParentPanel2;
import miuix.appcompat.widget.DialogAnimHelper;
import miuix.core.util.EnvStateManager;
import miuix.core.util.WindowBaseInfo;
import miuix.internal.util.AnimHelper;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.ActionSheet;
import miuix.internal.widget.ActionSheetRootView;
import miuix.os.Build;
import miuix.os.DeviceHelper;
import miuix.springback.view.SpringBackLayout;
import miuix.theme.token.DimToken;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
class ActionSheetController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SHOW_ARROW_SHAPE_WINDOW_WIDTH_THRESHOLD_DP = 747;
    private static final String TAG = "ActionSheetController";
    private ActionSheet.ActionSheetItemType[] mActionItemTypes;
    private CharSequence[] mActionItems;
    private int mActionSheetLayout;
    protected boolean mCanceledOnTouchOutside;
    private ActionSheet.ContentController mContentController;
    private int mContentLayout;
    private ViewGroup mContentPanel;
    private int mContentPanelHeight;
    private int mContentPanelWidth;
    private final Context mContext;
    final AppCompatDialog mDialog;
    private final DialogAnimHelper mDialogAnimHelper;
    private View mDimBg;
    private View mDivider;
    protected boolean mEnableEnterAnim;
    protected boolean mHapticFeedbackEnabled;
    protected boolean mHasPendingDismiss;
    protected boolean mIsAssociatedActivityNavigationBarHidden;
    private boolean mIsDialogAnimating;
    protected boolean mIsFlipTinyScreen;
    private boolean mIsFromRebuild;
    private DialogInterface.OnClickListener mItemClickListener;
    private int mListItemLayout;
    private ListView mListView;
    ListAdapter mListViewAdapter;
    private CharSequence mMessage;
    private TextView mMessageView;
    private ActionSheet.ActionSheetMode mMode;
    private DialogInterface.OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private final DialogInterface.OnDismissListener mOnDismissListenerWrapper;
    private DialogInterface.OnKeyListener mOnKeyListener;
    private DialogInterface.OnShowListener mOnShowListener;
    private final DialogInterface.OnShowListener mOnShowListenerWrapper;
    private Rect mPanelMargins;
    private ActionSheetRootView mRootView;
    private Point mRootViewSize;
    private Point mRootViewSizeDp;
    private int mScreenOrientation;
    private DialogInterface.OnClickListener mSeparateClickListener;
    private CharSequence mSeparateText;
    private TextView mSeparateView;
    private boolean mSetupWindowInsetsAnimation;
    private AlertDialog.OnDialogShowAnimListener mShowAnimListener;
    private final AlertDialog.OnDialogShowAnimListener mShowAnimListenerWrapper;
    private SpringBackLayout mSpringBackLayout;
    private final Map<ActionSheet.ActionSheetItemType, Integer> mTypeColorMap;
    private final Window mWindow;
    private final WindowManager mWindowManager;

    /* JADX INFO: renamed from: miuix.internal.widget.ActionSheetController$8, reason: invalid class name */
    public class AnonymousClass8 implements View.OnApplyWindowInsetsListener {
        public AnonymousClass8() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onApplyWindowInsets$0(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets != null) {
                ActionSheetController.this.updateContentPanelMarginByWindowInsetsListener(rootWindowInsets);
            }
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        @NonNull
        public WindowInsets onApplyWindowInsets(@NonNull final View view, @NonNull WindowInsets windowInsets) {
            ActionSheetController.this.updateContentPanelMarginByWindowInsetsListener(windowInsets);
            view.post(new Runnable() { // from class: miuix.internal.widget.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6101a.lambda$onApplyWindowInsets$0(view);
                }
            });
            return WindowInsets.CONSUMED;
        }
    }

    public static class ActionSheetListViewAdapter extends ArrayAdapter<CharSequence> {
        private final Context mContext;
        private final ActionSheet.ActionSheetItemType[] mItemTypes;
        private final Map<ActionSheet.ActionSheetItemType, Integer> mTypeColorMap;

        public ActionSheetListViewAdapter(@NonNull Context context, int i2, int i3, @NonNull CharSequence[] charSequenceArr, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, Map<ActionSheet.ActionSheetItemType, Integer> map) {
            super(context, i2, i3, charSequenceArr);
            this.mContext = context;
            this.mItemTypes = actionSheetItemTypeArr;
            this.mTypeColorMap = map;
        }

        private void adjustTypedItemColor(TextView textView, int i2) {
            Map<ActionSheet.ActionSheetItemType, Integer> map;
            Integer num;
            ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr = this.mItemTypes;
            if (actionSheetItemTypeArr == null || textView == null || (map = this.mTypeColorMap) == null || (num = map.get(actionSheetItemTypeArr[i2])) == null) {
                return;
            }
            textView.setTextColor(this.mContext.getResources().getColor(num.intValue()));
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            adjustTypedItemColor((TextView) view2.findViewById(R.id.text1), i2);
            if (view == null) {
                AnimHelper.addPressAnim(view2);
            }
            return view2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }
    }

    public ActionSheetController(Context context, AppCompatDialog appCompatDialog, Window window) {
        this(context, appCompatDialog, window, Build.IS_TABLET ? ActionSheet.ActionSheetMode.ARROW_MODE : ActionSheet.ActionSheetMode.ALERT_MODE);
    }

    private void adjustSpringEnabled() {
        ListView listView;
        if (this.mSpringBackLayout == null || (listView = this.mListView) == null) {
            return;
        }
        listView.post(new Runnable() { // from class: miuix.internal.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6099a.lambda$adjustSpringEnabled$0();
            }
        });
    }

    @RequiresApi(api = 30)
    private void cleanWindowInsetsAnimation() {
        if (this.mSetupWindowInsetsAnimation) {
            this.mWindow.getDecorView().setWindowInsetsAnimationCallback(null);
            this.mWindow.getDecorView().setOnApplyWindowInsetsListener(null);
            this.mSetupWindowInsetsAnimation = false;
        }
    }

    private void clearFitSystemWindow(View view) {
        if ((view instanceof DialogParentPanel2) || view == null) {
            return;
        }
        int i2 = 0;
        view.setFitsSystemWindows(false);
        if (!(view instanceof ViewGroup)) {
            return;
        }
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 >= viewGroup.getChildCount()) {
                return;
            }
            clearFitSystemWindow(viewGroup.getChildAt(i2));
            i2++;
        }
    }

    @RequiresApi(api = 30)
    private void configWindow() {
        this.mWindow.setSoftInputMode((this.mWindow.getAttributes().softInputMode & 15) | 48);
        this.mWindow.getDecorView().setWindowInsetsAnimationCallback(new WindowInsetsAnimation.Callback(1) { // from class: miuix.internal.widget.ActionSheetController.7
            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                super.onEnd(windowInsetsAnimation);
                ActionSheetController.this.updateContentPanelMarginByWindowInsetsListener(ActionSheetController.this.mWindow.getDecorView().getRootWindowInsets());
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                super.onPrepare(windowInsetsAnimation);
                if (windowInsetsAnimation == null || (windowInsetsAnimation.getTypeMask() & WindowInsets.Type.ime()) <= 0) {
                    return;
                }
                ActionSheetController.this.mDialogAnimHelper.cancelAnimator();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsets onProgress(@NonNull WindowInsets windowInsets, @NonNull List<WindowInsetsAnimation> list) {
                return windowInsets;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            @NonNull
            public WindowInsetsAnimation.Bounds onStart(@NonNull WindowInsetsAnimation windowInsetsAnimation, @NonNull WindowInsetsAnimation.Bounds bounds) {
                return super.onStart(windowInsetsAnimation, bounds);
            }
        });
        this.mWindow.getDecorView().setOnApplyWindowInsetsListener(new AnonymousClass8());
        this.mSetupWindowInsetsAnimation = true;
    }

    private boolean getAssociatedActivitySystemBarVisibility(Activity activity, int i2) {
        if (activity == null || activity.getWindow() == null) {
            return true;
        }
        View decorView = activity.getWindow().getDecorView();
        WindowInsets rootWindowInsets = decorView != null ? decorView.getRootWindowInsets() : null;
        if (rootWindowInsets != null) {
            return rootWindowInsets.isVisible(i2);
        }
        return true;
    }

    @RequiresApi(api = 28)
    private int getCutoutMode(int i2, int i3) {
        return i3 == 0 ? i2 == 2 ? 2 : 1 : i3;
    }

    private int getScreenOrientation() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null) {
            return 0;
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        return (rotation == 1 || rotation == 3) ? 2 : 1;
    }

    private int getVisibleItemTotalHeight(ListView listView, int i2) {
        if (listView == null || i2 <= 0) {
            return 0;
        }
        int height = 0;
        for (int i3 = 0; i3 < Math.min(listView.getChildCount(), i2); i3++) {
            View childAt = listView.getChildAt(i3);
            if (childAt != null) {
                height += childAt.getHeight();
            }
        }
        return height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDismiss() {
        this.mDialog.dismiss();
    }

    private void initListener() {
        AppCompatDialog appCompatDialog = this.mDialog;
        if (appCompatDialog == null) {
            return;
        }
        appCompatDialog.setOnShowListener(this.mOnShowListenerWrapper);
        this.mDialog.setOnDismissListener(this.mOnDismissListenerWrapper);
        DialogInterface.OnCancelListener onCancelListener = this.mOnCancelListener;
        if (onCancelListener != null) {
            this.mDialog.setOnCancelListener(onCancelListener);
        }
        DialogInterface.OnKeyListener onKeyListener = this.mOnKeyListener;
        if (onKeyListener != null) {
            this.mDialog.setOnKeyListener(onKeyListener);
        }
    }

    private void initViewAndLayout(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, miuix.appcompat.R.styleable.ActionSheet, this.mMode == ActionSheet.ActionSheetMode.ALERT_MODE ? miuix.appcompat.R.attr.actionSheetAlertStyle : miuix.appcompat.R.attr.actionSheetArrowStyle, 0);
        this.mActionSheetLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.ActionSheet_actionSheetLayout, miuix.appcompat.R.layout.miuix_appcompat_action_sheet_layout);
        this.mContentLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.ActionSheet_actionSheetContentLayout, miuix.appcompat.R.layout.miuix_appcompat_action_sheet_alert_content);
        this.mListItemLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.ActionSheet_actionSheetListItem, miuix.appcompat.R.layout.miuix_appcompat_action_sheet_list_item);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$adjustSpringEnabled$0() {
        this.mSpringBackLayout.setSpringBackEnable(true ^ (getVisibleItemTotalHeight(this.mListView, (this.mListView.getLastVisiblePosition() - this.mListView.getFirstVisiblePosition()) + 1) == this.mListView.getHeight()));
    }

    private void postUpdateRootViewSize(View view) {
        if (view == null) {
            return;
        }
        this.mRootViewSize.x = view.getWidth();
        this.mRootViewSize.y = view.getHeight();
        float f2 = this.mContext.getResources().getDisplayMetrics().density;
        Point point = this.mRootViewSizeDp;
        Point point2 = this.mRootViewSize;
        point.x = (int) (point2.x / f2);
        point.y = (int) (point2.y / f2);
    }

    private void prepareTypeColorMap(Context context) {
        if (this.mActionItemTypes == null || context == null) {
            return;
        }
        int[] iArr = {miuix.appcompat.R.color.miuix_appcompat_action_sheet_item_text_primary_color_light, miuix.appcompat.R.color.miuix_appcompat_action_sheet_item_text_error_color_light};
        int[] iArr2 = {miuix.appcompat.R.color.miuix_appcompat_action_sheet_item_text_primary_color_dark, miuix.appcompat.R.color.miuix_appcompat_action_sheet_item_text_error_color_dark};
        this.mTypeColorMap.clear();
        for (ActionSheet.ActionSheetItemType actionSheetItemType : this.mActionItemTypes) {
            int iOrdinal = actionSheetItemType.ordinal();
            this.mTypeColorMap.put(actionSheetItemType, Integer.valueOf(ViewUtils.isNightMode(context) ? iArr2[iOrdinal] : iArr[iOrdinal]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runConfigurationChanged(Configuration configuration) {
        updateRootViewSizeByWindow();
        Log.d(TAG, "onConfigurationChanged: mRootViewSizeDp = " + this.mRootViewSizeDp);
        boolean z2 = Build.IS_TABLET;
        boolean z3 = z2 && this.mRootViewSizeDp.x >= 747;
        boolean z4 = z2 && this.mRootViewSizeDp.x < 747;
        AppCompatDialog appCompatDialog = this.mDialog;
        if ((appCompatDialog instanceof ArrowActionSheet) && z4) {
            ((ArrowActionSheet) appCompatDialog).dismissForShiftWithoutAnimation();
            View arrowAnchor = ((ArrowActionSheet) this.mDialog).getArrowAnchor();
            ActionSheet.ArrowMode arrowMode = ((ArrowActionSheet) this.mDialog).getArrowMode();
            AlertActionSheet alertActionSheetCreateAlertActionSheet = ((ArrowActionSheet) this.mDialog).createAlertActionSheet(arrowAnchor);
            alertActionSheetCreateAlertActionSheet.setArrowMode(arrowMode);
            alertActionSheetCreateAlertActionSheet.setEnableEnterAnim(false);
            alertActionSheetCreateAlertActionSheet.setIsFromArrowShape(true);
            alertActionSheetCreateAlertActionSheet.show();
            Log.d(TAG, "onConfigurationChanged first branch: ArrowActionSheet -> AlertActionSheet shift");
            return;
        }
        if (!(appCompatDialog instanceof AlertActionSheet) || !z3) {
            onConfigurationChanged(configuration);
            Log.d(TAG, "onConfigurationChanged third branch: run config changed");
            return;
        }
        View arrowActionAnchor = ((AlertActionSheet) appCompatDialog).getArrowActionAnchor();
        ActionSheet.ArrowMode arrowMode2 = ((AlertActionSheet) this.mDialog).getArrowMode();
        if (arrowActionAnchor == null || arrowMode2 == null || arrowMode2 == ActionSheet.ArrowMode.ARROW_MODE_NONE) {
            return;
        }
        ((AlertActionSheet) this.mDialog).dismissForShiftWithoutAnimation();
        ArrowActionSheet arrowActionSheetCreateArrowActionSheet = ((AlertActionSheet) this.mDialog).createArrowActionSheet(arrowActionAnchor);
        arrowActionSheetCreateArrowActionSheet.setArrowMode(arrowMode2);
        arrowActionSheetCreateArrowActionSheet.setEnableEnterAnim(false);
        arrowActionSheetCreateArrowActionSheet.setIsFromAlertShape(true);
        arrowActionSheetCreateArrowActionSheet.show();
        Log.d(TAG, "onConfigurationChanged second branch: AlertActionSheet -> ArrowActionSheet shift");
    }

    private void setWindowNavigationBarHidden() {
        View decorView = this.mWindow.getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(4098);
            this.mIsAssociatedActivityNavigationBarHidden = true;
        }
    }

    private void setupContent() {
        TextView textView;
        ViewGroup viewGroup = this.mContentPanel;
        if (viewGroup == null) {
            return;
        }
        this.mMessageView = (TextView) viewGroup.findViewById(miuix.appcompat.R.id.action_sheet_message);
        this.mDivider = this.mContentPanel.findViewById(miuix.appcompat.R.id.action_sheet_content_divider);
        if (TextUtils.isEmpty(this.mMessage) || (textView = this.mMessageView) == null) {
            TextView textView2 = this.mMessageView;
            if (textView2 != null && textView2.getVisibility() == 0) {
                this.mMessageView.setVisibility(8);
            }
            View view = this.mDivider;
            if (view != null && view.getVisibility() == 0) {
                this.mDivider.setVisibility(8);
            }
        } else {
            textView.setText(this.mMessage);
            if (this.mMessageView.getVisibility() == 8) {
                this.mMessageView.setVisibility(0);
            }
            View view2 = this.mDivider;
            if (view2 != null && view2.getVisibility() == 8) {
                this.mDivider.setVisibility(0);
            }
        }
        setupListView();
        if (this.mMode == ActionSheet.ActionSheetMode.ALERT_MODE) {
            this.mSeparateView = (TextView) this.mContentPanel.findViewById(miuix.appcompat.R.id.separate_item_text);
        }
        if (TextUtils.isEmpty(this.mSeparateText)) {
            this.mSeparateText = this.mContext.getString(miuix.appcompat.R.string.miuix_appcompat_cancel_description);
        }
        TextView textView3 = this.mSeparateView;
        if (textView3 != null) {
            textView3.setText(this.mSeparateText);
            AnimHelper.addPressAnim(this.mSeparateView);
            this.mSeparateView.setOnClickListener(new View.OnClickListener() { // from class: miuix.internal.widget.ActionSheetController.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    if (view3 != null) {
                        HapticCompat.performHapticFeedbackAsync(view3, HapticFeedbackConstants.MIUI_BUTTON_MIDDLE, HapticFeedbackConstants.MIUI_TAP_LIGHT);
                    }
                    if (ActionSheetController.this.mSeparateClickListener != null) {
                        ActionSheetController.this.mSeparateClickListener.onClick(ActionSheetController.this.mDialog, -2);
                    }
                    ActionSheetController.this.handleDismiss();
                }
            });
        }
    }

    private void setupContentPanel() {
        FrameLayout.LayoutParams layoutParams;
        if (this.mContentController == null) {
            throw new RuntimeException("action sheet require set contentController");
        }
        WindowInsets rootWindowInsets = this.mWindow.getDecorView().getRootWindowInsets();
        if (this.mContentPanel == null) {
            this.mContentPanel = (ViewGroup) LayoutInflater.from(this.mContext).inflate(this.mContentLayout, (ViewGroup) this.mRootView, false);
        }
        ViewGroup viewGroup = this.mContentPanel;
        if (viewGroup instanceof ArrowActionSheetPanel) {
            ((ArrowActionSheetPanel) viewGroup).setArrowMode(this.mContentController.getArrowMode());
        }
        this.mContentPanelWidth = this.mContentController.calcContentPanelWidth(this.mContext, this.mRootView, this.mContentPanel, this.mRootViewSize.x, rootWindowInsets);
        this.mContentPanelHeight = this.mContentController.calcContentPanelHeight(this.mContext, this.mRootView, this.mContentPanel, this.mRootViewSize.y, rootWindowInsets);
        int[] iArrCalcHorizontalMargin = this.mContentController.calcHorizontalMargin(this.mContext, rootWindowInsets);
        Rect rect = this.mPanelMargins;
        int i2 = iArrCalcHorizontalMargin[0];
        rect.left = i2;
        int i3 = iArrCalcHorizontalMargin[1];
        rect.right = i3;
        if (this.mContentPanelWidth == -1) {
            this.mContentPanelWidth = this.mRootViewSize.x - (i2 + i3);
        }
        int[] iArrCalcVerticalMargin = this.mContentController.calcVerticalMargin(this.mContext, rootWindowInsets);
        Rect rect2 = this.mPanelMargins;
        rect2.top = iArrCalcVerticalMargin[0];
        rect2.bottom = iArrCalcVerticalMargin[1];
        this.mRootView.setContentPanelExtraBounds(rect2);
        ViewGroup viewGroup2 = this.mContentPanel;
        if (viewGroup2 == null || viewGroup2.getParent() != null) {
            layoutParams = (FrameLayout.LayoutParams) this.mContentPanel.getLayoutParams();
            layoutParams.width = this.mContentPanelWidth;
            layoutParams.height = this.mContentPanelHeight;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.mContentPanelWidth, this.mContentPanelHeight);
        }
        Rect rect3 = this.mPanelMargins;
        layoutParams.topMargin = rect3.top;
        layoutParams.bottomMargin = rect3.bottom;
        layoutParams.leftMargin = rect3.left;
        layoutParams.rightMargin = rect3.right;
        this.mContentPanel.setLayoutParams(layoutParams);
        ViewGroup viewGroup3 = this.mContentPanel;
        if (viewGroup3 == null || viewGroup3.getParent() != null) {
            return;
        }
        this.mRootView.addView(this.mContentPanel);
    }

    private void setupContentView(boolean z2) {
        setupContentPanel();
        if (!z2) {
            setupContent();
        }
        adjustSpringEnabled();
    }

    private void setupListView() {
        ViewGroup viewGroup = this.mContentPanel;
        if (viewGroup == null) {
            return;
        }
        this.mSpringBackLayout = (SpringBackLayout) viewGroup.findViewById(miuix.appcompat.R.id.actionSheetSpringBack);
        ListView listView = (ListView) this.mContentPanel.findViewById(miuix.appcompat.R.id.action_sheet_list_view);
        this.mListView = listView;
        if (this.mItemClickListener != null) {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.internal.widget.ActionSheetController.10
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                    if (view.isEnabled()) {
                        ActionSheetController.this.mItemClickListener.onClick(ActionSheetController.this.mDialog, i2);
                        ActionSheetController.this.handleDismiss();
                    }
                }
            });
        }
        ListAdapter actionSheetListViewAdapter = this.mListViewAdapter;
        if (actionSheetListViewAdapter == null) {
            actionSheetListViewAdapter = new ActionSheetListViewAdapter(this.mContext, this.mListItemLayout, R.id.text1, this.mActionItems, this.mActionItemTypes, this.mTypeColorMap);
        }
        this.mListView.setAdapter(actionSheetListViewAdapter);
    }

    private void setupWindow() {
        this.mWindow.setLayout(-1, -1);
        this.mWindow.setBackgroundDrawableResource(miuix.appcompat.R.color.miuix_color_transparent);
        this.mWindow.setDimAmount(0.0f);
        this.mWindow.setWindowAnimations(miuix.appcompat.R.style.Animation_Dialog_NoAnimation);
        this.mWindow.addFlags(-2147481344);
        this.mWindow.setFlags(131072, 131072);
        ActionSheet.ActionSheetMode actionSheetMode = this.mMode;
        ActionSheet.ActionSheetMode actionSheetMode2 = ActionSheet.ActionSheetMode.ALERT_MODE;
        Activity associatedActivity = actionSheetMode == actionSheetMode2 ? ((AlertDialog) this.mDialog).getAssociatedActivity() : ((AlertDialog) this.mDialog).getAssociatedActivity();
        if (associatedActivity != null) {
            this.mWindow.getAttributes().layoutInDisplayCutoutMode = getCutoutMode(getScreenOrientation(), associatedActivity.getWindow().getAttributes().layoutInDisplayCutoutMode);
        } else {
            this.mWindow.getAttributes().layoutInDisplayCutoutMode = getScreenOrientation() != 2 ? 3 : 2;
        }
        clearFitSystemWindow(this.mWindow.getDecorView());
        this.mWindow.getAttributes().setFitInsetsSides(0);
        Activity associatedActivity2 = this.mMode == actionSheetMode2 ? ((AlertDialog) this.mDialog).getAssociatedActivity() : ((AlertDialog) this.mDialog).getAssociatedActivity();
        boolean associatedActivitySystemBarVisibility = getAssociatedActivitySystemBarVisibility(associatedActivity2, WindowInsets.Type.statusBars());
        if (associatedActivity2 != null && (associatedActivity2.getWindow().getAttributes().flags & 1024) != 1024 && associatedActivitySystemBarVisibility) {
            this.mWindow.clearFlags(1024);
        }
        if (getAssociatedActivitySystemBarVisibility(associatedActivity2, WindowInsets.Type.navigationBars())) {
            return;
        }
        setWindowNavigationBarHidden();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 30)
    public void updateContentPanelMarginByWindowInsetsListener(@NonNull WindowInsets windowInsets) {
        boolean z2;
        int[] iArrCalcVerticalMargin = this.mContentController.calcVerticalMargin(this.mContext, windowInsets);
        int[] iArrCalcHorizontalMargin = this.mContentController.calcHorizontalMargin(this.mContext, windowInsets);
        int width = this.mRootView.getWidth() == 0 ? this.mRootViewSize.x : this.mRootView.getWidth();
        int height = this.mRootView.getHeight() == 0 ? this.mRootViewSize.y : this.mRootView.getHeight();
        int iCalcContentPanelWidth = this.mContentController.calcContentPanelWidth(this.mContext, this.mRootView, this.mContentPanel, width, windowInsets);
        int iCalcContentPanelHeight = this.mContentController.calcContentPanelHeight(this.mContext, this.mRootView, this.mContentPanel, height, windowInsets);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mContentPanel.getLayoutParams();
        boolean z3 = true;
        if (layoutParams.width != iCalcContentPanelWidth) {
            layoutParams.width = iCalcContentPanelWidth;
            z2 = true;
        } else {
            z2 = false;
        }
        if (layoutParams.height != iCalcContentPanelHeight) {
            layoutParams.height = iCalcContentPanelHeight;
            z2 = true;
        }
        int i2 = layoutParams.topMargin;
        int i3 = iArrCalcVerticalMargin[0];
        if (i2 != i3) {
            layoutParams.topMargin = i3;
            this.mPanelMargins.top = i3;
            z2 = true;
        }
        int i4 = layoutParams.bottomMargin;
        int i5 = iArrCalcVerticalMargin[1];
        if (i4 != i5) {
            layoutParams.bottomMargin = i5;
            this.mPanelMargins.bottom = i5;
            z2 = true;
        }
        int i6 = layoutParams.leftMargin;
        int i7 = iArrCalcHorizontalMargin[0];
        if (i6 != i7) {
            layoutParams.leftMargin = i7;
            this.mPanelMargins.left = i7;
            z2 = true;
        }
        int i8 = layoutParams.rightMargin;
        int i9 = iArrCalcHorizontalMargin[1];
        if (i8 != i9) {
            layoutParams.rightMargin = i9;
            this.mPanelMargins.right = i9;
        } else {
            z3 = z2;
        }
        this.mRootView.setContentPanelExtraBounds(this.mPanelMargins);
        if (z3) {
            this.mContentPanel.requestLayout();
        }
    }

    private void updateDimBgBottomMargin(int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mDimBg.getLayoutParams();
        if (marginLayoutParams.bottomMargin != i2) {
            marginLayoutParams.bottomMargin = i2;
            this.mDimBg.requestLayout();
        }
    }

    private void updateRootViewSizeByWindow() {
        WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(this.mContext);
        Point point = this.mRootViewSize;
        Point point2 = windowInfo.windowSize;
        point.x = point2.x;
        point.y = point2.y;
        Point point3 = this.mRootViewSizeDp;
        Point point4 = windowInfo.windowSizeDp;
        point3.x = point4.x;
        point3.y = point4.y;
    }

    private void updateWindowCutoutMode() {
        int screenOrientation = getScreenOrientation();
        if (this.mScreenOrientation != screenOrientation) {
            this.mScreenOrientation = screenOrientation;
            Activity associatedActivity = ((AlertDialog) this.mDialog).getAssociatedActivity();
            if (associatedActivity != null) {
                int cutoutMode = getCutoutMode(screenOrientation, associatedActivity.getWindow().getAttributes().layoutInDisplayCutoutMode);
                if (this.mWindow.getAttributes().layoutInDisplayCutoutMode != cutoutMode) {
                    this.mWindow.getAttributes().layoutInDisplayCutoutMode = cutoutMode;
                    View decorView = this.mWindow.getDecorView();
                    if (this.mDialog.isShowing() && decorView.isAttachedToWindow()) {
                        this.mWindowManager.updateViewLayout(this.mWindow.getDecorView(), this.mWindow.getAttributes());
                        return;
                    }
                    return;
                }
                return;
            }
            int i2 = getScreenOrientation() != 2 ? 3 : 2;
            if (this.mWindow.getAttributes().layoutInDisplayCutoutMode != i2) {
                this.mWindow.getAttributes().layoutInDisplayCutoutMode = i2;
                View decorView2 = this.mWindow.getDecorView();
                if (this.mDialog.isShowing() && decorView2.isAttachedToWindow()) {
                    this.mWindowManager.updateViewLayout(this.mWindow.getDecorView(), this.mWindow.getAttributes());
                }
            }
        }
    }

    private boolean useTabletAnim() {
        return Build.IS_TABLET && this.mMode == ActionSheet.ActionSheetMode.ARROW_MODE;
    }

    public void checkAndClearFocus() {
        View currentFocus = this.mWindow.getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }
    }

    public void dismiss(DialogAnimHelper.OnDismiss onDismiss) {
        cleanWindowInsetsAnimation();
        ViewGroup viewGroup = this.mContentPanel;
        if (viewGroup == null) {
            if (onDismiss != null) {
                onDismiss.end();
            }
        } else {
            if (viewGroup.isAttachedToWindow()) {
                checkAndClearFocus();
                this.mDialogAnimHelper.executeDismissAnim(this.mContentPanel, useTabletAnim(), this.mDimBg, onDismiss);
                return;
            }
            Log.d(TAG, "dialog is not attached to window when dismiss is invoked");
            try {
                ((AlertDialog) this.mDialog).realDismiss();
            } catch (IllegalArgumentException e2) {
                Log.wtf(TAG, "Not catch the dialog will throw the illegalArgumentException (In Case cause the crash , we expect it should be caught)", e2);
            }
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 82;
    }

    public CharSequence[] getActionItems() {
        return this.mActionItems;
    }

    public DialogInterface.OnClickListener getItemClickListener() {
        return this.mItemClickListener;
    }

    public ActionSheet.ActionSheetItemType[] getItemTypes() {
        return this.mActionItemTypes;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public ListAdapter getListViewAdapter() {
        return this.mListViewAdapter;
    }

    public CharSequence getMessage() {
        return this.mMessage;
    }

    public TextView getMessageView() {
        return this.mMessageView;
    }

    public DialogInterface.OnCancelListener getOnCancelListener() {
        return this.mOnCancelListener;
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return this.mOnDismissListener;
    }

    public DialogInterface.OnKeyListener getOnKeyListener() {
        return this.mOnKeyListener;
    }

    public DialogInterface.OnShowListener getOnShowListener() {
        return this.mOnShowListener;
    }

    public TextView getSeparateView() {
        return this.mSeparateView;
    }

    public AlertDialog.OnDialogShowAnimListener getShowAnimListener() {
        return this.mShowAnimListener;
    }

    public void installContent(Bundle bundle) {
        this.mIsFromRebuild = bundle != null;
        this.mDialog.setContentView(this.mActionSheetLayout);
        ActionSheetRootView actionSheetRootView = (ActionSheetRootView) this.mWindow.findViewById(miuix.appcompat.R.id.action_sheet_root_view);
        this.mRootView = actionSheetRootView;
        actionSheetRootView.setConfigurationChangedCallback(new ActionSheetRootView.ConfigurationChangedCallback() { // from class: miuix.internal.widget.ActionSheetController.4
            @Override // miuix.internal.widget.ActionSheetRootView.ConfigurationChangedCallback
            public void onConfigurationChanged(Configuration configuration) {
                ActionSheetController.this.runConfigurationChanged(configuration);
            }
        });
        this.mRootView.setContentController(this.mContentController);
        View viewFindViewById = this.mWindow.findViewById(miuix.appcompat.R.id.action_sheet_dim_bg);
        this.mDimBg = viewFindViewById;
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: miuix.internal.widget.ActionSheetController.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ActionSheetController actionSheetController = ActionSheetController.this;
                if (actionSheetController.mCanceledOnTouchOutside) {
                    actionSheetController.mDialog.cancel();
                }
            }
        });
        this.mDimBg.setAlpha(ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT);
        updateRootViewSizeByWindow();
        setupWindow();
        setupContentView(false);
        prepareTypeColorMap(this.mContext);
    }

    public boolean isCanceledOnTouchOutside() {
        return this.mCanceledOnTouchOutside;
    }

    public boolean isShowingAnimation() {
        return this.mEnableEnterAnim && this.mIsDialogAnimating;
    }

    public void onAttachedToWindow() {
        configWindow();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mIsFlipTinyScreen = Build.IS_FLIP && DeviceHelper.isTinyScreen(this.mContext);
        updateRootViewSizeByWindow();
        if (this.mWindow.getDecorView().isAttachedToWindow()) {
            updateWindowCutoutMode();
            setupContentView(true);
        }
        WindowInsets rootWindowInsets = this.mWindow.getDecorView().getRootWindowInsets();
        if (rootWindowInsets != null) {
            updateContentPanelMarginByWindowInsetsListener(rootWindowInsets);
        }
        this.mRootView.post(new Runnable() { // from class: miuix.internal.widget.ActionSheetController.6
            @Override // java.lang.Runnable
            public void run() {
                WindowInsets rootWindowInsets2 = ActionSheetController.this.mWindow.getDecorView().getRootWindowInsets();
                if (rootWindowInsets2 != null) {
                    ActionSheetController.this.updateContentPanelMarginByWindowInsetsListener(rootWindowInsets2);
                }
            }
        });
    }

    public void onDetachedFromWindow() {
        if (AnimHelper.isDialogDebugInAndroidUIThreadEnabled()) {
            return;
        }
        Folme.clean(this.mContentPanel, this.mDimBg);
        translateContentPanel(0);
    }

    public void onStart() {
        if (this.mDimBg != null) {
            updateDimBgBottomMargin(0);
        }
        updateWindowCutoutMode();
        if (this.mIsFromRebuild || !this.mEnableEnterAnim) {
            this.mDimBg.setAlpha(ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT);
        } else {
            this.mDialogAnimHelper.executeShowAnim(this.mContentPanel, this.mDimBg, useTabletAnim(), false, this.mShowAnimListenerWrapper);
        }
    }

    public void setActionItems(@ArrayRes int i2, DialogInterface.OnClickListener onClickListener) {
        this.mActionItems = this.mContext.getResources().getTextArray(i2);
        this.mItemClickListener = onClickListener;
    }

    public void setCanceledOnTouchOutside(boolean z2) {
        this.mCanceledOnTouchOutside = z2;
    }

    public void setContentController(ActionSheet.ContentController contentController) {
        this.mContentController = contentController;
    }

    public void setEnableEnterAnim(boolean z2) {
        this.mEnableEnterAnim = z2;
    }

    public void setListViewAdapter(ListAdapter listAdapter) {
        this.mListViewAdapter = listAdapter;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.mOnKeyListener = onKeyListener;
    }

    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    public void setPendingDismiss(boolean z2) {
        this.mHasPendingDismiss = z2;
    }

    public void setSeparateClickListener(DialogInterface.OnClickListener onClickListener) {
        this.mSeparateClickListener = onClickListener;
    }

    public void setSeparateItemText(CharSequence charSequence) {
        this.mSeparateText = charSequence;
        TextView textView = this.mSeparateView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setShowAnimListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        this.mShowAnimListener = onDialogShowAnimListener;
    }

    public void translateContentPanel(int i2) {
        ViewGroup viewGroup = this.mContentPanel;
        if (viewGroup == null) {
            return;
        }
        viewGroup.animate().cancel();
        this.mContentPanel.setTranslationY(i2);
    }

    public ActionSheetController(Context context, AppCompatDialog appCompatDialog, Window window, ActionSheet.ActionSheetMode actionSheetMode) {
        this.mPanelMargins = new Rect();
        this.mRootViewSize = new Point();
        this.mRootViewSizeDp = new Point();
        this.mTypeColorMap = new HashMap();
        boolean z2 = false;
        this.mScreenOrientation = 0;
        this.mDialogAnimHelper = new DialogAnimHelper();
        this.mHasPendingDismiss = false;
        this.mCanceledOnTouchOutside = true;
        this.mIsAssociatedActivityNavigationBarHidden = false;
        this.mOnShowListenerWrapper = new DialogInterface.OnShowListener() { // from class: miuix.internal.widget.ActionSheetController.1
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                boolean zIsFromAlertShape = dialogInterface instanceof ArrowActionSheet ? ((ArrowActionSheet) dialogInterface).isFromAlertShape() : dialogInterface instanceof AlertActionSheet ? ((AlertActionSheet) dialogInterface).isFromArrowShape() : false;
                if (ActionSheetController.this.mOnShowListener == null || zIsFromAlertShape) {
                    return;
                }
                ActionSheetController.this.mOnShowListener.onShow(dialogInterface);
            }
        };
        this.mOnDismissListenerWrapper = new DialogInterface.OnDismissListener() { // from class: miuix.internal.widget.ActionSheetController.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                boolean z3 = dialogInterface instanceof ArrowActionSheet;
                boolean zIsDismissForShift = z3 ? ((ArrowActionSheet) dialogInterface).isDismissForShift() : dialogInterface instanceof AlertActionSheet ? ((AlertActionSheet) dialogInterface).isDismissForShift() : false;
                if (ActionSheetController.this.mOnDismissListener != null && !zIsDismissForShift) {
                    ActionSheetController.this.mOnDismissListener.onDismiss(dialogInterface);
                    if (z3) {
                        ((ArrowActionSheet) dialogInterface).setIsFromAlertShape(false);
                    } else if (dialogInterface instanceof AlertActionSheet) {
                        ((AlertActionSheet) dialogInterface).setIsFromArrowShape(false);
                    }
                }
                if (z3) {
                    ((ArrowActionSheet) dialogInterface).setIsDismissForShift(false);
                } else if (dialogInterface instanceof AlertActionSheet) {
                    ((AlertActionSheet) dialogInterface).setIsDismissForShift(false);
                }
            }
        };
        this.mShowAnimListenerWrapper = new AlertDialog.OnDialogShowAnimListener() { // from class: miuix.internal.widget.ActionSheetController.3
            @Override // miuix.appcompat.app.AlertDialog.OnDialogShowAnimListener
            public void onShowAnimComplete() {
                ActionSheetController.this.mIsDialogAnimating = false;
                if (ActionSheetController.this.mShowAnimListener != null) {
                    ActionSheetController.this.mShowAnimListener.onShowAnimComplete();
                }
                ActionSheetController actionSheetController = ActionSheetController.this;
                if (!actionSheetController.mHasPendingDismiss || actionSheetController.mDialog == null || actionSheetController.mWindow == null) {
                    return;
                }
                View decorView = ActionSheetController.this.mWindow.getDecorView();
                final AppCompatDialog appCompatDialog2 = ActionSheetController.this.mDialog;
                Objects.requireNonNull(appCompatDialog2);
                decorView.post(new Runnable() { // from class: miuix.internal.widget.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        appCompatDialog2.dismiss();
                    }
                });
            }

            @Override // miuix.appcompat.app.AlertDialog.OnDialogShowAnimListener
            public void onShowAnimStart() {
                ActionSheetController.this.mIsDialogAnimating = true;
                if (ActionSheetController.this.mShowAnimListener != null) {
                    ActionSheetController.this.mShowAnimListener.onShowAnimStart();
                }
            }
        };
        this.mMode = actionSheetMode;
        this.mContext = context;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mEnableEnterAnim = true;
        if (Build.IS_FLIP && DeviceHelper.isTinyScreen(context)) {
            z2 = true;
        }
        this.mIsFlipTinyScreen = z2;
        initViewAndLayout(context);
        initListener();
    }

    public void setActionItems(@ArrayRes int i2, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionItems = this.mContext.getResources().getTextArray(i2);
        this.mActionItemTypes = actionSheetItemTypeArr;
        this.mItemClickListener = onClickListener;
    }

    public void setActionItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionItems = charSequenceArr;
        this.mItemClickListener = onClickListener;
    }

    public void setActionItems(CharSequence[] charSequenceArr, ActionSheet.ActionSheetItemType[] actionSheetItemTypeArr, DialogInterface.OnClickListener onClickListener) {
        this.mActionItems = charSequenceArr;
        this.mActionItemTypes = actionSheetItemTypeArr;
        this.mItemClickListener = onClickListener;
    }
}
