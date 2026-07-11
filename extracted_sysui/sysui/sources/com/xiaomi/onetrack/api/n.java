package com.xiaomi.onetrack.api;

import android.text.TextUtils;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class n implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Map f2991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2992b;

    public n(c cVar, Map map) {
        this.f2992b = cVar;
        this.f2991a = map;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            JSONObject jSONObjectA = com.xiaomi.onetrack.util.s.a((Map<String, Object>) this.f2991a, true);
            String strA = com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2992b.f2956f));
            com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2992b.f2956f), com.xiaomi.onetrack.util.s.a(jSONObjectA, !TextUtils.isEmpty(strA) ? new JSONObject(strA) : null).toString());
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", " " + e2.toString());
        }
    }
}
