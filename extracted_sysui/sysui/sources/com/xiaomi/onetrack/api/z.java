package com.xiaomi.onetrack.api;

import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class z implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f3017b;

    public z(c cVar, String str) {
        this.f3017b = cVar;
        this.f3016a = str;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f3017b.h()) {
                return;
            }
            JSONArray jSONArray = new JSONArray(this.f3016a);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i2);
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has(ai.f2848b) && jSONObjectOptJSONObject.has(ai.f2847a)) {
                    aj ajVar = new aj(jSONObjectOptJSONObject.optJSONObject(ai.f2848b));
                    if (!this.f3017b.e(ajVar.a())) {
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(ai.f2847a);
                        JSONObject jSONObjectF = this.f3017b.f(ajVar.a());
                        c cVar = this.f3017b;
                        this.f3017b.i().a(ajVar.a(), ai.a(ajVar, jSONObjectOptJSONObject2, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
                    }
                } else {
                    com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "h5 json is empty or has no \"H\" or \"B\" ");
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "trackEventFromH5 error: " + e2.toString());
        }
    }
}
