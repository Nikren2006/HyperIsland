package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideCustomizerBindingFactory implements e {
    private final a controlCenterBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideCustomizerBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideCustomizerBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideCustomizerBindingFactory(controlCenterViewModule, aVar);
    }

    public static CustomizerPanelBinding provideCustomizerBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterBinding controlCenterBinding) {
        return (CustomizerPanelBinding) i.d(controlCenterViewModule.provideCustomizerBinding(controlCenterBinding));
    }

    @Override // G0.a
    public CustomizerPanelBinding get() {
        return provideCustomizerBinding(this.module, (ControlCenterBinding) this.controlCenterBindingProvider.get());
    }
}
