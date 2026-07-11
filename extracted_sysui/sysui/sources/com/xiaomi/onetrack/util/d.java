package com.xiaomi.onetrack.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.onetrack.api.ah;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3496a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3497b = "com.xiaomi.onetrack.DEBUG";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3498c = "com.xiaomi.onetrack.permissions.DEBUG_MODE";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3499d = "/api/open/device/writeBack";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3500e = "http://";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3501f = "https://";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final String f3502g = ".mi.com";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static volatile d f3503h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f3504i = 100;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Context f3505j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Handler f3506k = new e(this, Looper.getMainLooper());

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private BroadcastReceiver f3507l = new f(this);

    private d() {
        Context contextB = com.xiaomi.onetrack.f.a.b();
        this.f3505j = contextB;
        a(contextB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Toast.makeText(this.f3505j, str, 1).show();
    }

    public static d a() {
        if (f3503h == null) {
            synchronized (d.class) {
                try {
                    if (f3503h == null) {
                        f3503h = new d();
                    }
                } finally {
                }
            }
        }
        return f3503h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt(com.xiaomi.onetrack.g.a.f3351d);
            String strOptString = jSONObject.optString(ah.f2833m);
            String strOptString2 = jSONObject.optString("result");
            boolean zOptBoolean = jSONObject.optBoolean("success");
            Message messageObtain = Message.obtain();
            messageObtain.what = 100;
            Bundle bundle = new Bundle();
            if (iOptInt == 0 && zOptBoolean) {
                bundle.putString("hint", strOptString2);
            } else {
                bundle.putString("hint", strOptString);
            }
            messageObtain.setData(bundle);
            this.f3506k.sendMessage(messageObtain);
        } catch (JSONException e2) {
            q.b(f3496a, e2.getMessage());
        }
    }

    private void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(f3497b);
            context.registerReceiver(this.f3507l, intentFilter, f3498c, null, 2);
        } catch (Exception e2) {
            q.a(f3496a, "registerDebugModeReceiver: " + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.endsWith(f3502g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        i.a(new g(this, str, str2, str3));
    }
}
