package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.databinding.SmartHomePanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideSmartHomeBindingFactory implements e {
    private final a controlCenterBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideSmartHomeBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideSmartHomeBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideSmartHomeBindingFactory(controlCenterViewModule, aVar);
    }

    public static SmartHomePanelBinding provideSmartHomeBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterBinding controlCenterBinding) {
        return (SmartHomePanelBinding) i.d(controlCenterViewModule.provideSmartHomeBinding(controlCenterBinding));
    }

    @Override // G0.a
    public SmartHomePanelBinding get() {
        return provideSmartHomeBinding(this.module, (ControlCenterBinding) this.controlCenterBindingProvider.get());
    }
}
