package I0;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class E {
    public static final Object a(Map map, Object obj) {
        kotlin.jvm.internal.n.g(map, "<this>");
        if (map instanceof C) {
            return ((C) map).b(obj);
        }
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static Map b(Map map, Function1 defaultValue) {
        kotlin.jvm.internal.n.g(map, "<this>");
        kotlin.jvm.internal.n.g(defaultValue, "defaultValue");
        return map instanceof C ? b(((C) map).a(), defaultValue) : new D(map, defaultValue);
    }
}
