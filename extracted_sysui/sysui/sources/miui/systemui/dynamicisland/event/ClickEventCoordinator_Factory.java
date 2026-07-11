package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class ClickEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public ClickEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static ClickEventCoordinator_Factory create(G0.a aVar) {
        return new ClickEventCoordinator_Factory(aVar);
    }

    public static ClickEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new ClickEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public ClickEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
