package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.os.Handler;
import com.android.systemui.plugins.ActivityStarter;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.devicecenter.DeviceCenterController;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterCardController_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a deviceCenterControllerProvider;
    private final G0.a entryControllerProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a uiHandlerProvider;
    private final G0.a windowViewProvider;

    public DeviceCenterCardController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.windowViewProvider = aVar;
        this.uiHandlerProvider = aVar2;
        this.deviceCenterControllerProvider = aVar3;
        this.activityStarterProvider = aVar4;
        this.entryControllerProvider = aVar5;
        this.hapticFeedbackProvider = aVar6;
    }

    public static DeviceCenterCardController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new DeviceCenterCardController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static DeviceCenterCardController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Handler handler, DeviceCenterController deviceCenterController, ActivityStarter activityStarter, E0.a aVar, HapticFeedback hapticFeedback) {
        return new DeviceCenterCardController(controlCenterWindowViewImpl, handler, deviceCenterController, activityStarter, aVar, hapticFeedback);
    }

    @Override // G0.a
    public DeviceCenterCardController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Handler) this.uiHandlerProvider.get(), (DeviceCenterController) this.deviceCenterControllerProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), F0.d.a(this.entryControllerProvider), (HapticFeedback) this.hapticFeedbackProvider.get());
    }
}
