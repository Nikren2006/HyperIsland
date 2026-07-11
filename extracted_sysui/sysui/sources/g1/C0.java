package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class C0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final B f4366a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0377k f4367b;

    public C0(B b2, InterfaceC0377k interfaceC0377k) {
        this.f4366a = b2;
        this.f4367b = interfaceC0377k;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4367b.p(this.f4366a, H0.s.f314a);
    }
}
