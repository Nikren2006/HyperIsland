package miui.systemui.controlcenter.panel.customize;

import F0.e;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizePanelLinkageController_Factory implements e {
    private final G0.a bindingProvider;

    public CustomizePanelLinkageController_Factory(G0.a aVar) {
        this.bindingProvider = aVar;
    }

    public static CustomizePanelLinkageController_Factory create(G0.a aVar) {
        return new CustomizePanelLinkageController_Factory(aVar);
    }

    public static CustomizePanelLinkageController newInstance(CustomizerPanelBinding customizerPanelBinding) {
        return new CustomizePanelLinkageController(customizerPanelBinding);
    }

    @Override // G0.a
    public CustomizePanelLinkageController get() {
        return newInstance((CustomizerPanelBinding) this.bindingProvider.get());
    }
}
