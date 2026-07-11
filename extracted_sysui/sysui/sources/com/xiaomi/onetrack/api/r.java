package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2998b;

    public r(c cVar, String str) {
        this.f2998b = cVar;
        this.f2997a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            com.xiaomi.onetrack.util.p.a().a(this.f2997a);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "setInstanceId error: " + e2.toString());
        }
    }
}
