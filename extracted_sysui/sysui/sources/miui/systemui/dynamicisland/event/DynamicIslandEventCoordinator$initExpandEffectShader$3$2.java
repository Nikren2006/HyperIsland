package miui.systemui.dynamicisland.event;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.dynamicisland.view.DynamicIslandExpandedView;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEventCoordinator$initExpandEffectShader$3$2 extends o implements Function1 {
    final /* synthetic */ DynamicIslandEventCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandEventCoordinator$initExpandEffectShader$3$2(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(1);
        this.this$0 = dynamicIslandEventCoordinator;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return s.f314a;
    }

    public final void invoke(String it) {
        DynamicIslandExpandedView expandedView;
        n.g(it, "it");
        DynamicIslandContentView lastExpandedView = this.this$0.getExpandedStateHandler().getLastExpandedView();
        if (lastExpandedView == null || (expandedView = lastExpandedView.getExpandedView()) == null) {
            return;
        }
        expandedView.stopGlowEffect$miui_dynamicisland_release(true);
    }
}
