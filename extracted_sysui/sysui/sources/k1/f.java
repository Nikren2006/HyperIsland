package k1;

import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.D;
import l1.J;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    public static final Object b(L0.g gVar, Object obj, Object obj2, Function2 function2, L0.d dVar) {
        Object objC = J.c(gVar, obj2);
        try {
            Object objInvoke = ((Function2) D.e(function2, 2)).invoke(obj, new x(dVar, gVar));
            J.a(gVar, objC);
            if (objInvoke == M0.c.c()) {
                N0.h.c(dVar);
            }
            return objInvoke;
        } catch (Throwable th) {
            J.a(gVar, objC);
            throw th;
        }
    }

    public static /* synthetic */ Object c(L0.g gVar, Object obj, Object obj2, Function2 function2, L0.d dVar, int i2, Object obj3) {
        if ((i2 & 4) != 0) {
            obj2 = J.b(gVar);
        }
        return b(gVar, obj, obj2, function2, dVar);
    }

    public static final InterfaceC0419g d(InterfaceC0419g interfaceC0419g, L0.g gVar) {
        return interfaceC0419g instanceof w ? true : interfaceC0419g instanceof r ? interfaceC0419g : new z(interfaceC0419g, gVar);
    }
}
