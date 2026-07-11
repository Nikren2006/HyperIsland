package l1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5242a = AtomicReferenceFieldUpdater.newUpdater(r.class, Object.class, "_cur");
    private volatile Object _cur;

    public r(boolean z2) {
        this._cur = new s(8, z2);
    }

    public final boolean a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5242a;
        while (true) {
            s sVar = (s) atomicReferenceFieldUpdater.get(this);
            int iA = sVar.a(obj);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                f5242a.compareAndSet(this, sVar, sVar.i());
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5242a;
        while (true) {
            s sVar = (s) atomicReferenceFieldUpdater.get(this);
            if (sVar.d()) {
                return;
            } else {
                f5242a.compareAndSet(this, sVar, sVar.i());
            }
        }
    }

    public final int c() {
        return ((s) f5242a.get(this)).f();
    }

    public final Object d() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5242a;
        while (true) {
            s sVar = (s) atomicReferenceFieldUpdater.get(this);
            Object objJ = sVar.j();
            if (objJ != s.f5246h) {
                return objJ;
            }
            f5242a.compareAndSet(this, sVar, sVar.i());
        }
    }
}
