package g1;

import H0.j;

/* JADX INFO: loaded from: classes2.dex */
public final class B0 extends s0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L0.d f4363e;

    public B0(L0.d dVar) {
        this.f4363e = dVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        r((Throwable) obj);
        return H0.s.f314a;
    }

    @Override // g1.AbstractC0396x
    public void r(Throwable th) {
        L0.d dVar = this.f4363e;
        j.a aVar = H0.j.f299a;
        dVar.resumeWith(H0.j.a(H0.s.f314a));
    }
}
