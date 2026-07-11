package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.statusbar.StatusBarStateController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory INSTANCE = new ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideStatusBarStateControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static StatusBarStateController provideStatusBarStateController() {
        return (StatusBarStateController) i.d(ControlCenterPluginInstance.provideStatusBarStateController());
    }

    @Override // G0.a
    public StatusBarStateController get() {
        return provideStatusBarStateController();
    }
}
