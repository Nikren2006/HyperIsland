package miui.systemui.dynamicisland.event.handler;

import F0.e;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class HiddenStateHandler_Factory implements e {
    private final G0.a touchInteractorProvider;

    public HiddenStateHandler_Factory(G0.a aVar) {
        this.touchInteractorProvider = aVar;
    }

    public static HiddenStateHandler_Factory create(G0.a aVar) {
        return new HiddenStateHandler_Factory(aVar);
    }

    public static HiddenStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new HiddenStateHandler(dynamicIslandTouchInteractor);
    }

    @Override // G0.a
    public HiddenStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
