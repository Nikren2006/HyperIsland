package com.mi.widget.core;

import H0.k;
import H0.s;
import N0.l;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.EmptySuper;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.tracing.Trace;
import com.mi.widget.core.AbsDrawable;
import f0.EnumC0351e;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import g1.Q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class AbsDrawable<T extends View> extends Drawable {
    private static final boolean DEBUG = true;
    private FrameRate frameRate;
    private final d mAttachStateChangeListener;
    private long mCycleTime;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private boolean mEnableFrameLoop;
    private final Runnable mNextFrame;
    private boolean mRunning;
    private long mStartTime;
    private final String mTraceNameDraw;
    private final String mTraceNameUpdate;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    private final E mUICoroutine;
    private final boolean refreshByActive;
    private final T view;
    private static final b Companion = new b(null);
    public static final int $stable = 8;

    public static final class a extends o implements Function0 {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m63invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m63invoke() {
            AbsDrawable.this.onActive();
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public b() {
        }
    }

    public /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2316a;

        static {
            int[] iArr = new int[EnumC0351e.values().length];
            try {
                iArr[EnumC0351e.f4232c.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EnumC0351e.f4230a.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EnumC0351e.f4231b.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f2316a = iArr;
        }
    }

    public static final class d implements View.OnAttachStateChangeListener {

        public static final class a extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbsDrawable f2318a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AbsDrawable absDrawable) {
                super(0);
                this.f2318a = absDrawable;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m64invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m64invoke() {
                this.f2318a.onActive();
            }
        }

        public static final class b extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbsDrawable f2319a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AbsDrawable absDrawable) {
                super(0);
                this.f2319a = absDrawable;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m65invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m65invoke() {
                this.f2319a.onInactive();
            }
        }

        public d() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v2) {
            n.g(v2, "v");
            Log.i(AbsDrawable.this.getDebugName(), "onActiveWithViewAttachedToWindow d=" + this + " v=" + AbsDrawable.this.getView());
            AbsDrawable absDrawable = AbsDrawable.this;
            absDrawable.runOnUIThread(new a(absDrawable));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v2) {
            n.g(v2, "v");
            Log.i(AbsDrawable.this.getDebugName(), "onInActiveWithViewAttachedToWindow d=" + this + " v=" + AbsDrawable.this.getView());
            AbsDrawable absDrawable = AbsDrawable.this;
            absDrawable.runOnUIThread(new b(absDrawable));
        }
    }

    public static final class e extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2320a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function0 f2321b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.f2321b = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new e(this.f2321b, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((e) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.f2320a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            this.f2321b.invoke();
            return s.f314a;
        }
    }

    public static final class f extends o implements Function0 {
        public f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m66invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m66invoke() {
            AbsDrawable.this.getView().setBackground(AbsDrawable.this);
            AbsDrawable.this.invalidateSelf();
            AbsDrawable.this.onStart();
        }
    }

    public static final class g extends o implements Function0 {
        public g() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m67invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m67invoke() {
            AbsDrawable.this.onStop();
            AbsDrawable.this.getView().setBackground(null);
        }
    }

    public AbsDrawable(T view) {
        n.g(view, "view");
        this.view = view;
        this.mNextFrame = new Runnable() { // from class: f0.a
            @Override // java.lang.Runnable
            public final void run() {
                AbsDrawable.mNextFrame$lambda$0(this.f4226a);
            }
        };
        this.mEnableFrameLoop = true;
        this.mUICoroutine = F.a(Q.c().z());
        this.refreshByActive = true;
        d dVar = new d();
        this.mAttachStateChangeListener = dVar;
        view.addOnAttachStateChangeListener(dVar);
        if (view.isAttachedToWindow()) {
            runOnUIThread(new a());
        }
        this.frameRate = FrameRate.AtMost30;
        this.mTraceNameDraw = getDebugName() + ".draw";
        this.mTraceNameUpdate = getDebugName() + ".updateDraw";
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mNextFrame$lambda$0(AbsDrawable this$0) {
        n.g(this$0, "this$0");
        this$0.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        n.g(canvas, "canvas");
        if (this.mRunning) {
            Trace.beginSection(this.mTraceNameDraw);
            try {
                if (getRefreshByActive$hyper_widget_1_0_7_pluginRelease()) {
                    if (this.mStartTime <= 0) {
                        this.mStartTime = SystemClock.elapsedRealtime();
                    }
                    long jUpdateCycleTime = updateCycleTime(SystemClock.elapsedRealtime(), this.mStartTime);
                    if (jUpdateCycleTime < this.mCycleTime) {
                        onStartCycle(canvas);
                    }
                    this.mCycleTime = jUpdateCycleTime;
                    Trace.beginSection(this.mTraceNameUpdate);
                    try {
                        onUpdateDraw(canvas, SystemClock.elapsedRealtime(), this.mCycleTime);
                        s sVar = s.f314a;
                        Trace.endSection();
                        if (this.mEnableFrameLoop) {
                            this.view.removeCallbacks(this.mNextFrame);
                            this.view.postOnAnimationDelayed(this.mNextFrame, this.frameRate.getDelay$hyper_widget_1_0_7_pluginRelease());
                        }
                    } finally {
                    }
                } else {
                    Trace.beginSection(this.mTraceNameUpdate);
                    try {
                        onUpdateDraw(canvas, SystemClock.elapsedRealtime(), 0L);
                        s sVar2 = s.f314a;
                        Trace.endSection();
                    } finally {
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract long getAnimCycleTime();

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public abstract String getDebugName();

    @AnyThread
    public final FrameRate getFrameRate() {
        return this.frameRate;
    }

    public final boolean getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease() {
        return this.mEnableFrameLoop;
    }

    public final E getMUICoroutine() {
        return this.mUICoroutine;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting(otherwise = 4)
    public boolean getRefreshByActive$hyper_widget_1_0_7_pluginRelease() {
        return this.refreshByActive;
    }

    public final T getView() {
        return this.view;
    }

    @AnyThread
    public final boolean isRunning() {
        return this.mRunning;
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onActive() {
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onInactive() {
        this.view.removeCallbacks(this.mNextFrame);
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void onStart() {
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onStartCycle(Canvas canvas) {
        n.g(canvas, "canvas");
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void onStop() {
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public abstract void onUpdateDraw(Canvas canvas, long j2, long j3);

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InterfaceC0380l0 runOnUIThread(Function0 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this.mUICoroutine, null, null, new e(block, null), 3, null);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(@IntRange(from = 0, to = ScatterMapKt.Sentinel) int i2) {
        throw new UnsupportedOperationException("Not supported In " + getDebugName());
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException("Not supported In " + getDebugName());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setFrameLoopStrategy$hyper_widget_1_0_7_pluginRelease(EnumC0351e strategy) {
        n.g(strategy, "strategy");
        if (!getRefreshByActive$hyper_widget_1_0_7_pluginRelease()) {
            Log.w(getDebugName(), "setFrameLoopStrategy must be called when refreshByActive is enabled");
            return;
        }
        if (!isRunning()) {
            Log.w(getDebugName(), "setFrameLoopStrategy must be called when Drawable is not running, strategy=" + strategy);
            return;
        }
        Log.i(getDebugName(), "setFrameLoopStrategy to " + strategy);
        int i2 = c.f2316a[strategy.ordinal()];
        if (i2 == 1) {
            this.mEnableFrameLoop = true;
            invalidateSelf();
        } else if (i2 == 2) {
            this.mEnableFrameLoop = false;
        } else if (i2 == 3) {
            throw new IllegalArgumentException("Can't support  FrameLoopStrategy:DISABLE for drawable shader.");
        }
    }

    @UiThread
    public final void setFrameRate(FrameRate value) {
        n.g(value, "value");
        if (this.mRunning) {
            throw new IllegalStateException("Can't change frameRate when ShaderDrawable is running.");
        }
        this.frameRate = value;
    }

    public final void setMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease(boolean z2) {
        this.mEnableFrameLoop = z2;
    }

    @AnyThread
    public final synchronized void start() {
        try {
            if (this.mRunning) {
                return;
            }
            if (getRefreshByActive$hyper_widget_1_0_7_pluginRelease() && getAnimCycleTime() <= 0) {
                throw new IllegalStateException(("animCycleTime must be greater than 0 with refreshByActive = true, current is " + getAnimCycleTime()).toString());
            }
            Log.d(getDebugName(), "start drawable shader refreshByActive=" + getRefreshByActive$hyper_widget_1_0_7_pluginRelease() + ", animCycleTime=" + getAnimCycleTime());
            this.mRunning = true;
            this.mStartTime = 0L;
            this.mCycleTime = getAnimCycleTime();
            runOnUIThread(new f());
        } catch (Throwable th) {
            throw th;
        }
    }

    @AnyThread
    public final synchronized void stop() {
        if (this.mRunning) {
            Log.d(getDebugName(), "stop drawable shader");
            this.mRunning = false;
            this.mEnableFrameLoop = true;
            runOnUIThread(new g());
        }
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public long updateCycleTime(long j2, long j3) {
        return (j2 - j3) % getAnimCycleTime();
    }
}
