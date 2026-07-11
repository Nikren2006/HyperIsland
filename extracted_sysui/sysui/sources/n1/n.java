package n1;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.y;

/* JADX INFO: loaded from: classes2.dex */
public final class n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f6285b = AtomicReferenceFieldUpdater.newUpdater(n.class, Object.class, "lastScheduledTask");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f6286c = AtomicIntegerFieldUpdater.newUpdater(n.class, "producerIndex");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f6287d = AtomicIntegerFieldUpdater.newUpdater(n.class, "consumerIndex");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f6288e = AtomicIntegerFieldUpdater.newUpdater(n.class, "blockingTasksInBuffer");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceArray f6289a = new AtomicReferenceArray(128);
    private volatile int blockingTasksInBuffer;
    private volatile int consumerIndex;
    private volatile Object lastScheduledTask;
    private volatile int producerIndex;

    public final h a(h hVar, boolean z2) {
        if (z2) {
            return b(hVar);
        }
        h hVar2 = (h) f6285b.getAndSet(this, hVar);
        if (hVar2 == null) {
            return null;
        }
        return b(hVar2);
    }

    public final h b(h hVar) {
        if (d() == 127) {
            return hVar;
        }
        if (hVar.f6273b.b() == 1) {
            f6288e.incrementAndGet(this);
        }
        int i2 = f6286c.get(this) & 127;
        while (this.f6289a.get(i2) != null) {
            Thread.yield();
        }
        this.f6289a.lazySet(i2, hVar);
        f6286c.incrementAndGet(this);
        return null;
    }

    public final void c(h hVar) {
        if (hVar == null || hVar.f6273b.b() != 1) {
            return;
        }
        f6288e.decrementAndGet(this);
    }

    public final int d() {
        return f6286c.get(this) - f6287d.get(this);
    }

    public final int e() {
        Object obj = f6285b.get(this);
        int iD = d();
        return obj != null ? iD + 1 : iD;
    }

    public final void f(d dVar) {
        h hVar = (h) f6285b.getAndSet(this, null);
        if (hVar != null) {
            dVar.a(hVar);
        }
        while (j(dVar)) {
        }
    }

    public final h g() {
        h hVar = (h) f6285b.getAndSet(this, null);
        return hVar == null ? i() : hVar;
    }

    public final h h() {
        return k(true);
    }

    public final h i() {
        h hVar;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f6287d;
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 - f6286c.get(this) == 0) {
                return null;
            }
            int i3 = i2 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i2, i2 + 1) && (hVar = (h) this.f6289a.getAndSet(i3, null)) != null) {
                c(hVar);
                return hVar;
            }
        }
    }

    public final boolean j(d dVar) {
        h hVarI = i();
        if (hVarI == null) {
            return false;
        }
        dVar.a(hVarI);
        return true;
    }

    public final h k(boolean z2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        h hVar;
        do {
            atomicReferenceFieldUpdater = f6285b;
            hVar = (h) atomicReferenceFieldUpdater.get(this);
            if (hVar != null) {
                if ((hVar.f6273b.b() == 1) == z2) {
                }
            }
            int i2 = f6287d.get(this);
            int i3 = f6286c.get(this);
            while (i2 != i3) {
                if (z2 && f6288e.get(this) == 0) {
                    return null;
                }
                i3--;
                h hVarM = m(i3, z2);
                if (hVarM != null) {
                    return hVarM;
                }
            }
            return null;
        } while (!atomicReferenceFieldUpdater.compareAndSet(this, hVar, null));
        return hVar;
    }

    public final h l(int i2) {
        int i3 = f6287d.get(this);
        int i4 = f6286c.get(this);
        boolean z2 = i2 == 1;
        while (i3 != i4) {
            if (z2 && f6288e.get(this) == 0) {
                return null;
            }
            int i5 = i3 + 1;
            h hVarM = m(i3, z2);
            if (hVarM != null) {
                return hVarM;
            }
            i3 = i5;
        }
        return null;
    }

    public final h m(int i2, boolean z2) {
        int i3 = i2 & 127;
        h hVar = (h) this.f6289a.get(i3);
        if (hVar != null) {
            if ((hVar.f6273b.b() == 1) == z2 && this.f6289a.compareAndSet(i3, hVar, null)) {
                if (z2) {
                    f6288e.decrementAndGet(this);
                }
                return hVar;
            }
        }
        return null;
    }

    public final long n(int i2, y yVar) {
        h hVarI = i2 == 3 ? i() : l(i2);
        if (hVarI == null) {
            return o(i2, yVar);
        }
        yVar.f5059a = hVarI;
        return -1L;
    }

    public final long o(int i2, y yVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        h hVar;
        do {
            atomicReferenceFieldUpdater = f6285b;
            hVar = (h) atomicReferenceFieldUpdater.get(this);
            if (hVar == null) {
                return -2L;
            }
            if (((hVar.f6273b.b() != 1 ? 2 : 1) & i2) == 0) {
                return -2L;
            }
            long jA = l.f6281f.a() - hVar.f6272a;
            long j2 = l.f6277b;
            if (jA < j2) {
                return j2 - jA;
            }
        } while (!atomicReferenceFieldUpdater.compareAndSet(this, hVar, null));
        yVar.f5059a = hVar;
        return -1L;
    }
}
