package j1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class q {

    public static final class a implements InterfaceC0419g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Function2 f4745a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ kotlin.jvm.internal.y f4746b;

        /* JADX INFO: renamed from: j1.q$a$a, reason: collision with other inner class name */
        public static final class C0095a extends N0.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public Object f4747a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ Object f4748b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f4749c;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public Object f4751e;

            public C0095a(L0.d dVar) {
                super(dVar);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) {
                this.f4748b = obj;
                this.f4749c |= Integer.MIN_VALUE;
                return a.this.emit(null, this);
            }
        }

        public a(Function2 function2, kotlin.jvm.internal.y yVar) {
            this.f4745a = function2;
            this.f4746b = yVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // j1.InterfaceC0419g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
            /*
                r4 = this;
                boolean r0 = r6 instanceof j1.q.a.C0095a
                if (r0 == 0) goto L13
                r0 = r6
                j1.q$a$a r0 = (j1.q.a.C0095a) r0
                int r1 = r0.f4749c
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.f4749c = r1
                goto L18
            L13:
                j1.q$a$a r0 = new j1.q$a$a
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.f4748b
                java.lang.Object r1 = M0.c.c()
                int r2 = r0.f4749c
                r3 = 1
                if (r2 == 0) goto L37
                if (r2 != r3) goto L2f
                java.lang.Object r5 = r0.f4751e
                java.lang.Object r4 = r0.f4747a
                j1.q$a r4 = (j1.q.a) r4
                H0.k.b(r6)
                goto L51
            L2f:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L37:
                H0.k.b(r6)
                kotlin.jvm.functions.Function2 r6 = r4.f4745a
                r0.f4747a = r4
                r0.f4751e = r5
                r0.f4749c = r3
                r2 = 6
                kotlin.jvm.internal.m.c(r2)
                java.lang.Object r6 = r6.invoke(r5, r0)
                r0 = 7
                kotlin.jvm.internal.m.c(r0)
                if (r6 != r1) goto L51
                return r1
            L51:
                java.lang.Boolean r6 = (java.lang.Boolean) r6
                boolean r6 = r6.booleanValue()
                if (r6 != 0) goto L5c
                H0.s r4 = H0.s.f314a
                return r4
            L5c:
                kotlin.jvm.internal.y r6 = r4.f4746b
                r6.f5059a = r5
                k1.a r5 = new k1.a
                r5.<init>(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.q.a.emit(java.lang.Object, L0.d):java.lang.Object");
        }
    }

    public static final class b extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4752a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4753b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f4754c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public /* synthetic */ Object f4755d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4756e;

        public b(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4755d = obj;
            this.f4756e |= Integer.MIN_VALUE;
            return AbstractC0420h.t(null, null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(j1.InterfaceC0418f r4, kotlin.jvm.functions.Function2 r5, L0.d r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof j1.q.b
            if (r0 == 0) goto L13
            r0 = r6
            j1.q$b r0 = (j1.q.b) r0
            int r1 = r0.f4756e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4756e = r1
            goto L18
        L13:
            j1.q$b r0 = new j1.q$b
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f4755d
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4756e
            r3 = 1
            if (r2 == 0) goto L3f
            if (r2 != r3) goto L37
            java.lang.Object r4 = r0.f4754c
            j1.q$a r4 = (j1.q.a) r4
            java.lang.Object r5 = r0.f4753b
            kotlin.jvm.internal.y r5 = (kotlin.jvm.internal.y) r5
            java.lang.Object r0 = r0.f4752a
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            H0.k.b(r6)     // Catch: k1.a -> L35
            goto L6a
        L35:
            r6 = move-exception
            goto L67
        L37:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3f:
            H0.k.b(r6)
            kotlin.jvm.internal.y r6 = new kotlin.jvm.internal.y
            r6.<init>()
            l1.F r2 = k1.s.f5012a
            r6.f5059a = r2
            j1.q$a r2 = new j1.q$a
            r2.<init>(r5, r6)
            r0.f4752a = r5     // Catch: k1.a -> L62
            r0.f4753b = r6     // Catch: k1.a -> L62
            r0.f4754c = r2     // Catch: k1.a -> L62
            r0.f4756e = r3     // Catch: k1.a -> L62
            java.lang.Object r4 = r4.collect(r2, r0)     // Catch: k1.a -> L62
            if (r4 != r1) goto L5f
            return r1
        L5f:
            r0 = r5
            r5 = r6
            goto L6a
        L62:
            r4 = move-exception
            r0 = r5
            r5 = r6
            r6 = r4
            r4 = r2
        L67:
            k1.o.a(r6, r4)
        L6a:
            java.lang.Object r4 = r5.f5059a
            l1.F r5 = k1.s.f5012a
            if (r4 == r5) goto L71
            return r4
        L71:
            java.util.NoSuchElementException r4 = new java.util.NoSuchElementException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Expected at least one element matching the predicate "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.q.a(j1.f, kotlin.jvm.functions.Function2, L0.d):java.lang.Object");
    }
}
