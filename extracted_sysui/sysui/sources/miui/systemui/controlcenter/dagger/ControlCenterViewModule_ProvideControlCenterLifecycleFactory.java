package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideControlCenterLifecycleFactory implements e {
    private final ControlCenterViewModule module;
    private final a windowViewProvider;

    public ControlCenterViewModule_ProvideControlCenterLifecycleFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.windowViewProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideControlCenterLifecycleFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideControlCenterLifecycleFactory(controlCenterViewModule, aVar);
    }

    public static Lifecycle provideControlCenterLifecycle(ControlCenterViewModule controlCenterViewModule, ControlCenterWindowViewImpl controlCenterWindowViewImpl) {
        return (Lifecycle) i.d(controlCenterViewModule.provideControlCenterLifecycle(controlCenterWindowViewImpl));
    }

    @Override // G0.a
    public Lifecycle get() {
        return provideControlCenterLifecycle(this.module, (ControlCenterWindowViewImpl) this.windowViewProvider.get());
    }
}
