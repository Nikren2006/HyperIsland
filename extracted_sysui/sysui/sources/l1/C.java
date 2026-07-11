package l1;

import g1.z0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public abstract class C extends AbstractC0450e implements z0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f5192d = AtomicIntegerFieldUpdater.newUpdater(C.class, "cleanedAndPointers");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f5193c;
    private volatile int cleanedAndPointers;

    public C(long j2, C c2, int i2) {
        super(c2);
        this.f5193c = j2;
        this.cleanedAndPointers = i2 << 16;
    }

    @Override // l1.AbstractC0450e
    public boolean h() {
        return f5192d.get(this) == n() && !i();
    }

    public final boolean m() {
        return f5192d.addAndGet(this, -65536) == n() && !i();
    }

    public abstract int n();

    public abstract void o(int i2, Throwable th, L0.g gVar);

    public final void p() {
        if (f5192d.incrementAndGet(this) == n()) {
            k();
        }
    }

    public final boolean q() {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f5192d;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 == n() && !i()) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 65536 + i2));
        return true;
    }
}
