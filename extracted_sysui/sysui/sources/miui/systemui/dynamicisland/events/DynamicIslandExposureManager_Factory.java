package miui.systemui.dynamicisland.events;

import F0.e;
import G0.a;
import android.content.Context;
import miui.systemui.dynamicisland.display.AntiBurnInManager;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExposureManager_Factory implements e {
    private final a antiBurnInManProvider;
    private final a contextProvider;
    private final a eventCoordinatorProvider;

    public DynamicIslandExposureManager_Factory(a aVar, a aVar2, a aVar3) {
        this.contextProvider = aVar;
        this.eventCoordinatorProvider = aVar2;
        this.antiBurnInManProvider = aVar3;
    }

    public static DynamicIslandExposureManager_Factory create(a aVar, a aVar2, a aVar3) {
        return new DynamicIslandExposureManager_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandExposureManager newInstance(Context context, DynamicIslandEventCoordinator dynamicIslandEventCoordinator, AntiBurnInManager antiBurnInManager) {
        return new DynamicIslandExposureManager(context, dynamicIslandEventCoordinator, antiBurnInManager);
    }

    @Override // G0.a
    public DynamicIslandExposureManager get() {
        return newInstance((Context) this.contextProvider.get(), (DynamicIslandEventCoordinator) this.eventCoordinatorProvider.get(), (AntiBurnInManager) this.antiBurnInManProvider.get());
    }
}
