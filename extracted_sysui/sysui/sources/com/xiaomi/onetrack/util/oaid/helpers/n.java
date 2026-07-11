package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.h;
import com.xiaomi.onetrack.util.q;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3612d = "ZTEDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f3613a = "com.mdid.msa";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3614b = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ServiceConnection f3615c = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.ZTEDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3554a.f3614b.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                q.a("ZTEDeviceIDHelper", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    private void a(String str, Context context) {
        Intent intent = new Intent();
        intent.setClassName(this.f3613a, "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", str);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            context.startService(intent);
        } catch (Exception e2) {
            q.a(f3612d, e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x006a -> B:33:0x0091). Please report as a decompilation issue!!! */
    public String a(Context context) {
        try {
            context.getPackageManager().getPackageInfo(this.f3613a, 0);
        } catch (Exception e2) {
            q.a(f3612d, e2.getMessage());
        }
        String packageName = context.getPackageName();
        a(packageName, context);
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        boolean zBindService = context.bindService(intent, this.f3615c, 1);
        String strB = "";
        try {
            try {
            } finally {
                try {
                    context.unbindService(this.f3615c);
                } catch (Exception e3) {
                    q.a(f3612d, e3.getMessage());
                }
            }
        } catch (Exception e4) {
            String message = e4.getMessage();
            q.a(f3612d, message);
            this = message;
        }
        if (zBindService) {
            try {
                IBinder iBinderPoll = this.f3614b.poll(1L, TimeUnit.SECONDS);
                if (iBinderPoll == null) {
                    return "";
                }
                strB = new h.a.C0075a(iBinderPoll).b();
                ServiceConnection serviceConnection = this.f3615c;
                context.unbindService(serviceConnection);
                this = serviceConnection;
            } catch (Exception e5) {
                q.a(f3612d, e5.getMessage());
                ServiceConnection serviceConnection2 = this.f3615c;
                context.unbindService(serviceConnection2);
                this = serviceConnection2;
            }
        }
        return strB;
    }
}
