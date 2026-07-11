package com.xiaomi.onetrack.util;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3512c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ d f3513d;

    public g(d dVar, String str, String str2, String str3) {
        this.f3513d = dVar;
        this.f3510a = str;
        this.f3511b = str2;
        this.f3512c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        try {
            if (TextUtils.isEmpty(this.f3510a)) {
                return;
            }
            if (this.f3510a.contains("http://") || this.f3510a.contains("https://")) {
                str = this.f3510a + "/api/open/device/writeBack";
            } else {
                str = "https://" + this.f3510a + "/api/open/device/writeBack";
            }
            HashMap map = new HashMap();
            map.put("instanceId", p.a().b());
            map.put("imei", DeviceUtil.b(this.f3513d.f3505j));
            map.put("oaid", com.xiaomi.onetrack.util.oaid.a.a().a(this.f3513d.f3505j));
            map.put("projectId", this.f3511b);
            map.put("user", this.f3512c);
            String strB = com.xiaomi.onetrack.g.b.b(str, map, false);
            if (!TextUtils.isEmpty(strB) && !"".equals(strB)) {
                this.f3513d.b(strB);
                return;
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 100;
            Bundle bundle = new Bundle();
            bundle.putString("hint", "注册信息失败，请检查是网络环境是否在内网");
            messageObtain.setData(bundle);
            this.f3513d.f3506k.sendMessage(messageObtain);
        } catch (Exception e2) {
            q.b(d.f3496a, e2.getMessage());
        }
    }
}
