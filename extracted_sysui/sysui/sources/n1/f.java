package n1;

import g1.AbstractC0360b0;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f extends AbstractC0360b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f6269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f6270e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f6271f = z();

    public f(int i2, int i3, long j2, String str) {
        this.f6267b = i2;
        this.f6268c = i3;
        this.f6269d = j2;
        this.f6270e = str;
    }

    public final void A(Runnable runnable, i iVar, boolean z2) {
        this.f6271f.l(runnable, iVar, z2);
    }

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        a.n(this.f6271f, runnable, null, false, 6, null);
    }

    @Override // g1.B
    public void dispatchYield(L0.g gVar, Runnable runnable) {
        a.n(this.f6271f, runnable, null, true, 2, null);
    }

    public final a z() {
        return new a(this.f6267b, this.f6268c, this.f6269d, this.f6270e);
    }
}
