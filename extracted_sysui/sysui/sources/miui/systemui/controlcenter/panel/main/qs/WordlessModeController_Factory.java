package miui.systemui.controlcenter.panel.main.qs;

import android.os.Handler;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class WordlessModeController_Factory implements F0.e {
    private final G0.a bgHandlerProvider;
    private final G0.a distributorProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a qsListControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a windowViewProvider;

    public WordlessModeController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.windowViewProvider = aVar;
        this.distributorProvider = aVar2;
        this.bgHandlerProvider = aVar3;
        this.uiExecutorProvider = aVar4;
        this.qsListControllerProvider = aVar5;
        this.mainPanelControllerProvider = aVar6;
        this.mainPanelModeControllerProvider = aVar7;
    }

    public static WordlessModeController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new WordlessModeController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static WordlessModeController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, E0.a aVar, Handler handler, DelayableExecutor delayableExecutor, E0.a aVar2, E0.a aVar3, E0.a aVar4) {
        return new WordlessModeController(controlCenterWindowViewImpl, aVar, handler, delayableExecutor, aVar2, aVar3, aVar4);
    }

    @Override // G0.a
    public WordlessModeController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), F0.d.a(this.distributorProvider), (Handler) this.bgHandlerProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), F0.d.a(this.qsListControllerProvider), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.mainPanelModeControllerProvider));
    }
}
