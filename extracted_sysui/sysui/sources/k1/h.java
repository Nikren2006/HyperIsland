package k1;

import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class h extends g {
    public /* synthetic */ h(InterfaceC0418f interfaceC0418f, L0.g gVar, int i2, i1.a aVar, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(interfaceC0418f, (i3 & 2) != 0 ? L0.h.f402a : gVar, (i3 & 4) != 0 ? -3 : i2, (i3 & 8) != 0 ? i1.a.SUSPEND : aVar);
    }

    @Override // k1.e
    public e h(L0.g gVar, int i2, i1.a aVar) {
        return new h(this.f4964d, gVar, i2, aVar);
    }

    @Override // k1.e
    public InterfaceC0418f i() {
        return this.f4964d;
    }

    @Override // k1.g
    public Object p(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        Object objCollect = this.f4964d.collect(interfaceC0419g, dVar);
        return objCollect == M0.c.c() ? objCollect : H0.s.f314a;
    }

    public h(InterfaceC0418f interfaceC0418f, L0.g gVar, int i2, i1.a aVar) {
        super(interfaceC0418f, gVar, i2, aVar);
    }
}
