package com.xiaomi.onetrack.api;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2996b;

    public q(c cVar, String str) {
        this.f2996b = cVar;
        this.f2995a = str;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            String strA = com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2996b.f2956f));
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strA);
            jSONObject.remove(this.f2995a);
            com.xiaomi.onetrack.util.l.a(com.xiaomi.onetrack.util.s.a(this.f2996b.f2956f), jSONObject.toString());
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "removeCommonProperty error:" + e2.toString());
        }
    }
}
