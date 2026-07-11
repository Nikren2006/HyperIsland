package com.mi.widget.core;

import H0.s;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.EmptySuper;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.IShaderViewListener;
import f0.EnumC0351e;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class AbsShaderView<V extends View> extends AbsShader<V> {
    public static final int $stable = 8;
    private boolean mCurrentVisible;
    private long mGlobalTime;

    public static final class a extends o implements Function1 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f2343b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(View view) {
            super(1);
            this.f2343b = view;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Boolean) obj).booleanValue());
            return s.f314a;
        }

        public final void invoke(boolean z2) {
            if (AbsShaderView.this.mCurrentVisible == z2) {
                return;
            }
            Log.i(AbsShaderView.this.getDebugName(), this.f2343b + " viewTree visibility changed lastVisible=" + AbsShaderView.this.mCurrentVisible + ", curVisible=" + z2 + ", visibilityStopFrame=" + AbsShaderView.this.visibilityStopFrame());
            AbsShaderView.this.mCurrentVisible = z2;
            if (!AbsShaderView.this.visibilityStopFrame()) {
                AbsShaderView.this.setFrameLoopStrategy$hyper_widget_1_0_7_pluginRelease(z2 ? EnumC0351e.f4232c : EnumC0351e.f4231b);
                return;
            }
            AbsShaderView absShaderView = AbsShaderView.this;
            if (z2) {
                absShaderView.start();
            } else {
                absShaderView.stop();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbsShaderView(V view, IShaderViewListener listener) {
        super(view);
        n.g(view, "view");
        n.g(listener, "listener");
        this.mGlobalTime = SystemClock.elapsedRealtime();
        this.mCurrentVisible = view.isShown();
        listener.addListener(new IShaderViewListener.a(new a(view)));
        autoStart();
    }

    private final void autoStart() {
        boolean z2 = true;
        boolean z3 = !attachStateStopFrame() || getView().isAttachedToWindow();
        if (visibilityStopFrame() && !this.mCurrentVisible) {
            z2 = false;
        }
        if (z3 && z2) {
            Log.i(getDebugName(), getView() + " autoStart shader rendering");
            start();
            if (!visibilityStopFrame()) {
                setFrameLoopStrategy$hyper_widget_1_0_7_pluginRelease(this.mCurrentVisible ? EnumC0351e.f4232c : EnumC0351e.f4231b);
            }
        }
        Log.i(getDebugName(), "autoStart attachStartShader=" + z3 + " visibilityStartShader=" + z2);
    }

    public boolean attachStateStopFrame() {
        return true;
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onViewAttachedToWindow(View v2) {
        n.g(v2, "v");
        if (!visibilityStopFrame() || this.mCurrentVisible) {
            start();
        }
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void onViewDetachedFromWindow(View v2) {
        n.g(v2, "v");
        if (attachStateStopFrame()) {
            stop();
        }
    }

    @EmptySuper
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void updateContent() {
    }

    @Override // com.mi.widget.core.AbsShader
    @CallSuper
    public void updateShader() {
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uTime", (SystemClock.elapsedRealtime() - this.mGlobalTime) / 1000.0f);
        updateContent();
        super.updateShader();
    }

    public boolean visibilityStopFrame() {
        return true;
    }
}
