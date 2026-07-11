package N0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j extends a {
    public j(L0.d dVar) {
        super(dVar);
        if (dVar != null && dVar.getContext() != L0.h.f402a) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // L0.d
    public L0.g getContext() {
        return L0.h.f402a;
    }
}
