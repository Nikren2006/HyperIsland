package L0;

import L0.e;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public interface g {

    public static final class a {

        /* JADX INFO: renamed from: L0.g$a$a, reason: collision with other inner class name */
        public static final class C0014a extends o implements Function2 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final C0014a f401a = new C0014a();

            public C0014a() {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public final g invoke(g acc, b element) {
                L0.c cVar;
                n.g(acc, "acc");
                n.g(element, "element");
                g gVarMinusKey = acc.minusKey(element.getKey());
                h hVar = h.f402a;
                if (gVarMinusKey == hVar) {
                    return element;
                }
                e.b bVar = e.f399t;
                e eVar = (e) gVarMinusKey.get(bVar);
                if (eVar == null) {
                    cVar = new L0.c(gVarMinusKey, element);
                } else {
                    g gVarMinusKey2 = gVarMinusKey.minusKey(bVar);
                    if (gVarMinusKey2 == hVar) {
                        return new L0.c(element, eVar);
                    }
                    cVar = new L0.c(new L0.c(gVarMinusKey2, element), eVar);
                }
                return cVar;
            }
        }

        public static g a(g gVar, g context) {
            n.g(context, "context");
            return context == h.f402a ? gVar : (g) context.fold(gVar, C0014a.f401a);
        }
    }

    public interface b extends g {

        public static final class a {
            public static Object a(b bVar, Object obj, Function2 operation) {
                n.g(operation, "operation");
                return operation.invoke(obj, bVar);
            }

            public static b b(b bVar, c key) {
                n.g(key, "key");
                if (!n.c(bVar.getKey(), key)) {
                    return null;
                }
                n.e(bVar, "null cannot be cast to non-null type E of kotlin.coroutines.CoroutineContext.Element.get");
                return bVar;
            }

            public static g c(b bVar, c key) {
                n.g(key, "key");
                return n.c(bVar.getKey(), key) ? h.f402a : bVar;
            }

            public static g d(b bVar, g context) {
                n.g(context, "context");
                return a.a(bVar, context);
            }
        }

        @Override // L0.g
        b get(c cVar);

        c getKey();
    }

    public interface c {
    }

    Object fold(Object obj, Function2 function2);

    b get(c cVar);

    g minusKey(c cVar);

    g plus(g gVar);
}
