package com.android.systemui;

import kotlin.jvm.functions.Function0;
import miui.systemui.widget.RelativeSeekBarInjector;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayVolumeBar$mInjector$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ MiPlayVolumeBar this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayVolumeBar$mInjector$2(MiPlayVolumeBar miPlayVolumeBar) {
        super(0);
        this.this$0 = miPlayVolumeBar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final RelativeSeekBarInjector invoke() {
        return new RelativeSeekBarInjector(this.this$0, false);
    }
}
