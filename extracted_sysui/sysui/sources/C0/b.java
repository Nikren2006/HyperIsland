package C0;

import B0.c;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public class b implements B0.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f41a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f45e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f42b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c.a f43c = new C0002b(this);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f44d = new AtomicBoolean(false);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c f46f = new c();

    public static class a extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f47a;

        public a(c.a aVar) {
            this.f47a = new WeakReference(aVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            c.a aVar = (c.a) this.f47a.get();
            String action = intent.getAction();
            if (action == null) {
                k0.b.e("AudioShareStateReceiver", "action is null, return;");
            }
            k0.b.e("AudioShareStateReceiver", "receiver state change, action is: " + action);
            if (aVar == null) {
                return;
            }
            switch (action) {
                case "MultiA2dp.ACTION.RESET_STATE_CHANGED":
                    aVar.f((BluetoothDevice) intent.getParcelableExtra("device"), intent.getIntExtra("MultiA2dp.EXTRA.STATE", -1));
                    break;
                case "MultiA2dp.ACTION.VOLUME_CHANGED":
                    aVar.b(intent.getIntExtra("MultiA2dp.EXTRA.VOLUME_VALUE", 0));
                    break;
                case "com.milink.service.FASTCONNECT_NOTIFICATION":
                    k0.b.e("AudioShareStateReceiver", "onPairingStateChange");
                    aVar.c();
                    break;
                case "MultiA2dp.ACTION.SETVOLUME_CHANGED":
                    aVar.b(intent.getIntExtra("MultiA2dp.EXTRA.VOLUME_VALUE", 0));
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: C0.b$b, reason: collision with other inner class name */
    public static class C0002b implements c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f48a;

        public C0002b(b bVar) {
            this.f48a = new WeakReference(bVar);
        }

        @Override // B0.c.a
        public void b(int i2) {
            k0.b.e("AudioShared_ListenerStub", "onAudioVolumeChange" + i2);
            b bVar = (b) this.f48a.get();
            if (bVar != null) {
                bVar.f46f.a(i2);
                bVar.k(i2);
            }
        }

        @Override // B0.c.a
        public void c() {
            k0.b.e("AudioShared_ListenerStub", "onAudioSharePairing");
            b bVar = (b) this.f48a.get();
            if (bVar != null) {
                bVar.i();
            }
        }

        @Override // B0.c.a
        public void f(BluetoothDevice bluetoothDevice, int i2) {
            k0.b.e("AudioShared_ListenerStub", "onAudioSharedStateChanged, state" + i2);
            b bVar = (b) this.f48a.get();
            if (bVar != null) {
                bVar.j(bluetoothDevice, i2);
            }
        }
    }

    public b(Context context) {
        this.f41a = context;
    }

    @Override // B0.c
    public void a(c.a aVar) {
        k0.b.e("AudioShareRepository", "registerListener: listener, " + aVar.hashCode() + ", hasRegistered: " + this.f44d.get());
        this.f42b.add(aVar);
        if (this.f44d.compareAndSet(false, true)) {
            l();
        }
    }

    @Override // B0.c
    public boolean b() {
        k0.b.e("AudioShareRepository", "deviceSupportAudioShare: " + B0.b.j());
        return B0.b.j();
    }

    @Override // B0.c
    public void c(c.a aVar) {
        k0.b.e("AudioShareRepository", "unregisterListener: listener, " + aVar.hashCode() + ", listeners: " + this.f42b.size() + ", hasRegistered: " + this.f44d.get());
        this.f42b.remove(aVar);
        if (this.f42b.isEmpty() && this.f44d.compareAndSet(true, false)) {
            m();
            this.f44d.set(false);
        }
    }

    @Override // B0.c
    public boolean d(String str) {
        String string = Settings.Secure.getString(this.f41a.getContentResolver(), "miui_store_audio_share_device_address");
        try {
            k0.b.e("AudioShareRepository", "isAudioShareActive, localMac:" + str.substring(0, 3) + ", remoteMac: " + string.substring(0, 3));
        } catch (Exception unused) {
            k0.b.e("AudioShareRepository", "remoteMac is null");
        }
        return TextUtils.equals(str, string);
    }

    public final void i() {
        Iterator it = this.f42b.iterator();
        while (it.hasNext()) {
            ((c.a) it.next()).c();
        }
    }

    public final void j(BluetoothDevice bluetoothDevice, int i2) {
        Iterator it = this.f42b.iterator();
        while (it.hasNext()) {
            ((c.a) it.next()).f(bluetoothDevice, i2);
        }
    }

    public final void k(int i2) {
        Iterator it = this.f42b.iterator();
        while (it.hasNext()) {
            ((c.a) it.next()).b(i2);
        }
    }

    public final void l() {
        this.f45e = new a(this.f43c);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MultiA2dp.ACTION.SETVOLUME_CHANGED");
        intentFilter.addAction("MultiA2dp.ACTION.RESET_STATE_CHANGED");
        intentFilter.addAction("com.milink.service.FASTCONNECT_NOTIFICATION");
        intentFilter.addAction("MultiA2dp.ACTION.VOLUME_CHANGED");
        this.f41a.registerReceiver(this.f45e, intentFilter, 2);
    }

    public final void m() {
        a aVar = this.f45e;
        if (aVar != null) {
            this.f41a.unregisterReceiver(aVar);
        }
    }
}
