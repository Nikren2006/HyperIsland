package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.a;
import com.xiaomi.onetrack.util.q;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3555c = "ASUSDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3556a = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ServiceConnection f3557b = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.ASUSDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3547a.f3556a.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                q.a("ASUSDeviceIDHelper", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0050 -> B:28:0x0077). Please report as a decompilation issue!!! */
    public String a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        String strA = "";
        try {
        } catch (Exception e2) {
            q.a(f3555c, e2.getMessage());
        }
        if (context.bindService(intent, this.f3557b, 1)) {
            try {
                try {
                    IBinder iBinderPoll = this.f3556a.poll(1L, TimeUnit.SECONDS);
                    if (iBinderPoll == null) {
                        return "";
                    }
                    strA = new a.C0070a(iBinderPoll).a();
                    context.unbindService(this.f3557b);
                } catch (Exception e3) {
                    q.a(f3555c, e3.getMessage());
                    context.unbindService(this.f3557b);
                }
            } finally {
                try {
                    context.unbindService(this.f3557b);
                } catch (Exception e4) {
                    q.a(f3555c, e4.getMessage());
                }
            }
        }
        return strA;
    }
}
