package com.mi.widget.core;

import H0.s;
import android.util.Log;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.AbsShader;
import com.mi.widget.core.IShaderViewListener;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ShaderViewWrapper<S extends AbsShader<?>> {
    public static final int $stable = 8;
    private final String debugName;
    private boolean mLastVisible;
    private final S shader;

    public static final class a extends o implements Function1 {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Boolean) obj).booleanValue());
            return s.f314a;
        }

        public final void invoke(boolean z2) {
            Log.i(ShaderViewWrapper.this.debugName, ShaderViewWrapper.this.getShader().getView() + " viewTree's visible state changed lastVisible=" + ShaderViewWrapper.this.mLastVisible + ", curVisible=" + z2);
            ShaderViewWrapper.this.mLastVisible = z2;
            if (ShaderViewWrapper.this.mLastVisible) {
                ShaderViewWrapper.this.autoStart();
            } else {
                ShaderViewWrapper.this.getShader().stop();
            }
        }
    }

    public static final class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View v2) {
            n.g(v2, "v");
            ShaderViewWrapper.this.mLastVisible = v2.isShown();
            ShaderViewWrapper.this.autoStart();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View v2) {
            n.g(v2, "v");
            ShaderViewWrapper.this.getShader().stop();
        }
    }

    public ShaderViewWrapper(S shader, IShaderViewListener listener, String debugName) {
        n.g(shader, "shader");
        n.g(listener, "listener");
        n.g(debugName, "debugName");
        this.shader = shader;
        this.debugName = debugName;
        this.mLastVisible = shader.getView().isShown();
        listener.addListener(new IShaderViewListener.a(new a()));
        shader.getView().addOnAttachStateChangeListener(new b());
        autoStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void autoStart() {
        if (this.shader.getView().isAttachedToWindow() && this.mLastVisible) {
            Log.i(this.debugName, this.shader.getView() + " autoStart shader rendering.");
            this.shader.start();
            return;
        }
        String str = this.debugName;
        boolean z2 = this.shader.getView().getVisibility() == 0;
        Log.i(str, "autoStart failed, view curVisible=" + z2 + " curAttached=" + this.shader.getView().isAttachedToWindow() + " lastVisible=" + this.mLastVisible + ".");
    }

    public final S getShader() {
        return this.shader;
    }

    public /* synthetic */ ShaderViewWrapper(AbsShader absShader, IShaderViewListener iShaderViewListener, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(absShader, iShaderViewListener, (i2 & 4) != 0 ? "ShaderViewWrapper" : str);
    }
}
