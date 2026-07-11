package com.xiaomi.onetrack.f;

/* JADX INFO: loaded from: classes2.dex */
final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3337b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ String f3338c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final /* synthetic */ String f3339d;

    public e(String str, String str2, String str3, String str4) {
        this.f3336a = str;
        this.f3337b = str2;
        this.f3338c = str3;
        this.f3339d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.onetrack.c.d.a().a(com.xiaomi.onetrack.e.b.a(this.f3336a, this.f3337b, this.f3338c, this.f3339d), false);
    }
}
