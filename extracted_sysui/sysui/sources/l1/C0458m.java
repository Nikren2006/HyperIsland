package l1;

import g1.InterfaceC0377k;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: renamed from: l1.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0458m extends g1.B implements g1.L {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f5228f = AtomicIntegerFieldUpdater.newUpdater(C0458m.class, "runningWorkers");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g1.B f5229a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5230b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g1.L f5231c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r f5232d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f5233e;
    private volatile int runningWorkers;

    /* JADX INFO: renamed from: l1.m$a */
    public final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Runnable f5234a;

        public a(Runnable runnable) {
            this.f5234a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = 0;
            while (true) {
                try {
                    this.f5234a.run();
                } catch (Throwable th) {
                    g1.D.a(L0.h.f402a, th);
                }
                Runnable runnableB = C0458m.this.B();
                if (runnableB == null) {
                    return;
                }
                this.f5234a = runnableB;
                i2++;
                if (i2 >= 16 && C0458m.this.f5229a.isDispatchNeeded(C0458m.this)) {
                    C0458m.this.f5229a.dispatch(C0458m.this, this);
                    return;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0458m(g1.B b2, int i2) {
        this.f5229a = b2;
        this.f5230b = i2;
        g1.L l2 = b2 instanceof g1.L ? (g1.L) b2 : null;
        this.f5231c = l2 == null ? g1.K.a() : l2;
        this.f5232d = new r(false);
        this.f5233e = new Object();
    }

    public final Runnable B() {
        while (true) {
            Runnable runnable = (Runnable) this.f5232d.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.f5233e) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5228f;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f5232d.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean C() {
        synchronized (this.f5233e) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5228f;
            if (atomicIntegerFieldUpdater.get(this) >= this.f5230b) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        Runnable runnableB;
        this.f5232d.a(runnable);
        if (f5228f.get(this) >= this.f5230b || !C() || (runnableB = B()) == null) {
            return;
        }
        this.f5229a.dispatch(this, new a(runnableB));
    }

    @Override // g1.B
    public void dispatchYield(L0.g gVar, Runnable runnable) {
        Runnable runnableB;
        this.f5232d.a(runnable);
        if (f5228f.get(this) >= this.f5230b || !C() || (runnableB = B()) == null) {
            return;
        }
        this.f5229a.dispatchYield(this, new a(runnableB));
    }

    @Override // g1.B
    public g1.B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return i2 >= this.f5230b ? this : super.limitedParallelism(i2);
    }

    @Override // g1.L
    public void t(long j2, InterfaceC0377k interfaceC0377k) {
        this.f5231c.t(j2, interfaceC0377k);
    }
}
