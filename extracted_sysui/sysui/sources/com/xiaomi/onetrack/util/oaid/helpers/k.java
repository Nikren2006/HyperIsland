package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import com.xiaomi.onetrack.util.oaid.a.f;
import com.xiaomi.onetrack.util.q;
import java.security.MessageDigest;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3603d = "OppoDeviceIDHelper";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    com.xiaomi.onetrack.util.oaid.a.f f3604a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedBlockingQueue<IBinder> f3605b = new LinkedBlockingQueue<>(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ServiceConnection f3606c = new ServiceConnection() { // from class: com.xiaomi.onetrack.util.oaid.helpers.OppoDeviceIDHelper$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f3552a.f3605b.offer(iBinder, 1L, TimeUnit.SECONDS);
            } catch (Exception e2) {
                q.a("OppoDeviceIDHelper", e2.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.f3552a.f3604a = null;
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3607e;

    private boolean b(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            if (packageInfo == null) {
                return false;
            }
            return ((long) packageInfo.versionCode) >= 1;
        } catch (Exception e2) {
            q.a(f3603d, e2.getMessage());
            return true;
        }
    }

    public String a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        String strA = "";
        try {
            if (context.bindService(intent, this.f3606c, 1)) {
                try {
                    try {
                        IBinder iBinderPoll = this.f3605b.poll(1L, TimeUnit.SECONDS);
                        if (iBinderPoll == null) {
                            return "";
                        }
                        com.xiaomi.onetrack.util.oaid.a.f fVarA = f.a.a(iBinderPoll);
                        this.f3604a = fVarA;
                        if (fVarA != null) {
                            strA = a("OUID", context);
                        }
                        context.unbindService(this.f3606c);
                    } catch (Exception e2) {
                        q.a(f3603d, e2.getMessage());
                        context.unbindService(this.f3606c);
                    }
                } finally {
                    try {
                        context.unbindService(this.f3606c);
                    } catch (Exception e3) {
                        q.a(f3603d, e3.getMessage());
                    }
                }
            }
        } catch (Exception e4) {
            q.a(f3603d, e4.getMessage());
        }
        return strA;
    }

    private String a(String str, Context context) {
        Signature[] signatureArr;
        String packageName = context.getPackageName();
        if (this.f3607e == null) {
            String string = null;
            try {
                signatureArr = context.getPackageManager().getPackageInfo(packageName, 64).signatures;
            } catch (Exception e2) {
                e2.printStackTrace();
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
                    e3.printStackTrace();
                }
            }
            this.f3607e = string;
        }
        return ((f.a.C0074a) this.f3604a).a(packageName, this.f3607e, str);
    }
}
