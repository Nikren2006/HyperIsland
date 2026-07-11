package com.mi.widget.view;

import H0.s;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import androidx.annotation.AttrRes;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.tracing.Trace;
import com.airbnb.lottie.LottieAnimationView;
import com.mi.widget.core.FrameRate;
import com.mi.widget.core.ShaderStrategy;
import d.F;
import f0.AbstractC0354h;
import java.lang.reflect.Field;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import p.ChoreographerFrameCallbackC0725e;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@SuppressLint({"RestrictedApi"})
public final class LottieView extends LottieAnimationView {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f2410p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final H0.d f2411q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public FrameRate f2412r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f2413s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Runnable f2414x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final a f2409y = new a(null);
    public static final int $stable = 8;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static final class b extends o implements Function0 {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final ChoreographerFrameCallbackC0725e invoke() throws IllegalAccessException {
            try {
                Field declaredField = LottieAnimationView.class.getDeclaredField("lottieDrawable");
                LottieView lottieView = LottieView.this;
                declaredField.setAccessible(true);
                Field declaredField2 = F.class.getDeclaredField("animator");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(declaredField.get(lottieView));
                if (obj instanceof ChoreographerFrameCallbackC0725e) {
                    return (ChoreographerFrameCallbackC0725e) obj;
                }
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e("LottieView", "Reflect [LottieAnimationView] animator fail: " + e2.getMessage());
                return null;
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LottieView(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    private final ChoreographerFrameCallbackC0725e getAnimator() {
        return (ChoreographerFrameCallbackC0725e) this.f2411q.getValue();
    }

    public static final void r(LottieView this$0) {
        n.g(this$0, "this$0");
        Trace.beginSection("invalidateDelayLottie");
        try {
            this$0.f2410p = SystemClock.elapsedRealtime();
            this$0.invalidate();
            s sVar = s.f314a;
        } finally {
            Trace.endSection();
        }
    }

    public final boolean getAdaptiveFrameRate() {
        return this.f2413s;
    }

    public final FrameRate getFrameRate() {
        return this.f2412r;
    }

    @Override // com.airbnb.lottie.LottieAnimationView, android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable dr) {
        n.g(dr, "dr");
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.f2410p;
        if (!isAnimating() || !(dr instanceof F)) {
            super.invalidateDrawable(dr);
            return;
        }
        if (jElapsedRealtime >= this.f2412r.getDelay$hyper_widget_1_0_7_pluginRelease()) {
            Trace.beginSection("invalidateLottie");
            try {
                this.f2410p = SystemClock.elapsedRealtime();
                super.invalidateDrawable(dr);
                s sVar = s.f314a;
            } finally {
                Trace.endSection();
            }
        }
        if (jElapsedRealtime >= this.f2412r.getDelay$hyper_widget_1_0_7_pluginRelease()) {
            jElapsedRealtime = 0;
        }
        q(jElapsedRealtime);
        s(jElapsedRealtime);
    }

    public final void q(long j2) {
        if (getAnimator() == null) {
            Trace.beginSection("triggerNextDelayLottie");
            try {
                removeCallbacks(this.f2414x);
                postOnAnimationDelayed(this.f2414x, this.f2412r.getDelay$hyper_widget_1_0_7_pluginRelease() - j2);
                s sVar = s.f314a;
            } finally {
                Trace.endSection();
            }
        }
    }

    public final void s(long j2) {
        ChoreographerFrameCallbackC0725e animator = getAnimator();
        if (animator != null) {
            Trace.beginSection("triggerNextLottie");
            try {
                invalidate();
                Choreographer.getInstance().removeFrameCallback(animator);
                Choreographer.getInstance().postFrameCallbackDelayed(animator, this.f2412r.getDelay$hyper_widget_1_0_7_pluginRelease() - j2);
                s sVar = s.f314a;
            } finally {
                Trace.endSection();
            }
        }
    }

    public final void setAdaptiveFrameRate(boolean z2) {
        if (this.f2413s == z2) {
            return;
        }
        if (isAnimating()) {
            throw new IllegalStateException("Can't enable adaptive frame-rate when animating");
        }
        if (z2) {
            setFrameRate(AbstractC0354h.a() == ShaderStrategy.AGSL ? FrameRate.AtMost120 : FrameRate.AtMost30);
        }
        this.f2413s = z2;
    }

    public final void setFrameRate(FrameRate value) {
        n.g(value, "value");
        if (isAnimating()) {
            throw new IllegalStateException("Can't change frame-rate when animating.");
        }
        if (this.f2413s) {
            Log.w("LottieView", "Can't change frame-rate when enable adaptiveFrameRate.");
        } else {
            this.f2412r = value;
        }
    }

    public final void t() {
        if (getAnimator() == null) {
            Trace.beginSection("removeNextDelayLottie");
            try {
                removeCallbacks(this.f2414x);
            } finally {
                Trace.endSection();
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LottieView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ LottieView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LottieView(Context context, AttributeSet attributeSet, @AttrRes int i2) throws Exception {
        super(context, attributeSet, i2);
        n.g(context, "context");
        this.f2411q = H0.e.b(new b());
        FrameRate frameRate = FrameRate.AtMost30;
        this.f2412r = frameRate;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e0.e.f4042c, i2, 0);
        try {
            boolean z2 = typedArrayObtainStyledAttributes.getBoolean(e0.e.f4043d, false);
            int i3 = typedArrayObtainStyledAttributes.getInt(e0.e.f4044e, -1);
            if (this.f2413s && i3 >= 0) {
                Log.e("LottieView", "Set both [adaptiveFrameRate] and [frameRate] at the same time, Prefer to use [frameRate].");
                setAdaptiveFrameRate(false);
            }
            if (i3 != 0) {
                if (i3 == 1) {
                    frameRate = FrameRate.AtMost60;
                } else if (i3 != 2) {
                    frameRate = this.f2412r;
                } else {
                    frameRate = FrameRate.AtMost120;
                }
            }
            setFrameRate(frameRate);
            setAdaptiveFrameRate(z2);
            s sVar = s.f314a;
            T0.a.a(typedArrayObtainStyledAttributes, null);
            if (getAnimator() == null) {
                Log.i("LottieView", "Reflect [LottieAnimationView] animator fail, use built-in frame-loop");
            }
            addAnimatorListener(new Animator.AnimatorListener() { // from class: com.mi.widget.view.LottieView.2
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animation) {
                    n.g(animation, "animation");
                    LottieView.this.t();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    n.g(animation, "animation");
                    LottieView.this.t();
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animation) {
                    n.g(animation, "animation");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animation) {
                    n.g(animation, "animation");
                }
            });
            addAnimatorPauseListener(new Animator.AnimatorPauseListener() { // from class: com.mi.widget.view.LottieView.3
                @Override // android.animation.Animator.AnimatorPauseListener
                public void onAnimationPause(Animator animation) {
                    n.g(animation, "animation");
                    LottieView.this.t();
                }

                @Override // android.animation.Animator.AnimatorPauseListener
                public void onAnimationResume(Animator animation) {
                    n.g(animation, "animation");
                    LottieView.this.q(0L);
                }
            });
            this.f2414x = new Runnable() { // from class: com.mi.widget.view.c
                @Override // java.lang.Runnable
                public final void run() {
                    LottieView.r(this.f2452a);
                }
            };
        } finally {
        }
    }
}
