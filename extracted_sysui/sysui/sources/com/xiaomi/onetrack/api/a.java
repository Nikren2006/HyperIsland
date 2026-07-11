package com.xiaomi.onetrack.api;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2741a = "action";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2742b = "AppActiveBroadcastManager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f2743c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2744d = "content://com.miui.analytics.OneTrackProvider/traceId";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2745e = "traceId";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f2746f = "pkg";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f2747g = "sign";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static String f2748j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f2749k = "package";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f2750l = "installer";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f2751m = "userId";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f2752n = "miuiActiveId";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final String f2753o = "miuiActiveTime";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final String f2754p = "activeTime";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final String f2755q = "queryTime";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final Set<String> f2756r = new HashSet(Arrays.asList("com.xiaomi.xmsf", "com.xiaomi.market", "com.miui.packageinstaller"));

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Context f2757h = com.xiaomi.onetrack.f.a.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Context f2758i = com.xiaomi.onetrack.f.a.b();

    private a() {
        f2748j = com.xiaomi.onetrack.f.a.e();
    }

    public static void b() {
        if (f2743c == null) {
            synchronized (a.class) {
                try {
                    if (f2743c == null) {
                        f2743c = new a();
                    }
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean f() {
        try {
            int i2 = com.xiaomi.onetrack.f.a.b().getPackageManager().getPackageInfo(at.f2905a, 0).versionCode;
            if (i2 >= 2023010300) {
                return true;
            }
            com.xiaomi.onetrack.util.q.a(f2742b, "not support getTraceId versionCode: " + i2);
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f2742b, "isSupportEmptyEvent error:" + th.getMessage());
        }
        return false;
    }

    public boolean c() {
        return f2756r.contains(com.xiaomi.onetrack.f.a.b().getPackageName());
    }

    public static a a() {
        if (f2743c == null) {
            b();
        }
        return f2743c;
    }

    public String a(Intent intent) {
        String str;
        String str2 = "";
        FutureTask futureTask = new FutureTask(new b(this, intent));
        long jCurrentTimeMillis = System.currentTimeMillis();
        com.xiaomi.onetrack.util.i.a(futureTask);
        try {
            str = (String) futureTask.get(5L, TimeUnit.SECONDS);
            try {
                if (com.xiaomi.onetrack.util.q.f3627a) {
                    com.xiaomi.onetrack.util.q.a(f2742b, "packageName:" + f2748j + " _start ------getTraceId:" + str + "  _tid:" + Process.myTid());
                }
            } catch (Exception e2) {
                e = e2;
                str2 = str;
                com.xiaomi.onetrack.util.q.b(f2742b, "getTraceId error: " + e.toString());
                str = str2;
            }
        } catch (Exception e3) {
            e = e3;
        }
        com.xiaomi.onetrack.util.q.a(f2742b, "packageName:" + f2748j + " _end ------getTraceId:" + str + "  _tid:" + Process.myTid() + " diffTime:" + (System.currentTimeMillis() - jCurrentTimeMillis));
        return str;
    }
}
