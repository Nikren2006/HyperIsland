package miui.systemui.plugins;

import F0.e;
import F0.i;
import G0.a;
import com.android.systemui.plugins.ActivityStarter;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule_ProvidesActivityStarterFactory implements e {
    private final a holderProvider;
    private final PluginDependencyModule module;

    public PluginDependencyModule_ProvidesActivityStarterFactory(PluginDependencyModule pluginDependencyModule, a aVar) {
        this.module = pluginDependencyModule;
        this.holderProvider = aVar;
    }

    public static PluginDependencyModule_ProvidesActivityStarterFactory create(PluginDependencyModule pluginDependencyModule, a aVar) {
        return new PluginDependencyModule_ProvidesActivityStarterFactory(pluginDependencyModule, aVar);
    }

    public static ActivityStarter providesActivityStarter(PluginDependencyModule pluginDependencyModule, PluginDependencyHolder<ActivityStarter> pluginDependencyHolder) {
        return (ActivityStarter) i.d(pluginDependencyModule.providesActivityStarter(pluginDependencyHolder));
    }

    @Override // G0.a
    public ActivityStarter get() {
        return providesActivityStarter(this.module, (PluginDependencyHolder) this.holderProvider.get());
    }
}
