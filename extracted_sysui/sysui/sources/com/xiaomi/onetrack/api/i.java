package com.xiaomi.onetrack.api;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import com.xiaomi.onetrack.util.DeviceUtil;

/* JADX INFO: loaded from: classes2.dex */
class i implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f2976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2977b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f2979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2980e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2981f;

    public i(c cVar) {
        this.f2976a = cVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.f2976a.a(activity.getClass().getName(), this.f2978c == System.identityHashCode(activity) ? SystemClock.elapsedRealtime() - this.f2979d : 0L);
        if (com.xiaomi.onetrack.util.q.f3627a) {
            com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "onActivityPaused:" + activity.getLocalClassName());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f2976a.e(this.f2981f);
        this.f2978c = System.identityHashCode(activity);
        this.f2979d = SystemClock.elapsedRealtime();
        this.f2976a.a(activity.getClass().getName(), this.f2980e);
        if (com.xiaomi.onetrack.util.q.f3627a) {
            com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "onActivityResumed:" + activity.getLocalClassName() + " isAppStart:" + this.f2980e);
        }
        this.f2980e = false;
        this.f2976a.l();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (this.f2977b == 0) {
            this.f2976a.i().a(1);
            this.f2980e = true;
            this.f2981f = false;
            DeviceUtil.a();
        } else {
            this.f2980e = false;
        }
        this.f2977b++;
        com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "onActivityStarted: " + activity.getLocalClassName());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i2 = this.f2977b - 1;
        this.f2977b = i2;
        if (i2 == 0) {
            this.f2976a.i().a(2);
            this.f2976a.n();
            this.f2981f = true;
            this.f2980e = false;
        } else {
            this.f2981f = false;
        }
        this.f2976a.e(this.f2981f);
        com.xiaomi.onetrack.util.q.a("BaseOneTrackImp", "onActivityStopped: " + activity.getLocalClassName());
    }
}
