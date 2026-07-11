package miui.systemui.dynamicisland.window.domain.interactor;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowStateInteractor_Factory implements e {
    private final a eventCoordinatorProvider;
    private final a scopeProvider;
    private final a windowViewControllerProvider;
    private final a windowViewTouchInteractorProvider;

    public DynamicIslandWindowStateInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.scopeProvider = aVar;
        this.eventCoordinatorProvider = aVar2;
        this.windowViewControllerProvider = aVar3;
        this.windowViewTouchInteractorProvider = aVar4;
    }

    public static DynamicIslandWindowStateInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new DynamicIslandWindowStateInteractor_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DynamicIslandWindowStateInteractor newInstance(E e2, DynamicIslandEventCoordinator dynamicIslandEventCoordinator, DynamicIslandWindowViewController dynamicIslandWindowViewController, DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor) {
        return new DynamicIslandWindowStateInteractor(e2, dynamicIslandEventCoordinator, dynamicIslandWindowViewController, dynamicIslandWindowViewTouchInteractor);
    }

    @Override // G0.a
    public DynamicIslandWindowStateInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandEventCoordinator) this.eventCoordinatorProvider.get(), (DynamicIslandWindowViewController) this.windowViewControllerProvider.get(), (DynamicIslandWindowViewTouchInteractor) this.windowViewTouchInteractorProvider.get());
    }
}
