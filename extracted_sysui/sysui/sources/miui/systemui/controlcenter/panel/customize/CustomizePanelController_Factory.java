package miui.systemui.controlcenter.panel.customize;

import F0.d;
import F0.e;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizePanelController_Factory implements e {
    private final G0.a bindingProvider;
    private final G0.a linkageControllerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a screenshotProvider;

    public CustomizePanelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.bindingProvider = aVar;
        this.mainPanelControllerProvider = aVar2;
        this.linkageControllerProvider = aVar3;
        this.screenshotProvider = aVar4;
    }

    public static CustomizePanelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new CustomizePanelController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static CustomizePanelController newInstance(CustomizerPanelBinding customizerPanelBinding, E0.a aVar, CustomizePanelLinkageController customizePanelLinkageController, E0.a aVar2) {
        return new CustomizePanelController(customizerPanelBinding, aVar, customizePanelLinkageController, aVar2);
    }

    @Override // G0.a
    public CustomizePanelController get() {
        return newInstance((CustomizerPanelBinding) this.bindingProvider.get(), d.a(this.mainPanelControllerProvider), (CustomizePanelLinkageController) this.linkageControllerProvider.get(), d.a(this.screenshotProvider));
    }
}
