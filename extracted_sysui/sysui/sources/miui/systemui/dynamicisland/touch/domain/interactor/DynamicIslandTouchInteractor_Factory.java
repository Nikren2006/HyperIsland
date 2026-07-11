package miui.systemui.dynamicisland.touch.domain.interactor;

import F0.d;
import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationController;
import miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchInteractor_Factory implements e {
    private final a animationControllerProvider;
    private final a bigIslandStateHandlerProvider;
    private final a contextProvider;
    private final a eventCoordinatorProvider;
    private final a expandedStateHandlerProvider;
    private final a externalTouchInteractorProvider;
    private final a scopeProvider;
    private final a smallIslandStateHandlerProvider;
    private final a sysUIContextProvider;
    private final a touchConstantsProvider;
    private final a touchRegionInteractorProvider;
    private final a windowStateProvider;
    private final a windowViewControllerProvider;
    private final a windowViewProvider;
    private final a windowViewTouchInteractorProvider;

    public DynamicIslandTouchInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9, a aVar10, a aVar11, a aVar12, a aVar13, a aVar14, a aVar15) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.sysUIContextProvider = aVar3;
        this.touchConstantsProvider = aVar4;
        this.windowViewProvider = aVar5;
        this.externalTouchInteractorProvider = aVar6;
        this.windowViewTouchInteractorProvider = aVar7;
        this.eventCoordinatorProvider = aVar8;
        this.bigIslandStateHandlerProvider = aVar9;
        this.smallIslandStateHandlerProvider = aVar10;
        this.expandedStateHandlerProvider = aVar11;
        this.windowStateProvider = aVar12;
        this.windowViewControllerProvider = aVar13;
        this.animationControllerProvider = aVar14;
        this.touchRegionInteractorProvider = aVar15;
    }

    public static DynamicIslandTouchInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9, a aVar10, a aVar11, a aVar12, a aVar13, a aVar14, a aVar15) {
        return new DynamicIslandTouchInteractor_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15);
    }

    public static DynamicIslandTouchInteractor newInstance(E e2, Context context, Context context2, DynamicIslandTouchConstantsRepository dynamicIslandTouchConstantsRepository, DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandExternalTouchInteractor dynamicIslandExternalTouchInteractor, DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, DynamicIslandWindowState dynamicIslandWindowState, DynamicIslandWindowViewController dynamicIslandWindowViewController, DynamicIslandAnimationController dynamicIslandAnimationController, DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor) {
        return new DynamicIslandTouchInteractor(e2, context, context2, dynamicIslandTouchConstantsRepository, dynamicIslandWindowView, dynamicIslandExternalTouchInteractor, dynamicIslandWindowViewTouchInteractor, aVar, aVar2, aVar3, aVar4, dynamicIslandWindowState, dynamicIslandWindowViewController, dynamicIslandAnimationController, dynamicIslandTouchRegionInteractor);
    }

    @Override // G0.a
    public DynamicIslandTouchInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (Context) this.sysUIContextProvider.get(), (DynamicIslandTouchConstantsRepository) this.touchConstantsProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (DynamicIslandExternalTouchInteractor) this.externalTouchInteractorProvider.get(), (DynamicIslandWindowViewTouchInteractor) this.windowViewTouchInteractorProvider.get(), d.a(this.eventCoordinatorProvider), d.a(this.bigIslandStateHandlerProvider), d.a(this.smallIslandStateHandlerProvider), d.a(this.expandedStateHandlerProvider), (DynamicIslandWindowState) this.windowStateProvider.get(), (DynamicIslandWindowViewController) this.windowViewControllerProvider.get(), (DynamicIslandAnimationController) this.animationControllerProvider.get(), (DynamicIslandTouchRegionInteractor) this.touchRegionInteractorProvider.get());
    }
}
