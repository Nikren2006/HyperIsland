package F0;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements G0.a, E0.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f170c = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile G0.a f171a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile Object f172b = f170c;

    public d(G0.a aVar) {
        this.f171a = aVar;
    }

    public static E0.a a(G0.a aVar) {
        return aVar instanceof E0.a ? (E0.a) aVar : new d((G0.a) i.b(aVar));
    }

    public static G0.a b(G0.a aVar) {
        i.b(aVar);
        return aVar instanceof d ? aVar : new d(aVar);
    }

    public static Object c(Object obj, Object obj2) {
        if (obj == f170c || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // G0.a
    public Object get() {
        Object obj = this.f172b;
        Object obj2 = f170c;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.f172b;
                    if (obj == obj2) {
                        obj = this.f171a.get();
                        this.f172b = c(this.f172b, obj);
                        this.f171a = null;
                    }
                } finally {
                }
            }
        }
        return obj;
    }
}
