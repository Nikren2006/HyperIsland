package miui.systemui.controlcenter.panel;

import H0.s;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class SecondaryPanelRouter$onGlobalLayoutListener$1$onGlobalLayout$2 extends o implements Function0 {
    final /* synthetic */ SecondaryPanelRouter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecondaryPanelRouter$onGlobalLayoutListener$1$onGlobalLayout$2(SecondaryPanelRouter secondaryPanelRouter) {
        super(0);
        this.this$0 = secondaryPanelRouter;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m84invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m84invoke() {
        this.this$0.performComplete();
    }
}
