package com.android.systemui.miui.volume.widget;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import com.miui.blur.sdk.backdrop.a;
import com.miui.blur.sdk.backdrop.k;

/* JADX INFO: loaded from: classes2.dex */
public class VolumeBlurFrameLayout extends a {
    private static final k BLUR_LIGHT = new k.b().c(10).a(1166049408, BlendMode.COLOR_BURN).a(1079597401, null).a(643389785, null).b();

    public VolumeBlurFrameLayout(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain;
        if (motionEvent == null || (getScaleX() >= 1.0f && getScaleY() >= 1.0f)) {
            motionEventObtain = null;
        } else {
            motionEventObtain = MotionEvent.obtain(motionEvent);
            Matrix matrix = getMatrix();
            if (matrix != null) {
                motionEventObtain.transform(matrix);
            }
        }
        if (motionEventObtain != null) {
            motionEvent = motionEventObtain;
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (motionEventObtain != null) {
            motionEventObtain.recycle();
        }
        return zDispatchTouchEvent;
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

    public VolumeBlurFrameLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
