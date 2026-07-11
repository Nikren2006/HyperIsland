package com.miui.circulate.device.api;

import androidx.annotation.WorkerThread;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface DeviceControlReceiver {

    public static final class DefaultImpls {
        public static /* synthetic */ void onDeviceListChange$default(DeviceControlReceiver deviceControlReceiver, List list, boolean z2, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onDeviceListChange");
            }
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            deviceControlReceiver.onDeviceListChange(list, z2);
        }
    }

    @WorkerThread
    void onDeviceChange(DeviceInfo deviceInfo);

    @WorkerThread
    void onDeviceListChange(List<DeviceInfo> list, boolean z2);
}
