package com.xiaomi.cast.api;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public interface IMiCastSDK {
    void connectDevice(DeviceInfo deviceInfo);

    void disConnectAll();

    void disConnectDevice(DeviceInfo deviceInfo);

    void enterUI();

    void exitUI();

    void init(Context context, IInitListener iInitListener);

    void setDeviceChangeListener(DeviceStatusListener deviceStatusListener);

    void setDeviceVolume(double d2);

    void setMediaMetaData(MediaMetaData mediaMetaData, String str);

    void setPlaybackState(int i2);

    void setServiceStatusListener(ServiceStatusListener serviceStatusListener);

    void startScan();

    void stopScan();

    void unInit();
}
