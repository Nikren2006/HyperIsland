package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import G0.a;
import g1.E;
import miui.systemui.plugins.domain.interactor.PluginLifecycleInteractor;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_PluginScopeFactory implements e {
    private final a pluginLifecycleInteractorProvider;
    private final a runningAsPluginProvider;

    public CoroutinesModule_PluginScopeFactory(a aVar, a aVar2) {
        this.runningAsPluginProvider = aVar;
        this.pluginLifecycleInteractorProvider = aVar2;
    }

    public static CoroutinesModule_PluginScopeFactory create(a aVar, a aVar2) {
        return new CoroutinesModule_PluginScopeFactory(aVar, aVar2);
    }

    public static E pluginScope(boolean z2, PluginLifecycleInteractor pluginLifecycleInteractor) {
        return (E) i.d(CoroutinesModule.INSTANCE.pluginScope(z2, pluginLifecycleInteractor));
    }

    @Override // G0.a
    public E get() {
        return pluginScope(((Boolean) this.runningAsPluginProvider.get()).booleanValue(), (PluginLifecycleInteractor) this.pluginLifecycleInteractorProvider.get());
    }
}
