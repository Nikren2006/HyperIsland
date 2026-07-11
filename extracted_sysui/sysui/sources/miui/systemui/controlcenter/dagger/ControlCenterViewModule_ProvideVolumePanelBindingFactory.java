package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideVolumePanelBindingFactory implements e {
    private final a controlCenterSecondaryBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideVolumePanelBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterSecondaryBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideVolumePanelBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideVolumePanelBindingFactory(controlCenterViewModule, aVar);
    }

    public static VolumePanelBinding provideVolumePanelBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        return (VolumePanelBinding) i.d(controlCenterViewModule.provideVolumePanelBinding(controlCenterSecondaryBinding));
    }

    @Override // G0.a
    public VolumePanelBinding get() {
        return provideVolumePanelBinding(this.module, (ControlCenterSecondaryBinding) this.controlCenterSecondaryBindingProvider.get());
    }
}
