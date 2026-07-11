package com.xiaomi.onetrack.api;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2921a = "name";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2922b = "gender";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2923c = "birthday";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f2924d = "phone";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f2925e = "job";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f2926f = "hobby";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f2927g = "region";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f2928h = "province";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f2929i = "city";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private String f2930j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private String f2931k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private String f2932l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private String f2933m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private String f2934n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private String f2935o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private String f2936p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private String f2937q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private String f2938r;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2939a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2940b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2941c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f2942d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f2943e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f2944f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f2945g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f2946h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private String f2947i;

        public a a(String str) {
            this.f2939a = str;
            return this;
        }

        public a b(String str) {
            this.f2940b = str;
            return this;
        }

        public a c(String str) {
            this.f2941c = str;
            return this;
        }

        public a d(String str) {
            this.f2942d = str;
            return this;
        }

        public a e(String str) {
            this.f2943e = str;
            return this;
        }

        public a f(String str) {
            this.f2944f = str;
            return this;
        }

        public a g(String str) {
            this.f2945g = str;
            return this;
        }

        public a h(String str) {
            this.f2946h = str;
            return this;
        }

        public a i(String str) {
            this.f2947i = str;
            return this;
        }

        public au a() {
            au auVar = new au();
            auVar.f2935o = this.f2944f;
            auVar.f2934n = this.f2943e;
            auVar.f2938r = this.f2947i;
            auVar.f2933m = this.f2942d;
            auVar.f2937q = this.f2946h;
            auVar.f2932l = this.f2941c;
            auVar.f2930j = this.f2939a;
            auVar.f2936p = this.f2945g;
            auVar.f2931k = this.f2940b;
            return auVar;
        }
    }

    public JSONObject j() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f2921a, this.f2930j);
            jSONObject.put(f2922b, this.f2931k);
            jSONObject.put(f2923c, this.f2932l);
            jSONObject.put(f2924d, this.f2933m);
            jSONObject.put(f2925e, this.f2934n);
            jSONObject.put(f2926f, this.f2935o);
            jSONObject.put("region", this.f2936p);
            jSONObject.put(f2928h, this.f2937q);
            jSONObject.put(f2929i, this.f2938r);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return j().toString();
    }

    private au() {
    }

    public String a() {
        return this.f2930j;
    }

    public String b() {
        return this.f2931k;
    }

    public String c() {
        return this.f2932l;
    }

    public String d() {
        return this.f2933m;
    }

    public String e() {
        return this.f2934n;
    }

    public String f() {
        return this.f2935o;
    }

    public String g() {
        return this.f2936p;
    }

    public String h() {
        return this.f2937q;
    }

    public String i() {
        return this.f2938r;
    }
}
