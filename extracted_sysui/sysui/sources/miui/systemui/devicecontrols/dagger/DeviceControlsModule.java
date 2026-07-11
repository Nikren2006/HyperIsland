package miui.systemui.devicecontrols.dagger;

import miui.systemui.devicecontrols.DeviceControlsPresenter;
import miui.systemui.devicecontrols.DeviceControlsPresenterImpl;

/* JADX INFO: loaded from: classes3.dex */
public interface DeviceControlsModule {
    DeviceControlsPresenter bindDeviceControlsPresenter(DeviceControlsPresenterImpl deviceControlsPresenterImpl);
}
