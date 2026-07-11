package miuix.pickerwidget.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import java.util.Locale;
import miuix.pickerwidget.R;
import miuix.pickerwidget.date.Calendar;
import miuix.pickerwidget.date.CalendarFormatSymbols;
import miuix.pickerwidget.date.DateUtils;
import miuix.pickerwidget.internal.widget.ProperPaddingViewGroup;
import miuix.pickerwidget.widget.NumberPicker;

/* JADX INFO: loaded from: classes5.dex */
public class TimePicker extends FrameLayout {
    private static final boolean DEFAULT_ENABLED_STATE = true;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final OnTimeChangedListener NO_OP_CHANGE_LISTENER = new OnTimeChangedListener() { // from class: miuix.pickerwidget.widget.TimePicker.1
        @Override // miuix.pickerwidget.widget.TimePicker.OnTimeChangedListener
        public void onTimeChanged(TimePicker timePicker, int i2, int i3) {
        }
    };
    private final Button mAmPmButton;
    private final NumberPicker mAmPmSpinner;
    private Locale mCurrentLocale;
    private final NumberPicker mHourSpinner;
    private boolean mIs24HourView;
    private boolean mIsAm;
    private boolean mIsEnabled;
    private final NumberPicker mMinuteSpinner;
    private OnTimeChangedListener mOnTimeChangedListener;
    private ProperPaddingViewGroup mProperPaddingViewGroup;
    private Calendar mTempCalendar;

    public interface OnTimeChangedListener {
        void onTimeChanged(TimePicker timePicker, int i2, int i3);
    }

    public TimePicker(Context context) {
        this(context, null);
    }

    private boolean isAmPmAtStart() {
        return getContext().getString(R.string.fmt_time_12hour_pm).startsWith("a");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeChanged() {
        sendAccessibilityEvent(4);
        OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
        if (onTimeChangedListener != null) {
            onTimeChangedListener.onTimeChanged(this, getCurrentHour().intValue(), getCurrentMinute().intValue());
        }
    }

    private void setCurrentLocale(Locale locale) {
        if (locale.equals(this.mCurrentLocale)) {
            return;
        }
        this.mCurrentLocale = locale;
        if (this.mTempCalendar == null) {
            this.mTempCalendar = new Calendar();
        }
    }

    private void trySetContentDescription(View view, int i2, int i3) {
        View viewFindViewById = view.findViewById(i2);
        if (viewFindViewById != null) {
            viewFindViewById.setContentDescription(getContext().getString(i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAmPmControl() {
        if (is24HourView()) {
            NumberPicker numberPicker = this.mAmPmSpinner;
            if (numberPicker != null) {
                numberPicker.setVisibility(8);
            } else {
                this.mAmPmButton.setVisibility(8);
            }
        } else {
            int i2 = !this.mIsAm ? 1 : 0;
            NumberPicker numberPicker2 = this.mAmPmSpinner;
            if (numberPicker2 != null) {
                numberPicker2.setValue(i2);
                this.mAmPmSpinner.setVisibility(0);
            } else {
                this.mAmPmButton.setText(CalendarFormatSymbols.getOrCreate(getContext()).getAmPms()[i2]);
                this.mAmPmButton.setVisibility(0);
            }
        }
        sendAccessibilityEvent(4);
    }

    private void updateHourControl() {
        if (is24HourView()) {
            this.mHourSpinner.setMinValue(0);
            this.mHourSpinner.setMaxValue(23);
            this.mHourSpinner.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        } else {
            this.mHourSpinner.setMinValue(1);
            this.mHourSpinner.setMaxValue(12);
            this.mHourSpinner.setFormatter(null);
        }
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.mHourSpinner.getBaseline();
    }

    public Integer getCurrentHour() {
        int value = this.mHourSpinner.getValue();
        return is24HourView() ? Integer.valueOf(value) : this.mIsAm ? Integer.valueOf(value % 12) : Integer.valueOf((value % 12) + 12);
    }

    public Integer getCurrentMinute() {
        return Integer.valueOf(this.mMinuteSpinner.getValue());
    }

    public boolean is24HourView() {
        return this.mIs24HourView;
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TimePicker.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TimePicker.class.getName());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        int i2 = this.mIs24HourView ? 44 : 28;
        this.mTempCalendar.set(18, getCurrentHour().intValue());
        this.mTempCalendar.set(20, getCurrentMinute().intValue());
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.mTempCalendar.getTimeInMillis(), i2));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentHour(Integer.valueOf(savedState.getHour()));
        setCurrentMinute(Integer.valueOf(savedState.getMinute()));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getCurrentHour().intValue(), getCurrentMinute().intValue());
    }

    public void set24HourView(Boolean bool) {
        if (this.mIs24HourView == bool.booleanValue()) {
            return;
        }
        this.mIs24HourView = bool.booleanValue();
        Integer currentHour = getCurrentHour();
        currentHour.intValue();
        updateHourControl();
        setCurrentHour(currentHour);
        updateAmPmControl();
    }

    public void setCurrentHour(Integer num) {
        if (num == null || num.equals(getCurrentHour())) {
            return;
        }
        if (!is24HourView()) {
            if (num.intValue() >= 12) {
                this.mIsAm = false;
                if (num.intValue() > 12) {
                    num = Integer.valueOf(num.intValue() - 12);
                }
            } else {
                this.mIsAm = true;
                if (num.intValue() == 0) {
                    num = 12;
                }
            }
            updateAmPmControl();
        }
        this.mHourSpinner.setValue(num.intValue());
        onTimeChanged();
    }

    public void setCurrentMinute(Integer num) {
        if (num.equals(getCurrentMinute())) {
            return;
        }
        this.mMinuteSpinner.setValue(num.intValue());
        onTimeChanged();
    }

    @Override // android.view.View
    public void setEnabled(boolean z2) {
        if (this.mIsEnabled == z2) {
            return;
        }
        super.setEnabled(z2);
        this.mMinuteSpinner.setEnabled(z2);
        this.mHourSpinner.setEnabled(z2);
        NumberPicker numberPicker = this.mAmPmSpinner;
        if (numberPicker != null) {
            numberPicker.setEnabled(z2);
        } else {
            this.mAmPmButton.setEnabled(z2);
        }
        this.mIsEnabled = z2;
    }

    public void setFixedContentHorizontalPadding(int i2, int i3) {
        this.mProperPaddingViewGroup.setFixedContentHorizontalPadding(i2, i3);
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.mOnTimeChangedListener = onTimeChangedListener;
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: miuix.pickerwidget.widget.TimePicker.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        private final int mHour;
        private final int mMinute;

        public int getHour() {
            return this.mHour;
        }

        public int getMinute() {
            return this.mMinute;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mHour);
            parcel.writeInt(this.mMinute);
        }

        private SavedState(Parcelable parcelable, int i2, int i3) {
            super(parcelable);
            this.mHour = i2;
            this.mMinute = i3;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mHour = parcel.readInt();
            this.mMinute = parcel.readInt();
        }
    }

