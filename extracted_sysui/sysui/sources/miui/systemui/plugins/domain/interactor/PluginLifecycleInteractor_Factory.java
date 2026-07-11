package miui.systemui.plugins.domain.interactor;

import F0.e;
import G0.a;
import L0.g;
import android.content.Context;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginLifecycleInteractor_Factory implements e {
    private final a mainContextProvider;
    private final a pluginContextProvider;
    private final a pluginInstancesRepositoryProvider;
    private final a sysuiContextProvider;

    public PluginLifecycleInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.mainContextProvider = aVar;
        this.pluginContextProvider = aVar2;
        this.sysuiContextProvider = aVar3;
        this.pluginInstancesRepositoryProvider = aVar4;
    }

    public static PluginLifecycleInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new PluginLifecycleInteractor_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static PluginLifecycleInteractor newInstance(g gVar, Context context, Context context2, PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl) {
        return new PluginLifecycleInteractor(gVar, context, context2, pluginInstancesRepositoryImpl);
    }

    @Override // G0.a
    public PluginLifecycleInteractor get() {
        return newInstance((g) this.mainContextProvider.get(), (Context) this.pluginContextProvider.get(), (Context) this.sysuiContextProvider.get(), (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
    }
}
