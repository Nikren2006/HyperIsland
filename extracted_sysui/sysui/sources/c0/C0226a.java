package c0;

import W.f;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: c0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0226a implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Reader f1313a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f1321i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1322j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f1323k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f1324l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f1326n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f1327o;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1314b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char[] f1315c = new char[1024];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1316d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1317e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1318f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1319g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1320h = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1325m = 1;

    /* JADX INFO: renamed from: c0.a$a, reason: collision with other inner class name */
    public class C0047a extends f {
        @Override // W.f
        public void a(C0226a c0226a) throws IOException {
            int iF = c0226a.f1320h;
            if (iF == 0) {
                iF = c0226a.f();
            }
            if (iF == 13) {
                c0226a.f1320h = 9;
                return;
            }
            if (iF == 12) {
                c0226a.f1320h = 8;
                return;
            }
            if (iF == 14) {
                c0226a.f1320h = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + c0226a.M() + c0226a.B());
        }
    }

    static {
        f.f797a = new C0047a();
    }

    public C0226a(Reader reader) {
        int[] iArr = new int[32];
        this.f1324l = iArr;
        iArr[0] = 6;
        this.f1326n = new String[32];
        this.f1327o = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f1313a = reader;
    }

    public final boolean A(char c2) throws IOException {
        if (c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (c2 != '/' && c2 != '=') {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        d();
        return false;
    }

    public String B() {
        return " at line " + (this.f1318f + 1) + " column " + ((this.f1316d - this.f1319g) + 1) + " path " + t();
    }

    public boolean C() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 5) {
            this.f1320h = 0;
            int[] iArr = this.f1327o;
            int i2 = this.f1325m - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iF == 6) {
            this.f1320h = 0;
            int[] iArr2 = this.f1327o;
            int i3 = this.f1325m - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + M() + B());
    }

    public double D() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            this.f1320h = 0;
            int[] iArr = this.f1327o;
            int i2 = this.f1325m - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f1321i;
        }
        if (iF == 16) {
            this.f1323k = new String(this.f1315c, this.f1316d, this.f1322j);
            this.f1316d += this.f1322j;
        } else if (iF == 8 || iF == 9) {
            this.f1323k = J(iF == 8 ? '\'' : '\"');
        } else if (iF == 10) {
            this.f1323k = L();
        } else if (iF != 11) {
            throw new IllegalStateException("Expected a double but was " + M() + B());
        }
        this.f1320h = 11;
        double d2 = Double.parseDouble(this.f1323k);
        if (!this.f1314b && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new d("JSON forbids NaN and infinities: " + d2 + B());
        }
        this.f1323k = null;
        this.f1320h = 0;
        int[] iArr2 = this.f1327o;
        int i3 = this.f1325m - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return d2;
    }

    public int E() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            long j2 = this.f1321i;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.f1320h = 0;
                int[] iArr = this.f1327o;
                int i3 = this.f1325m - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.f1321i + B());
        }
        if (iF == 16) {
            this.f1323k = new String(this.f1315c, this.f1316d, this.f1322j);
            this.f1316d += this.f1322j;
        } else {
            if (iF != 8 && iF != 9 && iF != 10) {
                throw new IllegalStateException("Expected an int but was " + M() + B());
            }
            if (iF == 10) {
                this.f1323k = L();
            } else {
                this.f1323k = J(iF == 8 ? '\'' : '\"');
            }
            try {
                int i4 = Integer.parseInt(this.f1323k);
                this.f1320h = 0;
                int[] iArr2 = this.f1327o;
                int i5 = this.f1325m - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        }
        this.f1320h = 11;
        double d2 = Double.parseDouble(this.f1323k);
        int i6 = (int) d2;
        if (i6 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.f1323k + B());
        }
        this.f1323k = null;
        this.f1320h = 0;
        int[] iArr3 = this.f1327o;
        int i7 = this.f1325m - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public long F() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            this.f1320h = 0;
            int[] iArr = this.f1327o;
            int i2 = this.f1325m - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f1321i;
        }
        if (iF == 16) {
            this.f1323k = new String(this.f1315c, this.f1316d, this.f1322j);
            this.f1316d += this.f1322j;
        } else {
            if (iF != 8 && iF != 9 && iF != 10) {
                throw new IllegalStateException("Expected a long but was " + M() + B());
            }
            if (iF == 10) {
                this.f1323k = L();
            } else {
                this.f1323k = J(iF == 8 ? '\'' : '\"');
            }
            try {
                long j2 = Long.parseLong(this.f1323k);
                this.f1320h = 0;
                int[] iArr2 = this.f1327o;
                int i3 = this.f1325m - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return j2;
            } catch (NumberFormatException unused) {
            }
        }
        this.f1320h = 11;
        double d2 = Double.parseDouble(this.f1323k);
        long j3 = (long) d2;
        if (j3 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.f1323k + B());
        }
        this.f1323k = null;
        this.f1320h = 0;
        int[] iArr3 = this.f1327o;
        int i4 = this.f1325m - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j3;
    }

    public String G() throws IOException {
        String strJ;
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 14) {
            strJ = L();
        } else if (iF == 12) {
            strJ = J('\'');
        } else {
            if (iF != 13) {
                throw new IllegalStateException("Expected a name but was " + M() + B());
            }
            strJ = J('\"');
        }
        this.f1320h = 0;
        this.f1326n[this.f1325m - 1] = strJ;
        return strJ;
    }

    public final int H(boolean z2) throws IOException {
        char[] cArr = this.f1315c;
        int i2 = this.f1316d;
        int i3 = this.f1317e;
        while (true) {
            if (i2 == i3) {
                this.f1316d = i2;
                if (!r(1)) {
                    if (!z2) {
                        return -1;
                    }
                    throw new EOFException("End of input" + B());
                }
                i2 = this.f1316d;
                i3 = this.f1317e;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '\n') {
                this.f1318f++;
                this.f1319g = i4;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f1316d = i4;
                    if (i4 == i3) {
                        this.f1316d = i2;
                        boolean zR = r(2);
                        this.f1316d++;
                        if (!zR) {
                            return c2;
                        }
                    }
                    d();
                    int i5 = this.f1316d;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f1316d = i5 + 1;
                        if (!T("*/")) {
                            throw X("Unterminated comment");
                        }
                        i2 = this.f1316d + 2;
                        i3 = this.f1317e;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f1316d = i5 + 1;
                        U();
                        i2 = this.f1316d;
                        i3 = this.f1317e;
                    }
                } else {
                    if (c2 != '#') {
                        this.f1316d = i4;
                        return c2;
                    }
                    this.f1316d = i4;
                    d();
                    U();
                    i2 = this.f1316d;
                    i3 = this.f1317e;
                }
            }
            i2 = i4;
        }
    }

    public void I() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 7) {
            this.f1320h = 0;
            int[] iArr = this.f1327o;
            int i2 = this.f1325m - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + M() + B());
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r9.f1316d = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String J(char r10) throws java.io.IOException {
        /*
            r9 = this;
            char[] r0 = r9.f1315c
            r1 = 0
        L3:
            int r2 = r9.f1316d
            int r3 = r9.f1317e
        L7:
            r4 = r3
            r3 = r2
        L9:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L5a
            int r7 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r10) goto L28
            r9.f1316d = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L20
            java.lang.String r9 = new java.lang.String
            r9.<init>(r0, r3, r7)
            return r9
        L20:
            r1.append(r0, r3, r7)
            java.lang.String r9 = r1.toString()
            return r9
        L28:
            r8 = 92
            if (r2 != r8) goto L4d
            r9.f1316d = r7
            int r7 = r7 - r3
            int r2 = r7 + (-1)
            if (r1 != 0) goto L3e
            int r7 = r7 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r7, r5)
            r1.<init>(r4)
        L3e:
            r1.append(r0, r3, r2)
            char r2 = r9.Q()
            r1.append(r2)
            int r2 = r9.f1316d
            int r3 = r9.f1317e
            goto L7
        L4d:
            r5 = 10
            if (r2 != r5) goto L58
            int r2 = r9.f1318f
            int r2 = r2 + r6
            r9.f1318f = r2
            r9.f1319g = r7
        L58:
            r2 = r7
            goto L9
        L5a:
            if (r1 != 0) goto L6a
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L6a:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r9.f1316d = r2
            boolean r2 = r9.r(r6)
            if (r2 == 0) goto L78
            goto L3
        L78:
            java.lang.String r10 = "Unterminated string"
            java.io.IOException r9 = r9.X(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0226a.J(char):java.lang.String");
    }

    public String K() throws IOException {
        String str;
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 10) {
            str = L();
        } else if (iF == 8) {
            str = J('\'');
        } else if (iF == 9) {
            str = J('\"');
        } else if (iF == 11) {
            str = this.f1323k;
            this.f1323k = null;
        } else if (iF == 15) {
            str = Long.toString(this.f1321i);
        } else {
            if (iF != 16) {
                throw new IllegalStateException("Expected a string but was " + M() + B());
            }
            str = new String(this.f1315c, this.f1316d, this.f1322j);
            this.f1316d += this.f1322j;
        }
        this.f1320h = 0;
        int[] iArr = this.f1327o;
        int i2 = this.f1325m - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        d();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String L() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r6.f1316d
            int r4 = r3 + r2
            int r5 = r6.f1317e
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f1315c
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5c
            r4 = 10
            if (r3 == r4) goto L5c
            r4 = 12
            if (r3 == r4) goto L5c
            r4 = 13
            if (r3 == r4) goto L5c
            r4 = 32
            if (r3 == r4) goto L5c
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5c
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5c
            r4 = 58
            if (r3 == r4) goto L5c
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5c;
                case 92: goto L4a;
                case 93: goto L5c;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r6.d()
            goto L5c
        L4e:
            char[] r3 = r6.f1315c
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.r(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r1 = r2
            goto L7e
        L5e:
            if (r0 != 0) goto L6b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L6b:
            char[] r3 = r6.f1315c
            int r4 = r6.f1316d
            r0.append(r3, r4, r2)
            int r3 = r6.f1316d
            int r3 = r3 + r2
            r6.f1316d = r3
            r2 = 1
            boolean r2 = r6.r(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r0 != 0) goto L8a
            java.lang.String r0 = new java.lang.String
            char[] r2 = r6.f1315c
            int r3 = r6.f1316d
            r0.<init>(r2, r3, r1)
            goto L95
        L8a:
            char[] r2 = r6.f1315c
            int r3 = r6.f1316d
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L95:
            int r2 = r6.f1316d
            int r2 = r2 + r1
            r6.f1316d = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0226a.L():java.lang.String");
    }

    public EnumC0227b M() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        switch (iF) {
            case 1:
                return EnumC0227b.BEGIN_OBJECT;
            case 2:
                return EnumC0227b.END_OBJECT;
            case 3:
                return EnumC0227b.BEGIN_ARRAY;
            case 4:
                return EnumC0227b.END_ARRAY;
            case 5:
            case 6:
                return EnumC0227b.BOOLEAN;
            case 7:
                return EnumC0227b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return EnumC0227b.STRING;
            case 12:
            case 13:
            case 14:
                return EnumC0227b.NAME;
            case 15:
            case 16:
                return EnumC0227b.NUMBER;
            case 17:
                return EnumC0227b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final int N() {
        String str;
        String str2;
        int i2;
        char c2 = this.f1315c[this.f1316d];
        if (c2 == 't' || c2 == 'T') {
            str = com.xiaomi.onetrack.util.a.f3424i;
            str2 = "TRUE";
            i2 = 5;
        } else if (c2 == 'f' || c2 == 'F') {
            str = "false";
            str2 = "FALSE";
            i2 = 6;
        } else {
            if (c2 != 'n' && c2 != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i2 = 7;
        }
        int length = str.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f1316d + i3 >= this.f1317e && !r(i3 + 1)) {
                return 0;
            }
            char c3 = this.f1315c[this.f1316d + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f1316d + length < this.f1317e || r(length + 1)) && A(this.f1315c[this.f1316d + length])) {
            return 0;
        }
        this.f1316d += length;
        this.f1320h = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0091, code lost:
    
        if (A(r14) != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0093, code lost:
    
        if (r9 != 2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0095, code lost:
    
        if (r10 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009b, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009d, code lost:
    
        if (r13 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a3, code lost:
    
        if (r11 != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a5, code lost:
    
        if (r13 != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a7, code lost:
    
        if (r13 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00aa, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ab, code lost:
    
        r18.f1321i = r11;
        r18.f1316d += r8;
        r18.f1320h = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b7, code lost:
    
        if (r9 == 2) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ba, code lost:
    
        if (r9 == 4) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00bd, code lost:
    
        if (r9 != 7) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c2, code lost:
    
        r18.f1322j = r8;
        r18.f1320h = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c8, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00c9, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int O() {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0226a.O():int");
    }

    public final void P(int i2) {
        int i3 = this.f1325m;
        int[] iArr = this.f1324l;
        if (i3 == iArr.length) {
            int i4 = i3 * 2;
            this.f1324l = Arrays.copyOf(iArr, i4);
            this.f1327o = Arrays.copyOf(this.f1327o, i4);
            this.f1326n = (String[]) Arrays.copyOf(this.f1326n, i4);
        }
        int[] iArr2 = this.f1324l;
        int i5 = this.f1325m;
        this.f1325m = i5 + 1;
        iArr2[i5] = i2;
    }

    public final char Q() throws IOException {
        int i2;
        if (this.f1316d == this.f1317e && !r(1)) {
            throw X("Unterminated escape sequence");
        }
        char[] cArr = this.f1315c;
        int i3 = this.f1316d;
        int i4 = i3 + 1;
        this.f1316d = i4;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.f1318f++;
            this.f1319g = i4;
        } else if (c2 != '\"' && c2 != '\'' && c2 != '/' && c2 != '\\') {
            if (c2 == 'b') {
                return '\b';
            }
            if (c2 == 'f') {
                return '\f';
            }
            if (c2 == 'n') {
                return '\n';
            }
            if (c2 == 'r') {
                return '\r';
            }
            if (c2 == 't') {
                return '\t';
            }
            if (c2 != 'u') {
                throw X("Invalid escape sequence");
            }
            if (i3 + 5 > this.f1317e && !r(4)) {
                throw X("Unterminated escape sequence");
            }
            int i5 = this.f1316d;
            int i6 = i5 + 4;
            char c3 = 0;
            while (i5 < i6) {
                char c4 = this.f1315c[i5];
                char c5 = (char) (c3 << 4);
                if (c4 >= '0' && c4 <= '9') {
                    i2 = c4 - '0';
                } else if (c4 >= 'a' && c4 <= 'f') {
                    i2 = c4 - 'W';
                } else {
                    if (c4 < 'A' || c4 > 'F') {
                        throw new NumberFormatException("\\u" + new String(this.f1315c, this.f1316d, 4));
                    }
                    i2 = c4 - '7';
                }
                c3 = (char) (c5 + i2);
                i5++;
            }
            this.f1316d += 4;
            return c3;
        }
        return c2;
    }

    public final void R(boolean z2) {
        this.f1314b = z2;
    }

    public final void S(char c2) throws IOException {
        char[] cArr = this.f1315c;
        do {
            int i2 = this.f1316d;
            int i3 = this.f1317e;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f1316d = i4;
                    return;
                }
                if (c3 == '\\') {
                    this.f1316d = i4;
                    Q();
                    i2 = this.f1316d;
                    i3 = this.f1317e;
                } else {
                    if (c3 == '\n') {
                        this.f1318f++;
                        this.f1319g = i4;
                    }
                    i2 = i4;
                }
            }
            this.f1316d = i2;
        } while (r(1));
        throw X("Unterminated string");
    }

    public final boolean T(String str) {
        int length = str.length();
        while (true) {
            if (this.f1316d + length > this.f1317e && !r(length)) {
                return false;
            }
            char[] cArr = this.f1315c;
            int i2 = this.f1316d;
            if (cArr[i2] != '\n') {
                for (int i3 = 0; i3 < length; i3++) {
                    if (this.f1315c[this.f1316d + i3] != str.charAt(i3)) {
                        break;
                    }
                }
                return true;
            }
            this.f1318f++;
            this.f1319g = i2 + 1;
            this.f1316d++;
        }
    }

    public final void U() {
        char c2;
        do {
            if (this.f1316d >= this.f1317e && !r(1)) {
                return;
            }
            char[] cArr = this.f1315c;
            int i2 = this.f1316d;
            int i3 = i2 + 1;
            this.f1316d = i3;
            c2 = cArr[i2];
            if (c2 == '\n') {
                this.f1318f++;
                this.f1319g = i3;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        d();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void V() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f1316d
            int r2 = r1 + r0
            int r3 = r4.f1317e
            if (r2 >= r3) goto L51
            char[] r2 = r4.f1315c
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.d()
        L4b:
            int r1 = r4.f1316d
            int r1 = r1 + r0
            r4.f1316d = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f1316d = r1
            r0 = 1
            boolean r0 = r4.r(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0226a.V():void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void W() throws IOException {
        int i2 = 0;
        do {
            int iF = this.f1320h;
            if (iF == 0) {
                iF = f();
            }
            switch (iF) {
                case 1:
                    P(3);
                    i2++;
                    this.f1320h = 0;
                    break;
                case 2:
                    if (i2 == 0) {
                        this.f1326n[this.f1325m - 1] = null;
                    }
                    this.f1325m--;
                    i2--;
                    this.f1320h = 0;
                    break;
                case 3:
                    P(1);
                    i2++;
                    this.f1320h = 0;
                    break;
                case 4:
                    this.f1325m--;
                    i2--;
                    this.f1320h = 0;
                    break;
                case 5:
                case 6:
                case 7:
                case 11:
                case 15:
                default:
                    this.f1320h = 0;
                    break;
                case 8:
                    S('\'');
                    this.f1320h = 0;
                    break;
                case 9:
                    S('\"');
                    this.f1320h = 0;
                    break;
                case 10:
                    V();
                    this.f1320h = 0;
                    break;
                case 12:
                    S('\'');
                    if (i2 == 0) {
                        this.f1326n[this.f1325m - 1] = "<skipped>";
                    }
                    this.f1320h = 0;
                    break;
                case 13:
                    S('\"');
                    if (i2 == 0) {
                        this.f1326n[this.f1325m - 1] = "<skipped>";
                    }
                    this.f1320h = 0;
                    break;
                case 14:
                    V();
                    if (i2 == 0) {
                        this.f1326n[this.f1325m - 1] = "<skipped>";
                    }
                    this.f1320h = 0;
                    break;
                case 16:
                    this.f1316d += this.f1322j;
                    this.f1320h = 0;
                    break;
                case 17:
                    break;
            }
            return;
        } while (i2 > 0);
        int[] iArr = this.f1327o;
        int i3 = this.f1325m - 1;
        iArr[i3] = iArr[i3] + 1;
    }

    public final IOException X(String str) throws d {
        throw new d(str + B());
    }

    public void a() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 3) {
            P(1);
            this.f1327o[this.f1325m - 1] = 0;
            this.f1320h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + M() + B());
        }
    }

    public void c() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 1) {
            P(3);
            this.f1320h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + M() + B());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1320h = 0;
        this.f1324l[0] = 8;
        this.f1325m = 1;
        this.f1313a.close();
    }

    public final void d() throws IOException {
        if (!this.f1314b) {
            throw X("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void e() throws IOException {
        H(true);
        int i2 = this.f1316d;
        this.f1316d = i2 - 1;
        if (i2 + 4 <= this.f1317e || r(5)) {
            int i3 = this.f1316d;
            char[] cArr = this.f1315c;
            if (cArr[i3] == ')' && cArr[i3 + 1] == ']' && cArr[i3 + 2] == '}' && cArr[i3 + 3] == '\'' && cArr[i3 + 4] == '\n') {
                this.f1316d = i3 + 5;
            }
        }
    }

    public int f() throws IOException {
        int iH;
        int[] iArr = this.f1324l;
        int i2 = this.f1325m;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iH2 = H(true);
            if (iH2 != 44) {
                if (iH2 != 59) {
                    if (iH2 != 93) {
                        throw X("Unterminated array");
                    }
                    this.f1320h = 4;
                    return 4;
                }
                d();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5 && (iH = H(true)) != 44) {
                    if (iH != 59) {
                        if (iH != 125) {
                            throw X("Unterminated object");
                        }
                        this.f1320h = 2;
                        return 2;
                    }
                    d();
                }
                int iH3 = H(true);
                if (iH3 == 34) {
                    this.f1320h = 13;
                    return 13;
                }
                if (iH3 == 39) {
                    d();
                    this.f1320h = 12;
                    return 12;
                }
                if (iH3 == 125) {
                    if (i3 == 5) {
                        throw X("Expected name");
                    }
                    this.f1320h = 2;
                    return 2;
                }
                d();
                this.f1316d--;
                if (!A((char) iH3)) {
                    throw X("Expected name");
                }
                this.f1320h = 14;
                return 14;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iH4 = H(true);
                if (iH4 != 58) {
                    if (iH4 != 61) {
                        throw X("Expected ':'");
                    }
                    d();
                    if (this.f1316d < this.f1317e || r(1)) {
                        char[] cArr = this.f1315c;
                        int i4 = this.f1316d;
                        if (cArr[i4] == '>') {
                            this.f1316d = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.f1314b) {
                    e();
                }
                this.f1324l[this.f1325m - 1] = 7;
            } else if (i3 == 7) {
                if (H(false) == -1) {
                    this.f1320h = 17;
                    return 17;
                }
                d();
                this.f1316d--;
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iH5 = H(true);
        if (iH5 == 34) {
            this.f1320h = 9;
            return 9;
        }
        if (iH5 == 39) {
            d();
            this.f1320h = 8;
            return 8;
        }
        if (iH5 != 44 && iH5 != 59) {
            if (iH5 == 91) {
                this.f1320h = 3;
                return 3;
            }
            if (iH5 != 93) {
                if (iH5 == 123) {
                    this.f1320h = 1;
                    return 1;
                }
                this.f1316d--;
                int iN = N();
                if (iN != 0) {
                    return iN;
                }
                int iO = O();
                if (iO != 0) {
                    return iO;
                }
                if (!A(this.f1315c[this.f1316d])) {
                    throw X("Expected value");
                }
                d();
                this.f1320h = 10;
                return 10;
            }
            if (i3 == 1) {
                this.f1320h = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw X("Unexpected value");
        }
        d();
        this.f1316d--;
        this.f1320h = 7;
        return 7;
    }

    public void l() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + M() + B());
        }
        int i2 = this.f1325m;
        this.f1325m = i2 - 1;
        int[] iArr = this.f1327o;
        int i3 = i2 - 2;
        iArr[i3] = iArr[i3] + 1;
        this.f1320h = 0;
    }

    public void n() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + M() + B());
        }
        int i2 = this.f1325m;
        int i3 = i2 - 1;
        this.f1325m = i3;
        this.f1326n[i3] = null;
        int[] iArr = this.f1327o;
        int i4 = i2 - 2;
        iArr[i4] = iArr[i4] + 1;
        this.f1320h = 0;
    }

    public final boolean r(int i2) throws IOException {
        int i3;
        int i4;
        char[] cArr = this.f1315c;
        int i5 = this.f1319g;
        int i6 = this.f1316d;
        this.f1319g = i5 - i6;
        int i7 = this.f1317e;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.f1317e = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.f1317e = 0;
        }
        this.f1316d = 0;
        do {
            Reader reader = this.f1313a;
            int i9 = this.f1317e;
            int i10 = reader.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.f1317e + i10;
            this.f1317e = i3;
            if (this.f1318f == 0 && (i4 = this.f1319g) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.f1316d++;
                this.f1319g = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    public String t() {
        return u(false);
    }

    public String toString() {
        return getClass().getSimpleName() + B();
    }

    public final String u(boolean z2) {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = 0;
        while (true) {
            int i3 = this.f1325m;
            if (i2 >= i3) {
                return sb.toString();
            }
            int i4 = this.f1324l[i2];
            if (i4 == 1 || i4 == 2) {
                int i5 = this.f1327o[i2];
                if (z2 && i5 > 0 && i2 == i3 - 1) {
                    i5--;
                }
                sb.append('[');
                sb.append(i5);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String str = this.f1326n[i2];
                if (str != null) {
                    sb.append(str);
                }
            }
            i2++;
        }
    }

    public String w() {
        return u(true);
    }

    public boolean x() throws IOException {
        int iF = this.f1320h;
        if (iF == 0) {
            iF = f();
        }
        return (iF == 2 || iF == 4 || iF == 17) ? false : true;
    }

    public final boolean z() {
        return this.f1314b;
    }
}
