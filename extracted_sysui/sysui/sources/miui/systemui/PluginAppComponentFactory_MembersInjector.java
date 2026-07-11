package miui.systemui;

import E0.b;
import G0.a;
import miui.systemui.dagger.ContextComponentHelper;

/* JADX INFO: loaded from: classes2.dex */
public final class PluginAppComponentFactory_MembersInjector implements b {
    private final a mComponentHelperProvider;

    public PluginAppComponentFactory_MembersInjector(a aVar) {
        this.mComponentHelperProvider = aVar;
    }

    public static b create(a aVar) {
        return new PluginAppComponentFactory_MembersInjector(aVar);
    }

    public static void injectMComponentHelper(PluginAppComponentFactory pluginAppComponentFactory, ContextComponentHelper contextComponentHelper) {
        pluginAppComponentFactory.mComponentHelper = contextComponentHelper;
    }

    public void injectMembers(PluginAppComponentFactory pluginAppComponentFactory) {
        injectMComponentHelper(pluginAppComponentFactory, (ContextComponentHelper) this.mComponentHelperProvider.get());
    }
}
