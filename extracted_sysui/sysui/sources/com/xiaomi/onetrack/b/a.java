package com.xiaomi.onetrack.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.miui.circulate.device.api.Constant;
import com.xiaomi.onetrack.BuildConfig;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.api.an;
import com.xiaomi.onetrack.util.DeviceUtil;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.ab;
import com.xiaomi.onetrack.util.q;
import com.xiaomi.onetrack.util.r;
import com.xiaomi.onetrack.util.y;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3021a = "disable_log";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3022b = "event";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3023c = "events";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3024d = "level";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f3025e = "sample";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f3026f = "needIds";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f3027g = "bannedParams";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f3028h = "version";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final String f3029i = "AppConfigUpdater";

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final long f3030j = 172800000;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String f3031k = "hash";

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f3032l = "appId";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final String f3033m = "apps";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final String f3034n = "type";

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final String f3035o = "status";

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final String f3036p = "deleted";

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final String f3037q = "Android";

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final int f3039s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final int f3040t = 1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final int f3041u = 2;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private static final int f3042v = 100;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private static final long f3044x = 1800000;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final int f3046z = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static AtomicBoolean f3038r = new AtomicBoolean(false);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static ConcurrentHashMap<String, Long> f3043w = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private static b f3045y = new b(Looper.getMainLooper(), null);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    private static ConcurrentHashMap<String, Boolean> f3018A = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    private static ConcurrentHashMap<String, Boolean> f3019B = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    private static String f3020C = "";

    /* JADX INFO: renamed from: com.xiaomi.onetrack.b.a$a, reason: collision with other inner class name */
    public static class C0068a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a f3047a = new a(null);

        private C0068a() {
        }
    }

    public static class b extends Handler {
        public /* synthetic */ b(Looper looper, com.xiaomi.onetrack.b.b bVar) {
            this(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            q.a(a.f3029i, "ScheduleCloudHandler.handleMessage, msg.what=" + message.what);
            if (message.what == 0) {
                Object obj = message.obj;
                if (obj == null) {
                    q.a(a.f3029i, "ScheduleCloudHandler.handleMessage, msg.obj is null");
                    return;
                }
                try {
                    String str = (String) obj;
                    q.a(a.f3029i, "ScheduleCloudHandler.handleMessage, appId: " + str);
                    com.xiaomi.onetrack.util.i.a(new d(this, str));
                } catch (Exception e2) {
                    q.b(a.f3029i, "handleMessage error: " + e2.getMessage());
                }
            }
        }

        private b(Looper looper) {
            super(looper);
        }
    }

    public /* synthetic */ a(com.xiaomi.onetrack.b.b bVar) {
        this();
    }

    private static void d(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            f3043w.put(list.get(i2), Long.valueOf(System.currentTimeMillis() + f3044x));
        }
    }

    private static boolean f(String str) {
        ConcurrentHashMap<String, Boolean> concurrentHashMap = f3019B;
        return concurrentHashMap != null && concurrentHashMap.containsKey(str) && f3019B.get(str).booleanValue();
    }

    private boolean g(String str) {
        if (!com.xiaomi.onetrack.g.c.a()) {
            q.a(f3029i, "net is not connected!");
            return false;
        }
        l lVarF = h.a().f(str);
        if (lVarF == null) {
            return true;
        }
        long j2 = lVarF.f3085c;
        return j2 < System.currentTimeMillis() || j2 - System.currentTimeMillis() > f3030j || f(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean h(String str) {
        Long l2 = f3043w.get(str);
        return l2 == null || l2.longValue() - System.currentTimeMillis() < 0 || l2.longValue() - System.currentTimeMillis() > f3044x;
    }

    private a() {
        String strC = ab.C();
        if (!TextUtils.isEmpty(strC)) {
            f3020C = strC;
            return;
        }
        String strL = r.l();
        if (TextUtils.isEmpty(strL)) {
            return;
        }
        f3020C = strL;
        ab.l(strL);
    }

    public static a a() {
        return C0068a.f3047a;
    }

    public void b(String str) {
        com.xiaomi.onetrack.util.i.a(new com.xiaomi.onetrack.b.b(this, str));
    }

    public void c(String str) {
        if (!g(str)) {
            q.a(f3029i, "AppConfigUpdater Does not meet prerequisites for request");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        b(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<String> list) {
        q.a(f3029i, "pullCloudData start, appIds: " + list.toString());
        if (!r.a(f3029i) && f3038r.compareAndSet(false, true)) {
            HashMap map = new HashMap();
            try {
                try {
                    String strL = r.l();
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
                    map.put(m.f3095h, strL);
                    map.put(m.f3096i, c(list));
                    map.put(m.f3097j, com.xiaomi.onetrack.f.a.e());
                    map.put(m.f3099l, f3037q);
                    map.put(m.f3101n, "1");
                    String strC = y.a().c();
                    q.a(f3029i, "pullData:" + strC);
                    String strB = com.xiaomi.onetrack.g.b.b(strC, map, true);
                    q.a(f3029i, "response:" + strB);
                    a(strB, list);
                } catch (Exception e2) {
                    q.b(f3029i, "pullCloudData error: " + e2.getMessage());
                }
                f3038r.set(false);
            } catch (Throwable th) {
                f3038r.set(false);
                throw th;
            }
        }
    }

    public void a(String str) {
        f3019B.put(str, Boolean.FALSE);
    }

    public static void a(String str, List<String> list) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(com.xiaomi.onetrack.g.a.f3351d) == 0) {
                d(list);
                a(jSONObject.optJSONObject("data").optJSONArray("apps"), list);
            }
        } catch (Exception e2) {
            q.a(f3029i, "saveAppCloudData: " + e2.toString());
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(f3020C) || TextUtils.isEmpty(str) || TextUtils.equals(f3020C, str)) {
            return;
        }
        an.a(f3020C, str);
        Iterator<Map.Entry<String, Boolean>> it = f3019B.entrySet().iterator();
        while (it.hasNext()) {
            it.next().setValue(Boolean.TRUE);
        }
        f3020C = str;
        ab.l(str);
    }

    private static String c(List<String> list) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            for (int i2 = 0; i2 < list.size(); i2++) {
                String str = list.get(i2);
                jSONObject.put("appId", str);
                if (f(str)) {
                    jSONObject.put("hash", "");
                } else {
                    jSONObject.put("hash", h.a().d(str));
                }
                jSONArray.put(jSONObject);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONArray.toString();
    }

    private static void a(JSONArray jSONArray, List<String> list) throws JSONException {
        q.a(f3029i, "updateDataToDb start");
        long jCurrentTimeMillis = System.currentTimeMillis() + Constant.CACHE_EXPIRED_DURATION + ((long) new Random().nextInt(86400000));
        if (jSONArray != null && jSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                String strOptString = jSONObjectOptJSONObject == null ? "" : jSONObjectOptJSONObject.optString("appId");
                q.a(f3029i, "appId: " + strOptString);
                if (!TextUtils.isEmpty(strOptString)) {
                    arrayList.add(strOptString);
                    a(strOptString, jSONObjectOptJSONObject, jCurrentTimeMillis);
                }
            }
            a(list, jCurrentTimeMillis, arrayList);
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                f3019B.put(it.next(), Boolean.FALSE);
            }
            return;
        }
        a(list, jCurrentTimeMillis);
    }

    private static void a(String str, JSONObject jSONObject, long j2) throws JSONException {
        int iOptInt = jSONObject == null ? 0 : jSONObject.optInt("version");
        int iE = h.a().e(str);
        q.a(f3029i, "local version: " + iE + ", server version: " + iOptInt);
        if (iE > 0 && iOptInt < iE && !f(str)) {
            a(jSONObject, j2);
            return;
        }
        int iOptInt2 = jSONObject != null ? jSONObject.optInt("type") : -1;
        q.a(f3029i, "type: " + iOptInt2);
        if (iOptInt2 == 0 || iOptInt2 == 1 || f(str)) {
            a(jSONObject, j2, iOptInt);
        } else if (iOptInt2 == 2) {
            b(jSONObject, j2);
        } else {
            q.a(f3029i, "handleData do nothing!");
        }
    }

    private static void b(JSONObject jSONObject, long j2) {
        ArrayList<l> arrayList = new ArrayList<>();
        if (jSONObject == null || !jSONObject.has("events")) {
            q.a(f3029i, "handleIncrementalUpdate config is not change!");
        } else {
            l lVar = new l();
            lVar.f3086d = jSONObject.optString("hash");
            String strOptString = jSONObject.optString("appId");
            lVar.f3083a = strOptString;
            lVar.f3084b = b(jSONObject);
            lVar.f3085c = j2;
            lVar.f3087e = a(strOptString, jSONObject);
            arrayList.add(lVar);
        }
        if (arrayList.isEmpty()) {
            q.a(f3029i, "handleIncrementalUpdate no configuration can be updated!");
        } else {
            h.a().a(arrayList);
        }
    }

    private static void a(JSONObject jSONObject, long j2) {
        ArrayList<l> arrayList = new ArrayList<>();
        if (jSONObject != null) {
            l lVar = new l();
            lVar.f3083a = jSONObject.optString("appId");
            lVar.f3085c = j2;
            arrayList.add(lVar);
        }
        if (!arrayList.isEmpty()) {
            h.a().a(arrayList);
        } else {
            q.a(f3029i, "updateMinVersionData no timestamp can be updated!");
        }
    }

    private static void a(JSONObject jSONObject, long j2, int i2) throws JSONException {
        l lVarF;
        JSONObject jSONObject2;
        ArrayList<l> arrayList = new ArrayList<>();
        if (jSONObject != null) {
            l lVar = new l();
            lVar.f3086d = jSONObject.optString("hash");
            lVar.f3083a = jSONObject.optString("appId");
            lVar.f3084b = b(jSONObject);
            lVar.f3085c = j2;
            if (!jSONObject.has("events") && (lVarF = h.a().f(lVar.f3083a)) != null && (jSONObject2 = lVarF.f3087e) != null && jSONObject2.optJSONArray("events") != null) {
                jSONObject.put("events", lVarF.f3087e.optJSONArray("events"));
            }
            lVar.f3087e = jSONObject;
            arrayList.add(lVar);
        }
        if (!arrayList.isEmpty()) {
            h.a().a(arrayList);
        } else {
            q.a(f3029i, "handleFullOrNoNewData no configuration can be updated!");
        }
    }

    private static int b(JSONObject jSONObject) {
        try {
            int iOptInt = jSONObject.optInt(f3025e, 100);
            if (iOptInt < 0 || iOptInt > 100) {
                return 100;
            }
            return iOptInt;
        } catch (Exception e2) {
            q.a(f3029i, "getCommonSample Exception:" + e2.getMessage());
            return 100;
        }
    }

    private static JSONObject a(String str, JSONObject jSONObject) {
        try {
            l lVarF = h.a().f(str);
            jSONObject.put("events", a(lVarF != null ? lVarF.f3087e.optJSONArray("events") : null, jSONObject.optJSONArray("events")));
            return jSONObject;
        } catch (Exception e2) {
            q.b(f3029i, "mergeConfig: " + e2.toString());
            return null;
        }
    }

    private static JSONArray a(JSONArray jSONArray, JSONArray jSONArray2) {
        int i2 = 0;
        while (jSONArray2 != null) {
            try {
                if (i2 >= jSONArray2.length()) {
                    break;
                }
                JSONObject jSONObjectOptJSONObject = jSONArray2.optJSONObject(i2);
                String strOptString = jSONObjectOptJSONObject.optString("event");
                int i3 = 0;
                while (true) {
                    if (jSONArray == null || i3 >= jSONArray.length()) {
                        break;
                    }
                    if (TextUtils.equals(strOptString, jSONArray.optJSONObject(i3).optString("event"))) {
                        jSONArray.remove(i3);
                        break;
                    }
                    i3++;
                }
                if (!jSONObjectOptJSONObject.has("status") || (jSONObjectOptJSONObject.has("status") && !TextUtils.equals(jSONObjectOptJSONObject.optString("status"), f3036p))) {
                    if (jSONArray == null) {
                        jSONArray = new JSONArray();
                    }
                    jSONArray.put(jSONObjectOptJSONObject);
                }
                i2++;
            } catch (Exception e2) {
                q.b(f3029i, "mergeEventsElement error:" + e2.toString());
            }
        }
        return jSONArray;
    }

    private static void a(List<String> list, long j2, List<String> list2) {
        try {
            if (list.size() != list2.size()) {
                list.removeAll(list2);
                a(list, j2);
            }
        } catch (Exception e2) {
            q.b(f3029i, "handleInvalidAppIds error:" + e2.toString());
        }
    }

    private static void a(List<String> list, long j2) {
        try {
            ArrayList<l> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < list.size(); i2++) {
                l lVar = new l();
                lVar.f3083a = list.get(i2);
                lVar.f3084b = 100L;
                lVar.f3085c = j2;
                arrayList.add(lVar);
            }
            h.a().a(arrayList);
        } catch (Exception e2) {
            q.b(f3029i, "handleError" + e2.toString());
        }
    }

    public void a(JSONObject jSONObject) {
        com.xiaomi.onetrack.util.i.a(new c(this, jSONObject));
    }
}
