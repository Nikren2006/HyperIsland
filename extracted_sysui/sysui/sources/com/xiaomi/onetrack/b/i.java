package com.xiaomi.onetrack.b;

import com.xiaomi.onetrack.util.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ArrayList f3077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ h f3078b;

    public i(h hVar, ArrayList arrayList) {
        this.f3078b = hVar;
        this.f3077a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        if (q.f3627a) {
            q.a("ConfigDbManager", "update: " + this.f3077a);
        }
        this.f3078b.b((ArrayList<l>) this.f3077a);
    }
}
