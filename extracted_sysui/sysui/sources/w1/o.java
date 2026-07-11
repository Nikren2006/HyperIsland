package w1;

/* JADX INFO: loaded from: classes2.dex */
public final class o extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7006e;

    public o(String source) {
        kotlin.jvm.internal.n.g(source, "source");
        this.f7006e = source;
    }

    @Override // w1.a
    public int B() {
        char cCharAt;
        int i2 = this.f6983a;
        if (i2 == -1) {
            return i2;
        }
        while (i2 < v().length() && ((cCharAt = v().charAt(i2)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
            i2++;
        }
        this.f6983a = i2;
        return i2;
    }

    @Override // w1.a
    public boolean E() {
        int iB = B();
        if (iB == v().length() || iB == -1 || v().charAt(iB) != ',') {
            return false;
        }
        this.f6983a++;
        return true;
    }

    @Override // w1.a
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public String v() {
        return this.f7006e;
    }

    @Override // w1.a
    public boolean f() {
        int i2 = this.f6983a;
        if (i2 == -1) {
            return false;
        }
        while (i2 < v().length()) {
            char cCharAt = v().charAt(i2);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.f6983a = i2;
                return w(cCharAt);
            }
            i2++;
        }
        this.f6983a = i2;
        return false;
    }

    @Override // w1.a
    public String g() {
        i('\"');
        int i2 = this.f6983a;
        int iD = f1.o.D(v(), '\"', i2, false, 4, null);
        if (iD == -1) {
            s((byte) 1);
            throw new H0.c();
        }
        for (int i3 = i2; i3 < iD; i3++) {
            if (v().charAt(i3) == '\\') {
                return k(v(), this.f6983a, i3);
            }
        }
        this.f6983a = iD + 1;
        String strSubstring = v().substring(i2, iD);
        kotlin.jvm.internal.n.f(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @Override // w1.a
    public byte h() {
        byte bA;
        String strV = v();
        do {
            int i2 = this.f6983a;
            if (i2 == -1 || i2 >= strV.length()) {
                return (byte) 10;
            }
            int i3 = this.f6983a;
            this.f6983a = i3 + 1;
            bA = b.a(strV.charAt(i3));
        } while (bA == 3);
        return bA;
    }

    @Override // w1.a
    public void i(char c2) {
        if (this.f6983a == -1) {
            G(c2);
        }
        String strV = v();
        while (this.f6983a < strV.length()) {
            int i2 = this.f6983a;
            this.f6983a = i2 + 1;
            char cCharAt = strV.charAt(i2);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                if (cCharAt == c2) {
                    return;
                } else {
                    G(c2);
                }
            }
        }
        G(c2);
    }

    @Override // w1.a
    public int z(int i2) {
        if (i2 < v().length()) {
            return i2;
        }
        return -1;
    }
}
