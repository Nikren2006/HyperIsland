package com.xiaomi.onetrack.b;

import android.text.TextUtils;
import com.miui.circulate.device.api.Constant;
import com.xiaomi.onetrack.BuildConfig;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.util.DeviceUtil;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.ab;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import com.xiaomi.onetrack.util.y;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3054a = "l";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3055b = "CommonConfigUpdater";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3056c = "t";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3057d = "levels";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3058e = "Android";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static ConcurrentHashMap<Integer, Integer> f3059f = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final long f3060g = 172800000;

    public static void a() {
        com.xiaomi.onetrack.util.i.a(new f());
    }

    public static void b() {
        if (e()) {
            f();
        } else {
            q.a(f3055b, "CommonConfigUpdater Does not meet prerequisites for request");
        }
    }

    public static Map<Integer, Integer> c() {
        try {
        } catch (Exception e2) {
            q.a(f3055b, "getLevelIntervalConfig: " + e2.toString());
        }
        if (!f3059f.isEmpty()) {
            return f3059f;
        }
        String strL = ab.l();
        if (!TextUtils.isEmpty(strL)) {
            JSONArray jSONArrayOptJSONArray = new JSONObject(strL).optJSONArray(f3057d);
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                int iOptInt = jSONObject.optInt(f3054a);
                int iOptInt2 = jSONObject.optInt(f3056c);
                if (iOptInt > 0 && iOptInt2 > 0) {
                    f3059f.put(Integer.valueOf(iOptInt), Integer.valueOf(iOptInt2));
                }
            }
        }
        return f3059f.isEmpty() ? g() : f3059f;
    }

    private static boolean e() {
        if (!com.xiaomi.onetrack.g.c.a()) {
            q.b(f3055b, "net is not connected!");
            return false;
        }
        if (TextUtils.isEmpty(ab.l())) {
            return true;
        }
        long j2 = ab.j();
        return j2 < System.currentTimeMillis() || j2 - System.currentTimeMillis() > f3060g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        if (r.a(f3055b)) {
            return;
        }
        HashMap map = new HashMap();
        try {
            if (OneTrack.getGlobalBasicModeEnable() != 2) {
                String strA = com.xiaomi.onetrack.util.oaid.a.a().a(com.xiaomi.onetrack.f.a.b());
                if (aa.b(strA)) {
                    strA = com.xiaomi.onetrack.d.d.c(strA);
                }
                map.put(m.f3088a, strA);
            }
            map.put(m.f3089b, r.e());
            map.put(m.f3090c, r.d());
            map.put(m.f3091d, r.k() ? "1" : "0");
            map.put(m.f3092e, BuildConfig.SDK_VERSION);
            map.put(m.f3100m, com.xiaomi.onetrack.f.a.c());
            map.put(m.f3093f, r.g());
            map.put(m.f3094g, DeviceUtil.b());
            map.put(m.f3095h, r.l());
            map.put(m.f3099l, f3058e);
            String strD = y.a().d();
            String strB = com.xiaomi.onetrack.g.b.b(strD, map, true);
            q.a(f3055b, "url:" + strD + " response:" + strB);
            a(strB);
        } catch (IOException e2) {
            q.a(f3055b, "requestCloudData: " + e2.toString());
        }
    }

    private static HashMap<Integer, Integer> g() {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1000);
        map.put(2, 15000);
        map.put(3, 900000);
        return map;
    }

    private static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(com.xiaomi.onetrack.g.a.f3351d) == 0) {
                String strOptString = jSONObject.optString(com.xiaomi.onetrack.g.a.f3352e);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                if (jSONObjectOptJSONObject != null) {
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("regionUrl");
                    if (jSONObjectOptJSONObject2 != null) {
                        y.a().a(jSONObjectOptJSONObject2);
                    }
                    ab.d(jSONObjectOptJSONObject.toString());
                    ab.c(strOptString);
                }
                ab.j(System.currentTimeMillis() + Constant.CACHE_EXPIRED_DURATION + ((long) new Random().nextInt(86400000)));
            }
        } catch (JSONException e2) {
            q.a(f3055b, "saveCommonCloudData: " + e2.toString());
        }
    }
}
