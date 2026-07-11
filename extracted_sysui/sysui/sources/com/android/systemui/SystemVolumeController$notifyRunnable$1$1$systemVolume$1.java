package com.android.systemui;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.SystemVolumeController$notifyRunnable$1$1$systemVolume$1", f = "MiPlayDetailViewModel.kt", l = {}, m = "invokeSuspend")
public final class SystemVolumeController$notifyRunnable$1$1$systemVolume$1 extends N0.l implements Function2 {
    int label;

    public SystemVolumeController$notifyRunnable$1$1$systemVolume$1(L0.d dVar) {
        super(2, dVar);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new SystemVolumeController$notifyRunnable$1$1$systemVolume$1(dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((SystemVolumeController$notifyRunnable$1$1$systemVolume$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        return N0.b.c(SystemVolumeController.INSTANCE.getMAudioManager().getStreamVolume(3));
    }
}
