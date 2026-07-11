package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory INSTANCE = new ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideBrightnessControllerBaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static BrightnessControllerBase provideBrightnessControllerBase() {
        return (BrightnessControllerBase) i.d(ControlCenterPluginInstance.provideBrightnessControllerBase());
    }

    @Override // G0.a
    public BrightnessControllerBase get() {
        return provideBrightnessControllerBase();
    }
}
