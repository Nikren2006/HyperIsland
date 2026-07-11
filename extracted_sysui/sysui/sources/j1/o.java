package j1;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class o {

    public static final class a implements InterfaceC0418f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4720a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Function2 f4721b;

        public a(InterfaceC0418f interfaceC0418f, Function2 function2) {
            this.f4720a = interfaceC0418f;
            this.f4721b = function2;
        }

        @Override // j1.InterfaceC0418f
        public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            Object objCollect = this.f4720a.collect(new b(new kotlin.jvm.internal.w(), interfaceC0419g, this.f4721b), dVar);
            return objCollect == M0.c.c() ? objCollect : H0.s.f314a;
        }
    }

    public static final class b implements InterfaceC0419g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ kotlin.jvm.internal.w f4722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f4723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Function2 f4724c;

        public static final class a extends N0.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public Object f4725a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public Object f4726b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public /* synthetic */ Object f4727c;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public int f4729e;

            public a(L0.d dVar) {
                super(dVar);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) {
                this.f4727c = obj;
                this.f4729e |= Integer.MIN_VALUE;
                return b.this.emit(null, this);
            }
        }

        public b(kotlin.jvm.internal.w wVar, InterfaceC0419g interfaceC0419g, Function2 function2) {
            this.f4722a = wVar;
            this.f4723b = interfaceC0419g;
            this.f4724c = function2;
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // j1.InterfaceC0419g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r7, L0.d r8) throws java.lang.Throwable {
            /*
                r6 = this;
                boolean r0 = r8 instanceof j1.o.b.a
                if (r0 == 0) goto L13
                r0 = r8
                j1.o$b$a r0 = (j1.o.b.a) r0
                int r1 = r0.f4729e
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.f4729e = r1
                goto L18
            L13:
                j1.o$b$a r0 = new j1.o$b$a
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.f4727c
                java.lang.Object r1 = M0.c.c()
                int r2 = r0.f4729e
                r3 = 3
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L45
                if (r2 == r5) goto L41
                if (r2 == r4) goto L37
                if (r2 != r3) goto L2f
                H0.k.b(r8)
                goto L87
            L2f:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L37:
                java.lang.Object r7 = r0.f4726b
                java.lang.Object r6 = r0.f4725a
                j1.o$b r6 = (j1.o.b) r6
                H0.k.b(r8)
                goto L6b
            L41:
                H0.k.b(r8)
                goto L59
            L45:
                H0.k.b(r8)
                kotlin.jvm.internal.w r8 = r6.f4722a
                boolean r8 = r8.f5057a
                if (r8 == 0) goto L5c
                j1.g r6 = r6.f4723b
                r0.f4729e = r5
                java.lang.Object r6 = r6.emit(r7, r0)
                if (r6 != r1) goto L59
                return r1
            L59:
                H0.s r6 = H0.s.f314a
                return r6
            L5c:
                kotlin.jvm.functions.Function2 r8 = r6.f4724c
                r0.f4725a = r6
                r0.f4726b = r7
                r0.f4729e = r4
                java.lang.Object r8 = r8.invoke(r7, r0)
                if (r8 != r1) goto L6b
                return r1
            L6b:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 != 0) goto L8a
                kotlin.jvm.internal.w r8 = r6.f4722a
                r8.f5057a = r5
                j1.g r6 = r6.f4723b
                r8 = 0
                r0.f4725a = r8
                r0.f4726b = r8
                r0.f4729e = r3
                java.lang.Object r6 = r6.emit(r7, r0)
                if (r6 != r1) goto L87
                return r1
            L87:
                H0.s r6 = H0.s.f314a
                return r6
            L8a:
                H0.s r6 = H0.s.f314a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.o.b.emit(java.lang.Object, L0.d):java.lang.Object");
        }
    }

    public static final class c extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4730a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4731b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4732c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Function3 f4733d;

        public static final class a implements InterfaceC0419g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Function3 f4734a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0419g f4735b;

            /* JADX INFO: renamed from: j1.o$c$a$a, reason: collision with other inner class name */
            public static final class C0094a extends N0.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public Object f4736a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public /* synthetic */ Object f4737b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public int f4738c;

                public C0094a(L0.d dVar) {
                    super(dVar);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) {
                    this.f4737b = obj;
                    this.f4738c |= Integer.MIN_VALUE;
                    return a.this.emit(null, this);
                }
            }

            public a(Function3 function3, InterfaceC0419g interfaceC0419g) {
                this.f4734a = function3;
                this.f4735b = interfaceC0419g;
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
                    boolean r0 = r6 instanceof j1.o.c.a.C0094a
                    if (r0 == 0) goto L13
                    r0 = r6
                    j1.o$c$a$a r0 = (j1.o.c.a.C0094a) r0
                    int r1 = r0.f4738c
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f4738c = r1
                    goto L18
                L13:
                    j1.o$c$a$a r0 = new j1.o$c$a$a
                    r0.<init>(r6)
                L18:
                    java.lang.Object r6 = r0.f4737b
                    java.lang.Object r1 = M0.c.c()
                    int r2 = r0.f4738c
                    r3 = 1
                    if (r2 == 0) goto L35
                    if (r2 != r3) goto L2d
                    java.lang.Object r4 = r0.f4736a
                    j1.o$c$a r4 = (j1.o.c.a) r4
                    H0.k.b(r6)
                    goto L4f
                L2d:
                    java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                    java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                    r4.<init>(r5)
                    throw r4
                L35:
                    H0.k.b(r6)
                    kotlin.jvm.functions.Function3 r6 = r4.f4734a
                    j1.g r2 = r4.f4735b
                    r0.f4736a = r4
                    r0.f4738c = r3
                    r3 = 6
                    kotlin.jvm.internal.m.c(r3)
                    java.lang.Object r6 = r6.invoke(r2, r5, r0)
                    r5 = 7
                    kotlin.jvm.internal.m.c(r5)
                    if (r6 != r1) goto L4f
                    return r1
                L4f:
                    java.lang.Boolean r6 = (java.lang.Boolean) r6
                    boolean r5 = r6.booleanValue()
                    if (r5 == 0) goto L5a
                    H0.s r4 = H0.s.f314a
                    return r4
                L5a:
                    k1.a r5 = new k1.a
                    r5.<init>(r4)
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: j1.o.c.a.emit(java.lang.Object, L0.d):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(InterfaceC0418f interfaceC0418f, Function3 function3, L0.d dVar) {
            super(2, dVar);
            this.f4732c = interfaceC0418f;
            this.f4733d = function3;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            c cVar = new c(this.f4732c, this.f4733d, dVar);
            cVar.f4731b = obj;
            return cVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            return ((c) create(interfaceC0419g, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            a aVar;
            Object objC = M0.c.c();
            int i2 = this.f4730a;
            if (i2 == 0) {
                H0.k.b(obj);
                InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.f4731b;
                InterfaceC0418f interfaceC0418f = this.f4732c;
                a aVar2 = new a(this.f4733d, interfaceC0419g);
                try {
                    this.f4731b = aVar2;
                    this.f4730a = 1;
                    if (interfaceC0418f.collect(aVar2, this) == objC) {
                        return objC;
                    }
                } catch (k1.a e2) {
                    e = e2;
                    aVar = aVar2;
                    k1.o.a(e, aVar);
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                aVar = (a) this.f4731b;
                try {
                    H0.k.b(obj);
                } catch (k1.a e3) {
                    e = e3;
                    k1.o.a(e, aVar);
                }
            }
            return H0.s.f314a;
        }
    }

    public static final InterfaceC0418f a(InterfaceC0418f interfaceC0418f, Function2 function2) {
        return new a(interfaceC0418f, function2);
    }

    public static final InterfaceC0418f b(InterfaceC0418f interfaceC0418f, Function3 function3) {
        return AbstractC0420h.u(new c(interfaceC0418f, function3, null));
    }
}
