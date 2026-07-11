package L0;

import L0.g;
import java.io.Serializable;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class h implements g, Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f402a = new h();

    @Override // L0.g
    public Object fold(Object obj, Function2 operation) {
        n.g(operation, "operation");
        return obj;
    }

    @Override // L0.g
    public g.b get(g.c key) {
        n.g(key, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // L0.g
    public g minusKey(g.c key) {
        n.g(key, "key");
        return this;
    }

    @Override // L0.g
    public g plus(g context) {
        n.g(context, "context");
        return context;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
