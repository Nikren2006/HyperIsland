package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class L0 extends B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final L0 f4383a = new L0();

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        P0 p02 = (P0) gVar.get(P0.f4390b);
        if (p02 == null) {
            throw new UnsupportedOperationException("Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls.");
        }
        p02.f4391a = true;
    }

    @Override // g1.B
    public boolean isDispatchNeeded(L0.g gVar) {
        return false;
    }

    @Override // g1.B
    public B limitedParallelism(int i2) {
        throw new UnsupportedOperationException("limitedParallelism is not supported for Dispatchers.Unconfined");
    }

    @Override // g1.B
    public String toString() {
        return "Dispatchers.Unconfined";
    }
}
