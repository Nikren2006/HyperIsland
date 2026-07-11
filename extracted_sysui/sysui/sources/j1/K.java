package j1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l1.F f4678a = new l1.F("NONE");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l1.F f4679b = new l1.F("PENDING");

    public static final u a(Object obj) {
        if (obj == null) {
            obj = k1.s.f5012a;
        }
        return new J(obj);
    }

    public static final InterfaceC0418f d(I i2, L0.g gVar, int i3, i1.a aVar) {
        return (((i3 < 0 || i3 >= 2) && i3 != -2) || aVar != i1.a.DROP_OLDEST) ? A.e(i2, gVar, i3, aVar) : i2;
    }
}
