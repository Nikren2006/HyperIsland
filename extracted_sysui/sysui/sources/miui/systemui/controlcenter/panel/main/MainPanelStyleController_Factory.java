package miui.systemui.controlcenter.panel.main;

import android.content.Context;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelStyleController_Factory implements F0.e {
    private final G0.a distributorProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a windowViewProvider;

    public MainPanelStyleController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.windowViewProvider = aVar;
        this.sysUIContextProvider = aVar2;
        this.distributorProvider = aVar3;
        this.mainPanelControllerProvider = aVar4;
    }

    public static MainPanelStyleController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new MainPanelStyleController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static MainPanelStyleController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Context context, E0.a aVar, E0.a aVar2) {
        return new MainPanelStyleController(controlCenterWindowViewImpl, context, aVar, aVar2);
    }

    @Override // G0.a
    public MainPanelStyleController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Context) this.sysUIContextProvider.get(), F0.d.a(this.distributorProvider), F0.d.a(this.mainPanelControllerProvider));
    }
}
