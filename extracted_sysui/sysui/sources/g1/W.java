package g1;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public abstract class W extends X implements L {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4401d = AtomicReferenceFieldUpdater.newUpdater(W.class, Object.class, "_queue");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4402e = AtomicReferenceFieldUpdater.newUpdater(W.class, Object.class, "_delayed");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4403f = AtomicIntegerFieldUpdater.newUpdater(W.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    public final class a extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final InterfaceC0377k f4404c;

        public a(long j2, InterfaceC0377k interfaceC0377k) {
            super(j2);
            this.f4404c = interfaceC0377k;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4404c.p(W.this, H0.s.f314a);
        }

        @Override // g1.W.b
        public String toString() {
            return super.toString() + this.f4404c;
        }
    }

    public static abstract class b implements Runnable, Comparable, S, l1.M {
        private volatile Object _heap;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f4406a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4407b = -1;

        public b(long j2) {
            this.f4406a = j2;
        }

        @Override // l1.M
        public void a(l1.L l2) {
            if (this._heap == Z.f4409a) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this._heap = l2;
        }

        @Override // l1.M
        public l1.L c() {
            Object obj = this._heap;
            if (obj instanceof l1.L) {
                return (l1.L) obj;
            }
            return null;
        }

        @Override // l1.M
        public void d(int i2) {
            this.f4407b = i2;
        }

        @Override // g1.S
        public final void dispose() {
            synchronized (this) {
                try {
                    Object obj = this._heap;
                    if (obj == Z.f4409a) {
                        return;
                    }
                    c cVar = obj instanceof c ? (c) obj : null;
                    if (cVar != null) {
                        cVar.g(this);
                    }
                    this._heap = Z.f4409a;
                    H0.s sVar = H0.s.f314a;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // l1.M
        public int e() {
            return this.f4407b;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            long j2 = this.f4406a - bVar.f4406a;
            if (j2 > 0) {
                return 1;
            }
            return j2 < 0 ? -1 : 0;
        }

        public final int g(long j2, c cVar, W w2) {
            synchronized (this) {
                if (this._heap == Z.f4409a) {
                    return 2;
                }
                synchronized (cVar) {
                    try {
                        b bVar = (b) cVar.b();
                        if (w2.R()) {
                            return 1;
                        }
                        if (bVar == null) {
                            cVar.f4408c = j2;
                        } else {
                            long j3 = bVar.f4406a;
                            if (j3 - j2 < 0) {
                                j2 = j3;
                            }
                            if (j2 - cVar.f4408c > 0) {
                                cVar.f4408c = j2;
                            }
                        }
                        long j4 = this.f4406a;
                        long j5 = cVar.f4408c;
                        if (j4 - j5 < 0) {
                            this.f4406a = j5;
                        }
                        cVar.a(this);
                        return 0;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public final boolean h(long j2) {
            return j2 - this.f4406a >= 0;
        }

        public String toString() {
            return "Delayed[nanos=" + this.f4406a + ']';
        }
    }

    public static final class c extends l1.L {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f4408c;

        public c(long j2) {
            this.f4408c = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean R() {
        return f4403f.get(this) != 0;
    }

    @Override // g1.V
    public long C() {
        b bVar;
        if (super.C() == 0) {
            return 0L;
        }
        Object obj = f4401d.get(this);
        if (obj != null) {
            if (!(obj instanceof l1.s)) {
                if (obj == Z.f4410b) {
                    return LocationRequestCompat.PASSIVE_INTERVAL;
                }
                return 0L;
            }
            if (!((l1.s) obj).g()) {
                return 0L;
            }
        }
        c cVar = (c) f4402e.get(this);
        if (cVar == null || (bVar = (b) cVar.e()) == null) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        long j2 = bVar.f4406a;
        AbstractC0361c.a();
        return c1.f.d(j2 - System.nanoTime(), 0L);
    }

    @Override // g1.V
    public void I() {
        H0.f4374a.b();
        Y(true);
        N();
        while (T() <= 0) {
        }
        U();
    }

    public final void N() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4401d;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                if (f4401d.compareAndSet(this, null, Z.f4410b)) {
                    return;
                }
            } else if (obj instanceof l1.s) {
                ((l1.s) obj).d();
                return;
            } else {
                if (obj == Z.f4410b) {
                    return;
                }
                l1.s sVar = new l1.s(8, true);
                kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                sVar.a((Runnable) obj);
                if (f4401d.compareAndSet(this, obj, sVar)) {
                    return;
                }
            }
        }
    }

    public final Runnable O() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4401d;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                return null;
            }
            if (obj instanceof l1.s) {
                kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                l1.s sVar = (l1.s) obj;
                Object objJ = sVar.j();
                if (objJ != l1.s.f5246h) {
                    return (Runnable) objJ;
                }
                f4401d.compareAndSet(this, obj, sVar.i());
            } else {
                if (obj == Z.f4410b) {
                    return null;
                }
                if (f4401d.compareAndSet(this, obj, null)) {
                    kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                    return (Runnable) obj;
                }
            }
        }
    }

    public void P(Runnable runnable) {
        if (Q(runnable)) {
            L();
        } else {
            J.f4376g.P(runnable);
        }
    }

    public final boolean Q(Runnable runnable) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4401d;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (R()) {
                return false;
            }
            if (obj == null) {
                if (f4401d.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof l1.s) {
                kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeTaskQueueCore<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }>{ kotlinx.coroutines.EventLoop_commonKt.Queue<java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }> }");
                l1.s sVar = (l1.s) obj;
                int iA = sVar.a(runnable);
                if (iA == 0) {
                    return true;
                }
                if (iA == 1) {
                    f4401d.compareAndSet(this, obj, sVar.i());
                } else if (iA == 2) {
                    return false;
                }
            } else {
                if (obj == Z.f4410b) {
                    return false;
                }
                l1.s sVar2 = new l1.s(8, true);
                kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type java.lang.Runnable{ kotlinx.coroutines.RunnableKt.Runnable }");
                sVar2.a((Runnable) obj);
                sVar2.a(runnable);
                if (f4401d.compareAndSet(this, obj, sVar2)) {
                    return true;
                }
            }
        }
    }

    public boolean S() {
        if (!G()) {
            return false;
        }
        c cVar = (c) f4402e.get(this);
        if (cVar != null && !cVar.d()) {
            return false;
        }
        Object obj = f4401d.get(this);
        if (obj != null) {
            if (obj instanceof l1.s) {
                return ((l1.s) obj).g();
            }
            if (obj != Z.f4410b) {
                return false;
            }
        }
        return true;
    }

    public long T() {
        l1.M mH;
        if (H()) {
            return 0L;
        }
        c cVar = (c) f4402e.get(this);
        if (cVar != null && !cVar.d()) {
            AbstractC0361c.a();
            long jNanoTime = System.nanoTime();
            do {
                synchronized (cVar) {
                    try {
                        l1.M mB = cVar.b();
                        if (mB != null) {
                            b bVar = (b) mB;
                            mH = bVar.h(jNanoTime) ? Q(bVar) : false ? cVar.h(0) : null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } while (((b) mH) != null);
        }
        Runnable runnableO = O();
        if (runnableO == null) {
            return C();
        }
        runnableO.run();
        return 0L;
    }

    public final void U() {
        b bVar;
        AbstractC0361c.a();
        long jNanoTime = System.nanoTime();
        while (true) {
            c cVar = (c) f4402e.get(this);
            if (cVar == null || (bVar = (b) cVar.i()) == null) {
                return;
            } else {
                K(jNanoTime, bVar);
            }
        }
    }

    public final void V() {
        f4401d.set(this, null);
        f4402e.set(this, null);
    }

    public final void W(long j2, b bVar) {
        int iX = X(j2, bVar);
        if (iX == 0) {
            if (Z(bVar)) {
                L();
            }
        } else if (iX == 1) {
            K(j2, bVar);
        } else if (iX != 2) {
            throw new IllegalStateException("unexpected result");
        }
    }

    public final int X(long j2, b bVar) {
        if (R()) {
            return 1;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4402e;
        c cVar = (c) atomicReferenceFieldUpdater.get(this);
        if (cVar == null) {
            atomicReferenceFieldUpdater.compareAndSet(this, null, new c(j2));
            Object obj = atomicReferenceFieldUpdater.get(this);
            kotlin.jvm.internal.n.d(obj);
            cVar = (c) obj;
        }
        return bVar.g(j2, cVar, this);
    }

    public final void Y(boolean z2) {
        f4403f.set(this, z2 ? 1 : 0);
    }

    public final boolean Z(b bVar) {
        c cVar = (c) f4402e.get(this);
        return (cVar != null ? (b) cVar.e() : null) == bVar;
    }

    @Override // g1.B
    public final void dispatch(L0.g gVar, Runnable runnable) {
        P(runnable);
    }

    @Override // g1.L
    public void t(long j2, InterfaceC0377k interfaceC0377k) {
        long jC = Z.c(j2);
        if (jC < 4611686018427387903L) {
            AbstractC0361c.a();
            long jNanoTime = System.nanoTime();
            a aVar = new a(jC + jNanoTime, interfaceC0377k);
            W(jNanoTime, aVar);
            AbstractC0383n.a(interfaceC0377k, aVar);
        }
    }
}
