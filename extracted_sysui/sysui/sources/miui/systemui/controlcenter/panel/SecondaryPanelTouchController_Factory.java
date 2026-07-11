package miui.systemui.controlcenter.panel;

import F0.d;
import F0.e;
import G0.a;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SecondaryPanelTouchController_Factory implements e {
    private final a windowViewControllerProvider;
    private final a windowViewProvider;

    public SecondaryPanelTouchController_Factory(a aVar, a aVar2) {
        this.windowViewProvider = aVar;
        this.windowViewControllerProvider = aVar2;
    }

    public static SecondaryPanelTouchController_Factory create(a aVar, a aVar2) {
        return new SecondaryPanelTouchController_Factory(aVar, aVar2);
    }

    public static SecondaryPanelTouchController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar) {
        return new SecondaryPanelTouchController(controlCenterWindowViewImpl, aVar);
    }

    @Override // G0.a
    public SecondaryPanelTouchController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), d.a(this.windowViewControllerProvider));
    }
}
