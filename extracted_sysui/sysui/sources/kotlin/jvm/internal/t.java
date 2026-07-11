package kotlin.jvm.internal;

import d1.InterfaceC0323b;
import d1.InterfaceC0328g;

/* JADX INFO: loaded from: classes2.dex */
public abstract class t extends v implements InterfaceC0328g {
    public t(Object obj, Class cls, String str, String str2, int i2) {
        super(obj, cls, str, str2, i2);
    }

    @Override // kotlin.jvm.internal.AbstractC0430d
    public InterfaceC0323b computeReflected() {
        return z.e(this);
    }

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        return get();
    }
}
