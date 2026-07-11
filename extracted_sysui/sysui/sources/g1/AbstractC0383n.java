package g1;

import l1.C0455j;

/* JADX INFO: renamed from: g1.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0383n {
    public static final void a(InterfaceC0377k interfaceC0377k, S s2) {
        interfaceC0377k.g(new T(s2));
    }

    public static final C0379l b(L0.d dVar) {
        if (!(dVar instanceof C0455j)) {
            return new C0379l(dVar, 1);
        }
        C0379l c0379lK = ((C0455j) dVar).k();
        if (c0379lK != null) {
            if (!c0379lK.K()) {
                c0379lK = null;
            }
            if (c0379lK != null) {
                return c0379lK;
            }
        }
        return new C0379l(dVar, 2);
    }
}
