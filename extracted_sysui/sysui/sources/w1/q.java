package w1;

import s1.e;
import s1.f;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q {
    public static final s1.c a(s1.c cVar, x1.b module) {
        s1.c cVarA;
        kotlin.jvm.internal.n.g(cVar, "<this>");
        kotlin.jvm.internal.n.g(module, "module");
        if (!kotlin.jvm.internal.n.c(cVar.c(), e.a.f6478a)) {
            return cVar.isInline() ? a(cVar.g(0), module) : cVar;
        }
        s1.c cVarB = s1.a.b(module, cVar);
        return (cVarB == null || (cVarA = a(cVarB, module)) == null) ? cVar : cVarA;
    }

    public static final p b(v1.a aVar, s1.c desc) {
        kotlin.jvm.internal.n.g(aVar, "<this>");
        kotlin.jvm.internal.n.g(desc, "desc");
        s1.e eVarC = desc.c();
        if (kotlin.jvm.internal.n.c(eVarC, f.b.f6481a)) {
            return p.LIST;
        }
        if (!kotlin.jvm.internal.n.c(eVarC, f.c.f6482a)) {
            return p.OBJ;
        }
        s1.c cVarA = a(desc.g(0), aVar.c());
        s1.e eVarC2 = cVarA.c();
        if ((eVarC2 instanceof s1.b) || kotlin.jvm.internal.n.c(eVarC2, e.b.f6479a)) {
            return p.MAP;
        }
        if (aVar.b().b()) {
            return p.LIST;
        }
        throw j.a(cVarA);
    }
}
