package miui.systemui.controlcenter.panel.main.footer;

import F0.e;
import G0.a;
import miui.systemui.controlcenter.panel.main.footer.FooterSpaceController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FooterSpaceController_Factory_Factory implements e {
    private final a windowViewProvider;

    public FooterSpaceController_Factory_Factory(a aVar) {
        this.windowViewProvider = aVar;
    }

    public static FooterSpaceController_Factory_Factory create(a aVar) {
        return new FooterSpaceController_Factory_Factory(aVar);
    }

    public static FooterSpaceController.Factory newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return new FooterSpaceController.Factory(controlCenterWindowViewImpl);
    }

    @Override // G0.a
    public FooterSpaceController.Factory get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
