package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterSecondary;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory INSTANCE = new ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideControlCenterSecondaryFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ControlCenterSecondary provideControlCenterSecondary() {
        return (ControlCenterSecondary) i.d(ControlCenterPluginInstance.provideControlCenterSecondary());
    }

    @Override // G0.a
    public ControlCenterSecondary get() {
        return provideControlCenterSecondary();
    }
}
