package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import G0.a;
import L0.g;
import g1.E;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_ApplicationScopeFactory implements e {
    private final a mainContextProvider;
    private final a runningAsPluginProvider;

    public CoroutinesModule_ApplicationScopeFactory(a aVar, a aVar2) {
        this.runningAsPluginProvider = aVar;
        this.mainContextProvider = aVar2;
    }

    public static E applicationScope(boolean z2, g gVar) {
        return (E) i.d(CoroutinesModule.INSTANCE.applicationScope(z2, gVar));
    }

    public static CoroutinesModule_ApplicationScopeFactory create(a aVar, a aVar2) {
        return new CoroutinesModule_ApplicationScopeFactory(aVar, aVar2);
    }

    @Override // G0.a
    public E get() {
        return applicationScope(((Boolean) this.runningAsPluginProvider.get()).booleanValue(), (g) this.mainContextProvider.get());
    }
}
