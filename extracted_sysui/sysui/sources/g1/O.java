package g1;

import H0.j;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import l1.C0455j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class O extends n1.h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4389c;

    public O(int i2) {
        this.f4389c = i2;
    }

    public abstract void a(Object obj, Throwable th);

    public abstract L0.d c();

    public Throwable d(Object obj) {
        C0394v c0394v = obj instanceof C0394v ? (C0394v) obj : null;
        if (c0394v != null) {
            return c0394v.f4464a;
        }
        return null;
    }

    public Object e(Object obj) {
        return obj;
    }

    public final void f(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            H0.a.a(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        kotlin.jvm.internal.n.d(th);
        D.a(c().getContext(), new H("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
    }

    public abstract Object i();

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        Object objA;
        Object objA2;
        n1.i iVar = this.f6273b;
        try {
            L0.d dVarC = c();
            kotlin.jvm.internal.n.e(dVarC, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            C0455j c0455j = (C0455j) dVarC;
            L0.d dVar = c0455j.f5223e;
            Object obj = c0455j.f5225g;
            L0.g context = dVar.getContext();
            Object objC = l1.J.c(context, obj);
            M0 m0G = objC != l1.J.f5199a ? A.g(dVar, context, objC) : null;
            try {
                L0.g context2 = dVar.getContext();
                Object objI = i();
                Throwable thD = d(objI);
                InterfaceC0380l0 interfaceC0380l0 = (thD == null && P.b(this.f4389c)) ? (InterfaceC0380l0) context2.get(InterfaceC0380l0.f4430z) : null;
                if (interfaceC0380l0 != null && !interfaceC0380l0.isActive()) {
                    CancellationException cancellationExceptionF = interfaceC0380l0.f();
                    a(objI, cancellationExceptionF);
                    j.a aVar = H0.j.f299a;
                    dVar.resumeWith(H0.j.a(H0.k.a(cancellationExceptionF)));
                } else if (thD != null) {
                    j.a aVar2 = H0.j.f299a;
                    dVar.resumeWith(H0.j.a(H0.k.a(thD)));
                } else {
                    j.a aVar3 = H0.j.f299a;
                    dVar.resumeWith(H0.j.a(e(objI)));
                }
                H0.s sVar = H0.s.f314a;
                if (m0G == null || m0G.F0()) {
                    l1.J.a(context, objC);
                }
                try {
                    iVar.a();
                    objA2 = H0.j.a(H0.s.f314a);
                } catch (Throwable th) {
                    j.a aVar4 = H0.j.f299a;
                    objA2 = H0.j.a(H0.k.a(th));
                }
                f(null, H0.j.b(objA2));
            } catch (Throwable th2) {
                if (m0G == null || m0G.F0()) {
                    l1.J.a(context, objC);
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                j.a aVar5 = H0.j.f299a;
                iVar.a();
                objA = H0.j.a(H0.s.f314a);
            } catch (Throwable th4) {
                j.a aVar6 = H0.j.f299a;
                objA = H0.j.a(H0.k.a(th4));
            }
            f(th3, H0.j.b(objA));
        }
    }
}
