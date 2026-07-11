package miuix.pickerwidget.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import miuix.pickerwidget.R;

/* JADX INFO: loaded from: classes5.dex */
public class NumberPickerGroup extends LinearLayout {
    private static final String EXTRA_TEXT = "    ";
    private final Paint mValuePaint;

    public NumberPickerGroup(Context context) {
        super(context);
        this.mValuePaint = new Paint();
    }

    private boolean isDayNumberPicker(NumberPicker numberPicker) {
        return numberPicker.getId() == R.id.day;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        float displayedMaxTextWidth;
        float originalLabelWidth;
        super.onMeasure(i2, i3);
        if (getOrientation() == 0) {
            int childCount = getChildCount();
            int measuredWidth = 0;
            float fMax = 0.0f;
            float f2 = 0.0f;
            float marginLabelLeft = 0.0f;
            float displayedMaxTextWidth2 = 0.0f;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (childAt instanceof NumberPicker) {
                    NumberPicker numberPicker = (NumberPicker) childAt;
                    displayedMaxTextWidth2 += numberPicker.getDisplayedMaxTextWidth();
                    float originalLabelWidth2 = numberPicker.getOriginalLabelWidth();
                    if (originalLabelWidth2 > 0.0f) {
                        f2 += originalLabelWidth2;
                        marginLabelLeft += numberPicker.getMarginLabelLeft();
                    }
                    fMax = Math.max(fMax, numberPicker.getOriginTextSizeHighlight());
                } else {
                    measuredWidth += childAt.getMeasuredWidth();
                }
            }
            this.mValuePaint.setTextSize(fMax);
            float fMeasureText = this.mValuePaint.measureText(EXTRA_TEXT);
            float f3 = displayedMaxTextWidth2 + (f2 * 2.0f) + fMeasureText;
            float measuredWidth2 = (getMeasuredWidth() - measuredWidth) - (marginLabelLeft * 2.0f);
            float f4 = measuredWidth2 / f3;
            float f5 = f4 < 1.0f ? f4 * fMax : fMax;
            if (f5 <= fMax) {
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt2 = getChildAt(i5);
                    if (childAt2 instanceof NumberPicker) {
                        NumberPicker numberPicker2 = (NumberPicker) childAt2;
                        numberPicker2.setTextSizeHighlight((int) f5);
                        numberPicker2.setTextSizeHint((int) ((numberPicker2.getOriginTextSizeHint() * f5) / fMax));
                        float marginLabelLeft2 = numberPicker2.getOriginalLabelWidth() > 0.0f ? numberPicker2.getMarginLabelLeft() * 2 : 0.0f;
                        if (isDayNumberPicker(numberPicker2)) {
                            displayedMaxTextWidth = numberPicker2.getDisplayedMaxTextWidth() + fMeasureText;
                            originalLabelWidth = numberPicker2.getOriginalLabelWidth();
                        } else {
                            displayedMaxTextWidth = numberPicker2.getDisplayedMaxTextWidth();
                            originalLabelWidth = numberPicker2.getOriginalLabelWidth();
                        }
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (marginLabelLeft2 + (((displayedMaxTextWidth + (originalLabelWidth * 2.0f)) * measuredWidth2) / f3)), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(childAt2.getMeasuredHeight(), BasicMeasure.EXACTLY));
                    }
                }
            }
        }
    }

    public NumberPickerGroup(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mValuePaint = new Paint();
    }

    public NumberPickerGroup(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mValuePaint = new Paint();
    }
}
