package j1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class x extends AbstractC0413a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Function2 f4800a;

    public x(Function2 function2) {
        this.f4800a = function2;
    }

    @Override // j1.AbstractC0413a
    public Object e(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        Object objInvoke = this.f4800a.invoke(interfaceC0419g, dVar);
        return objInvoke == M0.c.c() ? objInvoke : H0.s.f314a;
    }
}
