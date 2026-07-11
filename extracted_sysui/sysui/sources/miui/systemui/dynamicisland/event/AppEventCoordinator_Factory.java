package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class AppEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public AppEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static AppEventCoordinator_Factory create(G0.a aVar) {
        return new AppEventCoordinator_Factory(aVar);
    }

    public static AppEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new AppEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public AppEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
