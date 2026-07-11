package com.xiaomi.onetrack.api;

import android.content.Context;
import com.xiaomi.onetrack.CrashAnalysis;

/* JADX INFO: loaded from: classes2.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f2961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ c f2962b;

    public d(c cVar, Context context) {
        this.f2962b = cVar;
        this.f2961a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (CrashAnalysis.getInstance(this.f2961a, this.f2962b).isSupport()) {
            return;
        }
        c cVar = this.f2962b;
        cVar.f2955e = new al(cVar);
        this.f2962b.f2955e.a();
    }
}
