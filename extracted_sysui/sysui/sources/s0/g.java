package s0;

import com.miui.miplay.audio.data.MediaMetaData;
import com.xiaomi.cast.api.DeviceInfo;
import com.xiaomi.cast.api.IMiCastSDK;
import l0.InterfaceC0441d;
import l0.InterfaceC0442e;
import l0.InterfaceC0444g;

/* JADX INFO: loaded from: classes2.dex */
public class g extends InterfaceC0441d.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IMiCastSDK f6460a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DeviceInfo f6461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l f6462c;

    public g(DeviceInfo deviceInfo, IMiCastSDK iMiCastSDK, l lVar) {
        this.f6461b = deviceInfo;
        this.f6460a = iMiCastSDK;
        this.f6462c = lVar;
    }

    @Override // l0.InterfaceC0441d
    public int D0(int i2) {
        return t0.c.h(this.f6461b.getConnectState());
    }

    @Override // l0.InterfaceC0441d
    public int T(int i2) {
        return t0.c.e(this.f6461b.getConnectState());
    }

    @Override // l0.InterfaceC0441d
    public InterfaceC0444g T0() {
        return new InterfaceC0444g.a();
    }

    @Override // l0.InterfaceC0441d
    public int W0(int i2, MediaMetaData mediaMetaData) {
        z0.e.c("GoogleCast_AudioDeviceController", "selectDevice,");
        this.f6460a.connectDevice(this.f6461b);
        return 0;
    }

    public void a1(DeviceInfo deviceInfo) {
        this.f6461b = deviceInfo;
    }

    @Override // l0.InterfaceC0441d
    public int d0() {
        return this.f6461b.getVolumeMax();
    }

    @Override // l0.InterfaceC0441d
    public void e0(InterfaceC0442e interfaceC0442e) {
        this.f6462c.a(this.f6461b.getId(), interfaceC0442e);
    }

    @Override // l0.InterfaceC0441d
    public int g0() {
        return 0;
    }

    @Override // l0.InterfaceC0441d
    public int getVolume() {
        return this.f6461b.getVolume();
    }

    @Override // l0.InterfaceC0441d
    public int i0() {
        return D0(0);
    }

    @Override // l0.InterfaceC0441d
    public void n0(InterfaceC0442e interfaceC0442e) {
        this.f6462c.e(this.f6461b.getId(), interfaceC0442e);
    }

    @Override // l0.InterfaceC0441d
    public int q() {
        z0.e.c("GoogleCast_AudioDeviceController", "cancelSelectDevice,");
        this.f6460a.disConnectDevice(this.f6461b);
        return 0;
    }

    @Override // l0.InterfaceC0441d
    public void setStreamVolume(int i2, int i3) {
        z0.e.c("GoogleCast_AudioDeviceController", "setStreamVolume," + i2);
        this.f6460a.setDeviceVolume((double) i2);
    }

    @Override // l0.InterfaceC0441d
    public int t() {
        return T(0);
    }

    @Override // l0.InterfaceC0441d
    public int w0() {
        z0.e.c("GoogleCast_AudioDeviceController", "selectDevice,");
        this.f6460a.connectDevice(this.f6461b);
        return 0;
    }

    @Override // l0.InterfaceC0441d
    public int y(int i2) {
        z0.e.c("GoogleCast_AudioDeviceController", "selectDevice,");
        this.f6460a.connectDevice(this.f6461b);
        return 0;
    }
}
