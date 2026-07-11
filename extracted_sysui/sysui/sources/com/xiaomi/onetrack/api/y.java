package com.xiaomi.onetrack.api;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class y implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Map f3013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ List f3014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ c f3015d;

    public y(c cVar, String str, Map map, List list) {
        this.f3015d = cVar;
        this.f3012a = str;
        this.f3013b = map;
        this.f3014c = list;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (TextUtils.isEmpty(this.f3015d.f2956f.getAdEventAppId())) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "adEventAppId is null,Please configure,event name:" + this.f3012a);
                return;
            }
            if (!this.f3015d.h() && !this.f3015d.d(this.f3012a)) {
                JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f3013b, true);
                JSONArray jSONArrayA = com.xiaomi.onetrack.util.s.a(this.f3014c);
                JSONObject jSONObjectF = this.f3015d.f(this.f3012a);
                String str = this.f3012a;
                c cVar = this.f3015d;
                this.f3015d.i().a(this.f3012a, ai.a(str, jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, jSONArrayA, cVar.f2960j));
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "track map error: " + e2.toString());
        }
    }
}
