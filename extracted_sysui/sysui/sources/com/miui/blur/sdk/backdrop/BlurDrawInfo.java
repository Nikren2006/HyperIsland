package com.miui.blur.sdk.backdrop;

import android.graphics.Outline;
import android.view.ViewRootImpl;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes2.dex */
public interface BlurDrawInfo {
    public static final int DEFAULT_SAMPLING_PERIOD_NS = 20000000;

    void getBlurOutline(Outline outline);

    @RequiresApi(api = 30)
    default k getBlurStyle() {
        return k.f2514f;
    }

    ViewRootImpl getBlurViewRootImpl();

    int getHeight();

    void getLocationOnScreen(int[] iArr);

    default int getRequestedSamplingPeriodNs() {
        return DEFAULT_SAMPLING_PERIOD_NS;
    }

    int getWidth();

    void postInvalidateOnAnimation();
}
