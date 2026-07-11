package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f3231a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ v f3232b;

    public w(v vVar, boolean z2) {
        this.f3232b = vVar;
        this.f3231a = z2;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f3232b.a();
        try {
            if (this.f3231a) {
                int[] iArr = {0, 1, 2};
                for (int i2 = 0; i2 < 3; i2++) {
                    int i3 = iArr[i2];
                    int iA = com.xiaomi.onetrack.b.n.a(i3);
                    if (!this.f3232b.hasMessages(i3)) {
                        this.f3232b.sendEmptyMessageDelayed(i3, iA);
                    }
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.a("UploadTimer", "netReceiver error: " + e2);
        }
    }
}
