package I0;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: renamed from: I0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0184l {
    public static List a(List builder) {
        kotlin.jvm.internal.n.g(builder, "builder");
        return ((J0.a) builder).k();
    }

    public static final Object[] b(Object[] objArr, boolean z2) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        if (z2 && kotlin.jvm.internal.n.c(objArr.getClass(), Object[].class)) {
            return objArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length, Object[].class);
        kotlin.jvm.internal.n.f(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }

    public static List c(int i2) {
        return new J0.a(i2);
    }

    public static List d(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        kotlin.jvm.internal.n.f(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static Object[] e(int i2, Object[] array) {
        kotlin.jvm.internal.n.g(array, "array");
        if (i2 < array.length) {
            array[i2] = null;
        }
        return array;
    }
}
