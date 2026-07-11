package miui.systemui.controlcenter.panel.main.header;

import F0.e;
import miui.systemui.controlcenter.panel.main.header.HeaderSpaceController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class HeaderSpaceController_Factory_Factory implements e {
    private final G0.a mainPanelHeaderControllerProvider;
    private final G0.a windowViewProvider;

    public HeaderSpaceController_Factory_Factory(G0.a aVar, G0.a aVar2) {
        this.windowViewProvider = aVar;
        this.mainPanelHeaderControllerProvider = aVar2;
    }

    public static HeaderSpaceController_Factory_Factory create(G0.a aVar, G0.a aVar2) {
        return new HeaderSpaceController_Factory_Factory(aVar, aVar2);
    }

    public static HeaderSpaceController.Factory newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, MainPanelHeaderController mainPanelHeaderController) {
        return new HeaderSpaceController.Factory(controlCenterWindowViewImpl, mainPanelHeaderController);
    }

    @Override // G0.a
    public HeaderSpaceController.Factory get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (MainPanelHeaderController) this.mainPanelHeaderControllerProvider.get());
    }
}
