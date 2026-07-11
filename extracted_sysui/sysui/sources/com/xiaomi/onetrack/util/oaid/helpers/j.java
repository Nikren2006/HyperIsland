package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.e;
import com.xiaomi.onetrack.util.q;
import java.security.MessageDigest;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3598d = "OnePlusDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.xiaomi.onetrack.util.oaid.a.e f3599a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3600b = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ServiceConnection f3601c = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.OnePlusDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3551a.f3600b.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                q.a("OnePlusDeviceIDHelper", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.f3551a.f3599a = null;
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3602e;

    private boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (packageInfo == null) {
                return false;
            }
            return ((long) packageInfo.versionCode) >= 1;
        } catch (Exception e2) {
            q.a(f3598d, e2.getMessage());
            return true;
        }
    }

    public String a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        String strA = "";
        try {
            if (context.bindService(intent, this.f3601c, 1)) {
                try {
                    try {
                        IBinder iBinderPoll = this.f3600b.poll(1L, TimeUnit.SECONDS);
                        if (iBinderPoll == null) {
                            return "";
                        }
                        com.xiaomi.onetrack.util.oaid.a.e eVarA = e.a.a(iBinderPoll);
                        this.f3599a = eVarA;
                        if (eVarA != null) {
                            strA = a("OUID", context);
                        }
                        context.unbindService(this.f3601c);
                    } catch (Exception e2) {
                        q.a(f3598d, e2.getMessage());
                        context.unbindService(this.f3601c);
                    }
                } finally {
                    try {
                        context.unbindService(this.f3601c);
                    } catch (Exception e3) {
                        q.a(f3598d, e3.getMessage());
                    }
                }
            }
        } catch (Exception e4) {
            q.a(f3598d, e4.getMessage());
        }
        return strA;
    }

    private String a(String str, Context context) {
        Signature[] signatureArr;
        String packageName = context.getPackageName();
        if (this.f3602e == null) {
            String string = null;
            try {
                signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            } catch (Exception e2) {
                q.a(f3598d, e2.getMessage());
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    if (messageDigest != null) {
                        byte[] bArrDigest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b2 : bArrDigest) {
                            sb.append(Integer.toHexString((b2 & TransitionInfo.INIT) | 256).substring(1, 3));
                        }
                        string = sb.toString();
                    }
                } catch (Exception e3) {
                    q.a(f3598d, e3.getMessage());
                }
            }
            this.f3602e = string;
        }
        return ((e.a.C0073a) this.f3599a).a(packageName, this.f3602e, str);
    }
}
