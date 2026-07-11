package r0;

import android.bluetooth.BluetoothDevice;
import android.media.AudioManager;
import com.miui.miplay.audio.data.DeviceInfo;
import p0.h;

/* JADX INFO: loaded from: classes2.dex */
public class b extends h {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final DeviceInfo f6431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final BluetoothDevice f6432h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final B0.a f6433i;

    public b(BluetoothDevice bluetoothDevice, AudioManager audioManager, DeviceInfo deviceInfo, B0.a aVar) {
        super(bluetoothDevice.getAddress(), audioManager);
        this.f6432h = bluetoothDevice;
        this.f6431g = deviceInfo;
        this.f6433i = aVar;
    }

    @Override // p0.AbstractC0728a
    public DeviceInfo c() {
        return this.f6431g;
    }

    public String h() {
        return this.f6432h.getAddress();
    }
}
