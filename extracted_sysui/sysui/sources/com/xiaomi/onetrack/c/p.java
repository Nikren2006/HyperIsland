package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ m f3197b;

    public p(m mVar, String str) {
        this.f3197b = mVar;
        this.f3196a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3197b.a((String) null, (String) null, (String) null, this.f3196a, false);
    }
}
