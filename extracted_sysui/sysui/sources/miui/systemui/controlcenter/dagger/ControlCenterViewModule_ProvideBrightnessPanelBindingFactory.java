package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideBrightnessPanelBindingFactory implements e {
    private final a controlCenterSecondaryBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideBrightnessPanelBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterSecondaryBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideBrightnessPanelBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideBrightnessPanelBindingFactory(controlCenterViewModule, aVar);
    }

    public static BrightnessPanelBinding provideBrightnessPanelBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        return (BrightnessPanelBinding) i.d(controlCenterViewModule.provideBrightnessPanelBinding(controlCenterSecondaryBinding));
    }

    @Override // G0.a
    public BrightnessPanelBinding get() {
        return provideBrightnessPanelBinding(this.module, (ControlCenterSecondaryBinding) this.controlCenterSecondaryBindingProvider.get());
    }
}
