package m1;

import H0.j;
import H0.k;
import H0.s;
import L0.d;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import l1.AbstractC0456k;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public static final void a(d dVar, Throwable th) throws Throwable {
        j.a aVar = j.f299a;
        dVar.resumeWith(j.a(k.a(th)));
        throw th;
    }

    public static final void b(d dVar, d dVar2) throws Throwable {
        try {
            d dVarB = M0.b.b(dVar);
            j.a aVar = j.f299a;
            AbstractC0456k.c(dVarB, j.a(s.f314a), null, 2, null);
        } catch (Throwable th) {
            a(dVar2, th);
        }
    }

    public static final void c(Function2 function2, Object obj, d dVar, Function1 function1) throws Throwable {
        try {
            d dVarB = M0.b.b(M0.b.a(function2, obj, dVar));
            j.a aVar = j.f299a;
            AbstractC0456k.b(dVarB, j.a(s.f314a), function1);
        } catch (Throwable th) {
            a(dVar, th);
        }
    }

    public static /* synthetic */ void d(Function2 function2, Object obj, d dVar, Function1 function1, int i2, Object obj2) throws Throwable {
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        c(function2, obj, dVar, function1);
    }
}
