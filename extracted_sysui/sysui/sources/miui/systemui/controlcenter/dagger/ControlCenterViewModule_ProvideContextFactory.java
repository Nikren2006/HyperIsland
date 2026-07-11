package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import android.content.Context;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideContextFactory implements e {
    private final ControlCenterViewModule module;
    private final a windowViewProvider;

    public ControlCenterViewModule_ProvideContextFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.windowViewProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideContextFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideContextFactory(controlCenterViewModule, aVar);
    }

    public static Context provideContext(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return (Context) i.d(controlCenterViewModule.provideContext(controlCenterWindowViewImpl));
    }

    @Override // G0.a
    public Context get() {
        return provideContext(this.module, (ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
