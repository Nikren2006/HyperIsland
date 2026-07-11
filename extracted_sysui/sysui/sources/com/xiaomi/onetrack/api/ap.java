package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class ap implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f2893a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ ao f2894b;

    public ap(ao aoVar, int i2) {
        this.f2894b = aoVar;
        this.f2893a = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.xiaomi.onetrack.c.j.b() && this.f2893a == 2) {
            com.xiaomi.onetrack.c.y.a().a(0, true);
            com.xiaomi.onetrack.c.y.a().a(1, true);
            com.xiaomi.onetrack.a.c.b.a().b();
        }
    }
}
