package i1;

import g1.O0;
import i1.h;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.z;
import l1.O;
import l1.x;

/* JADX INFO: loaded from: classes2.dex */
public class n extends b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f4610m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f4611n;

    public n(int i2, a aVar, Function1 function1) {
        super(i2, function1);
        this.f4610m = i2;
        this.f4611n = aVar;
        if (aVar == a.SUSPEND) {
            throw new IllegalArgumentException(("This implementation does not support suspension for senders, use " + z.b(b.class).c() + " instead").toString());
        }
        if (i2 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i2 + " was specified").toString());
    }

    public static /* synthetic */ Object I0(n nVar, Object obj, L0.d dVar) throws Throwable {
        O oD;
        Object objL0 = nVar.L0(obj, true);
        if (!(objL0 instanceof h.a)) {
            return H0.s.f314a;
        }
        h.e(objL0);
        Function1 function1 = nVar.f4562b;
        if (function1 == null || (oD = x.d(function1, obj, null, 2, null)) == null) {
            throw nVar.O();
        }
        H0.a.a(oD, nVar.O());
        throw oD;
    }

    public final Object J0(Object obj, boolean z2) {
        Function1 function1;
        O oD;
        Object objJ = super.j(obj);
        if (h.i(objJ) || h.h(objJ)) {
            return objJ;
        }
        if (!z2 || (function1 = this.f4562b) == null || (oD = x.d(function1, obj, null, 2, null)) == null) {
            return h.f4604b.c(H0.s.f314a);
        }
        throw oD;
    }

    public final Object K0(Object obj) {
        i iVar;
        Object obj2 = c.f4583d;
        i iVar2 = (i) b.f4556h.get(this);
        while (true) {
            long andIncrement = b.f4552d.getAndIncrement(this);
            long j2 = andIncrement & 1152921504606846975L;
            boolean zY = Y(andIncrement);
            int i2 = c.f4581b;
            long j3 = j2 / ((long) i2);
            int i3 = (int) (j2 % ((long) i2));
            if (iVar2.f5193c != j3) {
                i iVarJ = J(j3, iVar2);
                if (iVarJ != null) {
                    iVar = iVarJ;
                } else if (zY) {
                    return h.f4604b.a(O());
                }
            } else {
                iVar = iVar2;
            }
            int iD0 = D0(iVar, i3, obj, j2, obj2, zY);
            if (iD0 == 0) {
                iVar.b();
                return h.f4604b.c(H0.s.f314a);
            }
            if (iD0 == 1) {
                return h.f4604b.c(H0.s.f314a);
            }
            if (iD0 == 2) {
                if (zY) {
                    iVar.p();
                    return h.f4604b.a(O());
                }
                O0 o02 = obj2 instanceof O0 ? (O0) obj2 : null;
                if (o02 != null) {
                    n0(o02, iVar, i3);
                }
                F((iVar.f5193c * ((long) i2)) + ((long) i3));
                return h.f4604b.c(H0.s.f314a);
            }
            if (iD0 == 3) {
                throw new IllegalStateException("unexpected");
            }
            if (iD0 == 4) {
                if (j2 < N()) {
                    iVar.b();
                }
                return h.f4604b.a(O());
            }
            if (iD0 == 5) {
                iVar.b();
            }
            iVar2 = iVar;
        }
    }

    public final Object L0(Object obj, boolean z2) {
        return this.f4611n == a.DROP_LATEST ? J0(obj, z2) : K0(obj);
    }

    @Override // i1.b
    public boolean Z() {
        return this.f4611n == a.DROP_OLDEST;
    }

    @Override // i1.b, i1.t
    public Object b(Object obj, L0.d dVar) {
        return I0(this, obj, dVar);
    }

    @Override // i1.b, i1.t
    public Object j(Object obj) {
        return L0(obj, false);
    }
}
