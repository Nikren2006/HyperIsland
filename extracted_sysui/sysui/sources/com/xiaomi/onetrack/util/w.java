package com.xiaomi.onetrack.util;

import com.xiaomi.onetrack.Configuration;
import com.xiaomi.onetrack.OneTrack;
import com.xiaomi.onetrack.api.ah;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes2.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3671a = "custom_open";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3672b = "custom_close";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3673c = "exprience_open";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3674d = "exprience_close";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3675e = "PrivacyManager";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final long f3676k = 900000;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private OneTrack.IEventHook f3677f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Configuration f3678g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3679h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f3680i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private long f3681j = 0;

    public w(Configuration configuration) {
        this.f3678g = configuration;
        this.f3679h = ab.k(s.a(configuration));
    }

    private boolean b(String str) {
        return "onetrack_dau".equals(str) || ah.f2827g.equals(str);
    }

    private boolean c(String str) {
        OneTrack.IEventHook iEventHook = this.f3677f;
        return iEventHook != null && iEventHook.isRecommendEvent(str);
    }

    private boolean d(String str) {
        OneTrack.IEventHook iEventHook = this.f3677f;
        return iEventHook != null && iEventHook.isCustomDauEvent(str);
    }

    public boolean a(String str) {
        boolean zB;
        boolean zIsUseCustomPrivacyPolicy = this.f3678g.isUseCustomPrivacyPolicy();
        String str2 = Const.Param.REOPEN_FALSE;
        if (zIsUseCustomPrivacyPolicy) {
            StringBuilder sb = new StringBuilder();
            sb.append("use custom privacy policy, the policy is ");
            if (this.f3679h) {
                str2 = "open";
            }
            sb.append(str2);
            q.a(f3675e, sb.toString());
            zB = this.f3679h;
        } else {
            zB = b();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("use system experience plan, the policy is ");
            if (zB) {
                str2 = "open";
            }
            sb2.append(str2);
            q.a(f3675e, sb2.toString());
        }
        if (zB) {
            return zB;
        }
        boolean zB2 = b(str);
        boolean zC = c(str);
        boolean zD = d(str);
        StringBuilder sb3 = new StringBuilder();
        sb3.append("This event ");
        sb3.append(str);
        sb3.append(zB2 ? " is " : " is not ");
        sb3.append("basic event and ");
        sb3.append(zC ? "is" : "is not");
        sb3.append(" recommend event and ");
        sb3.append(zD ? "is" : "is not");
        sb3.append(" custom dau event");
        q.a(f3675e, sb3.toString());
        return zB2 || zC || zD;
    }

    private boolean b() {
        if (Math.abs(System.currentTimeMillis() - this.f3681j) > f3676k) {
            this.f3681j = System.currentTimeMillis();
            this.f3680i = r.a(com.xiaomi.onetrack.f.a.b());
        }
        return this.f3680i;
    }

    public void a(OneTrack.IEventHook iEventHook) {
        this.f3677f = iEventHook;
    }

    public void a(boolean z2) {
        this.f3679h = z2;
    }

    public String a() {
        if (this.f3678g.isUseCustomPrivacyPolicy()) {
            if (this.f3679h) {
                return f3671a;
            }
            return f3672b;
        }
        if (b()) {
            return f3673c;
        }
        return f3674d;
    }
}
