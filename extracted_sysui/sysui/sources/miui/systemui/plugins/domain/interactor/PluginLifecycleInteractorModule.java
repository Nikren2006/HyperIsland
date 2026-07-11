package miui.systemui.plugins.domain.interactor;

import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginLifecycleInteractorModule {
    @Plugin
    public final Lifecycle providesPluginLifecycle(PluginLifecycleInteractor pluginLifecycleInteractor) {
        n.g(pluginLifecycleInteractor, "pluginLifecycleInteractor");
        return pluginLifecycleInteractor.getLifecycle();
    }
}
