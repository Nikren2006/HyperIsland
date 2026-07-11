package miui.systemui.controlcenter.panel.main.devicecenter.entry;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.concurrent.Executor;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterEntryController_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bgHandlerProvider;
    private final G0.a deviceCenterCardControllerProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainExecutorProvider;
    private final G0.a mainPanelContentDistributorProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a miLinkControllerProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a userTrackerProvider;
    private final G0.a windowViewControllerProvider;
    private final G0.a windowViewProvider;

    public DeviceCenterEntryController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14) {
        this.windowViewProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.deviceCenterCardControllerProvider = aVar3;
        this.bgHandlerProvider = aVar4;
        this.mainExecutorProvider = aVar5;
        this.activityStarterProvider = aVar6;
        this.mainPanelContentDistributorProvider = aVar7;
        this.windowViewControllerProvider = aVar8;
        this.mainPanelStyleControllerProvider = aVar9;
        this.mainPanelModeControllerProvider = aVar10;
        this.miLinkControllerProvider = aVar11;
        this.hapticFeedbackProvider = aVar12;
        this.userTrackerProvider = aVar13;
        this.superSaveModeControllerProvider = aVar14;
    }

    public static DeviceCenterEntryController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14) {
        return new DeviceCenterEntryController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14);
    }

    public static DeviceCenterEntryController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, DeviceCenterCardController deviceCenterCardController, Handler handler, Executor executor, ActivityStarter activityStarter, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, MiLinkController miLinkController, HapticFeedback hapticFeedback, IUserTracker iUserTracker, SuperSaveModeController superSaveModeController) {
        return new DeviceCenterEntryController(controlCenterWindowViewImpl, lifecycle, deviceCenterCardController, handler, executor, activityStarter, aVar, aVar2, aVar3, aVar4, miLinkController, hapticFeedback, iUserTracker, superSaveModeController);
    }

    @Override // G0.a
    public DeviceCenterEntryController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (DeviceCenterCardController) this.deviceCenterCardControllerProvider.get(), (Handler) this.bgHandlerProvider.get(), (Executor) this.mainExecutorProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), F0.d.a(this.mainPanelContentDistributorProvider), F0.d.a(this.windowViewControllerProvider), F0.d.a(this.mainPanelStyleControllerProvider), F0.d.a(this.mainPanelModeControllerProvider), (MiLinkController) this.miLinkControllerProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get(), (IUserTracker) this.userTrackerProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get());
    }
}
