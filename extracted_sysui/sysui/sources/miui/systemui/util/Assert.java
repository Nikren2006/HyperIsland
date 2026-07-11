package miui.systemui.util;

import android.os.Looper;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes4.dex */
public class Assert {
    private static final Looper sMainLooper = Looper.getMainLooper();
    private static Thread sTestThread = null;

    public static void isMainThread() {
        Looper looper = sMainLooper;
        if (looper.isCurrentThread()) {
            return;
        }
        Thread thread = sTestThread;
        if (thread == null || thread != Thread.currentThread()) {
            throw new IllegalStateException("should be called from the main thread. sMainLooper.threadName=" + looper.getThread().getName() + " Thread.currentThread()=" + Thread.currentThread().getName());
        }
    }

    public static void isNotMainThread() {
        if (sMainLooper.isCurrentThread()) {
            Thread thread = sTestThread;
            if (thread == null || thread == Thread.currentThread()) {
                throw new IllegalStateException("should not be called from the main thread.");
            }
        }
    }

    @VisibleForTesting
    public static void setTestThread(Thread thread) {
        sTestThread = thread;
    }

    @VisibleForTesting
    public static void setTestableLooper(Looper looper) {
        setTestThread(looper == null ? null : looper.getThread());
    }
}
