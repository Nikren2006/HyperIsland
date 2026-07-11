package g1;

import L0.e;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: g1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0369g {
    public static final InterfaceC0380l0 a(E e2, L0.g gVar, G g2, Function2 function2) {
        L0.g gVarE = A.e(e2, gVar);
        D0 v0Var = g2.c() ? new v0(gVarE, function2) : new D0(gVarE, true);
        v0Var.E0(g2, v0Var, function2);
        return v0Var;
    }

    public static /* synthetic */ InterfaceC0380l0 b(E e2, L0.g gVar, G g2, Function2 function2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            gVar = L0.h.f402a;
        }
        if ((i2 & 2) != 0) {
            g2 = G.DEFAULT;
        }
        return AbstractC0367f.a(e2, gVar, g2, function2);
    }

    public static final Object c(L0.g gVar, Function2 function2, L0.d dVar) throws Throwable {
        Object objF0;
        L0.g context = dVar.getContext();
        L0.g gVarD = A.d(context, gVar);
        AbstractC0388p0.g(gVarD);
        if (gVarD == context) {
            l1.B b2 = new l1.B(gVarD, dVar);
            objF0 = m1.b.b(b2, b2, function2);
        } else {
            e.b bVar = L0.e.f399t;
            if (kotlin.jvm.internal.n.c(gVarD.get(bVar), context.get(bVar))) {
                M0 m02 = new M0(gVarD, dVar);
                L0.g context2 = m02.getContext();
                Object objC = l1.J.c(context2, null);
                try {
                    Object objB = m1.b.b(m02, m02, function2);
                    l1.J.a(context2, objC);
                    objF0 = objB;
                } catch (Throwable th) {
                    l1.J.a(context2, objC);
                    throw th;
                }
            } else {
                N n2 = new N(gVarD, dVar);
                m1.a.d(function2, n2, n2, null, 4, null);
                objF0 = n2.F0();
            }
        }
        if (objF0 == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objF0;
    }
}
