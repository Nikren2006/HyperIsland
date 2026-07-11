package com.xiaomi.onetrack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.xiaomi.onetrack.util.oaid.a;
import com.xiaomi.onetrack.util.p;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class OneTrackDebugger {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile OneTrackDebugger f2647a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2648b = "com.xiaomi.onetrack.otdebugger.FloatWindowService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<Long, Configuration> f2649c = new ConcurrentHashMap<>();

    private OneTrackDebugger() {
    }

    public static OneTrackDebugger getInstance() {
        if (f2647a == null) {
            synchronized (OneTrackDebugger.class) {
                try {
                    if (f2647a == null) {
                        f2647a = new OneTrackDebugger();
                    }
                } finally {
                }
            }
        }
        return f2647a;
    }

    public String getInstanceId() {
        return p.a().b();
    }

    public String getOaid(Context context) {
        return a.a().a(context.getApplicationContext());
    }

    public ConcurrentHashMap<Long, Configuration> getSdkConfig() {
        return this.f2649c;
    }

    public void setSdkConfig(Configuration configuration) {
        this.f2649c.put(Long.valueOf(System.currentTimeMillis()), configuration);
    }

    public void startDebugger() {
        try {
            com.xiaomi.onetrack.f.a.b().startService(new Intent(com.xiaomi.onetrack.f.a.b(), Class.forName(f2648b)));
        } catch (Throwable th) {
            Log.d("startDebugger", th.getMessage());
        }
    }
}
