package j1;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: j1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0416d implements InterfaceC0418f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0418f f4691a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f4692b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Function2 f4693c;

    /* JADX INFO: renamed from: j1.d$a */
    public static final class a implements InterfaceC0419g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ kotlin.jvm.internal.y f4695b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0419g f4696c;

        /* JADX INFO: renamed from: j1.d$a$a, reason: collision with other inner class name */
        public static final class C0092a extends N0.d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public /* synthetic */ Object f4697a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f4699c;

            public C0092a(L0.d dVar) {
                super(dVar);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) {
                this.f4697a = obj;
                this.f4699c |= Integer.MIN_VALUE;
                return a.this.emit(null, this);
            }
        }

        public a(kotlin.jvm.internal.y yVar, InterfaceC0419g interfaceC0419g) {
            this.f4695b = yVar;
            this.f4696c = interfaceC0419g;
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
                boolean r0 = r7 instanceof j1.C0416d.a.C0092a
                if (r0 == 0) goto L13
                r0 = r7
                j1.d$a$a r0 = (j1.C0416d.a.C0092a) r0
                int r1 = r0.f4699c
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.f4699c = r1
                goto L18
            L13:
                j1.d$a$a r0 = new j1.d$a$a
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.f4697a
                java.lang.Object r1 = M0.c.c()
                int r2 = r0.f4699c
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                H0.k.b(r7)
                goto L67
            L29:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L31:
                H0.k.b(r7)
                j1.d r7 = j1.C0416d.this
                kotlin.jvm.functions.Function1 r7 = r7.f4692b
                java.lang.Object r7 = r7.invoke(r6)
                kotlin.jvm.internal.y r2 = r5.f4695b
                java.lang.Object r2 = r2.f5059a
                l1.F r4 = k1.s.f5012a
                if (r2 == r4) goto L58
                j1.d r4 = j1.C0416d.this
                kotlin.jvm.functions.Function2 r4 = r4.f4693c
                java.lang.Object r2 = r4.invoke(r2, r7)
                java.lang.Boolean r2 = (java.lang.Boolean) r2
                boolean r2 = r2.booleanValue()
                if (r2 != 0) goto L55
                goto L58
            L55:
                H0.s r5 = H0.s.f314a
                return r5
            L58:
                kotlin.jvm.internal.y r2 = r5.f4695b
                r2.f5059a = r7
                j1.g r5 = r5.f4696c
                r0.f4699c = r3
                java.lang.Object r5 = r5.emit(r6, r0)
                if (r5 != r1) goto L67
                return r1
            L67:
                H0.s r5 = H0.s.f314a
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: j1.C0416d.a.emit(java.lang.Object, L0.d):java.lang.Object");
        }
    }

    public C0416d(InterfaceC0418f interfaceC0418f, Function1 function1, Function2 function2) {
        this.f4691a = interfaceC0418f;
        this.f4692b = function1;
        this.f4693c = function2;
    }

    @Override // j1.InterfaceC0418f
    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
        yVar.f5059a = k1.s.f5012a;
        Object objCollect = this.f4691a.collect(new a(yVar, interfaceC0419g), dVar);
        return objCollect == M0.c.c() ? objCollect : H0.s.f314a;
    }
}
