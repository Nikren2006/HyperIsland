package m0;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.miui.miplay.audio.data.AdvertisementParam;
import com.miui.miplay.audio.data.MediaMetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import l0.InterfaceC0443f;
import l0.InterfaceC0444g;
import z0.h;

/* JADX INFO: loaded from: classes2.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0444g f5327a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5331e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f5328b = new b(this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f5329c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z0.h f5330d = new z0.h();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f5332f = new ArrayList();

    public static final class b extends InterfaceC0443f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f5333a;

        public b(w wVar) {
            this.f5333a = new WeakReference(wVar);
        }

        @Override // l0.InterfaceC0443f
        public void H(int i2, String str) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.s(110, str, i2);
            }
        }

        @Override // l0.InterfaceC0443f
        public void L0(int i2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(104, Integer.valueOf(i2));
            }
        }

        @Override // l0.InterfaceC0443f
        public void O0(List list) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(109, wVar.z(list));
            }
        }

        @Override // l0.InterfaceC0443f
        public void V0(float f2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(105, Float.valueOf(f2));
            }
        }

        @Override // l0.InterfaceC0443f
        public void h0(int i2, String str, int i3) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(108, new AdvertisementParam(i2, str, i3));
            }
        }

        @Override // l0.InterfaceC0443f
        public void onBufferStateChange(int i2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(103, Integer.valueOf(i2));
            }
        }

        @Override // l0.InterfaceC0443f
        public void onCastModeChange(int i2, int i3) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.s(106, Integer.valueOf(i2), i3);
            }
        }

        @Override // l0.InterfaceC0443f
        public void onCpStateChange(String str, int i2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.s(107, str, i2);
            }
        }

        @Override // l0.InterfaceC0443f
        public void onMediaMetaChange(MediaMetaData mediaMetaData) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(101, mediaMetaData);
            }
        }

        @Override // l0.InterfaceC0443f
        public void onPlaybackStateChange(int i2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(100, Integer.valueOf(i2));
            }
        }

        @Override // l0.InterfaceC0443f
        public void onPositionChange(long j2) {
            w wVar = (w) this.f5333a.get();
            if (wVar != null) {
                wVar.r(102, Long.valueOf(j2));
            }
        }
    }

    public static class c extends x {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final InterfaceC0464B f5334b;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f5335a) {
                switch (message.what) {
                    case 100:
                        this.f5334b.onPlaybackStateChange(((Integer) message.obj).intValue());
                        break;
                    case 101:
                        this.f5334b.onMediaMetaChange((MediaMetaData) message.obj);
                        break;
                    case 102:
                        this.f5334b.onPositionChange(((Long) message.obj).longValue());
                        break;
                    case 103:
                        this.f5334b.onBufferStateChange(((Integer) message.obj).intValue());
                        break;
                    case 105:
                        this.f5334b.onPlaySpeedChange(((Float) message.obj).floatValue());
                        break;
                    case 106:
                        this.f5334b.onCastModeChange(((Integer) message.obj).intValue(), message.arg1);
                        break;
                    case 107:
                        this.f5334b.onCpStateChange((String) message.obj, message.arg1);
                        break;
                    case 108:
                        this.f5334b.onPlayingAdvertisementChange((AdvertisementParam) message.obj);
                        break;
                    case 109:
                        this.f5334b.onPlaySpeedListChange((List) message.obj);
                        break;
                    case 110:
                        this.f5334b.onCpAppStateChange(message.arg1, (String) message.obj);
                        break;
                }
            }
        }

        public c(InterfaceC0464B interfaceC0464B, Handler handler) {
            super(handler);
            this.f5334b = interfaceC0464B;
        }
    }

    public w(InterfaceC0444g interfaceC0444g) {
        this.f5327a = interfaceC0444g;
    }

    public boolean A(InterfaceC0464B interfaceC0464B) {
        boolean zB;
        synchronized (this.f5329c) {
            zB = B(interfaceC0464B);
        }
        return zB;
    }

    public final boolean B(InterfaceC0464B interfaceC0464B) {
        boolean z2 = false;
        for (int size = this.f5332f.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f5332f.get(size);
            if (interfaceC0464B == cVar.f5334b) {
                this.f5332f.remove(size);
                cVar.f5335a = false;
                z2 = true;
            }
        }
        if (this.f5331e && this.f5332f.size() == 0) {
            try {
                this.f5327a.V(this.f5328b);
            } catch (RemoteException e2) {
                z0.e.b("MediaController", "unregisterMediaChangeListenerLocked binder call error", e2);
            }
            this.f5331e = false;
        }
        return z2;
    }

    public void f() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call fastForward", new h.a() { // from class: m0.j
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.i();
            }
        });
    }

    public final c g(InterfaceC0464B interfaceC0464B) {
        for (int size = this.f5332f.size() - 1; size >= 0; size--) {
            c cVar = (c) this.f5332f.get(size);
            if (cVar.f5334b == interfaceC0464B) {
                return cVar;
            }
        }
        return null;
    }

    public MediaMetaData h() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        return (MediaMetaData) hVar.a("MediaController", "call getMediaMetaData", null, new h.b() { // from class: m0.u
            @Override // z0.h.b
            public final Object invoke() {
                return interfaceC0444g.B();
            }
        });
    }

    public void i() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call getPlaySpeedList", new h.a() { // from class: m0.q
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.X();
            }
        });
    }

    public int j() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        return ((Integer) hVar.a("MediaController", "call getPlaybackState", 0, new h.b() { // from class: m0.s
            @Override // z0.h.b
            public final Object invoke() {
                return Integer.valueOf(interfaceC0444g.getPlaybackState());
            }
        })).intValue();
    }

    public long k() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        return ((Long) hVar.a("MediaController", "call getPosition", -1L, new h.b() { // from class: m0.m
            @Override // z0.h.b
            public final Object invoke() {
                return Long.valueOf(interfaceC0444g.getPosition());
            }
        })).longValue();
    }

    public void l() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call getSpeed", new h.a() { // from class: m0.n
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.F();
            }
        });
    }

    public final /* synthetic */ void m(long j2) {
        this.f5327a.a(j2);
    }

    public final /* synthetic */ void n(float f2) {
        this.f5327a.r0(f2);
    }

    public void o() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call next", new h.a() { // from class: m0.k
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.next();
            }
        });
    }

    public void p() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call pause", new h.a() { // from class: m0.t
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.pause();
            }
        });
    }

    public void q() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call play", new h.a() { // from class: m0.v
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.d();
            }
        });
    }

    public final void r(int i2, Object obj) {
        synchronized (this.f5329c) {
            try {
                for (int size = this.f5332f.size() - 1; size >= 0; size--) {
                    ((c) this.f5332f.get(size)).a(i2, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void s(int i2, Object obj, int i3) {
        synchronized (this.f5329c) {
            try {
                for (int size = this.f5332f.size() - 1; size >= 0; size--) {
                    ((c) this.f5332f.get(size)).b(i2, obj, i3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void t() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call previous", new h.a() { // from class: m0.p
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.previous();
            }
        });
    }

    public void u(InterfaceC0464B interfaceC0464B, Handler handler) {
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        synchronized (this.f5329c) {
            v(interfaceC0464B, handler);
        }
    }

    public final void v(InterfaceC0464B interfaceC0464B, Handler handler) {
        if (g(interfaceC0464B) != null) {
            z0.e.d("MediaController", "this mediaChangeListener has been register");
            return;
        }
        c cVar = new c(interfaceC0464B, handler);
        this.f5332f.add(cVar);
        cVar.f5335a = true;
        if (this.f5331e) {
            return;
        }
        try {
            this.f5327a.c0(this.f5328b);
            this.f5331e = true;
        } catch (RemoteException e2) {
            z0.e.b("MediaController", "registerMediaChangeListenerLocked binder call error", e2);
        }
    }

    public void w() {
        z0.h hVar = this.f5330d;
        final InterfaceC0444g interfaceC0444g = this.f5327a;
        Objects.requireNonNull(interfaceC0444g);
        hVar.b("MediaController", "call rewind", new h.a() { // from class: m0.r
            @Override // z0.h.a
            public final void invoke() {
                interfaceC0444g.h();
            }
        });
    }

    public void x(final long j2) {
        this.f5330d.b("MediaController", "call seekTo, pos:" + j2, new h.a() { // from class: m0.o
            @Override // z0.h.a
            public final void invoke() {
                this.f5318a.m(j2);
            }
        });
    }

    public void y(final float f2) {
        this.f5330d.b("MediaController", "call setSpeed", new h.a() { // from class: m0.l
            @Override // z0.h.a
            public final void invoke() {
                this.f5314a.n(f2);
            }
        });
    }

    public final List z(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(Float.valueOf(Float.parseFloat((String) it.next())));
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }
}
