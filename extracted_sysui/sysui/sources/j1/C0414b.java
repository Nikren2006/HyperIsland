package j1;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: j1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0414b extends C0415c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Function2 f4685e;

    /* JADX INFO: renamed from: j1.b$a */
    public static final class a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4686a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public /* synthetic */ Object f4687b;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4689d;

        public a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4687b = obj;
            this.f4689d |= Integer.MIN_VALUE;
            return C0414b.this.g(null, this);
        }
    }

    public /* synthetic */ C0414b(Function2 function2, L0.g gVar, int i2, i1.a aVar, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, (i3 & 2) != 0 ? L0.h.f402a : gVar, (i3 & 4) != 0 ? -2 : i2, (i3 & 8) != 0 ? i1.a.SUSPEND : aVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // j1.C0415c, k1.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object g(i1.q r5, L0.d r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof j1.C0414b.a
            if (r0 == 0) goto L13
            r0 = r6
            j1.b$a r0 = (j1.C0414b.a) r0
            int r1 = r0.f4689d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4689d = r1
            goto L18
        L13:
            j1.b$a r0 = new j1.b$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f4687b
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4689d
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r4 = r0.f4686a
            r5 = r4
            i1.q r5 = (i1.q) r5
            H0.k.b(r6)
            goto L44
        L2e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L36:
            H0.k.b(r6)
            r0.f4686a = r5
            r0.f4689d = r3
            java.lang.Object r4 = super.g(r5, r0)
            if (r4 != r1) goto L44
            return r1
        L44:
            boolean r4 = r5.s()
            if (r4 == 0) goto L4d
            H0.s r4 = H0.s.f314a
            return r4
        L4d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: j1.C0414b.g(i1.q, L0.d):java.lang.Object");
    }

    @Override // j1.C0415c, k1.e
    public k1.e h(L0.g gVar, int i2, i1.a aVar) {
        return new C0414b(this.f4685e, gVar, i2, aVar);
    }

    public C0414b(Function2 function2, L0.g gVar, int i2, i1.a aVar) {
        super(function2, gVar, i2, aVar);
        this.f4685e = function2;
    }
}
