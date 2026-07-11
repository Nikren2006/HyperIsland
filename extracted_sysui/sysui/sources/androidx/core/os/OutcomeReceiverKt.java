package androidx.core.os;

import android.os.OutcomeReceiver;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(31)
public final class OutcomeReceiverKt {
    @RequiresApi(31)
    public static final <R, E extends Throwable> OutcomeReceiver<R, E> asOutcomeReceiver(L0.d dVar) {
        return new ContinuationOutcomeReceiver(dVar);
    }
}
