package miui.systemui.controlcenter.windowview;

import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterSuperPowerController_Factory implements F0.e {
    private final G0.a expandControllerProvider;
    private final G0.a shadeSwitchControllerProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a windowViewProvider;

    public ControlCenterSuperPowerController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.windowViewProvider = aVar;
        this.superSaveModeControllerProvider = aVar2;
        this.shadeSwitchControllerProvider = aVar3;
        this.expandControllerProvider = aVar4;
    }

    public static ControlCenterSuperPowerController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new ControlCenterSuperPowerController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static ControlCenterSuperPowerController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, SuperSaveModeController superSaveModeController, ShadeSwitchController shadeSwitchController, ControlCenterExpandController controlCenterExpandController) {
        return new ControlCenterSuperPowerController(controlCenterWindowViewImpl, superSaveModeController, shadeSwitchController, controlCenterExpandController);
    }

    @Override // G0.a
    public ControlCenterSuperPowerController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get(), (ShadeSwitchController) this.shadeSwitchControllerProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get());
    }
}
