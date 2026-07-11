package g1;

import L0.g;
import g1.InterfaceC0380l0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import l1.q;

/* JADX INFO: loaded from: classes2.dex */
public class t0 implements InterfaceC0380l0, InterfaceC0391s, A0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4439a = AtomicReferenceFieldUpdater.newUpdater(t0.class, Object.class, "_state");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f4440b = AtomicReferenceFieldUpdater.newUpdater(t0.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    public static final class a extends s0 {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final t0 f4441e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final b f4442f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final r f4443g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final Object f4444h;

        public a(t0 t0Var, b bVar, r rVar, Object obj) {
            this.f4441e = t0Var;
            this.f4442f = bVar;
            this.f4443g = rVar;
            this.f4444h = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            r((Throwable) obj);
            return H0.s.f314a;
        }

        @Override // g1.AbstractC0396x
        public void r(Throwable th) {
            this.f4441e.K(this.f4442f, this.f4443g, this.f4444h);
        }
    }

    public static final class b implements InterfaceC0370g0 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final AtomicIntegerFieldUpdater f4445b = AtomicIntegerFieldUpdater.newUpdater(b.class, "_isCompleting");

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final AtomicReferenceFieldUpdater f4446c = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_rootCause");

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final AtomicReferenceFieldUpdater f4447d = AtomicReferenceFieldUpdater.newUpdater(b.class, Object.class, "_exceptionsHolder");
        private volatile Object _exceptionsHolder;
        private volatile int _isCompleting;
        private volatile Object _rootCause;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final x0 f4448a;

        public b(x0 x0Var, boolean z2, Throwable th) {
            this.f4448a = x0Var;
            this._isCompleting = z2 ? 1 : 0;
            this._rootCause = th;
        }

        public final void a(Throwable th) {
            Throwable thE = e();
            if (thE == null) {
                l(th);
                return;
            }
            if (th == thE) {
                return;
            }
            Object objD = d();
            if (objD == null) {
                k(th);
                return;
            }
            if (objD instanceof Throwable) {
                if (th == objD) {
                    return;
                }
                ArrayList arrayListB = b();
                arrayListB.add(objD);
                arrayListB.add(th);
                k(arrayListB);
                return;
            }
            if (objD instanceof ArrayList) {
                ((ArrayList) objD).add(th);
                return;
            }
            throw new IllegalStateException(("State is " + objD).toString());
        }

        public final ArrayList b() {
            return new ArrayList(4);
        }

        @Override // g1.InterfaceC0370g0
        public x0 c() {
            return this.f4448a;
        }

        public final Object d() {
            return f4447d.get(this);
        }

        public final Throwable e() {
            return (Throwable) f4446c.get(this);
        }

        public final boolean f() {
            return e() != null;
        }

        public final boolean g() {
            return f4445b.get(this) != 0;
        }

        public final boolean h() {
            return d() == u0.f4460e;
        }

        public final List i(Throwable th) {
            ArrayList arrayListB;
            Object objD = d();
            if (objD == null) {
                arrayListB = b();
            } else if (objD instanceof Throwable) {
                ArrayList arrayListB2 = b();
                arrayListB2.add(objD);
                arrayListB = arrayListB2;
            } else {
                if (!(objD instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + objD).toString());
                }
                arrayListB = (ArrayList) objD;
            }
            Throwable thE = e();
            if (thE != null) {
                arrayListB.add(0, thE);
            }
            if (th != null && !kotlin.jvm.internal.n.c(th, thE)) {
                arrayListB.add(th);
            }
            k(u0.f4460e);
            return arrayListB;
        }

        @Override // g1.InterfaceC0370g0
        public boolean isActive() {
            return e() == null;
        }

        public final void j(boolean z2) {
            f4445b.set(this, z2 ? 1 : 0);
        }

        public final void k(Object obj) {
            f4447d.set(this, obj);
        }

        public final void l(Throwable th) {
            f4446c.set(this, th);
        }

        public String toString() {
            return "Finishing[cancelling=" + f() + ", completing=" + g() + ", rootCause=" + e() + ", exceptions=" + d() + ", list=" + c() + ']';
        }
    }

    public static final class c extends q.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ t0 f4449d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f4450e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(l1.q qVar, t0 t0Var, Object obj) {
            super(qVar);
            this.f4449d = t0Var;
            this.f4450e = obj;
        }

        @Override // l1.AbstractC0447b
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Object d(l1.q qVar) {
            if (this.f4449d.U() == this.f4450e) {
                return null;
            }
            return l1.p.a();
        }
    }

    public t0(boolean z2) {
        this._state = z2 ? u0.f4462g : u0.f4461f;
    }

    public static /* synthetic */ CancellationException u0(t0 t0Var, Throwable th, String str, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i2 & 1) != 0) {
            str = null;
        }
        return t0Var.t0(th, str);
    }

    public final void A(Throwable th, List list) throws IllegalAccessException, InvocationTargetException {
        if (list.size() <= 1) {
            return;
        }
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(list.size()));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Throwable th2 = (Throwable) it.next();
            if (th2 != th && th2 != th && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                H0.a.a(th, th2);
            }
        }
    }

    public final boolean A0(b bVar, r rVar, Object obj) {
        while (InterfaceC0380l0.a.d(rVar.f4437e, false, false, new a(this, bVar, rVar, obj), 1, null) == y0.f4468a) {
            rVar = h0(rVar);
            if (rVar == null) {
                return false;
            }
        }
        return true;
    }

    public void B(Object obj) {
    }

    public final boolean C(Throwable th) {
        return D(th);
    }

    public final boolean D(Object obj) throws Throwable {
        Object objD0 = u0.f4456a;
        if (R() && (objD0 = F(obj)) == u0.f4457b) {
            return true;
        }
        if (objD0 == u0.f4456a) {
            objD0 = d0(obj);
        }
        if (objD0 == u0.f4456a || objD0 == u0.f4457b) {
            return true;
        }
        if (objD0 == u0.f4459d) {
            return false;
        }
        B(objD0);
        return true;
    }

    public void E(Throwable th) throws Throwable {
        D(th);
    }

    public final Object F(Object obj) {
        Object objY0;
        do {
            Object objU = U();
            if (!(objU instanceof InterfaceC0370g0) || ((objU instanceof b) && ((b) objU).g())) {
                return u0.f4456a;
            }
            objY0 = y0(objU, new C0394v(L(obj), false, 2, null));
        } while (objY0 == u0.f4458c);
        return objY0;
    }

    public final boolean G(Throwable th) {
        if (a0()) {
            return true;
        }
        boolean z2 = th instanceof CancellationException;
        InterfaceC0389q interfaceC0389qT = T();
        return (interfaceC0389qT == null || interfaceC0389qT == y0.f4468a) ? z2 : interfaceC0389qT.b(th) || z2;
    }

    public String H() {
        return "Job was cancelled";
    }

    public boolean I(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return D(th) && Q();
    }

    public final void J(InterfaceC0370g0 interfaceC0370g0, Object obj) throws Throwable {
        InterfaceC0389q interfaceC0389qT = T();
        if (interfaceC0389qT != null) {
            interfaceC0389qT.dispose();
            q0(y0.f4468a);
        }
        C0394v c0394v = obj instanceof C0394v ? (C0394v) obj : null;
        Throwable th = c0394v != null ? c0394v.f4464a : null;
        if (!(interfaceC0370g0 instanceof s0)) {
            x0 x0VarC = interfaceC0370g0.c();
            if (x0VarC != null) {
                j0(x0VarC, th);
                return;
            }
            return;
        }
        try {
            ((s0) interfaceC0370g0).r(th);
        } catch (Throwable th2) {
            W(new C0397y("Exception in completion handler " + interfaceC0370g0 + " for " + this, th2));
        }
    }

    public final void K(b bVar, r rVar, Object obj) {
        r rVarH0 = h0(rVar);
        if (rVarH0 == null || !A0(bVar, rVarH0, obj)) {
            B(M(bVar, obj));
        }
    }

    public final Throwable L(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new C0382m0(H(), null, this) : th;
        }
        kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((A0) obj).n();
    }

    public final Object M(b bVar, Object obj) throws Throwable {
        boolean zF;
        Throwable thP;
        C0394v c0394v = obj instanceof C0394v ? (C0394v) obj : null;
        Throwable th = c0394v != null ? c0394v.f4464a : null;
        synchronized (bVar) {
            zF = bVar.f();
            List listI = bVar.i(th);
            thP = P(bVar, listI);
            if (thP != null) {
                A(thP, listI);
            }
        }
        if (thP != null && thP != th) {
            obj = new C0394v(thP, false, 2, null);
        }
        if (thP != null && (G(thP) || V(thP))) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            ((C0394v) obj).b();
        }
        if (!zF) {
            k0(thP);
        }
        l0(obj);
        f4439a.compareAndSet(this, bVar, u0.g(obj));
        J(bVar, obj);
        return obj;
    }

    public final r N(InterfaceC0370g0 interfaceC0370g0) {
        r rVar = interfaceC0370g0 instanceof r ? (r) interfaceC0370g0 : null;
        if (rVar != null) {
            return rVar;
        }
        x0 x0VarC = interfaceC0370g0.c();
        if (x0VarC != null) {
            return h0(x0VarC);
        }
        return null;
    }

    public final Throwable O(Object obj) {
        C0394v c0394v = obj instanceof C0394v ? (C0394v) obj : null;
        if (c0394v != null) {
            return c0394v.f4464a;
        }
        return null;
    }

    public final Throwable P(b bVar, List list) {
        Object obj = null;
        if (list.isEmpty()) {
            if (bVar.f()) {
                return new C0382m0(H(), null, this);
            }
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                obj = next;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        return th != null ? th : (Throwable) list.get(0);
    }

    public boolean Q() {
        return true;
    }

    public boolean R() {
        return false;
    }

    public final x0 S(InterfaceC0370g0 interfaceC0370g0) {
        x0 x0VarC = interfaceC0370g0.c();
        if (x0VarC != null) {
            return x0VarC;
        }
        if (interfaceC0370g0 instanceof U) {
            return new x0();
        }
        if (interfaceC0370g0 instanceof s0) {
            o0((s0) interfaceC0370g0);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + interfaceC0370g0).toString());
    }

    public final InterfaceC0389q T() {
        return (InterfaceC0389q) f4440b.get(this);
    }

    public final Object U() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f4439a;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof l1.y)) {
                return obj;
            }
            ((l1.y) obj).a(this);
        }
    }

    public boolean V(Throwable th) {
        return false;
    }

    public void W(Throwable th) throws Throwable {
        throw th;
    }

    public final void X(InterfaceC0380l0 interfaceC0380l0) {
        if (interfaceC0380l0 == null) {
            q0(y0.f4468a);
            return;
        }
        interfaceC0380l0.start();
        InterfaceC0389q interfaceC0389qR = interfaceC0380l0.r(this);
        q0(interfaceC0389qR);
        if (Z()) {
            interfaceC0389qR.dispose();
            q0(y0.f4468a);
        }
    }

    public final boolean Y() {
        Object objU = U();
        return (objU instanceof C0394v) || ((objU instanceof b) && ((b) objU).f());
    }

    public final boolean Z() {
        return !(U() instanceof InterfaceC0370g0);
    }

    @Override // g1.InterfaceC0380l0
    public void a(CancellationException cancellationException) throws Throwable {
        if (cancellationException == null) {
            cancellationException = new C0382m0(H(), null, this);
        }
        E(cancellationException);
    }

    public boolean a0() {
        return false;
    }

    public final boolean b0() {
        Object objU;
        do {
            objU = U();
            if (!(objU instanceof InterfaceC0370g0)) {
                return false;
            }
        } while (r0(objU) < 0);
        return true;
    }

    @Override // g1.InterfaceC0380l0
    public final Object c(L0.d dVar) {
        if (b0()) {
            Object objC0 = c0(dVar);
            return objC0 == M0.c.c() ? objC0 : H0.s.f314a;
        }
        AbstractC0388p0.g(dVar.getContext());
        return H0.s.f314a;
    }

    public final Object c0(L0.d dVar) {
        C0379l c0379l = new C0379l(M0.b.b(dVar), 1);
        c0379l.A();
        AbstractC0383n.a(c0379l, l(new B0(c0379l)));
        Object objX = c0379l.x();
        if (objX == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objX == M0.c.c() ? objX : H0.s.f314a;
    }

    @Override // g1.InterfaceC0380l0
    public final S d(boolean z2, boolean z3, Function1 function1) {
        s0 s0VarF0 = f0(function1, z2);
        while (true) {
            Object objU = U();
            if (objU instanceof U) {
                U u2 = (U) objU;
                if (!u2.isActive()) {
                    n0(u2);
                } else if (f4439a.compareAndSet(this, objU, s0VarF0)) {
                    return s0VarF0;
                }
            } else {
                if (!(objU instanceof InterfaceC0370g0)) {
                    if (z3) {
                        C0394v c0394v = objU instanceof C0394v ? (C0394v) objU : null;
                        function1.invoke(c0394v != null ? c0394v.f4464a : null);
                    }
                    return y0.f4468a;
                }
                x0 x0VarC = ((InterfaceC0370g0) objU).c();
                if (x0VarC == null) {
                    kotlin.jvm.internal.n.e(objU, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    o0((s0) objU);
                } else {
                    S s2 = y0.f4468a;
                    if (z2 && (objU instanceof b)) {
                        synchronized (objU) {
                            try {
                                thE = ((b) objU).e();
                                if (thE == null || ((function1 instanceof r) && !((b) objU).g())) {
                                    if (z(objU, x0VarC, s0VarF0)) {
                                        if (thE == null) {
                                            return s0VarF0;
                                        }
                                        s2 = s0VarF0;
                                    }
                                }
                                H0.s sVar = H0.s.f314a;
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    if (thE != null) {
                        if (z3) {
                            function1.invoke(thE);
                        }
                        return s2;
                    }
                    if (z(objU, x0VarC, s0VarF0)) {
                        return s0VarF0;
                    }
                }
            }
        }
    }

    public final Object d0(Object obj) throws Throwable {
        Throwable thL = null;
        while (true) {
            Object objU = U();
            if (objU instanceof b) {
                synchronized (objU) {
                    if (((b) objU).h()) {
                        return u0.f4459d;
                    }
                    boolean zF = ((b) objU).f();
                    if (obj != null || !zF) {
                        if (thL == null) {
                            thL = L(obj);
                        }
                        ((b) objU).a(thL);
                    }
                    Throwable thE = zF ? null : ((b) objU).e();
                    if (thE != null) {
                        i0(((b) objU).c(), thE);
                    }
                    return u0.f4456a;
                }
            }
            if (!(objU instanceof InterfaceC0370g0)) {
                return u0.f4459d;
            }
            if (thL == null) {
                thL = L(obj);
            }
            InterfaceC0370g0 interfaceC0370g0 = (InterfaceC0370g0) objU;
            if (!interfaceC0370g0.isActive()) {
                Object objY0 = y0(objU, new C0394v(thL, false, 2, null));
                if (objY0 == u0.f4456a) {
                    throw new IllegalStateException(("Cannot happen in " + objU).toString());
                }
                if (objY0 != u0.f4458c) {
                    return objY0;
                }
            } else if (x0(interfaceC0370g0, thL)) {
                return u0.f4456a;
            }
        }
    }

    @Override // g1.InterfaceC0391s
    public final void e(A0 a02) throws Throwable {
        D(a02);
    }

    public final Object e0(Object obj) {
        Object objY0;
        do {
            objY0 = y0(U(), obj);
            if (objY0 == u0.f4456a) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + obj, O(obj));
            }
        } while (objY0 == u0.f4458c);
        return objY0;
    }

    @Override // g1.InterfaceC0380l0
    public final CancellationException f() {
        Object objU = U();
        if (!(objU instanceof b)) {
            if (objU instanceof InterfaceC0370g0) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (objU instanceof C0394v) {
                return u0(this, ((C0394v) objU).f4464a, null, 1, null);
            }
            return new C0382m0(I.a(this) + " has completed normally", null, this);
        }
        Throwable thE = ((b) objU).e();
        if (thE != null) {
            CancellationException cancellationExceptionT0 = t0(thE, I.a(this) + " is cancelling");
            if (cancellationExceptionT0 != null) {
                return cancellationExceptionT0;
            }
        }
        throw new IllegalStateException(("Job is still new or active: " + this).toString());
    }

    public final s0 f0(Function1 function1, boolean z2) {
        s0 c0378k0;
        if (z2) {
            c0378k0 = function1 instanceof AbstractC0384n0 ? (AbstractC0384n0) function1 : null;
            if (c0378k0 == null) {
                c0378k0 = new C0376j0(function1);
            }
        } else {
            c0378k0 = function1 instanceof s0 ? (s0) function1 : null;
            if (c0378k0 == null) {
                c0378k0 = new C0378k0(function1);
            }
        }
        c0378k0.t(this);
        return c0378k0;
    }

    @Override // L0.g
    public Object fold(Object obj, Function2 function2) {
        return InterfaceC0380l0.a.b(this, obj, function2);
    }

    public String g0() {
        return I.a(this);
    }

    @Override // L0.g.b, L0.g
    public g.b get(g.c cVar) {
        return InterfaceC0380l0.a.c(this, cVar);
    }

    @Override // L0.g.b
    public final g.c getKey() {
        return InterfaceC0380l0.f4430z;
    }

    @Override // g1.InterfaceC0380l0
    public InterfaceC0380l0 getParent() {
        InterfaceC0389q interfaceC0389qT = T();
        if (interfaceC0389qT != null) {
            return interfaceC0389qT.getParent();
        }
        return null;
    }

    public final r h0(l1.q qVar) {
        while (qVar.m()) {
            qVar = qVar.l();
        }
        while (true) {
            qVar = qVar.k();
            if (!qVar.m()) {
                if (qVar instanceof r) {
                    return (r) qVar;
                }
                if (qVar instanceof x0) {
                    return null;
                }
            }
        }
    }

    public final void i0(x0 x0Var, Throwable th) throws Throwable {
        k0(th);
        Object objJ = x0Var.j();
        kotlin.jvm.internal.n.e(objJ, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        C0397y c0397y = null;
        for (l1.q qVarK = (l1.q) objJ; !kotlin.jvm.internal.n.c(qVarK, x0Var); qVarK = qVarK.k()) {
            if (qVarK instanceof AbstractC0384n0) {
                s0 s0Var = (s0) qVarK;
                try {
                    s0Var.r(th);
                } catch (Throwable th2) {
                    if (c0397y != null) {
                        H0.a.a(c0397y, th2);
                    } else {
                        c0397y = new C0397y("Exception in completion handler " + s0Var + " for " + this, th2);
                        H0.s sVar = H0.s.f314a;
                    }
                }
            }
        }
        if (c0397y != null) {
            W(c0397y);
        }
        G(th);
    }

    @Override // g1.InterfaceC0380l0
    public boolean isActive() {
        Object objU = U();
        return (objU instanceof InterfaceC0370g0) && ((InterfaceC0370g0) objU).isActive();
    }

    public final void j0(x0 x0Var, Throwable th) throws Throwable {
        Object objJ = x0Var.j();
        kotlin.jvm.internal.n.e(objJ, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        C0397y c0397y = null;
        for (l1.q qVarK = (l1.q) objJ; !kotlin.jvm.internal.n.c(qVarK, x0Var); qVarK = qVarK.k()) {
            if (qVarK instanceof s0) {
                s0 s0Var = (s0) qVarK;
                try {
                    s0Var.r(th);
                } catch (Throwable th2) {
                    if (c0397y != null) {
                        H0.a.a(c0397y, th2);
                    } else {
                        c0397y = new C0397y("Exception in completion handler " + s0Var + " for " + this, th2);
                        H0.s sVar = H0.s.f314a;
                    }
                }
            }
        }
        if (c0397y != null) {
            W(c0397y);
        }
    }

    public void k0(Throwable th) {
    }

    @Override // g1.InterfaceC0380l0
    public final S l(Function1 function1) {
        return d(false, true, function1);
    }

    public void l0(Object obj) {
    }

    public void m0() {
    }

    @Override // L0.g
    public L0.g minusKey(g.c cVar) {
        return InterfaceC0380l0.a.e(this, cVar);
    }

    @Override // g1.A0
    public CancellationException n() {
        Throwable thE;
        Object objU = U();
        if (objU instanceof b) {
            thE = ((b) objU).e();
        } else if (objU instanceof C0394v) {
            thE = ((C0394v) objU).f4464a;
        } else {
            if (objU instanceof InterfaceC0370g0) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objU).toString());
            }
            thE = null;
        }
        CancellationException cancellationException = thE instanceof CancellationException ? (CancellationException) thE : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        return new C0382m0("Parent job is " + s0(objU), thE, this);
    }

    public final void n0(U u2) {
        x0 x0Var = new x0();
        Object c0368f0 = x0Var;
        if (!u2.isActive()) {
            c0368f0 = new C0368f0(x0Var);
        }
        f4439a.compareAndSet(this, u2, c0368f0);
    }

    public final void o0(s0 s0Var) {
        s0Var.f(new x0());
        f4439a.compareAndSet(this, s0Var, s0Var.k());
    }

    public final void p0(s0 s0Var) {
        Object objU;
        do {
            objU = U();
            if (!(objU instanceof s0)) {
                if (!(objU instanceof InterfaceC0370g0) || ((InterfaceC0370g0) objU).c() == null) {
                    return;
                }
                s0Var.n();
                return;
            }
            if (objU != s0Var) {
                return;
            }
        } while (!f4439a.compareAndSet(this, objU, u0.f4462g));
    }

    @Override // L0.g
    public L0.g plus(L0.g gVar) {
        return InterfaceC0380l0.a.f(this, gVar);
    }

    public final void q0(InterfaceC0389q interfaceC0389q) {
        f4440b.set(this, interfaceC0389q);
    }

    @Override // g1.InterfaceC0380l0
    public final InterfaceC0389q r(InterfaceC0391s interfaceC0391s) {
        S sD = InterfaceC0380l0.a.d(this, true, false, new r(interfaceC0391s), 2, null);
        kotlin.jvm.internal.n.e(sD, "null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
        return (InterfaceC0389q) sD;
    }

    public final int r0(Object obj) {
        if (obj instanceof U) {
            if (((U) obj).isActive()) {
                return 0;
            }
            if (!f4439a.compareAndSet(this, obj, u0.f4462g)) {
                return -1;
            }
            m0();
            return 1;
        }
        if (!(obj instanceof C0368f0)) {
            return 0;
        }
        if (!f4439a.compareAndSet(this, obj, ((C0368f0) obj).c())) {
            return -1;
        }
        m0();
        return 1;
    }

    public final String s0(Object obj) {
        if (!(obj instanceof b)) {
            return obj instanceof InterfaceC0370g0 ? ((InterfaceC0370g0) obj).isActive() ? "Active" : "New" : obj instanceof C0394v ? "Cancelled" : "Completed";
        }
        b bVar = (b) obj;
        return bVar.f() ? "Cancelling" : bVar.g() ? "Completing" : "Active";
    }

    @Override // g1.InterfaceC0380l0
    public final boolean start() {
        int iR0;
        do {
            iR0 = r0(U());
            if (iR0 == 0) {
                return false;
            }
        } while (iR0 != 1);
        return true;
    }

    public final CancellationException t0(Throwable th, String str) {
        CancellationException c0382m0 = th instanceof CancellationException ? (CancellationException) th : null;
        if (c0382m0 == null) {
            if (str == null) {
                str = H();
            }
            c0382m0 = new C0382m0(str, th, this);
        }
        return c0382m0;
    }

    public String toString() {
        return v0() + '@' + I.b(this);
    }

    public final String v0() {
        return g0() + '{' + s0(U()) + '}';
    }

    public final boolean w0(InterfaceC0370g0 interfaceC0370g0, Object obj) throws Throwable {
        if (!f4439a.compareAndSet(this, interfaceC0370g0, u0.g(obj))) {
            return false;
        }
        k0(null);
        l0(obj);
        J(interfaceC0370g0, obj);
        return true;
    }

    public final boolean x0(InterfaceC0370g0 interfaceC0370g0, Throwable th) throws Throwable {
        x0 x0VarS = S(interfaceC0370g0);
        if (x0VarS == null) {
            return false;
        }
        if (!f4439a.compareAndSet(this, interfaceC0370g0, new b(x0VarS, false, th))) {
            return false;
        }
        i0(x0VarS, th);
        return true;
    }

    public final Object y0(Object obj, Object obj2) {
        return !(obj instanceof InterfaceC0370g0) ? u0.f4456a : ((!(obj instanceof U) && !(obj instanceof s0)) || (obj instanceof r) || (obj2 instanceof C0394v)) ? z0((InterfaceC0370g0) obj, obj2) : w0((InterfaceC0370g0) obj, obj2) ? obj2 : u0.f4458c;
    }

    public final boolean z(Object obj, x0 x0Var, s0 s0Var) {
        int iQ;
        c cVar = new c(s0Var, this, obj);
        do {
            iQ = x0Var.l().q(s0Var, x0Var, cVar);
            if (iQ == 1) {
                return true;
            }
        } while (iQ != 2);
        return false;
    }

    public final Object z0(InterfaceC0370g0 interfaceC0370g0, Object obj) throws Throwable {
        x0 x0VarS = S(interfaceC0370g0);
        if (x0VarS == null) {
            return u0.f4458c;
        }
        b bVar = interfaceC0370g0 instanceof b ? (b) interfaceC0370g0 : null;
        if (bVar == null) {
            bVar = new b(x0VarS, false, null);
        }
        kotlin.jvm.internal.y yVar = new kotlin.jvm.internal.y();
        synchronized (bVar) {
            if (bVar.g()) {
                return u0.f4456a;
            }
            bVar.j(true);
            if (bVar != interfaceC0370g0 && !f4439a.compareAndSet(this, interfaceC0370g0, bVar)) {
                return u0.f4458c;
            }
            boolean zF = bVar.f();
            C0394v c0394v = obj instanceof C0394v ? (C0394v) obj : null;
            if (c0394v != null) {
                bVar.a(c0394v.f4464a);
            }
            Throwable thE = zF ? null : bVar.e();
            yVar.f5059a = thE;
            H0.s sVar = H0.s.f314a;
            if (thE != null) {
                i0(x0VarS, thE);
            }
            r rVarN = N(interfaceC0370g0);
            return (rVarN == null || !A0(bVar, rVarN, obj)) ? M(bVar, obj) : u0.f4457b;
        }
    }
}
