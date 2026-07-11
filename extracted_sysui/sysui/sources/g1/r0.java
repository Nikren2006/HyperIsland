package g1;

import g1.InterfaceC0380l0;
import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class r0 {
    public static final InterfaceC0392t a(InterfaceC0380l0 interfaceC0380l0) {
        return new C0386o0(interfaceC0380l0);
    }

    public static /* synthetic */ InterfaceC0392t b(InterfaceC0380l0 interfaceC0380l0, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            interfaceC0380l0 = null;
        }
        return AbstractC0388p0.a(interfaceC0380l0);
    }

    public static final void c(L0.g gVar, CancellationException cancellationException) {
        InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) gVar.get(InterfaceC0380l0.f4430z);
        if (interfaceC0380l0 != null) {
            interfaceC0380l0.a(cancellationException);
        }
    }

    public static /* synthetic */ void d(L0.g gVar, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        AbstractC0388p0.c(gVar, cancellationException);
    }

    public static final Object e(InterfaceC0380l0 interfaceC0380l0, L0.d dVar) {
        InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        Object objC = interfaceC0380l0.c(dVar);
        return objC == M0.c.c() ? objC : H0.s.f314a;
    }

    public static final void f(L0.g gVar) {
        InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) gVar.get(InterfaceC0380l0.f4430z);
        if (interfaceC0380l0 != null) {
            AbstractC0388p0.h(interfaceC0380l0);
        }
    }

    public static final void g(InterfaceC0380l0 interfaceC0380l0) {
        if (!interfaceC0380l0.isActive()) {
            throw interfaceC0380l0.f();
        }
    }
}
