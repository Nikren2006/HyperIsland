package n1;

import g1.AbstractC0360b0;
import g1.B;
import java.util.concurrent.Executor;
import l1.G;
import l1.I;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends AbstractC0360b0 implements Executor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f6263b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final B f6264c = m.f6284a.limitedParallelism(I.e("kotlinx.coroutines.io.parallelism", c1.f.c(64, G.a()), 0, 0, 12, null));

    @Override // g1.AbstractC0360b0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Cannot be invoked on Dispatchers.IO");
    }

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        f6264c.dispatch(gVar, runnable);
    }

    @Override // g1.B
    public void dispatchYield(L0.g gVar, Runnable runnable) {
        f6264c.dispatchYield(gVar, runnable);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        dispatch(L0.h.f402a, runnable);
    }

    @Override // g1.B
    public B limitedParallelism(int i2) {
        return m.f6284a.limitedParallelism(i2);
    }

    @Override // g1.B
    public String toString() {
        return "Dispatchers.IO";
    }
}
