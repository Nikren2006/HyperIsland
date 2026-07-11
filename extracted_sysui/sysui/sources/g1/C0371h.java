package g1;

import java.util.concurrent.Future;

/* JADX INFO: renamed from: g1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0371h extends AbstractC0373i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Future f4419a;

    public C0371h(Future future) {
        this.f4419a = future;
    }

    @Override // g1.AbstractC0375j
    public void b(Throwable th) {
        if (th != null) {
            this.f4419a.cancel(false);
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        b((Throwable) obj);
        return H0.s.f314a;
    }

    public String toString() {
        return "CancelFutureOnCancel[" + this.f4419a + ']';
    }
}
