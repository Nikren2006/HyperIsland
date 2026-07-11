package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.view.LayoutInflater;
import miui.systemui.controlcenter.databinding.MainPanelMsgHeaderBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory implements e {
    private final a layoutInflaterProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.layoutInflaterProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideMainPanelMsgHeaderBindingFactory(controlCenterViewModule, aVar);
    }

    public static MainPanelMsgHeaderBinding provideMainPanelMsgHeaderBinding(ControlCenterViewModule controlCenterViewModule, LayoutInflater layoutInflater) {
        return (MainPanelMsgHeaderBinding) i.d(controlCenterViewModule.provideMainPanelMsgHeaderBinding(layoutInflater));
    }

    @Override // G0.a
    public MainPanelMsgHeaderBinding get() {
        return provideMainPanelMsgHeaderBinding(this.module, (LayoutInflater) this.layoutInflaterProvider.get());
    }
}
