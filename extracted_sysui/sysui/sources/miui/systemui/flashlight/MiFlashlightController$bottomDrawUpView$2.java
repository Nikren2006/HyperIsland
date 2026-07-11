package miui.systemui.flashlight;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.flashlight.view.MiBottomDrawUpView;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$bottomDrawUpView$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$bottomDrawUpView$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final MiBottomDrawUpView invoke() {
        return (MiBottomDrawUpView) this.this$0.findViewById(R.id.mi_bottom_draw_up_view);
    }
}
