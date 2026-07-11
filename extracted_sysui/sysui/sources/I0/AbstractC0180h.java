package I0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: renamed from: I0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0180h extends AbstractC0179g {
    public static List c(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        List listA = AbstractC0182j.a(objArr);
        kotlin.jvm.internal.n.f(listA, "asList(...)");
        return listA;
    }

    public static float[] d(float[] fArr, float[] destination, int i2, int i3, int i4) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        System.arraycopy(fArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static int[] e(int[] iArr, int[] destination, int i2, int i3, int i4) {
        kotlin.jvm.internal.n.g(iArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        System.arraycopy(iArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static long[] f(long[] jArr, long[] destination, int i2, int i3, int i4) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        System.arraycopy(jArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static Object[] g(Object[] objArr, Object[] destination, int i2, int i3, int i4) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        System.arraycopy(objArr, i3, destination, i2, i4 - i3);
        return destination;
    }

    public static /* synthetic */ float[] h(float[] fArr, float[] fArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = fArr.length;
        }
        return d(fArr, fArr2, i2, i3, i4);
    }

    public static /* synthetic */ int[] i(int[] iArr, int[] iArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = iArr.length;
        }
        return e(iArr, iArr2, i2, i3, i4);
    }

    public static /* synthetic */ long[] j(long[] jArr, long[] jArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = jArr.length;
        }
        return f(jArr, jArr2, i2, i3, i4);
    }

    public static /* synthetic */ Object[] k(Object[] objArr, Object[] objArr2, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = objArr.length;
        }
        return g(objArr, objArr2, i2, i3, i4);
    }

    public static final float[] l(float[] fArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        AbstractC0178f.b(i3, fArr.length);
        float[] fArrCopyOfRange = Arrays.copyOfRange(fArr, i2, i3);
        kotlin.jvm.internal.n.f(fArrCopyOfRange, "copyOfRange(...)");
        return fArrCopyOfRange;
    }

    public static Object[] m(Object[] objArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        AbstractC0178f.b(i3, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i2, i3);
        kotlin.jvm.internal.n.f(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static final void n(long[] jArr, long j2, int i2, int i3) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        Arrays.fill(jArr, i2, i3, j2);
    }

    public static void o(Object[] objArr, Object obj, int i2, int i3) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        Arrays.fill(objArr, i2, i3, obj);
    }

    public static /* synthetic */ void p(long[] jArr, long j2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = jArr.length;
        }
        n(jArr, j2, i2, i3);
    }

    public static /* synthetic */ void q(Object[] objArr, Object obj, int i2, int i3, int i4, Object obj2) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = objArr.length;
        }
        o(objArr, obj, i2, i3);
    }

    public static void r(float[] fArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        Arrays.sort(fArr, i2, i3);
    }

    public static void s(int[] iArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(iArr, "<this>");
        Arrays.sort(iArr, i2, i3);
    }

    public static void t(long[] jArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        Arrays.sort(jArr, i2, i3);
    }

    public static final void u(Object[] objArr, Comparator comparator) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        kotlin.jvm.internal.n.g(comparator, "comparator");
        if (objArr.length > 1) {
            Arrays.sort(objArr, comparator);
        }
    }

    public static Double[] v(double[] dArr) {
        kotlin.jvm.internal.n.g(dArr, "<this>");
        Double[] dArr2 = new Double[dArr.length];
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            dArr2[i2] = Double.valueOf(dArr[i2]);
        }
        return dArr2;
    }
}
