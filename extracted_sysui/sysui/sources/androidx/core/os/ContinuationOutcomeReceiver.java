package androidx.core.os;

import H0.j;
import H0.k;
import android.os.OutcomeReceiver;
import androidx.annotation.RequiresApi;
import java.lang.Throwable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(31)
final class ContinuationOutcomeReceiver<R, E extends Throwable> extends AtomicBoolean implements OutcomeReceiver<R, E> {
    private final L0.d continuation;

    public ContinuationOutcomeReceiver(L0.d dVar) {
        super(false);
        this.continuation = dVar;
    }

    @Override // android.os.OutcomeReceiver
    public void onError(E e2) {
        if (compareAndSet(false, true)) {
            L0.d dVar = this.continuation;
            j.a aVar = j.f299a;
            dVar.resumeWith(j.a(k.a(e2)));
        }
    }

    @Override // android.os.OutcomeReceiver
    public void onResult(R r2) {
        if (compareAndSet(false, true)) {
            this.continuation.resumeWith(j.a(r2));
        }
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        return "ContinuationOutcomeReceiver(outcomeReceived = " + get() + ')';
    }
}
