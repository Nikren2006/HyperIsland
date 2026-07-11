package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3198a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3199b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3200c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f3201d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ m f3202e;

    public q(m mVar, String str, String str2, String str3, String str4) {
        this.f3202e = mVar;
        this.f3198a = str;
        this.f3199b = str2;
        this.f3200c = str3;
        this.f3201d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3202e.a(this.f3198a, this.f3199b, this.f3200c, this.f3201d, true);
    }
}
