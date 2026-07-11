package miui.systemui.dynamicisland.domain.interactor;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewController;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandStateInteractor_Factory implements e {
    private final a controlCenterExpandRepositoryProvider;
    private final a dynamicIslandWindowViewControllerProvider;
    private final a scopeProvider;

    public DynamicIslandStateInteractor_Factory(a aVar, a aVar2, a aVar3) {
        this.scopeProvider = aVar;
        this.dynamicIslandWindowViewControllerProvider = aVar2;
        this.controlCenterExpandRepositoryProvider = aVar3;
    }

    public static DynamicIslandStateInteractor_Factory create(a aVar, a aVar2, a aVar3) {
        return new DynamicIslandStateInteractor_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandStateInteractor newInstance(E e2, DynamicIslandWindowViewController dynamicIslandWindowViewController, ControlCenterExpandRepository controlCenterExpandRepository) {
        return new DynamicIslandStateInteractor(e2, dynamicIslandWindowViewController, controlCenterExpandRepository);
    }

    @Override // G0.a
    public DynamicIslandStateInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandWindowViewController) this.dynamicIslandWindowViewControllerProvider.get(), (ControlCenterExpandRepository) this.controlCenterExpandRepositoryProvider.get());
    }
}
