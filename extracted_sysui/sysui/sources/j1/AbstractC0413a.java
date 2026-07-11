package j1;

/* JADX INFO: renamed from: j1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0413a implements InterfaceC0418f {

    /* JADX INFO: renamed from: j1.a$a, reason: collision with other inner class name */
    public static final class C0091a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4681a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4682b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4684d;

        public C0091a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4682b = obj;
            this.f4684d |= Integer.MIN_VALUE;
            return AbstractC0413a.this.collect(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // j1.InterfaceC0418f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collect(j1.InterfaceC0419g r5, L0.d r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof j1.AbstractC0413a.C0091a
            if (r0 == 0) goto L13
            r0 = r6
            j1.a$a r0 = (j1.AbstractC0413a.C0091a) r0
            int r1 = r0.f4684d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4684d = r1
            goto L18
        L13:
            j1.a$a r0 = new j1.a$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f4682b
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4684d
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r4 = r0.f4681a
            k1.t r4 = (k1.t) r4
            H0.k.b(r6)     // Catch: java.lang.Throwable -> L2d
            goto L4f
        L2d:
            r5 = move-exception
            goto L57
        L2f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L37:
            H0.k.b(r6)
            k1.t r6 = new k1.t
            L0.g r2 = r0.getContext()
            r6.<init>(r5, r2)
            r0.f4681a = r6     // Catch: java.lang.Throwable -> L55
            r0.f4684d = r3     // Catch: java.lang.Throwable -> L55
            java.lang.Object r4 = r4.e(r6, r0)     // Catch: java.lang.Throwable -> L55
            if (r4 != r1) goto L4e
            return r1
        L4e:
            r4 = r6
        L4f:
            r4.releaseIntercepted()
            H0.s r4 = H0.s.f314a
            return r4
        L55:
            r5 = move-exception
            r4 = r6
        L57:
            r4.releaseIntercepted()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.AbstractC0413a.collect(j1.g, L0.d):java.lang.Object");
    }

    public abstract Object e(InterfaceC0419g interfaceC0419g, L0.d dVar);
}
