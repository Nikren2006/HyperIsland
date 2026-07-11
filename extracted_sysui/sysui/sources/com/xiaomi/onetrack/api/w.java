package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3005a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Map f3006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3007c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ c f3008d;

    public w(c cVar, String str, Map map, String str2) {
        this.f3008d = cVar;
        this.f3005a = str;
        this.f3006b = map;
        this.f3007c = str2;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (!this.f3008d.h() && !this.f3008d.d(this.f3005a)) {
                JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f3006b, true);
                JSONObject jSONObjectF = this.f3008d.f(this.f3005a);
                String str = this.f3007c;
                String str2 = this.f3005a;
                c cVar = this.f3008d;
                this.f3008d.i().a(this.f3005a, ai.a(str, str2, jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "track json error:" + e2.toString());
        }
    }
}
