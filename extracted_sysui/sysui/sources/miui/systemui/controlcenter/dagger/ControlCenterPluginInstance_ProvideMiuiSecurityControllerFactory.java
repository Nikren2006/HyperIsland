package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.statusbar.MiuiSecurityController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory INSTANCE = new ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideMiuiSecurityControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MiuiSecurityController provideMiuiSecurityController() {
        return (MiuiSecurityController) i.d(ControlCenterPluginInstance.provideMiuiSecurityController());
    }

    @Override // G0.a
    public MiuiSecurityController get() {
        return provideMiuiSecurityController();
    }
}
