package g1;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.functions.Function1;

/* JADX INFO: renamed from: g1.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0376j0 extends AbstractC0384n0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f4422f = AtomicIntegerFieldUpdater.newUpdater(C0376j0.class, "_invoked");
    private volatile int _invoked;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Function1 f4423e;

    public C0376j0(Function1 function1) {
        this.f4423e = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        r((Throwable) obj);
        return H0.s.f314a;
    }

    @Override // g1.AbstractC0396x
    public void r(Throwable th) {
        if (f4422f.compareAndSet(this, 0, 1)) {
            this.f4423e.invoke(th);
        }
    }
}
