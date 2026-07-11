package com.android.systemui;

import android.util.Log;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayDeviceChangeListener implements m0.y {
    private final m0.i device;

    public MiPlayDeviceChangeListener(m0.i device) {
        kotlin.jvm.internal.n.g(device, "device");
        this.device = device;
    }

    public final m0.i getDevice() {
        return this.device;
    }

    public void onDeviceConnectionStateChange(int i2) {
    }

    @Override // m0.y
    public void onDeviceInfoChange(DeviceInfo p02) {
        kotlin.jvm.internal.n.g(p02, "p0");
    }

    public void onDeviceSelectStatusChange(int i2) {
    }

    @Override // m0.y
    public void onVolumeChange(int i2, int i3) {
        Log.d(MiPlayDeviceVolumeCache.TAG, "onVolumeChange(): device.deviceInfo.name = " + this.device.k().getName() + ", volume = " + i2);
        MiPlayDeviceVolumeCache.INSTANCE.putValue(this.device, Integer.valueOf(i2));
    }

    @Override // m0.y
    public void onDeviceConnectionStateChange(int i2, int i3) {
    }

    @Override // m0.y
    public void onDeviceSelectStatusChange(int i2, int i3) {
    }
}
