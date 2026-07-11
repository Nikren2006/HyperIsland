package miui.systemui.plugins;

import F0.e;
import F0.i;
import G0.a;
import miui.systemui.plugins.PluginDependencyHolder;
import miui.systemui.plugins.PluginDependencyHolderImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule_ProvidesPluginDependencyHolderFactoryFactory implements e {
    private final a implProvider;
    private final PluginDependencyModule module;

    public PluginDependencyModule_ProvidesPluginDependencyHolderFactoryFactory(PluginDependencyModule pluginDependencyModule, a aVar) {
        this.module = pluginDependencyModule;
        this.implProvider = aVar;
    }

    public static PluginDependencyModule_ProvidesPluginDependencyHolderFactoryFactory create(PluginDependencyModule pluginDependencyModule, a aVar) {
        return new PluginDependencyModule_ProvidesPluginDependencyHolderFactoryFactory(pluginDependencyModule, aVar);
    }

    public static PluginDependencyHolder.Factory providesPluginDependencyHolderFactory(PluginDependencyModule pluginDependencyModule, PluginDependencyHolderImpl.Factory factory) {
        return (PluginDependencyHolder.Factory) i.d(pluginDependencyModule.providesPluginDependencyHolderFactory(factory));
    }

    @Override // G0.a
    public PluginDependencyHolder.Factory get() {
        return providesPluginDependencyHolderFactory(this.module, (PluginDependencyHolderImpl.Factory) this.implProvider.get());
    }
}
