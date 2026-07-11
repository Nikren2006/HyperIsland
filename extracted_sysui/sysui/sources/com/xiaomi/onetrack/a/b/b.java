package com.xiaomi.onetrack.a.b;

import android.text.TextUtils;
import com.xiaomi.onetrack.api.ai;
import com.xiaomi.onetrack.b.h;
import com.xiaomi.onetrack.f.b;
import com.xiaomi.onetrack.util.q;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b extends com.xiaomi.onetrack.f.b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2730d = "OTAdEvent";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<a> f2731e = null;

    public b(String str, String str2, String str3, String str4) {
        try {
            a(str);
            c(str3);
            b(str2);
            b(System.currentTimeMillis());
            JSONObject jSONObject = new JSONObject(str4);
            JSONObject jSONObject2 = jSONObject.getJSONObject(ai.f2848b);
            b(jSONObject);
            a(h.a().a(str, str3, com.xiaomi.onetrack.b.a.f3024d, 0));
            a(jSONObject2);
            c(jSONObject2);
        } catch (Exception e2) {
            q.b(f2730d, "CustomEvent error:" + e2.toString());
        }
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(b.C0069b.f3305H)) {
            jSONObject.remove(b.C0069b.f3305H);
        }
    }

    public List<a> a() {
        return this.f2731e;
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(b.C0069b.f3305H);
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                String strOptString = jSONArrayOptJSONArray.optString(i2);
                if (!TextUtils.isEmpty(strOptString)) {
                    if (strOptString.contains("api.ad.xiaomi.com") && !strOptString.contains("_sn_")) {
                        strOptString = strOptString.contains("?") ? strOptString + "&_sn_=" + UUID.randomUUID().toString() : strOptString + "?_sn_=" + UUID.randomUUID().toString();
                    }
                    a aVar = new a();
                    aVar.c(c());
                    aVar.a(e());
                    aVar.a(h());
                    aVar.d(d());
                    aVar.b(strOptString);
                    arrayList.add(aVar);
                }
            }
            this.f2731e = arrayList;
        } catch (Throwable th) {
            q.a(f2730d, "parseAdMonitor Throwable:" + th.getMessage());
        }
    }
}
