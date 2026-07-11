package J0;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static final Object[] d(int i2) {
        if (i2 >= 0) {
            return new Object[i2];
        }
        throw new IllegalArgumentException("capacity must be non-negative.");
    }

    public static final Object[] e(Object[] objArr, int i2) {
        n.g(objArr, "<this>");
        Object[] objArrCopyOf = Arrays.copyOf(objArr, i2);
        n.f(objArrCopyOf, "copyOf(...)");
        return objArrCopyOf;
    }

    public static final void f(Object[] objArr, int i2) {
        n.g(objArr, "<this>");
        objArr[i2] = null;
    }

    public static final void g(Object[] objArr, int i2, int i3) {
        n.g(objArr, "<this>");
        while (i2 < i3) {
            f(objArr, i2);
            i2++;
        }
    }

    public static final boolean h(Object[] objArr, int i2, int i3, List list) {
        if (i3 != list.size()) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (!n.c(objArr[i2 + i4], list.get(i4))) {
                return false;
            }
        }
        return true;
    }

    public static final int i(Object[] objArr, int i2, int i3) {
        int iHashCode = 1;
        for (int i4 = 0; i4 < i3; i4++) {
            Object obj = objArr[i2 + i4];
            iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public static final String j(Object[] objArr, int i2, int i3, Collection collection) {
        StringBuilder sb = new StringBuilder((i3 * 3) + 2);
        sb.append("[");
        for (int i4 = 0; i4 < i3; i4++) {
            if (i4 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i2 + i4];
            if (obj == collection) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        String string = sb.toString();
        n.f(string, "toString(...)");
        return string;
    }
}
