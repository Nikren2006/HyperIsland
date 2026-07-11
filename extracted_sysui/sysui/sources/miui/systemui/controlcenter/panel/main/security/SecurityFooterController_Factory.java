package miui.systemui.controlcenter.panel.main.security;

import F0.d;
import F0.e;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.statusbar.MiuiSecurityController;
import miui.systemui.controlcenter.policy.SecurityController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.SystemUIResourcesHelper;

/* JADX INFO: loaded from: classes.dex */
public final class SecurityFooterController_Factory implements e {
    private final G0.a bgLooperProvider;
    private final G0.a dialogProvider;
    private final G0.a distributorProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a securityControllerProvider;
    private final G0.a systemUIResourcesHelperProvider;
    private final G0.a uiHandlerProvider;
    private final G0.a windowViewProvider;

    public SecurityFooterController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        this.windowViewProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.securityControllerProvider = aVar3;
        this.uiHandlerProvider = aVar4;
        this.bgLooperProvider = aVar5;
        this.distributorProvider = aVar6;
        this.systemUIResourcesHelperProvider = aVar7;
        this.mainPanelStyleControllerProvider = aVar8;
        this.mainPanelModeControllerProvider = aVar9;
        this.dialogProvider = aVar10;
    }

    public static SecurityFooterController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        return new SecurityFooterController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10);
    }

    public static SecurityFooterController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, SecurityController securityController, Handler handler, Looper looper, E0.a aVar, SystemUIResourcesHelper systemUIResourcesHelper, E0.a aVar2, E0.a aVar3, MiuiSecurityController miuiSecurityController) {
        return new SecurityFooterController(controlCenterWindowViewImpl, lifecycle, securityController, handler, looper, aVar, systemUIResourcesHelper, aVar2, aVar3, miuiSecurityController);
    }

    @Override // G0.a
    public SecurityFooterController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (SecurityController) this.securityControllerProvider.get(), (Handler) this.uiHandlerProvider.get(), (Looper) this.bgLooperProvider.get(), d.a(this.distributorProvider), (SystemUIResourcesHelper) this.systemUIResourcesHelperProvider.get(), d.a(this.mainPanelStyleControllerProvider), d.a(this.mainPanelModeControllerProvider), (MiuiSecurityController) this.dialogProvider.get());
    }
}
