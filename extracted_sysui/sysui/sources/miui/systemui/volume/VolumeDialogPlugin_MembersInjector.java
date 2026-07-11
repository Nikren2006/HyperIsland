package miui.systemui.volume;

import E0.b;
import G0.a;
import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class VolumeDialogPlugin_MembersInjector implements b {
    private final a autoDensityControllerProvider;
    private final a controlCenterExpandRepositoryProvider;
    private final a pluginContextProvider;
    private final a pluginInstancesRepositoryProvider;
    private final a sysuiContextProvider;

    public VolumeDialogPlugin_MembersInjector(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
        this.controlCenterExpandRepositoryProvider = aVar5;
    }

    public static b create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        return new VolumeDialogPlugin_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static void injectControlCenterExpandRepository(VolumeDialogPlugin volumeDialogPlugin, ControlCenterExpandRepository controlCenterExpandRepository) {
        volumeDialogPlugin.controlCenterExpandRepository = controlCenterExpandRepository;
    }

    public void injectMembers(VolumeDialogPlugin volumeDialogPlugin) {
        PluginBase_MembersInjector.injectPluginInstancesRepository(volumeDialogPlugin, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        PluginBase_MembersInjector.injectAutoDensityController(volumeDialogPlugin, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        PluginBase_MembersInjector.injectPluginContext(volumeDialogPlugin, (Context) this.pluginContextProvider.get());
        PluginBase_MembersInjector.injectSysuiContext(volumeDialogPlugin, (Context) this.sysuiContextProvider.get());
        injectControlCenterExpandRepository(volumeDialogPlugin, (ControlCenterExpandRepository) this.controlCenterExpandRepositoryProvider.get());
    }
}
