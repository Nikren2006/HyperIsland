package com.mi.widget.shader;

import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.RestrictTo;
import androidx.compose.runtime.internal.StabilityInferred;
import com.mi.widget.core.IDriverShareStructure;
import e0.d;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
@StabilityInferred(parameters = 0)
@Keep
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class ChargerTextShader<T extends View> extends WaveShader<T> {
    public static final int $stable = 8;
    private float mGlowTime;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargerTextShader(T view) {
        super(view);
        n.g(view, "view");
    }

    @Override // com.mi.widget.core.AbsShader
    public String getDebugName() {
        return "ChargerTextShader";
    }

    @Override // com.mi.widget.core.AbsShader
    public int getShaderResId() {
        return d.f4032e;
    }

    @Override // com.mi.widget.shader.WaveShader, com.mi.widget.core.IShaderDriven
    public IDriverShareStructure onDriveFrameParameters(boolean z2, long j2, float f2, IDriverShareStructure data) {
        n.g(data, "data");
        this.mGlowTime = f2;
        return super.onDriveFrameParameters(z2, j2, f2, data);
    }

    @Override // com.mi.widget.shader.WaveShader, com.mi.widget.core.AbsShader
    public void updateShader() {
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uScale", 1.0f);
        getMShader$hyper_widget_1_0_7_pluginRelease().setFloatUniform("uRippleTime", this.mGlowTime);
        super.updateShader();
    }
}
