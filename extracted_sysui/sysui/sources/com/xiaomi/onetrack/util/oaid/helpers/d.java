package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.b;
import com.xiaomi.onetrack.util.q;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3579c = "HWDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3580a = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ServiceConnection f3581b = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.HWDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3548a.f3580a.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                q.a("HWDeviceIDHelper", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0046 -> B:32:0x006d). Please report as a decompilation issue!!! */
    public String a(Context context) {
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        String strA = "";
        if (context.bindService(intent, this.f3581b, 1)) {
            try {
                try {
                    try {
                        IBinder iBinderPoll = this.f3580a.poll(1L, TimeUnit.SECONDS);
                        if (iBinderPoll == null) {
                            return "";
                        }
                        strA = new b.a(iBinderPoll).a();
                        context.unbindService(this.f3581b);
                    } catch (Exception e2) {
                        q.a(f3579c, e2.getMessage());
                        context.unbindService(this.f3581b);
                    }
                } catch (Exception e3) {
                    q.a(f3579c, e3.getMessage());
                }
            } finally {
                try {
                    context.unbindService(this.f3581b);
                } catch (Exception e4) {
                    q.a(f3579c, e4.getMessage());
                }
            }
        }
        return strA;
    }
}
