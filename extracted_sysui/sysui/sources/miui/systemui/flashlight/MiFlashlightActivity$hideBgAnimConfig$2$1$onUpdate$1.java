package miui.systemui.flashlight;

import H0.s;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.util.DeviceUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$hideBgAnimConfig$2$1$onUpdate$1 extends o implements Function1 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$hideBgAnimConfig$2$1$onUpdate$1(MiFlashlightActivity miFlashlightActivity) {
        super(1);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).floatValue());
        return s.f314a;
    }

    public final void invoke(float f2) {
        if (DeviceUtils.isLowEndDevice()) {
            return;
        }
        this.this$0.getFlashlightLayout().setAlpha(f2);
    }
}
