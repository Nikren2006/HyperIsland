package miui.systemui.util;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes4.dex */
public class ThreadUtils {
    private static volatile Thread sMainThread;
    private static volatile Handler sMainThreadHandler;
    private static volatile ExecutorService sThreadExecutor;

    public static void ensureMainThread() {
        if (!isMainThread()) {
            throw new RuntimeException("Must be called on the UI thread");
        }
    }

    private static synchronized ExecutorService getThreadExecutor() {
        try {
            if (sThreadExecutor == null) {
                sThreadExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            }
        } catch (Throwable th) {
            throw th;
        }
        return sThreadExecutor;
    }

    public static Handler getUiThreadHandler() {
        if (sMainThreadHandler == null) {
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return sMainThreadHandler;
    }

    public static boolean isMainThread() {
        if (sMainThread == null) {
            sMainThread = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == sMainThread;
    }

    public static Future postOnBackgroundThread(Runnable runnable) {
        return getThreadExecutor().submit(runnable);
    }

    public static void postOnMainThread(Runnable runnable) {
        getUiThreadHandler().post(runnable);
    }

    public static Future postOnBackgroundThread(Callable callable) {
        return getThreadExecutor().submit(callable);
    }
}
