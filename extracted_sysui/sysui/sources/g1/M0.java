package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class M0 extends l1.B {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ThreadLocal f4386e;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public M0(L0.g gVar, L0.d dVar) {
        N0 n02 = N0.f4388a;
        super(gVar.get(n02) == null ? gVar.plus(n02) : gVar, dVar);
        this.f4386e = new ThreadLocal();
        if (dVar.getContext().get(L0.e.f399t) instanceof B) {
            return;
        }
        Object objC = l1.J.c(gVar, null);
        l1.J.a(gVar, objC);
        G0(gVar, objC);
    }

    @Override // l1.B, g1.AbstractC0357a
    public void B0(Object obj) {
        if (this.threadLocalIsSet) {
            H0.i iVar = (H0.i) this.f4386e.get();
            if (iVar != null) {
                l1.J.a((L0.g) iVar.a(), iVar.b());
            }
            this.f4386e.remove();
        }
        Object objA = AbstractC0398z.a(obj, this.f5191d);
        L0.d dVar = this.f5191d;
        L0.g context = dVar.getContext();
        Object objC = l1.J.c(context, null);
        M0 m0G = objC != l1.J.f5199a ? A.g(dVar, context, objC) : null;
        try {
            this.f5191d.resumeWith(objA);
            H0.s sVar = H0.s.f314a;
        } finally {
            if (m0G == null || m0G.F0()) {
                l1.J.a(context, objC);
            }
        }
    }

    public final boolean F0() {
        boolean z2 = this.threadLocalIsSet && this.f4386e.get() == null;
        this.f4386e.remove();
        return !z2;
    }

    public final void G0(L0.g gVar, Object obj) {
        this.threadLocalIsSet = true;
        this.f4386e.set(H0.o.a(gVar, obj));
    }
}
