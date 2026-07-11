package miui.systemui.dynamicisland.window.content;

import F0.e;
import g1.E;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEmptyContentViewController_Factory implements e {
    private final G0.a scopeProvider;
    private final G0.a touchInteractorProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandEmptyContentViewController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.windowViewProvider = aVar;
        this.scopeProvider = aVar2;
        this.touchInteractorProvider = aVar3;
    }

    public static DynamicIslandEmptyContentViewController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new DynamicIslandEmptyContentViewController_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandEmptyContentViewController newInstance(DynamicIslandWindowView dynamicIslandWindowView, E e2, DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new DynamicIslandEmptyContentViewController(dynamicIslandWindowView, e2, dynamicIslandTouchInteractor);
    }

    @Override // G0.a
    public DynamicIslandEmptyContentViewController get() {
        return newInstance((DynamicIslandWindowView) this.windowViewProvider.get(), (E) this.scopeProvider.get(), (DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
