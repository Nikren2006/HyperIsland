package f1;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q extends p {
    public static final String e0(String str, int i2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        if (i2 >= 0) {
            String strSubstring = str.substring(c1.f.f(i2, str.length()));
            kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }

    public static char f0(CharSequence charSequence) {
        kotlin.jvm.internal.n.g(charSequence, "<this>");
        if (charSequence.length() != 0) {
            return charSequence.charAt(o.y(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static String g0(String str, int i2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        if (i2 >= 0) {
            String strSubstring = str.substring(0, c1.f.f(i2, str.length()));
            kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }

    public static String h0(String str, int i2) {
        kotlin.jvm.internal.n.g(str, "<this>");
        if (i2 >= 0) {
            int length = str.length();
            String strSubstring = str.substring(length - c1.f.f(i2, length));
            kotlin.jvm.internal.n.f(strSubstring, "substring(...)");
            return strSubstring;
        }
        throw new IllegalArgumentException(("Requested character count " + i2 + " is less than zero.").toString());
    }
}
