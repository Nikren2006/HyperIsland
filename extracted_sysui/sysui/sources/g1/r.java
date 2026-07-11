package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class r extends AbstractC0384n0 implements InterfaceC0389q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC0391s f4437e;

    public r(InterfaceC0391s interfaceC0391s) {
        this.f4437e = interfaceC0391s;
    }

    @Override // g1.InterfaceC0389q
    public boolean b(Throwable th) {
        return s().I(th);
    }

    @Override // g1.InterfaceC0389q
    public InterfaceC0380l0 getParent() {
        return s();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        r((Throwable) obj);
        return H0.s.f314a;
    }

    @Override // g1.AbstractC0396x
    public void r(Throwable th) {
        this.f4437e.e(s());
    }
}
