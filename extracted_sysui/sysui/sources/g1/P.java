package g1;

import H0.j;
import l1.C0455j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class P {
    public static final void a(O o2, int i2) {
        L0.d dVarC = o2.c();
        boolean z2 = i2 == 4;
        if (z2 || !(dVarC instanceof C0455j) || b(i2) != b(o2.f4389c)) {
            d(o2, dVarC, z2);
            return;
        }
        B b2 = ((C0455j) dVarC).f5222d;
        L0.g context = dVarC.getContext();
        if (b2.isDispatchNeeded(context)) {
            b2.dispatch(context, o2);
        } else {
            e(o2);
        }
    }

    public static final boolean b(int i2) {
        return i2 == 1 || i2 == 2;
    }

    public static final boolean c(int i2) {
        return i2 == 2;
    }

    public static final void d(O o2, L0.d dVar, boolean z2) {
        Object objE;
        Object objI = o2.i();
        Throwable thD = o2.d(objI);
        if (thD != null) {
            j.a aVar = H0.j.f299a;
            objE = H0.k.a(thD);
        } else {
            j.a aVar2 = H0.j.f299a;
            objE = o2.e(objI);
        }
        Object objA = H0.j.a(objE);
        if (!z2) {
            dVar.resumeWith(objA);
            return;
        }
        kotlin.jvm.internal.n.e(dVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        C0455j c0455j = (C0455j) dVar;
        L0.d dVar2 = c0455j.f5223e;
        Object obj = c0455j.f5225g;
        L0.g context = dVar2.getContext();
        Object objC = l1.J.c(context, obj);
        M0 m0G = objC != l1.J.f5199a ? A.g(dVar2, context, objC) : null;
        try {
            c0455j.f5223e.resumeWith(objA);
            H0.s sVar = H0.s.f314a;
        } finally {
            if (m0G == null || m0G.F0()) {
                l1.J.a(context, objC);
            }
        }
    }

    public static final void e(O o2) {
        V vA = H0.f4374a.a();
        if (vA.F()) {
            vA.B(o2);
            return;
        }
        vA.D(true);
        try {
            d(o2, o2.c(), true);
            do {
            } while (vA.H());
        } finally {
            try {
            } finally {
            }
        }
    }
}
