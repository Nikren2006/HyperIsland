package com.android.systemui;

import kotlin.jvm.functions.Function2;
import m0.C0465C;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1", f = "MiPlayDetailViewModel.kt", l = {}, m = "invokeSuspend")
public final class SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1 extends N0.l implements Function2 {
    int label;

    public SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1(L0.d dVar) {
        super(2, dVar);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1(dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((SystemVolumeController$mPackageInstallerReceiver$2$1$onReceive$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        MiPlayController miPlayController = MiPlayController.INSTANCE;
        miPlayController.cancelReconnectAction();
        ((C0465C) miPlayController.get_MIPLAY_AUDIO_MANAGER().get()).k();
        return H0.s.f314a;
    }
}
