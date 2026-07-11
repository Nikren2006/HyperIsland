package com.android.systemui;

import androidx.lifecycle.LiveData;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDetailViewModel$mController$1$1 extends kotlin.jvm.internal.o implements Function1 {
    public static final MiPlayDetailViewModel$mController$1$1 INSTANCE = new MiPlayDetailViewModel$mController$1$1();

    public MiPlayDetailViewModel$mController$1$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final LiveData<Integer> invoke(VolumeController volumeController) {
        return volumeController.getVolumeLiveData();
    }
}
