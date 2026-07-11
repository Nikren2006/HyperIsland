package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory implements e {
    private final a lifecycleProvider;
    private final ControlCenterViewModule module;

    public ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory(ControlCenterViewModule controlCenterViewModule, a aVar) {
        this.module = controlCenterViewModule;
        this.lifecycleProvider = aVar;
    }

    public static ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory create(ControlCenterViewModule controlCenterViewModule, a aVar) {
        return new ControlCenterViewModule_ProvideLifecycleCoroutineScopeFactory(controlCenterViewModule, aVar);
    }

    public static LifecycleCoroutineScope provideLifecycleCoroutineScope(ControlCenterViewModule controlCenterViewModule, Lifecycle lifecycle) {
        return (LifecycleCoroutineScope) i.d(controlCenterViewModule.provideLifecycleCoroutineScope(lifecycle));
    }

    @Override // G0.a
    public LifecycleCoroutineScope get() {
        return provideLifecycleCoroutineScope(this.module, (Lifecycle) this.lifecycleProvider.get());
    }
}
