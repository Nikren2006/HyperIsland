package miuix.colorful.texteffect;

import android.content.Context;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import miuix.animation.physics.AnimationHelper;
import miuix.colorful.texteffect.formatter.DefaultDateFormatter;
import miuix.device.DeviceUtils;

/* JADX INFO: loaded from: classes3.dex */
public class TimerTextEffectView extends AppCompatTextView implements TextSwitcher {
    public static boolean DEBUGGABLE = false;
    private static final int DEFAULT_INTERVAL = 1000;
    public static final int EFFECT_LEVEL_COMMON = 2;
    public static final int EFFECT_LEVEL_HIGHEST = 3;
    public static final int EFFECT_LEVEL_LOW = 1;
    public static final int EFFECT_LEVEL_NONE = 0;
    public static final String TAG = "TimerTextEffect";

    @Nullable
    private CountdownListener mCountdownListener;
    private long mDeadlineTime;
    private int mEffectLevel;
    private boolean mEnableEffect;
    private boolean mEnableEffectWithInit;
    private boolean mInPreMeasureMode;
    private final Runnable mInternalCountdownImpl;
    private int mInterval;
    private boolean mRequestAnimation;
    private final Runnable mStopAnimation;
    private final Runnable mStopTextChangeAnimation;

    @Nullable
    private TextChangeProcessor mTextChangeProcessor;

    public TimerTextEffectView(Context context) {
        this(context, null);
    }

    private TimerTextEffectView setTimeInterval(int i2) {
        this.mInterval = i2;
        return this;
    }

