package miui.systemui.dynamicisland.event.handler;

import F0.e;
import android.content.Context;
import miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandTouchInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class BigIslandStateHandler_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a touchInteractorProvider;

    public BigIslandStateHandler_Factory(G0.a aVar, G0.a aVar2) {
        this.touchInteractorProvider = aVar;
        this.contextProvider = aVar2;
    }

    public static BigIslandStateHandler_Factory create(G0.a aVar, G0.a aVar2) {
        return new BigIslandStateHandler_Factory(aVar, aVar2);
    }

    public static BigIslandStateHandler newInstance(DynamicIslandTouchInteractor dynamicIslandTouchInteractor, Context context) {
        return new BigIslandStateHandler(dynamicIslandTouchInteractor, context);
    }

    @Override // G0.a
    public BigIslandStateHandler get() {
        return newInstance((DynamicIslandTouchInteractor) this.touchInteractorProvider.get(), (Context) this.contextProvider.get());
    }
}
