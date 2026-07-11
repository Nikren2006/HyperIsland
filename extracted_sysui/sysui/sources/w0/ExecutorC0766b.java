package w0;

import android.os.Handler;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: renamed from: w0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class ExecutorC0766b implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f6979a;

    public ExecutorC0766b(Handler handler) {
        Objects.requireNonNull(handler);
        this.f6979a = handler;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.f6979a.post(runnable)) {
            return;
        }
        throw new RejectedExecutionException(this.f6979a + " is shutting down");
    }
}
