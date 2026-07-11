package miui.systemui.devicecontrols.ui;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public final class MiuiControlsUiControllerImpl$listingCallback$1$onServicesUpdated$3 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ MiuiControlsUiControllerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiControlsUiControllerImpl$listingCallback$1$onServicesUpdated$3(MiuiControlsUiControllerImpl miuiControlsUiControllerImpl) {
        super(0);
        this.this$0 = miuiControlsUiControllerImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m127invoke();
        return H0.s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m127invoke() {
        this.this$0.updateHeader();
    }
}
