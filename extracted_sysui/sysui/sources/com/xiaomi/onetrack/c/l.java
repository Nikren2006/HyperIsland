package com.xiaomi.onetrack.c;

import com.xiaomi.onetrack.api.ak;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ak f3164a;

    public l(ak akVar) {
        this.f3164a = akVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            List<JSONObject> listC = j.c();
            if (listC != null && listC.size() > 0) {
                com.xiaomi.onetrack.util.q.a("NetworkAccessManager", "cache file has " + listC.size() + " events, start to upload");
                for (JSONObject jSONObject : listC) {
                    this.f3164a.a(jSONObject.optString(j.f3152b), jSONObject.optString("data"));
                }
            }
            j.c(true);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("NetworkAccessManager", "cta event error: " + e2.getMessage());
        }
        boolean unused = j.f3161k = false;
    }
}
