package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.databinding.MainPanelHeaderBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory implements e {
    private final a controlCenterBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideMainPanelHeaderBindingFactory(controlCenterViewModule, aVar);
    }

    public static MainPanelHeaderBinding provideMainPanelHeaderBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterBinding controlCenterBinding) {
        return (MainPanelHeaderBinding) i.d(controlCenterViewModule.provideMainPanelHeaderBinding(controlCenterBinding));
    }

    @Override // G0.a
    public MainPanelHeaderBinding get() {
        return provideMainPanelHeaderBinding(this.module, (ControlCenterBinding) this.controlCenterBindingProvider.get());
    }
}
