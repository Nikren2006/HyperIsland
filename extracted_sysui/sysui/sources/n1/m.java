package n1;

import g1.B;
import l1.AbstractC0459n;

/* JADX INFO: loaded from: classes2.dex */
public final class m extends B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f6284a = new m();

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        c.f6265g.A(runnable, l.f6283h, false);
    }

    @Override // g1.B
    public void dispatchYield(L0.g gVar, Runnable runnable) {
        c.f6265g.A(runnable, l.f6283h, true);
    }

    @Override // g1.B
    public B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return i2 >= l.f6279d ? this : super.limitedParallelism(i2);
    }
}
