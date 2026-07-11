package miui.systemui.controlcenter.dagger;

import F0.e;
import androidx.annotation.Nullable;
import com.android.systemui.plugins.miui.controlcenter.ControlCenterPlugin;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideCCPluginFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideCCPluginFactory INSTANCE = new ControlCenterPluginInstance_ProvideCCPluginFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideCCPluginFactory create() {
        return InstanceHolder.INSTANCE;
    }

    @Nullable
    public static ControlCenterPlugin provideCCPlugin() {
        return ControlCenterPluginInstance.provideCCPlugin();
    }

    @Override // G0.a
    @Nullable
    public ControlCenterPlugin get() {
        return provideCCPlugin();
    }
}
