package com.xiaomi.onetrack.api;

import android.os.Process;
import android.util.Log;
import java.lang.Thread;
import java.util.Date;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class al implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2868a = "OneTrackExceptionHandler";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2869c = "tombstone";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2870d = ".java.xcrash";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2871e = "backtrace feature id:\n\t";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f2872f = "error reason:\n\t";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final long f2873h = 2;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f2874b;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private c f2876i;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Date f2875g = new Date();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f2877j = 50;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f2878k = 50;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f2879l = 200;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f2880m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f2881n = true;

    public al(c cVar) {
        this.f2876i = cVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Log.d(com.xiaomi.onetrack.util.q.a(f2868a), "crash happened->stacktrace: " + th.getStackTrace());
        FutureTask futureTask = new FutureTask(new am(this, thread, th), null);
        com.xiaomi.onetrack.util.i.a(futureTask);
        try {
            futureTask.get(2L, TimeUnit.SECONDS);
        } catch (Exception e2) {
            Log.e(com.xiaomi.onetrack.util.q.a(f2868a), "handleException error :" + e2.getMessage());
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2874b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public void a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler instanceof al) {
            return;
        }
        this.f2874b = defaultUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:80:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x0165 -> B:87:0x0185). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.Thread r16, java.lang.Throwable r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.api.al.a(java.lang.Thread, java.lang.Throwable):void");
    }

    private String a(Date date, Thread thread, String str) {
        return com.xiaomi.onetrack.util.b.a(this.f2875g, date, "java", com.xiaomi.onetrack.f.a.e(), com.xiaomi.onetrack.util.b.a(com.xiaomi.onetrack.f.a.b())) + "pid: " + Process.myPid() + ", tid: " + Process.myTid() + ", name: " + thread.getName() + "  >>> " + com.xiaomi.onetrack.f.a.e() + " <<<\n\njava stacktrace:\n" + str + "\n";
    }
}
