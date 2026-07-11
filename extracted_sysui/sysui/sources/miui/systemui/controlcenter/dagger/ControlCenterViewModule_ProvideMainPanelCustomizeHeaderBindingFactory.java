package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.LayoutInflater;
import miui.systemui.controlcenter.databinding.MainPanelCustomizeHeaderBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory implements e {
    private final a layoutInflaterProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.layoutInflaterProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideMainPanelCustomizeHeaderBindingFactory(controlCenterViewModule, aVar);
    }

    public static MainPanelCustomizeHeaderBinding provideMainPanelCustomizeHeaderBinding(ControlCenterViewModule controlCenterViewModule, LayoutInflater layoutInflater) {
        return (MainPanelCustomizeHeaderBinding) i.d(controlCenterViewModule.provideMainPanelCustomizeHeaderBinding(layoutInflater));
    }

    @Override // G0.a
    public MainPanelCustomizeHeaderBinding get() {
        return provideMainPanelCustomizeHeaderBinding(this.module, (LayoutInflater) this.layoutInflaterProvider.get());
    }
}
