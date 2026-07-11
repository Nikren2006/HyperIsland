package miui.systemui.settings.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.settings.data.repository.OneHandedModeRepository$isActivated$1$1", f = "OneHandedModeRepository.kt", l = {}, m = "invokeSuspend")
public final class OneHandedModeRepository$isActivated$1$1 extends l implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public OneHandedModeRepository$isActivated$1$1(d dVar) {
        super(3, dVar);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), (d) obj3);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        return b.a(this.Z$0 && this.Z$1);
    }

    public final Object invoke(boolean z2, boolean z3, d dVar) {
        OneHandedModeRepository$isActivated$1$1 oneHandedModeRepository$isActivated$1$1 = new OneHandedModeRepository$isActivated$1$1(dVar);
        oneHandedModeRepository$isActivated$1$1.Z$0 = z2;
        oneHandedModeRepository$isActivated$1$1.Z$1 = z3;
        return oneHandedModeRepository$isActivated$1$1.invokeSuspend(s.f314a);
    }
}
