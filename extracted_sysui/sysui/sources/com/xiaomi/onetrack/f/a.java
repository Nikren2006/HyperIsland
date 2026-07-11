package com.xiaomi.onetrack.f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.xiaomi.onetrack.util.j;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f3273a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Context f3274b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f3275c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f3276d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f3277e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static long f3278f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static volatile boolean f3279g = false;

    public static void a(Context context) {
        if (f3279g) {
            return;
        }
        synchronized (a.class) {
            try {
                if (f3279g) {
                    return;
                }
                f3273a = context;
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(f3273a.getPackageName(), 0);
                    f3275c = packageInfo.versionCode;
                    f3276d = packageInfo.versionName;
                    f3278f = packageInfo.lastUpdateTime;
                    f3277e = f3273a.getPackageName();
                } catch (PackageManager.NameNotFoundException e2) {
                    e2.printStackTrace();
                }
                f3279g = true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfoA = a(context, str, 0);
        return (packageInfoA == null || packageInfoA.applicationInfo == null) ? false : true;
    }

    public static String c(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(str, 0).loadLabel(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int d() {
        return f3275c;
    }

    public static String e() {
        return f3277e;
    }

    public static long f() {
        return f3278f;
    }

    public static Context b() {
        return f3273a;
    }

    public static String c() {
        return f3276d;
    }

    public static boolean a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 1) != 0;
    }

    public static boolean a(Context context, String str) {
        try {
            return a(a(context, str, 0).applicationInfo);
        } catch (Exception unused) {
            return false;
        }
    }

    public static PackageInfo a(Context context, String str, int i2) {
        try {
            return context.getPackageManager().getPackageInfo(str, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Context a() {
        if (j.d(f3273a)) {
            Context context = f3274b;
            if (context != null) {
                return context;
            }
            synchronized (a.class) {
                try {
                    if (f3274b == null) {
                        f3274b = j.a(f3273a);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return f3274b;
        }
        return f3273a;
    }
}
