package g1;

import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: g1.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0374i0 extends AbstractC0373i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Function1 f4421a;

    public C0374i0(Function1 function1) {
        this.f4421a = function1;
    }

    @Override // g1.AbstractC0375j
    public void b(Throwable th) {
        this.f4421a.invoke(th);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        b((Throwable) obj);
        return H0.s.f314a;
    }

    public String toString() {
        return "InvokeOnCancel[" + I.a(this.f4421a) + '@' + I.b(this) + ']';
    }
}
