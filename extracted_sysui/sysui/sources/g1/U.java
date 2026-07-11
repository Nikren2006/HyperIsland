package g1;

/* JADX INFO: loaded from: classes2.dex */
public final class U implements InterfaceC0370g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f4397a;

    public U(boolean z2) {
        this.f4397a = z2;
    }

    @Override // g1.InterfaceC0370g0
    public x0 c() {
        return null;
    }

    @Override // g1.InterfaceC0370g0
    public boolean isActive() {
        return this.f4397a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Empty{");
        sb.append(isActive() ? "Active" : "New");
        sb.append('}');
        return sb.toString();
    }
}
