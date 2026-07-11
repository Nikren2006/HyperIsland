package com.android.systemui;

import com.android.systemui.QSControlMiPlayDetailContent;
import kotlin.jvm.functions.Function0;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContent$DeviceItemHolder$folmeAnim$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ QSControlMiPlayDetailContent.DeviceItemHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$DeviceItemHolder$folmeAnim$2(QSControlMiPlayDetailContent.DeviceItemHolder deviceItemHolder) {
        super(0);
        this.this$0 = deviceItemHolder;
    }

    @Override // kotlin.jvm.functions.Function0
    public final IStateStyle invoke() {
        return Folme.useAt(this.this$0.getVolumeBar()).state();
    }
}
