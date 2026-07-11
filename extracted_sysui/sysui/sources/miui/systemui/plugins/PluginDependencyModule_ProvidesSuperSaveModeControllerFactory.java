package miui.systemui.plugins;

import F0.e;
import F0.i;
import G0.a;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule_ProvidesSuperSaveModeControllerFactory implements e {
    private final a holderProvider;
    private final PluginDependencyModule module;

    public PluginDependencyModule_ProvidesSuperSaveModeControllerFactory(PluginDependencyModule pluginDependencyModule, a aVar) {
        this.module = pluginDependencyModule;
        this.holderProvider = aVar;
    }

    public static PluginDependencyModule_ProvidesSuperSaveModeControllerFactory create(PluginDependencyModule pluginDependencyModule, a aVar) {
        return new PluginDependencyModule_ProvidesSuperSaveModeControllerFactory(pluginDependencyModule, aVar);
    }

    public static SuperSaveModeController providesSuperSaveModeController(PluginDependencyModule pluginDependencyModule, PluginDependencyHolder<SuperSaveModeController> pluginDependencyHolder) {
        return (SuperSaveModeController) i.d(pluginDependencyModule.providesSuperSaveModeController(pluginDependencyHolder));
    }

    @Override // G0.a
    public SuperSaveModeController get() {
        return providesSuperSaveModeController(this.module, (PluginDependencyHolder) this.holderProvider.get());
    }
}
