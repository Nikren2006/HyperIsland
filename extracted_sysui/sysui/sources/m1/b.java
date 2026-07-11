package m1;

import H0.j;
import H0.k;
import L0.d;
import L0.g;
import M0.c;
import N0.h;
import g1.C0394v;
import g1.u0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import l1.B;
import l1.J;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static final void a(Function2 function2, Object obj, d dVar) {
        d dVarA = h.a(dVar);
        try {
            g context = dVar.getContext();
            Object objC = J.c(context, null);
            try {
                Object objInvoke = ((Function2) D.e(function2, 2)).invoke(obj, dVarA);
                if (objInvoke != c.c()) {
                    dVarA.resumeWith(j.a(objInvoke));
                }
            } finally {
                J.a(context, objC);
            }
        } catch (Throwable th) {
            j.a aVar = j.f299a;
            dVarA.resumeWith(j.a(k.a(th)));
        }
    }

    public static final Object b(B b2, Object obj, Function2 function2) {
        Object c0394v;
        Object objE0;
        try {
            c0394v = ((Function2) D.e(function2, 2)).invoke(obj, b2);
        } catch (Throwable th) {
            c0394v = new C0394v(th, false, 2, null);
        }
        if (c0394v != c.c() && (objE0 = b2.e0(c0394v)) != u0.f4457b) {
            if (objE0 instanceof C0394v) {
                throw ((C0394v) objE0).f4464a;
            }
            return u0.h(objE0);
        }
        return c.c();
    }
}
