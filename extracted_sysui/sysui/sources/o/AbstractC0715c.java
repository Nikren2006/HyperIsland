package o;

import D1.f;
import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: renamed from: o.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0715c implements Closeable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String[] f6290g = new String[128];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6291a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f6292b = new int[32];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String[] f6293c = new String[32];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f6294d = new int[32];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6295e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6296f;

    /* JADX INFO: renamed from: o.c$a */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String[] f6297a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f f6298b;

        public a(String[] strArr, f fVar) {
            this.f6297a = strArr;
            this.f6298b = fVar;
        }

        public static a a(String... strArr) {
            try {
                D1.d[] dVarArr = new D1.d[strArr.length];
                D1.a aVar = new D1.a();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    AbstractC0715c.F(aVar, strArr[i2]);
                    aVar.readByte();
                    dVarArr[i2] = aVar.w();
                }
                return new a((String[]) strArr.clone(), f.d(dVarArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    /* JADX INFO: renamed from: o.c$b */
    public enum b {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f6290g[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f6290g;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void F(D1.b r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = o.AbstractC0715c.f6290g
            r1 = 34
            r7.o(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        Ld:
            if (r3 >= r2) goto L36
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r6) goto L1c
            r5 = r0[r5]
            if (r5 != 0) goto L29
            goto L33
        L1c:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L23
            java.lang.String r5 = "\\u2028"
            goto L29
        L23:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L33
            java.lang.String r5 = "\\u2029"
        L29:
            if (r4 >= r3) goto L2e
            r7.k(r8, r4, r3)
        L2e:
            r7.i(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.k(r8, r4, r2)
        L3b:
            r7.o(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o.AbstractC0715c.F(D1.b, java.lang.String):void");
    }

    public static AbstractC0715c z(D1.c cVar) {
        return new C0717e(cVar);
    }

    public abstract b A();

    public final void B(int i2) {
        int i3 = this.f6291a;
        int[] iArr = this.f6292b;
        if (i3 == iArr.length) {
            if (i3 == 256) {
                throw new C0713a("Nesting too deep at " + l());
            }
            this.f6292b = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f6293c;
            this.f6293c = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f6294d;
            this.f6294d = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f6292b;
        int i4 = this.f6291a;
        this.f6291a = i4 + 1;
        iArr3[i4] = i2;
    }

    public abstract int C(a aVar);

    public abstract void D();

    public abstract void E();

    public final C0714b G(String str) throws C0714b {
        throw new C0714b(str + " at path " + l());
    }

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final String l() {
        return AbstractC0716d.a(this.f6291a, this.f6292b, this.f6293c, this.f6294d);
    }

    public abstract boolean n();

    public abstract boolean r();

    public abstract double t();

    public abstract int u();

    public abstract String w();

    public abstract String x();
}
