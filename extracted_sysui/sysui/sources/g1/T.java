package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class T extends AbstractC0373i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final S f4396a;

    public T(S s2) {
        this.f4396a = s2;
    }

    @Override // g1.AbstractC0375j
    public void b(Throwable th) {
        this.f4396a.dispose();
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        b((Throwable) obj);
        return H0.s.f314a;
    }

    public String toString() {
        return "DisposeOnCancel[" + this.f4396a + ']';
    }
}
