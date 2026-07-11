package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class as implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ aq f2904a;

    public as(aq aqVar) {
        this.f2904a = aqVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (com.xiaomi.onetrack.c.j.b()) {
            this.f2904a.b();
        }
    }
}
