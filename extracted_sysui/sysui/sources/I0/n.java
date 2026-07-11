package I0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n extends m {
    public static int o(Iterable iterable, int i2) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i2;
    }

    public static final Integer p(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        if (iterable instanceof Collection) {
            return Integer.valueOf(((Collection) iterable).size());
        }
        return null;
    }

    public static List q(Iterable iterable) {
        kotlin.jvm.internal.n.g(iterable, "<this>");
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            r.t(arrayList, (Iterable) it.next());
        }
        return arrayList;
    }
}
