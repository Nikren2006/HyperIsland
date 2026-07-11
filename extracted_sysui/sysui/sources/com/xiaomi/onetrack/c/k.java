package com.xiaomi.onetrack.c;

import com.xiaomi.onetrack.api.ad;

/* JADX INFO: loaded from: classes2.dex */
final class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f3163b;

    public k(String str, String str2) {
        this.f3162a = str;
        this.f3163b = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ad.a().e();
        j.c(this.f3162a, this.f3163b);
    }
}
