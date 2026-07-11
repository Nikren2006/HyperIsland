package com.xiaomi.onetrack.api;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class x implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Map f3010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ c f3011c;

    public x(c cVar, String str, Map map) {
        this.f3011c = cVar;
        this.f3009a = str;
        this.f3010b = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (!this.f3011c.h() && !this.f3011c.d(this.f3009a)) {
                JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f3010b, true);
                JSONObject jSONObjectF = this.f3011c.f(this.f3009a);
                String str = this.f3009a;
                c cVar = this.f3011c;
                this.f3011c.i().a(this.f3009a, ai.a(str, jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "track map error: " + e2.toString());
        }
    }
}
