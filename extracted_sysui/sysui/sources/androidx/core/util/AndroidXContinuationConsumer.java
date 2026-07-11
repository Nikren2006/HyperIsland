package androidx.core.util;

import H0.j;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class AndroidXContinuationConsumer<T> extends AtomicBoolean implements Consumer<T> {
    private final L0.d continuation;

    public AndroidXContinuationConsumer(L0.d dVar) {
        super(false);
        this.continuation = dVar;
    }

    @Override // androidx.core.util.Consumer
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
