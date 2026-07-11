package g1;

import java.util.concurrent.CancellationException;
import kotlin.jvm.functions.Function2;
import l1.C0451f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class F {
    public static final E a(L0.g gVar) {
        if (gVar.get(InterfaceC0380l0.f4430z) == null) {
            gVar = gVar.plus(r0.b(null, 1, null));
        }
        return new C0451f(gVar);
    }

    public static final void b(E e2, String str, Throwable th) {
        c(e2, AbstractC0358a0.a(str, th));
    }

    public static final void c(E e2, CancellationException cancellationException) {
        InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) e2.getCoroutineContext().get(InterfaceC0380l0.f4430z);
        if (interfaceC0380l0 != null) {
            interfaceC0380l0.a(cancellationException);
            return;
        }
        throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + e2).toString());
    }

    public static /* synthetic */ void d(E e2, String str, Throwable th, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            th = null;
        }
        b(e2, str, th);
    }

    public static /* synthetic */ void e(E e2, CancellationException cancellationException, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            cancellationException = null;
        }
        c(e2, cancellationException);
    }

    public static final Object f(Function2 function2, L0.d dVar) {
        l1.B b2 = new l1.B(dVar.getContext(), dVar);
        Object objB = m1.b.b(b2, b2, function2);
        if (objB == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objB;
    }

    public static final E g(E e2, L0.g gVar) {
        return new C0451f(e2.getCoroutineContext().plus(gVar));
    }
}
