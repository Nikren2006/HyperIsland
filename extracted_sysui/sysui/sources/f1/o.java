package f1;

import I0.A;
import I0.AbstractC0180h;
import I0.AbstractC0181i;
import I0.AbstractC0184l;
import I0.u;
import c1.C0230b;
import c1.C0232d;
import com.miui.maml.elements.MusicLyricParser;
import e1.InterfaceC0340e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o extends n {

    public static final class a extends kotlin.jvm.internal.o implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f4258a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f4259b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(List list, boolean z2) {
            super(2);
            this.f4258a = list;
            this.f4259b = z2;
        }

        public final H0.i b(CharSequence $receiver, int i2) {
            kotlin.jvm.internal.n.g($receiver, "$this$$receiver");
            H0.i iVarW = o.w($receiver, this.f4258a, i2, this.f4259b, false);
            if (iVarW != null) {
                return H0.o.a(iVarW.d(), Integer.valueOf(((String) iVarW.e()).length()));
            }
            return null;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return b((CharSequence) obj, ((Number) obj2).intValue());
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ CharSequence f4260a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(CharSequence charSequence) {
            super(1);
            this.f4260a = charSequence;
        }

        @Override // kotlin.jvm.functions.Function1
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final String invoke(C0232d it) {
            kotlin.jvm.internal.n.g(it, "it");
            return o.W(this.f4260a, it);
        }
    }

    public static final int A(CharSequence charSequence, String string, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(string, "string");
        return (z2 || !(charSequence instanceof String)) ? C(charSequence, string, i2, charSequence.length(), z2, false, 16, null) : ((String) charSequence).indexOf(string, i2);
    }

    public static final int B(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z2, boolean z3) {
        C0230b c0232d = !z3 ? new C0232d(c1.f.c(i2, 0), c1.f.f(i3, charSequence.length())) : c1.f.j(c1.f.f(i2, y(charSequence)), c1.f.c(i3, 0));
        if ((charSequence instanceof String) && (charSequence2 instanceof String)) {
            int iC = c0232d.c();
            int iD = c0232d.d();
            int iE = c0232d.e();
            if ((iE <= 0 || iC > iD) && (iE >= 0 || iD > iC)) {
                return -1;
            }
            while (!n.o((String) charSequence2, 0, (String) charSequence, iC, charSequence2.length(), z2)) {
                if (iC == iD) {
                    return -1;
                }
                iC += iE;
            }
            return iC;
        }
        int iC2 = c0232d.c();
        int iD2 = c0232d.d();
        int iE2 = c0232d.e();
        if ((iE2 <= 0 || iC2 > iD2) && (iE2 >= 0 || iD2 > iC2)) {
            return -1;
        }
        while (!P(charSequence2, 0, charSequence, iC2, charSequence2.length(), z2)) {
            if (iC2 == iD2) {
                return -1;
            }
            iC2 += iE2;
        }
        return iC2;
    }

    public static /* synthetic */ int C(CharSequence charSequence, CharSequence charSequence2, int i2, int i3, boolean z2, boolean z3, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            z3 = false;
        }
        return B(charSequence, charSequence2, i2, i3, z2, z3);
    }

    public static /* synthetic */ int D(CharSequence charSequence, char c2, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return z(charSequence, c2, i2, z2);
    }

    public static /* synthetic */ int E(CharSequence charSequence, String str, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return A(charSequence, str, i2, z2);
    }

    public static final int F(CharSequence charSequence, char[] chars, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(chars, "chars");
        if (!z2 && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(AbstractC0181i.K(chars), i2);
        }
        A it = new C0232d(c1.f.c(i2, 0), y(charSequence)).iterator();
        while (it.hasNext()) {
            int iNextInt = it.nextInt();
            char cCharAt = charSequence.charAt(iNextInt);
            for (char c2 : chars) {
                if (f1.b.d(c2, cCharAt, z2)) {
                    return iNextInt;
                }
            }
        }
        return -1;
    }

    public static final int G(CharSequence charSequence, char c2, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return (z2 || !(charSequence instanceof String)) ? K(charSequence, new char[]{c2}, i2, z2) : ((String) charSequence).lastIndexOf(c2, i2);
    }

    public static final int H(CharSequence charSequence, String string, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(string, "string");
        return (z2 || !(charSequence instanceof String)) ? B(charSequence, string, i2, 0, z2, true) : ((String) charSequence).lastIndexOf(string, i2);
    }

    public static /* synthetic */ int I(CharSequence charSequence, char c2, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = y(charSequence);
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return G(charSequence, c2, i2, z2);
    }

    public static /* synthetic */ int J(CharSequence charSequence, String str, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = y(charSequence);
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        return H(charSequence, str, i2, z2);
    }

    public static final int K(CharSequence charSequence, char[] chars, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(chars, "chars");
        if (!z2 && chars.length == 1 && (charSequence instanceof String)) {
            return ((String) charSequence).lastIndexOf(AbstractC0181i.K(chars), i2);
        }
        for (int iF = c1.f.f(i2, y(charSequence)); -1 < iF; iF--) {
            char cCharAt = charSequence.charAt(iF);
            for (char c2 : chars) {
                if (f1.b.d(c2, cCharAt, z2)) {
                    return iF;
                }
            }
        }
        return -1;
    }

    public static final InterfaceC0340e L(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return V(charSequence, new String[]{MusicLyricParser.CRLF, "\n", "\r"}, false, 0, 6, null);
    }

    public static final List M(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return e1.l.r(L(charSequence));
    }

    public static final InterfaceC0340e N(CharSequence charSequence, String[] strArr, int i2, boolean z2, int i3) {
        Q(i3);
        return new d(charSequence, i2, i3, new a(AbstractC0180h.c(strArr), z2));
    }

    public static /* synthetic */ InterfaceC0340e O(CharSequence charSequence, String[] strArr, int i2, boolean z2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            z2 = false;
        }
        if ((i4 & 8) != 0) {
            i3 = 0;
        }
        return N(charSequence, strArr, i2, z2, i3);
    }

    public static final boolean P(CharSequence charSequence, int i2, CharSequence other, int i3, int i4, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        if (i3 < 0 || i2 < 0 || i2 > charSequence.length() - i4 || i3 > other.length() - i4) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            if (!f1.b.d(charSequence.charAt(i2 + i5), other.charAt(i3 + i5), z2)) {
                return false;
            }
        }
        return true;
    }

    public static final void Q(int i2) {
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i2).toString());
    }

    public static final List R(CharSequence charSequence, String[] delimiters, boolean z2, int i2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(delimiters, "delimiters");
        if (delimiters.length == 1) {
            String str = delimiters[0];
            if (str.length() != 0) {
                return S(charSequence, str, z2, i2);
            }
        }
        Iterable iterableF = e1.l.f(O(charSequence, delimiters, 0, z2, i2, 2, null));
        ArrayList arrayList = new ArrayList(I0.n.o(iterableF, 10));
        Iterator it = iterableF.iterator();
        while (it.hasNext()) {
            arrayList.add(W(charSequence, (C0232d) it.next()));
        }
        return arrayList;
    }

    public static final List S(CharSequence charSequence, String str, boolean z2, int i2) {
        Q(i2);
        int length = 0;
        int iA = A(charSequence, str, 0, z2);
        if (iA == -1 || i2 == 1) {
            return AbstractC0184l.d(charSequence.toString());
        }
        boolean z3 = i2 > 0;
        ArrayList arrayList = new ArrayList(z3 ? c1.f.f(i2, 10) : 10);
        do {
            arrayList.add(charSequence.subSequence(length, iA).toString());
            length = str.length() + iA;
            if (z3 && arrayList.size() == i2 - 1) {
                break;
            }
            iA = A(charSequence, str, length, z2);
        } while (iA != -1);
        arrayList.add(charSequence.subSequence(length, charSequence.length()).toString());
        return arrayList;
    }

    public static /* synthetic */ List T(CharSequence charSequence, String[] strArr, boolean z2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return R(charSequence, strArr, z2, i2);
    }

    public static final InterfaceC0340e U(CharSequence charSequence, String[] delimiters, boolean z2, int i2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(delimiters, "delimiters");
        return e1.l.o(O(charSequence, delimiters, 0, z2, i2, 2, null), new b(charSequence));
    }

    public static /* synthetic */ InterfaceC0340e V(CharSequence charSequence, String[] strArr, boolean z2, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return U(charSequence, strArr, z2, i2);
    }

    public static final String W(CharSequence charSequence, C0232d range) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(range, "range");
        return charSequence.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1).toString();
    }

    public static final String X(String str, char c2, String missingDelimiterValue) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(missingDelimiterValue, "missingDelimiterValue");
        int iD = D(str, c2, 0, false, 6, null);
        if (iD == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iD + 1, str.length());
        kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final String Y(String str, String delimiter, String missingDelimiterValue) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(delimiter, "delimiter");
        kotlin.jvm.internal.n.g(missingDelimiterValue, "missingDelimiterValue");
        int iE = E(str, delimiter, 0, false, 6, null);
        if (iE == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iE + delimiter.length(), str.length());
        kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String Z(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return X(str, c2, str2);
    }

    public static /* synthetic */ String a0(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str3 = str;
        }
        return Y(str, str2, str3);
    }

    public static final String b0(String str, char c2, String missingDelimiterValue) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(missingDelimiterValue, "missingDelimiterValue");
        int I2 = I(str, c2, 0, false, 6, null);
        if (I2 == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(I2 + 1, str.length());
        kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static String c0(String str, String delimiter, String missingDelimiterValue) {
        kotlin.jvm.internal.n.g(str, "<this>");
        kotlin.jvm.internal.n.g(delimiter, "delimiter");
        kotlin.jvm.internal.n.g(missingDelimiterValue, "missingDelimiterValue");
        int iJ = J(str, delimiter, 0, false, 6, null);
        if (iJ == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = str.substring(iJ + delimiter.length(), str.length());
        kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static /* synthetic */ String d0(String str, char c2, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = str;
        }
        return b0(str, c2, str2);
    }

    public static final boolean u(CharSequence charSequence, CharSequence other, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        kotlin.jvm.internal.n.g(other, "other");
        if (other instanceof String) {
            if (E(charSequence, (String) other, 0, z2, 2, null) < 0) {
                return false;
            }
        } else if (C(charSequence, other, 0, charSequence.length(), z2, false, 16, null) < 0) {
            return false;
        }
        return true;
    }

    public static /* synthetic */ boolean v(CharSequence charSequence, CharSequence charSequence2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        return u(charSequence, charSequence2, z2);
    }

    public static final H0.i w(CharSequence charSequence, Collection collection, int i2, boolean z2, boolean z3) {
        Object next;
        Object next2;
        if (!z2 && collection.size() == 1) {
            String str = (String) u.d0(collection);
            int iE = !z3 ? E(charSequence, str, i2, false, 4, null) : J(charSequence, str, i2, false, 4, null);
            if (iE < 0) {
                return null;
            }
            return H0.o.a(Integer.valueOf(iE), str);
        }
        C0230b c0232d = !z3 ? new C0232d(c1.f.c(i2, 0), charSequence.length()) : c1.f.j(c1.f.f(i2, y(charSequence)), 0);
        if (charSequence instanceof String) {
            int iC = c0232d.c();
            int iD = c0232d.d();
            int iE2 = c0232d.e();
            if ((iE2 > 0 && iC <= iD) || (iE2 < 0 && iD <= iC)) {
                while (true) {
                    Iterator it = collection.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                        String str2 = (String) next2;
                        if (n.o(str2, 0, (String) charSequence, iC, str2.length(), z2)) {
                            break;
                        }
                    }
                    String str3 = (String) next2;
                    if (str3 == null) {
                        if (iC == iD) {
                            break;
                        }
                        iC += iE2;
                    } else {
                        return H0.o.a(Integer.valueOf(iC), str3);
                    }
                }
            }
        } else {
            int iC2 = c0232d.c();
            int iD2 = c0232d.d();
            int iE3 = c0232d.e();
            if ((iE3 > 0 && iC2 <= iD2) || (iE3 < 0 && iD2 <= iC2)) {
                while (true) {
                    Iterator it2 = collection.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it2.next();
                        String str4 = (String) next;
                        if (P(str4, 0, charSequence, iC2, str4.length(), z2)) {
                            break;
                        }
                    }
                    String str5 = (String) next;
                    if (str5 == null) {
                        if (iC2 == iD2) {
                            break;
                        }
                        iC2 += iE3;
                    } else {
                        return H0.o.a(Integer.valueOf(iC2), str5);
                    }
                }
            }
        }
        return null;
    }

    public static final C0232d x(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return new C0232d(0, charSequence.length() - 1);
    }

    public static final int y(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int z(CharSequence charSequence, char c2, int i2, boolean z2) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        return (z2 || !(charSequence instanceof String)) ? F(charSequence, new char[]{c2}, i2, z2) : ((String) charSequence).indexOf(c2, i2);
    }
}
