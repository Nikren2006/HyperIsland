package g1;

import java.lang.reflect.InvocationTargetException;
import l1.AbstractC0453h;

/* JADX INFO: loaded from: classes2.dex */
public abstract class D {
    public static final void a(L0.g gVar, Throwable th) {
        try {
            C c2 = (C) gVar.get(C.f4364w);
            if (c2 != null) {
                c2.u(gVar, th);
            } else {
                AbstractC0453h.a(gVar, th);
            }
        } catch (Throwable th2) {
            AbstractC0453h.a(gVar, b(th, th2));
        }
    }

    public static final Throwable b(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        H0.a.a(runtimeException, th);
        return runtimeException;
    }
}
