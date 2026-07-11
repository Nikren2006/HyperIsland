package w1;

import java.util.Map;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f6990a = d.a(1);

    public static final class a {
    }

    public final Object a(s1.c descriptor, a key) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        kotlin.jvm.internal.n.g(key, "key");
        Map map = (Map) this.f6990a.get(descriptor);
        Object obj = map != null ? map.get(key) : null;
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public final Object b(s1.c descriptor, a key, Function0 defaultValue) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        kotlin.jvm.internal.n.g(key, "key");
        kotlin.jvm.internal.n.g(defaultValue, "defaultValue");
        Object objA = a(descriptor, key);
        if (objA != null) {
            return objA;
        }
        Object objInvoke = defaultValue.invoke();
        c(descriptor, key, objInvoke);
        return objInvoke;
    }

    public final void c(s1.c descriptor, a key, Object value) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        kotlin.jvm.internal.n.g(key, "key");
        kotlin.jvm.internal.n.g(value, "value");
        Map map = this.f6990a;
        Object objA = map.get(descriptor);
        if (objA == null) {
            objA = d.a(1);
            map.put(descriptor, objA);
        }
        ((Map) objA).put(key, value);
    }
}
