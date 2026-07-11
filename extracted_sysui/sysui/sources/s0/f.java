package s0;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.miui.miplay.audio.data.MediaMetaData;
import com.xiaomi.cast.api.DeviceInfo;
import com.xiaomi.cast.api.DeviceStatusListener;
import com.xiaomi.cast.api.IMiCastSDK;
import com.xiaomi.cast.api.ServiceStatusListener;
import com.xiaomi.cast.api.runtime.DeviceConst;
import com.xiaomi.cast.env.MiCastSDK;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import m0.C0466a;
import m0.InterfaceC0464B;
import t0.AbstractC0742a;
import u0.InterfaceC0747a;
import y0.c;

/* JADX INFO: loaded from: classes2.dex */
public class f extends AbstractC0737a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6438a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m f6441d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public u0.d f6442e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0466a f6443f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public y0.c f6445h;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6454q;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f6440c = new Object();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f6444g = new LinkedList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final e f6446i = new e(this);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final DeviceStatusListener f6447j = new b(this);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final c f6448k = new c(this);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final InterfaceC0747a f6449l = new a(this);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InterfaceC0464B f6450m = new d(this);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t0.e f6451n = new t0.e();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final l f6452o = new l();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Handler f6453p = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final IMiCastSDK f6439b = MiCastSDK.getInstance();

    public static final class a implements InterfaceC0747a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6455a;

        public a(f fVar) {
            this.f6455a = new WeakReference(fVar);
        }

        @Override // u0.InterfaceC0747a
        public void a(boolean z2) {
        }

        @Override // u0.InterfaceC0747a
        public void onActiveAudioSessionChange(List list) {
            f fVar = (f) this.f6455a.get();
            if (fVar == null) {
                return;
            }
            fVar.H(list.isEmpty() ? null : (C0466a) list.get(0));
            fVar.x(1, list);
        }
    }

    public static class b implements DeviceStatusListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6456a;

        public b(f fVar) {
            this.f6456a = new WeakReference(fVar);
        }

        @Override // com.xiaomi.cast.api.DeviceStatusListener
        public void onCastSessionStatusChange(int i2) {
            f fVar = (f) this.f6456a.get();
            if (fVar != null && i2 == 1) {
                fVar.N();
            }
        }

        @Override // com.xiaomi.cast.api.DeviceStatusListener
        public void onDeviceStatusChanged(List list) {
            f fVar = (f) this.f6456a.get();
            if (fVar == null) {
                return;
            }
            fVar.J(list);
        }

        @Override // com.xiaomi.cast.api.DeviceStatusListener
        public void onDeviceTakeOver() {
            f fVar = (f) this.f6456a.get();
            if (fVar == null) {
                return;
            }
            fVar.I();
        }

        @Override // com.xiaomi.cast.api.DeviceStatusListener
        public void onDeviceVolumeChanged(String str, double d2) {
        }
    }

    public static final class c implements ServiceStatusListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6457a;

        public c(f fVar) {
            this.f6457a = new WeakReference(fVar);
        }

        @Override // com.xiaomi.cast.api.ServiceStatusListener
        public void onServiceStatusChange(int i2) {
            f fVar = (f) this.f6457a.get();
            if (fVar != null && i2 == 0) {
                fVar.M();
            }
        }
    }

    public static final class d extends AbstractC0742a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6458a;

        public d(f fVar) {
            this.f6458a = new WeakReference(fVar);
        }

        @Override // m0.InterfaceC0464B
        public void onMediaMetaChange(MediaMetaData mediaMetaData) {
            f fVar = (f) this.f6458a.get();
            if (fVar == null) {
                return;
            }
            fVar.K(mediaMetaData);
        }

        @Override // m0.InterfaceC0464B
        public void onPlaybackStateChange(int i2) {
            f fVar = (f) this.f6458a.get();
            if (fVar == null) {
                return;
            }
            fVar.L(i2);
        }
    }

    public static final class e implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6459a;

        public e(f fVar) {
            this.f6459a = new WeakReference(fVar);
        }

        @Override // y0.c.b
        public void a(int i2) {
            f fVar = (f) this.f6459a.get();
            if (fVar == null) {
                return;
            }
            fVar.O();
        }
    }

    public f(Context context) {
        this.f6438a = context;
        h(context, false);
        this.f6454q = 3;
    }

    public static /* synthetic */ boolean E(t0.b bVar) {
        return bVar.k().isLocalSpeaker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i2, Object obj) {
        synchronized (this.f6440c) {
            try {
                if (this.f6441d == null) {
                    z0.e.c("GlobalMiPlayAudioManager", "dispatchCallback, " + i2 + "while callback is null");
                    return;
                }
                z0.e.c("GlobalMiPlayAudioManager", "dispatchCallback, what, " + i2);
                this.f6441d.a(i2, obj);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void A(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            z0.e.c("GlobalMiPlayAudioManager", "dumpDeviceInfo " + ((DeviceInfo) it.next()));
        }
    }

    public final void B(List list) {
        z0.e.c("GlobalMiPlayAudioManager", "dumpGoogleCastDeviceInfos === start");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            z0.e.c("GlobalMiPlayAudioManager", "dumpGoogleCastDeviceInfos " + ((t0.b) it.next()));
        }
        z0.e.c("GlobalMiPlayAudioManager", "dumpGoogleCastDeviceInfos === emd");
    }

    public final boolean C() {
        boolean z2;
        synchronized (this.f6440c) {
            try {
                Iterator it = this.f6444g.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    z2 = true;
                    if (((t0.b) it.next()).h() == 1) {
                    }
                }
            } finally {
            }
        }
        return z2;
    }

    public final /* synthetic */ void D() {
        z0.f.a(this.f6438a).b(false);
    }

    public final /* synthetic */ t0.b F(t0.b bVar) {
        DeviceInfo deviceInfoZ = bVar.z();
        deviceInfoZ.setConnectState(1);
        return t0.c.a(deviceInfoZ, this.f6439b, this.f6452o);
    }

    public final void G() {
        z0.f.a(this.f6438a).b(true);
        this.f6453p.removeCallbacksAndMessages(null);
        this.f6453p.postDelayed(new Runnable() { // from class: s0.e
            @Override // java.lang.Runnable
            public final void run() {
                this.f6437a.D();
            }
        }, 1500L);
    }

    public final void H(C0466a c0466a) {
        synchronized (this.f6440c) {
            try {
                if (c0466a == null) {
                    z0.e.c("GlobalMiPlayAudioManager", "onActiveAudioSessionChange activeSession is null");
                    this.f6439b.setPlaybackState(DeviceConst.MEDIA_SESSION_DESTORY);
                } else {
                    C0466a c0466a2 = this.f6443f;
                    if (c0466a2 != null) {
                        String packageName = c0466a2.a().getPackageName();
                        String packageName2 = c0466a.a().getPackageName();
                        if (!packageName.equals(packageName2)) {
                            z0.e.c("GlobalMiPlayAudioManager", "osPkgName" + packageName + ", nsPkgName:" + packageName2);
                            this.f6439b.setPlaybackState(DeviceConst.MEDIA_SESSION_DESTORY);
                            C0466a c0466a3 = this.f6443f;
                            if (C()) {
                                c0466a3.b().p();
                                G();
                            }
                        }
                    }
                }
                C0466a c0466a4 = this.f6443f;
                if (c0466a4 != null) {
                    c0466a4.b().A(this.f6450m);
                }
                this.f6443f = c0466a;
                if (c0466a != null) {
                    c0466a.b().u(this.f6450m, null);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void I() {
        synchronized (this.f6440c) {
            try {
                C0466a c0466a = this.f6443f;
                if (c0466a == null) {
                    return;
                }
                c0466a.b().p();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void J(List list) {
        boolean z2;
        A(list);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        synchronized (this.f6440c) {
            try {
                t0.d.b(P(), list, arrayList, arrayList2, arrayList3);
                z0.e.c("GlobalMiPlayAudioManager", "onGlobalDeviceChange, toAddSize:" + arrayList.size() + "\ntoRemoveSize:" + arrayList2.size() + "\ntoChangeSize:" + arrayList3.size());
                Iterator it = arrayList3.iterator();
                boolean z3 = false;
                while (true) {
                    z2 = true;
                    if (!it.hasNext()) {
                        break;
                    } else if (z((h) it.next())) {
                        z3 = true;
                    }
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    this.f6444g.add(t0.c.a((DeviceInfo) it2.next(), this.f6439b, this.f6452o));
                    z3 = true;
                }
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    t0.b bVarD = t0.d.d(this.f6444g, (DeviceInfo) it3.next());
                    if (bVarD != null) {
                        this.f6444g.remove(bVarD);
                        z3 = true;
                    }
                }
                boolean zB = this.f6451n.b(this.f6444g);
                if (zB) {
                    z0.e.c("GlobalMiPlayAudioManager", "deviceRankChanged" + zB);
                } else {
                    z2 = z3;
                }
                if (z2) {
                    z0.e.c("GlobalMiPlayAudioManager", "onGlobalDeviceChange, deviceListSize:" + this.f6444g.size());
                    y(this.f6444g);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void K(MediaMetaData mediaMetaData) {
        synchronized (this.f6440c) {
            try {
                C0466a c0466a = this.f6443f;
                if (c0466a != null) {
                    if (c0466a.b().j() != 3) {
                        return;
                    }
                    this.f6439b.setMediaMetaData(t0.c.f(mediaMetaData), this.f6443f.a().getPackageName());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void L(int i2) {
        synchronized (this.f6440c) {
            try {
                z0.e.c("GlobalMiPlayAudioManager", "onPlayStateChange: " + i2);
                this.f6439b.setPlaybackState(i2);
                if (i2 == 3) {
                    z0.e.c("GlobalMiPlayAudioManager", "playStateChange," + i2);
                    C0466a c0466a = this.f6443f;
                    if (c0466a != null) {
                        this.f6439b.setMediaMetaData(t0.c.f(c0466a.b().h()), this.f6443f.a().getPackageName());
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void M() {
        z0.e.c("GlobalMiPlayAudioManager", "onServiceDisconnect");
        int i2 = this.f6454q;
        if (i2 > 0) {
            this.f6454q = i2 - 1;
            Q();
            w();
            R();
        }
    }

    public void N() {
        z0.e.c("GlobalMiPlayAudioManager", "onSuccessConnectDevice,");
        synchronized (this.f6440c) {
            try {
                C0466a c0466a = this.f6443f;
                if (c0466a != null) {
                    MediaMetaData mediaMetaDataH = c0466a.b().h();
                    this.f6439b.setMediaMetaData(t0.c.f(mediaMetaDataH), this.f6443f.a().getPackageName());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void O() {
        z0.e.c("GlobalMiPlayAudioManager", "onUserSwitch");
        w();
        R();
    }

    public final List P() {
        return (List) this.f6444g.stream().map(new Function() { // from class: s0.d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((t0.b) obj).z();
            }
        }).collect(Collectors.toList());
    }

    public final void Q() {
        z0.e.c("GlobalMiPlayAudioManager", "pauseMusicWhileCasting");
        synchronized (this.f6440c) {
            try {
                if (this.f6443f != null && C()) {
                    this.f6443f.b().p();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void R() {
        z0.e.c("GlobalMiPlayAudioManager", "rebuildEverything");
        h(this.f6438a, false);
    }

    public final void S() {
        z0.e.c("GlobalMiPlayAudioManager", "reportOnlyPhone");
        synchronized (this.f6440c) {
            y((List) this.f6444g.stream().filter(new Predicate() { // from class: s0.b
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return f.E((t0.b) obj);
                }
            }).map(new Function() { // from class: s0.c
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f6436a.F((t0.b) obj);
                }
            }).collect(Collectors.toList()));
        }
    }

    @Override // m0.z
    public void b(int i2) {
        z0.e.c("GlobalMiPlayAudioManager", "markUIStop");
        this.f6439b.exitUI();
    }

    @Override // s0.AbstractC0737a
    public void c() {
        synchronized (this.f6440c) {
            this.f6451n.c(this.f6444g);
            z0.e.c("GlobalMiPlayAudioManager", "queryDeviceListWithCache," + this.f6444g.size());
            y(this.f6444g);
        }
    }

    @Override // m0.z
    public boolean d() {
        return false;
    }

    @Override // m0.z
    public boolean e() {
        z0.e.c("GlobalMiPlayAudioManager", "startScan");
        this.f6439b.startScan();
        return true;
    }

    @Override // m0.z
    public boolean f() {
        z0.e.c("GlobalMiPlayAudioManager", "stopScan");
        this.f6439b.stopScan();
        return true;
    }

    @Override // m0.z
    public boolean g(int i2) {
        z0.e.c("GlobalMiPlayAudioManager", "startScan");
        this.f6439b.startScan();
        return true;
    }

    @Override // m0.z
    public void h(Context context, boolean z2) {
        u0.d dVar = new u0.d(context, this);
        this.f6442e = dVar;
        dVar.q(this.f6449l);
        this.f6439b.init(this.f6438a, null);
        this.f6439b.setDeviceChangeListener(this.f6447j);
        this.f6439b.setServiceStatusListener(this.f6448k);
        this.f6445h = new y0.c(context, this.f6446i);
    }

    @Override // m0.z
    public void j(int i2) {
        z0.e.c("GlobalMiPlayAudioManager", "markUIStart");
        this.f6454q = 3;
        this.f6439b.enterUI();
    }

    @Override // m0.z
    public List l() {
        u0.d dVar = this.f6442e;
        if (dVar == null) {
            z0.e.a("GlobalMiPlayAudioManager", "queryActiveAudioSession mMediaManager is null");
            return Collections.emptyList();
        }
        List listP = dVar.p(null);
        z0.e.c("GlobalMiPlayAudioManager", "queryActiveAudioSession, resultSize, " + listP.size());
        return listP;
    }

    @Override // m0.z
    public void o(m mVar) {
        z0.e.c("GlobalMiPlayAudioManager", "registerMiPlayServiceCallback");
        synchronized (this.f6440c) {
            this.f6441d = mVar;
        }
    }

    @Override // m0.z
    public void release() {
        z0.e.c("GlobalMiPlayAudioManager", "release");
        this.f6439b.unInit();
        u0.d dVar = this.f6442e;
        if (dVar != null) {
            dVar.r();
        }
        y0.c cVar = this.f6445h;
        if (cVar != null) {
            cVar.g();
        }
    }

    public final void w() {
        this.f6439b.disConnectAll();
        z0.e.c("GlobalMiPlayAudioManager", "destroyEverything");
        S();
        release();
        synchronized (this.f6440c) {
            this.f6444g.clear();
            this.f6443f = null;
        }
    }

    public final void y(List list) {
        B(list);
        x(2, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0083, code lost:
    
        r3 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean z(s0.h r11) {
        /*
            r10 = this;
            com.xiaomi.cast.api.DeviceInfo r0 = r11.b()
            java.lang.String r0 = r0.getId()
            java.lang.Object r1 = r10.f6440c
            monitor-enter(r1)
            java.util.List r2 = r10.f6444g     // Catch: java.lang.Throwable -> L51
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L51
        L11:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L51
            r4 = 1
            if (r3 == 0) goto L83
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L51
            t0.b r3 = (t0.b) r3     // Catch: java.lang.Throwable -> L51
            java.lang.String r5 = r3.j()     // Catch: java.lang.Throwable -> L51
            boolean r5 = android.text.TextUtils.equals(r5, r0)     // Catch: java.lang.Throwable -> L51
            if (r5 == 0) goto L11
            boolean r5 = r11.a(r4)     // Catch: java.lang.Throwable -> L51
            r6 = 2
            boolean r6 = r11.a(r6)     // Catch: java.lang.Throwable -> L51
            r7 = 4
            boolean r7 = r11.a(r7)     // Catch: java.lang.Throwable -> L51
            com.xiaomi.cast.api.DeviceInfo r8 = r11.b()     // Catch: java.lang.Throwable -> L51
            java.lang.String r9 = r8.getId()     // Catch: java.lang.Throwable -> L51
            if (r5 == 0) goto L53
            java.lang.String r0 = "GlobalMiPlayAudioManager"
            java.lang.String r2 = "infoChange"
            z0.e.c(r0, r2)     // Catch: java.lang.Throwable -> L51
            s0.l r0 = r10.f6452o     // Catch: java.lang.Throwable -> L51
            com.miui.miplay.audio.data.DeviceInfo r2 = t0.c.c(r8)     // Catch: java.lang.Throwable -> L51
            r0.c(r9, r2)     // Catch: java.lang.Throwable -> L51
            goto L84
        L51:
            r10 = move-exception
            goto La7
        L53:
            if (r6 == 0) goto L6a
            java.lang.String r0 = "GlobalMiPlayAudioManager"
            java.lang.String r2 = "connectChange"
            z0.e.c(r0, r2)     // Catch: java.lang.Throwable -> L51
            int r0 = r8.getConnectState()     // Catch: java.lang.Throwable -> L51
            int r0 = t0.c.h(r0)     // Catch: java.lang.Throwable -> L51
            s0.l r2 = r10.f6452o     // Catch: java.lang.Throwable -> L51
            r2.b(r9, r0)     // Catch: java.lang.Throwable -> L51
            goto L84
        L6a:
            if (r7 == 0) goto L11
            java.lang.String r0 = "GlobalMiPlayAudioManager"
            java.lang.String r2 = "volumeChange"
            z0.e.c(r0, r2)     // Catch: java.lang.Throwable -> L51
            com.xiaomi.cast.api.DeviceInfo r0 = r11.b()     // Catch: java.lang.Throwable -> L51
            int r0 = r0.getVolume()     // Catch: java.lang.Throwable -> L51
            s0.l r2 = r10.f6452o     // Catch: java.lang.Throwable -> L51
            r2.d(r9, r0)     // Catch: java.lang.Throwable -> L51
            r3.A(r8)     // Catch: java.lang.Throwable -> L51
        L83:
            r3 = 0
        L84:
            if (r3 == 0) goto La4
            java.util.List r0 = r10.f6444g     // Catch: java.lang.Throwable -> L51
            int r0 = r0.indexOf(r3)     // Catch: java.lang.Throwable -> L51
            java.util.List r2 = r10.f6444g     // Catch: java.lang.Throwable -> L51
            r2.remove(r3)     // Catch: java.lang.Throwable -> L51
            java.util.List r2 = r10.f6444g     // Catch: java.lang.Throwable -> L51
            com.xiaomi.cast.api.DeviceInfo r11 = r11.b()     // Catch: java.lang.Throwable -> L51
            com.xiaomi.cast.api.IMiCastSDK r3 = r10.f6439b     // Catch: java.lang.Throwable -> L51
            s0.l r10 = r10.f6452o     // Catch: java.lang.Throwable -> L51
            t0.b r10 = t0.c.a(r11, r3, r10)     // Catch: java.lang.Throwable -> L51
            r2.add(r0, r10)     // Catch: java.lang.Throwable -> L51
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L51
            return r4
        La4:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L51
            r10 = 0
            return r10
        La7:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L51
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: s0.f.z(s0.h):boolean");
    }
}
