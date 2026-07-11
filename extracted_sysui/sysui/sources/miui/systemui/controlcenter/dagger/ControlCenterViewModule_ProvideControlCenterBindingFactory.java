package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideControlCenterBindingFactory implements e {
    private final ControlCenterViewModule module;
    private final a windowViewProvider;

    public ControlCenterViewModule_ProvideControlCenterBindingFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.windowViewProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideControlCenterBindingFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideControlCenterBindingFactory(controlCenterViewModule, aVar);
    }

    public static ControlCenterBinding provideControlCenterBinding(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return (ControlCenterBinding) i.d(controlCenterViewModule.provideControlCenterBinding(controlCenterWindowViewImpl));
    }

    @Override // G0.a
    public ControlCenterBinding get() {
        return provideControlCenterBinding(this.module, (ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
