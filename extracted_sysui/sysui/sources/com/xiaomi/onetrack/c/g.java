package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ com.xiaomi.onetrack.f.b f3143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ boolean f3144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f3145c;

    public g(d dVar, com.xiaomi.onetrack.f.b bVar, boolean z2) {
        this.f3145c = dVar;
        this.f3143a = bVar;
        this.f3144b = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f3145c.b(this.f3143a, this.f3144b);
            if (this.f3144b) {
                com.xiaomi.onetrack.a.a.a().a(this.f3143a);
            }
            com.xiaomi.onetrack.a.c.b.a().b();
            com.xiaomi.onetrack.util.q.a("EventManager", "addEvent: " + this.f3143a.e() + ", data: " + this.f3143a.g().toString());
            y.a().a(this.f3143a.f(), false);
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("EventManager", "EventManager.addEvent exception: ", e2);
        }
    }
}
