package androidx.core.util;

import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(24)
public final class ConsumerKt {
    @RequiresApi(24)
    public static final <T> java.util.function.Consumer<T> asConsumer(L0.d dVar) {
        return new ContinuationConsumer(dVar);
    }
}
