package f1;

import I0.A;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class n extends m {
    public static final boolean j(String str, String suffix, boolean z2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(suffix, "suffix");
        return !z2 ? str.endsWith(suffix) : o(str, str.length() - suffix.length(), suffix, 0, suffix.length(), true);
    }

    public static /* synthetic */ boolean k(String str, String str2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return j(str, str2, z2);
    }

    public static final boolean l(String str, String str2, boolean z2) {
        return str == null ? str2 == null : !z2 ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public static /* synthetic */ boolean m(String str, String str2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return l(str, str2, z2);
    }

    public static boolean n(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        if (charSequence.length() != 0) {
            Iterable iterableX = o.x(charSequence);
            if (!(iterableX instanceof Collection) || !((Collection) iterableX).isEmpty()) {
                Iterator it = iterableX.iterator();
                while (it.hasNext()) {
                    if (!a.c(charSequence.charAt(((A) it).nextInt()))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static final boolean o(String str, int i2, String other, int i3, int i4, boolean z2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        return !z2 ? str.regionMatches(i2, other, i3, i4) : str.regionMatches(z2, i2, other, i3, i4);
    }

    public static final String p(String str, String oldValue, String newValue, boolean z2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(oldValue, "oldValue");
        kotlin.jvm.internal.n.g(newValue, "newValue");
        int i2 = 0;
        int iA = o.A(str, oldValue, 0, z2);
        if (iA < 0) {
            return str;
        }
        int length = oldValue.length();
        int iC = c1.f.c(length, 1);
        int length2 = (str.length() - length) + newValue.length();
        if (length2 < 0) {
            throw new OutOfMemoryError();
        }
        StringBuilder sb = new StringBuilder(length2);
        do {
            sb.append((CharSequence) str, i2, iA);
            sb.append(newValue);
            i2 = iA + length;
            if (iA >= str.length()) {
                break;
            }
            iA = o.A(str, oldValue, iA + iC, z2);
        } while (iA > 0);
        sb.append((CharSequence) str, i2, str.length());
        String string = sb.toString();
        kotlin.jvm.internal.n.f(string, "toString(...)");
        return string;
    }

    public static /* synthetic */ String q(String str, String str2, String str3, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        return p(str, str2, str3, z2);
    }

    public static final boolean r(String str, String prefix, boolean z2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(prefix, "prefix");
        return !z2 ? str.startsWith(prefix) : o(str, 0, prefix, 0, prefix.length(), z2);
    }

    public static /* synthetic */ boolean s(String str, String str2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return r(str, str2, z2);
    }
}
