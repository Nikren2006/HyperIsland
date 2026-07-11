package g1;

import H0.j;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: g1.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0398z {
    public static final Object a(Object obj, L0.d dVar) {
        if (!(obj instanceof C0394v)) {
            return H0.j.a(obj);
        }
        j.a aVar = H0.j.f299a;
        return H0.j.a(H0.k.a(((C0394v) obj).f4464a));
    }

    public static final Object b(Object obj, InterfaceC0377k interfaceC0377k) {
        Throwable thB = H0.j.b(obj);
        return thB == null ? obj : new C0394v(thB, false, 2, null);
    }

    public static final Object c(Object obj, Function1 function1) {
        Throwable thB = H0.j.b(obj);
        return thB == null ? function1 != null ? new C0395w(obj, function1) : obj : new C0394v(thB, false, 2, null);
    }

    public static /* synthetic */ Object d(Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            function1 = null;
        }
        return c(obj, function1);
    }
}
