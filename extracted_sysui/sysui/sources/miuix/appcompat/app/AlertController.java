package miuix.appcompat.app;

import android.R;
import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miuix.animation.Folme;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.DialogContract;
import miuix.appcompat.app.strategy.DialogButtonBehaviorImpl;
import miuix.appcompat.app.strategy.DialogPanelBehaviorImpl;
import miuix.appcompat.internal.widget.DialogButtonPanel;
import miuix.appcompat.internal.widget.DialogParentPanel2;
import miuix.appcompat.internal.widget.DialogRootView;
import miuix.appcompat.internal.widget.NestedScrollViewExpander;
import miuix.appcompat.widget.DialogAnimHelper;
import miuix.core.util.EnvStateManager;
import miuix.core.util.MiuixUIUtils;
import miuix.core.util.RomUtils;
import miuix.core.util.ScreenModeHelper;
import miuix.core.util.SystemProperties;
import miuix.core.util.WindowBaseInfo;
import miuix.core.util.WindowUtils;
import miuix.device.DeviceUtils;
import miuix.internal.util.AnimHelper;
import miuix.internal.util.AsyncInflateLayoutManager;
import miuix.internal.util.AttributeResolver;
import miuix.internal.util.LiteUtils;
import miuix.internal.util.ReflectUtil;
import miuix.internal.util.ViewUtils;
import miuix.internal.widget.GroupButton;
import miuix.os.Build;
import miuix.os.DeviceHelper;
import miuix.reflect.ReflectionHelper;
import miuix.theme.token.DimToken;
import miuix.view.CompatViewMethod;
import miuix.view.DensityChangedHelper;
import miuix.view.animation.CubicEaseInOutInterpolator;

/* JADX INFO: loaded from: classes2.dex */
class AlertController {
    private static final int FLAG_NO_EAR_AREA = 768;
    private static final String TAG = "AlertController";
    private boolean buildJustNow;
    private Configuration configurationAfterInstalled;
    ListAdapter mAdapter;
    private final int mAlertDialogLayout;
    private boolean mAsyncInflatePanelEnabled;
    private boolean mButtonForceVertical;
    private final View.OnClickListener mButtonHandler;
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private CharSequence mCheckBoxMessage;
    int mCheckedItem;
    private CharSequence mComment;
    private float mCommentTextSize;
    private TextView mCommentView;
    private AlertDialog.OnConfigurationChangedListener mConfigurationChangedListener;
    private final Context mContext;
    private final Thread mCreateThread;
    private int mCurrentDensityDpi;
    private float mCustomTitleTextSize;
    private TextView mCustomTitleTextView;
    private View mCustomTitleView;
    private TextWatcher mDefaultButtonsTextWatcher;
    final AppCompatDialog mDialog;
    private final DialogAnimHelper mDialogAnimHelper;
    private int mDialogContentLayout;
    private DialogRootView mDialogRootView;
    private View mDimBg;
    private boolean mDiscardImeAnimEnabled;
    private Rect mDisplayCutoutSafeInsets;
    private final DialogDisplayStrategy mDisplayStrategy;
    boolean mEnableEnterAnim;
    private List<ButtonInfo> mExtraButtonList;
    private DisplayCutout mFlipCutout;
    Handler mHandler;
    boolean mHapticFeedbackEnabled;
    private boolean mHasPendingDismiss;
    private Drawable mIcon;
    private int mIconHeight;
    private int mIconId;
    private int mIconWidth;
    private View mInflatedView;
    private boolean mInsetsAnimationPlayed;
    private boolean mIsAssociatedActivityHideNavigationBar;
    private boolean mIsCarWithScreen;
    private boolean mIsChecked;
    private boolean mIsDialogAnimating;
    private boolean mIsEnableImmersive;
    private boolean mIsFlipTinyScreen;
    private boolean mIsFromRebuild;
    private boolean mIsSynergy;
    private boolean mIsWindowLandScape;
    private boolean mLandscapePanel;
    private final LayoutChangeListener mLayoutChangeListener;
    private AlertDialog.OnDialogLayoutReloadListener mLayoutReloadListener;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private int mLiteVersion;
    private boolean mMarkLandscape;
    private CharSequence mMessage;
    private float mMessageTextSize;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    private boolean mNavigationBarHiddenEnabled;
    long mNonImmersiveDialogShowAnimDuration;
    private int mPanelAndImeMargin;
    private int mPanelFixedHeight;
    private boolean mPanelFixedSizeEnabled;
    private int mPanelFixedWidth;
    private int mPanelParamsHorizontalMargin;
    private int mPanelParamsWidth;
    private AlertDialog.OnPanelSizeChangedListener mPanelSizeChangedListener;
    private DialogParentPanel2 mParentPanel;
    private boolean mPreferLandscape;
    private boolean mPrimaryButtonFirstEnabled;
    private Point mRootViewSize;
    private Point mRootViewSizeDp;
    private int mScreenMinorSize;
    private int mScreenOrientation;
    private Point mScreenRealSize;
    private boolean mSetupWindowInsetsAnimation;
    private AlertDialog.OnDialogShowAnimListener mShowAnimListener;
    private AlertDialog.OnDialogShowAnimListener mShowAnimListenerWrapper;
    private final boolean mShowTitle;
    int mSingleChoiceItemLayout;
    private boolean mSmallIcon;
    private CharSequence mTitle;
    private float mTitleTextSize;
    private TextView mTitleView;
    private boolean mUseForceFlipCutout;
    private View mView;
    private int mViewLayoutResId;
    private final Window mWindow;
    private WindowManager mWindowManager;
    private boolean mIsDebugEnabled = false;
    private int mExtraImeMargin = -1;
    private boolean mIsInFreeForm = false;
    private int mNonImmersiveDialogHeight = -2;
    long mShowUpTimeMillis = 0;
    private final DialogContract.DimensConfig mDimensConfig = new DialogContract.DimensConfig();

    /* JADX INFO: renamed from: miuix.appcompat.app.AlertController$10, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$miuix$appcompat$app$AlertController$AlertParams$ItemType;

        static {
            int[] iArr = new int[AlertParams.ItemType.values().length];
            $SwitchMap$miuix$appcompat$app$AlertController$AlertParams$ItemType = iArr;
            try {
                iArr[AlertParams.ItemType.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$miuix$appcompat$app$AlertController$AlertParams$ItemType[AlertParams.ItemType.CHOICE_SINGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$miuix$appcompat$app$AlertController$AlertParams$ItemType[AlertParams.ItemType.CHOICE_MULTI.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: miuix.appcompat.app.AlertController$2, reason: invalid class name */
    public class AnonymousClass2 implements AlertDialog.OnDialogShowAnimListener {
        public AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onShowAnimComplete$0() {
            AlertController.this.mDialog.dismiss();
        }

        @Override // miuix.appcompat.app.AlertDialog.OnDialogShowAnimListener
        public void onShowAnimComplete() {
            AlertController.this.mIsDialogAnimating = false;
            if (AlertController.this.mShowAnimListener != null) {
                AlertController.this.mShowAnimListener.onShowAnimComplete();
            }
            if (AlertController.this.mHasPendingDismiss) {
                AlertController alertController = AlertController.this;
                if (alertController.mDialog == null || alertController.mWindow == null) {
                    return;
                }
                AlertController.this.mWindow.getDecorView().post(new Runnable() { // from class: miuix.appcompat.app.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6026a.lambda$onShowAnimComplete$0();
                    }
                });
            }
        }

