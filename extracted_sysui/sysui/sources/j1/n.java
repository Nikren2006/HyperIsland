package j1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class n {

    public static final class a implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Function2 f4712a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4713b;

        /* JADX INFO: renamed from: j1.n$a$a, reason: collision with other inner class name */
        public static final class C0093a extends N0.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public /* synthetic */ Object f4714a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f4715b;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public Object f4717d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public Object f4718e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public Object f4719f;

            public C0093a(L0.d dVar) {
                super(dVar);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) {
                this.f4714a = obj;
                this.f4715b |= Integer.MIN_VALUE;
                return a.this.collect(null, this);
            }
        }

        public a(Function2 function2, InterfaceC0418f interfaceC0418f) {
            this.f4712a = function2;
            this.f4713b = interfaceC0418f;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0081 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // j1.InterfaceC0418f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object collect(j1.InterfaceC0419g r6, L0.d r7) throws java.lang.Throwable {
            /*
                r5 = this;
                boolean r0 = r7 instanceof j1.n.a.C0093a
                if (r0 == 0) goto L13
                r0 = r7
                j1.n$a$a r0 = (j1.n.a.C0093a) r0
                int r1 = r0.f4715b
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.f4715b = r1
                goto L18
            L13:
                j1.n$a$a r0 = new j1.n$a$a
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.f4714a
                java.lang.Object r1 = M0.c.c()
                int r2 = r0.f4715b
                r3 = 2
                r4 = 1
                if (r2 == 0) goto L46
                if (r2 == r4) goto L34
                if (r2 != r3) goto L2c
                H0.k.b(r7)
                goto L82
            L2c:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L34:
                java.lang.Object r5 = r0.f4719f
                k1.t r5 = (k1.t) r5
                java.lang.Object r6 = r0.f4718e
                j1.g r6 = (j1.InterfaceC0419g) r6
                java.lang.Object r2 = r0.f4717d
                j1.n$a r2 = (j1.n.a) r2
                H0.k.b(r7)     // Catch: java.lang.Throwable -> L44
                goto L6d
            L44:
                r6 = move-exception
                goto L87
            L46:
                H0.k.b(r7)
                k1.t r7 = new k1.t
                L0.g r2 = r0.getContext()
                r7.<init>(r6, r2)
                kotlin.jvm.functions.Function2 r2 = r5.f4712a     // Catch: java.lang.Throwable -> L85
                r0.f4717d = r5     // Catch: java.lang.Throwable -> L85
                r0.f4718e = r6     // Catch: java.lang.Throwable -> L85
                r0.f4719f = r7     // Catch: java.lang.Throwable -> L85
                r0.f4715b = r4     // Catch: java.lang.Throwable -> L85
                r4 = 6
                kotlin.jvm.internal.m.c(r4)     // Catch: java.lang.Throwable -> L85
                java.lang.Object r2 = r2.invoke(r7, r0)     // Catch: java.lang.Throwable -> L85
                r4 = 7
                kotlin.jvm.internal.m.c(r4)     // Catch: java.lang.Throwable -> L85
                if (r2 != r1) goto L6b
                return r1
            L6b:
                r2 = r5
                r5 = r7
            L6d:
                r5.releaseIntercepted()
                j1.f r5 = r2.f4713b
                r7 = 0
                r0.f4717d = r7
                r0.f4718e = r7
                r0.f4719f = r7
                r0.f4715b = r3
                java.lang.Object r5 = r5.collect(r6, r0)
                if (r5 != r1) goto L82
                return r1
            L82:
                H0.s r5 = H0.s.f314a
                return r5
            L85:
                r6 = move-exception
                r5 = r7
            L87:
                r5.releaseIntercepted()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.n.a.collect(j1.g, L0.d):java.lang.Object");
        }
    }

    public static final void a(InterfaceC0419g interfaceC0419g) {
    }

    public static final InterfaceC0418f b(InterfaceC0418f interfaceC0418f, Function2 function2) {
        return new a(function2, interfaceC0418f);
    }
}
