package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f2982a;

    public j(c cVar) {
        this.f2982a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2982a.h() || com.xiaomi.onetrack.util.ad.d(com.xiaomi.onetrack.util.ab.t())) {
                return;
            }
            com.xiaomi.onetrack.util.ab.m(System.currentTimeMillis());
            JSONObject jSONObjectF = this.f2982a.f("onetrack_dau");
            c cVar = this.f2982a;
            this.f2982a.i().a("onetrack_dau", ai.a(cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "trackDau error  e:" + e2.toString());
        }
    }
}
