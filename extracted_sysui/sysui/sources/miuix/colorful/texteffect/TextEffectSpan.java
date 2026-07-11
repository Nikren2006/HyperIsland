package miuix.colorful.texteffect;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public class TextEffectSpan extends TextAppearanceSpan {
    private TextEffectAnimator mController;
    private boolean mHasDispose;
    private int mId;
    private boolean mNeedUpdate;
    private CharSequence mText;

    public TextEffectSpan(@NonNull TextEffectAnimator textEffectAnimator, int i2, Context context) {
        this(context, 0);
        this.mController = textEffectAnimator;
        this.mId = i2;
    }

    public void clear() {
        TextEffectAnimator textEffectAnimator = this.mController;
        if (textEffectAnimator != null) {
            textEffectAnimator.cancel();
        }
    }

    public void dispose() {
        this.mHasDispose = true;
    }

    public boolean isDispose() {
        return this.mHasDispose;
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence;
    }

    @Override // android.text.style.TextAppearanceSpan, android.text.style.CharacterStyle
    public void updateDrawState(@NonNull TextPaint textPaint) {
        super.updateDrawState(textPaint);
        TextEffectAnimator textEffectAnimator = this.mController;
        if (textEffectAnimator != null) {
            int effectColor = textEffectAnimator.getEffectColor();
            int alphaInt = this.mController.getAlphaInt();
            int blurRadius = this.mController.getBlurRadius();
            textPaint.setColor((effectColor & ViewCompat.MEASURED_SIZE_MASK) | ((((effectColor >>> 24) * alphaInt) / 255) << 24));
            textPaint.setMaskFilter(blurRadius == 0 ? null : TextEffectAnimator.PREBUILT_BLUR_FILTERS[blurRadius - 1]);
        }
    }

    private TextEffectSpan(Context context, int i2) {
        super(context, i2);
        this.mNeedUpdate = false;
        this.mHasDispose = false;
    }

    private TextEffectSpan(Context context, int i2, int i3) {
        super(context, i2, i3);
        this.mNeedUpdate = false;
        this.mHasDispose = false;
    }

    public TextEffectSpan(String str, int i2, int i3, ColorStateList colorStateList, ColorStateList colorStateList2) {
        super(str, i2, i3, colorStateList, colorStateList2);
        this.mNeedUpdate = false;
        this.mHasDispose = false;
    }
}