    private void updateText(CharSequence charSequence) {
        setText(charSequence, TextView.BufferType.SPANNABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long updateTextOnTick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j2 = this.mDeadlineTime - jCurrentTimeMillis;
        if (!(this.mTextChangeProcessor instanceof DateFormatterIntf)) {
            this.mTextChangeProcessor = new DefaultDateFormatter();
        }
        updateText(((DateFormatterIntf) this.mTextChangeProcessor).formatTime(this, j2 >= 0 ? j2 : 0L));
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onTick(this, jCurrentTimeMillis, this.mDeadlineTime);
        }
        return j2;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void cancelAnim() {
        if (DEBUGGABLE) {
            Log.d(TAG, "cancelAnim view=" + this + " trace:" + Log.getStackTraceString(new Throwable()));
        }
        if (getText() instanceof Spannable) {
            Spannable spannable = (Spannable) getText();
            for (TimerTextEffectSpan timerTextEffectSpan : (TimerTextEffectSpan[]) spannable.getSpans(0, spannable.length(), TimerTextEffectSpan.class)) {
                timerTextEffectSpan.clear();
            }
        }
        TextChangeProcessor textChangeProcessor = this.mTextChangeProcessor;
        if (textChangeProcessor != null) {
            textChangeProcessor.clear();
        }
    }

    public CharSequence createSafeText(@Nullable CharSequence charSequence) {
        Object parent;
        View view;
        ViewGroup.LayoutParams layoutParams;
        if (charSequence == null) {
            charSequence = "";
        }
        try {
            parent = getParent();
        } catch (Exception e2) {
            e = e2;
        }
        if ((parent instanceof ViewGroup) && (layoutParams = (view = (View) parent).getLayoutParams()) != null && layoutParams.width != -2) {
            int width = view.getWidth();
            int width2 = getWidth();
            if (width == 0) {
                return charSequence;
            }
            float fMeasureText = getPaint().measureText(charSequence.toString());
            if (DEBUGGABLE) {
                Log.d(TAG, "createSafeText parentViewWidth:" + width + " textViewWidth:" + width2);
                StringBuilder sb = new StringBuilder();
                sb.append("createSafeText textWidth:");
                sb.append(fMeasureText);
                Log.d(TAG, sb.toString());
            }
            float f2 = width;
            if (f2 >= fMeasureText || fMeasureText <= f2) {
                return charSequence;
            }
            CharSequence charSequenceEllipsize = TextUtils.ellipsize(charSequence, getPaint(), f2, getEllipsize());
            try {
                if (DEBUGGABLE) {
                    Log.d(TAG, " -> ellipsize text:" + ((Object) charSequenceEllipsize) + " originText:" + ((Object) charSequence));
                }
                return charSequenceEllipsize;
            } catch (Exception e3) {
                charSequence = charSequenceEllipsize;
                e = e3;
            }
            Log.w(TAG, "createSafeText failed: " + e);
            return charSequence;
        }
        return charSequence;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void enableEffect(boolean z2) {
        if (this.mEnableEffect != z2) {
            if (DEBUGGABLE) {
                Log.d(TAG, "enableEffect enable:" + z2 + " view=" + this);
                StringBuilder sb = new StringBuilder();
                sb.append("enableEffect trace:");
                sb.append(Log.getStackTraceString(new Throwable()));
                Log.d(TAG, sb.toString());
            }
            this.mEnableEffect = z2;
            if (getText() instanceof Spannable) {
                Spannable spannable = (Spannable) getText();
                TimerTextEffectSpan[] timerTextEffectSpanArr = (TimerTextEffectSpan[]) spannable.getSpans(0, spannable.length(), TimerTextEffectSpan.class);
                for (int i2 = 0; i2 < timerTextEffectSpanArr.length; i2++) {
                    timerTextEffectSpanArr[i2].clear();
                    timerTextEffectSpanArr[i2].setEnableAnim(this.mEnableEffect);
                }
            }
        }
    }

    public void enablePreMeasureMode(boolean z2) {
        this.mInPreMeasureMode = z2;
        if (DEBUGGABLE) {
            Log.d(TAG, "enablePreMeasureMode enable=" + z2 + " trace:" + Log.getStackTraceString(new Throwable()));
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public int getEffectLevel() {
        return this.mEffectLevel;
    }

    @Nullable
    public TextChangeProcessor getTextChangeProcessor() {
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

    public boolean isInPreMeasureMode() {
        return this.mInPreMeasureMode;
    }

    public void measureSize(int i2, int i3) {
        if (DEBUGGABLE) {
            Log.d(TAG, "measureSize widthMeasureSpec=" + i2 + " trace:" + Log.getStackTraceString(new Throwable()));
        }
        this.mInPreMeasureMode = true;
        measure(i2, i3);
        this.mInPreMeasureMode = false;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (DEBUGGABLE) {
            Log.d(TAG, "onAttachedToWindow text=" + ((Object) getText()) + " view=" + this);
        }
        removeCallbacks(this.mStopAnimation);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (DEBUGGABLE) {
            Log.d(TAG, "onDetachedFromWindow text=" + ((Object) getText()) + " view=" + this);
        }
        removeCallbacks(this.mInternalCountdownImpl);
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onStopTick();
        }
        try {
            this.mStopAnimation.run();
        } catch (Exception e2) {
            Log.w(TAG, "onDetachedFromWindow Exception: " + e2);
        }
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void requestAnimation() {
        if (DEBUGGABLE) {
            Log.d(TAG, "requestAnimation view=" + this + " trace:" + Log.getStackTraceString(new Throwable()));
        }
        if (this.mTextChangeProcessor == null) {
            return;
        }
        this.mRequestAnimation = true;
        AnimationHelper.postOnAnimation(this, this);
    }

    @Override // java.lang.Runnable
    public void run() {
        TextChangeProcessor textChangeProcessor;
        if (DEBUGGABLE) {
            Log.d(TAG, " run() mRequestAnimation=" + this.mRequestAnimation + " text=" + ((Object) getText()) + " view=" + this);
            StringBuilder sb = new StringBuilder();
            sb.append("  —> mTextChangeProcessor=");
            sb.append(this.mTextChangeProcessor);
            Log.d(TAG, sb.toString());
            if (this.mTextChangeProcessor != null) {
                Log.d(TAG, "  -> mTextChangeProcessor.isRunningAnim()=" + this.mTextChangeProcessor.isRunningAnim());
            }
        }
        invalidate();
        if (this.mRequestAnimation || (textChangeProcessor = this.mTextChangeProcessor) == null || textChangeProcessor.isRunningAnim()) {
            this.mRequestAnimation = false;
            if (DEBUGGABLE) {
                Log.d(TAG, " ->postOnAnimation()");
            }
            AnimationHelper.postOnAnimation(this, this);
            return;
        }
        if (DEBUGGABLE) {
            Log.d(TAG, " ->run() return text=" + ((Object) getText()) + " view=" + this);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("  ->return mTextChangeProcessor=");
            sb2.append(this.mTextChangeProcessor);
            Log.d(TAG, sb2.toString());
            if (this.mTextChangeProcessor != null) {
                Log.d(TAG, "  ->return mTextChangeProcessor.isRunningAnim()=" + this.mTextChangeProcessor.isRunningAnim());
            }
        }
    }

    public TimerTextEffectView setCountdownDeadlineTime(long j2) {
        this.mDeadlineTime = j2;
        return this;
    }

    public TimerTextEffectView setCountdownListener(CountdownListener countdownListener) {
        this.mCountdownListener = countdownListener;
        return this;
    }

    @Override // miuix.colorful.texteffect.TextSwitcher
    public void setEffectLevel(int i2) {
        if (this.mEffectLevel != i2) {
            this.mEffectLevel = i2;
            if (i2 == 0 && (getText() instanceof Spannable)) {
                Spannable spannable = (Spannable) getText();
                TimerTextEffectSpan[] timerTextEffectSpanArr = (TimerTextEffectSpan[]) spannable.getSpans(0, spannable.length(), TimerTextEffectSpan.class);
                for (int i3 = 0; i3 < timerTextEffectSpanArr.length; i3++) {
                    timerTextEffectSpanArr[i3].clear();
                    spannable.removeSpan(timerTextEffectSpanArr[i3]);
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
        if (DEBUGGABLE) {
            Log.d(TAG, "TimerTextEffectView setText text=" + ((Object) charSequence) + " " + this);
        }
        if (this.mTextChangeProcessor == null) {
            Runnable runnable = this.mStopTextChangeAnimation;
            if (runnable != null) {
                removeCallbacks(runnable);
                this.mStopTextChangeAnimation.run();
            }
            super.setText(charSequence, bufferType);
            return;
        }
        removeCallbacks(this.mStopTextChangeAnimation);
        this.mStopTextChangeAnimation.run();
        CharSequence charSequenceCreateSafeText = createSafeText(charSequence);
        CharSequence content = this.mTextChangeProcessor.formatContent(this, charSequenceCreateSafeText);
        if (content instanceof Spannable) {
            Spannable spannable = (Spannable) content;
            TimerTextEffectSpan[] timerTextEffectSpanArr = (TimerTextEffectSpan[]) spannable.getSpans(0, spannable.length(), TimerTextEffectSpan.class);
            if (timerTextEffectSpanArr.length > 0) {
                timerTextEffectSpanArr[0].clear();
                if (getVisibility() != 0 || !isAttachedToWindow()) {
                    timerTextEffectSpanArr[0].setOldTextAppearance(charSequenceCreateSafeText, null);
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

    public TimerTextEffectView setTextChangeProcessor(@NonNull TextChangeProcessor textChangeProcessor) {
        if (DEBUGGABLE) {
            Log.d(TAG, "setTextChangeProcessor text=" + ((Object) getText()) + " view=" + this);
            StringBuilder sb = new StringBuilder();
            sb.append("setTextChangeProcessor trace:");
            sb.append(Log.getStackTraceString(new Throwable()));
            Log.d(TAG, sb.toString());
        }
        TextChangeProcessor textChangeProcessor2 = this.mTextChangeProcessor;
        if (textChangeProcessor2 != null && textChangeProcessor2 != textChangeProcessor) {
            this.mStopAnimation.run();
        }
        this.mTextChangeProcessor = textChangeProcessor;
        if (textChangeProcessor instanceof TextChangeHelper) {
            setText(textChangeProcessor.formatContent(this, getText()), TextView.BufferType.SPANNABLE);
        }
        return this;
    }

    public void start() {
        post(this.mInternalCountdownImpl);
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onStartTick();
        }
        removeCallbacks(this.mStopAnimation);
    }

    public void startByCustomCountDown() {
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onStartTick();
        }
        removeCallbacks(this.mStopAnimation);
    }

    public void stop() {
        removeCallbacks(this.mInternalCountdownImpl);
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onStopTick();
        }
        post(this.mStopAnimation);
    }

    public void stopByCustomCountDown() {
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onStopTick();
        }
        post(this.mStopAnimation);
    }

    public void updateTextWithNewAppearance(@Nullable CharSequence charSequence, @Nullable @ColorInt Integer num) {
        if (DEBUGGABLE) {
            Log.d(TAG, "TimerTextEffectView updateTextWithNewAppearance text=" + ((Object) charSequence) + " color=" + (num != null ? Integer.toHexString(num.intValue()) : "null") + " " + this);
        }
        CharSequence text = getText();
        if (this.mTextChangeProcessor != null) {
            removeCallbacks(this.mStopTextChangeAnimation);
            this.mStopTextChangeAnimation.run();
            CharSequence charSequenceCreateSafeText = createSafeText(charSequence);
            CharSequence content = this.mTextChangeProcessor.formatContent(this, charSequenceCreateSafeText);
            if (content instanceof Spannable) {
                Spannable spannable = (Spannable) content;
                TimerTextEffectSpan[] timerTextEffectSpanArr = (TimerTextEffectSpan[]) spannable.getSpans(0, spannable.length(), TimerTextEffectSpan.class);
                if (timerTextEffectSpanArr.length > 0) {
                    timerTextEffectSpanArr[0].clear();
                    if (getVisibility() == 0 && isAttachedToWindow()) {
                        timerTextEffectSpanArr[0].setNewTextAppearance(num);
                    } else {
                        timerTextEffectSpanArr[0].setOldTextAppearance(charSequenceCreateSafeText, num);
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

    public void updateTickFromCustomCountDown(long j2, long j3) {
        long j4 = j3 - j2;
        if (!(this.mTextChangeProcessor instanceof DateFormatterIntf)) {
            this.mTextChangeProcessor = new DefaultDateFormatter();
        }
        DateFormatterIntf dateFormatterIntf = (DateFormatterIntf) this.mTextChangeProcessor;
        if (j4 < 0) {
            j4 = 0;
        }
        updateText(dateFormatterIntf.formatTime(this, j4));
        CountdownListener countdownListener = this.mCountdownListener;
        if (countdownListener != null) {
            countdownListener.onTick(this, j2, j3);
        }
        if (j4 <= 0) {
            stopByCustomCountDown();
        } else {
            requestAnimation();
        }
    }

    public TimerTextEffectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimerTextEffectView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mInterval = 1000;
        this.mRequestAnimation = false;
        this.mEffectLevel = 3;
        this.mEnableEffect = true;
        this.mEnableEffectWithInit = true;
        this.mInPreMeasureMode = false;
        this.mStopAnimation = new Runnable() { // from class: miuix.colorful.texteffect.TimerTextEffectView.1
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
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, "mStopAnimation run view=" + TimerTextEffectView.this);
                }
                TimerTextEffectView timerTextEffectView = TimerTextEffectView.this;
                timerTextEffectView.removeCallbacks(timerTextEffectView);
                TimerTextEffectView.this.cancelAnim();
            }
        };
        this.mStopTextChangeAnimation = new Runnable() { // from class: miuix.colorful.texteffect.TimerTextEffectView.2
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
                if (TimerTextEffectView.DEBUGGABLE) {
                    Log.d(TimerTextEffectView.TAG, " mStopTextChangeAnimation run view=" + TimerTextEffectView.this);
                }
                TimerTextEffectView timerTextEffectView = TimerTextEffectView.this;
                timerTextEffectView.removeCallbacks(timerTextEffectView);
            }
        };
        this.mInternalCountdownImpl = new Runnable() { // from class: miuix.colorful.texteffect.TimerTextEffectView.3
            @Override // java.lang.Runnable
            public void run() {
                if (TimerTextEffectView.this.updateTextOnTick() <= 0) {
                    TimerTextEffectView.this.stop();
                    return;
                }
                TimerTextEffectView.this.requestAnimation();
                TimerTextEffectView.this.postDelayed(this, r0.mInterval);
            }
        };
        if (TextEffectUtils.isGoogleSystem()) {
            this.mEffectLevel = 3;
            return;
        }
        int deviceLevel = DeviceUtils.getDeviceLevel();
        if (deviceLevel == 2) {
            this.mEffectLevel = 3;
        } else if (deviceLevel == 0) {
            this.mEffectLevel = 0;
        } else {
            this.mEffectLevel = 2;
        }
    }
}
