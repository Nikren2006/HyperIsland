package j1;

/* JADX INFO: renamed from: j1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0422j {

    /* JADX INFO: renamed from: j1.j$a */
    public static final class a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4702a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4703b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f4704c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f4705d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public /* synthetic */ Object f4706e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f4707f;

        public a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4706e = obj;
            this.f4707f |= Integer.MIN_VALUE;
            return AbstractC0422j.c(null, null, false, this);
        }
    }

    public static final Object b(InterfaceC0419g interfaceC0419g, i1.s sVar, L0.d dVar) throws Throwable {
        Object objC = c(interfaceC0419g, sVar, true, dVar);
        return objC == M0.c.c() ? objC : H0.s.f314a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007f A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #0 {all -> 0x003c, blocks: (B:13:0x0036, B:24:0x0062, B:28:0x0077, B:30:0x007f, B:20:0x0054, B:23:0x005e), top: B:42:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0091 -> B:14:0x0039). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(j1.InterfaceC0419g r6, i1.s r7, boolean r8, L0.d r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof j1.AbstractC0422j.a
            if (r0 == 0) goto L13
            r0 = r9
            j1.j$a r0 = (j1.AbstractC0422j.a) r0
            int r1 = r0.f4707f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4707f = r1
            goto L18
        L13:
            j1.j$a r0 = new j1.j$a
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f4706e
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4707f
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L58
            if (r2 == r4) goto L46
            if (r2 != r3) goto L3e
            boolean r8 = r0.f4705d
            java.lang.Object r6 = r0.f4704c
            i1.f r6 = (i1.f) r6
            java.lang.Object r7 = r0.f4703b
            i1.s r7 = (i1.s) r7
            java.lang.Object r2 = r0.f4702a
            j1.g r2 = (j1.InterfaceC0419g) r2
            H0.k.b(r9)     // Catch: java.lang.Throwable -> L3c
        L39:
            r9 = r6
            r6 = r2
            goto L62
        L3c:
            r6 = move-exception
            goto L9d
        L3e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L46:
            boolean r8 = r0.f4705d
            java.lang.Object r6 = r0.f4704c
            i1.f r6 = (i1.f) r6
            java.lang.Object r7 = r0.f4703b
            i1.s r7 = (i1.s) r7
            java.lang.Object r2 = r0.f4702a
            j1.g r2 = (j1.InterfaceC0419g) r2
            H0.k.b(r9)     // Catch: java.lang.Throwable -> L3c
            goto L77
        L58:
            H0.k.b(r9)
            j1.AbstractC0420h.s(r6)
            i1.f r9 = r7.iterator()     // Catch: java.lang.Throwable -> L3c
        L62:
            r0.f4702a = r6     // Catch: java.lang.Throwable -> L3c
            r0.f4703b = r7     // Catch: java.lang.Throwable -> L3c
            r0.f4704c = r9     // Catch: java.lang.Throwable -> L3c
            r0.f4705d = r8     // Catch: java.lang.Throwable -> L3c
            r0.f4707f = r4     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r2 = r9.a(r0)     // Catch: java.lang.Throwable -> L3c
            if (r2 != r1) goto L73
            return r1
        L73:
            r5 = r2
            r2 = r6
            r6 = r9
            r9 = r5
        L77:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L3c
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L3c
            if (r9 == 0) goto L94
            java.lang.Object r9 = r6.next()     // Catch: java.lang.Throwable -> L3c
            r0.f4702a = r2     // Catch: java.lang.Throwable -> L3c
            r0.f4703b = r7     // Catch: java.lang.Throwable -> L3c
            r0.f4704c = r6     // Catch: java.lang.Throwable -> L3c
            r0.f4705d = r8     // Catch: java.lang.Throwable -> L3c
            r0.f4707f = r3     // Catch: java.lang.Throwable -> L3c
            java.lang.Object r9 = r2.emit(r9, r0)     // Catch: java.lang.Throwable -> L3c
            if (r9 != r1) goto L39
            return r1
        L94:
            if (r8 == 0) goto L9a
            r6 = 0
            i1.j.a(r7, r6)
        L9a:
            H0.s r6 = H0.s.f314a
            return r6
        L9d:
            throw r6     // Catch: java.lang.Throwable -> L9e
        L9e:
            r9 = move-exception
            if (r8 == 0) goto La4
            i1.j.a(r7, r6)
        La4:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.AbstractC0422j.c(j1.g, i1.s, boolean, L0.d):java.lang.Object");
    }
}
