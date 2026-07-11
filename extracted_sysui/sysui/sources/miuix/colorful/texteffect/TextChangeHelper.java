package miuix.colorful.texteffect;

import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class TextChangeHelper implements TextChangeProcessor {
    private final SparseArray<TimerTextEffectSpan> mEffectSpanArray = new SparseArray<>();

    private void addSpanStyle(TimerTextEffectView timerTextEffectView, SpannableStringBuilder spannableStringBuilder) {
        TimerTextEffectSpan timerTextEffectSpan = this.mEffectSpanArray.get(0);
        if (timerTextEffectSpan == null) {
            timerTextEffectSpan = new TimerTextEffectSpan(timerTextEffectView, 0, timerTextEffectView.getCurrentTextColor(), new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f}, -2);
            this.mEffectSpanArray.append(0, timerTextEffectSpan);
        }
        timerTextEffectSpan.setEnableAnimWithInit(timerTextEffectView.isEnableEffectWithInit());
        spannableStringBuilder.setSpan(timerTextEffectSpan, 0, spannableStringBuilder.length(), 18);
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public void clear() {
        this.mEffectSpanArray.clear();
    }

    @Override // miuix.colorful.texteffect.TextChangeProcessor
    public CharSequence formatContent(TimerTextEffectView timerTextEffectView, @Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        SpannableStringBuilder spannableStringBuilder = !(charSequence instanceof SpannableStringBuilder) ? new SpannableStringBuilder(charSequence) : (SpannableStringBuilder) charSequence;
        addSpanStyle(timerTextEffectView, spannableStringBuilder);
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
}
