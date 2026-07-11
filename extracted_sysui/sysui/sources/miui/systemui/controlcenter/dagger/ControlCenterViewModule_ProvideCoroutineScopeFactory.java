package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import g1.E;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideCoroutineScopeFactory implements e {
    private final a lifecycleProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideCoroutineScopeFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.lifecycleProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideCoroutineScopeFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideCoroutineScopeFactory(controlCenterViewModule, aVar);
    }

    public static E provideCoroutineScope(ControlCenterViewModule controlCenterViewModule, Lifecycle lifecycle) {
        return (E) i.d(controlCenterViewModule.provideCoroutineScope(lifecycle));
    }

    @Override // G0.a
    public E get() {
        return provideCoroutineScope(this.module, (Lifecycle) this.lifecycleProvider.get());
    }
}
