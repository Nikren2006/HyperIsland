package I0;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public abstract class L extends K {
    public static Set e(Set set, Iterable elements) {
        kotlin.jvm.internal.n.g(set, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        Collection<?> collectionV = r.v(elements);
        if (collectionV.isEmpty()) {
            return u.o0(set);
        }
        if (!(collectionV instanceof Set)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(set);
            linkedHashSet.removeAll(collectionV);
            return linkedHashSet;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Object obj : set) {
            if (!collectionV.contains(obj)) {
                linkedHashSet2.add(obj);
            }
        }
        return linkedHashSet2;
    }

    public static Set f(Set set, Object obj) {
        kotlin.jvm.internal.n.g(set, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(F.c(set.size()));
        boolean z2 = false;
        for (Object obj2 : set) {
            boolean z3 = true;
            if (!z2 && kotlin.jvm.internal.n.c(obj2, obj)) {
                z2 = true;
                z3 = false;
            }
            if (z3) {
                linkedHashSet.add(obj2);
            }
        }
        return linkedHashSet;
    }

    public static Set g(Set set, Iterable elements) {
        int size;
        kotlin.jvm.internal.n.g(set, "<this>");
        kotlin.jvm.internal.n.g(elements, "elements");
        Integer numP = n.p(elements);
        if (numP != null) {
            size = set.size() + numP.intValue();
        } else {
            size = set.size() * 2;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(F.c(size));
        linkedHashSet.addAll(set);
        r.t(linkedHashSet, elements);
        return linkedHashSet;
    }

    public static Set h(Set set, Object obj) {
        kotlin.jvm.internal.n.g(set, "<this>");
        LinkedHashSet linkedHashSet = new LinkedHashSet(F.c(set.size() + 1));
        linkedHashSet.addAll(set);
        linkedHashSet.add(obj);
        return linkedHashSet;
    }
}
