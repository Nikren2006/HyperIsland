package com.xiaomi.onetrack.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3105a = "BroadcastExecutor";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3106b = "onetrack_broadcast";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Handler f3107c;

    public static void a() {
        if (f3107c == null) {
            synchronized (a.class) {
                try {
                    if (f3107c == null) {
                        Log.d(f3105a, "initIfNeeded : " + Thread.currentThread().getId());
                        HandlerThread handlerThread = new HandlerThread(f3106b);
                        handlerThread.start();
                        f3107c = new Handler(handlerThread.getLooper());
                    }
                } finally {
                }
            }
        }
    }

    public static void a(Runnable runnable) {
        Log.d(f3105a, "BroadcastExecutor : " + Thread.currentThread().getId());
        a();
        f3107c.post(runnable);
    }
}
