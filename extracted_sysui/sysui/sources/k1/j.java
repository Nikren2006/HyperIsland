package k1;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes2.dex */
public final class j extends CancellationException {
    public j() {
        super("Child of the scoped flow was cancelled");
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
