package g1;

import L0.g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class N0 implements g.b, g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final N0 f4388a = new N0();

    @Override // L0.g
    public Object fold(Object obj, Function2 function2) {
        return g.b.a.a(this, obj, function2);
    }

    @Override // L0.g.b, L0.g
    public g.b get(g.c cVar) {
        return g.b.a.b(this, cVar);
    }

    @Override // L0.g.b
    public g.c getKey() {
        return this;
    }

    @Override // L0.g
    public L0.g minusKey(g.c cVar) {
        return g.b.a.c(this, cVar);
    }

    @Override // L0.g
    public L0.g plus(L0.g gVar) {
        return g.b.a.d(this, gVar);
    }
}
