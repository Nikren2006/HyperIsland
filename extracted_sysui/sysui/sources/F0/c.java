package F0;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public G0.a f169a;

    public static void a(G0.a aVar, G0.a aVar2) {
        i.b(aVar2);
        c cVar = (c) aVar;
        if (cVar.f169a != null) {
            throw new IllegalStateException();
        }
        cVar.f169a = aVar2;
    }

    @Override // G0.a
    public Object get() {
        G0.a aVar = this.f169a;
        if (aVar != null) {
            return aVar.get();
        }
        throw new IllegalStateException();
    }
}
