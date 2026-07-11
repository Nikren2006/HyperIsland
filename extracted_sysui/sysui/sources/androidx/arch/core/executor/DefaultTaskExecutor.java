package androidx.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class DefaultTaskExecutor extends TaskExecutor {

    @Nullable
    private volatile Handler mMainHandler;
    private final Object mLock = new Object();
    private final ExecutorService mDiskIO = Executors.newFixedThreadPool(4, new ThreadFactory() { // from class: androidx.arch.core.executor.DefaultTaskExecutor.1
        private static final String THREAD_NAME_STEM = "arch_disk_io_";
        private final AtomicInteger mThreadId = new AtomicInteger(0);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName(THREAD_NAME_STEM + this.mThreadId.getAndIncrement());
            return thread;
        }
    });

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        @NonNull
        public static Handler createAsync(@NonNull Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    @NonNull
    private static Handler createAsync(@NonNull Looper looper) {
        return Api28Impl.createAsync(looper);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void executeOnDiskIO(@NonNull Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // androidx.arch.core.executor.TaskExecutor
    public void postToMainThread(@NonNull Runnable runnable) {
        if (this.mMainHandler == null) {
            synchronized (this.mLock) {
                try {
                    if (this.mMainHandler == null) {
                        this.mMainHandler = createAsync(Looper.getMainLooper());
                    }
                } finally {
                }
            }
        }
        this.mMainHandler.post(runnable);
    }
}
