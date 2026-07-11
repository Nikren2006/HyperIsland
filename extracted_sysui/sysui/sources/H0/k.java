package H0;

import H0.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k {
    public static final Object a(Throwable exception) {
        kotlin.jvm.internal.n.g(exception, "exception");
        return new j.b(exception);
    }

    public static final void b(Object obj) throws Throwable {
        if (obj instanceof j.b) {
            throw ((j.b) obj).f300a;
        }
    }
}
