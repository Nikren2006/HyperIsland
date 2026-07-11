package com.mi.widget.shader;

import H0.i;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.l;
import V0.n;
import android.os.SystemClock;
import android.util.Size;
import android.view.View;
import androidx.annotation.AnyThread;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsShader;
import com.mi.widget.core.DriverShareStrategy;
import com.mi.widget.core.IDriverShareStructure;
import com.mi.widget.core.Origin;
import f0.AbstractC0352f;
import g1.AbstractC0369g;
import g1.E;
import g1.InterfaceC0380l0;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY})
public abstract class WaveShader<T extends View> extends AbsShader<T> {
    public static final int $stable = 8;
    private final u _glowIconOffset;
    private final u _glowIconPosition;
    private final u _glowIconWidth;
    private InterfaceC0380l0 mJob;
    private float mTime;

    public static final class a extends l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2379a;

        /* JADX INFO: renamed from: com.mi.widget.shader.WaveShader$a$a, reason: collision with other inner class name */
        public static final class C0060a extends l implements n {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f2381a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ float f2382b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public /* synthetic */ float f2383c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public /* synthetic */ Object f2384d;

            public C0060a(d dVar) {
                super(4, dVar);
            }

            public final Object e(float f2, float f3, Origin origin, d dVar) {
                C0060a c0060a = new C0060a(dVar);
                c0060a.f2382b = f2;
                c0060a.f2383c = f3;
                c0060a.f2384d = origin;
                return c0060a.invokeSuspend(s.f314a);
            }

            @Override // V0.n
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return e(((Number) obj).floatValue(), ((Number) obj2).floatValue(), (Origin) obj3, (d) obj4);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                c.c();
                if (this.f2381a != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                return new Float[]{N0.b.b(this.f2382b), N0.b.b(this.f2383c), N0.b.b(((Origin) this.f2384d).ordinal())};
            }
        }

        public static final class b implements InterfaceC0419g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ WaveShader f2385a;

            public b(WaveShader waveShader) {
                this.f2385a = waveShader;
            }

            @Override // j1.InterfaceC0419g
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Object emit(Float[] fArr, d dVar) {
                if (fArr.length != 3) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                this.f2385a.applyShaderSize();
                return s.f314a;
            }
        }

        public a(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new a(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((a) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.f2379a;
            if (i2 == 0) {
                k.b(obj);
                InterfaceC0418f interfaceC0418fI = AbstractC0420h.i(WaveShader.this._glowIconWidth, WaveShader.this._glowIconOffset, WaveShader.this._glowIconPosition, new C0060a(null));
                b bVar = new b(WaveShader.this);
                this.f2379a = 1;
                if (interfaceC0418fI.collect(bVar, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WaveShader(T view) {
        super(view);
        kotlin.jvm.internal.n.g(view, "view");
        Float fValueOf = Float.valueOf(0.0f);
        this._glowIconWidth = K.a(fValueOf);
        this._glowIconOffset = K.a(fValueOf);
        this._glowIconPosition = K.a(Origin.END);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyShaderSize() {
        i iVarA = AbstractC0352f.a((Origin) this._glowIconPosition.getValue(), new Size(getView().getWidth(), getView().getHeight()), ((Number) this._glowIconWidth.getValue()).floatValue(), ((Number) this._glowIconOffset.getValue()).floatValue(), getView().getLayoutDirection(), getDebugName());
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uRippleOrigin", ((Number) iVarA.d()).floatValue(), ((Number) iVarA.e()).floatValue());
        invalidate$hyper_widget_1_0_7_pluginRelease();
    }

    @Override // com.mi.widget.core.IShaderDriven
    public DriverShareStrategy getDriverShareStrategy() {
        return DriverShareStrategy.SHARE_COMMON_TYPE;
    }

    @AnyThread
    public final float getGlowIconOffset() {
        return ((Number) this._glowIconOffset.getValue()).floatValue();
    }

    @AnyThread
    public final Origin getGlowIconOrigin() {
        return (Origin) this._glowIconPosition.getValue();
    }

    @AnyThread
    public final float getGlowIconWidth() {
        return ((Number) this._glowIconWidth.getValue()).floatValue();
    }

    @Override // com.mi.widget.core.IShaderDriven
    @CallSuper
    public IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure data) {
        kotlin.jvm.internal.n.g(data, "data");
        this.mTime = (SystemClock.elapsedRealtime() - j2) / 1000.0f;
        return data;
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onStart() {
        applyShaderSize();
        this.mJob = AbstractC0369g.b(getMUICoroutine(), null, null, new a(null), 3, null);
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onStop() {
        InterfaceC0380l0 interfaceC0380l0 = this.mJob;
        if (interfaceC0380l0 != null) {
            if (interfaceC0380l0 == null) {
                kotlin.jvm.internal.n.w("mJob");
                interfaceC0380l0 = null;
            }
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
    }

    @UiThread
    public final void setGlowIconOffset(float f2) {
        this._glowIconOffset.setValue(Float.valueOf(f2));
    }

    @UiThread
    public final void setGlowIconOrigin(Origin value) {
        kotlin.jvm.internal.n.g(value, "value");
        this._glowIconPosition.setValue(value);
    }

    @UiThread
    public final void setGlowIconWidth(float f2) {
        this._glowIconWidth.setValue(Float.valueOf(f2));
    }

    @Override // com.mi.widget.core.AbsShader
    public void updateShader() {
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uTime", this.mTime);
        super.updateShader();
    }

    @Override // com.mi.widget.core.AbsShader
    public void updateSize(int i2, int i3) {
        super.updateSize(i2, i3);
        applyShaderSize();
    }
}
