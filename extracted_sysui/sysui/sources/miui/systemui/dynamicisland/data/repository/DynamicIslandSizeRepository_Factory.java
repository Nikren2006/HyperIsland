package miui.systemui.dynamicisland.data.repository;

import F0.e;
import G0.a;
import g1.E;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandSizeRepository_Factory implements e {
    private final a scopeProvider;

    public DynamicIslandSizeRepository_Factory(a aVar) {
        this.scopeProvider = aVar;
    }

    public static DynamicIslandSizeRepository_Factory create(a aVar) {
        return new DynamicIslandSizeRepository_Factory(aVar);
    }

    public static DynamicIslandSizeRepository newInstance(E e2) {
        return new DynamicIslandSizeRepository(e2);
    }

    @Override // G0.a
    public DynamicIslandSizeRepository get() {
        return newInstance((E) this.scopeProvider.get());
    }
}
