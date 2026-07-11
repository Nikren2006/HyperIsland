package l1;

import g1.G0;

/* JADX INFO: loaded from: classes2.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L0.g f5208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f5209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final G0[] f5210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5211d;

    public N(L0.g gVar, int i2) {
        this.f5208a = gVar;
        this.f5209b = new Object[i2];
        this.f5210c = new G0[i2];
    }

    public final void a(G0 g02, Object obj) {
        Object[] objArr = this.f5209b;
        int i2 = this.f5211d;
        objArr[i2] = obj;
        G0[] g0Arr = this.f5210c;
        this.f5211d = i2 + 1;
        kotlin.jvm.internal.n.e(g02, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        g0Arr[i2] = g02;
    }

    public final void b(L0.g gVar) {
        int length = this.f5210c.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            G0 g02 = this.f5210c[length];
            kotlin.jvm.internal.n.d(g02);
            g02.w(gVar, this.f5209b[length]);
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }
}
