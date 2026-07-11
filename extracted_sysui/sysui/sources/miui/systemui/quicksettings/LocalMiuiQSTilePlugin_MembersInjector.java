package miui.systemui.quicksettings;

import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.flashlight.MiFlashlightOperationReceiver;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes4.dex */
public final class LocalMiuiQSTilePlugin_MembersInjector implements E0.b {
    private final G0.a autoDensityControllerProvider;
    private final G0.a mMiFlashlightOperationReceiverProvider;
    private final G0.a mMiLinkControllerProvider;
    private final G0.a pluginContextProvider;
    private final G0.a pluginInstancesRepositoryProvider;
    private final G0.a sysuiContextProvider;

    public LocalMiuiQSTilePlugin_MembersInjector(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
        this.mMiLinkControllerProvider = aVar5;
        this.mMiFlashlightOperationReceiverProvider = aVar6;
    }

    public static E0.b create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new LocalMiuiQSTilePlugin_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static void injectMMiFlashlightOperationReceiver(LocalMiuiQSTilePlugin localMiuiQSTilePlugin, MiFlashlightOperationReceiver miFlashlightOperationReceiver) {
        localMiuiQSTilePlugin.mMiFlashlightOperationReceiver = miFlashlightOperationReceiver;
    }

    public static void injectMMiLinkController(LocalMiuiQSTilePlugin localMiuiQSTilePlugin, MiLinkController miLinkController) {
        localMiuiQSTilePlugin.mMiLinkController = miLinkController;
    }

    public void injectMembers(LocalMiuiQSTilePlugin localMiuiQSTilePlugin) {
        PluginBase_MembersInjector.injectPluginInstancesRepository(localMiuiQSTilePlugin, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        PluginBase_MembersInjector.injectAutoDensityController(localMiuiQSTilePlugin, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        PluginBase_MembersInjector.injectPluginContext(localMiuiQSTilePlugin, (Context) this.pluginContextProvider.get());
        PluginBase_MembersInjector.injectSysuiContext(localMiuiQSTilePlugin, (Context) this.sysuiContextProvider.get());
        injectMMiLinkController(localMiuiQSTilePlugin, (MiLinkController) this.mMiLinkControllerProvider.get());
        injectMMiFlashlightOperationReceiver(localMiuiQSTilePlugin, (MiFlashlightOperationReceiver) this.mMiFlashlightOperationReceiverProvider.get());
    }
}
