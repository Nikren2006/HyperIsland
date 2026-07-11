package n1;

import H0.s;
import androidx.core.location.LocationRequestCompat;
import g1.AbstractC0361c;
import g1.I;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.y;
import l1.A;
import l1.F;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Executor, Closeable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C0160a f6235h = new C0160a(null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f6236i = AtomicLongFieldUpdater.newUpdater(a.class, "parkedWorkersStack");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AtomicLongFieldUpdater f6237j = AtomicLongFieldUpdater.newUpdater(a.class, "controlState");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f6238k = AtomicIntegerFieldUpdater.newUpdater(a.class, "_isTerminated");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final F f6239l = new F("NOT_IN_STACK");
    private volatile int _isTerminated;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f6240a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f6242c;
    private volatile long controlState;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n1.d f6244e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n1.d f6245f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final A f6246g;
    private volatile long parkedWorkersStack;

    /* JADX INFO: renamed from: n1.a$a, reason: collision with other inner class name */
    public static final class C0160a {
        public /* synthetic */ C0160a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public C0160a() {
        }
    }

    public /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6247a;

        static {
            int[] iArr = new int[d.values().length];
            try {
                iArr[d.PARKING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[d.BLOCKING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[d.CPU_ACQUIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[d.DORMANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[d.TERMINATED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            f6247a = iArr;
        }
    }

    public enum d {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public a(int i2, int i3, long j2, String str) {
        this.f6240a = i2;
        this.f6241b = i3;
        this.f6242c = j2;
        this.f6243d = str;
        if (i2 < 1) {
            throw new IllegalArgumentException(("Core pool size " + i2 + " should be at least 1").toString());
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should be greater than or equals to core pool size " + i2).toString());
        }
        if (i3 > 2097150) {
            throw new IllegalArgumentException(("Max pool size " + i3 + " should not exceed maximal supported number of threads 2097150").toString());
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j2 + " must be positive").toString());
        }
        this.f6244e = new n1.d();
        this.f6245f = new n1.d();
        this.f6246g = new A((i2 + 1) * 2);
        this.controlState = ((long) i2) << 42;
        this._isTerminated = 0;
    }

    public static /* synthetic */ boolean F(a aVar, long j2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = f6237j.get(aVar);
        }
        return aVar.E(j2);
    }

    public static /* synthetic */ void n(a aVar, Runnable runnable, i iVar, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            iVar = l.f6282g;
        }
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        aVar.l(runnable, iVar, z2);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void A(long r8) throws java.lang.InterruptedException {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = n1.a.f6238k
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r7, r1, r2)
            if (r0 != 0) goto Lb
            return
        Lb:
            n1.a$c r0 = r7.f()
            l1.A r1 = r7.f6246g
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r3 = a()     // Catch: java.lang.Throwable -> L87
            long r3 = r3.get(r7)     // Catch: java.lang.Throwable -> L87
            r5 = 2097151(0x1fffff, double:1.0361303E-317)
            long r3 = r3 & r5
            int r3 = (int) r3
            monitor-exit(r1)
            if (r2 > r3) goto L49
            r1 = r2
        L23:
            l1.A r4 = r7.f6246g
            java.lang.Object r4 = r4.b(r1)
            kotlin.jvm.internal.n.d(r4)
            n1.a$c r4 = (n1.a.c) r4
            if (r4 == r0) goto L44
        L30:
            boolean r5 = r4.isAlive()
            if (r5 == 0) goto L3d
            java.util.concurrent.locks.LockSupport.unpark(r4)
            r4.join(r8)
            goto L30
        L3d:
            n1.n r4 = r4.f6249a
            n1.d r5 = r7.f6245f
            r4.f(r5)
        L44:
            if (r1 == r3) goto L49
            int r1 = r1 + 1
            goto L23
        L49:
            n1.d r8 = r7.f6245f
            r8.b()
            n1.d r8 = r7.f6244e
            r8.b()
        L53:
            if (r0 == 0) goto L5b
            n1.h r8 = r0.g(r2)
            if (r8 != 0) goto L83
        L5b:
            n1.d r8 = r7.f6244e
            java.lang.Object r8 = r8.d()
            n1.h r8 = (n1.h) r8
            if (r8 != 0) goto L83
            n1.d r8 = r7.f6245f
            java.lang.Object r8 = r8.d()
            n1.h r8 = (n1.h) r8
            if (r8 != 0) goto L83
            if (r0 == 0) goto L76
            n1.a$d r8 = n1.a.d.TERMINATED
            r0.u(r8)
        L76:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = n1.a.f6236i
            r0 = 0
            r8.set(r7, r0)
            java.util.concurrent.atomic.AtomicLongFieldUpdater r8 = n1.a.f6237j
            r8.set(r7, r0)
            return
        L83:
            r7.z(r8)
            goto L53
        L87:
            r7 = move-exception
            monitor-exit(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: n1.a.A(long):void");
    }

    public final void B(long j2, boolean z2) {
        if (z2 || G() || E(j2)) {
            return;
        }
        G();
    }

    public final void C() {
        if (G() || F(this, 0L, 1, null)) {
            return;
        }
        G();
    }

    public final h D(c cVar, h hVar, boolean z2) {
        if (cVar == null || cVar.f6251c == d.TERMINATED) {
            return hVar;
        }
        if (hVar.f6273b.b() == 0 && cVar.f6251c == d.BLOCKING) {
            return hVar;
        }
        cVar.f6255g = true;
        return cVar.f6249a.a(hVar, z2);
    }

    public final boolean E(long j2) {
        if (c1.f.c(((int) (2097151 & j2)) - ((int) ((j2 & 4398044413952L) >> 21)), 0) < this.f6240a) {
            int iD = d();
            if (iD == 1 && this.f6240a > 1) {
                d();
            }
            if (iD > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean G() {
        c cVarU;
        do {
            cVarU = u();
            if (cVarU == null) {
                return false;
            }
        } while (!c.j().compareAndSet(cVarU, -1, 0));
        LockSupport.unpark(cVarU);
        return true;
    }

    public final boolean c(h hVar) {
        return hVar.f6273b.b() == 1 ? this.f6245f.a(hVar) : this.f6244e.a(hVar);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws InterruptedException {
        A(10000L);
    }

    public final int d() {
        synchronized (this.f6246g) {
            try {
                if (r()) {
                    return -1;
                }
                AtomicLongFieldUpdater atomicLongFieldUpdater = f6237j;
                long j2 = atomicLongFieldUpdater.get(this);
                int i2 = (int) (j2 & 2097151);
                int iC = c1.f.c(i2 - ((int) ((j2 & 4398044413952L) >> 21)), 0);
                if (iC >= this.f6240a) {
                    return 0;
                }
                if (i2 >= this.f6241b) {
                    return 0;
                }
                int i3 = ((int) (f6237j.get(this) & 2097151)) + 1;
                if (i3 <= 0 || this.f6246g.b(i3) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                c cVar = new c(this, i3);
                this.f6246g.c(i3, cVar);
                if (i3 != ((int) (2097151 & atomicLongFieldUpdater.incrementAndGet(this)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i4 = iC + 1;
                cVar.start();
                return i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final h e(Runnable runnable, i iVar) {
        long jA = l.f6281f.a();
        if (!(runnable instanceof h)) {
            return new k(runnable, jA, iVar);
        }
        h hVar = (h) runnable;
        hVar.f6272a = jA;
        hVar.f6273b = iVar;
        return hVar;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        n(this, runnable, null, false, 6, null);
    }

    public final c f() {
        Thread threadCurrentThread = Thread.currentThread();
        c cVar = threadCurrentThread instanceof c ? (c) threadCurrentThread : null;
        if (cVar == null || !kotlin.jvm.internal.n.c(a.this, this)) {
            return null;
        }
        return cVar;
    }

    public final void l(Runnable runnable, i iVar, boolean z2) {
        AbstractC0361c.a();
        h hVarE = e(runnable, iVar);
        boolean z3 = false;
        boolean z4 = hVarE.f6273b.b() == 1;
        long jAddAndGet = z4 ? f6237j.addAndGet(this, 2097152L) : 0L;
        c cVarF = f();
        h hVarD = D(cVarF, hVarE, z2);
        if (hVarD != null && !c(hVarD)) {
            throw new RejectedExecutionException(this.f6243d + " was terminated");
        }
        if (z2 && cVarF != null) {
            z3 = true;
        }
        if (z4) {
            B(jAddAndGet, z3);
        } else {
            if (z3) {
                return;
            }
            C();
        }
    }

    public final boolean r() {
        return f6238k.get(this) != 0;
    }

    public final int t(c cVar) {
        Object objI = cVar.i();
        while (objI != f6239l) {
            if (objI == null) {
                return 0;
            }
            c cVar2 = (c) objI;
            int iH = cVar2.h();
            if (iH != 0) {
                return iH;
            }
            objI = cVar2.i();
        }
        return -1;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        int iA = this.f6246g.a();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 1; i7 < iA; i7++) {
            c cVar = (c) this.f6246g.b(i7);
            if (cVar != null) {
                int iE = cVar.f6249a.e();
                int i8 = b.f6247a[cVar.f6251c.ordinal()];
                if (i8 == 1) {
                    i4++;
                } else if (i8 == 2) {
                    i3++;
                    StringBuilder sb = new StringBuilder();
                    sb.append(iE);
                    sb.append('b');
                    arrayList.add(sb.toString());
                } else if (i8 == 3) {
                    i2++;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(iE);
                    sb2.append('c');
                    arrayList.add(sb2.toString());
                } else if (i8 == 4) {
                    i5++;
                    if (iE > 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(iE);
                        sb3.append('d');
                        arrayList.add(sb3.toString());
                    }
                } else if (i8 == 5) {
                    i6++;
                }
            }
        }
        long j2 = f6237j.get(this);
        return this.f6243d + '@' + I.b(this) + "[Pool Size {core = " + this.f6240a + ", max = " + this.f6241b + "}, Worker States {CPU = " + i2 + ", blocking = " + i3 + ", parked = " + i4 + ", dormant = " + i5 + ", terminated = " + i6 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.f6244e.c() + ", global blocking queue size = " + this.f6245f.c() + ", Control State {created workers= " + ((int) (2097151 & j2)) + ", blocking tasks = " + ((int) ((4398044413952L & j2) >> 21)) + ", CPUs acquired = " + (this.f6240a - ((int) ((9223367638808264704L & j2) >> 42))) + "}]";
    }

    public final c u() {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f6236i;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            c cVar = (c) this.f6246g.b((int) (2097151 & j2));
            if (cVar == null) {
                return null;
            }
            long j3 = (2097152 + j2) & (-2097152);
            int iT = t(cVar);
            if (iT >= 0 && f6236i.compareAndSet(this, j2, ((long) iT) | j3)) {
                cVar.r(f6239l);
                return cVar;
            }
        }
    }

    public final boolean w(c cVar) {
        long j2;
        int iH;
        if (cVar.i() != f6239l) {
            return false;
        }
        AtomicLongFieldUpdater atomicLongFieldUpdater = f6236i;
        do {
            j2 = atomicLongFieldUpdater.get(this);
            iH = cVar.h();
            cVar.r(this.f6246g.b((int) (2097151 & j2)));
        } while (!f6236i.compareAndSet(this, j2, ((2097152 + j2) & (-2097152)) | ((long) iH)));
        return true;
    }

    public final void x(c cVar, int i2, int i3) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f6236i;
        while (true) {
            long j2 = atomicLongFieldUpdater.get(this);
            int iT = (int) (2097151 & j2);
            long j3 = (2097152 + j2) & (-2097152);
            if (iT == i2) {
                iT = i3 == 0 ? t(cVar) : i3;
            }
            if (iT >= 0 && f6236i.compareAndSet(this, j2, j3 | ((long) iT))) {
                return;
            }
        }
    }

    public final void z(h hVar) {
        try {
            hVar.run();
        } finally {
            try {
            } finally {
            }
        }
    }

    public final class c extends Thread {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final AtomicIntegerFieldUpdater f6248i = AtomicIntegerFieldUpdater.newUpdater(c.class, "workerCtl");

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n f6249a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final y f6250b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f6251c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f6252d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f6253e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f6254f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f6255g;
        private volatile int indexInArray;
        private volatile Object nextParkedWorker;
        private volatile int workerCtl;

        public c() {
            setDaemon(true);
            this.f6249a = new n();
            this.f6250b = new y();
            this.f6251c = d.DORMANT;
            this.nextParkedWorker = a.f6239l;
            this.f6254f = a1.c.f985a.b();
        }

        public static final AtomicIntegerFieldUpdater j() {
            return f6248i;
        }

        public final void b(int i2) {
            if (i2 == 0) {
                return;
            }
            a.f6237j.addAndGet(a.this, -2097152L);
            if (this.f6251c != d.TERMINATED) {
                this.f6251c = d.DORMANT;
            }
        }

        public final void c(int i2) {
            if (i2 != 0 && u(d.BLOCKING)) {
                a.this.C();
            }
        }

        public final void d(h hVar) {
            int iB = hVar.f6273b.b();
            k(iB);
            c(iB);
            a.this.z(hVar);
            b(iB);
        }

        public final h e(boolean z2) {
            h hVarO;
            h hVarO2;
            if (z2) {
                boolean z3 = m(a.this.f6240a * 2) == 0;
                if (z3 && (hVarO2 = o()) != null) {
                    return hVarO2;
                }
                h hVarG = this.f6249a.g();
                if (hVarG != null) {
                    return hVarG;
                }
                if (!z3 && (hVarO = o()) != null) {
                    return hVarO;
                }
            } else {
                h hVarO3 = o();
                if (hVarO3 != null) {
                    return hVarO3;
                }
            }
            return v(3);
        }

        public final h f() {
            h hVarH = this.f6249a.h();
            if (hVarH != null) {
                return hVarH;
            }
            h hVar = (h) a.this.f6245f.d();
            return hVar == null ? v(1) : hVar;
        }

        public final h g(boolean z2) {
            return s() ? e(z2) : f();
        }

        public final int h() {
            return this.indexInArray;
        }

        public final Object i() {
            return this.nextParkedWorker;
        }

        public final void k(int i2) {
            this.f6252d = 0L;
            if (this.f6251c == d.PARKING) {
                this.f6251c = d.BLOCKING;
            }
        }

        public final boolean l() {
            return this.nextParkedWorker != a.f6239l;
        }

        public final int m(int i2) {
            int i3 = this.f6254f;
            int i4 = i3 ^ (i3 << 13);
            int i5 = i4 ^ (i4 >> 17);
            int i6 = i5 ^ (i5 << 5);
            this.f6254f = i6;
            int i7 = i2 - 1;
            return (i7 & i2) == 0 ? i7 & i6 : (Integer.MAX_VALUE & i6) % i2;
        }

        public final void n() {
            if (this.f6252d == 0) {
                this.f6252d = System.nanoTime() + a.this.f6242c;
            }
            LockSupport.parkNanos(a.this.f6242c);
            if (System.nanoTime() - this.f6252d >= 0) {
                this.f6252d = 0L;
                w();
            }
        }

        public final h o() {
            if (m(2) == 0) {
                h hVar = (h) a.this.f6244e.d();
                return hVar != null ? hVar : (h) a.this.f6245f.d();
            }
            h hVar2 = (h) a.this.f6245f.d();
            return hVar2 != null ? hVar2 : (h) a.this.f6244e.d();
        }

        public final void p() {
            loop0: while (true) {
                boolean z2 = false;
                while (!a.this.r() && this.f6251c != d.TERMINATED) {
                    h hVarG = g(this.f6255g);
                    if (hVarG != null) {
                        this.f6253e = 0L;
                        d(hVarG);
                    } else {
                        this.f6255g = false;
                        if (this.f6253e == 0) {
                            t();
                        } else if (z2) {
                            u(d.PARKING);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.f6253e);
                            this.f6253e = 0L;
                        } else {
                            z2 = true;
                        }
                    }
                }
                break loop0;
            }
            u(d.TERMINATED);
        }

        public final void q(int i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(a.this.f6243d);
            sb.append("-worker-");
            sb.append(i2 == 0 ? "TERMINATED" : String.valueOf(i2));
            setName(sb.toString());
            this.indexInArray = i2;
        }

        public final void r(Object obj) {
            this.nextParkedWorker = obj;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            p();
        }

        public final boolean s() {
            long j2;
            if (this.f6251c == d.CPU_ACQUIRED) {
                return true;
            }
            a aVar = a.this;
            AtomicLongFieldUpdater atomicLongFieldUpdater = a.f6237j;
            do {
                j2 = atomicLongFieldUpdater.get(aVar);
                if (((int) ((9223367638808264704L & j2) >> 42)) == 0) {
                    return false;
                }
            } while (!a.f6237j.compareAndSet(aVar, j2, j2 - 4398046511104L));
            this.f6251c = d.CPU_ACQUIRED;
            return true;
        }

        public final void t() {
            if (!l()) {
                a.this.w(this);
                return;
            }
            f6248i.set(this, -1);
            while (l() && f6248i.get(this) == -1 && !a.this.r() && this.f6251c != d.TERMINATED) {
                u(d.PARKING);
                Thread.interrupted();
                n();
            }
        }

        public final boolean u(d dVar) {
            d dVar2 = this.f6251c;
            boolean z2 = dVar2 == d.CPU_ACQUIRED;
            if (z2) {
                a.f6237j.addAndGet(a.this, 4398046511104L);
            }
            if (dVar2 != dVar) {
                this.f6251c = dVar;
            }
            return z2;
        }

        public final h v(int i2) {
            int i3 = (int) (a.f6237j.get(a.this) & 2097151);
            if (i3 < 2) {
                return null;
            }
            int iM = m(i3);
            a aVar = a.this;
            long jMin = Long.MAX_VALUE;
            for (int i4 = 0; i4 < i3; i4++) {
                iM++;
                if (iM > i3) {
                    iM = 1;
                }
                c cVar = (c) aVar.f6246g.b(iM);
                if (cVar != null && cVar != this) {
                    long jN = cVar.f6249a.n(i2, this.f6250b);
                    if (jN == -1) {
                        y yVar = this.f6250b;
                        h hVar = (h) yVar.f5059a;
                        yVar.f5059a = null;
                        return hVar;
                    }
                    if (jN > 0) {
                        jMin = Math.min(jMin, jN);
                    }
                }
            }
            if (jMin == LocationRequestCompat.PASSIVE_INTERVAL) {
                jMin = 0;
            }
            this.f6253e = jMin;
            return null;
        }

        public final void w() {
            a aVar = a.this;
            synchronized (aVar.f6246g) {
                try {
                    if (aVar.r()) {
                        return;
                    }
                    if (((int) (a.f6237j.get(aVar) & 2097151)) <= aVar.f6240a) {
                        return;
                    }
                    if (f6248i.compareAndSet(this, -1, 1)) {
                        int i2 = this.indexInArray;
                        q(0);
                        aVar.x(this, i2, 0);
                        int andDecrement = (int) (a.f6237j.getAndDecrement(aVar) & 2097151);
                        if (andDecrement != i2) {
                            Object objB = aVar.f6246g.b(andDecrement);
                            kotlin.jvm.internal.n.d(objB);
                            c cVar = (c) objB;
                            aVar.f6246g.c(i2, cVar);
                            cVar.q(i2);
                            aVar.x(cVar, andDecrement, i2);
                        }
                        aVar.f6246g.c(andDecrement, null);
                        s sVar = s.f314a;
                        this.f6251c = d.TERMINATED;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public c(a aVar, int i2) {
            this();
            q(i2);
        }
    }
}
