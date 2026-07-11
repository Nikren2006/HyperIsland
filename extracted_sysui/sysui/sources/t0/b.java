package t0;

import com.xiaomi.cast.api.DeviceInfo;
import l0.C0440c;
import l0.InterfaceC0441d;
import m0.i;
import s0.g;

/* JADX INFO: loaded from: classes2.dex */
public class b extends i {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public DeviceInfo f6828l;

    public b(C0440c c0440c, DeviceInfo deviceInfo) {
        super(c0440c);
        this.f6828l = deviceInfo;
    }

    public void A(DeviceInfo deviceInfo) {
        this.f6828l = deviceInfo;
        InterfaceC0441d interfaceC0441d = this.f5303e;
        if (interfaceC0441d instanceof g) {
            ((g) interfaceC0441d).a1(deviceInfo);
        }
    }

    public String toString() {
        return "GoogleCastAudioDeviceInfo: deviceName:" + k().getName() + ",\ndeviceId:" + j() + ",\nconnectState:" + h() + ",\nselectStatus:" + l() + ",\nvoluem:" + o() + ",\nmExtra:" + k().getExtra();
    }

    public DeviceInfo z() {
        return this.f6828l;
    }
}
