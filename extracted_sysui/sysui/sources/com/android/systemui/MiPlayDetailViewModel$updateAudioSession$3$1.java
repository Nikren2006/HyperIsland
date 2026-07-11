package com.android.systemui;

import kotlin.jvm.functions.Function2;
import m0.C0466a;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.MiPlayDetailViewModel$updateAudioSession$3$1", f = "MiPlayDetailViewModel.kt", l = {176}, m = "invokeSuspend")
public final class MiPlayDetailViewModel$updateAudioSession$3$1 extends N0.l implements Function2 {
    final /* synthetic */ C0466a $it;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayDetailViewModel$updateAudioSession$3$1(C0466a c0466a, L0.d dVar) {
        super(2, dVar);
        this.$it = c0466a;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new MiPlayDetailViewModel$updateAudioSession$3$1(this.$it, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((MiPlayDetailViewModel$updateAudioSession$3$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            MiPlayDetailViewModel miPlayDetailViewModel = MiPlayDetailViewModel.INSTANCE;
            String packageName = this.$it.a().getPackageName();
            kotlin.jvm.internal.n.f(packageName, "getPackageName(...)");
            this.label = 1;
            if (miPlayDetailViewModel.savePlayingPackageName(packageName, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        return H0.s.f314a;
    }
}
