package miuix.colorful.texteffect;

import android.content.Context;
import android.text.Spannable;
import android.util.AttributeSet;
import android.widget.Chronometer;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import miuix.animation.physics.AnimationHelper;
import miuix.device.DeviceUtils;

/* JADX INFO: loaded from: classes3.dex */
public class HyperChronometer extends Chronometer implements TextSwitcher {
    public static boolean DEBUGGABLE = false;
    private static final int DEFAULT_INTERVAL = 1000;
    public static final String TAG = "HyperChronometer";

    @Nullable
    private CountdownListener mCountdownListener;
    private long mDeadlineTime;
    private int mEffectLevel;
    private boolean mEnableEffect;
    private boolean mEnableEffectWithInit;
    private int mInterval;
    private boolean mRequestAnimation;
    private final Runnable mStopAnimation;
    private final Runnable mStopTextChangeAnimation;

    @Nullable
    private CharChangeProcessor mTextChangeProcessor;

    public HyperChronometer(Context context) {
        this(context, null);
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void cancelAnim() {
        if (getText() instanceof Spannable) {
            Spannable spannable = (Spannable) getText();
            for (HyperChronometerEffectSpan hyperChronometerEffectSpan : (HyperChronometerEffectSpan[]) spannable.getSpans(0, spannable.length(), HyperChronometerEffectSpan.class)) {
                hyperChronometerEffectSpan.clear();
            }
        }
        CharChangeProcessor charChangeProcessor = this.mTextChangeProcessor;
        if (charChangeProcessor != null) {
            charChangeProcessor.clear();
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void enableEffect(boolean z2) {
        if (this.mEnableEffect != z2) {
            this.mEnableEffect = z2;
            if (getText() instanceof Spannable) {
                Spannable spannable = (Spannable) getText();
                HyperChronometerEffectSpan[] hyperChronometerEffectSpanArr = (HyperChronometerEffectSpan[]) spannable.getSpans(0, spannable.length(), HyperChronometerEffectSpan.class);
                for (int i2 = 0; i2 < hyperChronometerEffectSpanArr.length; i2++) {
                    hyperChronometerEffectSpanArr[i2].clear();
                    hyperChronometerEffectSpanArr[i2].setEnableAnim(this.mEnableEffect);
                }
            }
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public int getEffectLevel() {
        return this.mEffectLevel;
    }

    @Nullable
    public CharChangeProcessor getTextChangeProcessor() {
        return this.mTextChangeProcessor;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public boolean isEnableEffect() {
        return this.mEnableEffect;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public boolean isEnableEffectWithInit() {
        return this.mEnableEffectWithInit;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void requestAnimation() {
        if (this.mTextChangeProcessor == null) {
            return;
        }
        this.mRequestAnimation = true;
        AnimationHelper.postOnAnimation(this, this);
    }

    @Override // java.lang.Runnable
    public void run() {
        CharChangeProcessor charChangeProcessor;
        invalidate();
        if (this.mRequestAnimation || (charChangeProcessor = this.mTextChangeProcessor) == null || charChangeProcessor.isRunningAnim()) {
            this.mRequestAnimation = false;
            AnimationHelper.postOnAnimation(this, this);
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void setEffectLevel(int i2) {
        if (this.mEffectLevel != i2) {
            this.mEffectLevel = i2;
            if (getText() instanceof Spannable) {
                Spannable spannable = (Spannable) getText();
                HyperChronometerEffectSpan[] hyperChronometerEffectSpanArr = (HyperChronometerEffectSpan[]) spannable.getSpans(0, spannable.length(), HyperChronometerEffectSpan.class);
                for (int i3 = 0; i3 < hyperChronometerEffectSpanArr.length; i3++) {
                    hyperChronometerEffectSpanArr[i3].clear();
                    hyperChronometerEffectSpanArr[i3].setEnableAnim(i2 != 0);
                }
            }
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void setEnableEffectWithInit(boolean z2) {
        this.mEnableEffectWithInit = z2;
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        CharSequence text = getText();
        if (this.mTextChangeProcessor == null) {
            Runnable runnable = this.mStopTextChangeAnimation;
            if (runnable != null) {
                runnable.run();
                removeCallbacks(this.mStopTextChangeAnimation);
            }
            super.setText(charSequence, bufferType);
            return;
        }
        removeCallbacks(this.mStopTextChangeAnimation);
        this.mStopTextChangeAnimation.run();
        CharSequence content = this.mTextChangeProcessor.formatContent(this, charSequence);
        if (content instanceof Spannable) {
            String string = content.toString();
            Spannable spannable = (Spannable) content;
            HyperChronometerEffectSpan[] hyperChronometerEffectSpanArr = (HyperChronometerEffectSpan[]) spannable.getSpans(0, spannable.length(), HyperChronometerEffectSpan.class);
            if (hyperChronometerEffectSpanArr.length > 0) {
                for (HyperChronometerEffectSpan hyperChronometerEffectSpan : hyperChronometerEffectSpanArr) {
                    hyperChronometerEffectSpan.clear();
                    if (getVisibility() != 0 || !isAttachedToWindow()) {
                        hyperChronometerEffectSpan.setOldTextAppearance(string.subSequence(spannable.getSpanStart(hyperChronometerEffectSpan), spannable.getSpanEnd(hyperChronometerEffectSpan)), null);
                    }
                }
            }
        }
        super.setText(content, TextView.BufferType.SPANNABLE);
        if (!this.mEnableEffect || charSequence == null || charSequence.toString().contentEquals(text)) {
            return;
        }
        requestAnimation();
        postDelayed(this.mStopTextChangeAnimation, 600L);
    }

    public HyperChronometer setTextChangeProcessor(@NonNull CharChangeProcessor charChangeProcessor) {
        CharChangeProcessor charChangeProcessor2 = this.mTextChangeProcessor;
        if (charChangeProcessor2 != null && charChangeProcessor2 != charChangeProcessor) {
            this.mStopAnimation.run();
        }
        this.mTextChangeProcessor = charChangeProcessor;
        return this;
    }

    public void updateTextWithNewAppearance(CharSequence charSequence, @Nullable @ColorInt Integer num) {
        CharSequence text = getText();
        if (this.mTextChangeProcessor != null) {
            removeCallbacks(this.mStopTextChangeAnimation);
            this.mStopTextChangeAnimation.run();
            CharSequence content = this.mTextChangeProcessor.formatContent(this, charSequence);
            if (content instanceof Spannable) {
                String string = content.toString();
                Spannable spannable = (Spannable) content;
                HyperChronometerEffectSpan[] hyperChronometerEffectSpanArr = (HyperChronometerEffectSpan[]) spannable.getSpans(0, spannable.length(), HyperChronometerEffectSpan.class);
                if (hyperChronometerEffectSpanArr.length > 0) {
                    for (int i2 = 0; i2 < hyperChronometerEffectSpanArr.length; i2++) {
                        HyperChronometerEffectSpan hyperChronometerEffectSpan = hyperChronometerEffectSpanArr[i2];
                        hyperChronometerEffectSpan.clear();
                        if (getVisibility() == 0 && isAttachedToWindow()) {
                            hyperChronometerEffectSpanArr[i2].setNewTextAppearance(num);
                        } else {
                            hyperChronometerEffectSpanArr[i2].setOldTextAppearance(string.substring(spannable.getSpanStart(hyperChronometerEffectSpan), spannable.getSpanEnd(hyperChronometerEffectSpan)), num);
                        }
                    }
                }
            }
            super.setText(content, TextView.BufferType.SPANNABLE);
            if (!this.mEnableEffect || charSequence == null || charSequence.toString().contentEquals(text)) {
                invalidate();
            } else {
                requestAnimation();
                postDelayed(this.mStopTextChangeAnimation, 600L);
            }
        }
    }

    public HyperChronometer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HyperChronometer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mInterval = 1000;
        this.mRequestAnimation = false;
        this.mEnableEffect = true;
        this.mEnableEffectWithInit = true;
        this.mStopAnimation = new Runnable() { // from class: miuix.colorful.texteffect.HyperChronometer.1
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
                HyperChronometer hyperChronometer = HyperChronometer.this;
                hyperChronometer.removeCallbacks(hyperChronometer);
                HyperChronometer.this.cancelAnim();
            }
        };
        this.mStopTextChangeAnimation = new Runnable() { // from class: miuix.colorful.texteffect.HyperChronometer.2
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
                HyperChronometer hyperChronometer = HyperChronometer.this;
                hyperChronometer.removeCallbacks(hyperChronometer);
            }
        };
        setTextDirection(3);
        int deviceLevel = DeviceUtils.getDeviceLevel();
        if (TextEffectUtils.isGoogleSystem()) {
            this.mEffectLevel = 3;
            return;
        }
        if (deviceLevel == 2) {
            this.mEffectLevel = 3;
        } else if (deviceLevel == 0) {
            this.mEffectLevel = 0;
        } else {
            this.mEffectLevel = 2;
        }
    }
}
