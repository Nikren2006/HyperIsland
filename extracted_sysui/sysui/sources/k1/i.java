package k1;

import g1.E;
import g1.F;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Function3 f4968e;

    public static final class a extends N0.l implements Function2 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4969a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4970b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f4972d;

        /* JADX INFO: renamed from: k1.i$a$a, reason: collision with other inner class name */
        public static final class C0099a implements InterfaceC0419g {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ kotlin.jvm.internal.y f4973a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ E f4974b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ i f4975c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ InterfaceC0419g f4976d;

            /* JADX INFO: renamed from: k1.i$a$a$a, reason: collision with other inner class name */
            public static final class C0100a extends N0.l implements Function2 {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public int f4977a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ i f4978b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public final /* synthetic */ InterfaceC0419g f4979c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                public final /* synthetic */ Object f4980d;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C0100a(i iVar, InterfaceC0419g interfaceC0419g, Object obj, L0.d dVar) {
                    super(2, dVar);
                    this.f4978b = iVar;
                    this.f4979c = interfaceC0419g;
                    this.f4980d = obj;
                }

                @Override // N0.a
                public final L0.d create(Object obj, L0.d dVar) {
                    return new C0100a(this.f4978b, this.f4979c, this.f4980d, dVar);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(E e2, L0.d dVar) {
                    return ((C0100a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object objC = M0.c.c();
                    int i2 = this.f4977a;
                    if (i2 == 0) {
                        H0.k.b(obj);
                        Function3 function3 = this.f4978b.f4968e;
                        InterfaceC0419g interfaceC0419g = this.f4979c;
                        Object obj2 = this.f4980d;
                        this.f4977a = 1;
                        if (function3.invoke(interfaceC0419g, obj2, this) == objC) {
                            return objC;
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

            /* JADX INFO: renamed from: k1.i$a$a$b */
            public static final class b extends N0.d {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public Object f4981a;

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public Object f4982b;

                /* JADX INFO: renamed from: c, reason: collision with root package name */
                public Object f4983c;

                /* JADX INFO: renamed from: d, reason: collision with root package name */
                public /* synthetic */ Object f4984d;

                /* JADX INFO: renamed from: f, reason: collision with root package name */
                public int f4986f;

                public b(L0.d dVar) {
                    super(dVar);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) {
                    this.f4984d = obj;
                    this.f4986f |= Integer.MIN_VALUE;
                    return C0099a.this.emit(null, this);
                }
            }

            public C0099a(kotlin.jvm.internal.y yVar, E e2, i iVar, InterfaceC0419g interfaceC0419g) {
                this.f4973a = yVar;
                this.f4974b = e2;
                this.f4975c = iVar;
                this.f4976d = interfaceC0419g;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            @Override // j1.InterfaceC0419g
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.lang.Object r7, L0.d r8) throws java.lang.Throwable {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof k1.i.a.C0099a.b
                    if (r0 == 0) goto L13
                    r0 = r8
                    k1.i$a$a$b r0 = (k1.i.a.C0099a.b) r0
                    int r1 = r0.f4986f
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.f4986f = r1
                    goto L18
                L13:
                    k1.i$a$a$b r0 = new k1.i$a$a$b
                    r0.<init>(r8)
                L18:
                    java.lang.Object r8 = r0.f4984d
                    java.lang.Object r1 = M0.c.c()
                    int r2 = r0.f4986f
                    r3 = 1
                    if (r2 == 0) goto L3b
                    if (r2 != r3) goto L33
                    java.lang.Object r6 = r0.f4983c
                    g1.l0 r6 = (g1.InterfaceC0380l0) r6
                    java.lang.Object r7 = r0.f4982b
                    java.lang.Object r6 = r0.f4981a
                    k1.i$a$a r6 = (k1.i.a.C0099a) r6
                    H0.k.b(r8)
                    goto L5d
                L33:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L3b:
                    H0.k.b(r8)
                    kotlin.jvm.internal.y r8 = r6.f4973a
                    java.lang.Object r8 = r8.f5059a
                    g1.l0 r8 = (g1.InterfaceC0380l0) r8
                    if (r8 == 0) goto L5d
                    k1.j r2 = new k1.j
                    r2.<init>()
                    r8.a(r2)
                    r0.f4981a = r6
                    r0.f4982b = r7
                    r0.f4983c = r8
                    r0.f4986f = r3
                    java.lang.Object r8 = r8.c(r0)
                    if (r8 != r1) goto L5d
                    return r1
                L5d:
                    kotlin.jvm.internal.y r8 = r6.f4973a
                    g1.E r0 = r6.f4974b
                    g1.G r2 = g1.G.UNDISPATCHED
                    k1.i$a$a$a r3 = new k1.i$a$a$a
                    k1.i r1 = r6.f4975c
                    j1.g r6 = r6.f4976d
                    r4 = 0
                    r3.<init>(r1, r6, r7, r4)
                    r4 = 1
                    r5 = 0
                    r1 = 0
                    g1.l0 r6 = g1.AbstractC0367f.b(r0, r1, r2, r3, r4, r5)
                    r8.f5059a = r6
                    H0.s r6 = H0.s.f314a
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: k1.i.a.C0099a.emit(java.lang.Object, L0.d):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(InterfaceC0419g interfaceC0419g, L0.d dVar) {
            super(2, dVar);
            this.f4972d = interfaceC0419g;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            a aVar = i.this.new a(this.f4972d, dVar);
            aVar.f4970b = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((a) create(e2, dVar)).invokeSuspend(H0.s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.f4969a;
            if (i2 == 0) {
                H0.k.b(obj);
                E e2 = (E) this.f4970b;
                kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
                i iVar = i.this;
                InterfaceC0418f interfaceC0418f = iVar.f4964d;
                C0099a c0099a = new C0099a(yVar, e2, iVar, this.f4972d);
                this.f4969a = 1;
                if (interfaceC0418f.collect(c0099a, this) == objC) {
                    return objC;
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

    public /* synthetic */ i(Function3 function3, InterfaceC0418f interfaceC0418f, L0.g gVar, int i2, i1.a aVar, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function3, interfaceC0418f, (i3 & 4) != 0 ? L0.h.f402a : gVar, (i3 & 8) != 0 ? -2 : i2, (i3 & 16) != 0 ? i1.a.SUSPEND : aVar);
    }

    @Override // k1.e
    public e h(L0.g gVar, int i2, i1.a aVar) {
        return new i(this.f4968e, this.f4964d, gVar, i2, aVar);
    }

    @Override // k1.g
    public Object p(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        Object objF = F.f(new a(interfaceC0419g, null), dVar);
        return objF == M0.c.c() ? objF : H0.s.f314a;
    }

    public i(Function3 function3, InterfaceC0418f interfaceC0418f, L0.g gVar, int i2, i1.a aVar) {
        super(interfaceC0418f, gVar, i2, aVar);
        this.f4968e = function3;
    }
}
