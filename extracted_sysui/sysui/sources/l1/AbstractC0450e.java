package l1;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: l1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0450e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5216a = AtomicReferenceFieldUpdater.newUpdater(AbstractC0450e.class, Object.class, "_next");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5217b = AtomicReferenceFieldUpdater.newUpdater(AbstractC0450e.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public AbstractC0450e(AbstractC0450e abstractC0450e) {
        this._prev = abstractC0450e;
    }

    public final void b() {
        f5217b.lazySet(this, null);
    }

    public final AbstractC0450e c() {
        AbstractC0450e abstractC0450eG = g();
        while (abstractC0450eG != null && abstractC0450eG.h()) {
            abstractC0450eG = (AbstractC0450e) f5217b.get(abstractC0450eG);
        }
        return abstractC0450eG;
    }

    public final AbstractC0450e d() {
        AbstractC0450e abstractC0450eE;
        AbstractC0450e abstractC0450eE2 = e();
        kotlin.jvm.internal.n.d(abstractC0450eE2);
        while (abstractC0450eE2.h() && (abstractC0450eE = abstractC0450eE2.e()) != null) {
            abstractC0450eE2 = abstractC0450eE;
        }
        return abstractC0450eE2;
    }

    public final AbstractC0450e e() {
        Object objF = f();
        if (objF == AbstractC0449d.f5215a) {
            return null;
        }
        return (AbstractC0450e) objF;
    }

    public final Object f() {
        return f5216a.get(this);
    }

    public final AbstractC0450e g() {
        return (AbstractC0450e) f5217b.get(this);
    }

    public abstract boolean h();

    public final boolean i() {
        return e() == null;
    }

    public final boolean j() {
        return f5216a.compareAndSet(this, null, AbstractC0449d.f5215a);
    }

    public final void k() {
        Object obj;
        if (i()) {
            return;
        }
        while (true) {
            AbstractC0450e abstractC0450eC = c();
            AbstractC0450e abstractC0450eD = d();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5217b;
            do {
                obj = atomicReferenceFieldUpdater.get(abstractC0450eD);
            } while (!atomicReferenceFieldUpdater.compareAndSet(abstractC0450eD, obj, ((AbstractC0450e) obj) == null ? null : abstractC0450eC));
            if (abstractC0450eC != null) {
                f5216a.set(abstractC0450eC, abstractC0450eD);
            }
            if (!abstractC0450eD.h() || abstractC0450eD.i()) {
                if (abstractC0450eC == null || !abstractC0450eC.h()) {
                    return;
                }
            }
        }
    }

    public final boolean l(AbstractC0450e abstractC0450e) {
        return f5216a.compareAndSet(this, null, abstractC0450e);
    }
}
