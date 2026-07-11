package miui.systemui.dynamicisland.domain.interactor;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor;
import miui.systemui.handles.RegionSamplingHelperRefactor;
import miui.systemui.statusbar.data.repository.StatusBarAreaRepository;
import miui.systemui.statusbar.data.repository.StatusBarStateRepository;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandRegionSamplingInteractor_Factory implements e {
    private final a contextProvider;
    private final a controlCenterExpandRepositoryProvider;
    private final a externalStateRepositoryProvider;
    private final a regionSamplingHelperFactoryProvider;
    private final a scopeProvider;
    private final a statusBarAreaRepositoryProvider;
    private final a statusBarStateRepositoryProvider;
    private final a windowStateInteractorProvider;
    private final a windowViewProvider;

    public DynamicIslandRegionSamplingInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9) {
        this.scopeProvider = aVar;
        this.windowViewProvider = aVar2;
        this.contextProvider = aVar3;
        this.windowStateInteractorProvider = aVar4;
        this.regionSamplingHelperFactoryProvider = aVar5;
        this.statusBarAreaRepositoryProvider = aVar6;
        this.statusBarStateRepositoryProvider = aVar7;
        this.controlCenterExpandRepositoryProvider = aVar8;
        this.externalStateRepositoryProvider = aVar9;
    }

    public static DynamicIslandRegionSamplingInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5, a aVar6, a aVar7, a aVar8, a aVar9) {
        return new DynamicIslandRegionSamplingInteractor_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static DynamicIslandRegionSamplingInteractor newInstance(E e2, DynamicIslandWindowView dynamicIslandWindowView, Context context, DynamicIslandWindowStateInteractor dynamicIslandWindowStateInteractor, RegionSamplingHelperRefactor.Factory factory, StatusBarAreaRepository statusBarAreaRepository, StatusBarStateRepository statusBarStateRepository, ControlCenterExpandRepository controlCenterExpandRepository, DynamicIslandExternalStateRepository dynamicIslandExternalStateRepository) {
        return new DynamicIslandRegionSamplingInteractor(e2, dynamicIslandWindowView, context, dynamicIslandWindowStateInteractor, factory, statusBarAreaRepository, statusBarStateRepository, controlCenterExpandRepository, dynamicIslandExternalStateRepository);
    }

    @Override // G0.a
    public DynamicIslandRegionSamplingInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (Context) this.contextProvider.get(), (DynamicIslandWindowStateInteractor) this.windowStateInteractorProvider.get(), (RegionSamplingHelperRefactor.Factory) this.regionSamplingHelperFactoryProvider.get(), (StatusBarAreaRepository) this.statusBarAreaRepositoryProvider.get(), (StatusBarStateRepository) this.statusBarStateRepositoryProvider.get(), (ControlCenterExpandRepository) this.controlCenterExpandRepositoryProvider.get(), (DynamicIslandExternalStateRepository) this.externalStateRepositoryProvider.get());
    }
}
