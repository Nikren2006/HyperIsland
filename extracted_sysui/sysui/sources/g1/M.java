package g1;

import L0.g;
import androidx.core.location.LocationRequestCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class M {

    public static final class a extends N0.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public /* synthetic */ Object f4384a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4385b;

        public a(L0.d dVar) {
            super(dVar);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) {
            this.f4384a = obj;
            this.f4385b |= Integer.MIN_VALUE;
            return M.a(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object a(L0.d r4) throws java.lang.Throwable {
        /*
            boolean r0 = r4 instanceof g1.M.a
            if (r0 == 0) goto L13
            r0 = r4
            g1.M$a r0 = (g1.M.a) r0
            int r1 = r0.f4385b
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4385b = r1
            goto L18
        L13:
            g1.M$a r0 = new g1.M$a
            r0.<init>(r4)
        L18:
            java.lang.Object r4 = r0.f4384a
            java.lang.Object r1 = M0.c.c()
            int r2 = r0.f4385b
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 == r3) goto L2d
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r0)
            throw r4
        L2d:
            H0.k.b(r4)
            goto L52
        L31:
            H0.k.b(r4)
            r0.f4385b = r3
            g1.l r4 = new g1.l
            L0.d r2 = M0.b.b(r0)
            r4.<init>(r2, r3)
            r4.A()
            java.lang.Object r4 = r4.x()
            java.lang.Object r2 = M0.c.c()
            if (r4 != r2) goto L4f
            N0.h.c(r0)
        L4f:
            if (r4 != r1) goto L52
            return r1
        L52:
            H0.c r4 = new H0.c
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.M.a(L0.d):java.lang.Object");
    }

    public static final Object b(long j2, L0.d dVar) {
        if (j2 <= 0) {
            return H0.s.f314a;
        }
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        if (j2 < LocationRequestCompat.PASSIVE_INTERVAL) {
            c(c0379l.getContext()).t(j2, c0379l);
        }
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    public static final L c(L0.g gVar) {
        g.b bVar = gVar.get(L0.e.f399t);
        L l2 = bVar instanceof L ? (L) bVar : null;
        return l2 == null ? K.a() : l2;
    }
}
