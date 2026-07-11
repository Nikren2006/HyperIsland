package t1;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public interface b {

    public static final class a {
        public static int a(b bVar, s1.c descriptor) {
            n.g(descriptor, "descriptor");
            return -1;
        }

        public static boolean b(b bVar) {
            return false;
        }

        public static /* synthetic */ Object c(b bVar, s1.c cVar, int i2, q1.a aVar, Object obj, int i3, Object obj2) {
            if (obj2 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: decodeSerializableElement");
            }
            if ((i3 & 8) != 0) {
                obj = null;
            }
            return bVar.i(cVar, i2, aVar, obj);
        }
    }

    int b(s1.c cVar);

    float c(s1.c cVar, int i2);

    boolean d();

    int e(s1.c cVar);

    void h(s1.c cVar);

    Object i(s1.c cVar, int i2, q1.a aVar, Object obj);
}
