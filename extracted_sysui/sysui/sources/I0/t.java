package I0;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t extends s {
    public static final void C(List list) {
        kotlin.jvm.internal.n.g(list, "<this>");
        Collections.reverse(list);
    }

    public static SortedSet D(Iterable iterable, Comparator comparator) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        kotlin.jvm.internal.n.g(comparator, "comparator");
        return (SortedSet) u.j0(iterable, new TreeSet(comparator));
    }
}
