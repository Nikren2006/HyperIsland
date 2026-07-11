package I0;

import c1.C0232d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: I0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0181i extends AbstractC0180h {
    public static int A(long[] jArr) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        return jArr.length - 1;
    }

    public static final int B(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        return objArr.length - 1;
    }

    public static Object C(Object[] objArr, int i2) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        if (i2 < 0 || i2 > B(objArr)) {
            return null;
        }
        return objArr[i2];
    }

    public static int D(Object[] objArr, Object obj) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        int i2 = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i2 < length) {
                if (objArr[i2] == null) {
                    return i2;
                }
                i2++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i2 < length2) {
            if (kotlin.jvm.internal.n.c(obj, objArr[i2])) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static final Appendable E(byte[] bArr, Appendable buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) throws IOException {
        kotlin.jvm.internal.n.g(bArr, "<this>");
        kotlin.jvm.internal.n.g(buffer, "buffer");
        kotlin.jvm.internal.n.g(separator, "separator");
        kotlin.jvm.internal.n.g(prefix, "prefix");
        kotlin.jvm.internal.n.g(postfix, "postfix");
        kotlin.jvm.internal.n.g(truncated, "truncated");
        buffer.append(prefix);
        int i3 = 0;
        for (byte b2 : bArr) {
            i3++;
            if (i3 > 1) {
                buffer.append(separator);
            }
            if (i2 >= 0 && i3 > i2) {
                break;
            }
            if (function1 != null) {
                buffer.append((CharSequence) function1.invoke(Byte.valueOf(b2)));
            } else {
                buffer.append(String.valueOf((int) b2));
            }
        }
        if (i2 >= 0 && i3 > i2) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final String F(byte[] bArr, CharSequence separator, CharSequence prefix, CharSequence postfix, int i2, CharSequence truncated, Function1 function1) {
        kotlin.jvm.internal.n.g(bArr, "<this>");
        kotlin.jvm.internal.n.g(separator, "separator");
        kotlin.jvm.internal.n.g(prefix, "prefix");
        kotlin.jvm.internal.n.g(postfix, "postfix");
        kotlin.jvm.internal.n.g(truncated, "truncated");
        String string = ((StringBuilder) E(bArr, new StringBuilder(), separator, prefix, postfix, i2, truncated, function1)).toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    public static /* synthetic */ String G(byte[] bArr, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, CharSequence charSequence4, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i3 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i3 & 4) == 0 ? charSequence3 : "";
        if ((i3 & 8) != 0) {
            i2 = -1;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i3 & 32) != 0) {
            function1 = null;
        }
        return F(bArr, charSequence, charSequence5, charSequence6, i4, charSequence7, function1);
    }

    public static final void H(float[] fArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        AbstractC0174b.f320a.c(i2, i3, fArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            float f2 = fArr[i2];
            fArr[i2] = fArr[i5];
            fArr[i5] = f2;
            i5--;
            i2++;
        }
    }

    public static final void I(int[] iArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(iArr, "<this>");
        AbstractC0174b.f320a.c(i2, i3, iArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            int i6 = iArr[i2];
            iArr[i2] = iArr[i5];
            iArr[i5] = i6;
            i5--;
            i2++;
        }
    }

    public static final void J(long[] jArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        AbstractC0174b.f320a.c(i2, i3, jArr.length);
        int i4 = (i2 + i3) / 2;
        if (i2 == i4) {
            return;
        }
        int i5 = i3 - 1;
        while (i2 < i4) {
            long j2 = jArr[i2];
            jArr[i2] = jArr[i5];
            jArr[i5] = j2;
            i5--;
            i2++;
        }
    }

    public static char K(char[] cArr) {
        kotlin.jvm.internal.n.g(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static Object L(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        if (objArr.length == 1) {
            return objArr[0];
        }
        return null;
    }

    public static float[] M(float[] fArr, C0232d indices) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        kotlin.jvm.internal.n.g(indices, "indices");
        return indices.isEmpty() ? new float[0] : AbstractC0180h.l(fArr, indices.getStart().intValue(), indices.getEndInclusive().intValue() + 1);
    }

    public static void N(float[] fArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(fArr, "<this>");
        AbstractC0180h.r(fArr, i2, i3);
        H(fArr, i2, i3);
    }

    public static void O(int[] iArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(iArr, "<this>");
        AbstractC0180h.s(iArr, i2, i3);
        I(iArr, i2, i3);
    }

    public static void P(long[] jArr, int i2, int i3) {
        kotlin.jvm.internal.n.g(jArr, "<this>");
        AbstractC0180h.t(jArr, i2, i3);
        J(jArr, i2, i3);
    }

    public static final List Q(Object[] objArr, int i2) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
        }
        if (i2 == 0) {
            return m.h();
        }
        int length = objArr.length;
        if (i2 >= length) {
            return S(objArr);
        }
        if (i2 == 1) {
            return AbstractC0184l.d(objArr[length - 1]);
        }
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = length - i2; i3 < length; i3++) {
            arrayList.add(objArr[i3]);
        }
        return arrayList;
    }

    public static final Collection R(Object[] objArr, Collection destination) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        for (Object obj : objArr) {
            destination.add(obj);
        }
        return destination;
    }

    public static List S(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        int length = objArr.length;
        return length != 0 ? length != 1 ? T(objArr) : AbstractC0184l.d(objArr[0]) : m.h();
    }

    public static final List T(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        return new ArrayList(m.g(objArr));
    }

    public static final Set U(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        int length = objArr.length;
        return length != 0 ? length != 1 ? (Set) R(objArr, new LinkedHashSet(F.c(objArr.length))) : J.a(objArr[0]) : K.b();
    }

    public static boolean w(Object[] objArr, Object obj) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        return D(objArr, obj) >= 0;
    }

    public static List x(Object[] objArr, int i2) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        if (i2 >= 0) {
            return Q(objArr, c1.f.c(objArr.length - i2, 0));
        }
        throw new IllegalArgumentException(("Requested element count " + i2 + " is less than zero.").toString());
    }

    public static List y(Object[] objArr) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        return (List) z(objArr, new ArrayList());
    }

    public static final Collection z(Object[] objArr, Collection destination) {
        kotlin.jvm.internal.n.g(objArr, "<this>");
        kotlin.jvm.internal.n.g(destination, "destination");
        for (Object obj : objArr) {
            if (obj != null) {
                destination.add(obj);
            }
        }
        return destination;
    }
}