    public TimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsEnabled = true;
        setCurrentLocale(Locale.getDefault());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.miuix_appcompat_time_picker, (ViewGroup) this, true);
        this.mProperPaddingViewGroup = (ProperPaddingViewGroup) findViewById(R.id.properPaddingViewGroup);
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.hour);
        this.mHourSpinner = numberPicker;
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: miuix.pickerwidget.widget.TimePicker.2
            @Override // miuix.pickerwidget.widget.NumberPicker.OnValueChangeListener
            public void onValueChange(NumberPicker numberPicker2, int i3, int i4) {
                if (!TimePicker.this.is24HourView() && ((i3 == 11 && i4 == 12) || (i3 == 12 && i4 == 11))) {
                    TimePicker.this.mIsAm = !r2.mIsAm;
                    TimePicker.this.updateAmPmControl();
                }
                TimePicker.this.onTimeChanged();
            }
        });
        int i3 = R.id.number_picker_input;
        ((EditText) numberPicker.findViewById(i3)).setImeOptions(5);
        NumberPicker numberPicker2 = (NumberPicker) findViewById(R.id.minute);
        this.mMinuteSpinner = numberPicker2;
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(59);
        numberPicker2.setOnLongPressUpdateInterval(100L);
        numberPicker2.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: miuix.pickerwidget.widget.TimePicker.3
            @Override // miuix.pickerwidget.widget.NumberPicker.OnValueChangeListener
            public void onValueChange(NumberPicker numberPicker3, int i4, int i5) {
                TimePicker.this.onTimeChanged();
            }
        });
        ((EditText) numberPicker2.findViewById(i3)).setImeOptions(5);
        View viewFindViewById = findViewById(R.id.amPm);
        if (viewFindViewById instanceof Button) {
            this.mAmPmSpinner = null;
            Button button = (Button) viewFindViewById;
            this.mAmPmButton = button;
            button.setOnClickListener(new View.OnClickListener() { // from class: miuix.pickerwidget.widget.TimePicker.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    view.requestFocus();
                    TimePicker.this.mIsAm = !r2.mIsAm;
                    TimePicker.this.updateAmPmControl();
                    TimePicker.this.onTimeChanged();
                }
            });
        } else {
            this.mAmPmButton = null;
            NumberPicker numberPicker3 = (NumberPicker) viewFindViewById;
            this.mAmPmSpinner = numberPicker3;
            numberPicker3.setMinValue(0);
            numberPicker3.setMaxValue(1);
            numberPicker3.setDisplayedValues(CalendarFormatSymbols.getOrCreate(getContext()).getAmPms());
            numberPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() { // from class: miuix.pickerwidget.widget.TimePicker.5
                @Override // miuix.pickerwidget.widget.NumberPicker.OnValueChangeListener
                public void onValueChange(NumberPicker numberPicker4, int i4, int i5) {
                    numberPicker4.requestFocus();
                    TimePicker.this.mIsAm = !r1.mIsAm;
                    TimePicker.this.updateAmPmControl();
                    TimePicker.this.onTimeChanged();
                }
            });
            ((EditText) numberPicker3.findViewById(i3)).setImeOptions(6);
        }
        if (isAmPmAtStart()) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.timePickerLayout);
            viewGroup.removeView(viewFindViewById);
            viewGroup.addView(viewFindViewById, 0);
        }
        updateHourControl();
        updateAmPmControl();
        setOnTimeChangedListener(NO_OP_CHANGE_LISTENER);
        setCurrentHour(Integer.valueOf(this.mTempCalendar.get(18)));
        setCurrentMinute(Integer.valueOf(this.mTempCalendar.get(20)));
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }
}
