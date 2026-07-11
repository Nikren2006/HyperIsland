package androidx.core.os;

import java.lang.Throwable;

/* JADX INFO: loaded from: classes.dex */
public interface OutcomeReceiverCompat<R, E extends Throwable> {
    default void onError(E e2) {
    }

    void onResult(R r2);
}
