package com.xiaomi.onetrack.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
class e extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3508a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(d dVar, Looper looper) {
        super(looper);
        this.f3508a = dVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 100) {
            return;
        }
        this.f3508a.c(message.getData().getString("hint"));
    }
}
