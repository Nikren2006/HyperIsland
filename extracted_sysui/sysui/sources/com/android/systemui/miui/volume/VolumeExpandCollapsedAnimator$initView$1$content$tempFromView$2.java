package com.android.systemui.miui.volume;

import android.view.View;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public final class VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2 extends kotlin.jvm.internal.o implements Function1 {
    public static final VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2 INSTANCE = new VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2();

    public VolumeExpandCollapsedAnimator$initView$1$content$tempFromView$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Boolean invoke(View it) {
        kotlin.jvm.internal.n.g(it, "it");
        return Boolean.valueOf(it.getVisibility() == 0);
    }
}
