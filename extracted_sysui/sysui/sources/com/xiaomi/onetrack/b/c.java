package com.xiaomi.onetrack.b;

import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.onetrack.c.y;
import com.xiaomi.onetrack.util.q;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ JSONObject f3050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f3051b;

    public c(a aVar, JSONObject jSONObject) {
        this.f3051b = aVar;
        this.f3050a = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        q.a("AppConfigUpdater", "checkAppConfigVersion start");
        JSONArray jSONArrayOptJSONArray = this.f3050a.optJSONArray(y.f3234a);
        if (jSONArrayOptJSONArray != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                String strOptString = jSONObjectOptJSONObject == null ? "" : jSONObjectOptJSONObject.optString(y.f3235b);
                q.a("AppConfigUpdater", "appId: " + strOptString);
                if (!TextUtils.isEmpty(strOptString)) {
                    int iOptInt = jSONObjectOptJSONObject == null ? 0 : jSONObjectOptJSONObject.optInt("version");
                    int iE = h.a().e(strOptString);
                    boolean zH = a.h(strOptString);
                    boolean z2 = a.f3018A.containsKey(strOptString) && ((Boolean) a.f3018A.get(strOptString)).booleanValue();
                    q.a("AppConfigUpdater", "local version: " + iE + ", server version: " + iOptInt + ", canUpdate: " + zH + ", isScheduling: " + z2);
                    if (iOptInt > 0 && iOptInt > iE && zH && !z2) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 0;
                        messageObtain.obj = strOptString;
                        long jRandom = (long) (Math.random() * 1800000.0d);
                        q.a("AppConfigUpdater", "the message will be handled after " + jRandom + " ms");
                        a.f3045y.sendMessageDelayed(messageObtain, jRandom);
                        a.f3018A.put(strOptString, Boolean.TRUE);
                    }
                }
            }
        }
    }
}
