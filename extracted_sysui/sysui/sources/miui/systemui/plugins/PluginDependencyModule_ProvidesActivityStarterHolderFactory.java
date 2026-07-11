package miui.systemui.plugins;

import F0.e;
import F0.i;
import G0.a;
import com.android.systemui.plugins.ActivityStarter;
import miui.systemui.plugins.PluginDependencyHolderImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule_ProvidesActivityStarterHolderFactory implements e {
    private final a holderFactoryProvider;
    private final PluginDependencyModule module;

    public PluginDependencyModule_ProvidesActivityStarterHolderFactory(PluginDependencyModule pluginDependencyModule, a aVar) {
        this.module = pluginDependencyModule;
        this.holderFactoryProvider = aVar;
    }

    public static PluginDependencyModule_ProvidesActivityStarterHolderFactory create(PluginDependencyModule pluginDependencyModule, a aVar) {
        return new PluginDependencyModule_ProvidesActivityStarterHolderFactory(pluginDependencyModule, aVar);
    }

    public static PluginDependencyHolder<ActivityStarter> providesActivityStarterHolder(PluginDependencyModule pluginDependencyModule, PluginDependencyHolderImpl.Factory factory) {
        return (PluginDependencyHolder) i.d(pluginDependencyModule.providesActivityStarterHolder(factory));
    }

    @Override // G0.a
    public PluginDependencyHolder<ActivityStarter> get() {
        return providesActivityStarterHolder(this.module, (PluginDependencyHolderImpl.Factory) this.holderFactoryProvider.get());
    }
}
