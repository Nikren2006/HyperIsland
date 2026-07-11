package miuix.appcompat.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.ArrayRes;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.executor.DefaultTaskExecutor;
import androidx.arch.core.executor.TaskExecutor;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miuix.appcompat.R;
import miuix.appcompat.app.AlertController;
import miuix.appcompat.internal.widget.DialogParentPanel2;
import miuix.appcompat.widget.DialogAnimHelper;
import miuix.autodensity.DensityUtil;
import miuix.core.util.EnvStateManager;
import miuix.core.util.RomUtils;
import miuix.internal.widget.ActionSheet;
import miuix.reflect.ReflectionHelper;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes2.dex */
public class AlertDialog extends AppCompatDialog implements DialogInterface {
    public static final int[] DIALOG_CONTENT_LAYOUT = {R.layout.miuix_appcompat_alert_dialog_content, R.layout.miuix_appcompat_alert_dialog_content_land};
    public static final String KEY_BUTTON_CLICK_AUTO_DISMISSIBLE = "BUTTON_CLICK_AUTO_DISMISSIBLE";
    final AlertController mAlert;
    protected LifecycleOwnerCompat mLifecycleOwnerCompat;
    protected DialogAnimHelper.OnDismiss mOnDismiss;
    private CharSequence mShowDescription;

    public static class Builder {

