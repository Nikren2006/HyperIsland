package com.xiaomi.onetrack.f;

/* JADX INFO: loaded from: classes2.dex */
final class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3340a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3341b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3342c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f3343d;

    public f(String str, String str2, String str3, String str4) {
        this.f3340a = str;
        this.f3341b = str2;
        this.f3342c = str3;
        this.f3343d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.onetrack.c.d.a().a(com.xiaomi.onetrack.e.b.b(this.f3340a, this.f3341b, this.f3342c, this.f3343d), true);
    }
}
