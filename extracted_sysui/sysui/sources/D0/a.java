package D0;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class a implements ServiceConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public IBinder f59a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f60b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Intent f61c;

    public a(Context context, Intent intent) {
        this.f60b = context;
        this.f61c = intent;
        Log.i("BlockServiceConnect", "BlockServiceConnect create");
    }

    public IBinder a() {
        synchronized (this) {
            try {
                if (this.f60b == null || this.f61c == null) {
                    throw new IllegalStateException("BlockServiceConnect stop");
                }
                IBinder iBinder = this.f59a;
                if (iBinder != null && iBinder.isBinderAlive()) {
                    return this.f59a;
                }
                for (int i2 = 0; i2 < 10; i2++) {
                    if (!this.f60b.bindService(this.f61c, this, 75497473)) {
                        throw new IllegalArgumentException("BlockServiceConnect");
                    }
                    try {
                        wait(1000L);
                    } catch (InterruptedException unused) {
                        Log.i("BlockServiceConnect", "getService InterruptedException");
                    }
                    if (this.f60b == null || this.f61c == null) {
                        throw new IllegalStateException("BlockServiceConnect times " + i2);
                    }
                    if (this.f59a != null) {
                        Log.i("BlockServiceConnect", "getService wait 1000 " + i2);
                        return this.f59a;
                    }
                }
                throw new IllegalStateException("bindService");
            } finally {
            }
        }
    }

    public void b() {
        Context context = this.f60b;
        Intent intent = this.f61c;
        synchronized (this) {
            this.f60b = null;
            this.f61c = null;
            this.f59a = null;
            notifyAll();
        }
        if (context == null || intent == null) {
            return;
        }
        Log.i("BlockServiceConnect", "unbindService");
        try {
            context.unbindService(this);
        } catch (Exception unused) {
        }
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        Log.i("BlockServiceConnect", "onBindingDied");
        synchronized (this) {
            this.f59a = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        Log.i("BlockServiceConnect", "onNullBinding");
        synchronized (this) {
            this.f59a = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i("BlockServiceConnect", "onServiceConnected");
        synchronized (this) {
            this.f59a = iBinder;
            notifyAll();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i("BlockServiceConnect", "onServiceDisconnected");
        synchronized (this) {
            this.f59a = null;
        }
    }
}
