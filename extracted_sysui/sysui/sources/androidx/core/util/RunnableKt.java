package androidx.core.util;

/* JADX INFO: loaded from: classes.dex */
public final class RunnableKt {
    public static final Runnable asRunnable(L0.d dVar) {
        return new ContinuationRunnable(dVar);
    }
}
