package g1;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import l1.AbstractC0456k;

/* JADX INFO: loaded from: classes2.dex */
public final class N extends l1.B {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4387e = AtomicIntegerFieldUpdater.newUpdater(N.class, "_decision");
    private volatile int _decision;

    public N(L0.g gVar, L0.d dVar) {
        super(gVar, dVar);
    }

    @Override // l1.B, g1.t0
    public void B(Object obj) {
        B0(obj);
    }

    @Override // l1.B, g1.AbstractC0357a
    public void B0(Object obj) {
        if (G0()) {
            return;
        }
        AbstractC0456k.c(M0.b.b(this.f5191d), AbstractC0398z.a(obj, this.f5191d), null, 2, null);
    }

    public final Object F0() {
        if (H0()) {
            return M0.c.c();
        }
        Object objH = u0.h(U());
        if (objH instanceof C0394v) {
            throw ((C0394v) objH).f4464a;
        }
        return objH;
    }

    public final boolean G0() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4387e;
        do {
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!f4387e.compareAndSet(this, 0, 2));
        return true;
    }

    public final boolean H0() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4387e;
        do {
            int i2 = atomicIntegerFieldUpdater.get(this);
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!f4387e.compareAndSet(this, 0, 1));
        return true;
    }
}
