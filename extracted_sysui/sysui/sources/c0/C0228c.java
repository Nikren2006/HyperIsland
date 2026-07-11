package c0;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;

/* JADX INFO: renamed from: c0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0228c implements Closeable, Flushable {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f1339j = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String[] f1340k = new String[128];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String[] f1341l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Writer f1342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f1343b = new int[32];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1344c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1345d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1347f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1349h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1350i;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f1340k[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f1340k;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        f1341l = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public C0228c(Writer writer) {
        D(6);
        this.f1346e = MethodCodeHelper.IDENTITY_INFO_SEPARATOR;
        this.f1350i = true;
        Objects.requireNonNull(writer, "out == null");
        this.f1342a = writer;
    }

    public static boolean w(Class cls) {
        return cls == Integer.class || cls == Long.class || cls == Double.class || cls == Float.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class;
    }

    public C0228c A() throws IOException {
        if (this.f1349h != null) {
            if (!this.f1350i) {
                this.f1349h = null;
                return this;
            }
            Q();
        }
        c();
        this.f1342a.write("null");
        return this;
    }

    public final C0228c B(int i2, char c2) throws IOException {
        c();
        D(i2);
        this.f1342a.write(c2);
        return this;
    }

    public final int C() {
        int i2 = this.f1344c;
        if (i2 != 0) {
            return this.f1343b[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void D(int i2) {
        int i3 = this.f1344c;
        int[] iArr = this.f1343b;
        if (i3 == iArr.length) {
            this.f1343b = Arrays.copyOf(iArr, i3 * 2);
        }
        int[] iArr2 = this.f1343b;
        int i4 = this.f1344c;
        this.f1344c = i4 + 1;
        iArr2[i4] = i2;
    }

    public final void E(int i2) {
        this.f1343b[this.f1344c - 1] = i2;
    }

    public final void F(boolean z2) {
        this.f1348g = z2;
    }

    public final void G(String str) {
        if (str.length() == 0) {
            this.f1345d = null;
            this.f1346e = MethodCodeHelper.IDENTITY_INFO_SEPARATOR;
        } else {
            this.f1345d = str;
            this.f1346e = ": ";
        }
    }

    public final void H(boolean z2) {
        this.f1347f = z2;
    }

    public final void I(boolean z2) {
        this.f1350i = z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void J(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.f1348g
            if (r0 == 0) goto L7
            java.lang.String[] r0 = c0.C0228c.f1341l
            goto L9
        L7:
            java.lang.String[] r0 = c0.C0228c.f1340k
        L9:
            java.io.Writer r1 = r8.f1342a
            r2 = 34
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.f1342a
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.f1342a
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.f1342a
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r8 = r8.f1342a
            r8.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c0.C0228c.J(java.lang.String):void");
    }

    public C0228c K(double d2) throws IOException {
        Q();
        if (this.f1347f || !(Double.isNaN(d2) || Double.isInfinite(d2))) {
            c();
            this.f1342a.append((CharSequence) Double.toString(d2));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
    }

    public C0228c L(long j2) throws IOException {
        Q();
        c();
        this.f1342a.write(Long.toString(j2));
        return this;
    }

    public C0228c M(Boolean bool) throws IOException {
        if (bool == null) {
            return A();
        }
        Q();
        c();
        this.f1342a.write(bool.booleanValue() ? com.xiaomi.onetrack.util.a.f3424i : "false");
        return this;
    }

    public C0228c N(Number number) throws IOException {
        if (number == null) {
            return A();
        }
        Q();
        String string = number.toString();
        if (!string.equals("-Infinity") && !string.equals("Infinity") && !string.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (!w(cls) && !f1339j.matcher(string).matches()) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + string);
            }
        } else if (!this.f1347f) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + string);
        }
        c();
        this.f1342a.append((CharSequence) string);
        return this;
    }

    public C0228c O(String str) throws IOException {
        if (str == null) {
            return A();
        }
        Q();
        c();
        J(str);
        return this;
    }

    public C0228c P(boolean z2) throws IOException {
        Q();
        c();
        this.f1342a.write(z2 ? com.xiaomi.onetrack.util.a.f3424i : "false");
        return this;
    }

    public final void Q() throws IOException {
        if (this.f1349h != null) {
            a();
            J(this.f1349h);
            this.f1349h = null;
        }
    }

    public final void a() throws IOException {
        int iC = C();
        if (iC == 5) {
            this.f1342a.write(44);
        } else if (iC != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        z();
        E(4);
    }

    public final void c() throws IOException {
        int iC = C();
        if (iC == 1) {
            E(2);
            z();
            return;
        }
        if (iC == 2) {
            this.f1342a.append(',');
            z();
        } else {
            if (iC == 4) {
                this.f1342a.append((CharSequence) this.f1346e);
                E(5);
                return;
            }
            if (iC != 6) {
                if (iC != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f1347f) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            E(7);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1342a.close();
        int i2 = this.f1344c;
        if (i2 > 1 || (i2 == 1 && this.f1343b[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f1344c = 0;
    }

    public C0228c d() throws IOException {
        Q();
        return B(1, '[');
    }

    public C0228c e() throws IOException {
        Q();
        return B(3, '{');
    }

    public final C0228c f(int i2, int i3, char c2) throws IOException {
        int iC = C();
        if (iC != i3 && iC != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f1349h != null) {
            throw new IllegalStateException("Dangling name: " + this.f1349h);
        }
        this.f1344c--;
        if (iC == i3) {
            z();
        }
        this.f1342a.write(c2);
        return this;
    }

    public void flush() throws IOException {
        if (this.f1344c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f1342a.flush();
    }

    public C0228c l() {
        return f(1, 2, ']');
    }

    public C0228c n() {
        return f(3, 5, '}');
    }

    public final boolean r() {
        return this.f1350i;
    }

    public final boolean t() {
        return this.f1348g;
    }

    public boolean u() {
        return this.f1347f;
    }

    public C0228c x(String str) {
        Objects.requireNonNull(str, "name == null");
        if (this.f1349h != null) {
            throw new IllegalStateException();
        }
        if (this.f1344c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f1349h = str;
        return this;
    }

    public final void z() throws IOException {
        if (this.f1345d == null) {
            return;
        }
        this.f1342a.write(10);
        int i2 = this.f1344c;
        for (int i3 = 1; i3 < i2; i3++) {
            this.f1342a.write(this.f1345d);
        }
    }
}
