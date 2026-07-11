package com.mi.widget.core;

import H0.k;
import H0.s;
import N0.l;
import android.util.Log;
import android.view.Choreographer;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.tracing.Trace;
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
public class ShaderAnimator {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "ShaderAnimator";
    private boolean mRunning;
    private static final a Companion = new a(null);
    public static final int $stable = 8;
    private final H0.d mChoreographer$delegate = H0.e.b(c.f2346a);

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    private final ShaderDriver mShaderDriver = new ShaderDriver();

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    private final E mUICoroutine = F.a(Q.c().z());
    private final d mFrameCallback = new d();
    private FrameRate frameRate = FrameRate.AtMost30;

    @RestrictTo({RestrictTo.Scope.TESTS})
    private Function0 frameUpdate = b.f2345a;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public static final class b extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2345a = new b();

        public b() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m75invoke() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m75invoke();
            return s.f314a;
        }
    }

    public static final class c extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2346a = new c();

        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Choreographer invoke() {
            return Choreographer.getInstance();
        }
    }

    public static final class d implements Choreographer.FrameCallback {
        public d() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j2) {
            if (ShaderAnimator.this.mRunning) {
                ShaderAnimator shaderAnimator = ShaderAnimator.this;
                Trace.beginSection("ShaderAnimator-Update");
                try {
                    shaderAnimator.getFrameUpdate$hyper_widget_1_0_7_pluginRelease().invoke();
                    shaderAnimator.getMShaderDriver().driveShader();
                    s sVar = s.f314a;
                    Trace.endSection();
                    ShaderAnimator.this.getMChoreographer().postFrameCallbackDelayed(this, ShaderAnimator.this.getFrameRate().getDelay$hyper_widget_1_0_7_pluginRelease());
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            }
        }
    }

    public static final class e extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2348a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function0 f2349b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.f2349b = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new e(this.f2349b, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((e) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.f2348a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            this.f2349b.invoke();
            return s.f314a;
        }
    }

    public static final class f extends o implements Function0 {
        public f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m76invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m76invoke() {
            ShaderAnimator.this.getMChoreographer().postFrameCallback(ShaderAnimator.this.mFrameCallback);
        }
    }

    public static final class g extends o implements Function0 {
        public g() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m77invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m77invoke() {
            ShaderAnimator.this.getMChoreographer().postFrameCallback(ShaderAnimator.this.mFrameCallback);
        }
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getFrameUpdate$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @AnyThread
    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addShader(IShaderDriven shaderDriven) {
        n.g(shaderDriven, "shaderDriven");
        if (this.mShaderDriver.addShaderDriven(shaderDriven)) {
            Log.d(LOG_TAG, "addShader: " + shaderDriven);
            if (shaderDriven instanceof AbsShader) {
                AbsShader absShader = (AbsShader) shaderDriven;
                absShader.getShaderDriver().reset();
                absShader.getShaderDriver().removeShaderDriven(shaderDriven);
            }
        }
    }

    @AnyThread
    public final FrameRate getFrameRate() {
        return this.frameRate;
    }

    @UiThread
    public final Function0 getFrameUpdate$hyper_widget_1_0_7_pluginRelease() {
        return this.frameUpdate;
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public final Choreographer getMChoreographer() {
        Object value = this.mChoreographer$delegate.getValue();
        n.f(value, "getValue(...)");
        return (Choreographer) value;
    }

    public final ShaderDriver getMShaderDriver() {
        return this.mShaderDriver;
    }

    public final E getMUICoroutine() {
        return this.mUICoroutine;
    }

    @AnyThread
    public final boolean isRunning() {
        return this.mRunning;
    }

    @AnyThread
    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void removeShader(IShaderDriven shaderDriven) {
        n.g(shaderDriven, "shaderDriven");
        if (this.mShaderDriver.removeShaderDriven(shaderDriven)) {
            Log.d(LOG_TAG, "removeShader: " + shaderDriven);
            if (shaderDriven instanceof AbsShader) {
                AbsShader absShader = (AbsShader) shaderDriven;
                absShader.getShaderDriver().addShaderDriven(shaderDriven);
                absShader.getShaderDriver().reset();
            }
        }
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public final InterfaceC0380l0 runOnUIThread(Function0 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this.mUICoroutine, null, null, new e(block, null), 3, null);
    }

    @UiThread
    public final void setFrameRate(FrameRate value) {
        n.g(value, "value");
        if (isRunning()) {
            throw new IllegalStateException("Can't change frameRate when running.");
        }
        this.frameRate = value;
    }

    @AnyThread
    public final void setFrameUpdate$hyper_widget_1_0_7_pluginRelease(Function0 function0) {
        n.g(function0, "<set-?>");
        this.frameUpdate = function0;
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized void start() {
        if (this.mRunning) {
            return;
        }
        Log.d(LOG_TAG, "start animator shader.");
        this.mRunning = true;
        runOnUIThread(new f());
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final synchronized void stop() {
        if (this.mRunning) {
            Log.d(LOG_TAG, "stop animator shader.");
            this.mRunning = false;
            this.mShaderDriver.reset();
            runOnUIThread(new g());
        }
    }
}
