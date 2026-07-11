package miui.systemui.controlcenter;

import E0.b;
import G0.a;
import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.controlcenter.policy.SecurityController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewCreator;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;
import miui.systemui.util.BlurUtilsExt;
import miui.systemui.util.MiLinkController;

/* JADX INFO: loaded from: classes.dex */
public final class MiuiControlCenter_MembersInjector implements b {
    private final a autoDensityControllerProvider;
    private final a blurUtilsExtProvider;
    private final a miLinkControllerProvider;
    private final a pluginContextProvider;
    private final a pluginInstancesRepositoryProvider;
    private final a securityControllerProvider;
    private final a sysuiContextProvider;
    private final a windowViewCreatorProvider;

    public MiuiControlCenter_MembersInjector(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
        this.windowViewCreatorProvider = aVar5;
        this.miLinkControllerProvider = aVar6;
        this.securityControllerProvider = aVar7;
        this.blurUtilsExtProvider = aVar8;
    }

    public static b create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8) {
        return new MiuiControlCenter_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static void injectBlurUtilsExt(MiuiControlCenter miuiControlCenter, BlurUtilsExt blurUtilsExt) {
        miuiControlCenter.blurUtilsExt = blurUtilsExt;
    }

    public static void injectMiLinkController(MiuiControlCenter miuiControlCenter, MiLinkController miLinkController) {
        miuiControlCenter.miLinkController = miLinkController;
    }

    public static void injectSecurityController(MiuiControlCenter miuiControlCenter, SecurityController securityController) {
        miuiControlCenter.securityController = securityController;
    }

    public static void injectWindowViewCreator(MiuiControlCenter miuiControlCenter, ControlCenterWindowViewCreator controlCenterWindowViewCreator) {
        miuiControlCenter.windowViewCreator = controlCenterWindowViewCreator;
    }

    public void injectMembers(MiuiControlCenter miuiControlCenter) {
        PluginBase_MembersInjector.injectPluginInstancesRepository(miuiControlCenter, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        PluginBase_MembersInjector.injectAutoDensityController(miuiControlCenter, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        PluginBase_MembersInjector.injectPluginContext(miuiControlCenter, (Context) this.pluginContextProvider.get());
        PluginBase_MembersInjector.injectSysuiContext(miuiControlCenter, (Context) this.sysuiContextProvider.get());
        injectWindowViewCreator(miuiControlCenter, (ControlCenterWindowViewCreator) this.windowViewCreatorProvider.get());
        injectMiLinkController(miuiControlCenter, (MiLinkController) this.miLinkControllerProvider.get());
        injectSecurityController(miuiControlCenter, (SecurityController) this.securityControllerProvider.get());
        injectBlurUtilsExt(miuiControlCenter, (BlurUtilsExt) this.blurUtilsExtProvider.get());
    }
}
