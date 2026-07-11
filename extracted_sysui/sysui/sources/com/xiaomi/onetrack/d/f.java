package com.xiaomi.onetrack.d;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.util.ab;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import com.xiaomi.onetrack.util.y;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JSONObject f3263a = new JSONObject();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3264b = "SecretKeyManager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3265c = "secretKey";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3266d = "sid";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3267e = "key";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Context f3268f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private JSONObject f3269g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String[] f3270h;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final f f3271a = new f();

        private a() {
        }
    }

    public static f a() {
        return a.f3271a;
    }

    private void d() {
        if (q.f3627a) {
            if (TextUtils.isEmpty(this.f3270h[0]) || TextUtils.isEmpty(this.f3270h[1])) {
                q.a(f3264b, "key or sid is invalid!");
            } else {
                q.a(f3264b, "key  and sid is valid! ");
            }
        }
    }

    private JSONObject e() {
        JSONObject jSONObjectF = this.f3269g;
        if (jSONObjectF == null && (jSONObjectF = f()) != null) {
            this.f3269g = jSONObjectF;
        }
        return jSONObjectF == null ? c() : jSONObjectF;
    }

    private JSONObject f() {
        try {
            String strG = ab.g();
            if (TextUtils.isEmpty(strG)) {
                return null;
            }
            return new JSONObject(b.b(this.f3268f, strG));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public synchronized String[] b() {
        try {
            JSONObject jSONObjectE = e();
            this.f3270h[0] = jSONObjectE != null ? jSONObjectE.optString(f3267e) : "";
            this.f3270h[1] = jSONObjectE != null ? jSONObjectE.optString(f3266d) : "";
            d();
        } catch (Throwable th) {
            throw th;
        }
        return this.f3270h;
    }

    public JSONObject c() {
        try {
        } catch (Exception e2) {
            q.b(f3264b, "requestSecretData: " + e2.toString());
        }
        if (r.a(f3264b)) {
            return f3263a;
        }
        byte[] bArrA = com.xiaomi.onetrack.d.a.a();
        String strA = c.a(e.a(bArrA));
        HashMap map = new HashMap();
        map.put(f3265c, strA);
        String strB = com.xiaomi.onetrack.g.b.b(y.a().e(), map, true);
        if (!TextUtils.isEmpty(strB)) {
            JSONObject jSONObject = new JSONObject(strB);
            int iOptInt = jSONObject.optInt(com.xiaomi.onetrack.g.a.f3351d);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
            if (iOptInt == 0 && jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString(f3267e);
                String strOptString2 = jSONObjectOptJSONObject.optString(f3266d);
                if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString2)) {
                    String strA2 = c.a(com.xiaomi.onetrack.d.a.b(c.a(strOptString), bArrA));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(f3267e, strA2);
                    jSONObject2.put(f3266d, strOptString2);
                    this.f3269g = jSONObject2;
                    ab.a(b.a(this.f3268f, jSONObject2.toString()));
                    ab.i(System.currentTimeMillis());
                }
            }
        }
        return this.f3269g;
    }

    private f() {
        this.f3269g = null;
        this.f3270h = new String[2];
        this.f3268f = com.xiaomi.onetrack.f.a.a();
    }
}
