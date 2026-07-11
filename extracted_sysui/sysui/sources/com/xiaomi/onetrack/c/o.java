package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3192b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3193c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f3194d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final /* synthetic */ m f3195e;

    public o(m mVar, String str, String str2, String str3, String str4) {
        this.f3195e = mVar;
        this.f3191a = str;
        this.f3192b = str2;
        this.f3193c = str3;
        this.f3194d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3195e.c(this.f3191a, this.f3192b, this.f3193c, this.f3194d);
    }
}
