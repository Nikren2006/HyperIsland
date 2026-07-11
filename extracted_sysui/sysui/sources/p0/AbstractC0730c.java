package p0;

import B0.c;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.miui.miplay.audio.data.DeviceInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import q0.InterfaceC0733a;
import r0.C0735a;
import z0.AbstractC0776a;
import z0.AbstractC0777b;

/* JADX INFO: renamed from: p0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0730c implements C0735a.b, c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BluetoothAdapter f6348b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f6350d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f6352f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final B0.a f6353g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r0.b f6355i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final b f6356j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final HandlerThread f6357k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AudioManager f6358l;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f6361o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final r0.c f6362p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final i f6363q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final g f6364r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final InterfaceC0733a f6365s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0735a.C0166a f6366t;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f6347a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f6349c = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BluetoothA2dp f6351e = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AtomicBoolean f6354h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public BluetoothProfile f6359m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f6360n = new Object();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final AtomicBoolean f6367u = new AtomicBoolean(false);

    /* JADX INFO: renamed from: p0.c$b */
    public static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0730c f6368a;

        public b(Looper looper, AbstractC0730c abstractC0730c) {
            super(looper);
            this.f6368a = abstractC0730c;
        }

        public final void b(long j2) {
            removeMessages(1);
            removeMessages(2);
            sendMessageDelayed(obtainMessage(1), j2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AbstractC0730c abstractC0730c = this.f6368a;
            if (abstractC0730c == null) {
                return;
            }
            try {
                int i2 = message.what;
                if (i2 == 1 || i2 == 2) {
                    abstractC0730c.u();
                }
            } catch (Exception e2) {
                z0.e.b("BluetoothDeviceManager", "BaseBleHandler handleMessage", e2);
            }
        }
    }

    /* JADX INFO: renamed from: p0.c$c, reason: collision with other inner class name */
    public class C0161c implements BluetoothProfile.ServiceListener {
        public C0161c() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i2, BluetoothProfile bluetoothProfile) {
            try {
                if (i2 == 2) {
                    z0.e.c("BluetoothDeviceManager", "a2dp connect");
                    AbstractC0730c.this.f6351e = (BluetoothA2dp) bluetoothProfile;
                } else {
                    if (i2 != 22) {
                        return;
                    }
                    z0.e.c("BluetoothDeviceManager", "LE_AUDIO connect");
                    AbstractC0730c.this.f6359m = bluetoothProfile;
                }
            } catch (Exception e2) {
                z0.e.b("BluetoothDeviceManager", "onServiceConnected", e2);
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i2) {
            try {
                if (i2 == 2) {
                    z0.e.c("BluetoothDeviceManager", "a2dp disconnect");
                    AbstractC0730c.this.f6351e = null;
                } else {
                    if (i2 != 22) {
                        return;
                    }
                    z0.e.c("BluetoothDeviceManager", "LE_AUDIO disconnect");
                    AbstractC0730c.this.f6359m = null;
                }
            } catch (Exception e2) {
                z0.e.b("BluetoothDeviceManager", "onServiceDisconnected", e2);
            }
        }
    }

    /* JADX INFO: renamed from: p0.c$d */
    public interface d {
        void a(String str, int i2);

        void e(r0.b bVar);

        boolean g();
    }

    /* JADX INFO: renamed from: p0.c$e */
    public class e extends BroadcastReceiver {
        public e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            z0.e.c("BluetoothDeviceManager", "RefreshActionReceiver onReceive: " + intent.getAction());
            AbstractC0730c.this.w(500L);
        }
    }

    public AbstractC0730c(Context context, i iVar, g gVar) {
        this.f6352f = context;
        this.f6363q = iVar;
        this.f6364r = gVar;
        this.f6353g = new B0.a(context, this);
        HandlerThread handlerThread = new HandlerThread("BleHandler");
        this.f6357k = handlerThread;
        handlerThread.start();
        this.f6356j = new b(handlerThread.getLooper(), this);
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        this.f6358l = audioManager;
        this.f6362p = new r0.c(AbstractC0777b.b(), audioManager);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.f6348b = defaultAdapter;
        m();
        this.f6365s = new C0735a(context, defaultAdapter);
        C0735a.C0166a c0166a = new C0735a.C0166a(context);
        this.f6366t = c0166a;
        c0166a.a(this);
        y();
        v();
    }

    public final void A() {
        BluetoothA2dp bluetoothA2dp = this.f6351e;
        if (bluetoothA2dp != null) {
            this.f6348b.closeProfileProxy(2, bluetoothA2dp);
        }
        BluetoothProfile bluetoothProfile = this.f6359m;
        if (bluetoothProfile != null) {
            this.f6348b.closeProfileProxy(22, bluetoothProfile);
        }
    }

    public final void B(boolean z2, boolean z3) {
        this.f6367u.compareAndSet(z2, z3);
    }

    public boolean C() {
        return this.f6353g.b();
    }

    public void D(d dVar) {
        if (this.f6347a == dVar) {
            this.f6347a = null;
        }
    }

    public final void E() {
        e eVar = this.f6350d;
        if (eVar != null) {
            this.f6352f.unregisterReceiver(eVar);
            this.f6350d = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [p0.c] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [p0.c] */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    public boolean F() {
        boolean z2;
        synchronized (this.f6360n) {
            try {
                this.f6361o = "";
                z2 = false;
                try {
                    if (this.f6351e != null) {
                        Method method = Class.forName("android.bluetooth.BluetoothA2dp").getMethod("getActiveDevice", null);
                        method.setAccessible(true);
                        BluetoothDevice bluetoothDevice = (BluetoothDevice) method.invoke(this.f6351e, null);
                        if (bluetoothDevice == null) {
                            Iterator it = this.f6349c.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                AbstractC0728a abstractC0728a = (AbstractC0728a) it.next();
                                if (abstractC0728a instanceof r0.b) {
                                    String strH = ((r0.b) abstractC0728a).h();
                                    if (t(strH)) {
                                        try {
                                            this.f6361o = strH;
                                            z2 = true;
                                            break;
                                        } catch (Exception e2) {
                                            z2 = true;
                                            z0.e.b("BluetoothDeviceManager", "updateActiveBt failed ", e2);
                                        }
                                    }
                                }
                            }
                        } else {
                            this.f6361o = bluetoothDevice.getAddress();
                        }
                    }
                } catch (Exception e3) {
                    z0.e.b("BluetoothDeviceManager", "updateActiveBt failed ", e3);
                }
                this = "mCurrentBtAddress: " + z0.i.c(this.f6361o);
                z0.e.c("BluetoothDeviceManager", this);
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    @Override // B0.c.a
    public void b(int i2) {
        for (AbstractC0728a abstractC0728a : this.f6349c) {
            if (abstractC0728a.c().isAudioSharing()) {
                this.f6363q.a(abstractC0728a.b(), i2);
                return;
            }
        }
    }

    @Override // B0.c.a
    public void c() {
    }

    @Override // r0.C0735a.b
    public void d(BluetoothDevice bluetoothDevice, int i2) {
        this.f6365s.a(bluetoothDevice);
        B(false, true);
        v();
    }

    @Override // B0.c.a
    public void f(BluetoothDevice bluetoothDevice, int i2) {
        z0.e.c("BluetoothDeviceManager", "onAudioSharedStateChanged, state:" + i2);
        if (i2 == 1) {
            this.f6363q.d();
        }
        this.f6354h.set(true);
        v();
    }

    public final r0.b k(BluetoothDevice bluetoothDevice) {
        int deviceClass;
        int majorDeviceClass;
        String name = TextUtils.isEmpty(bluetoothDevice.getAlias()) ? bluetoothDevice.getName() : bluetoothDevice.getAlias();
        Bundle bundle = new Bundle();
        BluetoothClass bluetoothClassA = AbstractC0776a.a(bluetoothDevice);
        if (bluetoothClassA != null) {
            deviceClass = bluetoothClassA.getDeviceClass();
            majorDeviceClass = bluetoothClassA.getMajorDeviceClass();
            bundle.putParcelable(DeviceInfo.EXTRA_KEY_BLUETOOTH_CLASS, bluetoothClassA);
        } else {
            deviceClass = 0;
            majorDeviceClass = 0;
        }
        boolean zE = AbstractC0776a.e(bluetoothClassA, false);
        boolean zD = this.f6353g.d(bluetoothDevice.getAddress());
        boolean zD2 = AbstractC0776a.d(bluetoothDevice);
        boolean zF = AbstractC0776a.f(bluetoothDevice);
        int iB = this.f6365s.b(bluetoothDevice);
        bundle.putBoolean(DeviceInfo.EXTRA_KEY_IS_BLUETOOTH_HEADSET, false);
        bundle.putBoolean(DeviceInfo.Extra_KEY_IS_BLUETOOTH_SPEAKER, zE);
        bundle.putBoolean(DeviceInfo.EXTRA_IS_AUDIO_SHARE, zD);
        bundle.putBoolean(DeviceInfo.EXTRA_KEY_IS_XIAOMI_CAR, zD2);
        bundle.putBoolean(DeviceInfo.EXTRA_SUPPORT_ABSOLUTE_VOLUME, zF);
        bundle.putString("bluetoothMac", bluetoothDevice.getAddress());
        bundle.putString(DeviceInfo.EXTRA_KEY_MI_ACCOUNT_ID, "LOCAL");
        bundle.putInt(DeviceInfo.EXTRA_KEY_BLE_DEVICE_TYPE, iB);
        bundle.putBoolean(DeviceInfo.EXTRA_KEY_IS_BLUETOOTH_GLASSES, false);
        z0.e.c("BoundedBluetoothDevice", "name:" + name + ", address:" + z0.i.a(bluetoothDevice.getAddress()) + ", majorClass:" + Integer.toHexString(majorDeviceClass) + ", deviceClass:" + Integer.toHexString(deviceClass) + ", isHeadset:false, isSpeaker:" + zE + ", isMiCar:" + zD2 + ", isGlasses:false, isAudioSharing:" + zD + ", isBleDeviceType:" + iB + ", isSupportAbsoluteVolume:" + zF);
        return new r0.b(bluetoothDevice, this.f6358l, new DeviceInfo(name, null, 2, bundle), this.f6353g);
    }

    public Collection l() {
        return this.f6349c;
    }

    public final void m() {
        C0161c c0161c = new C0161c();
        this.f6348b.getProfileProxy(this.f6352f, c0161c, 2);
        this.f6348b.getProfileProxy(this.f6352f, c0161c, 1);
        if (this.f6348b.isLeAudioSupported() == 10) {
            this.f6348b.getProfileProxy(this.f6352f, c0161c, 22);
        }
    }

    public final List n() {
        ArrayList arrayList = new ArrayList();
        BluetoothA2dp bluetoothA2dp = this.f6351e;
        return bluetoothA2dp != null ? bluetoothA2dp.getConnectedDevices() : arrayList;
    }

    public final IntentFilter o() {
        IntentFilter intentFilter = new IntentFilter();
        String[] strArr = {"android.bluetooth.device.action.BOND_STATE_CHANGED", "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED", "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED", "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED", "android.bluetooth.adapter.action.STATE_CHANGED", "android.intent.action.HEADSET_PLUG"};
        for (int i2 = 0; i2 < 6; i2++) {
            intentFilter.addAction(strArr[i2]);
        }
        return intentFilter;
    }

    public boolean p(r0.b bVar) {
        r0.b bVar2 = this.f6355i;
        return (bVar2 == null || TextUtils.isEmpty(bVar2.h()) || !TextUtils.equals(bVar.h(), this.f6355i.h())) ? false : true;
    }

    public final boolean q() {
        return this.f6367u.get();
    }

    public final boolean r(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile = this.f6359m;
        return bluetoothProfile != null && bluetoothProfile.getConnectionState(bluetoothDevice) == 2;
    }

    public boolean s(String str) {
        boolean z2;
        synchronized (this.f6360n) {
            try {
                String str2 = this.f6361o;
                z2 = (str2 == null || TextUtils.isEmpty(str2) || !TextUtils.equals(str, this.f6361o)) ? false : true;
            } finally {
            }
        }
        return z2;
    }

    public final boolean t(String str) {
        String strSubstring;
        String strSubstring2;
        try {
            if (this.f6359m == null) {
                return false;
            }
            String string = Settings.Global.getString(this.f6352f.getContentResolver(), "three_mac_for_ble_f");
            if (string == null || string.length() < 54 || !string.contains(str)) {
                strSubstring = "00:00:00:00:00:00";
                strSubstring2 = "00:00:00:00:00:00";
            } else {
                int iIndexOf = string.indexOf(str);
                strSubstring = string.substring(iIndexOf + 18, iIndexOf + 35);
                strSubstring2 = string.substring(iIndexOf + 36, iIndexOf + 53);
            }
            BluetoothAdapter bluetoothAdapter = this.f6348b;
            if (bluetoothAdapter != null) {
                BluetoothDevice remoteDevice = bluetoothAdapter.getRemoteDevice(strSubstring);
                BluetoothDevice remoteDevice2 = this.f6348b.getRemoteDevice(strSubstring2);
                if (remoteDevice == null && remoteDevice2 == null) {
                    return false;
                }
                if (!r(remoteDevice)) {
                    if (!r(remoteDevice2)) {
                        return false;
                    }
                }
                return true;
            }
        } catch (Exception e2) {
            z0.e.b("BluetoothDeviceManager", "isLeAudioConnected failed ", e2);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u() {
        /*
            Method dump skipped, instruction units count: 730
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p0.AbstractC0730c.u():void");
    }

    public void v() {
        w(500L);
    }

    public void w(long j2) {
        z0.e.c("BluetoothDeviceManager", "refreshBluetoothDeviceDelayed: " + j2);
        this.f6356j.b(j2);
    }

    public void x(d dVar) {
        if (this.f6347a == null) {
            this.f6347a = dVar;
        }
    }

    public final void y() {
        if (this.f6350d == null) {
            this.f6350d = new e();
        }
        try {
            this.f6352f.registerReceiver(this.f6350d, o(), 2);
        } catch (Exception e2) {
            z0.e.b("BluetoothDeviceManager", "registerRefreshActionReceiver failed ", e2);
        }
    }

    public void z() {
        try {
            this.f6357k.quitSafely();
            A();
            E();
            this.f6366t.b(this);
            this.f6353g.e();
        } catch (Exception unused) {
        }
    }
}
