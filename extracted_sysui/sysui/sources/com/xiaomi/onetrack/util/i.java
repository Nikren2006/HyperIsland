package com.xiaomi.onetrack.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ThreadPoolExecutor f3514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f3515b = Runtime.getRuntime().availableProcessors() + 1;

    static {
        int i2 = f3515b;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i2, i2, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        f3514a = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static void a(Runnable runnable) {
        try {
            f3514a.execute(runnable);
        } catch (Throwable unused) {
        }
    }
}
