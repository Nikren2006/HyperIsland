package l1;

/* JADX INFO: renamed from: l1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0451f implements g1.E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.g f5218a;

    public C0451f(L0.g gVar) {
        this.f5218a = gVar;
    }

    @Override // g1.E
    public L0.g getCoroutineContext() {
        return this.f5218a;
    }

    public String toString() {
        return "CoroutineScope(coroutineContext=" + getCoroutineContext() + ')';
    }
}
