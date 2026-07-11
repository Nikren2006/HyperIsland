package miui.systemui.controlcenter.panel.main.devicecontrol;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.ActivityStarter;
import java.util.Optional;
import java.util.concurrent.Executor;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.devicecontrols.DeviceControlsPresenter;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceControlsEntryController_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bgHandlerProvider;
    private final G0.a deviceControlsPresenterProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainExecutorProvider;
    private final G0.a mainPanelContentDistributorProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a miLinkControllerProvider;
    private final G0.a modeControllerProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public DeviceControlsEntryController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14) {
        this.windowViewProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.bgHandlerProvider = aVar3;
        this.mainExecutorProvider = aVar4;
        this.secondaryPanelRouterProvider = aVar5;
        this.deviceControlsPresenterProvider = aVar6;
        this.mainPanelContentDistributorProvider = aVar7;
        this.mainPanelStyleControllerProvider = aVar8;
        this.mainPanelModeControllerProvider = aVar9;
        this.miLinkControllerProvider = aVar10;
        this.hapticFeedbackProvider = aVar11;
        this.activityStarterProvider = aVar12;
        this.windowViewControllerProvider = aVar13;
        this.modeControllerProvider = aVar14;
    }

    public static DeviceControlsEntryController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14) {
        return new DeviceControlsEntryController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14);
    }

    public static DeviceControlsEntryController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, Handler handler, Executor executor, E0.a aVar, Optional<DeviceControlsPresenter> optional, E0.a aVar2, E0.a aVar3, E0.a aVar4, MiLinkController miLinkController, HapticFeedback hapticFeedback, ActivityStarter activityStarter, E0.a aVar5, E0.a aVar6) {
        return new DeviceControlsEntryController(controlCenterWindowViewImpl, lifecycle, handler, executor, aVar, optional, aVar2, aVar3, aVar4, miLinkController, hapticFeedback, activityStarter, aVar5, aVar6);
    }

    @Override // G0.a
    public DeviceControlsEntryController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (Handler) this.bgHandlerProvider.get(), (Executor) this.mainExecutorProvider.get(), F0.d.a(this.secondaryPanelRouterProvider), (Optional) this.deviceControlsPresenterProvider.get(), F0.d.a(this.mainPanelContentDistributorProvider), F0.d.a(this.mainPanelStyleControllerProvider), F0.d.a(this.mainPanelModeControllerProvider), (MiLinkController) this.miLinkControllerProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), F0.d.a(this.windowViewControllerProvider), F0.d.a(this.modeControllerProvider));
    }
}
