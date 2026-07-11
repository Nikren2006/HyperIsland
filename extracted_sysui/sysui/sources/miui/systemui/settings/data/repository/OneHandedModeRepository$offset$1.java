package miui.systemui.settings.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.settings.data.repository.OneHandedModeRepository$offset$1", f = "OneHandedModeRepository.kt", l = {84}, m = "invokeSuspend")
public final class OneHandedModeRepository$offset$1 extends l implements Function2 {
    private /* synthetic */ Object L$0;
    int label;

    public OneHandedModeRepository$offset$1(d dVar) {
        super(2, dVar);
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        OneHandedModeRepository$offset$1 oneHandedModeRepository$offset$1 = new OneHandedModeRepository$offset$1(dVar);
        oneHandedModeRepository$offset$1.L$0 = obj;
        return oneHandedModeRepository$offset$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(InterfaceC0419g interfaceC0419g, d dVar) {
        return ((OneHandedModeRepository$offset$1) create(interfaceC0419g, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            s sVar = s.f314a;
            this.label = 1;
            if (interfaceC0419g.emit(sVar, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        return s.f314a;
    }
}
