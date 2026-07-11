package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class H0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final H0 f4374a = new H0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal f4375b = l1.K.a(new l1.F("ThreadLocalEventLoop"));

    public final V a() {
        ThreadLocal threadLocal = f4375b;
        V v2 = (V) threadLocal.get();
        if (v2 != null) {
            return v2;
        }
        V vA = Y.a();
        threadLocal.set(vA);
        return vA;
    }

    public final void b() {
        f4375b.set(null);
    }

    public final void c(V v2) {
        f4375b.set(v2);
    }
}
