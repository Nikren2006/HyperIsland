package miui.systemui.plugins;

import E0.b;
import G0.a;
import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginBase_MembersInjector implements b {
    private final a autoDensityControllerProvider;
    private final a pluginContextProvider;
    private final a pluginInstancesRepositoryProvider;
    private final a sysuiContextProvider;

    public PluginBase_MembersInjector(a aVar, a aVar2, a aVar3, a aVar4) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
    }

    public static b create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new PluginBase_MembersInjector(aVar, aVar2, aVar3, aVar4);
    }

    public static void injectAutoDensityController(PluginBase pluginBase, AutoDensityControllerImpl autoDensityControllerImpl) {
        pluginBase.autoDensityController = autoDensityControllerImpl;
    }

    public static void injectPluginContext(PluginBase pluginBase, Context context) {
        pluginBase.pluginContext = context;
    }

    public static void injectPluginInstancesRepository(PluginBase pluginBase, PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl) {
        pluginBase.pluginInstancesRepository = pluginInstancesRepositoryImpl;
    }

    @SystemUI
    public static void injectSysuiContext(PluginBase pluginBase, Context context) {
        pluginBase.sysuiContext = context;
    }

    public void injectMembers(PluginBase pluginBase) {
        injectPluginInstancesRepository(pluginBase, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        injectAutoDensityController(pluginBase, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        injectPluginContext(pluginBase, (Context) this.pluginContextProvider.get());
        injectSysuiContext(pluginBase, (Context) this.sysuiContextProvider.get());
    }
}
