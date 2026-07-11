package miui.systemui.controlcenter.panel;

import F0.d;
import F0.e;
import G0.a;
import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SecondaryPanelRouter_Factory implements e {
    private final a brightnessPanelControllerProvider;
    private final a customizePanelControllerProvider;
    private final a detailPanelControllerProvider;
    private final a deviceControlPanelControllerProvider;
    private final a lifecycleProvider;
    private final a mainPanelAnimControllerProvider;
    private final a mediaPanelControllerProvider;
    private final a screenshotProvider;
    private final a shadeSwitchControllerProvider;
    private final a touchControllerProvider;
    private final a volumePanelControllerProvider;
    private final a windowViewControllerProvider;
    private final a windowViewProvider;

    public SecondaryPanelRouter_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9, a aVar10, a aVar11, a aVar12, a aVar13) {
        this.lifecycleProvider = aVar;
        this.windowViewProvider = aVar2;
        this.screenshotProvider = aVar3;
        this.touchControllerProvider = aVar4;
        this.mainPanelAnimControllerProvider = aVar5;
        this.deviceControlPanelControllerProvider = aVar6;
        this.customizePanelControllerProvider = aVar7;
        this.detailPanelControllerProvider = aVar8;
        this.mediaPanelControllerProvider = aVar9;
        this.brightnessPanelControllerProvider = aVar10;
        this.volumePanelControllerProvider = aVar11;
        this.windowViewControllerProvider = aVar12;
        this.shadeSwitchControllerProvider = aVar13;
    }

    public static SecondaryPanelRouter_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9, a aVar10, a aVar11, a aVar12, a aVar13) {
        return new SecondaryPanelRouter_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13);
    }

    public static SecondaryPanelRouter newInstance(Lifecycle lifecycle, ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, SecondaryPanelTouchController secondaryPanelTouchController, MainPanelAnimController mainPanelAnimController, E0.a aVar2, E0.a aVar3, E0.a aVar4, E0.a aVar5, E0.a aVar6, E0.a aVar7, E0.a aVar8, E0.a aVar9) {
        return new SecondaryPanelRouter(lifecycle, controlCenterWindowViewImpl, aVar, secondaryPanelTouchController, mainPanelAnimController, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    @Override // G0.a
    public SecondaryPanelRouter get() {
        return newInstance((Lifecycle) this.lifecycleProvider.get(), (ControlCenterWindowViewImpl) this.windowViewProvider.get(), d.a(this.screenshotProvider), (SecondaryPanelTouchController) this.touchControllerProvider.get(), (MainPanelAnimController) this.mainPanelAnimControllerProvider.get(), d.a(this.deviceControlPanelControllerProvider), d.a(this.customizePanelControllerProvider), d.a(this.detailPanelControllerProvider), d.a(this.mediaPanelControllerProvider), d.a(this.brightnessPanelControllerProvider), d.a(this.volumePanelControllerProvider), d.a(this.windowViewControllerProvider), d.a(this.shadeSwitchControllerProvider));
    }
}
