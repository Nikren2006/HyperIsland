package miui.systemui.dynamicisland.window.domain.interactor;

import F0.d;
import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchRegionInteractor_Factory implements e {
    private final a contextProvider;
    private final a eventCoordinatorLazyProvider;
    private final a scopeProvider;
    private final a windowViewControllerLazyProvider;
    private final a windowViewProvider;

    public DynamicIslandTouchRegionInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.windowViewProvider = aVar3;
        this.windowViewControllerLazyProvider = aVar4;
        this.eventCoordinatorLazyProvider = aVar5;
    }

    public static DynamicIslandTouchRegionInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        return new DynamicIslandTouchRegionInteractor_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static DynamicIslandTouchRegionInteractor newInstance(E e2, Context context, DynamicIslandWindowView dynamicIslandWindowView, E0.a aVar, E0.a aVar2) {
        return new DynamicIslandTouchRegionInteractor(e2, context, dynamicIslandWindowView, aVar, aVar2);
    }

    @Override // G0.a
    public DynamicIslandTouchRegionInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), d.a(this.windowViewControllerLazyProvider), d.a(this.eventCoordinatorLazyProvider));
    }
}
