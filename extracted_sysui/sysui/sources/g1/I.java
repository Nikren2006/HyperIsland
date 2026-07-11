package g1;

import H0.j;
import l1.C0455j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class I {
    public static final String a(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final String b(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String c(L0.d dVar) {
        Object objA;
        if (dVar instanceof C0455j) {
            return dVar.toString();
        }
        try {
            j.a aVar = H0.j.f299a;
            objA = H0.j.a(dVar + '@' + b(dVar));
        } catch (Throwable th) {
            j.a aVar2 = H0.j.f299a;
            objA = H0.j.a(H0.k.a(th));
        }
        if (H0.j.b(objA) != null) {
            objA = dVar.getClass().getName() + '@' + b(dVar);
        }
        return (String) objA;
    }
}
