package m0;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import n0.AbstractC0712a;
import x0.C0770A;
import x0.C0771a;

/* JADX INFO: renamed from: m0.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0465C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5266a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f5267b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f5268c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z f5269d;

    /* JADX INFO: renamed from: m0.C$b */
    public static final class b extends s0.m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f5270a;

        @Override // s0.m
        public void a(int i2, Object obj) {
            C0465C c0465c = (C0465C) this.f5270a.get();
            if (c0465c == null) {
                return;
            }
            c0465c.b(i2, obj);
        }

        public b(C0465C c0465c) {
            this.f5270a = new WeakReference(c0465c);
        }
    }

    /* JADX INFO: renamed from: m0.C$c */
    public static final class c extends x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final D f5271b;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f5335a) {
                switch (message.what) {
                    case 1:
                        this.f5271b.onActiveAudioSessionChange((List) message.obj);
                        break;
                    case 2:
                        this.f5271b.onAudioDeviceListChange((List) message.obj);
                        break;
                    case 3:
                        this.f5271b.onServiceStateChange(((Integer) message.obj).intValue());
                        break;
                    case 4:
                        this.f5271b.onProjectionStateChange(((Integer) message.obj).intValue());
                        break;
                    case 5:
                        C0771a c0771a = (C0771a) message.obj;
                        this.f5271b.onError(c0771a.f7038a, c0771a.f7039b);
                        break;
                    case 6:
                        this.f5271b.onCastStateChange(((Boolean) message.obj).booleanValue());
                        break;
                    case 7:
                        this.f5271b.onAudioParingStateChange();
                        break;
                    case 8:
                        this.f5271b.onAudioShareFinish();
                        break;
                    case 9:
                        x0.z zVar = (x0.z) message.obj;
                        this.f5271b.onVideoCastModeChange(zVar.f7094a, zVar.f7095b);
                        break;
                    case 11:
                        C0770A c0770a = (C0770A) message.obj;
                        this.f5271b.onVideoCpAppStateChange(c0770a.f7036a, c0770a.f7037b);
                        break;
                    case 12:
                        this.f5271b.onDeviceStartPlaying((Bundle) message.obj);
                        break;
                    case 13:
                        this.f5271b.onBluetoothDeviceConnectFail((String) message.obj);
                        break;
                    case 14:
                        this.f5271b.onBluetoothDeviceConnectSuccess((String) message.obj);
                        break;
                }
                super.handleMessage(message);
            }
        }

        public c(D d2, Handler handler) {
            super(handler);
            this.f5271b = d2;
        }
    }

    public C0465C(Context context) {
        this.f5269d = AbstractC0712a.a(context, true);
    }

    public static boolean r(Context context) {
        if (z0.g.a()) {
            return false;
        }
        return x0.y.c(context);
    }

    public final void b(int i2, Object obj) {
        synchronized (this.f5266a) {
            try {
                for (int size = this.f5267b.size() - 1; size >= 0; size--) {
                    ((c) this.f5267b.get(size)).a(i2, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final c c(D d2) {
        for (int size = this.f5267b.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f5267b.get(size);
            if (cVar.f5271b == d2) {
                return cVar;
            }
        }
        return null;
    }

    public int d(int i2) {
        return this.f5269d.a(i2);
    }

    public int e() {
        return this.f5269d.k();
    }

    public void f(Context context) {
        this.f5269d.h(context, true);
    }

    public void g(int i2) {
        this.f5269d.j(i2);
    }

    public void h(int i2) {
        this.f5269d.b(i2);
    }

    public List i() {
        return this.f5269d.l();
    }

    public void j(int i2) {
        this.f5269d.m(i2);
    }

    public void k() {
        this.f5269d.i();
    }

    public void l(D d2, Handler handler) {
        synchronized (this.f5266a) {
            try {
                if (c(d2) != null) {
                    z0.e.d("MiPlayAudioManager", "this MiPlayServiceCallback has been registered");
                    return;
                }
                if (handler == null) {
                    handler = new Handler();
                }
                c cVar = new c(d2, handler);
                this.f5267b.add(cVar);
                cVar.f5335a = true;
                if (this.f5267b.size() == 1) {
                    this.f5269d.o(this.f5268c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void m() {
        z0.e.c("MiPlayAudioManager", "release");
        synchronized (this.f5266a) {
            this.f5267b.clear();
        }
        this.f5269d.release();
    }

    public boolean n() {
        return this.f5269d.e();
    }

    public boolean o(int i2) {
        z0.e.c("MiPlayAudioManager", "scanDeviceV2 " + i2);
        return this.f5269d.g(i2);
    }

    public boolean p() {
        return this.f5269d.f();
    }

    public boolean q() {
        return this.f5269d.d();
    }
}
