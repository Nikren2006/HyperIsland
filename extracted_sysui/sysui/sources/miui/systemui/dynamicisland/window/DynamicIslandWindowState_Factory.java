package miui.systemui.dynamicisland.window;

import g1.E;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowState_Factory implements F0.e {
    private final G0.a externalRepositoryProvider;
    private final G0.a scopeProvider;

    public DynamicIslandWindowState_Factory(G0.a aVar, G0.a aVar2) {
        this.scopeProvider = aVar;
        this.externalRepositoryProvider = aVar2;
    }

    public static DynamicIslandWindowState_Factory create(G0.a aVar, G0.a aVar2) {
        return new DynamicIslandWindowState_Factory(aVar, aVar2);
    }

    public static DynamicIslandWindowState newInstance(E e2, DynamicIslandExternalStateRepository dynamicIslandExternalStateRepository) {
        return new DynamicIslandWindowState(e2, dynamicIslandExternalStateRepository);
    }

    @Override // G0.a
    public DynamicIslandWindowState get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandExternalStateRepository) this.externalRepositoryProvider.get());
    }
}
