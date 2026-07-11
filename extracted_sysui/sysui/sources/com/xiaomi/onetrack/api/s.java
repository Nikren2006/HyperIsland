package com.xiaomi.onetrack.api;

import com.xiaomi.onetrack.ServiceQualityEvent;

/* JADX INFO: loaded from: classes2.dex */
class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ServiceQualityEvent f2999a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f3000b;

    public s(c cVar, ServiceQualityEvent serviceQualityEvent) {
        this.f3000b = cVar;
        this.f2999a = serviceQualityEvent;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f3000b.h()) {
                return;
            }
            ServiceQualityEvent serviceQualityEvent = this.f2999a;
            c cVar = this.f3000b;
            this.f3000b.i().a(ah.f2825e, ai.a(serviceQualityEvent, cVar.f2956f, cVar.f2958h, cVar.f2959i, cVar.f2960j));
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b("BaseOneTrackImp", "trackNetAvailableEvent error: " + e2.toString());
        }
    }
}
