package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Number f2971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f2972c;

    public g(c cVar, String str, Number number) {
        this.f2972c = cVar;
        this.f2970a = str;
        this.f2971b = number;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2972c.h()) {
                return;
            }
            JSONObject jSONObjectF = this.f2972c.f(ah.f2824d);
            JSONObject jSONObjectPut = new JSONObject().put(this.f2970a, this.f2971b);
            c cVar = this.f2972c;
            this.f2972c.i().a(ah.f2824d, ai.b(jSONObjectPut, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "userProfileIncrement single error:" + e2.toString());
        }
    }
}
