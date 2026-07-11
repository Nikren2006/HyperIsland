package miui.systemui.dynamicisland.touch.domain.interactor;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandExternalTouchInteractor_Factory implements e {
    private final a contextProvider;
    private final a scopeProvider;
    private final a sysUIContextProvider;
    private final a touchRegionInteractorProvider;

    public DynamicIslandExternalTouchInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.sysUIContextProvider = aVar3;
        this.touchRegionInteractorProvider = aVar4;
    }

    public static DynamicIslandExternalTouchInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new DynamicIslandExternalTouchInteractor_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DynamicIslandExternalTouchInteractor newInstance(E e2, Context context, Context context2, DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor) {
        return new DynamicIslandExternalTouchInteractor(e2, context, context2, dynamicIslandTouchRegionInteractor);
    }

    @Override // G0.a
    public DynamicIslandExternalTouchInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (Context) this.sysUIContextProvider.get(), (DynamicIslandTouchRegionInteractor) this.touchRegionInteractorProvider.get());
    }
}
