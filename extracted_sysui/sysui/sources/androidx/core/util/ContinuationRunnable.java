package androidx.core.util;

import H0.j;
import H0.s;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
final class ContinuationRunnable extends AtomicBoolean implements Runnable {
    private final L0.d continuation;

    public ContinuationRunnable(L0.d dVar) {
        super(false);
        this.continuation = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (compareAndSet(false, true)) {
            L0.d dVar = this.continuation;
            j.a aVar = j.f299a;
            dVar.resumeWith(j.a(s.f314a));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationRunnable(ran = " + get() + ')';
    }
}
