package j1;

import H0.j;
import g1.C0379l;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes2.dex */
public final class L extends k1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4680a = AtomicReferenceFieldUpdater.newUpdater(L.class, Object.class, "_state");
    private volatile Object _state;

    @Override // k1.d
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(J j2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4680a;
        if (atomicReferenceFieldUpdater.get(this) != null) {
            return false;
        }
        atomicReferenceFieldUpdater.set(this, K.f4678a);
        return true;
    }

    public final Object e(L0.d dVar) {
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        if (!f4680a.compareAndSet(this, K.f4678a, c0379l)) {
            j.a aVar = H0.j.f299a;
            c0379l.resumeWith(H0.j.a(H0.s.f314a));
        }
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    @Override // k1.d
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public L0.d[] b(J j2) {
        f4680a.set(this, null);
        return k1.c.f4953a;
    }

    public final void g() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4680a;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null || obj == K.f4679b) {
                return;
            }
            if (obj == K.f4678a) {
                if (f4680a.compareAndSet(this, obj, K.f4679b)) {
                    return;
                }
            } else if (f4680a.compareAndSet(this, obj, K.f4678a)) {
                j.a aVar = H0.j.f299a;
                ((C0379l) obj).resumeWith(H0.j.a(H0.s.f314a));
                return;
            }
        }
    }

    public final boolean h() {
        Object andSet = f4680a.getAndSet(this, K.f4678a);
        kotlin.jvm.internal.n.d(andSet);
        return andSet == K.f4679b;
    }
}
