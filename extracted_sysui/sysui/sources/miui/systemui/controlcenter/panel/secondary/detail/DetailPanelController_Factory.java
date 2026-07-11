package miui.systemui.controlcenter.panel.secondary.detail;

import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelController_Factory implements F0.e {
    private final G0.a animatorProvider;
    private final G0.a bindingProvider;
    private final G0.a delegateProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a secondaryBindingProvider;

    public DetailPanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
        this.mainPanelControllerProvider = aVar3;
        this.delegateProvider = aVar4;
        this.animatorProvider = aVar5;
        this.lifecycleProvider = aVar6;
    }

    public static DetailPanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new DetailPanelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static DetailPanelController newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, DetailPanelBinding detailPanelBinding, E0.a aVar, DetailPanelDelegate detailPanelDelegate, DetailPanelAnimator detailPanelAnimator, Lifecycle lifecycle) {
        return new DetailPanelController(controlCenterSecondaryBinding, detailPanelBinding, aVar, detailPanelDelegate, detailPanelAnimator, lifecycle);
    }

    @Override // G0.a
    public DetailPanelController get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (DetailPanelBinding) this.bindingProvider.get(), F0.d.a(this.mainPanelControllerProvider), (DetailPanelDelegate) this.delegateProvider.get(), (DetailPanelAnimator) this.animatorProvider.get(), (Lifecycle) this.lifecycleProvider.get());
    }
}
