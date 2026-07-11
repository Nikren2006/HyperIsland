package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class AvoidScreenBurnInEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public AvoidScreenBurnInEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static AvoidScreenBurnInEventCoordinator_Factory create(G0.a aVar) {
        return new AvoidScreenBurnInEventCoordinator_Factory(aVar);
    }

    public static AvoidScreenBurnInEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new AvoidScreenBurnInEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public AvoidScreenBurnInEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
