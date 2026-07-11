package g1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class K0 {
    public static final AbstractC0360b0 b(final int i2, final String str) {
        if (i2 >= 1) {
            final AtomicInteger atomicInteger = new AtomicInteger();
            return AbstractC0364d0.b(Executors.newScheduledThreadPool(i2, new ThreadFactory() { // from class: g1.J0
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    return K0.c(i2, str, atomicInteger, runnable);
                }
            }));
        }
        throw new IllegalArgumentException(("Expected at least one thread, but " + i2 + " specified").toString());
    }

    public static final Thread c(int i2, String str, AtomicInteger atomicInteger, Runnable runnable) {
        if (i2 != 1) {
            str = str + '-' + atomicInteger.incrementAndGet();
        }
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(true);
        return thread;
    }
}
