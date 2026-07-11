package L0;

import L0.g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements g.b {
    private final g.c key;

    public a(g.c key) {
        n.g(key, "key");
        this.key = key;
    }

    @Override // L0.g
    public <R> R fold(R r2, Function2 function2) {
        return (R) g.b.a.a(this, r2, function2);
    }

    @Override // L0.g.b, L0.g
    public g.b get(g.c cVar) {
        return g.b.a.b(this, cVar);
    }

    @Override // L0.g.b
    public g.c getKey() {
        return this.key;
    }

    @Override // L0.g
    public g minusKey(g.c cVar) {
        return g.b.a.c(this, cVar);
    }

    @Override // L0.g
    public g plus(g gVar) {
        return g.b.a.d(this, gVar);
    }
}
