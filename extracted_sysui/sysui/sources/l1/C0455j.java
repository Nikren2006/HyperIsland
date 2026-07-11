package l1;

import g1.AbstractC0398z;
import g1.C0379l;
import g1.C0395w;
import g1.H0;
import g1.InterfaceC0377k;
import g1.V;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: l1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0455j extends g1.O implements N0.e, L0.d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f5221h = AtomicReferenceFieldUpdater.newUpdater(C0455j.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g1.B f5222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L0.d f5223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f5224f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f5225g;

    public C0455j(g1.B b2, L0.d dVar) {
        super(-1);
        this.f5222d = b2;
        this.f5223e = dVar;
        this.f5224f = AbstractC0456k.f5226a;
        this.f5225g = J.b(getContext());
    }

    @Override // g1.O
    public void a(Object obj, Throwable th) {
        if (obj instanceof C0395w) {
            ((C0395w) obj).f4467b.invoke(th);
        }
    }

    @Override // g1.O
    public L0.d c() {
        return this;
    }

    @Override // N0.e
    public N0.e getCallerFrame() {
        L0.d dVar = this.f5223e;
        if (dVar instanceof N0.e) {
            return (N0.e) dVar;
        }
        return null;
    }

    @Override // L0.d
    public L0.g getContext() {
        return this.f5223e.getContext();
    }

    @Override // g1.O
    public Object i() {
        Object obj = this.f5224f;
        this.f5224f = AbstractC0456k.f5226a;
        return obj;
    }

    public final void j() {
        while (f5221h.get(this) == AbstractC0456k.f5227b) {
        }
    }

    public final C0379l k() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5221h;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj == null) {
                f5221h.set(this, AbstractC0456k.f5227b);
                return null;
            }
            if (obj instanceof C0379l) {
                if (f5221h.compareAndSet(this, obj, AbstractC0456k.f5227b)) {
                    return (C0379l) obj;
                }
            } else if (obj != AbstractC0456k.f5227b && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
    }

    public final void l(L0.g gVar, Object obj) {
        this.f5224f = obj;
        this.f4389c = 1;
        this.f5222d.dispatchYield(gVar, this);
    }

    public final C0379l n() {
        Object obj = f5221h.get(this);
        if (obj instanceof C0379l) {
            return (C0379l) obj;
        }
        return null;
    }

    public final boolean o() {
        return f5221h.get(this) != null;
    }

    public final boolean q(Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5221h;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            F f2 = AbstractC0456k.f5227b;
            if (kotlin.jvm.internal.n.c(obj, f2)) {
                if (f5221h.compareAndSet(this, f2, th)) {
                    return true;
                }
            } else {
                if (obj instanceof Throwable) {
                    return true;
                }
                if (f5221h.compareAndSet(this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void r() {
        j();
        C0379l c0379lN = n();
        if (c0379lN != null) {
            c0379lN.r();
        }
    }

    @Override // L0.d
    public void resumeWith(Object obj) {
        L0.g context = this.f5223e.getContext();
        Object objD = AbstractC0398z.d(obj, null, 1, null);
        if (this.f5222d.isDispatchNeeded(context)) {
            this.f5224f = objD;
            this.f4389c = 0;
            this.f5222d.dispatch(context, this);
            return;
        }
        V vA = H0.f4374a.a();
        if (vA.F()) {
            this.f5224f = objD;
            this.f4389c = 0;
            vA.B(this);
            return;
        }
        vA.D(true);
        try {
            L0.g context2 = getContext();
            Object objC = J.c(context2, this.f5225g);
            try {
                this.f5223e.resumeWith(obj);
                H0.s sVar = H0.s.f314a;
                while (vA.H()) {
                }
            } finally {
                J.a(context2, objC);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final Throwable s(InterfaceC0377k interfaceC0377k) {
        F f2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f5221h;
        do {
            Object obj = atomicReferenceFieldUpdater.get(this);
            f2 = AbstractC0456k.f5227b;
            if (obj != f2) {
                if (obj instanceof Throwable) {
                    if (f5221h.compareAndSet(this, obj, null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.");
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!f5221h.compareAndSet(this, f2, interfaceC0377k));
        return null;
    }

    public String toString() {
        return "DispatchedContinuation[" + this.f5222d + ", " + g1.I.c(this.f5223e) + ']';
    }
}
