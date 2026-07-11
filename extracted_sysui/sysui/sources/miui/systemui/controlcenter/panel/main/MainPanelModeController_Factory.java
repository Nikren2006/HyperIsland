package miui.systemui.controlcenter.panel.main;

import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelModeController_Factory implements F0.e {
    private final G0.a distributorProvider;
    private final G0.a expandControllerProvider;
    private final G0.a leftMainPanelProvider;
    private final G0.a lifecycleProvider;
    private final G0.a rightMainPanelProvider;
    private final G0.a windowViewProvider;

    public MainPanelModeController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.windowViewProvider = aVar;
        this.rightMainPanelProvider = aVar2;
        this.leftMainPanelProvider = aVar3;
        this.distributorProvider = aVar4;
        this.lifecycleProvider = aVar5;
        this.expandControllerProvider = aVar6;
    }

    public static MainPanelModeController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new MainPanelModeController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static MainPanelModeController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, RecyclerView recyclerView, RecyclerView recyclerView2, MainPanelContentDistributor mainPanelContentDistributor, Lifecycle lifecycle, ControlCenterExpandController controlCenterExpandController) {
        return new MainPanelModeController(controlCenterWindowViewImpl, recyclerView, recyclerView2, mainPanelContentDistributor, lifecycle, controlCenterExpandController);
    }

    @Override // G0.a
    public MainPanelModeController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (RecyclerView) this.rightMainPanelProvider.get(), (RecyclerView) this.leftMainPanelProvider.get(), (MainPanelContentDistributor) this.distributorProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get());
    }
}
