package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MiniWindowEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public MiniWindowEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static MiniWindowEventCoordinator_Factory create(G0.a aVar) {
        return new MiniWindowEventCoordinator_Factory(aVar);
    }

    public static MiniWindowEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new MiniWindowEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public MiniWindowEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
