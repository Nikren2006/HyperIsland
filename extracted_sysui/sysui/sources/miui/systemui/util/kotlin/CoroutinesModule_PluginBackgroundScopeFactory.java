package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import G0.a;
import L0.g;
import g1.E;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_PluginBackgroundScopeFactory implements e {
    private final a applicationScopeProvider;
    private final a dispatcherContextProvider;
    private final a pluginScopeProvider;
    private final a runningAsPluginProvider;

    public CoroutinesModule_PluginBackgroundScopeFactory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.runningAsPluginProvider = aVar;
        this.pluginScopeProvider = aVar2;
        this.applicationScopeProvider = aVar3;
        this.dispatcherContextProvider = aVar4;
    }

    public static CoroutinesModule_PluginBackgroundScopeFactory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new CoroutinesModule_PluginBackgroundScopeFactory(aVar, aVar2, aVar3, aVar4);
    }

    public static E pluginBackgroundScope(boolean z2, a aVar, a aVar2, g gVar) {
        return (E) i.d(CoroutinesModule.INSTANCE.pluginBackgroundScope(z2, aVar, aVar2, gVar));
    }

    @Override // G0.a
    public E get() {
        return pluginBackgroundScope(((Boolean) this.runningAsPluginProvider.get()).booleanValue(), this.pluginScopeProvider, this.applicationScopeProvider, (g) this.dispatcherContextProvider.get());
    }
}
