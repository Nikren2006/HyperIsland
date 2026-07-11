package miuix.colorful.texteffect.formatter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import miuix.colorful.texteffect.DateFormatterIntf;
import miuix.colorful.texteffect.TimerTextEffectSpan;
import miuix.colorful.texteffect.TimerTextEffectView;

/* JADX INFO: loaded from: classes3.dex */
public class SpannableTimeFormatter implements DateFormatterIntf {
    private int mNumBackgroundColor;
    private int mNumForegroundColor;
    private int mSplitBackgroundColor;
    private int mSplitForegroundColor;
    private DateFormatterIntf mWrappedFormatter;
    private final SparseArray<TimerTextEffectSpan> mEffectSpanArray = new SparseArray<>();
    public final SparseArray<Bitmap> mNumberBitmapArray = new SparseArray<>();

    public SpannableTimeFormatter(DateFormatterIntf dateFormatterIntf) {
        this.mWrappedFormatter = dateFormatterIntf;
    }

    private void addSpanStyle(TimerTextEffectView timerTextEffectView, float f2, SpannableStringBuilder spannableStringBuilder) {
        float f3 = f2 / 6.0f;
        int i2 = -1;
        for (int length = spannableStringBuilder.length() - 1; length >= 0; length--) {
            TimerTextEffectSpan timerTextEffectSpan = this.mEffectSpanArray.get(length);
            if (timerTextEffectSpan == null) {
                if (Character.isDigit(spannableStringBuilder.charAt(length))) {
                    i2++;
                    int i3 = this.mNumBackgroundColor;
                    timerTextEffectSpan = new TimerTextEffectSpan(timerTextEffectView, i3, this.mNumForegroundColor, i3 == 0 ? new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f} : new float[]{f3, f3, f3, f3, f3}, i2);
                } else {
                    timerTextEffectSpan = new TimerTextEffectSpan(timerTextEffectView, this.mSplitBackgroundColor, this.mSplitForegroundColor, this.mNumBackgroundColor == 0 ? new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f} : new float[]{0.0f, 0.0f, 0.0f, f3, f3}, -1);
                }
                this.mEffectSpanArray.append(length, timerTextEffectSpan);
            }
            timerTextEffectSpan.setEnableAnimWithInit(timerTextEffectView.isEnableEffectWithInit());
            spannableStringBuilder.setSpan(timerTextEffectSpan, length, length + 1, 18);
        }
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public void clear() {
        this.mEffectSpanArray.clear();
        for (int i2 = 0; i2 < this.mNumberBitmapArray.size(); i2++) {
            Bitmap bitmap = this.mNumberBitmapArray.get(i2);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        this.mNumberBitmapArray.clear();
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public CharSequence formatContent(TimerTextEffectView timerTextEffectView, @Nullable CharSequence charSequence) {
        return charSequence;
    }

    @Override // miuix.colorful.texteffect.DateFormatterIntf
    public CharSequence formatTime(TimerTextEffectView timerTextEffectView, long j2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(this.mWrappedFormatter.formatTime(timerTextEffectView, j2));
        addSpanStyle(timerTextEffectView, timerTextEffectView.getTextSize(), spannableStringBuilder);
        return spannableStringBuilder;
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public boolean isRunningAnim() {
        for (int i2 = 0; i2 < this.mEffectSpanArray.size(); i2++) {
            if (this.mEffectSpanArray.get(i2).isRunningAnim()) {
                return true;
            }
        }
        return false;
    }

    public void setNumColor(int i2, int i3) {
        this.mNumForegroundColor = i2;
        this.mNumBackgroundColor = i3;
    }

    public void setSplitColor(int i2, int i3) {
        this.mSplitForegroundColor = i2;
        this.mSplitBackgroundColor = i3;
    }

    public static class Builder {
        private DateFormatterIntf innerFormatter;
        private int numBackgroundColor;
        private int numForegroundColor;
        private boolean setNumBg;
        private boolean setNumFg;
        private boolean setSplitBg;
        private boolean setSplitFg;
        private int splitBackgroundColor;
        private int splitForegroundColor;

        private void createNumericBitmap(TextView textView, SpannableTimeFormatter spannableTimeFormatter) {
            Canvas canvas = new Canvas();
            Paint paint = new Paint(textView.getPaint());
            paint.setColor(textView.getCurrentTextColor());
            float f2 = 0.0f;
            for (int i2 = 0; i2 < 10; i2++) {
                float fMeasureText = paint.measureText(String.valueOf(i2));
                if (fMeasureText > f2) {
                    f2 = fMeasureText;
                }
            }
            int lineHeight = textView.getLineHeight();
            int paddingTop = textView.getPaddingTop() - textView.getPaint().getFontMetricsInt().top;
            for (int i3 = 0; i3 < 10; i3++) {
                float fMeasureText2 = paint.measureText(String.valueOf(i3));
                String strValueOf = String.valueOf(i3);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap((int) f2, lineHeight, Bitmap.Config.ARGB_8888);
                canvas.setBitmap(bitmapCreateBitmap);
                canvas.drawText(strValueOf, (f2 - fMeasureText2) / 2.0f, paddingTop, paint);
                spannableTimeFormatter.mNumberBitmapArray.put(i3, bitmapCreateBitmap);
            }
        }

        public SpannableTimeFormatter build() {
            if (this.innerFormatter == null) {
                this.innerFormatter = new DefaultDateFormatter(DefaultDateFormatter.FORMAT_MM_SS);
            }
            SpannableTimeFormatter spannableTimeFormatter = new SpannableTimeFormatter(this.innerFormatter);
            boolean z2 = this.setNumBg;
            int i2 = ViewCompat.MEASURED_STATE_MASK;
            spannableTimeFormatter.mNumBackgroundColor = !z2 ? -16777216 : this.numBackgroundColor;
            spannableTimeFormatter.mNumForegroundColor = !this.setNumFg ? -1 : this.numForegroundColor;
            spannableTimeFormatter.mSplitBackgroundColor = !this.setSplitBg ? 0 : this.splitBackgroundColor;
            if (this.setSplitFg) {
                i2 = this.splitForegroundColor;
            }
            spannableTimeFormatter.mSplitForegroundColor = i2;
            return spannableTimeFormatter;
        }

        public Builder setFormatter(DateFormatterIntf dateFormatterIntf) {
            this.innerFormatter = dateFormatterIntf;
            return this;
        }

        public Builder setNumBackgroundColor(int i2) {
            this.numBackgroundColor = i2;
            this.setNumBg = true;
            return this;
        }

        public Builder setNumForegroundColor(int i2) {
            this.numForegroundColor = i2;
            this.setNumFg = true;
            return this;
        }

        public Builder setSplitBackgroundColor(int i2) {
            this.splitBackgroundColor = i2;
            this.setSplitBg = true;
            return this;
        }

        public Builder setSplitForegroundColor(int i2) {
            this.splitForegroundColor = i2;
            this.setSplitFg = true;
            return this;
        }

        public SpannableTimeFormatter build(TimerTextEffectView timerTextEffectView) {
            if (this.innerFormatter == null) {
                this.innerFormatter = new DefaultDateFormatter(DefaultDateFormatter.FORMAT_MM_SS);
            }
            SpannableTimeFormatter spannableTimeFormatter = new SpannableTimeFormatter(this.innerFormatter);
            createNumericBitmap(timerTextEffectView, spannableTimeFormatter);
            spannableTimeFormatter.mNumBackgroundColor = 0;
            spannableTimeFormatter.mNumForegroundColor = timerTextEffectView.getCurrentTextColor();
            spannableTimeFormatter.mSplitBackgroundColor = 0;
            spannableTimeFormatter.mSplitForegroundColor = timerTextEffectView.getCurrentTextColor();
            return spannableTimeFormatter;
        }
    }
}
