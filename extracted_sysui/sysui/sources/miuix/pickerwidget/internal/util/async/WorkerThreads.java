package miuix.pickerwidget.internal.util.async;

import android.os.HandlerThread;
import android.os.Looper;
import android.util.ArrayMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class WorkerThreads {
    public static final String TAG_COMMON_WORK = "common_work";
    private static final Map<String, ThreadWrapper> sWorkers = new ArrayMap();

    public static class ThreadWrapper {
        int refCount = 1;
        final HandlerThread thread;

        public ThreadWrapper(String str) {
            HandlerThread handlerThread = new HandlerThread(str);
            this.thread = handlerThread;
            handlerThread.start();
        }
    }

    private WorkerThreads() {
    }

    public static synchronized Looper acquireWorker(String str) {
        ThreadWrapper threadWrapper;
        try {
            Map<String, ThreadWrapper> map = sWorkers;
            threadWrapper = map.get(str);
            if (threadWrapper == null) {
                threadWrapper = new ThreadWrapper(str);
                map.put(str, threadWrapper);
            } else {
                threadWrapper.refCount++;
            }
        } catch (Throwable th) {
            throw th;
        }
        return threadWrapper.thread.getLooper();
    }

    public static synchronized void releaseWorker(String str) {
        Map<String, ThreadWrapper> map = sWorkers;
        ThreadWrapper threadWrapper = map.get(str);
        if (threadWrapper != null) {
            int i2 = threadWrapper.refCount - 1;
            threadWrapper.refCount = i2;
            if (i2 == 0) {
                map.remove(str);
                threadWrapper.thread.quitSafely();
            }
        }
    }
}
