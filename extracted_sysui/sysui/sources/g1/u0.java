package g1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l1.F f4456a = new l1.F("COMPLETING_ALREADY");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l1.F f4457b = new l1.F("COMPLETING_WAITING_CHILDREN");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final l1.F f4458c = new l1.F("COMPLETING_RETRY");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final l1.F f4459d = new l1.F("TOO_LATE_TO_CANCEL");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final l1.F f4460e = new l1.F("SEALED");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final U f4461f = new U(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final U f4462g = new U(true);

    public static final Object g(Object obj) {
        return obj instanceof InterfaceC0370g0 ? new C0372h0((InterfaceC0370g0) obj) : obj;
    }

    public static final Object h(Object obj) {
        InterfaceC0370g0 interfaceC0370g0;
        C0372h0 c0372h0 = obj instanceof C0372h0 ? (C0372h0) obj : null;
        return (c0372h0 == null || (interfaceC0370g0 = c0372h0.f4420a) == null) ? obj : interfaceC0370g0;
    }
}
