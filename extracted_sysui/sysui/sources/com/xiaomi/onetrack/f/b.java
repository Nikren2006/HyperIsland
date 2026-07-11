package com.xiaomi.onetrack.f;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.BuildConfig;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.api.ai;
import com.xiaomi.onetrack.api.aj;
import com.xiaomi.onetrack.util.DeviceUtil;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.ab;
import com.xiaomi.onetrack.util.ad;
import com.xiaomi.onetrack.util.p;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import com.xiaomi.onetrack.util.w;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3280a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f3281b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3282c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3283d = "Event";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f3284e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f3285f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f3286g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3287h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3288i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private JSONObject f3289j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private long f3290k;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f3291a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3292b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3293c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3294d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3295e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private JSONObject f3296f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private long f3297g;

        public a a(long j2) {
            this.f3291a = this.f3291a;
            return this;
        }

        public a b(String str) {
            this.f3293c = str;
            return this;
        }

        public a c(String str) {
            this.f3294d = str;
            return this;
        }

        public a a(String str) {
            this.f3292b = str;
            return this;
        }

        public a b(long j2) {
            this.f3297g = j2;
            return this;
        }

        public a a(int i2) {
            this.f3295e = i2;
            return this;
        }

        public a a(JSONObject jSONObject) {
            this.f3296f = jSONObject;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    /* JADX INFO: renamed from: com.xiaomi.onetrack.f.b$b, reason: collision with other inner class name */
    public static class C0069b {

        /* JADX INFO: renamed from: A, reason: collision with root package name */
        public static String f3298A = "sdk_mode";

        /* JADX INFO: renamed from: B, reason: collision with root package name */
        public static String f3299B = "ot_first_day";

        /* JADX INFO: renamed from: C, reason: collision with root package name */
        public static String f3300C = "ot_test_env";

        /* JADX INFO: renamed from: D, reason: collision with root package name */
        public static String f3301D = "ot_privacy_policy";

        /* JADX INFO: renamed from: E, reason: collision with root package name */
        public static String f3302E = "market_name";

        /* JADX INFO: renamed from: F, reason: collision with root package name */
        public static String f3303F = "ot_ad";

        /* JADX INFO: renamed from: G, reason: collision with root package name */
        public static String f3304G = "ot_basic_mode";

        /* JADX INFO: renamed from: H, reason: collision with root package name */
        public static String f3305H = "ot_ad_monitor";

        /* JADX INFO: renamed from: I, reason: collision with root package name */
        public static String f3306I = "ot_hybrid_type";

        /* JADX INFO: renamed from: J, reason: collision with root package name */
        public static String f3307J = "ot_mi_os";

        /* JADX INFO: renamed from: K, reason: collision with root package name */
        public static String f3308K = "ot_device_type";

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static String f3309a = "event";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static String f3310b = "imei";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static String f3311c = "oaid";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static String f3312d = "sn";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static String f3313e = "gaid";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static String f3314f = "android_id";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static String f3315g = "instance_id";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static String f3316h = "mfrs";

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static String f3317i = "model";

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static String f3318j = "platform";

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static String f3319k = "miui";

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static String f3320l = "build";

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static String f3321m = "os_ver";

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static String f3322n = "app_id";

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static String f3323o = "app_ver";

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static String f3324p = "pkg";

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static String f3325q = "channel";

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static String f3326r = "e_ts";

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static String f3327s = "tz";

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static String f3328t = "net";

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static String f3329u = "region";

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static String f3330v = "plugin_id";

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static String f3331w = "sdk_ver";

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static String f3332x = "uid";

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static String f3333y = "uid_type";

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static String f3334z = "sid";
    }

    public void a(long j2) {
        this.f3284e = j2;
    }

    public long b() {
        return this.f3284e;
    }

    public String c() {
        return this.f3285f;
    }

    public String d() {
        return this.f3286g;
    }

    public String e() {
        return this.f3287h;
    }

    public int f() {
        return this.f3288i;
    }

    public JSONObject g() {
        return this.f3289j;
    }

    public long h() {
        return this.f3290k;
    }

    public boolean i() {
        try {
            JSONObject jSONObject = this.f3289j;
            if (jSONObject == null || !jSONObject.has(ai.f2848b) || !this.f3289j.has(ai.f2847a) || TextUtils.isEmpty(this.f3285f)) {
                return false;
            }
            return !TextUtils.isEmpty(this.f3286g);
        } catch (Exception e2) {
            q.b(f3283d, "check event isValid error, ", e2);
            return false;
        }
    }

    public b() {
    }

    public void a(String str) {
        this.f3285f = str;
    }

    public void b(String str) {
        this.f3286g = str;
    }

    public void c(String str) {
        this.f3287h = str;
    }

    private b(a aVar) {
        this.f3284e = aVar.f3291a;
        this.f3285f = aVar.f3292b;
        this.f3286g = aVar.f3293c;
        this.f3287h = aVar.f3294d;
        this.f3288i = aVar.f3295e;
        this.f3289j = aVar.f3296f;
        this.f3290k = aVar.f3297g;
    }

    public void a(int i2) {
        this.f3288i = i2;
    }

    public void b(JSONObject jSONObject) {
        this.f3289j = jSONObject;
    }

    public static JSONObject a(String str, Configuration configuration, OneTrack.IEventHook iEventHook, w wVar, boolean z2, boolean z3) {
        return a(str, configuration, iEventHook, "", wVar, z2, z3);
    }

    public void b(long j2) {
        this.f3290k = j2;
    }

    public static JSONObject a(String str, Configuration configuration, OneTrack.IEventHook iEventHook, String str2, w wVar, boolean z2, boolean z3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Context contextB = com.xiaomi.onetrack.f.a.b();
        jSONObject.put(C0069b.f3309a, str);
        boolean zA = a(z2);
        if (!zA) {
            if (r.b() ? r.k() : configuration.isInternational()) {
                if (iEventHook != null && iEventHook.isRecommendEvent(str)) {
                    String strE = DeviceUtil.e(contextB);
                    if (!TextUtils.isEmpty(strE)) {
                        jSONObject.put(C0069b.f3313e, strE);
                    }
                }
            } else {
                jSONObject.put(C0069b.f3310b, DeviceUtil.b(contextB));
                String strA = com.xiaomi.onetrack.util.oaid.a.a().a(contextB);
                jSONObject.put(C0069b.f3311c, strA);
                if (!aa.b(strA)) {
                    jSONObject.put(C0069b.f3314f, DeviceUtil.k(contextB));
                }
            }
            jSONObject.put(C0069b.f3315g, p.a().b());
            a(jSONObject, configuration, str2);
            a(jSONObject, contextB);
            jSONObject.put(C0069b.f3334z, r.h());
        }
        jSONObject.put(C0069b.f3316h, DeviceUtil.d());
        jSONObject.put(C0069b.f3317i, DeviceUtil.b());
        jSONObject.put(C0069b.f3318j, "Android");
        jSONObject.put(C0069b.f3308K, DeviceUtil.h());
        jSONObject.put(C0069b.f3319k, r.e());
        jSONObject.put(C0069b.f3307J, r.f());
        jSONObject.put(C0069b.f3320l, r.d());
        jSONObject.put(C0069b.f3321m, r.g());
        jSONObject.put(C0069b.f3323o, com.xiaomi.onetrack.f.a.c());
        jSONObject.put(C0069b.f3326r, System.currentTimeMillis());
        jSONObject.put(C0069b.f3327s, r.c());
        jSONObject.put(C0069b.f3328t, com.xiaomi.onetrack.g.c.a(contextB).toString());
        String strL = r.l();
        com.xiaomi.onetrack.b.a.a().d(strL);
        jSONObject.put(C0069b.f3329u, strL);
        jSONObject.put(C0069b.f3331w, BuildConfig.SDK_VERSION);
        if (z3) {
            jSONObject.put(C0069b.f3322n, configuration.getAdEventAppId());
        } else {
            jSONObject.put(C0069b.f3322n, configuration.getAppId());
        }
        jSONObject.put(C0069b.f3303F, z3);
        jSONObject.put(C0069b.f3324p, com.xiaomi.onetrack.f.a.e());
        jSONObject.put(C0069b.f3325q, !TextUtils.isEmpty(configuration.getChannel()) ? configuration.getChannel() : "default");
        jSONObject.put(C0069b.f3298A, (configuration.getMode() != null ? configuration.getMode() : OneTrack.Mode.APP).getType());
        jSONObject.put(C0069b.f3299B, ad.d(ab.B()));
        if (q.f3629c) {
            jSONObject.put(C0069b.f3300C, true);
        }
        jSONObject.put(C0069b.f3301D, wVar.a());
        jSONObject.put(C0069b.f3302E, DeviceUtil.c());
        jSONObject.put(C0069b.f3304G, zA);
        return jSONObject;
    }

    public static JSONObject a(aj ajVar, Configuration configuration, OneTrack.IEventHook iEventHook, w wVar, boolean z2, boolean z3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Context contextB = com.xiaomi.onetrack.f.a.b();
        jSONObject.put(C0069b.f3309a, ajVar.a());
        boolean zA = a(z2);
        if (!zA) {
            if (r.b() ? r.k() : configuration.isInternational()) {
                if (iEventHook != null && iEventHook.isRecommendEvent(ajVar.a())) {
                    String strE = DeviceUtil.e(contextB);
                    if (!TextUtils.isEmpty(strE)) {
                        jSONObject.put(C0069b.f3313e, strE);
                    }
                }
            } else {
                jSONObject.put(C0069b.f3310b, DeviceUtil.b(contextB));
                String strA = com.xiaomi.onetrack.util.oaid.a.a().a(contextB);
                jSONObject.put(C0069b.f3311c, strA);
                if (!aa.b(strA)) {
                    jSONObject.put(C0069b.f3314f, DeviceUtil.k(contextB));
                }
            }
            jSONObject.put(C0069b.f3315g, p.a().b());
            jSONObject.put(C0069b.f3330v, configuration.getPluginId());
            if (!TextUtils.isEmpty(ajVar.e()) && !TextUtils.isEmpty(ajVar.f())) {
                jSONObject.put(C0069b.f3332x, ajVar.e());
                jSONObject.put(C0069b.f3333y, ajVar.f());
            }
            jSONObject.put(C0069b.f3334z, r.h());
        }
        jSONObject.put(C0069b.f3303F, z3);
        jSONObject.put(C0069b.f3316h, DeviceUtil.d());
        jSONObject.put(C0069b.f3317i, DeviceUtil.b());
        jSONObject.put(C0069b.f3318j, "Android");
        jSONObject.put(C0069b.f3308K, DeviceUtil.h());
        jSONObject.put(C0069b.f3319k, r.e());
        jSONObject.put(C0069b.f3307J, r.f());
        jSONObject.put(C0069b.f3320l, r.d());
        jSONObject.put(C0069b.f3321m, r.g());
        jSONObject.put(C0069b.f3323o, com.xiaomi.onetrack.f.a.c());
        jSONObject.put(C0069b.f3326r, ajVar.b());
        jSONObject.put(C0069b.f3327s, r.c());
        jSONObject.put(C0069b.f3328t, com.xiaomi.onetrack.g.c.a(contextB).toString());
        jSONObject.put(C0069b.f3329u, r.l());
        jSONObject.put(C0069b.f3331w, BuildConfig.SDK_VERSION);
        jSONObject.put(C0069b.f3322n, ajVar.c());
        jSONObject.put(C0069b.f3324p, com.xiaomi.onetrack.f.a.e());
        jSONObject.put(C0069b.f3325q, !TextUtils.isEmpty(ajVar.d()) ? ajVar.d() : "default");
        jSONObject.put(C0069b.f3298A, (configuration.getMode() != null ? configuration.getMode() : OneTrack.Mode.APP).getType());
        jSONObject.put(C0069b.f3299B, ad.d(ab.B()));
        if (q.f3629c) {
            jSONObject.put(C0069b.f3300C, true);
        }
        jSONObject.put(C0069b.f3301D, wVar.a());
        jSONObject.put(C0069b.f3302E, DeviceUtil.c());
        jSONObject.put(C0069b.f3304G, zA);
        jSONObject.put(C0069b.f3306I, "JS");
        return jSONObject;
    }

    private static void a(JSONObject jSONObject, Context context) throws JSONException {
        String strU = ab.u();
        String strW = ab.w();
        if (TextUtils.isEmpty(strU) || TextUtils.isEmpty(strW)) {
            return;
        }
        jSONObject.put(C0069b.f3332x, strU);
        jSONObject.put(C0069b.f3333y, strW);
    }

    private static void a(JSONObject jSONObject, Configuration configuration, String str) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            jSONObject.put(C0069b.f3330v, str);
        } else {
            jSONObject.put(C0069b.f3330v, configuration.getPluginId());
        }
    }

    public static boolean a(boolean z2) {
        return OneTrack.getGlobalBasicModeEnable() == 0 ? z2 : OneTrack.getGlobalBasicModeEnable() != 1 && OneTrack.getGlobalBasicModeEnable() == 2;
    }
}
