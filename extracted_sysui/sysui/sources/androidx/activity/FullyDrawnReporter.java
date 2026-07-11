package androidx.activity;

import H0.s;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FullyDrawnReporter {
    private final Executor executor;
    private final Object lock;

    @GuardedBy("lock")
    private final List<Function0> onReportCallbacks;
    private final Function0 reportFullyDrawn;

    @GuardedBy("lock")
    private boolean reportPosted;
    private final Runnable reportRunnable;

    @GuardedBy("lock")
    private boolean reportedFullyDrawn;

    @GuardedBy("lock")
    private int reporterCount;

    public FullyDrawnReporter(Executor executor, Function0 reportFullyDrawn) {
        n.g(executor, "executor");
        n.g(reportFullyDrawn, "reportFullyDrawn");
        this.executor = executor;
        this.reportFullyDrawn = reportFullyDrawn;
        this.lock = new Object();
        this.onReportCallbacks = new ArrayList();
        this.reportRunnable = new Runnable() { // from class: androidx.activity.g
            @Override // java.lang.Runnable
            public final void run() {
                FullyDrawnReporter.reportRunnable$lambda$2(this.f1119a);
            }
        };
    }

    private final void postWhenReportersAreDone() {
        if (this.reportPosted || this.reporterCount != 0) {
            return;
        }
        this.reportPosted = true;
        this.executor.execute(this.reportRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reportRunnable$lambda$2(FullyDrawnReporter this$0) {
        n.g(this$0, "this$0");
        synchronized (this$0.lock) {
            try {
                this$0.reportPosted = false;
                if (this$0.reporterCount == 0 && !this$0.reportedFullyDrawn) {
                    this$0.reportFullyDrawn.invoke();
                    this$0.fullyDrawnReported();
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void addOnReportDrawnListener(Function0 callback) {
        boolean z2;
        n.g(callback, "callback");
        synchronized (this.lock) {
            if (this.reportedFullyDrawn) {
                z2 = true;
            } else {
                this.onReportCallbacks.add(callback);
                z2 = false;
            }
        }
        if (z2) {
            callback.invoke();
        }
    }

    public final void addReporter() {
        synchronized (this.lock) {
            try {
                if (!this.reportedFullyDrawn) {
                    this.reporterCount++;
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void fullyDrawnReported() {
        synchronized (this.lock) {
            try {
                this.reportedFullyDrawn = true;
                Iterator<T> it = this.onReportCallbacks.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).invoke();
                }
                this.onReportCallbacks.clear();
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isFullyDrawnReported() {
        boolean z2;
        synchronized (this.lock) {
            z2 = this.reportedFullyDrawn;
        }
        return z2;
    }

    public final void removeOnReportDrawnListener(Function0 callback) {
        n.g(callback, "callback");
        synchronized (this.lock) {
            this.onReportCallbacks.remove(callback);
            s sVar = s.f314a;
        }
    }

    public final void removeReporter() {
        int i2;
        synchronized (this.lock) {
            try {
                if (!this.reportedFullyDrawn && (i2 = this.reporterCount) > 0) {
                    this.reporterCount = i2 - 1;
                    postWhenReportersAreDone();
                }
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
