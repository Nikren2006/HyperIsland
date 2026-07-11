package j1;

import H0.j;
import g1.AbstractC0383n;
import g1.C0379l;
import g1.S;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class z extends k1.b implements t, InterfaceC0418f, k1.p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4802f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i1.a f4803g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object[] f4804h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4805i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f4806j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4807k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4808l;

    public static final class a implements S {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final z f4809a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f4810b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Object f4811c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final L0.d f4812d;

        public a(z zVar, long j2, Object obj, L0.d dVar) {
            this.f4809a = zVar;
            this.f4810b = j2;
            this.f4811c = obj;
            this.f4812d = dVar;
        }

        @Override // g1.S
        public void dispose() {
            this.f4809a.x(this);
        }
    }

    public /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4813a;

        static {
            int[] iArr = new int[i1.a.values().length];
            try {
                iArr[i1.a.SUSPEND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[i1.a.DROP_LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[i1.a.DROP_OLDEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f4813a = iArr;
        }
    }

    public static final class c extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4814a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4815b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f4816c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object f4817d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public /* synthetic */ Object f4818e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f4820g;

        public c(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4818e = obj;
            this.f4820g |= Integer.MIN_VALUE;
            return z.z(z.this, null, this);
        }
    }

    public z(int i2, int i3, i1.a aVar) {
        this.f4801e = i2;
        this.f4802f = i3;
        this.f4803g = aVar;
    }

    public static /* synthetic */ Object E(z zVar, Object obj, L0.d dVar) {
        Object objF;
        return (!zVar.b(obj) && (objF = zVar.F(obj, dVar)) == M0.c.c()) ? objF : H0.s.f314a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a A[Catch: all -> 0x0042, TryCatch #1 {all -> 0x0042, blocks: (B:15:0x003b, B:32:0x0092, B:34:0x009a, B:38:0x00ad, B:39:0x00b0, B:22:0x005d), top: B:48:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v4, types: [j1.g, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r5v1, types: [k1.b] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4, types: [j1.z, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [j1.g] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2, types: [k1.d] */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [j1.B, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v9, types: [j1.B] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00be -> B:16:0x003e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object z(j1.z r8, j1.InterfaceC0419g r9, L0.d r10) throws java.lang.Throwable {
        /*
            boolean r0 = r10 instanceof j1.z.c
            if (r0 == 0) goto L13
            r0 = r10
            j1.z$c r0 = (j1.z.c) r0
            int r1 = r0.f4820g
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4820g = r1
            goto L18
        L13:
            j1.z$c r0 = new j1.z$c
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.f4818e
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4820g
            r3 = 3
            r4 = 2
            if (r2 == 0) goto L77
            r8 = 1
            if (r2 == r8) goto L61
            if (r2 == r4) goto L4d
            if (r2 != r3) goto L45
            java.lang.Object r8 = r0.f4817d
            g1.l0 r8 = (g1.InterfaceC0380l0) r8
            java.lang.Object r9 = r0.f4816c
            j1.B r9 = (j1.B) r9
            java.lang.Object r2 = r0.f4815b
            j1.g r2 = (j1.InterfaceC0419g) r2
            java.lang.Object r5 = r0.f4814a
            j1.z r5 = (j1.z) r5
            H0.k.b(r10)     // Catch: java.lang.Throwable -> L42
        L3e:
            r10 = r2
            r2 = r8
            r8 = r5
            goto L8f
        L42:
            r8 = move-exception
            goto Lc4
        L45:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L4d:
            java.lang.Object r8 = r0.f4817d
            g1.l0 r8 = (g1.InterfaceC0380l0) r8
            java.lang.Object r9 = r0.f4816c
            j1.B r9 = (j1.B) r9
            java.lang.Object r2 = r0.f4815b
            j1.g r2 = (j1.InterfaceC0419g) r2
            java.lang.Object r5 = r0.f4814a
            j1.z r5 = (j1.z) r5
            H0.k.b(r10)     // Catch: java.lang.Throwable -> L42
            goto L92
        L61:
            java.lang.Object r8 = r0.f4816c
            r9 = r8
            j1.B r9 = (j1.B) r9
            java.lang.Object r8 = r0.f4815b
            j1.g r8 = (j1.InterfaceC0419g) r8
            java.lang.Object r2 = r0.f4814a
            j1.z r2 = (j1.z) r2
            H0.k.b(r10)     // Catch: java.lang.Throwable -> L74
            r10 = r8
            r8 = r2
            goto L83
        L74:
            r8 = move-exception
            r5 = r2
            goto Lc4
        L77:
            H0.k.b(r10)
            k1.d r10 = r8.g()
            j1.B r10 = (j1.B) r10
            r7 = r10
            r10 = r9
            r9 = r7
        L83:
            L0.g r2 = r0.getContext()     // Catch: java.lang.Throwable -> Lc1
            g1.l0$b r5 = g1.InterfaceC0380l0.f4430z     // Catch: java.lang.Throwable -> Lc1
            L0.g$b r2 = r2.get(r5)     // Catch: java.lang.Throwable -> Lc1
            g1.l0 r2 = (g1.InterfaceC0380l0) r2     // Catch: java.lang.Throwable -> Lc1
        L8f:
            r5 = r8
            r8 = r2
            r2 = r10
        L92:
            java.lang.Object r10 = r5.T(r9)     // Catch: java.lang.Throwable -> L42
            l1.F r6 = j1.A.f4637a     // Catch: java.lang.Throwable -> L42
            if (r10 != r6) goto Lab
            r0.f4814a = r5     // Catch: java.lang.Throwable -> L42
            r0.f4815b = r2     // Catch: java.lang.Throwable -> L42
            r0.f4816c = r9     // Catch: java.lang.Throwable -> L42
            r0.f4817d = r8     // Catch: java.lang.Throwable -> L42
            r0.f4820g = r4     // Catch: java.lang.Throwable -> L42
            java.lang.Object r10 = r5.w(r9, r0)     // Catch: java.lang.Throwable -> L42
            if (r10 != r1) goto L92
            return r1
        Lab:
            if (r8 == 0) goto Lb0
            g1.AbstractC0388p0.h(r8)     // Catch: java.lang.Throwable -> L42
        Lb0:
            r0.f4814a = r5     // Catch: java.lang.Throwable -> L42
            r0.f4815b = r2     // Catch: java.lang.Throwable -> L42
            r0.f4816c = r9     // Catch: java.lang.Throwable -> L42
            r0.f4817d = r8     // Catch: java.lang.Throwable -> L42
            r0.f4820g = r3     // Catch: java.lang.Throwable -> L42
            java.lang.Object r10 = r2.emit(r10, r0)     // Catch: java.lang.Throwable -> L42
            if (r10 != r1) goto L3e
            return r1
        Lc1:
            r10 = move-exception
            r5 = r8
            r8 = r10
        Lc4:
            r5.j(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.z.z(j1.z, j1.g, L0.d):java.lang.Object");
    }

    public final void A(long j2) {
        k1.d[] dVarArr;
        if (this.f4950b != 0 && (dVarArr = this.f4949a) != null) {
            for (k1.d dVar : dVarArr) {
                if (dVar != null) {
                    B b2 = (B) dVar;
                    long j3 = b2.f4638a;
                    if (j3 >= 0 && j3 < j2) {
                        b2.f4638a = j2;
                    }
                }
            }
        }
        this.f4806j = j2;
    }

    @Override // k1.b
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public B h() {
        return new B();
    }

    @Override // k1.b
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public B[] i(int i2) {
        return new B[i2];
    }

    public final void D() {
        Object[] objArr = this.f4804h;
        kotlin.jvm.internal.n.d(objArr);
        A.g(objArr, J(), null);
        this.f4807k--;
        long J2 = J() + 1;
        if (this.f4805i < J2) {
            this.f4805i = J2;
        }
        if (this.f4806j < J2) {
            A(J2);
        }
    }

    public final Object F(Object obj, L0.d dVar) {
        L0.d[] dVarArrH;
        a aVar;
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        L0.d[] dVarArrH2 = k1.c.f4953a;
        synchronized (this) {
            try {
                if (Q(obj)) {
                    j.a aVar2 = H0.j.f299a;
                    c0379l.resumeWith(H0.j.a(H0.s.f314a));
                    dVarArrH = H(dVarArrH2);
                    aVar = null;
                } else {
                    a aVar3 = new a(this, ((long) O()) + J(), obj, c0379l);
                    G(aVar3);
                    this.f4808l++;
                    if (this.f4802f == 0) {
                        dVarArrH2 = H(dVarArrH2);
                    }
                    dVarArrH = dVarArrH2;
                    aVar = aVar3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (aVar != null) {
            AbstractC0383n.a(c0379l, aVar);
        }
        for (L0.d dVar2 : dVarArrH) {
            if (dVar2 != null) {
                j.a aVar4 = H0.j.f299a;
                dVar2.resumeWith(H0.j.a(H0.s.f314a));
            }
        }
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    public final void G(Object obj) {
        int iO = O();
        Object[] objArrP = this.f4804h;
        if (objArrP == null) {
            objArrP = P(null, 0, 2);
        } else if (iO >= objArrP.length) {
            objArrP = P(objArrP, iO, objArrP.length * 2);
        }
        A.g(objArrP, J() + ((long) iO), obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [L0.d[]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public final L0.d[] H(L0.d[] dVarArr) {
        k1.d[] dVarArr2;
        B b2;
        L0.d dVar;
        int length = dVarArr.length;
        if (this.f4950b != 0 && (dVarArr2 = this.f4949a) != null) {
            int length2 = dVarArr2.length;
            int i2 = 0;
            dVarArr = dVarArr;
            while (i2 < length2) {
                k1.d dVar2 = dVarArr2[i2];
                if (dVar2 != null && (dVar = (b2 = (B) dVar2).f4639b) != null && S(b2) >= 0) {
                    int length3 = dVarArr.length;
                    dVarArr = dVarArr;
                    if (length >= length3) {
                        Object[] objArrCopyOf = Arrays.copyOf((Object[]) dVarArr, Math.max(2, dVarArr.length * 2));
                        kotlin.jvm.internal.n.f(objArrCopyOf, "copyOf(this, newSize)");
                        dVarArr = objArrCopyOf;
                    }
                    ((L0.d[]) dVarArr)[length] = dVar;
                    b2.f4639b = null;
                    length++;
                }
                i2++;
                dVarArr = dVarArr;
            }
        }
        return (L0.d[]) dVarArr;
    }

    public final long I() {
        return J() + ((long) this.f4807k);
    }

    public final long J() {
        return Math.min(this.f4806j, this.f4805i);
    }

    public final Object K() {
        Object[] objArr = this.f4804h;
        kotlin.jvm.internal.n.d(objArr);
        return A.f(objArr, (this.f4805i + ((long) N())) - 1);
    }

    public final Object L(long j2) {
        Object[] objArr = this.f4804h;
        kotlin.jvm.internal.n.d(objArr);
        Object objF = A.f(objArr, j2);
        return objF instanceof a ? ((a) objF).f4811c : objF;
    }

    public final long M() {
        return J() + ((long) this.f4807k) + ((long) this.f4808l);
    }

    public final int N() {
        return (int) ((J() + ((long) this.f4807k)) - this.f4805i);
    }

    public final int O() {
        return this.f4807k + this.f4808l;
    }

    public final Object[] P(Object[] objArr, int i2, int i3) {
        if (i3 <= 0) {
            throw new IllegalStateException("Buffer size overflow");
        }
        Object[] objArr2 = new Object[i3];
        this.f4804h = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long J2 = J();
        for (int i4 = 0; i4 < i2; i4++) {
            long j2 = ((long) i4) + J2;
            A.g(objArr2, j2, A.f(objArr, j2));
        }
        return objArr2;
    }

    public final boolean Q(Object obj) {
        if (k() == 0) {
            return R(obj);
        }
        if (this.f4807k >= this.f4802f && this.f4806j <= this.f4805i) {
            int i2 = b.f4813a[this.f4803g.ordinal()];
            if (i2 == 1) {
                return false;
            }
            if (i2 == 2) {
                return true;
            }
        }
        G(obj);
        int i3 = this.f4807k + 1;
        this.f4807k = i3;
        if (i3 > this.f4802f) {
            D();
        }
        if (N() > this.f4801e) {
            U(this.f4805i + 1, this.f4806j, I(), M());
        }
        return true;
    }

    public final boolean R(Object obj) {
        if (this.f4801e == 0) {
            return true;
        }
        G(obj);
        int i2 = this.f4807k + 1;
        this.f4807k = i2;
        if (i2 > this.f4801e) {
            D();
        }
        this.f4806j = J() + ((long) this.f4807k);
        return true;
    }

    public final long S(B b2) {
        long j2 = b2.f4638a;
        if (j2 < I()) {
            return j2;
        }
        if (this.f4802f <= 0 && j2 <= J() && this.f4808l != 0) {
            return j2;
        }
        return -1L;
    }

    public final Object T(B b2) {
        Object obj;
        L0.d[] dVarArrV = k1.c.f4953a;
        synchronized (this) {
            try {
                long jS = S(b2);
                if (jS < 0) {
                    obj = A.f4637a;
                } else {
                    long j2 = b2.f4638a;
                    Object objL = L(jS);
                    b2.f4638a = jS + 1;
                    dVarArrV = V(j2);
                    obj = objL;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (L0.d dVar : dVarArrV) {
            if (dVar != null) {
                j.a aVar = H0.j.f299a;
                dVar.resumeWith(H0.j.a(H0.s.f314a));
            }
        }
        return obj;
    }

    public final void U(long j2, long j3, long j4, long j5) {
        long jMin = Math.min(j3, j2);
        for (long J2 = J(); J2 < jMin; J2++) {
            Object[] objArr = this.f4804h;
            kotlin.jvm.internal.n.d(objArr);
            A.g(objArr, J2, null);
        }
        this.f4805i = j2;
        this.f4806j = j3;
        this.f4807k = (int) (j4 - jMin);
        this.f4808l = (int) (j5 - j4);
    }

    public final L0.d[] V(long j2) {
        long j3;
        long j4;
        long j5;
        k1.d[] dVarArr;
        if (j2 > this.f4806j) {
            return k1.c.f4953a;
        }
        long J2 = J();
        long j6 = ((long) this.f4807k) + J2;
        if (this.f4802f == 0 && this.f4808l > 0) {
            j6++;
        }
        if (this.f4950b != 0 && (dVarArr = this.f4949a) != null) {
            for (k1.d dVar : dVarArr) {
                if (dVar != null) {
                    long j7 = ((B) dVar).f4638a;
                    if (j7 >= 0 && j7 < j6) {
                        j6 = j7;
                    }
                }
            }
        }
        if (j6 <= this.f4806j) {
            return k1.c.f4953a;
        }
        long jI = I();
        int iMin = k() > 0 ? Math.min(this.f4808l, this.f4802f - ((int) (jI - j6))) : this.f4808l;
        L0.d[] dVarArr2 = k1.c.f4953a;
        long j8 = ((long) this.f4808l) + jI;
        if (iMin > 0) {
            dVarArr2 = new L0.d[iMin];
            Object[] objArr = this.f4804h;
            kotlin.jvm.internal.n.d(objArr);
            long j9 = jI;
            int i2 = 0;
            while (true) {
                if (jI >= j8) {
                    j3 = j6;
                    j4 = j8;
                    break;
                }
                Object objF = A.f(objArr, jI);
                j3 = j6;
                l1.F f2 = A.f4637a;
                if (objF != f2) {
                    kotlin.jvm.internal.n.e(objF, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    a aVar = (a) objF;
                    int i3 = i2 + 1;
                    j4 = j8;
                    dVarArr2[i2] = aVar.f4812d;
                    A.g(objArr, jI, f2);
                    A.g(objArr, j9, aVar.f4811c);
                    j5 = 1;
                    j9++;
                    if (i3 >= iMin) {
                        break;
                    }
                    i2 = i3;
                } else {
                    j4 = j8;
                    j5 = 1;
                }
                jI += j5;
                j6 = j3;
                j8 = j4;
            }
            jI = j9;
        } else {
            j3 = j6;
            j4 = j8;
        }
        int i4 = (int) (jI - J2);
        long j10 = k() == 0 ? jI : j3;
        long jMax = Math.max(this.f4805i, jI - ((long) Math.min(this.f4801e, i4)));
        if (this.f4802f == 0 && jMax < j4) {
            Object[] objArr2 = this.f4804h;
            kotlin.jvm.internal.n.d(objArr2);
            if (kotlin.jvm.internal.n.c(A.f(objArr2, jMax), A.f4637a)) {
                jI++;
                jMax++;
            }
        }
        U(jMax, j10, jI, j4);
        y();
        return !(dVarArr2.length == 0) ? H(dVarArr2) : dVarArr2;
    }

    public final long W() {
        long j2 = this.f4805i;
        if (j2 < this.f4806j) {
            this.f4806j = j2;
        }
        return j2;
    }

    @Override // k1.p
    public InterfaceC0418f a(L0.g gVar, int i2, i1.a aVar) {
        return A.e(this, gVar, i2, aVar);
    }

    @Override // j1.t
    public boolean b(Object obj) {
        int i2;
        boolean z2;
        L0.d[] dVarArrH = k1.c.f4953a;
        synchronized (this) {
            if (Q(obj)) {
                dVarArrH = H(dVarArrH);
                z2 = true;
            } else {
                z2 = false;
            }
        }
        for (L0.d dVar : dVarArrH) {
            if (dVar != null) {
                j.a aVar = H0.j.f299a;
                dVar.resumeWith(H0.j.a(H0.s.f314a));
            }
        }
        return z2;
    }

    @Override // j1.y, j1.InterfaceC0418f
    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        return z(this, interfaceC0419g, dVar);
    }

    @Override // j1.t
    public void d() {
        synchronized (this) {
            U(I(), this.f4806j, I(), M());
            H0.s sVar = H0.s.f314a;
        }
    }

    @Override // j1.t, j1.InterfaceC0419g
    public Object emit(Object obj, L0.d dVar) {
        return E(this, obj, dVar);
    }

    public final Object w(B b2, L0.d dVar) {
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        synchronized (this) {
            try {
                if (S(b2) < 0) {
                    b2.f4639b = c0379l;
                } else {
                    j.a aVar = H0.j.f299a;
                    c0379l.resumeWith(H0.j.a(H0.s.f314a));
                }
                H0.s sVar = H0.s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    public final void x(a aVar) {
        synchronized (this) {
            if (aVar.f4810b < J()) {
                return;
            }
            Object[] objArr = this.f4804h;
            kotlin.jvm.internal.n.d(objArr);
            if (A.f(objArr, aVar.f4810b) != aVar) {
                return;
            }
            A.g(objArr, aVar.f4810b, A.f4637a);
            y();
            H0.s sVar = H0.s.f314a;
        }
    }

    public final void y() {
        if (this.f4802f != 0 || this.f4808l > 1) {
            Object[] objArr = this.f4804h;
            kotlin.jvm.internal.n.d(objArr);
            while (this.f4808l > 0 && A.f(objArr, (J() + ((long) O())) - 1) == A.f4637a) {
                this.f4808l--;
                A.g(objArr, J() + ((long) O()), null);
            }
        }
    }
}
