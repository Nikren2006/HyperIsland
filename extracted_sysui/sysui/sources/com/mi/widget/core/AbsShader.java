package com.mi.widget.core;

import H0.k;
import H0.s;
import N0.l;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.util.Log;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.EmptySuper;
import androidx.annotation.Keep;
import androidx.annotation.RawRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.tracing.Trace;
import com.mi.widget.core.AbsShader;
import com.mi.widget.core.IShaderDriven;
import f0.EnumC0351e;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import g1.Q;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
public abstract class AbsShader<T extends View> implements IShaderDriven {
    private static final boolean DEBUG = true;
    private FrameRate frameRate;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private Function0 frameUpdate;
    private final d mAttachedStateChangeListener;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private boolean mEnableFrameLoop;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private final Runnable mFrameCallback;
    private final View.OnLayoutChangeListener mLayoutChangeListener;
    private boolean mRunning;
    private final H0.d mShader$delegate;
    private final String mTraceNameDoFrame;
    private final String mTraceNameInvalidate;
    private final String mTraceNameUpdateShader;
    private final String mTraceNameUpdateShaderDriver;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    private final E mUICoroutine;
    private ShaderDriver shaderDriver;
    private final T view;
    private static final a Companion = new a(null);
    public static final int $stable = 8;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    public /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2324a;

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
            f2324a = iArr;
        }
    }

    public static final class c extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2325a = new c();

        public c() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m68invoke() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m68invoke();
            return s.f314a;
        }
    }

    public static final class d implements View.OnAttachStateChangeListener {

        public static final class a extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbsShader f2327a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ View f2328b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AbsShader absShader, View view) {
                super(0);
                this.f2327a = absShader;
                this.f2328b = view;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m69invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m69invoke() {
                this.f2327a.onViewAttachedToWindow(this.f2328b);
            }
        }

        public static final class b extends o implements Function0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbsShader f2329a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ View f2330b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(AbsShader absShader, View view) {
                super(0);
                this.f2329a = absShader;
                this.f2330b = view;
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m70invoke();
                return s.f314a;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m70invoke() {
                this.f2329a.onViewDetachedFromWindow(this.f2330b);
            }
        }

        public d() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v2) {
            n.g(v2, "v");
            if (AbsShader.this.mRunning && AbsShader.this.getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease()) {
                AbsShader.this.requestNextFrame$hyper_widget_1_0_7_pluginRelease();
            }
            Log.d(AbsShader.this.getDebugName(), "Add frameCallback with view attached isRunning=" + AbsShader.this.mRunning + " enableFrameLoop=" + AbsShader.this.getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease() + ".");
            AbsShader absShader = AbsShader.this;
            absShader.runOnUIThread(new a(absShader, v2));
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v2) {
            n.g(v2, "v");
            AbsShader.this.cancelNextFrame();
            Log.i(AbsShader.this.getDebugName(), "Remove frameCallback with view detached. enableFrameLoop=" + AbsShader.this.getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease() + ".");
            AbsShader absShader = AbsShader.this;
            absShader.runOnUIThread(new b(absShader, v2));
        }
    }

    public static final class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = AbsShader.this.mTraceNameDoFrame;
            AbsShader absShader = AbsShader.this;
            Trace.beginSection(str);
            try {
                absShader.getFrameUpdate$hyper_widget_1_0_7_pluginRelease().invoke();
                if (absShader.mRunning) {
                    if (absShader.isLayerAvailable()) {
                        if (absShader.getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease()) {
                            absShader.getView().postOnAnimationDelayed(this, absShader.getFrameRate().getDelay$hyper_widget_1_0_7_pluginRelease());
                        }
                        Trace.beginSection(absShader.mTraceNameUpdateShaderDriver);
                        try {
                            absShader.getShaderDriver().driveShader();
                            s sVar = s.f314a;
                            Trace.endSection();
                            Trace.beginSection(absShader.mTraceNameUpdateShader);
                            try {
                                absShader.updateShader();
                                Trace.endSection();
                            } finally {
                            }
                        } finally {
                        }
                    } else {
                        Log.e(absShader.getDebugName(), "Skip shader frame-loop due-to unavailable layer");
                    }
                }
                s sVar2 = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final class f extends o implements Function0 {
        public f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final RuntimeShader invoke() {
            AbsShader absShader = AbsShader.this;
            return new RuntimeShader(absShader.readRawString(absShader.getShaderResId()));
        }
    }

    public static final class g extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2333a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function0 f2334b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.f2334b = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new g(this.f2334b, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((g) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.f2333a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            this.f2334b.invoke();
            return s.f314a;
        }
    }

    public static final class h extends o implements Function0 {
        public h() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m71invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m71invoke() {
            AbsShader.startInternal$default(AbsShader.this, null, 1, null);
        }
    }

    public static final class i extends o implements Function0 {
        public i() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m72invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m72invoke() {
            AbsShader.stopInternal$default(AbsShader.this, null, 1, null);
        }
    }

    public AbsShader(T view) {
        n.g(view, "view");
        this.view = view;
        this.mUICoroutine = F.a(Q.c().z());
        this.mEnableFrameLoop = true;
        this.mTraceNameDoFrame = getDebugName() + ".doFrame " + view.hashCode();
        this.mTraceNameUpdateShaderDriver = getDebugName() + ".updateShaderDriver";
        this.mTraceNameUpdateShader = getDebugName() + ".updateShader";
        this.mFrameCallback = new e();
        d dVar = new d();
        this.mAttachedStateChangeListener = dVar;
        this.mLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: f0.b
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                AbsShader.mLayoutChangeListener$lambda$0(this.f4227a, view2, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        };
        this.mShader$delegate = H0.e.b(new f());
        ShaderDriver shaderDriver = new ShaderDriver();
        shaderDriver.addShaderDriven(this);
        this.shaderDriver = shaderDriver;
        this.frameRate = FrameRate.AtMost30;
        view.addOnAttachStateChangeListener(dVar);
        this.mTraceNameInvalidate = getDebugName() + ".invalidate";
        this.frameUpdate = c.f2325a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelNextFrame() {
        this.view.removeCallbacks(this.mFrameCallback);
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getFrameUpdate$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMFrameCallback$annotations() {
    }

    @VisibleForTesting(otherwise = 4)
    public static /* synthetic */ void getMShader$hyper_widget_1_0_7_pluginRelease$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLayerAvailable() {
        return this.view.isHardwareAccelerated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mLayoutChangeListener$lambda$0(AbsShader this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.updateSize(i4 - i2, i5 - i3);
    }

    private final void startInternal(String str) {
        Trace.beginSection(getDebugName() + " start : " + str);
        try {
            Log.d(getDebugName(), "startInternal reason=" + str);
            this.shaderDriver.init();
            this.view.addOnLayoutChangeListener(this.mLayoutChangeListener);
            requestNextFrame$hyper_widget_1_0_7_pluginRelease();
            updateSize(this.view.getWidth(), this.view.getHeight());
            onStart();
            s sVar = s.f314a;
        } finally {
            Trace.endSection();
        }
    }

    public static /* synthetic */ void startInternal$default(AbsShader absShader, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: startInternal");
        }
        if ((i2 & 1) != 0) {
            str = "default";
        }
        absShader.startInternal(str);
    }

    private final void stopInternal(String str) {
        Trace.beginSection(getDebugName() + " stop : " + str);
        try {
            Log.d(getDebugName(), "stopInternal reason=" + str);
            onStop();
            this.view.removeOnLayoutChangeListener(this.mLayoutChangeListener);
            cancelNextFrame();
            this.view.setRenderEffect(null);
            this.shaderDriver.reset();
            s sVar = s.f314a;
        } finally {
            Trace.endSection();
        }
    }

    public static /* synthetic */ void stopInternal$default(AbsShader absShader, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stopInternal");
        }
        if ((i2 & 1) != 0) {
            str = "default";
        }
        absShader.stopInternal(str);
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public abstract String getDebugName();

    @AnyThread
    public final FrameRate getFrameRate() {
        return this.frameRate;
    }

    @UiThread
    public final Function0 getFrameUpdate$hyper_widget_1_0_7_pluginRelease() {
        return this.frameUpdate;
    }

    public final boolean getMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease() {
        return this.mEnableFrameLoop;
    }

    public final Runnable getMFrameCallback() {
        return this.mFrameCallback;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final RuntimeShader getMShader$hyper_widget_1_0_7_pluginRelease() {
        return (RuntimeShader) this.mShader$delegate.getValue();
    }

    public final E getMUICoroutine() {
        return this.mUICoroutine;
    }

    @UiThread
    public final ShaderDriver getShaderDriver() {
        return this.shaderDriver;
    }

    @RawRes
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract int getShaderResId();

    public final T getView() {
        return this.view;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void invalidate$hyper_widget_1_0_7_pluginRelease() {
        if (isRunning() && isLayerAvailable()) {
            Trace.beginSection(this.mTraceNameInvalidate);
            try {
                this.shaderDriver.driveShader();
                updateShader();
                s sVar = s.f314a;
            } finally {
                Trace.endSection();
            }
        }
    }

    @AnyThread
    public final boolean isRunning() {
        return this.mRunning;
    }

    @Override // com.mi.widget.core.IShaderDriven
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onInitFrameParameters() {
        IShaderDriven.a.a(this);
    }

    @EmptySuper
    @AnyThread
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public boolean onInterceptStart() {
        return false;
    }

    @EmptySuper
    @AnyThread
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public boolean onInterceptStop() {
        return false;
    }

    @Override // com.mi.widget.core.IShaderDriven
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onResetFrameParameters() {
        IShaderDriven.a.b(this);
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void onStart() {
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void onStop() {
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public void onViewAttachedToWindow(View v2) {
        n.g(v2, "v");
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public void onViewDetachedFromWindow(View v2) {
        n.g(v2, "v");
    }

    @AnyThread
    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final String readRawString(@RawRes int i2) throws IOException {
        InputStream inputStreamOpenRawResource = this.view.getResources().openRawResource(i2);
        byte[] bArr = new byte[inputStreamOpenRawResource.available()];
        inputStreamOpenRawResource.read(bArr);
        inputStreamOpenRawResource.close();
        return new String(bArr, f1.c.f4238b);
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @VisibleForTesting(otherwise = 2)
    public final void requestNextFrame$hyper_widget_1_0_7_pluginRelease() {
        this.view.removeCallbacks(this.mFrameCallback);
        this.view.postOnAnimation(this.mFrameCallback);
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InterfaceC0380l0 runOnUIThread(Function0 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this.mUICoroutine, null, null, new g(block, null), 3, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public final void setFrameLoopStrategy$hyper_widget_1_0_7_pluginRelease(EnumC0351e strategy) {
        n.g(strategy, "strategy");
        if (!isRunning()) {
            Log.w(getDebugName(), "setFrameLoopStrategy must be called when Shader is running, strategy=" + strategy);
            return;
        }
        Log.i(getDebugName(), "setFrameLoopStrategy to " + strategy);
        int i2 = b.f2324a[strategy.ordinal()];
        if (i2 == 1) {
            this.mEnableFrameLoop = true;
            requestNextFrame$hyper_widget_1_0_7_pluginRelease();
        } else if (i2 == 2) {
            this.mEnableFrameLoop = false;
            requestNextFrame$hyper_widget_1_0_7_pluginRelease();
        } else {
            if (i2 != 3) {
                return;
            }
            this.mEnableFrameLoop = false;
            cancelNextFrame();
        }
    }

    @UiThread
    public final void setFrameRate(FrameRate value) {
        n.g(value, "value");
        if (isRunning()) {
            throw new IllegalStateException("Can't change frameRate when Shader is running.");
        }
        this.frameRate = value;
    }

    @AnyThread
    public final void setFrameUpdate$hyper_widget_1_0_7_pluginRelease(Function0 function0) {
        n.g(function0, "<set-?>");
        this.frameUpdate = function0;
    }

    public final void setMEnableFrameLoop$hyper_widget_1_0_7_pluginRelease(boolean z2) {
        this.mEnableFrameLoop = z2;
    }

    @VisibleForTesting(otherwise = 2)
    public final void setShaderDriver$hyper_widget_1_0_7_pluginRelease(ShaderDriver shaderDriver) {
        n.g(shaderDriver, "<set-?>");
        this.shaderDriver = shaderDriver;
    }

    @AnyThread
    public final synchronized void start() {
        if (this.mRunning) {
            return;
        }
        if (onInterceptStart()) {
            Log.i(getDebugName(), "start is ignored by onInterceptStart");
            return;
        }
        Log.d(getDebugName(), "start shader layerAvailable=" + isLayerAvailable());
        this.mRunning = true;
        runOnUIThread(new h());
    }

    @AnyThread
    public final synchronized void stop() {
        if (this.mRunning) {
            if (onInterceptStop()) {
                Log.i(getDebugName(), "stop is ignored by onInterceptStop");
                return;
            }
            Log.d(getDebugName(), "stop shader layerAvailable=" + isLayerAvailable());
            this.mEnableFrameLoop = true;
            this.mRunning = false;
            runOnUIThread(new i());
        }
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void updateShader() {
        this.view.setRenderEffect(RenderEffect.createRuntimeShaderEffect(getMShader$hyper_widget_1_0_7_pluginRelease(), "uTex"));
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void updateSize(int i2, int i3) {
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uResolution", new float[]{i2, i3});
        requestNextFrame$hyper_widget_1_0_7_pluginRelease();
    }
}
