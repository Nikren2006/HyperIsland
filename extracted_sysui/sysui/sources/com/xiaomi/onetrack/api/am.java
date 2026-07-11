package com.xiaomi.onetrack.api;

/* JADX INFO: loaded from: classes2.dex */
class am implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Thread f2882a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Throwable f2883b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final /* synthetic */ al f2884c;

    public am(al alVar, Thread thread, Throwable th) {
        this.f2884c = alVar;
        this.f2882a = thread;
        this.f2883b = th;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.f2884c.a(this.f2882a, this.f2883b);
    }
}
