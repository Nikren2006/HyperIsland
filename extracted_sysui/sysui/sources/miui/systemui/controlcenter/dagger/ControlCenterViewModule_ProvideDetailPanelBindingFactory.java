package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideDetailPanelBindingFactory implements e {
    private final a controlCenterSecondaryBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideDetailPanelBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterSecondaryBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideDetailPanelBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideDetailPanelBindingFactory(controlCenterViewModule, aVar);
    }

    public static DetailPanelBinding provideDetailPanelBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        return (DetailPanelBinding) i.d(controlCenterViewModule.provideDetailPanelBinding(controlCenterSecondaryBinding));
    }

    @Override // G0.a
    public DetailPanelBinding get() {
        return provideDetailPanelBinding(this.module, (ControlCenterSecondaryBinding) this.controlCenterSecondaryBindingProvider.get());
    }
}
