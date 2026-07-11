package l1;

import g1.w0;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class v {
    public static final w a(Throwable th, String str) throws Throwable {
        if (th != null) {
            throw th;
        }
        d();
        throw new H0.c();
    }

    public static /* synthetic */ w b(Throwable th, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            th = null;
        }
        if ((i2 & 2) != 0) {
            str = null;
        }
        return a(th, str);
    }

    public static final boolean c(w0 w0Var) {
        return w0Var.z() instanceof w;
    }

    public static final Void d() {
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }

    public static final w0 e(t tVar, List list) {
        try {
            return tVar.b(list);
        } catch (Throwable th) {
            return a(th, tVar.a());
        }
    }
}
