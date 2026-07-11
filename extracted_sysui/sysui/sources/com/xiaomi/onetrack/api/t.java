package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class t implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f3001a;

    public t(c cVar) {
        this.f3001a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f3001a.h()) {
                return;
            }
            c cVar = this.f3001a;
            this.f3001a.i().a(ah.f2829i, ai.a(cVar.f2956f, cVar.f2958h, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "cta event error: " + e2.toString());
        }
    }
}
