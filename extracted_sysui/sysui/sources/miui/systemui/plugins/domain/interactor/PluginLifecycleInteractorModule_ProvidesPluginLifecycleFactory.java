package miui.systemui.plugins.domain.interactor;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginLifecycleInteractorModule_ProvidesPluginLifecycleFactory implements e {
    private final PluginLifecycleInteractorModule module;
    private final a pluginLifecycleInteractorProvider;

    public PluginLifecycleInteractorModule_ProvidesPluginLifecycleFactory(PluginLifecycleInteractorModule pluginLifecycleInteractorModule, a aVar) {
        this.module = pluginLifecycleInteractorModule;
        this.pluginLifecycleInteractorProvider = aVar;
    }

    public static PluginLifecycleInteractorModule_ProvidesPluginLifecycleFactory create(PluginLifecycleInteractorModule pluginLifecycleInteractorModule, a aVar) {
        return new PluginLifecycleInteractorModule_ProvidesPluginLifecycleFactory(pluginLifecycleInteractorModule, aVar);
    }

    public static Lifecycle providesPluginLifecycle(PluginLifecycleInteractorModule pluginLifecycleInteractorModule, PluginLifecycleInteractor pluginLifecycleInteractor) {
        return (Lifecycle) i.d(pluginLifecycleInteractorModule.providesPluginLifecycle(pluginLifecycleInteractor));
    }

    @Override // G0.a
    public Lifecycle get() {
        return providesPluginLifecycle(this.module, (PluginLifecycleInteractor) this.pluginLifecycleInteractorProvider.get());
    }
}
