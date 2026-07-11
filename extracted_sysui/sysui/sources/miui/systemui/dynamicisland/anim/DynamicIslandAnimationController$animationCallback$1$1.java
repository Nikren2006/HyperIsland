package miui.systemui.dynamicisland.anim;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandAnimationController$animationCallback$1$1 extends o implements Function1 {
    final /* synthetic */ DynamicIslandAnimationController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandAnimationController$animationCallback$1$1(DynamicIslandAnimationController dynamicIslandAnimationController) {
        super(1);
        this.this$0 = dynamicIslandAnimationController;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return s.f314a;
    }

    public final void invoke(String it) {
        n.g(it, "it");
        this.this$0.setAnimatorRunning(true);
        this.this$0.animatorSet.add(it);
    }
}
