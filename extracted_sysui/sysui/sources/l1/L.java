package l1;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public class L {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f5206b = AtomicIntegerFieldUpdater.newUpdater(L.class, "_size");
    private volatile int _size;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public M[] f5207a;

    public final void a(M m2) {
        m2.a(this);
        M[] mArrF = f();
        int iC = c();
        j(iC + 1);
        mArrF[iC] = m2;
        m2.d(iC);
        l(iC);
    }

    public final M b() {
        M[] mArr = this.f5207a;
        if (mArr != null) {
            return mArr[0];
        }
        return null;
    }

    public final int c() {
        return f5206b.get(this);
    }

    public final boolean d() {
        return c() == 0;
    }

    public final M e() {
        M mB;
        synchronized (this) {
            mB = b();
        }
        return mB;
    }

    public final M[] f() {
        M[] mArr = this.f5207a;
        if (mArr == null) {
            M[] mArr2 = new M[4];
            this.f5207a = mArr2;
            return mArr2;
        }
        if (c() < mArr.length) {
            return mArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(mArr, c() * 2);
        kotlin.jvm.internal.n.f(objArrCopyOf, "copyOf(this, newSize)");
        M[] mArr3 = (M[]) objArrCopyOf;
        this.f5207a = mArr3;
        return mArr3;
    }

    public final boolean g(M m2) {
        boolean z2;
        synchronized (this) {
            if (m2.c() == null) {
                z2 = false;
            } else {
                h(m2.e());
                z2 = true;
            }
        }
        return z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final l1.M h(int r6) {
        /*
            r5 = this;
            l1.M[] r0 = r5.f5207a
            kotlin.jvm.internal.n.d(r0)
            int r1 = r5.c()
            r2 = -1
            int r1 = r1 + r2
            r5.j(r1)
            int r1 = r5.c()
            if (r6 >= r1) goto L3d
            int r1 = r5.c()
            r5.m(r6, r1)
            int r1 = r6 + (-1)
            int r1 = r1 / 2
            if (r6 <= 0) goto L3a
            r3 = r0[r6]
            kotlin.jvm.internal.n.d(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r0[r1]
            kotlin.jvm.internal.n.d(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L3a
            r5.m(r6, r1)
            r5.l(r1)
            goto L3d
        L3a:
            r5.k(r6)
        L3d:
            int r6 = r5.c()
            r6 = r0[r6]
            kotlin.jvm.internal.n.d(r6)
            r1 = 0
            r6.a(r1)
            r6.d(r2)
            int r5 = r5.c()
            r0[r5] = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: l1.L.h(int):l1.M");
    }

    public final M i() {
        M mH;
        synchronized (this) {
            mH = c() > 0 ? h(0) : null;
        }
        return mH;
    }

    public final void j(int i2) {
        f5206b.set(this, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k(int r6) {
        /*
            r5 = this;
        L0:
            int r0 = r6 * 2
            int r1 = r0 + 1
            int r2 = r5.c()
            if (r1 < r2) goto Lb
            return
        Lb:
            l1.M[] r2 = r5.f5207a
            kotlin.jvm.internal.n.d(r2)
            int r0 = r0 + 2
            int r3 = r5.c()
            if (r0 >= r3) goto L2b
            r3 = r2[r0]
            kotlin.jvm.internal.n.d(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r2[r1]
            kotlin.jvm.internal.n.d(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L2b
            goto L2c
        L2b:
            r0 = r1
        L2c:
            r1 = r2[r6]
            kotlin.jvm.internal.n.d(r1)
            java.lang.Comparable r1 = (java.lang.Comparable) r1
            r2 = r2[r0]
            kotlin.jvm.internal.n.d(r2)
            int r1 = r1.compareTo(r2)
            if (r1 > 0) goto L3f
            return
        L3f:
            r5.m(r6, r0)
            r6 = r0
            goto L0
        */
        throw new UnsupportedOperationException("Method not decompiled: l1.L.k(int):void");
    }

    public final void l(int i2) {
        while (i2 > 0) {
            M[] mArr = this.f5207a;
            kotlin.jvm.internal.n.d(mArr);
            int i3 = (i2 - 1) / 2;
            M m2 = mArr[i3];
            kotlin.jvm.internal.n.d(m2);
            M m3 = mArr[i2];
            kotlin.jvm.internal.n.d(m3);
            if (((Comparable) m2).compareTo(m3) <= 0) {
                return;
            }
            m(i2, i3);
            i2 = i3;
        }
    }

    public final void m(int i2, int i3) {
        M[] mArr = this.f5207a;
        kotlin.jvm.internal.n.d(mArr);
        M m2 = mArr[i3];
        kotlin.jvm.internal.n.d(m2);
        M m3 = mArr[i2];
        kotlin.jvm.internal.n.d(m3);
        mArr[i2] = m2;
        mArr[i3] = m3;
        m2.d(i2);
        m3.d(i3);
    }
}
