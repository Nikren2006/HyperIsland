package miui.systemui.plugins.data.repository;

import F0.d;
import F0.e;
import G0.a;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginInstancesRepositoryImpl_Factory implements e {
    private final a scopeProvider;

    public PluginInstancesRepositoryImpl_Factory(a aVar) {
        this.scopeProvider = aVar;
    }

    public static PluginInstancesRepositoryImpl_Factory create(a aVar) {
        return new PluginInstancesRepositoryImpl_Factory(aVar);
    }

    public static PluginInstancesRepositoryImpl newInstance(E0.a aVar) {
        return new PluginInstancesRepositoryImpl(aVar);
    }

    @Override // G0.a
    public PluginInstancesRepositoryImpl get() {
        return newInstance(d.a(this.scopeProvider));
    }
}
