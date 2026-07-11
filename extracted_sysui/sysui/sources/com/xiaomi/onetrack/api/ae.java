package com.xiaomi.onetrack.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
class ae extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ad f2788a;

    public ae(ad adVar) {
        this.f2788a = adVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            if (this.f2788a.f2780d != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    this.f2788a.f2780d.sendEmptyMessageDelayed(100, 500L);
                } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                    this.f2788a.f2780d.sendEmptyMessageDelayed(101, 500L);
                }
            }
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b("BroadcastManager", "screenReceiver throwable: " + th.getMessage());
        }
    }
}
