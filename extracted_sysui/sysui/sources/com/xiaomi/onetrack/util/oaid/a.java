package com.xiaomi.onetrack.util.oaid;

import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.onetrack.util.o;
import com.xiaomi.onetrack.util.oaid.helpers.b;
import com.xiaomi.onetrack.util.oaid.helpers.h;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import com.xiaomi.onetrack.util.x;
import miui.systemui.devicecontrols.ui.TouchBehavior;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3532a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile a f3533b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static long f3534d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile String f3535c = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3536e = 3;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile int f3537f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3538g = false;

    public static a a() {
        if (f3533b == null) {
            synchronized (a.class) {
                try {
                    if (f3533b == null) {
                        f3533b = new a();
                    }
                } finally {
                }
            }
        }
        return f3533b;
    }

    private boolean d() {
        if (this.f3537f > 3) {
            return true;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jAbs = Math.abs(jElapsedRealtime - f3534d);
        if (this.f3537f == 1) {
            if (jAbs < TouchBehavior.STATELESS_ENABLE_TIMEOUT_IN_MILLIS) {
                return true;
            }
        } else if (this.f3537f == 2) {
            if (jAbs < 10000) {
                return true;
            }
        } else if (this.f3537f == 3 && jAbs < 60000) {
            return true;
        }
        q.a(f3532a, "get time：" + this.f3537f);
        f3534d = jElapsedRealtime;
        return false;
    }

    public void b() {
        this.f3537f = 0;
    }

    public boolean c() {
        return (this.f3535c == null || this.f3535c.equals("")) ? false : true;
    }

    public void a(boolean z2) {
        this.f3538g = z2;
        q.a(f3532a, "setCloseOaidDependMsaSDK：" + this.f3538g);
    }

    public String a(Context context) {
        String strA;
        synchronized (this.f3535c) {
            try {
                if (x.a()) {
                    if (!q.f3627a) {
                        q.b(f3532a, "getOaid() throw exception : Don't use it on the main thread");
                        return "";
                    }
                    throw new IllegalStateException("Don't use it on the main thread");
                }
                if (this.f3535c != null && !this.f3535c.equals("")) {
                    return this.f3535c;
                }
                if (d()) {
                    q.a(f3532a, "isNotAllowedGetOaid");
                    return this.f3535c;
                }
                if (r.b()) {
                    this.f3535c = o.a(context);
                    this.f3537f++;
                    return this.f3535c;
                }
                if (!this.f3538g && (strA = new h().a(context)) != null && !strA.equals("")) {
                    this.f3535c = strA;
                    this.f3537f++;
                    return strA;
                }
                String strA2 = new b().a(context);
                if (strA2 != null && !strA2.equals("")) {
                    this.f3535c = strA2;
                    this.f3537f++;
                    return strA2;
                }
                this.f3537f++;
                return this.f3535c;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
