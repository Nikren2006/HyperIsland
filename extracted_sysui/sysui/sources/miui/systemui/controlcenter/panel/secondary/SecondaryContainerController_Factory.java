package miui.systemui.controlcenter.panel.secondary;

import com.android.systemui.plugins.miui.controlcenter.ControlCenterSecondary;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.panel.SecondaryPanelTouchController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SecondaryContainerController_Factory implements F0.e {
    private final G0.a controlCenterSecondaryProvider;
    private final G0.a secondaryBindingProvider;
    private final G0.a touchControllerProvider;
    private final G0.a windowViewProvider;

    public SecondaryContainerController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.windowViewProvider = aVar;
        this.controlCenterSecondaryProvider = aVar2;
        this.secondaryBindingProvider = aVar3;
        this.touchControllerProvider = aVar4;
    }

    public static SecondaryContainerController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new SecondaryContainerController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static SecondaryContainerController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, ControlCenterSecondary controlCenterSecondary, ControlCenterSecondaryBinding controlCenterSecondaryBinding, SecondaryPanelTouchController secondaryPanelTouchController) {
        return new SecondaryContainerController(controlCenterWindowViewImpl, controlCenterSecondary, controlCenterSecondaryBinding, secondaryPanelTouchController);
    }

    @Override // G0.a
    public SecondaryContainerController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (ControlCenterSecondary) this.controlCenterSecondaryProvider.get(), (ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (SecondaryPanelTouchController) this.touchControllerProvider.get());
    }
}
