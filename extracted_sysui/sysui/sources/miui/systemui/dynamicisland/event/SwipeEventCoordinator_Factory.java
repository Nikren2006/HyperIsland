package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class SwipeEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public SwipeEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static SwipeEventCoordinator_Factory create(G0.a aVar) {
        return new SwipeEventCoordinator_Factory(aVar);
    }

    public static SwipeEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new SwipeEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public SwipeEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
