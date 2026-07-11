package miui.systemui.controlcenter.panel.devicecontrol;

import java.util.Optional;
import miui.systemui.controlcenter.databinding.SmartHomePanelBinding;
import miui.systemui.devicecontrols.DeviceControlsPresenter;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceControlPanelController_Factory implements F0.e {
    private final G0.a bindingProvider;
    private final G0.a deviceControlsPresenterProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a screenshotProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a windowViewControllerProvider;

    public DeviceControlPanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.bindingProvider = aVar;
        this.secondaryPanelRouterProvider = aVar2;
        this.screenshotProvider = aVar3;
        this.deviceControlsPresenterProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
        this.windowViewControllerProvider = aVar6;
    }

    public static DeviceControlPanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new DeviceControlPanelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static DeviceControlPanelController newInstance(SmartHomePanelBinding smartHomePanelBinding, E0.a aVar, E0.a aVar2, Optional<DeviceControlsPresenter> optional, E0.a aVar3, E0.a aVar4) {
        return new DeviceControlPanelController(smartHomePanelBinding, aVar, aVar2, optional, aVar3, aVar4);
    }

    @Override // G0.a
    public DeviceControlPanelController get() {
        return newInstance((SmartHomePanelBinding) this.bindingProvider.get(), F0.d.a(this.secondaryPanelRouterProvider), F0.d.a(this.screenshotProvider), (Optional) this.deviceControlsPresenterProvider.get(), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.windowViewControllerProvider));
    }
}
