package miui.systemui.notification.focus.moduleV3;

import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes4.dex */
@N0.f(c = "miui.systemui.notification.focus.moduleV3.ModuleViewHolder$compressChanged$1", f = "ModuleViewHolder.kt", l = {}, m = "invokeSuspend")
public final class ModuleViewHolder$compressChanged$1 extends N0.l implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;

    public ModuleViewHolder$compressChanged$1(L0.d dVar) {
        super(3, dVar);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), (L0.d) obj3);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        return N0.b.a(this.Z$0 || this.Z$1);
    }

    public final Object invoke(boolean z2, boolean z3, L0.d dVar) {
        ModuleViewHolder$compressChanged$1 moduleViewHolder$compressChanged$1 = new ModuleViewHolder$compressChanged$1(dVar);
        moduleViewHolder$compressChanged$1.Z$0 = z2;
        moduleViewHolder$compressChanged$1.Z$1 = z3;
        return moduleViewHolder$compressChanged$1.invokeSuspend(H0.s.f314a);
    }
}
