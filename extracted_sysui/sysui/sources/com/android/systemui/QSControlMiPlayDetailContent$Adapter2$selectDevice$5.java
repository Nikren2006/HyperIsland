package com.android.systemui;

import java.util.ArrayList;
import kotlin.jvm.functions.Function3;
import systemui.plugin.eventtracking.events.MiPlayEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContent$Adapter2$selectDevice$5 extends kotlin.jvm.internal.o implements Function3 {
    final /* synthetic */ m0.i $device;
    final /* synthetic */ int $resCode;
    final /* synthetic */ boolean $select;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$Adapter2$selectDevice$5(m0.i iVar, boolean z2, int i2) {
        super(3);
        this.$device = iVar;
        this.$select = z2;
        this.$resCode = i2;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke(((Boolean) obj).booleanValue(), ((Number) obj2).longValue(), (ArrayList<?>) obj3);
        return H0.s.f314a;
    }

    public final void invoke(boolean z2, long j2, ArrayList<?> arrayList) {
        String str = z2 ? MiPlayEventsKt.VALUE_RESULT_FAIL : MiPlayEventsKt.VALUE_RESULT_SUCCESS;
        if (arrayList != null) {
            m0.i iVar = this.$device;
            boolean z3 = this.$select;
            int i2 = this.$resCode;
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            miPlayDetailViewModel.trackSelectDevice(iVar, z3, miPlayDetailViewModel.getMMiPlayRef().getMRef(), str, String.valueOf(i2), arrayList);
        }
    }
}
