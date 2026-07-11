package miui.systemui.controlcenter.panel.main.qs;

import android.os.Handler;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class EditButtonController_Factory implements F0.e {
    private final G0.a bgHandlerProvider;
    private final G0.a distributorProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a qsListControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a shadeSwitchControllerProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a windowViewProvider;

    public EditButtonController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.windowViewProvider = aVar;
        this.distributorProvider = aVar2;
        this.bgHandlerProvider = aVar3;
        this.uiExecutorProvider = aVar4;
        this.qsListControllerProvider = aVar5;
        this.mainPanelControllerProvider = aVar6;
        this.superSaveModeControllerProvider = aVar7;
        this.secondaryPanelRouterProvider = aVar8;
        this.shadeSwitchControllerProvider = aVar9;
    }

    public static EditButtonController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new EditButtonController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static EditButtonController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, Handler handler, DelayableExecutor delayableExecutor, E0.a aVar2, E0.a aVar3, SuperSaveModeController superSaveModeController, E0.a aVar4, E0.a aVar5) {
        return new EditButtonController(controlCenterWindowViewImpl, aVar, handler, delayableExecutor, aVar2, aVar3, superSaveModeController, aVar4, aVar5);
    }

    @Override // G0.a
    public EditButtonController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.distributorProvider), (Handler) this.bgHandlerProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), F0.d.a(this.qsListControllerProvider), F0.d.a(this.mainPanelControllerProvider), (SuperSaveModeController) this.superSaveModeControllerProvider.get(), F0.d.a(this.secondaryPanelRouterProvider), F0.d.a(this.shadeSwitchControllerProvider));
    }
}
