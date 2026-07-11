package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f2973a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Map f2974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f2975c;

    public h(c cVar, boolean z2, Map map) {
        this.f2975c = cVar;
        this.f2973a = z2;
        this.f2974b = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2975c.h()) {
                return;
            }
            if (!this.f2973a) {
                JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f2974b, false);
                if (!com.xiaomi.onetrack.f.b.a(this.f2975c.f2960j)) {
                    String strU = com.xiaomi.onetrack.util.ab.u();
                    String strW = com.xiaomi.onetrack.util.ab.w();
                    jSONObjectA.put("uid", strU);
                    jSONObjectA.put("uid_type", strW);
                }
                JSONObject jSONObjectF = this.f2975c.f("ot_logout");
                c cVar = this.f2975c;
                this.f2975c.i().a("ot_logout", ai.d(jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
            }
            com.xiaomi.onetrack.util.ab.v();
            com.xiaomi.onetrack.util.ab.x();
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "logout error:" + e2.toString());
        }
    }
}
