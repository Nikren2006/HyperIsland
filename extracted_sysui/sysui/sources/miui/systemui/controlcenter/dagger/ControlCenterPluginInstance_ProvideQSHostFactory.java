package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideQSHostFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideQSHostFactory INSTANCE = new ControlCenterPluginInstance_ProvideQSHostFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideQSHostFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MiuiQSHost provideQSHost() {
        return (MiuiQSHost) i.d(ControlCenterPluginInstance.provideQSHost());
    }

    @Override // G0.a
    public MiuiQSHost get() {
        return provideQSHost();
    }
}
