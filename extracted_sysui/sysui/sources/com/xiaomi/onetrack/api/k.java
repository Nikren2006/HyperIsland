package com.xiaomi.onetrack.api;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ boolean f2984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f2985c;

    public k(c cVar, String str, boolean z2) {
        this.f2985c = cVar;
        this.f2983a = str;
        this.f2984b = z2;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2985c.h()) {
                return;
            }
            if (!this.f2985c.f2956f.isAutoTrackActivityAction()) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "config.autoTrackActivityAction is false, ignore onetrack_pa resume event");
                return;
            }
            JSONObject jSONObjectF = this.f2985c.f(ah.f2827g);
            String str = this.f2983a;
            c cVar = this.f2985c;
            this.f2985c.i().a(ah.f2827g, ai.a(str, ah.f2827g, cVar.f2956f, cVar.f2958h, jSONObjectF, this.f2984b, cVar.f2959i, cVar.f2960j));
            if (com.xiaomi.onetrack.util.q.f3627a) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "trackPageStartAuto");
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "auto trackPageStartAuto error: " + e2.toString());
        }
    }
}
