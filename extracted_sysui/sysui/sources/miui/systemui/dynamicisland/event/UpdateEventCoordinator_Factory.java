package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class UpdateEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public UpdateEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static UpdateEventCoordinator_Factory create(G0.a aVar) {
        return new UpdateEventCoordinator_Factory(aVar);
    }

    public static UpdateEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new UpdateEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public UpdateEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
