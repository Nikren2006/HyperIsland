package j1;

/* JADX INFO: loaded from: classes2.dex */
public final class B extends k1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4638a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L0.d f4639b;

    @Override // k1.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean a(z zVar) {
        if (this.f4638a >= 0) {
            return false;
        }
        this.f4638a = zVar.W();
        return true;
    }

    @Override // k1.d
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public L0.d[] b(z zVar) {
        long j2 = this.f4638a;
        this.f4638a = -1L;
        this.f4639b = null;
        return zVar.V(j2);
    }
}
