package com.xiaomi.onetrack.api;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f2993a;

    public o(c cVar) {
        this.f2993a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.xiaomi.onetrack.util.q.a();
            com.xiaomi.onetrack.util.r.a();
            com.xiaomi.onetrack.b.a.a().a(this.f2993a.f2956f.getAppId());
            if (com.xiaomi.onetrack.util.aa.b(this.f2993a.f2956f.getAdEventAppId())) {
                ad.a().c();
                com.xiaomi.onetrack.b.a.a().a(this.f2993a.f2956f.getAdEventAppId());
            }
            if (com.xiaomi.onetrack.util.ab.B() == 0) {
                com.xiaomi.onetrack.util.ab.n(System.currentTimeMillis());
            }
            if (!TextUtils.isEmpty(this.f2993a.f2956f.getInstanceId())) {
                com.xiaomi.onetrack.util.p.a().a(this.f2993a.f2956f.getInstanceId());
            }
            this.f2993a.g();
            com.xiaomi.onetrack.util.d.a();
            com.xiaomi.onetrack.c.j.c(false);
            com.xiaomi.onetrack.c.m.a().b(this.f2993a.f2956f.getAppId());
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "init WorkerExecutor execute throwable:" + th.getMessage());
        }
    }
}
