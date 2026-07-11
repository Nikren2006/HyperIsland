package miuix.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import miuix.androidbasewidget.widget.CheckedTextView;
import miuix.appcompat.adapter.SpinnerDoubleLineContentAdapter;
import miuix.appcompat.app.AlertDialog;
import miuix.appcompat.app.IActivity;
import miuix.appcompat.internal.adapter.SpinnerCheckableArrayAdapter;
import miuix.internal.util.AnimHelper;
import miuix.internal.util.TaggingDrawableUtil;
import miuix.popupwidget.widget.PopupWindow;
import miuix.view.CompatViewMethod;
import miuix.view.Fence;
import miuix.view.HapticCompat;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes3.dex */
public class Spinner extends android.widget.Spinner {
    private static Field FORWARDING_LISTENER = null;
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MAX_ITEMS_SHOWN = 8;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "Spinner";
    private boolean mDisableChildrenWhenDisabled;
    int mDropDownMaxWidth;
    int mDropDownMinWidth;
    int mDropDownWidth;
    private float mLastDensity;
    private OnSpinnerDismissListener mOnSpinnerDismissListener;
    private SpinnerPopup mPopup;
    private final Context mPopupContext;
    private final boolean mPopupSet;
    private int mSelectedPosition;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;

    public static class DialogPopupAdapter extends DropDownAdapter {
        public DialogPopupAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Resources.Theme theme) {
            super(spinnerAdapter, theme);
        }
    }

    public static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;

        public DropDownAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Resources.Theme theme) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof androidx.appcompat.widget.ThemedSpinnerAdapter) {
                    androidx.appcompat.widget.ThemedSpinnerAdapter themedSpinnerAdapter2 = (androidx.appcompat.widget.ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                        themedSpinnerAdapter2.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i2, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i2);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i2) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View dropDownView = getDropDownView(i2, view, viewGroup);
            if (view == null) {
                AnimHelper.addItemPressEffect(dropDownView);
            }
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter instanceof SpinnerCheckableArrayAdapter) {
                ((SpinnerCheckableArrayAdapter) spinnerAdapter).setAccessibilityDelegate(dropDownView, i2);
            } else if (spinnerAdapter instanceof ArrayAdapter) {
                setAccessibilityDelegate(dropDownView);
            }
            return dropDownView;
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i2);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        public void setAccessibilityDelegate(View view) {
            view.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miuix.appcompat.widget.Spinner.DropDownAdapter.1
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfo);
                    CheckedTextView checkedTextView = (CheckedTextView) view2.findViewById(R.id.text1);
                    accessibilityNodeInfo.setClassName(Checkable.class.getName());
                    accessibilityNodeInfo.setCheckable(true);
                    if (checkedTextView != null) {
                        accessibilityNodeInfo.setChecked(checkedTextView.isChecked());
                        if (!checkedTextView.isChecked()) {
                            accessibilityNodeInfo.setClickable(true);
                        } else {
                            accessibilityNodeInfo.setClickable(false);
                            accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
                        }
                    }
                }
            });
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    public static class DropDownPopupAdapter extends DropDownAdapter {
        public DropDownPopupAdapter(@Nullable SpinnerAdapter spinnerAdapter, @Nullable Resources.Theme theme) {
            super(spinnerAdapter, theme);
        }

        @Override // miuix.appcompat.widget.Spinner.DropDownAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            TaggingDrawableUtil.updateItemBackground(view2, i2, getCount());
            return view2;
        }
    }

    public interface OnSpinnerDismissListener {
        void onSpinnerDismiss();
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: miuix.appcompat.widget.Spinner.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        boolean mShowDropdown;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeByte(this.mShowDropdown ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mShowDropdown = parcel.readByte() != 0;
        }
    }

    public static class SpinnerCheckedProvider implements SpinnerCheckableArrayAdapter.CheckedStateProvider {
        private Spinner mSpinner;

        public SpinnerCheckedProvider(Spinner spinner) {
            this.mSpinner = spinner;
        }

        @Override // miuix.appcompat.internal.adapter.SpinnerCheckableArrayAdapter.CheckedStateProvider
        public boolean isChecked(int i2) {
            return this.mSpinner.getSelectedItemPosition() == i2;
        }
    }

    public interface SpinnerPopup {
        void dismiss();

        void enableHideSoftInput(boolean z2);

        Drawable getBackground();

        CharSequence getHintText();

        int getHorizontalOffset();

        int getHorizontalOriginalOffset();

        int getVerticalOffset();

        boolean isShowing();

        void setAdapter(ListAdapter listAdapter);

        void setBackgroundDrawable(Drawable drawable);

        void setDropDownGravity(int i2);

        void setHorizontalOffset(int i2);

        void setHorizontalOriginalOffset(int i2);

        void setPromptText(CharSequence charSequence);

        void setVerticalOffset(int i2);

        void show(int i2, int i3);

        @Deprecated
        void show(int i2, int i3, float f2, float f3);
    }

    static {
        try {
            Field declaredField = android.widget.Spinner.class.getDeclaredField("mForwardingListener");
            FORWARDING_LISTENER = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            Log.e(TAG, "static initializer: ", e2);
        }
    }

    public Spinner(Context context) {
        this(context, (AttributeSet) null);
    }

    private int compatMeasureSelectItemWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null || spinnerAdapter.getCount() == 0) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        View view = spinnerAdapter.getView(Math.max(0, Math.min(spinnerAdapter.getCount() - 1, getSelectedItemPosition())), null, this);
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        }
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        int iMax = Math.max(0, view.getMeasuredWidth());
        if (drawable == null) {
            return iMax;
        }
        drawable.getPadding(this.mTempRect);
        Rect rect = this.mTempRect;
        return iMax + rect.left + rect.right;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAdapter$0() {
        setChildEnabled(isEnabled());
    }

    private void makeSupperForwardingListenerInvalid() {
        Field field = FORWARDING_LISTENER;
        if (field == null) {
            return;
        }
        try {
            field.set(this, null);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "makeSupperForwardingListenerInvalid: ", e2);
        }
        setLongClickable(false);
    }

    private void notifySpinnerDismiss() {
        OnSpinnerDismissListener onSpinnerDismissListener = this.mOnSpinnerDismissListener;
        if (onSpinnerDismissListener != null) {
            onSpinnerDismissListener.onSpinnerDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSpinnerDismiss() {
        enableActivatedState(false);
        notifySpinnerDismiss();
    }

    private void setChildEnabled(boolean z2) {
        View viewFindViewById = findViewById(R.id.text1);
        View viewFindViewById2 = findViewById(R.id.icon1);
        if (viewFindViewById != null) {
            viewFindViewById.setEnabled(z2);
        }
        if (viewFindViewById2 != null) {
            viewFindViewById2.setEnabled(z2);
        }
    }

    private boolean superViewPerformClick() {
        sendAccessibilityEvent(1);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vibrate() {
        HapticCompat.performHapticFeedback(this, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_MESH_NORMAL);
    }

    public void dismissPopup() {
        this.mPopup.dismiss();
    }

    public void enableActivatedState(boolean z2) {
        if (z2 && isClickable()) {
            setActivated(true);
        } else {
            setActivated(false);
        }
    }

    public void enableHideSoftInput(boolean z2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.enableHideSoftInput(z2);
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        SpinnerPopup spinnerPopup = this.mPopup;
        return spinnerPopup != null ? spinnerPopup.getHorizontalOffset() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        SpinnerPopup spinnerPopup = this.mPopup;
        return spinnerPopup != null ? spinnerPopup.getVerticalOffset() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.mPopup != null ? this.mDropDownWidth : super.getDropDownWidth();
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        SpinnerPopup spinnerPopup = this.mPopup;
        return spinnerPopup != null ? spinnerPopup.getBackground() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.mPopupContext;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        SpinnerPopup spinnerPopup = this.mPopup;
        return spinnerPopup != null ? spinnerPopup.getHintText() : super.getPrompt();
    }

    public int getWindowManagerFlag() {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DropdownPopup) {
            return ((DropdownPopup) spinnerPopup).getWindowManagerFlags();
        }
        return -1;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        float f2 = getContext().getResources().getDisplayMetrics().density;
        if (this.mLastDensity != f2) {
            this.mLastDensity = f2;
            final AdapterView.OnItemSelectedListener onItemSelectedListener = getOnItemSelectedListener();
            setOnItemSelectedListener(null);
            setAdapter(getAdapter());
            post(new Runnable() { // from class: miuix.appcompat.widget.Spinner.1
                @Override // java.lang.Runnable
                public void run() {
                    if (Spinner.this.mSelectedPosition >= 0 && Spinner.this.getAdapter() != null && Spinner.this.mSelectedPosition < Spinner.this.getAdapter().getCount()) {
                        Spinner spinner = Spinner.this;
                        spinner.setSelection(spinner.mSelectedPosition);
                    }
                    if (Spinner.this.getOnItemSelectedListener() == null) {
                        Spinner.this.setOnItemSelectedListener(onItemSelectedListener);
                    }
                }
            });
        }
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup == null || !spinnerPopup.isShowing()) {
            return;
        }
        this.mPopup.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.mPopup == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.min(getMeasuredWidth(), compatMeasureSelectItemWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!savedState.mShowDropdown || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: miuix.appcompat.widget.Spinner.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (!Spinner.this.mPopup.isShowing()) {
                    Spinner.this.showPopup();
                }
                ViewTreeObserver viewTreeObserver2 = Spinner.this.getViewTreeObserver();
                if (viewTreeObserver2 != null) {
                    viewTreeObserver2.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SpinnerPopup spinnerPopup = this.mPopup;
        savedState.mShowDropdown = spinnerPopup != null && spinnerPopup.isShowing();
        return savedState;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            enableActivatedState(true);
        }
        if (isActivated() && !this.mPopup.isShowing() && ((motionEvent.getAction() == 1 && !isPressed()) || motionEvent.getAction() == 3)) {
            enableActivatedState(false);
        }
        return zOnTouchEvent;
    }

    @Deprecated
    public boolean performClick(float f2, float f3) {
        if (isClickable() && superViewPerformClick()) {
            return true;
        }
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup == null) {
            return super.performClick();
        }
        if (!spinnerPopup.isShowing()) {
            if (!isActivated()) {
                enableActivatedState(true);
            }
            showPopup(f2, f3);
            HapticCompat.performHapticFeedback(this, HapticFeedbackConstants.MIUI_BUTTON_SMALL, HapticFeedbackConstants.MIUI_POPUP_LIGHT);
        }
        return true;
    }

    @Override // android.view.View
    public void setActivated(boolean z2) {
        if (isClickable()) {
            super.setActivated(z2);
        }
    }

    public void setDimAmount(float f2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DropdownPopup) {
            ((DropdownPopup) spinnerPopup).setDimAmount(f2);
        }
    }

    public void setDoubleLineContentAdapter(SpinnerDoubleLineContentAdapter spinnerDoubleLineContentAdapter) {
        setAdapter((SpinnerAdapter) new SpinnerCheckableArrayAdapter(getContext(), miuix.appcompat.R.layout.miuix_appcompat_simple_spinner_layout, spinnerDoubleLineContentAdapter, new SpinnerCheckedProvider(this)));
    }

    public void setDropDownGravity(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setDropDownGravity(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup == null) {
            super.setDropDownHorizontalOffset(i2);
        } else {
            spinnerPopup.setHorizontalOriginalOffset(i2);
            this.mPopup.setHorizontalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setVerticalOffset(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.mPopup != null) {
            this.mDropDownWidth = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner, android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        if (this.mDisableChildrenWhenDisabled) {
            setChildEnabled(z2);
        }
    }

    public void setFenceView(View view) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DropdownPopup) {
            ((DropdownPopup) spinnerPopup).setFenceView(view);
        }
    }

    public void setOnDialogPopupItemClickListener(DialogInterface.OnClickListener onClickListener) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DialogPopup) {
            ((DialogPopup) spinnerPopup).setOnPopupItemClickListener(onClickListener);
        }
    }

    @Override // android.widget.Spinner, android.widget.AdapterView
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DropdownPopup) {
            ((DropdownPopup) spinnerPopup).setOnPopupItemClickListener(onItemClickListener);
        }
    }

    public void setOnSpinnerDismissListener(OnSpinnerDismissListener onSpinnerDismissListener) {
        this.mOnSpinnerDismissListener = onSpinnerDismissListener;
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(@DrawableRes int i2) {
        setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup != null) {
            spinnerPopup.setPromptText(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // android.widget.AbsSpinner, android.widget.AdapterView
    public void setSelection(int i2) {
        this.mSelectedPosition = i2;
        super.setSelection(i2);
        enableActivatedState(false);
    }

    public void setWindowManagerFlags(int i2) {
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DropdownPopup) {
            ((DropdownPopup) spinnerPopup).setWindowManagerFlags(i2);
        }
    }

    public void showPopup() {
        this.mPopup.show(getTextDirection(), getTextAlignment());
    }

    public Spinner(Context context, int i2) {
        this(context, null, miuix.appcompat.R.attr.miuiSpinnerStyle, i2);
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        SpinnerPopup spinnerPopup = this.mPopup;
        if (spinnerPopup instanceof DialogPopup) {
            spinnerPopup.setAdapter(new DialogPopupAdapter(spinnerAdapter, getPopupContext().getTheme()));
        } else if (spinnerPopup instanceof DropdownPopup) {
            spinnerPopup.setAdapter(new DropDownPopupAdapter(spinnerAdapter, getPopupContext().getTheme()));
        }
        post(new Runnable() { // from class: miuix.appcompat.widget.g
            @Override // java.lang.Runnable
            public final void run() {
                this.f6096a.lambda$setAdapter$0();
            }
        });
    }

    public void showPopup(float f2, float f3) {
        this.mPopup.show(getTextDirection(), getTextAlignment(), f2, f3);
    }

    public Spinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, miuix.appcompat.R.attr.miuiSpinnerStyle);
    }

    public Spinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, -1);
    }

    public Spinner(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, null);
    }

    public Spinner(Context context, AttributeSet attributeSet, int i2, int i3, Resources.Theme theme) {
        super(context, attributeSet, i2);
        this.mTempRect = new Rect();
        this.mLastDensity = context.getResources().getDisplayMetrics().density;
        int[] iArr = miuix.appcompat.R.styleable.Spinner;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        if (theme != null) {
            this.mPopupContext = new ContextThemeWrapper(context, theme);
        } else {
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(miuix.appcompat.R.styleable.Spinner_popupTheme, 0);
            if (resourceId != 0) {
                this.mPopupContext = new ContextThemeWrapper(context, resourceId);
            } else {
                this.mPopupContext = context;
            }
        }
        i3 = i3 == -1 ? typedArrayObtainStyledAttributes.getInt(miuix.appcompat.R.styleable.Spinner_spinnerModeCompat, 0) : i3;
        if (i3 == 0) {
            DialogPopup dialogPopup = new DialogPopup();
            this.mPopup = dialogPopup;
            dialogPopup.setPromptText(typedArrayObtainStyledAttributes.getString(miuix.appcompat.R.styleable.Spinner_android_prompt));
        } else if (i3 == 1) {
            DropdownPopup dropdownPopup = new DropdownPopup(this.mPopupContext);
            TypedArray typedArrayObtainStyledAttributes2 = this.mPopupContext.obtainStyledAttributes(attributeSet, iArr, i2, 0);
            this.mDropDownWidth = typedArrayObtainStyledAttributes2.getLayoutDimension(miuix.appcompat.R.styleable.Spinner_android_dropDownWidth, -2);
            this.mDropDownMinWidth = typedArrayObtainStyledAttributes2.getLayoutDimension(miuix.appcompat.R.styleable.Spinner_dropDownMinWidth, -2);
            this.mDropDownMaxWidth = typedArrayObtainStyledAttributes2.getLayoutDimension(miuix.appcompat.R.styleable.Spinner_dropDownMaxWidth, -2);
            int i4 = miuix.appcompat.R.styleable.Spinner_android_popupBackground;
            int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(i4, 0);
            if (resourceId2 != 0) {
                setPopupBackgroundResource(resourceId2);
            } else {
                dropdownPopup.setBackgroundDrawable(typedArrayObtainStyledAttributes2.getDrawable(i4));
            }
            dropdownPopup.setPromptText(typedArrayObtainStyledAttributes.getString(miuix.appcompat.R.styleable.Spinner_android_prompt));
            typedArrayObtainStyledAttributes2.recycle();
            this.mPopup = dropdownPopup;
        }
        makeSupperForwardingListenerInvalid();
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(miuix.appcompat.R.styleable.Spinner_android_entries);
        if (textArray != null) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, miuix.appcompat.R.layout.miuix_appcompat_simple_spinner_layout, R.id.text1, textArray);
            if (i3 == 0) {
                arrayAdapter.setDropDownViewResource(miuix.appcompat.R.layout.miuix_appcompat_simple_spinner_dialog_item);
            } else {
                arrayAdapter.setDropDownViewResource(miuix.appcompat.R.layout.miuix_appcompat_simple_spinner_dropdown_item);
            }
            setAdapter((SpinnerAdapter) arrayAdapter);
        }
        this.mDisableChildrenWhenDisabled = typedArrayObtainStyledAttributes.getBoolean(miuix.appcompat.R.styleable.Spinner_disableChildrenWhenDisabled, false);
        typedArrayObtainStyledAttributes.recycle();
        this.mPopupSet = true;
        SpinnerAdapter spinnerAdapter = this.mTempAdapter;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.mTempAdapter = null;
        }
        CompatViewMethod.setForceDarkAllowed(this, false);
    }

    public class DropdownPopup extends PopupWindow implements SpinnerPopup {
        private static final int INVALID_VALUE = -1;
        private static final float SCREEN_MARGIN_BOTTOM_PROPORTION = 0.1f;
        private static final float SCREEN_MARGIN_TOP_PROPORTION = 0.1f;
        ListAdapter mAdapter;
        private View mFenceView;
        private CharSequence mHintText;
        private int mOriginalHorizontalOffset;
        private AdapterView.OnItemClickListener mPopupItemClickListener;
        private final Rect mVisibleRect;

        /* JADX INFO: renamed from: miuix.appcompat.widget.Spinner$DropdownPopup$1, reason: invalid class name */
        public class AnonymousClass1 implements AdapterView.OnItemClickListener {
            final /* synthetic */ Spinner val$this$0;

            public AnonymousClass1(Spinner spinner) {
                this.val$this$0 = spinner;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$onItemClick$0() {
                DropdownPopup.this.dismiss();
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                Spinner.this.setSelection(i2);
                Spinner.this.vibrate();
                if (Spinner.this.getOnItemClickListener() != null) {
                    DropdownPopup dropdownPopup = DropdownPopup.this;
                    Spinner.this.performItemClick(view, i2, dropdownPopup.mAdapter.getItemId(i2));
                }
                Spinner.this.postDelayed(new Runnable() { // from class: miuix.appcompat.widget.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f6097a.lambda$onItemClick$0();
                    }
                }, 60L);
                if (DropdownPopup.this.mPopupItemClickListener != null) {
                    DropdownPopup.this.mPopupItemClickListener.onItemClick(adapterView, view, i2, j2);
                }
            }
        }

        public DropdownPopup(Context context) {
            super(context, null);
            this.mVisibleRect = new Rect();
            Resources resources = context.getResources();
            this.mPopupWindowSpec.mMinHeight = ((resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_drop_down_menu_padding_single_item) * 2) + resources.getDimensionPixelSize(miuix.appcompat.R.dimen.miuix_appcompat_drop_down_item_min_height)) * 2;
            setDropDownGravity(8388691);
            setOnItemClickListener(new AnonymousClass1(Spinner.this));
            this.mIgnoreAnchorVisibility = true;
        }

        private void initListView(int i2, int i3) {
            ListView listView = getListView();
            listView.setChoiceMode(1);
            listView.setTextDirection(i2);
            listView.setTextAlignment(i3);
            int selectedItemPosition = Spinner.this.getSelectedItemPosition();
            listView.setSelection(selectedItemPosition);
            listView.setItemChecked(selectedItemPosition, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnPopupItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.mPopupItemClickListener = onItemClickListener;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void setProperFenceView() {
            if (this.mFenceView != null) {
                return;
            }
            Spinner spinner = Spinner.this;
            if ((spinner.getContext() instanceof IActivity) && ((IActivity) spinner.getContext()).isInFloatingWindowMode()) {
                setFenceView(spinner.getRootView().findViewById(miuix.appcompat.R.id.action_bar_overlay_layout));
                return;
            }
            for (ViewParent parent = spinner.getParent(); parent != 0; parent = parent.getParent()) {
                if ((parent instanceof Fence) && ((Fence) parent).isFenceEnabled() && (parent instanceof View)) {
                    setFenceView((View) parent);
                    return;
                }
            }
        }

        private void showWithAnchor(View view) {
            Log.d(Spinner.TAG, this.mPopupWindowSpec.toString());
            if (getAnchor() != view) {
                setAnchorView(view);
            }
            if (this.mPopupWindowSpec.mAnchorViewBounds.centerX() <= this.mPopupWindowSpec.mDecorViewBounds.centerX()) {
                setDropDownGravity(83);
            } else {
                setDropDownGravity(85);
            }
            int xInWindow = this.mPopupWindowStrategy.getXInWindow(this.mPopupWindowSpec);
            int yInWindow = this.mPopupWindowStrategy.getYInWindow(this.mPopupWindowSpec);
            setWidth(this.mPopupWindowSpec.mFinalPopupWidth);
            setHeight(this.mPopupWindowSpec.mFinalPopupHeight);
            if (isShowing()) {
                update(xInWindow, yInWindow, getWidth(), getHeight());
            } else {
                showAtLocation(view, 0, xInWindow, yInWindow);
            }
        }

        @Override // miuix.popupwidget.widget.PopupWindow, miuix.appcompat.widget.Spinner.SpinnerPopup
        public void enableHideSoftInput(boolean z2) {
            super.enableHideSoftInput(z2);
        }

        public View getFenceView() {
            View view = this.mFenceView;
            return view != null ? view : Spinner.this.getRootView();
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public CharSequence getHintText() {
            return this.mHintText;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public int getHorizontalOriginalOffset() {
            return this.mOriginalHorizontalOffset;
        }

        @Override // miuix.popupwidget.widget.PopupWindow
        public int[][] getItemViewBounds(ListAdapter listAdapter, ViewGroup viewGroup, Context context) {
            if (listAdapter == null) {
                this.mContentView.measure(View.MeasureSpec.makeMeasureSpec(this.mPopupWindowSpec.mMaxWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
                int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 1, 2);
                iArr[0][0] = this.mContentView.getMeasuredWidth();
                iArr[0][1] = this.mContentView.getMeasuredHeight();
                return iArr;
            }
            ListView listView = getListView();
            int count = listAdapter.getCount();
            int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, count, 2);
            for (int i2 = 0; i2 < count; i2++) {
                View view = listAdapter.getView(i2, null, listView);
                view.measure(View.MeasureSpec.makeMeasureSpec(this.mPopupWindowSpec.mMaxWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
                iArr2[i2][0] = view.getMeasuredWidth();
                iArr2[i2][1] = view.getMeasuredHeight();
            }
            return iArr2;
        }

        public boolean isVisibleToUser(View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
        }

        @Override // miuix.popupwidget.widget.PopupWindow
        public boolean prepareShow(View view) {
            if (!super.prepareShow(view)) {
                return false;
            }
            setInputMethodMode(2);
            return true;
        }

        @Override // miuix.popupwidget.widget.PopupWindow
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }

        @Override // miuix.popupwidget.widget.PopupWindow, miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setDropDownGravity(int i2) {
            super.setDropDownGravity(i2);
        }

        public void setFenceView(View view) {
            this.mFenceView = view;
            super.setDecorView(view);
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setHorizontalOriginalOffset(int i2) {
            this.mOriginalHorizontalOffset = i2;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void show(int i2, int i3) {
            boolean zIsShowing = isShowing();
            setProperFenceView();
            setInputMethodMode(2);
            if (prepareShow(Spinner.this)) {
                showWithAnchor(Spinner.this);
                initListView(i2, i3);
            }
            if (zIsShowing) {
                return;
            }
            setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.appcompat.widget.Spinner.DropdownPopup.2
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    Spinner.this.onSpinnerDismiss();
                }
            });
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        @Deprecated
        public void show(int i2, int i3, float f2, float f3) {
            show(i2, i3);
        }
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        return performClick(0.0f, 0.0f);
    }

    public class DialogPopup implements SpinnerPopup, DialogInterface.OnClickListener {
        private ListAdapter mListAdapter;
        AlertDialog mPopup;
        private DialogInterface.OnClickListener mPopupItemClickListener;
        private CharSequence mPrompt;

        private DialogPopup() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOnPopupItemClickListener(DialogInterface.OnClickListener onClickListener) {
            this.mPopupItemClickListener = onClickListener;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void dismiss() {
            AlertDialog alertDialog = this.mPopup;
            if (alertDialog != null) {
                alertDialog.dismiss();
                this.mPopup = null;
            }
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void enableHideSoftInput(boolean z2) {
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public Drawable getBackground() {
            return null;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public CharSequence getHintText() {
            return this.mPrompt;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public int getHorizontalOffset() {
            return 0;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public int getHorizontalOriginalOffset() {
            return 0;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public int getVerticalOffset() {
            return 0;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public boolean isShowing() {
            AlertDialog alertDialog = this.mPopup;
            return alertDialog != null && alertDialog.isShowing();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Spinner.this.setSelection(i2);
            HapticCompat.performHapticFeedback(Spinner.this, HapticFeedbackConstants.MIUI_POPUP_LIGHT);
            if (Spinner.this.getOnItemClickListener() != null) {
                Spinner.this.performItemClick(null, i2, this.mListAdapter.getItemId(i2));
            }
            DialogInterface.OnClickListener onClickListener = this.mPopupItemClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(dialogInterface, i2);
            }
            dismiss();
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setAdapter(ListAdapter listAdapter) {
            this.mListAdapter = listAdapter;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setBackgroundDrawable(Drawable drawable) {
            Log.e(Spinner.TAG, "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setDropDownGravity(int i2) {
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setHorizontalOffset(int i2) {
            Log.e(Spinner.TAG, "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setHorizontalOriginalOffset(int i2) {
            Log.e(Spinner.TAG, "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setPromptText(CharSequence charSequence) {
            this.mPrompt = charSequence;
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void setVerticalOffset(int i2) {
            Log.e(Spinner.TAG, "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        public void show(int i2, int i3) {
            if (this.mListAdapter == null) {
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(Spinner.this.getPopupContext());
            CharSequence charSequence = this.mPrompt;
            if (charSequence != null) {
                builder.setTitle(charSequence);
            }
            AlertDialog alertDialogCreate = builder.setSingleChoiceItems(this.mListAdapter, Spinner.this.getSelectedItemPosition(), this).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: miuix.appcompat.widget.Spinner.DialogPopup.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    Spinner.this.onSpinnerDismiss();
                }
            }).create();
            this.mPopup = alertDialogCreate;
            ListView listView = alertDialogCreate.getListView();
            listView.setTextDirection(i2);
            listView.setTextAlignment(i3);
            this.mPopup.show();
        }

        @Override // miuix.appcompat.widget.Spinner.SpinnerPopup
        @Deprecated
        public void show(int i2, int i3, float f2, float f3) {
            show(i2, i3);
        }
    }
}
