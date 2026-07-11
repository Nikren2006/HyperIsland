package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class v implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f3003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f3004b;

    public v(c cVar, boolean z2) {
        this.f3004b = cVar;
        this.f3003a = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3004b.f2959i.a(this.f3003a);
        com.xiaomi.onetrack.util.ab.a(com.xiaomi.onetrack.util.s.a(this.f3004b.f2956f), this.f3003a);
    }
}
