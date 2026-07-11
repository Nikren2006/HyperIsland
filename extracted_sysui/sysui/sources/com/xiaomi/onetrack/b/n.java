package com.xiaomi.onetrack.b;

import android.text.TextUtils;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3102a = "ConfigProvider";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile boolean f3103b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile boolean f3104c = true;

    public static boolean a() {
        try {
            String[] strArrB = com.xiaomi.onetrack.d.f.a().b();
            return (!TextUtils.isEmpty(strArrB[0]) && !TextUtils.isEmpty(strArrB[1])) && !r.a(f3102a);
        } catch (Exception e2) {
            q.a(f3102a, "ConfigProvider.available", e2);
            return false;
        }
    }

    public static synchronized boolean b() {
        return f3103b;
    }

    public static boolean c() {
        return f3104c;
    }

    public static void b(boolean z2) {
        f3104c = z2;
    }

    public static int a(int i2) {
        int iIntValue;
        if (q.f3628b) {
            q.a(f3102a, "debug upload mode, send events immediately");
            return 0;
        }
        try {
            iIntValue = e.c().get(Integer.valueOf(i2 + 1)).intValue();
        } catch (Exception unused) {
            iIntValue = 60000;
        }
        q.a(f3102a, "getUploadInterval " + iIntValue);
        return iIntValue;
    }

    public static synchronized void a(boolean z2) {
        f3103b = z2;
    }
}
