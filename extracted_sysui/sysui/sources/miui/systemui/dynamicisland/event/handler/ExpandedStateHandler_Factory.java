package miui.systemui.dynamicisland.event.handler;

import F0.e;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class ExpandedStateHandler_Factory implements e {
    private final G0.a touchInteractorProvider;

    public ExpandedStateHandler_Factory(G0.a aVar) {
        this.touchInteractorProvider = aVar;
    }

    public static ExpandedStateHandler_Factory create(G0.a aVar) {
        return new ExpandedStateHandler_Factory(aVar);
    }

    public static ExpandedStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor) {
        return new ExpandedStateHandler(dynamicIslandTouchInteractor);
    }

    @Override // G0.a
    public ExpandedStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get());
    }
}
