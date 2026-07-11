package m0;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import com.miui.miplay.audio.data.DeviceInfo;
import com.miui.miplay.audio.data.MediaMetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import l0.C0439b;
import l0.C0440c;
import l0.InterfaceC0441d;
import l0.InterfaceC0442e;
import l0.InterfaceC0444g;
import z0.h;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5299a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final DeviceInfo f5300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5301c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC0441d f5303e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public w f5304f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f5305g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f5306h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final z0.h f5307i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5308j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List f5309k;

    public static final class b extends x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final y f5310b;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f5335a) {
                int i2 = message.what;
                if (i2 == 1) {
                    this.f5310b.onDeviceInfoChange((DeviceInfo) message.obj);
                    return;
                }
                if (i2 == 2) {
                    this.f5310b.onDeviceConnectionStateChange(((Integer) message.obj).intValue(), message.arg1);
                    return;
                }
                if (i2 == 3) {
                    this.f5310b.onDeviceSelectStatusChange(((Integer) message.obj).intValue(), message.arg1);
                } else if (i2 != 4) {
                    super.handleMessage(message);
                } else {
                    this.f5310b.onVolumeChange(((Integer) message.obj).intValue(), message.arg1);
                }
            }
        }

        public b(y yVar, Handler handler) {
            super(handler);
            this.f5310b = yVar;
        }
    }

    public static final class c extends InterfaceC0442e.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f5311a;

        @Override // l0.InterfaceC0442e
        public void onDeviceConnectionStateChange(int i2, int i3) {
            i iVar = (i) this.f5311a.get();
            if (iVar != null) {
                iVar.f(2, Integer.valueOf(i2), i3);
            }
        }

        @Override // l0.InterfaceC0442e
        public void onDeviceInfoChange(DeviceInfo deviceInfo) {
            i iVar = (i) this.f5311a.get();
            if (iVar != null) {
                iVar.f(1, deviceInfo, -1);
            }
        }

        @Override // l0.InterfaceC0442e
        public void onDeviceSelectStatusChange(int i2, int i3) {
            i iVar = (i) this.f5311a.get();
            if (iVar != null) {
                iVar.f(3, Integer.valueOf(i2), i3);
            }
        }

        @Override // l0.InterfaceC0442e
        public void onVolumeChange(int i2, int i3) {
            i iVar = (i) this.f5311a.get();
            if (iVar != null) {
                iVar.f(4, Integer.valueOf(i2), i3);
            }
        }

        public c(i iVar) {
            this.f5311a = new WeakReference(iVar);
        }
    }

    public i(C0439b c0439b) {
        this.f5304f = null;
        this.f5305g = new c();
        this.f5306h = new Object();
        this.f5307i = new z0.h();
        this.f5309k = new ArrayList();
        this.f5299a = c0439b.r();
        this.f5300b = c0439b.s();
        this.f5303e = c0439b.q();
        this.f5301c = c0439b.t();
        this.f5302d = 0;
    }

    public int e() {
        z0.h hVar = this.f5307i;
        final InterfaceC0441d interfaceC0441d = this.f5303e;
        Objects.requireNonNull(interfaceC0441d);
        return ((Integer) hVar.a("AudioDevice", "cancelSelectDevice", -101, new h.b() { // from class: m0.g
            @Override // z0.h.b
            public final Object invoke() {
                return Integer.valueOf(interfaceC0441d.q());
            }
        })).intValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f5299a.equals(((i) obj).f5299a);
    }

    public void f(int i2, Object obj, int i3) {
        synchronized (this.f5306h) {
            try {
                for (int size = this.f5309k.size() - 1; size >= 0; size--) {
                    ((b) this.f5309k.get(size)).b(i2, obj, i3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final b g(y yVar) {
        for (int size = this.f5309k.size() - 1; size >= 0; size--) {
            b bVar = (b) this.f5309k.get(size);
            if (bVar.f5310b == yVar) {
                return bVar;
            }
        }
        return null;
    }

    public int h() {
        z0.h hVar = this.f5307i;
        final InterfaceC0441d interfaceC0441d = this.f5303e;
        Objects.requireNonNull(interfaceC0441d);
        return ((Integer) hVar.a("AudioDevice", "getDeviceConnectionState", 0, new h.b() { // from class: m0.b
            @Override // z0.h.b
            public final Object invoke() {
                return Integer.valueOf(interfaceC0441d.i0());
            }
        })).intValue();
    }

    public int hashCode() {
        return Objects.hash(this.f5299a);
    }

    public int i(final int i2) {
        return ((Integer) this.f5307i.a("AudioDevice", "getDeviceConnectionState", 0, new h.b() { // from class: m0.d
            @Override // z0.h.b
            public final Object invoke() {
                return this.f5288a.p(i2);
            }
        })).intValue();
    }

    public String j() {
        return this.f5299a;
    }

    public DeviceInfo k() {
        return this.f5300b;
    }

    public int l() {
        return this.f5301c;
    }

    public int m(int i2) {
        return i2 == 0 ? this.f5301c : this.f5302d;
    }

    public w n() {
        InterfaceC0444g aVar;
        if (this.f5304f == null) {
            try {
                aVar = this.f5303e.T0();
            } catch (RemoteException unused) {
                z0.e.d("AudioDevice", "get device media controller error, use default controller");
                aVar = new InterfaceC0444g.a();
            }
            this.f5304f = new w(aVar);
        }
        return this.f5304f;
    }

    public int o() {
        z0.h hVar = this.f5307i;
        final InterfaceC0441d interfaceC0441d = this.f5303e;
        Objects.requireNonNull(interfaceC0441d);
        return ((Integer) hVar.a("AudioDevice", "getVolume", -102, new h.b() { // from class: m0.c
            @Override // z0.h.b
            public final Object invoke() {
                return Integer.valueOf(interfaceC0441d.getVolume());
            }
        })).intValue();
    }

    public final /* synthetic */ Integer p(int i2) {
        return Integer.valueOf(this.f5303e.D0(i2));
    }

    public final /* synthetic */ Integer q(int i2, MediaMetaData mediaMetaData) {
        return Integer.valueOf(this.f5303e.W0(i2, mediaMetaData));
    }

    public final /* synthetic */ Integer r(int i2) {
        return Integer.valueOf(this.f5303e.y(i2));
    }

    public final /* synthetic */ void s(int i2, int i3) {
        this.f5303e.setStreamVolume(i2, i3);
    }

    public boolean t() {
        IBinder iBinderAsBinder = this.f5303e.asBinder();
        if (iBinderAsBinder == null) {
            return false;
        }
        return iBinderAsBinder.pingBinder();
    }

    public void u(y yVar, Handler handler) {
        synchronized (this.f5306h) {
            try {
                if (g(yVar) != null) {
                    z0.e.d("AudioDevice", "this DeviceChangeListener has been register");
                    return;
                }
                if (handler == null) {
                    handler = new Handler();
                }
                b bVar = new b(yVar, handler);
                this.f5309k.add(bVar);
                bVar.f5335a = true;
                if (!this.f5308j) {
                    try {
                        this.f5303e.e0(this.f5305g);
                        this.f5308j = true;
                    } catch (RemoteException e2) {
                        z0.e.b("AudioDevice", "registerDeviceChangeListener binder call error", e2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int v(final int i2, final MediaMetaData mediaMetaData) {
        return ((Integer) this.f5307i.a("AudioDevice", "selectDeviceWithMetaData", -102, new h.b() { // from class: m0.h
            @Override // z0.h.b
            public final Object invoke() {
                return this.f5296a.q(i2, mediaMetaData);
            }
        })).intValue();
    }

    public int w(final int i2) {
        return ((Integer) this.f5307i.a("AudioDevice", "selectDeviceWithType", -100, new h.b() { // from class: m0.f
            @Override // z0.h.b
            public final Object invoke() {
                return this.f5293a.r(i2);
            }
        })).intValue();
    }

    public void x(final int i2, final int i3) {
        this.f5307i.b("AudioDevice", "setVolume", new h.a() { // from class: m0.e
            @Override // z0.h.a
            public final void invoke() {
                this.f5290a.s(i2, i3);
            }
        });
    }

    public boolean y(y yVar) {
        boolean z2;
        synchronized (this.f5306h) {
            try {
                z2 = false;
                for (int size = this.f5309k.size() - 1; size >= 0; size--) {
                    b bVar = (b) this.f5309k.get(size);
                    if (yVar == bVar.f5310b) {
                        this.f5309k.remove(size);
                        bVar.f5335a = false;
                        z2 = true;
                    }
                }
                if (this.f5308j && this.f5309k.size() == 0) {
                    try {
                        this.f5303e.n0(this.f5305g);
                    } catch (RemoteException e2) {
                        z0.e.b("AudioDevice", "unregisterDeviceChangeListener binder call error", e2);
                    }
                    this.f5308j = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public i(C0440c c0440c) {
        this.f5304f = null;
        this.f5305g = new c();
        this.f5306h = new Object();
        this.f5307i = new z0.h();
        this.f5309k = new ArrayList();
        this.f5299a = c0440c.r();
        this.f5300b = c0440c.s();
        this.f5303e = c0440c.q();
        this.f5301c = c0440c.t();
        this.f5302d = c0440c.v();
    }
}
