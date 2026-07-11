package L0;

import L0.g;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b implements g.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Function1 f394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.c f395b;

    public b(g.c baseKey, Function1 safeCast) {
        n.g(baseKey, "baseKey");
        n.g(safeCast, "safeCast");
        this.f394a = safeCast;
        this.f395b = baseKey instanceof b ? ((b) baseKey).f395b : baseKey;
    }

    public final boolean a(g.c key) {
        n.g(key, "key");
        return key == this || this.f395b == key;
    }

    public final g.b b(g.b element) {
        n.g(element, "element");
        return (g.b) this.f394a.invoke(element);
    }
}
