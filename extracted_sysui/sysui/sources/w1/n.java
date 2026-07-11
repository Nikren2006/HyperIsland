package w1;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import s1.e;

/* JADX INFO: loaded from: classes2.dex */
public class n extends t1.a implements v1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v1.a f6998a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f6999b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w1.a f7000c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x1.b f7001d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final v1.b f7003f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g f7004g;

    public /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7005a;

        static {
            int[] iArr = new int[p.values().length];
            iArr[p.LIST.ordinal()] = 1;
            iArr[p.MAP.ordinal()] = 2;
            iArr[p.POLY_OBJ.ordinal()] = 3;
            iArr[p.OBJ.ordinal()] = 4;
            f7005a = iArr;
        }
    }

    public n(v1.a json, p mode, w1.a lexer, s1.c descriptor) {
        kotlin.jvm.internal.n.g(json, "json");
        kotlin.jvm.internal.n.g(mode, "mode");
        kotlin.jvm.internal.n.g(lexer, "lexer");
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        this.f6998a = json;
        this.f6999b = mode;
        this.f7000c = lexer;
        this.f7001d = json.c();
        this.f7002e = -1;
        v1.b bVarB = json.b();
        this.f7003f = bVarB;
        this.f7004g = bVarB.d() ? null : new g(descriptor);
    }

    @Override // t1.d
    public t1.b a(s1.c descriptor) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        p pVarB = q.b(this.f6998a, descriptor);
        this.f7000c.f6984b.c(descriptor);
        this.f7000c.i(pVarB.f7012a);
        l();
        int i2 = a.f7005a[pVarB.ordinal()];
        if (i2 != 1 && i2 != 2 && i2 != 3 && this.f6999b == pVarB && this.f6998a.b().d()) {
            return this;
        }
        n nVar = new n(this.f6998a, pVarB, this.f7000c, descriptor);
        return nVar;
    }

    @Override // t1.b
    public int b(s1.c descriptor) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        int i2 = a.f7005a[this.f6999b.ordinal()];
        int iN = i2 != 2 ? i2 != 4 ? n() : p(descriptor) : o();
        if (this.f6999b != p.MAP) {
            this.f7000c.f6984b.g(iN);
        }
        return iN;
    }

    @Override // t1.a, t1.d
    public float f() {
        w1.a aVar = this.f7000c;
        String strL = aVar.l();
        try {
            float f2 = Float.parseFloat(strL);
            if (this.f6998a.b().a() || !(Float.isInfinite(f2) || Float.isNaN(f2))) {
                return f2;
            }
            j.e(this.f7000c, Float.valueOf(f2));
            throw new H0.c();
        } catch (IllegalArgumentException unused) {
            w1.a.r(aVar, "Failed to parse type '" + TypedValues.Custom.S_FLOAT + "' for input '" + strL + '\'', 0, null, 6, null);
            throw new H0.c();
        }
    }

    @Override // t1.d
    public String g() {
        return this.f7003f.g() ? this.f7000c.m() : this.f7000c.j();
    }

    @Override // t1.b
    public void h(s1.c descriptor) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        if (this.f6998a.b().e() && descriptor.d() == 0) {
            s(descriptor);
        }
        this.f7000c.i(this.f6999b.f7013b);
        this.f7000c.f6984b.b();
    }

    @Override // t1.a, t1.b
    public Object i(s1.c descriptor, int i2, q1.a deserializer, Object obj) {
        kotlin.jvm.internal.n.g(descriptor, "descriptor");
        kotlin.jvm.internal.n.g(deserializer, "deserializer");
        boolean z2 = this.f6999b == p.MAP && (i2 & 1) == 0;
        if (z2) {
            this.f7000c.f6984b.d();
        }
        Object objI = super.i(descriptor, i2, deserializer, obj);
        if (z2) {
            this.f7000c.f6984b.f(objI);
        }
        return objI;
    }

    @Override // t1.a
    public Object j(q1.a deserializer) {
        kotlin.jvm.internal.n.g(deserializer, "deserializer");
        try {
            return m.a(this, deserializer);
        } catch (q1.c e2) {
            throw new q1.c(e2.getMessage() + " at path: " + this.f7000c.f6984b.a(), e2);
        }
    }

    public final void l() {
        if (this.f7000c.x() != 4) {
            return;
        }
        w1.a.r(this.f7000c, "Unexpected leading comma", 0, null, 6, null);
        throw new H0.c();
    }

    public final boolean m(s1.c cVar, int i2) {
        String strY;
        v1.a aVar = this.f6998a;
        s1.c cVarG = cVar.g(i2);
        if (!cVarG.a() && !this.f7000c.F()) {
            return true;
        }
        if (!kotlin.jvm.internal.n.c(cVarG.c(), e.b.f6479a) || (strY = this.f7000c.y(this.f7003f.g())) == null || k.c(cVarG, aVar, strY) != -3) {
            return false;
        }
        this.f7000c.j();
        return true;
    }

    public final int n() {
        boolean zE = this.f7000c.E();
        if (!this.f7000c.f()) {
            if (!zE) {
                return -1;
            }
            w1.a.r(this.f7000c, "Unexpected trailing comma", 0, null, 6, null);
            throw new H0.c();
        }
        int i2 = this.f7002e;
        if (i2 != -1 && !zE) {
            w1.a.r(this.f7000c, "Expected end of the array or comma", 0, null, 6, null);
            throw new H0.c();
        }
        int i3 = i2 + 1;
        this.f7002e = i3;
        return i3;
    }

    public final int o() {
        int i2 = this.f7002e;
        boolean zE = false;
        boolean z2 = i2 % 2 != 0;
        if (!z2) {
            this.f7000c.i(':');
        } else if (i2 != -1) {
            zE = this.f7000c.E();
        }
        if (!this.f7000c.f()) {
            if (!zE) {
                return -1;
            }
            w1.a.r(this.f7000c, "Expected '}', but had ',' instead", 0, null, 6, null);
            throw new H0.c();
        }
        if (z2) {
            if (this.f7002e == -1) {
                w1.a aVar = this.f7000c;
                int i3 = aVar.f6983a;
                if (zE) {
                    w1.a.r(aVar, "Unexpected trailing comma", i3, null, 4, null);
                    throw new H0.c();
                }
            } else {
                w1.a aVar2 = this.f7000c;
                int i4 = aVar2.f6983a;
                if (!zE) {
                    w1.a.r(aVar2, "Expected comma after the key-value pair", i4, null, 4, null);
                    throw new H0.c();
                }
            }
        }
        int i5 = this.f7002e + 1;
        this.f7002e = i5;
        return i5;
    }

    public final int p(s1.c cVar) {
        boolean zE;
        boolean zE2 = this.f7000c.E();
        while (this.f7000c.f()) {
            String strQ = q();
            this.f7000c.i(':');
            int iC = k.c(cVar, this.f6998a, strQ);
            boolean z2 = false;
            if (iC == -3) {
                z2 = true;
                zE = false;
            } else {
                if (!this.f7003f.c() || !m(cVar, iC)) {
                    g gVar = this.f7004g;
                    if (gVar != null) {
                        gVar.b(iC);
                    }
                    return iC;
                }
                zE = this.f7000c.E();
            }
            zE2 = z2 ? r(strQ) : zE;
        }
        if (zE2) {
            w1.a.r(this.f7000c, "Unexpected trailing comma", 0, null, 6, null);
            throw new H0.c();
        }
        g gVar2 = this.f7004g;
        if (gVar2 != null) {
            return gVar2.c();
        }
        return -1;
    }

    public final String q() {
        return this.f7003f.g() ? this.f7000c.m() : this.f7000c.g();
    }

    public final boolean r(String str) {
        if (this.f7003f.e()) {
            this.f7000c.A(this.f7003f.g());
        } else {
            this.f7000c.t(str);
        }
        return this.f7000c.E();
    }

    public final void s(s1.c cVar) {
        while (b(cVar) != -1) {
        }
    }
}
