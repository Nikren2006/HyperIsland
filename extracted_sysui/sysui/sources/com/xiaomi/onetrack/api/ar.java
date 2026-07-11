package com.xiaomi.onetrack.api;

import com.xiaomi.onetrack.OneTrack;

/* JADX INFO: loaded from: classes2.dex */
class ar implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aq f2903a;

    public ar(aq aqVar) {
        this.f2903a = aqVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.onetrack.util.p.a().a(Boolean.TRUE);
        if (OneTrack.isRestrictGetNetworkInfo()) {
            ad.a().f();
        }
    }
}
