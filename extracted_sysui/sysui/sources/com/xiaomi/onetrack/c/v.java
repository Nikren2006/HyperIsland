package com.xiaomi.onetrack.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class v extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3222a = "UploadTimer";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3223b = 5000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3224c = 15000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f3225d = 1200000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3226e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3227f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3228g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3229h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private AtomicBoolean f3230i;

    public v(Looper looper) {
        super(looper);
        this.f3226e = 1000;
        this.f3227f = 10000;
        this.f3228g = f3225d;
        this.f3229h = 10000;
        this.f3230i = new AtomicBoolean(false);
    }

    private void b() {
        if (y.a().a(2)) {
            this.f3229h = 10000;
            com.xiaomi.onetrack.util.q.a(f3222a, "retry success");
            return;
        }
        removeMessages(1000);
        int i2 = this.f3229h * 2;
        this.f3229h = i2;
        if (i2 > f3225d) {
            this.f3229h = f3225d;
        }
        com.xiaomi.onetrack.util.q.a(f3222a, "will restart retry msg after " + this.f3229h);
        sendEmptyMessageDelayed(1000, (long) this.f3229h);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i2 = message.what;
        if (i2 == 1000) {
            b();
            return;
        }
        boolean zA = y.a().a(i2);
        com.xiaomi.onetrack.util.q.a(f3222a, "handleCheckUpload ret=" + zA + ", prio=" + i2);
        if (zA) {
            return;
        }
        com.xiaomi.onetrack.util.q.a(f3222a, "handleCheckUpload failed, will check if need to send retry msg");
        if (hasMessages(1000)) {
            return;
        }
        sendEmptyMessageDelayed(1000, this.f3229h);
        com.xiaomi.onetrack.util.q.a(f3222a, "fire retry timer after " + this.f3229h);
    }

    public void a(int i2, boolean z2) {
        if (hasMessages(1000)) {
            com.xiaomi.onetrack.util.q.a(f3222a, "in retry mode, return, prio=" + i2);
            return;
        }
        if (z2) {
            removeMessages(i2);
        }
        if (hasMessages(i2)) {
            return;
        }
        long jA = z2 ? 0L : com.xiaomi.onetrack.b.n.a(i2);
        com.xiaomi.onetrack.util.q.a(f3222a, "will check prio=" + i2 + ", delay=" + jA);
        a(i2, jA);
    }

    private void a(int i2, long j2) {
        removeMessages(i2);
        com.xiaomi.onetrack.util.q.a(f3222a, "will post msg, prio=" + i2 + ", delay=" + j2);
        sendEmptyMessageDelayed(i2, j2);
    }

    public void a(boolean z2) {
        a.a(new w(this, z2));
    }

    public void a() {
        com.xiaomi.onetrack.util.i.a(new x(this));
    }
}
