package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory INSTANCE = new ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideShadeHeaderControllerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ShadeHeaderController provideShadeHeaderController() {
        return (ShadeHeaderController) i.d(ControlCenterPluginInstance.provideShadeHeaderController());
    }

    @Override // G0.a
    public ShadeHeaderController get() {
        return provideShadeHeaderController();
    }
}
