package x0;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;
import l0.C0438a;
import l0.C0439b;
import l0.C0440c;
import l0.InterfaceC0445h;
import l0.i;
import m0.C0466a;
import m0.E;
import m0.InterfaceC0463A;
import miui.systemui.util.SmartDeviceUtils;
import o0.C0719b;
import o0.C0720c;
import p0.d;
import u0.InterfaceC0747a;
import x0.x;
import y0.c;
import z0.AbstractC0778c;
import z0.h;

/* JADX INFO: loaded from: classes2.dex */
public class v implements m0.z, x.a, IBinder.DeathRecipient, d.c, C0720c.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public s0.m f7063A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f7065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final z0.h f7066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public InterfaceC0445h f7069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7070g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Boolean f7071h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InterfaceC0445h f7072i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public e f7073j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f7074k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d f7075l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public u0.d f7076m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public p0.d f7077n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final InterfaceC0747a f7078o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final InterfaceC0463A f7079p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f7080q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public y0.c f7081r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f7082s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public C0719b f7083x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final x f7084y;

    public static final class b implements InterfaceC0747a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f7085a;

        public b(v vVar) {
            this.f7085a = new WeakReference(vVar);
        }

        @Override // u0.InterfaceC0747a
        public void a(boolean z2) {
            v vVar = (v) this.f7085a.get();
            if (vVar != null) {
                vVar.g0(z2);
            }
        }

        @Override // u0.InterfaceC0747a
        public void onActiveAudioSessionChange(List list) {
            v vVar = (v) this.f7085a.get();
            if (vVar != null) {
                vVar.X(1, list);
            }
        }
    }

    public static class c extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f7086a;

        public c(v vVar) {
            this.f7086a = new WeakReference(vVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            v vVar = (v) this.f7086a.get();
            if (vVar == null || intent.getAction() == null) {
                return;
            }
            String action = intent.getAction();
            action.hashCode();
            if (!action.equals("MultiA2dp.ACTION.RESET_STATE_CHANGED")) {
                if (action.equals("com.milink.service.FASTCONNECT_NOTIFICATION")) {
                    z0.e.c("MiPlayAudioManager_AudioShareReceiver", "receive audio-share, pairing state change,");
                    vVar.X(7, -1);
                    return;
                }
                return;
            }
            int intExtra = intent.getIntExtra("MultiA2dp.EXTRA.STATE", -1);
            z0.e.c("MiPlayAudioManager_AudioShareReceiver", "receive audio-share, a2dp state change," + intExtra);
            if (intExtra == 0) {
                vVar.f7079p.b(false);
                vVar.g0(false);
                vVar.X(8, -1);
            } else if (intExtra == 1) {
                vVar.f7079p.b(true);
                vVar.g0(true);
            }
        }
    }

    public static final class d extends i.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f7087a;

        @Override // l0.i
        public void A(List list) {
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            C0440c c0440c = null;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    C0440c c0440c2 = (C0440c) it.next();
                    if (c0440c2.u() == 0) {
                        c0440c = c0440c2;
                    }
                    arrayList.add(new m0.i(c0440c2));
                }
            }
            vVar.X(2, arrayList);
            boolean z2 = (c0440c == null || c0440c.t() == 3) ? false : true;
            Boolean boolC0 = vVar.c0();
            if (boolC0 == null || boolC0.booleanValue() != z2) {
                vVar.D0(Boolean.valueOf(z2));
                vVar.X(6, Boolean.valueOf(z2));
            }
        }

        @Override // l0.i
        public void F0(int i2, int i3) {
            z0.e.c("LocalMiplayAudioManager", "onProjectionStateChangeV2, " + i2 + ", mediaType: " + i3);
            onProjectionStateChange(i2);
        }

        @Override // l0.i
        public void onActiveAudioSessionChange(List list) {
            v vVar = (v) this.f7087a.get();
            if (vVar == null || vVar.f7076m == null) {
                return;
            }
            vVar.f7076m.n(list.isEmpty() ? null : new C0466a((C0438a) list.get(0)));
        }

        @Override // l0.i
        public void onAudioDeviceListChange(List list) {
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            C0439b c0439b = null;
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    C0439b c0439b2 = (C0439b) it.next();
                    if (c0439b2.u() == 0) {
                        c0439b = c0439b2;
                    }
                    arrayList.add(new m0.i(c0439b2));
                }
            }
            synchronized (vVar.f7065b) {
                try {
                    if (vVar.f7069f != vVar.f7072i) {
                        vVar.X(2, arrayList);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            boolean z2 = (c0439b == null || c0439b.t() == 3) ? false : true;
            Boolean boolC0 = vVar.c0();
            if (boolC0 == null || boolC0.booleanValue() != z2) {
                vVar.D0(Boolean.valueOf(z2));
                vVar.X(6, Boolean.valueOf(z2));
            }
        }

        @Override // l0.i
        public void onBluetoothDeviceConnectFail(String str) {
            z0.e.c("LocalMiplayAudioManager", "onBluetoothDeviceConnectFail, " + str);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(13, str);
        }

        @Override // l0.i
        public void onBluetoothDeviceConnectSuccess(String str) {
            z0.e.c("LocalMiplayAudioManager", "onBluetoothDeviceConnectSuccess, " + str);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(14, str);
        }

        @Override // l0.i
        public void onDeviceStartPlaying(Bundle bundle) {
            z0.e.c("LocalMiplayAudioManager", "onDeviceStartPlaying, " + bundle.toString());
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(12, bundle);
        }

        @Override // l0.i
        public void onError(int i2, String str) {
            z0.e.c("LocalMiplayAudioManager", "onToastError, " + i2 + ", msg:" + str);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(5, new C0771a(i2, str));
        }

        @Override // l0.i
        public void onProjectionStateChange(int i2) {
            z0.e.c("LocalMiplayAudioManager", "onProjectionStateChange, " + i2);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.E0(i2);
            vVar.X(4, Integer.valueOf(i2));
            if (i2 == 1) {
                vVar.f7079p.e(true);
            } else {
                vVar.f7079p.e(false);
                vVar.f7084y.e();
            }
        }

        @Override // l0.i
        public void onServiceStateChange(int i2) {
            z0.e.c("LocalMiplayAudioManager", "onServiceStateChange, " + i2);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(3, Integer.valueOf(i2));
        }

        @Override // l0.i
        public void onVideoCastModeChange(int i2, int i3) {
            z0.e.c("LocalMiplayAudioManager", "onVideoCastModeChange, protocolType: " + i2 + ", mode: " + i3);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(9, new z(i2, i3));
        }

        @Override // l0.i
        public void onVideoCpAppStateChange(int i2, String str) {
            z0.e.c("LocalMiplayAudioManager", "onVideoCpAppStateChange, state: " + i2 + ", clientUrn: " + str);
            v vVar = (v) this.f7087a.get();
            if (vVar == null) {
                return;
            }
            vVar.X(11, new C0770A(i2, str));
        }

        @Override // l0.i
        public void s(int i2) {
        }

        public d(v vVar) {
            this.f7087a = new WeakReference(vVar);
        }
    }

    public class e implements ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Queue f7088a = new ArrayDeque();

        public e() {
        }

        public final void b() {
            while (!this.f7088a.isEmpty()) {
                Runnable runnable = (Runnable) this.f7088a.poll();
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception e2) {
                        z0.e.b("LocalMiplayAudioManager", "pendingAction error", e2);
                    }
                }
            }
        }

        public final void c() {
            v.this.X(3, Integer.valueOf(v.this.e0()));
            v.this.X(1, v.this.l());
            v.this.X(2, v.this.m(0));
            int iA = v.this.a(0);
            v.this.E0(iA);
            v.this.X(4, Integer.valueOf(iA));
        }

        public final void d(Runnable runnable) {
            this.f7088a.offer(runnable);
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            z0.e.c("LocalMiplayAudioManager", "onBindingDied");
            v.this.f0();
            synchronized (v.this.f7065b) {
                v vVar = v.this;
                vVar.f7069f = vVar.f7072i;
                v.this.f7068e = 6;
                v.this.f7067d = false;
            }
            v vVar2 = v.this;
            vVar2.X(3, Integer.valueOf(vVar2.f7068e));
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            z0.e.c("LocalMiplayAudioManager", "onServiceConnected");
            synchronized (v.this.f7065b) {
                v.this.f7069f = InterfaceC0445h.b.Z0(iBinder);
                v.this.f7068e = 1;
                v.this.y0();
            }
            v vVar = v.this;
            vVar.X(3, Integer.valueOf(vVar.f7068e));
            c();
            b();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            z0.e.c("LocalMiplayAudioManager", "onServiceDisconnected");
            synchronized (v.this.f7065b) {
                v vVar = v.this;
                vVar.f7069f = vVar.f7072i;
                v.this.f7068e = 7;
                v.this.f7067d = false;
            }
            v vVar2 = v.this;
            vVar2.X(3, Integer.valueOf(vVar2.f7068e));
        }
    }

    public v(Context context, boolean z2) {
        Object obj = new Object();
        this.f7065b = obj;
        this.f7066c = new z0.h();
        this.f7071h = null;
        InterfaceC0445h.a aVar = new InterfaceC0445h.a();
        this.f7072i = aVar;
        this.f7074k = false;
        this.f7075l = new d();
        this.f7078o = new b(this);
        this.f7079p = E.g();
        this.f7082s = false;
        synchronized (obj) {
            this.f7069f = aVar;
        }
        this.f7064a = context;
        this.f7084y = new x(this);
        h(context, z2);
    }

    public static /* synthetic */ boolean j0(m0.i iVar) {
        return iVar.k().isLocalSpeaker();
    }

    public static /* synthetic */ void k0(m0.i iVar) {
        iVar.w(AbstractC0778c.b());
    }

    public static /* synthetic */ boolean l0(m0.i iVar) {
        return iVar.k().isLocalSpeaker();
    }

    public static /* synthetic */ void m0(m0.i iVar) {
        iVar.v(AbstractC0778c.b(), AbstractC0778c.a());
    }

    public static UserHandle w0(int i2) {
        try {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("of", Integer.TYPE);
            declaredMethod.setAccessible(true);
            return (UserHandle) declaredMethod.invoke(null, Integer.valueOf(i2));
        } catch (Exception unused) {
            return null;
        }
    }

    public final void A0() {
        synchronized (this.f7065b) {
            this.f7067d = false;
            try {
                this.f7069f.N(this.f7075l);
            } catch (RemoteException e2) {
                z0.e.b("LocalMiplayAudioManager", "unregisterMiPlayServiceCallback binder call error", e2);
            }
        }
    }

    public final void B0() {
        synchronized (this.f7065b) {
            try {
                if (this.f7069f == this.f7072i) {
                    V();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void C0() {
        z0.e.c("LocalMiplayAudioManager", "safeUnBindService");
        if (Z()) {
            F0();
        } else {
            z0.e.c("LocalMiplayAudioManager", "can not unbindService;");
        }
    }

    public final void D0(Boolean bool) {
        synchronized (this.f7065b) {
            this.f7071h = bool;
        }
    }

    public final void E0(int i2) {
        synchronized (this.f7065b) {
            this.f7070g = i2;
        }
    }

    public final void F0() {
        z0.e.c("LocalMiplayAudioManager", "unbindService");
        z0();
        A0();
        try {
            e eVar = this.f7073j;
            if (eVar != null && this.f7074k) {
                this.f7064a.unbindService(eVar);
                this.f7073j = null;
                this.f7074k = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        synchronized (this.f7065b) {
            this.f7069f = this.f7072i;
        }
    }

    public void V() {
        W(null);
    }

    public final void W(Runnable runnable) {
        boolean zBindService;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(SmartDeviceUtils.MI_LINK_PACKAGE_NAME, "com.miui.miplay.audio.service.CoreService"));
        intent.setPackage(SmartDeviceUtils.MI_LINK_PACKAGE_NAME);
        intent.putExtra("client_api_version", 2);
        if (this.f7073j == null) {
            this.f7073j = new e();
        }
        if (runnable != null) {
            this.f7073j.d(runnable);
        }
        if (Process.myUid() == 1000) {
            UserHandle userHandleW0 = w0(y.a());
            if (userHandleW0 == null) {
                z0.e.c("LocalMiplayAudioManager", "reflect current UserHandle fail");
                userHandleW0 = Process.myUserHandle();
            }
            z0.e.c("LocalMiplayAudioManager", "bindServiceAsUser, use: " + userHandleW0);
            zBindService = this.f7064a.bindServiceAsUser(intent, this.f7073j, 4097, userHandleW0);
        } else {
            z0.e.c("LocalMiplayAudioManager", "bindService normal");
            zBindService = this.f7064a.bindService(intent, this.f7073j, 4097);
        }
        synchronized (this.f7065b) {
            try {
                if (zBindService) {
                    this.f7068e = 8;
                } else {
                    this.f7068e = 6;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zBindService) {
            this.f7074k = true;
        }
        X(3, Integer.valueOf(this.f7068e));
        z0.e.c("LocalMiplayAudioManager", "bind service: " + zBindService);
    }

    public final void X(int i2, Object obj) {
        synchronized (this.f7065b) {
            try {
                s0.m mVar = this.f7063A;
                if (mVar != null) {
                    mVar.a(i2, obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean Y() {
        return this.f7079p.c();
    }

    public final boolean Z() {
        return !this.f7079p.c();
    }

    @Override // m0.z
    public int a(final int i2) {
        return ((Integer) this.f7066c.a("LocalMiplayAudioManager", "getProjectionStateWithParams", 0, new h.b() { // from class: x0.o
            @Override // z0.h.b
            public final Object invoke() {
                return this.f7054a.h0(i2);
            }
        })).intValue();
    }

    public final void a0(Runnable runnable) {
        boolean z2;
        synchronized (this.f7065b) {
            z2 = this.f7069f == this.f7072i;
        }
        if (z2 && Y()) {
            W(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // m0.z
    public void b(final int i2) {
        this.f7079p.f(false);
        this.f7066c.b("LocalMiplayAudioManager", "markUIStop", new h.a() { // from class: x0.s
            @Override // z0.h.a
            public final void invoke() {
                this.f7059a.q0(i2);
            }
        });
        this.f7084y.e();
    }

    public Context b0() {
        return this.f7064a;
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        synchronized (this.f7065b) {
            this.f7068e = 6;
        }
        X(3, 6);
    }

    @Override // o0.C0720c.a
    public void c(boolean z2) {
        g0(z2);
    }

    public final Boolean c0() {
        Boolean bool;
        synchronized (this.f7065b) {
            bool = this.f7071h;
        }
        return bool;
    }

    @Override // m0.z
    public boolean d() {
        return this.f7077n.h();
    }

    public final int d0() {
        int i2;
        synchronized (this.f7065b) {
            i2 = this.f7070g;
        }
        return i2;
    }

    @Override // m0.z
    public boolean e() {
        a0(new Runnable() { // from class: x0.f
            @Override // java.lang.Runnable
            public final void run() {
                this.f7041a.s0();
            }
        });
        return true;
    }

    public int e0() {
        synchronized (this.f7065b) {
            try {
                if (this.f7069f.asBinder() == null) {
                    return this.f7068e;
                }
                z0.h hVar = this.f7066c;
                final InterfaceC0445h interfaceC0445h = this.f7069f;
                Objects.requireNonNull(interfaceC0445h);
                return ((Integer) hVar.a("LocalMiplayAudioManager", "getServiceState", 6, new h.b() { // from class: x0.m
                    @Override // z0.h.b
                    public final Object invoke() {
                        return Integer.valueOf(interfaceC0445h.q0());
                    }
                })).intValue();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // m0.z
    public boolean f() {
        z0.h hVar = this.f7066c;
        Boolean bool = Boolean.FALSE;
        final InterfaceC0445h interfaceC0445h = this.f7069f;
        Objects.requireNonNull(interfaceC0445h);
        return ((Boolean) hVar.a("LocalMiplayAudioManager", "stopScanDevice", bool, new h.b() { // from class: x0.g
            @Override // z0.h.b
            public final Object invoke() {
                return Boolean.valueOf(interfaceC0445h.f());
            }
        })).booleanValue();
    }

    public void f0() {
        if (this.f7073j != null) {
            z0.e.c("LocalMiplayAudioManager", "unbindService");
            F0();
            this.f7073j = new e();
            if (d0() == 1) {
                z0.e.c("LocalMiplayAudioManager", "current is in projection state");
                this.f7073j.d(new Runnable() { // from class: x0.q
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f7057a.i0();
                    }
                });
            }
        }
    }

    @Override // m0.z
    public boolean g(final int i2) {
        try {
            a0(new Runnable() { // from class: x0.h
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7043a.u0(i2);
                }
            });
            return true;
        } catch (Exception unused) {
            a0(new Runnable() { // from class: x0.i
                @Override // java.lang.Runnable
                public final void run() {
                    this.f7045a.v0();
                }
            });
            return true;
        }
    }

    public void g0(boolean z2) {
        if (z2) {
            z0.e.c("LocalMiplayAudioManager", "handleServiceLifecycle safeBindService");
            B0();
        } else {
            z0.e.c("LocalMiplayAudioManager", "handleServiceLifecycle safeUnBindService");
            this.f7084y.e();
        }
    }

    @Override // m0.z
    public void h(Context context, boolean z2) {
        if (this.f7082s) {
            return;
        }
        this.f7082s = true;
        if (z2) {
            this.f7077n = new p0.d(this);
            x0(context);
            this.f7081r = new y0.c(context, new c.b() { // from class: x0.p
                @Override // y0.c.b
                public final void a(int i2) {
                    this.f7056a.n0(i2);
                }
            });
        }
        u0.d dVar = new u0.d(context, this);
        this.f7076m = dVar;
        dVar.q(this.f7078o);
        this.f7083x = new C0719b(context, this);
    }

    public final /* synthetic */ Integer h0(int i2) {
        return Integer.valueOf(this.f7069f.N0(i2));
    }

    @Override // m0.z
    public void i() {
        z0.e.c("LocalMiplayAudioManager", "reconnect service");
        if (Y()) {
            V();
        }
    }

    public final /* synthetic */ void i0() {
        z0.e.c("LocalMiplayAudioManager", "auto pause when reconnect");
        List listL = l();
        if (listL.isEmpty()) {
            z0.e.c("LocalMiplayAudioManager", "auto pause fail when reconnect");
        } else {
            ((C0466a) listL.get(0)).b().p();
        }
    }

    @Override // m0.z
    public void j(final int i2) {
        this.f7079p.f(true);
        a0(new Runnable() { // from class: x0.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f7061a.p0(i2);
            }
        });
    }

    @Override // m0.z
    public int k() {
        z0.h hVar = this.f7066c;
        final InterfaceC0445h interfaceC0445h = this.f7069f;
        Objects.requireNonNull(interfaceC0445h);
        return ((Integer) hVar.a("LocalMiplayAudioManager", "getServiceVersion error", -1, new h.b() { // from class: x0.r
            @Override // z0.h.b
            public final Object invoke() {
                return Integer.valueOf(interfaceC0445h.k());
            }
        })).intValue();
    }

    @Override // m0.z
    public List l() {
        if (this.f7076m == null) {
            return Collections.emptyList();
        }
        return this.f7076m.p(n());
    }

    @Override // m0.z
    public List m(final int i2) {
        List list = (List) this.f7066c.a("LocalMiplayAudioManager", "queryDeviceListWithParams error", null, new h.b() { // from class: x0.n
            @Override // z0.h.b
            public final Object invoke() {
                return this.f7052a.r0(i2);
            }
        });
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new m0.i((C0440c) it.next()));
        }
        return arrayList;
    }

    @Override // m0.z
    public List n() {
        z0.h hVar = this.f7066c;
        final InterfaceC0445h interfaceC0445h = this.f7069f;
        Objects.requireNonNull(interfaceC0445h);
        return (List) hVar.a("LocalMiplayAudioManager", "queryActiveAudioSession", null, new h.b() { // from class: x0.b
            @Override // z0.h.b
            public final Object invoke() {
                return interfaceC0445h.l();
            }
        });
    }

    public final /* synthetic */ void n0(int i2) {
        z0.e.c("LocalMiplayAudioManager", "newUserId" + i2);
        try {
            m(0).stream().filter(new Predicate() { // from class: x0.u
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return v.j0((m0.i) obj);
                }
            }).findFirst().ifPresent(new Consumer() { // from class: x0.c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    v.k0((m0.i) obj);
                }
            });
            m(1).stream().filter(new Predicate() { // from class: x0.d
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return v.l0((m0.i) obj);
                }
            }).findFirst().ifPresent(new Consumer() { // from class: x0.e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    v.m0((m0.i) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            z0.e.c("LocalMiplayAudioManager", "observe user switch with exception");
        }
    }

    @Override // m0.z
    public void o(s0.m mVar) {
        synchronized (this.f7065b) {
            this.f7063A = mVar;
            y0();
        }
    }

    public final /* synthetic */ void o0(int i2) {
        this.f7069f.j(i2);
    }

    @Override // x0.x.a
    public void p() {
        C0();
    }

    public final /* synthetic */ void p0(final int i2) {
        this.f7066c.b("LocalMiplayAudioManager", "markUIStart", new h.a() { // from class: x0.j
            @Override // z0.h.a
            public final void invoke() {
                this.f7046a.o0(i2);
            }
        });
    }

    @Override // p0.d.c
    public void q(List list) {
        synchronized (this.f7065b) {
            try {
                if (this.f7069f == this.f7072i) {
                    z0.e.c("LocalMiplayAudioManager", "onBluetoothDeviceListChange:" + list.size());
                    X(2, list);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final /* synthetic */ void q0(int i2) {
        this.f7069f.b(i2);
    }

    public final /* synthetic */ List r0(int i2) {
        return this.f7069f.B0(i2);
    }

    @Override // m0.z
    public void release() {
        z0.e.c("LocalMiplayAudioManager", "release");
        try {
            this.f7082s = false;
            F0();
            u0.d dVar = this.f7076m;
            if (dVar != null) {
                dVar.s(this.f7078o);
            }
            p0.d dVar2 = this.f7077n;
            if (dVar2 != null) {
                dVar2.g();
            }
            y0.c cVar = this.f7081r;
            if (cVar != null) {
                cVar.g();
            }
            this.f7084y.d();
            c cVar2 = this.f7080q;
            if (cVar2 != null) {
                this.f7064a.unregisterReceiver(cVar2);
                this.f7080q = null;
            }
            C0719b c0719b = this.f7083x;
            if (c0719b != null) {
                c0719b.b();
            }
        } catch (Exception e2) {
            z0.e.c("LocalMiplayAudioManager", "release error:" + e2.getMessage());
        }
    }

    public final /* synthetic */ void s0() {
        z0.h hVar = this.f7066c;
        Boolean bool = Boolean.FALSE;
        InterfaceC0445h interfaceC0445h = this.f7069f;
        Objects.requireNonNull(interfaceC0445h);
        hVar.a("LocalMiplayAudioManager", "scanDevice", bool, new l(interfaceC0445h));
    }

    public final /* synthetic */ Boolean t0(int i2) {
        return Boolean.valueOf(this.f7069f.g(i2));
    }

    public final /* synthetic */ void u0(final int i2) {
        this.f7066c.a("LocalMiplayAudioManager", "scanDeviceV2", Boolean.FALSE, new h.b() { // from class: x0.k
            @Override // z0.h.b
            public final Object invoke() {
                return this.f7048a.t0(i2);
            }
        });
    }

    public final /* synthetic */ void v0() {
        z0.h hVar = this.f7066c;
        Boolean bool = Boolean.FALSE;
        InterfaceC0445h interfaceC0445h = this.f7069f;
        Objects.requireNonNull(interfaceC0445h);
        hVar.a("LocalMiplayAudioManager", "scanDevice", bool, new l(interfaceC0445h));
    }

    public final void x0(Context context) {
        z0.e.c("LocalMiplayAudioManager", "registerAudioSharedBroadcast");
        this.f7080q = new c(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.milink.service.FASTCONNECT_NOTIFICATION");
        intentFilter.addAction("MultiA2dp.ACTION.RESET_STATE_CHANGED");
        context.registerReceiver(this.f7080q, intentFilter, 2);
    }

    public final void y0() {
        if (this.f7067d || this.f7069f.asBinder() == null) {
            return;
        }
        try {
            this.f7069f.X0(this.f7075l);
            this.f7067d = true;
        } catch (RemoteException e2) {
            z0.e.b("LocalMiplayAudioManager", "registerMiPlayServiceCallback error", e2);
        }
    }

    public final void z0() {
        p0.d dVar = this.f7077n;
        if (dVar != null) {
            X(2, dVar.f());
        }
    }
}
