package miui.systemui.dagger;

import android.content.Context;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class PluginModule_Companion_ProvidesRunningAsPluginFactory implements F0.e {
    private final G0.a optionalSysuiContextProvider;

    public PluginModule_Companion_ProvidesRunningAsPluginFactory(G0.a aVar) {
        this.optionalSysuiContextProvider = aVar;
    }

    public static PluginModule_Companion_ProvidesRunningAsPluginFactory create(G0.a aVar) {
        return new PluginModule_Companion_ProvidesRunningAsPluginFactory(aVar);
    }

    public static boolean providesRunningAsPlugin(Optional<Context> optional) {
        return PluginModule.Companion.providesRunningAsPlugin(optional);
    }

    @Override // G0.a
    public Boolean get() {
        return Boolean.valueOf(providesRunningAsPlugin((Optional) this.optionalSysuiContextProvider.get()));
    }
}
