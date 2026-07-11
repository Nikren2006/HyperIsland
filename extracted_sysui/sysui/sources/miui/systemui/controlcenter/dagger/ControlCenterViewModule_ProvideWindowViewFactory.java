package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideWindowViewFactory implements e {
    private final ControlCenterViewModule module;
    private final a windowViewProvider;

    public ControlCenterViewModule_ProvideWindowViewFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.windowViewProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideWindowViewFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideWindowViewFactory(controlCenterViewModule, aVar);
    }

    public static ControlCenterWindowViewImpl provideWindowView(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return (ControlCenterWindowViewImpl) i.d(controlCenterViewModule.provideWindowView(controlCenterWindowViewImpl));
    }

    @Override // G0.a
    public ControlCenterWindowViewImpl get() {
        return provideWindowView(this.module, (ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
