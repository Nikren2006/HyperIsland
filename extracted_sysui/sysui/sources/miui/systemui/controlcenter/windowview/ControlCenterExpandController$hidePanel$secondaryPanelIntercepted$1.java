package miui.systemui.controlcenter.windowview;

import H0.s;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterExpandController$hidePanel$secondaryPanelIntercepted$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ ControlCenterExpandController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterExpandController$hidePanel$secondaryPanelIntercepted$1(ControlCenterExpandController controlCenterExpandController) {
        super(0);
        this.this$0 = controlCenterExpandController;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m99invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m99invoke() {
        this.this$0.handleAnimFinish();
    }
}
