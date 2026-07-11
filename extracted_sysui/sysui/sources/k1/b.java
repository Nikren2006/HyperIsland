package k1;

import H0.j;
import j1.I;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d[] f4949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4950b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4951c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public y f4952d;

    public final I c() {
        y yVar;
        synchronized (this) {
            yVar = this.f4952d;
            if (yVar == null) {
                yVar = new y(this.f4950b);
                this.f4952d = yVar;
            }
        }
        return yVar;
    }

    public final d g() {
        d dVarH;
        y yVar;
        synchronized (this) {
            try {
                d[] dVarArrI = this.f4949a;
                if (dVarArrI == null) {
                    dVarArrI = i(2);
                    this.f4949a = dVarArrI;
                } else if (this.f4950b >= dVarArrI.length) {
                    Object[] objArrCopyOf = Arrays.copyOf(dVarArrI, dVarArrI.length * 2);
                    kotlin.jvm.internal.n.f(objArrCopyOf, "copyOf(this, newSize)");
                    this.f4949a = (d[]) objArrCopyOf;
                    dVarArrI = (d[]) objArrCopyOf;
                }
                int i2 = this.f4951c;
                do {
                    dVarH = dVarArrI[i2];
                    if (dVarH == null) {
                        dVarH = h();
                        dVarArrI[i2] = dVarH;
                    }
                    i2++;
                    if (i2 >= dVarArrI.length) {
                        i2 = 0;
                    }
                    kotlin.jvm.internal.n.e(dVarH, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                } while (!dVarH.a(this));
                this.f4951c = i2;
                this.f4950b++;
                yVar = this.f4952d;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (yVar != null) {
            yVar.Y(1);
        }
        return dVarH;
    }

    public abstract d h();

    public abstract d[] i(int i2);

    public final void j(d dVar) {
        y yVar;
        int i2;
        L0.d[] dVarArrB;
        synchronized (this) {
            try {
                int i3 = this.f4950b - 1;
                this.f4950b = i3;
                yVar = this.f4952d;
                if (i3 == 0) {
                    this.f4951c = 0;
                }
                kotlin.jvm.internal.n.e(dVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                dVarArrB = dVar.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (L0.d dVar2 : dVarArrB) {
            if (dVar2 != null) {
                j.a aVar = H0.j.f299a;
                dVar2.resumeWith(H0.j.a(H0.s.f314a));
            }
        }
        if (yVar != null) {
            yVar.Y(-1);
        }
    }

    public final int k() {
        return this.f4950b;
    }

    public final d[] l() {
        return this.f4949a;
    }
}
