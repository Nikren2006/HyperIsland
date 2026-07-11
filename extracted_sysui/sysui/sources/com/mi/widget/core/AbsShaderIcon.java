package com.mi.widget.core;

import H0.e;
import H0.k;
import H0.s;
import N0.l;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.Keep;
import androidx.annotation.RawRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.tracing.Trace;
import com.mi.widget.core.AbsShaderIcon;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import g1.Q;
import java.io.IOException;
import java.io.InputStream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class AbsShaderIcon<T extends View> {
    public static final int $stable = 8;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    private final Runnable mOnAnimationDraw;
    private final H0.d mShader$delegate;
    private final String mTraceNameUpdateFrame;
    private final E mUICoroutine;
    private final T view;

    public static final class a extends o implements Function0 {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m73invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m73invoke() {
            AbsShaderIcon absShaderIcon = AbsShaderIcon.this;
            absShaderIcon.updateSize(absShaderIcon.getView().getWidth(), AbsShaderIcon.this.getView().getHeight());
        }
    }

    public static final class b extends o implements Function0 {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m74invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m74invoke() {
            AbsShaderIcon.this.getMOnAnimationDraw().run();
        }
    }

    public static final class c extends o implements Function0 {
        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final RuntimeShader invoke() {
            AbsShaderIcon absShaderIcon = AbsShaderIcon.this;
            return new RuntimeShader(absShaderIcon.loadShader(absShaderIcon.getShaderResId()));
        }
    }

    public static final class d extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2340a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function0 f2341b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Function0 function0, L0.d dVar) {
            super(2, dVar);
            this.f2341b = function0;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new d(this.f2341b, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((d) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            M0.c.c();
            if (this.f2340a != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            this.f2341b.invoke();
            return s.f314a;
        }
    }

    public AbsShaderIcon(T view) {
        n.g(view, "view");
        this.view = view;
        this.mUICoroutine = F.a(Q.c().z());
        this.mShader$delegate = e.b(new c());
        this.mTraceNameUpdateFrame = getDebugName() + " updateFrame";
        this.mOnAnimationDraw = new Runnable() { // from class: f0.c
            @Override // java.lang.Runnable
            public final void run() {
                AbsShaderIcon.mOnAnimationDraw$lambda$1(this.f4228a);
            }
        };
        runOnUIThread(new a());
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: f0.d
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                AbsShaderIcon._init_$lambda$2(this.f4229a, view2, i2, i3, i4, i5, i6, i7, i8, i9);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(AbsShaderIcon this$0, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        n.g(this$0, "this$0");
        this$0.updateSize(i4 - i2, i5 - i3);
        this$0.invalidate();
    }

    @VisibleForTesting(otherwise = 2)
    public static /* synthetic */ void getMOnAnimationDraw$annotations() {
    }

    private final boolean isLayerAvailable() {
        return this.view.isHardwareAccelerated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void mOnAnimationDraw$lambda$1(AbsShaderIcon this$0) {
        n.g(this$0, "this$0");
        Trace.beginSection(this$0.mTraceNameUpdateFrame);
        try {
            this$0.frameLoop();
            if (this$0.view.getVisibility() == 0 && this$0.isLayerAvailable()) {
                this$0.updateFrame();
            }
            s sVar = s.f314a;
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @UiThread
    @VisibleForTesting(otherwise = 2)
    public void frameLoop() {
    }

    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    public abstract String getDebugName();

    @UiThread
    public final Runnable getMOnAnimationDraw() {
        return this.mOnAnimationDraw;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final RuntimeShader getMShader() {
        return (RuntimeShader) this.mShader$delegate.getValue();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final E getMUICoroutine() {
        return this.mUICoroutine;
    }

    @RawRes
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public abstract int getShaderResId();

    public final T getView() {
        return this.view;
    }

    @AnyThread
    public final void invalidate() {
        runOnUIThread(new b());
    }

    @AnyThread
    @CheckResult
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final String loadShader(@RawRes int i2) throws IOException {
        InputStream inputStreamOpenRawResource = this.view.getContext().getResources().openRawResource(i2);
        byte[] bArr = new byte[inputStreamOpenRawResource.available()];
        inputStreamOpenRawResource.read(bArr);
        inputStreamOpenRawResource.close();
        return new String(bArr, f1.c.f4238b);
    }

    @AnyThread
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InterfaceC0380l0 runOnUIThread(Function0 block) {
        n.g(block, "block");
        return AbstractC0369g.b(this.mUICoroutine, null, null, new d(block, null), 3, null);
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void updateFrame() {
        this.view.setRenderEffect(RenderEffect.createRuntimeShaderEffect(getMShader(), "uTex"));
    }

    @CallSuper
    @RestrictTo({RestrictTo.Scope.SUBCLASSES})
    @UiThread
    public void updateSize(int i2, int i3) {
        getMShader().setFloatUniform("uResolution", new float[]{i2, i3});
    }
}
