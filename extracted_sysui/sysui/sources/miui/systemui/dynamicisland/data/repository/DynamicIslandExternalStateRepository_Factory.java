package miui.systemui.dynamicisland.data.repository;

import F0.e;
import G0.a;
import g1.E;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExternalStateRepository_Factory implements e {
    private final a scopeProvider;

    public DynamicIslandExternalStateRepository_Factory(a aVar) {
        this.scopeProvider = aVar;
    }

    public static DynamicIslandExternalStateRepository_Factory create(a aVar) {
        return new DynamicIslandExternalStateRepository_Factory(aVar);
    }

    public static DynamicIslandExternalStateRepository newInstance(E e2) {
        return new DynamicIslandExternalStateRepository(e2);
    }

    @Override // G0.a
    public DynamicIslandExternalStateRepository get() {
        return newInstance((E) this.scopeProvider.get());
    }
}
