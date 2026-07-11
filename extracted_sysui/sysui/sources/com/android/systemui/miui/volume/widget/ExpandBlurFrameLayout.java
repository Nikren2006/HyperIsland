package com.android.systemui.miui.volume.widget;

import android.content.Context;
import android.graphics.BlendMode;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.miui.blur.sdk.backdrop.a;
import com.miui.blur.sdk.backdrop.k;

/* JADX INFO: loaded from: classes2.dex */
public class ExpandBlurFrameLayout extends a {
    private static final k BLUR_LIGHT = new k.b().c(10).a(2141430691, BlendMode.COLOR_BURN).a(BasicMeasure.EXACTLY, null).b();

    public ExpandBlurFrameLayout(Context context) {
        super(context);
    }

    @Override // com.miui.blur.sdk.backdrop.ViewBlurDrawInfo
    public k getBlurStyleDayMode() {
        return BLUR_LIGHT;
    }

    @Override // com.miui.blur.sdk.backdrop.ViewBlurDrawInfo
    public k getBlurStyleNightMode() {
        return BLUR_LIGHT;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public ExpandBlurFrameLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
