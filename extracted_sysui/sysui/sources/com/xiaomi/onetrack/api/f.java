package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Map f2968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2969b;

    public f(c cVar, Map map) {
        this.f2969b = cVar;
        this.f2968a = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2969b.h()) {
                return;
            }
            JSONObject jSONObjectF = this.f2969b.f(ah.f2824d);
            JSONObject jSONObject = new JSONObject(this.f2968a);
            c cVar = this.f2969b;
            this.f2969b.i().a(ah.f2824d, ai.b(jSONObject, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "userProfileIncrement map error:" + e2.toString());
        }
    }
}
