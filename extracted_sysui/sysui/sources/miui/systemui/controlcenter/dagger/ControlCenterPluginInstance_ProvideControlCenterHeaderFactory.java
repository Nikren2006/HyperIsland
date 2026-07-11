package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterHeader;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideControlCenterHeaderFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideControlCenterHeaderFactory INSTANCE = new ControlCenterPluginInstance_ProvideControlCenterHeaderFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideControlCenterHeaderFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ControlCenterHeader provideControlCenterHeader() {
        return (ControlCenterHeader) i.d(ControlCenterPluginInstance.provideControlCenterHeader());
    }

    @Override // G0.a
    public ControlCenterHeader get() {
        return provideControlCenterHeader();
    }
}
