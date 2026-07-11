package miuix.colorful.texteffect;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public class CharChangeProcessor {
    private final SparseArray<HyperChronometerEffectSpan> mEffectSpanArray = new SparseArray<>();

    /* JADX WARN: Removed duplicated region for block: B:28:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void addSpanStyle(miuix.colorful.texteffect.HyperChronometer r24, android.text.Spannable r25) {
        /*
            Method dump skipped, instruction units count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.colorful.texteffect.CharChangeProcessor.addSpanStyle(miuix.colorful.texteffect.HyperChronometer, android.text.Spannable):void");
    }

    public void clear() {
        this.mEffectSpanArray.clear();
    }

    @Nullable
    public CharSequence formatContent(HyperChronometer hyperChronometer, @Nullable CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        Spannable spannableStringBuilder = charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableStringBuilder(charSequence);
        addSpanStyle(hyperChronometer, spannableStringBuilder);
        return spannableStringBuilder;
    }

    public boolean isRunningAnim() {
        for (int i2 = 0; i2 < this.mEffectSpanArray.size(); i2++) {
            if (this.mEffectSpanArray.get(i2).isRunningAnim()) {
                return true;
            }
        }
        return false;
    }
}
