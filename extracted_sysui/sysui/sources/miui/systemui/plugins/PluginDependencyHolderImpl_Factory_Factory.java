package miui.systemui.plugins;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.plugins.PluginDependencyHolderImpl;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyHolderImpl_Factory_Factory implements e {
    private final a pluginInstancesRepositoryProvider;
    private final a uiScopeProvider;

    public PluginDependencyHolderImpl_Factory_Factory(a aVar, a aVar2) {
        this.uiScopeProvider = aVar;
        this.pluginInstancesRepositoryProvider = aVar2;
    }

    public static PluginDependencyHolderImpl_Factory_Factory create(a aVar, a aVar2) {
        return new PluginDependencyHolderImpl_Factory_Factory(aVar, aVar2);
    }

    public static PluginDependencyHolderImpl.Factory newInstance(E e2, PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl) {
        return new PluginDependencyHolderImpl.Factory(e2, pluginInstancesRepositoryImpl);
    }

    @Override // G0.a
    public PluginDependencyHolderImpl.Factory get() {
        return newInstance((E) this.uiScopeProvider.get(), (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
    }
}
