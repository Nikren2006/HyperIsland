package com.xiaomi.onetrack.b;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3048a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f3049b;

    public b(a aVar, String str) {
        this.f3049b = aVar;
        this.f3048a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (TextUtils.isEmpty(this.f3048a)) {
            return;
        }
        this.f3049b.c(this.f3048a);
    }
}