        @Override // miuix.appcompat.app.AlertDialog.OnDialogShowAnimListener
        public void onShowAnimStart() {
            AlertController.this.mIsDialogAnimating = true;
            if (AlertController.this.mShowAnimListener != null) {
                AlertController.this.mShowAnimListener.onShowAnimStart();
            }
        }
    }

    /* JADX INFO: renamed from: miuix.appcompat.app.AlertController$8, reason: invalid class name */
    public class AnonymousClass8 implements View.OnApplyWindowInsetsListener {
        public AnonymousClass8() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onApplyWindowInsets$0(View view) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            if (rootWindowInsets != null) {
                AlertController.this.updateDialogPanelByWindowInsets(rootWindowInsets);
            }
        }

        @Override // android.view.View.OnApplyWindowInsetsListener
        @RequiresApi(api = 30)
        public WindowInsets onApplyWindowInsets(final View view, WindowInsets windowInsets) {
            if (AlertController.this.mIsDebugEnabled) {
                Log.d(AlertController.TAG, "onApplyWindowInsets insets " + windowInsets);
            }
            AlertController.this.mPanelAndImeMargin = (int) (r0.getDialogPanelMargin() + AlertController.this.mParentPanel.getTranslationY());
            if (view != null && windowInsets != null) {
                if (AlertController.this.mLayoutChangeListener != null) {
                    AlertController.this.mLayoutChangeListener.updateLayout(view);
                }
                AlertController.this.updateDialogPanelByWindowInsets(windowInsets);
                view.post(new Runnable() { // from class: miuix.appcompat.app.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6041a.lambda$onApplyWindowInsets$0(view);
                    }
                });
            }
            return WindowInsets.CONSUMED;
        }
    }

    public static class AlertParams {
        int iconHeight;
        int iconWidth;
        ListAdapter mAdapter;
        boolean mAsyncInflatePanelEnabled;
        boolean mButtonForceVertical;
        CharSequence mCheckBoxMessage;
        boolean[] mCheckedItems;
        CharSequence mComment;
        AlertDialog.OnConfigurationChangedListener mConfigurationChangedListener;
        final Context mContext;
        Cursor mCursor;
        View mCustomTitleView;
        boolean mDiscardImeAnimEnabled;
        boolean mEnableDialogImmersive;
        boolean mEnableEnterAnim;
        List<ButtonInfo> mExtraButtonList;
        boolean mHapticFeedbackEnabled;
        Drawable mIcon;
        final LayoutInflater mInflater;
        boolean mIsChecked;
        String mIsCheckedColumn;
        boolean mIsMultiChoice;
        boolean mIsSingleChoice;
        CharSequence[] mItems;
        AccessibilityDelegateProvider mItemsProvider;
        String mLabelColumn;
        int mLiteVersion;
        CharSequence mMessage;
        DialogInterface.OnClickListener mNegativeButtonListener;
        CharSequence mNegativeButtonText;
        DialogInterface.OnClickListener mNeutralButtonListener;
        CharSequence mNeutralButtonText;
        int mNonImmersiveDialogHeight;
        DialogInterface.OnCancelListener mOnCancelListener;
        DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        DialogInterface.OnClickListener mOnClickListener;
        AlertDialog.OnDialogShowAnimListener mOnDialogShowAnimListener;
        DialogInterface.OnDismissListener mOnDismissListener;
        AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        DialogInterface.OnKeyListener mOnKeyListener;
        OnPrepareListViewListener mOnPrepareListViewListener;
        DialogInterface.OnShowListener mOnShowListener;
        AlertDialog.OnPanelSizeChangedListener mPanelSizeChangedListener;
        DialogInterface.OnClickListener mPositiveButtonListener;
        CharSequence mPositiveButtonText;
        boolean mPreferLandscape;
        boolean mSmallIcon;
        CharSequence mTitle;
        boolean mUseForceFlipCutout;
        View mView;
        int mViewLayoutResId;
        int mIconId = 0;
        int mIconAttrId = 0;
        int mCheckedItem = -1;
        boolean mPrimaryButtonFirstEnabled = false;
        boolean mCancelable = true;

        public enum ItemType {
            DEFAULT,
            CHOICE_SINGLE,
            CHOICE_MULTI
        }

        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            this.mContext = context;
            this.mEnableDialogImmersive = (LiteUtils.isCommonLiteStrategy() || (Build.IS_FLIP && DeviceHelper.isTinyScreen(context))) ? false : true;
            this.mNonImmersiveDialogHeight = -2;
            int miuiLiteVersion = DeviceUtils.getMiuiLiteVersion();
            this.mLiteVersion = miuiLiteVersion;
            if (miuiLiteVersion < 0) {
                this.mLiteVersion = 0;
            }
            this.mEnableEnterAnim = true;
            this.mExtraButtonList = new ArrayList();
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        private void createListView(final AlertController alertController) {
            int i2;
            ItemType itemType;
            ListAdapter listAdapter;
            final ListView listView = (ListView) this.mInflater.inflate(alertController.mListLayout, (ViewGroup) null);
            if (this.mIsMultiChoice) {
                listAdapter = this.mCursor == null ? new ArrayAdapter<CharSequence>(this.mContext, alertController.mMultiChoiceItemLayout, R.id.text1, this.mItems) { // from class: miuix.appcompat.app.AlertController.AlertParams.2
                    @Override // android.widget.ArrayAdapter, android.widget.Adapter
                    public View getView(int i3, View view, ViewGroup viewGroup) {
                        View view2 = super.getView(i3, view, viewGroup);
                        boolean[] zArr = AlertParams.this.mCheckedItems;
                        if (zArr != null && zArr[i3]) {
                            listView.setItemChecked(i3, true);
                        }
                        CompatViewMethod.setForceDarkAllowed(view2, false);
                        AlertParams.setAccessibilityDelegate(view2, ItemType.CHOICE_MULTI, AlertParams.this.mItemsProvider);
                        return view2;
                    }
                } : new CursorAdapter(this.mContext, this.mCursor, false) { // from class: miuix.appcompat.app.AlertController.AlertParams.3
                    private final int mIsCheckedIndex;
                    private final int mLabelIndex;

                    {
                        Cursor cursor = getCursor();
                        this.mLabelIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                        this.mIsCheckedIndex = cursor.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                    }

                    @Override // android.widget.CursorAdapter
                    public void bindView(View view, Context context, Cursor cursor) {
                        ((CheckedTextView) view.findViewById(R.id.text1)).setText(cursor.getString(this.mLabelIndex));
                        listView.setItemChecked(cursor.getPosition(), cursor.getInt(this.mIsCheckedIndex) == 1);
                        AlertParams.setAccessibilityDelegate(view, ItemType.CHOICE_MULTI, AlertParams.this.mItemsProvider);
                    }

                    @Override // android.widget.CursorAdapter
                    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                        View viewInflate = AlertParams.this.mInflater.inflate(alertController.mMultiChoiceItemLayout, viewGroup, false);
                        CompatViewMethod.setForceDarkAllowed(viewInflate, false);
                        return viewInflate;
                    }
                };
            } else {
                if (this.mIsSingleChoice) {
                    i2 = alertController.mSingleChoiceItemLayout;
                    itemType = ItemType.CHOICE_SINGLE;
                } else {
                    i2 = alertController.mListItemLayout;
                    itemType = ItemType.DEFAULT;
                }
                int i3 = i2;
                final ItemType itemType2 = itemType;
                if (this.mCursor != null) {
                    listAdapter = new SimpleCursorAdapter(this.mContext, i3, this.mCursor, new String[]{this.mLabelColumn}, new int[]{R.id.text1}) { // from class: miuix.appcompat.app.AlertController.AlertParams.4
                        @Override // android.widget.CursorAdapter, android.widget.Adapter
                        public View getView(int i4, View view, ViewGroup viewGroup) {
                            View view2 = super.getView(i4, view, viewGroup);
                            if (view == null) {
                                AnimHelper.addPressAnim(view2);
                            }
                            AlertParams.setAccessibilityDelegate(view2, itemType2, AlertParams.this.mItemsProvider);
                            return view2;
                        }
                    };
                } else {
                    ListAdapter listAdapter2 = this.mAdapter;
                    listAdapter = listAdapter2;
                    if (listAdapter2 == null) {
                        CheckedItemAdapter checkedItemAdapter = new CheckedItemAdapter(this.mContext, i3, R.id.text1, this.mItems);
                        checkedItemAdapter.setItemsProvider(this.mItemsProvider);
                        checkedItemAdapter.setItemType(itemType2);
                        listAdapter = checkedItemAdapter;
                    }
                }
            }
            OnPrepareListViewListener onPrepareListViewListener = this.mOnPrepareListViewListener;
            if (onPrepareListViewListener != null) {
                onPrepareListViewListener.onPrepareListView(listView);
            }
            alertController.mAdapter = listAdapter;
            alertController.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.appcompat.app.AlertController.AlertParams.5
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j2) {
                        AlertParams.this.mOnClickListener.onClick(alertController.mDialog, i4);
                        if (AlertParams.this.mIsSingleChoice) {
                            return;
                        }
                        alertController.mDialog.dismiss();
                    }
                });
            } else if (this.mOnCheckboxClickListener != null) {
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.appcompat.app.AlertController.AlertParams.6
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j2) {
                        boolean[] zArr = AlertParams.this.mCheckedItems;
                        if (zArr != null) {
                            zArr[i4] = listView.isItemChecked(i4);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick(alertController.mDialog, i4, listView.isItemChecked(i4));
                    }
                });
            }
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
            if (onItemSelectedListener != null) {
                listView.setOnItemSelectedListener(onItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                listView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                listView.setChoiceMode(2);
            }
            alertController.mListView = listView;
        }

        private static AccessibilityDelegateCompat getDefaultAccessibilityDelegateCompat(final ItemType itemType) {
            return new AccessibilityDelegateCompat() { // from class: miuix.appcompat.app.AlertController.AlertParams.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    int i2 = AnonymousClass10.$SwitchMap$miuix$appcompat$app$AlertController$AlertParams$ItemType[itemType.ordinal()];
                    if (i2 == 1) {
                        accessibilityNodeInfoCompat.addAction(16);
                        return;
                    }
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                        accessibilityNodeInfoCompat.setClassName(CheckBox.class.getName());
                        accessibilityNodeInfoCompat.addAction(16);
                        return;
                    }
                    accessibilityNodeInfoCompat.setCheckable(true);
                    accessibilityNodeInfoCompat.setClassName(RadioButton.class.getName());
                    if (view instanceof miuix.androidbasewidget.widget.CheckedTextView) {
                        boolean zIsChecked = ((miuix.androidbasewidget.widget.CheckedTextView) view).isChecked();
                        accessibilityNodeInfoCompat.setChecked(zIsChecked);
                        accessibilityNodeInfoCompat.setClickable(!zIsChecked);
                        if (zIsChecked) {
                            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                        }
                    }
                }
            };
        }

        public static void setAccessibilityDelegate(View view, ItemType itemType, AccessibilityDelegateProvider accessibilityDelegateProvider) {
            AccessibilityDelegateCompat defaultAccessibilityDelegateCompat;
            if (accessibilityDelegateProvider != null) {
                defaultAccessibilityDelegateCompat = accessibilityDelegateProvider.getAccessibilityDelegate();
            } else {
                Log.i(AlertController.TAG, "type=" + itemType);
                defaultAccessibilityDelegateCompat = getDefaultAccessibilityDelegateCompat(itemType);
            }
            if (defaultAccessibilityDelegateCompat != null) {
                ViewCompat.setAccessibilityDelegate(view, defaultAccessibilityDelegateCompat);
            }
        }

        public void apply(AlertController alertController) {
            int i2;
            View view = this.mCustomTitleView;
            if (view != null) {
                alertController.setCustomTitle(view);
            } else {
                CharSequence charSequence = this.mTitle;
                if (charSequence != null) {
                    alertController.setTitle(charSequence);
                }
                Drawable drawable = this.mIcon;
                if (drawable != null) {
                    alertController.setIcon(drawable);
                }
                int i3 = this.mIconId;
                if (i3 != 0) {
                    alertController.setIcon(i3);
                }
                int i4 = this.mIconAttrId;
                if (i4 != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId(i4));
                }
                if (this.mSmallIcon) {
                    alertController.setUseSmallIcon(true);
                }
                int i5 = this.iconWidth;
                if (i5 != 0 && (i2 = this.iconHeight) != 0) {
                    alertController.setIconSize(i5, i2);
                }
            }
            CharSequence charSequence2 = this.mMessage;
            if (charSequence2 != null) {
                alertController.setMessage(charSequence2);
            }
            CharSequence charSequence3 = this.mComment;
            if (charSequence3 != null) {
                alertController.setComment(charSequence3);
            }
            CharSequence charSequence4 = this.mPositiveButtonText;
            if (charSequence4 != null) {
                alertController.setButton(-1, charSequence4, this.mPositiveButtonListener, null);
            }
            CharSequence charSequence5 = this.mNegativeButtonText;
            if (charSequence5 != null) {
                alertController.setButton(-2, charSequence5, this.mNegativeButtonListener, null);
            }
            CharSequence charSequence6 = this.mNeutralButtonText;
            if (charSequence6 != null) {
                alertController.setButton(-3, charSequence6, this.mNeutralButtonListener, null);
            }
            if (this.mExtraButtonList != null) {
                alertController.mExtraButtonList = new ArrayList(this.mExtraButtonList);
            }
            if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
                createListView(alertController);
            }
            View view2 = this.mView;
            if (view2 != null) {
                alertController.setView(view2);
            } else {
                int i6 = this.mViewLayoutResId;
                if (i6 != 0) {
                    alertController.setView(i6);
                }
            }
            CharSequence charSequence7 = this.mCheckBoxMessage;
            if (charSequence7 != null) {
                alertController.setCheckBox(this.mIsChecked, charSequence7);
            }
            alertController.mHapticFeedbackEnabled = this.mHapticFeedbackEnabled;
            alertController.setEnableImmersive(this.mEnableDialogImmersive);
            alertController.setNonImmersiveDialogHeight(this.mNonImmersiveDialogHeight);
            alertController.setLiteVersion(this.mLiteVersion);
            alertController.setPreferLandscape(this.mPreferLandscape);
            alertController.setButtonForceVertical(this.mButtonForceVertical);
            alertController.setPrimaryButtonFirstEnabled(this.mPrimaryButtonFirstEnabled);
            alertController.setAsyncInflatePanelEnable(this.mAsyncInflatePanelEnabled);
            alertController.setDiscardImeAnimEnabled(this.mDiscardImeAnimEnabled);
            alertController.setPanelSizeChangedListener(this.mPanelSizeChangedListener);
            alertController.setConfigurationChangedListener(this.mConfigurationChangedListener);
            alertController.setEnableEnterAnim(this.mEnableEnterAnim);
            alertController.setUseForceFlipCutout(this.mUseForceFlipCutout);
        }
    }

    public static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = -1651327837;
        private static final int MSG_RUN_ON_CLICK = -1651327821;
        private final WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<>(dialogInterface);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DialogInterface dialogInterface = this.mDialog.get();
            int i2 = message.what;
            if (i2 == MSG_DISMISS_DIALOG) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            } else {
                Object obj = message.obj;
                if (obj instanceof DialogInterface.OnClickListener) {
                    ((DialogInterface.OnClickListener) obj).onClick(dialogInterface, i2);
                }
            }
        }
    }

    public static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        private AccessibilityDelegateProvider mItemsProvider;
        private AlertParams.ItemType mType;

        public CheckedItemAdapter(Context context, int i2, int i3, CharSequence[] charSequenceArr) {
            super(context, i2, i3, charSequenceArr);
            this.mType = AlertParams.ItemType.DEFAULT;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            if (view == null) {
                AnimHelper.addPressAnim(view2);
            }
            AlertParams.setAccessibilityDelegate(view2, this.mType, this.mItemsProvider);
            return view2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }

        public void setItemType(AlertParams.ItemType itemType) {
            this.mType = itemType;
        }

        public void setItemsProvider(AccessibilityDelegateProvider accessibilityDelegateProvider) {
            this.mItemsProvider = accessibilityDelegateProvider;
        }
    }

    public static class LayoutChangeListener implements View.OnLayoutChangeListener {
        private final WeakReference<AlertController> mHost;
        private final Rect mCutoutInsets = new Rect();
        private final Rect mWindowVisibleFrame = new Rect();

        public LayoutChangeListener(AlertController alertController) {
            this.mHost = new WeakReference<>(alertController);
        }

        private void changeViewPadding(View view, int i2, int i3) {
            view.setPadding(i2, 0, i3, 0);
        }

        private void handleImeChange(View view, Rect rect, AlertController alertController) {
            int height = (view.getHeight() - alertController.getDialogPanelExtraBottomPadding()) - rect.bottom;
            if (height > 0) {
                int i2 = -height;
                WindowInsets rootWindowInsets = view.getRootWindowInsets();
                i = (rootWindowInsets != null ? rootWindowInsets.getInsets(WindowInsets.Type.systemBars()).bottom : 0) + i2;
                alertController.mDialogAnimHelper.cancelAnimator();
            }
            alertController.translateDialogPanel(i);
        }

        private void handleMultiWindowLandscapeChange(AlertController alertController, int i2) {
            if (!MiuixUIUtils.isInMultiWindowMode(alertController.mContext)) {
                DialogRootView dialogRootView = alertController.mDialogRootView;
                if (dialogRootView.getPaddingLeft() > 0 || dialogRootView.getPaddingRight() > 0) {
                    changeViewPadding(dialogRootView, 0, 0);
                    return;
                }
                return;
            }
            boolean z2 = Build.VERSION.SDK_INT >= 36;
            Rect rect = this.mCutoutInsets;
            boolean z3 = (rect.left > 0 || rect.right > 0) && z2;
            Rect rect2 = this.mWindowVisibleFrame;
            if (rect2.left <= 0 || z3) {
                changeViewPadding(alertController.mDialogRootView, 0, 0);
                return;
            }
            int iWidth = i2 - rect2.width();
            if (this.mWindowVisibleFrame.right == i2) {
                changeViewPadding(alertController.mDialogRootView, iWidth, 0);
            } else {
                changeViewPadding(alertController.mDialogRootView, 0, iWidth);
            }
        }

        private void updateCutoutInsets(View view, Rect rect) {
            Insets insets;
            WindowInsets rootWindowInsets = view != null ? view.getRootWindowInsets() : null;
            if (rootWindowInsets == null || (insets = rootWindowInsets.getInsets(WindowInsets.Type.displayCutout())) == null) {
                return;
            }
            rect.left = insets.left;
            rect.top = insets.top;
            rect.right = insets.right;
            rect.bottom = insets.bottom;
        }

        public boolean hasNavigationBarHeightInMultiWindowMode() {
            WindowUtils.getScreenSize(this.mHost.get().mContext, this.mHost.get().mScreenRealSize);
            Rect rect = this.mWindowVisibleFrame;
            return (rect.left == 0 && rect.right == this.mHost.get().mScreenRealSize.x && this.mWindowVisibleFrame.top <= EnvStateManager.getStatusBarHeight(this.mHost.get().mContext, false)) ? false : true;
        }

        public boolean isInMultiScreenTop() {
            AlertController alertController = this.mHost.get();
            if (alertController == null) {
                return false;
            }
            WindowUtils.getScreenSize(alertController.mContext, alertController.mScreenRealSize);
            Rect rect = this.mWindowVisibleFrame;
            if (rect.left != 0 || rect.right != alertController.mScreenRealSize.x) {
                return false;
            }
            int i2 = (int) (alertController.mScreenRealSize.y * 0.75f);
            Rect rect2 = this.mWindowVisibleFrame;
            return rect2.top >= 0 && rect2.bottom <= i2;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            AlertController alertController = this.mHost.get();
            if (alertController != null) {
                view.getWindowVisibleDisplayFrame(this.mWindowVisibleFrame);
                updateCutoutInsets(view, this.mCutoutInsets);
                handleMultiWindowLandscapeChange(alertController, i4);
            }
        }

        public void updateLayout(View view) {
            AlertController alertController = this.mHost.get();
            if (alertController != null) {
                view.getWindowVisibleDisplayFrame(this.mWindowVisibleFrame);
                updateCutoutInsets(view, this.mCutoutInsets);
                handleMultiWindowLandscapeChange(alertController, view.getWidth());
            }
        }
    }

    public AlertController(Context context, AppCompatDialog appCompatDialog, Window window) {
        DialogDisplayStrategy dialogDisplayStrategy = new DialogDisplayStrategy();
        this.mDisplayStrategy = dialogDisplayStrategy;
        this.mPanelFixedWidth = -1;
        this.mPanelFixedHeight = -1;
        this.mPanelFixedSizeEnabled = false;
        this.mDefaultButtonsTextWatcher = new TextWatcher() { // from class: miuix.appcompat.app.AlertController.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (AlertController.this.mParentPanel != null) {
                    DialogParentPanel2 dialogParentPanel2 = AlertController.this.mParentPanel;
                    int i2 = miuix.appcompat.R.id.buttonPanel;
                    if (dialogParentPanel2.findViewById(i2) != null) {
                        AlertController.this.mParentPanel.findViewById(i2).requestLayout();
                    }
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        };
        this.mIconId = 0;
        this.mCustomTitleTextView = null;
        this.mCheckedItem = -1;
        this.mDialogAnimHelper = new DialogAnimHelper();
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        this.mScreenOrientation = 0;
        this.mTitleTextSize = 18.0f;
        this.mMessageTextSize = 16.0f;
        this.mCommentTextSize = 13.0f;
        this.mCustomTitleTextSize = 18.0f;
        this.mRootViewSize = new Point();
        this.mRootViewSizeDp = new Point();
        this.mScreenRealSize = new Point();
        this.mDisplayCutoutSafeInsets = new Rect();
        this.mHasPendingDismiss = false;
        this.mUseForceFlipCutout = false;
        this.mNavigationBarHiddenEnabled = false;
        this.mIsAssociatedActivityHideNavigationBar = false;
        this.mShowAnimListenerWrapper = new AnonymousClass2();
        this.mPrimaryButtonFirstEnabled = false;
        this.mDiscardImeAnimEnabled = false;
        this.mButtonHandler = new View.OnClickListener() { // from class: miuix.appcompat.app.AlertController.3
            /* JADX WARN: Removed duplicated region for block: B:43:0x0093  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.view.View r6) {
                /*
                    r5 = this;
                    int r0 = miuix.view.HapticFeedbackConstants.MIUI_TAP_LIGHT
                    miuix.appcompat.app.AlertController r1 = miuix.appcompat.app.AlertController.this
                    android.widget.Button r2 = r1.mButtonPositive
                    r3 = 0
                    if (r6 != r2) goto L14
                    android.os.Message r0 = r1.mButtonPositiveMessage
                    if (r0 == 0) goto L11
                    android.os.Message r3 = android.os.Message.obtain(r0)
                L11:
                    int r0 = miuix.view.HapticFeedbackConstants.MIUI_TAP_NORMAL
                    goto L76
                L14:
                    android.widget.Button r2 = r1.mButtonNegative
                    if (r6 != r2) goto L21
                    android.os.Message r1 = r1.mButtonNegativeMessage
                    if (r1 == 0) goto L76
                    android.os.Message r3 = android.os.Message.obtain(r1)
                    goto L76
                L21:
                    android.widget.Button r2 = r1.mButtonNeutral
                    if (r6 != r2) goto L2e
                    android.os.Message r1 = r1.mButtonNeutralMessage
                    if (r1 == 0) goto L76
                    android.os.Message r3 = android.os.Message.obtain(r1)
                    goto L76
                L2e:
                    java.util.List r1 = miuix.appcompat.app.AlertController.access$500(r1)
                    if (r1 == 0) goto L67
                    miuix.appcompat.app.AlertController r1 = miuix.appcompat.app.AlertController.this
                    java.util.List r1 = miuix.appcompat.app.AlertController.access$500(r1)
                    boolean r1 = r1.isEmpty()
                    if (r1 != 0) goto L67
                    miuix.appcompat.app.AlertController r1 = miuix.appcompat.app.AlertController.this
                    java.util.List r1 = miuix.appcompat.app.AlertController.access$500(r1)
                    java.util.Iterator r1 = r1.iterator()
                L4a:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto L67
                    java.lang.Object r2 = r1.next()
                    miuix.appcompat.app.AlertController$ButtonInfo r2 = (miuix.appcompat.app.AlertController.ButtonInfo) r2
                    miuix.internal.widget.GroupButton r4 = miuix.appcompat.app.AlertController.ButtonInfo.access$600(r2)
                    if (r6 != r4) goto L4a
                    android.os.Message r1 = miuix.appcompat.app.AlertController.ButtonInfo.access$700(r2)
                    if (r1 == 0) goto L66
                    android.os.Message r1 = android.os.Message.obtain(r1)
                L66:
                    r3 = r1
                L67:
                    boolean r1 = r6 instanceof miuix.internal.widget.GroupButton
                    if (r1 == 0) goto L76
                    r1 = r6
                    miuix.internal.widget.GroupButton r1 = (miuix.internal.widget.GroupButton) r1
                    boolean r1 = r1.isPrimary()
                    if (r1 == 0) goto L76
                    int r0 = miuix.view.HapticFeedbackConstants.MIUI_TAP_NORMAL
                L76:
                    int r1 = miuix.view.HapticFeedbackConstants.MIUI_BUTTON_MIDDLE
                    miuix.view.HapticCompat.performHapticFeedbackAsync(r6, r1, r0)
                    if (r3 == 0) goto L93
                    r3.sendToTarget()
                    android.os.Bundle r6 = r3.getData()
                    if (r6 == 0) goto L93
                    java.lang.String r0 = "BUTTON_CLICK_AUTO_DISMISSIBLE"
                    boolean r1 = r6.containsKey(r0)
                    if (r1 == 0) goto L93
                    boolean r6 = r6.getBoolean(r0)
                    goto L94
                L93:
                    r6 = 1
                L94:
                    miuix.appcompat.app.AlertController r5 = miuix.appcompat.app.AlertController.this
                    android.os.Handler r5 = r5.mHandler
                    if (r6 == 0) goto L9e
                    r6 = -1651327837(0xffffffff9d92bca3, float:-3.8840924E-21)
                    goto La1
                L9e:
                    r6 = -1651327821(0xffffffff9d92bcb3, float:-3.884099E-21)
                La1:
                    r5.sendEmptyMessage(r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.AlertController.AnonymousClass3.onClick(android.view.View):void");
            }
        };
        this.mInsetsAnimationPlayed = false;
        dialogDisplayStrategy.setPanelBehavior(new DialogPanelBehaviorImpl()).setButtonBehavior(new DialogButtonBehaviorImpl());
        this.mContext = context;
        this.mCurrentDensityDpi = context.getResources().getConfiguration().densityDpi;
        this.mDialog = appCompatDialog;
        this.mWindow = window;
        this.mEnableEnterAnim = true;
        this.mNonImmersiveDialogShowAnimDuration = context.getResources().getInteger(miuix.appcompat.R.integer.dialog_enter_duration);
        this.mHandler = new ButtonHandler(appCompatDialog);
        this.mLayoutChangeListener = new LayoutChangeListener(this);
        this.mIsFlipTinyScreen = miuix.os.Build.IS_FLIP && DeviceHelper.isTinyScreen(context);
        this.mIsEnableImmersive = (LiteUtils.isCommonLiteStrategy() || this.mIsFlipTinyScreen) ? false : true;
        updateDisplayInfo(context);
        initScreenMinorSize(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, miuix.appcompat.R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.AlertDialog_layout, 0);
        this.mListLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.AlertDialog_listItemLayout, 0);
        this.mShowTitle = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.AlertDialog_showTitle, true);
        typedArrayObtainStyledAttributes.recycle();
        appCompatDialog.supportRequestWindowFeature(1);
        updateDimensConfig(context.getResources());
        this.mMarkLandscape = context.getResources().getBoolean(miuix.appcompat.R.bool.treat_as_land);
        this.mCreateThread = Thread.currentThread();
        isDialogImeDebugEnabled();
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "create Dialog mCurrentDensityDpi " + this.mCurrentDensityDpi);
        }
    }

    private void adjustHeight2WrapContent() {
        ViewGroup.LayoutParams layoutParams = this.mListView.getLayoutParams();
        layoutParams.height = -2;
        this.mListView.setLayoutParams(layoutParams);
    }

    private boolean buttonNeedScrollable(DialogButtonPanel dialogButtonPanel, int i2) {
        int buttonFullyVisibleHeight = dialogButtonPanel.getButtonFullyVisibleHeight();
        ViewGroup viewGroup = (ViewGroup) this.mParentPanel.findViewById(miuix.appcompat.R.id.topPanel);
        int height = viewGroup != null ? viewGroup.getHeight() : 0;
        int i3 = EnvStateManager.getWindowSize(this.mContext).y;
        boolean z2 = MiuixUIUtils.getFontLevel(this.mContext) == 2;
        DialogContract.ButtonScrollSpec buttonScrollSpec = new DialogContract.ButtonScrollSpec();
        buttonScrollSpec.updateData(buttonFullyVisibleHeight, dialogButtonPanel.getHeight(), i3, height, this.mIsFlipTinyScreen, getScreenOrientation(), i2, this.mRootViewSizeDp.y, z2, this.mListView != null);
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "buttonNeedScrollable: buttonScrollSpec = " + buttonScrollSpec);
        }
        return ifNeedMoveButtonToContentPanel(dialogButtonPanel, viewGroup) || this.mDisplayStrategy.isButtonScrollable(buttonScrollSpec);
    }

    public static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (canTextInput(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    private void changeTitlePadding(TextView textView) {
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), 0);
    }

    private void checkAndClearFocus() {
        View currentFocus = this.mWindow.getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }
    }

    private boolean checkThread() {
        return this.mCreateThread == Thread.currentThread();
    }

    @RequiresApi(api = 30)
    private void cleanWindowInsetsAnimation() {
        if (this.mSetupWindowInsetsAnimation) {
            this.mWindow.getDecorView().setWindowInsetsAnimationCallback(null);
            this.mWindow.getDecorView().setOnApplyWindowInsetsListener(null);
            this.mSetupWindowInsetsAnimation = false;
        }
    }

    private void clearCustomContent() {
        View view = this.mInflatedView;
        if (view != null && view.getParent() != null) {
            safeRemoveFromParent(this.mInflatedView);
            this.mInflatedView = null;
        }
        View view2 = this.mView;
        if (view2 == null || view2.getParent() == null) {
            return;
        }
        safeRemoveFromParent(this.mView);
        this.mView = null;
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

    private int computeParentPanelMaxHeight() {
        boolean zIsPortrait = WindowUtils.isPortrait(this.mContext);
        boolean z2 = this.mIsFlipTinyScreen || this.mIsInFreeForm;
        Point screenSize = EnvStateManager.getScreenSize(this.mContext);
        int iPx2dp = MiuixUIUtils.px2dp(this.mContext, screenSize.y);
        if (z2) {
            return screenSize.y;
        }
        if (zIsPortrait) {
            return (int) (screenSize.y * 0.7f);
        }
        return (int) (iPx2dp >= 500 ? screenSize.y * 0.7f : screenSize.y * 0.9f);
    }

    private void disableForceDark(View view) {
        CompatViewMethod.setForceDarkAllowed(view, false);
    }

    private Insets getAssociatedActivityInsets(int i2) {
        WindowInsets rootWindowInsets;
        Activity associatedActivity = ((AlertDialog) this.mDialog).getAssociatedActivity();
        if (associatedActivity == null || (rootWindowInsets = associatedActivity.getWindow().getDecorView().getRootWindowInsets()) == null) {
            return null;
        }
        return rootWindowInsets.getInsets(i2);
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

    private Point getAvailableWindowSizeDp(@Nullable WindowInsets windowInsets) {
        Point point = new Point();
        Point point2 = this.mRootViewSizeDp;
        int i2 = point2.x;
        int i3 = point2.y;
        new Rect();
        Rect naviBarInsets = getNaviBarInsets(windowInsets, true);
        if (this.mIsFlipTinyScreen) {
            Rect guaranteedCutout = getGuaranteedCutout(windowInsets, true);
            i2 -= guaranteedCutout.left + guaranteedCutout.right;
            i3 -= guaranteedCutout.top + guaranteedCutout.bottom;
        } else if (!isDialogImmersive()) {
            Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.displayCutout());
            if (associatedActivityInsets != null) {
                Rect rectPx2dp = px2dp(new Rect(associatedActivityInsets.left, associatedActivityInsets.top, associatedActivityInsets.right, associatedActivityInsets.bottom));
                i2 -= rectPx2dp.left + rectPx2dp.right;
                i3 -= rectPx2dp.top + rectPx2dp.bottom;
            }
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "getAvailableWindowSizeDp: cutoutInsets = " + associatedActivityInsets);
            }
        }
        int i4 = i2 - (naviBarInsets.left + naviBarInsets.right);
        int i5 = i3 - (naviBarInsets.top + naviBarInsets.bottom);
        point.x = i4;
        point.y = i5;
        return point;
    }

    private int getCutoutMode(int i2, int i3) {
        return i3 == 0 ? i2 == 2 ? 2 : 1 : i3;
    }

    @RequiresApi(api = 30)
    private DisplayCutout getCutoutSafely() {
        if (showSystemAlertInFlip() && this.mFlipCutout != null) {
            printDebugMsg("getCutoutSafely", "show system alert in flip, use displayCutout by reflect, displayCutout = " + this.mFlipCutout, false);
            return this.mFlipCutout;
        }
        try {
            DisplayCutout cutout = this.mContext.getDisplay().getCutout();
            printDebugMsg("getCutoutSafely", "get displayCutout from context = " + cutout, false);
            return cutout;
        } catch (Exception e2) {
            Log.e(TAG, "context is not associated display info, please check the type of context, the exception info = " + Log.getStackTraceString(e2));
            WindowManager windowManager = this.mWindowManager;
            Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
            if (defaultDisplay != null) {
                return defaultDisplay.getCutout();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDialogPanelExtraBottomPadding() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDialogPanelMargin() {
        int[] iArr = new int[2];
        this.mParentPanel.getLocationInWindow(iArr);
        if (this.mExtraImeMargin == -1) {
            this.mExtraImeMargin = this.mDimensConfig.extraImeMargin;
        }
        return (this.mWindow.getDecorView().getHeight() - (iArr[1] + this.mParentPanel.getHeight())) - this.mExtraImeMargin;
    }

    @RequiresApi(api = 30)
    private Rect getDisplayCutout(boolean z2) {
        Rect rect = new Rect();
        Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.displayCutout());
        if (associatedActivityInsets != null) {
            rect.set(associatedActivityInsets.left, associatedActivityInsets.top, associatedActivityInsets.right, associatedActivityInsets.bottom);
            printDebugMsg("getDisplayCutout", "get cutout from host, cutout = " + rect.flattenToString(), false);
        } else {
            DisplayCutout cutoutSafely = getCutoutSafely();
            rect.left = cutoutSafely != null ? cutoutSafely.getSafeInsetLeft() : 0;
            rect.top = cutoutSafely != null ? cutoutSafely.getSafeInsetTop() : 0;
            rect.right = cutoutSafely != null ? cutoutSafely.getSafeInsetRight() : 0;
            rect.bottom = cutoutSafely != null ? cutoutSafely.getSafeInsetBottom() : 0;
        }
        return z2 ? px2dp(rect) : rect;
    }

    private void getFlipCutout() {
        if (this.mIsFlipTinyScreen) {
            try {
                Display display = this.mContext.getDisplay();
                if (display != null) {
                    this.mFlipCutout = (DisplayCutout) ReflectionHelper.getMethod(display.getClass(), "getFlipFoldedCutout", new Class[0]).invoke(display, null);
                } else {
                    this.mFlipCutout = null;
                }
            } catch (Exception unused) {
                printDebugMsg("getFlipCutout", "can't reflect from display to query cutout", false);
                this.mFlipCutout = null;
            }
        }
    }

    private int getFreeFormAvoidSpace(@Nullable Rect rect) {
        int i2;
        int i3;
        if (rect != null) {
            i3 = rect.top;
            i2 = rect.bottom;
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (i3 == 0 || i2 == 0) {
            Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.captionBar());
            i3 = associatedActivityInsets != null ? associatedActivityInsets.top : 0;
            i2 = associatedActivityInsets != null ? associatedActivityInsets.bottom : 0;
        }
        if (i3 == 0) {
            if (isTablet()) {
                i3 = this.mDimensConfig.freeTabletCompactHeight;
            } else {
                DialogContract.DimensConfig dimensConfig = this.mDimensConfig;
                i3 = dimensConfig.freePhoneCompactHeight + dimensConfig.extraImeMargin;
            }
        }
        if (i2 == 0) {
            if (isTablet()) {
                i2 = this.mDimensConfig.freeTabletCompactHeight;
            } else {
                DialogContract.DimensConfig dimensConfig2 = this.mDimensConfig;
                i2 = dimensConfig2.freePhoneCompactHeight + dimensConfig2.extraImeMargin;
            }
        }
        return i3 + i2;
    }

    private int getGravity() {
        return isTablet() ? 17 : 81;
    }

    private Rect getGuaranteedCutout(@Nullable WindowInsets windowInsets, boolean z2) {
        Rect rect = new Rect();
        if (windowInsets == null) {
            return getDisplayCutout(z2);
        }
        Insets insets = windowInsets.getInsets(WindowInsets.Type.displayCutout());
        rect.set(insets.left, insets.top, insets.right, insets.bottom);
        if (!z2) {
            return rect;
        }
        px2dp(rect);
        return rect;
    }

    @RequiresApi(api = 30)
    private int getImeBottomByWindowInsets(@Nullable WindowInsets windowInsets) {
        if (windowInsets == null) {
            windowInsets = this.mWindow.getDecorView().getRootWindowInsets();
        }
        if (windowInsets != null) {
            Insets insets = windowInsets.getInsets(WindowInsets.Type.ime());
            if (insets != null) {
                return insets.bottom;
            }
            return 0;
        }
        Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.ime());
        if (associatedActivityInsets != null) {
            return associatedActivityInsets.bottom;
        }
        return 0;
    }

    @RequiresApi(api = 30)
    private Rect getNaviBarInsets(@Nullable WindowInsets windowInsets, boolean z2) {
        Rect rect = new Rect();
        if (windowInsets == null) {
            windowInsets = this.mDialogRootView.getRootWindowInsets();
        }
        if (windowInsets != null) {
            Insets insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
            rect.set(insetsIgnoringVisibility.left, insetsIgnoringVisibility.top, insetsIgnoringVisibility.right, insetsIgnoringVisibility.bottom);
            return z2 ? px2dp(rect) : rect;
        }
        Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.navigationBars());
        if (associatedActivityInsets != null) {
            rect.set(associatedActivityInsets.left, associatedActivityInsets.top, associatedActivityInsets.right, associatedActivityInsets.bottom);
            return z2 ? px2dp(rect) : rect;
        }
        int navigationBarHeight = EnvStateManager.getNavigationBarHeight(this.mContext, z2);
        int rotationSafely = getRotationSafely();
        if (rotationSafely == 1) {
            rect.right = navigationBarHeight;
        } else if (rotationSafely == 2) {
            rect.top = navigationBarHeight;
        } else if (rotationSafely != 3) {
            rect.bottom = navigationBarHeight;
        } else {
            rect.left = navigationBarHeight;
        }
        return rect;
    }

    private int getPanelMaxLimitHeight(@Nullable Rect rect) {
        int i2;
        int i3;
        int i4 = EnvStateManager.getWindowSize(this.mContext).y;
        int i5 = this.mIsFlipTinyScreen ? this.mDimensConfig.widthSmallMargin : this.mDimensConfig.extraImeMargin;
        if (rect != null) {
            i3 = rect.top;
            i2 = rect.bottom;
        } else {
            Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.systemBars());
            int i6 = associatedActivityInsets != null ? associatedActivityInsets.top : 0;
            i2 = associatedActivityInsets != null ? associatedActivityInsets.bottom : 0;
            i3 = i6;
        }
        int iMax = (i4 - Math.max(i3, i5)) - (i2 + i5);
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "getPanelMaxLimitHeight: boundInset = " + rect + ", topInset = " + i3 + ", bottomInset = " + i2 + ", windowHeight = " + i4 + ", verticalMargin = " + i5 + ", panelMaxLimitHeight = " + iMax);
        }
        if (this.mIsInFreeForm) {
            iMax = i4 - getFreeFormAvoidSpace(rect);
        }
        return this.mIsFlipTinyScreen ? i4 - (Math.max(i3, EnvStateManager.getStatusBarHeight(this.mContext, false)) + i5) : iMax;
    }

    @RequiresApi(api = 30)
    private int getRotationSafely() {
        try {
            return this.mContext.getDisplay().getRotation();
        } catch (Exception e2) {
            Log.e(TAG, "context is not associated display info, please check the type of context, the exception info = " + Log.getStackTraceString(e2));
            WindowManager windowManager = this.mWindowManager;
            Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
            if (defaultDisplay != null) {
                return defaultDisplay.getRotation();
            }
            return 0;
        }
    }

    private int getScreenOrientation() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null) {
            return 0;
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        return (rotation == 1 || rotation == 3) ? 2 : 1;
    }

    private int getVerticalAvoidSpace() {
        Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.captionBar());
        int i2 = associatedActivityInsets != null ? associatedActivityInsets.top : 0;
        int i3 = associatedActivityInsets != null ? associatedActivityInsets.bottom : 0;
        DialogContract.DimensConfig dimensConfig = this.mDimensConfig;
        int i4 = dimensConfig.freeTabletCompactHeight;
        int i5 = dimensConfig.freePhoneCompactHeight;
        int i6 = dimensConfig.extraImeMargin;
        if (i2 == 0) {
            i2 = isTablet() ? i4 : i5 + i6;
        }
        if (i3 == 0) {
            i3 = isTablet() ? i4 : i5 + i6;
        }
        return i2 + i3;
    }

    private int getVisibleButtonCount() {
        Button button = this.mButtonPositive;
        int i2 = 1;
        if (button == null) {
            i2 = 1 ^ (TextUtils.isEmpty(this.mButtonPositiveText) ? 1 : 0);
        } else if (button.getVisibility() != 0) {
            i2 = 0;
        }
        Button button2 = this.mButtonNegative;
        if (button2 == null ? !TextUtils.isEmpty(this.mButtonNegativeText) : button2.getVisibility() == 0) {
            i2++;
        }
        Button button3 = this.mButtonNeutral;
        if (button3 == null ? !TextUtils.isEmpty(this.mButtonNeutralText) : button3.getVisibility() == 0) {
            i2++;
        }
        List<ButtonInfo> list = this.mExtraButtonList;
        if (list != null && !list.isEmpty()) {
            Iterator<ButtonInfo> it = this.mExtraButtonList.iterator();
            while (it.hasNext()) {
                GroupButton groupButton = it.next().mButton;
                if (groupButton == null || groupButton.getVisibility() == 0) {
                    i2++;
                }
            }
        }
        return i2;
    }

    private void hideSoftIME() {
        InputMethodManager inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(this.mContext, InputMethodManager.class);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mParentPanel.getWindowToken(), 0);
        }
    }

    private boolean ifNeedMoveButtonToContentPanel(DialogButtonPanel dialogButtonPanel, ViewGroup viewGroup) {
        int height;
        DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
        if (dialogParentPanel2 == null || dialogButtonPanel == null || viewGroup == null || this.mLandscapePanel || (height = dialogParentPanel2.getHeight()) == 0) {
            return false;
        }
        int paddingTop = this.mParentPanel.getPaddingTop();
        int paddingBottom = this.mParentPanel.getPaddingBottom();
        int height2 = viewGroup.getHeight();
        int height3 = dialogButtonPanel.getHeight();
        ViewGroup.LayoutParams layoutParams = dialogButtonPanel.getLayoutParams();
        int i2 = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).topMargin : 0;
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "ifNeedMoveButtonToContentPanel: topPanelHeight = " + height2 + ", buttonPanelHeight = " + height3 + ", buttonPanelMarginTop = " + i2 + ", parentPanelPaddingTop = " + paddingTop + ", parentPanelPaddingBottom = " + paddingBottom + ", parentPanelHeight = " + height);
        }
        return (((height2 + height3) + i2) + paddingTop) + paddingBottom > height;
    }

    private void inflateDialogLayout() {
        this.mLandscapePanel = false;
        int i2 = miuix.appcompat.R.layout.miuix_appcompat_alert_dialog_content;
        if (this.mPreferLandscape && shouldUseLandscapePanel()) {
            i2 = miuix.appcompat.R.layout.miuix_appcompat_alert_dialog_content_land;
            this.mLandscapePanel = true;
        }
        if (this.mDialogContentLayout != i2) {
            this.mDialogContentLayout = i2;
            DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
            if (dialogParentPanel2 != null) {
                this.mDialogRootView.removeView(dialogParentPanel2);
            }
            if (this.mAsyncInflatePanelEnabled) {
                this.mParentPanel = (DialogParentPanel2) AsyncInflateLayoutManager.getInstance().inflateViewById(Integer.valueOf(this.mDialogContentLayout), this.mContext, this.mDialogRootView, false);
                if (this.mIsDebugEnabled) {
                    Log.w(TAG, "inflateDialogLayout: parentPanel.getParent = " + this.mParentPanel.getParent());
                    Log.w(TAG, "inflateDialogLayout: mParentPanel.getTag = " + this.mParentPanel.getTag());
                }
                DialogParentPanel2 dialogParentPanel22 = this.mParentPanel;
                if (dialogParentPanel22 != null && dialogParentPanel22.getParent() != null) {
                    this.mParentPanel = (DialogParentPanel2) LayoutInflater.from(this.mContext).inflate(this.mDialogContentLayout, (ViewGroup) this.mDialogRootView, false);
                }
            } else {
                this.mParentPanel = (DialogParentPanel2) LayoutInflater.from(this.mContext).inflate(this.mDialogContentLayout, (ViewGroup) this.mDialogRootView, false);
            }
            if (this.mPanelFixedSizeEnabled) {
                this.mParentPanel.setPanelFixedWidth(this.mPanelFixedWidth);
                this.mParentPanel.setPanelFixedHeight(this.mPanelFixedHeight);
            }
            this.mParentPanel.setIsInTinyScreen(this.mIsFlipTinyScreen);
            this.mParentPanel.setIsDebugEnabled(this.mIsDebugEnabled);
            this.mParentPanel.setPanelMaxLimitHeight(getPanelMaxLimitHeight(null));
            this.mDialogRootView.addView(this.mParentPanel);
        }
    }

    private void initScreenMinorSize(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        updateMinorScreenSize();
    }

    private boolean isCancelable() {
        return this.mCancelable;
    }

    private boolean isCanceledOnTouchOutside() {
        return this.mCanceledOnTouchOutside;
    }

    private boolean isConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = this.configurationAfterInstalled;
        return (configuration2.uiMode != configuration.uiMode) || (configuration2.screenLayout != configuration.screenLayout) || (configuration2.orientation != configuration.orientation) || (configuration2.screenWidthDp != configuration.screenWidthDp) || (configuration2.screenHeightDp != configuration.screenHeightDp) || ((configuration2.fontScale > configuration.fontScale ? 1 : (configuration2.fontScale == configuration.fontScale ? 0 : -1)) != 0) || (configuration2.smallestScreenWidthDp != configuration.smallestScreenWidthDp) || (configuration2.keyboard != configuration.keyboard);
    }

    private boolean isDialogImeDebugEnabled() {
        String str = "";
        try {
            String str2 = SystemProperties.get("log.tag.alertdialog.ime.debug.enable");
            if (str2 != null) {
                str = str2;
            }
        } catch (Exception e2) {
            Log.i(TAG, "can not access property log.tag.alertdialog.ime.enable, undebugable", e2);
        }
        Log.d(TAG, "Alert dialog ime debugEnable = " + str);
        boolean zEquals = TextUtils.equals(com.xiaomi.onetrack.util.a.f3424i, str);
        this.mIsDebugEnabled = zEquals;
        return zEquals;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFreeFormMode() {
        return EnvStateManager.isFreeFormMode(this.mContext);
    }

    @Deprecated
    private boolean isMiuiLegacyNotch() {
        Class<?> cls = ReflectUtil.getClass("android.os.SystemProperties");
        Class cls2 = Integer.TYPE;
        return ((Integer) ReflectUtil.callStaticObjectMethod(cls, cls2, "getInt", new Class[]{String.class, cls2}, "ro.miui.notch", 0)).intValue() == 1;
    }

    private boolean isNeedUpdateDialogPanelTranslationY() {
        boolean zIsInMultiWindowMode = MiuixUIUtils.isInMultiWindowMode(this.mContext);
        int i2 = this.mContext.getResources().getConfiguration().keyboard;
        boolean z2 = i2 == 2 || i2 == 3;
        boolean z3 = miuix.os.Build.IS_TABLET;
        byte b2 = (!zIsInMultiWindowMode || isFreeFormMode()) ? (byte) -1 : DeviceHelper.isWideScreen(this.mContext) ? (byte) 0 : (byte) 1;
        if (this.mIsDialogAnimating) {
            if ((!z3 || !z2) && b2 == 0) {
                return true;
            }
        } else if ((!z3 || !z2) && this.mSetupWindowInsetsAnimation && (this.mInsetsAnimationPlayed || zIsInMultiWindowMode)) {
            return true;
        }
        return false;
    }

    @RequiresApi(api = 28)
    private boolean isNotch(WindowInsets windowInsets) {
        DisplayCutout displayCutout;
        List<Rect> boundingRects;
        return (windowInsets == null || (displayCutout = windowInsets.getDisplayCutout()) == null || (boundingRects = displayCutout.getBoundingRects()) == null || boundingRects.size() <= 0) ? false : true;
    }

    private boolean isSpecifiedDialogHeight() {
        return (isDialogImmersive() || this.mNonImmersiveDialogHeight == -2) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTablet() {
        return miuix.os.Build.IS_TABLET || this.mIsCarWithScreen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setCustomPanelSize$3(ViewGroup.LayoutParams layoutParams) {
        this.mParentPanel.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupView$0(View view) {
        if (isCancelable() && isCanceledOnTouchOutside()) {
            this.mDialog.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupView$1(View view) {
        if (isCancelable() && isCanceledOnTouchOutside()) {
            hideSoftIME();
            this.mDialog.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupView$2() {
        AlertDialog.OnPanelSizeChangedListener onPanelSizeChangedListener = this.mPanelSizeChangedListener;
        if (onPanelSizeChangedListener != null) {
            onPanelSizeChangedListener.onPanelSizeChanged((AlertDialog) this.mDialog, this.mParentPanel);
        }
    }

    private boolean listViewIsNeedFullScroll() {
        int singleItemMinHeight = getSingleItemMinHeight();
        int count = this.mAdapter.getCount() * singleItemMinHeight;
        boolean z2 = MiuixUIUtils.getFontLevel(this.mContext) == 2;
        int iComputeParentPanelMaxHeight = computeParentPanelMaxHeight();
        if (this.mIsDebugEnabled) {
            Log.i(TAG, "listViewIsNeedFullScroll: itemsMinHeight = " + count + ", singleItemMinHeight = " + singleItemMinHeight + ", panelMaxHeight = " + iComputeParentPanelMaxHeight);
        }
        if (!z2 || iComputeParentPanelMaxHeight <= 0) {
            return count > ((int) (((float) this.mRootViewSize.y) * 0.35f));
        }
        float f2 = (count * 1.0f) / iComputeParentPanelMaxHeight;
        if (this.mIsDebugEnabled) {
            Log.i(TAG, "listViewIsNeedFullScroll: radioInMaxHeight = " + f2);
        }
        return f2 >= 0.3f;
    }

    private void onLayoutReload() {
        ((AlertDialog) this.mDialog).onLayoutReload();
        AlertDialog.OnDialogLayoutReloadListener onDialogLayoutReloadListener = this.mLayoutReloadListener;
        if (onDialogLayoutReloadListener != null) {
            onDialogLayoutReloadListener.onLayoutReload();
        }
    }

    private void printDebugMsg(String str, String str2, boolean z2) {
        if (this.mIsDebugEnabled || z2) {
            Log.d(TAG, str + ": " + str2);
        }
    }

    private Rect px2dp(Rect rect) {
        float f2 = this.mContext.getResources().getDisplayMetrics().densityDpi / 160.0f;
        rect.left = MiuixUIUtils.px2dp(f2, rect.left);
        rect.top = MiuixUIUtils.px2dp(f2, rect.top);
        rect.right = MiuixUIUtils.px2dp(f2, rect.right);
        rect.bottom = MiuixUIUtils.px2dp(f2, rect.bottom);
        return rect;
    }

    private void reInitLandConfig() {
        this.mMarkLandscape = this.mContext.getResources().getBoolean(miuix.appcompat.R.bool.treat_as_land);
        updateMinorScreenSize();
    }

    private void recordButtonTypeForOS3Display(ViewGroup viewGroup) {
        if (RomUtils.getHyperOsVersion() > 2 || this.mPrimaryButtonFirstEnabled) {
            boolean z2 = viewGroup instanceof DialogButtonPanel;
            if (z2 && (this.mButtonPositive instanceof GroupButton)) {
                DialogButtonPanel dialogButtonPanel = (DialogButtonPanel) viewGroup;
                dialogButtonPanel.clearPrimaryStyleButtonList();
                dialogButtonPanel.addPrimaryStyleButtons((GroupButton) this.mButtonPositive);
            }
            if (z2 && (this.mButtonNegative instanceof GroupButton)) {
                DialogButtonPanel dialogButtonPanel2 = (DialogButtonPanel) viewGroup;
                dialogButtonPanel2.clearNegativeStyleButtonList();
                dialogButtonPanel2.addNegativeStyleButtons((GroupButton) this.mButtonNegative);
            }
            if (z2 && (this.mButtonNeutral instanceof GroupButton)) {
                DialogButtonPanel dialogButtonPanel3 = (DialogButtonPanel) viewGroup;
                dialogButtonPanel3.clearNeutralStyleButtonList();
                dialogButtonPanel3.addNeutralStyleButtons((GroupButton) this.mButtonNeutral);
            }
            List<ButtonInfo> list = this.mExtraButtonList;
            if (list == null || list.isEmpty()) {
                return;
            }
            for (ButtonInfo buttonInfo : this.mExtraButtonList) {
                if (buttonInfo != null && buttonInfo.mButton != null && z2) {
                    if (buttonInfo.mStyle == 16843913 || buttonInfo.mStyle == miuix.appcompat.R.attr.buttonBarPrimaryButtonStyle) {
                        ((DialogButtonPanel) viewGroup).addPrimaryStyleButtons(buttonInfo.mButton);
                    } else {
                        if (buttonInfo.mStyle != 16843915) {
                            int i2 = buttonInfo.mStyle;
                            int i3 = miuix.appcompat.R.attr.buttonBarButtonStyle;
                            if (i2 != i3 && buttonInfo.mStyle != 16843567) {
                                if (buttonInfo.mStyle == 16843914 || buttonInfo.mStyle == i3) {
                                    ((DialogButtonPanel) viewGroup).addNeutralStyleButtons(buttonInfo.mButton);
                                }
                            }
                        }
                        ((DialogButtonPanel) viewGroup).addNegativeStyleButtons(buttonInfo.mButton);
                    }
                }
            }
        }
    }

    private void resetListMaxHeight() {
        int singleItemMinHeight = getSingleItemMinHeight();
        int i2 = singleItemMinHeight * (((int) (this.mRootViewSize.y * 0.35f)) / singleItemMinHeight);
        this.mListView.setMinimumHeight(i2);
        ViewGroup.LayoutParams layoutParams = this.mListView.getLayoutParams();
        layoutParams.height = i2;
        this.mListView.setLayoutParams(layoutParams);
    }

    private void setAnimIfEditExistForNonImmersive(View view) {
        if (!this.mEnableEnterAnim || view == null || isTablet() || isDialogImmersive() || !canTextInput(view)) {
            return;
        }
        this.mWindow.setWindowAnimations(miuix.appcompat.R.style.Animation_Dialog_ExistIme);
    }

    private void setWindowNavigationBarHidden() {
        View decorView = this.mWindow.getDecorView();
        if (decorView != null) {
            decorView.setSystemUiVisibility(4098);
            this.mIsAssociatedActivityHideNavigationBar = true;
        }
    }

    private void setupButtons(ViewGroup viewGroup) {
        int i2;
        Button button = (Button) viewGroup.findViewById(16908313);
        this.mButtonPositive = button;
        button.setOnClickListener(this.mButtonHandler);
        this.mButtonPositive.removeTextChangedListener(this.mDefaultButtonsTextWatcher);
        this.mButtonPositive.addTextChangedListener(this.mDefaultButtonsTextWatcher);
        boolean z2 = true;
        if (TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
            i2 = 0;
        } else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            disableForceDark(this.mButtonPositive);
            i2 = 1;
        }
        Button button2 = (Button) viewGroup.findViewById(16908314);
        this.mButtonNegative = button2;
        button2.setOnClickListener(this.mButtonHandler);
        this.mButtonNegative.removeTextChangedListener(this.mDefaultButtonsTextWatcher);
        this.mButtonNegative.addTextChangedListener(this.mDefaultButtonsTextWatcher);
        if (TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            i2++;
            disableForceDark(this.mButtonNegative);
        }
        Button button3 = (Button) viewGroup.findViewById(R.id.button3);
        this.mButtonNeutral = button3;
        button3.setOnClickListener(this.mButtonHandler);
        this.mButtonNeutral.removeTextChangedListener(this.mDefaultButtonsTextWatcher);
        this.mButtonNeutral.addTextChangedListener(this.mDefaultButtonsTextWatcher);
        if (TextUtils.isEmpty(this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(8);
        } else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            i2++;
            disableForceDark(this.mButtonNeutral);
        }
        List<ButtonInfo> list = this.mExtraButtonList;
        if (list != null && !list.isEmpty()) {
            for (ButtonInfo buttonInfo : this.mExtraButtonList) {
                if (buttonInfo.mButton != null) {
                    safeRemoveFromParent(buttonInfo.mButton);
                }
            }
            for (ButtonInfo buttonInfo2 : this.mExtraButtonList) {
                if (buttonInfo2.mButton == null) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f);
                    buttonInfo2.mButton = new GroupButton(this.mContext, null, buttonInfo2.mStyle);
                    buttonInfo2.mButton.setText(buttonInfo2.mText);
                    buttonInfo2.mButton.setOnClickListener(this.mButtonHandler);
                    buttonInfo2.mButton.setLayoutParams(layoutParams);
                    buttonInfo2.mButton.setMaxLines(1);
                    buttonInfo2.mButton.setGravity(17);
                }
                if (buttonInfo2.mMsg == null) {
                    buttonInfo2.mMsg = this.mHandler.obtainMessage(buttonInfo2.mWhich, buttonInfo2.mOnClickListener);
                }
                if (buttonInfo2.mButton.getVisibility() != 8) {
                    i2++;
                    disableForceDark(buttonInfo2.mButton);
                }
                viewGroup.addView(buttonInfo2.mButton);
            }
        }
        recordButtonTypeForOS3Display(viewGroup);
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        } else {
            ((DialogButtonPanel) viewGroup).setForceVertical(this.mButtonForceVertical || this.mLandscapePanel || (this.mIsFlipTinyScreen && WindowUtils.isPortrait(this.mContext)) || (MiuixUIUtils.getFontLevel(this.mContext) == 2));
        }
        Point point = new Point();
        WindowUtils.getScreenSize(this.mContext, point);
        int iMax = Math.max(point.x, point.y);
        ViewGroup viewGroup2 = (ViewGroup) this.mParentPanel.findViewById(miuix.appcompat.R.id.contentPanel);
        boolean zButtonNeedScrollable = buttonNeedScrollable((DialogButtonPanel) viewGroup, i2);
        if (this.mRootViewSize.y > iMax * 0.33f && !zButtonNeedScrollable) {
            z2 = false;
        }
        if (this.mLandscapePanel) {
            return;
        }
        if (!z2) {
            safeMoveView(viewGroup, this.mParentPanel);
        } else {
            safeMoveView(viewGroup, viewGroup2);
            ((NestedScrollViewExpander) viewGroup2).setExpandView(null);
        }
    }

    private void setupCheckbox(ViewGroup viewGroup, @NonNull ViewStub viewStub) {
        if (this.mCheckBoxMessage != null) {
            viewStub.inflate();
            CheckBox checkBox = (CheckBox) viewGroup.findViewById(R.id.checkbox);
            checkBox.setVisibility(0);
            checkBox.setChecked(this.mIsChecked);
            checkBox.setText(this.mCheckBoxMessage);
        }
        TextAlignLayout textAlignLayout = (TextAlignLayout) viewGroup.findViewById(miuix.appcompat.R.id.textAlign);
        if (textAlignLayout != null) {
            textAlignLayout.setDialogPanelHasCheckbox(this.mCheckBoxMessage != null);
        }
    }

    private void setupContent(ViewGroup viewGroup, boolean z2) {
        View childAt;
        FrameLayout frameLayout = (FrameLayout) viewGroup.findViewById(R.id.custom);
        boolean z3 = false;
        if (frameLayout != null) {
            if (z2) {
                LayoutTransition layoutTransition = new LayoutTransition();
                layoutTransition.setDuration(0, 200L);
                layoutTransition.setInterpolator(0, new CubicEaseInOutInterpolator());
                frameLayout.setLayoutTransition(layoutTransition);
            } else {
                frameLayout.setLayoutTransition(null);
            }
        }
        if (this.mListView == null) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(miuix.appcompat.R.id.contentView);
            if (viewGroup2 != null) {
                setupContentView(viewGroup2);
            }
            if (frameLayout != null) {
                boolean z4 = setupCustomContent(frameLayout);
                if (z4 && (childAt = frameLayout.getChildAt(0)) != null) {
                    ViewCompat.setNestedScrollingEnabled(childAt, true);
                }
                z3 = z4;
            }
            NestedScrollViewExpander nestedScrollViewExpander = (NestedScrollViewExpander) viewGroup;
            if (!z3) {
                frameLayout = null;
            }
            nestedScrollViewExpander.setExpandView(frameLayout);
            return;
        }
        if (!(frameLayout != null ? setupCustomContent(frameLayout) : false)) {
            viewGroup.removeView(viewGroup.findViewById(miuix.appcompat.R.id.contentView));
            if (frameLayout != null) {
                safeRemoveFromParent(frameLayout);
            }
            safeRemoveFromParent(this.mListView);
            this.mListView.setMinimumHeight(getSingleItemMinHeight());
            ViewCompat.setNestedScrollingEnabled(this.mListView, true);
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
            if (getVisibleButtonCount() > 0 && !shouldUseLandscapePanel()) {
                marginLayoutParams.bottomMargin = this.mDimensConfig.listViewMarginBottom;
            }
            viewGroup.addView(this.mListView, 0, marginLayoutParams);
            ((NestedScrollViewExpander) viewGroup).setExpandView(this.mListView);
            return;
        }
        int i2 = miuix.appcompat.R.id.contentView;
        viewGroup.removeView(viewGroup.findViewById(i2));
        safeRemoveFromParent(frameLayout);
        LinearLayout linearLayout = new LinearLayout(viewGroup.getContext());
        linearLayout.setOrientation(1);
        safeRemoveFromParent(this.mListView);
        ViewCompat.setNestedScrollingEnabled(this.mListView, true);
        linearLayout.addView(this.mListView, 0, new ViewGroup.MarginLayoutParams(-1, -2));
        boolean zListViewIsNeedFullScroll = listViewIsNeedFullScroll();
        if (zListViewIsNeedFullScroll) {
            resetListMaxHeight();
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(-1, -2, 0.0f));
        } else {
            adjustHeight2WrapContent();
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
        viewGroup.addView(linearLayout, 0, new ViewGroup.MarginLayoutParams(-1, -2));
        ViewGroup viewGroup3 = (ViewGroup) viewGroup.findViewById(i2);
        if (viewGroup3 != null) {
            setupContentView(viewGroup3);
        }
        ((NestedScrollViewExpander) viewGroup).setExpandView(zListViewIsNeedFullScroll ? null : linearLayout);
    }

    private void setupContentView(ViewGroup viewGroup) {
        CharSequence charSequence;
        this.mMessageView = (TextView) viewGroup.findViewById(miuix.appcompat.R.id.message);
        this.mCommentView = (TextView) viewGroup.findViewById(miuix.appcompat.R.id.comment);
        TextView textView = this.mMessageView;
        if (textView == null || (charSequence = this.mMessage) == null) {
            safeRemoveFromParent(viewGroup);
            return;
        }
        textView.setText(charSequence);
        TextView textView2 = this.mCommentView;
        if (textView2 != null) {
            CharSequence charSequence2 = this.mComment;
            if (charSequence2 != null) {
                textView2.setText(charSequence2);
            } else {
                textView2.setVisibility(8);
            }
        }
    }

    private boolean setupCustomContent(ViewGroup viewGroup) {
        View view = this.mInflatedView;
        View viewInflate = null;
        if (view != null && view.getParent() != null) {
            safeRemoveFromParent(this.mInflatedView);
            this.mInflatedView = null;
        }
        View view2 = this.mView;
        if (view2 != null) {
            viewInflate = view2;
        } else if (this.mViewLayoutResId != 0) {
            viewInflate = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false);
            this.mInflatedView = viewInflate;
        }
        boolean z2 = viewInflate != null;
        if (z2 && canTextInput(viewInflate)) {
            this.mWindow.clearFlags(131072);
        } else {
            this.mWindow.setFlags(131072, 131072);
        }
        setAnimIfEditExistForNonImmersive(viewInflate);
        if (z2) {
            safeMoveView(viewInflate, viewGroup);
        } else {
            safeRemoveFromParent(viewGroup);
        }
        return z2;
    }

    private void setupImmersiveWindow() {
        this.mWindow.setLayout(-1, -1);
        this.mWindow.setBackgroundDrawableResource(miuix.appcompat.R.color.miuix_appcompat_transparent);
        this.mWindow.setDimAmount(0.0f);
        this.mWindow.setWindowAnimations(miuix.appcompat.R.style.Animation_Dialog_NoAnimation);
        this.mWindow.addFlags(-2147481344);
        Activity associatedActivity = ((AlertDialog) this.mDialog).getAssociatedActivity();
        if (associatedActivity != null) {
            this.mWindow.getAttributes().layoutInDisplayCutoutMode = getCutoutMode(getScreenOrientation(), associatedActivity.getWindow().getAttributes().layoutInDisplayCutoutMode);
        } else {
            this.mWindow.getAttributes().layoutInDisplayCutoutMode = getScreenOrientation() != 2 ? 1 : 2;
        }
        clearFitSystemWindow(this.mWindow.getDecorView());
        this.mWindow.getAttributes().setFitInsetsSides(0);
        Activity associatedActivity2 = ((AlertDialog) this.mDialog).getAssociatedActivity();
        boolean associatedActivitySystemBarVisibility = getAssociatedActivitySystemBarVisibility(associatedActivity2, WindowInsets.Type.statusBars());
        boolean z2 = (associatedActivity2 == null || (associatedActivity2.getWindow().getAttributes().flags & 1024) == 1024) ? false : true;
        if (z2 && associatedActivitySystemBarVisibility) {
            this.mWindow.clearFlags(1024);
        }
        boolean associatedActivitySystemBarVisibility2 = getAssociatedActivitySystemBarVisibility(associatedActivity2, WindowInsets.Type.navigationBars());
        if (this.mIsDebugEnabled) {
            Log.i(TAG, "setupImmersiveWindow: statusBarIsVisible = " + associatedActivitySystemBarVisibility + ", windowExcludeFullScreenFlag = " + z2 + ", navigationBarIsVisible = " + associatedActivitySystemBarVisibility2);
        }
        if (associatedActivitySystemBarVisibility2) {
            return;
        }
        setWindowNavigationBarHidden();
    }

    private void setupNonImmersiveWindow() {
        Point availableWindowSizeDp = getAvailableWindowSizeDp(null);
        updateDialogPanelLayoutParams(availableWindowSizeDp);
        int iDp2px = this.mPanelParamsWidth;
        if (iDp2px == -1) {
            iDp2px = MiuixUIUtils.dp2px(this.mContext, availableWindowSizeDp.x) - (this.mPanelParamsHorizontalMargin * 2);
        }
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "setupNonImmersiveWindow: windowWidth = " + iDp2px);
            Log.d(TAG, "setupNonImmersiveWindow: availableWindowSizeDp = " + availableWindowSizeDp);
            Log.d(TAG, "setupNonImmersiveWindow: horizontalMargin = " + this.mPanelParamsHorizontalMargin);
        }
        int i2 = this.mNonImmersiveDialogHeight;
        int i3 = (i2 <= 0 || i2 < this.mRootViewSize.y) ? i2 : -1;
        int gravity = getGravity();
        this.mWindow.setGravity(gravity);
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        if ((gravity & 80) == 80) {
            int i4 = this.mIsFlipTinyScreen ? this.mDimensConfig.widthSmallMargin : this.mDimensConfig.extraImeMargin;
            boolean zIsShowNavigationHandle = MiuixUIUtils.isShowNavigationHandle(this.mContext);
            boolean z2 = MiuixUIUtils.isInMultiWindowMode(this.mContext) && !this.mIsInFreeForm && DeviceHelper.isWideScreen(this.mContext);
            if (this.mIsInFreeForm || (z2 && zIsShowNavigationHandle)) {
                Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.captionBar());
                int i5 = this.mDimensConfig.freePhoneCompactHeight;
                int i6 = associatedActivityInsets != null ? associatedActivityInsets.bottom : 0;
                i4 = i6 == 0 ? i4 + i5 : i4 + i6;
            }
            int i7 = attributes.flags;
            if ((i7 & 134217728) != 0) {
                this.mWindow.clearFlags(134217728);
            }
            if ((i7 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0) {
                this.mWindow.clearFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            }
            attributes.verticalMargin = (i4 * 1.0f) / this.mRootViewSize.y;
        }
        attributes.layoutInDisplayCutoutMode = 2;
        this.mWindow.setAttributes(attributes);
        this.mWindow.addFlags(2);
        this.mWindow.addFlags(262144);
        this.mWindow.setDimAmount(ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT);
        this.mWindow.setLayout(iDp2px, i3);
        this.mWindow.setBackgroundDrawableResource(miuix.appcompat.R.color.miuix_appcompat_transparent);
        DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
        if (dialogParentPanel2 != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) dialogParentPanel2.getLayoutParams();
            layoutParams.width = iDp2px;
            layoutParams.height = -2;
            if (isSpecifiedDialogHeight()) {
                layoutParams.gravity = getGravity();
            }
            this.mParentPanel.setLayoutParams(layoutParams);
            this.mParentPanel.setTag(null);
        }
        if (!this.mEnableEnterAnim) {
            this.mWindow.setWindowAnimations(0);
        } else if (isTablet()) {
            this.mWindow.setWindowAnimations(miuix.appcompat.R.style.Animation_Dialog_Center);
        }
    }

    private void setupTitle(ViewGroup viewGroup) {
        ImageView imageView = (ImageView) this.mWindow.findViewById(R.id.icon);
        View view = this.mCustomTitleView;
        if (view != null) {
            safeRemoveFromParent(view);
            viewGroup.addView(this.mCustomTitleView, 0, new ViewGroup.LayoutParams(-1, -2));
            this.mWindow.findViewById(miuix.appcompat.R.id.alertTitle).setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        if (TextUtils.isEmpty(this.mTitle) || !this.mShowTitle) {
            this.mWindow.findViewById(miuix.appcompat.R.id.alertTitle).setVisibility(8);
            imageView.setVisibility(8);
            viewGroup.setVisibility(8);
            return;
        }
        TextView textView = (TextView) this.mWindow.findViewById(miuix.appcompat.R.id.alertTitle);
        this.mTitleView = textView;
        textView.setText(this.mTitle);
        int i2 = this.mIconId;
        if (i2 != 0) {
            imageView.setImageResource(i2);
        } else {
            Drawable drawable = this.mIcon;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else {
                this.mTitleView.setPadding(imageView.getPaddingLeft(), imageView.getPaddingTop(), imageView.getPaddingRight(), imageView.getPaddingBottom());
                imageView.setVisibility(8);
            }
        }
        if (this.mSmallIcon) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            DialogContract.DimensConfig dimensConfig = this.mDimensConfig;
            layoutParams.width = dimensConfig.smallIconWidth;
            layoutParams.height = dimensConfig.smallIconHeight;
        }
        if (this.mIconWidth != 0 && this.mIconHeight != 0) {
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            layoutParams2.width = this.mIconWidth;
            layoutParams2.height = this.mIconHeight;
        }
        if (this.mMessage == null || viewGroup.getVisibility() == 8) {
            return;
        }
        changeTitlePadding(this.mTitleView);
    }

    private void setupView() {
        setupView(true, false, false, 1.0f);
        storeCustomViewInitialTextSize();
    }

    private void setupWindow() {
        if (isDialogImmersive()) {
            setupImmersiveWindow();
        } else {
            setupNonImmersiveWindow();
        }
    }

    @RequiresApi(api = 30)
    private void setupWindowInsetsAnimation() {
        if (isDialogImmersive()) {
            this.mWindow.setSoftInputMode((this.mWindow.getAttributes().softInputMode & 15) | 48);
            WindowInsetsAnimation.Callback callback = new WindowInsetsAnimation.Callback(1) { // from class: miuix.appcompat.app.AlertController.7
                boolean isTablet = false;

                @Override // android.view.WindowInsetsAnimation.Callback
                public void onEnd(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                    super.onEnd(windowInsetsAnimation);
                    AlertController.this.mInsetsAnimationPlayed = true;
                    WindowInsets rootWindowInsets = AlertController.this.mWindow.getDecorView().getRootWindowInsets();
                    if (rootWindowInsets != null) {
                        Insets insets = rootWindowInsets.getInsets(WindowInsets.Type.ime());
                        if (insets.bottom <= 0 && AlertController.this.mParentPanel.getTranslationY() < 0.0f) {
                            AlertController.this.translateDialogPanel(0);
                        }
                        AlertController.this.updateParentPanelMarginByWindowInsets(rootWindowInsets);
                        if (this.isTablet) {
                            return;
                        }
                        AlertController.this.updateDimBgBottomMargin(insets.bottom);
                    }
                }

                @Override // android.view.WindowInsetsAnimation.Callback
                public void onPrepare(@NonNull WindowInsetsAnimation windowInsetsAnimation) {
                    super.onPrepare(windowInsetsAnimation);
                    if (windowInsetsAnimation != null && (windowInsetsAnimation.getTypeMask() & WindowInsets.Type.ime()) > 0) {
                        AlertController.this.mDialogAnimHelper.cancelAnimator();
                    }
                    AlertController.this.mInsetsAnimationPlayed = false;
                    this.isTablet = AlertController.this.isTablet();
                }

                @Override // android.view.WindowInsetsAnimation.Callback
                @NonNull
                @RequiresApi(api = 30)
                public WindowInsets onProgress(@NonNull WindowInsets windowInsets, @NonNull List<WindowInsetsAnimation> list) {
                    Insets insets = windowInsets.getInsets(WindowInsets.Type.ime());
                    Insets insets2 = windowInsets.getInsets(WindowInsets.Type.navigationBars());
                    int iMax = insets.bottom - Math.max(AlertController.this.mPanelAndImeMargin, insets2.bottom);
                    if (windowInsets.isVisible(WindowInsets.Type.ime())) {
                        if (AlertController.this.mIsDebugEnabled) {
                            Log.d(AlertController.TAG, "WindowInsetsAnimation onProgress mPanelAndImeMargin : " + AlertController.this.mPanelAndImeMargin);
                            Log.d(AlertController.TAG, "WindowInsetsAnimation onProgress ime : " + insets.bottom);
                            Log.d(AlertController.TAG, "WindowInsetsAnimation onProgress navigationBar : " + insets2.bottom);
                        }
                        AlertController.this.translateDialogPanel(-(iMax < 0 ? 0 : iMax));
                    }
                    if (!this.isTablet) {
                        AlertController.this.updateDimBgBottomMargin(iMax);
                    }
                    return windowInsets;
                }

                @Override // android.view.WindowInsetsAnimation.Callback
                @NonNull
                public WindowInsetsAnimation.Bounds onStart(@NonNull WindowInsetsAnimation windowInsetsAnimation, @NonNull WindowInsetsAnimation.Bounds bounds) {
                    AlertController.this.mPanelAndImeMargin = (int) (r0.getDialogPanelMargin() + AlertController.this.mParentPanel.getTranslationY());
                    if (AlertController.this.mIsDebugEnabled) {
                        Log.d(AlertController.TAG, "WindowInsetsAnimation onStart mPanelAndImeMargin : " + AlertController.this.mPanelAndImeMargin);
                    }
                    if (AlertController.this.mPanelAndImeMargin <= 0) {
                        AlertController.this.mPanelAndImeMargin = 0;
                    }
                    return super.onStart(windowInsetsAnimation, bounds);
                }
            };
            if (!(this.mDialog instanceof PairingDialog)) {
                this.mWindow.getDecorView().setWindowInsetsAnimationCallback(callback);
            }
            this.mWindow.getDecorView().setOnApplyWindowInsetsListener(new AnonymousClass8());
            this.mSetupWindowInsetsAnimation = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldUseLandscapePanel() {
        if (getVisibleButtonCount() == 0) {
            return false;
        }
        Point point = this.mRootViewSize;
        int i2 = point.x;
        return i2 >= this.mDimensConfig.panelMaxWidthLand && i2 * 2 > point.y && this.mPreferLandscape;
    }

    private boolean showSystemAlertInFlip() {
        int i2 = this.mWindow.getAttributes().type;
        return this.mIsFlipTinyScreen && ((i2 == 2038 || i2 == 2003) || this.mUseForceFlipCutout);
    }

    private void storeCustomViewInitialTextSize() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        float f2 = displayMetrics.scaledDensity;
        float f3 = displayMetrics.density;
        View view = this.mCustomTitleView;
        if (view != null) {
            this.mCustomTitleTextView = (TextView) view.findViewById(R.id.title);
        }
        TextView textView = this.mCustomTitleTextView;
        if (textView != null) {
            this.mCustomTitleTextSize = textView.getTextSize();
            int textSizeUnit = this.mCustomTitleTextView.getTextSizeUnit();
            if (textSizeUnit == 1) {
                this.mCustomTitleTextSize /= f3;
            } else if (textSizeUnit == 2) {
                this.mCustomTitleTextSize /= f2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateDialogPanel(int i2) {
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "The DialogPanel transitionY for : " + i2);
        }
        this.mParentPanel.animate().cancel();
        this.mParentPanel.setTranslationY(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateButtons(@NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2) {
        int visibleButtonCount = getVisibleButtonCount();
        Point point = new Point();
        WindowUtils.getScreenSize(this.mContext, point);
        DialogButtonPanel dialogButtonPanel = (DialogButtonPanel) viewGroup;
        boolean z2 = ((float) this.mRootViewSize.y) <= ((float) Math.max(point.x, point.y)) * 0.33f || buttonNeedScrollable(dialogButtonPanel, visibleButtonCount);
        dialogButtonPanel.setForceVertical(this.mButtonForceVertical || this.mLandscapePanel || (this.mIsFlipTinyScreen && (this.mContext.getResources().getConfiguration().orientation == 1)) || (MiuixUIUtils.getFontLevel(this.mContext) == 2));
        if (!z2) {
            safeMoveView(viewGroup, this.mParentPanel);
        } else {
            safeMoveView(viewGroup, viewGroup2);
            ((NestedScrollViewExpander) viewGroup2).setExpandView(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContent(@NonNull ViewGroup viewGroup) {
        FrameLayout frameLayout = (FrameLayout) viewGroup.findViewById(R.id.custom);
        boolean z2 = frameLayout != null && frameLayout.getChildCount() > 0;
        ListView listView = this.mListView;
        if (listView == null) {
            if (z2) {
                ViewCompat.setNestedScrollingEnabled(frameLayout.getChildAt(0), true);
            }
            NestedScrollViewExpander nestedScrollViewExpander = (NestedScrollViewExpander) viewGroup;
            if (!z2) {
                frameLayout = null;
            }
            nestedScrollViewExpander.setExpandView(frameLayout);
            return;
        }
        if (!z2) {
            ((NestedScrollViewExpander) viewGroup).setExpandView(listView);
            return;
        }
        if (listViewIsNeedFullScroll()) {
            resetListMaxHeight();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.height = -2;
            layoutParams.weight = 0.0f;
            frameLayout.setLayoutParams(layoutParams);
            ((NestedScrollViewExpander) viewGroup).setExpandView(null);
            viewGroup.requestLayout();
            return;
        }
        adjustHeight2WrapContent();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams2.height = 0;
        layoutParams2.weight = 1.0f;
        frameLayout.setLayoutParams(layoutParams2);
        ((NestedScrollViewExpander) viewGroup).setExpandView((View) frameLayout.getParent());
        viewGroup.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 30)
    public void updateDialogPanelByWindowInsets(@NonNull WindowInsets windowInsets) {
        updateParentPanelMarginByWindowInsets(windowInsets);
        if (isNeedUpdateDialogPanelTranslationY()) {
            boolean zIsInMultiWindowMode = MiuixUIUtils.isInMultiWindowMode(this.mContext);
            Insets insets = windowInsets.getInsets(WindowInsets.Type.ime());
            Insets insets2 = windowInsets.getInsets(WindowInsets.Type.navigationBars());
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "======================Debug for checkTranslateDialogPanel======================");
                Log.d(TAG, "The mPanelAndImeMargin: " + this.mPanelAndImeMargin);
                Log.d(TAG, "The imeInsets info: " + insets.toString());
                Log.d(TAG, "The navigationBarInsets info: " + insets2.toString());
                Log.d(TAG, "The insets info: " + windowInsets);
            }
            boolean zIsTablet = isTablet();
            if (!zIsTablet) {
                updateDimBgBottomMargin(insets.bottom);
            }
            int i2 = insets.bottom;
            if (zIsInMultiWindowMode && !zIsTablet) {
                i2 -= insets2.bottom;
            }
            updateDialogPanelTranslationYByIme(i2, zIsInMultiWindowMode, zIsTablet);
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "===================End of Debug for checkTranslateDialogPanel===================");
            }
        }
    }

    private void updateDialogPanelLayoutParams(@Nullable Point point) {
        Point availableWindowSizeDp = point == null ? getAvailableWindowSizeDp(null) : point;
        DialogContract.OrientationSpec orientationSpec = new DialogContract.OrientationSpec();
        orientationSpec.mUsableWindowSizeDp.set(availableWindowSizeDp.x, availableWindowSizeDp.y);
        Point point2 = orientationSpec.mWindowSize;
        Point point3 = this.mRootViewSize;
        point2.set(point3.x, point3.y);
        WindowUtils.getScreenSize(this.mContext, orientationSpec.mRealScreenSize);
        orientationSpec.updateData(this.mMarkLandscape, this.mIsInFreeForm, getScreenOrientation(), this.mIsCarWithScreen, this.mIsSynergy);
        boolean zIsLandscapeWindow = this.mDisplayStrategy.isLandscapeWindow(orientationSpec);
        int i2 = availableWindowSizeDp.x;
        boolean zShouldLimitPanelWidth = this.mDisplayStrategy.shouldLimitPanelWidth(i2);
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateDialogPanelLayoutParams isLandScape " + zIsLandscapeWindow);
            Log.d(TAG, "updateDialogPanelLayoutParams shouldLimitWidth " + zShouldLimitPanelWidth);
        }
        boolean z2 = false;
        int widthMargin = zShouldLimitPanelWidth ? 0 : this.mDisplayStrategy.getWidthMargin(this.mDimensConfig, i2);
        this.mIsWindowLandScape = zIsLandscapeWindow;
        DialogContract.PanelWidthSpec panelWidthSpec = new DialogContract.PanelWidthSpec();
        if (this.mPreferLandscape && shouldUseLandscapePanel()) {
            z2 = true;
        }
        panelWidthSpec.updateData(z2, zIsLandscapeWindow, this.mIsCarWithScreen, this.mMarkLandscape, i2, this.mScreenMinorSize, this.mIsDebugEnabled);
        this.mPanelParamsWidth = this.mDisplayStrategy.getPanelWidth(panelWidthSpec, this.mDimensConfig);
        inflateDialogLayout();
        this.mPanelParamsHorizontalMargin = widthMargin;
    }

    private void updateDialogPanelTranslationYByIme(int i2, boolean z2, boolean z3) {
        if (i2 <= 0) {
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "updateDialogPanelTranslationYByIme imeBottom <= 0");
            }
            if (this.mParentPanel.getTranslationY() < 0.0f) {
                translateDialogPanel(0);
                return;
            }
            return;
        }
        int dialogPanelMargin = (int) (getDialogPanelMargin() + this.mParentPanel.getTranslationY());
        this.mPanelAndImeMargin = dialogPanelMargin;
        if (dialogPanelMargin <= 0) {
            this.mPanelAndImeMargin = 0;
        }
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateDialogPanelTranslationYByIme mPanelAndImeMargin " + this.mPanelAndImeMargin + " isMultiWindowMode " + z2 + " imeBottom " + i2);
        }
        int i3 = (!z3 || i2 >= this.mPanelAndImeMargin) ? (!z2 || z3) ? (-i2) + this.mPanelAndImeMargin : -i2 : 0;
        if (!this.mIsDialogAnimating) {
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "updateDialogPanelTranslationYByIme translateDialogPanel Y=" + i3);
            }
            translateDialogPanel(i3);
            return;
        }
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateDialogPanelTranslationYByIme anim translateDialogPanel Y=" + i3);
        }
        this.mParentPanel.animate().cancel();
        this.mParentPanel.animate().setDuration(200L).translationY(i3).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDimBgBottomMargin(int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mDimBg.getLayoutParams();
        if (marginLayoutParams.bottomMargin != i2) {
            marginLayoutParams.bottomMargin = i2;
            this.mDimBg.requestLayout();
        }
    }

    private void updateDimensConfig(Resources resources) {
        this.mDimensConfig.panelMaxWidth = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_max_width);
        this.mDimensConfig.panelMaxWidthLand = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_max_width_land);
        this.mDimensConfig.listViewMarginBottom = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_list_view_margin_bottom);
        this.mDimensConfig.extraImeMargin = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_ime_margin);
        this.mDimensConfig.fakeLandScreenMinorSize = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.fake_landscape_screen_minor_size);
        this.mDimensConfig.widthSmallMargin = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_width_small_margin);
        this.mDimensConfig.widthMargin = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_width_margin);
        this.mDimensConfig.freeTabletCompactHeight = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_freeform_bottom_height_tablet_t);
        this.mDimensConfig.freePhoneCompactHeight = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_freeform_bottom_height_phone_t);
        this.mDimensConfig.smallIconWidth = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_icon_drawable_width_small);
        this.mDimensConfig.smallIconHeight = resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_icon_drawable_height_small);
    }

    private void updateDisplayInfo(Context context) {
        boolean zIsCarWithScreen = DeviceHelper.isCarWithScreen(context, null);
        this.mIsCarWithScreen = zIsCarWithScreen;
        if (zIsCarWithScreen) {
            this.mIsSynergy = true;
        } else {
            this.mIsSynergy = DeviceHelper.isXiaomiSynergy(context);
        }
    }

    private void updateImmersiveDialogPanel() {
        updateDialogPanelLayoutParams(getAvailableWindowSizeDp(null));
        int iDp2px = this.mPanelParamsWidth;
        if (iDp2px == -1) {
            iDp2px = MiuixUIUtils.dp2px(this.mContext, r0.x) - (this.mPanelParamsHorizontalMargin * 2);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(iDp2px, -2);
        layoutParams.gravity = getGravity();
        if (this.mPanelParamsWidth == -1) {
            int i2 = this.mPanelParamsHorizontalMargin;
            layoutParams.leftMargin = i2;
            layoutParams.rightMargin = i2;
        }
        this.mParentPanel.setLayoutParams(layoutParams);
    }

    private void updateMinorScreenSize() {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int iMin = (int) (Math.min(configuration.screenWidthDp, configuration.screenHeightDp) * (configuration.densityDpi / 160.0f));
        if (iMin > 0) {
            this.mScreenMinorSize = iMin;
            return;
        }
        Point point = new Point();
        this.mWindowManager.getDefaultDisplay().getSize(point);
        this.mScreenMinorSize = Math.min(point.x, point.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 30)
    public void updateParentPanelMarginByWindowInsets(WindowInsets windowInsets) {
        int i2;
        boolean z2;
        int i3;
        if (isTablet() || windowInsets == null) {
            return;
        }
        Insets insets = (this.mNavigationBarHiddenEnabled || this.mIsAssociatedActivityHideNavigationBar) ? windowInsets.getInsets(WindowInsets.Type.systemBars()) : windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars());
        Rect rectInsetsToRect = DialogContract.insetsToRect(insets);
        Insets insets2 = windowInsets.getInsets(WindowInsets.Type.displayCutout());
        this.mDisplayCutoutSafeInsets.setEmpty();
        int width = this.mDialogRootView.getWidth();
        int height = this.mDialogRootView.getHeight();
        Point point = this.mRootViewSize;
        if (point.x == 0 || point.y == 0) {
            updateRootViewSize((Configuration) null);
            Point point2 = this.mRootViewSize;
            i2 = point2.x;
            height = point2.y;
        } else {
            i2 = width;
        }
        if (insets2 != null && !this.mIsInFreeForm) {
            this.mDisplayCutoutSafeInsets.set(insets2.left, insets2.top, insets2.right, insets2.bottom);
        }
        if (this.mIsFlipTinyScreen) {
            Rect guaranteedCutout = getGuaranteedCutout(windowInsets, false);
            this.mDisplayCutoutSafeInsets.set(guaranteedCutout.left, guaranteedCutout.top, guaranteedCutout.right, guaranteedCutout.bottom);
        }
        Rect rectMergeInsets = DialogContract.mergeInsets(rectInsetsToRect, this.mDisplayCutoutSafeInsets);
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateParentPanelMargin systemBarInsets: " + insets);
            Log.d(TAG, "updateParentPanelMargin mDisplayCutoutSafeInsets: " + this.mDisplayCutoutSafeInsets);
            Log.d(TAG, "updateParentPanelMargin boundInsets = " + rectMergeInsets);
        }
        Point point3 = this.mRootViewSize;
        Point point4 = new Point(point3.x, point3.y);
        if (i2 != 0 && i2 != point4.x) {
            point4.x = i2;
            point4.y = height;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mParentPanel.getLayoutParams();
        DialogContract.OrientationSpec orientationSpec = new DialogContract.OrientationSpec();
        Point windowSize = EnvStateManager.getWindowSize(this.mContext);
        orientationSpec.updateData(this.mMarkLandscape, this.mIsInFreeForm, getScreenOrientation(), this.mIsCarWithScreen, this.mIsSynergy);
        orientationSpec.mWindowSize.set(windowSize.x, windowSize.y);
        WindowUtils.getScreenSize(this.mContext, orientationSpec.mRealScreenSize);
        float f2 = this.mContext.getResources().getDisplayMetrics().densityDpi / 160.0f;
        int i4 = (point4.x - rectMergeInsets.left) - rectMergeInsets.right;
        int i5 = (point4.y - rectMergeInsets.top) - rectMergeInsets.bottom;
        int iPx2dp = MiuixUIUtils.px2dp(f2, i4);
        orientationSpec.mUsableWindowSizeDp.set(iPx2dp, MiuixUIUtils.px2dp(f2, i5));
        boolean zIsLandscapeWindow = this.mDisplayStrategy.isLandscapeWindow(orientationSpec);
        DialogContract.PanelWidthSpec panelWidthSpec = new DialogContract.PanelWidthSpec();
        boolean z3 = true;
        panelWidthSpec.updateData(this.mPreferLandscape && shouldUseLandscapePanel(), zIsLandscapeWindow, this.mIsCarWithScreen, this.mMarkLandscape, iPx2dp, this.mScreenMinorSize, this.mIsDebugEnabled);
        int panelWidth = this.mDisplayStrategy.getPanelWidth(panelWidthSpec, this.mDimensConfig);
        DialogContract.PanelPosSpec panelPosSpec = new DialogContract.PanelPosSpec();
        panelPosSpec.mBoundInsets.set(rectMergeInsets.left, rectMergeInsets.top, rectMergeInsets.right, rectMergeInsets.bottom);
        panelPosSpec.updateData(this.mDialogRootView.getPaddingLeft(), this.mDialogRootView.getPaddingRight(), i2, panelWidth, iPx2dp, i4, this.mRootViewSize.x, this.mIsFlipTinyScreen, this.mIsDebugEnabled);
        Rect rect = new Rect();
        int iUpdatePanelPosMargins = this.mDisplayStrategy.updatePanelPosMargins(panelPosSpec, this.mDimensConfig, rect);
        layoutParams.width = iUpdatePanelPosMargins;
        int i6 = rect.bottom;
        boolean z4 = MiuixUIUtils.isInMultiWindowMode(this.mContext) && !this.mIsInFreeForm && DeviceHelper.isWideScreen(this.mContext);
        if ((this.mIsInFreeForm || z4) && insets.bottom == 0) {
            Insets associatedActivityInsets = getAssociatedActivityInsets(WindowInsets.Type.captionBar());
            int i7 = this.mDimensConfig.freePhoneCompactHeight;
            int i8 = associatedActivityInsets != null ? associatedActivityInsets.bottom : 0;
            int i9 = i8 == 0 ? i7 + i6 : i6 + i8;
            if (getImeBottomByWindowInsets(windowInsets) <= 0) {
                i6 = i9;
            }
        } else {
            boolean z5 = this.mIsFlipTinyScreen;
            if (!z5 || (i3 = this.mDisplayCutoutSafeInsets.bottom) <= 0) {
                i3 = z5 ? 0 : rectMergeInsets.bottom;
            }
            i6 += i3;
        }
        int i10 = layoutParams.topMargin;
        int i11 = rect.top;
        if (i10 != i11) {
            layoutParams.topMargin = i11;
            z2 = true;
        } else {
            z2 = false;
        }
        if (layoutParams.bottomMargin != i6) {
            layoutParams.bottomMargin = i6;
            z2 = true;
        }
        int i12 = layoutParams.leftMargin;
        int i13 = rect.left;
        if (i12 != i13) {
            layoutParams.leftMargin = i13;
            z2 = true;
        }
        int i14 = layoutParams.rightMargin;
        int i15 = rect.right;
        if (i14 != i15) {
            layoutParams.rightMargin = i15;
            z2 = true;
        }
        if (panelWidth != iUpdatePanelPosMargins) {
            z2 = true;
        }
        int panelMaxLimitHeight = getPanelMaxLimitHeight(rectMergeInsets);
        if (panelMaxLimitHeight != this.mParentPanel.getPanelMaxLimitHeight()) {
            this.mParentPanel.setPanelMaxLimitHeight(panelMaxLimitHeight);
        } else {
            z3 = z2;
        }
        if (z3) {
            this.mParentPanel.requestLayout();
        }
    }

    private void updateRootViewSize(@Nullable Configuration configuration) {
        WindowBaseInfo windowInfo = this.mIsFlipTinyScreen ? EnvStateManager.getWindowInfo(this.mContext) : EnvStateManager.getWindowInfo(this.mContext, configuration);
        Point point = this.mRootViewSizeDp;
        Point point2 = windowInfo.windowSizeDp;
        point.x = point2.x;
        point.y = point2.y;
        Point point3 = this.mRootViewSize;
        Point point4 = windowInfo.windowSize;
        point3.x = point4.x;
        point3.y = point4.y;
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateRootViewSize mRootViewSizeDp " + this.mRootViewSizeDp + " mRootViewSize " + this.mRootViewSize);
            if (configuration != null) {
                Log.d(TAG, "configuration.density " + (configuration.densityDpi / 160.0f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewOnDensityChanged(float f2) {
        TextView textView;
        DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
        if (dialogParentPanel2 != null) {
            DensityChangedHelper.updateViewPadding(dialogParentPanel2, f2);
        }
        TextView textView2 = this.mTitleView;
        if (textView2 != null) {
            DensityChangedHelper.updateTextSizeSpUnit(textView2, this.mTitleTextSize);
        }
        TextView textView3 = this.mMessageView;
        if (textView3 != null) {
            DensityChangedHelper.updateTextSizeSpUnit(textView3, this.mMessageTextSize);
        }
        TextView textView4 = this.mCommentView;
        if (textView4 != null) {
            DensityChangedHelper.updateTextSizeSpUnit(textView4, this.mCommentTextSize);
            DensityChangedHelper.updateViewPadding(this.mCommentView, f2);
        }
        if (this.mCustomTitleView != null && (textView = this.mCustomTitleTextView) != null) {
            DensityChangedHelper.updateTextSizeDefaultUnit(textView, this.mCustomTitleTextSize);
        }
        View viewFindViewById = this.mWindow.findViewById(miuix.appcompat.R.id.buttonPanel);
        if (viewFindViewById != null) {
            DensityChangedHelper.updateViewMargin(viewFindViewById, f2);
        }
        ViewGroup viewGroup = (ViewGroup) this.mWindow.findViewById(miuix.appcompat.R.id.topPanel);
        if (viewGroup != null) {
            DensityChangedHelper.updateViewPadding(viewGroup, f2);
        }
        View viewFindViewById2 = this.mWindow.findViewById(miuix.appcompat.R.id.contentView);
        if (viewFindViewById2 != null) {
            DensityChangedHelper.updateViewMargin(viewFindViewById2, f2);
        }
        CheckBox checkBox = (CheckBox) this.mWindow.findViewById(R.id.checkbox);
        if (checkBox != null) {
            DensityChangedHelper.updateViewMargin(checkBox, f2);
        }
        ImageView imageView = (ImageView) this.mWindow.findViewById(R.id.icon);
        if (imageView != null) {
            DensityChangedHelper.updateViewSize(imageView, f2);
            DensityChangedHelper.updateViewMargin(imageView, f2);
        }
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
            int i2 = getScreenOrientation() != 2 ? 1 : 2;
            if (this.mWindow.getAttributes().layoutInDisplayCutoutMode != i2) {
                this.mWindow.getAttributes().layoutInDisplayCutoutMode = i2;
                View decorView2 = this.mWindow.getDecorView();
                if (this.mDialog.isShowing() && decorView2.isAttachedToWindow()) {
                    this.mWindowManager.updateViewLayout(this.mWindow.getDecorView(), this.mWindow.getAttributes());
                }
            }
        }
    }

    public void addExtraButton(ButtonInfo buttonInfo) {
        if (TextUtils.isEmpty(buttonInfo.mText)) {
            return;
        }
        if (this.mExtraButtonList == null) {
            this.mExtraButtonList = new ArrayList();
        }
        this.mExtraButtonList.add(buttonInfo);
    }

    public void clearExtraButton() {
        List<ButtonInfo> list = this.mExtraButtonList;
        if (list != null) {
            list.clear();
        }
    }

    public void dismiss(DialogAnimHelper.OnDismiss onDismiss) {
        cleanWindowInsetsAnimation();
        DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
        if (dialogParentPanel2 == null) {
            if (onDismiss != null) {
                onDismiss.end();
                return;
            }
            return;
        }
        if (!dialogParentPanel2.isAttachedToWindow()) {
            Log.d(TAG, "dialog is not attached to window when dismiss is invoked");
            try {
                ((AlertDialog) this.mDialog).realDismiss();
                return;
            } catch (IllegalArgumentException e2) {
                Log.wtf(TAG, "Not catch the dialog will throw the illegalArgumentException (In Case cause the crash , we expect it should be caught)", e2);
                return;
            }
        }
        checkAndClearFocus();
        if (isTablet()) {
            hideSoftIME();
        } else {
            WindowBaseInfo windowInfo = EnvStateManager.getWindowInfo(this.mContext);
            if (ScreenModeHelper.isInFreeFormMode(windowInfo.windowMode) || ScreenModeHelper.isInSplitScreenMode(windowInfo.windowMode)) {
                hideSoftIME();
            }
        }
        this.mDialogAnimHelper.executeDismissAnim(this.mParentPanel, isTablet(), this.mDimBg, onDismiss);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 82;
    }

    public Button getButton(int i2) {
        if (i2 == -3) {
            return this.mButtonNeutral;
        }
        if (i2 == -2) {
            return this.mButtonNegative;
        }
        if (i2 == -1) {
            return this.mButtonPositive;
        }
        List<ButtonInfo> list = this.mExtraButtonList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (ButtonInfo buttonInfo : this.mExtraButtonList) {
            if (buttonInfo.mWhich == i2) {
                return buttonInfo.mButton;
            }
        }
        return null;
    }

    public int getIconAttributeResId(int i2) {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(i2, typedValue, true);
        return typedValue.resourceId;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public TextView getMessageView() {
        return this.mMessageView;
    }

    public int getNonImmersiveDialogHeight() {
        return this.mNonImmersiveDialogHeight;
    }

    public int getSingleItemMinHeight() {
        return AttributeResolver.resolveDimensionPixelSize(this.mContext, miuix.appcompat.R.attr.dialogListPreferredItemHeight);
    }

    public boolean hasPendingDismiss() {
        return this.mHasPendingDismiss;
    }

    public void installContent(Bundle bundle) {
        this.mIsFromRebuild = bundle != null;
        this.mIsInFreeForm = MiuixUIUtils.isFreeformMode(this.mContext);
        getFlipCutout();
        this.mDialog.setContentView(this.mAlertDialogLayout);
        this.mDialogRootView = (DialogRootView) this.mWindow.findViewById(miuix.appcompat.R.id.dialog_root_view);
        this.mDimBg = this.mWindow.findViewById(miuix.appcompat.R.id.dialog_dim_bg);
        this.mDialogRootView.setConfigurationChangedCallback(new DialogRootView.ConfigurationChangedCallback() { // from class: miuix.appcompat.app.AlertController.4
            @Override // miuix.appcompat.internal.widget.DialogRootView.ConfigurationChangedCallback
            public void onConfigurationChanged(Configuration configuration, int i2, int i3, int i4, int i5) {
                AlertController.this.onConfigurationChanged(configuration, false, false);
            }
        });
        Configuration configuration = this.mContext.getResources().getConfiguration();
        updateRootViewSize((Configuration) null);
        setupWindow();
        setupView();
        this.configurationAfterInstalled = configuration;
        this.buildJustNow = true;
        this.mDialogRootView.post(new Runnable() { // from class: miuix.appcompat.app.AlertController.5
            @Override // java.lang.Runnable
            public void run() {
                if (AlertController.this.isDialogImmersive()) {
                    AlertController alertController = AlertController.this;
                    alertController.updateRootViewSize(alertController.mDialogRootView);
                }
                ViewGroup viewGroup = (ViewGroup) AlertController.this.mParentPanel.findViewById(miuix.appcompat.R.id.contentPanel);
                ViewGroup viewGroup2 = (ViewGroup) AlertController.this.mParentPanel.findViewById(miuix.appcompat.R.id.buttonPanel);
                if (viewGroup2 == null || viewGroup == null || AlertController.this.shouldUseLandscapePanel()) {
                    return;
                }
                AlertController.this.updateButtons(viewGroup2, viewGroup);
            }
        });
    }

    public boolean isAsyncInflatePanelEnabled() {
        return this.mAsyncInflatePanelEnabled;
    }

    public boolean isChecked() {
        CheckBox checkBox = (CheckBox) this.mWindow.findViewById(R.id.checkbox);
        if (checkBox == null) {
            return false;
        }
        boolean zIsChecked = checkBox.isChecked();
        this.mIsChecked = zIsChecked;
        return zIsChecked;
    }

    public boolean isDialogImmersive() {
        return this.mIsEnableImmersive;
    }

    public boolean isShowingAnimation() {
        boolean z2 = !isDialogImmersive() && Math.abs(this.mShowUpTimeMillis - SystemClock.uptimeMillis()) < this.mNonImmersiveDialogShowAnimDuration;
        if (this.mEnableEnterAnim) {
            return this.mIsDialogAnimating || z2;
        }
        return false;
    }

    public void onAttachedToWindow() {
        reInitLandConfig();
        setupWindowInsetsAnimation();
    }

    public void onConfigurationChanged(Configuration configuration, boolean z2, boolean z3) {
        this.mIsFlipTinyScreen = miuix.os.Build.IS_FLIP && DeviceHelper.isTinyScreen(this.mContext);
        this.mIsInFreeForm = MiuixUIUtils.isFreeformMode(this.mContext);
        updateDimensConfig(this.mContext.getResources());
        getFlipCutout();
        updateDisplayInfo(this.mContext);
        int i2 = configuration.densityDpi;
        float f2 = (i2 * 1.0f) / this.mCurrentDensityDpi;
        if (f2 != 1.0f) {
            this.mCurrentDensityDpi = i2;
        }
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "onConfigurationChangednewDensityDpi " + this.mCurrentDensityDpi + " densityScale " + f2);
        }
        if (!this.buildJustNow || isConfigurationChanged(configuration) || this.mIsFlipTinyScreen || z2) {
            this.buildJustNow = false;
            this.mExtraImeMargin = -1;
            updateRootViewSize((Configuration) null);
            if (this.mIsDebugEnabled) {
                Log.d(TAG, "onConfigurationChanged mRootViewSize " + this.mRootViewSize);
            }
            if (!checkThread()) {
                Log.w(TAG, "dialog is created in thread:" + this.mCreateThread + ", but onConfigurationChanged is called from different thread:" + Thread.currentThread() + ", so this onConfigurationChanged call should be ignore");
                return;
            }
            if (isDialogImmersive()) {
                this.mWindow.getDecorView().removeOnLayoutChangeListener(this.mLayoutChangeListener);
            }
            if (this.mWindow.getDecorView().isAttachedToWindow()) {
                if (f2 != 1.0f) {
                    this.mDimensConfig.panelMaxWidth = this.mContext.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_max_width);
                    this.mDimensConfig.panelMaxWidthLand = this.mContext.getResources().getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_dialog_max_width_land);
                }
                reInitLandConfig();
                if (isDialogImmersive()) {
                    updateWindowCutoutMode();
                } else {
                    setupNonImmersiveWindow();
                }
                this.mParentPanel.setIsInTinyScreen(this.mIsFlipTinyScreen);
                this.mParentPanel.setIsDebugEnabled(this.mIsDebugEnabled);
                setupView(false, z2, z3, f2);
                this.mParentPanel.notifyConfigurationChanged();
            }
            if (isDialogImmersive()) {
                this.mLayoutChangeListener.updateLayout(this.mWindow.getDecorView());
                this.mWindow.getDecorView().addOnLayoutChangeListener(this.mLayoutChangeListener);
                WindowInsets rootWindowInsets = this.mWindow.getDecorView().getRootWindowInsets();
                if (rootWindowInsets != null) {
                    updateDialogPanelByWindowInsets(rootWindowInsets);
                }
                this.mDialogRootView.post(new Runnable() { // from class: miuix.appcompat.app.AlertController.9
                    @Override // java.lang.Runnable
                    public void run() {
                        WindowInsets rootWindowInsets2 = AlertController.this.mWindow.getDecorView().getRootWindowInsets();
                        if (rootWindowInsets2 != null) {
                            AlertController.this.updateDialogPanelByWindowInsets(rootWindowInsets2);
                        }
                    }
                });
            }
            this.mParentPanel.setPanelMaxLimitHeight(getPanelMaxLimitHeight(null));
            AlertDialog.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigurationChangedListener;
            if (onConfigurationChangedListener != null) {
                onConfigurationChangedListener.onConfigurationChanged(this.mDialog, this.mParentPanel, configuration);
            }
        }
    }

    public void onDetachedFromWindow() {
        if (!AnimHelper.isDialogDebugInAndroidUIThreadEnabled()) {
            Folme.clean(this.mParentPanel, this.mDimBg);
            translateDialogPanel(0);
        }
        if (isDialogImmersive() && isAsyncInflatePanelEnabled()) {
            safeRemovePanelFromParent();
        }
    }

    public void onStart() {
        if (isDialogImmersive()) {
            if (this.mDimBg != null) {
                updateDimBgBottomMargin(0);
            }
            reInitLandConfig();
            updateWindowCutoutMode();
            if (this.mIsFromRebuild || !this.mEnableEnterAnim) {
                this.mParentPanel.setTag(null);
                this.mDimBg.setAlpha(ViewUtils.isNightMode(this.mContext) ? DimToken.DIM_DARK : DimToken.DIM_LIGHT);
            } else {
                this.mDialogAnimHelper.setDiscardImeAnimEnabled(this.mDiscardImeAnimEnabled);
                this.mDialogAnimHelper.executeShowAnim(this.mParentPanel, this.mDimBg, isTablet(), this.mIsWindowLandScape, this.mShowAnimListenerWrapper);
            }
            this.mLayoutChangeListener.updateLayout(this.mWindow.getDecorView());
            this.mWindow.getDecorView().addOnLayoutChangeListener(this.mLayoutChangeListener);
        }
    }

    public void onStop() {
        if (isDialogImmersive()) {
            this.mWindow.getDecorView().removeOnLayoutChangeListener(this.mLayoutChangeListener);
        }
    }

    public void replaceView(int i2, boolean z2) {
        clearCustomContent();
        this.mView = null;
        this.mViewLayoutResId = i2;
        onConfigurationChanged(this.mContext.getResources().getConfiguration(), true, z2);
    }

    public void safeMoveView(View view, ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) view.getParent();
        if (viewGroup2 == viewGroup) {
            return;
        }
        if (viewGroup2 != null) {
            viewGroup2.removeView(view);
        }
        viewGroup.addView(view);
    }

    public void safeRemoveFromParent(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public void safeRemovePanelFromParent() {
        if (this.mParentPanel == null) {
            return;
        }
        if (isAsyncInflatePanelEnabled()) {
            this.mParentPanel.setTag(null);
        }
        ViewGroup viewGroup = (ViewGroup) this.mParentPanel.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.mParentPanel);
        }
    }

    public void setAsyncInflatePanelEnable(boolean z2) {
        this.mAsyncInflatePanelEnabled = z2;
    }

    public void setButton(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.mHandler.obtainMessage(i2, onClickListener);
        }
        if (message != null && message.getTarget() == null) {
            message.setTarget(this.mHandler);
        }
        if (i2 == -3) {
            this.mButtonNeutralText = charSequence;
            this.mButtonNeutralMessage = message;
        } else if (i2 == -2) {
            this.mButtonNegativeText = charSequence;
            this.mButtonNegativeMessage = message;
        } else {
            if (i2 != -1) {
                throw new IllegalArgumentException("Button does not exist");
            }
            this.mButtonPositiveText = charSequence;
            this.mButtonPositiveMessage = message;
        }
    }

    public void setButtonForceVertical(boolean z2) {
        this.mButtonForceVertical = z2;
    }

    public void setCancelable(boolean z2) {
        this.mCancelable = z2;
    }

    public void setCanceledOnTouchOutside(boolean z2) {
        this.mCanceledOnTouchOutside = z2;
    }

    public void setCheckBox(boolean z2, CharSequence charSequence) {
        this.mIsChecked = z2;
        this.mCheckBoxMessage = charSequence;
    }

    public void setComment(CharSequence charSequence) {
        this.mComment = charSequence;
        TextView textView = this.mCommentView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setConfigurationChangedListener(AlertDialog.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigurationChangedListener = onConfigurationChangedListener;
    }

    public void setCustomPanelSize(final ViewGroup.LayoutParams layoutParams) {
        DialogParentPanel2 dialogParentPanel2 = this.mParentPanel;
        if (dialogParentPanel2 == null || layoutParams == null) {
            throw new IllegalArgumentException("mParentPanel or layoutParams is null");
        }
        dialogParentPanel2.post(new Runnable() { // from class: miuix.appcompat.app.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f6021a.lambda$setCustomPanelSize$3(layoutParams);
            }
        });
        if (isDialogImmersive()) {
            return;
        }
        WindowManager.LayoutParams attributes = this.mWindow.getAttributes();
        attributes.width = layoutParams.width;
        attributes.height = layoutParams.height;
        this.mWindow.setAttributes(attributes);
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setDiscardImeAnimEnabled(boolean z2) {
        this.mDiscardImeAnimEnabled = z2;
    }

    public void setEnableEnterAnim(boolean z2) {
        this.mEnableEnterAnim = z2;
    }

    public void setEnableImmersive(boolean z2) {
        this.mIsEnableImmersive = z2;
    }

    public void setIcon(int i2) {
        this.mIcon = null;
        this.mIconId = i2;
    }

    public void setIconSize(int i2, int i3) {
        this.mIconWidth = i2;
        this.mIconHeight = i3;
    }

    public void setLayoutReloadListener(AlertDialog.OnDialogLayoutReloadListener onDialogLayoutReloadListener) {
        this.mLayoutReloadListener = onDialogLayoutReloadListener;
    }

    public void setLiteVersion(int i2) {
        this.mLiteVersion = i2;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        TextView textView = this.mMessageView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setNavigationHiddenEnabled(boolean z2) {
        this.mNavigationBarHiddenEnabled = z2;
    }

    public void setNonImmersiveDialogHeight(int i2) {
        this.mNonImmersiveDialogHeight = i2;
    }

    public void setPanelFixedHeight(int i2) {
        this.mPanelFixedHeight = i2;
    }

    public void setPanelFixedSizeEnabled(boolean z2) {
        this.mPanelFixedSizeEnabled = z2;
    }

    public void setPanelFixedWidth(int i2) {
        this.mPanelFixedWidth = i2;
    }

    public void setPanelSizeChangedListener(AlertDialog.OnPanelSizeChangedListener onPanelSizeChangedListener) {
        this.mPanelSizeChangedListener = onPanelSizeChangedListener;
    }

    public void setPendingDismiss(boolean z2) {
        this.mHasPendingDismiss = z2;
    }

    public void setPreferLandscape(boolean z2) {
        this.mPreferLandscape = z2;
    }

    public void setPrimaryButtonFirstEnabled(boolean z2) {
        this.mPrimaryButtonFirstEnabled = z2;
    }

    public void setShowAnimListener(AlertDialog.OnDialogShowAnimListener onDialogShowAnimListener) {
        this.mShowAnimListener = onDialogShowAnimListener;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public void setUseForceFlipCutout(boolean z2) {
        this.mUseForceFlipCutout = z2;
    }

    public void setUseSmallIcon(boolean z2) {
        this.mSmallIcon = z2;
    }

    public void setView(int i2) {
        this.mView = null;
        this.mViewLayoutResId = i2;
    }

    private void setupView(boolean z2, boolean z3, boolean z4, final float f2) {
        ListAdapter listAdapter;
        if (isDialogImmersive()) {
            this.mDimBg.setOnClickListener(new View.OnClickListener() { // from class: miuix.appcompat.app.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f6023a.lambda$setupView$0(view);
                }
            });
            updateImmersiveDialogPanel();
        } else {
            if (isSpecifiedDialogHeight()) {
                this.mDialogRootView.setOnClickListener(new View.OnClickListener() { // from class: miuix.appcompat.app.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f6024a.lambda$setupView$1(view);
                    }
                });
            }
            this.mDimBg.setVisibility(8);
        }
        if (!z2 && !z3 && !this.mPreferLandscape) {
            this.mParentPanel.post(new Runnable() { // from class: miuix.appcompat.app.AlertController.6
                @Override // java.lang.Runnable
                public void run() {
                    ViewGroup viewGroup = (ViewGroup) AlertController.this.mParentPanel.findViewById(miuix.appcompat.R.id.contentPanel);
                    ViewGroup viewGroup2 = (ViewGroup) AlertController.this.mParentPanel.findViewById(miuix.appcompat.R.id.buttonPanel);
                    if (viewGroup != null) {
                        AlertController.this.updateContent(viewGroup);
                        if (viewGroup2 != null && !AlertController.this.mPreferLandscape) {
                            AlertController.this.updateButtons(viewGroup2, viewGroup);
                        }
                    }
                    float f3 = f2;
                    if (f3 != 1.0f) {
                        AlertController.this.updateViewOnDensityChanged(f3);
                    }
                }
            });
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mParentPanel.findViewById(miuix.appcompat.R.id.topPanel);
            ViewGroup viewGroup2 = (ViewGroup) this.mParentPanel.findViewById(miuix.appcompat.R.id.contentPanel);
            ViewGroup viewGroup3 = (ViewGroup) this.mParentPanel.findViewById(miuix.appcompat.R.id.buttonPanel);
            if (viewGroup2 != null) {
                setupContent(viewGroup2, z4);
            }
            if (viewGroup3 instanceof DialogButtonPanel) {
                DialogButtonPanel dialogButtonPanel = (DialogButtonPanel) viewGroup3;
                dialogButtonPanel.isContentLandscape(shouldUseLandscapePanel());
                dialogButtonPanel.setPrimaryButtonFirstEnabled(this.mPrimaryButtonFirstEnabled);
                setupButtons(viewGroup3);
            }
            if (viewGroup != null) {
                setupTitle(viewGroup);
            }
            if (viewGroup != null && viewGroup.getVisibility() != 8) {
                View viewFindViewById = (this.mMessage == null && this.mListView == null) ? null : viewGroup.findViewById(miuix.appcompat.R.id.titleDividerNoCustom);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(0);
                }
            }
            ListView listView = this.mListView;
            if (listView != null && (listAdapter = this.mAdapter) != null) {
                listView.setAdapter(listAdapter);
                int i2 = this.mCheckedItem;
                if (i2 > -1) {
                    listView.setItemChecked(i2, true);
                    listView.setSelection(i2);
                }
            }
            ViewStub viewStub = (ViewStub) this.mParentPanel.findViewById(miuix.appcompat.R.id.checkbox_stub);
            if (viewStub != null) {
                setupCheckbox(this.mParentPanel, viewStub);
            }
            if (!z2) {
                onLayoutReload();
            }
        }
        this.mParentPanel.post(new Runnable() { // from class: miuix.appcompat.app.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f6025a.lambda$setupView$2();
            }
        });
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewLayoutResId = 0;
    }

    public void replaceView(View view, boolean z2) {
        clearCustomContent();
        this.mView = view;
        this.mViewLayoutResId = 0;
        onConfigurationChanged(this.mContext.getResources().getConfiguration(), true, z2);
    }

    public static class ButtonInfo {
        private GroupButton mButton;
        private Message mMsg;
        private final DialogInterface.OnClickListener mOnClickListener;
        private final int mStyle;
        private final CharSequence mText;
        private final int mWhich;

        public ButtonInfo(CharSequence charSequence, int i2, Message message) {
            this.mText = charSequence;
            this.mStyle = i2;
            this.mMsg = message;
            this.mOnClickListener = null;
            this.mWhich = 0;
        }

        public ButtonInfo(CharSequence charSequence, int i2, DialogInterface.OnClickListener onClickListener, int i3) {
            this.mText = charSequence;
            this.mStyle = i2;
            this.mMsg = null;
            this.mOnClickListener = onClickListener;
            this.mWhich = i3;
        }
    }

    @Deprecated
    private void setupCheckbox(CheckBox checkBox) {
        if (this.mCheckBoxMessage != null) {
            checkBox.setVisibility(0);
            checkBox.setChecked(this.mIsChecked);
            checkBox.setText(this.mCheckBoxMessage);
            return;
        }
        checkBox.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRootViewSize(View view) {
        this.mRootViewSize.x = view.getWidth();
        this.mRootViewSize.y = view.getHeight();
        float f2 = this.mContext.getResources().getDisplayMetrics().density;
        Point point = this.mRootViewSizeDp;
        Point point2 = this.mRootViewSize;
        point.x = (int) (point2.x / f2);
        point.y = (int) (point2.y / f2);
        if (this.mIsDebugEnabled) {
            Log.d(TAG, "updateRootViewSize by view mRootViewSizeDp " + this.mRootViewSizeDp + " mRootViewSize " + this.mRootViewSize + " configuration.density " + f2);
        }
    }
}
