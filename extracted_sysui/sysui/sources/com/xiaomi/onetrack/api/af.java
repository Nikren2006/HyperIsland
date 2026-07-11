package com.xiaomi.onetrack.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
class af extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ad f2789a;

    public af(ad adVar) {
        this.f2789a = adVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (this.f2789a.f2780d != null) {
            Intent intent2 = new Intent();
            intent2.putExtras(intent);
            Message messageObtain = Message.obtain();
            messageObtain.what = 10;
            messageObtain.obj = intent2;
            this.f2789a.f2780d.sendMessage(messageObtain);
            com.xiaomi.onetrack.util.q.a("BroadcastManager", "netReceiver");
        }
    }
}
