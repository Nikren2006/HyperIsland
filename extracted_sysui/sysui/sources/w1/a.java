package w1;

import I0.r;
import I0.u;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6983a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f6985c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f6984b = new l();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public StringBuilder f6986d = new StringBuilder();

    public static /* synthetic */ Void r(a aVar, String str, int i2, String str2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i3 & 2) != 0) {
            i2 = aVar.f6983a;
        }
        if ((i3 & 4) != 0) {
            str2 = "";
        }
        return aVar.q(str, i2, str2);
    }

    public final void A(boolean z2) {
        ArrayList arrayList = new ArrayList();
        byte bX = x();
        if (bX != 8 && bX != 6) {
            l();
            return;
        }
        while (true) {
            byte bX2 = x();
            if (bX2 != 1) {
                if (bX2 == 8 || bX2 == 6) {
                    arrayList.add(Byte.valueOf(bX2));
                } else if (bX2 == 9) {
                    if (((Number) u.V(arrayList)).byteValue() != 8) {
                        throw j.c(this.f6983a, "found ] instead of } at path: " + this.f6984b, v());
                    }
                    r.A(arrayList);
                } else if (bX2 == 7) {
                    if (((Number) u.V(arrayList)).byteValue() != 6) {
                        throw j.c(this.f6983a, "found } instead of ] at path: " + this.f6984b, v());
                    }
                    r.A(arrayList);
                } else if (bX2 == 10) {
                    r(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, null, 6, null);
                    throw new H0.c();
                }
                h();
                if (arrayList.size() == 0) {
                    return;
                }
            } else if (z2) {
                l();
            } else {
                g();
            }
        }
    }

    public abstract int B();

    public String C(int i2, int i3) {
        return v().subSequence(i2, i3).toString();
    }

    public final String D() {
        String str = this.f6985c;
        kotlin.jvm.internal.n.d(str);
        this.f6985c = null;
        return str;
    }

    public abstract boolean E();

    public final boolean F() {
        int iZ = z(B());
        int length = v().length() - iZ;
        if (length < 4 || iZ == -1) {
            return true;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            if ("null".charAt(i2) != v().charAt(iZ + i2)) {
                return true;
            }
        }
        if (length > 4 && b.a(v().charAt(iZ + 4)) == 0) {
            return true;
        }
        this.f6983a = iZ + 4;
        return false;
    }

    public final void G(char c2) {
        int i2 = this.f6983a - 1;
        this.f6983a = i2;
        if (i2 >= 0 && c2 == '\"' && kotlin.jvm.internal.n.c(l(), "null")) {
            q("Expected string literal but 'null' literal was found", this.f6983a - 4, "Use 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.");
            throw new H0.c();
        }
        s(b.a(c2));
        throw new H0.c();
    }

    public final boolean H() {
        return v().charAt(this.f6983a - 1) != '\"';
    }

    public final int b(int i2) {
        int iZ = z(i2);
        if (iZ == -1) {
            r(this, "Expected escape sequence to continue, got EOF", 0, null, 6, null);
            throw new H0.c();
        }
        int i3 = iZ + 1;
        char cCharAt = v().charAt(iZ);
        if (cCharAt == 'u') {
            return d(v(), i3);
        }
        char cB = b.b(cCharAt);
        if (cB != 0) {
            this.f6986d.append(cB);
            return i3;
        }
        r(this, "Invalid escaped char '" + cCharAt + '\'', 0, null, 6, null);
        throw new H0.c();
    }

    public final int c(int i2, int i3) {
        e(i2, i3);
        return b(i3 + 1);
    }

    public final int d(CharSequence charSequence, int i2) {
        int i3 = i2 + 4;
        if (i3 < charSequence.length()) {
            this.f6986d.append((char) ((u(charSequence, i2) << 12) + (u(charSequence, i2 + 1) << 8) + (u(charSequence, i2 + 2) << 4) + u(charSequence, i2 + 3)));
            return i3;
        }
        this.f6983a = i2;
        o();
        if (this.f6983a + 4 < charSequence.length()) {
            return d(charSequence, this.f6983a);
        }
        r(this, "Unexpected EOF during unicode escape", 0, null, 6, null);
        throw new H0.c();
    }

    public void e(int i2, int i3) {
        this.f6986d.append(v(), i2, i3);
    }

    public abstract boolean f();

    public abstract String g();

    public abstract byte h();

    public abstract void i(char c2);

    public final String j() {
        return this.f6985c != null ? D() : g();
    }

    public final String k(CharSequence source, int i2, int i3) {
        int iZ;
        kotlin.jvm.internal.n.g(source, "source");
        char cCharAt = source.charAt(i3);
        boolean z2 = false;
        while (cCharAt != '\"') {
            if (cCharAt == '\\') {
                iZ = z(c(i2, i3));
                if (iZ == -1) {
                    r(this, "EOF", iZ, null, 4, null);
                    throw new H0.c();
                }
            } else {
                i3++;
                if (i3 >= source.length()) {
                    e(i2, i3);
                    iZ = z(i3);
                    if (iZ == -1) {
                        r(this, "EOF", iZ, null, 4, null);
                        throw new H0.c();
                    }
                } else {
                    continue;
                    cCharAt = source.charAt(i3);
                }
            }
            z2 = true;
            i2 = iZ;
            i3 = i2;
            cCharAt = source.charAt(i3);
        }
        String strC = !z2 ? C(i2, i3) : n(i2, i3);
        this.f6983a = i3 + 1;
        return strC;
    }

    public final String l() {
        if (this.f6985c != null) {
            return D();
        }
        int iB = B();
        if (iB >= v().length() || iB == -1) {
            r(this, "EOF", iB, null, 4, null);
            throw new H0.c();
        }
        byte bA = b.a(v().charAt(iB));
        if (bA == 1) {
            return j();
        }
        if (bA != 0) {
            r(this, "Expected beginning of the string, but got " + v().charAt(iB), 0, null, 6, null);
            throw new H0.c();
        }
        boolean z2 = false;
        while (b.a(v().charAt(iB)) == 0) {
            iB++;
            if (iB >= v().length()) {
                e(this.f6983a, iB);
                int iZ = z(iB);
                if (iZ == -1) {
                    this.f6983a = iB;
                    return n(0, 0);
                }
                iB = iZ;
                z2 = true;
            }
        }
        String strC = !z2 ? C(this.f6983a, iB) : n(this.f6983a, iB);
        this.f6983a = iB;
        return strC;
    }

    public final String m() {
        String strL = l();
        if (!kotlin.jvm.internal.n.c(strL, "null") || !H()) {
            return strL;
        }
        r(this, "Unexpected 'null' value instead of string literal", 0, null, 6, null);
        throw new H0.c();
    }

    public final String n(int i2, int i3) {
        e(i2, i3);
        String string = this.f6986d.toString();
        kotlin.jvm.internal.n.f(string, "escapedString.toString()");
        this.f6986d.setLength(0);
        return string;
    }

    public void o() {
    }

    public final void p() {
        if (h() == 10) {
            return;
        }
        r(this, "Expected EOF after parsing, but had " + v().charAt(this.f6983a - 1) + " instead", 0, null, 6, null);
        throw new H0.c();
    }

    public final Void q(String message, int i2, String hint) {
        String str;
        kotlin.jvm.internal.n.g(message, "message");
        kotlin.jvm.internal.n.g(hint, "hint");
        if (hint.length() == 0) {
            str = "";
        } else {
            str = '\n' + hint;
        }
        throw j.c(i2, message + " at path: " + this.f6984b.a() + str, v());
    }

    public final Void s(byte b2) {
        r(this, "Expected " + (b2 == 1 ? "quotation mark '\"'" : b2 == 4 ? "comma ','" : b2 == 5 ? "semicolon ':'" : b2 == 6 ? "start of the object '{'" : b2 == 7 ? "end of the object '}'" : b2 == 8 ? "start of the array '['" : b2 == 9 ? "end of the array ']'" : "valid token") + ", but had '" + ((this.f6983a == v().length() || this.f6983a <= 0) ? "EOF" : String.valueOf(v().charAt(this.f6983a - 1))) + "' instead", this.f6983a - 1, null, 4, null);
        throw new H0.c();
    }

    public final void t(String key) {
        kotlin.jvm.internal.n.g(key, "key");
        q("Encountered an unknown key '" + key + '\'', f1.o.J(C(0, this.f6983a), key, 0, false, 6, null), "Use 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.");
        throw new H0.c();
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) v()) + "', currentPosition=" + this.f6983a + ')';
    }

    public final int u(CharSequence charSequence, int i2) {
        char cCharAt = charSequence.charAt(i2);
        if ('0' <= cCharAt && cCharAt < ':') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt < 'g') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt < 'G') {
            return cCharAt - '7';
        }
        r(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, null, 6, null);
        throw new H0.c();
    }

    public abstract CharSequence v();

    public final boolean w(char c2) {
        return !(c2 == '}' || c2 == ']' || c2 == ':' || c2 == ',');
    }

    public final byte x() {
        CharSequence charSequenceV = v();
        int i2 = this.f6983a;
        while (true) {
            int iZ = z(i2);
            if (iZ == -1) {
                this.f6983a = iZ;
                return (byte) 10;
            }
            char cCharAt = charSequenceV.charAt(iZ);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.f6983a = iZ;
                return b.a(cCharAt);
            }
            i2 = iZ + 1;
        }
    }

    public final String y(boolean z2) {
        String strJ;
        byte bX = x();
        if (z2) {
            if (bX != 1 && bX != 0) {
                return null;
            }
            strJ = l();
        } else {
            if (bX != 1) {
                return null;
            }
            strJ = j();
        }
        this.f6985c = strJ;
        return strJ;
    }

    public abstract int z(int i2);
}
