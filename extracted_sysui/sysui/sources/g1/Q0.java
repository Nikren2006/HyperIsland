package g1;

import l1.AbstractC0456k;
import l1.C0455j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Q0 {
    public static final Object a(L0.d dVar) {
        Object objC;
        L0.g context = dVar.getContext();
        AbstractC0388p0.g(context);
        L0.d dVarB = M0.b.b(dVar);
        C0455j c0455j = dVarB instanceof C0455j ? (C0455j) dVarB : null;
        if (c0455j == null) {
            objC = H0.s.f314a;
        } else {
            if (c0455j.f5222d.isDispatchNeeded(context)) {
                c0455j.l(context, H0.s.f314a);
            } else {
                P0 p02 = new P0();
                L0.g gVarPlus = context.plus(p02);
                H0.s sVar = H0.s.f314a;
                c0455j.l(gVarPlus, sVar);
                objC = (!p02.f4391a || AbstractC0456k.d(c0455j)) ? M0.c.c() : sVar;
            }
            objC = M0.c.c();
        }
        if (objC == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objC == M0.c.c() ? objC : H0.s.f314a;
    }
}
