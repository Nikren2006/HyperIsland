package j1;

import g1.InterfaceC0380l0;

/* JADX INFO: loaded from: classes2.dex */
public final class w implements I, InterfaceC0418f, k1.p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0380l0 f4798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ I f4799b;

    public w(I i2, InterfaceC0380l0 interfaceC0380l0) {
        this.f4798a = interfaceC0380l0;
        this.f4799b = i2;
    }

    @Override // k1.p
    public InterfaceC0418f a(L0.g gVar, int i2, i1.a aVar) {
        return K.d(this, gVar, i2, aVar);
    }

    @Override // j1.y, j1.InterfaceC0418f
    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        return this.f4799b.collect(interfaceC0419g, dVar);
    }

    @Override // j1.I
    public Object getValue() {
        return this.f4799b.getValue();
    }
}
