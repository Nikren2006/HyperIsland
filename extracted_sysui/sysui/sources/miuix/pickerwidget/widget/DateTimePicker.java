package miuix.pickerwidget.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.annotation.IntRange;
import com.miui.maml.folme.AnimatedProperty;
import java.util.Locale;
import miuix.pickerwidget.R;
import miuix.pickerwidget.date.Calendar;
import miuix.pickerwidget.date.DateUtils;
import miuix.pickerwidget.widget.NumberPicker;

/* JADX INFO: loaded from: classes5.dex */
public class DateTimePicker extends LinearLayout {
    private static final int DAYPICKER_ALL_ITEM_MAX_VALUE = 4;
    private static final int DAYPICKER_WHEEL_ITEM_COUNT = 5;
    private static DayFormatter DEFAULT_DAY_FORMATTER = null;
    private static final int DEFAULT_MINUTE_INTERVAL = 1;
    private static final int HALF_WHEEL_ITEM_COUNT = 1;
    private static final int HOUR_IN_MINUTES = 60;
    private static final ThreadLocal<Calendar> sCalCache = new ThreadLocal<>();
    private static ThreadLocal<Calendar> sCalendarCache = new ThreadLocal<>();
    private Calendar mCalendar;
    String[] mDayDisplayValues;
    private DayFormatter mDayFormatter;
    private int mDayLastValue;
    private NumberPicker mDayPicker;
    private NumberPicker mHourPicker;
    private boolean mIsLunarMode;
    private OnDateTimeChangedListener mListener;
    private DayFormatter mLunarFormatter;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private String[] mMinuteDisplayValues;
    private int mMinuteInterval;
    private NumberPicker mMinutePicker;

    public static class DayFormatter {
        protected Context mContext;

        public DayFormatter(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public String formatDay(int i2, int i3, int i4) {
            Calendar calendar = (Calendar) DateTimePicker.sCalendarCache.get();
            if (calendar == null) {
                calendar = new Calendar();
                DateTimePicker.sCalendarCache.set(calendar);
            }
            calendar.set(1, i2);
            calendar.set(5, i3);
            calendar.set(9, i4);
            if (!Locale.getDefault().getLanguage().equals(Locale.CHINESE.getLanguage())) {
                return DateUtils.formatDateTime(this.mContext, calendar.getTimeInMillis(), 13696);
            }
            String dateTime = DateUtils.formatDateTime(this.mContext, calendar.getTimeInMillis(), 4480);
            return dateTime.replace(" ", "") + " " + DateUtils.formatDateTime(this.mContext, calendar.getTimeInMillis(), 9216);
        }
    }

    public static class LunarFormatter extends DayFormatter {
        public LunarFormatter(Context context) {
            super(context);
        }

