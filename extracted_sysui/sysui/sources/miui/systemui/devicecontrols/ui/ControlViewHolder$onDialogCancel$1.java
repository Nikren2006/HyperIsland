package miui.systemui.devicecontrols.ui;

import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlViewHolder$onDialogCancel$1 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ ControlViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlViewHolder$onDialogCancel$1(ControlViewHolder controlViewHolder) {
        super(0);
        this.this$0 = controlViewHolder;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m124invoke();
        return H0.s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m124invoke() {
        this.this$0.lastChallengeDialog = null;
    }
}
