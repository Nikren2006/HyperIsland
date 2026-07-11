package kotlin.jvm.internal;

import W0.e;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public abstract class D {
    public static Collection a(Object obj) {
        if ((obj instanceof W0.a) && !(obj instanceof W0.b)) {
            o(obj, "kotlin.collections.MutableCollection");
        }
        return f(obj);
    }

    public static Iterable b(Object obj) {
        if ((obj instanceof W0.a) && !(obj instanceof W0.c)) {
            o(obj, "kotlin.collections.MutableIterable");
        }
        return g(obj);
    }

    public static List c(Object obj) {
        if ((obj instanceof W0.a) && !(obj instanceof W0.d)) {
            o(obj, "kotlin.collections.MutableList");
        }
        return h(obj);
    }

    public static Map d(Object obj) {
        if ((obj instanceof W0.a) && !(obj instanceof W0.e)) {
            o(obj, "kotlin.collections.MutableMap");
        }
        return i(obj);
    }

    public static Object e(Object obj, int i2) {
        if (obj != null && !k(obj, i2)) {
            o(obj, "kotlin.jvm.functions.Function" + i2);
        }
        return obj;
    }

    public static Collection f(Object obj) {
        try {
            return (Collection) obj;
        } catch (ClassCastException e2) {
            throw n(e2);
        }
    }

    public static Iterable g(Object obj) {
        try {
            return (Iterable) obj;
        } catch (ClassCastException e2) {
            throw n(e2);
        }
    }

    public static List h(Object obj) {
        try {
            return (List) obj;
        } catch (ClassCastException e2) {
            throw n(e2);
        }
    }

    public static Map i(Object obj) {
        try {
            return (Map) obj;
        } catch (ClassCastException e2) {
            throw n(e2);
        }
    }

    public static int j(Object obj) {
        if (obj instanceof j) {
            return ((j) obj).getArity();
        }
        if (obj instanceof Function0) {
            return 0;
        }
        if (obj instanceof Function1) {
            return 1;
        }
        if (obj instanceof Function2) {
            return 2;
        }
        if (obj instanceof Function3) {
            return 3;
        }
        if (obj instanceof V0.n) {
            return 4;
        }
        if (obj instanceof V0.o) {
            return 5;
        }
        return obj instanceof V0.p ? 6 : -1;
    }

    public static boolean k(Object obj, int i2) {
        return (obj instanceof H0.b) && j(obj) == i2;
    }

    public static boolean l(Object obj) {
        return (obj instanceof Map.Entry) && (!(obj instanceof W0.a) || (obj instanceof e.a));
    }

    public static Throwable m(Throwable th) {
        return n.n(th, D.class.getName());
    }

    public static ClassCastException n(ClassCastException classCastException) {
        throw ((ClassCastException) m(classCastException));
    }

    public static void o(Object obj, String str) {
        p((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
    }

    public static void p(String str) {
        throw n(new ClassCastException(str));
    }
}
