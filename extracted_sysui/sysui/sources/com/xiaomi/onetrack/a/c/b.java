package com.xiaomi.onetrack.a.c;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.onetrack.b.n;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2736a = "AdMonitorUploadTimer";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile b f2737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f2738c;

    public static final class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            q.a(b.f2736a, "AdMonitorUploadTimer.handleMessage, msg.what=" + message.what);
            c.a();
        }

        public void a(int i2) {
            if (hasMessages(i2)) {
                q.a(b.f2736a, "has message\u3000prio=" + i2);
                return;
            }
            long jA = n.a(i2);
            q.a(b.f2736a, "will check prio=" + i2 + ", delay=" + jA);
            a(i2, jA);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i2, long j2) {
            removeMessages(i2);
            q.a(b.f2736a, "will post msg, prio=" + i2 + ", delay=" + j2);
            sendEmptyMessageDelayed(i2, j2);
        }
    }

    private b() {
        HandlerThread handlerThread = new HandlerThread("onetrack_ad_monitor_uploader");
        handlerThread.start();
        this.f2738c = new a(handlerThread.getLooper());
    }

    public static b a() {
        if (f2737b == null) {
            synchronized (b.class) {
                try {
                    if (f2737b == null) {
                        f2737b = new b();
                    }
                } finally {
                }
            }
        }
        return f2737b;
    }

    public void b() {
        this.f2738c.a(0);
    }

    public void a(boolean z2) {
        a aVar;
        if (!z2 || (aVar = this.f2738c) == null) {
            return;
        }
        aVar.a(0, 1000L);
    }
}
