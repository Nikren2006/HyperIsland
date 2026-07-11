package miui.systemui.devicecontrols;

import F0.d;
import F0.e;
import G0.a;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.PrefDeviceControlsController;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceControlsModelImpl_Factory implements e {
    private final a bgExecutorProvider;
    private final a controlsControllerProvider;
    private final a controlsListingControllerProvider;
    private final a controlsUiControllerProvider;
    private final a prefDeviceControlsControllerProvider;

    public DeviceControlsModelImpl_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        this.prefDeviceControlsControllerProvider = aVar;
        this.controlsListingControllerProvider = aVar2;
        this.controlsUiControllerProvider = aVar3;
        this.controlsControllerProvider = aVar4;
        this.bgExecutorProvider = aVar5;
    }

    public static DeviceControlsModelImpl_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        return new DeviceControlsModelImpl_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static DeviceControlsModelImpl newInstance(PrefDeviceControlsController prefDeviceControlsController, ControlsListingController controlsListingController, E0.a aVar, ControlsController controlsController, DelayableExecutor delayableExecutor) {
        return new DeviceControlsModelImpl(prefDeviceControlsController, controlsListingController, aVar, controlsController, delayableExecutor);
    }

    @Override // G0.a
    public DeviceControlsModelImpl get() {
        return newInstance((PrefDeviceControlsController) this.prefDeviceControlsControllerProvider.get(), (ControlsListingController) this.controlsListingControllerProvider.get(), d.a(this.controlsUiControllerProvider), (ControlsController) this.controlsControllerProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get());
    }
}
