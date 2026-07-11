package I0;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class F extends E {
    public static int c(int i2) {
        if (i2 < 0) {
            return i2;
        }
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static final Map d(H0.i pair) {
        kotlin.jvm.internal.n.g(pair, "pair");
        Map mapSingletonMap = Collections.singletonMap(pair.d(), pair.e());
        kotlin.jvm.internal.n.f(mapSingletonMap, "singletonMap(...)");
        return mapSingletonMap;
    }

    public static final Map e(Map map) {
        kotlin.jvm.internal.n.g(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        kotlin.jvm.internal.n.f(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }
}
