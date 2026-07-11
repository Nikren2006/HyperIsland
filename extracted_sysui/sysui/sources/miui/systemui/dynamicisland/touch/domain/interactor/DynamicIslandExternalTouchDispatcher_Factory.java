package miui.systemui.dynamicisland.touch.domain.interactor;

import F0.e;
import G0.a;
import g1.E;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExternalTouchDispatcher_Factory implements e {
    private final a scopeProvider;
    private final a windowViewProvider;
    private final a windowViewTouchInteractorProvider;

    public DynamicIslandExternalTouchDispatcher_Factory(a aVar, a aVar2, a aVar3) {
        this.scopeProvider = aVar;
        this.windowViewProvider = aVar2;
        this.windowViewTouchInteractorProvider = aVar3;
    }

    public static DynamicIslandExternalTouchDispatcher_Factory create(a aVar, a aVar2, a aVar3) {
        return new DynamicIslandExternalTouchDispatcher_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandExternalTouchDispatcher newInstance(E e2, DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor) {
        return new DynamicIslandExternalTouchDispatcher(e2, dynamicIslandWindowView, dynamicIslandWindowViewTouchInteractor);
    }

    @Override // G0.a
    public DynamicIslandExternalTouchDispatcher get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (DynamicIslandWindowViewTouchInteractor) this.windowViewTouchInteractorProvider.get());
    }
}
