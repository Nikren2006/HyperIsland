package miui.systemui.dynamicisland.event.handler;

import F0.e;
import android.content.Context;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class SmallIslandStateHandler_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a touchInteractorProvider;

    public SmallIslandStateHandler_Factory(G0.a aVar, G0.a aVar2) {
        this.touchInteractorProvider = aVar;
        this.contextProvider = aVar2;
    }

    public static SmallIslandStateHandler_Factory create(G0.a aVar, G0.a aVar2) {
        return new SmallIslandStateHandler_Factory(aVar, aVar2);
    }

    public static SmallIslandStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor, Context context) {
        return new SmallIslandStateHandler(dynamicIslandTouchInteractor, context);
    }

    @Override // G0.a
    public SmallIslandStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get(), (Context) this.contextProvider.get());
    }
}
