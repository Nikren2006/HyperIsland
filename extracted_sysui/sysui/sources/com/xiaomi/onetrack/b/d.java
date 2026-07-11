package com.xiaomi.onetrack.b;

import com.xiaomi.onetrack.b.a;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a.b f3053b;

    public d(a.b bVar, String str) {
        this.f3053b = bVar;
        this.f3052a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.b((List<String>) Arrays.asList(this.f3052a));
        a.f3018A.put(this.f3052a, Boolean.FALSE);
    }
}
