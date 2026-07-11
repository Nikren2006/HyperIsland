package miui.systemui.plugins;

import F0.e;
import F0.i;
import G0.a;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import miui.systemui.plugins.PluginDependencyHolderImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory implements e {
    private final a holderFactoryProvider;
    private final PluginDependencyModule module;

    public PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory(PluginDependencyModule pluginDependencyModule, a aVar) {
        this.module = pluginDependencyModule;
        this.holderFactoryProvider = aVar;
    }

    public static PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory create(PluginDependencyModule pluginDependencyModule, a aVar) {
        return new PluginDependencyModule_ProvidesSuperSaveModeControllerHolderFactory(pluginDependencyModule, aVar);
    }

    public static PluginDependencyHolder<SuperSaveModeController> providesSuperSaveModeControllerHolder(PluginDependencyModule pluginDependencyModule, PluginDependencyHolderImpl.Factory factory) {
        return (PluginDependencyHolder) i.d(pluginDependencyModule.providesSuperSaveModeControllerHolder(factory));
    }

    @Override // G0.a
    public PluginDependencyHolder<SuperSaveModeController> get() {
        return providesSuperSaveModeControllerHolder(this.module, (PluginDependencyHolderImpl.Factory) this.holderFactoryProvider.get());
    }
}
