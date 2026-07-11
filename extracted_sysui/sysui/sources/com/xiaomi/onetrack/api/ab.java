package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ab implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Map f2767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2768b;

    public ab(c cVar, Map map) {
        this.f2768b = cVar;
        this.f2767a = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2768b.h()) {
                return;
            }
            JSONObject jSONObjectF = this.f2768b.f(ah.f2823c);
            JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f2767a, false);
            c cVar = this.f2768b;
            this.f2768b.i().a(ah.f2823c, ai.a(jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "setUserProfile map error:" + e2.toString());
        }
    }
}