        /* JADX INFO: renamed from: P, reason: collision with root package name */
        private final AlertController.AlertParams f6019P;
        private boolean mActionSheetEnabled;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, 0));
        }

        private AlertDialog createActionSheet() {
            ActionSheet.Builder builder = new ActionSheet.Builder(this.f6019P.mContext);
            if (!TextUtils.isEmpty(this.f6019P.mMessage)) {
                builder.setMessage(this.f6019P.mMessage);
            }
            if (!TextUtils.isEmpty(this.f6019P.mTitle)) {
                builder.setMessage(this.f6019P.mTitle);
            }
            AlertController.AlertParams alertParams = this.f6019P;
            builder.setActionItems(alertParams.mItems, alertParams.mOnClickListener);
            if (!TextUtils.isEmpty(this.f6019P.mNegativeButtonText)) {
                builder.setSeparateText(this.f6019P.mNegativeButtonText);
            }
            DialogInterface.OnClickListener onClickListener = this.f6019P.mNegativeButtonListener;
            if (onClickListener != null) {
                builder.setSeparateClickListener(onClickListener);
            }
            OnDialogShowAnimListener onDialogShowAnimListener = this.f6019P.mOnDialogShowAnimListener;
            if (onDialogShowAnimListener != null) {
                builder.setShowAnimListener(onDialogShowAnimListener);
            }
            DialogInterface.OnShowListener onShowListener = this.f6019P.mOnShowListener;
            if (onShowListener != null) {
                builder.setOnShowListener(onShowListener);
            }
            DialogInterface.OnDismissListener onDismissListener = this.f6019P.mOnDismissListener;
            if (onDismissListener != null) {
                builder.setOnDismissListener(onDismissListener);
            }
            DialogInterface.OnKeyListener onKeyListener = this.f6019P.mOnKeyListener;
            if (onKeyListener != null) {
                builder.setOnKeyListener(onKeyListener);
            }
            ListAdapter listAdapter = this.f6019P.mAdapter;
            if (listAdapter != null) {
                builder.setListViewAdapter(listAdapter);
            }
            AlertDialog alertDialog = (AlertDialog) builder.create();
            if (this.f6019P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            DialogInterface.OnCancelListener onCancelListener = this.f6019P.mOnCancelListener;
            if (onCancelListener != null) {
                alertDialog.setOnCancelListener(onCancelListener);
            }
            return alertDialog;
        }

        private AlertDialog createAlertDialog() {
            AlertDialog alertDialog = new AlertDialog(this.f6019P.mContext, this.mTheme);
            this.f6019P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f6019P.mCancelable);
            if (this.f6019P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f6019P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f6019P.mOnDismissListener);
            alertDialog.setOnShowListener(this.f6019P.mOnShowListener);
            alertDialog.setOnShowAnimListener(this.f6019P.mOnDialogShowAnimListener);
            DialogInterface.OnKeyListener onKeyListener = this.f6019P.mOnKeyListener;
            if (onKeyListener != null) {
                alertDialog.setOnKeyListener(onKeyListener);
            }
            return alertDialog;
        }

        public Builder addButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener, int i2) {
            this.f6019P.mExtraButtonList.add(new AlertController.ButtonInfo(charSequence, android.R.attr.buttonBarButtonStyle, onClickListener, i2));
            return this;
        }

        public Builder addNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener, int i2) {
            this.f6019P.mExtraButtonList.add(new AlertController.ButtonInfo(charSequence, android.R.attr.buttonBarNegativeButtonStyle, onClickListener, i2));
            return this;
        }

        public Builder addNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener, int i2) {
            this.f6019P.mExtraButtonList.add(new AlertController.ButtonInfo(charSequence, android.R.attr.buttonBarNeutralButtonStyle, onClickListener, i2));
            return this;
        }

        public Builder addPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener, int i2) {
            this.f6019P.mExtraButtonList.add(new AlertController.ButtonInfo(charSequence, android.R.attr.buttonBarPositiveButtonStyle, onClickListener, i2));
            return this;
        }

        public Builder clearButtons() {
            this.f6019P.mExtraButtonList.clear();
            return this;
        }

        @NonNull
        public AlertDialog create() {
            AlertController.AlertParams alertParams = this.f6019P;
            boolean z2 = false;
            boolean z3 = (alertParams.mItems == null || alertParams.mIsSingleChoice || alertParams.mIsMultiChoice) ? false : true;
            if (alertParams.mView == null && alertParams.mViewLayoutResId == 0) {
                z2 = true;
            }
            return (this.mActionSheetEnabled && z3 && z2) ? createActionSheet() : createAlertDialog();
        }

        @NonNull
        public Context getContext() {
            return this.f6019P.mContext;
        }

        public Builder setActionSheetEnabled(boolean z2) {
            this.mActionSheetEnabled = z2;
            return this;
        }

        public Builder setAdapter(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAsyncInflatePanelEnabled(boolean z2) {
            this.f6019P.mAsyncInflatePanelEnabled = z2;
            return this;
        }

        public Builder setButtonForceVertical(boolean z2) {
            this.f6019P.mButtonForceVertical = z2;
            return this;
        }

        public Builder setCancelable(boolean z2) {
            this.f6019P.mCancelable = z2;
            return this;
        }

        public Builder setCheckBox(boolean z2, CharSequence charSequence) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mIsChecked = z2;
            alertParams.mCheckBoxMessage = charSequence;
            return this;
        }

        public Builder setComment(@StringRes int i2) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mComment = alertParams.mContext.getText(i2);
            return this;
        }

        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mCursor = cursor;
            alertParams.mLabelColumn = str;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCustomTitle(@Nullable View view) {
            this.f6019P.mCustomTitleView = view;
            return this;
        }

        public Builder setDiscardImeAnimEnabled(boolean z2) {
            this.f6019P.mDiscardImeAnimEnabled = z2;
            return this;
        }

        public Builder setEnableDialogImmersive(boolean z2) {
            this.f6019P.mEnableDialogImmersive = z2;
            return this;
        }

        public Builder setEnableEnterAnim(boolean z2) {
            this.f6019P.mEnableEnterAnim = z2;
            return this;
        }

        public Builder setHapticFeedbackEnabled(boolean z2) {
            this.f6019P.mHapticFeedbackEnabled = z2;
            return this;
        }

        public Builder setIcon(@DrawableRes int i2) {
            this.f6019P.mIconId = i2;
            return this;
        }

        public Builder setIconAttribute(@AttrRes int i2) {
            TypedValue typedValue = new TypedValue();
            this.f6019P.mContext.getTheme().resolveAttribute(i2, typedValue, true);
            this.f6019P.mIconId = typedValue.resourceId;
            return this;
        }

        public Builder setIconSize(int i2, int i3) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.iconWidth = i2;
            alertParams.iconHeight = i3;
            return this;
        }

        public Builder setItems(@ArrayRes int i2, DialogInterface.OnClickListener onClickListener) {
            if (RomUtils.getHyperOsVersion() > 2) {
                this.mActionSheetEnabled = true;
            }
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i2);
            this.f6019P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItemsAccessibility(AccessibilityDelegateProvider accessibilityDelegateProvider) {
            this.f6019P.mItemsProvider = accessibilityDelegateProvider;
            return this;
        }

        public Builder setMessage(@StringRes int i2) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mMessage = alertParams.mContext.getText(i2);
            return this;
        }

        public Builder setMultiChoiceItems(@ArrayRes int i2, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i2);
            AlertController.AlertParams alertParams2 = this.f6019P;
            alertParams2.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams2.mCheckedItems = zArr;
            alertParams2.mIsMultiChoice = true;
            return this;
        }

        public Builder setNegativeButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mNegativeButtonText = alertParams.mContext.getText(i2);
            this.f6019P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mNeutralButtonText = alertParams.mContext.getText(i2);
            this.f6019P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNonImmersiveDialogHeight(int i2) {
            this.f6019P.mNonImmersiveDialogHeight = i2;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
            this.f6019P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
            this.f6019P.mConfigurationChangedListener = onConfigurationChangedListener;
            return this;
        }

        public Builder setOnDialogShowAnimListener(OnDialogShowAnimListener onDialogShowAnimListener) {
            this.f6019P.mOnDialogShowAnimListener = onDialogShowAnimListener;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.f6019P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f6019P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
            this.f6019P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setOnPanelSizeChangedListener(OnPanelSizeChangedListener onPanelSizeChangedListener) {
            this.f6019P.mPanelSizeChangedListener = onPanelSizeChangedListener;
            return this;
        }

        public Builder setOnShowListener(DialogInterface.OnShowListener onShowListener) {
            this.f6019P.mOnShowListener = onShowListener;
            return this;
        }

        public Builder setPositiveButton(@StringRes int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mPositiveButtonText = alertParams.mContext.getText(i2);
            this.f6019P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPreferLandscape(boolean z2) {
            this.f6019P.mPreferLandscape = z2;
            return this;
        }

        public Builder setPrimaryButtonFirstEnabled(boolean z2) {
            this.f6019P.mPrimaryButtonFirstEnabled = z2;
            return this;
        }

        @Deprecated
        public Builder setRelayoutWhenSwitchToLandscape(boolean z2) {
            this.f6019P.mPreferLandscape = z2;
            return this;
        }

        public Builder setSingleChoiceItems(@ArrayRes int i2, int i3, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = alertParams.mContext.getResources().getTextArray(i2);
            AlertController.AlertParams alertParams2 = this.f6019P;
            alertParams2.mOnClickListener = onClickListener;
            alertParams2.mCheckedItem = i3;
            alertParams2.mIsSingleChoice = true;
            return this;
        }

        public Builder setTitle(@StringRes int i2) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mTitle = alertParams.mContext.getText(i2);
            return this;
        }

        public Builder setUseForceFlipCutout(boolean z2) {
            this.f6019P.mUseForceFlipCutout = z2;
            return this;
        }

        public Builder setUseLiteDrawable(boolean z2) {
            this.f6019P.mLiteVersion = z2 ? DynamicIslandConstants.USER_XSPACE : 0;
            return this;
        }

        public Builder setView(int i2) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mView = null;
            alertParams.mViewLayoutResId = i2;
            return this;
        }

        public AlertDialog show() {
            AlertDialog alertDialogCreate = create();
            alertDialogCreate.show();
            return alertDialogCreate;
        }

        public Builder useSmallIcon(boolean z2) {
            this.f6019P.mSmallIcon = z2;
            return this;
        }

        public Builder(@NonNull Context context, @StyleRes int i2) {
            this.mActionSheetEnabled = false;
            this.f6019P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i2)));
            this.mTheme = i2;
        }

        public Builder setComment(@Nullable CharSequence charSequence) {
            this.f6019P.mComment = charSequence;
            return this;
        }

        public Builder setIcon(@Nullable Drawable drawable) {
            this.f6019P.mIcon = drawable;
            return this;
        }

        public Builder setMessage(@Nullable CharSequence charSequence) {
            this.f6019P.mMessage = charSequence;
            return this;
        }

        public Builder setTitle(@Nullable CharSequence charSequence) {
            this.f6019P.mTitle = charSequence;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mNegativeButtonText = charSequence;
            alertParams.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mNeutralButtonText = charSequence;
            alertParams.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mPositiveButtonText = charSequence;
            alertParams.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setView(View view) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mView = view;
            alertParams.mViewLayoutResId = 0;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            if (RomUtils.getHyperOsVersion() > 2) {
                this.mActionSheetEnabled = true;
            }
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mCheckedItems = zArr;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i2, String str, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mCursor = cursor;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i2;
            alertParams.mLabelColumn = str;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mCursor = cursor;
            alertParams.mOnCheckboxClickListener = onMultiChoiceClickListener;
            alertParams.mIsCheckedColumn = str;
            alertParams.mLabelColumn = str2;
            alertParams.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mItems = charSequenceArr;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i2;
            alertParams.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i2, DialogInterface.OnClickListener onClickListener) {
            AlertController.AlertParams alertParams = this.f6019P;
            alertParams.mAdapter = listAdapter;
            alertParams.mOnClickListener = onClickListener;
            alertParams.mCheckedItem = i2;
            alertParams.mIsSingleChoice = true;
            return this;
        }
    }

    public class LifecycleOwnerCompat {

        @Nullable
        private Object mOriginalExecutor;
        private TaskExecutor mSpecialUiExecutor;

        public LifecycleOwnerCompat() {
        }

        @SuppressLint({"RestrictedApi"})
        private TaskExecutor createSpecialUiTaskExecutor() {
            return new DefaultTaskExecutor() { // from class: miuix.appcompat.app.AlertDialog.LifecycleOwnerCompat.1
                private final Object mLock = new Object();

                @Nullable
                private volatile Handler mSpecialMainHandler;

                private Handler createAsync(@NonNull Looper looper) {
                    return Handler.createAsync(looper);
                }

                @Override // androidx.arch.core.executor.DefaultTaskExecutor, androidx.arch.core.executor.TaskExecutor
                public boolean isMainThread() {
                    return true;
                }

                @Override // androidx.arch.core.executor.DefaultTaskExecutor, androidx.arch.core.executor.TaskExecutor
                public void postToMainThread(Runnable runnable) {
                    if (this.mSpecialMainHandler == null) {
                        synchronized (this.mLock) {
                            try {
                                if (this.mSpecialMainHandler == null) {
                                    this.mSpecialMainHandler = createAsync(Looper.myLooper());
                                }
                            } finally {
                            }
                        }
                    }
                    this.mSpecialMainHandler.post(runnable);
                }
            };
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v3, types: [androidx.arch.core.executor.TaskExecutor] */
        @SuppressLint({"RestrictedApi"})
        public void onCreate() {
            try {
                try {
                    try {
                        Object fieldValue = ReflectionHelper.getFieldValue(ArchTaskExecutor.class, ArchTaskExecutor.getInstance(), "mDelegate");
                        if (fieldValue != null) {
                            this.mOriginalExecutor = fieldValue;
                        }
                    } catch (IllegalAccessException e2) {
                        Log.d("MiuixDialog", "onCreate() taskExecutor get failed IllegalAccessException " + e2);
                    }
                } catch (NoSuchMethodException e3) {
                    Log.d("MiuixDialog", "onCreate() taskExecutor get failed NoSuchMethodException " + e3);
                } catch (InvocationTargetException e4) {
                    Log.d("MiuixDialog", "onCreate() taskExecutor get failed InvocationTargetException " + e4);
                }
            } finally {
                this.mSpecialUiExecutor = this.createSpecialUiTaskExecutor();
                ArchTaskExecutor.getInstance().setDelegate(this.mSpecialUiExecutor);
            }
        }

        @SuppressLint({"RestrictedApi"})
        public void onStopAfter() {
            if (this.mOriginalExecutor instanceof TaskExecutor) {
                ArchTaskExecutor.getInstance().setDelegate((TaskExecutor) this.mOriginalExecutor);
            }
        }

        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
        /* JADX WARN: Type inference failed for: r4v5 */
        /* JADX WARN: Type inference failed for: r4v6 */
        /* JADX WARN: Type inference failed for: r4v7 */
        /* JADX WARN: Type inference failed for: r4v9 */
        @android.annotation.SuppressLint({"RestrictedApi"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onStopBefore() {
            /*
                r4 = this;
                java.lang.String r0 = "MiuixDialog"
                java.lang.Class<androidx.arch.core.executor.ArchTaskExecutor> r1 = androidx.arch.core.executor.ArchTaskExecutor.class
                androidx.arch.core.executor.ArchTaskExecutor r2 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()     // Catch: java.lang.Throwable -> L2f java.lang.reflect.InvocationTargetException -> L32 java.lang.NoSuchMethodException -> L34 java.lang.IllegalAccessException -> L36
                java.lang.String r3 = "mDelegate"
                java.lang.Object r0 = miuix.reflect.ReflectionHelper.getFieldValue(r1, r2, r3)     // Catch: java.lang.Throwable -> L2f java.lang.reflect.InvocationTargetException -> L32 java.lang.NoSuchMethodException -> L34 java.lang.IllegalAccessException -> L36
                if (r0 == 0) goto L16
                java.lang.Object r1 = r4.mOriginalExecutor
                if (r0 == r1) goto L16
                r4.mOriginalExecutor = r0
            L16:
                androidx.arch.core.executor.TaskExecutor r1 = r4.mSpecialUiExecutor
                if (r0 != r1) goto L24
                androidx.arch.core.executor.ArchTaskExecutor r0 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                boolean r0 = r0.isMainThread()
                if (r0 != 0) goto La1
            L24:
                androidx.arch.core.executor.ArchTaskExecutor r0 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                androidx.arch.core.executor.TaskExecutor r4 = r4.mSpecialUiExecutor
                r0.setDelegate(r4)
                goto La1
            L2f:
                r0 = move-exception
                goto La2
            L32:
                r1 = move-exception
                goto L38
            L34:
                r1 = move-exception
                goto L5b
            L36:
                r1 = move-exception
                goto L7e
            L38:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
                r2.<init>()     // Catch: java.lang.Throwable -> L2f
                java.lang.String r3 = "onStop() taskExecutor get failed InvocationTargetException "
                r2.append(r3)     // Catch: java.lang.Throwable -> L2f
                r2.append(r1)     // Catch: java.lang.Throwable -> L2f
                java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L2f
                android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2f
                androidx.arch.core.executor.TaskExecutor r0 = r4.mSpecialUiExecutor
                if (r0 != 0) goto L24
                androidx.arch.core.executor.ArchTaskExecutor r0 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                boolean r0 = r0.isMainThread()
                if (r0 != 0) goto La1
                goto L24
            L5b:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
                r2.<init>()     // Catch: java.lang.Throwable -> L2f
                java.lang.String r3 = "onStop() taskExecutor get failed NoSuchMethodException "
                r2.append(r3)     // Catch: java.lang.Throwable -> L2f
                r2.append(r1)     // Catch: java.lang.Throwable -> L2f
                java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L2f
                android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2f
                androidx.arch.core.executor.TaskExecutor r0 = r4.mSpecialUiExecutor
                if (r0 != 0) goto L24
                androidx.arch.core.executor.ArchTaskExecutor r0 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                boolean r0 = r0.isMainThread()
                if (r0 != 0) goto La1
                goto L24
            L7e:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
                r2.<init>()     // Catch: java.lang.Throwable -> L2f
                java.lang.String r3 = "onStop() taskExecutor get failed IllegalAccessException "
                r2.append(r3)     // Catch: java.lang.Throwable -> L2f
                r2.append(r1)     // Catch: java.lang.Throwable -> L2f
                java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L2f
                android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2f
                androidx.arch.core.executor.TaskExecutor r0 = r4.mSpecialUiExecutor
                if (r0 != 0) goto L24
                androidx.arch.core.executor.ArchTaskExecutor r0 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                boolean r0 = r0.isMainThread()
                if (r0 != 0) goto La1
                goto L24
            La1:
                return
            La2:
                androidx.arch.core.executor.TaskExecutor r1 = r4.mSpecialUiExecutor
                if (r1 != 0) goto Lb0
                androidx.arch.core.executor.ArchTaskExecutor r1 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                boolean r1 = r1.isMainThread()
                if (r1 != 0) goto Lb9
            Lb0:
                androidx.arch.core.executor.ArchTaskExecutor r1 = androidx.arch.core.executor.ArchTaskExecutor.getInstance()
                androidx.arch.core.executor.TaskExecutor r4 = r4.mSpecialUiExecutor
                r1.setDelegate(r4)
            Lb9:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: miuix.appcompat.app.AlertDialog.LifecycleOwnerCompat.onStopBefore():void");
        }
    }

    public interface OnConfigurationChangedListener {
        void onConfigurationChanged(AppCompatDialog appCompatDialog, DialogParentPanel2 dialogParentPanel2, Configuration configuration);
    }

    public interface OnDialogLayoutReloadListener {
        void onLayoutReload();
    }

    public interface OnDialogShowAnimListener {
        void onShowAnimComplete();

        void onShowAnimStart();
    }

    public interface OnPanelSizeChangedListener {
        void onPanelSizeChanged(AlertDialog alertDialog, DialogParentPanel2 dialogParentPanel2);
    }

    public AlertDialog(@NonNull Context context) {
        this(context, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$dismissWithAnimationExistDecorView$2() {
        this.mAlert.dismiss(this.mOnDismiss);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        View decorView;
        if (getWindow() == null || (decorView = getWindow().getDecorView()) == null || !decorView.isAttachedToWindow()) {
            return;
        }
        realDismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$show$0() {
        if (this.mAlert.hasPendingDismiss()) {
            dismiss();
        }
    }

    private Context parseContext(Context context) {
        return (context != null && context.getClass() == ContextThemeWrapper.class) ? context : getContext();
    }

    public static int resolveDialogTheme(@NonNull Context context, @StyleRes int i2) {
        if (((i2 >>> 24) & 255) >= 1) {
            return i2;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.miuiAlertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public void addExtraButton(CharSequence charSequence, @AttrRes int i2, Message message) {
        this.mAlert.addExtraButton(new AlertController.ButtonInfo(charSequence, i2, message));
    }

    public void clearExtraButton() {
        this.mAlert.clearExtraButton();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (!this.mAlert.isDialogImmersive() && this.mAlert.isAsyncInflatePanelEnabled()) {
            this.mAlert.safeRemovePanelFromParent();
        }
        View decorView = getWindow().getDecorView();
        if (this.mAlert.isShowingAnimation()) {
            this.mAlert.setPendingDismiss(true);
            return;
        }
        this.mAlert.setPendingDismiss(false);
        if (DensityUtil.findAutoDensityContextWrapper(decorView.getContext()) != null) {
            EnvStateManager.removeInfoOfContext(decorView.getContext());
        }
        if (!this.mAlert.isDialogImmersive()) {
            dismissIfAttachedToWindow(decorView);
            return;
        }
        Activity associatedActivity = getAssociatedActivity();
        if (associatedActivity == null || !associatedActivity.isFinishing()) {
            dismissWithAnimationOrNot(decorView);
        } else {
            dismissIfAttachedToWindow(decorView);
        }
    }

    public void dismissIfAttachedToWindow(View view) {
        if (view == null || view.isAttachedToWindow()) {
            super.dismiss();
        }
    }

    public void dismissWithAnimationExistDecorView(View view) {
        if (Thread.currentThread() == view.getHandler().getLooper().getThread()) {
            this.mAlert.dismiss(this.mOnDismiss);
        } else {
            view.post(new Runnable() { // from class: miuix.appcompat.app.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f6044a.lambda$dismissWithAnimationExistDecorView$2();
                }
            });
        }
    }

    public void dismissWithAnimationOrNot(View view) {
        if (view == null) {
            super.dismiss();
        } else if (view.getHandler() != null) {
            dismissWithAnimationExistDecorView(view);
        } else {
            dismissIfAttachedToWindow(view);
        }
    }

    public void dismissWithoutAnimation() {
        View decorView = getWindow().getDecorView();
        if (getWindow().getDecorView().isAttachedToWindow()) {
            if (this.mAlert.isShowingAnimation()) {
                this.mAlert.setPendingDismiss(true);
                return;
            }
            this.mAlert.setPendingDismiss(false);
            if (DensityUtil.findAutoDensityContextWrapper(decorView.getContext()) != null) {
                EnvStateManager.removeInfoOfContext(decorView.getContext());
            }
            realDismiss();
        }
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mAlert.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public Activity getAssociatedActivity() {
        Activity ownerActivity = getOwnerActivity();
        Context context = getContext();
        while (ownerActivity == null && context != null) {
            if (context instanceof Activity) {
                ownerActivity = (Activity) context;
            } else {
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
        }
        return ownerActivity;
    }

    public Button getButton(int i2) {
        return this.mAlert.getButton(i2);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public TextView getMessageView() {
        return this.mAlert.getMessageView();
    }

    public boolean isChecked() {
        return this.mAlert.isChecked();
    }

    public boolean isSystemSpecialUiThread() {
        return TextUtils.equals("android.ui", Thread.currentThread().getName()) || TextUtils.equals("android.imms", Thread.currentThread().getName()) || TextUtils.equals("system_server", Thread.currentThread().getName());
    }

    public boolean miuixSuperDispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View decorView = getWindow().getDecorView();
        if (decorView != null && this.mAlert.mHapticFeedbackEnabled) {
            HapticCompat.performHapticFeedbackAsync(decorView, HapticFeedbackConstants.MIUI_ALERT, HapticFeedbackConstants.MIUI_POPUP_NORMAL);
        }
        this.mAlert.onAttachedToWindow();
        setAccessibilityDelegate(decorView);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        LifecycleOwnerCompat lifecycleOwnerCompat;
        if (isSystemSpecialUiThread() && (lifecycleOwnerCompat = this.mLifecycleOwnerCompat) != null) {
            lifecycleOwnerCompat.onCreate();
        }
        if (this.mAlert.isDialogImmersive() || !this.mAlert.mEnableEnterAnim) {
            getWindow().setWindowAnimations(0);
        }
        super.onCreate(bundle);
        this.mAlert.installContent(bundle);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAlert.onDetachedFromWindow();
    }

    public void onLayoutReload() {
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public void onStart() {
        super.onStart();
        this.mAlert.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onStop() {
        LifecycleOwnerCompat lifecycleOwnerCompat;
        LifecycleOwnerCompat lifecycleOwnerCompat2;
        if (isSystemSpecialUiThread() && (lifecycleOwnerCompat2 = this.mLifecycleOwnerCompat) != null) {
            lifecycleOwnerCompat2.onStopBefore();
        }
        super.onStop();
        this.mAlert.onStop();
        if (!isSystemSpecialUiThread() || (lifecycleOwnerCompat = this.mLifecycleOwnerCompat) == null) {
            return;
        }
        lifecycleOwnerCompat.onStopAfter();
    }

    public void realDismiss() {
        super.dismiss();
    }

    public void replaceView(int i2) {
        replaceView(i2, true);
    }

    public void setAccessibilityDelegate(View view) {
        if (view == null) {
            return;
        }
        ViewCompat.setAccessibilityPaneTitle(view, this.mShowDescription);
    }

    public void setButton(int i2, CharSequence charSequence, Message message) {
        this.mAlert.setButton(i2, charSequence, null, message);
    }

    public void setButtonForceVertical(boolean z2) {
        this.mAlert.setButtonForceVertical(z2);
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z2) {
        super.setCancelable(z2);
        this.mAlert.setCancelable(z2);
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z2) {
        super.setCanceledOnTouchOutside(z2);
        this.mAlert.setCanceledOnTouchOutside(z2);
    }

    public void setConfigurationChangedListener(OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mAlert.setConfigurationChangedListener(onConfigurationChangedListener);
    }

    public void setCustomPanelSize(ViewGroup.LayoutParams layoutParams) {
        this.mAlert.setCustomPanelSize(layoutParams);
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setEnableEnterAnim(boolean z2) {
        this.mAlert.setEnableEnterAnim(z2);
    }

    public void setEnableImmersive(boolean z2) {
        this.mAlert.setEnableImmersive(z2);
    }

    public void setHapticFeedbackEnabled(boolean z2) {
        this.mAlert.mHapticFeedbackEnabled = z2;
    }

    public void setIcon(int i2) {
        this.mAlert.setIcon(i2);
    }

    public void setIconAttribute(int i2) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i2, typedValue, true);
        this.mAlert.setIcon(typedValue.resourceId);
    }

    public void setIconSize(int i2, int i3) {
        this.mAlert.setIconSize(i2, i3);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    public void setNonImmersiveDialogHeight(int i2) {
        this.mAlert.setNonImmersiveDialogHeight(i2);
    }

    public void setOnLayoutReloadListener(OnDialogLayoutReloadListener onDialogLayoutReloadListener) {
        this.mAlert.setLayoutReloadListener(onDialogLayoutReloadListener);
    }

    public void setOnShowAnimListener(OnDialogShowAnimListener onDialogShowAnimListener) {
        this.mAlert.setShowAnimListener(onDialogShowAnimListener);
    }

    public void setPanelSizeChangedListener(OnPanelSizeChangedListener onPanelSizeChangedListener) {
        this.mAlert.setPanelSizeChangedListener(onPanelSizeChangedListener);
    }

    public void setPreferLandscape(boolean z2) {
        this.mAlert.setPreferLandscape(z2);
    }

    public void setPrimaryButtonFirstEnabled(boolean z2) {
        this.mAlert.setPrimaryButtonFirstEnabled(z2);
    }

    @Deprecated
    public void setRelayoutWhenSwitchToLandscape(boolean z2) {
        this.mAlert.setPreferLandscape(z2);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        this.mAlert.setTitle(charSequence);
    }

    public void setUseSmallIcon(boolean z2) {
        this.mAlert.setUseSmallIcon(z2);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    @Override // android.app.Dialog
    public void show() {
        this.mAlert.mShowUpTimeMillis = SystemClock.uptimeMillis();
        super.show();
        if (getWindow() == null || this.mAlert.isDialogImmersive()) {
            return;
        }
        getWindow().getDecorView().postDelayed(new Runnable() { // from class: miuix.appcompat.app.j
            @Override // java.lang.Runnable
            public final void run() {
                this.f6045a.lambda$show$0();
            }
        }, this.mAlert.mNonImmersiveDialogShowAnimDuration);
    }

    public void superOnAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void superOnCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void superOnDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void superOnStart() {
        super.onStart();
    }

    public void superOnStop() {
        super.onStop();
    }

    public void superShow() {
        super.show();
    }

    public AlertDialog(@NonNull Context context, @StyleRes int i2) {
        super(context, resolveDialogTheme(context, i2));
        this.mOnDismiss = new DialogAnimHelper.OnDismiss() { // from class: miuix.appcompat.app.h
            @Override // miuix.appcompat.widget.DialogAnimHelper.OnDismiss
            public final void end() {
                this.f6043a.lambda$new$1();
            }
        };
        Context context2 = parseContext(context);
        if (DensityUtil.findAutoDensityContextWrapper(context2) != null) {
            EnvStateManager.removeInfoOfContext(context);
        }
        this.mAlert = new AlertController(context2, this, getWindow());
        this.mLifecycleOwnerCompat = new LifecycleOwnerCompat();
        this.mShowDescription = context.getResources().getString(R.string.miuix_appcompat_show_dialog_description);
    }

    public void addExtraButton(CharSequence charSequence, @AttrRes int i2, DialogInterface.OnClickListener onClickListener, int i3) {
        this.mAlert.addExtraButton(new AlertController.ButtonInfo(charSequence, i2, onClickListener, i3));
    }

    public void replaceView(int i2, boolean z2) {
        this.mAlert.replaceView(i2, z2);
    }

    public void setButton(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.mAlert.setButton(i2, charSequence, onClickListener, null);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void replaceView(View view) {
        replaceView(view, true);
    }

    public void replaceView(View view, boolean z2) {
        this.mAlert.replaceView(view, z2);
    }

    public AlertDialog(@NonNull Context context, boolean z2, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        this(context, 0);
        setCancelable(z2);
        setOnCancelListener(onCancelListener);
    }
}
