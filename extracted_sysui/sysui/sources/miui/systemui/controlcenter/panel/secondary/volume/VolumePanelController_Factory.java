package miui.systemui.controlcenter.panel.secondary.volume;

import F0.d;
import F0.e;
import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class VolumePanelController_Factory implements e {
    private final G0.a animatorProvider;
    private final G0.a bindingProvider;
    private final G0.a delegateProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a secondaryBindingProvider;

    public VolumePanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
        this.mainPanelControllerProvider = aVar3;
        this.delegateProvider = aVar4;
        this.animatorProvider = aVar5;
        this.lifecycleProvider = aVar6;
    }

    public static VolumePanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new VolumePanelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static VolumePanelController newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, VolumePanelBinding volumePanelBinding, E0.a aVar, VolumePanelDelegate volumePanelDelegate, VolumePanelAnimator volumePanelAnimator, Lifecycle lifecycle) {
        return new VolumePanelController(controlCenterSecondaryBinding, volumePanelBinding, aVar, volumePanelDelegate, volumePanelAnimator, lifecycle);
    }

    @Override // G0.a
    public VolumePanelController get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (VolumePanelBinding) this.bindingProvider.get(), d.a(this.mainPanelControllerProvider), (VolumePanelDelegate) this.delegateProvider.get(), (VolumePanelAnimator) this.animatorProvider.get(), (Lifecycle) this.lifecycleProvider.get());
    }
}
