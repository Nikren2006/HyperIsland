package miui.systemui.devicecenter.devices;

import com.miui.circulate.device.api.Constant;
import com.miui.circulate.device.api.DeviceInfo;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceSortsProxyKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean compare(DeviceInfo deviceInfo, DeviceInfo deviceInfo2) {
        return n.c(deviceInfo.getId(), deviceInfo2.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAudioCastDevice(DeviceInfo deviceInfo) {
        Constant.State state = Constant.State.INSTANCE;
        return state.check(deviceInfo.getState(), 128) || state.check(deviceInfo.getState(), 2048);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSyncDevice(DeviceInfo deviceInfo) {
        Constant.State state = Constant.State.INSTANCE;
        return state.check(deviceInfo.getState(), 2) || state.check(deviceInfo.getState(), 4) || state.check(deviceInfo.getState(), 16) || state.check(deviceInfo.getState(), 32) || state.check(deviceInfo.getState(), 512) || state.check(deviceInfo.getState(), 1024) || state.check(deviceInfo.getState(), 8);
    }
}
