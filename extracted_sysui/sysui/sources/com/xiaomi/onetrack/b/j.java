package com.xiaomi.onetrack.b;

/* JADX INFO: loaded from: classes2.dex */
class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3079a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ h f3080b;

    public j(h hVar, String str) {
        this.f3080b = hVar;
        this.f3079a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f3080b.f3073b.getWritableDatabase().delete(g.f3062b, "app_id=?", new String[]{this.f3079a});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
