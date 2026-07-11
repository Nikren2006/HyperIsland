package j1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class G implements E {

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4652a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4653b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ I f4654c;

        /* JADX INFO: renamed from: j1.G$a$a, reason: collision with other inner class name */
        public static final class C0089a implements InterfaceC0419g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ kotlin.jvm.internal.w f4655a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0419g f4656b;

            /* JADX INFO: renamed from: j1.G$a$a$a, reason: collision with other inner class name */
            public static final class C0090a extends N0.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public /* synthetic */ Object f4657a;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public int f4659c;

                public C0090a(L0.d dVar) {
                    super(dVar);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) {
                    this.f4657a = obj;
                    this.f4659c |= Integer.MIN_VALUE;
                    return C0089a.this.a(0, this);
                }
            }

            public C0089a(kotlin.jvm.internal.w wVar, InterfaceC0419g interfaceC0419g) {
                this.f4655a = wVar;
                this.f4656b = interfaceC0419g;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object a(int r5, L0.d r6) throws java.lang.Throwable {
                /*
                    r4 = this;
                    boolean r0 = r6 instanceof j1.G.a.C0089a.C0090a
                    if (r0 == 0) goto L13
                    r0 = r6
                    j1.G$a$a$a r0 = (j1.G.a.C0089a.C0090a) r0
                    int r1 = r0.f4659c
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f4659c = r1
                    goto L18
                L13:
                    j1.G$a$a$a r0 = new j1.G$a$a$a
                    r0.<init>(r6)
                L18:
                    java.lang.Object r6 = r0.f4657a
                    java.lang.Object r1 = M0.c.c()
                    int r2 = r0.f4659c
                    r3 = 1
                    if (r2 == 0) goto L31
                    if (r2 != r3) goto L29
                    H0.k.b(r6)
                    goto L4b
                L29:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r5)
                    throw r4
                L31:
                    H0.k.b(r6)
                    if (r5 <= 0) goto L4e
                    kotlin.jvm.internal.w r5 = r4.f4655a
                    boolean r6 = r5.f5057a
                    if (r6 != 0) goto L4e
                    r5.f5057a = r3
                    j1.g r4 = r4.f4656b
                    j1.C r5 = j1.C.START
                    r0.f4659c = r3
                    java.lang.Object r4 = r4.emit(r5, r0)
                    if (r4 != r1) goto L4b
                    return r1
                L4b:
                    H0.s r4 = H0.s.f314a
                    return r4
                L4e:
                    H0.s r4 = H0.s.f314a
                    return r4
                */
                throw new UnsupportedOperationException("Method not decompiled: j1.G.a.C0089a.a(int, L0.d):java.lang.Object");
            }

            @Override // j1.InterfaceC0419g
            public /* bridge */ /* synthetic */ Object emit(Object obj, L0.d dVar) {
                return a(((Number) obj).intValue(), dVar);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(I i2, L0.d dVar) {
            super(2, dVar);
            this.f4654c = i2;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = new a(this.f4654c, dVar);
            aVar.f4653b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            return ((a) create(interfaceC0419g, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f4652a;
            if (i2 == 0) {
                H0.k.b(obj);
                InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.f4653b;
                kotlin.jvm.internal.w wVar = new kotlin.jvm.internal.w();
                I i3 = this.f4654c;
                C0089a c0089a = new C0089a(wVar, interfaceC0419g);
                this.f4652a = 1;
                if (i3.collect(c0089a, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    @Override // j1.E
    public InterfaceC0418f a(I i2) {
        return AbstractC0420h.u(new a(i2, null));
    }

    public String toString() {
        return "SharingStarted.Lazily";
    }
}
