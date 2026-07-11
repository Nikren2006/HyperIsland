package androidx.lifecycle;

import L0.g;
import g1.E;
import g1.r0;
import java.io.Closeable;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class CloseableCoroutineScope implements Closeable, E {
    private final g coroutineContext;

    public CloseableCoroutineScope(g context) {
        n.g(context, "context");
        this.coroutineContext = context;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        r0.d(getCoroutineContext(), null, 1, null);
    }

    @Override // g1.E
    public g getCoroutineContext() {
        return this.coroutineContext;
    }
}
