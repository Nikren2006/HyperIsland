package miui.systemui.dynamicisland.touch.dagger;

import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchDispatcher;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public interface DynamicIslandTouchModule {
    DynamicIslandStartable bindsTouchDispatcherAsStartable(DynamicIslandExternalTouchDispatcher dynamicIslandExternalTouchDispatcher);

    DynamicIslandStartable bindsTouchHandlerAsStartable(DynamicIslandTouchInteractor dynamicIslandTouchInteractor);

    DynamicIslandStartable bindsWindowViewTouchInteractorAsStartable(DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor);
}
