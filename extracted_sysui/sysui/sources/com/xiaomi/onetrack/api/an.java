package com.xiaomi.onetrack.api;

import android.content.Context;
import com.xiaomi.onetrack.Configuration;

/* JADX INFO: loaded from: classes2.dex */
public class an extends c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static volatile boolean f2885k = true;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String f2886l = "OneTrackImp";

    public an(Context context, Configuration configuration) {
        super(context, configuration);
    }

    public static void a(String str, String str2) {
    }

    @Override // com.xiaomi.onetrack.api.c
    public void a(Context context) {
        if (com.xiaomi.onetrack.util.r.b() && j() && a()) {
            com.xiaomi.onetrack.util.p.a().a(Boolean.TRUE);
            this.f2952a = new aq(this.f2956f, this.f2959i);
        } else {
            com.xiaomi.onetrack.util.p.a().a(Boolean.FALSE);
            this.f2952a = new ao(context, this.f2956f, this.f2959i);
        }
        ad.a().a(this.f2952a);
    }
}
