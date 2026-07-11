package miui.systemui.controlcenter.panel.main;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.controlcenter.panel.main.header.MainPanelHeaderController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelTouchController_Factory implements F0.e {
    private final G0.a expandControllerProvider;
    private final G0.a gestureDispatcherProvider;
    private final G0.a headerControllerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public MainPanelTouchController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.windowViewProvider = aVar;
        this.windowViewControllerProvider = aVar2;
        this.expandControllerProvider = aVar3;
        this.gestureDispatcherProvider = aVar4;
        this.statusBarStateControllerProvider = aVar5;
        this.mainPanelControllerProvider = aVar6;
        this.headerControllerProvider = aVar7;
    }

    public static MainPanelTouchController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new MainPanelTouchController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static MainPanelTouchController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, ControlCenterExpandController controlCenterExpandController, GestureDispatcher gestureDispatcher, StatusBarStateController statusBarStateController, E0.a aVar2, MainPanelHeaderController mainPanelHeaderController) {
        return new MainPanelTouchController(controlCenterWindowViewImpl, aVar, controlCenterExpandController, gestureDispatcher, statusBarStateController, aVar2, mainPanelHeaderController);
    }

    @Override // G0.a
    public MainPanelTouchController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.windowViewControllerProvider), (ControlCenterExpandController) this.expandControllerProvider.get(), (GestureDispatcher) this.gestureDispatcherProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), F0.d.a(this.mainPanelControllerProvider), (MainPanelHeaderController) this.headerControllerProvider.get());
    }
}
