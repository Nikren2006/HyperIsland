package com.xiaomi.onetrack.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f2989a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2990b;

    public m(c cVar, boolean z2) {
        this.f2990b = cVar;
        this.f2989a = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f2990b.h()) {
                return;
            }
            if (!this.f2990b.f2956f.isAutoTrackActivityAction()) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "config.autoTrackActivityAction is false, ignore onetrack_pa pause event");
                return;
            }
            String strZ = com.xiaomi.onetrack.util.ab.z();
            if (TextUtils.isEmpty(strZ)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strZ);
            this.f2990b.i().a(ah.f2827g, jSONObject.put(ai.f2847a, jSONObject.optJSONObject(ai.f2847a).put(ah.f2841u, this.f2989a)).toString());
            if (com.xiaomi.onetrack.util.q.f3627a) {
                com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "trackPageEndAuto");
            }
            com.xiaomi.onetrack.util.ab.i("");
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "trackPageEndAuto error:" + e2.toString());
        }
    }
}
