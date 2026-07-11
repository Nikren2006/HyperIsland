package i1;

import H0.j;
import g1.A;
import g1.E;
import g1.G;
import g1.InterfaceC0377k;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o {

    public static final class a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4612a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Object f4613b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public /* synthetic */ Object f4614c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f4615d;

        public a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4614c = obj;
            this.f4615d |= Integer.MIN_VALUE;
            return o.a(null, null, this);
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0377k f4616a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(InterfaceC0377k interfaceC0377k) {
            super(1);
            this.f4616a = interfaceC0377k;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return H0.s.f314a;
        }

        public final void invoke(Throwable th) {
            InterfaceC0377k interfaceC0377k = this.f4616a;
            j.a aVar = H0.j.f299a;
            interfaceC0377k.resumeWith(H0.j.a(H0.s.f314a));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(i1.q r4, kotlin.jvm.functions.Function0 r5, L0.d r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof i1.o.a
            if (r0 == 0) goto L13
            r0 = r6
            i1.o$a r0 = (i1.o.a) r0
            int r1 = r0.f4615d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4615d = r1
            goto L18
        L13:
            i1.o$a r0 = new i1.o$a
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f4614c
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4615d
            r3 = 1
            if (r2 == 0) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r4 = r0.f4613b
            r5 = r4
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            java.lang.Object r4 = r0.f4612a
            i1.q r4 = (i1.q) r4
            H0.k.b(r6)     // Catch: java.lang.Throwable -> L32
            goto L75
        L32:
            r4 = move-exception
            goto L7b
        L34:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3c:
            H0.k.b(r6)
            L0.g r6 = r0.getContext()
            g1.l0$b r2 = g1.InterfaceC0380l0.f4430z
            L0.g$b r6 = r6.get(r2)
            if (r6 != r4) goto L7f
            r0.f4612a = r4     // Catch: java.lang.Throwable -> L32
            r0.f4613b = r5     // Catch: java.lang.Throwable -> L32
            r0.f4615d = r3     // Catch: java.lang.Throwable -> L32
            g1.l r6 = new g1.l     // Catch: java.lang.Throwable -> L32
            L0.d r2 = M0.b.b(r0)     // Catch: java.lang.Throwable -> L32
            r6.<init>(r2, r3)     // Catch: java.lang.Throwable -> L32
            r6.A()     // Catch: java.lang.Throwable -> L32
            i1.o$b r2 = new i1.o$b     // Catch: java.lang.Throwable -> L32
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L32
            r4.i(r2)     // Catch: java.lang.Throwable -> L32
            java.lang.Object r4 = r6.x()     // Catch: java.lang.Throwable -> L32
            java.lang.Object r6 = M0.c.c()     // Catch: java.lang.Throwable -> L32
            if (r4 != r6) goto L72
            N0.h.c(r0)     // Catch: java.lang.Throwable -> L32
        L72:
            if (r4 != r1) goto L75
            return r1
        L75:
            r5.invoke()
            H0.s r4 = H0.s.f314a
            return r4
        L7b:
            r5.invoke()
            throw r4
        L7f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "awaitClose() can only be invoked from the producer context"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.o.a(i1.q, kotlin.jvm.functions.Function0, L0.d):java.lang.Object");
    }

    public static final s b(E e2, L0.g gVar, int i2, i1.a aVar, G g2, Function1 function1, Function2 function2) {
        p pVar = new p(A.e(e2, gVar), g.b(i2, aVar, null, 4, null));
        if (function1 != null) {
            pVar.l(function1);
        }
        pVar.E0(g2, pVar, function2);
        return pVar;
    }

    public static /* synthetic */ s c(E e2, L0.g gVar, int i2, i1.a aVar, G g2, Function1 function1, Function2 function2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            gVar = L0.h.f402a;
        }
        L0.g gVar2 = gVar;
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            aVar = i1.a.SUSPEND;
        }
        i1.a aVar2 = aVar;
        if ((i3 & 8) != 0) {
            g2 = G.DEFAULT;
        }
        G g3 = g2;
        if ((i3 & 16) != 0) {
            function1 = null;
        }
        return b(e2, gVar2, i4, aVar2, g3, function1, function2);
    }
}
