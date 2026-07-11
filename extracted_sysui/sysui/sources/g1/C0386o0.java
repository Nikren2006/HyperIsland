package g1;

/* JADX INFO: renamed from: g1.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0386o0 extends t0 implements InterfaceC0392t {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4435c;

    public C0386o0(InterfaceC0380l0 interfaceC0380l0) {
        super(true);
        X(interfaceC0380l0);
        this.f4435c = B0();
    }

    public final boolean B0() {
        t0 t0VarS;
        InterfaceC0389q interfaceC0389qT = T();
        r rVar = interfaceC0389qT instanceof r ? (r) interfaceC0389qT : null;
        if (rVar != null && (t0VarS = rVar.s()) != null) {
            while (!t0VarS.Q()) {
                InterfaceC0389q interfaceC0389qT2 = t0VarS.T();
                r rVar2 = interfaceC0389qT2 instanceof r ? (r) interfaceC0389qT2 : null;
                if (rVar2 == null || (t0VarS = rVar2.s()) == null) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // g1.t0
    public boolean Q() {
        return this.f4435c;
    }

    @Override // g1.t0
    public boolean R() {
        return true;
    }
}
