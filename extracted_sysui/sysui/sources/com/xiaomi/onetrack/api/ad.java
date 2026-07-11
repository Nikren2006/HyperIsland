package com.xiaomi.onetrack.api;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.xiaomi.onetrack.OneTrack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2772a = "BroadcastManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2773b = "onetrack_broadcast_manager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile ad f2774c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f2775e = 10;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f2776f = 100;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f2777g = 101;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static volatile boolean f2778h = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static volatile boolean f2779j = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f2780d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private CopyOnWriteArrayList<ak> f2781i = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private AtomicBoolean f2782k = new AtomicBoolean(false);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f2783l = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f2784m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private BroadcastReceiver f2785n = new ae(this);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private BroadcastReceiver f2786o = new af(this);

    public class a extends Handler {
        public /* synthetic */ a(ad adVar, Looper looper, ae aeVar) {
            this(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean zA;
            if (message == null) {
                return;
            }
            int i2 = message.what;
            if (i2 == 100 || i2 == 101) {
                try {
                    ad.this.a(i2);
                } catch (Exception e2) {
                    com.xiaomi.onetrack.util.q.a(ad.f2772a, "screenReceiver exception: ", e2);
                }
            }
            if (message.what == 10) {
                if (ad.this.f2782k.get()) {
                    try {
                        if (OneTrack.isRestrictGetNetworkInfo()) {
                            zA = com.xiaomi.onetrack.b.n.c();
                            com.xiaomi.onetrack.b.n.b(!zA);
                        } else {
                            zA = com.xiaomi.onetrack.g.c.a();
                            com.xiaomi.onetrack.b.n.b(zA);
                        }
                        com.xiaomi.onetrack.util.q.a(ad.f2772a, "Only one of allowed NetworkInfo :" + OneTrack.isRestrictGetNetworkInfo() + " ,network status changed, isNetworkConnected: " + com.xiaomi.onetrack.b.n.c());
                        if (zA) {
                            if (ad.this.f2783l) {
                                com.xiaomi.onetrack.a.c.b.a().a(com.xiaomi.onetrack.b.n.c());
                            }
                            if (ad.this.f2784m) {
                                com.xiaomi.onetrack.c.y.a().a(com.xiaomi.onetrack.b.n.c());
                            }
                        }
                    } catch (Throwable th) {
                        com.xiaomi.onetrack.util.q.b(ad.f2772a, "MESSAGE_BROADCAST_NET_RECEIVER throwable:" + th.getMessage());
                    }
                }
                ad.this.f2782k.set(true);
            }
        }

        private a(Looper looper) {
            super(looper);
        }
    }

    private ad() {
        try {
            HandlerThread handlerThread = new HandlerThread(f2773b);
            handlerThread.start();
            this.f2780d = new a(this, handlerThread.getLooper(), null);
        } catch (Throwable unused) {
        }
    }

    private void g() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        com.xiaomi.onetrack.f.a.b().registerReceiver(this.f2785n, intentFilter);
        Log.d(com.xiaomi.onetrack.util.q.a(f2772a), "register screen receiver");
    }

    private void h() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        com.xiaomi.onetrack.f.a.b().registerReceiver(this.f2786o, intentFilter);
        Log.d(com.xiaomi.onetrack.util.q.a(f2772a), "register net receiver");
    }

    public void e() {
        if (f2778h) {
            return;
        }
        f2778h = true;
        try {
            g();
        } catch (Throwable unused) {
            f2778h = false;
        }
    }

    public void f() {
        if (f2779j) {
            return;
        }
        f2779j = true;
        boolean zB = com.xiaomi.onetrack.g.c.b();
        com.xiaomi.onetrack.util.q.a(f2772a, "Get network status for the first time, isNetworkConnected: " + zB);
        com.xiaomi.onetrack.b.n.b(zB);
        try {
            h();
        } catch (Throwable unused) {
            f2779j = false;
        }
    }

    public static void b() {
        if (f2774c == null) {
            synchronized (ad.class) {
                try {
                    if (f2774c == null) {
                        f2774c = new ad();
                    }
                } finally {
                }
            }
        }
    }

    public void c() {
        this.f2783l = true;
    }

    public void d() {
        this.f2784m = true;
    }

    public static ad a() {
        if (f2774c == null) {
            b();
        }
        return f2774c;
    }

    public void a(ak akVar) {
        if (this.f2781i.contains(akVar)) {
            return;
        }
        this.f2781i.add(akVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i2) {
        for (ak akVar : this.f2781i) {
            if (i2 == 100) {
                akVar.a(true);
            } else if (i2 == 101) {
                akVar.a(false);
            }
        }
    }
}
