package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.d;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3588a = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ServiceConnection f3589b = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.LenovoDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3550a.f3588a.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public String a(Context context) {
        context.getPackageName();
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        String strA = "";
        if (context.bindService(intent, this.f3589b, 1)) {
            try {
                try {
                    IBinder iBinderPoll = this.f3588a.poll(1L, TimeUnit.SECONDS);
                    if (iBinderPoll == null) {
                        return "";
                    }
                    strA = new d.a.C0072a(iBinderPoll).a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    context.unbindService(this.f3589b);
                } catch (Exception unused) {
                }
            } finally {
                try {
                    context.unbindService(this.f3589b);
                } catch (Exception unused2) {
                }
            }
        }
        return strA;
    }
}
