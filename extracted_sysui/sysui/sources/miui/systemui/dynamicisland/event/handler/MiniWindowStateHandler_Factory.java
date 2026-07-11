package miui.systemui.dynamicisland.event.handler;

import F0.e;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class MiniWindowStateHandler_Factory implements e {
    private final G0.a touchInteractorProvider;

    public MiniWindowStateHandler_Factory(G0.a aVar) {
        this.touchInteractorProvider = aVar;
    }

    public static MiniWindowStateHandler_Factory create(G0.a aVar) {
        return new MiniWindowStateHandler_Factory(aVar);
    }

    public static MiniWindowStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new MiniWindowStateHandler(dynamicIslandTouchInteractor);
    }

    @Override // G0.a
    public MiniWindowStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
