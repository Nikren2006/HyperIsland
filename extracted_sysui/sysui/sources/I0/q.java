package I0;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q extends p {
    public static void r(List list, Comparator comparator) {
        kotlin.jvm.internal.n.g(list, "<this>");
        kotlin.jvm.internal.n.g(comparator, "comparator");
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }
}
