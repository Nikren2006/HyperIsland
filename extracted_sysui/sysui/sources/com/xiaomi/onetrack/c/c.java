package com.xiaomi.onetrack.c;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3114a = "DbExecutor";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3115b = "onetrack_db";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Handler f3116c;

    private static void a() {
        if (f3116c == null) {
            synchronized (c.class) {
                try {
                    if (f3116c == null) {
                        HandlerThread handlerThread = new HandlerThread(f3115b);
                        handlerThread.start();
                        f3116c = new Handler(handlerThread.getLooper());
                    }
                } finally {
                }
            }
        }
    }

    public static void a(Runnable runnable) {
        try {
            a();
            f3116c.post(runnable);
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f3114a, th.getMessage());
        }
    }

    public static void a(Runnable runnable, long j2) {
        try {
            a();
            f3116c.postDelayed(runnable, j2);
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f3114a, th.getMessage());
        }
    }
}
