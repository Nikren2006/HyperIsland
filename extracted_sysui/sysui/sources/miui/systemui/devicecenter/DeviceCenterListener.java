package miui.systemui.devicecenter;

import java.util.List;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;

/* JADX INFO: loaded from: classes3.dex */
public interface DeviceCenterListener {
    void onDeviceListChanged(List<DeviceInfoWrapper> list);
}
