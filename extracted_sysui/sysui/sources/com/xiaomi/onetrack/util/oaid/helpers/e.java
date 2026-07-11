package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.xiaomi.onetrack.util.oaid.a.c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3582d = "HonorDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3583a = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedBlockingQueue<String> f3584b = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ServiceConnection f3585c = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.HonorDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder == null) {
                return;
            }
            try {
                this.f3549a.f3583a.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                Log.d("HonorDeviceIDHelper", "onServiceConnected get exception: " + e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f3586e;

    public class a extends c.a.AbstractBinderC0071a {
        public a() {
        }

        @Override // com.xiaomi.onetrack.util.oaid.a.c.a
        public void a(int i2, long j2, boolean z2, float f2, double d2, String str) {
        }

        @Override // com.xiaomi.onetrack.util.oaid.a.c.a
        public void a(int i2, Bundle bundle) {
            if (bundle == null || !bundle.containsKey("oa_id_flag")) {
                return;
            }
            try {
                e.this.f3584b.offer(bundle.getString("oa_id_flag"), 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                Log.d(e.f3582d, "callback exception: " + e2.getMessage());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [android.content.ServiceConnection] */
    public String a(Context context) throws InterruptedException {
        String strPoll = "";
        try {
            try {
                Intent intent = new Intent("com.hihonor.id.HnOaIdService");
                intent.setPackage("com.hihonor.id");
                if (context.bindService(intent, this.f3585c, 1)) {
                    LinkedBlockingQueue<IBinder> linkedBlockingQueue = this.f3583a;
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    IBinder iBinderPoll = linkedBlockingQueue.poll(1L, timeUnit);
                    if (iBinderPoll == null) {
                        return "";
                    }
                    c.b bVar = new c.b(iBinderPoll);
                    a aVar = new a();
                    this.f3586e = aVar;
                    bVar.a(aVar);
                    strPoll = this.f3584b.poll(1L, timeUnit);
                }
            } catch (Exception e2) {
                Log.e(f3582d, "aidl getOaid error: " + e2.getMessage());
            }
            return strPoll;
        } finally {
            context.unbindService(this.f3585c);
        }
    }
}
