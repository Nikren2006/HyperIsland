package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class DeletedEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public DeletedEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static DeletedEventCoordinator_Factory create(G0.a aVar) {
        return new DeletedEventCoordinator_Factory(aVar);
    }

    public static DeletedEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new DeletedEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public DeletedEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
