package miui.systemui.dynamicisland.event;

import F0.e;

/* JADX INFO: loaded from: classes3.dex */
public final class ConfigChangedEventCoordinator_Factory implements e {
    private final G0.a dynamicIslandEventCoordinatorProvider;

    public ConfigChangedEventCoordinator_Factory(G0.a aVar) {
        this.dynamicIslandEventCoordinatorProvider = aVar;
    }

    public static ConfigChangedEventCoordinator_Factory create(G0.a aVar) {
        return new ConfigChangedEventCoordinator_Factory(aVar);
    }

    public static ConfigChangedEventCoordinator newInstance(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        return new ConfigChangedEventCoordinator(dynamicIslandEventCoordinator);
    }

    @Override // G0.a
    public ConfigChangedEventCoordinator get() {
        return newInstance((DynamicIslandEventCoordinator) this.dynamicIslandEventCoordinatorProvider.get());
    }
}
