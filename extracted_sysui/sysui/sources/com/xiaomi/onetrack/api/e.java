package com.xiaomi.onetrack.api;

import com.xiaomi.onetrack.OneTrack;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ OneTrack.UserIdType f2964b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ boolean f2965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ Map f2966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ c f2967e;

    public e(c cVar, String str, OneTrack.UserIdType userIdType, boolean z2, Map map) {
        this.f2967e = cVar;
        this.f2963a = str;
        this.f2964b = userIdType;
        this.f2965c = z2;
        this.f2966d = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            if (this.f2967e.h()) {
                return;
            }
            com.xiaomi.onetrack.util.ab.g(this.f2963a);
            com.xiaomi.onetrack.util.ab.h(this.f2964b.getUserIdType());
            if (this.f2965c) {
                return;
            }
            JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f2966d, false);
            if (!com.xiaomi.onetrack.f.b.a(this.f2967e.f2960j)) {
                jSONObjectA.put("uid", this.f2963a);
                jSONObjectA.put("uid_type", this.f2964b.getUserIdType());
            }
            JSONObject jSONObjectF = this.f2967e.f("ot_login");
            c cVar = this.f2967e;
            this.f2967e.i().a("ot_login", ai.c(jSONObjectA, cVar.f2956f, cVar.f2958h, jSONObjectF, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "login error:" + e2.toString());
        }
    }
}
