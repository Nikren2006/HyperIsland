package m0;

/* JADX INFO: loaded from: classes2.dex */
public class E implements InterfaceC0463A {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static volatile E f5272f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f5273a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5274b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5275c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5276d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5277e = false;

    public static E g() {
        if (f5272f == null) {
            synchronized (E.class) {
                try {
                    if (f5272f == null) {
                        f5272f = new E();
                    }
                } finally {
                }
            }
        }
        return f5272f;
    }

    @Override // m0.InterfaceC0463A
    public void a(boolean z2) {
        if (z2) {
            z0.e.c("MiPlayServiceAdapter", "markVideoSessionExist");
            h(1);
        } else {
            z0.e.c("MiPlayServiceAdapter", "markVideoKilled");
            i(1);
        }
    }

    @Override // m0.InterfaceC0463A
    public void b(boolean z2) {
        z0.e.c("MiPlayServiceAdapter", "markAudioSharedChange, isInAudioShare:" + z2);
        if (z2) {
            h(4);
        } else {
            i(4);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x001a  */
    @Override // m0.InterfaceC0463A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean c() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.f5273a     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L1a
            boolean r0 = r1.f5274b     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L1a
            boolean r0 = r1.f5275c     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L1a
            boolean r0 = r1.f5276d     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L1a
            boolean r0 = r1.f5277e     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L16
            goto L1a
        L16:
            r0 = 0
            goto L1b
        L18:
            r0 = move-exception
            goto L1d
        L1a:
            r0 = 1
        L1b:
            monitor-exit(r1)
            return r0
        L1d:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L18
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: m0.E.c():boolean");
    }

    @Override // m0.InterfaceC0463A
    public void d(boolean z2) {
        if (z2) {
            z0.e.c("MiPlayServiceAdapter", "markSessionChange");
            h(0);
        } else {
            z0.e.c("MiPlayServiceAdapter", "markSessionKilled");
            i(0);
        }
    }

    @Override // m0.InterfaceC0463A
    public void e(boolean z2) {
        if (z2) {
            z0.e.c("MiPlayServiceAdapter", "markMiPlayProjectionStart");
            h(3);
        } else {
            z0.e.c("MiPlayServiceAdapter", "markMIPlayProjectionStop");
            i(3);
        }
    }

    @Override // m0.InterfaceC0463A
    public void f(boolean z2) {
        if (z2) {
            z0.e.c("MiPlayServiceAdapter", "markUIStart");
            h(2);
        } else {
            z0.e.c("MiPlayServiceAdapter", "markUIStop");
            i(2);
        }
    }

    public final synchronized void h(int i2) {
        try {
            z0.e.c("MiPlayServiceAdapter", "intent to startService : " + i2);
            if (i2 == 0) {
                this.f5273a = true;
            } else if (i2 == 1) {
                this.f5274b = true;
            } else if (i2 == 2) {
                this.f5275c = true;
            } else if (i2 == 3) {
                this.f5276d = true;
            } else if (i2 == 4) {
                this.f5277e = true;
            }
        } finally {
        }
    }

    public final synchronized void i(int i2) {
        try {
            if (i2 == 0) {
                this.f5273a = false;
            } else if (i2 == 1) {
                this.f5274b = false;
            } else if (i2 == 2) {
                this.f5275c = false;
            } else if (i2 == 3) {
                this.f5276d = false;
            } else if (i2 == 4) {
                this.f5277e = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
