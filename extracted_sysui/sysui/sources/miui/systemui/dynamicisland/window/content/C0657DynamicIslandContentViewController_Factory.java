package miui.systemui.dynamicisland.window.content;

import L0.g;
import g1.E;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor;
import miui.systemui.dynamicisland.events.DynamicIslandExposureManager;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandContentViewController_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0657DynamicIslandContentViewController_Factory {
    private final G0.a expoManagerProvider;
    private final G0.a islandTemplateFactoryProvider;
    private final G0.a regionSamplingInteractorProvider;
    private final G0.a scopeProvider;
    private final G0.a touchInteractorProvider;
    private final G0.a uiContextProvider;
    private final G0.a windowViewProvider;

    public C0657DynamicIslandContentViewController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.scopeProvider = aVar;
        this.uiContextProvider = aVar2;
        this.windowViewProvider = aVar3;
        this.regionSamplingInteractorProvider = aVar4;
        this.islandTemplateFactoryProvider = aVar5;
        this.touchInteractorProvider = aVar6;
        this.expoManagerProvider = aVar7;
    }

    public static C0657DynamicIslandContentViewController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new C0657DynamicIslandContentViewController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static DynamicIslandContentViewController newInstance(DynamicIslandContentView dynamicIslandContentView, E e2, g gVar, DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor, IslandTemplateFactory islandTemplateFactory, DynamicIslandTouchInteractor dynamicIslandTouchInteractor, DynamicIslandExposureManager dynamicIslandExposureManager) {
        return new DynamicIslandContentViewController(dynamicIslandContentView, e2, gVar, dynamicIslandWindowView, dynamicIslandRegionSamplingInteractor, islandTemplateFactory, dynamicIslandTouchInteractor, dynamicIslandExposureManager);
    }

    public DynamicIslandContentViewController get(DynamicIslandContentView dynamicIslandContentView) {
        return newInstance(dynamicIslandContentView, (E) this.scopeProvider.get(), (g) this.uiContextProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (DynamicIslandRegionSamplingInteractor) this.regionSamplingInteractorProvider.get(), (IslandTemplateFactory) this.islandTemplateFactoryProvider.get(), (DynamicIslandTouchInteractor) this.touchInteractorProvider.get(), (DynamicIslandExposureManager) this.expoManagerProvider.get());
    }
}
