package g1;

import androidx.core.location.LocationRequestCompat;
import g1.W;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes2.dex */
public final class J extends W implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final J f4376g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f4377h;

    static {
        Long l2;
        J j2 = new J();
        f4376g = j2;
        V.E(j2, false, 1, null);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l2 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l2 = 1000L;
        }
        f4377h = timeUnit.toNanos(l2.longValue());
    }

    @Override // g1.W, g1.V
    public void I() {
        debugStatus = 4;
        super.I();
    }

    @Override // g1.X
    public Thread J() {
        Thread thread = _thread;
        return thread == null ? b0() : thread;
    }

    @Override // g1.X
    public void K(long j2, W.b bVar) {
        f0();
    }

    @Override // g1.W
    public void P(Runnable runnable) {
        if (c0()) {
            f0();
        }
        super.P(runnable);
    }

    public final synchronized void a0() {
        if (d0()) {
            debugStatus = 3;
            V();
            kotlin.jvm.internal.n.e(this, "null cannot be cast to non-null type java.lang.Object");
            notifyAll();
        }
    }

    public final synchronized Thread b0() {
        Thread thread;
        thread = _thread;
        if (thread == null) {
            thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread;
            thread.setDaemon(true);
            thread.start();
        }
        return thread;
    }

    public final boolean c0() {
        return debugStatus == 4;
    }

    public final boolean d0() {
        int i2 = debugStatus;
        return i2 == 2 || i2 == 3;
    }

    public final synchronized boolean e0() {
        if (d0()) {
            return false;
        }
        debugStatus = 1;
        kotlin.jvm.internal.n.e(this, "null cannot be cast to non-null type java.lang.Object");
        notifyAll();
        return true;
    }

    public final void f0() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zS;
        H0.f4374a.c(this);
        AbstractC0361c.a();
        try {
            if (!e0()) {
                if (zS) {
                    return;
                } else {
                    return;
                }
            }
            long j2 = Long.MAX_VALUE;
            while (true) {
                Thread.interrupted();
                long jT = T();
                if (jT == LocationRequestCompat.PASSIVE_INTERVAL) {
                    AbstractC0361c.a();
                    long jNanoTime = System.nanoTime();
                    if (j2 == LocationRequestCompat.PASSIVE_INTERVAL) {
                        j2 = f4377h + jNanoTime;
                    }
                    long j3 = j2 - jNanoTime;
                    if (j3 <= 0) {
                        _thread = null;
                        a0();
                        AbstractC0361c.a();
                        if (S()) {
                            return;
                        }
                        J();
                        return;
                    }
                    jT = c1.f.g(jT, j3);
                } else {
                    j2 = Long.MAX_VALUE;
                }
                if (jT > 0) {
                    if (d0()) {
                        _thread = null;
                        a0();
                        AbstractC0361c.a();
                        if (S()) {
                            return;
                        }
                        J();
                        return;
                    }
                    AbstractC0361c.a();
                    LockSupport.parkNanos(this, jT);
                }
            }
        } finally {
            _thread = null;
            a0();
            AbstractC0361c.a();
            if (!S()) {
                J();
            }
        }
    }
}
