package k1;

import g1.E;
import i1.t;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes2.dex */
public abstract class k {

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4987a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4988b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4989c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4990d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4991e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public /* synthetic */ Object f4992f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0418f[] f4993g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Function0 f4994h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Function3 f4995i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f4996j;

        /* JADX INFO: renamed from: k1.k$a$a, reason: collision with other inner class name */
        public static final class C0101a extends N0.l implements Function2 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f4997a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0418f[] f4998b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ int f4999c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ AtomicInteger f5000d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ i1.d f5001e;

            /* JADX INFO: renamed from: k1.k$a$a$a, reason: collision with other inner class name */
            public static final class C0102a implements InterfaceC0419g {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ i1.d f5002a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ int f5003b;

                /* JADX INFO: renamed from: k1.k$a$a$a$a, reason: collision with other inner class name */
                public static final class C0103a extends N0.d {

                    /* JADX INFO: renamed from: a, reason: collision with root package name */
                    public /* synthetic */ Object f5004a;

                    /* JADX INFO: renamed from: c, reason: collision with root package name */
                    public int f5006c;

                    public C0103a(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.f5004a = obj;
                        this.f5006c |= Integer.MIN_VALUE;
                        return C0102a.this.emit(null, this);
                    }
                }

                public C0102a(i1.d dVar, int i2) {
                    this.f5002a = dVar;
                    this.f5003b = i2;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, L0.d r7) throws java.lang.Throwable {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof k1.k.a.C0101a.C0102a.C0103a
                        if (r0 == 0) goto L13
                        r0 = r7
                        k1.k$a$a$a$a r0 = (k1.k.a.C0101a.C0102a.C0103a) r0
                        int r1 = r0.f5006c
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.f5006c = r1
                        goto L18
                    L13:
                        k1.k$a$a$a$a r0 = new k1.k$a$a$a$a
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.f5004a
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.f5006c
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L38
                        if (r2 == r4) goto L34
                        if (r2 != r3) goto L2c
                        H0.k.b(r7)
                        goto L56
                    L2c:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L34:
                        H0.k.b(r7)
                        goto L4d
                    L38:
                        H0.k.b(r7)
                        i1.d r7 = r5.f5002a
                        I0.z r2 = new I0.z
                        int r5 = r5.f5003b
                        r2.<init>(r5, r6)
                        r0.f5006c = r4
                        java.lang.Object r5 = r7.b(r2, r0)
                        if (r5 != r1) goto L4d
                        return r1
                    L4d:
                        r0.f5006c = r3
                        java.lang.Object r5 = g1.Q0.a(r0)
                        if (r5 != r1) goto L56
                        return r1
                    L56:
                        H0.s r5 = H0.s.f314a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: k1.k.a.C0101a.C0102a.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0101a(InterfaceC0418f[] interfaceC0418fArr, int i2, AtomicInteger atomicInteger, i1.d dVar, L0.d dVar2) {
                super(2, dVar2);
                this.f4998b = interfaceC0418fArr;
                this.f4999c = i2;
                this.f5000d = atomicInteger;
                this.f5001e = dVar;
            }

            @Override // N0.a
            public final L0.d create(Object obj, L0.d dVar) {
                return new C0101a(this.f4998b, this.f4999c, this.f5000d, this.f5001e, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, L0.d dVar) {
                return ((C0101a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                AtomicInteger atomicInteger;
                Object objC = M0.c.c();
                int i2 = this.f4997a;
                try {
                    if (i2 == 0) {
                        H0.k.b(obj);
                        InterfaceC0418f[] interfaceC0418fArr = this.f4998b;
                        int i3 = this.f4999c;
                        InterfaceC0418f interfaceC0418f = interfaceC0418fArr[i3];
                        C0102a c0102a = new C0102a(this.f5001e, i3);
                        this.f4997a = 1;
                        if (interfaceC0418f.collect(c0102a, this) == objC) {
                            return objC;
                        }
                    } else {
                        if (i2 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        H0.k.b(obj);
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        t.a.a(this.f5001e, null, 1, null);
                    }
                    return H0.s.f314a;
                } finally {
                    if (this.f5000d.decrementAndGet() == 0) {
                        t.a.a(this.f5001e, null, 1, null);
                    }
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(InterfaceC0418f[] interfaceC0418fArr, Function0 function0, Function3 function3, InterfaceC0419g interfaceC0419g, L0.d dVar) {
            super(2, dVar);
            this.f4993g = interfaceC0418fArr;
            this.f4994h = function0;
            this.f4995i = function3;
            this.f4996j = interfaceC0419g;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = new a(this.f4993g, this.f4994h, this.f4995i, this.f4996j, dVar);
            aVar.f4992f = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x00bd A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00ca A[LOOP:0: B:28:0x00ca->B:48:?, LOOP_START, PHI: r6 r10
          0x00ca: PHI (r6v6 int) = (r6v5 int), (r6v7 int) binds: [B:25:0x00c5, B:48:?] A[DONT_GENERATE, DONT_INLINE]
          0x00ca: PHI (r10v8 I0.z) = (r10v7 I0.z), (r10v21 I0.z) binds: [B:25:0x00c5, B:48:?] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0135 -> B:45:0x0137). Please report as a decompilation issue!!! */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r24) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 314
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: k1.k.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object a(InterfaceC0419g interfaceC0419g, InterfaceC0418f[] interfaceC0418fArr, Function0 function0, Function3 function3, L0.d dVar) {
        Object objA = n.a(new a(interfaceC0418fArr, function0, function3, interfaceC0419g, null), dVar);
        return objA == M0.c.c() ? objA : H0.s.f314a;
    }
}
