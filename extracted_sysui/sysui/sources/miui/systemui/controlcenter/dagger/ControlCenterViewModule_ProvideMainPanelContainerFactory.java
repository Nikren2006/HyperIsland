package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.widget.LinearLayout;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideMainPanelContainerFactory implements e {
    private final a controlCenterBindingProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideMainPanelContainerFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.controlCenterBindingProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideMainPanelContainerFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideMainPanelContainerFactory(controlCenterViewModule, aVar);
    }

    public static LinearLayout provideMainPanelContainer(ControlCenterViewModule controlCenterViewModule, ControlCenterBinding controlCenterBinding) {
        return (LinearLayout) i.d(controlCenterViewModule.provideMainPanelContainer(controlCenterBinding));
    }

    @Override // G0.a
    public LinearLayout get() {
        return provideMainPanelContainer(this.module, (ControlCenterBinding) this.controlCenterBindingProvider.get());
    }
}
