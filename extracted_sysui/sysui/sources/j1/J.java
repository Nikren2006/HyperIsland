package j1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public final class J extends k1.b implements u, InterfaceC0418f, k1.p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4668f = AtomicReferenceFieldUpdater.newUpdater(J.class, Object.class, "_state");
    private volatile Object _state;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4669e;

    public static final class a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4670a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4671b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f4672c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object f4673d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f4674e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public /* synthetic */ Object f4675f;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f4677h;

        public a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4675f = obj;
            this.f4677h |= Integer.MIN_VALUE;
            return J.this.collect(null, this);
        }
    }

    public J(Object obj) {
        this._state = obj;
    }

    @Override // k1.p
    public InterfaceC0418f a(L0.g gVar, int i2, i1.a aVar) {
        return K.d(this, gVar, i2, aVar);
    }

    @Override // j1.t
    public boolean b(Object obj) {
        setValue(obj);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:36:0x00a1, B:38:0x00a7], limit reached: 53 */
    /* JADX WARN: Path cross not found for [B:38:0x00a7, B:36:0x00a1], limit reached: 53 */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c A[Catch: all -> 0x0042, TryCatch #0 {all -> 0x0042, blocks: (B:15:0x003e, B:32:0x0094, B:34:0x009c, B:36:0x00a1, B:46:0x00c2, B:48:0x00c8, B:38:0x00a7, B:42:0x00ae, B:22:0x005f), top: B:53:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1 A[Catch: all -> 0x0042, TryCatch #0 {all -> 0x0042, blocks: (B:15:0x003e, B:32:0x0094, B:34:0x009c, B:36:0x00a1, B:46:0x00c2, B:48:0x00c8, B:38:0x00a7, B:42:0x00ae, B:22:0x005f), top: B:53:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c8 A[Catch: all -> 0x0042, TRY_LEAVE, TryCatch #0 {all -> 0x0042, blocks: (B:15:0x003e, B:32:0x0094, B:34:0x009c, B:36:0x00a1, B:46:0x00c2, B:48:0x00c8, B:38:0x00a7, B:42:0x00ae, B:22:0x005f), top: B:53:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [k1.d] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [j1.L, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v9, types: [j1.L] */
    /* JADX WARN: Type inference failed for: r7v1, types: [k1.b] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00c6 -> B:32:0x0094). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x00d8 -> B:32:0x0094). Please report as a decompilation issue!!! */
    @Override // j1.y, j1.InterfaceC0418f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object collect(j1.InterfaceC0419g r10, L0.d r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.J.collect(j1.g, L0.d):java.lang.Object");
    }

    @Override // j1.t
    public void d() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override // j1.t, j1.InterfaceC0419g
    public Object emit(Object obj, L0.d dVar) {
        setValue(obj);
        return H0.s.f314a;
    }

    @Override // j1.u, j1.I
    public Object getValue() {
        l1.F f2 = k1.s.f5012a;
        Object obj = f4668f.get(this);
        if (obj == f2) {
            return null;
        }
        return obj;
    }

    @Override // k1.b
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public L h() {
        return new L();
    }

    @Override // k1.b
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public L[] i(int i2) {
        return new L[i2];
    }

    public final boolean o(Object obj, Object obj2) {
        int i2;
        k1.d[] dVarArrL;
        synchronized (this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4668f;
            Object obj3 = atomicReferenceFieldUpdater.get(this);
            if (obj != null && !kotlin.jvm.internal.n.c(obj3, obj)) {
                return false;
            }
            if (kotlin.jvm.internal.n.c(obj3, obj2)) {
                return true;
            }
            atomicReferenceFieldUpdater.set(this, obj2);
            int i3 = this.f4669e;
            if ((i3 & 1) != 0) {
                this.f4669e = i3 + 2;
                return true;
            }
            int i4 = i3 + 1;
            this.f4669e = i4;
            k1.d[] dVarArrL2 = l();
            H0.s sVar = H0.s.f314a;
            while (true) {
                L[] lArr = (L[]) dVarArrL2;
                if (lArr != null) {
                    for (L l2 : lArr) {
                        if (l2 != null) {
                            l2.g();
                        }
                    }
                }
                synchronized (this) {
                    i2 = this.f4669e;
                    if (i2 == i4) {
                        this.f4669e = i4 + 1;
                        return true;
                    }
                    dVarArrL = l();
                    H0.s sVar2 = H0.s.f314a;
                }
                dVarArrL2 = dVarArrL;
                i4 = i2;
            }
        }
    }

    @Override // j1.u
    public void setValue(Object obj) {
        if (obj == null) {
            obj = k1.s.f5012a;
        }
        o(null, obj);
    }
}
