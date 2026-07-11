package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.MediaPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideMediaPanelBindingFactory implements e {
    private final a controlCenterSecondaryBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideMediaPanelBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterSecondaryBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideMediaPanelBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideMediaPanelBindingFactory(controlCenterViewModule, aVar);
    }

    public static MediaPanelBinding provideMediaPanelBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        return (MediaPanelBinding) i.d(controlCenterViewModule.provideMediaPanelBinding(controlCenterSecondaryBinding));
    }

    @Override // G0.a
    public MediaPanelBinding get() {
        return provideMediaPanelBinding(this.module, (ControlCenterSecondaryBinding) this.controlCenterSecondaryBindingProvider.get());
    }
}
