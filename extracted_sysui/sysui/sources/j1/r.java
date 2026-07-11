package j1;

import g1.AbstractC0367f;
import g1.InterfaceC0380l0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class r {

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4757a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ E f4758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f f4759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ t f4760d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f4761e;

        /* JADX INFO: renamed from: j1.r$a$a, reason: collision with other inner class name */
        public static final class C0096a extends N0.l implements Function2 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4762a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ int f4763b;

            public C0096a(L0.d dVar) {
                super(2, dVar);
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                C0096a c0096a = new C0096a(dVar);
                c0096a.f4763b = ((Number) obj).intValue();
                return c0096a;
            }

            public final Object e(int i2, L0.d dVar) {
                return ((C0096a) create(Integer.valueOf(i2), dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return e(((Number) obj).intValue(), (L0.d) obj2);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                M0.c.c();
                if (this.f4762a != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
                return N0.b.a(this.f4763b > 0);
            }
        }

        public static final class b extends N0.l implements Function2 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4764a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public /* synthetic */ Object f4765b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0418f f4766c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ t f4767d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ Object f4768e;

            /* JADX INFO: renamed from: j1.r$a$b$a, reason: collision with other inner class name */
            public /* synthetic */ class C0097a {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public static final /* synthetic */ int[] f4769a;

                static {
                    int[] iArr = new int[C.values().length];
                    try {
                        iArr[C.START.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[C.STOP.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[C.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    f4769a = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(InterfaceC0418f interfaceC0418f, t tVar, Object obj, L0.d dVar) {
                super(2, dVar);
                this.f4766c = interfaceC0418f;
                this.f4767d = tVar;
                this.f4768e = obj;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                b bVar = new b(this.f4766c, this.f4767d, this.f4768e, dVar);
                bVar.f4765b = obj;
                return bVar;
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public final Object invoke(C c2, L0.d dVar) {
                return ((b) create(c2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = M0.c.c();
                int i2 = this.f4764a;
                if (i2 == 0) {
                    H0.k.b(obj);
                    int i3 = C0097a.f4769a[((C) this.f4765b).ordinal()];
                    if (i3 == 1) {
                        InterfaceC0418f interfaceC0418f = this.f4766c;
                        t tVar = this.f4767d;
                        this.f4764a = 1;
                        if (interfaceC0418f.collect(tVar, this) == objC) {
                            return objC;
                        }
                    } else if (i3 == 3) {
                        Object obj2 = this.f4768e;
                        if (obj2 == A.f4637a) {
                            this.f4767d.d();
                        } else {
                            this.f4767d.b(obj2);
                        }
                    }
                } else {
                    if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    H0.k.b(obj);
                }
                return H0.s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(E e2, InterfaceC0418f interfaceC0418f, t tVar, Object obj, L0.d dVar) {
            super(2, dVar);
            this.f4758b = e2;
            this.f4759c = interfaceC0418f;
            this.f4760d = tVar;
            this.f4761e = obj;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return new a(this.f4758b, this.f4759c, this.f4760d, this.f4761e, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(g1.E e2, L0.d dVar) {
            return ((a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0068 A[RETURN] */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
            /*
                r7 = this;
                java.lang.Object r0 = M0.c.c()
                int r1 = r7.f4757a
                r2 = 4
                r3 = 3
                r4 = 2
                r5 = 1
                if (r1 == 0) goto L25
                if (r1 == r5) goto L21
                if (r1 == r4) goto L1d
                if (r1 == r3) goto L21
                if (r1 != r2) goto L15
                goto L21
            L15:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L1d:
                H0.k.b(r8)
                goto L5c
            L21:
                H0.k.b(r8)
                goto L8d
            L25:
                H0.k.b(r8)
                j1.E r8 = r7.f4758b
                j1.E$a r1 = j1.E.f4648a
                j1.E r6 = r1.c()
                if (r8 != r6) goto L3f
                j1.f r8 = r7.f4759c
                j1.t r1 = r7.f4760d
                r7.f4757a = r5
                java.lang.Object r7 = r8.collect(r1, r7)
                if (r7 != r0) goto L8d
                return r0
            L3f:
                j1.E r8 = r7.f4758b
                j1.E r1 = r1.d()
                r5 = 0
                if (r8 != r1) goto L69
                j1.t r8 = r7.f4760d
                j1.I r8 = r8.c()
                j1.r$a$a r1 = new j1.r$a$a
                r1.<init>(r5)
                r7.f4757a = r4
                java.lang.Object r8 = j1.AbstractC0420h.t(r8, r1, r7)
                if (r8 != r0) goto L5c
                return r0
            L5c:
                j1.f r8 = r7.f4759c
                j1.t r1 = r7.f4760d
                r7.f4757a = r3
                java.lang.Object r7 = r8.collect(r1, r7)
                if (r7 != r0) goto L8d
                return r0
            L69:
                j1.E r8 = r7.f4758b
                j1.t r1 = r7.f4760d
                j1.I r1 = r1.c()
                j1.f r8 = r8.a(r1)
                j1.f r8 = j1.AbstractC0420h.n(r8)
                j1.r$a$b r1 = new j1.r$a$b
                j1.f r3 = r7.f4759c
                j1.t r4 = r7.f4760d
                java.lang.Object r6 = r7.f4761e
                r1.<init>(r3, r4, r6, r5)
                r7.f4757a = r2
                java.lang.Object r7 = j1.AbstractC0420h.h(r8, r1, r7)
                if (r7 != r0) goto L8d
                return r0
            L8d:
                H0.s r7 = H0.s.f314a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.r.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final y a(t tVar) {
        return new v(tVar, null);
    }

    public static final I b(u uVar) {
        return new w(uVar, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final j1.D c(j1.InterfaceC0418f r7, int r8) {
        /*
            i1.d$a r0 = i1.d.f4600C
            int r0 = r0.a()
            int r0 = c1.f.c(r8, r0)
            int r0 = r0 - r8
            boolean r1 = r7 instanceof k1.e
            if (r1 == 0) goto L3c
            r1 = r7
            k1.e r1 = (k1.e) r1
            j1.f r2 = r1.i()
            if (r2 == 0) goto L3c
            j1.D r7 = new j1.D
            int r3 = r1.f4955b
            r4 = -3
            if (r3 == r4) goto L26
            r4 = -2
            if (r3 == r4) goto L26
            if (r3 == 0) goto L26
            r0 = r3
            goto L34
        L26:
            i1.a r4 = r1.f4956c
            i1.a r5 = i1.a.SUSPEND
            r6 = 0
            if (r4 != r5) goto L31
            if (r3 != 0) goto L34
        L2f:
            r0 = r6
            goto L34
        L31:
            if (r8 != 0) goto L2f
            r0 = 1
        L34:
            i1.a r8 = r1.f4956c
            L0.g r1 = r1.f4954a
            r7.<init>(r2, r0, r8, r1)
            return r7
        L3c:
            j1.D r8 = new j1.D
            i1.a r1 = i1.a.SUSPEND
            L0.h r2 = L0.h.f402a
            r8.<init>(r7, r0, r1, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.r.c(j1.f, int):j1.D");
    }

    public static final InterfaceC0380l0 d(g1.E e2, L0.g gVar, InterfaceC0418f interfaceC0418f, t tVar, E e3, Object obj) {
        return AbstractC0367f.a(e2, gVar, kotlin.jvm.internal.n.c(e3, E.f4648a.c()) ? g1.G.DEFAULT : g1.G.UNDISPATCHED, new a(e3, interfaceC0418f, tVar, obj, null));
    }

    public static final y e(InterfaceC0418f interfaceC0418f, g1.E e2, E e3, int i2) {
        D dC = c(interfaceC0418f, i2);
        t tVarA = A.a(i2, dC.f4645b, dC.f4646c);
        return new v(tVarA, d(e2, dC.f4647d, dC.f4644a, tVarA, e3, A.f4637a));
    }

    public static /* synthetic */ y f(InterfaceC0418f interfaceC0418f, g1.E e2, E e3, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return AbstractC0420h.z(interfaceC0418f, e2, e3, i2);
    }

    public static final I g(InterfaceC0418f interfaceC0418f, g1.E e2, E e3, Object obj) {
        D dC = c(interfaceC0418f, 1);
        u uVarA = K.a(obj);
        return new w(uVarA, d(e2, dC.f4647d, dC.f4644a, uVarA, e3, obj));
    }
}
