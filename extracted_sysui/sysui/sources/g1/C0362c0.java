package g1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import l1.AbstractC0448c;

/* JADX INFO: renamed from: g1.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0362c0 extends AbstractC0360b0 implements L {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f4414b;

    public C0362c0(Executor executor) {
        this.f4414b = executor;
        AbstractC0448c.a(A());
    }

    public Executor A() {
        return this.f4414b;
    }

    public final ScheduledFuture B(ScheduledExecutorService scheduledExecutorService, Runnable runnable, L0.g gVar, long j2) {
        try {
            return scheduledExecutorService.schedule(runnable, j2, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e2) {
            this.z(gVar, e2);
            return null;
        }
    }

    @Override // g1.AbstractC0360b0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executorA = A();
        ExecutorService executorService = executorA instanceof ExecutorService ? (ExecutorService) executorA : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // g1.B
    public void dispatch(L0.g gVar, Runnable runnable) {
        try {
            Executor executorA = A();
            AbstractC0361c.a();
            executorA.execute(runnable);
        } catch (RejectedExecutionException e2) {
            AbstractC0361c.a();
            z(gVar, e2);
            Q.b().dispatch(gVar, runnable);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof C0362c0) && ((C0362c0) obj).A() == A();
    }

    public int hashCode() {
        return System.identityHashCode(A());
    }

    @Override // g1.L
    public void t(long j2, InterfaceC0377k interfaceC0377k) {
        Executor executorA = A();
        ScheduledExecutorService scheduledExecutorService = executorA instanceof ScheduledExecutorService ? (ScheduledExecutorService) executorA : null;
        ScheduledFuture scheduledFutureB = scheduledExecutorService != null ? B(scheduledExecutorService, new C0(this, interfaceC0377k), interfaceC0377k.getContext(), j2) : null;
        if (scheduledFutureB != null) {
            AbstractC0388p0.f(interfaceC0377k, scheduledFutureB);
        } else {
            J.f4376g.t(j2, interfaceC0377k);
        }
    }

    @Override // g1.B
    public String toString() {
        return A().toString();
    }

    public final void z(L0.g gVar, RejectedExecutionException rejectedExecutionException) {
        AbstractC0388p0.c(gVar, AbstractC0358a0.a("The task was rejected", rejectedExecutionException));
    }
}
