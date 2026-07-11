package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTempHiddenEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public IslandTempHiddenEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static IslandTempHiddenEventCoordinator_Factory create(G0.a aVar) {
        return new IslandTempHiddenEventCoordinator_Factory(aVar);
    }

    public static IslandTempHiddenEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new IslandTempHiddenEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public IslandTempHiddenEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