        @Override // miuix.pickerwidget.widget.DateTimePicker.DayFormatter
        public String formatDay(int i2, int i3, int i4) {
            Calendar calendar = (Calendar) DateTimePicker.sCalendarCache.get();
            if (calendar == null) {
                calendar = new Calendar();
                DateTimePicker.sCalendarCache.set(calendar);
            }
            calendar.set(1, i2);
            calendar.set(5, i3);
            calendar.set(9, i4);
            Context context = this.mContext;
            return calendar.format(context, context.getString(R.string.fmt_chinese_date));
        }
    }

    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker dateTimePicker, long j2);
    }

    public class PickerValueChangeListener implements NumberPicker.OnValueChangeListener {
        private PickerValueChangeListener() {
        }

        private void notifyTimeChanged(DateTimePicker dateTimePicker) {
            DateTimePicker.this.sendAccessibilityEvent(4);
            if (DateTimePicker.this.mListener != null) {
                DateTimePicker.this.mListener.onDateTimeChanged(dateTimePicker, DateTimePicker.this.getTimeInMillis());
            }
        }

        @Override // miuix.pickerwidget.widget.NumberPicker.OnValueChangeListener
        public void onValueChange(NumberPicker numberPicker, int i2, int i3) {
            if (numberPicker == DateTimePicker.this.mDayPicker) {
                DateTimePicker.this.mCalendar.add(12, ((numberPicker.getValue() - DateTimePicker.this.mDayLastValue) + 5) % 5 != 1 ? -1 : 1);
                DateTimePicker.this.mDayLastValue = numberPicker.getValue();
            } else if (numberPicker == DateTimePicker.this.mHourPicker) {
                DateTimePicker.this.mCalendar.set(18, DateTimePicker.this.mHourPicker.getValue());
            } else if (numberPicker == DateTimePicker.this.mMinutePicker) {
                DateTimePicker.this.mCalendar.set(20, DateTimePicker.this.mMinuteInterval * DateTimePicker.this.mMinutePicker.getValue());
            }
            DateTimePicker.this.checkCurrentTime();
            DateTimePicker.this.updateDayPicker(false);
            DateTimePicker.this.updateHourPicker();
            DateTimePicker.this.updateMinutePicker();
            notifyTimeChanged(DateTimePicker.this);
        }
    }

    public DateTimePicker(Context context) {
        this(context, null);
    }

    private void adjustCalendar(Calendar calendar, boolean z2) {
        calendar.set(22, 0);
        calendar.set(21, 0);
        int i2 = calendar.get(20) % this.mMinuteInterval;
        if (i2 != 0) {
            if (!z2) {
                calendar.add(20, -i2);
                return;
            }
            int i3 = calendar.get(20);
            int i4 = this.mMinuteInterval;
            if ((i3 + i4) - i2 < 60) {
                calendar.add(20, i4 - i2);
            } else {
                calendar.add(18, 1);
                calendar.set(20, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCurrentTime() {
        Calendar calendar = this.mMinDate;
        if (calendar != null && calendar.getTimeInMillis() > this.mCalendar.getTimeInMillis()) {
            this.mCalendar.setSafeTimeInMillis(this.mMinDate.getTimeInMillis(), this.mIsLunarMode);
        }
        Calendar calendar2 = this.mMaxDate;
        if (calendar2 == null || calendar2.getTimeInMillis() >= this.mCalendar.getTimeInMillis()) {
            return;
        }
        this.mCalendar.setSafeTimeInMillis(this.mMaxDate.getTimeInMillis(), this.mIsLunarMode);
    }

    private void checkDisplayeValid(NumberPicker numberPicker, int i2, int i3) {
        String[] displayedValues = numberPicker.getDisplayedValues();
        if (displayedValues == null || displayedValues.length >= (i3 - i2) + 1) {
            return;
        }
        numberPicker.setDisplayedValues(null);
    }

    private int computeDayCount(Calendar calendar, Calendar calendar2) {
        Calendar calendar3 = (Calendar) calendar.clone();
        Calendar calendar4 = (Calendar) calendar2.clone();
        calendar3.set(18, 0);
        calendar3.set(20, 0);
        calendar3.set(21, 0);
        calendar3.set(22, 0);
        calendar4.set(18, 0);
        calendar4.set(20, 0);
        calendar4.set(21, 0);
        calendar4.set(22, 0);
        return (int) (((((calendar3.getTimeInMillis() / 1000) / 60) / 60) / 24) - ((((calendar4.getTimeInMillis() / 1000) / 60) / 60) / 24));
    }

    private String formatDay(int i2, int i3, int i4) {
        DayFormatter dayFormatter = DEFAULT_DAY_FORMATTER;
        if (this.mIsLunarMode) {
            if (this.mLunarFormatter == null) {
                this.mLunarFormatter = new LunarFormatter(getContext());
            }
            dayFormatter = this.mLunarFormatter;
        }
        DayFormatter dayFormatter2 = this.mDayFormatter;
        if (dayFormatter2 != null) {
            dayFormatter = dayFormatter2;
        }
        return dayFormatter.formatDay(i2, i3, i4);
    }

    private void reorderLayout() {
        Resources resources = getResources();
        boolean z2 = resources.getConfiguration().getLayoutDirection() == 1;
        boolean zStartsWith = resources.getString(R.string.fmt_time_12hour_minute).startsWith(AnimatedProperty.PROPERTY_NAME_H);
        if (!(zStartsWith && z2) && (zStartsWith || z2)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) this.mHourPicker.getParent();
        viewGroup.removeView(this.mHourPicker);
        viewGroup.addView(this.mHourPicker, viewGroup.getChildCount());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDayPicker(boolean z2) {
        String[] strArr;
        Calendar calendar = this.mMinDate;
        int iComputeDayCount = calendar == null ? Integer.MAX_VALUE : computeDayCount(this.mCalendar, calendar);
        Calendar calendar2 = this.mMaxDate;
        int iComputeDayCount2 = calendar2 != null ? computeDayCount(calendar2, this.mCalendar) : Integer.MAX_VALUE;
        if (iComputeDayCount > 1 || iComputeDayCount2 > 1) {
            checkDisplayeValid(this.mDayPicker, 0, 4);
            this.mDayPicker.setMinValue(0);
            this.mDayPicker.setMaxValue(4);
            if (iComputeDayCount <= 1) {
                this.mDayPicker.setValue(iComputeDayCount);
                this.mDayLastValue = iComputeDayCount;
                this.mDayPicker.setWrapSelectorWheel(false);
            }
            if (iComputeDayCount2 <= 1) {
                int i2 = 4 - iComputeDayCount2;
                this.mDayLastValue = i2;
                this.mDayPicker.setValue(i2);
                this.mDayPicker.setWrapSelectorWheel(false);
            }
            if (iComputeDayCount > 1 && iComputeDayCount2 > 1) {
                this.mDayPicker.setWrapSelectorWheel(true);
            }
        } else {
            int iComputeDayCount3 = computeDayCount(this.mMaxDate, this.mMinDate);
            checkDisplayeValid(this.mDayPicker, 0, iComputeDayCount3);
            this.mDayPicker.setMinValue(0);
            this.mDayPicker.setMaxValue(iComputeDayCount3);
            this.mDayPicker.setValue(iComputeDayCount);
            this.mDayLastValue = iComputeDayCount;
            this.mDayPicker.setWrapSelectorWheel(false);
        }
        int maxValue = (this.mDayPicker.getMaxValue() - this.mDayPicker.getMinValue()) + 1;
        if (z2 || (strArr = this.mDayDisplayValues) == null || strArr.length != maxValue) {
            this.mDayDisplayValues = new String[maxValue];
        }
        int value = this.mDayPicker.getValue();
        ThreadLocal<Calendar> threadLocal = sCalCache;
        Calendar calendar3 = threadLocal.get();
        if (calendar3 == null) {
            calendar3 = new Calendar();
            threadLocal.set(calendar3);
        }
        calendar3.setSafeTimeInMillis(this.mCalendar.getTimeInMillis(), this.mIsLunarMode);
        this.mDayDisplayValues[value] = formatDay(calendar3.get(1), calendar3.get(5), calendar3.get(9));
        for (int i3 = 1; i3 <= 2; i3++) {
            calendar3.add(12, 1);
            int i4 = (value + i3) % 5;
            String[] strArr2 = this.mDayDisplayValues;
            if (i4 >= strArr2.length) {
                break;
            }
            strArr2[i4] = formatDay(calendar3.get(1), calendar3.get(5), calendar3.get(9));
        }
        calendar3.setSafeTimeInMillis(this.mCalendar.getTimeInMillis(), this.mIsLunarMode);
        for (int i5 = 1; i5 <= 2; i5++) {
            calendar3.add(12, -1);
            int i6 = ((value - i5) + 5) % 5;
            String[] strArr3 = this.mDayDisplayValues;
            if (i6 >= strArr3.length) {
                break;
            }
            strArr3[i6] = formatDay(calendar3.get(1), calendar3.get(5), calendar3.get(9));
        }
        this.mDayPicker.setDisplayedValues(this.mDayDisplayValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHourPicker() {
        boolean z2;
        Calendar calendar = this.mMaxDate;
        if (calendar == null || computeDayCount(this.mCalendar, calendar) != 0) {
            z2 = false;
        } else {
            this.mHourPicker.setMaxValue(this.mMaxDate.get(18));
            this.mHourPicker.setWrapSelectorWheel(false);
            z2 = true;
        }
        Calendar calendar2 = this.mMinDate;
        if (calendar2 != null && computeDayCount(this.mCalendar, calendar2) == 0) {
            this.mHourPicker.setMinValue(this.mMinDate.get(18));
            this.mHourPicker.setWrapSelectorWheel(false);
            z2 = true;
        }
        if (!z2) {
            this.mHourPicker.setMinValue(0);
            this.mHourPicker.setMaxValue(23);
            this.mHourPicker.setWrapSelectorWheel(true);
        }
        this.mHourPicker.setValue(this.mCalendar.get(18));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMinutePicker() {
        boolean z2;
        Calendar calendar = this.mMaxDate;
        if (calendar != null && computeDayCount(this.mCalendar, calendar) == 0 && this.mCalendar.get(18) == this.mMaxDate.get(18)) {
            int i2 = this.mMaxDate.get(20);
            this.mMinutePicker.setMinValue(0);
            this.mMinutePicker.setMaxValue(i2 / this.mMinuteInterval);
            this.mMinutePicker.setWrapSelectorWheel(false);
            z2 = true;
        } else {
            z2 = false;
        }
        Calendar calendar2 = this.mMinDate;
        if (calendar2 != null && computeDayCount(this.mCalendar, calendar2) == 0 && this.mCalendar.get(18) == this.mMinDate.get(18)) {
            this.mMinutePicker.setMinValue(this.mMinDate.get(20) / this.mMinuteInterval);
            this.mMinutePicker.setWrapSelectorWheel(false);
            z2 = true;
        }
        if (!z2) {
            int i3 = this.mMinuteInterval;
            int i4 = 60 / i3;
            if (60 % i3 == 0) {
                i4--;
            }
            checkDisplayeValid(this.mMinutePicker, 0, i4);
            this.mMinutePicker.setMinValue(0);
            this.mMinutePicker.setMaxValue(i4);
            this.mMinutePicker.setWrapSelectorWheel(true);
        }
        int maxValue = (this.mMinutePicker.getMaxValue() - this.mMinutePicker.getMinValue()) + 1;
        String[] strArr = this.mMinuteDisplayValues;
        if (strArr == null || strArr.length != maxValue) {
            this.mMinuteDisplayValues = new String[maxValue];
            for (int i5 = 0; i5 < maxValue; i5++) {
                this.mMinuteDisplayValues[i5] = NumberPicker.TWO_DIGIT_FORMATTER.format((this.mMinutePicker.getMinValue() + i5) * this.mMinuteInterval);
            }
            this.mMinutePicker.setDisplayedValues(this.mMinuteDisplayValues);
        }
        this.mMinutePicker.setValue(this.mCalendar.get(20) / this.mMinuteInterval);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public long getTimeInMillis() {
        return this.mCalendar.getTimeInMillis();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(DateTimePicker.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DateTimePicker.class.getName());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.mCalendar.getTimeInMillis(), 1420));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mIsLunarMode = savedState.mIsLunar;
        update(savedState.getTimeInMillis());
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), getTimeInMillis(), this.mIsLunarMode);
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        this.mDayFormatter = dayFormatter;
        updateDayPicker(true);
    }

    public void setLunarMode(boolean z2) {
        boolean z3 = this.mIsLunarMode;
        this.mIsLunarMode = z2;
        updateDayPicker(true);
        if (z3 != this.mIsLunarMode) {
            this.mDayPicker.requestLayout();
        }
    }

    public void setMaxDateTime(long j2) {
        if (j2 <= 0) {
            this.mMaxDate = null;
        } else {
            Calendar calendar = new Calendar();
            this.mMaxDate = calendar;
            calendar.setSafeTimeInMillis(j2, this.mIsLunarMode);
            adjustCalendar(this.mMaxDate, false);
            Calendar calendar2 = this.mMinDate;
            if (calendar2 != null && calendar2.getTimeInMillis() > this.mMaxDate.getTimeInMillis()) {
                this.mMaxDate.setSafeTimeInMillis(this.mMinDate.getTimeInMillis(), this.mIsLunarMode);
            }
        }
        checkCurrentTime();
        updateDayPicker(true);
        updateHourPicker();
        updateMinutePicker();
    }

    public void setMinDateTime(long j2) {
        if (j2 <= 0) {
            this.mMinDate = null;
        } else {
            Calendar calendar = new Calendar();
            this.mMinDate = calendar;
            calendar.setSafeTimeInMillis(j2, this.mIsLunarMode);
            if (this.mMinDate.get(21) != 0 || this.mMinDate.get(22) != 0) {
                this.mMinDate.add(20, 1);
            }
            adjustCalendar(this.mMinDate, true);
            Calendar calendar2 = this.mMaxDate;
            if (calendar2 != null && calendar2.getTimeInMillis() < this.mMinDate.getTimeInMillis()) {
                this.mMinDate.setSafeTimeInMillis(this.mMaxDate.getTimeInMillis(), this.mIsLunarMode);
            }
        }
        checkCurrentTime();
        updateDayPicker(true);
        updateHourPicker();
        updateMinutePicker();
    }

    public void setMinuteInterval(@IntRange(from = 1, to = 30) int i2) {
        if (this.mMinuteInterval == i2) {
            return;
        }
        this.mMinuteInterval = i2;
        adjustCalendar(this.mCalendar, true);
        checkCurrentTime();
        updateHourPicker();
        updateMinutePicker();
    }

    public void setOnTimeChangedListener(OnDateTimeChangedListener onDateTimeChangedListener) {
        this.mListener = onDateTimeChangedListener;
    }

    public void update(long j2) {
        this.mCalendar.setSafeTimeInMillis(j2, this.mIsLunarMode);
        adjustCalendar(this.mCalendar, true);
        checkCurrentTime();
        updateDayPicker(true);
        updateHourPicker();
        updateMinutePicker();
    }

    public DateTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.dateTimePickerStyle);
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: miuix.pickerwidget.widget.DateTimePicker.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        private boolean mIsLunar;
        private long mTimeInMillis;

        public SavedState(Parcelable parcelable, long j2, boolean z2) {
            super(parcelable);
            this.mTimeInMillis = j2;
            this.mIsLunar = z2;
        }

        public long getTimeInMillis() {
            return this.mTimeInMillis;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeLong(this.mTimeInMillis);
            parcel.writeInt(this.mIsLunar ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mTimeInMillis = parcel.readLong();
            this.mIsLunar = parcel.readInt() == 1;
        }
    }

    public DateTimePicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMinuteInterval = 1;
        this.mMinDate = null;
        this.mMaxDate = null;
        this.mDayDisplayValues = null;
        this.mIsLunarMode = false;
        DEFAULT_DAY_FORMATTER = new DayFormatter(getContext());
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.miuix_appcompat_date_time_picker, (ViewGroup) this, true);
        PickerValueChangeListener pickerValueChangeListener = new PickerValueChangeListener();
        Calendar calendar = new Calendar();
        this.mCalendar = calendar;
        adjustCalendar(calendar, true);
        ThreadLocal<Calendar> threadLocal = sCalCache;
        Calendar calendar2 = threadLocal.get();
        if (calendar2 == null) {
            calendar2 = new Calendar();
            threadLocal.set(calendar2);
        }
        calendar2.setSafeTimeInMillis(0L, this.mIsLunarMode);
        this.mDayPicker = (NumberPicker) findViewById(R.id.day);
        this.mHourPicker = (NumberPicker) findViewById(R.id.hour);
        this.mMinutePicker = (NumberPicker) findViewById(R.id.minute);
        this.mDayPicker.setOnValueChangedListener(pickerValueChangeListener);
        this.mDayPicker.setMaxFlingSpeedFactor(3.0f);
        this.mHourPicker.setOnValueChangedListener(pickerValueChangeListener);
        this.mMinutePicker.setOnValueChangedListener(pickerValueChangeListener);
        this.mMinutePicker.setMinValue(0);
        this.mMinutePicker.setMaxValue(59);
        this.mHourPicker.setFormatter(NumberPicker.TWO_DIGIT_FORMATTER);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DateTimePicker, i2, 0);
        this.mIsLunarMode = typedArrayObtainStyledAttributes.getBoolean(R.styleable.DateTimePicker_lunarCalendar, false);
        typedArrayObtainStyledAttributes.recycle();
        reorderLayout();
        checkCurrentTime();
        updateDayPicker(true);
        updateHourPicker();
        updateMinutePicker();
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }
}
