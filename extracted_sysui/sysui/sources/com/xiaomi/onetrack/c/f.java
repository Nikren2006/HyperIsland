package com.xiaomi.onetrack.c;

import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Intent f3141a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ e f3142b;

    public f(e eVar, Intent intent) {
        this.f3142b = eVar;
        this.f3141a = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        String action = this.f3141a.getAction();
        if (action.equals("android.intent.action.SCREEN_OFF") || action.equals("android.intent.action.SCREEN_ON")) {
            com.xiaomi.onetrack.util.q.a("EventManager", "screen on/off");
            y.a().a(action.equals("android.intent.action.SCREEN_ON") ? 0 : 2, false);
            com.xiaomi.onetrack.a.c.b.a().b();
        }
    }
}
