package miui.systemui.controlcenter.dagger;

import F0.e;
import F0.i;
import com.android.systemui.plugins.miui.settings.IUserTracker;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterPluginInstance_ProvideUserTrackerFactory implements e {

    public static final class InstanceHolder {
        private static final ControlCenterPluginInstance_ProvideUserTrackerFactory INSTANCE = new ControlCenterPluginInstance_ProvideUserTrackerFactory();

        private InstanceHolder() {
        }
    }

    public static ControlCenterPluginInstance_ProvideUserTrackerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static IUserTracker provideUserTracker() {
        return (IUserTracker) i.d(ControlCenterPluginInstance.provideUserTracker());
    }

    @Override // G0.a
    public IUserTracker get() {
        return provideUserTracker();
    }
}
