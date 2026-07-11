package miui.systemui.dynamicisland.event.handler;

import F0.e;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class AppStateHandler_Factory implements e {
    private final G0.a touchInteractorProvider;

    public AppStateHandler_Factory(G0.a aVar) {
        this.touchInteractorProvider = aVar;
    }

    public static AppStateHandler_Factory create(G0.a aVar) {
        return new AppStateHandler_Factory(aVar);
    }

    public static AppStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new AppStateHandler(dynamicIslandTouchInteractor);
    }

    @Override // G0.a
    public AppStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
