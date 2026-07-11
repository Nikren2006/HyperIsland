package miuix.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import miuix.pickerwidget.widget.DateTimePicker;
import miuix.slidingwidget.widget.SlidingButton;

/* JADX INFO: loaded from: classes2.dex */
public class DateTimePickerDialog extends AlertDialog {
    private View mLunarModePanel;
    private SlidingButton mLunarModeState;
    private OnTimeSetListener mTimeListener;
    private DateTimePicker mTimePicker;

    public interface OnTimeSetListener {
        void onTimeSet(DateTimePickerDialog dateTimePickerDialog, long j2);
    }

    public DateTimePickerDialog(Context context, OnTimeSetListener onTimeSetListener) {
        this(context, onTimeSetListener, 1);
    }

    private void init(int i2) {
        setButton(-1, getContext().getText(R.string.ok), new DialogInterface.OnClickListener() { // from class: miuix.appcompat.app.DateTimePickerDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                if (DateTimePickerDialog.this.mTimeListener != null) {
                    OnTimeSetListener onTimeSetListener = DateTimePickerDialog.this.mTimeListener;
                    DateTimePickerDialog dateTimePickerDialog = DateTimePickerDialog.this;
                    onTimeSetListener.onTimeSet(dateTimePickerDialog, dateTimePickerDialog.mTimePicker.getTimeInMillis());
                }
            }
        });
        setButton(-2, getContext().getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(miuix.appcompat.R.layout.miuix_appcompat_datetime_picker_dialog, (ViewGroup) null);
        setView(viewInflate);
        DateTimePicker dateTimePicker = (DateTimePicker) viewInflate.findViewById(miuix.appcompat.R.id.dateTimePicker);
        this.mTimePicker = dateTimePicker;
        dateTimePicker.setMinuteInterval(i2);
        View viewFindViewById = viewInflate.findViewById(miuix.appcompat.R.id.lunarModePanel);
        this.mLunarModePanel = viewFindViewById;
        ViewCompat.setAccessibilityDelegate(viewFindViewById, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.app.DateTimePickerDialog.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.setClassName(Switch.class.getName());
                if (DateTimePickerDialog.this.mLunarModeState != null) {
                    accessibilityNodeInfoCompat.setChecked(DateTimePickerDialog.this.mLunarModeState.isChecked());
                    accessibilityNodeInfoCompat.setContentDescription(DateTimePickerDialog.this.mLunarModeState.getContentDescription());
                }
            }
        });
        ViewCompat.replaceAccessibilityAction(this.mLunarModePanel, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, "", new AccessibilityViewCommand() { // from class: miuix.appcompat.app.o
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return this.f6051a.lambda$init$0(view, commandArguments);
            }
        });
        SlidingButton slidingButton = (SlidingButton) viewInflate.findViewById(miuix.appcompat.R.id.datePickerLunar);
        this.mLunarModeState = slidingButton;
        slidingButton.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: miuix.appcompat.app.p
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                this.f6052a.lambda$init$1(compoundButton, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$init$0(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        SlidingButton slidingButton = this.mLunarModeState;
        if (slidingButton != null) {
            slidingButton.performClick();
        }
        view.sendAccessibilityEvent(1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$1(CompoundButton compoundButton, boolean z2) {
        this.mTimePicker.setLunarMode(z2);
    }

    @Override // android.app.Dialog
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public void setLunarMode(boolean z2) {
        this.mLunarModePanel.setVisibility(z2 ? 0 : 8);
    }

    public void setMaxDateTime(long j2) {
        this.mTimePicker.setMaxDateTime(j2);
    }

    public void setMinDateTime(long j2) {
        this.mTimePicker.setMinDateTime(j2);
    }

    public void switchLunarState(boolean z2) {
        this.mLunarModeState.setChecked(z2);
        this.mTimePicker.setLunarMode(z2);
    }

    public void update(long j2) {
        this.mTimePicker.update(j2);
    }

    public DateTimePickerDialog(Context context, OnTimeSetListener onTimeSetListener, @IntRange(from = 1, to = 30) int i2) {
        super(context);
        this.mTimeListener = onTimeSetListener;
        init(i2);
        setTitle(miuix.appcompat.R.string.date_time_picker_dialog_title);
    }
}
