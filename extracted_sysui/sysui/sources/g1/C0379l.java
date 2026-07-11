package g1;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import g1.InterfaceC0380l0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import l1.C0455j;

/* JADX INFO: renamed from: g1.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0379l extends O implements InterfaceC0377k, N0.e, O0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4425f = AtomicIntegerFieldUpdater.newUpdater(C0379l.class, "_decisionAndIndex");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4426g = AtomicReferenceFieldUpdater.newUpdater(C0379l.class, Object.class, "_state");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4427h = AtomicReferenceFieldUpdater.newUpdater(C0379l.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final L0.d f4428d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L0.g f4429e;

    public C0379l(L0.d dVar, int i2) {
        super(i2);
        this.f4428d = dVar;
        this.f4429e = dVar.getContext();
        this._decisionAndIndex = 536870911;
        this._state = C0363d.f4415a;
    }

    public static /* synthetic */ void M(C0379l c0379l, Object obj, int i2, Function1 function1, int i3, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resumeImpl");
        }
        if ((i3 & 4) != 0) {
            function1 = null;
        }
        c0379l.L(obj, i2, function1);
    }

    private final boolean O() {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4425f;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!f4425f.compareAndSet(this, i2, BasicMeasure.EXACTLY + (536870911 & i2)));
        return true;
    }

    private final boolean Q() {
        int i2;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4425f;
        do {
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!f4425f.compareAndSet(this, i2, 536870912 + (536870911 & i2)));
        return true;
    }

    public void A() {
        S sB = B();
        if (sB != null && D()) {
            sB.dispose();
            f4427h.set(this, y0.f4468a);
        }
    }

    public final S B() {
        InterfaceC0380l0 interfaceC0380l0 = (InterfaceC0380l0) getContext().get(InterfaceC0380l0.f4430z);
        if (interfaceC0380l0 == null) {
            return null;
        }
        S sD = InterfaceC0380l0.a.d(interfaceC0380l0, true, false, new C0387p(this), 2, null);
        f4427h.compareAndSet(this, null, sD);
        return sD;
    }

    public final void C(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof C0363d)) {
                if (obj2 instanceof AbstractC0373i ? true : obj2 instanceof l1.C) {
                    G(obj, obj2);
                } else {
                    if (obj2 instanceof C0394v) {
                        C0394v c0394v = (C0394v) obj2;
                        if (!c0394v.b()) {
                            G(obj, obj2);
                        }
                        if (obj2 instanceof C0385o) {
                            if (!(obj2 instanceof C0394v)) {
                                c0394v = null;
                            }
                            Throwable th = c0394v != null ? c0394v.f4464a : null;
                            if (obj instanceof AbstractC0373i) {
                                k((AbstractC0373i) obj, th);
                                return;
                            } else {
                                kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                                n((l1.C) obj, th);
                                return;
                            }
                        }
                        return;
                    }
                    if (obj2 instanceof C0393u) {
                        C0393u c0393u = (C0393u) obj2;
                        if (c0393u.f4452b != null) {
                            G(obj, obj2);
                        }
                        if (obj instanceof l1.C) {
                            return;
                        }
                        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        AbstractC0373i abstractC0373i = (AbstractC0373i) obj;
                        if (c0393u.c()) {
                            k(abstractC0373i, c0393u.f4455e);
                            return;
                        } else {
                            if (f4426g.compareAndSet(this, obj2, C0393u.b(c0393u, null, abstractC0373i, null, null, null, 29, null))) {
                                return;
                            }
                        }
                    } else {
                        if (obj instanceof l1.C) {
                            return;
                        }
                        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                        if (f4426g.compareAndSet(this, obj2, new C0393u(obj2, (AbstractC0373i) obj, null, null, null, 28, null))) {
                            return;
                        }
                    }
                }
            } else if (f4426g.compareAndSet(this, obj2, obj)) {
                return;
            }
        }
    }

    public boolean D() {
        return !(y() instanceof z0);
    }

    public final boolean E() {
        if (P.c(this.f4389c)) {
            L0.d dVar = this.f4428d;
            kotlin.jvm.internal.n.e(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            if (((C0455j) dVar).o()) {
                return true;
            }
        }
        return false;
    }

    public final AbstractC0373i F(Function1 function1) {
        return function1 instanceof AbstractC0373i ? (AbstractC0373i) function1 : new C0374i0(function1);
    }

    public final void G(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public String H() {
        return "CancellableContinuation";
    }

    public final void I(Throwable th) {
        if (q(th)) {
            return;
        }
        o(th);
        s();
    }

    public final void J() {
        Throwable thS;
        L0.d dVar = this.f4428d;
        C0455j c0455j = dVar instanceof C0455j ? (C0455j) dVar : null;
        if (c0455j == null || (thS = c0455j.s(this)) == null) {
            return;
        }
        r();
        o(thS);
    }

    public final boolean K() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if ((obj instanceof C0393u) && ((C0393u) obj).f4454d != null) {
            r();
            return false;
        }
        f4425f.set(this, 536870911);
        atomicReferenceFieldUpdater.set(this, C0363d.f4415a);
        return true;
    }

    public final void L(Object obj, int i2, Function1 function1) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        do {
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof z0)) {
                if (obj2 instanceof C0385o) {
                    C0385o c0385o = (C0385o) obj2;
                    if (c0385o.c()) {
                        if (function1 != null) {
                            l(function1, c0385o.f4464a);
                            return;
                        }
                        return;
                    }
                }
                j(obj);
                throw new H0.c();
            }
        } while (!f4426g.compareAndSet(this, obj2, N((z0) obj2, obj, i2, function1, null)));
        s();
        u(i2);
    }

    public final Object N(z0 z0Var, Object obj, int i2, Function1 function1, Object obj2) {
        if (obj instanceof C0394v) {
            return obj;
        }
        if (!P.b(i2) && obj2 == null) {
            return obj;
        }
        if (function1 == null && !(z0Var instanceof AbstractC0373i) && obj2 == null) {
            return obj;
        }
        return new C0393u(obj, z0Var instanceof AbstractC0373i ? (AbstractC0373i) z0Var : null, function1, obj2, null, 16, null);
    }

    public final l1.F P(Object obj, Object obj2, Function1 function1) {
        Object obj3;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        do {
            obj3 = atomicReferenceFieldUpdater.get(this);
            if (!(obj3 instanceof z0)) {
                if ((obj3 instanceof C0393u) && obj2 != null && ((C0393u) obj3).f4454d == obj2) {
                    return AbstractC0381m.f4432a;
                }
                return null;
            }
        } while (!f4426g.compareAndSet(this, obj3, N((z0) obj3, obj, this.f4389c, function1, obj2)));
        s();
        return AbstractC0381m.f4432a;
    }

    @Override // g1.O
    public void a(Object obj, Throwable th) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        while (true) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof z0) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof C0394v) {
                return;
            }
            if (obj2 instanceof C0393u) {
                C0393u c0393u = (C0393u) obj2;
                if (c0393u.c()) {
                    throw new IllegalStateException("Must be called at most once");
                }
                if (f4426g.compareAndSet(this, obj2, C0393u.b(c0393u, null, null, null, null, th, 15, null))) {
                    c0393u.d(this, th);
                    return;
                }
            } else if (f4426g.compareAndSet(this, obj2, new C0393u(obj2, null, null, null, th, 14, null))) {
                return;
            }
        }
    }

    @Override // g1.O0
    public void b(l1.C c2, int i2) {
        int i3;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4425f;
        do {
            i3 = atomicIntegerFieldUpdater.get(this);
            if ((i3 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, ((i3 >> 29) << 29) + i2));
        C(c2);
    }

    @Override // g1.O
    public final L0.d c() {
        return this.f4428d;
    }

    @Override // g1.O
    public Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // g1.O
    public Object e(Object obj) {
        return obj instanceof C0393u ? ((C0393u) obj).f4451a : obj;
    }

    @Override // g1.InterfaceC0377k
    public void g(Function1 function1) {
        C(F(function1));
    }

    @Override // N0.e
    public N0.e getCallerFrame() {
        L0.d dVar = this.f4428d;
        if (dVar instanceof N0.e) {
            return (N0.e) dVar;
        }
        return null;
    }

    @Override // L0.d
    public L0.g getContext() {
        return this.f4429e;
    }

    @Override // g1.InterfaceC0377k
    public void h(Object obj, Function1 function1) {
        L(obj, this.f4389c, function1);
    }

    @Override // g1.O
    public Object i() {
        return y();
    }

    public final Void j(Object obj) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
    }

    public final void k(AbstractC0373i abstractC0373i, Throwable th) {
        try {
            abstractC0373i.b(th);
        } catch (Throwable th2) {
            D.a(getContext(), new C0397y("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void l(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            D.a(getContext(), new C0397y("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    @Override // g1.InterfaceC0377k
    public Object m(Object obj, Object obj2, Function1 function1) {
        return P(obj, obj2, function1);
    }

    public final void n(l1.C c2, Throwable th) {
        int i2 = f4425f.get(this) & 536870911;
        if (i2 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            c2.o(i2, th, getContext());
        } catch (Throwable th2) {
            D.a(getContext(), new C0397y("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public boolean o(Throwable th) {
        Object obj;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4426g;
        do {
            obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof z0)) {
                return false;
            }
        } while (!f4426g.compareAndSet(this, obj, new C0385o(this, th, (obj instanceof AbstractC0373i) || (obj instanceof l1.C))));
        z0 z0Var = (z0) obj;
        if (z0Var instanceof AbstractC0373i) {
            k((AbstractC0373i) obj, th);
        } else if (z0Var instanceof l1.C) {
            n((l1.C) obj, th);
        }
        s();
        u(this.f4389c);
        return true;
    }

    @Override // g1.InterfaceC0377k
    public void p(B b2, Object obj) {
        L0.d dVar = this.f4428d;
        C0455j c0455j = dVar instanceof C0455j ? (C0455j) dVar : null;
        M(this, obj, (c0455j != null ? c0455j.f5222d : null) == b2 ? 4 : this.f4389c, null, 4, null);
    }

    public final boolean q(Throwable th) {
        if (!E()) {
            return false;
        }
        L0.d dVar = this.f4428d;
        kotlin.jvm.internal.n.e(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return ((C0455j) dVar).q(th);
    }

    public final void r() {
        S sW = w();
        if (sW == null) {
            return;
        }
        sW.dispose();
        f4427h.set(this, y0.f4468a);
    }

    @Override // L0.d
    public void resumeWith(Object obj) {
        M(this, AbstractC0398z.b(obj, this), this.f4389c, null, 4, null);
    }

    public final void s() {
        if (E()) {
            return;
        }
        r();
    }

    @Override // g1.InterfaceC0377k
    public void t(Object obj) {
        u(this.f4389c);
    }

    public String toString() {
        return H() + '(' + I.c(this.f4428d) + "){" + z() + "}@" + I.b(this);
    }

    public final void u(int i2) {
        if (O()) {
            return;
        }
        P.a(this, i2);
    }

    public Throwable v(InterfaceC0380l0 interfaceC0380l0) {
        return interfaceC0380l0.f();
    }

    public final S w() {
        return (S) f4427h.get(this);
    }

    public final Object x() {
        InterfaceC0380l0 interfaceC0380l0;
        boolean zE = E();
        if (Q()) {
            if (w() == null) {
                B();
            }
            if (zE) {
                J();
            }
            return M0.c.c();
        }
        if (zE) {
            J();
        }
        Object objY = y();
        if (objY instanceof C0394v) {
            throw ((C0394v) objY).f4464a;
        }
        if (!P.b(this.f4389c) || (interfaceC0380l0 = (InterfaceC0380l0) getContext().get(InterfaceC0380l0.f4430z)) == null || interfaceC0380l0.isActive()) {
            return e(objY);
        }
        CancellationException cancellationExceptionF = interfaceC0380l0.f();
        a(objY, cancellationExceptionF);
        throw cancellationExceptionF;
    }

    public final Object y() {
        return f4426g.get(this);
    }

    public final String z() {
        Object objY = y();
        return objY instanceof z0 ? "Active" : objY instanceof C0385o ? "Cancelled" : "Completed";
    }
}
