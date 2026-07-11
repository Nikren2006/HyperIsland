package com.xiaomi.onetrack.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import j0.InterfaceC0412a;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2905a = "com.miui.analytics";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2906b = "com.miui.analytics.onetrack.OneTrackService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f2907c = "ServiceConnectManager";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f2908h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f2909i = 2;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static volatile at f2910k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static String f2911l = "onetrack_service_connect";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile InterfaceC0412a f2912d;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private b f2916j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private AtomicBoolean f2913e = new AtomicBoolean(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private AtomicBoolean f2914f = new AtomicBoolean(false);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private AtomicBoolean f2917m = new AtomicBoolean(false);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private ServiceConnection f2918n = new ServiceConnection() { // from class: com.xiaomi.onetrack.api.ServiceConnectionManager$1
        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            try {
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onBindingDied");
                try {
                    this.f2740a.f2915g.unbindService(this.f2740a.f2918n);
                } catch (Exception e2) {
                    com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onBindingDied: " + e2.toString());
                }
                this.f2740a.c();
            } catch (Throwable th) {
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onBindingDied throwable:" + th.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            try {
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onNullBinding");
                this.f2740a.c();
            } catch (Throwable th) {
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onNullBinding throwable:" + th.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f2740a.f2912d = InterfaceC0412a.AbstractBinderC0087a.Z0(iBinder);
                this.f2740a.f2914f.set(true);
                this.f2740a.f2913e.set(false);
                this.f2740a.g();
                StringBuilder sb = new StringBuilder();
                sb.append("onServiceConnected  mConnecting ");
                sb.append(this.f2740a.f2913e);
                sb.append(" mBindResult:");
                sb.append(this.f2740a.f2914f);
                sb.append(" mIOneTrackService ");
                sb.append(this.f2740a.f2912d == null ? 0 : 1);
                sb.append(" pid:");
                sb.append(Process.myPid());
                sb.append(" tid:");
                sb.append(Process.myTid());
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", sb.toString());
            } catch (Throwable th) {
                this.f2740a.c();
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onServiceConnected throwable:" + th.getMessage());
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                this.f2740a.c();
                StringBuilder sb = new StringBuilder();
                sb.append("onServiceDisconnected:  mConnecting ");
                sb.append(this.f2740a.f2913e);
                sb.append(" mIOneTrackService ");
                sb.append(this.f2740a.f2912d == null ? 0 : 1);
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", sb.toString());
            } catch (Throwable th) {
                com.xiaomi.onetrack.util.q.a("ServiceConnectManager", "onServiceDisconnected throwable:" + th.getMessage());
            }
        }
    };

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private CopyOnWriteArrayList<a> f2919o = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f2915g = com.xiaomi.onetrack.f.a.b();

    public interface a {
        void a();
    }

    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1) {
                at.this.f();
            } else if (i2 == 2) {
                at.this.d();
            }
        }
    }

    private at() {
        HandlerThread handlerThread = new HandlerThread(f2911l);
        handlerThread.start();
        b bVar = new b(handlerThread.getLooper());
        this.f2916j = bVar;
        bVar.sendEmptyMessage(2);
    }

    public static void b() {
        if (f2910k == null) {
            synchronized (at.class) {
                try {
                    if (f2910k == null) {
                        f2910k = new at();
                    }
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f2912d = null;
        this.f2914f.set(false);
        this.f2913e.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!this.f2913e.get() && !this.f2914f.get() && this.f2912d == null) {
            e();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ensureService mConnecting: ");
        sb.append(this.f2913e.get());
        sb.append(" mIsBindSuccess:");
        sb.append(this.f2914f.get());
        sb.append(" mAnalytics: ");
        sb.append(this.f2912d == null ? 0 : 1);
        com.xiaomi.onetrack.util.q.a(f2907c, sb.toString());
    }

    private void e() {
        this.f2913e.set(true);
        try {
            Intent intent = new Intent();
            intent.setClassName(f2905a, f2906b);
            boolean zBindService = this.f2915g.bindService(intent, this.f2918n, 1);
            if (zBindService) {
                this.f2914f.set(true);
            } else {
                this.f2914f.set(false);
                try {
                    this.f2915g.unbindService(this.f2918n);
                } catch (Throwable th) {
                    Log.d(f2907c, "unbindService e1: " + th.getMessage());
                }
            }
            com.xiaomi.onetrack.util.q.a(f2907c, "bindService:  mConnecting: " + this.f2913e + " bindResult:" + zBindService);
        } catch (Throwable th2) {
            try {
                this.f2914f.set(false);
                this.f2913e.set(false);
                this.f2915g.unbindService(this.f2918n);
            } catch (Throwable th3) {
                Log.d(f2907c, "bindService e1: " + th3.getMessage());
            }
            com.xiaomi.onetrack.util.q.b(f2907c, "bindService e: " + th2.getMessage());
        }
        this.f2913e.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        try {
            if (this.f2914f.get()) {
                this.f2915g.unbindService(this.f2918n);
            }
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.a(f2907c, "unBindService Throwable: " + th.getMessage());
        }
        c();
        com.xiaomi.onetrack.util.q.a(f2907c, "unBindService  mIsBindSuccess:" + this.f2914f.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Iterator<a> it = this.f2919o.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
    }

    public static at a() {
        if (f2910k == null) {
            b();
        }
        return f2910k;
    }

    public boolean a(String str, String str2, String str3) {
        if (this.f2913e.get()) {
            return false;
        }
        if (this.f2912d != null) {
            try {
                this.f2912d.O(str3, com.xiaomi.onetrack.f.a.e(), str, str2);
                return true;
            } catch (Throwable th) {
                f();
                com.xiaomi.onetrack.util.q.a(f2907c, "track throwable: " + th.getMessage());
                return false;
            }
        }
        this.f2916j.sendEmptyMessage(2);
        return false;
    }

    public boolean a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (this.f2913e.get()) {
            return false;
        }
        if (this.f2912d != null) {
            try {
                this.f2912d.y0(str, str2, str3, str4, com.xiaomi.onetrack.f.a.e(), str5, str6, str7);
                return true;
            } catch (Throwable th) {
                f();
                com.xiaomi.onetrack.util.q.a(f2907c, "track throwable: " + th.getMessage());
                return false;
            }
        }
        this.f2916j.sendEmptyMessage(2);
        return false;
    }

    public void a(a aVar) {
        if (this.f2919o.contains(aVar)) {
            return;
        }
        this.f2919o.add(aVar);
    }

    public void a(int i2) {
        if (i2 == 2) {
            this.f2916j.sendEmptyMessageDelayed(1, 5000L);
        } else if (this.f2916j.hasMessages(1)) {
            this.f2916j.removeMessages(1);
        }
    }
}
