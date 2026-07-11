package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ac implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Object f2769a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f2770b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f2771c;

    public ac(c cVar, Object obj, String str) {
        this.f2771c = cVar;
        this.f2769a = obj;
        this.f2770b = str;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2771c.h()) {
                return;
            }
            if (!com.xiaomi.onetrack.util.s.b(this.f2769a)) {
                com.xiaomi.onetrack.util.s.a("BaseOneTrackImp", this.f2770b);
                return;
            }
            JSONObject jSONObjectF = this.f2771c.f(ah.f2823c);
            JSONObject jSONObjectPut = new JSONObject().put(this.f2770b, this.f2769a);
            c cVar = this.f2771c;
            this.f2771c.i().a(ah.f2823c, ai.a(jSONObjectPut, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "setUserProfile single error:" + e2.toString());
        }
    }
}
