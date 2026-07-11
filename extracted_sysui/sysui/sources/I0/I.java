package I0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class I extends H {
    public static List q(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        if (map.size() == 0) {
            return m.h();
        }
        Iterator it = map.entrySet().iterator();
        if (!it.hasNext()) {
            return m.h();
        }
        Map.Entry entry = (Map.Entry) it.next();
        if (!it.hasNext()) {
            return AbstractC0184l.d(new H0.i(entry.getKey(), entry.getValue()));
        }
        ArrayList arrayList = new ArrayList(map.size());
        arrayList.add(new H0.i(entry.getKey(), entry.getValue()));
        do {
            Map.Entry entry2 = (Map.Entry) it.next();
            arrayList.add(new H0.i(entry2.getKey(), entry2.getValue()));
        } while (it.hasNext());
        return arrayList;
    }
}
