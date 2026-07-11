package com.xiaomi.onetrack.util;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3627a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3628b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3629c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f3630d = 3000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3631e = "OneTrack-Api-";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f3632f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f3633g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f3634h = 2;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f3635i = 3;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final int f3636j = 4;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static boolean f3637k = false;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static boolean f3638l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static boolean f3639m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static boolean f3640n = false;

    public static void a() {
        try {
            String strE = com.xiaomi.onetrack.f.a.e();
            String strA = ac.a("debug.onetrack.log");
            boolean z2 = false;
            f3638l = (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strE) || !TextUtils.equals(strE, strA)) ? false : true;
            String strA2 = ac.a("debug.onetrack.upload");
            f3628b = (TextUtils.isEmpty(strA2) || TextUtils.isEmpty(strE) || !TextUtils.equals(strE, strA2)) ? false : true;
            String strA3 = ac.a("debug.onetrack.test");
            if (!TextUtils.isEmpty(strA3) && !TextUtils.isEmpty(strE) && TextUtils.equals(strE, strA3)) {
                z2 = true;
            }
            f3640n = z2;
            b();
            c();
        } catch (Exception e2) {
            Log.e("OneTrackSdk", "LogUtil static initializer: " + e2.toString());
        }
        Log.d("OneTrackSdk", "log on: " + f3638l + ", quick upload on: " + f3628b);
    }

    public static void b(String str, String str2) {
        if (f3627a) {
            a(a(str), str2, 0);
        }
    }

    public static void c(String str, String str2) {
        if (f3627a) {
            a(a(str), str2, 1);
        }
    }

    public static void d(String str, String str2) {
        if (f3627a) {
            a(a(str), str2, 2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f3627a) {
            Log.e(a(str), str2, th);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (f3627a) {
            Log.w(a(str), str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (f3627a) {
            Log.i(a(str), str2, th);
        }
    }

    private static void b() {
        f3627a = f3637k || f3638l;
        Log.d("OneTrackSdk", "updateDebugSwitch sEnable: " + f3627a + " sDebugMode：" + f3637k + " sDebugProperty：" + f3638l);
    }

    private static void c() {
        f3629c = f3639m || f3640n;
        Log.d("OneTrackSdk", "updateTestSwitch sTestEnable: " + f3629c + " sTestMode：" + f3639m + " sTestProperty：" + f3640n);
    }

    public static void b(boolean z2) {
        f3639m = z2;
        c();
    }

    public static void a(String str, String str2) {
        if (f3627a) {
            a(a(str), str2, 3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f3627a) {
            Log.d(a(str), str2, th);
        }
    }

    private static void a(String str, String str2, int i2) {
        if (str2 == null) {
            return;
        }
        int i3 = 0;
        while (i3 <= str2.length() / 3000) {
            int i4 = i3 * 3000;
            i3++;
            int iMin = Math.min(str2.length(), i3 * 3000);
            if (i4 < iMin) {
                String strSubstring = str2.substring(i4, iMin);
                if (i2 == 0) {
                    Log.e(str, strSubstring);
                } else if (i2 == 1) {
                    Log.w(str, strSubstring);
                } else if (i2 == 2) {
                    Log.i(str, strSubstring);
                } else if (i2 == 3) {
                    Log.d(str, strSubstring);
                } else if (i2 == 4) {
                    Log.v(str, strSubstring);
                }
            }
        }
    }

    public static String a(String str) {
        return f3631e + str;
    }

    public static void a(boolean z2) {
        f3637k = z2;
        b();
    }
}
