package k1;

/* JADX INFO: loaded from: classes2.dex */
public final class x implements L0.d, N0.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.d f5025a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L0.g f5026b;

    public x(L0.d dVar, L0.g gVar) {
        this.f5025a = dVar;
        this.f5026b = gVar;
    }

    @Override // N0.e
    public N0.e getCallerFrame() {
        L0.d dVar = this.f5025a;
        if (dVar instanceof N0.e) {
            return (N0.e) dVar;
        }
        return null;
    }

    @Override // L0.d
    public L0.g getContext() {
        return this.f5026b;
    }

    @Override // L0.d
    public void resumeWith(Object obj) {
        this.f5025a.resumeWith(obj);
    }
}
