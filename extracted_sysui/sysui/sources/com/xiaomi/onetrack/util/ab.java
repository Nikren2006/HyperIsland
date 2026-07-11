package com.xiaomi.onetrack.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ab {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private static final String f3431A = "onetrack_user_id";

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private static final String f3432B = "onetrack_user_type";

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    private static final String f3433C = "custom_id";

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    private static final String f3434D = "page_end";

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    private static final String f3435E = "last_app_version";

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    private static final String f3436F = "first_launch_time";

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    private static final String f3437G = "pref_custom_privacy_policy_";

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    private static final String f3438H = "app_config_region";

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    private static final String f3439I = "loc_token";

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    private static final String f3440J = "loc_config";

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    private static final String f3441K = "sdk_token_init_time";

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    private static final String f3442L = "PubSub_Project_Manager_init_time";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3443a = "onetrack";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3444b = "one_track_pref";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SharedPreferences f3445c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static SharedPreferences.Editor f3446d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3447e = "last_upload_active_time";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3448f = "last_upload_usage_time";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f3449g = "last_collect_crash_time";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f3450h = "report_crash_ticket";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String f3451i = "last_secret_key_time";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String f3452j = "last_common_cloud_time";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f3453k = "next_update_common_conf_time";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f3454l = "common_cloud_data";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f3455m = "common_config_hash";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f3456n = "s_t";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final String f3457o = "l_t";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final String f3458p = "e_t";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final String f3459q = "secret_key_data";

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final String f3460r = "region_rul";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final String f3461s = "pref_instance_id";

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final String f3462t = "pref_instance_id_last_use_time";

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final String f3463u = "last_usage_resume_component";

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static final String f3464v = "last_usage_resume_time";

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static final String f3465w = "network_access_enabled";

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final String f3466x = "anonymous_enabled";

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static final String f3467y = "onetrack_first_open";

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final String f3468z = "dau_last_time";

    public static String A() {
        return a(f3435E, "");
    }

    public static long B() {
        return a(f3436F, 0L);
    }

    public static String C() {
        return a(f3438H, "");
    }

    public static String D() {
        return a(f3439I, "");
    }

    public static String E() {
        return a(f3440J, "");
    }

    public static long F() {
        return a(f3441K, 0L);
    }

    public static long G() {
        return a(f3442L, 0L);
    }

    private static void H() {
        if (f3446d != null) {
            return;
        }
        synchronized (ab.class) {
            try {
                if (f3446d == null) {
                    SharedPreferences sharedPreferences = com.xiaomi.onetrack.f.a.a().getSharedPreferences(f3444b, 0);
                    f3445c = sharedPreferences;
                    f3446d = sharedPreferences.edit();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static String a(String str, String str2) {
        H();
        return f3445c.getString(str, str2);
    }

    private static void b(String str, String str2) {
        H();
        f3446d.putString(str, str2).apply();
    }

    private static void c(String str, boolean z2) {
        H();
        f3446d.putBoolean(str, z2).apply();
    }

    public static void d(long j2) {
        b(f3450h, j2);
    }

    public static void e(long j2) {
        b(f3448f, j2);
    }

    public static void f(long j2) {
        b(f3456n, j2);
    }

    public static void g(long j2) {
        b(f3457o, j2);
    }

    public static void h(long j2) {
        b(f3458p, j2);
    }

    public static void i(long j2) {
        b(f3451i, j2);
    }

    public static void j(long j2) {
        b(f3453k, j2);
    }

    public static String k() {
        return a(f3455m, "");
    }

    public static String l() {
        return a(f3454l, "");
    }

    public static String m() {
        return a(f3461s, "");
    }

    public static long n() {
        return a(f3462t, 0L);
    }

    private static void o(String str) {
        H();
        f3446d.remove(str).apply();
    }

    public static long p() {
        return a(f3464v, 0L);
    }

    public static boolean q() {
        return b(f3465w, true);
    }

    public static boolean r() {
        return b(f3466x, false);
    }

    public static boolean s() {
        return b(f3467y, true);
    }

    public static long t() {
        return a(f3468z, 0L);
    }

    public static String u() {
        return a(f3431A, "");
    }

    public static void v() {
        o(f3431A);
    }

    public static String w() {
        return a(f3432B, "");
    }

    public static void x() {
        o(f3432B);
    }

    public static long y() {
        return a(f3465w, 0L);
    }

    public static String z() {
        return a(f3434D, "");
    }

    public static long d() {
        return a(f3456n, 0L);
    }

    public static long e() {
        return a(f3457o, 0L);
    }

    public static long f() {
        return a(f3458p, 0L);
    }

    public static String g() {
        return a(f3459q, "");
    }

    public static String h() {
        return a(f3460r, "");
    }

    public static long i() {
        return a(f3451i, 0L);
    }

    public static long j() {
        return a(f3453k, 0L);
    }

    public static void k(long j2) {
        b(f3462t, j2);
    }

    public static void l(long j2) {
        b(f3464v, j2);
    }

    public static void m(long j2) {
        b(f3468z, j2);
    }

    public static void n(long j2) {
        b(f3436F, j2);
    }

    public static void p(long j2) {
        b(f3442L, j2);
    }

    private static long a(String str, long j2) {
        H();
        return f3445c.getLong(str, j2);
    }

    private static void b(String str, long j2) {
        H();
        f3446d.putLong(str, j2).apply();
    }

    public static void c(long j2) {
        b(f3449g, j2);
    }

    public static void d(String str) {
        b(f3454l, str);
    }

    public static void e(String str) {
        b(f3461s, str);
        k(ad.a());
    }

    public static void f(String str) {
        b(f3463u, str);
    }

    public static void g(String str) {
        b(f3431A, str);
    }

    public static void h(String str) {
        b(f3432B, str);
    }

    public static void i(String str) {
        b(f3434D, str);
    }

    public static void j(String str) {
        b(f3435E, str);
    }

    public static boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return b(f3437G + str, true);
    }

    public static void l(String str) {
        b(f3438H, str);
    }

    public static void m(String str) {
        b(f3439I, str);
    }

    public static void n(String str) {
        b(f3440J, str);
    }

    public static String o() {
        return a(f3463u, "");
    }

    public static long c() {
        return a(f3450h, 0L);
    }

    public static void o(long j2) {
        b(f3441K, j2);
    }

    private static void a(String str, float f2) {
        H();
        f3446d.putFloat(str, f2).apply();
    }

    private static boolean b(String str, boolean z2) {
        H();
        return f3445c.getBoolean(str, z2);
    }

    public static void c(String str) {
        b(f3455m, str);
    }

    public static void c(boolean z2) {
        c(f3467y, z2);
    }

    public static long a() {
        return a(f3447e, 0L);
    }

    private static float b(String str, float f2) {
        H();
        return f3445c.getFloat(str, f2);
    }

    public static void a(long j2) {
        b(f3447e, j2);
    }

    public static void a(String str) {
        b(f3459q, str);
    }

    public static long b(long j2) {
        return a(f3448f, j2);
    }

    public static void a(boolean z2) {
        c(f3465w, z2);
    }

    public static long b() {
        return a(f3449g, 0L);
    }

    public static void a(Context context, String str) {
        b(f3433C, str);
    }

    public static void b(String str) {
        b(f3460r, str);
    }

    public static String a(Context context) {
        return a(f3433C, "");
    }

    public static void b(boolean z2) {
        c(f3466x, z2);
    }

    public static void a(String str, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(f3437G + str, z2);
    }
}
