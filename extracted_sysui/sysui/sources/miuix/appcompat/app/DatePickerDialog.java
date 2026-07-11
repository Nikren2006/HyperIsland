package miuix.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import miuix.pickerwidget.date.Calendar;
import miuix.pickerwidget.date.DateUtils;
import miuix.pickerwidget.widget.DatePicker;
import miuix.slidingwidget.widget.SlidingButton;

/* JADX INFO: loaded from: classes2.dex */
public class DatePickerDialog extends AlertDialog {
    private final Calendar mCalendar;
    private final OnDateSetListener mCallBack;
    private final DatePicker mDatePicker;
    private View mLunarModePanel;
    private SlidingButton mLunarModeState;
    private DatePicker.OnDateChangedListener mOnDateChangedListener;
    private boolean mTitleNeedsUpdate;

    public interface OnDateSetListener {
        void onDateSet(DatePicker datePicker, int i2, int i3, int i4);
    }

    public DatePickerDialog(Context context, OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
        this(context, 0, onDateSetListener, i2, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(CompoundButton compoundButton, boolean z2) {
        this.mDatePicker.setLunarMode(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        SlidingButton slidingButton = this.mLunarModeState;
        if (slidingButton != null) {
            slidingButton.performClick();
        }
        view.sendAccessibilityEvent(1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryNotifyDateSet() {
        if (this.mCallBack != null) {
            this.mDatePicker.clearFocus();
            OnDateSetListener onDateSetListener = this.mCallBack;
            DatePicker datePicker = this.mDatePicker;
            onDateSetListener.onDateSet(datePicker, datePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitle(int i2, int i3, int i4) {
        this.mCalendar.set(1, i2);
        this.mCalendar.set(5, i3);
        this.mCalendar.set(9, i4);
        super.setTitle(DateUtils.formatDateTime(getContext(), this.mCalendar.getTimeInMillis(), 14208));
    }

    public DatePicker getDatePicker() {
        return this.mDatePicker;
    }

    public void setLunarMode(boolean z2) {
        this.mLunarModePanel.setVisibility(z2 ? 0 : 8);
    }

    @Override // miuix.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mTitleNeedsUpdate = false;
    }

    public void switchLunarState(boolean z2) {
        this.mLunarModeState.setChecked(z2);
        this.mDatePicker.setLunarMode(z2);
    }

    public void updateDate(int i2, int i3, int i4) {
        this.mDatePicker.updateDate(i2, i3, i4);
    }

    public DatePickerDialog(Context context, int i2, OnDateSetListener onDateSetListener, int i3, int i4, int i5) {
        super(context, i2);
        this.mTitleNeedsUpdate = true;
        this.mOnDateChangedListener = new DatePicker.OnDateChangedListener() { // from class: miuix.appcompat.app.DatePickerDialog.1
            @Override // miuix.pickerwidget.widget.DatePicker.OnDateChangedListener
            public void onDateChanged(DatePicker datePicker, int i6, int i7, int i8, boolean z2) {
                if (DatePickerDialog.this.mTitleNeedsUpdate) {
                    DatePickerDialog.this.updateTitle(i6, i7, i8);
                }
            }
        };
        this.mCallBack = onDateSetListener;
        this.mCalendar = new Calendar();
        Context context2 = getContext();
        setButton(-1, context2.getText(R.string.ok), new DialogInterface.OnClickListener() { // from class: miuix.appcompat.app.DatePickerDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i6) {
                DatePickerDialog.this.tryNotifyDateSet();
            }
        });
        setButton(-2, getContext().getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        View viewInflate = ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(miuix.appcompat.R.layout.miuix_appcompat_date_picker_dialog, (ViewGroup) null);
        setView(viewInflate);
        DatePicker datePicker = (DatePicker) viewInflate.findViewById(miuix.appcompat.R.id.datePicker);
        this.mDatePicker = datePicker;
        datePicker.init(i3, i4, i5, this.mOnDateChangedListener);
        updateTitle(i3, i4, i5);
        this.mLunarModePanel = viewInflate.findViewById(miuix.appcompat.R.id.lunarModePanel);
        SlidingButton slidingButton = (SlidingButton) viewInflate.findViewById(miuix.appcompat.R.id.datePickerLunar);
        this.mLunarModeState = slidingButton;
        slidingButton.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: miuix.appcompat.app.m
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                this.f6049a.lambda$new$0(compoundButton, z2);
            }
        });
        ViewCompat.setAccessibilityDelegate(this.mLunarModePanel, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.app.DatePickerDialog.3
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.setClassName(Switch.class.getName());
                if (DatePickerDialog.this.mLunarModeState != null) {
                    accessibilityNodeInfoCompat.setChecked(DatePickerDialog.this.mLunarModeState.isChecked());
                    accessibilityNodeInfoCompat.setContentDescription(DatePickerDialog.this.mLunarModeState.getContentDescription());
                }
            }
        });
        ViewCompat.replaceAccessibilityAction(this.mLunarModePanel, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, "", new AccessibilityViewCommand() { // from class: miuix.appcompat.app.n
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return this.f6050a.lambda$new$1(view, commandArguments);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void setTitle(int i2) {
        super.setTitle(i2);
        this.mTitleNeedsUpdate = false;
    }
}
