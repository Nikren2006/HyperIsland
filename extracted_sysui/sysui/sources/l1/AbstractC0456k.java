package l1;

import g1.H0;
import g1.V;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: l1.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0456k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final F f5226a = new F("UNDEFINED");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final F f5227b = new F("REUSABLE_CLAIMED");

    /* JADX WARN: Removed duplicated region for block: B:27:0x008f A[Catch: all -> 0x0068, DONT_GENERATE, TryCatch #0 {all -> 0x0068, blocks: (B:11:0x003f, B:13:0x004d, B:15:0x0053, B:28:0x0092, B:18:0x006a, B:20:0x007a, B:25:0x0089, B:27:0x008f, B:33:0x009f, B:36:0x00a8, B:35:0x00a5, B:23:0x0080), top: B:44:0x003f, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void b(L0.d r6, java.lang.Object r7, kotlin.jvm.functions.Function1 r8) {
        /*
            boolean r0 = r6 instanceof l1.C0455j
            if (r0 == 0) goto Lb2
            l1.j r6 = (l1.C0455j) r6
            java.lang.Object r8 = g1.AbstractC0398z.c(r7, r8)
            g1.B r0 = r6.f5222d
            L0.g r1 = r6.getContext()
            boolean r0 = r0.isDispatchNeeded(r1)
            r1 = 1
            if (r0 == 0) goto L26
            r6.f5224f = r8
            r6.f4389c = r1
            g1.B r7 = r6.f5222d
            L0.g r8 = r6.getContext()
            r7.dispatch(r8, r6)
            goto Lb5
        L26:
            g1.H0 r0 = g1.H0.f4374a
            g1.V r0 = r0.a()
            boolean r2 = r0.F()
            if (r2 == 0) goto L3b
            r6.f5224f = r8
            r6.f4389c = r1
            r0.B(r6)
            goto Lb5
        L3b:
            r0.D(r1)
            r2 = 0
            L0.g r3 = r6.getContext()     // Catch: java.lang.Throwable -> L68
            g1.l0$b r4 = g1.InterfaceC0380l0.f4430z     // Catch: java.lang.Throwable -> L68
            L0.g$b r3 = r3.get(r4)     // Catch: java.lang.Throwable -> L68
            g1.l0 r3 = (g1.InterfaceC0380l0) r3     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L6a
            boolean r4 = r3.isActive()     // Catch: java.lang.Throwable -> L68
            if (r4 != 0) goto L6a
            java.util.concurrent.CancellationException r7 = r3.f()     // Catch: java.lang.Throwable -> L68
            r6.a(r8, r7)     // Catch: java.lang.Throwable -> L68
            H0.j$a r8 = H0.j.f299a     // Catch: java.lang.Throwable -> L68
            java.lang.Object r7 = H0.k.a(r7)     // Catch: java.lang.Throwable -> L68
            java.lang.Object r7 = H0.j.a(r7)     // Catch: java.lang.Throwable -> L68
            r6.resumeWith(r7)     // Catch: java.lang.Throwable -> L68
            goto L92
        L68:
            r7 = move-exception
            goto La9
        L6a:
            L0.d r8 = r6.f5223e     // Catch: java.lang.Throwable -> L68
            java.lang.Object r3 = r6.f5225g     // Catch: java.lang.Throwable -> L68
            L0.g r4 = r8.getContext()     // Catch: java.lang.Throwable -> L68
            java.lang.Object r3 = l1.J.c(r4, r3)     // Catch: java.lang.Throwable -> L68
            l1.F r5 = l1.J.f5199a     // Catch: java.lang.Throwable -> L68
            if (r3 == r5) goto L7f
            g1.M0 r8 = g1.A.g(r8, r4, r3)     // Catch: java.lang.Throwable -> L68
            goto L80
        L7f:
            r8 = r2
        L80:
            L0.d r5 = r6.f5223e     // Catch: java.lang.Throwable -> L9c
            r5.resumeWith(r7)     // Catch: java.lang.Throwable -> L9c
            H0.s r7 = H0.s.f314a     // Catch: java.lang.Throwable -> L9c
            if (r8 == 0) goto L8f
            boolean r7 = r8.F0()     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L92
        L8f:
            l1.J.a(r4, r3)     // Catch: java.lang.Throwable -> L68
        L92:
            boolean r7 = r0.H()     // Catch: java.lang.Throwable -> L68
            if (r7 != 0) goto L92
        L98:
            r0.z(r1)
            goto Lb5
        L9c:
            r7 = move-exception
            if (r8 == 0) goto La5
            boolean r8 = r8.F0()     // Catch: java.lang.Throwable -> L68
            if (r8 == 0) goto La8
        La5:
            l1.J.a(r4, r3)     // Catch: java.lang.Throwable -> L68
        La8:
            throw r7     // Catch: java.lang.Throwable -> L68
        La9:
            r6.f(r7, r2)     // Catch: java.lang.Throwable -> Lad
            goto L98
        Lad:
            r6 = move-exception
            r0.z(r1)
            throw r6
        Lb2:
            r6.resumeWith(r7)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: l1.AbstractC0456k.b(L0.d, java.lang.Object, kotlin.jvm.functions.Function1):void");
    }

    public static /* synthetic */ void c(L0.d dVar, Object obj, Function1 function1, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        b(dVar, obj, function1);
    }

    public static final boolean d(C0455j c0455j) {
        H0.s sVar = H0.s.f314a;
        V vA = H0.f4374a.a();
        if (vA.G()) {
            return false;
        }
        if (vA.F()) {
            c0455j.f5224f = sVar;
            c0455j.f4389c = 1;
            vA.B(c0455j);
            return true;
        }
        vA.D(true);
        try {
            c0455j.run();
            do {
            } while (vA.H());
        } finally {
            try {
            } finally {
            }
        }
        return false;
    }
}
