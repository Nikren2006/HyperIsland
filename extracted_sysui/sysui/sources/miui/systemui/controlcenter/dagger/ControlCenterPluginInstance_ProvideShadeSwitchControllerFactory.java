package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory INSTANCE = new ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideShadeSwitchControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ShadeSwitchController provideShadeSwitchController() {
        return (ShadeSwitchController) i.d(ControlCenterPluginInstance.provideShadeSwitchController());
    }

    @Override // G0.a
    public ShadeSwitchController get() {
        return provideShadeSwitchController();
    }
}
