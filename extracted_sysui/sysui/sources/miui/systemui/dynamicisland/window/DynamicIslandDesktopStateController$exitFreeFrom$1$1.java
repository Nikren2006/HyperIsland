package miui.systemui.dynamicisland.window;

import H0.s;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$exitFreeFrom$1$1", f = "DynamicIslandDesktopStateController.kt", l = {}, m = "invokeSuspend")
public final class DynamicIslandDesktopStateController$exitFreeFrom$1$1 extends N0.l implements Function2 {
    final /* synthetic */ String $it;
    int label;
    final /* synthetic */ DynamicIslandDesktopStateController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandDesktopStateController$exitFreeFrom$1$1(DynamicIslandDesktopStateController dynamicIslandDesktopStateController, String str, L0.d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandDesktopStateController;
        this.$it = str;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new DynamicIslandDesktopStateController$exitFreeFrom$1$1(this.this$0, this.$it, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, L0.d dVar) {
        return ((DynamicIslandDesktopStateController$exitFreeFrom$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        DynamicIslandWindowViewController windowViewController;
        DynamicIslandSafeguardsController dynamicIslandSafeguardsController;
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        DynamicIslandWindowView dynamicIslandWindowView = (DynamicIslandWindowView) this.this$0.windowViewWeakReference.get();
        if (dynamicIslandWindowView != null && (windowViewController = dynamicIslandWindowView.getWindowViewController()) != null && (dynamicIslandSafeguardsController = windowViewController.getDynamicIslandSafeguardsController()) != null) {
            DynamicIslandSafeguardsController.delayExitMiniWindow$default(dynamicIslandSafeguardsController, this.$it, false, 2, null);
        }
        return s.f314a;
    }
}
