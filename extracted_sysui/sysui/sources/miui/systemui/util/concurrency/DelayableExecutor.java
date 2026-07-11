package miui.systemui.util.concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public interface DelayableExecutor extends Executor {
    default Runnable executeAtTime(Runnable runnable, long j2) {
        return executeAtTime(runnable, j2, TimeUnit.MILLISECONDS);
    }

    Runnable executeAtTime(Runnable runnable, long j2, TimeUnit timeUnit);

    default Runnable executeDelayed(Runnable runnable, long j2) {
        return executeDelayed(runnable, j2, TimeUnit.MILLISECONDS);
    }

    Runnable executeDelayed(Runnable runnable, long j2, TimeUnit timeUnit);
}
