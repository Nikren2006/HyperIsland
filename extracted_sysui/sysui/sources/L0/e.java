package L0;

import L0.g;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public interface e extends g.b {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final b f399t = b.f400a;

    public static final class a {
        public static g.b a(e eVar, g.c key) {
            g.b bVarB;
            n.g(key, "key");
            if (!(key instanceof L0.b)) {
                if (e.f399t != key) {
                    return null;
                }
                n.e(eVar, "null cannot be cast to non-null type E of kotlin.coroutines.ContinuationInterceptor.get");
                return eVar;
            }
            L0.b bVar = (L0.b) key;
            if (!bVar.a(eVar.getKey()) || (bVarB = bVar.b(eVar)) == null) {
                return null;
            }
            return bVarB;
        }

        public static g b(e eVar, g.c key) {
            n.g(key, "key");
            if (!(key instanceof L0.b)) {
                return e.f399t == key ? h.f402a : eVar;
            }
            L0.b bVar = (L0.b) key;
            return (!bVar.a(eVar.getKey()) || bVar.b(eVar) == null) ? eVar : h.f402a;
        }
    }

    public static final class b implements g.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ b f400a = new b();
    }

    d interceptContinuation(d dVar);

    void releaseInterceptedContinuation(d dVar);
}
