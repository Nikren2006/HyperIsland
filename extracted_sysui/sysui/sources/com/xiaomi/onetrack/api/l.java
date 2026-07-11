package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f2987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f2988c;

    public l(c cVar, String str, long j2) {
        this.f2988c = cVar;
        this.f2986a = str;
        this.f2987b = j2;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2988c.h()) {
                return;
            }
            if (!this.f2988c.f2956f.isAutoTrackActivityAction()) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "config.autoTrackActivityAction is false, ignore onetrack_pa pause event");
                return;
            }
            JSONObject jSONObjectF = this.f2988c.f(ah.f2827g);
            String str = this.f2986a;
            long j2 = this.f2987b;
            c cVar = this.f2988c;
            com.xiaomi.onetrack.util.ab.i(ai.a(str, ah.f2827g, j2, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "savePageEndData error:" + e2.toString());
        }
    }
}
