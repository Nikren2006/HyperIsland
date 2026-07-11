package miui.systemui.controlcenter.panel.secondary.detail;

import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelCellAnimator_Factory implements F0.e {
    private final G0.a bindingProvider;
    private final G0.a secondaryBindingProvider;

    public DetailPanelCellAnimator_Factory(G0.a aVar, G0.a aVar2) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
    }

    public static DetailPanelCellAnimator_Factory create(G0.a aVar, G0.a aVar2) {
        return new DetailPanelCellAnimator_Factory(aVar, aVar2);
    }

    public static DetailPanelCellAnimator newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, DetailPanelBinding detailPanelBinding) {
        return new DetailPanelCellAnimator(controlCenterSecondaryBinding, detailPanelBinding);
    }

    @Override // G0.a
    public DetailPanelCellAnimator get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (DetailPanelBinding) this.bindingProvider.get());
    }
}
