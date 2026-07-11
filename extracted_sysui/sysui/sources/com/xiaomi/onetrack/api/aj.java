package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class aj {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2850a = "H5DataModel";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2851b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private long f2852c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f2853d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2854e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2855f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f2856g;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static String f2857a = "event";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static String f2858b = "session_id";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static String f2859c = "instance_id";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static String f2860d = "platform";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static String f2861e = "e_ts";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static String f2862f = "tz";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static String f2863g = "sdk_ver";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static String f2864h = "app_id";

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static String f2865i = "channel";

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static String f2866j = "uid";

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static String f2867k = "uid_type";
    }

    public aj(JSONObject jSONObject) {
        this.f2851b = a(jSONObject, a.f2857a);
        try {
            this.f2852c = Long.parseLong(a(jSONObject, a.f2861e));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2850a, "e_ts parse error: " + e2.getMessage());
        }
        this.f2853d = a(jSONObject, a.f2864h);
        this.f2854e = a(jSONObject, a.f2865i);
        this.f2855f = a(jSONObject, a.f2866j);
        this.f2856g = a(jSONObject, a.f2867k);
    }

    private String a(JSONObject jSONObject, String str) {
        Object objOpt = jSONObject.opt(str);
        return objOpt == null ? "" : String.valueOf(objOpt);
    }

    public long b() {
        return this.f2852c;
    }

    public String c() {
        return this.f2853d;
    }

    public String d() {
        return this.f2854e;
    }

    public String e() {
        return this.f2855f;
    }

    public String f() {
        return this.f2856g;
    }

    public String toString() {
        return "H5DataModel{eventName='" + this.f2851b + "', e_ts=" + this.f2852c + ", appId='" + this.f2853d + "', channel='" + this.f2854e + "', uid='" + this.f2855f + "', uidType='" + this.f2856g + "'}";
    }

    public String a() {
        return this.f2851b;
    }
}
