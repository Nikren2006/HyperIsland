package g1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: g1.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0378k0 extends s0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Function1 f4424e;

    public C0378k0(Function1 function1) {
        this.f4424e = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        r((Throwable) obj);
        return H0.s.f314a;
    }

    @Override // g1.AbstractC0396x
    public void r(Throwable th) {
        this.f4424e.invoke(th);
    }
}
