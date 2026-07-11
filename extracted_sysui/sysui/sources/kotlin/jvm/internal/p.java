package kotlin.jvm.internal;

import d1.InterfaceC0323b;
import d1.InterfaceC0327f;
import d1.InterfaceC0329h;

/* JADX INFO: loaded from: classes2.dex */
public abstract class p extends r implements InterfaceC0327f {
    public p(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }

    @Override // d1.InterfaceC0329h
    public InterfaceC0329h.a a() {
        ((InterfaceC0327f) getReflected()).a();
        return null;
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    public InterfaceC0323b computeReflected() {
        return z.d(this);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return get(obj);
    }
}
