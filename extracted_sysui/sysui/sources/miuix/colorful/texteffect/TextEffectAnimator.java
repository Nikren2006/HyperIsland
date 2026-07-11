package miuix.colorful.texteffect;

import android.graphics.BlurMaskFilter;
import android.text.Spannable;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import miuix.animation.Folme;
import miuix.animation.FolmeEase;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ColorProperty;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IntValueProperty;
import miuix.animation.property.ValueProperty;
import miuix.colorful.texteffect.TextEffectConfig;

/* JADX INFO: loaded from: classes3.dex */
public class TextEffectAnimator {
    public static final ValueProperty BLUR_RADIUS;
    public static final FloatProperty EFFECT_COLOR;
    static BlurMaskFilter[] PREBUILT_BLUR_FILTERS = new BlurMaskFilter[40];
    static final long TEXT_COLOR_ANIM_DELAY = 200;
    static final long TEXT_COLOR_ANIM_DURATION = 150;
    static final long TEXT_SHOW_ANIM_DURATION = 300;
    private int alphaInt;
    private int blurRadius;
    private int color;
    private int colorAnimStage;
    private final TextEffectConfig mEffectConfig;
    private final IStateStyle mFolmeState;
    private final WeakReference<TextView> mHost;
    private int mId;
    private TextEffectSpan mSpan;
    private CharSequence mText;
    private long showAnimDuration;
    private long textAnimDelay;
    private long textAnimDuration;

    static {
        for (int i2 = 0; i2 < 40; i2++) {
            PREBUILT_BLUR_FILTERS[i2] = new BlurMaskFilter(i2 + 1.0f, BlurMaskFilter.Blur.NORMAL);
        }
        BLUR_RADIUS = new IntValueProperty("blurRadius", 1.0f);
        EFFECT_COLOR = new ColorProperty<TextEffectAnimator>("effectColor") { // from class: miuix.colorful.texteffect.TextEffectAnimator.1
            @Override // miuix.animation.property.ColorProperty, miuix.animation.property.IIntValueProperty
            public int getIntValue(TextEffectAnimator textEffectAnimator) {
                return textEffectAnimator.color;
            }

            @Override // miuix.animation.property.ColorProperty, miuix.animation.property.IIntValueProperty
            public void setIntValue(TextEffectAnimator textEffectAnimator, int i3) {
                super.setIntValue(textEffectAnimator, i3);
                textEffectAnimator.color = i3;
            }
        };
    }

    public TextEffectAnimator(@NonNull TextView textView, int i2, int i3) {
        this(textView, null, i2, i3);
    }

    public static /* synthetic */ int access$208(TextEffectAnimator textEffectAnimator) {
        int i2 = textEffectAnimator.colorAnimStage;
        textEffectAnimator.colorAnimStage = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animToColor(int i2) {
        AnimConfig animConfig = new AnimConfig();
        if (this.colorAnimStage == 1) {
            animConfig.setDelay(this.textAnimDelay);
        }
        animConfig.enableStartImmediately(false).setEase(FolmeEase.sineOut(this.textAnimDuration)).addListeners(new TransitionListener() { // from class: miuix.colorful.texteffect.TextEffectAnimator.3
            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                TextEffectAnimator.this.removeSpan();
                Folme.clean(TextEffectAnimator.this);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                TextEffectAnimator.access$208(TextEffectAnimator.this);
                if (TextEffectAnimator.this.colorAnimStage >= TextEffectAnimator.this.mEffectConfig.colorsConfig.length) {
                    TextEffectAnimator.this.removeSpan();
                    Folme.clean(TextEffectAnimator.this);
                } else {
                    TextEffectAnimator textEffectAnimator = TextEffectAnimator.this;
                    textEffectAnimator.animToColor(textEffectAnimator.mEffectConfig.colorsConfig[TextEffectAnimator.this.colorAnimStage]);
                }
            }
        });
        this.mFolmeState.to(EFFECT_COLOR, Integer.valueOf(i2), animConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSpan() {
        TextView textView = this.mHost.get();
        if (textView != null) {
            if (textView.getText() instanceof Spannable) {
                ((Spannable) textView.getText()).removeSpan(this.mSpan);
            }
            this.mSpan.dispose();
        }
    }

    public void attachSpan(TextEffectSpan textEffectSpan) {
        this.mSpan = textEffectSpan;
    }

    public void cancel() {
        IStateStyle iStateStyle = this.mFolmeState;
        if (iStateStyle != null) {
            iStateStyle.cancel();
        }
    }

    public int getAlphaInt() {
        return this.alphaInt;
    }

    public long getAnimDuration() {
        int[] iArr = this.mEffectConfig.colorsConfig;
        if (iArr == null) {
            return this.showAnimDuration;
        }
        long length = ((long) iArr.length) * this.textAnimDuration;
        long j2 = this.showAnimDuration;
        return length + j2 + (j2 - this.textAnimDelay);
    }

    public int getBlurRadius() {
        return this.blurRadius;
    }

    public int getEffectColor() {
        return this.color;
    }

    public void setAlphaInt(int i2) {
        this.alphaInt = i2;
    }

    public void setBlurRadius(int i2) {
        this.blurRadius = i2;
    }

    public void setEffectColor(int i2) {
        this.color = i2;
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence;
    }

    public void start() {
        AnimConfig animConfig = new AnimConfig();
        animConfig.enableStartImmediately(false);
        animConfig.setEase(FolmeEase.sineOut(this.showAnimDuration)).addListeners(new TransitionListener() { // from class: miuix.colorful.texteffect.TextEffectAnimator.2
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                super.onBegin(obj);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                TextEffectAnimator.this.removeSpan();
                Folme.clean(TextEffectAnimator.this);
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
            }
        });
        this.mFolmeState.to(ValueProperty.ALPHA_INT, Integer.valueOf(this.mEffectConfig.alphaIntConfig[1]), BLUR_RADIUS, 0, animConfig);
        int[] iArr = this.mEffectConfig.colorsConfig;
        if (iArr != null) {
            int i2 = this.colorAnimStage + 1;
            this.colorAnimStage = i2;
            animToColor(iArr[i2]);
        }
    }

    public TextEffectAnimator(@NonNull TextView textView, TextEffectConfig textEffectConfig, int i2, int i3) {
        this.mId = i2;
        this.mHost = new WeakReference<>(textView);
        this.mFolmeState = Folme.useValue(this);
        textEffectConfig = textEffectConfig == null ? new TextEffectConfig.Builder(i3).create() : textEffectConfig;
        this.mEffectConfig = textEffectConfig;
        this.alphaInt = textEffectConfig.alphaIntConfig[0];
        this.blurRadius = textEffectConfig.blurRadius;
        int[] iArr = textEffectConfig.colorsConfig;
        if (iArr != null) {
            this.color = iArr[0];
        } else {
            this.color = i3;
        }
        this.colorAnimStage = 0;
        this.showAnimDuration = 300L;
        this.textAnimDelay = 200L;
        this.textAnimDuration = TEXT_COLOR_ANIM_DURATION;
    }
}
