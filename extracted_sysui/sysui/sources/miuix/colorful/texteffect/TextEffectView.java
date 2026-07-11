package miuix.colorful.texteffect;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miuix.animation.physics.AnimationHelper;

/* JADX INFO: loaded from: classes3.dex */
public class TextEffectView extends AppCompatTextView implements Runnable {
    public static final boolean DEBUGGABLE = false;
    public static final String TAG = "ColorfulTextEffect";
    private int mAnimCount;
    private int mDefaultTextColor;
    private final List<TextEffectSpan> mDisposedSpans;

    @Nullable
    private TextEffectConfig mEffectConfig;
    private boolean mEnableEffect;
    private final Map<TextEffectSpan, Pair<Integer, Integer>> mSpans;
    private Runnable mStopAnimation;

    public TextEffectView(Context context) {
        this(context, null);
    }

    private void forceUpdateLayout() {
        if (getText() instanceof Spannable) {
            Spannable spannable = (Spannable) getText();
            for (TextWatcher textWatcher : (TextWatcher[]) spannable.getSpans(0, spannable.length(), TextWatcher.class)) {
                if (textWatcher.getClass().toString().contains("android.text.DynamicLayout$ChangeWatcher")) {
                    textWatcher.onTextChanged(spannable, 0, spannable.length(), spannable.length());
                    return;
                }
            }
        }
    }

    private void restoreEffectAfterUpdateText(int i2) {
        for (TextEffectSpan textEffectSpan : this.mSpans.keySet()) {
            if (textEffectSpan.isDispose()) {
                this.mDisposedSpans.add(textEffectSpan);
            } else {
                Pair<Integer, Integer> pair = this.mSpans.get(textEffectSpan);
                Spannable spannable = (Spannable) getText();
                if (pair != null && ((Integer) pair.first).intValue() < i2) {
                    Integer num = (Integer) pair.first;
                    int iIntValue = num.intValue();
                    int iMin = Math.min(((Integer) pair.second).intValue(), i2);
                    if (iMin != ((Integer) pair.second).intValue()) {
                        this.mSpans.put(textEffectSpan, new Pair<>(num, Integer.valueOf(iMin)));
                    }
                    spannable.setSpan(textEffectSpan, iIntValue, iMin, 33);
                }
            }
        }
        Iterator<TextEffectSpan> it = this.mDisposedSpans.iterator();
        while (it.hasNext()) {
            this.mSpans.remove(it.next());
        }
        this.mDisposedSpans.clear();
    }

    private void startNewEffect(int i2, int i3) {
        this.mAnimCount++;
        int i4 = this.mDefaultTextColor;
        Spannable spannable = (Spannable) getText();
        TextEffectAnimator textEffectAnimator = new TextEffectAnimator(this, this.mEffectConfig, this.mAnimCount, i4);
        TextEffectSpan textEffectSpan = new TextEffectSpan(textEffectAnimator, this.mAnimCount, getContext());
        int i5 = (i3 - i2) + i2;
        this.mSpans.put(textEffectSpan, new Pair<>(Integer.valueOf(i2), Integer.valueOf(i5)));
        textEffectAnimator.attachSpan(textEffectSpan);
        spannable.setSpan(textEffectSpan, i2, i5, 33);
        textEffectAnimator.start();
        removeCallbacks(this);
        AnimationHelper.postOnAnimation(this, this);
        removeCallbacks(this.mStopAnimation);
        postDelayed(this.mStopAnimation, textEffectAnimator.getAnimDuration() + 50);
    }

    @Override // android.widget.TextView
    public void append(CharSequence charSequence, int i2, int i3) {
        int length = getText().length();
        super.append(charSequence, i2, i3);
        if (this.mEnableEffect && (getText() instanceof Spannable)) {
            startNewEffect(length, (i3 - i2) + length);
        }
    }

    public void enableEffect(boolean z2) {
        if (this.mEnableEffect != z2) {
            this.mEnableEffect = z2;
            if (z2 || this.mSpans.isEmpty()) {
                return;
            }
            Iterator<TextEffectSpan> it = this.mSpans.keySet().iterator();
            while (it.hasNext()) {
                it.next().clear();
            }
            this.mSpans.clear();
        }
    }

    public boolean isEnableEffect() {
        return this.mEnableEffect;
    }

    @Override // java.lang.Runnable
    public void run() {
        forceUpdateLayout();
        invalidate();
        AnimationHelper.postOnAnimation(this, this);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        int length = getText().length();
        super.setText(charSequence, bufferType);
        if (this.mEnableEffect) {
            int length2 = getText().length();
            if (TextUtils.isEmpty(charSequence)) {
                this.mAnimCount = 0;
                Map<TextEffectSpan, Pair<Integer, Integer>> map = this.mSpans;
                if (map != null) {
                    map.clear();
                    return;
                }
                return;
            }
            if (getText() instanceof Spannable) {
                if (length2 > length) {
                    startNewEffect(length, length2);
                }
                restoreEffectAfterUpdateText(length2);
            }
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        super.setTextColor(i2);
        this.mDefaultTextColor = getCurrentTextColor();
    }

    public void setTextEffect(TextEffectConfig textEffectConfig) {
        this.mEffectConfig = textEffectConfig;
    }

    public TextEffectView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextEffectView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mAnimCount = 0;
        this.mSpans = new HashMap();
        this.mDisposedSpans = new ArrayList();
        this.mEffectConfig = null;
        this.mEnableEffect = true;
        this.mStopAnimation = new Runnable() { // from class: miuix.colorful.texteffect.TextEffectView.1
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                TextEffectView textEffectView = TextEffectView.this;
                textEffectView.removeCallbacks(textEffectView);
            }
        };
        this.mDefaultTextColor = getCurrentTextColor();
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        this.mDefaultTextColor = getCurrentTextColor();
    }
}
