package com.xiaomi.onetrack.a.a;

import android.os.Handler;
import android.os.HandlerThread;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2714a = "AdMonitorDbExecutor";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2715b = "onetrack_ad_monitor_db";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Handler f2716c;

    private static void a() {
        if (f2716c == null) {
            synchronized (a.class) {
                try {
                    if (f2716c == null) {
                        HandlerThread handlerThread = new HandlerThread(f2715b);
                        handlerThread.start();
                        f2716c = new Handler(handlerThread.getLooper());
                    }
                } finally {
                }
            }
        }
    }

    public static void a(Runnable runnable) {
        a();
        f2716c.post(runnable);
    }

    public static void a(Runnable runnable, long j2) {
        try {
            a();
            f2716c.postDelayed(runnable, j2);
        } catch (Throwable th) {
            q.b(f2714a, th.getMessage());
        }
    }
}
