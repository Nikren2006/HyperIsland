package miui.systemui.dynamicisland.window;

import g1.E;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandSafeguardsController_Factory implements F0.e {
    private final G0.a scopeProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a windowViewControllerLazyProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandSafeguardsController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.windowViewProvider = aVar;
        this.uiExecutorProvider = aVar2;
        this.scopeProvider = aVar3;
        this.windowViewControllerLazyProvider = aVar4;
    }

    public static DynamicIslandSafeguardsController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new DynamicIslandSafeguardsController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DynamicIslandSafeguardsController newInstance(DynamicIslandWindowView dynamicIslandWindowView, DelayableExecutor delayableExecutor, E e2, E0.a aVar) {
        return new DynamicIslandSafeguardsController(dynamicIslandWindowView, delayableExecutor, e2, aVar);
    }

    @Override // G0.a
    public DynamicIslandSafeguardsController get() {
        return newInstance((DynamicIslandWindowView) this.windowViewProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), (E) this.scopeProvider.get(), F0.d.a(this.windowViewControllerLazyProvider));
    }
}
