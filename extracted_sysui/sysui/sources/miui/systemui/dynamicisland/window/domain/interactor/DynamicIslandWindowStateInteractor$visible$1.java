package miui.systemui.dynamicisland.window.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor$visible$1", f = "DynamicIslandWindowStateInteractor.kt", l = {}, m = "invokeSuspend")
public final class DynamicIslandWindowStateInteractor$visible$1 extends l implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;

    public DynamicIslandWindowStateInteractor$visible$1(d dVar) {
        super(3, dVar);
    }

    public final Object invoke(int i2, boolean z2, d dVar) {
        DynamicIslandWindowStateInteractor$visible$1 dynamicIslandWindowStateInteractor$visible$1 = new DynamicIslandWindowStateInteractor$visible$1(dVar);
        dynamicIslandWindowStateInteractor$visible$1.I$0 = i2;
        dynamicIslandWindowStateInteractor$visible$1.Z$0 = z2;
        return dynamicIslandWindowStateInteractor$visible$1.invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        return b.a(this.I$0 > 0 || this.Z$0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Number) obj).intValue(), ((Boolean) obj2).booleanValue(), (d) obj3);
    }
}
