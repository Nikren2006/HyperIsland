package L0;

import L0.g;
import java.io.Serializable;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements g, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.b f397b;

    public static final class a extends o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f398a = new a();

        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final String invoke(String acc, g.b element) {
            n.g(acc, "acc");
            n.g(element, "element");
            if (acc.length() == 0) {
                return element.toString();
            }
            return acc + ", " + element;
        }
    }

    public c(g left, g.b element) {
        n.g(left, "left");
        n.g(element, "element");
        this.f396a = left;
        this.f397b = element;
    }

    private final int h() {
        int i2 = 2;
        while (true) {
            g gVar = this.f396a;
            this = gVar instanceof c ? (c) gVar : null;
            if (this == null) {
                return i2;
            }
            i2++;
        }
    }

    public final boolean b(g.b bVar) {
        return n.c(get(bVar.getKey()), bVar);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof c) {
                c cVar = (c) obj;
                if (cVar.h() != h() || !cVar.g(this)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // L0.g
    public Object fold(Object obj, Function2 operation) {
        n.g(operation, "operation");
        return operation.invoke(this.f396a.fold(obj, operation), this.f397b);
    }

    public final boolean g(c cVar) {
        while (b(cVar.f397b)) {
            g gVar = cVar.f396a;
            if (!(gVar instanceof c)) {
                n.e(gVar, "null cannot be cast to non-null type kotlin.coroutines.CoroutineContext.Element");
                return b((g.b) gVar);
            }
            cVar = (c) gVar;
        }
        return false;
    }

    @Override // L0.g
    public g.b get(g.c key) {
        n.g(key, "key");
        while (true) {
            g.b bVar = this.f397b.get(key);
            if (bVar != null) {
                return bVar;
            }
            g gVar = this.f396a;
            if (!(gVar instanceof c)) {
                return gVar.get(key);
            }
            this = (c) gVar;
        }
    }

    public int hashCode() {
        return this.f396a.hashCode() + this.f397b.hashCode();
    }

    @Override // L0.g
    public g minusKey(g.c key) {
        n.g(key, "key");
        if (this.f397b.get(key) != null) {
            return this.f396a;
        }
        g gVarMinusKey = this.f396a.minusKey(key);
        return gVarMinusKey == this.f396a ? this : gVarMinusKey == h.f402a ? this.f397b : new c(gVarMinusKey, this.f397b);
    }

    @Override // L0.g
    public g plus(g gVar) {
        return g.a.a(this, gVar);
    }

    public String toString() {
        return '[' + ((String) fold("", a.f398a)) + ']';
    }
}
