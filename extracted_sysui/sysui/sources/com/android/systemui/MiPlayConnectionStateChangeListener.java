package com.android.systemui;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: loaded from: classes.dex */
public final class MiPlayConnectionStateChangeListener implements m0.y {
    private final m0.i device;

    public MiPlayConnectionStateChangeListener(m0.i device) {
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
    }

    @Override // m0.y
    public void onDeviceConnectionStateChange(int i2, int i3) {
        MiPlayDeviceConnectionStateCache miPlayDeviceConnectionStateCache = MiPlayDeviceConnectionStateCache.INSTANCE;
        Log.d(miPlayDeviceConnectionStateCache.getTAG(), "onDeviceConnectionStateChange(): device.deviceInfo.name =" + this.device.k().getName() + " ,state =" + i2 + ",mediaType=" + i3 + " ");
        if (i2 == 1) {
            MiPlayDetailViewModel.INSTANCE.getSelectedDevicesTimeMap().put(this.device, Long.valueOf(System.currentTimeMillis()));
        } else {
            MiPlayDetailViewModel.INSTANCE.getSelectedDevicesTimeMap().remove(this.device);
        }
        MutableLiveData<H0.i> liveData = miPlayDeviceConnectionStateCache.getLiveData(this.device);
        if (liveData != null && liveData.getValue() != null) {
            if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(i3))) {
                H0.i value = liveData.getValue();
                kotlin.jvm.internal.n.d(value);
                Integer numValueOf = Integer.valueOf(i2);
                H0.i value2 = liveData.getValue();
                kotlin.jvm.internal.n.d(value2);
                liveData.setValue(value.c(numValueOf, value2.e()));
            } else {
                H0.i value3 = liveData.getValue();
                kotlin.jvm.internal.n.d(value3);
                H0.i value4 = liveData.getValue();
                kotlin.jvm.internal.n.d(value4);
                liveData.setValue(value3.c(value4.d(), Integer.valueOf(i2)));
            }
            miPlayDeviceConnectionStateCache.putValue(this.device, liveData.getValue());
        } else if (MediaTypeUtils.INSTANCE.isAudioType(Integer.valueOf(i3))) {
            miPlayDeviceConnectionStateCache.putValue(this.device, new H0.i(Integer.valueOf(i2), 2));
        } else {
            miPlayDeviceConnectionStateCache.putValue(this.device, new H0.i(2, Integer.valueOf(i2)));
        }
        MiPlayDetailViewModel.INSTANCE.updateDeviceListNotCache();
    }

    @Override // m0.y
    public void onDeviceSelectStatusChange(int i2, int i3) {
    }
}
