package i1;

import g1.D;
import i1.t;

/* JADX INFO: loaded from: classes2.dex */
public final class p extends e implements q {
    public p(L0.g gVar, d dVar) {
        super(gVar, dVar, true, true);
    }

    @Override // g1.AbstractC0357a
    public void C0(Throwable th, boolean z2) {
        if (F0().o(th) || z2) {
            return;
        }
        D.a(getContext(), th);
    }

    @Override // g1.AbstractC0357a
    /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
    public void D0(H0.s sVar) {
        t.a.a(F0(), null, 1, null);
    }

    @Override // g1.AbstractC0357a, g1.t0, g1.InterfaceC0380l0
    public boolean isActive() {
        return super.isActive();
    }
}
