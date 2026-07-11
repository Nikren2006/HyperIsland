package com.xiaomi.onetrack.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private static final String f3683A = "/track/key_get";

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private static final String f3684B = "/api/v4/detail/config";

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    private static final String f3685C = "/api/v4/detail/config_common";

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    private static final String f3686D = "/api/v1/token";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3688a = "RegionDomainManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3689b = "CN";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3690c = "INTL";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3691d = "IN";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3692e = "RU";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3693f = "http://";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f3694g = "https://";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f3695h = "";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static String f3696i = "";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static String f3697j = "";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static String f3698k = "";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static String f3699l = "";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static String f3700m = "";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static String f3701n = "";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static String f3702o = "";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static String f3703p = "";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final String f3713z = "/track/v4";

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    private Context f3714E;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final byte[] f3704q = {116, 114, 97, 99, 107, 105, 110, 103, 46, 109, 105, 117, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final byte[] f3705r = {116, 114, 97, 99, 107, 105, 110, 103, 46, 105, 110, 116, 108, 46, 109, 105, 117, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final byte[] f3706s = {116, 114, 97, 99, 107, 105, 110, 103, 46, 114, 117, 115, 46, 109, 105, 117, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final byte[] f3707t = {116, 114, 97, 99, 107, 105, 110, 103, 46, 105, 110, 100, 105, 97, 46, 109, 105, 117, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final byte[] f3708u = {115, 100, 107, 99, 111, 110, 102, 105, 103, 46, 120, 105, 97, 111, 109, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static final byte[] f3709v = {115, 100, 107, 99, 111, 110, 102, 105, 103, 46, 105, 110, 116, 108, 46, 120, 105, 97, 111, 109, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final byte[] f3710w = {115, 100, 107, 99, 111, 110, 102, 105, 103, 46, 105, 110, 100, 105, 97, 46, 120, 105, 97, 111, 109, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final byte[] f3711x = {115, 100, 107, 99, 111, 110, 102, 105, 103, 46, 114, 117, 115, 46, 120, 105, 97, 111, 109, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static final byte[] f3712y = {115, 100, 107, 99, 111, 110, 102, 105, 103, 46, 101, 117, 46, 120, 105, 97, 111, 109, 105, 46, 99, 111, 109};

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    private static ConcurrentHashMap<String, String> f3687F = new ConcurrentHashMap<>();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final y f3715a = new y();

        private a() {
        }
    }

    private void g() {
        f3695h = a(f3704q);
        f3696i = a(f3705r);
        f3697j = a(f3706s);
        f3698k = a(f3707t);
        f3699l = a(f3708u);
        f3700m = a(f3709v);
        f3701n = a(f3710w);
        f3702o = a(f3711x);
        f3703p = a(f3712y);
    }

    private void h() {
        try {
            String strH = ab.h();
            if (TextUtils.isEmpty(strH)) {
                return;
            }
            a(new JSONObject(strH));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private String i() {
        return f3694g;
    }

    private String j() {
        return a(r.k(), r.l());
    }

    private String k() {
        boolean zK = r.k();
        String strL = r.l();
        if (!zK) {
            return f3699l;
        }
        if (TextUtils.equals(strL, f3691d)) {
            return f3701n;
        }
        if (TextUtils.equals(strL, f3692e)) {
            return f3702o;
        }
        try {
            return com.xiaomi.onetrack.util.a.f3425j.contains(strL.toUpperCase()) ? f3703p : f3700m;
        } catch (Exception e2) {
            q.b(f3688a, "getSdkConfigHost error: ", e2);
            return f3700m;
        }
    }

    public String a(byte[] bArr) {
        String str = "";
        try {
            String str2 = new String(bArr, "UTF-8");
            try {
                q.a(f3688a, "transmitToString host:" + str2);
                return str2;
            } catch (Exception e2) {
                e = e2;
                str = str2;
                q.b(f3688a, e.getMessage());
                return str;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public String b() {
        try {
            if (TextUtils.isEmpty(ab.l())) {
                com.xiaomi.onetrack.b.e.b();
            }
        } catch (Exception e2) {
            q.a(f3688a, "getTrackingUrl: " + e2.toString());
        }
        return a(i(), j(), f3713z);
    }

    public String c() {
        return a(i(), k(), f3684B);
    }

    public String d() {
        return a(i(), k(), f3685C);
    }

    public String e() {
        return a(i(), j(), f3683A);
    }

    public String f() {
        try {
            if (TextUtils.isEmpty(ab.l())) {
                com.xiaomi.onetrack.b.e.b();
            }
        } catch (Exception e2) {
            q.a(f3688a, "getTrackingUrl: " + e2.toString());
        }
        return a(i(), k(), f3686D);
    }

    private y() {
        g();
        f3687F.put(f3691d, f3698k);
        f3687F.put(f3692e, f3697j);
        h();
    }

    public static y a() {
        return a.f3715a;
    }

    public synchronized void a(JSONObject jSONObject) {
        try {
            q.a(f3688a, "updateHostMap:" + jSONObject.toString());
            try {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    String strOptString = jSONObject.optString(next);
                    if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(strOptString)) {
                        f3687F.put(next, strOptString);
                    }
                }
                ab.b(new JSONObject(f3687F).toString());
            } catch (Exception e2) {
                q.a(f3688a, "updateHostMap: " + e2.toString());
            }
            q.a(f3688a, "merge config:" + new JSONObject(f3687F).toString());
        } catch (Throwable th) {
            throw th;
        }
    }

    public String a(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    private String a(boolean z2, String str) {
        if (!z2) {
            return f3695h;
        }
        String str2 = f3687F.get(str);
        return TextUtils.isEmpty(str2) ? f3696i : str2;
    }
}
