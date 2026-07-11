package miui.systemui.dynamicisland.event;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandEventCoordinator$initExpandEffectShader$3$5 extends o implements Function1 {
    final /* synthetic */ DynamicIslandEventCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandEventCoordinator$initExpandEffectShader$3$5(DynamicIslandEventCoordinator dynamicIslandEventCoordinator) {
        super(1);
        this.this$0 = dynamicIslandEventCoordinator;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return s.f314a;
    }

    public final void invoke(String it) {
        n.g(it, "it");
        if (this.this$0.collapseAnimationRunningCount > 0) {
            DynamicIslandEventCoordinator dynamicIslandEventCoordinator = this.this$0;
            dynamicIslandEventCoordinator.collapseAnimationRunningCount--;
        }
        this.this$0.collapseAnimationRunning = false;
    }
}
