package com.xiaomi.cast.api;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceStatusListener {
    void onCastSessionStatusChange(int i2);

    void onDeviceStatusChanged(List<DeviceInfo> list);

    void onDeviceTakeOver();

    @Deprecated
    void onDeviceVolumeChanged(String str, double d2);
}
