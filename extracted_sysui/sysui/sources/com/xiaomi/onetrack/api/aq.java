package com.xiaomi.onetrack.api;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.api.at;
import com.xiaomi.onetrack.f.b;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class aq implements ak, at.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2895a = "OneTrackSystemImp";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2896b = 102400;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f2897c = 512000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f2898d = 2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f2899e = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Configuration f2900f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private at f2901g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private com.xiaomi.onetrack.util.w f2902h;

    public aq(Configuration configuration, com.xiaomi.onetrack.util.w wVar) {
        this.f2900f = configuration;
        this.f2902h = wVar;
        at atVarA = at.a();
        this.f2901g = atVarA;
        atVarA.a(this);
        com.xiaomi.onetrack.util.i.a(new ar(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            com.xiaomi.onetrack.c.m.a().c(this.f2900f.getAppId());
            if (TextUtils.isEmpty(this.f2900f.getAdEventAppId())) {
                return;
            }
            com.xiaomi.onetrack.c.m.a().c(this.f2900f.getAdEventAppId());
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.a(f2895a, "trackCachedEvents: " + e2.toString());
        }
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(String str, String str2) {
        boolean zA = a(str2);
        com.xiaomi.onetrack.util.w wVar = this.f2902h;
        if (wVar != null && !wVar.a(str) && !zA) {
            com.xiaomi.onetrack.util.q.a(f2895a, "The privacy policy is not permitted, and the event is not basic or recommend event or custom dau event, skip it.");
            return;
        }
        if (a(str, str2, zA)) {
            if (com.xiaomi.onetrack.c.j.b()) {
                com.xiaomi.onetrack.c.j.a(this);
            } else if (!ah.f2829i.equalsIgnoreCase(str)) {
                com.xiaomi.onetrack.c.j.a(str, str2);
                return;
            }
            if (com.xiaomi.onetrack.util.q.f3627a) {
                com.xiaomi.onetrack.util.q.a(f2895a, "track name:" + str + " data :" + str2 + " tid" + Process.myTid());
            }
            String appId = this.f2900f.getAppId();
            if (zA) {
                appId = this.f2900f.getAdEventAppId();
            }
            if (this.f2901g.a(str, str2, appId)) {
                return;
            }
            com.xiaomi.onetrack.c.m.a().a(appId, str, str2);
            if (com.xiaomi.onetrack.util.q.f3627a) {
                com.xiaomi.onetrack.util.q.a(f2895a, "track mIOneTrackService is null! SystemImpCacheManager cache data:" + str2);
            }
        }
    }

    private boolean a(String str, String str2, boolean z2) {
        if (OneTrack.isDisable()) {
            return false;
        }
        boolean z3 = str != null && str.equals("onetrack_bug_report");
        if (!z2 && !z3) {
            if (str2 != null && str2.length() * 2 > f2896b) {
                com.xiaomi.onetrack.util.q.a(f2895a, "Event size exceed limitation!");
                return false;
            }
        } else if (str2 != null && str2.length() > f2897c) {
            com.xiaomi.onetrack.util.q.a(f2895a, "ad Event size exceed limitation!");
            return false;
        }
        return true;
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(int i2) {
        this.f2901g.a(i2);
    }

    @Override // com.xiaomi.onetrack.api.ak
    public void a(boolean z2) {
        if (an.f2885k) {
            com.xiaomi.onetrack.c.j.a(this);
        }
    }

    @Override // com.xiaomi.onetrack.api.at.a
    public void a() {
        com.xiaomi.onetrack.util.i.a(new as(this));
    }

    private boolean a(String str) {
        try {
            return new JSONObject(str).optJSONObject(ai.f2848b).optBoolean(b.C0069b.f3303F, false);
        } catch (Throwable unused) {
            com.xiaomi.onetrack.util.q.a(f2895a, "");
            return false;
        }
    }
}
