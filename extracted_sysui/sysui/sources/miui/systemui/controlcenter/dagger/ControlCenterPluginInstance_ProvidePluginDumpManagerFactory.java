package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.dump.PluginDumpManager;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvidePluginDumpManagerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvidePluginDumpManagerFactory INSTANCE = new ControlCenterPluginInstance_ProvidePluginDumpManagerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvidePluginDumpManagerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PluginDumpManager providePluginDumpManager() {
        return (PluginDumpManager) i.d(ControlCenterPluginInstance.providePluginDumpManager());
    }

    @Override // G0.a
    public PluginDumpManager get() {
        return providePluginDumpManager();
    }
}
