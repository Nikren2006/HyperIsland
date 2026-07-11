package miui.systemui.dynamicisland.anim;

import F0.d;
import F0.e;
import miui.systemui.dynamicisland.anim.DynamicIslandAnimationDelegate;
import miui.systemui.dynamicisland.window.DynamicIslandWindowState;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationController_Factory implements e {
    private final G0.a animationDelegateFactoryProvider;
    private final G0.a dynamicIslandWindowStateProvider;
    private final G0.a eventCoordinatorProvider;

    public DynamicIslandAnimationController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.eventCoordinatorProvider = aVar;
        this.dynamicIslandWindowStateProvider = aVar2;
        this.animationDelegateFactoryProvider = aVar3;
    }

    public static DynamicIslandAnimationController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new DynamicIslandAnimationController_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandAnimationController newInstance(E0.a aVar, DynamicIslandWindowState dynamicIslandWindowState, DynamicIslandAnimationDelegate.Factory factory) {
        return new DynamicIslandAnimationController(aVar, dynamicIslandWindowState, factory);
    }

    @Override // G0.a
    public DynamicIslandAnimationController get() {
        return newInstance(d.a(this.eventCoordinatorProvider), (DynamicIslandWindowState) this.dynamicIslandWindowStateProvider.get(), (DynamicIslandAnimationDelegate.Factory) this.animationDelegateFactoryProvider.get());
    }
}
