package miui.systemui.controlcenter.panel.main.header;

import F0.d;
import F0.e;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterHeader;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelHeaderController_Factory implements e {
    private final G0.a controlCenterHeaderProvider;
    private final G0.a customizeHeaderProvider;
    private final G0.a detailHeaderControllerProvider;
    private final G0.a emptyHeaderControllerProvider;
    private final G0.a expandControllerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a msgHeaderProvider;
    private final G0.a panelContentDistributorProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a shadeHeaderControllerProvider;
    private final G0.a windowViewProvider;

    public MainPanelHeaderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12) {
        this.windowViewProvider = aVar;
        this.shadeHeaderControllerProvider = aVar2;
        this.msgHeaderProvider = aVar3;
        this.customizeHeaderProvider = aVar4;
        this.mainPanelControllerProvider = aVar5;
        this.expandControllerProvider = aVar6;
        this.panelContentDistributorProvider = aVar7;
        this.controlCenterHeaderProvider = aVar8;
        this.detailHeaderControllerProvider = aVar9;
        this.emptyHeaderControllerProvider = aVar10;
        this.secondaryPanelRouterProvider = aVar11;
        this.mainPanelModeControllerProvider = aVar12;
    }

    public static MainPanelHeaderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12) {
        return new MainPanelHeaderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12);
    }

    public static MainPanelHeaderController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, ShadeHeaderController shadeHeaderController, MessageHeaderController messageHeaderController, CustomizeHeaderController customizeHeaderController, E0.a aVar, E0.a aVar2, E0.a aVar3, ControlCenterHeader controlCenterHeader, EmptyHeaderController emptyHeaderController, EmptyHeaderMirrorController emptyHeaderMirrorController, SecondaryPanelRouter secondaryPanelRouter, E0.a aVar4) {
        return new MainPanelHeaderController(controlCenterWindowViewImpl, shadeHeaderController, messageHeaderController, customizeHeaderController, aVar, aVar2, aVar3, controlCenterHeader, emptyHeaderController, emptyHeaderMirrorController, secondaryPanelRouter, aVar4);
    }

    @Override // G0.a
    public MainPanelHeaderController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (ShadeHeaderController) this.shadeHeaderControllerProvider.get(), (MessageHeaderController) this.msgHeaderProvider.get(), (CustomizeHeaderController) this.customizeHeaderProvider.get(), d.a(this.mainPanelControllerProvider), d.a(this.expandControllerProvider), d.a(this.panelContentDistributorProvider), (ControlCenterHeader) this.controlCenterHeaderProvider.get(), (EmptyHeaderController) this.detailHeaderControllerProvider.get(), (EmptyHeaderMirrorController) this.emptyHeaderControllerProvider.get(), (SecondaryPanelRouter) this.secondaryPanelRouterProvider.get(), d.a(this.mainPanelModeControllerProvider));
    }
}
