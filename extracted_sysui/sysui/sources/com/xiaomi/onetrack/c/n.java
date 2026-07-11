package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class n implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3188b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ m f3190d;

    public n(m mVar, String str, String str2, String str3) {
        this.f3190d = mVar;
        this.f3187a = str;
        this.f3188b = str2;
        this.f3189c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3190d.c(this.f3187a, this.f3188b, this.f3189c, null);
    }
}
