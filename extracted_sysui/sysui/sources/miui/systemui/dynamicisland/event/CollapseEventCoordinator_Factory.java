package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class CollapseEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public CollapseEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static CollapseEventCoordinator_Factory create(G0.a aVar) {
        return new CollapseEventCoordinator_Factory(aVar);
    }

    public static CollapseEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new CollapseEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public CollapseEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
