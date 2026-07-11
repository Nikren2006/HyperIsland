package com.xiaomi.onetrack.f;

import android.content.Context;
import com.xiaomi.onetrack.api.ad;
import com.xiaomi.onetrack.util.p;

/* JADX INFO: loaded from: classes2.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f3346a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ g f3347b;

    public h(g gVar, Context context) {
        this.f3347b = gVar;
        this.f3346a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        p.a().a(Boolean.FALSE);
        com.xiaomi.onetrack.c.a.a();
        ad.a().d();
        ad.a().f();
        com.xiaomi.onetrack.c.d.a(this.f3346a);
    }
}
