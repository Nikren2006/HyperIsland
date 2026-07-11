package com.xiaomi.onetrack.api;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.f.b;
import com.xiaomi.onetrack.util.DeviceUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ao implements ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2887a = "OneTrackLocalImp";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2888b = 102400;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f2889c = 512000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f2890d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Configuration f2891e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.xiaomi.onetrack.util.w f2892f;

    public ao(Context context, Configuration configuration, com.xiaomi.onetrack.util.w wVar) {
        com.xiaomi.onetrack.f.g.a(context);
        this.f2891e = configuration;
        this.f2892f = wVar;
    }

    private boolean b(String str, String str2) {
        List<String> listA;
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String[] strArrSplit = str.split(com.xiaomi.onetrack.util.aa.f3428a);
            if (strArrSplit != null && strArrSplit.length >= 5 && (listA = com.xiaomi.onetrack.util.aa.a(str2, com.xiaomi.onetrack.util.aa.f3429b)) != null) {
                if (listA.contains(strArrSplit[4])) {
                    return true;
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2887a, "isMatchId error：" + e2.toString());
        }
        return false;
    }

    private boolean c(String str, String str2) {
        try {
            return com.xiaomi.onetrack.b.h.a().a(str, str2, com.xiaomi.onetrack.b.a.f3021a, false);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2887a, "isDisableTrackForEvent error: " + e2.toString());
            return false;
        }
    }

    private boolean d(String str, String str2) {
        String strA;
        long jB = com.xiaomi.onetrack.b.h.a().b(str, str2);
        Context contextB = com.xiaomi.onetrack.f.a.b();
        if (com.xiaomi.onetrack.util.r.j()) {
            strA = DeviceUtil.e(contextB);
            com.xiaomi.onetrack.util.q.a(f2887a, "gaid: " + strA);
        } else {
            strA = com.xiaomi.onetrack.util.oaid.a.a().a(contextB);
            com.xiaomi.onetrack.util.q.a(f2887a, "oaid: " + strA);
        }
        long jAbs = Math.abs(strA.hashCode()) % 100;
        boolean z2 = jB > jAbs;
        com.xiaomi.onetrack.util.q.a(f2887a, "shouldUploadBySampling " + str2 + ",  shouldUpload=" + z2 + ", sample=" + jB + ", val=" + jAbs);
        return !z2;
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(String str, String str2) {
        JSONObject jSONObject;
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2 = null;
        try {
            jSONObject = new JSONObject(str2);
            try {
                jSONObjectOptJSONObject = jSONObject.optJSONObject(ai.f2848b);
                try {
                    jSONObjectOptJSONObject2 = jSONObject.optJSONObject(ai.f2847a);
                } catch (JSONException e2) {
                    e = e2;
                    com.xiaomi.onetrack.util.q.a(f2887a, " data JSONException e:" + e.getMessage());
                }
            } catch (JSONException e3) {
                e = e3;
                jSONObjectOptJSONObject = null;
            }
        } catch (JSONException e4) {
            e = e4;
            jSONObject = null;
            jSONObjectOptJSONObject = null;
        }
        JSONObject jSONObject2 = jSONObjectOptJSONObject2;
        JSONObject jSONObject3 = jSONObject;
        JSONObject jSONObject4 = jSONObjectOptJSONObject;
        boolean zA = a(jSONObject4);
        com.xiaomi.onetrack.util.w wVar = this.f2892f;
        if (wVar != null && !wVar.a(str) && !zA) {
            com.xiaomi.onetrack.util.q.a(f2887a, "The privacy policy is not permitted, and the event is not basic or recommend event or custom dau event, skip it.");
            return;
        }
        if (a(str, str2, zA)) {
            if (!com.xiaomi.onetrack.c.j.b()) {
                com.xiaomi.onetrack.c.j.a(str, str2);
                return;
            }
            com.xiaomi.onetrack.c.j.a(this);
            if (com.xiaomi.onetrack.util.q.f3627a && !str.equalsIgnoreCase("onetrack_bug_report")) {
                com.xiaomi.onetrack.util.q.a(f2887a, "track data:" + str2);
            }
            if (zA) {
                com.xiaomi.onetrack.b.a.a().b(this.f2891e.getAdEventAppId());
            }
            com.xiaomi.onetrack.b.a.a().b(this.f2891e.getAppId());
            a(str, jSONObject3, jSONObject4, jSONObject2, zA);
        }
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(int i2) {
        com.xiaomi.onetrack.util.i.a(new ap(this, i2));
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(boolean z2) {
        if (an.f2885k) {
            com.xiaomi.onetrack.c.j.a(this);
        }
    }

    public boolean a(String str, String str2, boolean z2) {
        if (!OneTrack.isDisable() && !OneTrack.isUseSystemNetTrafficOnly()) {
            boolean z3 = str != null && str.equals("onetrack_bug_report");
            if (!z2 && !z3) {
                if (str2 != null && str2.length() * 2 > f2888b) {
                    com.xiaomi.onetrack.util.q.a(f2887a, "Event size exceed limitation!");
                    return false;
                }
            } else if (str2 != null && str2.length() > f2889c) {
                com.xiaomi.onetrack.util.q.a(f2887a, "ad Event size exceed limitation!");
                return false;
            }
            return true;
        }
        com.xiaomi.onetrack.util.q.a(f2887a, "Tracking data is disabled or onetrack use system net traffic only, skip it.");
        return false;
    }

    private void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, boolean z2) {
        String strOptString;
        String strA = "";
        String appId = this.f2891e.getAppId();
        if (z2) {
            appId = this.f2891e.getAdEventAppId();
        }
        if (!a(appId)) {
            try {
                String strA2 = com.xiaomi.onetrack.b.h.a().a(appId, str, com.xiaomi.onetrack.b.a.f3026f, "");
                if (jSONObject3 == null) {
                    strOptString = "";
                } else {
                    strOptString = jSONObject3.optString("tip");
                }
                com.xiaomi.onetrack.util.q.a(f2887a, "tip: " + strOptString + ", needIds: " + strA2);
                if (b(strOptString, strA2)) {
                    if (c(appId, str)) {
                        com.xiaomi.onetrack.util.q.a(f2887a, " This event disabled tracking data , skip it.");
                        return;
                    } else if (!d(appId, str)) {
                        strA = com.xiaomi.onetrack.b.h.a().a(appId, str, com.xiaomi.onetrack.b.a.f3027g, "");
                    } else {
                        com.xiaomi.onetrack.util.q.a(f2887a, " This event should not upload by sampling , skip it.");
                        return;
                    }
                }
                String strC = com.xiaomi.onetrack.b.h.a().c(appId);
                com.xiaomi.onetrack.util.q.a(f2887a, "bannedParamsForApp: " + strC + ", bannedParamsForEvent: " + strA);
                Set<String> setA = com.xiaomi.onetrack.util.aa.a(strC, strA, com.xiaomi.onetrack.util.aa.f3429b);
                a(jSONObject2, setA);
                a(jSONObject3, setA);
                if (z2) {
                    com.xiaomi.onetrack.f.d.b(appId, com.xiaomi.onetrack.f.a.e(), str, jSONObject.toString());
                    return;
                } else {
                    com.xiaomi.onetrack.f.d.a(appId, com.xiaomi.onetrack.f.a.e(), str, jSONObject.toString());
                    return;
                }
            } catch (Throwable th) {
                com.xiaomi.onetrack.util.q.b(f2887a, "checkCloudControl error：" + th.toString());
                return;
            }
        }
        com.xiaomi.onetrack.util.q.a(f2887a, "This app disabled tracking data, skip it.");
    }

    private void a(JSONObject jSONObject, Set<String> set) {
        if (jSONObject == null || set == null || set.size() == 0) {
            com.xiaomi.onetrack.util.q.a(f2887a, "jsonObject is null or bannedParams is empty");
            return;
        }
        com.xiaomi.onetrack.util.q.a(f2887a, "jsonObject: " + jSONObject.toString() + ", bannedParams: " + set.toString());
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                if (set.contains(itKeys.next())) {
                    itKeys.remove();
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2887a, "filterParams error：" + e2.toString());
        }
    }

    private boolean a(String str) {
        try {
            return com.xiaomi.onetrack.b.h.a().a(str, com.xiaomi.onetrack.b.a.f3021a);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f2887a, "isDisableTrackForApp error: " + e2.toString());
            return false;
        }
    }

    private boolean a(JSONObject jSONObject) {
        try {
            return jSONObject.optBoolean(b.C0069b.f3303F, false);
        } catch (Throwable unused) {
            com.xiaomi.onetrack.util.q.a(f2887a, "");
            return false;
        }
    }
}
