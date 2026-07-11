package com.xiaomi.onetrack.c;

/* JADX INFO: loaded from: classes2.dex */
class x implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ v f3233a;

    public x(v vVar) {
        this.f3233a = vVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3233a.f3230i.get()) {
            com.xiaomi.onetrack.b.e.b();
        }
        this.f3233a.f3230i.set(true);
    }
}
