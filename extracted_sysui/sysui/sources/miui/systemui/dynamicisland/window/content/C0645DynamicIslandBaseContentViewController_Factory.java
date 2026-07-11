package miui.systemui.dynamicisland.window.content;

import g1.E;
import miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor;
import miui.systemui.dynamicisland.template.IslandTemplateFactory;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentViewController_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0645DynamicIslandBaseContentViewController_Factory<T extends DynamicIslandBaseContentView> {
    private final G0.a islandTemplateFactoryProvider;
    private final G0.a regionSamplingInteractorProvider;
    private final G0.a scopeProvider;
    private final G0.a touchInteractorProvider;

    public C0645DynamicIslandBaseContentViewController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.scopeProvider = aVar;
        this.regionSamplingInteractorProvider = aVar2;
        this.islandTemplateFactoryProvider = aVar3;
        this.touchInteractorProvider = aVar4;
    }

    public static <T extends DynamicIslandBaseContentView> C0645DynamicIslandBaseContentViewController_Factory<T> create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new C0645DynamicIslandBaseContentViewController_Factory<>(aVar, aVar2, aVar3, aVar4);
    }

    public static <T extends DynamicIslandBaseContentView> DynamicIslandBaseContentViewController<T> newInstance(T t2, E e2, DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor, IslandTemplateFactory islandTemplateFactory, DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new DynamicIslandBaseContentViewController<>(t2, e2, dynamicIslandRegionSamplingInteractor, islandTemplateFactory, dynamicIslandTouchInteractor);
    }

    public DynamicIslandBaseContentViewController<T> get(T t2) {
        return newInstance(t2, (E) this.scopeProvider.get(), (DynamicIslandRegionSamplingInteractor) this.regionSamplingInteractorProvider.get(), (IslandTemplateFactory) this.islandTemplateFactoryProvider.get(), (DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
