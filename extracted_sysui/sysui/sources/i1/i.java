package i1;

import java.util.concurrent.atomic.AtomicReferenceArray;
import l1.C;

/* JADX INFO: loaded from: classes2.dex */
public final class i extends C {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f4608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AtomicReferenceArray f4609f;

    public i(long j2, i iVar, b bVar, int i2) {
        super(j2, iVar, i2);
        this.f4608e = bVar;
        this.f4609f = new AtomicReferenceArray(c.f4581b * 2);
    }

    public final void A(int i2, Object obj) {
        this.f4609f.set((i2 * 2) + 1, obj);
    }

    public final void B(int i2, Object obj) {
        z(i2, obj);
    }

    @Override // l1.C
    public int n() {
        return c.f4581b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
    
        s(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0066, code lost:
    
        if (r0 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0068, code lost:
    
        r3 = u().f4562b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
    
        if (r3 == null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0070, code lost:
    
        l1.x.b(r3, r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
    
        return;
     */
    @Override // l1.C
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void o(int r4, java.lang.Throwable r5, L0.g r6) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r3 = this;
            int r5 = i1.c.f4581b
            if (r4 < r5) goto L6
            r0 = 1
            goto L7
        L6:
            r0 = 0
        L7:
            if (r0 == 0) goto La
            int r4 = r4 - r5
        La:
            java.lang.Object r5 = r3.v(r4)
        Le:
            java.lang.Object r1 = r3.w(r4)
            boolean r2 = r1 instanceof g1.O0
            if (r2 != 0) goto L74
            boolean r2 = r1 instanceof i1.u
            if (r2 == 0) goto L1b
            goto L74
        L1b:
            l1.F r2 = i1.c.j()
            if (r1 == r2) goto L63
            l1.F r2 = i1.c.i()
            if (r1 != r2) goto L28
            goto L63
        L28:
            l1.F r2 = i1.c.p()
            if (r1 == r2) goto Le
            l1.F r2 = i1.c.q()
            if (r1 != r2) goto L35
            goto Le
        L35:
            l1.F r3 = i1.c.f()
            if (r1 == r3) goto L62
            l1.F r3 = i1.c.f4583d
            if (r1 != r3) goto L40
            goto L62
        L40:
            l1.F r3 = i1.c.z()
            if (r1 != r3) goto L47
            return
        L47:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "unexpected state: "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L62:
            return
        L63:
            r3.s(r4)
            if (r0 == 0) goto L73
            i1.b r3 = r3.u()
            kotlin.jvm.functions.Function1 r3 = r3.f4562b
            if (r3 == 0) goto L73
            l1.x.b(r3, r5, r6)
        L73:
            return
        L74:
            if (r0 == 0) goto L7b
            l1.F r2 = i1.c.j()
            goto L7f
        L7b:
            l1.F r2 = i1.c.i()
        L7f:
            boolean r1 = r3.r(r4, r1, r2)
            if (r1 == 0) goto Le
            r3.s(r4)
            r1 = r0 ^ 1
            r3.x(r4, r1)
            if (r0 == 0) goto L9a
            i1.b r3 = r3.u()
            kotlin.jvm.functions.Function1 r3 = r3.f4562b
            if (r3 == 0) goto L9a
            l1.x.b(r3, r5, r6)
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: i1.i.o(int, java.lang.Throwable, L0.g):void");
    }

    public final boolean r(int i2, Object obj, Object obj2) {
        return this.f4609f.compareAndSet((i2 * 2) + 1, obj, obj2);
    }

    public final void s(int i2) {
        z(i2, null);
    }

    public final Object t(int i2, Object obj) {
        return this.f4609f.getAndSet((i2 * 2) + 1, obj);
    }

    public final b u() {
        b bVar = this.f4608e;
        kotlin.jvm.internal.n.d(bVar);
        return bVar;
    }

    public final Object v(int i2) {
        return this.f4609f.get(i2 * 2);
    }

    public final Object w(int i2) {
        return this.f4609f.get((i2 * 2) + 1);
    }

    public final void x(int i2, boolean z2) {
        if (z2) {
            u().H0((this.f5193c * ((long) c.f4581b)) + ((long) i2));
        }
        p();
    }

    public final Object y(int i2) {
        Object objV = v(i2);
        s(i2);
        return objV;
    }

    public final void z(int i2, Object obj) {
        this.f4609f.lazySet(i2 * 2, obj);
    }
}
