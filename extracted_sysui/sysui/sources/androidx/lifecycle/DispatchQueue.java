package androidx.lifecycle;

import L0.g;
import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import g1.Q;
import g1.w0;
import java.util.ArrayDeque;
import java.util.Queue;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class DispatchQueue {
    private boolean finished;
    private boolean isDraining;
    private boolean paused = true;
    private final Queue<Runnable> queue = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dispatchAndEnqueue$lambda$2$lambda$1(DispatchQueue this$0, Runnable runnable) {
        n.g(this$0, "this$0");
        n.g(runnable, "$runnable");
        this$0.enqueue(runnable);
    }

    @MainThread
    private final void enqueue(Runnable runnable) {
        if (!this.queue.offer(runnable)) {
            throw new IllegalStateException("cannot enqueue any more runnables");
        }
        drainQueue();
    }

    @MainThread
    public final boolean canRun() {
        return this.finished || !this.paused;
    }

    @AnyThread
    public final void dispatchAndEnqueue(g context, final Runnable runnable) {
        n.g(context, "context");
        n.g(runnable, "runnable");
        w0 w0VarZ = Q.c().z();
        if (w0VarZ.isDispatchNeeded(context) || canRun()) {
            w0VarZ.dispatch(context, new Runnable() { // from class: androidx.lifecycle.c
                @Override // java.lang.Runnable
                public final void run() {
                    DispatchQueue.dispatchAndEnqueue$lambda$2$lambda$1(this.f1257a, runnable);
                }
            });
        } else {
            enqueue(runnable);
        }
    }

    @MainThread
    public final void drainQueue() {
        if (this.isDraining) {
            return;
        }
        try {
            this.isDraining = true;
            while (!this.queue.isEmpty() && canRun()) {
                Runnable runnablePoll = this.queue.poll();
                if (runnablePoll != null) {
                    runnablePoll.run();
                }
            }
        } finally {
            this.isDraining = false;
        }
    }

    @MainThread
    public final void finish() {
        this.finished = true;
        drainQueue();
    }

    @MainThread
    public final void pause() {
        this.paused = true;
    }

    @MainThread
    public final void resume() {
        if (this.paused) {
            if (this.finished) {
                throw new IllegalStateException("Cannot resume a finished dispatcher");
            }
            this.paused = false;
            drainQueue();
        }
    }
}
