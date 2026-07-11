package androidx.core.util;

import H0.j;
import androidx.annotation.RequiresApi;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
final class ContinuationConsumer<T> extends AtomicBoolean implements java.util.function.Consumer<T> {
    private final L0.d continuation;

    public ContinuationConsumer(L0.d dVar) {
        super(false);
        this.continuation = dVar;
    }

    @Override // java.util.function.Consumer
    public void accept(T t2) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(j.a(t2));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationConsumer(resultAccepted = " + get() + ')';
    }
}
