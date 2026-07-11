package com.android.systemui;

import kotlin.jvm.functions.Function0;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailHeader$folmeAnim$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ QSControlMiPlayDetailHeader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$folmeAnim$2(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader) {
        super(0);
        this.this$0 = qSControlMiPlayDetailHeader;
    }

    @Override // kotlin.jvm.functions.Function0
    public final IStateStyle invoke() {
        MiPlayVolumeBar miPlayVolumeBar = this.this$0.volumeBar;
        if (miPlayVolumeBar == null) {
            kotlin.jvm.internal.n.w("volumeBar");
            miPlayVolumeBar = null;
        }
        return Folme.useAt(miPlayVolumeBar).state();
    }
}
