package com.android.systemui;

import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import kotlin.jvm.functions.Function2;
import miui.systemui.coroutines.Dispatchers;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.SystemVolumeController$notifyRunnable$1$1", f = "MiPlayDetailViewModel.kt", l = {1211}, m = "invokeSuspend")
public final class SystemVolumeController$notifyRunnable$1$1 extends N0.l implements Function2 {
    int label;

    public SystemVolumeController$notifyRunnable$1$1(L0.d dVar) {
        super(2, dVar);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new SystemVolumeController$notifyRunnable$1$1(dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((SystemVolumeController$notifyRunnable$1$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
            SystemVolumeController$notifyRunnable$1$1$systemVolume$1 systemVolumeController$notifyRunnable$1$1$systemVolume$1 = new SystemVolumeController$notifyRunnable$1$1$systemVolume$1(null);
            this.label = 1;
            obj = AbstractC0367f.c(io, systemVolumeController$notifyRunnable$1$1$systemVolume$1, this);
            if (obj == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        SystemVolumeController.INSTANCE.updateVolumeProgress(((Number) obj).intValue());
        return H0.s.f314a;
    }
}
