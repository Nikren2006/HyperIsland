package miui.systemui.flashlight;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$seekBarTouchLength$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$seekBarTouchLength$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Integer invoke() {
        return Integer.valueOf(this.this$0.context.getResources().getDimensionPixelSize(R.dimen.mi_seekbar_bg_height));
    }
}
