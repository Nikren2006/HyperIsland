package i1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public interface t {

    public static final class a {
        public static /* synthetic */ boolean a(t tVar, Throwable th, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: close");
            }
            if ((i2 & 1) != 0) {
                th = null;
            }
            return tVar.o(th);
        }
    }

    Object b(Object obj, L0.d dVar);

    void i(Function1 function1);

    Object j(Object obj);

    boolean o(Throwable th);

    boolean s();
}
