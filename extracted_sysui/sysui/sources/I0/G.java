package I0;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class G extends F {
    public static Map f() {
        x xVar = x.f337a;
        kotlin.jvm.internal.n.e(xVar, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return xVar;
    }

    public static Object g(Map map, Object obj) {
        kotlin.jvm.internal.n.g(map, "<this>");
        return E.a(map, obj);
    }

    public static Map h(H0.i... pairs) {
        kotlin.jvm.internal.n.g(pairs, "pairs");
        return pairs.length > 0 ? o(pairs, new LinkedHashMap(F.c(pairs.length))) : f();
    }

    public static final Map i(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? map : F.e(map) : f();
    }

    public static final void j(Map map, Iterable pairs) {
        kotlin.jvm.internal.n.g(map, "<this>");
        kotlin.jvm.internal.n.g(pairs, "pairs");
        Iterator it = pairs.iterator();
        while (it.hasNext()) {
            H0.i iVar = (H0.i) it.next();
            map.put(iVar.a(), iVar.b());
        }
    }

    public static final void k(Map map, H0.i[] pairs) {
        kotlin.jvm.internal.n.g(map, "<this>");
        kotlin.jvm.internal.n.g(pairs, "pairs");
        for (H0.i iVar : pairs) {
            map.put(iVar.a(), iVar.b());
        }
    }

    public static Map l(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return i(m(iterable, new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return f();
        }
        if (size != 1) {
            return m(iterable, new LinkedHashMap(F.c(collection.size())));
        }
        return F.d((H0.i) (iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next()));
    }

    public static final Map m(Iterable iterable, Map destination) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        j(destination, iterable);
        return destination;
    }

    public static Map n(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? p(map) : F.e(map) : f();
    }

    public static final Map o(H0.i[] iVarArr, Map destination) {
        kotlin.jvm.internal.n.g(iVarArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        k(destination, iVarArr);
        return destination;
    }

    public static Map p(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        return new LinkedHashMap(map);
    }
}
