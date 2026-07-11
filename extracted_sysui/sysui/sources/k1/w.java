package k1;

import j1.InterfaceC0419g;

/* JADX INFO: loaded from: classes2.dex */
public final class w implements InterfaceC0419g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i1.t f5024a;

    public w(i1.t tVar) {
        this.f5024a = tVar;
    }

    @Override // j1.InterfaceC0419g
    public Object emit(Object obj, L0.d dVar) {
        Object objB = this.f5024a.b(obj, dVar);
        return objB == M0.c.c() ? objB : H0.s.f314a;
    }
}
