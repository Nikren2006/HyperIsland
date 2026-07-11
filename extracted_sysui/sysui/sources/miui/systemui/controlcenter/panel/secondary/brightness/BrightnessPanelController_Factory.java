package miui.systemui.controlcenter.panel.secondary.brightness;

import F0.d;
import F0.e;
import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelController_Factory implements e {
    private final G0.a animatorProvider;
    private final G0.a bindingProvider;
    private final G0.a delegateProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a secondaryBindingProvider;

    public BrightnessPanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
        this.mainPanelControllerProvider = aVar3;
        this.delegateProvider = aVar4;
        this.animatorProvider = aVar5;
        this.lifecycleProvider = aVar6;
    }

    public static BrightnessPanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new BrightnessPanelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static BrightnessPanelController newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, BrightnessPanelBinding brightnessPanelBinding, E0.a aVar, BrightnessPanelDelegate brightnessPanelDelegate, BrightnessPanelAnimator brightnessPanelAnimator, Lifecycle lifecycle) {
        return new BrightnessPanelController(controlCenterSecondaryBinding, brightnessPanelBinding, aVar, brightnessPanelDelegate, brightnessPanelAnimator, lifecycle);
    }

    @Override // G0.a
    public BrightnessPanelController get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (BrightnessPanelBinding) this.bindingProvider.get(), d.a(this.mainPanelControllerProvider), (BrightnessPanelDelegate) this.delegateProvider.get(), (BrightnessPanelAnimator) this.animatorProvider.get(), (Lifecycle) this.lifecycleProvider.get());
    }
}
